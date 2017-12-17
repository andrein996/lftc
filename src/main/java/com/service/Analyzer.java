package com.service;

import com.controller.StateController;
import com.gramatica.Gramatica;

public class Analyzer {

    private final Gramatica gramatica;

    public Analyzer(Gramatica gramatica) {
        this.gramatica = gramatica;

    }

    public boolean isInGrammar(String sequence) {
        StateController stateController =
                new StateController(gramatica, new State(gramatica.getSimbolStart()), sequence.length());

        while(!stateController.isOver()) {
            if (!stateController.isEpsilonBandaDeIntrare()
                    && !stateController.isTerminalInBandaDeIntrare()
                    && !stateController.isR()) {
                stateController.expandare();
            } else if (stateController.isEpsilonStivaDeLucru()
                    || !stateController.isTerminalInStivaDeLucru()
                    && stateController.isR()) {
                stateController.altaIncercare();
            } else {
                final String next = stateController.getFirstInBandaDeIntrare();
                final String last = stateController.getLastInStivaDeLucru();
                final int currentPosition = stateController.getCurrentPosition();

                if (stateController.isR() && stateController.isTerminal(last)) {
                    stateController.revenire();
                } else {
                    if (sequence.length() > currentPosition
                            && next != null
                            && sequence.charAt(currentPosition) == next.charAt(0)) {
                        stateController.avans();
                    } else {
                        stateController.insuccesDeMoment();
                    }
                }
            }
        }

        return stateController.isEpsilonBandaDeIntrare();
    }

}
