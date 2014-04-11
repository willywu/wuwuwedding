package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.node.ObjectNode;

import models.Guest;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.guest_all;

public class GuestController extends Controller {

    public enum GuestField {
        ID("id"),
        GUEST_ONE_NAME_SINGLE("guestOneNameSingle"),
        GUEST_ONE_NAME_DOUBLE("guestOneNameDouble"),
        GUEST_TWO_NAME_DOUBLE("guestTwoNameDouble"),
        HAS_EXTRA_GUEST("hasExtraGuest"),
        RSVP_CODE("rsvpCode"),
        PHONE_NUMBER("phoneNumber"),
        EMAIL("email"),
        COMMENTS("comments");

        /** the name of the param in HTTP calls, and also the name of the field in the Model */
        private final String paramName;

        private GuestField(String paramName) {
            this.paramName = paramName;
        }

        public String getName() {
            return this.paramName;
        }
    }

    /** Only allow access to the admin pages if the environment variable is set */
    public static boolean isAdminEnabled() {
        return System.getenv("WUWUWEDDING_ADMIN") != null;
    }

    /** Get the first parameter with the given name, either from query string or post body */
    public static String getParameter(String paramName) {
        Map<String, String[]> allParams = new HashMap<String, String[]>();
        allParams.putAll(request().queryString());
        if (request().body().asFormUrlEncoded() != null) {
            allParams.putAll(request().body().asFormUrlEncoded());
        }
        String[] values = allParams.get(paramName);
        if (values != null && values.length > 0) {
            return values[0];
        } else {
            return null;
        }
    }

    public static Result create() {
        if (!isAdminEnabled()) {
            return redirect("/");
        }
        String nameOne = getParameter(GuestField.GUEST_ONE_NAME_DOUBLE.getName());
        String nameTwo = getParameter(GuestField.GUEST_TWO_NAME_DOUBLE.getName());
        String extraGuest = getParameter(GuestField.HAS_EXTRA_GUEST.getName());
        String rsvpCode = getParameter(GuestField.RSVP_CODE.getName());
        if (nameOne == null || rsvpCode == null) {
            return redirect("/guests/all?error=bad_input");
        }
        Guest existingGuest = Guest.find.where().eq(GuestField.RSVP_CODE.getName(), rsvpCode).findUnique();
        if (existingGuest != null) {
            return redirect("/guests/all?error=duplicate_code_for_"+existingGuest.getId());
        }
        Guest g = new Guest();
        g.setGuestOneName(nameOne);
        g.setGuestTwoName(nameTwo);
        g.setHasExtraGuest(Boolean.valueOf(extraGuest));
        g.setRsvpCode(rsvpCode);
        g.save();
        return redirect("/guests/all");
    }

    public static Result delete() {
        if (!isAdminEnabled()) {
            return redirect("/");
        }
        String id = getParameter(GuestField.ID.getName());
        Guest existingGuest = Guest.find.where().eq(GuestField.ID.getName(), Long.valueOf(id)).findUnique();
        if (existingGuest != null) {
            existingGuest.delete();
            return redirect("/guests/all?success=deleted_"+id);
        } else {
            return redirect("/guests/all?error=no_id_"+id);
        }
    }

    public static Result index() {
        if (!isAdminEnabled()) {
            return redirect("/");
        }
        List<Guest> guests = Guest.find.all();
        return ok(guest_all.render(guests));
    }

    public static Result checkRsvpCode() {
        String rsvpCode = getParameter(GuestField.RSVP_CODE.getName());
        Guest existingGuest = Guest.find.where().eq(GuestField.RSVP_CODE.name().toLowerCase(), rsvpCode).findUnique();
        ObjectNode result = Json.newObject();
        if (existingGuest == null) {
            result.put("status", "bad");
            result.put("guest", "null");
            return ok(result);
        } else {
            result.put("status", "good");
            result.put("guest", Json.toJson(existingGuest));
            return ok(result);
        }
    }

    public static Result updateGuests() {
        ObjectNode result = Json.newObject();
        String rsvpCode = getParameter(GuestField.RSVP_CODE.getName());
        Guest existingGuest = Guest.find.where().eq(GuestField.RSVP_CODE.name().toLowerCase(), rsvpCode).findUnique();
        if (existingGuest == null) {
            result.put("status", "bad");
            return ok(result);
        }

        String guestOne;
        String guestTwo;
        if (existingGuest.getHasExtraGuest()) {
            guestOne = getParameter(GuestField.GUEST_ONE_NAME_DOUBLE.getName());
            guestTwo = getParameter(GuestField.GUEST_TWO_NAME_DOUBLE.getName());
        } else {
            guestOne = getParameter(GuestField.GUEST_ONE_NAME_SINGLE.getName());
            guestTwo = null;
        }
        existingGuest.setGuestOneName(guestOne);
        existingGuest.setGuestTwoName(guestTwo);
        existingGuest.setEmail(getParameter(GuestField.EMAIL.getName()));
        existingGuest.setComments(getParameter(GuestField.COMMENTS.getName()));
        existingGuest.setPhoneNumber(getParameter(GuestField.PHONE_NUMBER.getName()));
        existingGuest.save();

        result.put("status", "good");
        return ok(result);
    }

}
