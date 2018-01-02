// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import java.util.List;
import java.util.HashSet;
import java.util.Set;
import org.xmodel.util.Fifo;
import java.util.Iterator;

public class FollowingIterator implements Iterator<IModelObject>
{
    private Fifo<IModelObject> B;
    private Set<IModelObject> A;
    
    public FollowingIterator(final IModelObject modelObject) {
        this.B = new Fifo<IModelObject>();
        this.B(modelObject);
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
        this.B(modelObject);
        return modelObject;
    }
    
    private void B(final IModelObject modelObject) {
        if (!this.A(modelObject)) {
            final IModelObject parent = modelObject.getParent();
            if (parent != null) {
                this.A(parent);
            }
        }
    }
    
    private boolean A(final IModelObject modelObject) {
        final IModelObject parent = modelObject.getParent();
        if (parent != null) {
            final List<IModelObject> children = parent.getChildren();
            final int n = children.indexOf(modelObject) + 1;
            if (n < children.size()) {
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
