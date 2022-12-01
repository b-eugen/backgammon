import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* Group 47: Aness Al-Qawlaq, Yevhenii Mormul
 * Github IDs: anessk01, b-eugen*/
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class TbEventLog {
    EventLog eventLog;

    @BeforeEach
    void setUp(){
        this.eventLog = new EventLog();
    }

    @Test
    void testCorrectOrder(){
        List<String> testLogs = new LinkedList<String>(Arrays.asList("A", "B", "C"));
        for(String log: testLogs){
            eventLog.logEvent(log);
        }

        assertEquals(eventLog.getLogs().get(0), testLogs.get(0));
    }

    @Test
    void testCorrectCutoff(){
        List<String> testLogs = new LinkedList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));
        for(String log: testLogs){
            eventLog.logEvent(log);
        }

        // Tests that only the 5 most recent strings remain within the logs (F --> J)
        assertTrue(eventLog.getLogs().get(0).equals(testLogs.get(5)) && eventLog.getLogs().get(eventLog.getLogs().size()-1).equals(testLogs.get(testLogs.size()-1)));
    }
}
