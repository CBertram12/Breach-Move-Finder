package entities;

import containers.Position;

public class Mountain extends Stationary
{
    public Mountain(String name, Position position)
    {
        super(name, position, 2);
    }

    public Mountain(Mountain mountain)
    {
        super(mountain.name, new Position(mountain.position), mountain.health);
    }

    @Override
    public Entity makeCopy()
    {
        return new Mountain(this);
    }
}
