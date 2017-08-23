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
    char empty;
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

    char read() {
        return symbols.charAt(pos);
    }

    void write(char symbol) {
        symbols = String.format("%s%s%s", symbols.subSequence(0, pos), symbol, symbols.subSequence(pos + 1, symbols.length()));
    }

    @Override
    public String toString() {
        String withSpaces = " " + symbols.chars().mapToObj(i -> String.valueOf((char) i)).collect(Collectors.joining(" ")) + " ";
        int newPos = pos * 2 + 1; // 0,1,2,3,4 ... -> 1,3,5,7,9 ...
        return String.format("%s[%s]%s",
                withSpaces.subSequence(0, newPos - 1),
                withSpaces.subSequence(newPos, newPos + 1),
                withSpaces.subSequence(newPos + 2, withSpaces.length()));
    }
}