// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.SWTEventObject;

public class AccessibleActionEvent extends SWTEventObject
{
    public String result;
    public int count;
    public int index;
    public boolean localized;
    static final long serialVersionUID = 2849066792640153087L;
    
    public AccessibleActionEvent(final Object o) {
        super(o);
    }
    
    public String toString() {
        return "AccessibleActionEvent {string=" + this.result + " count=" + this.count + " index=" + this.index + "}";
    }
}
