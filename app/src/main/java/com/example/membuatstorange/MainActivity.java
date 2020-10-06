package com.example.membuatstorange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btninternal, btnexternal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btninternal = (Button)findViewById(R.id.btninternal);
        btnexternal = (Button)findViewById(R.id.btneksternal);

        btninternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Internal.class);
                startActivity(i);
            }
        });
        btnexternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, External.class);
                startActivity(i);
            }
        });
    }
}