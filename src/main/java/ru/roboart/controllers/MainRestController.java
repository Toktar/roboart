package ru.roboart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.NestedServletException;
import ru.roboart.models.exeptions.RestException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HeaderParam;
import java.util.Date;
import java.util.List;

/**
 * Created by Kida on 07.01.2017.
 */
@RestController
public class MainRestController<T> {

    PagingAndSortingRepository<T, Long> repository;
    Date lastUpdate;
    @Autowired
    HttpServletResponse httpServletResponse;

    @RequestMapping("/list")
    public List<T> getList(@HeaderParam("timestamp") long timestamp) throws NestedServletException {
        long update = lastUpdate.getTime();
        if(lastUpdate==null || lastUpdate.getTime()>timestamp) {
            httpServletResponse.setHeader("timestamp", String.valueOf(lastUpdate.getTime()));
            return (List<T>) repository.findAll();
        } else {
            RestException restException =  new RestException("msg");
            restException.setHttp_code(304);
            restException.setRequest_status(1);
            restException.setSystem_message("Client data is topical");
            restException.setUser_message("");
            throw new NestedServletException("msg");
        }
    }

}
