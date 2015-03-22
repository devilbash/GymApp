package mcs.gymapp.db.entita;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Utente on 31/01/2015.
 */
public class GruppoH {
    private List<GruppoI> listGruppoI;
    private int IDGS;
    private int posn;
    private int IDG;
    private String nome;
    private String note;


    public GruppoH() {
    }
    public GruppoH(int IDGS,int pos, int IDG, String note, String nome) {
        this.note = note;
        this.nome = nome;
        this.IDGS = IDGS;
        this.IDG = IDG;
        this.posn = pos;
    }

    public int getPosn() {
        return posn;
    }

    public void setPosn(int posn) {
        this.posn = posn;
    }

    public List<GruppoI> getGruppoI() {
        return listGruppoI;
    }

    public void setGruppoI(List<GruppoI> listGruppoI) {
        this.listGruppoI = listGruppoI;
    }

    public int getIDGS() {
        return IDGS;
    }

    public void setIDGS(int IDGs) {
        this.IDGS = IDGs;
    }

    public int getIDG() {
        return IDG;
    }

    public void setIDG(int IDG) {
        this.IDG = IDG;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String NOTE) {
        this.note = NOTE;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
}
