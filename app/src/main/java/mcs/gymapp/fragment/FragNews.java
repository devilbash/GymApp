package mcs.gymapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mcs.gymapp.R;
import mcs.gymapp.db.entita.AllenamentoI;


public class FragNews extends Fragment implements Frag {


    public FragNews() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fra_news, container, false);
        return rootView;
    }


    public void connectData(){

    }
    public void connectError(){

    }


    @Override
    public void setAllenamento(AllenamentoI a) { }
}
