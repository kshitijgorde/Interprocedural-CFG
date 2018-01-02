// 
// Decompiled by Procyon v0.5.30
// 

package JGrid;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class d
{
    protected String do;
    protected String a;
    protected boolean for;
    protected boolean if;
    protected static int int;
    
    protected Object getObject() {
        return new String();
    }
    
    protected void setValue(final Object o) {
    }
    
    protected void setFeature(final String s) {
    }
    
    protected abstract void draw(final Rectangle p0, final Graphics p1, final e p2);
    
    public d() {
        this.for = true;
        this.if = false;
    }
}
