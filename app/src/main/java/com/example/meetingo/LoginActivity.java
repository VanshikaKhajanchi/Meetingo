package com.example.meetingo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener {

    EditText uid,psswrd;
    Button login_btn;
    TextView frgtpass,newuser,mStatusTextView,mDetailTextView;
    String user_id,password;
    public static final String TAG = "Vk";
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*FirebaseApp.initializeApp(this);*/
        setContentView(R.layout.activity_login);



        // Views


        uid = findViewById(R.id.login_id);
        psswrd = findViewById(R.id.login_pass);

        mStatusTextView = findViewById(R.id.status);
        mDetailTextView = findViewById(R.id.detail);
        user_id=uid.getText().toString();
        password=psswrd.getText().toString();


        // Buttons
        login_btn=findViewById(R.id.login_lbutton);
        frgtpass=findViewById(R.id.login_frgtpass);
        newuser=findViewById(R.id.login_signup);

        login_btn.setOnClickListener(this);
        frgtpass.setOnClickListener(this);
        newuser.setOnClickListener(this);




        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

    }

    @VisibleForTesting
    public ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    //Signin with useerid and pssword;
    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            mStatusTextView.setText(R.string.auth_failed);
                        }
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    private void sendEmailVerification() {
        // Disable button
        findViewById(R.id.login_lbutton).setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        findViewById(R.id.login_lbutton).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(LoginActivity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = uid.getText().toString();
        if (TextUtils.isEmpty(email)) {
            uid.setError("Required.");
            valid = false;
        } else {
            uid.setError(null);
        }

        String password = psswrd.getText().toString();
        if (TextUtils.isEmpty(password)) {
            psswrd.setError("Required.");
            valid = false;
        } else {
            psswrd.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.login_pass).setVisibility(View.GONE);
            findViewById(R.id.login_frgtpass).setVisibility(View.GONE);
            findViewById(R.id.login_signup).setVisibility(View.VISIBLE);

            findViewById(R.id.login_lbutton).setEnabled(!user.isEmailVerified());

            Intent s_login=new Intent(this,HomePage.class);
            startActivity(s_login);
        } else {
           mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);

            findViewById(R.id.login_signup).setVisibility(View.VISIBLE);
            findViewById(R.id.login_pass).setVisibility(View.VISIBLE);
            findViewById(R.id.login_frgtpass).setVisibility(View.GONE);

            Intent s_login=new Intent(this,MainActivity.class);
            startActivity(s_login);


        }


    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.login_signup) {
            Intent login_intent=new Intent(this,Signup.class);
            startActivity(login_intent);
        } else if (i == R.id.login_frgtpass) {
            //intent of forgot password
            Toast.makeText(LoginActivity.this,
                    "Sending vk forgot password.",
                    Toast.LENGTH_SHORT).show();

        } else if (i == R.id.login_lbutton) {
           signIn(user_id,password);
           sendEmailVerification();
        }
    }

}
