package com.genericgroupname.supperplannerandroid.User;

import com.genericgroupname.supperplannerandroid.Utils.Names;

public enum ThemeName {
    DARK("dark"),LIGHT("light");
    private String themeName;

    ThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeName() {
        return themeName;
    }
}
