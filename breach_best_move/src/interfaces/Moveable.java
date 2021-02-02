package interfaces;

import java.util.List;

import containers.GameBoard;
import containers.MovementPath;
import containers.Position;

public interface Moveable
{
    public void move(Position finalPosition);
    
    public List<MovementPath> computeAllPossibleMoves(GameBoard gameBoard);
}
