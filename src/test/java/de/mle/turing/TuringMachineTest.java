package de.mle.turing;

import static de.mle.turing.Tape.E;
import static de.mle.turing.TuringMachine.TERMINATION_STATE;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.Test;

public class TuringMachineTest {
    @Test
    public void isRuleSetValidYes() {
        // given
        Rule[] rules = new Rule[] {
                new Rule("q0", E, E, Direction.NONE, "q1"),
                new Rule("q1", "1", "1", Direction.NONE, TERMINATION_STATE)
        };
        TuringMachine turingMachine = new TuringMachine(null, rules, null);

        // when
        boolean isValid = ReflectionTestUtils.invokeMethod(turingMachine, "isRuleSetValid", new Object[] {});

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    public void isRuleSetValidNo() {
        // given
        Rule[] rules = new Rule[] {
                new Rule("q0", E, E, Direction.NONE, "q1"),
                new Rule("q0", "1", "1", Direction.NONE, TERMINATION_STATE)
        };
        TuringMachine turingMachine = new TuringMachine(null, rules, null);

        // when
        boolean isValid = ReflectionTestUtils.invokeMethod(turingMachine, "isRuleSetValid", new Object[] {});

        // then
        assertThat(isValid).isFalse();
    }
}
