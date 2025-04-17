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

        Button btnClothes = findViewById(R.id.categoryClothes);
        Button btnProfessions = findViewById(R.id.categoryProfessions);
        Button btnAnimals = findViewById(R.id.categoryAnimals);
        Button btnFruits = findViewById(R.id.categoryFruits);
        Button btnColors = findViewById(R.id.categoryColors);

        btnClothes.setOnClickListener(v -> openFlashcards("Clothes"));
        btnProfessions.setOnClickListener(v -> openFlashcards("Professions"));
        btnAnimals.setOnClickListener(v -> openFlashcards("Animals"));
        btnFruits.setOnClickListener(v -> openFlashcards("Fruits"));
        btnColors.setOnClickListener(v -> openFlashcards("Colors"));
    }

    private void openFlashcards(String category) {
        Intent intent = new Intent(CategoriesActivity.this, FlashcardsActivity.class);
        intent.putExtra("category", category);
        intent.putExtra("language", selectedLanguage);
        startActivity(intent);
    }
}