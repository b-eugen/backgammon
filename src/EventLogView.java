/**
 * Group 47: Aness Al-Qawlaq, Yevhenii Mormul
 * Github IDs: anessk01, b-eugen
 * Represents the view (MVC) of EventLog
 * @version 1 2022-21-11
 * @author Aness Al-Qawlaq
 */

import java.util.LinkedList;

/**
 * {@code EventLogView} is the view class (MVC) of the EventLog class
 */

public class EventLogView {
    
    /** 
     * method to display an event log
     * @param eventLog - the event log to be displayed
     * @return String - the built string
     */
    public static String display(EventLog eventLog){
        LinkedList<String> logs = eventLog.getLogs();
        String returnStr = "";
        returnStr += String.format("%1$60s", "");
        returnStr += String.format("Logs: \n");
        for(int i = logs.size() - 1; i >=0; i--){
            returnStr += String.format(" %1$-130s\n", logs.get(i));
        }
        return returnStr;
    }
}
