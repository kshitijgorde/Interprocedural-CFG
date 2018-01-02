// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.SWTEventObject;

public class AccessibleTableCellEvent extends SWTEventObject
{
    public Accessible accessible;
    public Accessible[] accessibles;
    public boolean isSelected;
    public int count;
    public int index;
    static final long serialVersionUID = 7231059449172889781L;
    
    public AccessibleTableCellEvent(final Object o) {
        super(o);
    }
    
    public String toString() {
        return "AccessibleTableCellEvent { accessibles=" + this.accessibles + " isSelected=" + this.isSelected + " count=" + this.count + " index=" + this.index + "}";
    }
}
