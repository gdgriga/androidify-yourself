package com.github.androidify;


import java.util.ArrayList;
import java.util.List;

public class AndroidDrawables {

    private static final List<Integer> bodies = new ArrayList<Integer>() {{
        add(R.drawable.body1);
        add(R.drawable.body2);
        add(R.drawable.body3);
        add(R.drawable.body4);
        add(R.drawable.body5);
        add(R.drawable.body6);
        add(R.drawable.body7);
        add(R.drawable.body8);
        add(R.drawable.body9);
        add(R.drawable.body10);
        add(R.drawable.body11);
        add(R.drawable.body12);
        add(R.drawable.body13);
        add(R.drawable.body14);
        add(R.drawable.body15);
        add(R.drawable.body16);
        add(R.drawable.body17);
        add(R.drawable.body18);
        add(R.drawable.body19);
    }};

    private static final List<Integer> heads = new ArrayList<Integer>() {{
        add(R.drawable.head1);
        add(R.drawable.head2);
        add(R.drawable.head3);
        add(R.drawable.head4);
        add(R.drawable.head5);
        add(R.drawable.head6);
        add(R.drawable.head7);
        add(R.drawable.head8);
        add(R.drawable.head9);
        add(R.drawable.head10);
        add(R.drawable.head11);
        add(R.drawable.head12);
        add(R.drawable.head13);
        add(R.drawable.head14);
        add(R.drawable.head15);
        add(R.drawable.head16);
        add(R.drawable.head17);
        add(R.drawable.head18);
        add(R.drawable.head19);
    }};

    private static final List<Integer> legs = new ArrayList<Integer>() {{
        add(R.drawable.legs1);
        add(R.drawable.legs2);
        add(R.drawable.legs3);
        add(R.drawable.legs4);
        add(R.drawable.legs5);
        add(R.drawable.legs6);
        add(R.drawable.legs7);
        add(R.drawable.legs8);
        add(R.drawable.legs10);
        add(R.drawable.legs11);
        add(R.drawable.legs12);
        add(R.drawable.legs13);
        add(R.drawable.legs14);
        add(R.drawable.legs15);
        add(R.drawable.legs16);
        add(R.drawable.legs17);
        add(R.drawable.legs18);
        add(R.drawable.legs19);
    }};

    public static List<Integer> getBodies() {
        return bodies;
    }

    public static List<Integer> getHeads() {
        return heads;
    }

    public static List<Integer> getLegs() {
        return legs;
    }
}
