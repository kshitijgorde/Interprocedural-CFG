// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout;

import java.util.List;
import org.xidget.Log;

public class SubtractNode extends ComputeNode
{
    public SubtractNode(final IComputeNode computeNode, final IComputeNode computeNode2) {
        super.addDependency(computeNode2);
        super.addDependency(computeNode);
    }
    
    @Override
    public void addDependency(final IComputeNode computeNode) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void clearDependencies() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void update() {
        if (!this.hasValue()) {
            final List<IComputeNode> dependencies = this.getDependencies();
            if (dependencies.get(0).hasValue() && dependencies.get(1).hasValue()) {
                this.setValue(dependencies.get(0).getValue() - dependencies.get(1).getValue());
            }
            Log.printf("layout", "update: %s\n", this.toString());
        }
    }
    
    @Override
    public String toString() {
        return String.format("%d. (%s) subtract <- %s", this.getID(), this.printValue(), this.printDependencies());
    }
}
