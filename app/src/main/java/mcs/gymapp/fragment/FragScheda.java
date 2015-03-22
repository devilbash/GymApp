package mcs.gymapp.fragment;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;



import java.util.List;

import mcs.gymapp.R;
import mcs.gymapp.db.entita.AllenamentoI;
import mcs.gymapp.fragment.dataAdapter.EserItem;
import mcs.gymapp.fragment.dataAdapter.GruppoAdapter;
import mcs.gymapp.fragment.dataAdapter.MenuAdapter;


public class FragScheda extends Fragment implements Frag {
    private Activity act;
    private AllenamentoI allenamento;
    private GruppoAdapter listAdapter;
    private ListView grpView;



    public void setActivity(Activity a){
        act = a;
    }
    public void setAllenamento(AllenamentoI a) {this.allenamento = a;}


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        act = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fra_scheda, container, false);

        grpView = (ListView)  rootView.findViewById(R.id.listGrp);
        listAdapter = new GruppoAdapter(this.getActivity().getBaseContext(),
                R.layout.row_lst_gruppi,
                allenamento.getGruppoH(),
                grpView);
        grpView.setAdapter(listAdapter);

        return rootView;
    }
}
