// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external;

import org.xmodel.IChangeSet;

public interface ITransaction
{
    boolean lock(final int p0);
    
    void unlock();
    
    boolean commit();
    
    boolean rollback();
    
    IChangeSet getChanges();
}
