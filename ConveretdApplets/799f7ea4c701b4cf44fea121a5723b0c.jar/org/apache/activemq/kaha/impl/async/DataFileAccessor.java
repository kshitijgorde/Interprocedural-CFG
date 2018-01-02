// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.async;

import org.apache.activemq.util.ByteSequence;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

final class DataFileAccessor
{
    private final DataFile dataFile;
    private final Map<DataFileAppender.WriteKey, DataFileAppender.WriteCommand> inflightWrites;
    private final RandomAccessFile file;
    private boolean disposed;
    
    public DataFileAccessor(final AsyncDataManager dataManager, final DataFile dataFile) throws IOException {
        this.dataFile = dataFile;
        this.inflightWrites = dataManager.getInflightWrites();
        this.file = dataFile.openRandomAccessFile(false);
    }
    
    public DataFile getDataFile() {
        return this.dataFile;
    }
    
    public void dispose() {
        if (this.disposed) {
            return;
        }
        this.disposed = true;
        try {
            this.dataFile.closeRandomAccessFile(this.file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public ByteSequence readRecord(final Location location) throws IOException {
        if (!location.isValid()) {
            throw new IOException("Invalid location: " + location);
        }
        final DataFileAppender.WriteCommand asyncWrite = this.inflightWrites.get(new DataFileAppender.WriteKey(location));
        if (asyncWrite != null) {
            return asyncWrite.data;
        }
        try {
            if (location.getSize() == -1) {
                this.file.seek(location.getOffset());
                location.setSize(this.file.readInt());
                this.file.seek(location.getOffset() + 29);
            }
            else {
                this.file.seek(location.getOffset() + 29);
            }
            final byte[] data = new byte[location.getSize() - 32];
            this.file.readFully(data);
            return new ByteSequence(data, 0, data.length);
        }
        catch (RuntimeException e) {
            throw new IOException("Invalid location: " + location + ", : " + e);
        }
    }
    
    public void readLocationDetails(final Location location) throws IOException {
        final DataFileAppender.WriteCommand asyncWrite = this.inflightWrites.get(new DataFileAppender.WriteKey(location));
        if (asyncWrite != null) {
            location.setSize(asyncWrite.location.getSize());
            location.setType(asyncWrite.location.getType());
        }
        else {
            this.file.seek(location.getOffset());
            location.setSize(this.file.readInt());
            location.setType(this.file.readByte());
        }
    }
    
    public boolean readLocationDetailsAndValidate(final Location location) {
        try {
            final DataFileAppender.WriteCommand asyncWrite = this.inflightWrites.get(new DataFileAppender.WriteKey(location));
            if (asyncWrite != null) {
                location.setSize(asyncWrite.location.getSize());
                location.setType(asyncWrite.location.getType());
            }
            else {
                this.file.seek(location.getOffset());
                location.setSize(this.file.readInt());
                location.setType(this.file.readByte());
                final byte[] data = new byte[3];
                this.file.seek(location.getOffset() + 26);
                this.file.readFully(data);
                if (data[0] != AsyncDataManager.ITEM_HEAD_SOR[0] || data[1] != AsyncDataManager.ITEM_HEAD_SOR[1] || data[2] != AsyncDataManager.ITEM_HEAD_SOR[2]) {
                    return false;
                }
                this.file.seek(location.getOffset() + location.getSize() - 3);
                this.file.readFully(data);
                if (data[0] != AsyncDataManager.ITEM_HEAD_EOR[0] || data[1] != AsyncDataManager.ITEM_HEAD_EOR[1] || data[2] != AsyncDataManager.ITEM_HEAD_EOR[2]) {
                    return false;
                }
            }
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }
    
    public void updateRecord(final Location location, final ByteSequence data, final boolean sync) throws IOException {
        this.file.seek(location.getOffset() + 29);
        final int size = Math.min(data.getLength(), location.getSize());
        this.file.write(data.getData(), data.getOffset(), size);
        if (sync) {
            this.file.getFD().sync();
        }
    }
}
