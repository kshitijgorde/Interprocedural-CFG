// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd.check;

import org.xmodel.Xlate;
import org.xmodel.IModelObject;

public class StringCheck extends AbstractCheck
{
    private int a;
    private int _;
    
    public StringCheck(final IModelObject modelObject) {
        super(modelObject);
        final IModelObject firstChild = modelObject.getFirstChild("min");
        this.a = ((firstChild != null) ? Xlate.get(firstChild, 0) : -1);
        final IModelObject firstChild2 = modelObject.getFirstChild("max");
        this._ = ((firstChild2 != null) ? Xlate.get(firstChild2, 0) : -1);
    }
    
    @Override
    protected boolean validateImpl(final IModelObject modelObject) {
        final String value = Xlate.get(modelObject, "");
        return (this.a < 0 || value.length() >= this.a) && (this._ < 0 || value.length() <= this._);
    }
}
