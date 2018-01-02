// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import java.awt.Dimension;
import java.awt.Rectangle;

public class da
{
    public long a;
    public long b;
    public long c;
    public long d;
    
    public da() {
        this.a = 0L;
        this.b = 0L;
        this.c = 0L;
        this.d = 0L;
    }
    
    public da(final long a, final long b, final long c, final long d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public da(final da da) {
        if (da != null) {
            this.a = da.a;
            this.b = da.b;
            this.c = da.c;
            this.d = da.d;
        }
    }
    
    public da(final Rectangle rectangle) {
        if (rectangle != null) {
            this.a = rectangle.x;
            this.b = rectangle.y;
            this.c = rectangle.width;
            this.d = rectangle.height;
        }
    }
    
    public final Rectangle a() {
        return new Rectangle((int)this.a, (int)this.b, (int)this.c, (int)this.d);
    }
    
    public final Dimension b() {
        return new Dimension((int)this.c, (int)this.d);
    }
    
    public final boolean a(final int n, final int n2) {
        return n >= this.a && n <= this.a + this.c && n2 >= this.b && n2 <= this.b + this.d;
    }
    
    public final boolean a(final long n, final long n2) {
        return n >= this.a && n <= this.a + this.c && n2 >= this.b && n2 <= this.b + this.d;
    }
    
    public final boolean a(final da da) {
        return this.a(da.a, da.b) && this.a(da.a + da.c, da.b) && this.a(da.a + da.c, da.b + da.d) && this.a(da.a, da.b + da.d);
    }
    
    public final da b(final da da) {
        return new da(new da(this.a, this.b, this.c, this.d).a().intersection(da.a()));
    }
    
    public final da c(final da da) {
        final long max = Math.max(this.a + this.c, da.a + da.c);
        final long max2 = Math.max(this.b + this.d, da.b + da.d);
        final long min = Math.min(this.a, da.a);
        final long min2 = Math.min(this.b, da.b);
        return new da(min, min2, max - min, max2 - min2);
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer("jiRectangle [x=").append(this.a).append(",y=").append(this.b).append(",width=").append(this.c).append(",height=").append(this.d).append("]")));
    }
    
    public da c() {
        return new da(this);
    }
}
