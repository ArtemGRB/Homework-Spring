package org.skypro.skyshop.exception;

public class ShopError {
    private String code;
    private String massage;

    public ShopError(String code, String massage) {
        this.code = code;
        this.massage = massage;
    }

    public String getCode() {
        return code;
    }

    public String getMassage() {
        return massage;
    }
}
