// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.dependency;

import org.xmodel.IModelObject;

public class AncestorDependency implements IDependency
{
    @Override
    public boolean evaluate(final Object o, final Object o2) {
        final IModelObject modelObject = (IModelObject)o;
        final IModelObject modelObject2 = (IModelObject)o2;
        for (IModelObject modelObject3 = modelObject.getParent(); modelObject3 != null; modelObject3 = modelObject3.getParent()) {
            if (modelObject3.equals(modelObject2)) {
                return true;
            }
        }
        return false;
    }
}
