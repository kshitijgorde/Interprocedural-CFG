// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

public interface IAncestorListener
{
    void notifyAttach(final IModelObject p0, final IModelObject p1, final IModelObject p2, final IModelObject p3);
    
    void notifyDetach(final IModelObject p0, final IModelObject p1, final IModelObject p2);
}
