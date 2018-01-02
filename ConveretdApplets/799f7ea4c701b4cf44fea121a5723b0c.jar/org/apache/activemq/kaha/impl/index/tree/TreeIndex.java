// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index.tree;

import org.slf4j.LoggerFactory;
import org.apache.activemq.util.IOHelper;
import org.apache.activemq.kaha.StoreEntry;
import java.io.DataOutput;
import java.io.DataInput;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.activemq.kaha.Marshaller;
import org.apache.activemq.util.DataByteArrayOutputStream;
import org.apache.activemq.util.DataByteArrayInputStream;
import org.apache.activemq.util.LRUCache;
import org.apache.activemq.kaha.impl.index.IndexManager;
import java.io.RandomAccessFile;
import java.io.File;
import org.slf4j.Logger;
import org.apache.activemq.kaha.impl.index.Index;

public class TreeIndex implements Index
{
    private static final String NAME_PREFIX = "tree-index-";
    private static final int DEFAULT_PAGE_SIZE;
    private static final int DEFAULT_KEY_SIZE;
    private static final Logger LOG;
    private final String name;
    private File directory;
    private File file;
    private RandomAccessFile indexFile;
    private IndexManager indexManager;
    private int pageSize;
    private int keySize;
    private int keysPerPage;
    private TreePage root;
    private LRUCache<Long, TreePage> pageCache;
    private DataByteArrayInputStream dataIn;
    private DataByteArrayOutputStream dataOut;
    private byte[] readBuffer;
    private Marshaller keyMarshaller;
    private long length;
    private TreePage firstFree;
    private TreePage lastFree;
    private AtomicBoolean loaded;
    private boolean enablePageCaching;
    private int pageCacheSize;
    
    public TreeIndex(final File directory, final String name, final IndexManager indexManager) throws IOException {
        this.pageSize = TreeIndex.DEFAULT_PAGE_SIZE;
        this.keySize = TreeIndex.DEFAULT_KEY_SIZE;
        this.keysPerPage = this.pageSize / this.keySize;
        this.loaded = new AtomicBoolean();
        this.enablePageCaching = true;
        this.pageCacheSize = 10;
        this.directory = directory;
        this.name = name;
        this.indexManager = indexManager;
        this.pageCache = new LRUCache<Long, TreePage>(this.pageCacheSize, this.pageCacheSize, 0.75f, true);
        this.openIndexFile();
    }
    
    @Override
    public void setKeyMarshaller(final Marshaller marshaller) {
        this.keyMarshaller = marshaller;
    }
    
    public int getKeySize() {
        return this.keySize;
    }
    
    public void setKeySize(final int keySize) {
        this.keySize = keySize;
        if (this.loaded.get()) {
            throw new RuntimeException("Pages already loaded - can't reset key size");
        }
    }
    
    public int getPageSize() {
        return this.pageSize;
    }
    
    public void setPageSize(final int pageSize) {
        if (this.loaded.get() && pageSize != this.pageSize) {
            throw new RuntimeException("Pages already loaded - can't reset page size");
        }
        this.pageSize = pageSize;
    }
    
    @Override
    public boolean isTransient() {
        return false;
    }
    
    public boolean isEnablePageCaching() {
        return this.enablePageCaching;
    }
    
    public void setEnablePageCaching(final boolean enablePageCaching) {
        this.enablePageCaching = enablePageCaching;
    }
    
    public int getPageCacheSize() {
        return this.pageCacheSize;
    }
    
    public void setPageCacheSize(final int pageCacheSize) {
        this.pageCacheSize = pageCacheSize;
        this.pageCache.setMaxCacheSize(pageCacheSize);
    }
    
