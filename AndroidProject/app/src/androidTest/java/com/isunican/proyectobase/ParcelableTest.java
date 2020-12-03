package com.isunican.proyectobase;

import android.os.Parcel;
import com.isunican.proyectobase.Model.Descuento;
import com.isunican.proyectobase.Model.Vehiculo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.assertEquals;

/**
 * Comprobamos el correcto funcionamiento de parcelable
 *
 * La prueba se encuentra en androidTest ya que sin usar
 * roboelectric esta era la unica forma que encontré de
 * poder usar Parcel.
 *
 * @author Manuel Caballero Rabago
 * */
@RunWith(JUnit4.class)
public class ParcelableTest {

    @Test
    public void parcelTest(){
        //Creamos el descuento
        Descuento descuento = new Descuento("Descuento del 25%","Descuento del 25% en las gasolineras de la red Cepsa",25);
        //Obtenemos acceso al pool de parcel
        Parcel parcel = Parcel.obtain();
        //Guardamos el descuento en el parcel
        descuento.writeToParcel(parcel, 0);
        //Movemos el parcel a la posicion 0
        parcel.setDataPosition(0);
        //Obtenemos el descuento almacenado en el parcel
        Descuento descuentoDesdeParcel = Descuento.CREATOR.createFromParcel(parcel);
        //Comprobamos que el descuento guardado en el parcel es el mismo y por ello parcel funciona correctamente
        assertEquals(descuento, descuentoDesdeParcel);

        //Creamos el vehiculo
        Vehiculo vehiculo = new Vehiculo("Seat Ibiza");
        //Obtenemos acceso al pool de parcel
        Parcel parcelVehiculo = Parcel.obtain();
        //Guardamos el vehiculo en el parcel
        vehiculo.writeToParcel(parcelVehiculo,0);
        //Movemos el parcel a la posicion 0
        parcelVehiculo.setDataPosition(0);
        //Obtenemos el vehiculo almacenado en el parcel
        Vehiculo vehiculoDesdeParcel = Vehiculo.CREATOR.createFromParcel(parcelVehiculo);
        //Comprobamos que el modelo del vehiculo guardado en el parcel es el mismo que el del vehiculo original y por ello parcel funciona correctamente
        assertEquals(vehiculo.getModelo(), vehiculoDesdeParcel.getModelo());
    }
}
