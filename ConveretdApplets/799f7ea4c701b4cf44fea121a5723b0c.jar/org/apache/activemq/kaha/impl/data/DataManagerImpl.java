// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.data;

import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.activemq.util.IOExceptionSupport;
import org.apache.activemq.kaha.StoreLocation;
import java.io.IOException;
import java.io.FilenameFilter;
import org.apache.activemq.util.IOHelper;
import org.apache.activemq.kaha.impl.index.RedoStoreIndexItem;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.activemq.kaha.Marshaller;
import java.util.Map;
import java.io.File;
import org.slf4j.Logger;
import org.apache.activemq.kaha.impl.DataManager;

public final class DataManagerImpl implements DataManager
{
    public static final int ITEM_HEAD_SIZE = 5;
    public static final byte DATA_ITEM_TYPE = 1;
    public static final byte REDO_ITEM_TYPE = 2;
    public static final long MAX_FILE_LENGTH = 33554432L;
    private static final Logger LOG;
    private static final String NAME_PREFIX = "data-";
    private final File directory;
    private final String name;
    private SyncDataFileReader reader;
    private SyncDataFileWriter writer;
    private DataFile currentWriteFile;
    private long maxFileLength;
    private Map<Integer, DataFile> fileMap;
    private Marshaller redoMarshaller;
    private String dataFilePrefix;
    private final AtomicLong storeSize;
    
