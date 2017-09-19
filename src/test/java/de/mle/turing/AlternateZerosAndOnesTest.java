package de.mle.turing;

import static de.mle.turing.Tape.E;
import static de.mle.turing.TuringMachine.TERMINATION_STATE;

import org.testng.annotations.DataProvider;

public class AlternateZerosAndOnesTest extends CommonTestConfig {
    {
        rules = new Rule[] {
                new Rule("q0", "0", "0", Direction.RIGHT, "q1"),
                new Rule("q0", "E", "E", Direction.RIGHT, "q0"),
                new Rule("q0", E, E, Direction.NONE, TERMINATION_STATE),

                new Rule("q1", "1", "1", Direction.RIGHT, "q0"),
                new Rule("q1", "E", "E", Direction.RIGHT, "q1"),

        };
    }

    @DataProvider
    public Object[][] provideTape() {
        return new Object[][] {
                { Tape.with(E), Result.ACCEPT, "" },
                { Tape.with("01E"), Result.ACCEPT, "01E" },
                { Tape.with("001"), Result.REJECT, "001" },
                { Tape.with("011"), Result.REJECT, "011" },
                { Tape.with("01EEE"), Result.ACCEPT, "01EEE" },
                { Tape.with("0"), Result.REJECT, "0" },
                { Tape.with("01"), Result.ACCEPT, "01" },
                { Tape.with("010101"), Result.ACCEPT, "010101" },
                { Tape.with("1010"), Result.REJECT, "1010" },
                { Tape.with("10110"), Result.REJECT, "10110" },
                { Tape.with("10010"), Result.REJECT, "10010" },
                { Tape.with("01E0E101"), Result.ACCEPT, "01E0E101" },
                { Tape.with("EEE0E1E"), Result.ACCEPT, "EEE0E1E" },
                { Tape.with("x"), Result.REJECT, "x" }
        };
    }
}
