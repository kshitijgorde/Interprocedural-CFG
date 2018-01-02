// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.record;

import org.xmodel.IPath;
import org.xmodel.ModelAlgorithms;
import org.xmodel.IChangeRecord;
import org.xmodel.IBoundChangeRecord;
import org.xmodel.IModelObject;

public class RemoveChildBoundRecord extends AbstractBoundRecord
{
    IModelObject N;
    int M;
    
    public RemoveChildBoundRecord(final IModelObject modelObject, final IModelObject n) {
        super(modelObject);
        this.N = n;
        this.M = -1;
    }
    
    public RemoveChildBoundRecord(final IModelObject modelObject, final IModelObject n, final int m) {
        super(modelObject);
        this.N = n;
        this.M = m;
    }
    
    public IBoundChangeRecord createUndoRecord() {
        if (this.M < 0) {
            return new AddChildBoundRecord(this.J, this.N);
        }
        return new AddChildBoundRecord(this.J, this.N, this.M);
    }
    
    @Override
    public IChangeRecord createUnboundRecord() {
        IModelObject modelObject = this.N;
        if (modelObject == null) {
            modelObject = this.getBoundObject().getChild(this.M);
        }
        return new RemoveChildRecord(this.getPath(), ModelAlgorithms.createRelativePath(this.getBoundObject(), modelObject));
    }
    
    @Override
    public IChangeRecord createUnboundRecord(final IModelObject modelObject) {
        IModelObject modelObject2 = this.N;
        if (modelObject2 == null) {
            modelObject2 = this.getBoundObject().getChild(this.M);
        }
        return new RemoveChildRecord(this.getRelativePath(modelObject), ModelAlgorithms.createRelativePath(this.getBoundObject(), modelObject2));
    }
    
    @Override
    public int getType() {
        return 3;
    }
    
    @Override
    public IModelObject getChild() {
        return this.N;
    }
    
    @Override
    public int getIndex() {
        return this.M;
    }
    
    @Override
    public void applyChange() {
        if (this.M < 0) {
            this.getBoundObject().removeChild(this.N);
        }
        else if (this.N != null) {
            final IModelObject boundObject = this.getBoundObject();
            if (boundObject.getChild(this.M) == this.N) {
                boundObject.removeChild(this.M);
            }
            else {
                boundObject.removeChild(this.N);
            }
        }
        else {
            this.getBoundObject().removeChild(this.M);
        }
    }
    
    @Override
    public String toString() {
        final IModelObject boundObject = this.getBoundObject();
        if (this.M < 0) {
            return "remove: child: " + this.N + " parent: " + boundObject;
        }
        return "remove: child: " + this.N + ", index: " + this.M + " parent: " + boundObject;
    }
}
