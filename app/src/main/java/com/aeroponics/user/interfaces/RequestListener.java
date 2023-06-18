package com.aeroponics.user.interfaces;

public interface RequestListener {
    <T> void onSuccess(T any);

    void onError(Error error);
}
