package com.controller;

import com.gramatica.Gramatica;
import com.gramatica.Productie;
import com.gramatica.Production;
import com.service.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Deque;

public class StateController {

    private final State state;
    private final Gramatica gramatica;
    private final List<Production> productii;
    private final Integer n;
    private List<State> stari;

    public StateController(Gramatica gramatica, State state, Integer n) {
        this.gramatica = gramatica;
        this.state = state;
        this.productii = getProductii(gramatica.getProductii());
        this.n = n;
        this.stari = new ArrayList<>();

        System.out.println("Stare de inceput: " + state);
    }

    private List<Production> getProductii(List<Productie> productii) {
        List<Production> productions = new ArrayList<>();

        for (Productie productie : productii) {
            final String left = productie.getStanga();

            productie.getDreapta()
                    .forEach(right -> productions.add(new Production(left, right)));
        }

        return productions;
    }

    public boolean isOver() {
        return state.getStare().equals("e") ||
                (state.getPosition().equals(n)
                        && state.getBandaDeIntrare().isEmpty());
    }

    public boolean isR() {
        return state.getStare().equals("r");
    }

    public boolean isEpsilonStivaDeLucru() {
        return state.getStivaLucru().isEmpty();
    }

    public boolean isEpsilonBandaDeIntrare() {
        return state.getBandaDeIntrare().isEmpty();
    }

    private void setStareQ() {
        state.setStare("q");
    }

    private void setStareR() {
        state.setStare("r");
    }

    private void setStareE() {
        state.setStare("e");
    }

    private void increasePosition() {
        int position = state.getPosition();
        state.setPosition(++position);
    }

    private void decreasePosition() {
        int position = state.getPosition();
        state.setPosition(--position);
    }

    private void addAndPrintState(String tranzitie) {
        final State newState = new State(this.state);
        stari.add(newState);
        System.out.println(tranzitie + " --> " + newState);
    }

    /**
     * EXPANDARE
     */
    public void expandare() {
        List<String> stivaDeLucru = state.getStivaLucru();
        stivaDeLucru.add(productii.get(0).getStanga() + "-" + 1);

        Deque<String> bandaDeLucru = state.getBandaDeIntrare();
        bandaDeLucru.removeLast();
        addProductie(0);

        addAndPrintState("expandare");
    }

    /**
     * AVANS
     */
    public void avans() {
        increasePosition();

        Deque<String> bandaDeLucru = state.getBandaDeIntrare();

        List<String> stivaDeLucru = state.getStivaLucru();
        stivaDeLucru.add(bandaDeLucru.remove());

        addAndPrintState("avans");
    }

    /**
     * INSUCCES DE MOMENT
     */
    public void insuccesDeMoment() {
        setStareR();

        addAndPrintState("insucces de moment");
    }

    /**
     * REVENIRE
     */
    public void revenire() {
        decreasePosition();

        final String last = removeLastInStivaDeLucru();
        state.getBandaDeIntrare().addFirst(last);

        addAndPrintState("revenire");
    }

    /**
     * ALTA INCERCARE
     */
    public void altaIncercare() {
        if (state.getPosition() == 0 &&
                getFirstInBandaDeIntrare().equals(gramatica.getSimbolStart())) {
            setStareE();

            addAndPrintState("alta incercare");
            return;
        }

        final String last = getLastInStivaDeLucru();

        final List<String> strings = new ArrayList<>(
                Arrays.asList(last.split("-")));

        if (strings.size() != 2) {
            throw new RuntimeException("Eroare alta incercare not 2");
        }

        final Integer position = Integer.parseInt(strings.get(1));

        final Production production = productii.get(position - 1);
        final int productionSizeToRight = production.getDreapta().size();

        removeFromBandaDeIntrare(productionSizeToRight);
        removeLastInStivaDeLucru();

        //daca exista A j+1 --> Gama j+1
        if (position < productii.size()) {
            final List<String> stivaDeLucru = state.getStivaLucru();
            stivaDeLucru.add(productii.get(position).getStanga() + "-" + (position + 1));
            addProductie(position);
            setStareQ();
        } else {
            Deque<String> bandaDeIntrare = state.getBandaDeIntrare();
            bandaDeIntrare.add(strings.get(0));

            setStareR();
        }

        addAndPrintState("alta incercare");
    }

    public boolean isTerminalInBandaDeIntrare() {
        Deque<String> bandaDeIntrare = state.getBandaDeIntrare();

        String firstElement = bandaDeIntrare.peek();

        return gramatica.getTerminale().contains(firstElement);
    }

    public boolean isTerminalInStivaDeLucru() {
        List<String> stivaDeLucru = state.getStivaLucru();

        return gramatica.getTerminale()
                .contains(stivaDeLucru.get(stivaDeLucru.size() - 1));
    }

    public boolean isTerminal(String element) {
        return gramatica.getTerminale().contains(element);
    }

    private void addProductie(Integer position) {
        List<String> rightSide = productii.get(position).getDreapta();
        Deque<String> bandaDeIntrare = state.getBandaDeIntrare();

        rightSide.forEach(bandaDeIntrare::add);
    }

    public String getFirstInBandaDeIntrare() {
        return state.getBandaDeIntrare().peek();
    }

    public String getLastInStivaDeLucru() {
        return state.getStivaLucru()
                .get(this.state.getStivaLucru().size() - 1);
    }

    private void removeFromBandaDeIntrare(int number) {
        while (number > 0) {
            removeFirstInBandaDeIntrare();
            number--;
        }
    }

    private String removeLastInStivaDeLucru() {
        return state.getStivaLucru()
                .remove(this.state.getStivaLucru().size() - 1);
    }

    private String removeFirstInBandaDeIntrare() {
        return state.getBandaDeIntrare().remove();
    }

    public int getCurrentPosition() {
        return state.getPosition();
    }
}
