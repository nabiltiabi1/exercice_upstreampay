package com.upstreampay.paiements_exercice.exception;

public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException(String access_denied) {
    }
}
