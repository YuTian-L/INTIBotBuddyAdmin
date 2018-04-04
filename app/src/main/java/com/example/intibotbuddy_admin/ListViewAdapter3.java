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
 * Created by TIAN☺ on 02/04/2018.
 */

public class ListViewAdapter3 extends ArrayAdapter<Info3> {
    private final Context context;
    private final ArrayList<Info3> items;

    public ListViewAdapter3(@NonNull Context context, ArrayList<Info3> items) {
        super(context, R.layout.listview_layout3, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_layout3, parent, false);

        TextView title = view.findViewById(R.id.title);
        TextView info1i = view.findViewById(R.id.info1i);
        TextView info2i= view.findViewById(R.id.info2i);
        TextView info3i = view.findViewById(R.id.info3i);
        TextView info4i = view.findViewById(R.id.info4i);

        title.setText(items.get(position).getTitle());
        info1i.setText(items.get(position).getInfo1i());
        info2i.setText(items.get(position).getInfo2i());
        info3i.setText(items.get(position).getInfo3i());
        info4i.setText(items.get(position).getInfo4i());

        return view;
    }
}
