package com.ostapchuk.telegram.tourist.util;

public final class Const {

    private Const() {
        throw new UnsupportedOperationException();
    }

    // Endpoint
    public static final String CITIES_ENDPOINT = "/api/v1/cities";

    // String literal
    public static final String SLASH = "/";

    // Messages
    public static final String MSG_404 = "Could not find city with the given name: ";

    public static final String MSG_WELCOME = "Welcome to Tourist Adviser!";
}
