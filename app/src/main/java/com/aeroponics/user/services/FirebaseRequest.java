package com.aeroponics.user.services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.aeroponics.user.activities.models.FirebaseRequestBody;
import com.aeroponics.user.interfaces.FirebaseListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class FirebaseRequest {

    public static final String USERS_COLLECTION = "users";
    public static final String EMAIL_STRING = "email";

    FirebaseFirestore db;

    public FirebaseRequest() {
        db = FirebaseFirestore.getInstance();
    }

    public void findAll(FirebaseRequestBody body, FirebaseListener listener) {
        CollectionReference collectionReference = db.collection(body.getCollectionName());
        Query query = null;
        if (!body.getWhereFromField().equals("") && !body.getWhereValueField().equals("")) {
            query = collectionReference.whereEqualTo(body.getWhereFromField(), body.getWhereValueField());
        }

        if (query != null) {
            query.get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        if (queryDocumentSnapshots.isEmpty()) {
                            listener.onError();
                        } else {
                            listener.onSuccess(queryDocumentSnapshots);
                        }
                    })
                    .addOnFailureListener(e -> {
                        if (e != null) {
                            Log.e("findAll_err", e.getLocalizedMessage());
                        }
                        listener.onError();
                    });
        } else {
            collectionReference.get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        if (queryDocumentSnapshots.isEmpty()) {
                            listener.onError();
                        } else {
                            listener.onSuccess(queryDocumentSnapshots);
                        }
                    })
                    .addOnFailureListener(e -> {
                        if (e != null) {
                            Log.e("findAll_err", e.getLocalizedMessage());
                        }
                        listener.onError();
                    });
        }
    }

    public void insertData(FirebaseRequestBody body, FirebaseListener listener) {
        this.findAll(body, new FirebaseListener() {
            @Override
            public <T> void onSuccess(T any) {
                listener.onError();
            }

            @Override
            public void onError() {
                String documentID = db.collection(body.getCollectionName()).document().getId();
                db.collection(body.getCollectionName())
                        .document(documentID)
                        .set(body.getParam())
                        .addOnSuccessListener(unused -> listener.onSuccess(documentID))
                        .addOnFailureListener(e -> {
                            if (e != null) {
                                Log.e("insertData_err", e.getLocalizedMessage());
                            }
                            listener.onError();
                        });
            }
        });
    }
}
