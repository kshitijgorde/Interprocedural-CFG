// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.dependency;

import org.xmodel.IModelObject;

public class DecendentDependency implements IDependency
{
    @Override
    public boolean evaluate(final Object o, final Object o2) {
        final IModelObject modelObject = (IModelObject)o;
        for (IModelObject modelObject2 = ((IModelObject)o2).getParent(); modelObject2 != null; modelObject2 = modelObject2.getParent()) {
            if (modelObject2.equals(modelObject)) {
                return true;
            }
        }
        return false;
    }
}
