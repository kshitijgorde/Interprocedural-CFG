// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.record;

import org.xmodel.IChangeRecord;
import org.xmodel.IBoundChangeRecord;
import org.xmodel.IModelObject;

public class ClearAttributeBoundRecord extends AbstractBoundRecord
{
    String Q;
    
    public ClearAttributeBoundRecord(final IModelObject modelObject, final String q) {
        super(modelObject);
        this.Q = q;
    }
    
    public IBoundChangeRecord createUndoRecord() {
        final Object attribute = this.J.getAttribute(this.Q);
        if (attribute == null) {
            return new ChangeAttributeBoundRecord(this.J, this.Q);
        }
        return new ChangeAttributeBoundRecord(this.J, this.Q, attribute);
    }
    
    @Override
    public IChangeRecord createUnboundRecord() {
        return new ClearAttributeRecord(this.getPath(), this.Q);
    }
    
    @Override
    public IChangeRecord createUnboundRecord(final IModelObject modelObject) {
        return new ClearAttributeRecord(this.getRelativePath(modelObject), this.Q);
    }
    
    @Override
    public int getType() {
        return 1;
    }
    
    @Override
    public String getAttributeName() {
        return this.Q;
    }
    
    @Override
    public void applyChange() {
        this.getBoundObject().removeAttribute(this.Q);
    }
    
    @Override
    public String toString() {
        return "clear: attribute: " + this.Q + ", object: " + this.getBoundObject();
    }
}
