package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import containers.AttackReport;
import containers.GameBoard;
import containers.MovementPath;
import containers.Position;
import containers.TurnReport;
import entities.Mountain;
import entities.PlayerMech;
import entities.PowerBuilding;
import entities.PunchMech;
import entities.Vek;
import entities.Wasp;
import tiles.Tile;

public class BestMoveFinder
{
    public static void main(String[] args)
    {
        PunchMech testMech = new PunchMech(new Position(1, 1));
        Mountain mountain = new Mountain("Mountain 1", new Position(3, 2));
        Wasp wasp = new Wasp("Wasp 1", new Position(2, 2), 2, new Position(2, 3), 1);
        PowerBuilding powerBuilding = new PowerBuilding("Building 1", new Position(2, 3), 2);
        
        Tile[][] gameLayout =
        {
                { new Tile(null), new Tile(null), new Tile(null), new Tile(null) },
                { new Tile(null), new Tile(testMech), new Tile(null), new Tile(null) },
                { new Tile(null), new Tile(null), new Tile(wasp), new Tile(powerBuilding) },
                { new Tile(null), new Tile(null), new Tile(mountain), new Tile(null) } };

        GameBoard gameBoard = new GameBoard(gameLayout);
        
        List<TurnReport> allReports = tryAllMoves(gameBoard);
        
        printReports(allReports);
    }
    
    private static void printReports(List<TurnReport> allReports)
    {
        for (TurnReport report : allReports)
        {
            System.out.println(report.toString());
        }
    }

    private static List<TurnReport> tryAllMoves(GameBoard gameBoard)
    {   
        List<TurnReport> allReports = new ArrayList<TurnReport>();
        List<PlayerMech> initialMechList = gameBoard.getActiveMechs();
        
        for (PlayerMech mech1 : initialMechList)
        {
            for (MovementPath movement : mech1.computeAllPossibleMoves(gameBoard))
            {
                AttackReport report = new AttackReport();
                GameBoard mech1MovedBoard = new GameBoard(gameBoard);
                mech1MovedBoard.moveEntity(mech1.getPosition(), movement.getFinalPosition());
                
                PlayerMech movedMech = (PlayerMech) mech1MovedBoard.getEntity(movement.getFinalPosition());
                
                for (Position targetPosition : movedMech.computePossibleAttackingLocations(mech1MovedBoard))
                {
                    GameBoard mech1AttackedBoard = new GameBoard(mech1MovedBoard);
                    report.combine(movedMech.attack(mech1AttackedBoard, targetPosition));     
                    
                    GameBoard vekAttackedBoard = new GameBoard(mech1AttackedBoard);
                    
                    List<Vek> vekList = vekAttackedBoard.getActiveVek();
                    
                    report.combine(vekMoves(vekList, vekAttackedBoard));
                    
                    Map<String, MovementPath> movesTaken = new HashMap<String, MovementPath>();
                    movesTaken.put(movedMech.getName(), movement);
                    
                    allReports.add(new TurnReport(movesTaken, report));
                }
            }
        }
        return allReports;
    }
    
    private static AttackReport vekMoves(List<Vek> vekList, GameBoard gameBoard)
    {
        AttackReport report = new AttackReport();
        
        for (Vek vek : vekList)
        {
            report.combine(vek.attack(gameBoard));
        }
        
        return report;
    }
}
