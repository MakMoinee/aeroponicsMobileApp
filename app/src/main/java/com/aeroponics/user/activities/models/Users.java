package com.aeroponics.user.activities.models;

import lombok.Data;

@Data
public class Users {
    private String documentID;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String pictureURI;

    public Users() {
    }

    public Users(UserBuilder builder) {
        this.documentID=builder.documentID;
        this.email=builder.email;
        this.password=builder.password;
        this.firstName=builder.firstName;
        this.lastName=builder.lastName;
        this.phoneNumber=builder.phoneNumber;
        this.pictureURI=builder.pictureURI;
    }


    public static class UserBuilder{
        private String documentID;
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String pictureURI;

        public UserBuilder() {
        }

        public UserBuilder setDocumentID(String documentID) {
            this.documentID = documentID;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder setPictureURI(String pictureURI) {
            this.pictureURI = pictureURI;
            return this;
        }

        public Users build(){
            return new Users(this);
        }
    }
}
