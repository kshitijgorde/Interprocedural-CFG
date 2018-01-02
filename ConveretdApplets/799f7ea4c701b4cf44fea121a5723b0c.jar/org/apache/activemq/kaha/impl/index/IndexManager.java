// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index;

import org.slf4j.LoggerFactory;
import org.apache.activemq.util.IOHelper;
import java.nio.channels.FileLock;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.activemq.kaha.impl.DataManager;
import java.io.RandomAccessFile;
import java.io.File;
import org.slf4j.Logger;

public final class IndexManager
{
    public static final String NAME_PREFIX = "index-";
    private static final Logger LOG;
    private final String name;
    private File directory;
    private File file;
    private RandomAccessFile indexFile;
    private StoreIndexReader reader;
    private StoreIndexWriter writer;
    private DataManager redoLog;
    private String mode;
    private long length;
    private IndexItem firstFree;
    private IndexItem lastFree;
    private boolean dirty;
    private final AtomicLong storeSize;
    private int freeSize;
    
    public IndexManager(final File directory, final String name, final String mode, final DataManager redoLog, final AtomicLong storeSize) throws IOException {
        this.freeSize = 0;
        this.directory = directory;
        this.name = name;
        this.mode = mode;
        this.redoLog = redoLog;
        this.storeSize = storeSize;
        this.initialize();
    }
    
    public synchronized boolean isEmpty() {
        return this.lastFree == null && this.length == 0L;
    }
    
    public synchronized IndexItem getIndex(final long offset) throws IOException {
        IndexItem result = null;
        if (offset >= 0L) {
            result = this.reader.readItem(offset);
        }
        return result;
    }
    
    public synchronized IndexItem refreshIndex(final IndexItem item) throws IOException {
        this.reader.updateIndexes(item);
        return item;
    }
    
    public synchronized void freeIndex(final IndexItem item) throws IOException {
        item.reset();
        item.setActive(false);
        if (this.lastFree == null) {
            this.firstFree = item;
            this.lastFree = item;
        }
        else {
            this.lastFree.setNextItem(item.getOffset());
            if (this.lastFree.equals(this.firstFree)) {
                (this.firstFree = new IndexItem()).copyIndex(this.lastFree);
                this.writer.updateIndexes(this.firstFree);
            }
            this.writer.updateIndexes(this.lastFree);
            this.lastFree = item;
        }
        this.writer.updateIndexes(item);
        ++this.freeSize;
        this.dirty = true;
    }
    
    public synchronized void storeIndex(final IndexItem index) throws IOException {
        this.writer.storeItem(index);
        this.dirty = true;
    }
    
    public synchronized void updateIndexes(final IndexItem index) throws IOException {
        try {
            this.writer.updateIndexes(index);
        }
        catch (Throwable e) {
            IndexManager.LOG.error(this.name + " error updating indexes ", e);
        }
        this.dirty = true;
    }
    
    public synchronized void redo(final RedoStoreIndexItem redo) throws IOException {
        this.writer.redoStoreItem(redo);
        this.dirty = true;
    }
    
    public synchronized IndexItem createNewIndex() throws IOException {
        IndexItem result = this.getNextFreeIndex();
        if (result == null) {
            result = new IndexItem();
            result.setOffset(this.length);
            this.length += 51L;
            this.storeSize.addAndGet(51L);
        }
        return result;
    }
    
    public synchronized void close() throws IOException {
        if (this.indexFile != null) {
            this.indexFile.close();
            this.indexFile = null;
        }
    }
    
    public synchronized void force() throws IOException {
        if (this.indexFile != null && this.dirty) {
            this.indexFile.getFD().sync();
            this.dirty = false;
        }
    }
    
    public synchronized boolean delete() throws IOException {
        this.firstFree = null;
        this.lastFree = null;
        if (this.indexFile != null) {
            this.indexFile.close();
            this.indexFile = null;
        }
        return this.file.delete();
    }
    
    private synchronized IndexItem getNextFreeIndex() throws IOException {
        IndexItem result = null;
        if (this.firstFree != null) {
            if (this.firstFree.equals(this.lastFree)) {
                result = this.firstFree;
                this.firstFree = null;
                this.lastFree = null;
            }
            else {
                result = this.firstFree;
                this.firstFree = this.getIndex(this.firstFree.getNextItem());
                if (this.firstFree == null) {
                    this.lastFree = null;
                }
            }
            result.reset();
            this.writer.updateIndexes(result);
            --this.freeSize;
        }
        return result;
    }
    
    synchronized long getLength() {
        return this.length;
    }
    
    public final long size() {
        return this.length;
    }
    
    public synchronized void setLength(final long value) {
        this.length = value;
        this.storeSize.addAndGet(this.length);
    }
    
    public synchronized FileLock getLock() throws IOException {
        return this.indexFile.getChannel().tryLock(0L, this.indexFile.getChannel().size(), false);
    }
    
    @Override
    public String toString() {
        return "IndexManager:(index-" + this.name + ")";
    }
    
    protected void initialize() throws IOException {
        this.file = new File(this.directory, "index-" + IOHelper.toFileSystemSafeName(this.name));
        IOHelper.mkdirs(this.file.getParentFile());
        this.indexFile = new RandomAccessFile(this.file, this.mode);
        this.reader = new StoreIndexReader(this.indexFile);
        this.writer = new StoreIndexWriter(this.indexFile, this.name, this.redoLog);
        long offset;
        for (offset = 0L; offset + 51L <= this.indexFile.length(); offset += 51L) {
            final IndexItem index = this.reader.readItem(offset);
            if (!index.isActive()) {
                index.reset();
                if (this.lastFree != null) {
                    this.lastFree.setNextItem(index.getOffset());
                    this.updateIndexes(this.lastFree);
                    this.lastFree = index;
                }
                else {
                    this.lastFree = index;
                    this.firstFree = index;
                }
                ++this.freeSize;
            }
        }
        this.length = offset;
        this.storeSize.addAndGet(this.length);
    }
    
    static {
        LOG = LoggerFactory.getLogger(IndexManager.class);
    }
}
