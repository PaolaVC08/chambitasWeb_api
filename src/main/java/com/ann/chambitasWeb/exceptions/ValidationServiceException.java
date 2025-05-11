package com.ann.chambitasWeb.exceptions;

public class ValidationServiceException  extends RuntimeException{
        public ValidationServiceException(String message, Throwable cause) {
            super(message, cause);
        }

        public ValidationServiceException(String message) {
            super(message);
        }
}
