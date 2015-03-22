package mcs.gymapp.db.entita;

import java.util.List;

public class Serie {
    private int IDS;
    private int IDE;
    private int ripet;
    private int riposo;
    private String note;
    private int sserie;
    private String nome;
    private List<Esercizio> listEser;

    public Serie(int IDS, int IDE, int ripet, int riposo, String note, int sserie, String nome) {
        this.IDS = IDS;
        this.IDE = IDE;
        this.ripet = ripet;
        this.riposo = riposo;
        this.note = note;
        this.sserie = sserie;
        this.nome = nome;
    }

    public List<Esercizio> getEser() {
        return listEser;
    }

    public void setEser(List<Esercizio> listEser) {
        this.listEser = listEser;
    }

    public int getIDS() {
        return IDS;
    }

    public void setIDS(int IDS) {
        this.IDS = IDS;
    }

    public int getIDE() {
        return IDE;
    }

    public void setIDE(int IDE) {
        this.IDE = IDE;
    }

    public int getRipet() {
        return ripet;
    }

    public void setRipet(int ripet) {
        this.ripet = ripet;
    }

    public int getRiposo() {
        return riposo;
    }

    public void setRiposo(int riposo) {
        this.riposo = riposo;
    }

    public String getNOTE() {
        return note;
    }

    public void setNOTE(String note) {
        this.note = note;
    }

    public int getSserie() {
        return sserie;
    }

    public void setSserie(int sserie) {
        this.sserie = sserie;
    }

    public String getNome() {
        return nome;
    }
}
