package com.example.flashcards;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.content.SharedPreferences;

public class FlashcardsActivity extends BaseActivity {

    private ImageView flashcardImage;
    private TextView flashcardText;
    private Button btnNext;
    private Button btnFavorite;
    private boolean showingImage = true;
    private int currentIndex = 0;
    private List<Flashcard> flashcards = new ArrayList<>();
    private String category, language;
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

        category = getIntent().getStringExtra("category");
        language = getIntent().getStringExtra("language");

        setLocale(language);
        loadFlashcards();

        updateFlashcard();

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

    private void loadFlashcards() {
        switch (category) {
            case "Clothes":
                flashcards.add(new Flashcard(R.drawable.shirt, "clothes_shirt"));
                flashcards.add(new Flashcard(R.drawable.pants, "clothes_pants"));
                flashcards.add(new Flashcard(R.drawable.groves, "clothes_grove"));
                flashcards.add(new Flashcard(R.drawable.shoe, "clothes_shoes"));
                break;
            case "Professions":
                flashcards.add(new Flashcard(R.drawable.fireman, "professions_fireman"));
                flashcards.add(new Flashcard(R.drawable.doctor, "professions_doctor"));
                flashcards.add(new Flashcard(R.drawable.baker, "professions_baker"));
                flashcards.add(new Flashcard(R.drawable.nurse, "professions_nurse"));
                break;
            case "Animals":
                flashcards.add(new Flashcard(R.drawable.bird, "animals_bird"));
                flashcards.add(new Flashcard(R.drawable.dog, "animals_dog"));
                flashcards.add(new Flashcard(R.drawable.horse, "animals_horse"));
                flashcards.add(new Flashcard(R.drawable.sheep, "animals_sheep"));
                break;
            case "Fruits":
                flashcards.add(new Flashcard(R.drawable.banana, "fruits_banana"));
                flashcards.add(new Flashcard(R.drawable.grape, "fruits_grape"));
                flashcards.add(new Flashcard(R.drawable.pineapple, "fruits_pineapple"));
                break;
            case "Colors":
                flashcards.add(new Flashcard(R.drawable.black, "colors_black"));
                flashcards.add(new Flashcard(R.drawable.green, "colors_green"));
                flashcards.add(new Flashcard(R.drawable.red, "colors_red"));
                flashcards.add(new Flashcard(R.drawable.yellow, "colors_yellow"));
                flashcards.add(new Flashcard(R.drawable.white, "colors_white"));
        }
    }

    private void setLocale(String lang) {
        Locale locale;
        switch (lang) {
            case "Portuguese": locale = new Locale("pt"); break;
            case "French":     locale = new Locale("fr"); break;
            case "Spanish":    locale = new Locale("es"); break;
            default:           locale = new Locale("en"); break;
        }

        Locale.setDefault(locale);
        Resources res = getResources();
        android.content.res.Configuration config = res.getConfiguration();
        config.setLocale(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    private void toggleFavorite() {
        Flashcard card = flashcards.get(currentIndex);
        String lang = getCurrentLanguage();
        String key = lang + "_" + card.getWordKey();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean isFavorite = sharedPreferences.getBoolean(key, false);

        if (isFavorite) {
            editor.remove(key);
        } else {
            editor.putBoolean(key, true);
        }

        editor.apply();
        updateFavoriteButton();
    }

    private void updateFavoriteButton() {
        Flashcard card = flashcards.get(currentIndex);
        String key = getCurrentLanguage() + "_" + card.getWordKey();
        boolean isFavorite = sharedPreferences.getBoolean(key, false);
        btnFavorite.setText(isFavorite ? "★" : "☆");
    }

    private String getCurrentLanguage() {
        return Locale.getDefault().getLanguage();
    }
}