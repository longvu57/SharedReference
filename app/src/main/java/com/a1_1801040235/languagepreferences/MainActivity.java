package com.a1_1801040235.languagepreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String P_LANG = "LANG";

    private SharedPreferences sharedPreferences;
    private TextView tvLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLanguage = findViewById(R.id.tvLanguage);

        sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        String language = sharedPreferences.getString(P_LANG, null);
        if(language != null){
            tvLanguage.setText(language);
        }else{
            selectLanguage();
        }
    }

    public void selectLanguage(){
        new AlertDialog.Builder(MainActivity.this)
                .setIcon(android.R.drawable.ic_btn_speak_now)
                .setTitle("Choose a language")
                .setMessage("Which language would you like?")
                .setPositiveButton("Vietnamese", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setLanguage("Vietnamese");
                    }
                })
                .setNegativeButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setLanguage("English");
                    }
                })
                .show();

    }

    public void setLanguage(String language){
        sharedPreferences.edit().putString(P_LANG, language).apply();
        tvLanguage.setText(language);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mvLangV:
                setLanguage("Vietnamese");
                break;
            case R.id.mvLangE:
                setLanguage("English");
                break;
            case R.id.mvClear:
                sharedPreferences.edit().remove(P_LANG).apply();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}