package mcs.gymapp.fragment.dataAdapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import mcs.gymapp.R;

public class MenuAdapter extends ArrayAdapter<MenuItem> {

    private int resource;
    private LayoutInflater inflater;

    public MenuAdapter(Context context, int resourceId, List<MenuItem> objects) {
        super(context, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        MenuItem item = getItem(position);

        ViewHolder holder;

        if (v == null) {
            v = inflater.inflate(resource, parent, false);
            holder = new ViewHolder();
            holder.nome= (TextView) v.findViewById(R.id.menuNome);
            holder.stato= (TextView) v.findViewById(R.id.menuStato);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.nome.setText(item.getNome());
        holder.stato.setText(item.getStato());

        return v;
    }

    private static class ViewHolder {
        TextView nome;
        TextView stato;
    }
}