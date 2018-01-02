// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd.check;

import org.xmodel.Xlate;
import org.xmodel.IModelObject;

public class BooleanCheck extends AbstractCheck
{
    public BooleanCheck(final IModelObject modelObject) {
        super(modelObject);
    }
    
    @Override
    protected boolean validateImpl(final IModelObject modelObject) {
        final String value = Xlate.get(modelObject, "");
        return value.equals("true") || value.equals("false");
    }
}
