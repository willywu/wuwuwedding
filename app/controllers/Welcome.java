package controllers;

import main.Template;
import play.mvc.*;

import views.html.*;

public class Welcome extends Controller {

    public static Result index() {
        if (Template.get().equals(Template.GRAY)) {
            return ok(welcome.render("Hello"));
        } else if (Template.get().equals(Template.WHITE)) {
            return ok(welcome_white.render("Hello"));
        } else if (Template.get().equals(Template.SOPHIE)) {
            return ok(welcome_sophie.render("Hello"));
        } else {
            throw new IllegalArgumentException("Didn't understand template type " + Template.get());
        }
    }

}