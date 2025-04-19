package com.example.flashcards;

public class Flashcard {
    private int imageResId;
    private String wordKey;

    public Flashcard(int imageResId, String wordKey) {
        this.imageResId = imageResId;
        this.wordKey = wordKey;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getWordKey() {
        return wordKey;
    }
}

