// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import org.xmodel.external.IExternalReference;
import org.xml.sax.Attributes;

public interface IModelObjectFactory
{
    IModelObject createObject(final IModelObject p0, final String p1);
    
    IModelObject createObject(final IModelObject p0, final Attributes p1, final String p2);
    
    IModelObject createClone(final IModelObject p0);
    
    IExternalReference createExternalObject(final IModelObject p0, final String p1);
}
