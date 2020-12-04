package com.isunican.proyectobase.Presenter;

import android.widget.TextView;

import com.isunican.proyectobase.Model.ConDescuentoFiltro;
import com.isunican.proyectobase.Model.DieselFiltro;
import com.isunican.proyectobase.Model.Gasolina95Filtro;
import com.isunican.proyectobase.Model.IFiltro;
import com.isunican.proyectobase.Model.SinDescuentoFiltro;

import java.util.ArrayList;
import java.util.List;

public class PresenterFiltros {

    private List<IFiltro> listaFiltros;

    private final IFiltro filtroGasoleA;
    private final IFiltro filtroGasolina95;
    private final IFiltro descuentoSiFiltro;
    private final IFiltro descuentoNoFiltro;

    private boolean gasoleoA;
    private boolean gasolina95;
    private boolean descuentoSi;
    private boolean descuentoNo;

    private static TextView filtroMarcado;

    public PresenterFiltros(){
        listaFiltros = new ArrayList<>();
        filtroGasoleA = new DieselFiltro();
        filtroGasolina95 = new Gasolina95Filtro();
        descuentoSiFiltro = new ConDescuentoFiltro();
        descuentoNoFiltro = new SinDescuentoFiltro();
    }

    public boolean getGasoleoA() {
        return gasoleoA;
    }

    public void setGasoleoA(boolean gasoleoA) {
        this.gasoleoA = gasoleoA;
    }

    public boolean getGasolina95() {
        return gasolina95;
    }

    public void setGasolina95(boolean gasolina95) {
        this.gasolina95 = gasolina95;
    }

    public boolean getDescuentoSi() {
        return descuentoSi;
    }

    public void setDescuentoSi(boolean descuentoSi) {
        this.descuentoSi = descuentoSi;
    }

    public boolean getDescuentoNo() {
        return descuentoNo;
    }

    public void setDescuentoNo(boolean descuentoNo) {
        this.descuentoNo = descuentoNo;
    }


    public static TextView getFiltroMarcado(){return filtroMarcado;}
    public static void setFiltroMarcado(TextView nombreFiltro){filtroMarcado=nombreFiltro;}

    public IFiltro getFiltroGasoleA() {
        return filtroGasoleA;
    }

    public IFiltro getFiltroGasolina95() {
        return filtroGasolina95;
    }

    public IFiltro getDescuentoSiFiltro() {
        return descuentoSiFiltro;
    }

    public IFiltro getDescuentoNoFiltro() {
        return descuentoNoFiltro;
    }

    public List<IFiltro> getListaFiltros() {
        return listaFiltros;
    }

    public void vaciaListaFiltros(){
        listaFiltros.clear();
    }

    public void setListaFiltros(List<IFiltro> listaFiltros) {
        this.listaFiltros = listaFiltros;
    }

    /**
     * Método que elimina el filtro de la lista
     */
    public void eliminaFiltroLista(String filtro){

        switch (filtro) {
            case "Con Descuento":
                descuentoSi=false;
                listaFiltros.remove(descuentoSiFiltro);
                break;

            case "GasóleoA":
                gasoleoA=false;
                listaFiltros.remove(filtroGasoleA);
                break;
            case "Sin Descuento":
                descuentoNo=false;
                listaFiltros.remove(descuentoNoFiltro);
                break;
            case "Gasolina 95":
                gasolina95=false;
                listaFiltros.remove(filtroGasolina95);
                break;
            default:
                break;
        }
    }

}
