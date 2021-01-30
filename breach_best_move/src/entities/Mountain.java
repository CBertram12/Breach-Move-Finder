package entities;

import containers.Position;

public class Mountain extends Entity
{
    public Mountain(String name, Position position)
    {
        super(name, position, 2, false);
    }

    public Mountain(Mountain mountain)
    {
        super(mountain.name, new Position(mountain.position), mountain.health, false);
    }

    @Override
    public Entity makeCopy()
    {
        return new Mountain(this);
    }
}
