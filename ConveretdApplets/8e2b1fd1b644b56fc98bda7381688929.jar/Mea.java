import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mea
{
    Color T;
    int width;
    
    public Mea(final Color t, final int width) {
        if (t == null) {
            throw new IllegalArgumentException("invalid argument(s) passed");
        }
        this.T = t;
        this.width = width;
    }
    
    public Color getColor() {
        return this.T;
    }
    
    public int getWidth() {
        return this.width;
    }
}
