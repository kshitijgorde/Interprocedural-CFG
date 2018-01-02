// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

public final class Touch
{
    public long id;
    public TouchSource source;
    public int state;
    public boolean primary;
    public int x;
    public int y;
    
    Touch(final long id, final TouchSource source, final int state, final boolean primary, final int x, final int y) {
        this.id = id;
        this.source = source;
        this.state = state;
        this.primary = primary;
        this.x = x;
        this.y = y;
    }
    
    public String toString() {
        return "Touch {id=" + this.id + " source=" + this.source + " state=" + this.state + " primary=" + this.primary + " x=" + this.x + " y=" + this.y + "}";
    }
}
