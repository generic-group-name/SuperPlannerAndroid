package com.genericgroupname.supperplannerandroid.User;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class UserBuilder {
    private EditText name;
    private EditText age;
    private EditText weight;
    private EditText height;
    private CheckBox sexMan;
    private CheckBox sexWoman;
    private RadioButton themeLight;
    private RadioButton themeDark;
    private CheckBox preferredEye;
    private CheckBox preferredBack;
    private CheckBox preferredPsycho;

    public UserBuilder(EditText name, EditText age, EditText weight, EditText height, CheckBox sexMan, CheckBox sexWoman, RadioButton themeLight, RadioButton themeDark, CheckBox prefferedEye, CheckBox preferredBack, CheckBox preferredPsycho) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.sexMan = sexMan;
        this.sexWoman = sexWoman;
        this.themeLight = themeLight;
        this.themeDark = themeDark;
        this.preferredEye = prefferedEye;
        this.preferredBack = preferredBack;
        this.preferredPsycho = preferredPsycho;
    }

    public boolean checkInput() {
        return Integer.parseInt(this.age.getText().toString()) > 0 && Integer.parseInt(this.age.getText().toString()) < 120 &&
                Double.parseDouble(this.weight.getText().toString()) > 0.0 && Double.parseDouble(this.weight.getText().toString()) < 700.0 &&
                Double.parseDouble(this.height.getText().toString()) > 0.0 && Double.parseDouble(this.height.getText().toString()) < 250.0 &&
                !this.name.getText().toString().isEmpty() && (sexMan.isChecked() || sexWoman.isChecked()) && (themeLight.isChecked() || themeDark.isChecked());
    }

    public User build() throws Exception {
        String name = this.name.getText().toString();
        Integer age = Integer.parseInt(this.age.getText().toString());
        Double weight = Double.parseDouble(this.weight.getText().toString());
        Double height = Double.parseDouble(this.height.getText().toString());
        Boolean sexMan = this.sexMan.isChecked();
        Boolean sexWomen = this.sexWoman.isChecked();
        Boolean themeLight = this.themeLight.isChecked();
        Boolean themeDark = this.themeDark.isChecked();
        Boolean preferredEye = this.preferredEye.isChecked();
        Boolean preferredBack = this.preferredBack.isChecked();
        Boolean preferredPsycho = this.preferredPsycho.isChecked();

        if(!name.isEmpty() && age>0 && age <150 && weight>0.0 &&  weight< 700.0 && height > 30.0 && height<250.0 &&(sexMan||sexWomen)&&(themeLight||themeDark)){
            if(sexMan&&themeLight)
                return new User(name,age,weight,height,Sex.MAN,ThemeName.LIGHT,preferredEye,preferredBack,preferredPsycho,1);
           else if(sexMan)
                return new User(name,age,weight,height,Sex.MAN,ThemeName.DARK,preferredEye,preferredBack,preferredPsycho,1);
            else if(themeLight)
                return new User(name,age,weight,height,Sex.WOMAN,ThemeName.LIGHT,preferredEye,preferredBack,preferredPsycho,1);
            else
                return new User(name,age,weight,height,Sex.WOMAN,ThemeName.DARK,preferredEye,preferredBack,preferredPsycho,1);
        }
        else
            return null;

    }
}
