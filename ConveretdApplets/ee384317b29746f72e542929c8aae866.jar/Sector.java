import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sector implements Serializable
{
    private String time;
    private String sectorId;
    private String sectorName;
    private String status;
    private double indexPoints;
    private double change;
    private double percentChange;
    
    public Sector(final String sectorId, final String sectorName, final double indexPoints, final double change, final double percentChange) {
        this.sectorId = sectorId;
        this.sectorName = sectorName;
        this.indexPoints = indexPoints;
        this.change = change;
        this.percentChange = percentChange;
    }
    
    public String getTime() {
        return this.time;
    }
    
    public String getSectorId() {
        return this.sectorId;
    }
    
    public String getSectorName() {
        return this.sectorName;
    }
    
    public double getIndexPoints() {
        return this.indexPoints;
    }
    
    public double getChange() {
        return this.change;
    }
    
    public double getPercentChange() {
        return this.percentChange;
    }
}
