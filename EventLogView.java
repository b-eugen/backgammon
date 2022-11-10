import java.util.LinkedList;

public class EventLogView {
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
