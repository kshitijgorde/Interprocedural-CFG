// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import java.util.List;
import java.util.HashSet;
import java.util.Set;
import org.xmodel.util.Fifo;
import java.util.Iterator;

public class PrecedingIterator implements Iterator<IModelObject>
{
    private Fifo<IModelObject> B;
    private Set<IModelObject> A;
    
    public PrecedingIterator(final IModelObject modelObject) {
        this.B = new Fifo<IModelObject>();
        this.A(modelObject);
        this.A = new HashSet<IModelObject>();
    }
    
    @Override
    public boolean hasNext() {
        while (!this.B.empty() && !this.shouldTraverse(this.B.peek())) {
            this.B.pop();
        }
        return !this.B.empty();
    }
    
    @Override
    public IModelObject next() {
        final IModelObject modelObject = this.B.pop();
        this.A(modelObject);
        return modelObject;
    }
    
    private void A(final IModelObject modelObject) {
        if (!this.B(modelObject)) {
            final IModelObject parent = modelObject.getParent();
            if (parent != null) {
                this.B(parent);
            }
        }
    }
    
    private boolean B(final IModelObject modelObject) {
        final IModelObject parent = modelObject.getParent();
        if (parent != null) {
            final List<IModelObject> children = parent.getChildren();
            final int n = children.indexOf(modelObject) - 1;
            if (n >= 0) {
                this.B.push(children.get(n));
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
    protected boolean shouldTraverse(final IModelObject modelObject) {
        if (modelObject.getReferent() != modelObject) {
            if (this.A.contains(modelObject)) {
                return false;
            }
            this.A.add(modelObject);
        }
        return true;
    }
}
