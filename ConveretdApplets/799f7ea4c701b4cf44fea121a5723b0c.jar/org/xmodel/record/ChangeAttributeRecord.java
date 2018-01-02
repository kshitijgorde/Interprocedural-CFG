// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.record;

import org.xmodel.IChangeSet;
import org.xmodel.IModelObjectFactory;
import org.xmodel.ModelAlgorithms;
import org.xmodel.IModelObject;
import org.xmodel.IPath;

public class ChangeAttributeRecord extends AbstractChangeRecord
{
    String G;
    Object H;
    
    public ChangeAttributeRecord(final IPath path, final String g) {
        super(path);
        this.G = g;
    }
    
    public ChangeAttributeRecord(final IPath path, final String g, final Object h) {
        super(path);
        this.G = g;
        this.H = h;
    }
    
    @Override
    public int getType() {
        return 0;
    }
    
    @Override
    public String getAttributeName() {
        return this.G;
    }
    
    @Override
    public Object getAttributeValue() {
        return this.H;
    }
    
    public void applyChange(final IModelObject modelObject) {
        if (this.A == null) {
            return;
        }
        ModelAlgorithms.createPathSubtree(modelObject, this.A, null, null);
        this.A.queryFirst(modelObject).setAttribute(this.G, this.H);
    }
    
    @Override
    public String toString() {
        return "set: attribute: " + ((this.G.length() > 0) ? this.G : "text()") + ", value: " + this.H + ", path: " + this.A;
    }
}
