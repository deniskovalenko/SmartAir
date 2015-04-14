package com.breathe.model.api;

/**
 * Created by denis on 14.04.15.
 */
public class ApiResponseModel {
    // type : "error", type : "success"
    public String type;
    // http error code
    public int code;
    // flag for our device, if settings were changed and it has to update them (delay, wi-fi credentials)
    public byte changed;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public byte getChanged() {
        return changed;
    }

    public void setChanged(byte changed) {
        this.changed = changed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
