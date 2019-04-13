package com.genericgroupname.supperplannerandroid.User;

import com.genericgroupname.supperplannerandroid.Utils.Names;

public enum Sex {
    MAN("man"),WOMAN("woman");
    private String name;

    Sex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
