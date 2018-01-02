// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.SWTEventObject;

public class AccessibleValueEvent extends SWTEventObject
{
    public Number value;
    static final long serialVersionUID = -465979079760740668L;
    
    public AccessibleValueEvent(final Object o) {
        super(o);
    }
    
    public String toString() {
        return "AccessibleValueEvent {value=" + this.value + "}";
    }
}
