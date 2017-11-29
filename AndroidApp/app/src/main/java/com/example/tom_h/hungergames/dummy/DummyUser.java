package com.example.tom_h.hungergames.dummy;

import java.util.ArrayList;
import java.util.List;

public class DummyUser {
    public static final List<Object> user = new ArrayList<>();

    static {
        // Add some sample items.
        user.add("John Doe");
        user.add("johndoe@gmail.com");
        List<String> preferences = new ArrayList<String>();
        preferences.add("Pizza");
        preferences.add("Burger");
        preferences.add("Noodles");
        user.add(preferences);
    }
}
