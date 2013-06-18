package controllers;

import play.mvc.*;

import views.html.*;

public class Registry extends Controller {

    public static Result index() {
        return ok(registry.render("Babby.  I love you."));
    }

}