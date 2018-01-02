// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl;

import org.apache.activemq.kaha.impl.data.RedoListener;
import java.io.IOException;
import org.apache.activemq.kaha.StoreLocation;
import org.apache.activemq.kaha.Marshaller;

public interface DataManager
{
    String getName();
    
    Object readItem(final Marshaller p0, final StoreLocation p1) throws IOException;
    
    StoreLocation storeDataItem(final Marshaller p0, final Object p1) throws IOException;
    
    StoreLocation storeRedoItem(final Object p0) throws IOException;
    
    void updateItem(final StoreLocation p0, final Marshaller p1, final Object p2) throws IOException;
    
    void recoverRedoItems(final RedoListener p0) throws IOException;
    
    void close() throws IOException;
    
    void force() throws IOException;
    
    boolean delete() throws IOException;
    
    void addInterestInFile(final int p0) throws IOException;
    
    void removeInterestInFile(final int p0) throws IOException;
    
    void consolidateDataFiles() throws IOException;
    
    Marshaller getRedoMarshaller();
    
    void setRedoMarshaller(final Marshaller p0);
}
