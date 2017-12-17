package com.gramatica;

import java.util.List;

public final class Gramatica {
    public static final String EPSILON = "ε";
    private List<String> nonTerminale;
    private List<String> terminale;
    private List<Productie> productii;
    private String simbolStart;

    public Gramatica() {}

    public List<String> getNonTerminale() {
        return nonTerminale;
    }

    public List<String> getTerminale() {
        return terminale;
    }

    public List<Productie> getProductii() {
        return productii;
    }

    public String getSimbolStart() {
        return simbolStart;
    }

    public void setNonTerminale(List<String> nonTerminale) {
        this.nonTerminale = nonTerminale;
    }

    public void setTerminale(List<String> terminale) {
        this.terminale = terminale;
    }

    public void setProductii(List<Productie> productii) {
        this.productii = productii;
    }

    public void setSimbolStart(String simbolStart) {
        this.simbolStart = simbolStart;
    }
}
