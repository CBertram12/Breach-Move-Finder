package containers;

import entities.Entity;
import entities.PlayerMech;
import entities.PowerBuilding;
import entities.Vek;

public class Utilities
{
    public static TargetDirection determineDirection(Position source, Position target)
    {
        if (target.getX() > source.getX())
        {
            return TargetDirection.RIGHT;
        }
        else if (target.getX() < source.getX())
        {
            return TargetDirection.LEFT;
        }
        else if (target.getY() > source.getY())
        {
            return TargetDirection.UP;
        }
        else if (target.getY() < source.getY())
        {
            return TargetDirection.DOWN;
        }
        else
        {
            return null;
        }
    }
    
    public static void writeReport(AttackReport report, Entity entity, Integer damage)
    {
        if (entity instanceof PowerBuilding)
        {
            report.incrementPowerLost(damage);
        }
        else if (entity instanceof PlayerMech)
        {
            report.incrementMechHealthLost(damage);
        }
        else if (entity instanceof Vek)
        {
            report.incrementVekDamageDone(damage);
        }
    }
}
