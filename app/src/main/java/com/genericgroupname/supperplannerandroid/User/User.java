package com.genericgroupname.supperplannerandroid.User;

public class User {
    private Long id;
    private String name;
    private Integer age;
    private Double weight;
    private Double height;
    private Sex sex;
    private ThemeName themeName;
    private Boolean prefferedEye;
    private Boolean prefferedBack;
    private Boolean prefferedPsycho;

    public User(Long id, String name, Integer age, Double weight, Double height, Sex sex, ThemeName themeName, Boolean preferredEye, Boolean preferredBack, Boolean preferredPsycho) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.sex = sex;
        this.themeName = themeName;
        this.prefferedEye = preferredEye;
        this.prefferedBack = preferredBack;
        this.prefferedPsycho = preferredPsycho;
    }
}
