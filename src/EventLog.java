/**
 * Group 47: Aness Al-Qawlaq, Yevhenii Mormul
 * Github IDs: anessk01, b-eugen
 * Represents a log of game events
 * @version 1 2022-21-11
 * @author Aness Al-Qawlaq
 */


import java.util.LinkedList;

/**
 * {@code EventLog} is a queue of MAXLOGEVENTS events that occured most recently in game 
 */

public class EventLog {
    private LinkedList<String> logs;
    private static final int MAXLOGEVENTS = 5;

    public EventLog(){
        this.logs = new LinkedList<String>();
    }

    
    /** 
     * retuns the logs
     * @return LinkedList<String>
     */
    public LinkedList<String> getLogs(){
        return this.logs;
    }

    
    /** 
     * logs a new event
     * @param eventString
     */
    public void logEvent(String eventString){
        // if the number of logs has exceeded MAXLOGEVENTS, remove the oldest log so that a new one is added
        if(this.logs.size() == MAXLOGEVENTS){
            this.logs.remove();
        }
        this.logs.add(eventString);
    }
}
