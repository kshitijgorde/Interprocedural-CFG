// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.data;

import java.io.DataInput;
import org.apache.activemq.kaha.Marshaller;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.apache.activemq.kaha.StoreLocation;
import org.apache.activemq.util.DataByteArrayInputStream;

public final class SyncDataFileReader
{
    private DataManagerImpl dataManager;
    private DataByteArrayInputStream dataIn;
    
    SyncDataFileReader(final DataManagerImpl fileManager) {
        this.dataManager = fileManager;
        this.dataIn = new DataByteArrayInputStream();
    }
    
    public synchronized byte readDataItemSize(final DataItem item) throws IOException {
        final RandomAccessFile file = this.dataManager.getDataFile(item).getRandomAccessFile();
        file.seek(item.getOffset());
        final byte rc = file.readByte();
        item.setSize(file.readInt());
        return rc;
    }
    
    public synchronized Object readItem(final Marshaller marshaller, final StoreLocation item) throws IOException {
        final RandomAccessFile file = this.dataManager.getDataFile(item).getRandomAccessFile();
        final byte[] data = new byte[item.getSize()];
        file.seek(item.getOffset() + 5L);
        file.readFully(data);
        this.dataIn.restart(data);
        return marshaller.readPayload(this.dataIn);
    }
}
