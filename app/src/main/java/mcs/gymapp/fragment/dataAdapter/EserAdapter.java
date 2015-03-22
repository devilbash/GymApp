package mcs.gymapp.fragment.dataAdapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import mcs.gymapp.R;
import mcs.gymapp.view.digitalclock.CronometroView;
import mcs.gymapp.view.swipe.BaseSwipeListViewListener;
import mcs.gymapp.view.swipe.SwipeListView;


public class EserAdapter  extends BaseAdapter {

    List<Integer> invalidItem = new ArrayList<Integer>();


    private List<EserItem> data;
    private Context context;
    public EserAdapter(Context context, List<EserItem> data) {
        this.context = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public EserItem getItem(int position) {
        return data.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final EserItem item = getItem(position);
        EserViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.row_swp_eser, parent, false);
            holder = new EserViewHolder();
            holder.ivImage  = (ImageView) convertView.findViewById(R.id.image);
            holder.tvNome   = (TextView)  convertView.findViewById(R.id.nomeEser);
            holder.tvNSerie = (TextView)  convertView.findViewById(R.id.nSerie);
            holder.tvNRip   = (TextView)  convertView.findViewById(R.id.nRip);
            holder.chronometer = (CronometroView) convertView.findViewById(R.id.cronometro);
            holder.riposo = item.getRiposo();
            convertView.setTag(holder);
        } else {
            holder = (EserViewHolder) convertView.getTag();
        }
        ((SwipeListView)parent).recycle(convertView, position);
        item.setHolder(holder);

        if (item.getnSer() == 0 )
            holder.ivImage.setImageResource(R.drawable.verde);
        holder.tvNome.setText(item.getName());
        holder.tvNRip.setText(((Integer) item.getnRip()).toString());
        holder.tvNSerie.setText(((Integer) item.getnSer()).toString());
        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        boolean x = true;
        for(int i :invalidItem)
            if (i == position)
                x = false;
        return x;
    }
    public void addInvalidItem (int position){
        invalidItem.add(position);
    }


    public static class EserViewHolder {
        ImageView ivImage;
        TextView tvNome;
        TextView tvNSerie;
        TextView tvNRip;
        int riposo;
        CronometroView chronometer;

        public void countDown(BaseSwipeListViewListener baseSwipeListViewListener, int position){
            chronometer.countDown(riposo, baseSwipeListViewListener, position);
        }
    }
}
