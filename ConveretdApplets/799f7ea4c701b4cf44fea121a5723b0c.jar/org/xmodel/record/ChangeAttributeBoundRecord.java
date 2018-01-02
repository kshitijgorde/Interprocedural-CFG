// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.record;

import org.xmodel.IChangeRecord;
import org.xmodel.IBoundChangeRecord;
import org.xmodel.IModelObject;

public class ChangeAttributeBoundRecord extends AbstractBoundRecord
{
    String K;
    Object L;
    
    public ChangeAttributeBoundRecord(final IModelObject modelObject, final String k) {
        super(modelObject);
        this.K = k;
    }
    
    public ChangeAttributeBoundRecord(final IModelObject modelObject, final String k, final Object l) {
        super(modelObject);
        this.K = k;
        this.L = l;
    }
    
    public IBoundChangeRecord createUndoRecord() {
        final Object attribute = this.J.getAttribute(this.K);
        if (attribute == null) {
            return new ClearAttributeBoundRecord(this.J, this.K);
        }
        return new ChangeAttributeBoundRecord(this.J, this.K, attribute);
    }
    
    @Override
    public IChangeRecord createUnboundRecord() {
        if (this.L == null) {
            return new ChangeAttributeRecord(this.getPath(), this.K);
        }
        return new ChangeAttributeRecord(this.getPath(), this.K, this.L);
    }
    
    @Override
    public IChangeRecord createUnboundRecord(final IModelObject modelObject) {
        if (this.L == null) {
            return new ChangeAttributeRecord(this.getRelativePath(modelObject), this.K);
        }
        return new ChangeAttributeRecord(this.getRelativePath(modelObject), this.K, this.L);
    }
    
    @Override
    public int getType() {
        return 0;
    }
    
    @Override
    public String getAttributeName() {
        return this.K;
    }
    
    @Override
    public Object getAttributeValue() {
        return this.L;
    }
    
    @Override
    public void applyChange() {
        if (this.L == null) {
            this.getBoundObject().setAttribute(this.K);
        }
        else {
            this.getBoundObject().setAttribute(this.K, this.L);
        }
    }
    
    @Override
    public String toString() {
        return "set: attribute: " + ((this.K.length() > 0) ? this.K : "text()") + ", value: " + this.L + ", object: " + this.getBoundObject();
    }
}
