package org.example.HW4.Exceptions;

public class UnknownCharacterException extends DataException {
    public UnknownCharacterException() {
        super();
    }

    public UnknownCharacterException(String message) {
        super(message);
    }

    public UnknownCharacterException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownCharacterException(Throwable cause) {
        super(cause);
    }

    protected UnknownCharacterException(String message, Throwable cause,
                                        boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
