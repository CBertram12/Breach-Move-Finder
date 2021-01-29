package entities;

import immutable.Position;
import interfaces.Attacking;
import interfaces.Moveable;
import tiles.Tile;

public abstract class PlayerMech extends Entity implements Moveable, Attacking
{

    public PlayerMech(String name, Position position, Integer health)
    {
        super(name, position, health);
    }

    abstract public void move(int x_move, int y_move);

    abstract public void bumpAction(Entity entity);
    
    abstract public void attack(Tile[][] gameBoard);
}
