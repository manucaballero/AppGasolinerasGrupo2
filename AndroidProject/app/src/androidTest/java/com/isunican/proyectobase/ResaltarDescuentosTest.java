package com.isunican.proyectobase;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Checks;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Presenter.PresenterGasolineras;
import com.isunican.proyectobase.Views.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.w3c.dom.Text;

import java.util.Objects;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
public class ResaltarDescuentosTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void resaltarTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {

        }
        ListAdapter adapter = mActivityTestRule.getActivity().adapter;

        int i = 0;
        int j = 2;

        /*Gasolinera g = (Gasolinera) adapter.getItem(0);

        LayoutInflater inflater = (LayoutInflater) mActivityTestRule.getActivity().getApplicationContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_gasolinera, null);
        TextView gasoleoA = view.findViewById(R.id.textViewGasoleoA);
        TextView gasolina95 = view.findViewById(R.id.textViewGasolina95);
        if (g.getRotulo().equals("CEPSA")) {
            view.setBackgroundColor(0xfffffd82);
            gasoleoA.setTextColor(Color.RED);
            gasolina95.setTextColor(Color.RED);
        }

        View vDescuento = adapter.getView(i, view, (ViewGroup) view.getParent());
        View vSinDescuento = adapter.getView(j, view, (ViewGroup) view.getParent());

        ColorDrawable backColor1 = (ColorDrawable) vDescuento.getBackground();
        ColorDrawable backColor2 = (ColorDrawable) vSinDescuento.getBackground();

        Assert.assertTrue(backColor1.getColor() == 0xfffffd82);
        Assert.assertEquals(backColor2.getColor(), Color.WHITE);

        TextView gasoleoADesc = view.findViewById(R.id.textViewGasoleoA);
        TextView gasolina95Desc = view.findViewById(R.id.textViewGasolina95);
        TextView gasoleoASin = view.findViewById(R.id.textViewGasoleoA);
        TextView gasolina95Sin = view.findViewById(R.id.textViewGasolina95);

        Assert.assertEquals(gasoleoADesc.getCurrentTextColor(),Color.RED);
        Assert.assertEquals(gasolina95Desc.getCurrentTextColor(),Color.RED);

        Assert.assertEquals(gasoleoASin.getCurrentTextColor(),-65536);
        Assert.assertEquals(gasolina95Sin.getCurrentTextColor(),-65536);*/

        ListView lv = mActivityTestRule.getActivity().findViewById(R.id.listViewGasolineras);
        View v1 = lv.getChildAt(0);
        View v2 = lv.getChildAt(1);
        ColorDrawable cBck1 = (ColorDrawable) v1.getBackground();
        ColorDrawable cBck2 = (ColorDrawable) v2.getBackground();

        Assert.assertEquals(cBck1.getColor(), 0xfffffd82);
        Assert.assertEquals(cBck2.getColor(), Color.WHITE);

        TextView gasolinaCon = v1.findViewById(R.id.textViewGasolina95);
        TextView gasoleoCon = v1.findViewById(R.id.textViewGasoleoA);
        TextView gasolinaSin = v2.findViewById(R.id.textViewGasolina95);
        TextView gasoleoSin = v2.findViewById(R.id.textViewGasoleoA);

        Assert.assertEquals(gasolinaCon.getCurrentTextColor(),Color.RED);
        Assert.assertEquals(gasoleoCon.getCurrentTextColor(),Color.RED);

        Assert.assertEquals(gasolinaSin.getCurrentTextColor(),Color.BLACK);
        Assert.assertEquals(gasoleoSin.getCurrentTextColor(),Color.BLACK);




        /*
       while (!((Gasolinera) adapter.getItem(i)).getTieneDescuento()) {
            i++;
       }
        Gasolinera gDescuento = (Gasolinera) adapter.getItem(i);
        //Buscamos otra gasolinera sin descuento
        while (((Gasolinera) adapter.getItem(j)).getTieneDescuento()) {
            j++;
        }
        Gasolinera gSinDescuento = (Gasolinera) adapter.getItem(j);*/

      /*onView(withIndex(withId(R.id.relativeLayoutGasolinera),i)).check(matches(withBgColor(0xfffffd82)));


        DataInteraction v1 = onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(i);
        DataInteraction v2 = onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(j);

        v1.onChildView(withId(R.id.relativeLayoutGasolinera)).check(matches(withBgColor(0xfffffd82)));

           */
    }

    /*public static Matcher<View> withBgColor(final int color) {
        Checks.checkNotNull(color);
        return new BoundedMatcher<View, LinearLayout>(LinearLayout.class) {
            @Override
            public boolean matchesSafely(LinearLayout row) {
                return color == ((ColorDrawable) row.getBackground()).getColor();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with text color: ");
            }
        };
    }
    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }*/
}
