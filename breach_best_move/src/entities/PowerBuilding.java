package entities;

import containers.Position;

public class PowerBuilding extends Stationary
{

    public PowerBuilding(String name, Position position, Integer health)
    {
        super(name, position, health);
    }

    public PowerBuilding(PowerBuilding powerBuilding)
    {
        super(powerBuilding.name, new Position(powerBuilding.position), powerBuilding.health);
    }

    @Override
    public Entity makeCopy()
    {
        return new PowerBuilding(this);
    }
}
