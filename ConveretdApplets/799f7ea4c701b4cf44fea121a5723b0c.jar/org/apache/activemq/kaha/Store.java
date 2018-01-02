// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha;

import java.util.Set;
import java.io.IOException;

public interface Store
{
    public static final String DEFAULT_CONTAINER_NAME = "kaha";
    public static final Marshaller BYTES_MARSHALLER = new BytesMarshaller();
    public static final Marshaller OBJECT_MARSHALLER = new ObjectMarshaller();
    public static final Marshaller STRING_MARSHALLER = new StringMarshaller();
    public static final Marshaller COMMAND_MARSHALLER = new CommandMarshaller();
    public static final Marshaller MESSAGEID_MARSHALLER = new MessageIdMarshaller();
    
    void close() throws IOException;
    
    void force() throws IOException;
    
    void clear() throws IOException;
    
    boolean delete() throws IOException;
    
    boolean doesMapContainerExist(final Object p0) throws IOException;
    
    boolean doesMapContainerExist(final Object p0, final String p1) throws IOException;
    
    MapContainer getMapContainer(final Object p0) throws IOException;
    
    MapContainer getMapContainer(final Object p0, final String p1) throws IOException;
    
    MapContainer getMapContainer(final Object p0, final String p1, final boolean p2) throws IOException;
    
    void deleteMapContainer(final Object p0) throws IOException;
    
    void deleteMapContainer(final Object p0, final String p1) throws IOException;
    
    void deleteMapContainer(final ContainerId p0) throws IOException;
    
    Set<ContainerId> getMapContainerIds() throws IOException;
    
    boolean doesListContainerExist(final Object p0) throws IOException;
    
    boolean doesListContainerExist(final Object p0, final String p1) throws IOException;
    
    ListContainer getListContainer(final Object p0) throws IOException;
    
    ListContainer getListContainer(final Object p0, final String p1) throws IOException;
    
    ListContainer getListContainer(final Object p0, final String p1, final boolean p2) throws IOException;
    
    void deleteListContainer(final Object p0) throws IOException;
    
    void deleteListContainer(final Object p0, final String p1) throws IOException;
    
    void deleteListContainer(final ContainerId p0) throws IOException;
    
    Set<ContainerId> getListContainerIds() throws IOException;
    
    long getMaxDataFileLength();
    
    void setMaxDataFileLength(final long p0);
    
    boolean isInitialized();
    
    long size();
    
    boolean isPersistentIndex();
    
    void setPersistentIndex(final boolean p0);
    
    String getDefaultContainerName();
    
    void setDefaultContainerName(final String p0);
    
    void initialize() throws IOException;
}
