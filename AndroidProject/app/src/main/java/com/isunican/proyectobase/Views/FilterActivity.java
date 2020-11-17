package com.isunican.proyectobase.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;


import com.isunican.proyectobase.R;

public class FilterActivity extends AppCompatActivity  {



    CheckBox checkBoxGasoleA;
    CheckBox checkBoxGasolina95;

    CheckBox checkBoxDescuentoSi;
    CheckBox checkBoxDescuentoNo;

    Button buttonApply;
    Button buttonCancel;

    public static String gasoleoA = "gasoleoA";
    public static String gasolina95 = "gasolina95";
    public static String descuentoSi = "descuentoSI";
    public static String descuentoNo = "descuentoNo";

    boolean bgasoleoA = false;
    boolean bgasolina95 = false;
    boolean bdescuentoSi = false;
    boolean bDescuentoNo =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        checkBoxGasoleA = findViewById(R.id.checkBoxGasoleoA);
        checkBoxGasolina95 = findViewById(R.id.checkBoxGasolina95);
        checkBoxDescuentoSi = findViewById(R.id.checkBoxDescuentoSi);
        checkBoxDescuentoNo = findViewById(R.id.checkBoxDescuentoNo);
        buttonApply = findViewById(R.id.buttonApply);
        buttonCancel = findViewById(R.id.buttonCancel);

        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;

        getWindow().setLayout((int) (ancho * 0.8), (int) (alto * 0.5));



        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked();
                Intent intent = new Intent();
                intent.putExtra(gasoleoA, bgasoleoA);
                intent.putExtra(gasolina95,bgasolina95);
                intent.putExtra(descuentoSi,bdescuentoSi);
                intent.putExtra(descuentoNo,bDescuentoNo);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });


    }

    public void onCheckboxClicked() {
        bgasoleoA = checkBoxGasoleA.isChecked();
        bgasolina95 = checkBoxGasolina95.isChecked();
        bdescuentoSi = checkBoxDescuentoSi.isChecked();
        bDescuentoNo = checkBoxDescuentoNo.isChecked();

        }
    }