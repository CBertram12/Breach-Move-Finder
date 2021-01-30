package tiles;

import entities.Entity;

public class Tile
{
    private Entity entity;
    
    public Tile(Entity entity)
    {
        this.entity = entity;
    }

    public Tile(Tile tile, Integer copyBit)
    {
        if (tile.getEntity() == null)
        {
            entity = null;
        }
        else
        {
            this.entity = tile.getEntity().makeCopy();
        }
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
