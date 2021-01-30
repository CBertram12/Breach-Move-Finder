package containers;

import java.util.Map;

public class TurnReport
{
    private Map<String, MovementPath> movesTaken;
    private AttackReport report;
    
    public TurnReport(Map<String, MovementPath> movesTaken, AttackReport report)
    {
        this.movesTaken = movesTaken;
        this.report = report;
    }

    public Map<String, MovementPath> getMovesTaken()
    {
        return movesTaken;
    }

    public AttackReport getReport()
    {
        return report;
    }
    
    @Override 
    public String toString()
    {
        String string = "";
        
        string = string + "------------ Report ------------ \n";
        
        for (Map.Entry<String, MovementPath> entry : movesTaken.entrySet())
        {
            string = string + entry.getKey() + " " + entry.getValue().getFinalPosition().toString();
        }
        
        string = string + report.toString();
        
        return string;
    }
}
