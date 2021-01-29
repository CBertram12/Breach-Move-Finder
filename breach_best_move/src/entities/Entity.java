package entities;

import immutable.Position;

public abstract class Entity
{
    protected String name;
    protected Position position;
    protected Integer health;
    
    public Entity(String name, Position position, Integer health)
    {
        this.name = name;
        this.position = position;
        this.health = health;
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

    public abstract void bumpAction(Entity entity);
}
