package com.example.tom_h.hungergames.dummy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MichaelWang on 2017-11-27.
 */

public class DummyUser {
    public static final List<Object> user = new ArrayList<>();

    static {
        // Add some sample items.
        user.add("Michael Wang");
        user.add("wangmichael1997@gmail.com");
        List<String> preferences = new ArrayList<String>();
        preferences.add("Pizza");
        preferences.add("Burger");
        preferences.add("Chinese");
        preferences.add("Thai");
        preferences.add("Noodles");
        user.add(preferences);
    }
}
