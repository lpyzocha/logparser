package json;

import java.util.Date;

public class LogEntry {
    private String id;
    private LogEntryState state;
    private Date timestamp;
    private LogEntryType type;
    private String host;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LogEntryState getState() {
        return state;
    }

    public void setState(LogEntryState state) {
        this.state = state;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public LogEntryType getType() {
        return type;
    }

    public void setType(LogEntryType type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "json.LogEntry{" +
                "id='" + id + '\'' +
                ", state=" + state +
                ", timestamp='" + timestamp + '\'' +
                ", type=" + type +
                ", host='" + host + '\'' +
                '}';
    }
}
