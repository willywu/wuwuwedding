package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Welcome extends Controller {

    public static Result index() {
        return ok(welcome.render("Babby.  I love you."));
    }

}