package com.example.intibotbuddy_admin;

/**
 * Created by TIAN☺ on 02/04/2018.
 */

public class Info {
    private String title;
    private String info1i;
    private String info2i;
    private String info3i;

    public Info(String title, String info1i, String info2i, String info3i) {
        this.title = title;
        this.info1i = info1i;
        this.info2i = info2i;
        this.info3i = info3i;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo1i() {
        return info1i;
    }

    public String getInfo2i() {
        return info2i;
    }

    public String getInfo3i() {
        return info3i;
    }
}
