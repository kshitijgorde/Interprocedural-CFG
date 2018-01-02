// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.record;

import org.xmodel.IChangeRecord;
import org.xmodel.IBoundChangeRecord;
import org.xmodel.IModelObject;

public class AddChildBoundRecord extends AbstractBoundRecord
{
    IModelObject P;
    int O;
    
    public AddChildBoundRecord(final IModelObject modelObject, final IModelObject p2) {
        super(modelObject);
        this.P = p2;
        this.O = -1;
    }
    
    public AddChildBoundRecord(final IModelObject modelObject, final IModelObject p3, final int o) {
        super(modelObject);
        this.P = p3;
        this.O = o;
    }
    
    public IBoundChangeRecord createUndoRecord() {
        return new RemoveChildBoundRecord(this.J, this.P);
    }
    
    @Override
    public IChangeRecord createUnboundRecord() {
        return new AddChildRecord(this.getPath(), this.P, this.O);
    }
    
    @Override
    public IChangeRecord createUnboundRecord(final IModelObject modelObject) {
        return new AddChildRecord(this.getRelativePath(modelObject), this.P, this.O);
    }
    
    @Override
    public int getType() {
        return 2;
    }
    
    @Override
    public IModelObject getChild() {
        return this.P;
    }
    
    @Override
    public int getIndex() {
        return this.O;
    }
    
    @Override
    public void applyChange() {
        if (this.O < 0) {
            this.getBoundObject().addChild(this.P);
        }
        else {
            this.getBoundObject().addChild(this.P, this.O);
        }
    }
    
    @Override
    public String toString() {
        return "add child: " + this.P + ", index: " + this.O + ", parent: " + this.getBoundObject();
    }
}
