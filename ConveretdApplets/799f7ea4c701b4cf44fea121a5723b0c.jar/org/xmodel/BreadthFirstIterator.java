// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import java.util.HashSet;
import java.util.Set;
import org.xmodel.util.Fifo;
import java.util.Iterator;

public class BreadthFirstIterator implements Iterator<IModelObject>
{
    private Fifo<IModelObject> B;
    private Set<IModelObject> A;
    
    public BreadthFirstIterator(final IModelObject modelObject) {
        (this.B = new Fifo<IModelObject>()).push(modelObject);
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
        final Iterator<IModelObject> iterator = modelObject.getChildren().iterator();
        while (iterator.hasNext()) {
            this.B.push(iterator.next());
        }
        return modelObject;
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
