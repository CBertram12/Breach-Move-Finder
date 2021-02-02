package containers;

import java.util.HashMap;
import java.util.Map;

public class TurnReport
{
    private class InternalMoves
    {
        private String name;
        private MovementPath movement;
        
        private InternalMoves(String name, MovementPath movement)
        {
            this.name = name;
            this.movement = movement;
        }
    }
    
    private class InternalTarget
    {
        private String name;
        private Position targetPosition;
        
        private InternalTarget(String name, Position targetPosition)
        {
            this.name = name;
            this.targetPosition = targetPosition;
        }
    }
    
    private Map<Integer, InternalMoves> movesTaken;
    private Map<Integer, InternalTarget> targetLocations;
    private AttackReport report;
    private Integer turnCount;
    
    public TurnReport(String name, MovementPath movement, Position targetPosition, AttackReport report)
    {
        this.movesTaken = new HashMap<Integer, InternalMoves>();
        this.targetLocations = new HashMap<Integer, InternalTarget>();
        this.turnCount = 0;
        addMove(name, movement);
        addTargetLocation(name, targetPosition);
        this.report = report;
    }

    public void addMove(String name, MovementPath movement)
    {
        movesTaken.put(turnCount++, new InternalMoves(name, movement));
    }

    public void addTargetLocation(String name, Position targetPosition)
    {
        targetLocations.put(turnCount++, new InternalTarget(name, targetPosition));
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
        
        for (Integer i = turnCount - 1; i >= 0; i--)
        {
            if (movesTaken.get(i) != null)
            {
                InternalMoves moves = movesTaken.get(i);
                for (Position movedPosition : moves.movement.getMovements())
                {
                    string = string + moves.name + " " + movedPosition.toString();
                }
            }
            else if (targetLocations.get(i) != null)
            {
                InternalTarget target = targetLocations.get(i);
                string = string + target.name + " Target " + target.targetPosition.toString();
            }
        }
        
        string = string + report.toString();
        
        return string;
    }
}
