package mcs.gymapp.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import mcs.gymapp.R;
import mcs.gymapp.db.entita.AllenamentoI;
import mcs.gymapp.fragment.dataAdapter.GruppoAdapter;

import android.support.v4.app.Fragment;

public class FragCorso extends Fragment  implements  Frag{
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
        View rootView = inflater.inflate(R.layout.fra_corso, container, false);



        return rootView;
    }
}
