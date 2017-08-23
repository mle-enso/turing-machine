package de.mle.turing;

import static de.mle.turing.Tape.E;
import static de.mle.turing.TuringMachine.TERMINATION_STATE;
import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BusyBeaverTest {
    private Rule[] rules = new Rule[] {
            new Rule("q0", E, "1", Direction.RIGHT, "q1"),
            new Rule("q0", "1", "1", Direction.LEFT, "q1"),
            new Rule("q1", E, "1", Direction.LEFT, "q0"),
            new Rule("q1", "1", "1", Direction.NONE, TERMINATION_STATE)
    };

    @DataProvider
    public Object[][] provideTape() {
        return new Object[][] {
                { Tape.with(E), Result.ACCEPT, "1111" },
                { Tape.with("x"), Result.REJECT, "x" }
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
