package com.devforxkill.geekquote;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.devforxkill.geekquote.dao.DaoFactory;
import com.devforxkill.geekquote.model.Quote;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.devforxkill.geekquote", appContext.getPackageName());
    }

    /*@RunWith(RobolectricTestRunner.class)
    public static class IntegrationTest {
        // tests d'intégration : CRUD en base par la DAOFactory, test de l'id autoincrement
        public ArrayList<Quote> getQuotes();
        public int addQuote(Quote q);
        public void updateQuote(Quote q);
        public Quote getByID(int id);
        // 5 tests à faire
        private Context context = ApplicationProvider.getApplicationContext();


        @Test
        public void getAll_test(){
            assertNotNull(DaoFactory.getQuoteDao(context).getQuotes());
        }

        @Test
        public void add_test(){
            Quote q = new Quote(0, "toto", 5, LocalDate.now());
            assertTrue(DaoFactory.getQuoteDao(context).addQuote(q) > 0);
            // test auto increment
            int size = DaoFactory.getQuoteDao(context).getQuotes().size();
            assertTrue(q.getId() == size);
        }

        @Test
        public void getbyid_test(){
            Quote q = DaoFactory.getQuoteDao(context).getByID(1);
            Log.d("Test", q.toString());
            assertNotNull(q);
        }
    }*/
}