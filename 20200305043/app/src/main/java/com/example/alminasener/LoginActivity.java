package com.example.alminasener;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    // Kullanıcıdan alınacak email, şifre ve giriş butonu için değişkenler tanımladık.
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // XML dosyasındaki EditText ve Button elemanları ile Java kodu arasında bağlantı kurduk.
        emailEditText = findViewById(R.id.editTextText1);
        passwordEditText = findViewById(R.id.editTextText2);
        loginButton = findViewById(R.id.button);

        // Giriş butonuna tıklanıldığında yapılacak işlemleri tanımladık.
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Girilen email ve şifre alınıyor.
                String enteredEmail = emailEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                // Girilen kimlik bilgilerinin geçerli olup olmadığı kontrol ediyoruz.
                if (isValidCredentials(enteredEmail, enteredPassword)) {
                    // Geçerli kimlik bilgileri ise yeni bir aktivite başlatıyor.
                    Intent intent = new Intent(LoginActivity.this, DateActivity.class);
                    startActivity(intent);
                } else {
                    // Geçersiz kimlik bilgileri ise kullanıcıya bir uyarı gösterir.
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Girilen kimlik bilgilerinin geçerli olup olmadığını kontrol eden metod.
    private boolean isValidCredentials(String enteredEmail, String enteredPassword) {
        // Geçerli email-şifre çiftleri tanımladık.
        String[] validEmails = {"alminasener@pru.com", "john.doe@example.com", "emma.smith@example.com", "alex.jones@example.com", "sophia.miller@example.com", "jackson.brown@example.com", "olivia.davis@example.com", "ethan.white@example.com", "mia.johnson@example.com", "noah.taylor@example.com", "ava.martin@example.com"};
        String[] validPasswords = {"1234567", "john123", "emma123", "alex123", "sophia123", "jackson123", "olivia123", "ethan123", "mia123", "noah123", "ava123"};

        // Girilen kimlik bilgileri ile her geçerli çifti kontrol ediyoruz
        for (int i = 0; i < validEmails.length; i++) {
            if (enteredEmail.equals(validEmails[i]) && enteredPassword.equals(validPasswords[i])) {
                return true; // Geçerli kimlik bilgileri
            }
        }

        return false; // Geçersiz kimlik bilgileri
    }
}