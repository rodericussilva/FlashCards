package com.example.flashcards;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FlashcardsActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards);

        Button btnNext = findViewById(R.id.btnNext);
        Button btnFavorite = findViewById(R.id.btnFavorite);

        btnNext.setText(getString(R.string.btn_next));
        btnFavorite.setText(getString(R.string.btn_favorites));
    }
}