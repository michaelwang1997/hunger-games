package com.example.tom_h.hungergames.dummy;

import java.util.ArrayList;
import java.util.List;
/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContentMyEvents {
    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> MyEvents = new ArrayList<DummyItem>();

    static {
        // Add some sample items.
        DummyItem burger = new DummyItem("@drawable/burger",
                "CSSU Pub Event", "Feeds: 3-5",
                "40 St George St",
                "There are lots of cheeseburgers at 2270! Come get them before they run out!");

        MyEvents.add(burger);
    }
}
