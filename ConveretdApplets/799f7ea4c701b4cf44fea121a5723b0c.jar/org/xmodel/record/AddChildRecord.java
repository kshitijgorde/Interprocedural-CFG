// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.record;

import org.xmodel.IChangeSet;
import org.xmodel.IModelObjectFactory;
import org.xmodel.ModelAlgorithms;
import org.xmodel.IPath;
import org.xmodel.IModelObject;

public class AddChildRecord extends AbstractChangeRecord
{
    IModelObject F;
    int E;
    
    public AddChildRecord(final IPath path, final IModelObject f) {
        super(path);
        this.F = f;
        this.E = -1;
    }
    
    public AddChildRecord(final IPath path, final IModelObject f, final int e) {
        super(path);
        this.F = f;
        this.E = e;
    }
    
    @Override
    public int getType() {
        return 2;
    }
    
    @Override
    public IModelObject getChild() {
        return super.getChild();
    }
    
    @Override
    public int getIndex() {
        return this.E;
    }
    
    public void applyChange(final IModelObject modelObject) {
        if (this.A == null) {
            return;
        }
        ModelAlgorithms.createPathSubtree(modelObject, this.A, null, null);
        final IModelObject queryFirst = this.A.queryFirst(modelObject);
        if (this.E < 0) {
            queryFirst.addChild(this.F);
        }
        else {
            queryFirst.addChild(this.F, this.E);
        }
    }
    
    @Override
    public String toString() {
        return "add child: " + this.F + ", index: " + this.E + ", path: " + this.A;
    }
}
