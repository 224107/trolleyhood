package com.example.trolleyhood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class HelpOrAsk extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageViewHelp, imageViewAsk;
    private Button buttonSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_or_ask);

        buttonSettings = (Button) findViewById(R.id.buttonSettings);
        imageViewAsk = (ImageView) findViewById(R.id.imageViewAsk);
        imageViewHelp = (ImageView) findViewById(R.id.imageViewHelp);
        buttonSettings.setOnClickListener(this);
        imageViewHelp.setOnClickListener(this);
        imageViewAsk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonSettings:
                startActivity(new Intent(getApplicationContext(), Settings.class));
                break;
            case R.id.imageViewAsk:
                startActivity(new Intent(getApplicationContext(), CategorySelection.class));
                break;
            case R.id.imageViewHelp:
                startActivity(new Intent(getApplicationContext(), Help.class));
                break;
        }
    }
}
