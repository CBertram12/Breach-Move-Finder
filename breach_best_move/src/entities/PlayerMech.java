package entities;

import containers.Position;
import interfaces.Attacking;
import interfaces.Moveable;

public abstract class PlayerMech extends Entity implements Moveable, Attacking
{
    protected Integer maxMoves;
    protected boolean active;
    protected boolean hasMoves;

    public PlayerMech(String name, Position position, Integer health, Integer maxMoves, boolean active, boolean hasMoves)
    {
        super(name, position, health, true);
        this.maxMoves = maxMoves;
        this.active = active;
        this.hasMoves = hasMoves;
    }

    public Integer getMaxMoves()
    {
        return maxMoves;
    }

    public boolean isActive()
    {
        return active;
    }

    public boolean hasMoves()
    {
        return hasMoves;
    }
}
