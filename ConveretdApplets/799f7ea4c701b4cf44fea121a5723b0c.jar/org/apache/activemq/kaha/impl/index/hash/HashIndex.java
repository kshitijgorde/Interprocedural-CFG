// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index.hash;

import org.slf4j.LoggerFactory;
import java.util.Iterator;
import org.apache.activemq.util.IOHelper;
import java.io.DataInput;
import java.io.DataOutput;
import org.apache.activemq.kaha.StoreEntry;
import java.io.IOException;
import org.apache.activemq.util.LRUCache;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.LinkedList;
import org.apache.activemq.kaha.Marshaller;
import org.apache.activemq.util.DataByteArrayOutputStream;
import org.apache.activemq.util.DataByteArrayInputStream;
import org.apache.activemq.kaha.impl.index.IndexManager;
import java.io.RandomAccessFile;
import java.io.File;
import org.slf4j.Logger;
import org.apache.activemq.kaha.impl.index.Index;

public class HashIndex implements Index, HashIndexMBean
{
    public static final int DEFAULT_PAGE_SIZE;
    public static final int DEFAULT_KEY_SIZE;
    public static final int DEFAULT_BIN_SIZE;
    public static final int MAXIMUM_CAPACITY;
    public static final int DEFAULT_LOAD_FACTOR;
    private static final int LOW_WATER_MARK = 16384;
    private static final String NAME_PREFIX = "hash-index-";
    private static final Logger LOG;
    private final String name;
    private File directory;
    private File file;
    private RandomAccessFile indexFile;
    private IndexManager indexManager;
    private int pageSize;
    private int keySize;
    private int numberOfBins;
    private int keysPerPage;
    private DataByteArrayInputStream dataIn;
    private DataByteArrayOutputStream dataOut;
    private byte[] readBuffer;
    private HashBin[] bins;
    private Marshaller keyMarshaller;
    private long length;
    private LinkedList<HashPage> freeList;
    private AtomicBoolean loaded;
    private LRUCache<Long, HashPage> pageCache;
    private boolean enablePageCaching;
    private int pageCacheSize;
    private int size;
    private int highestSize;
    private int activeBins;
    private int threshold;
    private int maximumCapacity;
    private int loadFactor;
    
    public HashIndex(final File directory, final String name, final IndexManager indexManager) throws IOException {
        this.pageSize = HashIndex.DEFAULT_PAGE_SIZE;
        this.keySize = HashIndex.DEFAULT_KEY_SIZE;
        this.numberOfBins = HashIndex.DEFAULT_BIN_SIZE;
        this.keysPerPage = this.pageSize / this.keySize;
        this.freeList = new LinkedList<HashPage>();
        this.loaded = new AtomicBoolean();
        this.enablePageCaching = false;
        this.pageCacheSize = 10;
        this.highestSize = 0;
        this.maximumCapacity = HashIndex.MAXIMUM_CAPACITY;
        this.loadFactor = HashIndex.DEFAULT_LOAD_FACTOR;
        this.directory = directory;
        this.name = name;
        this.indexManager = indexManager;
        this.openIndexFile();
        this.pageCache = new LRUCache<Long, HashPage>(this.pageCacheSize, this.pageCacheSize, 0.75f, true);
    }
    
    @Override
    public synchronized void setKeyMarshaller(final Marshaller marshaller) {
        this.keyMarshaller = marshaller;
    }
    
    @Override
    public synchronized int getKeySize() {
        return this.keySize;
    }
    
    @Override
    public synchronized void setKeySize(final int keySize) {
        this.keySize = keySize;
        if (this.loaded.get()) {
            throw new RuntimeException("Pages already loaded - can't reset key size");
        }
    }
    
    @Override
    public synchronized int getPageSize() {
        return this.pageSize;
    }
    
    public synchronized void setPageSize(final int pageSize) {
        if (this.loaded.get() && pageSize != this.pageSize) {
            throw new RuntimeException("Pages already loaded - can't reset page size");
        }
        this.pageSize = pageSize;
    }
    
    @Override
    public int getNumberOfBins() {
        return this.numberOfBins;
    }
    
    public void setNumberOfBins(final int numberOfBins) {
        if (this.loaded.get() && numberOfBins != this.numberOfBins) {
            throw new RuntimeException("Pages already loaded - can't reset bin size");
        }
        this.numberOfBins = numberOfBins;
    }
    
    @Override
    public synchronized boolean isEnablePageCaching() {
        return this.enablePageCaching;
    }
    
