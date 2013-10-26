package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.register_mail_done;

public class RegisterDone extends Controller {

    public static Result index() {
        return ok(register_mail_done.render());
    }

}