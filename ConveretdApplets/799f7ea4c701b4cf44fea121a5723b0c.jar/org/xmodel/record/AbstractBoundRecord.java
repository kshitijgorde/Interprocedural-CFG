// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.record;

import org.xmodel.CanonicalPath;
import org.xmodel.ModelAlgorithms;
import org.xmodel.IPath;
import org.xmodel.IChangeRecord;
import org.xmodel.IModelObject;
import org.xmodel.IBoundChangeRecord;

public abstract class AbstractBoundRecord extends AbstractChangeRecord implements IBoundChangeRecord
{
    IModelObject J;
    
    public AbstractBoundRecord(final IModelObject j) {
        this.J = j;
    }
    
    @Override
    public IModelObject getBoundObject() {
        return this.J;
    }
    
    public IChangeRecord createUnboundRecord() {
        return null;
    }
    
    public IChangeRecord createUnboundRecord(final IModelObject modelObject) {
        return null;
    }
    
    @Override
    public IPath getPath() {
        return ModelAlgorithms.createIdentityPath(this.J);
    }
    
    protected IPath getRelativePath(final IModelObject modelObject) {
        final CanonicalPath relativePath = ModelAlgorithms.createRelativePath(modelObject, this.J);
        if (relativePath != null) {
            return relativePath;
        }
        return ModelAlgorithms.createIdentityPath(this.J);
    }
    
    public void applyChange(final IModelObject modelObject) {
        if (this.A != null) {
            final IModelObject j = this.J;
            this.J = this.A.queryFirst(modelObject);
            this.applyChange();
            this.J = j;
        }
    }
}