    public synchronized void setEnablePageCaching(final boolean enablePageCaching) {
        this.enablePageCaching = enablePageCaching;
    }
    
    @Override
    public synchronized int getPageCacheSize() {
        return this.pageCacheSize;
    }
    
    public synchronized void setPageCacheSize(final int pageCacheSize) {
        this.pageCacheSize = pageCacheSize;
        this.pageCache.setMaxCacheSize(pageCacheSize);
    }
    
    @Override
    public synchronized boolean isTransient() {
        return false;
    }
    
    public int getThreshold() {
        return this.threshold;
    }
    
    public void setThreshold(final int threshold) {
        this.threshold = threshold;
    }
    
    public int getLoadFactor() {
        return this.loadFactor;
    }
    
    public void setLoadFactor(final int loadFactor) {
        this.loadFactor = loadFactor;
    }
    
    public int getMaximumCapacity() {
        return this.maximumCapacity;
    }
    
    public void setMaximumCapacity(final int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }
    
    @Override
    public synchronized int getSize() {
        return this.size;
    }
    
    @Override
    public synchronized int getActiveBins() {
        return this.activeBins;
    }
    
    @Override
    public synchronized void load() {
        if (this.loaded.compareAndSet(false, true)) {
            int capacity;
            for (capacity = 1; capacity < this.numberOfBins; capacity <<= 1) {}
            this.bins = new HashBin[capacity];
            this.numberOfBins = capacity;
            this.threshold = this.calculateThreashold();
            this.keysPerPage = this.pageSize / this.keySize;
            this.dataIn = new DataByteArrayInputStream();
            this.dataOut = new DataByteArrayOutputStream(this.pageSize);
            this.readBuffer = new byte[this.pageSize];
            try {
                this.openIndexFile();
                if (this.indexFile.length() > 0L) {
                    this.doCompress();
                }
            }
            catch (IOException e) {
                HashIndex.LOG.error("Failed to load index ", e);
                throw new RuntimeException(e);
            }
        }
    }
    
    @Override
    public synchronized void unload() throws IOException {
        if (this.loaded.compareAndSet(true, false) && this.indexFile != null) {
            this.indexFile.close();
            this.indexFile = null;
            this.freeList.clear();
            this.pageCache.clear();
            this.bins = new HashBin[this.bins.length];
        }
    }
    
    @Override
    public synchronized void store(final Object key, final StoreEntry value) throws IOException {
        this.load();
        final HashEntry entry = new HashEntry();
        entry.setKey((Comparable)key);
        entry.setIndexOffset(value.getOffset());
        if (!this.getBin(key).put(entry)) {
            ++this.size;
        }
        if (this.size >= this.threshold) {
            this.resize(2 * this.bins.length);
        }
        if (this.size > this.highestSize) {
            this.highestSize = this.size;
        }
    }
    
    @Override
    public synchronized StoreEntry get(final Object key) throws IOException {
        this.load();
        final HashEntry entry = new HashEntry();
        entry.setKey((Comparable)key);
        final HashEntry result = this.getBin(key).find(entry);
        return (result != null) ? this.indexManager.getIndex(result.getIndexOffset()) : null;
    }
    
    @Override
    public synchronized StoreEntry remove(final Object key) throws IOException {
        this.load();
        StoreEntry result = null;
        final HashEntry entry = new HashEntry();
        entry.setKey((Comparable)key);
        final HashEntry he = this.getBin(key).remove(entry);
        if (he != null) {
            --this.size;
            result = this.indexManager.getIndex(he.getIndexOffset());
        }
        if (this.highestSize > 16384 && this.highestSize > this.size * 2) {
            int newSize = this.size / this.keysPerPage;
            newSize = Math.max(128, newSize);
            this.highestSize = 0;
            this.resize(newSize);
        }
        return result;
    }
    
    @Override
    public synchronized boolean containsKey(final Object key) throws IOException {
        return this.get(key) != null;
    }
    
    @Override
    public synchronized void clear() throws IOException {
        this.unload();
        this.delete();
        this.openIndexFile();
        this.load();
    }
    
    @Override
    public synchronized void delete() throws IOException {
        this.unload();
        if (this.file.exists()) {
            this.file.delete();
        }
        this.length = 0L;
    }
    
    HashPage lookupPage(final long pageId) throws IOException {
        HashPage result = null;
        if (pageId >= 0L) {
            result = this.getFromCache(pageId);
            if (result == null) {
                result = this.getFullPage(pageId);
                if (result != null) {
                    if (!result.isActive()) {
                        throw new IllegalStateException("Trying to access an inactive page: " + pageId);
                    }
                    this.addToCache(result);
                }
            }
        }
        return result;
    }
    
