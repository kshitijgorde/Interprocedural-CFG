// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Image;

public final class Y extends U
{
    public Image a;
    
    public final Object a(final String s) {
        if ("image".equals(s)) {
            return this.a;
        }
        if ("view".equals(s)) {
            return new Boolean(this.a(1));
        }
        if ("view2".equals(s)) {
            return new Boolean(this.a(2));
        }
        return super.a(s);
    }
    
    public final boolean a(final String s, final Object o) {
        if ("view".equals(s)) {
            final boolean booleanValue = (boolean)o;
            if (this.a(1) != booleanValue) {
                super.j = 1;
            }
            this.a(1, booleanValue);
            return true;
        }
        if ("view2".equals(s)) {
            final boolean booleanValue2 = (boolean)o;
            if (this.a(2) != booleanValue2) {
                super.j = 1;
            }
            this.a(2, booleanValue2);
            return true;
        }
        return false;
    }
    
    public final String a() {
        return null;
    }
    
    public Y(final Y y) {
        this(y.i, y.d);
        super.b = y.b;
        this.a = y.a;
    }
    
    public Y(final int n, final String s) {
        super(n, s);
    }
}
