package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class GuestBook extends Controller {

    public static Result index() {
        return ok(guestbook.render("Babby.  I love you."));
    }

}