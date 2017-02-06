package ru.roboart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.NestedServletException;
import ru.roboart.models.exeptions.RestException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HeaderParam;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Kida on 07.01.2017.
 */
@RestController
public abstract class MainRestController<T> {

    PagingAndSortingRepository<T, Long> repository;

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    protected Date lastUpdate;
    @Autowired
    HttpServletResponse httpServletResponse;

    @RequestMapping("/list")
    public Object getList(@HeaderParam("timestamp") String timestamp) throws NestedServletException, IOException, IllegalAccessException {
        
        long timestampLong = (timestamp == null || timestamp.isEmpty())?0:Long.parseLong(timestamp);
        if(lastUpdate==null) lastUpdate = new Date();
        long update =  lastUpdate.getTime();
        if (lastUpdate.getTime() > timestampLong) {
            httpServletResponse.setHeader("timestamp", String.valueOf(lastUpdate.getTime()));
            return (List<T>) repository.findAll();
        }
         /*   RestException restException =  new RestException("msg");
            restException.setHttp_code(304);
            restException.setRequest_status(1);
            restException.setSystem_message("Client data is topical");
            restException.setUser_message("");
*/
        return new RestException(304, "Not modified", "Client data is topical").toString();
    }


}
