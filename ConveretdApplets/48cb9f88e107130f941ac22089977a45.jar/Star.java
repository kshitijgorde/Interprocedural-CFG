import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Star extends PointThreeD
{
    private static final int DOT = 0;
    private static final int CROSS = 1;
    private static final int BLOB = 2;
    private static final int STAR = 3;
    public double originalX;
    public double originalY;
    public double originalZ;
    public Color colour;
    private int intType;
    public PointTwoD twoD;
    
    public void TranslateStar(final double n, final double n2, final double n3) {
        super.x += n;
        super.y += n2;
        super.z += n3;
        this.originalX = super.x;
        this.originalY = super.y;
        this.originalZ = super.z;
    }
    
    public void MapMeToTwoD(final Rectangle rectangle) {
        this.twoD = PointTwoD.MapToTwoD(rectangle, this);
    }
    
    public void draw(final Graphics graphics, final int n) {
        if (this.intType == 0 || n == 1) {
            graphics.drawLine(this.twoD.x, this.twoD.y, this.twoD.x, this.twoD.y);
            return;
        }
        if (this.intType == 1) {
            graphics.drawLine(this.twoD.x - n, this.twoD.y, this.twoD.x + n, this.twoD.y);
            graphics.drawLine(this.twoD.x, this.twoD.y - n, this.twoD.x, this.twoD.y + n);
            return;
        }
        if (this.intType == 2) {
            graphics.fillOval(this.twoD.x, this.twoD.y, n, n);
            return;
        }
        graphics.drawLine(this.twoD.x - n, this.twoD.y, this.twoD.x + n, this.twoD.y);
        graphics.drawLine(this.twoD.x, this.twoD.y - n, this.twoD.x, this.twoD.y + n);
    }
    
    public Star() {
        super.x = 0.0;
        super.y = 0.0;
        super.z = 0.0;
        this.originalX = 0.0;
        this.originalY = 0.0;
        this.originalZ = 0.0;
        this.colour = new Color(128, 128, 128);
        this.intType = 1;
    }
    
    public Star(final double x, final double y, final double z, final int intType, final Color colour) {
        super.x = x;
        super.y = y;
        super.z = z;
        this.originalX = super.x;
        this.originalY = super.y;
        this.originalZ = super.z;
        this.intType = intType;
        this.colour = colour;
    }
    
    public void RotateStarZAxis(final int n, final int n2) {
        final double x = super.x * TrigFunctions.cosarray[n] - super.y * n2 * TrigFunctions.sinarray[n];
        final double y = super.x * n2 * TrigFunctions.sinarray[n] + super.y * TrigFunctions.cosarray[n];
        super.x = x;
        super.y = y;
    }
}
