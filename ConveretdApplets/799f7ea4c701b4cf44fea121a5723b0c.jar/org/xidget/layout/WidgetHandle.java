// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout;

import org.xidget.ifeature.ILayoutFeature;
import org.xidget.IXidget;

public class WidgetHandle extends ComputeNode
{
    private String name;
    private int offset;
    
    public WidgetHandle(final IXidget xidget, final ILayoutFeature.Side side, final int offset) {
        String s = xidget.getConfig().getID();
        if (s.length() == 0) {
            s = xidget.getConfig().getType();
        }
        this.name = String.format("%s#%s", s, side.toString());
        this.offset = offset;
    }
    
    @Override
    public void setValue(final float n) {
        super.setValue(n + this.offset);
    }
    
    @Override
    public String toString() {
        return String.format("%d. (%s) handle( %s) %+d <- %s", this.getID(), this.printValue(), this.name, this.offset, this.printDependencies());
    }
}
