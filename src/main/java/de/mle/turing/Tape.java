package de.mle.turing;

import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "symbols")
public class Tape {
    String symbols;
    String empty;
    int pos;

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
        symbols = symbols + empty;
    }

    private void prependEmpty() {
        symbols = empty + symbols;
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