    @Override
    public void load() {
        if (this.loaded.compareAndSet(false, true)) {
            this.keysPerPage = this.pageSize / this.keySize;
            this.dataIn = new DataByteArrayInputStream();
            this.dataOut = new DataByteArrayOutputStream(this.pageSize);
            this.readBuffer = new byte[this.pageSize];
            try {
                this.openIndexFile();
                long offset;
                for (offset = 0L; offset + this.pageSize <= this.indexFile.length(); offset += this.pageSize) {
                    this.indexFile.seek(offset);
                    this.indexFile.readFully(this.readBuffer, 0, 18);
                    this.dataIn.restart(this.readBuffer);
                    final TreePage page = new TreePage(this.keysPerPage);
                    page.setTree(this);
                    page.setId(offset);
                    page.readHeader(this.dataIn);
                    if (!page.isActive()) {
                        if (this.lastFree != null) {
                            this.lastFree.setNextFreePageId(offset);
                            this.indexFile.seek(this.lastFree.getId());
                            this.dataOut.reset();
                            this.lastFree.writeHeader(this.dataOut);
                            this.indexFile.write(this.dataOut.getData(), 0, 18);
                            this.lastFree = page;
                        }
                        else {
                            this.lastFree = page;
                            this.firstFree = page;
                        }
                    }
                    else if (this.root == null && page.isRoot()) {
                        this.root = this.getFullPage(offset);
                    }
                }
                this.length = offset;
                if (this.root == null) {
                    this.root = this.createRoot();
                }
            }
            catch (IOException e) {
                TreeIndex.LOG.error("Failed to load index ", e);
                throw new RuntimeException(e);
            }
        }
    }
    
    @Override
    public void unload() throws IOException {
        if (this.loaded.compareAndSet(true, false) && this.indexFile != null) {
            this.indexFile.close();
            this.indexFile = null;
            this.pageCache.clear();
            this.root = null;
            this.firstFree = null;
            this.lastFree = null;
        }
    }
    
    @Override
    public void store(final Object key, final StoreEntry value) throws IOException {
        final TreeEntry entry = new TreeEntry();
        entry.setKey((Comparable)key);
        entry.setIndexOffset(value.getOffset());
        this.root.put(entry);
    }
    
    @Override
    public StoreEntry get(final Object key) throws IOException {
        final TreeEntry entry = new TreeEntry();
        entry.setKey((Comparable)key);
        final TreeEntry result = this.root.find(entry);
        return (result != null) ? this.indexManager.getIndex(result.getIndexOffset()) : null;
    }
    
    @Override
    public StoreEntry remove(final Object key) throws IOException {
        final TreeEntry entry = new TreeEntry();
        entry.setKey((Comparable)key);
        final TreeEntry result = this.root.remove(entry);
        return (result != null) ? this.indexManager.getIndex(result.getIndexOffset()) : null;
    }
    
    @Override
    public boolean containsKey(final Object key) throws IOException {
        final TreeEntry entry = new TreeEntry();
        entry.setKey((Comparable)key);
        return this.root.find(entry) != null;
    }
    
    @Override
    public void clear() throws IOException {
        this.unload();
        this.delete();
        this.openIndexFile();
        this.load();
    }
    
    @Override
    public void delete() throws IOException {
        this.unload();
        if (this.file.exists()) {
            final boolean result = this.file.delete();
        }
        this.length = 0L;
    }
    
    TreePage getRoot() {
        return this.root;
    }
    
    TreePage lookupPage(final long pageId) throws IOException {
        TreePage result = null;
        if (pageId >= 0L) {
            if (this.root != null && this.root.getId() == pageId) {
                result = this.root;
            }
            else {
                result = this.getFromCache(pageId);
            }
            if (result == null) {
                result = this.getFullPage(pageId);
                if (result != null) {
                    if (!result.isActive()) {
                        throw new IllegalStateException("Trying to access an inactive page: " + pageId + " root is " + this.root);
                    }
                    this.addToCache(result);
                }
            }
        }
        return result;
    }
    
    TreePage createRoot() throws IOException {
        final TreePage result = this.createPage(-1L);
        return this.root = result;
    }
    
