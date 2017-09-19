package de.mle.turing;

import static de.mle.turing.Tape.E;
import static de.mle.turing.TuringMachine.TERMINATION_STATE;

import org.testng.annotations.DataProvider;

public class TransformOnesToBinaryNumbers extends CommonTestConfig {
    {
        rules = new Rule[] {
                // Look for first one from left
                new Rule("q0", "1", "0", Direction.LEFT, "q1"),
                new Rule("q0", "0", "0", Direction.RIGHT, "q0"),
                new Rule("q0", E, E, Direction.LEFT, "q5"),

                // Move left to E
                new Rule("q1", "0", "0", Direction.LEFT, "q1"),
                new Rule("q1", E, E, Direction.LEFT, "q2"),

                // Add ones to left side
                new Rule("q2", "1", "0", Direction.LEFT, "q2"),
                new Rule("q2", "0", "1", Direction.RIGHT, "q3"),
                new Rule("q2", E, "1", Direction.RIGHT, "q3"),

                // Go back to middle E
                new Rule("q3", "0", "0", Direction.RIGHT, "q3"),
                new Rule("q3", E, E, Direction.RIGHT, "q0"),

                // Clean-up and end programme
                new Rule("q5", "0", "1", Direction.LEFT, "q5"),
                new Rule("q5", E, E, Direction.NONE, TERMINATION_STATE)
        };
    }

    @DataProvider
    public Object[][] provideTape() {
        return new Object[][] {
                { Tape.with("1"), Result.ACCEPT, "1_1" },
                { Tape.with("11"), Result.ACCEPT, "10_11" },
                { Tape.with("111"), Result.ACCEPT, "11_111" },
                { Tape.with("1111"), Result.ACCEPT, "100_1111" },
                { Tape.with("11111"), Result.ACCEPT, "101_11111" },
                { Tape.with("111111"), Result.ACCEPT, "110_111111" },
                { Tape.with("1111111"), Result.ACCEPT, "111_1111111" },
                { Tape.with("11111111"), Result.ACCEPT, "1000_11111111" },
                { Tape.with("111111111"), Result.ACCEPT, "1001_111111111" },
                { Tape.with("1111111111"), Result.ACCEPT, "1010_1111111111" },
                { Tape.with("111111111111111111"), Result.ACCEPT, "10010_111111111111111111" },
                { Tape.with("11111111111111111111"), Result.ACCEPT, "10100_11111111111111111111" }
        };
    }
}
