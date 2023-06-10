package com.aeroponics.user.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.aeroponics.user.R;
import com.aeroponics.user.activities.models.FirebaseRequestBody;
import com.aeroponics.user.activities.models.Users;
import com.aeroponics.user.common.MapForm;
import com.aeroponics.user.databinding.ActivityGoogleSigninBinding;
import com.aeroponics.user.interfaces.FirebaseListener;
import com.aeroponics.user.preference.UserPref;
import com.aeroponics.user.services.FirebaseRequest;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class GoogleSigninActivity extends AppCompatActivity {

    ActivityGoogleSigninBinding binding;

    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    ActivityResultLauncher<IntentSenderRequest> oneTapLauncher;
    FirebaseRequest request;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGoogleSigninBinding.inflate(getLayoutInflater());
        request = new FirebaseRequest();
        setContentView(binding.getRoot());
        setSignIn();
    }

    private void setSignIn() {
        oneTapLauncher = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                SignInCredential credential = null;
                try {
                    credential = oneTapClient.getSignInCredentialFromIntent(result.getData());
                    String idToken = credential.getGoogleIdToken();
                    String username = credential.getId();
                    String password = credential.getPassword();

                    if (idToken != null) {

                        String pic = "";
                        if (credential.getProfilePictureUri() != null) {
                            pic = credential.getProfilePictureUri().toString();
                        }

                        Users users = new Users.UserBuilder()
                                .setEmail(username)
                                .setFirstName(credential.getGivenName())
                                .setLastName(credential.getFamilyName())
                                .setPictureURI(pic)
                                .setPassword("default")
                                .setPhoneNumber(credential.getPhoneNumber())
                                .build();

                        FirebaseRequestBody body = new FirebaseRequestBody.FirebaseRequestBodyBuilder()
                                .setCollectionName(FirebaseRequest.USERS_COLLECTION)
                                .setWhereFromField(FirebaseRequest.EMAIL_STRING)
                                .setWhereValueField(username)
                                .setParam(MapForm.convertObjectToMap(users))
                                .build();

                        request.findAll(body, new FirebaseListener() {
                            @Override
                            public <T> void onSuccess(T any) {
                                if (any instanceof QuerySnapshot) {
                                    QuerySnapshot snapshots = (QuerySnapshot) any;
                                    Users u = null;
                                    for (QueryDocumentSnapshot documentSnapshot : snapshots) {
                                        u = documentSnapshot.toObject(Users.class);
                                        if (u != null) {
                                            u.setDocumentID(documentSnapshot.getId());
                                            break;
                                        }
                                    }

                                    if (u != null) {
                                        new UserPref(GoogleSigninActivity.this).storeUser(u);
                                        finish();
                                    } else {
                                        Toast.makeText(GoogleSigninActivity.this, "Failed to create account, Please try again later", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                            }

                            @Override
                            public void onError() {
                                request.insertData(body, new FirebaseListener() {
                                    @Override
                                    public <T> void onSuccess(T any) {
                                        if (any instanceof String) {
                                            String documentID = (String) any;
                                            users.setDocumentID(documentID);
                                            new UserPref(GoogleSigninActivity.this).storeUser(users);
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onError() {
                                        Toast.makeText(GoogleSigninActivity.this, "Failed to create account, Please try again later", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                            }
                        });


                    }

                } catch (ApiException e) {
                    Log.e("err", e.getLocalizedMessage());
                    Toast.makeText(GoogleSigninActivity.this, "Failed to login using Google Account", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        oneTapClient = Identity.getSignInClient(GoogleSigninActivity.this);
        signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId(getString(R.string.web_client_id))
                        .setFilterByAuthorizedAccounts(false)
                        .build())
                .build();

        oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener(beginSignInResult -> {
                    try {
                        oneTapLauncher.launch(new IntentSenderRequest.Builder(beginSignInResult.getPendingIntent().getIntentSender()).build());
                    } catch (Exception e) {
                        Log.e("oneTapClientFail", "Couldn't start One Tap UI: " + e.getLocalizedMessage());
                    }
                })
                .addOnFailureListener(e -> Log.e("oneTapClientFail", e.getLocalizedMessage()));
    }
}
