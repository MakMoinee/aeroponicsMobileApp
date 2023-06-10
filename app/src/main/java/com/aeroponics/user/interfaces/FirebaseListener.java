package com.aeroponics.user.interfaces;

public interface FirebaseListener {
    <T> void onSuccess(T any);
    void onError();
}
