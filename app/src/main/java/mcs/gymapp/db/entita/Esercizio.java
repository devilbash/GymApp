package mcs.gymapp.db.entita;

public class Esercizio {
    private int IDE;
    private String nome;
    private String note;

    public Esercizio() {    }
    public Esercizio(int ide, String nome, String note) {
        this.IDE = ide;
        this.nome = nome;
        this.note = note;
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
