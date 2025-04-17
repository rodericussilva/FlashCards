package com.example.flashcards;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private String selectedLanguage = "en";
    private static final String PREFS_NAME = "AppPrefs";
    private static final String LANGUAGE_KEY = "selected_language";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        selectedLanguage = prefs.getString(LANGUAGE_KEY, "en");

        setAppLocale(selectedLanguage);

        setContentView(R.layout.activity_main);

        Spinner languageSpinner = findViewById(R.id.languageSpinner);
        Button btnStart = findViewById(R.id.btnStart);
        Button btnFavorites = findViewById(R.id.btnFavorites);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.languages_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        int selectedIndex = getSpinnerIndexByLanguageCode(selectedLanguage);
        languageSpinner.setSelection(selectedIndex);

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                String languageName = parent.getItemAtPosition(position).toString();
                String languageCode = getLanguageCode(languageName);

                if (!languageCode.equals(selectedLanguage)) {
                    selectedLanguage = languageCode;

                    SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString(LANGUAGE_KEY, selectedLanguage);
                    editor.apply();

                    setAppLocale(selectedLanguage);
                    recreate();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
            intent.putExtra("language", selectedLanguage);
            startActivity(intent);
        });

        btnFavorites.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
            startActivity(intent);
        });
    }

    private void setAppLocale(String localeCode) {
        Locale locale = new Locale(localeCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    private String getLanguageCode(String languageName) {
        switch (languageName) {
            case "Português":
                return "pt";
            case "Français":
                return "fr";
            case "Español":
                return "es";
            case "English":
            default:
                return "en";
        }
    }

    private int getSpinnerIndexByLanguageCode(String code) {
        switch (code) {
            case "pt":
                return 1;
            case "fr":
                return 2;
            case "es":
                return 3;
            case "en":
            default:
                return 0;
        }
    }
}
