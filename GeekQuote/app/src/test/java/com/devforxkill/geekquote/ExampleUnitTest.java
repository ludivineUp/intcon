package com.devforxkill.geekquote;

import com.devforxkill.geekquote.model.Quote;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void length_string(){
        assertEquals(4,"anti".length());
    }

    // tests unitaires à faire : création d'une citation (texte, date, notation et tostring)

    @Test
    public void test_quote(){
        Quote q = new Quote(0, "toto", 5, LocalDate.now());
        assertEquals(5,q.getRating());
        assertEquals("toto",q.getStrQuote());
        assertEquals("toto",q.toString());
        assertEquals(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),q.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        q.setRating(0);
        assertEquals(0,q.getRating());
        q.setStrQuote("tti");
        assertEquals("tti",q.getStrQuote());
        q.setDate(LocalDate.parse("01/01/2000",DateTimeFormatter.ofPattern("dd/MM/yyyy") ));
        assertEquals("01/01/2000",q.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

}