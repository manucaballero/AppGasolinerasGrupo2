package com.isunican.proyectobase;

import android.Manifest;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import com.isunican.proyectobase.Views.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DesaplicarFiltrosUITest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION);

    RecyclerView mRecyclerView;
    MainActivity mActivity;

    @Before
    public void setUp(){
        mActivity=mActivityTestRule.getActivity();
        mRecyclerView=mActivity.findViewById(R.id.recyclerViewFiltros);
    }


    @Test
    public void desaplicarFiltrosTest() throws NoSuchFieldException, IllegalAccessException {
        onView(withId(R.id.buttonFiltrar)).perform(click());

        //onView(withId(R.id.popUpFiltros)).check(matches(isDisplayed()));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        onView(withId(R.id.checkBoxDescuentoSi)).perform(click());
        onView(withId(R.id.checkBoxGasoleoA)).perform(click());
        onView(withId(R.id.buttonApply)).perform(click());

        onView(withText("Con Descuento")).check(matches(isDisplayed()));
        onView(withText("GasóleoA")).check(matches(isDisplayed()));


        onView(withText("GasóleoA")).perform(click());

        onView(withId(R.id.buttonSiBorrarFiltro)).perform(click());

        onView(withText("Con Descuento")).check(matches(isDisplayed()));




        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




        /*

        //onView(withId(R.id.popUpFiltros)).check(matches(not(isDisplayed())));

        //onView(withId(R.id.recyclerViewFiltros))
        AdapterFiltros adapter=mActivityTestRule.getActivity().adapterFiltros;

        //List<IFiltro> lista=adapter.getListaFiltros();

        View v;
        DataInteraction d;
        IFiltro f;
        for(int i=0; i<adapter.getItemCount(); i++){
            d = onData(anything()).inAdapterView(withId(R.id.recyclerViewFiltros)).atPosition(i);
            v = mRecyclerView.getChildAt(i);
            //int id=v.getId();

            //onView(withId(id)).check(matches(withText("Con Descuento")));

            onView(withText("Con Descuento")).check(matches(isDisplayed()));

            //d.onChildView(withText("GasóleoA")).check(matches(withText(f.getNombre())));

            Log.d("desaplicarPrueba", "pasa por aquí ");


        }


        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/



    }



    /*
    @Test
    public void desaplicarFiltrosTest(){
        onView(withId(R.id.buttonFiltrar)).perform(click());
        onView(withId(R.id.checkBoxGasoleoA)).perform(click());
        onView(withId(R.id.checkBoxDescuentoSi)).perform(click());
        onView(withId(R.id.buttonApply)).perform(click());

        int itemCount=mRecyclerView.getAdapter().getItemCount();

        if(itemCount > 0) {
            for(int i=0; i < itemCount; i++) {

                // clicking the item
                onView(withId(R.id.recyclerViewFiltros))
                        .perform(RecyclerViewActions.actionOnItemAtPosition(i, click());

                // check if the ViewHolder is being displayed
                onView(new RecyclerViewMatcher(this.resId)
                        .atPositionOnView(i, R.id.cardview))
                        .check(matches(isDisplayed()));

                // checking for the text of the first one item
                if(i == 0) {
                    onView(new RecyclerViewMatcher(this.resId)
                            .atPositionOnView(i, R.id.ingredientName))
                            .check(matches(withText("Farbstoffe")));
                }

            }*/
        }




        /*
        AdapterFiltros adapter=mActivityTestRule.getActivity().adapterFiltros;



        IFiltro f;
        DataInteraction d;

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("desaplicarPrueba", "pasa por aquí "+adapter.getItemCount());

        List<IFiltro> lista=adapter.getListaFiltros();

        for(int i=0; i<adapter.getItemCount(); i++){
            d = onData(anything()).inAdapterView(withId(R.id.recyclerViewFiltros)).atPosition(i);
            f = lista.get(i);

            //d.onChildView(withText("GasóleoA")).check(matches(withText(f.getNombre())));

            Log.d("desaplicarPrueba", "pasa por aquí "+f);


        }*/





