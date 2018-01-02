// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.kahadb.journal;

import org.apache.kahadb.util.ByteSequence;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

final class DataFileAccessor
{
    private final DataFile dataFile;
    private final Map<DataFileAppender.WriteKey, DataFileAppender.WriteCommand> inflightWrites;
    private final RandomAccessFile file;
    private boolean disposed;
    
    public DataFileAccessor(final Journal dataManager, final DataFile dataFile) throws IOException {
        this.dataFile = dataFile;
        this.inflightWrites = dataManager.getInflightWrites();
        this.file = dataFile.openRandomAccessFile();
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
                location.setType(this.file.readByte());
            }
            else {
                this.file.seek(location.getOffset() + 5);
            }
            final byte[] data = new byte[location.getSize() - 5];
            this.file.readFully(data);
            return new ByteSequence(data, 0, data.length);
        }
        catch (RuntimeException e) {
            throw new IOException("Invalid location: " + location + ", : " + e);
        }
    }
    
    public void readFully(final long offset, final byte[] data) throws IOException {
        this.file.seek(offset);
        this.file.readFully(data);
    }
    
    public int read(final long offset, final byte[] data) throws IOException {
        this.file.seek(offset);
        return this.file.read(data);
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
    
    public void updateRecord(final Location location, final ByteSequence data, final boolean sync) throws IOException {
        this.file.seek(location.getOffset() + 5);
        final int size = Math.min(data.getLength(), location.getSize());
        this.file.write(data.getData(), data.getOffset(), size);
        if (sync) {
            this.file.getFD().sync();
        }
    }
}
