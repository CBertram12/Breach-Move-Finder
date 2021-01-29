package controller;

import java.util.ArrayList;
import java.util.List;

import entities.PlayerMech;
import tiles.Tile;

public class BestMoveFinder
{
    private static Tile[][] gameBoard = 
        {
                { new Tile(null), new Tile(null), new Tile(null), new Tile(null)},
                { new Tile(null), new Tile(null), new Tile(null), new Tile(null)},
                { new Tile(null), new Tile(null), new Tile(null), new Tile(null)},
                { new Tile(null), new Tile(null), new Tile(null), new Tile(null)}
        };
    
    private static List<PlayerMech> mechList = new ArrayList<PlayerMech>();
    
    public static void main(String[] args)
    {
        
    }
    
    private void tryAllMoves()
    {
        for (PlayerMech mech1 : mechList)
        {
            mech1.move(0, 0);
            mech1.attack(gameBoard);
            
            for (PlayerMech mech2 : mechList)
            {
                if (mech2 == mech1) break;
                
                mech2.move(0, 0);
                mech2.attack(gameBoard);
                
                for (PlayerMech mech3 : mechList)
                {
                    if (mech3 == mech1 || mech3 == mech2) break;
                    
                    mech3.move(0, 0);
                    mech3.attack(gameBoard);
                }
            }
        }
    }
}
