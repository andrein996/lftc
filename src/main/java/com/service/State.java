package com.service;

import java.util.*;

public class State {

    private String stare;
    private int position;
    private List<String> stivaLucru;
    private Deque<String> bandaDeIntrare;

    public State(String simbolStart) {
        this.stare = "q";
        this.position = 0;
        this.stivaLucru = new ArrayList<>();
        this.bandaDeIntrare = new LinkedList<>();
        this.bandaDeIntrare.add(simbolStart);
    }

    public State(State original) {
        this.stare = original.getStare();
        this.position = original.getPosition();
        this.stivaLucru = new ArrayList<>();
        original.getStivaLucru().forEach(string -> this.stivaLucru.add(string));

        this.bandaDeIntrare = new LinkedList<>();
        List<String> inBandaDeIntrare = new ArrayList<>();

        Deque<String> originalBandaDeIntrare = original.getBandaDeIntrare();

        while (!originalBandaDeIntrare.isEmpty()) {
            inBandaDeIntrare.add(originalBandaDeIntrare.removeLast());
        }

        Collections.reverse(inBandaDeIntrare);

        for (String string : inBandaDeIntrare) {
            this.bandaDeIntrare.add(string);
            originalBandaDeIntrare.add(string);
        }
    }

    public void setStare(String stare) {
        this.stare = stare;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setStivaLucru(List<String> stivaLucru) {
        this.stivaLucru = stivaLucru;
    }

    public Deque<String> getBandaDeIntrare() {
        return bandaDeIntrare;
    }

    public void setBandaDeIntrare(Deque<String> bandaDeIntrare) {
        this.bandaDeIntrare = bandaDeIntrare;
    }

    public String getStare() {
        return stare;
    }

    public Integer getPosition() {
        return position;
    }

    public List<String> getStivaLucru() {
        return stivaLucru;
    }

    @Override
    public String toString() {

        return "State(" + this.stare +
                ", " + (this.position + 1) +
                ", " + Arrays.toString(this.stivaLucru.toArray()) +
                ", " + Arrays.toString(this.bandaDeIntrare.toArray()) +
                ")";
    }
}
