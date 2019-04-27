package com.example.giuakyanroidnangcao;

import java.io.Serializable;

public class ModelMonHoc implements Serializable {
    private int ID;
    private String subjectname;
    private String subjectcode;
    private int credit;
    private String despi;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getSubjectcode() {
        return subjectcode;
    }

    public void setSubjectcode(String subjectcode) {
        this.subjectcode = subjectcode;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getDespi() {
        return despi;
    }

    public void setDespi(String despi) {
        this.despi = despi;
    }
}
