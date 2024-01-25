package com.yurisaito.gestore.exception;

public class UsernameIsAlredyTaken extends RuntimeException {
    public UsernameIsAlredyTaken(String message){
        super(message);
    }
}
