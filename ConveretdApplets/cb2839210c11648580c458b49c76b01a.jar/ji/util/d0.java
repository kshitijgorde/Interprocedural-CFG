// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import java.awt.Point;

public class d0
{
    public static Point a(final double n, final double n2, final double n3, final double n4) {
        return new Point((int)Math.round(n * n3), (int)Math.round(n2 * n4));
    }
    
    public static int a(final double n, final double n2) {
        return (int)Math.round(n * n2);
    }
}
