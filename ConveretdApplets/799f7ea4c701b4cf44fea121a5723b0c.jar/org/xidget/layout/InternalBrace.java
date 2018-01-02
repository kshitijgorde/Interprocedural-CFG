// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout;

import org.xidget.ifeature.IWidgetFeature;
import org.xidget.ifeature.ILayoutFeature;
import org.xidget.IXidget;

public class InternalBrace extends ComputeNode
{
    private String name;
    private IXidget xidget;
    private ILayoutFeature.Side side;
    
    public InternalBrace(final String name, final IXidget xidget, final IComputeNode computeNode, final ILayoutFeature.Side side) {
        this.name = name;
        this.xidget = xidget;
        this.side = side;
        this.addDependency(computeNode);
    }
    
    @Override
    public void setValue(final float n) {
        final Bounds defaultBounds = this.xidget.getFeature(IWidgetFeature.class).getDefaultBounds();
        switch (this.side) {
            case top: {
                super.setValue(n - defaultBounds.height);
                break;
            }
            case bottom: {
                super.setValue(n + defaultBounds.height);
                break;
            }
            case left: {
                super.setValue(n - defaultBounds.width);
                break;
            }
            case right: {
                super.setValue(n + defaultBounds.width);
                break;
            }
        }
    }
    
    @Override
    public String toString() {
        return String.format("%d. %s %s (%s) %s <- %s", this.getID(), this.xidget, this.name, this.printValue(), this.side.name(), this.printDependencies());
    }
}
