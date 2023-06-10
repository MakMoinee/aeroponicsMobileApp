package com.aeroponics.user.activities.models;

import java.util.Map;

import lombok.Data;

@Data
public class FirebaseRequestBody {
    private String collectionName;
    private String documentID;
    private Map<String, Object> param;
    private String whereFromField;
    private String whereValueField;
    private String email;

    public FirebaseRequestBody() {
    }

    public FirebaseRequestBody(FirebaseRequestBodyBuilder builder) {
        this.collectionName = builder.collectionName;
        this.documentID = builder.documentID;
        this.param = builder.param;
        this.whereFromField = builder.whereFromField;
        this.whereValueField = builder.whereValueField;
        this.email = builder.email;
    }

    public static class FirebaseRequestBodyBuilder {
        private String collectionName;
        private String documentID;
        private Map<String, Object> param;
        private String whereFromField;
        private String whereValueField;
        private String email;

        public FirebaseRequestBodyBuilder() {
        }

        public FirebaseRequestBodyBuilder setCollectionName(String collectionName) {
            this.collectionName = collectionName;
            return this;
        }

        public FirebaseRequestBodyBuilder setDocumentID(String documentID) {
            this.documentID = documentID;
            return this;
        }

        public FirebaseRequestBodyBuilder setParam(Map<String, Object> param) {
            this.param = param;
            return this;
        }

        public FirebaseRequestBodyBuilder setWhereFromField(String whereFromField) {
            this.whereFromField = whereFromField;
            return this;
        }

        public FirebaseRequestBodyBuilder setWhereValueField(String whereValueField) {
            this.whereValueField = whereValueField;
            return this;
        }

        public FirebaseRequestBodyBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public FirebaseRequestBody build() {
            return new FirebaseRequestBody(this);
        }


    }
}
