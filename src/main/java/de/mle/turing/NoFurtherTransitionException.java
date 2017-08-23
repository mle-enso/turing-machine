package de.mle.turing;

public class NoFurtherTransitionException extends Exception {
    private static final long serialVersionUID = 1335349521154623080L;

    public NoFurtherTransitionException(String s) {
        super(s);
    }
}
