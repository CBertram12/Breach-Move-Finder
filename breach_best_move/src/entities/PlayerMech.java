package entities;

import java.util.List;

import containers.AttackReport;
import containers.GameBoard;
import containers.MovementPath;
import containers.Position;
import interfaces.Attacking;
import interfaces.Moveable;

public abstract class PlayerMech extends Entity implements Moveable, Attacking
{
    protected Integer maxMoves;
    protected boolean active;

    public PlayerMech(String name, Position position, Integer health, Integer maxMoves, boolean active)
    {
        super(name, position, health, true);
        this.maxMoves = maxMoves;
        this.active = active;
    }

    abstract public void move(MovementPath path);
    
    abstract public AttackReport attack(GameBoard gameBoard, Position targetPosition);
    
    abstract public List<Position> computePossibleAttackingLocations(GameBoard gameBoard);

    public Integer getMaxMoves()
    {
        return maxMoves;
    }

    public boolean isActive()
    {
        return active;
    }
}
