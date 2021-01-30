package interfaces;

import java.util.List;

import containers.AttackReport;
import containers.GameBoard;
import containers.Position;

public interface Attacking
{
    public AttackReport attack(GameBoard gameBoard, Position targetPosition);
    
    public List<Position> computePossibleAttackingLocations(GameBoard gameBoard);
    
    public AttackReport attack(GameBoard gameBoard);
}
