// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.a;

import java.awt.Color;
import java.util.Hashtable;

public class h extends l
{
    private int[] if;
    private static Hashtable do;
    
    public h() {
        this.if = new int[255];
    }
    
    public void a(final Color color) {
        super.a(color);
        if (h.do.containsKey(color)) {
            this.if = h.do.get(color);
        }
        else {
            final double n = 0.00392156862745098;
            final float[] rgBtoHSB = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), new float[3]);
            this.if = new int[255];
            for (int i = 1; i < 255; ++i) {
                this.if[i - 1] = Color.HSBtoRGB(rgBtoHSB[0], rgBtoHSB[1], (float)n * i * rgBtoHSB[2]);
            }
            h.do.put(color, this.if);
        }
    }
    
    public int a(final double n) {
        return this.if[(int)(254.0 * n)];
    }
    
    public int a(final Color color, final double n) {
        final float[] rgBtoHSB = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), new float[3]);
        return Color.HSBtoRGB(rgBtoHSB[0], rgBtoHSB[1], (float)n * rgBtoHSB[2]);
    }
    
    static {
        h.do = new Hashtable();
    }
}
