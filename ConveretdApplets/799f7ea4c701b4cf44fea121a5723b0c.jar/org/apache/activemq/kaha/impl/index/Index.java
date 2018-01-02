// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index;

import org.apache.activemq.kaha.Marshaller;
import org.apache.activemq.kaha.StoreEntry;
import java.io.IOException;

public interface Index
{
    void clear() throws IOException;
    
    boolean containsKey(final Object p0) throws IOException;
    
    StoreEntry remove(final Object p0) throws IOException;
    
    void store(final Object p0, final StoreEntry p1) throws IOException;
    
    StoreEntry get(final Object p0) throws IOException;
    
    boolean isTransient();
    
    void load();
    
    void unload() throws IOException;
    
    void setKeyMarshaller(final Marshaller p0);
    
    int getSize();
    
    void delete() throws IOException;
}