    public DataManagerImpl(final File dir, final String name, final AtomicLong storeSize) {
        this.maxFileLength = 33554432L;
        this.fileMap = new HashMap<Integer, DataFile>();
        this.redoMarshaller = RedoStoreIndexItem.MARSHALLER;
        this.directory = dir;
        this.name = name;
        this.storeSize = storeSize;
        this.dataFilePrefix = IOHelper.toFileSystemSafeName("data-" + name + "-");
        final File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(final File dir, final String n) {
                return dir.equals(DataManagerImpl.this.directory) && n.startsWith(DataManagerImpl.this.dataFilePrefix);
            }
        });
        if (files != null) {
            for (int i = 0; i < files.length; ++i) {
                final File file = files[i];
                final String n = file.getName();
                final String numStr = n.substring(this.dataFilePrefix.length(), n.length());
                final int num = Integer.parseInt(numStr);
                final DataFile dataFile = new DataFile(file, num);
                storeSize.addAndGet(dataFile.getLength());
                this.fileMap.put(dataFile.getNumber(), dataFile);
                if (this.currentWriteFile == null || this.currentWriteFile.getNumber() < num) {
                    this.currentWriteFile = dataFile;
                }
            }
        }
    }
    
    private DataFile createAndAddDataFile(final int num) {
        final String fileName = this.dataFilePrefix + num;
        final File file = new File(this.directory, fileName);
        final DataFile result = new DataFile(file, num);
        this.fileMap.put(result.getNumber(), result);
        return result;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    synchronized DataFile findSpaceForData(final DataItem item) throws IOException {
        if (this.currentWriteFile == null || this.currentWriteFile.getLength() + item.getSize() > this.maxFileLength) {
            final int nextNum = (this.currentWriteFile != null) ? (this.currentWriteFile.getNumber() + 1) : 1;
            if (this.currentWriteFile != null && this.currentWriteFile.isUnused()) {
                this.removeDataFile(this.currentWriteFile);
            }
            this.currentWriteFile = this.createAndAddDataFile(nextNum);
        }
        item.setOffset(this.currentWriteFile.getLength());
        item.setFile(this.currentWriteFile.getNumber());
        final int len = item.getSize() + 5;
        this.currentWriteFile.incrementLength(len);
        this.storeSize.addAndGet(len);
        return this.currentWriteFile;
    }
    
    DataFile getDataFile(final StoreLocation item) throws IOException {
        final Integer key = item.getFile();
        final DataFile dataFile = this.fileMap.get(key);
        if (dataFile == null) {
            DataManagerImpl.LOG.error("Looking for key " + key + " but not found in fileMap: " + this.fileMap);
            throw new IOException("Could not locate data file data-" + this.name + "-" + item.getFile());
        }
        return dataFile;
    }
    
    @Override
    public synchronized Object readItem(final Marshaller marshaller, final StoreLocation item) throws IOException {
        return this.getReader().readItem(marshaller, item);
    }
    
    @Override
    public synchronized StoreLocation storeDataItem(final Marshaller marshaller, final Object payload) throws IOException {
        return this.getWriter().storeItem(marshaller, payload, (byte)1);
    }
    
    @Override
    public synchronized StoreLocation storeRedoItem(final Object payload) throws IOException {
        return this.getWriter().storeItem(this.redoMarshaller, payload, (byte)2);
    }
    
    @Override
    public synchronized void updateItem(final StoreLocation location, final Marshaller marshaller, final Object payload) throws IOException {
        this.getWriter().updateItem((DataItem)location, marshaller, payload, (byte)1);
    }
    
    @Override
    public synchronized void recoverRedoItems(final RedoListener listener) throws IOException {
        if (this.currentWriteFile == null) {
            return;
        }
        DataItem item = new DataItem();
        item.setFile(this.currentWriteFile.getNumber());
        item.setOffset(0L);
        while (true) {
            byte type;
            try {
                type = this.getReader().readDataItemSize(item);
            }
            catch (IOException ignore) {
                DataManagerImpl.LOG.trace("End of data file reached at (header was invalid): " + item);
                return;
            }
            if (type == 2) {
                Object object;
                try {
                    object = this.readItem(this.redoMarshaller, item);
                }
                catch (IOException e2) {
                    DataManagerImpl.LOG.trace("End of data file reached at (payload was invalid): " + item);
                    return;
                }
                try {
                    listener.onRedoItem(item, object);
                    item = item.copy();
                }
                catch (Exception e) {
                    throw IOExceptionSupport.create("Recovery handler failed: " + e, e);
                }
            }
            item.setOffset(item.getOffset() + 5L + item.getSize());
        }
    }
    
    @Override
    public synchronized void close() throws IOException {
        this.getWriter().close();
        for (final DataFile dataFile : this.fileMap.values()) {
            this.getWriter().force(dataFile);
            dataFile.close();
        }
        this.fileMap.clear();
    }
    
    @Override
    public synchronized void force() throws IOException {
        for (final DataFile dataFile : this.fileMap.values()) {
            this.getWriter().force(dataFile);
        }
    }
    
    @Override
    public synchronized boolean delete() throws IOException {
        boolean result = true;
        for (final DataFile dataFile : this.fileMap.values()) {
            this.storeSize.addAndGet(-dataFile.getLength());
            result &= dataFile.delete();
        }
        this.fileMap.clear();
        return result;
    }
    
    @Override
    public synchronized void addInterestInFile(final int file) throws IOException {
        if (file >= 0) {
            final Integer key = file;
            DataFile dataFile = this.fileMap.get(key);
            if (dataFile == null) {
                dataFile = this.createAndAddDataFile(file);
            }
            this.addInterestInFile(dataFile);
        }
    }
    
    synchronized void addInterestInFile(final DataFile dataFile) {
        if (dataFile != null) {
            dataFile.increment();
        }
    }
    
    @Override
    public synchronized void removeInterestInFile(final int file) throws IOException {
        if (file >= 0) {
            final Integer key = file;
            final DataFile dataFile = this.fileMap.get(key);
            this.removeInterestInFile(dataFile);
        }
    }
    
    synchronized void removeInterestInFile(final DataFile dataFile) throws IOException {
        if (dataFile != null && dataFile.decrement() <= 0 && dataFile != this.currentWriteFile) {
            this.removeDataFile(dataFile);
        }
    }
    
    @Override
    public synchronized void consolidateDataFiles() throws IOException {
        final List<DataFile> purgeList = new ArrayList<DataFile>();
        for (final DataFile dataFile : this.fileMap.values()) {
            if (dataFile.isUnused() && dataFile != this.currentWriteFile) {
                purgeList.add(dataFile);
            }
        }
        for (int j = 0; j < purgeList.size(); ++j) {
            final DataFile dataFile = purgeList.get(j);
            this.removeDataFile(dataFile);
        }
    }
    
    private void removeDataFile(final DataFile dataFile) throws IOException {
        this.fileMap.remove(dataFile.getNumber());
        if (this.writer != null) {
            this.writer.force(dataFile);
        }
        this.storeSize.addAndGet(-dataFile.getLength());
        final boolean result = dataFile.delete();
        DataManagerImpl.LOG.debug("discarding data file " + dataFile + (result ? "successful " : "failed"));
    }
    
    @Override
    public Marshaller getRedoMarshaller() {
        return this.redoMarshaller;
    }
    
    @Override
    public void setRedoMarshaller(final Marshaller redoMarshaller) {
        this.redoMarshaller = redoMarshaller;
    }
    
    public long getMaxFileLength() {
        return this.maxFileLength;
    }
    
    public void setMaxFileLength(final long maxFileLength) {
        this.maxFileLength = maxFileLength;
    }
    
    @Override
    public String toString() {
        return "DataManager:(data-" + this.name + ")";
    }
    
    public synchronized SyncDataFileReader getReader() {
        if (this.reader == null) {
            this.reader = this.createReader();
        }
        return this.reader;
    }
    
    protected synchronized SyncDataFileReader createReader() {
        return new SyncDataFileReader(this);
    }
    
    public synchronized void setReader(final SyncDataFileReader reader) {
        this.reader = reader;
    }
    
    public synchronized SyncDataFileWriter getWriter() {
        if (this.writer == null) {
            this.writer = this.createWriter();
        }
        return this.writer;
    }
    
    private SyncDataFileWriter createWriter() {
        return new SyncDataFileWriter(this);
    }
    
    public synchronized void setWriter(final SyncDataFileWriter writer) {
        this.writer = writer;
    }
    
    static {
        LOG = LoggerFactory.getLogger(DataManagerImpl.class);
    }
}
