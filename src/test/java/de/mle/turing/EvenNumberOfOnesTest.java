package de.mle.turing;

import static de.mle.turing.Tape.E;
import static de.mle.turing.TuringMachine.TERMINATION_STATE;
import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EvenNumberOfOnesTest {
    private Rule[] rules = new Rule[] {
            new Rule("q0", "1", "1", Direction.RIGHT, "q1"),
            new Rule("q0", "0", "0", Direction.RIGHT, "q0"),
            new Rule("q0", E, E, Direction.NONE, TERMINATION_STATE),

            new Rule("q1", "1", "1", Direction.RIGHT, "q0"),
            new Rule("q1", "0", "0", Direction.RIGHT, "q1")
    };

    @DataProvider
    public Object[][] provideTape() {
        return new Object[][] {
                { Tape.with(E), Result.ACCEPT },
                { Tape.with("1"), Result.REJECT },
                { Tape.with("11"), Result.ACCEPT },
                { Tape.with("111"), Result.REJECT },
                { Tape.with("1111"), Result.ACCEPT },
                { Tape.with("11111"), Result.REJECT },
                { Tape.with("101110"), Result.ACCEPT },
                { Tape.with("1011101"), Result.REJECT },
                { Tape.with("1010101100010"), Result.ACCEPT },
                { Tape.with("x"), Result.REJECT }
        };
    }

    @Test(dataProvider = "provideTape")
    public void runBusyBeaverAccepting(Tape initialTape, Result expectedResult) {
        // given
        TuringMachine tm = new TuringMachine(initialTape, rules, "q0");

        // when
        Result result = tm.run();

        // then
        assertThat(result).isEqualTo(expectedResult);
    }
}
