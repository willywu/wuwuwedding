package controllers;

import play.mvc.*;

import views.html.*;

public class Story extends Controller {

    public static Result index() {
        return ok(story.render("Babby.  I love you."));
    }

}