package com.aks007;

/**
 * Exception class for handling wrong form step errors.
 */
public class WrongFormStepException extends RuntimeException {

    /**
     * The error message template.
     */
    private static final String errorMessage = "Not able to %s, either it is already done or details on previous form(s) are not filled";

    public WrongFormStepException(String event) {

        super(errorMessage.formatted(event));
    }
}