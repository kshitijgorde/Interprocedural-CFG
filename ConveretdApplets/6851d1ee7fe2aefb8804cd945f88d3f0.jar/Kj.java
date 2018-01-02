import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Kj
{
    Color xa;
    int width;
    
    public Kj(final Color xa, final int width) {
        if (xa == null) {
            throw new IllegalArgumentException("invalid argument(s) passed");
        }
        this.xa = xa;
        this.width = width;
    }
    
    public Color getColor() {
        return this.xa;
    }
    
    public int getWidth() {
        return this.width;
    }
}
