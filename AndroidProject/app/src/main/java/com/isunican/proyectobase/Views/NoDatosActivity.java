package com.isunican.proyectobase.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.isunican.proyectobase.R;

public class NoDatosActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_datos);
        Button b = findViewById(R.id.button);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button){
            Intent myIntent = new Intent(NoDatosActivity.this, MainActivity.class);
            NoDatosActivity.this.startActivity(myIntent);

        }
    }
}