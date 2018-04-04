package com.example.intibotbuddy_admin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by TIAN☺ on 03/04/2018.
 */

public class ListViewAdapter2 extends ArrayAdapter<Info2> {
    private final Context context;
    private final ArrayList<Info2> items;

    public ListViewAdapter2(@NonNull Context context, ArrayList<Info2> items) {
        super(context, R.layout.listview_layout2, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_layout2, parent, false);

        TextView title = view.findViewById(R.id.title);
        TextView info1i = view.findViewById(R.id.info1i);

        title.setText(items.get(position).getTitle());
        info1i.setText(items.get(position).getInfo1i());

        return view;
    }
}
