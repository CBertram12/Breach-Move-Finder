package containers;

import java.util.List;

public class MovementPath
{
    private List<Position> movements;
    
    public MovementPath(List<Position> movements)
    {
        this.movements = movements;
    }

    public List<Position> getMovements()
    {
        return movements;
    }
    
    public Position getFinalPosition()
    {
        return movements.get(movements.size() - 1);
    }
    
    public int getNumberOfMoves()
    {
        return movements.size() - 1;
    }
}
