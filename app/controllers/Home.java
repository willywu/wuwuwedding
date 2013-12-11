package controllers;

import play.mvc.*;

import views.html.*;

public class Home extends Controller {

    public static Result index() {
        return ok(template_bootstrap.render());
    }

}