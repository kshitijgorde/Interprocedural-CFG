// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.SWTEventObject;

public class AccessibleHyperlinkEvent extends SWTEventObject
{
    public Accessible accessible;
    public String result;
    public int index;
    static final long serialVersionUID = 6253098373844074544L;
    
    public AccessibleHyperlinkEvent(final Object o) {
        super(o);
    }
    
    public String toString() {
        return "AccessibleHyperlinkEvent {accessible=" + this.accessible + " string=" + this.result + " index=" + this.index + "}";
    }
}
