package com.revenuemonster.payment.model;

import com.revenuemonster.payment.constant.Status;

import org.json.JSONObject;

/**
 * Created by yussuf on 4/30/19.
 */

public class Error {
    public static final Error SYSTEM_BUSY = new Error(Status.FAILED.toString(), "System busy");
    public static final Error INVALID_PAYMENT_METHOD = new Error(Status.FAILED.toString(), "Invalid payment method");


    private String code;
    private String message;

    public Error(String code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    // Getter Methods

    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

    // Setter methods

    public void setCode(String code) {
        this.code = code;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
