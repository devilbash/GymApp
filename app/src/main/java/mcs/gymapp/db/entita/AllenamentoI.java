package mcs.gymapp.db.entita;

import java.util.ArrayList;
import java.util.List;


public class AllenamentoI {
    private List<GruppoH> gruppoH;
    private String nome;
    private int IDA;
    private int posn;
    private int IDGS;
    private int tipo;
    private Corso corso;
    private String note;

    public AllenamentoI() {}

    public AllenamentoI(int ida, int POSN, int IDG, String note, String nome) {
        this.IDA = ida;
        this.posn = POSN;
        this.nome = nome;
        this.note = note;
    }

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(Corso corso) {
        this.corso = corso;
    }

    public List<GruppoH> getGruppoH() {
        return gruppoH;
    }

    public void setGruppoH(List<GruppoH> gruppoH) {
        this.gruppoH = gruppoH;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void add(){}

    public int getIDA() {
        return IDA;
    }

    public void setIDA(int ida) {
        this.IDA = ida;
    }

    public int getIDGS() {
        return IDGS;
    }

    public void setIDGS(int idgs) {
        this.IDGS = idgs;
    }
    public int getPosn() {
        return posn;
    }

    public void setPosn(int POSN) {
        this.posn = POSN;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;

    }
    public void setNome(String nome) {
        this.nome = nome;

    }

    public String getNome() {
        return nome;
    }
}
