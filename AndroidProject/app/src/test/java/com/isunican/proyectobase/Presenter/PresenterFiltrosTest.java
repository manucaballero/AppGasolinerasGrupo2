package com.isunican.proyectobase.Presenter;

import com.isunican.proyectobase.Model.Gasolina95Filtro;
import com.isunican.proyectobase.Model.IFiltro;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Clase que compruba el funcionamiento de los métodos de la clase PresenterFiltros.
 *
 * @author Daniel Sánchez
 */
public class PresenterFiltrosTest {

    private PresenterFiltros sut;
    private IFiltro f1;
    private IFiltro f2;

    /**
     * Inicializamos los atributos necesarios antes de la ejecución de cada método.
     */
    @Before
    public void setUp(){
        sut = new PresenterFiltros();
        f1 = sut.getFiltroGasoleA();
        f2 = sut.getDescuentoSiFiltro();
        sut.getListaFiltros().add(f1);
        sut.getListaFiltros().add(f2);
    }

    /**
     * Prueba que comprueba que el método de eliminar filtros hace lo que debe
     */
    @Test
    public void eliminaFiltrosTest(){

        Assert.assertEquals(2, sut.getListaFiltros().size());
        Assert.assertEquals(sut.getListaFiltros().get(0), f1);
        Assert.assertEquals(sut.getListaFiltros().get(1), f2);

        sut.eliminaFiltroLista(f2.getNombre());
        Assert.assertEquals(1, sut.getListaFiltros().size());
        Assert.assertEquals(sut.getListaFiltros().get(0), f1);

        sut.eliminaFiltroLista(new Gasolina95Filtro().getNombre());
        Assert.assertEquals(1, sut.getListaFiltros().size());
        Assert.assertEquals(sut.getListaFiltros().get(0), f1);

        sut.eliminaFiltroLista(f1.getNombre());
        Assert.assertEquals(0, sut.getListaFiltros().size());
    }
}
