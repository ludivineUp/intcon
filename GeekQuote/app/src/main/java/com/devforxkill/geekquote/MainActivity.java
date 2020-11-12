package com.devforxkill.geekquote;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.devforxkill.geekquote.dao.DaoFactory;
import com.devforxkill.geekquote.model.Quote;
import com.devforxkill.geekquote.model.QuoteListAdapter;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // tableau dynamique de Quotes
    private ArrayList<Quote> quotes;
    private QuoteListAdapter quoteListAdapter;
    private ListView quoteListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        quotes = new ArrayList<>();


        // deprecated car maintenant les citation sont stockés proprement
        //initQuotes();

        initQuotesWithDao();

        quoteListView = (ListView) findViewById(R.id.quote_list);
        quoteListAdapter = new QuoteListAdapter(this, android.R.layout.simple_list_item_1  , android.R.id.text1, quotes);
        quoteListView.setAdapter(quoteListAdapter);

        // gestion du click long sur les citations de la list
        quoteListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Quote quote = (Quote) quoteListView.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, QuoteActivity.class);
                intent.putExtra("quote", quote);
                intent.putExtra("position", position);
                // on attends de la nouvelle activité
                startActivityForResult(intent, 1);
                return false;
            }
        });
        final TextView tv = (TextView) findViewById(R.id.quote_input);
        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tv.setText("");
                return false;
            }
        });
    }

    private void initQuotesWithDao() {
        quotes = DaoFactory.getQuoteDao(this).getQuotes();
        for(Quote q : quotes){
            Log.d("QUOTES",q.getStrQuote());
        }
        // si y a rien en base pour tester l'affichage
        if(quotes == null || quotes.size() == 0){
            initQuotes();
        }
        Log.d("AHHHHHHHHHHHHHHHHHHHHHHH",""+quotes.size());
    }

    //
    // deprecated car maintenant les citation sont stockés proprement
    private void initQuotes(){
        String[] quoteExamples = getResources().getStringArray(R.array.ex_qotes);
        for(String s : quoteExamples){
            // date actuelle : LocalDate.now()
            // Attention les dates sont complexes à utiliser en java
            quotes.add(new Quote(0, s,0, LocalDate.now()));
            // ajouter la citation dans la vue version du début car l'adapter cela se fait automatiquement
            //addQuoteView(q);
        }
    }

    public void addQuote(View view){
        // ajouter la quote dans l array list
        Log.d("AHHHHHHHHHHHHHHHHHHHHHHH","on addQuote "+quotes.size());
        String stringQuote = ((TextView) findViewById(R.id.quote_input)).getText().toString();
        Log.d("AHHHHHHHHHHHHHHHHHHHHHHH","stringQuote "+stringQuote+" end");
        if(!stringQuote.equals("")){
            int ratingQuote = ((RatingBar) findViewById(R.id.quote_rating)).getNumStars();
            Quote q = new Quote(0,stringQuote,ratingQuote, LocalDate.now());
            int id = DaoFactory.getQuoteDao(this).addQuote(q);
            q.setId(id);
            quotes.add(q);

            Log.d("AHHHHHHHHHHHHHHHHHHHHHHH","on ajoute "+quotes.size());
            // affiche la nouvelle citation dans la vue version du début car l'adapter cela se fait automatiquement
            // addQuoteView(q);
        }
        //TextView tvinput = (TextView) findViewById(R.id.quote_input);
        //tvinput.setText(R.string.quote_invit);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                // la citation renvoyée par la quote activité qui a changée
                Quote q = (Quote) data.getExtras().get("quote");
                int position = (Integer) data.getExtras().get("position");
                quotes.set(position, q);
                quoteListAdapter.notifyDataSetChanged();
            }
        }
    }

    // on va remplacer l'ajout des citations dans la vue par un mécanisme plus générique : Adaptater
    /*private void addQuoteView(Quote q) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.listscroll);
        final TextView tv = new TextView(this);
        tv.setTextSize(32);
        tv.setText(q.getStrQuote());
        if(quotes.size() % 2 == 0){
            tv.setBackgroundResource(R.color.colorbgQuoteDark);
        }else{
            tv.setBackgroundResource(R.color.colorbgQuotelight);
        }
        tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuoteActivity.class);
                intent.putExtra("quotestr", tv.getText().toString());
                startActivity(intent);
                return true;
            }
        });
        layout.addView(tv);
        TextView tvinput = (TextView) findViewById(R.id.quote_input);
        tvinput.setText(R.string.quote_invit);
    }*/
}