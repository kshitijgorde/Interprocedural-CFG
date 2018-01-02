// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.accessibility;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.SWTEventObject;

public class AccessibleTextEvent extends SWTEventObject
{
    public int childID;
    public int offset;
    public int length;
    public Accessible accessible;
    public String result;
    public int count;
    public int index;
    public int start;
    public int end;
    public int type;
    public int x;
    public int y;
    public int width;
    public int height;
    public int[] ranges;
    public Rectangle[] rectangles;
    static final long serialVersionUID = 3977019530868308275L;
    
    public AccessibleTextEvent(final Object o) {
        super(o);
    }
    
    public String toString() {
        return "AccessibleTextEvent {childID=" + this.childID + " offset=" + this.offset + " length=" + this.length + "}";
    }
}
