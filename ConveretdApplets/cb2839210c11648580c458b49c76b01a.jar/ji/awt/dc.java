// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import java.awt.Dimension;

public class dc
{
    public long a;
    public long b;
    
    public dc() {
        this.a = 0L;
        this.b = 0L;
    }
    
    public dc(final long a, final long b) {
        this.a = a;
        this.b = b;
    }
    
    public dc(final dc dc) {
        if (dc != null) {
            this.a = dc.a;
            this.b = dc.b;
        }
    }
    
    public dc(final Dimension dimension) {
        if (dimension != null) {
            this.a = dimension.width;
            this.b = dimension.height;
        }
    }
    
    public final Dimension a() {
        return new Dimension((int)this.a, (int)this.b);
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer("jiDimension [width=").append(this.a).append(",height=").append(this.b).append("]")));
    }
}
