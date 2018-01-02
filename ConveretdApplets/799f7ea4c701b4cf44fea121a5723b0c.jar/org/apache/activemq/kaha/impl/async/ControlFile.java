// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.async;

import org.apache.activemq.util.ByteSequence;
import java.nio.channels.OverlappingFileLockException;
import org.apache.activemq.util.IOExceptionSupport;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.io.RandomAccessFile;
import java.io.File;

public final class ControlFile
{
    private static final boolean DISABLE_FILE_LOCK;
    private final File file;
    private final RandomAccessFile randomAccessFile;
    private final int maxRecordSize;
    private final int firstRecordStart;
    private final int secondRecordStart;
    private final int firstRecordEnd;
    private final int secondRecordEnd;
    private long version;
    private FileLock lock;
    private boolean disposed;
    
    public ControlFile(final File file, final int recordSize) throws IOException {
        this.file = file;
        this.maxRecordSize = recordSize + 4;
        this.firstRecordStart = 8;
        this.secondRecordStart = 8 + this.maxRecordSize + 8 + 8;
        this.firstRecordEnd = this.firstRecordStart + this.maxRecordSize;
        this.secondRecordEnd = this.secondRecordStart + this.maxRecordSize;
        this.randomAccessFile = new RandomAccessFile(file, "rw");
    }
    
    public void lock() throws IOException {
        if (ControlFile.DISABLE_FILE_LOCK) {
            return;
        }
        if (this.lock == null) {
            try {
                this.lock = this.randomAccessFile.getChannel().tryLock(0L, this.randomAccessFile.getChannel().size(), false);
            }
            catch (OverlappingFileLockException e) {
                throw IOExceptionSupport.create("Control file '" + this.file + "' could not be locked.", e);
            }
            if (this.lock == null) {
                throw new IOException("Control file '" + this.file + "' could not be locked.");
            }
        }
    }
    
    public void unlock() throws IOException {
        if (ControlFile.DISABLE_FILE_LOCK) {
            return;
        }
        if (this.lock != null) {
            this.lock.release();
            this.lock = null;
        }
    }
    
    public void dispose() {
        if (this.disposed) {
            return;
        }
        this.disposed = true;
        try {
            this.unlock();
        }
        catch (IOException ex) {}
        try {
            this.randomAccessFile.close();
        }
        catch (IOException ex2) {}
    }
    
    public synchronized ByteSequence load() throws IOException {
        final long l = this.randomAccessFile.length();
        if (l < this.maxRecordSize) {
            return null;
        }
        this.randomAccessFile.seek(this.firstRecordStart - 8);
        final long v1 = this.randomAccessFile.readLong();
        this.randomAccessFile.seek(this.firstRecordEnd);
        final long v1check = this.randomAccessFile.readLong();
        this.randomAccessFile.seek(this.secondRecordStart - 8);
        final long v2 = this.randomAccessFile.readLong();
        this.randomAccessFile.seek(this.secondRecordEnd);
        final long v2check = this.randomAccessFile.readLong();
        byte[] data = null;
        if (v2 == v2check) {
            this.version = v2;
            this.randomAccessFile.seek(this.secondRecordStart);
            final int size = this.randomAccessFile.readInt();
            data = new byte[size];
            this.randomAccessFile.readFully(data);
        }
        else {
            if (v1 != v1check) {
                throw new IOException("Control data corrupted.");
            }
            this.version = v1;
            this.randomAccessFile.seek(this.firstRecordStart);
            final int size = this.randomAccessFile.readInt();
            data = new byte[size];
            this.randomAccessFile.readFully(data);
        }
        return new ByteSequence(data, 0, data.length);
    }
    
    public void store(final ByteSequence data, final boolean sync) throws IOException {
        ++this.version;
        this.randomAccessFile.setLength(this.maxRecordSize * 2 + 32);
        this.randomAccessFile.seek(0L);
        this.randomAccessFile.writeLong(this.version);
        this.randomAccessFile.writeInt(data.getLength());
        this.randomAccessFile.write(data.getData());
        this.randomAccessFile.seek(this.firstRecordEnd);
        this.randomAccessFile.writeLong(this.version);
        this.randomAccessFile.writeLong(this.version);
        this.randomAccessFile.writeInt(data.getLength());
        this.randomAccessFile.write(data.getData());
        this.randomAccessFile.seek(this.secondRecordEnd);
        this.randomAccessFile.writeLong(this.version);
        if (sync) {
            this.randomAccessFile.getFD().sync();
        }
    }
    
    public boolean isDisposed() {
        return this.disposed;
    }
    
    static {
        DISABLE_FILE_LOCK = "true".equals(System.getProperty("java.nio.channels.FileLock.broken", "false"));
    }
}
