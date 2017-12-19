package com.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gramatica.Gramatica;
import com.gramatica.Productie;
import com.sequence.Sequence;
import com.service.Analyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class UserInterface {

    private static final String FILE_PATH = "src/main/resources/";
    private static final Scanner READER = new Scanner(System.in);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static Gramatica gramatica;

    private void printMenu() {
        System.out.println("=========================");
        System.out.println("Enter sequence: ");
    }

    private void readGramatica() throws IOException {
        byte[] bytes = Files.readAllBytes(new File(FILE_PATH + "gramatica.json").toPath());

        gramatica = MAPPER.readValue(bytes, Gramatica.class);
    }

    private Sequence readSequence() throws IOException {
        byte[] bytes = Files.readAllBytes(new File(FILE_PATH + "sequence.json").toPath());

        return MAPPER.readValue(bytes, Sequence.class);
    }

    private void writeGramatica(Gramatica gramatica) throws IOException {
        MAPPER.writeValue(new File(FILE_PATH + "gramaticaConverted.json"), gramatica);
    }

    private static void printLineWithTabs(String line) {
        System.out.println("\t" + line);
    }

    private static void printProductie(Productie productie) {
        printLineWithTabs(productie.toString());
    }

    private void printGramatica(Gramatica gramatica) {
        System.out.println("Nonterminalii sunt: ");
        gramatica.getNonTerminale().forEach(UserInterface::printLineWithTabs);

        System.out.println("\nTerminalele sunt: ");
        gramatica.getTerminale().forEach(UserInterface::printLineWithTabs);

        System.out.println("\nSimbolul de start este: " + gramatica.getSimbolStart());

        System.out.println("\nProductiile sunt: ");
        gramatica.getProductii().forEach(UserInterface::printProductie);
    }

    public void runMenu() throws IOException {
        while(true) {
            readGramatica();
            //printGramatica(gramatica);

            //Sequence secventa = readSequence();

            Analyzer analyzer = new Analyzer(gramatica);

            printMenu();
            String sequence = READER.next();
            if (analyzer.isInGrammar(sequence)) {
                System.out.println("Apartine gramaticii");
            } else {
                System.out.println("Nu apartine gramaticii");
            }

//            if (sequence.length() == 0) {
//                break;
//            }
            break;
        }
    }
}
