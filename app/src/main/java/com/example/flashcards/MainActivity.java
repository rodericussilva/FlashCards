package com.example.flashcards;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner languageSpinner;
    private Button btnStart, btnFavorites;
    private String[] languageCodes = {"en", "pt", "fr", "es"};
    private boolean userInteracted = false;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        languageSpinner = findViewById(R.id.languageSpinner);
        btnStart = findViewById(R.id.btnStart);
        btnFavorites = findViewById(R.id.btnFavorites);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.languages_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        // Obter idioma salvo e aplicar seleção correta
        String currentLang = LocaleHelper.getLanguage(this);
        int langIndex = getLanguageIndex(currentLang);
        if (langIndex != -1) {
            languageSpinner.setSelection(langIndex, false); // evita ativar listener no load
        }

        languageSpinner.setOnTouchListener((v, event) -> {
            userInteracted = true;
            return false;
        });

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                if (!userInteracted) return;

                String selectedLangCode = languageCodes[position];
                LocaleHelper.setLocale(MainActivity.this, selectedLangCode);
                recreate(); // Recria a activity com o novo idioma
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
            startActivity(intent);
        });

        btnFavorites.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FlashcardsActivity.class);
            startActivity(intent);
        });
    }

    private int getLanguageIndex(String langCode) {
        for (int i = 0; i < languageCodes.length; i++) {
            if (languageCodes[i].equals(langCode)) {
                return i;
            }
        }
        return -1;
    }
}
