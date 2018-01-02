// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd.check;

import org.xmodel.IModelObject;

public class AnyCheck extends ConstraintCheck
{
    private int G;
    
    public AnyCheck(final IModelObject modelObject) {
        super(modelObject);
    }
    
    public boolean validateOnce(final IModelObject modelObject, final int n, final int n2) {
        this.G = n2 - n;
        return true;
    }
    
    @Override
    public int anyCount() {
        return this.G;
    }
}