    HashPage createPage(final int binId) throws IOException {
        HashPage result = this.getNextFreePage();
        if (result == null) {
            result = new HashPage(this.keysPerPage);
            result.setId(this.length);
            result.setBinId(binId);
            this.writePageHeader(result);
            this.length += this.pageSize;
            this.indexFile.seek(this.length);
            this.indexFile.write(-1);
        }
        this.addToCache(result);
        return result;
    }
    
    void releasePage(final HashPage page) throws IOException {
        this.removeFromCache(page);
        page.reset();
        page.setActive(false);
        this.writePageHeader(page);
        this.freeList.add(page);
    }
    
    private HashPage getNextFreePage() throws IOException {
        HashPage result = null;
        if (!this.freeList.isEmpty()) {
            result = this.freeList.removeFirst();
            result.setActive(true);
            result.reset();
            this.writePageHeader(result);
        }
        return result;
    }
    
    void writeFullPage(final HashPage page) throws IOException {
        this.dataOut.reset();
        page.write(this.keyMarshaller, this.dataOut);
        if (this.dataOut.size() > this.pageSize) {
            throw new IOException("Page Size overflow: pageSize is " + this.pageSize + " trying to write " + this.dataOut.size());
        }
        this.indexFile.seek(page.getId());
        this.indexFile.write(this.dataOut.getData(), 0, this.dataOut.size());
    }
    
    void writePageHeader(final HashPage page) throws IOException {
        this.dataOut.reset();
        page.writeHeader(this.dataOut);
        this.indexFile.seek(page.getId());
        this.indexFile.write(this.dataOut.getData(), 0, 17);
    }
    
    HashPage getFullPage(final long id) throws IOException {
        this.indexFile.seek(id);
        this.indexFile.readFully(this.readBuffer, 0, this.pageSize);
        this.dataIn.restart(this.readBuffer);
        final HashPage page = new HashPage(this.keysPerPage);
        page.setId(id);
        page.read(this.keyMarshaller, this.dataIn);
        return page;
    }
    
    HashPage getPageHeader(final long id) throws IOException {
        this.indexFile.seek(id);
        this.indexFile.readFully(this.readBuffer, 0, 17);
        this.dataIn.restart(this.readBuffer);
        final HashPage page = new HashPage(this.keysPerPage);
        page.setId(id);
        page.readHeader(this.dataIn);
        return page;
    }
    
    void addToBin(final HashPage page) throws IOException {
        final int index = page.getBinId();
        if (index >= this.bins.length) {
            this.resize(index + 1);
        }
        final HashBin bin = this.getBin(index);
        bin.addHashPageInfo(page.getId(), page.getPersistedSize());
    }
    
    private HashBin getBin(final int index) {
        HashBin result = this.bins[index];
        if (result == null) {
            result = new HashBin(this, index, this.pageSize / this.keySize);
            this.bins[index] = result;
            ++this.activeBins;
        }
        return result;
    }
    
    private void openIndexFile() throws IOException {
        if (this.indexFile == null) {
            this.file = new File(this.directory, "hash-index-" + IOHelper.toFileSystemSafeName(this.name));
            IOHelper.mkdirs(this.file.getParentFile());
            this.indexFile = new RandomAccessFile(this.file, "rw");
        }
    }
    
    private HashBin getBin(final Object key) {
        final int hash = hash(key);
        final int i = indexFor(hash, this.bins.length);
        return this.getBin(i);
    }
    
    private HashPage getFromCache(final long pageId) {
        HashPage result = null;
        if (this.enablePageCaching) {
            result = this.pageCache.get(pageId);
        }
        return result;
    }
    
    private void addToCache(final HashPage page) {
        if (this.enablePageCaching) {
            this.pageCache.put(page.getId(), page);
        }
    }
    
    private void removeFromCache(final HashPage page) {
        if (this.enablePageCaching) {
            this.pageCache.remove(page.getId());
        }
    }
    
    private void doLoad() throws IOException {
        long offset = 0L;
        if (this.loaded.compareAndSet(false, true)) {
            while (offset + this.pageSize <= this.indexFile.length()) {
                this.indexFile.seek(offset);
                this.indexFile.readFully(this.readBuffer, 0, 17);
                this.dataIn.restart(this.readBuffer);
                final HashPage page = new HashPage(this.keysPerPage);
                page.setId(offset);
                page.readHeader(this.dataIn);
                if (!page.isActive()) {
                    page.reset();
                    this.freeList.add(page);
                }
                else {
                    this.addToBin(page);
                    this.size += page.size();
                }
                offset += this.pageSize;
            }
            this.length = offset;
        }
    }
    
