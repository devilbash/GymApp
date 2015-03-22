package mcs.gymapp.utility.menu;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import mcs.gymapp.db.entita.AllenamentoH;
import mcs.gymapp.db.DataBaseAdapter;
import mcs.gymapp.db.entita.AllenamentoI;
import mcs.gymapp.db.entita.Corso;
import mcs.gymapp.db.entita.Esercizio;
import mcs.gymapp.db.entita.GruppoH;
import mcs.gymapp.db.entita.GruppoI;
import mcs.gymapp.db.entita.Serie;
import mcs.gymapp.fragment.dataAdapter.MenuItem;

public class Scheda {
    private AllenamentoH scheda;
    private ArrayList<MenuItem> menuList;
    private boolean sync;
    private DataBaseAdapter dbA;
    private final String NEWS = "News";
    private final String ESCI = "Esci";

    public Scheda(ArrayList<MenuItem> mL, Context context, boolean sync) {
        this.sync = sync;
        this.menuList = mL;

        setup();
        if (this.sync) {
            dbA = new DataBaseAdapter(context);
            dbA.open();
            scheda();
            dbA.close();
        }
    }

    private void setup() {
        menuList.add (new MenuItem(0, "News",""));

    }

    private void scheda() {
        final String etichetta= "Allenamento ";

        scheda = dbA.getLastAllenamento();
        List<AllenamentoI> aI;
        List<GruppoH> gHS;
        List<GruppoI> gIS;
        Esercizio e;
        Corso c;

        aI = dbA.getAllenamentoI(scheda.getIDA());

        scheda.setListAI(aI);
        for (AllenamentoI a: aI) {

            if (a.getTipo() == 2) {
            //corso
                c = dbA.getCorso(a.getIDGS());
                a.setCorso(c);
                menuList.add(new MenuItem(1, etichetta + a.getNome(), ""));
            }else if (a.getTipo() == 1) {
            //fitnes
                gHS = dbA.getGruppoH(a.getIDGS());
                a.setGruppoH(gHS);
                menuList.add(new MenuItem(1, etichetta + a.getNome(), ""));

                for (GruppoH gH : gHS) {
                    gIS = dbA.getGruppoI(gH.getIDG());
                    gH.setGruppoI(gIS);

                    for (GruppoI gI : gIS) {
                        e = dbA.getEser(gI.getIDE());
                        gI.setEser(e);
                    }
                }
            }
        }
    }

    public AllenamentoH getScheda() {return scheda;}
}
