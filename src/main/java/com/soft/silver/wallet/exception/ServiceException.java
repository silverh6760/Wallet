package com.soft.silver.wallet.exception;

import lombok.Data;

@Data
public class ServiceException extends Exception {
    private Object[] args = new Object[0];
    private String message;

    public ServiceException(String message, Object[] args) {
        super(message);
        this.setArgs(args);
    }

    protected void setArgs(Object[] args) {
        this.args = args;
    }

}
