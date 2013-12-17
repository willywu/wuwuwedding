package controllers;

import java.util.Map;

import play.mvc.*;

import views.html.*;

public class Home extends Controller {

    public static Result index() {
        Map<String,String[]> qs = request().queryString();
        if (qs.get("plain") != null) {
            return ok(template_bootstrap.render());
        } else if (qs.get("responsive") != null) {
            return ok(template_responsive.render());
        } else {
            return ok(template_sophie2.render());
        }
    }

}