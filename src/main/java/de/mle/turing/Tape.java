package de.mle.turing;

import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(of = "symbols")
@RequiredArgsConstructor(staticName = "with")
public class Tape {
    /** The empty symbol */
    public static final String E = "_";

    @Getter
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
        if (StringUtils.isBlank(symbols))
            return "";
        return symbols.substring(pos, pos + 1);
    }

    void write(String symbol) {
        symbols = String.format("%s%s%s", symbols.subSequence(0, pos), symbol, symbols.subSequence(pos + 1, symbols.length()));
    }

    @Override
    public String toString() {
        String withSpaces = E + symbols.chars().mapToObj(i -> String.valueOf((char) i)).collect(Collectors.joining(" ")) + E;
        int newPos = pos * 2 + 1;
        return String.format("%s[%s]%s",
                withSpaces.subSequence(0, newPos - 1),
                withSpaces.subSequence(newPos, newPos + 1),
                withSpaces.subSequence(newPos + 2, withSpaces.length()));
    }
}