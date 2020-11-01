package com.isunican.proyectobase.Utilities;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


/*
------------------------------------------------------------------
    Clase que realiza la descarga de datos remotos
    Mantiene un stream con los datos en crudo
    e incluye un método para cargarlos desde una dirección URL
------------------------------------------------------------------
*/

public class RemoteFetch {

    /**
     * Constructor privado que oculta el público ya que esta clase
     * no tiene ojetivo de que se creen objetos de ella, ya que solo
     * tiene métodos static.
     */
    private RemoteFetch(){
        throw new IllegalStateException("Utility class");
    }

    /**
     * cargaBufferDesdeURL
     *
     * Toma la direccion pasada como parámetro y descarga los datos,
     * almacenándolos en el atributo stream
     *
     * @param in String Dirección web con los datos a descargar
     * @return void
     * @throws IOException
     */
    public static BufferedInputStream cargaBufferDesdeURL(final String direccion) throws IOException {
        URL url = new URL(direccion);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.addRequestProperty("Accept", "application/json");
        return new BufferedInputStream(urlConnection.getInputStream());
    }

}
