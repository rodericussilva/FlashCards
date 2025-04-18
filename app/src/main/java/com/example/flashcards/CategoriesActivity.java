package com.example.flashcards;

import android.content.Context;
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
    }
}