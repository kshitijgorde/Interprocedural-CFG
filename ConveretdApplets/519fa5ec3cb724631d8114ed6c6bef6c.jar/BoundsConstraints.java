import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class BoundsConstraints implements Cloneable
{
    public int minimumX;
    public int minimumY;
    public int minimumWidth;
    public int minimumHeight;
    public double weightX;
    public double weightY;
    public double weightWidth;
    public double weightHeight;
    
    public BoundsConstraints() {
        this(0, 0, 20, 20, 0.0, 0.0, 0.0, 0.0);
    }
    
    public BoundsConstraints(final int minimumX, final int minimumY, final int minimumWidth, final int minimumHeight) {
        this(minimumX, minimumY, minimumWidth, minimumHeight, 0.0, 0.0, 0.0, 0.0);
    }
    
    public BoundsConstraints(final int minimumX, final int minimumY, final int minimumWidth, final int minimumHeight, final double weightX, final double weightY, final double weightWidth, final double weightHeight) {
        this.minimumX = minimumX;
        this.minimumY = minimumY;
        this.minimumWidth = minimumWidth;
        this.minimumHeight = minimumHeight;
        this.weightX = weightX;
        this.weightY = weightY;
        this.weightWidth = weightWidth;
        this.weightHeight = weightHeight;
    }
    
    public Rectangle getBounds(final int extraWidth, final int extraHeight) {
        final Rectangle r = new Rectangle();
        r.x = this.minimumX + (int)(this.weightX * extraWidth);
        r.y = this.minimumY + (int)(this.weightY * extraHeight);
        r.width = this.minimumWidth + (int)(this.weightWidth * extraWidth);
        r.height = this.minimumHeight + (int)(this.weightHeight * extraHeight);
        return r;
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
}
