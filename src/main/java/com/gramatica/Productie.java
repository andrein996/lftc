package com.gramatica;

import java.util.List;

public final class Productie {
    private String stanga;
    private List<List<String>> dreapta;

    public Productie() {}

    public Productie(String stanga, List<List<String>> dreapta) {
        this.stanga = stanga;
        this.dreapta = dreapta;
    }

    public String getStanga() {
        return stanga;
    }

    public List<List<String>> getDreapta() {
        return dreapta;
    }

    @Override
    public String toString() {
        String productie = stanga + " => ";

        for (int i = 0; i < dreapta.size(); i++){
            productie += getProductionFromList(dreapta.get(i));

            if (i < dreapta.size() - 1){
                productie += " | ";
            }
        }

        return productie;
    }

    private String getProductionFromList(List<String> list) {
        final String space = list.size() == 2 ? " " : "";
        String production = "";

        for (String symbol : list) {
            production += symbol + space;
        }

        return production;
    }
}
