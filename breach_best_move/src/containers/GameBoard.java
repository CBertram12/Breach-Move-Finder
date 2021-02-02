package containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entities.Entity;
import entities.PlayerMech;
import entities.Vek;
import tiles.Tile;

public class GameBoard
{
    private Tile[][] gameLayout;
    
    public GameBoard(Tile[][] gameLayout)
    {
        this.gameLayout = gameLayout;
    }
    
    public GameBoard(GameBoard gameBoard)
    {
        this.gameLayout = new Tile[gameBoard.gameLayout.length][gameBoard.gameLayout[1].length];
        
        for (int i = 0; i < gameBoard.gameLayout.length; i++)
        {
            for (int j = 0; j < gameBoard.gameLayout[i].length; j++)
            {
                Tile potentialTile = new Tile(gameBoard.gameLayout[i][j], 0);
                
                if (potentialTile.getEntity() != null && potentialTile.getEntity().getHealth() <= 0)
                {
                    this.gameLayout[i][j] = new Tile(null);
                }
                else
                {
                    this.gameLayout[i][j] = new Tile(gameBoard.gameLayout[i][j], 0);
                }
            }
        }
    }

    public Entity getEntity(Position position) throws IndexOutOfBoundsException
    {
        if (gameLayout[position.getX()][position.getY()].getEntity() != null)
        {
            return gameLayout[position.getX()][position.getY()].getEntity();
        }
        else
        {
            return null;
        }
    }
    
    public AttackReport changeEntityPosition(Position startPosition, Position endPosition)
    {
        AttackReport report = new AttackReport();
        if (startPosition.equals(endPosition))
        {
            return report;
        }
        if (gameLayout[startPosition.getX()][startPosition.getY()].getEntity() != null)
        {
            Entity entity1 = gameLayout[startPosition.getX()][startPosition.getY()].getEntity();
            
            if (checkValidPosition(endPosition))
            {
                if (gameLayout[endPosition.getX()][endPosition.getY()].getEntity() == null)
                {
                    gameLayout[endPosition.getX()][endPosition.getY()].setEntity(entity1);
                    entity1.setPosition(new Position(endPosition.getX(), endPosition.getY()));
                    gameLayout[startPosition.getX()][startPosition.getY()].setEntity(null);
                }
                else
                {
                    Entity entity2 = gameLayout[endPosition.getX()][endPosition.getY()].getEntity();
                    report = entity2.bumpAction(entity1);
                } 
            }
        }
        return report;
    }
    
    public PlayerMech makeUnitMove(Position startPosition, Position endPosition)
    {
        if (startPosition.equals(endPosition))
        {
            return (PlayerMech) gameLayout[startPosition.getX()][startPosition.getY()].getEntity();
        }
        if (gameLayout[startPosition.getX()][startPosition.getY()].getEntity() != null)
        {
            PlayerMech mech = (PlayerMech) gameLayout[startPosition.getX()][startPosition.getY()].getEntity();
            
            if (checkValidPosition(endPosition))
            {
                if (gameLayout[endPosition.getX()][endPosition.getY()].getEntity() == null)
                {
                    gameLayout[endPosition.getX()][endPosition.getY()].setEntity(mech);
                    mech.move(new Position(endPosition.getX(), endPosition.getY()));
                    gameLayout[startPosition.getX()][startPosition.getY()].setEntity(null);
                    return mech;
                }
            }
        }
        
        return null;
    }
    
    public AttackReport makeUnitAttack(Position unitPosition, Position attackPosition)
    {
        AttackReport blankReport = new AttackReport();
        
        if (gameLayout[unitPosition.getX()][unitPosition.getY()].getEntity() != null)
        {
            PlayerMech mech = (PlayerMech) gameLayout[unitPosition.getX()][unitPosition.getY()].getEntity();
            
            return mech.attack(this, attackPosition);
        }
        
        return blankReport;
    }
    
    public List<PlayerMech> getActiveMechs()
    {
        List<PlayerMech> mechList = new ArrayList<PlayerMech>();
        
        for (int i = 0; i < gameLayout.length; i++)
        {
            for (int j = 0; j < gameLayout[i].length; j++)
            {
                Entity entity = gameLayout[i][j].getEntity();
                if (entity != null && entity instanceof PlayerMech)
                {
                    PlayerMech mech = (PlayerMech) entity;
                    if (mech.isActive())
                    {
                        mechList.add(mech);
                    }
                }
            }
        }
        
        return mechList;
    }
    
    public List<Vek> getActiveVek()
    {
        List<Vek> vekList = new ArrayList<Vek>();
        
        for (int i = 0; i < gameLayout.length; i++)
        {
            for (int j = 0; j < gameLayout[i].length; j++)
            {
                Entity entity = gameLayout[i][j].getEntity();
                if (entity != null && entity instanceof Vek)
                {
                    Vek vek = (Vek) entity;
                    if (vek.isActive())
                    {
                        vekList.add(vek);
                    }
                }
            }
        }
        
        Collections.sort(vekList);
        
        return vekList;
    }
    
    public int getLength()
    {
        return gameLayout.length;
    }
    
    public int getHeight()
    {
        return gameLayout[0].length;
    }
    
    public boolean checkValidPosition(Position position)
    {
        try 
        {
            Tile test = gameLayout[position.getX()][position.getY()];
        }
        catch (IndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }
}
