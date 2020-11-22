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


    boolean bgasoleoA = false;
    boolean bgasolina95 = false;
    boolean bdescuentoSi = false;
    boolean bDescuentoNo =false;



    public static String getGasoleoA(){
        return "gasoleoA";
    }

    public static String getGasolina95(){
        return "gasolina95";
    }
    public static String getDescuentoSi(){
        return "descuentoSI";
    }
    public static String getDescuentoNo(){
        return "descuentoNo";
    }

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
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked();
                Intent intent = new Intent();
                intent.putExtra(getGasoleoA(), bgasoleoA);
                intent.putExtra(getGasolina95(),bgasolina95);
                intent.putExtra(getDescuentoSi(),bdescuentoSi);
                intent.putExtra(getDescuentoNo(),bDescuentoNo);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

                Intent data = getIntent();
                bgasoleoA = data.getBooleanExtra("GasoleoA", false);
                bgasolina95 = data.getBooleanExtra("Gasolina95", false);
                bdescuentoSi = data.getBooleanExtra("DescuentoSI", false);
                bDescuentoNo = data.getBooleanExtra("DescuentoNo", false);

    }

    public void onCheckboxClicked() {

        boolean antiguoBGasoleoA = bgasoleoA;
        boolean antiguoBGasolina95 = bgasolina95;
        boolean antiguoBDescuentoNo = bDescuentoNo;
        boolean antiguoBDescuentoSi = bdescuentoSi;

        int cont=0;

        if(!bgasoleoA) {
            bgasoleoA = checkBoxGasoleA.isChecked();
        }
        if(!bgasolina95) {
            bgasolina95 = checkBoxGasolina95.isChecked();
        }
        if(!bDescuentoNo) {
            bDescuentoNo = checkBoxDescuentoNo.isChecked();
        }
        if(!bdescuentoSi) {
            bdescuentoSi = checkBoxDescuentoSi.isChecked();
        }



        if(checkBoxGasolina95.isChecked() && checkBoxGasoleA.isChecked() || bgasolina95 && bgasoleoA){
            if(checkBoxGasoleA.isChecked() && checkBoxGasolina95.isChecked()){
                if(antiguoBGasoleoA){
                    bgasolina95=false;
                }else if (antiguoBGasolina95){
                    bgasoleoA=false;
                }else{
                    bgasoleoA=false;
                    bgasolina95=false;
                }
            }
            else if(checkBoxGasoleA.isChecked()){
                bgasoleoA=false;
            }
            else if(checkBoxGasolina95.isChecked()){
                bgasolina95=false;
            }
            Intent myIntent = new Intent(FilterActivity.this, PopUpConflicto.class);
            cont++;
            FilterActivity.this.startActivity(myIntent);
        }
        if(checkBoxDescuentoNo.isChecked() && checkBoxDescuentoSi.isChecked() || bdescuentoSi && bDescuentoNo){
            if(checkBoxDescuentoNo.isChecked() && checkBoxDescuentoSi.isChecked()){
                if(antiguoBDescuentoNo){
                    bdescuentoSi=false;
                }else if (antiguoBDescuentoSi){
                    bDescuentoNo=false;
                }else{
                    bDescuentoNo=false;
                    bdescuentoSi=false;
                }
            }
            else if(checkBoxDescuentoSi.isChecked()){
                bdescuentoSi=false;
            }
            else if(checkBoxDescuentoNo.isChecked()){
                bDescuentoNo=false;
            }

            if(cont==0){
                Intent myIntent = new Intent(FilterActivity.this, PopUpConflicto.class);
                FilterActivity.this.startActivity(myIntent);
            }

        }

        }





    }