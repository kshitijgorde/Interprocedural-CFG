// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.diff;

import org.xmodel.IBoundChangeRecord;
import org.xmodel.IModelObject;
import org.xmodel.record.AbstractBoundRecord;

class AttrRemoved extends AbstractBoundRecord implements IAnnotatedRecord
{
    final String attrName;
    
    AttrRemoved(final IModelObject element, final String attrName) {
        super(element);
        this.attrName = attrName;
    }
    
    @Override
    public int getType() {
        return 1;
    }
    
    public IBoundChangeRecord createUndoRecord() {
        return null;
    }
    
    @Override
    public void applyChange() {
    }
    
    @Override
    public void annotate() {
        final Annotation an = new Annotation(new String[0]);
        an.setAction(Annotation.Action.removed);
        an.add(Annotation.Key.attribute, this.attrName);
        an.apply(this.getBoundObject());
    }
}
