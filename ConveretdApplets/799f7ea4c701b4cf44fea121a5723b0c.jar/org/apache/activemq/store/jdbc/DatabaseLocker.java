// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.jdbc;

import java.io.IOException;
import org.apache.activemq.Service;

public interface DatabaseLocker extends Service
{
    void setPersistenceAdapter(final JDBCPersistenceAdapter p0) throws IOException;
    
    boolean keepAlive();
    
    void setLockAcquireSleepInterval(final long p0);
}
