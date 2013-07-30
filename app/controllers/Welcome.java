package controllers;

import main.Template;
import play.mvc.*;

import views.html.*;

public class Welcome extends Controller {

    public static Result index() {
        if (Template.get().equals(Template.GRAY)) {
            return ok(welcome.render("Babby.  I love you."));
        } else if (Template.get().equals(Template.WHITE)) {
            return ok(welcome_white.render("Babby.  I love you."));
        } else {
            throw new IllegalArgumentException("Didn't understand template type " + Template.get());
        }
    }

}