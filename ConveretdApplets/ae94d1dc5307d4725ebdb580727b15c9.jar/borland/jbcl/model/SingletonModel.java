// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

public interface SingletonModel
{
    Object get();
    
    void addModelListener(final SingletonModelListener p0);
    
    void removeModelListener(final SingletonModelListener p0);
}
