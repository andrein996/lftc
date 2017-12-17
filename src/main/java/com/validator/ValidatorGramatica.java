package com.validator;

import com.gramatica.Gramatica;
import com.gramatica.Productie;

import java.util.List;

import static com.gramatica.Gramatica.EPSILON;

public class ValidatorGramatica implements IValidator<Gramatica> {

    @Override
    public void validate(Gramatica entity) throws MyException {
        String errorMessage = "";

        if (!entity.getNonTerminale().contains(entity.getSimbolStart())) {
            errorMessage += "Simbolul de start trebuie sa fie un nonterminal\n";
        }

        for (Productie productie : entity.getProductii()) {
            if (!entity.getNonTerminale().contains(productie.getStanga())) {
                errorMessage += "Nonterminalul " + productie.getStanga() + " din productia " + productie + " nu este un terminal din gramatica!\n";
            }

            for (List<String> symbols : productie.getDreapta()) {
                for (String symbol : symbols) {
                    if (!entity.getNonTerminale().contains(symbol)
                            && !entity.getTerminale().contains(symbol)
                            && !EPSILON.equals(symbol)) {
                        errorMessage += "Simbolul " + symbol + " nu este nici un terminal nici un nonterminal pentru gramatica!\n";
                    }
                }
            }
        }

        if (!errorMessage.isEmpty()) {
            throw new MyException(errorMessage);
        }
    }
}
