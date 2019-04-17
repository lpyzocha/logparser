package json;

import org.slf4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LogEntryParser {
    static final Logger LOG = LoggerFactory.getLogger(LogEntryParser.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static LogEntry parse(String logEntryString){
        LogEntry logEntry = null;
        try {
            logEntry = MAPPER.readValue(logEntryString, LogEntry.class);
            LOG.debug("Read from JSON : " + logEntry);

        } catch (IOException e) {
            LOG.error("Error while reading JSON", e);
        }
        return logEntry;
    }
}
