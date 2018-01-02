// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.record;

import org.xmodel.IChangeSet;
import org.xmodel.IModelObjectFactory;
import org.xmodel.ModelAlgorithms;
import org.xmodel.IPath;
import org.xmodel.IModelObject;

public class RemoveChildRecord extends AbstractChangeRecord
{
    int B;
    IModelObject D;
    IPath C;
    
    public RemoveChildRecord(final IPath path, final IModelObject d) {
        super(path);
        this.D = d;
        this.B = -1;
    }
    
    public RemoveChildRecord(final IPath path, final int b) {
        super(path);
        this.D = null;
        this.B = b;
    }
    
    public RemoveChildRecord(final IPath path, final IPath c) {
        super(path);
        this.C = c;
        this.D = null;
        this.B = -1;
    }
    
    @Override
    public int getType() {
        return 3;
    }
    
    @Override
    public IModelObject getChild() {
        return super.getChild();
    }
    
    @Override
    public int getIndex() {
        return this.B;
    }
    
    public void applyChange(final IModelObject modelObject) {
        if (this.A == null) {
            return;
        }
        ModelAlgorithms.createPathSubtree(modelObject, this.A, null, null);
        final IModelObject queryFirst = this.A.queryFirst(modelObject);
        if (this.B < 0) {
            if (this.D == null) {
                final IModelObject queryFirst2 = this.C.queryFirst(queryFirst);
                if (queryFirst2 != null) {
                    queryFirst.removeChild(queryFirst2);
                }
            }
            else {
                queryFirst.removeChild(this.D);
            }
        }
        else {
            queryFirst.removeChild(this.B);
        }
    }
    
    @Override
    public String toString() {
        if (this.B < 0) {
            return "remove: child: " + this.D + ", path: " + this.A;
        }
        return "remove: child: " + this.D + ", index: " + this.B + ", path: " + this.A;
    }
}
