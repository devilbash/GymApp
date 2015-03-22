package mcs.gymapp.db.entita;

public class Corso {
    private int IDE;
    private String nome;
    private int IDI;
    private String note;

    public Corso() {    }
    public Corso(int ide, String nome, int idi, String note) {
        this.IDE = ide;
        this.nome = nome;
        this.IDI = idi;
        this.note = note;
    }

    public int getIDI() {
        return IDI;
    }

    public void setIDI(int IDI) {
        this.IDI = IDI;
    }

    public int getIDE() {
        return IDE;
    }

    public void setIDE(int ide) {
        this.IDE = ide;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String nome) {
        this.note = note;
    }

}
