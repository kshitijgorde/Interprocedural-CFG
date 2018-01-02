// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xml;

import org.xmodel.IModelObject;

public interface ITransform
{
    void transform(final IModelObject p0, final IModelObject p1);
    
    void inverseTransform(final IModelObject p0, final IModelObject p1);
    
    IModelObject transform(final IModelObject p0);
    
    IModelObject inverseTransform(final IModelObject p0);
    
    boolean hasInverse();
}
