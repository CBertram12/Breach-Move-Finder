package entities;

import java.util.ArrayList;
import java.util.List;

import containers.AttackReport;
import containers.GameBoard;
import containers.MovementPath;
import containers.Position;
import containers.TargetDirection;
import containers.Utilities;

public class PunchMech extends PlayerMech
{
    
    public PunchMech(Position position)
    {
        super("Punch Mech", position, 3, 3, true);
    }

    public PunchMech(PunchMech punchMech)
    {
        super(punchMech.name, new Position(punchMech.position), punchMech.maxMoves, punchMech.health, punchMech.active);
    }

    @Override
    public void move(MovementPath movePath)
    {
        position = movePath.getFinalPosition();
    }

    @Override
    public AttackReport attack(GameBoard gameBoard, Position targetPosition)
    {
        active = false;
        Entity target = gameBoard.getEntity(targetPosition);
        AttackReport report = new AttackReport();
        
        int damage = 2;
        
        target.health -= damage;
        
        Utilities.writeReport(report, target, damage);
        
        if (target instanceof Vek)
        {
            TargetDirection direction = Utilities.determineDirection(position, targetPosition);
            
            Position bumpLocation = null;
            switch (direction)
            {
                case UP:
                    bumpLocation = new Position(targetPosition.getX(), targetPosition.getY() + 1);
                    break;
                case DOWN:
                    bumpLocation = new Position(targetPosition.getX(), targetPosition.getY() - 1);
                    break;
                case RIGHT:
                    bumpLocation = new Position(targetPosition.getX() + 1, targetPosition.getY());
                    break;
                case LEFT:
                    bumpLocation = new Position(targetPosition.getX() - 1, targetPosition.getY());
                    break;
            }
            
            report.combine(gameBoard.moveEntity(targetPosition, bumpLocation));
        }
        
        return report;
    }
    
    @Override
    public List<Position> computePossibleAttackingLocations(GameBoard gameBoard)
    {
        List<Position> possibleTargetLocations = new ArrayList<Position>();
        
        possibleTargetLocations.add(new Position(position.getX() + 1, position.getY()));
        possibleTargetLocations.add(new Position(position.getX(), position.getY() + 1));
        possibleTargetLocations.add(new Position(position.getX() - 1, position.getY()));
        possibleTargetLocations.add(new Position(position.getX(), position.getY() - 1));
        
        List<Position> targetLocations = new ArrayList<Position>();
        
        for (Position possiblePosition : possibleTargetLocations)
        {
            try
            {
                if (gameBoard.getEntity(possiblePosition) != null && !(gameBoard.getEntity(possiblePosition) instanceof PlayerMech))
                {
                    targetLocations.add(possiblePosition);
                }
            }
            catch (IndexOutOfBoundsException e)
            {
 
            }
        }
        
        return targetLocations;
    }
    
    @Override
    public List<MovementPath> computeAllPossibleMoves(GameBoard gameBoard)
    {
        List<MovementPath> possibleMoves = new ArrayList<MovementPath>();
        
        for (int pathLength = 0; pathLength <= maxMoves; pathLength++)
        {
            computeMove(new ArrayList<Position>(), position, pathLength, gameBoard, possibleMoves);
        }
        
        List<MovementPath> condensedPossibleMoves = new ArrayList<MovementPath>();
        
        for (MovementPath path1 : possibleMoves)
        {
            if (isShortestPath(possibleMoves, path1.getFinalPosition(), path1.getNumberOfMoves()) && isNotDuplicate(condensedPossibleMoves, path1.getFinalPosition()))
            {
                condensedPossibleMoves.add(path1);
            }
        }
        
        return condensedPossibleMoves;
    }
    
    private boolean isNotDuplicate(List<MovementPath> possibleMoves, Position comparePosition)
    {
        for (MovementPath path : possibleMoves)
        {
            if (comparePosition.equals(path.getFinalPosition()))
            {
                return false;
            }
        }
        return true;
    }

    private boolean isShortestPath(List<MovementPath> possibleMoves, Position comparePosition, int moves)
    {
        for (MovementPath path : possibleMoves)
        {
            if (comparePosition.equals(path.getFinalPosition()) && path.getNumberOfMoves() < moves)
            {
                return false;
            }
        }
        return true;
    }
    
    private void computeMove(List<Position> moveOrder, Position position, int movesLeft, GameBoard gameBoard, List<MovementPath> possibleMoves)
    {
        boolean validMove = true;
        
        try
        {
            if (gameBoard.getEntity(position) == null || gameBoard.getEntity(position).isPassable())
            {
                moveOrder.add(position);
            }
            else
            {
                validMove = false;
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            validMove = false;
        }
        
        if (movesLeft != 0 && validMove)
        {
            List<Position> newMoveOrder1 = new ArrayList<Position>();
            newMoveOrder1.addAll(moveOrder);
            List<Position> newMoveOrder2 = new ArrayList<Position>();
            newMoveOrder2.addAll(moveOrder);
            List<Position> newMoveOrder3 = new ArrayList<Position>();
            newMoveOrder3.addAll(moveOrder);
            List<Position> newMoveOrder4 = new ArrayList<Position>();
            newMoveOrder4.addAll(moveOrder);
            computeMove(newMoveOrder1, new Position(position.getX() + 1, position.getY()), movesLeft - 1, gameBoard, possibleMoves);
            computeMove(newMoveOrder2, new Position(position.getX(), position.getY() + 1), movesLeft - 1, gameBoard, possibleMoves);
            computeMove(newMoveOrder3, new Position(position.getX() - 1, position.getY()), movesLeft - 1, gameBoard, possibleMoves);
            computeMove(newMoveOrder4, new Position(position.getX(), position.getY() - 1), movesLeft - 1, gameBoard, possibleMoves);
        }
        possibleMoves.add(new MovementPath(moveOrder));
    }

    @Override
    public AttackReport attack(GameBoard gameBoard)
    {
        return null;
    }

    @Override
    public Entity makeCopy()
    {
        return new PunchMech(this);
    }


}
