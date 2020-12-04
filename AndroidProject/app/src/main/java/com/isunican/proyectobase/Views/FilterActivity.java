package com.isunican.proyectobase.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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

    public static final String GASOLEOA = "gasoleoA";
    public static final String GASOLINA95 = "gasolina95";
    public static final String DESCUENTOSI = "descuentoSI";
    public static final String DESCUENTONO = "descuentoNo";

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

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = 1000;
        params.width = 800;

        this.getWindow().setAttributes(params);

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
                intent.putExtra(GASOLEOA, bgasoleoA);
                intent.putExtra(GASOLINA95,bgasolina95);
                intent.putExtra(DESCUENTOSI,bdescuentoSi);
                intent.putExtra(DESCUENTONO,bDescuentoNo);
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

        cont = checkCombustibles(antiguoBGasoleoA, antiguoBGasolina95, cont);

        checkDescuento(antiguoBDescuentoNo, antiguoBDescuentoSi, cont);

    }

    private void checkDescuento(boolean antiguoBDescuentoNo, boolean antiguoBDescuentoSi, int cont) {
        if(checkBoxDescuentoNo.isChecked() && checkBoxDescuentoSi.isChecked() || bdescuentoSi && bDescuentoNo){
            if(checkBoxDescuentoNo.isChecked() && checkBoxDescuentoSi.isChecked()){
                estableceBooleanosDescuento(antiguoBDescuentoNo, antiguoBDescuentoSi);
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

    private int checkCombustibles(boolean antiguoBGasoleoA, boolean antiguoBGasolina95, int cont) {
        if(checkBoxGasolina95.isChecked() && checkBoxGasoleA.isChecked() || bgasolina95 && bgasoleoA){
            if(checkBoxGasoleA.isChecked() && checkBoxGasolina95.isChecked()){
                estableceBooleanosCombustible(antiguoBGasoleoA, antiguoBGasolina95);
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
        return cont;
    }

    private void estableceBooleanosDescuento(boolean antiguoBDescuentoNo, boolean antiguoBDescuentoSi) {
        if(antiguoBDescuentoNo){
            bdescuentoSi=false;
        }else if (antiguoBDescuentoSi){
            bDescuentoNo=false;
        }else{
            bDescuentoNo=false;
            bdescuentoSi=false;
        }
    }

    private void estableceBooleanosCombustible(boolean antiguoBGasoleoA, boolean antiguoBGasolina95) {
        if(antiguoBGasoleoA){
            bgasolina95=false;
        }else if (antiguoBGasolina95){
            bgasoleoA=false;
        }else{
            bgasoleoA=false;
            bgasolina95=false;
        }
    }


}