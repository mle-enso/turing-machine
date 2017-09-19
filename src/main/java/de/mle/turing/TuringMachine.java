package de.mle.turing;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TuringMachine {
    public static final String TERMINATION_STATE = "qf";
    public static final String ERROR_STATE = "qe";

    Tape tape;
    Rule[] rules;
    @NonFinal
    String state;

    private void step() throws NoFurtherTransitionException {
        Rule currentRule = Arrays.stream(rules)
                .filter(rule -> rule.getFromState().equals(state) && rule.getRead().equals(tape.read()))
                .findFirst()
                .orElseThrow(() -> new NoFurtherTransitionException("No transition from current state in rule set!"));

        tape.write(currentRule.getWrite());
        tape.move(currentRule.getMove());
        state = currentRule.getToState();
    }

    public Result run() {
        if (!isRuleSetValid())
            throw new IllegalStateException("Not all toStates have corresponding fromStates!");

        int counter = 0;
        log.info("step {}: state: {}, tape: {}", counter, state, tape);

        while (!state.equals(TERMINATION_STATE)) {
            try {
                step();
            } catch (NoFurtherTransitionException e) {
                return Result.REJECT;
            }
            log.info("step {}: state: {}, tape: {}", ++counter, state, tape);
            if (state.equals(ERROR_STATE))
                return Result.REJECT;
        }
        return Result.ACCEPT;
    }

    private boolean isRuleSetValid() {
        Set<String> fromStates = Arrays.stream(rules).map(Rule::getFromState).collect(Collectors.toSet());
        Set<String> toStates = Arrays.stream(rules)
                .map(Rule::getToState)
                .filter(state -> !state.equals(ERROR_STATE))
                .filter(state -> !state.equals(TERMINATION_STATE))
                .collect(Collectors.toSet());
        return fromStates.containsAll(toStates);
    }

    public String getCurrentTape() {
        return tape.getSymbols().replaceAll("^_+|_+$", "");
    }
}