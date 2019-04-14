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
    private ThemeName themeName;
    final private Boolean preferredEye;
    final private Boolean preferredBack;
    final private Boolean preferredPsycho;
    private Integer numberOfTrainingDay;
    private Double firstDayGrade = 0.0;
    private Double secondDayGrade = 0.0;
    private Double thirdDayGrade = 0.0;
    private Double fourthDayGrade = 0.0;
    private Double fifthDayGrade = 0.0;

    private JsonParser jsonParser;

    public void setThemeName(ThemeName themeName) {
        this.themeName = themeName;
    }

    public User(String name, Integer age, Double weight, Double height, Sex sex, ThemeName themeName, Boolean preferredEye, Boolean preferredBack, Boolean preferredPsycho, Integer numberOfTrainingDay) throws IOException, JSONException {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.sex = sex;
        this.themeName = themeName;
        this.preferredEye = preferredEye;
        this.preferredBack = preferredBack;
        this.preferredPsycho = preferredPsycho;
        this.numberOfTrainingDay = numberOfTrainingDay;
    }

    public Integer getNumberOfTrainingDay() {
        return numberOfTrainingDay;
    }

    public void setNumberOfTrainingDay(Integer numberOfTrainingDay) {
        this.numberOfTrainingDay = numberOfTrainingDay;
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

    public Double getFirstDayGrade() {
        return firstDayGrade;
    }

    public void setFirstDayGrade(Double firstDayGrade) {
        this.firstDayGrade = firstDayGrade;
    }

    public Double getSecondDayGrade() {
        return secondDayGrade;
    }

    public void setSecondDayGrade(Double secondDayGrade) {
        this.secondDayGrade = secondDayGrade;
    }

    public Double getThirdDayGrade() {
        return thirdDayGrade;
    }

    public void setThirdDayGrade(Double thirdDayGrade) {
        this.thirdDayGrade = thirdDayGrade;
    }

    public Double getFourthDayGrade() {
        return fourthDayGrade;
    }

    public void setFourthDayGrade(Double fourthDayGrade) {
        this.fourthDayGrade = fourthDayGrade;
    }

    public Double getFifthDayGrade() {
        return fifthDayGrade;
    }

    public void setFifthDayGrade(Double fifthDayGrade) {
        this.fifthDayGrade = fifthDayGrade;
    }

    public double getWaterAmount() {
        return this.weight * 30;
    }
    public double getCofeineMax(){
        if (age > 18)
        {
            return 300;
        }
        else
        {
            return 100;
        }
    }
    public double getSugarMax(){
        if (sex == Sex.MAN)
        {
            return 37.5;
        }
        else
        {
            return 25;
        }
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
                ", numberOfTrainingDay=" + numberOfTrainingDay +
                ", jsonParser=" + jsonParser +
                '}';
    }
}
