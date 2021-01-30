package entities;

import containers.AttackReport;
import containers.Position;
import containers.Utilities;

public abstract class Entity
{
    protected String name;
    protected Position position;
    protected Integer health;
    protected boolean isPassable;
    
    public Entity(String name, Position position, Integer health, boolean isPassable)
    {
        this.name = name;
        this.position = position;
        this.health = health;
        this.isPassable = isPassable;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public Position getPosition()
    {
        return position;
    }
    
    public void setPosition(Position position)
    {
        this.position = position;
    }
    
    public Integer getHealth()
    {
        return health;
    }

    public void setHealth(Integer health)
    {
        this.health = health;
    }

    public boolean isPassable()
    {
        return isPassable;
    }

    public void setPassable(boolean isPassable)
    {
        this.isPassable = isPassable;
    }

    public AttackReport bumpAction(Entity entity)
    {
        AttackReport report = new AttackReport();
        
        int damage = 1;
        
        entity.setHealth(entity.getHealth() - 1);
        health--;
        // TODO write report for host as well
        Utilities.writeReport(report, this, damage);
        Utilities.writeReport(report, entity, damage);
        
        return report;
    }
    
    abstract public Entity makeCopy();
}
