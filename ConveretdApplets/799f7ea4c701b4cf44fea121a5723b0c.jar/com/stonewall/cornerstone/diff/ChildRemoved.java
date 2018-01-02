// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.diff;

import org.xmodel.record.AddChildBoundRecord;
import org.xmodel.IBoundChangeRecord;
import org.xmodel.IModelObject;
import org.xmodel.record.RemoveChildBoundRecord;

class ChildRemoved extends RemoveChildBoundRecord implements IAnnotatedRecord
{
    ChildRemoved(final IModelObject target, final IModelObject child) {
        super(target, child);
    }
    
    ChildRemoved(final IModelObject target, final IModelObject child, final int index) {
        super(target, child, index);
    }
    
    @Override
    public int getType() {
        return 3;
    }
    
    @Override
    public IBoundChangeRecord createUndoRecord() {
        return new AddChildBoundRecord(this.getBoundObject(), this.getChild());
    }
    
    @Override
    public void annotate() {
        final Annotation an = new Annotation(new String[0]);
        an.setAction(Annotation.Action.removed);
        an.apply(this.getChild());
    }
}
