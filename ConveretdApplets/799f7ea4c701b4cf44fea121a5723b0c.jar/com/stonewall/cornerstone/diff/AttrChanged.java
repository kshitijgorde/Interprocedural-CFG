// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.diff;

import org.xmodel.IModelObject;

class AttrChanged extends AttrRemoved implements IAnnotatedRecord
{
    final String attrValue;
    
    AttrChanged(final IModelObject element, final String attrName, final String attrValue) {
        super(element, attrName);
        this.attrValue = attrValue;
    }
    
    @Override
    public int getType() {
        return 0;
    }
    
    @Override
    public void applyChange() {
    }
    
    @Override
    public void annotate() {
        final Annotation an = new Annotation(new String[0]);
        an.setAction(Annotation.Action.attr);
        an.add(Annotation.Key.attribute, this.attrName);
        an.add(Annotation.Key.to, this.attrValue);
        an.apply(this.getBoundObject());
    }
}
