package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avaje.ebean.Query;

import models.Guest;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.guest_all;

public class GuestController extends Controller {

    public static String getFirstParam(String paramName) {
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
        String name = getFirstParam("name");
        String rsvpCode = getFirstParam("rsvpCode");
        if (rsvpCode == null) {
            return redirect("/guests/all?error=bad_code");
        }
        Guest existingGuest = Guest.find.where().eq("rsvpCode", rsvpCode).findUnique();
        if (existingGuest != null) {
            return redirect("/guests/all?error=duplicate_code_"+rsvpCode);
        }
        Guest g = new Guest();
        g.setName(name);
        g.setRsvpCode(rsvpCode);
        g.save();
        return redirect("/guests/all");
    }

    public static Result delete() {
        String id = getFirstParam("id");
        Guest existingGuest = Guest.find.where().eq("id", Long.valueOf(id)).findUnique();
        if (existingGuest != null) {
            existingGuest.delete();
            return redirect("/guests/all?success=deleted_"+id);
        } else {
            return redirect("/guests/all?error=no_id_"+id);
        }
    }

    public static Result index() {
        List<Guest> guests = Guest.find.all();
        return ok(guest_all.render(guests));
    }

}
