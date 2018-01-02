// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout;

import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import org.xidget.ifeature.ILayoutFeature;

public class AnchorNode extends ComputeNode
{
    private IComputeNode node1;
    private IComputeNode node2;
    private ILayoutFeature.Side side;
    private float fraction;
    private IModelObject fractionNode;
    private int offset;
    private boolean handle;
    
    public AnchorNode(final IComputeNode node1, final IComputeNode node2, final ILayoutFeature.Side side, final float fraction, final IModelObject fractionNode, final int offset) {
        this.node1 = node1;
        this.node2 = node2;
        this.fraction = fraction;
        this.fractionNode = fractionNode;
        this.offset = offset;
        this.side = side;
    }
    
    @Override
    public void addDependency(final IComputeNode computeNode) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void clearDependencies() {
        throw new UnsupportedOperationException();
    }
    
    public void setHandle(final boolean handle) {
        this.handle = handle;
    }
    
    @Override
    public boolean hasXHandle() {
        return (this.side == ILayoutFeature.Side.left || this.side == ILayoutFeature.Side.right) && this.handle;
    }
    
    @Override
    public boolean hasYHandle() {
        return (this.side == ILayoutFeature.Side.top || this.side == ILayoutFeature.Side.bottom) && this.handle;
    }
    
    public void setFraction(final float fraction) {
        this.fraction = fraction;
        if (this.fractionNode != null) {
            Xlate.set(this.fractionNode, fraction);
        }
    }
    
    public void setOffset(final int offset) {
        this.offset = offset;
    }
    
    @Override
    public void update() {
        final float value = this.node1.getValue();
        this.setValue((this.node2.getValue() - value) * this.fraction + value + this.offset);
    }
    
    @Override
    public String toString() {
        return String.format("%d. (%s) (%%%2.1f, %+d) <- %s", this.getID(), this.printValue(), this.fraction, this.offset, this.printDependencies());
    }
}
