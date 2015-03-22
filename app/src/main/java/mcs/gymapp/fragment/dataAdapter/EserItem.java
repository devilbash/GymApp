package mcs.gymapp.fragment.dataAdapter;

import android.graphics.drawable.Drawable;


import mcs.gymapp.db.entita.GruppoI;
import mcs.gymapp.view.swipe.SwipeListView;

/**
 * Created by Utente on 18/01/2015.
 */
public class EserItem {
    private Drawable icon;
    private String name;
    private int nRip;
    private int nSer;
    private int riposo;

    private EserAdapter.EserViewHolder holder;

    public EserItem(GruppoI i) {
        name = i.getEser().getNome();
        nSer = i.getNSer();
        nRip = i.getNRip();
        riposo = i.getRiposo();
    }


    public int getRiposo() {
        return riposo;
    }


    public EserAdapter.EserViewHolder getHolder() {
        return holder;
    }

    public void setHolder(EserAdapter.EserViewHolder hold) {
        this.holder = hold;
    }

    public int getnRip() {
        return nRip;
    }
    public int getnSer() {
        return nSer;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Drawable getIcon() {
        return icon;
    }
    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void scalaSerie() {
        nSer--;
    }
}

