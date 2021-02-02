package entities;

import java.util.List;

import containers.AttackReport;
import containers.GameBoard;
import containers.Position;
import containers.Utilities;

public class Wasp extends Vek
{
    public Wasp(String name, Position position, Integer health, Position targetLocation, int moveOrder)
    {
        super(name, position, health, targetLocation, true, moveOrder);
    }

    public Wasp(Wasp wasp)
    {
        super(wasp.name, new Position(wasp.position), wasp.health, new Position(wasp.position.getX() + wasp.relative_x, wasp.position.getY() + wasp.relative_y), wasp.active, wasp.moveOrder);
    }

    public AttackReport attack(GameBoard gameBoard)
    {
        AttackReport report = new AttackReport();
        int damage = 1;
        
        Position targetPosition = new Position(position.getX() + relative_x, position.getY() + relative_y);
        
        Entity target = null;
        if (gameBoard.checkValidPosition(targetPosition))
        {
            target = gameBoard.getEntity(targetPosition);

        }
        else
        {
            return report;
        }
        
        if (target != null)
        {
            target.health -= damage;
            
            Utilities.writeReport(report, target, damage); 
        }
        
        return report;
    }

    @Override
    public AttackReport attack(GameBoard gameBoard, Position targetPosition)
    {
        return null;
    }

    @Override
    public List<Position> computePossibleAttackingLocations(GameBoard gameBoard)
    {
        return null;
    }

    @Override
    public Entity makeCopy()
    {
        return new Wasp(this);
    }

    @Override
    public int compareTo(Vek o)
    {
        return this.getMoveOrder().compareTo(o.getMoveOrder());
    }

}
