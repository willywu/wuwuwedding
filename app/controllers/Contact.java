package controllers;

import play.mvc.*;

import views.html.*;

public class Contact extends Controller {

    public static Result index() {
        return ok(contact.render("Babby.  I love you."));
    }

}