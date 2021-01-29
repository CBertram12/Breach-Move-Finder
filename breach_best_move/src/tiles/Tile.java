package tiles;

import entities.Entity;

public class Tile
{
    private Entity entity;
    
    public Tile(Entity entity)
    {
        this.entity = entity;
    }

    public Entity getEntity()
    {
        return entity;
    }

    public void setEntity(Entity entity)
    {
        this.entity = entity;
    }
}
