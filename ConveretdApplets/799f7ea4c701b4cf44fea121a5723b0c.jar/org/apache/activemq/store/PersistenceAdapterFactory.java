// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store;

import java.io.IOException;

public interface PersistenceAdapterFactory
{
    PersistenceAdapter createPersistenceAdapter() throws IOException;
}
