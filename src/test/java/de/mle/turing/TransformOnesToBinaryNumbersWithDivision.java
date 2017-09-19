package de.mle.turing;

import static de.mle.turing.Tape.E;
import static de.mle.turing.TuringMachine.TERMINATION_STATE;

import org.testng.annotations.DataProvider;

public class TransformOnesToBinaryNumbersWithDivision extends CommonTestConfig {
    {
        rules = new Rule[] {
                // Go right
                new Rule("q0", "1", "1", Direction.RIGHT, "q0"),
                new Rule("q0", "0", "0", Direction.RIGHT, "q0"),
                new Rule("q0", E, E, Direction.LEFT, "q1"),

                // Check if even or uneven
                new Rule("q1", "1", "1", Direction.LEFT, "q2"),
                new Rule("q1", "0", "0", Direction.LEFT, "q1"),
                new Rule("q1", E, E, Direction.LEFT, "q3"), // > is even number

                new Rule("q2", "1", "1", Direction.LEFT, "q1"),
                new Rule("q2", "0", "0", Direction.LEFT, "q2"),
                new Rule("q2", E, E, Direction.LEFT, "q4"), // > is uneven number

                // Even number: Go left and write 0
                new Rule("q3", "1", "1", Direction.LEFT, "q3"),
                new Rule("q3", "0", "0", Direction.LEFT, "q3"),
                new Rule("q3", E, "0", Direction.RIGHT, "q5"),

                // Uneven number: Go left and write 1
                new Rule("q4", "1", "1", Direction.LEFT, "q4"),
                new Rule("q4", "0", "0", Direction.LEFT, "q4"),
                new Rule("q4", E, "1", Direction.RIGHT, "q5"),

                // Go back right to middle E
                new Rule("q5", "1", "1", Direction.RIGHT, "q5"),
                new Rule("q5", "0", "0", Direction.RIGHT, "q5"),
                new Rule("q5", E, E, Direction.RIGHT, "q6"),

                // Divide by two
                new Rule("q6", "1", "0", Direction.RIGHT, "q7"),
                new Rule("q6", "0", "0", Direction.RIGHT, "q6"),
                new Rule("q6", E, E, Direction.LEFT, "q8"),

                new Rule("q7", "1", "1", Direction.RIGHT, "q6"),
                new Rule("q7", "0", "0", Direction.RIGHT, "q7"),
                new Rule("q7", E, E, Direction.LEFT, "q8"),

                // Go back left to middle E
                new Rule("q8", "1", "1", Direction.LEFT, "q8"),
                new Rule("q8", "0", "0", Direction.LEFT, "q8"),
                new Rule("q8", E, E, Direction.RIGHT, "q9"),

                // Check if there are still ones otherwise terminate
                new Rule("q9", "1", "1", Direction.RIGHT, "q0"),
                new Rule("q9", "0", "0", Direction.RIGHT, "q9"),
                new Rule("q9", E, E, Direction.LEFT, "q10"),

                new Rule("q10", "0", "1", Direction.LEFT, "q10"),
                new Rule("q10", E, E, Direction.RIGHT, TERMINATION_STATE)
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
