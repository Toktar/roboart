package ru.roboart.models.exeptions;

import org.springframework.web.client.RestClientException;
import org.springframework.web.util.NestedServletException;

import javax.servlet.ServletException;

/**
 * Created by Kida on 07.01.2017.
 */
public class RestException extends NestedServletException {
    private int request_status;
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
    }
}
