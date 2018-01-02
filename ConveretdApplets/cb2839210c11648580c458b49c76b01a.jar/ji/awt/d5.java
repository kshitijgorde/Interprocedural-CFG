// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import java.awt.Point;

public class d5
{
    public long a;
    public long b;
    
    public d5() {
        this.a = 0L;
        this.b = 0L;
    }
    
    public d5(final long a, final long b) {
        this.a = a;
        this.b = b;
    }
    
    public final Point a() {
        return new Point((int)this.a, (int)this.b);
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer("jiPoint [x=").append(this.a).append(",y=").append(this.b).append("]")));
    }
}
