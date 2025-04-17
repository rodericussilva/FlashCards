package com.example.flashcards;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class FlashcardsActivity extends AppCompatActivity {
    private List<Flashcard> flashcards;
    private int currentIndex = 0;
    private String selectedLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards);

        String category = getIntent().getStringExtra("category");
        selectedLanguage = getIntent().getStringExtra("language");
        flashcards = loadFlashcards(category, selectedLanguage);

        showFlashcard(currentIndex);

        Button btnNext = findViewById(R.id.btnNext);
        Button btnFavorite = findViewById(R.id.btnFavorite);
        ImageView flashcardImage = findViewById(R.id.flashcardImage);
        TextView flashcardText = findViewById(R.id.flashcardText);

        btnNext.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % flashcards.size();
            showFlashcard(currentIndex);
        });

        flashcardImage.setOnClickListener(v -> {
            Flashcard flashcard = flashcards.get(currentIndex);
            flashcardText.setText(flashcard.getAnswer());
        });

        btnFavorite.setOnClickListener(v -> {
            Flashcard flashcard = flashcards.get(currentIndex);
            flashcard.setFavorite(true);
            // Implementar lógica para salvar como favorito
        });
    }

    private void showFlashcard(int index) {
        Flashcard flashcard = flashcards.get(index);
        ImageView flashcardImage = findViewById(R.id.flashcardImage);
        TextView flashcardText = findViewById(R.id.flashcardText);

        flashcardImage.setImageResource(flashcard.getImageResId());
        flashcardText.setText("");
    }

    private List<Flashcard> loadFlashcards(String category, String language) {
        // Implementar lógica para carregar flashcards da categoria e idioma selecionado
        return new ArrayList<>();
    }
}

