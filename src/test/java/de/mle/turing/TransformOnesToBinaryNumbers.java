package de.mle.turing;

import static de.mle.turing.Tape.E;
import static de.mle.turing.TuringMachine.TERMINATION_STATE;
import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TransformOnesToBinaryNumbers {
    private Rule[] rules = new Rule[] {

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

    @DataProvider
    public Object[][] provideTape() {
        return new Object[][] {

                { Tape.with("1"), Result.ACCEPT, "1_1_" },
                { Tape.with("11"), Result.ACCEPT, "10_11_" },
                { Tape.with("111"), Result.ACCEPT, "11_111_" },
                { Tape.with("1111"), Result.ACCEPT, "100_1111_" },
                { Tape.with("11111"), Result.ACCEPT, "101_11111_" },
                { Tape.with("111111"), Result.ACCEPT, "110_111111_" },
                { Tape.with("1111111"), Result.ACCEPT, "111_1111111_" },
                { Tape.with("11111111"), Result.ACCEPT, "1000_11111111_" },
                { Tape.with("111111111"), Result.ACCEPT, "1001_111111111_" },
                { Tape.with("1111111111"), Result.ACCEPT, "1010_1111111111_" },
                { Tape.with("111111111111111111"), Result.ACCEPT, "10010_111111111111111111_" },
                { Tape.with("11111111111111111111"), Result.ACCEPT, "10100_11111111111111111111_" }
        };
    }

    @Test(dataProvider = "provideTape")
    public void runBusyBeaverAccepting(Tape initialTape, Result expectedResult, String finalTape) {
        // given
        TuringMachine tm = new TuringMachine(initialTape, rules, "q0");

        // when
        Result result = tm.run();

        // then
        assertThat(result).isEqualTo(expectedResult);
        assertThat(tm.getCurrentTape()).isEqualTo((finalTape));
    }
}
