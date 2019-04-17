package dao;

import model.Event;

public interface EventDao {
    Event fetchEvent(String id);
    boolean addEvent(Event event);
    boolean updateEvent(Event event);
}
