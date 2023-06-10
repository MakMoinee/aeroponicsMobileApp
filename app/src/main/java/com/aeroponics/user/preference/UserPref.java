package com.aeroponics.user.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.aeroponics.user.activities.models.Users;

public class UserPref {
    Context mContext;
    SharedPreferences pref;

    public UserPref(Context mContext) {
        this.mContext = mContext;
        this.pref = this.mContext.getSharedPreferences("users", Context.MODE_PRIVATE);
    }

    public void storeUser(Users users) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("documentID", users.getDocumentID());
        editor.putString("email", users.getEmail());
        editor.putString("firstName", users.getFirstName());
        editor.putString("lastName", users.getLastName());
        editor.putString("phoneNumber", users.getPhoneNumber());
        editor.putString("pictureURI", users.getPictureURI());
        editor.commit();
        editor.apply();
    }

    public Users getUsers() {
        Users users = new Users.UserBuilder()
                .setDocumentID(pref.getString("documentID", ""))
                .setEmail(pref.getString("email", ""))
                .setFirstName(pref.getString("firstName", ""))
                .setLastName(pref.getString("lastName", ""))
                .setPhoneNumber(pref.getString("phoneNumber", ""))
                .setPictureURI(pref.getString("pictureURI", ""))
                .build();

        return users;
    }
}
