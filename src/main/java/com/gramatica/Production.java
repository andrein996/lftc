package com.gramatica;

import java.util.List;

public class Production {

    private String stanga;
    private List<String> dreapta;

    public Production(String stanga, List<String> dreapta) {
        this.stanga = stanga;
        this.dreapta = dreapta;
    }

    public String getStanga() {
        return stanga;
    }

    public void setStanga(String stanga) {
        this.stanga = stanga;
    }

    public List<String> getDreapta() {
        return dreapta;
    }

    public void setDreapta(List<String> dreapta) {
        this.dreapta = dreapta;
    }
}
