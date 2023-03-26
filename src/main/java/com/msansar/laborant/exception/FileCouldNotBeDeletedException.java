package com.msansar.laborant.exception;


import java.io.IOException;

public class FileCouldNotBeDeletedException extends IOException {
    public FileCouldNotBeDeletedException(String message) {
        super(message);
    }
}
