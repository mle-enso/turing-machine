package de.mle.turing;

import java.util.stream.Collectors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "with")
@EqualsAndHashCode(of = "symbols")
public class Tape {
    /** The empty symbol */
    public static final String E = "_";

    @NonNull
    private String symbols;
    private int pos = 0;

    void move(Direction direction) {
        switch (direction) {
            case LEFT:
                if (pos == 0)
                    prependEmpty();
                else
                    pos--;
                break;
            case RIGHT:
                if (pos == symbols.length() - 1)
                    appendEmpty();
                pos++;
                break;
            default:
                break;
        }
    }

    private void appendEmpty() {
        symbols = symbols + E;
    }

    private void prependEmpty() {
        symbols = E + symbols;
    }

    String read() {
        return symbols.substring(pos, pos + 1);
    }

    void write(String symbol) {
        symbols = String.format("%s%s%s", symbols.subSequence(0, pos), symbol, symbols.subSequence(pos + 1, symbols.length()));
    }

    @Override
    public String toString() {
        String withSpaces = " " + symbols.chars().mapToObj(i -> String.valueOf((char) i)).collect(Collectors.joining(" ")) + " ";
        int newPos = pos * 2 + 1;
        return String.format("%s[%s]%s",
                withSpaces.subSequence(0, newPos - 1),
                withSpaces.subSequence(newPos, newPos + 1),
                withSpaces.subSequence(newPos + 2, withSpaces.length()));
    }
}