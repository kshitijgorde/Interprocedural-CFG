// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout;

public class ConstantNode extends ComputeNode
{
    public ConstantNode(final int n) {
        this.setValue(n);
    }
    
    public ConstantNode(final float value) {
        this.setValue(value);
    }
    
    @Override
    public void reset() {
    }
    
    @Override
    public String toString() {
        return String.format("%d. (%s) <- %s", this.getID(), this.printValue(), this.printDependencies());
    }
}
