package mcs.gymapp.fragment.dataAdapter;

/**
 * Created by Utente on 28/01/2015.
 */
public class MenuItem {
        private String nome;
        private String stato;
        private int tipo;
        public MenuItem(int tipo, String name, String stato) {
            this.tipo = tipo;
            this.nome = name;
            this.stato = stato;
        }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getNome() {
            return nome;
        }

        public String getStato() {
            return stato;
        }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
