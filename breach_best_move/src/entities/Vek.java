package entities;

import containers.Position;
import interfaces.Attacking;

public abstract class Vek extends Entity implements Attacking, Comparable<Vek>
{

    protected Integer relative_x;
    protected Integer relative_y;
    protected boolean active;
    protected Integer moveOrder;

    public Vek(String name, Position position, Integer health, Position targetLocation, boolean active, Integer moveOrder)
    {
        super(name, position, health, false);
        this.relative_x = targetLocation.getX() - position.getX();
        this.relative_y = targetLocation.getY() - position.getY();
        this.active = active;
        this.moveOrder = moveOrder;
    }

    public Integer getRelative_x()
    {
        return relative_x;
    }

    public Integer getRelative_y()
    {
        return relative_y;
    }

    public boolean isActive()
    {
        return active;
    }

    public Integer getMoveOrder()
    {
        return moveOrder;
    }
}
