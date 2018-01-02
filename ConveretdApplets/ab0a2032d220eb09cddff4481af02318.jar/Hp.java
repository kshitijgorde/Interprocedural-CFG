import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Hp
{
    Color Ba;
    int width;
    private final Fp n;
    
    public Hp(final Fp n, final Color ba, final int width) {
        this.n = n;
        if (ba == null) {
            throw new IllegalArgumentException("invalid argument(s) passed");
        }
        this.Ba = ba;
        this.width = width;
    }
    
    public Color getColor() {
        return this.Ba;
    }
    
    public int getWidth() {
        return this.width;
    }
}
