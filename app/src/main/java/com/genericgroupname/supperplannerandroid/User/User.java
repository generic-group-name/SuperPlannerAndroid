package com.genericgroupname.supperplannerandroid.User;

import com.genericgroupname.supperplannerandroid.Utils.JsonParser;

import org.json.JSONException;

import java.io.IOException;

public class User {
    final private String name;
    final private Integer age;
    final private Double weight;
    final private Double height;
    final private Sex sex;
    final private ThemeName themeName;
    final private Boolean preferredEye;
    final private Boolean preferredBack;
    final private Boolean preferredPsycho;
    private JsonParser jsonParser;

    public User(String name, Integer age, Double weight, Double height, Sex sex, ThemeName themeName, Boolean preferredEye, Boolean preferredBack, Boolean preferredPsycho) throws IOException, JSONException {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.sex = sex;
        this.themeName = themeName;
        this.preferredEye = preferredEye;
        this.preferredBack = preferredBack;
        this.preferredPsycho = preferredPsycho;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getHeight() {
        return height;
    }

    public Sex getSex() {
        return sex;
    }

    public ThemeName getThemeName() {
        return themeName;
    }

    public Boolean getPreferredEye() {
        return preferredEye;
    }

    public Boolean getPreferredBack() {
        return preferredBack;
    }

    public Boolean getPreferredPsycho() {
        return preferredPsycho;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                ", sex=" + sex +
                ", themeName=" + themeName +
                ", preferredEye=" + preferredEye +
                ", preferredBack=" + preferredBack +
                ", preferredPsycho=" + preferredPsycho +
                ", jsonParser=" + jsonParser +
                '}';
    }
}
