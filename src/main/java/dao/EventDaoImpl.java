package dao;

import json.LogEntryType;
import model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class EventDaoImpl implements EventDao {

    static final Logger LOG = LoggerFactory.getLogger(EventDaoImpl.class);
    @Override
    public Event fetchEvent(String id) {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM EVENT WHERE id=" + id);
            if(rs.next())
            {
                Event event = new Event();
                event.setId( rs.getString("id") );
                event.setAlert( rs.getBoolean("alert") );
                event.setDuration( rs.getLong("duration") );
                event.setHost( rs.getString("host") );
                event.setType(LogEntryType.valueOf(rs.getString("type")));
                return event;
            }
        } catch (SQLException ex) {
           LOG.error("Error during SELECT call", ex);
        }
        return null;
    }

    @Override
    public boolean addEvent(Event event) {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO EVENT VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, event.getId());
            ps.setBoolean(2, event.getAlert());
            ps.setLong(3, event.getDuration());
            ps.setString(4, event.getHost());
            ps.setString(5, event.getType().name());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            LOG.error("Error during INSERT call", ex);
        }
        return false;

    }

    @Override
    public boolean updateEvent(Event event) {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE EVENT SET alert=?, duration=?, host=?, type=? WHERE id=?");
            ps.setBoolean(1, event.getAlert());
            ps.setLong(2, event.getDuration());
            ps.setString(3, event.getHost());
            ps.setString(4, event.getType().name());
            ps.setString(5, event.getId());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            LOG.error("Error during UPDATE call", ex);
        }
        return false;
    }
}
