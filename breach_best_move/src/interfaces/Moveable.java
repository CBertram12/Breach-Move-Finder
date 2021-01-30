package interfaces;

import java.util.List;

import containers.GameBoard;
import containers.MovementPath;

public interface Moveable
{
    public void move(MovementPath path);
    
    public List<MovementPath> computeAllPossibleMoves(GameBoard gameBoard);
}
