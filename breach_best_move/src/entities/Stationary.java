package entities;

import containers.Position;

public abstract class Stationary extends Entity
{
    public Stationary(String name, Position position, Integer health)
    {
        super(name, position, health, false);
    }
}
