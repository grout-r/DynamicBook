package com.example.roman.dynamicbook;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by Roman on 09/04/2017.
 */

public class PagesArrayAdapter extends BaseAdapter {
    public PagesArrayAdapter(Context c, ArrayList<Page> al)
    {
        context = c;
        al_items = al;
    }

    public View getView(int position, View convert_view, ViewGroup parent) {
        ViewHolder holder;
        if(convert_view == null) {
            holder = new ViewHolder();
            LayoutInflater inflator = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convert_view = inflator.inflate(R.layout.page_entry, parent,
                    false);
            holder.tv_content = (TextView) convert_view.findViewById(R.id.tv_content);
            convert_view.setTag(holder);
        }
        else {
            holder = (ViewHolder) convert_view.getTag();
        }
        if (al_items.get(position).is_plain == 1)
        {
            holder.tv_content.setText(al_items.get(position).plain_content);
        }
        else
        {
            holder.tv_content.setText(al_items.get(position).url_content);
        }

        return convert_view;
    }
    public int getCount() { return al_items.size(); }
    public long getItemId(int position) { return position; }
    public Object getItem(int position) { return al_items.get(position); }

    static class ViewHolder {
        public TextView tv_content;
    }

    private Context context;
    private ArrayList<Page> al_items;

}
