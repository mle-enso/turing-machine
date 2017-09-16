package de.mle.turing;

import static de.mle.turing.Tape.E;
import static de.mle.turing.TuringMachine.TERMINATION_STATE;
import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddOneToBinaryNumbers {
    private Rule[] rules = new Rule[] {

            new Rule("q0", "1", "1", Direction.RIGHT, "q0"),
            new Rule("q0", "0", "0", Direction.RIGHT, "q0"),
            new Rule("q0", E, E, Direction.LEFT, "q1"),

            new Rule("q1", "1", "0", Direction.LEFT, "q1"),
            new Rule("q1", "0", "1", Direction.NONE, TERMINATION_STATE),
            new Rule("q1", E, "1", Direction.NONE, TERMINATION_STATE),
    };

    @DataProvider
    public Object[][] provideTape() {
        return new Object[][] {

                { Tape.with("1"), Result.ACCEPT, "10_" },
                { Tape.with("10"), Result.ACCEPT, "11_" },
                { Tape.with("11"), Result.ACCEPT, "100_" },
                { Tape.with("100"), Result.ACCEPT, "101_" },
                { Tape.with("101"), Result.ACCEPT, "110_" },

                { Tape.with("110"), Result.ACCEPT, "111_" },
                { Tape.with("111"), Result.ACCEPT, "1000_" },
                { Tape.with("1000"), Result.ACCEPT, "1001_" },
                { Tape.with("1001"), Result.ACCEPT, "1010_" },
                { Tape.with("1010"), Result.ACCEPT, "1011_" },

                { Tape.with("1011"), Result.ACCEPT, "1100_" },
                { Tape.with("1100"), Result.ACCEPT, "1101_" },
                { Tape.with("1101"), Result.ACCEPT, "1110_" },
                { Tape.with("1110"), Result.ACCEPT, "1111_" },
                { Tape.with("1111"), Result.ACCEPT, "10000_" },

                { Tape.with("10000"), Result.ACCEPT, "10001_" },
                { Tape.with("10001"), Result.ACCEPT, "10010_" },
                { Tape.with("10010"), Result.ACCEPT, "10011_" },
                { Tape.with("10011"), Result.ACCEPT, "10100_" },
                { Tape.with("10100"), Result.ACCEPT, "10101_" },

                { Tape.with("101110100"), Result.ACCEPT, "101110101_" },
                { Tape.with("101110101"), Result.ACCEPT, "101110110_" }
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
