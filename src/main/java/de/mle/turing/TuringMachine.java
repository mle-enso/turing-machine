package de.mle.turing;

import java.util.Arrays;

import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
public class TuringMachine {
    public static final String TERMINATION_STATE = "qf";
    Tape tape;
    Rule[] rules;
    @NonFinal
    String state;

    private void step() throws NoFurtherTransitionException {
        Rule currentRule = Arrays.stream(rules)
                .filter(rule -> rule.getFromState().equals(state) && rule.getRead().equals(tape.read()))
                .findFirst()
                .orElseThrow(() -> new NoFurtherTransitionException("no transition from current state"));

        tape.write(currentRule.getWrite());
        tape.move(currentRule.getMove());
        state = currentRule.getToState();
    }

    public Result run() {
        int counter = 0;
        log.info("step {}: state: {}, tape: {}", counter, state, tape);
        while (!state.equals(TERMINATION_STATE)) {
            try {
                step();
            } catch (NoFurtherTransitionException e) {
                return Result.REJECT;
            }
            log.info("step {}: state: {}, tape: {}", ++counter, state, tape);
        }
        return Result.ACCEPT;
    }

    public String getCurrentTape() {
        return tape.getSymbols();
    }
}