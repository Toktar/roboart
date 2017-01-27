package ru.roboart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.roboart.models.Gift;
import ru.roboart.models.exeptions.RestException;
import ru.roboart.repositories.GiftRepository;

/**
 * Created by Kida on 06.01.2017.
 */

@RestController
@RequestMapping("/gift")
public class GiftController extends MainRestController<Gift> {
    private boolean isStart = false;

    @Autowired
    GiftRepository giftRepository;

    @RequestMapping("/get")
    public String getGift() throws RestException {
        if(isStart) {
            Gift gift = new Gift();
            giftRepository.save(gift);
            return gift.getCode();

        } else {

            RestException restException =  new RestException("msg");
            restException.setHttp_code(304);
            restException.setRequest_status(1);
            restException.setSystem_message("The gift is not available");
            restException.setUser_message("Подарок будет доступен в день проведения мероприятия, не пропустите!");
            throw restException;
        }
    }
  /*  @Autowired
    EventRepository eventRepository;

    @RequestMapping("/list")
    public List<Event> getList(@HeaderParam("timestamp") long timestamp) {
        List<Event> eventList = eventRepository.findAll(new Sort())

    }
*/
}
