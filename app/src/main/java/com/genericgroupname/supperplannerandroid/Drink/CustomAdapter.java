package com.genericgroupname.supperplannerandroid.Drink;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.genericgroupname.supperplannerandroid.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<Drink> drinks;

    public CustomAdapter(Context context, ArrayList drinks) {
        this.context = context;
        this.inflater = (LayoutInflater.from(context));
        this.drinks = drinks;
    }

    @Override
    public int getCount() {
        return drinks.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.list_item,null);
        TextView name= convertView.findViewById(R.id.childName);
        TextView sugar = convertView.findViewById(R.id.childSugar);
        TextView cafeine = convertView.findViewById(R.id.childCafeine);
        TextView amount = convertView.findViewById(R.id.childVolumeAmount);
        name.setText(drinks.get(position).getName());
        sugar.setText("sugar: "+drinks.get(position).getSugar().toString());
        cafeine.setText("caffeine: "+drinks.get(position).getCofeine().toString());
        amount.setText(drinks.get(position).getAmount().toString());
        return convertView;
    }
}
