// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;

public final class ce extends bZ
{
    public ce(final int n, final String s) {
        super(n, s);
    }
    
    public ce(final ce ce) {
        this(ce.r, ce.getName());
        this.y(ce.y());
        this.t(ce.u);
        this.r(ce.y);
        this.q(ce.q());
    }
    
    public final boolean q(final String s, final Object o) {
        if ("master".equals(s)) {
            this.q(0, (boolean)o);
            return true;
        }
        if ("guestnick".equals(s)) {
            this.q(1, (boolean)o);
            return true;
        }
        if ("guestbackground".equals(s)) {
            this.q(2, (boolean)o);
            return true;
        }
        if ("guestwrite".equals(s)) {
            this.q(3, (boolean)o);
            return true;
        }
        return false;
    }
    
    public final Object q(final String s) {
        if ("image".equals(s)) {
            return new Color(this.y());
        }
        if ("master".equals(s)) {
            return new Boolean(this.q(0));
        }
        if ("guestnick".equals(s)) {
            return new Boolean(this.q(1));
        }
        if ("guestbackground".equals(s)) {
            return new Boolean(this.q(2));
        }
        if ("guestwrite".equals(s)) {
            return new Boolean(this.q(3));
        }
        return super.q(s);
    }
}
