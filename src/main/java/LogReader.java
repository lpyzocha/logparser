import json.LogEntry;
import json.LogEntryParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.EventService;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

public class LogReader {

    static final Logger LOG = LoggerFactory.getLogger(LogReader.class);

    public static void main(String[] args) {
        Instant start = Instant.now();
        if(args.length > 0){
            String fName = args[0];
            readLogsFromFile(fName);
        }
        else {
            LOG.info("File path was not specified");
        }

        Instant finish = Instant.now();

        long timeElapsed = Duration.between(start, finish).toMillis();
        LOG.debug("Elapsed time: " + timeElapsed +" ms");
    }

    private static void readLogsFromFile(String fileName) {
        EventService eventService = new EventService();
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get(fileName));
            Stream <LogEntry> lines = br.lines().map(s -> LogEntryParser.parse(s));
            LOG.info("<!-----Read all lines -----!>");
            lines.forEach(eventService::processLogEntry);
            lines.close();
        } catch (IOException io) {
           LOG.error("Error during file read", io);
        }
    }
}
