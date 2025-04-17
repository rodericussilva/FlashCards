package com.example.flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class CategoriesActivity extends AppCompatActivity {
    private String selectedLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        selectedLanguage = getIntent().getStringExtra("language");

        Button btnTravel = findViewById(R.id.categoryTravel);
        // Adicionar mais categorias conforme necessário

        btnTravel.setOnClickListener(v -> {
            Intent intent = new Intent(CategoriesActivity.this, FlashcardsActivity.class);
            intent.putExtra("category", "Travel");
            intent.putExtra("language", selectedLanguage);
            startActivity(intent);
        });
    }
}

