// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

public interface WritableSingletonModel extends SingletonModel
{
    boolean canSet(final boolean p0);
    
    void set(final Object p0);
    
    void touched();
    
    void enableModelEvents(final boolean p0);
}