    private void doCompress() throws IOException {
        final String backFileName = this.name + "-COMPRESS";
        final HashIndex backIndex = new HashIndex(this.directory, backFileName, this.indexManager);
        backIndex.setKeyMarshaller(this.keyMarshaller);
        backIndex.setKeySize(this.getKeySize());
        backIndex.setNumberOfBins(this.getNumberOfBins());
        backIndex.setPageSize(this.getPageSize());
        backIndex.load();
        final File backFile = backIndex.file;
        for (long offset = 0L; offset + this.pageSize <= this.indexFile.length(); offset += this.pageSize) {
            this.indexFile.seek(offset);
            HashPage page = this.getFullPage(offset);
            if (page.isActive()) {
                for (final HashEntry entry : page.getEntries()) {
                    backIndex.getBin(entry.getKey()).put(entry);
                    final HashIndex hashIndex = backIndex;
                    ++hashIndex.size;
                }
            }
            page = null;
        }
        backIndex.unload();
        this.unload();
        IOHelper.deleteFile(this.file);
        IOHelper.copyFile(backFile, this.file);
        IOHelper.deleteFile(backFile);
        this.openIndexFile();
        this.doLoad();
    }
    
    private void resize(int newCapacity) throws IOException {
        if (this.bins.length < this.getMaximumCapacity()) {
            if (newCapacity != this.numberOfBins) {
                int capacity;
                for (capacity = 1; capacity < newCapacity; capacity <<= 1) {}
                newCapacity = capacity;
                if (newCapacity != this.numberOfBins) {
                    HashIndex.LOG.info("Resize hash bins " + this.name + " from " + this.numberOfBins + " to " + newCapacity);
                    final String backFileName = this.name + "-REISZE";
                    final HashIndex backIndex = new HashIndex(this.directory, backFileName, this.indexManager);
                    backIndex.setKeyMarshaller(this.keyMarshaller);
                    backIndex.setKeySize(this.getKeySize());
                    backIndex.setNumberOfBins(newCapacity);
                    backIndex.setPageSize(this.getPageSize());
                    backIndex.load();
                    final File backFile = backIndex.file;
                    for (long offset = 0L; offset + this.pageSize <= this.indexFile.length(); offset += this.pageSize) {
                        this.indexFile.seek(offset);
                        HashPage page = this.getFullPage(offset);
                        if (page.isActive()) {
                            for (final HashEntry entry : page.getEntries()) {
                                backIndex.getBin(entry.getKey()).put(entry);
                                final HashIndex hashIndex = backIndex;
                                ++hashIndex.size;
                            }
                        }
                        page = null;
                    }
                    backIndex.unload();
                    this.unload();
                    IOHelper.deleteFile(this.file);
                    IOHelper.copyFile(backFile, this.file);
                    IOHelper.deleteFile(backFile);
                    this.setNumberOfBins(newCapacity);
                    this.bins = new HashBin[newCapacity];
                    this.threshold = this.calculateThreashold();
                    this.openIndexFile();
                    this.doLoad();
                }
            }
            return;
        }
        this.threshold = Integer.MAX_VALUE;
    }
    
    private int calculateThreashold() {
        return this.bins.length * this.loadFactor;
    }
    
    @Override
    public String toString() {
        final String str = "HashIndex" + System.identityHashCode(this) + ": " + this.file.getName();
        return str;
    }
    
    static int hash(final Object x) {
        int h = x.hashCode();
        h += ~(h << 9);
        h ^= h >>> 14;
        h += h << 4;
        h ^= h >>> 10;
        return h;
    }
    
    static int indexFor(final int h, final int length) {
        return h & length - 1;
    }
    
    static {
        LOG = LoggerFactory.getLogger(HashIndex.class);
        DEFAULT_PAGE_SIZE = Integer.parseInt(System.getProperty("defaultPageSize", "1024"));
        DEFAULT_KEY_SIZE = Integer.parseInt(System.getProperty("defaultKeySize", "96"));
        DEFAULT_BIN_SIZE = Integer.parseInt(System.getProperty("defaultBinSize", "1024"));
        MAXIMUM_CAPACITY = Integer.parseInt(System.getProperty("maximumCapacity", "16384"));
        DEFAULT_LOAD_FACTOR = Integer.parseInt(System.getProperty("defaultLoadFactor", "50"));
    }
}
