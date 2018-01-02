// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.diff;

import org.xmodel.IChangeSet;
import org.xmodel.IModelObject;

public interface IXmlDiffer
{
    void setMatcher(final IXmlMatcher p0);
    
    IXmlMatcher getMatcher();
    
    boolean diff(final IModelObject p0, final IModelObject p1, final IChangeSet p2);
    
    boolean diffAndApply(final IModelObject p0, final IModelObject p1);
    
    boolean diffAttributes(final IModelObject p0, final IModelObject p1, final IChangeSet p2);
    
    boolean diffChildren(final IModelObject p0, final IModelObject p1, final IChangeSet p2);
}
