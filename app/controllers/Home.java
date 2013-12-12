package controllers;

import play.mvc.*;

import views.html.*;

public class Home extends Controller {

    public static Result index() {
        if (request().queryString().get("plain") != null) {
            return ok(template_bootstrap.render());
        }

        return ok(template_responsive.render());
    }

}