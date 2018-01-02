// 
// Decompiled by Procyon v0.5.30
// 

package de.ts.b.a;

public class h extends a
{
    public static final int new = 0;
    public static final int G = 1;
    public static final int H = 2;
    public static final int K = 3;
    public static final int D = 4;
    public static final int I = 5;
    public static final int J = 5;
    private static final String[] E;
    String F;
    
    public h(final String f) {
        this.F = null;
        this.F = f;
    }
    
    public int long() {
        int n = 0;
        for (int i = 0; i < h.E.length; ++i) {
            if (this.F.equals(h.E[i])) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    static {
        E = new String[] { "none", "mouseClicked", "mouseEntered", "mouseExited", "mousePressed", "mouseReleased" };
    }
}
