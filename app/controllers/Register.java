package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.register_mail;

public class Register extends Controller {

    public static Result index() {
        return ok(register_mail.render());
    }

}