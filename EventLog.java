import java.util.LinkedList;

public class EventLog {
    private LinkedList<String> logs;
    private static final int MAXLOGEVENTS = 5;

    public EventLog(){
        this.logs = new LinkedList<String>();
    }

    public LinkedList<String> getLogs(){
        return this.logs;
    }

    public void logEvent(String eventString){
        if(this.logs.size() == MAXLOGEVENTS){
            this.logs.remove();
        }
        this.logs.add(eventString);
    }
}
