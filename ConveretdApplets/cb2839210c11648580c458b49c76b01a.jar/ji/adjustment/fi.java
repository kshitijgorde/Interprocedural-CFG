// 
// Decompiled by Procyon v0.5.30
// 

package ji.adjustment;

import java.awt.Point;
import ji.io.p;
import ji.awt.c;

public class fi
{
    private c a;
    private boolean b;
    private p c;
    private Object d;
    
    public fi(final boolean b, final String s, final Object d) {
        this.b = false;
        this.a = new c("jiMergeAdjustmentData", 100);
        this.b = b;
        this.c = new p(s);
        this.d = d;
    }
    
    public void a(final int n, final int n2, final int n3, final double n4, final double n5, final int n6, final int n7, final int n8, final Point point, final boolean b) {
        final v2 v2 = new v2(n2, n3, n4, n5, n6, n7, n8, point, b);
        if (this.a.d().a(String.valueOf(n))) {
            this.a.a(String.valueOf(n));
        }
        this.a.a(String.valueOf(n), v2);
    }
    
    public int a(final int n) {
        final v2 v2 = (v2)this.a.d(String.valueOf(n));
        if (v2 != null) {
            return v2.a;
        }
        return 0;
    }
    
    public int b(final int n) {
        final v2 v2 = (v2)this.a.d(String.valueOf(n));
        if (v2 != null) {
            return v2.b;
        }
        return 0;
    }
    
    public double c(final int n) {
        final v2 v2 = (v2)this.a.d(String.valueOf(n));
        if (v2 != null) {
            return v2.c;
        }
        return 0.0;
    }
    
    public double d(final int n) {
        final v2 v2 = (v2)this.a.d(String.valueOf(n));
        if (v2 != null) {
            return v2.d;
        }
        if (this.b) {
            return this.c.a("foregroundRotationAngle", 1.0, this.d);
        }
        return this.c.a("backgroundRotationAngle", 1.0, this.d);
    }
    
    public int e(final int n) {
        final v2 v2 = (v2)this.a.d(String.valueOf(n));
        if (v2 != null) {
            return v2.e;
        }
        if (this.b) {
            return this.c.b("foregroundAdjustmentStep", 5, this.d);
        }
        return this.c.b("backgroundAdjustmentStep", 5, this.d);
    }
    
    public int f(final int n) {
        final v2 v2 = (v2)this.a.d(String.valueOf(n));
        if (v2 != null) {
            return v2.f;
        }
        if (this.b) {
            return this.c.b("foregroundAdjustmentUnits", 0, this.d);
        }
        return this.c.b("backgroundAdjustmentUnits", 0, this.d);
    }
    
    public int g(final int n) {
        final v2 v2 = (v2)this.a.d(String.valueOf(n));
        if (v2 != null) {
            return v2.g;
        }
        if (this.b) {
            return this.c.b("foregroundRotationUnits", 2, this.d);
        }
        return this.c.b("backgroundRotationUnits", 2, this.d);
    }
    
    public Point h(final int n) {
        final v2 v2 = (v2)this.a.d(String.valueOf(n));
        if (v2 != null) {
            return v2.h;
        }
        if (this.b) {
            return this.c.a("foregroundRotationOrigin", eh.x, this.d);
        }
        return this.c.a("backgroundRotationOrigin", eh.x, this.d);
    }
    
    public boolean i(final int n) {
        final v2 v2 = (v2)this.a.d(String.valueOf(n));
        if (v2 != null) {
            return v2.i;
        }
        if (this.b) {
            return this.c.b("foregroundRotationOriginCentre", false, this.d);
        }
        return this.c.b("backgroundRotationOriginCentre", false, this.d);
    }
    
    private class v2
    {
        public int a;
        public int b;
        public double c;
        public double d;
        public int e;
        public int f;
        public int g;
        public Point h;
        public boolean i;
        
        public v2(final fi fi, final int a, final int b, final double c, final double d, final int e, final int f, final int g, final Point h, final boolean i) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
            this.f = f;
            this.g = g;
            this.h = h;
            this.i = i;
        }
    }
}
