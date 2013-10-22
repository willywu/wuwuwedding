package main;

/**
 * List of different themes for the site
 */
public enum Template {
    GRAY(),
    WHITE(),
    SOPHIE();

    /** Get the currently active template */
    public static Template get() {
        return SOPHIE;
    }
}
