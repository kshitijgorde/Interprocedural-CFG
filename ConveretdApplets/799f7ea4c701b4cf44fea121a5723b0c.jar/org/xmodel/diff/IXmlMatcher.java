// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.diff;

import java.util.List;
import org.xmodel.IChangeSet;
import org.xmodel.IModelObject;

public interface IXmlMatcher
{
    void startDiff(final IModelObject p0, final IModelObject p1, final IChangeSet p2);
    
    void endDiff(final IModelObject p0, final IModelObject p1, final IChangeSet p2);
    
    void enterDiff(final IModelObject p0, final IModelObject p1, final IChangeSet p2);
    
    void exitDiff(final IModelObject p0, final IModelObject p1, final IChangeSet p2);
    
    boolean shouldDiff(final IModelObject p0, final String p1, final boolean p2);
    
    boolean shouldDiff(final IModelObject p0, final boolean p1);
    
    boolean isList(final IModelObject p0);
    
    int findMatch(final List<IModelObject> p0, final IModelObject p1);
    
    boolean isMatch(final IModelObject p0, final IModelObject p1);
}
