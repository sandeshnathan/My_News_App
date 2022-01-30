package edmt.dev.mynewsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText registerEmailText;
    private EditText registerPasswordText;
    private EditText registerConfirmPasswordText;
    private Button registerAccountButton;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        registerEmailText = findViewById(R.id.register_email_edittext);
        registerPasswordText = findViewById(R.id.register_password_edittext);
        registerConfirmPasswordText = findViewById(R.id.register_confirm_password_edittext);
        registerAccountButton = findViewById(R.id.register_button);

        registerAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = registerEmailText.getText().toString();
                String password = registerPasswordText.getText().toString();
                String confirmPassword = registerConfirmPasswordText.getText().toString();

                if (!TextUtils.isEmpty(email) && (!TextUtils.isEmpty(password) && (!TextUtils.isEmpty(confirmPassword)))) {
                    if (password.equals(confirmPassword)) {
                        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Please login to continue!", Toast.LENGTH_SHORT).show();
                                    navigateToMainPage();
                                } else {
                                    String registrationError = task.getException().getMessage();
                                    Toast.makeText(RegisterActivity.this, "Registration error: " + registrationError, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show();
                        registerConfirmPasswordText.setText(null);
                        registerPasswordText.setText(null);
                        //registerPasswordText.setFocusable(true);
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            navigateToMainPage();
        }
    }

    private void navigateToMainPage() {
        Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}