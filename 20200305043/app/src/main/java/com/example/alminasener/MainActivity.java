package com.example.alminasener;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Yeni bir aktivite başlatmak için bir Intent oluşturduk.
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);

                // Oluşturulan Intent ile yeni aktivite (Login Activity) başlatılıyor.
                startActivity(intent);

                // Şu anki (MainActivity) aktivite sonlandırılıyor.
                finish();
            }
        }, 3000); // 3000 milisaniye (3 saniye) bekledikten sonra yeni aktiviteye geçmeye yarıyor.
    }
}