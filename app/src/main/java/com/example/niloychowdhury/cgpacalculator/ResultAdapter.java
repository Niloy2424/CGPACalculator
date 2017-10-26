package com.example.niloychowdhury.cgpacalculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.niloychowdhury.cgpacalculator.Model.Result;

import java.util.ArrayList;

/**
 * Created by Niloy Chowdhury on 2017-07-24.
 */

public class ResultAdapter extends ArrayAdapter<Result> {
    private Context context;
    private ArrayList<Result> results;
    public ResultAdapter(@NonNull Context context,@NonNull ArrayList<Result>results) {
        super(context, R.layout.resrow_layout, results);
        this.context=context;
        this.results=results;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.resrow_layout,null);
        TextView serialTV= (TextView) view.findViewById(R.id.serialTV);
        TextView creditTV= (TextView) view.findViewById(R.id.creditTV);
        TextView gpaTV= (TextView) view.findViewById(R.id.gpaTV);
        TextView totalTV= (TextView) view.findViewById(R.id.totalTV);
        serialTV.setText(String.valueOf(position+1));
        creditTV.setText(String.valueOf(results.get(position).getCredit()));
        gpaTV.setText(String.valueOf(results.get(position).getGpa()));
        totalTV.setText(String.valueOf(results.get(position).getTotal()));
        return view;
    }
}
