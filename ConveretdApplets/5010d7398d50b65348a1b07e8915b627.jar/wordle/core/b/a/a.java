// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.b.a;

import wordle.core.C;
import wordle.core.e.b;
import java.awt.Color;

public final class a implements e
{
    private final e a;
    private final double b;
    private final float[] c;
    
    public a(final e a, final double b) {
        this.c = new float[3];
        this.a = a;
        this.b = b;
    }
    
    public final Color[] a() {
        return this.a.a();
    }
    
    public final Color b() {
        return this.a.b();
    }
    
    private float a(final float n) {
        return Math.max(0.0f, Math.min(1.0f, (float)(n - this.b / 2.0 + this.b * wordle.core.e.b.a())));
    }
    
    public final Color a(final C c) {
        final a a = this;
        final Color a2 = this.a.a(c);
        this = a;
        Color.RGBtoHSB(a2.getRed(), a2.getGreen(), a2.getBlue(), this.c);
        if (this.c[0] < 0.01) {
            return Color.getHSBColor(0.0f, this.c[1], this.a(this.c[2]));
        }
        return Color.getHSBColor(this.a(this.c[0]), this.c[1], this.c[2]);
    }
    
    public final e c() {
        return this.a;
    }
    
    public final double d() {
        return this.b;
    }
}
