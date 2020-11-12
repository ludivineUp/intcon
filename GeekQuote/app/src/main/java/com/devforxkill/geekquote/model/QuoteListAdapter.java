package com.devforxkill.geekquote.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.devforxkill.geekquote.R;

import java.util.List;

public class QuoteListAdapter extends ArrayAdapter<Quote> {

    private int ressourcelocal;
    private int textViewResourceId;

    public QuoteListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Quote> objects) {
        super(context, resource, textViewResourceId, objects);
        this.ressourcelocal = resource;
        this.textViewResourceId = textViewResourceId;
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("AHHHHHHHHHHHHHHHHHHHHHHH","getview in adaptar");
        // génère la vue d'un élément de la liste
       if (convertView == null) {
           convertView = LayoutInflater.from(getContext()).inflate(ressourcelocal, parent, false);
       }

        Quote quote = getItem(position);

        if(quote != null) {
            Log.d("AHHHHHHHHHHHHHHHHHHHHHHH","quote not null");
            final TextView tv = (TextView) convertView.findViewById(textViewResourceId);
            tv.setTextSize(32);
            if(position % 2 == 0){
                tv.setBackgroundResource(R.color.colorbgQuoteDark);
            }else{
                tv.setBackgroundResource(R.color.colorbgQuotelight);
            }
        }
        return super.getView(position, convertView, parent);
    }
}
