// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import java.util.Iterator;

public class AnnotatingChangeSet extends ChangeSet
{
    private IModelObjectFactory B;
    
    public AnnotatingChangeSet() {
        this.B = new ModelObjectFactory();
    }
    
    @Override
    public void applyChanges() {
        for (final IBoundChangeRecord boundChangeRecord : this.getRecords()) {
            final IModelObject boundObject = boundChangeRecord.getBoundObject();
            switch (boundChangeRecord.getType()) {
                default: {
                    continue;
                }
                case 2: {
                    boundChangeRecord.getChild().addChild(this.B.createObject(null, "diff:insert"), 0);
                    boundChangeRecord.applyChange();
                    continue;
                }
                case 3: {
                    boundChangeRecord.getChild().addChild(this.B.createObject(null, "diff:delete"), 0);
                    continue;
                }
                case 0: {
                    final String attributeName = boundChangeRecord.getAttributeName();
                    final IModelObject object = this.B.createObject(null, "diff:change");
                    object.setAttribute("attribute", attributeName);
                    object.setAttribute("from", boundObject.getAttribute(attributeName));
                    boundObject.addChild(object, 0);
                    boundChangeRecord.applyChange();
                    continue;
                }
                case 1: {
                    final String attributeName2 = boundChangeRecord.getAttributeName();
                    final IModelObject object2 = this.B.createObject(null, "diff:clear");
                    object2.setAttribute("attribute", attributeName2);
                    object2.setAttribute("from", boundObject.getAttribute(attributeName2));
                    boundObject.addChild(object2, 0);
                    boundChangeRecord.applyChange();
                    continue;
                }
            }
        }
    }
}
