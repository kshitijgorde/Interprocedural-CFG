// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

import java.awt.Color;
import java.util.Vector;

public class pen_vector extends Vector
{
    public pen a(final int n) {
        return (n < 0 || n > super.elementCount) ? null : super.elementAt(n);
    }
    
    public Color b(final int n) {
        return (n < 0 || n > super.elementCount) ? null : super.elementAt(n).b;
    }
}
