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

public class ListViewAdapter7 extends ArrayAdapter<Info7> {
    private final Context context;
    private final ArrayList<Info7> items;

    public ListViewAdapter7(@NonNull Context context, ArrayList<Info7> items) {
        super(context, R.layout.listview_layout7, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_layout7, parent, false);

        TextView title = view.findViewById(R.id.title);
        TextView info1i = view.findViewById(R.id.info1i);

        title.setText(items.get(position).getTitle());
        info1i.setText(items.get(position).getInfo1i());

        return view;
    }
}
