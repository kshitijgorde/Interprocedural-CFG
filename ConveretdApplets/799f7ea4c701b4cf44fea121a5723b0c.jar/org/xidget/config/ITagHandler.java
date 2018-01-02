// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.config;

import org.xmodel.IModelObject;
import org.xidget.IFeatured;

public interface ITagHandler extends IFeatured
{
    boolean filter(final TagProcessor p0, final ITagHandler p1, final IModelObject p2);
    
    boolean enter(final TagProcessor p0, final ITagHandler p1, final IModelObject p2) throws TagException;
    
    void exit(final TagProcessor p0, final ITagHandler p1, final IModelObject p2) throws TagException;
}
