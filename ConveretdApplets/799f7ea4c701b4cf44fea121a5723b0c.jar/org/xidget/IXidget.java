// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget;

import org.xidget.config.TagException;
import org.xmodel.IModelObject;
import org.xidget.config.TagProcessor;
import java.util.List;

public interface IXidget extends IFeatured
{
    IXidget getParent();
    
    List<IXidget> getChildren();
    
    void addChild(final IXidget p0);
    
    void addChild(final int p0, final IXidget p1);
    
    void removeChild(final IXidget p0);
    
    boolean startConfig(final TagProcessor p0, final IXidget p1, final IModelObject p2) throws TagException;
    
    void endConfig(final TagProcessor p0, final IModelObject p1) throws TagException;
    
    IModelObject getConfig();
    
    void createWidget();
    
    void destroyWidget();
}
