// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.record;

import org.xmodel.IChangeSet;
import org.xmodel.IModelObjectFactory;
import org.xmodel.ModelAlgorithms;
import org.xmodel.IModelObject;
import org.xmodel.IPath;

public class ClearAttributeRecord extends AbstractChangeRecord
{
    String I;
    
    public ClearAttributeRecord(final IPath path, final String i) {
        super(path);
        this.I = i;
    }
    
    @Override
    public int getType() {
        return 1;
    }
    
    @Override
    public String getAttributeName() {
        return this.I;
    }
    
    public void applyChange(final IModelObject modelObject) {
        if (this.A == null) {
            return;
        }
        ModelAlgorithms.createPathSubtree(modelObject, this.A, null, null);
        this.A.queryFirst(modelObject).removeAttribute(this.I);
    }
    
    @Override
    public String toString() {
        return "clear: attribute: " + this.I + ", path: " + this.A;
    }
}
