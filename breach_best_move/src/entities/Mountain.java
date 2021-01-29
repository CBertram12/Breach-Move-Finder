package entities;

import immutable.Position;

public class Mountain extends Entity
{
    public Mountain(String name, Position position)
    {
        super(name, position, 2);
    }

    @Override
    public void bumpAction(Entity entity)
    {
        entity.setHealth(entity.getHealth() - 1);
        health--;
    }

}
