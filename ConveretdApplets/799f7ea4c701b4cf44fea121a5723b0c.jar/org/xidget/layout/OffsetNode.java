// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout;

public class OffsetNode extends ComputeNode
{
    private String name;
    private int offset;
    
    public OffsetNode(final IComputeNode computeNode, final int n) {
        this("", computeNode, n);
    }
    
    public OffsetNode(final String name, final IComputeNode computeNode, final int offset) {
        this.name = name;
        this.offset = offset;
        this.addDependency(computeNode);
    }
    
    @Override
    public void setValue(final float n) {
        super.setValue(n + this.offset);
    }
    
    @Override
    public String toString() {
        return String.format("%d. %s (%s) %+d <- %s", this.getID(), this.name, this.printValue(), this.offset, this.printDependencies());
    }
}
