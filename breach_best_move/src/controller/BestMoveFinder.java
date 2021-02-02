package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import containers.AttackReport;
import containers.GameBoard;
import containers.MovementPath;
import containers.Position;
import containers.TurnReport;
import entities.ArtilleryMech;
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
        ArtilleryMech artilleryMech = new ArtilleryMech(new Position(0, 0));
        Mountain mountain = new Mountain("Mountain 1", new Position(3, 2));
        Wasp wasp1 = new Wasp("Wasp 1", new Position(2, 2), 2, new Position(2, 3), 1);
        Wasp wasp2 = new Wasp("Wasp 1", new Position(2, 1), 2, new Position(1, 1), 2);
        PowerBuilding powerBuilding = new PowerBuilding("Building 1", new Position(2, 3), 2);
        
        Tile[][] gameLayout =
        {
                { new Tile(artilleryMech), new Tile(null), new Tile(null), new Tile(null) },
                { new Tile(null), new Tile(testMech), new Tile(null), new Tile(null) },
                { new Tile(null), new Tile(wasp2), new Tile(wasp1), new Tile(powerBuilding) },
                { new Tile(null), new Tile(null), new Tile(mountain), new Tile(null) } };

        GameBoard gameBoard = new GameBoard(gameLayout);
        
        List<TurnReport> allReports = recursiveTryAllMoves(gameBoard);
        
        printReports(allReports);
    }
    
    private static void printReports(List<TurnReport> allReports)
    {
        for (TurnReport report : allReports)
        {
            System.out.println(report.toString());
        }
    }
    
    private static List<TurnReport> recursiveTryAllMoves(GameBoard gameBoard)
    {   
        List<TurnReport> allReports = new ArrayList<TurnReport>();
        
        // Get current stock of the board
        GameBoard localGameBoard = new GameBoard(gameBoard);
        
        // Get a list of all active mechs
        List<PlayerMech> mechList = localGameBoard.getActiveMechs();
        
        for (PlayerMech mech : mechList)
        {
            // Check if the mech can move
            if (mech.hasMoves())
            {
                // Check every possible it can do from its current position
                for (MovementPath movement : mech.computeAllPossibleMoves(localGameBoard))
                {
                    // Move the mech to its possible location
                    AttackReport report = new AttackReport();
                    GameBoard mechMovedBoard = new GameBoard(localGameBoard);
                    PlayerMech movedMech = mechMovedBoard.makeUnitMove(mech.getPosition(), movement.getFinalPosition());
                    
                    // Try every possible attack from its location
                    List<TurnReport> tempTurnReportList = tryAllAttacks(mechMovedBoard, report, movedMech, movement,
                            mechList.size() == 1);
                    
                    // Return compiled report
                    for (TurnReport turnReport : tempTurnReportList)
                    {
                        turnReport.addMove(mech.getName(), movement);
                    }
                    allReports.addAll(tempTurnReportList);
                }   
            }
            
            // Check if mech can shoot
            if (mech.isActive())
            {
                List<TurnReport> tempTurnReportList = tryAllAttacks(localGameBoard, new AttackReport(), mech, new MovementPath(Arrays.asList(mech.getPosition())), mechList.size() == 1);
                for (TurnReport turnReport : tempTurnReportList)
                {
                    turnReport.addMove(mech.getName(), new MovementPath(Arrays.asList(mech.getPosition())));
                }
                allReports.addAll(tempTurnReportList);
            }
        }
        
        return allReports;
    }
    
    private static List<TurnReport> tryAllAttacks(GameBoard gameBoard, AttackReport report, PlayerMech mech, MovementPath movement, boolean onlyMech)
    {
        List<TurnReport> turnReports = new ArrayList<TurnReport>();
        GameBoard localGameBoard = new GameBoard(gameBoard);
        
        // Find each possible target location
        for (Position targetPosition : mech.computePossibleAttackingLocations(localGameBoard))
        {
            // Attack that position
            GameBoard mechAttackedBoard = new GameBoard(localGameBoard);
            report.combine(mechAttackedBoard.makeUnitAttack(mech.getPosition(), targetPosition));
            
            // Check if that was the last move
            if (onlyMech)
            {
                GameBoard vekAttackedBoard = new GameBoard(mechAttackedBoard);
                
                List<Vek> vekList = vekAttackedBoard.getActiveVek();
                
                // Make each Vek move
                report.combine(vekMoves(vekList, vekAttackedBoard));
                
                // Return what moves were taken
                
                
                turnReports.add(new TurnReport(mech.getName(), movement, targetPosition, report));
            }
            else
            {
                // If the mech was not the only one left, start from the beginning and try to make moves after the attack was made
                List<TurnReport> tempTurnReportList = recursiveTryAllMoves(mechAttackedBoard);
                
                for (TurnReport turnReport : tempTurnReportList)
                {
                    turnReport.getReport().combine(report);
                    turnReport.addTargetLocation(mech.getName(), targetPosition);
                }
                turnReports.addAll(tempTurnReportList);
            }
        }
        return turnReports;
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
