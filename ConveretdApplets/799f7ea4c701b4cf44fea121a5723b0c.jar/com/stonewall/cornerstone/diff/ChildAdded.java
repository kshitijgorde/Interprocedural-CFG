// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.diff;

import org.xmodel.IBoundChangeRecord;
import org.xmodel.IModelObject;
import org.xmodel.record.AddChildBoundRecord;

class ChildAdded extends AddChildBoundRecord implements IAnnotatedRecord
{
    ChildAdded(final IModelObject element, final IModelObject child, final int index) {
        super(element, child, index);
    }
    
    ChildAdded(final IModelObject element, final IModelObject child) {
        super(element, child);
    }
    
    @Override
    public int getType() {
        return 2;
    }
    
    @Override
    public IBoundChangeRecord createUndoRecord() {
        return null;
    }
    
    @Override
    public void annotate() {
        final Annotation an = new Annotation(new String[0]);
        an.setAction(Annotation.Action.added);
        an.apply(this.getChild());
    }
}
