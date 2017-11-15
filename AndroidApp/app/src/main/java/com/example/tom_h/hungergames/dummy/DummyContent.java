package com.example.tom_h.hungergames.dummy;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {
    Context context;
    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */

    static {
        // Add some sample items.
        DummyItem burger = new DummyItem("@drawable/burger",
                "CSSU Pub Event", "Feeds: 3-5",
                "40 St George St",
                "There are lots of cheeseburgers at 2270! Come get them before they run out!");
        DummyItem burger1 = new DummyItem("@drawable/burger",
                "Robarts Study Event", "Feeds: 10-20",
                "100 St George St",
                "There are lots of cheeseburgers at floor 2! Come get them before they run out!");
        DummyItem burger2 = new DummyItem("@drawable/burger",
                "CSSU Pub Event", "Feeds: 3-5",
                "40 St George St",
                "There are lots of cheeseburgers at 2270! Come get them before they run out!");
        DummyItem burger3 = new DummyItem("@drawable/burger",
                "Robarts Study Event", "Feeds: 10-20",
                "100 St George St",
                "There are lots of cheeseburgers at floor 2! Come get them before they run out!");
        DummyItem burger4 = new DummyItem("@drawable/burger",
                "CSSU Pub Event", "Feeds: 3-5",
                "40 St George St",
                "There are lots of cheeseburgers at 2270! Come get them before they run out!");
        DummyItem burger5 = new DummyItem("@drawable/burger",
                "Robarts Study Event", "Feeds: 10-20",
                "100 St George St",
                "There are lots of cheeseburgers at floor 2! Come get them before they run out!");
        DummyItem burger6 = new DummyItem("@drawable/burger",
                "CSSU Pub Event", "Feeds: 3-5",
                "40 St George St",
                "There are lots of cheeseburgers at 2270! Come get them before they run out!");
        DummyItem burger7 = new DummyItem("@drawable/burger",
                "Robarts Study Event", "Feeds: 10-20",
                "100 St George St",
                "There are lots of cheeseburgers at floor 2! Come get them before they run out!");
        DummyItem burger8 = new DummyItem("@drawable/burger",
                "CSSU Pub Event", "Feeds: 3-5",
                "40 St George St",
                "There are lots of cheeseburgers at 2270! Come get them before they run out!");
        DummyItem burger9 = new DummyItem("@drawable/burger",
                "Robarts Study Event", "Feeds: 10-20",
                "100 St George St",
                "There are lots of cheeseburgers at floor 2! Come get them before they run out!");
        ITEMS.add(burger);
        ITEMS.add(burger1);
        ITEMS.add(burger2);
        ITEMS.add(burger3);
        ITEMS.add(burger4);
        ITEMS.add(burger5);
        ITEMS.add(burger6);
        ITEMS.add(burger7);
        ITEMS.add(burger8);
        ITEMS.add(burger9);

    }


    private static DummyItem createDummyItem(String picture, String name, String amount, String location, String description) {
        return new DummyItem(picture, name, amount, location, description);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String picture;
        public final String name;
        public final String amount;
        public final String location;
        public final String description;


        public DummyItem(String picture, String name, String amount, String location, String description) {
            this.picture = picture;
            this.name = name;
            this.amount = amount;
            this.location = location;
            this.description = description;
        }

    }}