    TreePage createPage(final long parentId) throws IOException {
        TreePage result = this.getNextFreePage();
        if (result == null) {
            result = new TreePage(this.keysPerPage);
            result.setId(this.length);
            result.setTree(this);
            result.setParentId(parentId);
            this.writePage(result);
            this.length += this.pageSize;
            this.indexFile.seek(this.length);
            this.indexFile.write(-1);
        }
        this.addToCache(result);
        return result;
    }
    
    void releasePage(final TreePage page) throws IOException {
        this.removeFromCache(page);
        page.reset();
        page.setActive(false);
        if (this.lastFree == null) {
            this.firstFree = page;
            this.lastFree = page;
        }
        else {
            this.lastFree.setNextFreePageId(page.getId());
            this.writePage(this.lastFree);
        }
        this.writePage(page);
    }
    
    private TreePage getNextFreePage() throws IOException {
        TreePage result = null;
        if (this.firstFree != null) {
            if (this.firstFree.equals(this.lastFree)) {
                result = this.firstFree;
                this.firstFree = null;
                this.lastFree = null;
            }
            else {
                result = this.firstFree;
                this.firstFree = this.getPage(this.firstFree.getNextFreePageId());
                if (this.firstFree == null) {
                    this.lastFree = null;
                }
            }
            result.setActive(true);
            result.reset();
            result.saveHeader();
        }
        return result;
    }
    
    void writeFullPage(final TreePage page) throws IOException {
        this.dataOut.reset();
        page.write(this.keyMarshaller, this.dataOut);
        if (this.dataOut.size() > this.pageSize) {
            throw new IOException("Page Size overflow: pageSize is " + this.pageSize + " trying to write " + this.dataOut.size());
        }
        this.indexFile.seek(page.getId());
        this.indexFile.write(this.dataOut.getData(), 0, this.dataOut.size());
    }
    
    void writePage(final TreePage page) throws IOException {
        this.dataOut.reset();
        page.writeHeader(this.dataOut);
        this.indexFile.seek(page.getId());
        this.indexFile.write(this.dataOut.getData(), 0, 18);
    }
    
    TreePage getFullPage(final long id) throws IOException {
        this.indexFile.seek(id);
        this.indexFile.readFully(this.readBuffer, 0, this.pageSize);
        this.dataIn.restart(this.readBuffer);
        final TreePage page = new TreePage(this.keysPerPage);
        page.setId(id);
        page.setTree(this);
        page.read(this.keyMarshaller, this.dataIn);
        return page;
    }
    
    TreePage getPage(final long id) throws IOException {
        this.indexFile.seek(id);
        this.indexFile.readFully(this.readBuffer, 0, 18);
        this.dataIn.restart(this.readBuffer);
        final TreePage page = new TreePage(this.keysPerPage);
        page.setId(id);
        page.setTree(this);
        page.readHeader(this.dataIn);
        return page;
    }
    
    private TreePage getFromCache(final long pageId) {
        TreePage result = null;
        if (this.enablePageCaching) {
            result = this.pageCache.get(pageId);
        }
        return result;
    }
    
    private void addToCache(final TreePage page) {
        if (this.enablePageCaching) {
            this.pageCache.put(page.getId(), page);
        }
    }
    
    private void removeFromCache(final TreePage page) {
        if (this.enablePageCaching) {
            this.pageCache.remove(page.getId());
        }
    }
    
    protected void openIndexFile() throws IOException {
        if (this.indexFile == null) {
            this.file = new File(this.directory, "tree-index-" + IOHelper.toFileSystemSafeName(this.name));
            IOHelper.mkdirs(this.file.getParentFile());
            this.indexFile = new RandomAccessFile(this.file, "rw");
        }
    }
    
    @Override
    public int getSize() {
        return 0;
    }
    
    static {
        LOG = LoggerFactory.getLogger(TreeIndex.class);
        DEFAULT_PAGE_SIZE = Integer.parseInt(System.getProperty("defaultPageSize", "16384"));
        DEFAULT_KEY_SIZE = Integer.parseInt(System.getProperty("defaultKeySize", "96"));
    }
}
