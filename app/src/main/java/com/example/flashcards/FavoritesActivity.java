package com.example.flashcards;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FavoritesActivity extends AppCompatActivity {

    private ImageView flashcardImage;
    private TextView flashcardText;
    private Button btnNext;
    private Button btnFavorite;
    private boolean showingImage = true;
    private int currentIndex = 0;
    private List<Flashcard> flashcards = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private static final String FAVORITES_PREF = "favorites";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards);

        flashcardImage = findViewById(R.id.flashcardImage);
        flashcardText = findViewById(R.id.flashcardText);
        btnNext = findViewById(R.id.btnNext);
        btnFavorite = findViewById(R.id.btnFavorite);

        sharedPreferences = getSharedPreferences(FAVORITES_PREF, MODE_PRIVATE);

        loadFavoriteFlashcards();
        if (flashcards.isEmpty()) {
            flashcardText.setText("- - -");
            flashcardText.setVisibility(View.VISIBLE);
            flashcardImage.setVisibility(View.GONE);
            btnNext.setVisibility(View.GONE);
        } else {
            updateFlashcard();
        }

        flashcardImage.setOnClickListener(v -> toggleFlashcard());
        flashcardText.setOnClickListener(v -> toggleFlashcard());

        btnNext.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % flashcards.size();
            showingImage = true;
            updateFlashcard();
        });

        btnFavorite.setOnClickListener(v -> toggleFavorite());
    }

    private void toggleFlashcard() {
        showingImage = !showingImage;
        updateFlashcard();
    }

    private void updateFlashcard() {
        Flashcard card = flashcards.get(currentIndex);
        if (showingImage) {
            flashcardImage.setImageResource(card.getImageResId());
            flashcardImage.setVisibility(View.VISIBLE);
            flashcardText.setVisibility(View.GONE);
        } else {
            flashcardText.setText(getString(getStringIdByName(card.getWordKey())));
            flashcardImage.setVisibility(View.GONE);
            flashcardText.setVisibility(View.VISIBLE);
        }
        updateFavoriteButton();
    }

    private int getStringIdByName(String name) {
        return getResources().getIdentifier(name, "string", getPackageName());
    }

    private void loadFavoriteFlashcards() {
        String lang = getCurrentLanguage(); // ex: "fr"
        Map<String, ?> allFavorites = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : allFavorites.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith(lang + "_") && (Boolean) entry.getValue()) {
                String originalKey = key.substring(lang.length() + 1);
                Flashcard card = getFlashcardByKey(originalKey);
                if (card != null) {
                    flashcards.add(card);
                }
            }
        }
    }

    private Flashcard getFlashcardByKey(String key) {
        switch (key) {
            case "clothes_shirt": return new Flashcard(R.drawable.shirt, key);
            case "clothes_pants": return new Flashcard(R.drawable.pants, key);
            case "clothes_grove": return new Flashcard(R.drawable.groves, key);
            case "clothes_shoes": return new Flashcard(R.drawable.shoe, key);
            case "professions_fireman": return new Flashcard(R.drawable.fireman, key);
            case "professions_doctor": return new Flashcard(R.drawable.doctor, key);
            case "professions_baker": return new Flashcard(R.drawable.baker, key);
            case "professions_nurse": return new Flashcard(R.drawable.nurse, key);
            case "animals_bird": return new Flashcard(R.drawable.bird, key);
            case "animals_dog": return new Flashcard(R.drawable.dog, key);
            case "animals_horse": return new Flashcard(R.drawable.horse, key);
            case "animals_sheep": return new Flashcard(R.drawable.sheep, key);
            case "fruits_banana": return new Flashcard(R.drawable.banana, key);
            case "fruits_grape": return new Flashcard(R.drawable.grape, key);
            case "fruits_pineapple": return new Flashcard(R.drawable.pineapple, key);
            case "colors_black": return new Flashcard(R.drawable.black, key);
            case "colors_green": return new Flashcard(R.drawable.green, key);
            case "colors_red": return new Flashcard(R.drawable.red, key);
            case "colors_yellow": return new Flashcard(R.drawable.yellow, key);
            case "colors_white": return new Flashcard(R.drawable.white, key);
            default: return null;
        }
    }

    private void toggleFavorite() {
        if (flashcards.isEmpty()) return;

        Flashcard card = flashcards.get(currentIndex);
        String lang = getCurrentLanguage();
        String key = lang + "_" + card.getWordKey();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean isFavorite = sharedPreferences.getBoolean(key, false);

        if (isFavorite) {
            editor.remove(key);
            editor.apply();
            flashcards.remove(currentIndex);

            if (flashcards.isEmpty()) {
                flashcardText.setText("- - -");
                flashcardText.setVisibility(View.VISIBLE);
                flashcardImage.setVisibility(View.GONE);
                btnNext.setVisibility(View.GONE);
                btnFavorite.setVisibility(View.GONE);
            } else {
                if (currentIndex >= flashcards.size()) {
                    currentIndex = 0;
                }
                showingImage = true;
                updateFlashcard();
            }
        }
    }

    private void updateFavoriteButton() {
        if (flashcards.isEmpty()) return;

        Flashcard card = flashcards.get(currentIndex);
        String key = getCurrentLanguage() + "_" + card.getWordKey();
        boolean isFavorite = sharedPreferences.getBoolean(key, false);
        btnFavorite.setText(isFavorite ? "★" : "☆");
    }

    private String getCurrentLanguage() {
        return LocaleHelper.getLanguage(this);
    }
}