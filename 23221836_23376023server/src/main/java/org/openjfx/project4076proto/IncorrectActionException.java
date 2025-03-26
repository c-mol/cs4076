package org.openjfx.project4076proto;

/**
 * Custom exception for invalid client actions.
 */
public class IncorrectActionException extends Exception {
    /**
     * Constructor for IncorrectActionException.
     * @param message The error message to be displayed.
     */
    public IncorrectActionException(String message) {
        super(message);
    }
}
