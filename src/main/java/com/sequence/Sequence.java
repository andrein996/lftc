package com.sequence;

import java.util.List;

public class Sequence {

    private List<String> sequences;

    public Sequence() {}

    public List<String> getSequences() {
        return sequences;
    }

    public void setSequences(List<String> sequences) {
        this.sequences = sequences;
    }

    @Override
    public String toString() {
        String sequence = "";

        for (String string : sequences) {
            sequence += string + " ";
        }

        return sequence.substring(0, sequence.length() - 1);
    }
}
