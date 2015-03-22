package mcs.gymapp.db.entita;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Utente on 31/01/2015.
 */
public class GruppoI {
    private Esercizio eser;
    private int IDG;
    private int posn;
    private int IDE;
    private int NSer;
    private int NRip;
    private int riposo;
    private int sSer;

    public GruppoI() {}

    public int getRiposo() {
        return riposo;
    }

    public void setRiposo(int riposo) {
        this.riposo = riposo;
    }
    public Esercizio getEser() {
        return eser;
    }

    public void setEser(Esercizio eser) {
        this.eser = eser;
    }

    public int getIDG() {
        return IDG;
    }

    public void setIDG(int IDG) {
        this.IDG = IDG;
    }

    public int getPosn() {
        return posn;
    }

    public void setPosn(int posn) {
        this.posn = posn;
    }

    public int getIDE() {
        return IDE;
    }

    public void setIDE(int IDE) {
        this.IDE = IDE;
    }

    public int getNRip() {
        return NRip;
    }

    public void setNRip(int NRip) {
        this.NRip = NRip;
    }

    public int getNSer() {
        return NSer;
    }

    public void setNSer(int NSer) {
        this.NSer = NSer;
    }

    public int getSSer() {
        return sSer;
    }

    public void setSSer(int sSer) {
        this.sSer = sSer;
    }

}
