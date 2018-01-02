// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.record;

import org.xmodel.IModelObject;
import org.xmodel.IPath;
import org.xmodel.IChangeRecord;

public abstract class AbstractChangeRecord implements IChangeRecord
{
    IPath A;
    
    protected AbstractChangeRecord() {
    }
    
    public AbstractChangeRecord(final IPath a) {
        this.A = a;
    }
    
    @Override
    public boolean isType(final int n) {
        return this.getType() == n;
    }
    
    public IPath getPath() {
        return this.A;
    }
    
    public String getName() {
        return null;
    }
    
    @Override
    public String getAttributeName() {
        return null;
    }
    
    public Object getAttributeValue() {
        return null;
    }
    
    @Override
    public IModelObject getChild() {
        return null;
    }
    
    public int getIndex() {
        return -1;
    }
}
