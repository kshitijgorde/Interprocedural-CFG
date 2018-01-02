// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.diff;

import org.xmodel.IBoundChangeRecord;
import org.xmodel.IModelObject;
import org.xmodel.record.AbstractBoundRecord;

class TextChanged extends AbstractBoundRecord implements IAnnotatedRecord
{
    final String text;
    
    TextChanged(final IModelObject target, final String text) {
        super(target);
        this.text = text;
    }
    
    @Override
    public int getType() {
        return 0;
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
        an.setAction(Annotation.Action.text);
        an.add(Annotation.Key.to, this.text);
        an.apply(this.getBoundObject());
    }
}
