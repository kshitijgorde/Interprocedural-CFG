// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.util.EventListener;

public interface SingletonModelListener extends EventListener
{
    void modelContentChanged(final SingletonModelEvent p0);
}
