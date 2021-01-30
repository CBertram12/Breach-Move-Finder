package containers;

public class AttackReport
{
    private int powerLost;
    private int mechHealthLost;
    private int vekDamageDone;
    
    public AttackReport()
    {
        powerLost = 0;
        mechHealthLost = 0;
        vekDamageDone = 0;
    }
    
    public void incrementPowerLost(int increment)
    {
        powerLost += increment;
    }
    
    public void incrementMechHealthLost(int increment)
    {
        mechHealthLost += increment;
    }
    
    public void incrementVekDamageDone(int increment)
    {
        vekDamageDone += increment;
    }

    public int getPowerLost()
    {
        return powerLost;
    }

    public int getMechHealthLost()
    {
        return mechHealthLost;
    }

    public int getVekDamageDone()
    {
        return vekDamageDone;
    }
    
    public void combine(AttackReport newReport)
    {
        this.powerLost += newReport.powerLost;
        this.mechHealthLost += newReport.mechHealthLost;
        this.vekDamageDone += newReport.vekDamageDone;
    }
    
    @Override
    public String toString()
    {
        String string = "Power Lost: " + powerLost + " Mech Damage: " + mechHealthLost + " Vek Damage: " + vekDamageDone + "\n";
        
        return string;
    }
    
}
