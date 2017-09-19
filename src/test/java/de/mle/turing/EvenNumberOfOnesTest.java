package de.mle.turing;

import static de.mle.turing.Tape.E;
import static de.mle.turing.TuringMachine.TERMINATION_STATE;

import org.testng.annotations.DataProvider;

public class EvenNumberOfOnesTest extends CommonTestConfig {
    {
        rules = new Rule[] {
                new Rule("q0", "1", "1", Direction.RIGHT, "q1"),
                new Rule("q0", "0", "0", Direction.RIGHT, "q0"),
                new Rule("q0", E, E, Direction.NONE, TERMINATION_STATE),

                new Rule("q1", "1", "1", Direction.RIGHT, "q0"),
                new Rule("q1", "0", "0", Direction.RIGHT, "q1")
        };
    }

    @DataProvider
    public Object[][] provideTape() {
        return new Object[][] {
                { Tape.with(E), Result.ACCEPT, "" },
                { Tape.with("1"), Result.REJECT, "1" },
                { Tape.with("11"), Result.ACCEPT, "11" },
                { Tape.with("111"), Result.REJECT, "111" },
                { Tape.with("1111"), Result.ACCEPT, "1111" },
                { Tape.with("11111"), Result.REJECT, "11111" },
                { Tape.with("101110"), Result.ACCEPT, "101110" },
                { Tape.with("1011101"), Result.REJECT, "1011101" },
                { Tape.with("1010101100010"), Result.ACCEPT, "1010101100010" },
                { Tape.with("x"), Result.REJECT, "x" }
        };
    }
}
