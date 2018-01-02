// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import borland.jbcl.model.SingletonViewManager;
import borland.jbcl.model.WritableSingletonModel;
import borland.jbcl.model.SingletonModelListener;
import borland.jbcl.model.SingletonModel;

public interface SingletonView
{
    SingletonModel getModel();
    
    void setModel(final SingletonModel p0);
    
    void addModelListener(final SingletonModelListener p0);
    
    void removeModelListener(final SingletonModelListener p0);
    
    WritableSingletonModel getWriteModel();
    
    boolean isReadOnly();
    
    void setReadOnly(final boolean p0);
    
    SingletonViewManager getViewManager();
    
    void setViewManager(final SingletonViewManager p0);
}
