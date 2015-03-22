package mcs.gymapp.db.entita;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class AllenamentoH implements  Comparable{
    private List<AllenamentoI> listAI;
    private int IDA;
    private int IDI;
    private Date dataI;
    private Date dataF;
    private String note;

    public AllenamentoH() {
    }


    public AllenamentoH(int IDA, int IDI, Date dataI, Date dataF, String note) {
        this.IDA = IDA;
        this.IDI = IDI;
        this.dataI = dataI;
        this.dataF = dataF;
        this.note = note;
    }
    public List<AllenamentoI> getListAI() {
        return listAI;
    }

    public void setListAI(List<AllenamentoI> listAI) {
        this.listAI = listAI;
    }
    public int getIDA() {
        return IDA;
    }

    public void setIDA(int IDA) {
        this.IDA = IDA;
    }

    public int getIDI() {
        return IDI;
    }

    public void setIDI(int IDI) {
        this.IDI = IDI;
    }

    public Date getDataI() {
        return dataI;
    }

    public void setDataI(Date dataI) {
        this.dataI = dataI;
    }

    public Date getDataF() {
        return dataF;
    }

    public void setDataF(Date dataF) {
        this.dataF = dataF;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @Override
    public int compareTo(Object another) {
        Date d1 = this.getDataI();
        Date d2 = ((AllenamentoH) another).getDataI();

        return d1.compareTo(d2);
    }
}
