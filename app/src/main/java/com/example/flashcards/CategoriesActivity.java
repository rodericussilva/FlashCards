package com.example.flashcards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Button categoryClothes = findViewById(R.id.categoryClothes);
        Button categoryProfessions = findViewById(R.id.categoryProfessions);
        Button categoryAnimals = findViewById(R.id.categoryAnimals);
        Button categoryFruits = findViewById(R.id.categoryFruits);
        Button categoryColors = findViewById(R.id.categoryColors);

        categoryClothes.setText(getString(R.string.category_clothes));
        categoryProfessions.setText(getString(R.string.category_professions));
        categoryAnimals.setText(getString(R.string.category_animals));
        categoryFruits.setText(getString(R.string.category_fruits));
        categoryColors.setText(getString(R.string.category_colors));

        String language = LocaleHelper.getLanguage(this);
        String languageName;
        switch (language) {
            case "pt":
                languageName = "Portuguese";
                break;
            case "fr":
                languageName = "French";
                break;
            case "es":
                languageName = "Spanish";
                break;
            default:
                languageName = "English";
                break;
        }

        categoryClothes.setOnClickListener(v -> openFlashcards("Clothes", languageName));
        categoryProfessions.setOnClickListener(v -> openFlashcards("Professions", languageName));
        categoryAnimals.setOnClickListener(v -> openFlashcards("Animals", languageName));
        categoryFruits.setOnClickListener(v -> openFlashcards("Fruits", languageName));
        categoryColors.setOnClickListener(v -> openFlashcards("Colors", languageName));
    }

    private void openFlashcards(String category, String language) {
        Intent intent = new Intent(CategoriesActivity.this, FlashcardsActivity.class);
        intent.putExtra("category", category);
        intent.putExtra("language", language);
        startActivity(intent);
    }
}