// 
// Decompiled by Procyon v0.5.30
// 

public class fPointTree
{
    double x;
    double y;
    double vX;
    double vY;
    fPointTree left;
    fPointTree right;
    
    public fPointTree() {
        this.x = 0.0;
        this.y = 0.0;
        this.vX = 0.0;
        this.vY = 0.0;
        this.left = null;
        this.right = null;
    }
    
    public fPointTree(final double x, final double y, final double vx, final double vy, final fPointTree left, final fPointTree right) {
        this.x = x;
        this.y = y;
        this.vX = vx;
        this.vY = vy;
        this.left = left;
        this.right = right;
    }
}
