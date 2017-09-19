package de.mle.turing;

import static de.mle.turing.Tape.E;
import static de.mle.turing.TuringMachine.TERMINATION_STATE;

public class PalindromeTest extends CommonTestConfig {
    {
        rules = new Rule[] {
                new Rule("q0", E, E, Direction.LEFT, "q1"),
                new Rule("q0", "0", "0", Direction.RIGHT, "q0"),
                new Rule("q0", "1", "1", Direction.RIGHT, "q0"),

                new Rule("q1", E, E, Direction.NONE, TERMINATION_STATE),
                new Rule("q1", "0", E, Direction.LEFT, "q2"),
                new Rule("q1", "1", E, Direction.LEFT, "q4"),

                new Rule("q2", E, E, Direction.RIGHT, "q3"),
                new Rule("q2", "0", "0", Direction.LEFT, "q2"),
                new Rule("q2", "1", "1", Direction.LEFT, "q2"),

                new Rule("q3", E, E, Direction.NONE, "q0"),
                new Rule("q3", "0", E, Direction.RIGHT, "q0"),
                new Rule("q3", "1", "1", Direction.NONE, "q6"),

                new Rule("q4", E, E, Direction.RIGHT, "q5"),
                new Rule("q4", "0", "0", Direction.LEFT, "q4"),
                new Rule("q4", "1", "1", Direction.LEFT, "q4"),

                new Rule("q5", E, E, Direction.NONE, "q0"),
                new Rule("q5", "0", "0", Direction.NONE, "q6"),
                new Rule("q5", "1", E, Direction.RIGHT, "q0"),

                new Rule("q6", E, E, Direction.NONE, "q7"),
                new Rule("q6", "0", E, Direction.RIGHT, "q6"),
                new Rule("q6", "1", E, Direction.RIGHT, "q6")
        };

        initialTapeAndExpectedResultAndExpectedTape = new Object[][] {
                { Tape.with(E), Result.ACCEPT, "" },
                { Tape.with("0"), Result.ACCEPT, "" },
                { Tape.with("11"), Result.ACCEPT, "" },
                { Tape.with("1001"), Result.ACCEPT, "" },
                { Tape.with("101110101011101"), Result.ACCEPT, "" },
                { Tape.with("10"), Result.REJECT, "" },
                { Tape.with("1010"), Result.REJECT, "" },
                { Tape.with("101010110001010"), Result.REJECT, "" }
        };
    }
}
