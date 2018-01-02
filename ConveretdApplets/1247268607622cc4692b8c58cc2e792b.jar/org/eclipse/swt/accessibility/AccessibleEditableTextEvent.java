// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.SWTEventObject;

public class AccessibleEditableTextEvent extends SWTEventObject
{
    public int start;
    public int end;
    public String string;
    public String result;
    static final long serialVersionUID = -5045447704486894646L;
    
    public AccessibleEditableTextEvent(final Object o) {
        super(o);
    }
    
    public String toString() {
        return "AccessibleEditableTextEvent {start=" + this.start + " end=" + this.end + " string=" + this.string + " result=" + this.result + "}";
    }
}
