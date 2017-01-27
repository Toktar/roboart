package ru.roboart.models.exeptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Date;

/**
 * Created by Kida on 07.01.2017.
 */


public class RestException {


    public long timestamp = new Date().getTime();
    public long status = 500;
    public String error = "Internal Server Error";
    // private String exception = "Internal Server Error";
    public String message = "Internal Server Error";
    // private String path = "Internal Server Error";

    public RestException(long status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public RestException() {
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /*    private int request_status;
    private String user_message;
    private String system_message;
    private long http_code;

    public RestException(String msg) {
        super(msg);
    }

    public RestException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public void setRequest_status(int request_status) {
        this.request_status = request_status;
    }

    public void setUser_message(String user_message) {
        this.user_message = user_message;
    }

    public void setSystem_message(String system_message) {
        this.system_message = system_message;
    }

    public void setHttp_code(long http_code) {
        this.http_code = http_code;
    }*/
}
