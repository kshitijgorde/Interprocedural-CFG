// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Color;

public final class d extends bu implements a
{
    public d(final String s) {
        super(-999, s);
        super.a = null;
        final Color color = null;
        super.b = color;
        super.a = color;
    }
    
    public d(final bu bu) {
        super(bu.i, bu.d);
        super.b = bu.b;
        super.a = bu.a;
        super.b = bu.b;
        super.a = bu.a;
        super.a = bu.a;
        super.b = bu.b;
    }
    
    public final boolean a(final String s, final Object o) {
        if (!"view".equals(s)) {
            return false;
        }
        final boolean booleanValue = (boolean)o;
        if (this.a(8) != booleanValue) {
            super.j = 1;
        }
        this.a(8, booleanValue);
        return true;
    }
    
    public final Object a(final String s) {
        if ("view".equals(s)) {
            return new Boolean(this.a(8));
        }
        return super.a(s);
    }
}
