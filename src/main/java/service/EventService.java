package service;

import dao.EventDao;
import dao.EventDaoImpl;
import json.LogEntry;
import model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class EventService {
    static final Logger LOG = LoggerFactory.getLogger(EventService.class);
    private EventDao dao = new EventDaoImpl();

    public void processLogEntry(LogEntry logEntry){

        Event event = dao.fetchEvent(logEntry.getId());
        if(event == null) {
            LOG.debug("Didn't find event in db");
            event = new Event();

            event.setType(logEntry.getType());
            event.setHost(logEntry.getHost());
            event.setDuration(logEntry.getTimestamp().getTime());
            event.setAlert(Boolean.FALSE);
            event.setId(logEntry.getId());

            LOG.debug("Adding event to db");
            dao.addEvent(event);
        }
        else{
            LOG.debug("Found event in db");
            Date eventTimestamp = new Date(event.getDuration());
            Long duration;
            if(logEntry.getTimestamp().before(eventTimestamp)){
                duration = eventTimestamp.getTime() - logEntry.getTimestamp().getTime();
            }
            else {
                duration = logEntry.getTimestamp().getTime() -  eventTimestamp.getTime();
            }

            event.setDuration(duration);
            if(duration > 4){
                event.setAlert(Boolean.TRUE);
            }

            LOG.debug("Updating event in db");
            dao.updateEvent(event);
        }
    }
}
