// 
// Decompiled by Procyon v0.5.30
// 

class PointThreeD
{
    static final double PI = 3.14159265359;
    static final double DEGTORAD = 0.017453292519944444;
    public double x;
    public double y;
    public double z;
    public double originalX;
    public double originalY;
    public double originalZ;
    
    public PointThreeD() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        this.originalX = 0.0;
        this.originalY = 0.0;
        this.originalZ = 0.0;
    }
    
    public PointThreeD(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public PointThreeD Translate(final PointThreeD pointThreeD, final double n, final double n2, final double n3) {
        pointThreeD.x += n;
        pointThreeD.y += n2;
        pointThreeD.z += n3;
        return pointThreeD;
    }
    
    public PointThreeD RotateZAxis(final PointThreeD pointThreeD, final int n) {
        pointThreeD.x = pointThreeD.x * TrigFunctions.cosarray[n] - pointThreeD.y * TrigFunctions.sinarray[n];
        pointThreeD.y = pointThreeD.x * TrigFunctions.sinarray[n] + pointThreeD.y * TrigFunctions.cosarray[n];
        return pointThreeD;
    }
}
