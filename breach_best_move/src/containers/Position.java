package containers;

public class Position
{
    private int x;
    private int y;
    
    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Position(Position position)
    {
        this.x = position.x;
        this.y = position.y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
    
    public void shiftPosition(int x_move, int y_move)
    {
        x += x_move;
        y += y_move;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o == this) 
        { 
            return true; 
        } 

        if (!(o instanceof Position)) 
        { 
            return false; 
        }
        
        Position comparePosition = (Position)o;
        
        if (this.x == comparePosition.x && this.y == comparePosition.y)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public int hashCode()
    {
        return (x * 99 + 10) + (y * 99 + 11);
    }
    
    @Override
    public String toString()
    {
        String string = "Position x: " + x + " Position y: " + y + "\n";
        
        return string;
    }
}
