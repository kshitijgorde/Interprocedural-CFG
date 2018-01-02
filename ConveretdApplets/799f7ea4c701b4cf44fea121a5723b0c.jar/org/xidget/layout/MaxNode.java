// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout;

import java.util.Iterator;
import org.xidget.Log;

public class MaxNode extends ComputeNode
{
    @Override
    public void update() {
        if (!this.hasValue()) {
            float value = Float.NEGATIVE_INFINITY;
            for (final IComputeNode computeNode : this.getDependencies()) {
                if (computeNode.hasValue() && value < computeNode.getValue()) {
                    value = computeNode.getValue();
                }
            }
            if (value != Float.NEGATIVE_INFINITY) {
                this.setValue(value);
            }
            Log.printf("layout", "update: %s\n", this.toString());
        }
    }
}
