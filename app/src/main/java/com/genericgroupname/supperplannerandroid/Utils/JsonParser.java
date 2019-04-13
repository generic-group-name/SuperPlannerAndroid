package com.genericgroupname.supperplannerandroid.Utils;

import android.content.Context;

import com.genericgroupname.supperplannerandroid.User.Sex;
import com.genericgroupname.supperplannerandroid.User.ThemeName;
import com.genericgroupname.supperplannerandroid.User.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;

public class JsonParser {

    public JsonParser(Context context) {
        this.context = context;
    }

    private Context context;

    public void saveJson(User user) throws JSONException, IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", user.getName());
        jsonObject.put("age", user.getAge());
        jsonObject.put("weight", user.getWeight());
        jsonObject.put("height", user.getHeight());
        jsonObject.put("sex", user.getSex().getName());
        jsonObject.put("theme", user.getThemeName().getThemeName());
        jsonObject.put("eye", user.getPreferredEye());
        jsonObject.put("back", user.getPreferredBack());
        jsonObject.put("mind", user.getPreferredPsycho());
        jsonObject.put("number",user.getNumberOfTrainingDay());
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream
                (new File(context.getCacheDir(), "") + File.separator + "cacheFile.srl"));
        out.writeObject(jsonObject.toString());
        out.close();
    }

    public JSONObject readObject() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream
                    (new File(context.getCacheDir() + File.separator + "cacheFile.srl")));
            JSONObject jsonObject = new JSONObject((String) in.readObject());
            in.close();
            return jsonObject;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (OptionalDataException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUser() throws JSONException, IOException {
        JSONObject jsonObject = readObject();
        if(jsonObject != null) {
            Sex sex;
            ThemeName themeName;
            if (jsonObject.getString("sex").equals(Sex.MAN.getName()))
                sex = Sex.MAN;
            else
                sex = Sex.WOMAN;
            if (jsonObject.getString("theme").equals(ThemeName.LIGHT.getThemeName()))
                themeName = ThemeName.LIGHT;
            else
                themeName = ThemeName.DARK;


            return new User(jsonObject.getString("name"),
                    jsonObject.getInt("age"),
                    jsonObject.getDouble("weight"),
                    jsonObject.getDouble("height"),
                    sex, themeName,
                    jsonObject.getBoolean("eye"),
                    jsonObject.getBoolean("back"),
                    jsonObject.getBoolean("mind"),
                    jsonObject.getInt("number")

            );
        }
        else {
           return null;
        }
    }
}
