package de.mle.turing;

import static de.mle.turing.Tape.E;
import static de.mle.turing.TuringMachine.TERMINATION_STATE;

import org.testng.annotations.DataProvider;

public class AddOneToBinaryNumbers extends CommonTestConfig {
    {
        rules = new Rule[] {
                new Rule("q0", "1", "1", Direction.RIGHT, "q0"),
                new Rule("q0", "0", "0", Direction.RIGHT, "q0"),
                new Rule("q0", E, E, Direction.LEFT, "q1"),

                new Rule("q1", "1", "0", Direction.LEFT, "q1"),
                new Rule("q1", "0", "1", Direction.NONE, TERMINATION_STATE),
                new Rule("q1", E, "1", Direction.NONE, TERMINATION_STATE)
        };
    }

    @DataProvider
    public Object[][] provideTape() {
        return new Object[][] {
                { Tape.with("1"), Result.ACCEPT, "10" },
                { Tape.with("10"), Result.ACCEPT, "11" },
                { Tape.with("11"), Result.ACCEPT, "100" },
                { Tape.with("100"), Result.ACCEPT, "101" },
                { Tape.with("101"), Result.ACCEPT, "110" },

                { Tape.with("110"), Result.ACCEPT, "111" },
                { Tape.with("111"), Result.ACCEPT, "1000" },
                { Tape.with("1000"), Result.ACCEPT, "1001" },
                { Tape.with("1001"), Result.ACCEPT, "1010" },
                { Tape.with("1010"), Result.ACCEPT, "1011" },

                { Tape.with("1011"), Result.ACCEPT, "1100" },
                { Tape.with("1100"), Result.ACCEPT, "1101" },
                { Tape.with("1101"), Result.ACCEPT, "1110" },
                { Tape.with("1110"), Result.ACCEPT, "1111" },
                { Tape.with("1111"), Result.ACCEPT, "10000" },

                { Tape.with("10000"), Result.ACCEPT, "10001" },
                { Tape.with("10001"), Result.ACCEPT, "10010" },
                { Tape.with("10010"), Result.ACCEPT, "10011" },
                { Tape.with("10011"), Result.ACCEPT, "10100" },
                { Tape.with("10100"), Result.ACCEPT, "10101" },

                { Tape.with("101110100"), Result.ACCEPT, "101110101" },
                { Tape.with("101110101"), Result.ACCEPT, "101110110" }
        };
    }
}
