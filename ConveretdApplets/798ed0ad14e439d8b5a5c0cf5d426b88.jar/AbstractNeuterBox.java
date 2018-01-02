import java.awt.Font;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class AbstractNeuterBox
{
    public int x;
    public int y;
    public int width;
    public int height;
    protected int I;
    public int baseline;
    protected String Z;
    
    public abstract void calculRect(final BoxComponent p0);
    
    public void onPaint(final Graphics graphics) {
    }
    
    public void onPaint(final BoxComponent boxComponent, final Graphics graphics) {
        this.onPaint(graphics);
    }
    
    public final void setFontName(final String z) {
        this.Z = z;
    }
    
    public void enEsborrar() {
    }
    
    public abstract void setFont(final BoxComponent p0, final Font p1);
    
    public AbstractNeuterBox() {
        this.Z = null;
    }
    
    public final int em() {
        return this.I;
    }
    
    public final int em(final float n) {
        return Math.round(n * this.I);
    }
    
    public final int yh() {
        return this.y + this.height;
    }
}
