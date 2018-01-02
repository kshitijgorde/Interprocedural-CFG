// 
// Decompiled by Procyon v0.5.30
// 

public class SectorPresentation
{
    String sectorId;
    String sectorName;
    int x1Pos;
    int y1Pos;
    int x2Pos;
    int y2Pos;
    
    public SectorPresentation(final String sectorId, final String sectorName, final int x1Pos, final int y1Pos, final int x2Pos, final int y2Pos) {
        this.sectorId = sectorId;
        this.sectorName = sectorName;
        this.x1Pos = x1Pos;
        this.y1Pos = y1Pos;
        this.x2Pos = x2Pos;
        this.y2Pos = y2Pos;
    }
    
    public String getSectorId() {
        return this.sectorId;
    }
    
    public String getSectorName() {
        return this.sectorName;
    }
    
    public int getX1Pos() {
        return this.x1Pos;
    }
    
    public int getY1Pos() {
        return this.y1Pos;
    }
    
    public int getX2Pos() {
        return this.x2Pos;
    }
    
    public int getY2Pos() {
        return this.y2Pos;
    }
    
    public boolean include(final int n, final int n2) {
        boolean b = false;
        if (n >= this.x1Pos && n <= this.x2Pos && n2 <= this.y1Pos && n2 >= this.y2Pos) {
            b = true;
        }
        return b;
    }
}
