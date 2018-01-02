import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_dZ
{
    public Color a;
    public Color b;
    
    public rp_dZ(final Color a, final Color b) {
        this.a = a;
        this.b = b;
    }
    
    public final boolean a(final Color color, final Color color2) {
        if (this.a == null || this.b == null) {
            return color == null || color2 == null;
        }
        return color != null && color2 != null && this.a.getRGB() == color.getRGB() && this.b.getRGB() == color2.getRGB();
    }
}
