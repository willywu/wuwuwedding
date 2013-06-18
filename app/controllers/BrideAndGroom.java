package controllers;

import play.mvc.*;

import views.html.*;

public class BrideAndGroom extends Controller {

    public static Result index() {
        return ok(brideandgroom.render("Babby.  I love you."));
    }

}