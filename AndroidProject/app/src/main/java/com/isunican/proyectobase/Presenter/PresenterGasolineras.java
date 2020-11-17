package com.isunican.proyectobase.Presenter;

import android.util.Log;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Utilities.ParserJSONGasolineras;
import com.isunican.proyectobase.Utilities.RemoteFetch;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;

/*
------------------------------------------------------------------
    Clase presenter con la logica de gasolineras
    Mantiene un objeto ListaGasolineras que es el que mantendrá
    los datos de las gasolineras cargadas en nuestra aplicación
    Incluye métodos para gestionar la lista de gasolineras y
    cargar datos en ella.
------------------------------------------------------------------
*/
public class PresenterGasolineras {

    private List<Gasolinera> gasolineras;

    //URLs para obtener datos de las gasolineras
    //https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/help
    public static final String URL_GASOLINERAS_SPAIN="https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/";
    public static final String URL_GASOLINERAS_CANTABRIA="https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/FiltroCCAA/06";
    public static final String URL_GASOLINERAS_SANTANDER="https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/FiltroMunicipio/5819";
    public static final String SANTANDER="Santander";

    /**
     * Constructor, getters y setters
     */
    public PresenterGasolineras(){
        gasolineras = new ArrayList<>();
    }

    public List<Gasolinera> getGasolineras(){
        return gasolineras;
    }

    public void setGasolineras(List<Gasolinera> l) {
        this.gasolineras = l;
    }


    /**
     * cargaDatosGasolineras
     *
     * Carga los datos de las gasolineras en la lista de gasolineras de la clase.
     * Para ello llama a métodos de carga de datos internos de la clase ListaGasolineras.
     * En este caso realiza una carga de datos remotos dada una URL
     *
     * Habría que mejorar el método para que permita pasar un parámetro
     * con los datos a cargar (id de la ciudad, comunidad autónoma, etc.)
     *
     * @param
     * @return boolean Devuelve true si se han podido cargar los datos
     */
    public boolean cargaDatosGasolineras() {
        return cargaDatosRemotos(URL_GASOLINERAS_CANTABRIA);
    }

    /**
     * cargaDatosDummy
     *
     * Carga en la lista de gasolineras varias gasolineras definidas a "mano"
     * para hacer pruebas de funcionamiento
     *
     * @param
     * @return boolean
     */
    public boolean cargaDatosDummy(){
        this.gasolineras.add(new Gasolinera(1000,SANTANDER,SANTANDER, "Av Valdecilla", 90,90,"AVIA","43.45741814","-3.82677519"));
        this.gasolineras.add(new Gasolinera(1053,SANTANDER,SANTANDER, "Plaza Matias Montero", 100,90,"CEPSA","43.25741814","-3.84477519"));
        this.gasolineras.add(new Gasolinera(420,SANTANDER,SANTANDER, "Area Arrabal Puerto de Raos", 1.249,1.279,"E.E.S.S. MAS, S.L.","43.45741814","-3.82677519"));
        this.gasolineras.add(new Gasolinera(9564,SANTANDER,SANTANDER, "Av Parayas", 1.189,1.269,"CEPSA","43.40741814","-3.92677519"));
        this.gasolineras.add(new Gasolinera(1025,SANTANDER,SANTANDER, "Calle el Empalme", 1.259,1.319,"CARREFOUR","43.42741814","-3.02677519"));
        return true;
    }

    /**
     * cargaDatosLocales
     *
     * A partir de la dirección de un fichero JSON pasado como parámetro:
     * Parsea la información para obtener una lista de gasolineras.
     * Finalmente, dicha lista queda almacenada en la clase.
     *
     * @param String Nombre del fichero
     * @return boolean Devuelve true si se han podido cargar los datos
     */
    public boolean cargaDatosLocales(String fichero){
        return(fichero != null);
    }

    /**
     * cargaDatosRemotos
     *
     * A partir de la dirección pasada como parámetro:
     * Utiliza RemoteFetch para cargar el fichero JSON ubicado en dicha URL
     * en un stream de datos.
     * Luego utiliza ParserJSONGasolineras para parsear dicho stream
     * y extraer una lista de gasolineras.
     * Finalmente, dicha lista queda almacenada en la clase.
     *
     * @param String Dirección URL del JSON con los datos
     * @return boolean Devuelve true si se han podido cargar los datos
     */
    public boolean cargaDatosRemotos(String direccion){
        try {
            BufferedInputStream buffer = RemoteFetch.cargaBufferDesdeURL(direccion);
            gasolineras = ParserJSONGasolineras.parseaArrayGasolineras(buffer);
            Log.d("ENTRA", "Obten gasolineras:" + gasolineras.size());
            return true;
        } catch (Exception e) {
            Log.e("ERROR", "Error en la obtención de gasolineras: " + e.getMessage());
            return false;
        }
    }

    public void ordenaLista() {
        //Variable que nos permite saber si ha habido movimiento durante la ronda
        //Si en una ronda no hay movimiento, el programa sale, ya que ya esta la lista ordenada
        boolean movimiento = true;
        //Contador que nos indica cuantas rondas comparando parejas llevamos en el bucle
        int contRondas = 0;
        //Mientras que haya movimiento, comprobaremos las posiciones
        while(movimiento){
            /* Iniciamos el boleano como falso, y si cambia durante el bucle, es que ha habido un movimiento */
            movimiento = false;
			/*comenzamos el bucle en 1, y comparamos con el anterior para no salirnos de los limites
			de la array */
            for(int i=1;i<gasolineras.size()-contRondas;i++){
                /* Si el número de la derecha es menor que el de la izquierda, los intercambia */
                if(gasolineras.get(i).getGasoleoAConDescuento()<gasolineras.get(i-1).getGasoleoAConDescuento()){
                    /*Como ha habido movimiento, lo indicamos en el boleano que tenemos
                     * así cuando acabe el bucle, comenzará de nuevo
                     */
                    movimiento=true;
                    /* intercambiamos las posiciones */
                    /* guardamos uno de los valores temporalmente en otra variable para evitar su pérdida */
                    Gasolinera aux = gasolineras.get(i);
                    //Intercambiamos los valores en sendas posiciones
                    gasolineras.set(i,gasolineras.get(i-1));
                    gasolineras.set(i-1, aux);
                }
            }
        }
    }





}
