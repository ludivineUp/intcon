package com.devforxkill.geekquote;

import android.content.Context;
import android.test.mock.MockContext;

import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;

import com.devforxkill.geekquote.dao.DaoFactory;
import com.devforxkill.geekquote.model.Quote;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static android.content.Context.CONTEXT_IGNORE_SECURITY;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class IntegrationTest {
    // tests d'intégration : CRUD en base par la DAOFactory, test de l'id autoincrement
    /*public ArrayList<Quote> getQuotes();
    public int addQuote(Quote q);
    public void updateQuote(Quote q);
    public Quote getByID(int id);*/
    // 5 tests à faire
    private Context context = ApplicationProvider.getApplicationContext();


    @Test
    public void getAll_test(){
        assertNotNull(DaoFactory.getQuoteDao(context).getQuotes());
    }
}
