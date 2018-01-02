// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.data;

import java.io.RandomAccessFile;
import org.apache.activemq.kaha.StoreLocation;
import java.io.IOException;
import java.io.DataOutput;
import org.apache.activemq.kaha.Marshaller;
import org.apache.activemq.util.DataByteArrayOutputStream;

public final class SyncDataFileWriter
{
    private DataByteArrayOutputStream buffer;
    private DataManagerImpl dataManager;
    
    SyncDataFileWriter(final DataManagerImpl fileManager) {
        this.dataManager = fileManager;
        this.buffer = new DataByteArrayOutputStream();
    }
    
    public synchronized DataItem storeItem(final Marshaller marshaller, final Object payload, final byte type) throws IOException {
        this.buffer.reset();
        this.buffer.position(5);
        marshaller.writePayload(payload, this.buffer);
        final int size = this.buffer.size();
        final int payloadSize = size - 5;
        this.buffer.reset();
        this.buffer.writeByte(type);
        this.buffer.writeInt(payloadSize);
        final DataItem item = new DataItem();
        item.setSize(payloadSize);
        final DataFile dataFile = this.dataManager.findSpaceForData(item);
        dataFile.getRandomAccessFile().seek(item.getOffset());
        dataFile.getRandomAccessFile().write(this.buffer.getData(), 0, size);
        dataFile.setWriterData(Boolean.TRUE);
        this.dataManager.addInterestInFile(dataFile);
        return item;
    }
    
    public synchronized void updateItem(final DataItem item, final Marshaller marshaller, final Object payload, final byte type) throws IOException {
        this.buffer.reset();
        this.buffer.position(5);
        marshaller.writePayload(payload, this.buffer);
        final int size = this.buffer.size();
        final int payloadSize = size - 5;
        this.buffer.reset();
        this.buffer.writeByte(type);
        this.buffer.writeInt(payloadSize);
        item.setSize(payloadSize);
        final DataFile dataFile = this.dataManager.getDataFile(item);
        final RandomAccessFile file = dataFile.getRandomAccessFile();
        file.seek(item.getOffset());
        file.write(this.buffer.getData(), 0, size);
        dataFile.setWriterData(Boolean.TRUE);
    }
    
    public synchronized void force(final DataFile dataFile) throws IOException {
        if (dataFile.getWriterData() != null && dataFile.isDirty()) {
            dataFile.getRandomAccessFile().getFD().sync();
            dataFile.setWriterData(null);
            dataFile.setDirty(false);
        }
    }
    
    public void close() throws IOException {
    }
}
