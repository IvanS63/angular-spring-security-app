package com.myapp.roombookingapp.dto.error;

/**
 * DTO class that would be displayed on front-end with localized message.
 */
public class ErrorInfo {
    private String url;
    private Integer code;
    private String message;

    public ErrorInfo(String url, Integer code, String message) {
        this.url = url;
        this.code = code;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
