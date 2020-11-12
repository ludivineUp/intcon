package com.devforxkill.geekquote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.devforxkill.geekquote.model.Quote;

import java.time.format.DateTimeFormatter;

public class QuoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        final Quote quote = (Quote) getIntent().getExtras().get("quote");
        final TextView tv = (TextView) findViewById(R.id.quote_input);
        tv.setText(quote.getStrQuote());
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setNumStars(quote.getRating());
        TextView dateTv = (TextView) findViewById(R.id.creation_date_quote);
        dateTv.setText(quote.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        Button okBtn = (Button) findViewById(R.id.okBtn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // faut changer la notation à la main
                quote.setRating(ratingBar.getNumStars());
                quote.setStrQuote(tv.getText().toString());
                getIntent().putExtra("quote",quote);
                setResult(RESULT_OK, getIntent());
                finish();
            }
        });
        Button cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED, getIntent());
                finish();
            }
        });
    }
}