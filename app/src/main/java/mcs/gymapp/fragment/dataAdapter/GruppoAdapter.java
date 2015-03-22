package mcs.gymapp.fragment.dataAdapter;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.*;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import mcs.gymapp.R;
import mcs.gymapp.db.entita.GruppoH;
import mcs.gymapp.db.entita.GruppoI;
import mcs.gymapp.view.swipe.BaseSwipeListViewListener;
import mcs.gymapp.view.swipe.SwipeListView;


public class GruppoAdapter extends ArrayAdapter<GruppoH> {

    private List<GruppoH> data;

    private int resource;
    private LayoutInflater inflater;
    private Context  context;

    private ListView grpView;

    public GruppoAdapter(Context context, int resourceId, List<GruppoH> gH, ListView grpView) {
        super(context, resourceId, gH);
        this.context = context;
        this.resource = resourceId;

        this.grpView = grpView;

        this.data = gH;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<GruppoH> getData() {
        return data;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final GruppoH item = getItem(position);
        final GrpViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(resource, parent, false);
            holder = new GrpViewHolder();
            holder.swpLstView = (SwipeListView)  convertView.findViewById(R.id.swipList);
            holder.gruppo = (TextView) convertView.findViewById(R.id.muscolarGRP);
            convertView.setTag(holder);
        } else {
            holder = (GrpViewHolder) convertView.getTag();
        }

        final List<EserItem> data2;
        data2 = new ArrayList<EserItem>();
        for(GruppoI i : item.getGruppoI() ){
            data2.add( new EserItem(i));
        }
        final EserAdapter adapter = new EserAdapter(context.getApplicationContext(), data2);
        holder.swpLstView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            holder.swpLstView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

                @Override
                public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                    mode.setTitle("Selected ( prova )");
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_delete:
                            holder.swpLstView.skipSelected();
                            for (Integer i: holder.swpLstView.skipSelected()) {
                                adapter.getItem(i).getHolder().ivImage.setImageResource(R.drawable.rosso);
                                ((EserAdapter) holder.swpLstView.getAdapter()).addInvalidItem((int)i);
                            }
                            adapter.notifyDataSetChanged();
                            mode.finish();
                            return true;
                        default:
                            return false;
                    }
                }

                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    MenuInflater inflater = mode.getMenuInflater();
                    inflater.inflate(R.menu.swipe, menu);
                    return true;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    holder.swpLstView.unselectedChoiceStates();
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }
            });
        }
        holder.swpLstView.setSwipeListViewListener(new BaseSwipeListViewListener(this, holder, holder.swpLstView));
        holder.swpLstView.setAdapter(adapter);
        holder.gruppo.setText((CharSequence) item.getNome());
        setup(holder.swpLstView);
        return convertView;
    }

    private void setup(SwipeListView swpLst) {
        swpLst.setSwipeMode(SwipeListView.SWIPE_MODE_RIGHT);
        swpLst.setSwipeActionRight(SwipeListView.SWIPE_ACTION_REVEAL);
        swpLst.setSwipeOpenOnLongPress(false);
    }

    public int convertDpToPixel(float dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }
    public static class GrpViewHolder {
        TextView gruppo;
        SwipeListView swpLstView;
    }
}
