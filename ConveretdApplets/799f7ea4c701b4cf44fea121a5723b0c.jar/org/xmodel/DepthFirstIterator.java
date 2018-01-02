// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import java.util.ListIterator;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.Iterator;

public class DepthFirstIterator implements Iterator<IModelObject>
{
    Stack<IModelObject> A;
    Set<IModelObject> B;
    
    public DepthFirstIterator(final IModelObject modelObject) {
        (this.A = new Stack<IModelObject>()).push(modelObject);
        this.B = new HashSet<IModelObject>();
    }
    
    @Override
    public boolean hasNext() {
        while (!this.A.empty() && !this.shouldTraverse(this.A.peek())) {
            this.A.pop();
        }
        return !this.A.empty();
    }
    
    @Override
    public IModelObject next() {
        final IModelObject modelObject = this.A.pop();
        final List<IModelObject> children = modelObject.getChildren();
        final ListIterator<IModelObject> listIterator = children.listIterator(children.size());
        while (listIterator.hasPrevious()) {
            this.A.push(listIterator.previous());
        }
        return modelObject;
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
    protected boolean shouldTraverse(final IModelObject modelObject) {
        if (modelObject.getReferent() != modelObject) {
            if (this.B.contains(modelObject)) {
                return false;
            }
            this.B.add(modelObject);
        }
        return true;
    }
}
