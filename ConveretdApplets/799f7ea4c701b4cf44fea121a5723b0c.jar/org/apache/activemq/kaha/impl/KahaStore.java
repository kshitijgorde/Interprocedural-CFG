// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl;

import org.slf4j.LoggerFactory;
import java.util.Date;
import org.apache.activemq.kaha.impl.index.RedoStoreIndexItem;
import org.apache.activemq.kaha.StoreLocation;
import org.apache.activemq.kaha.impl.data.RedoListener;
import org.apache.activemq.kaha.impl.data.DataManagerImpl;
import org.apache.activemq.kaha.impl.async.DataManagerFacade;
import org.apache.activemq.kaha.impl.async.AsyncDataManager;
import java.util.HashSet;
import java.util.Set;
import org.apache.activemq.kaha.impl.index.IndexItem;
import org.apache.activemq.kaha.ListContainer;
import org.apache.activemq.kaha.MapContainer;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import org.apache.activemq.util.IOHelper;
import java.util.concurrent.atomic.AtomicLong;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import org.apache.activemq.kaha.impl.index.IndexManager;
import org.apache.activemq.kaha.impl.container.MapContainerImpl;
import org.apache.activemq.kaha.impl.container.ListContainerImpl;
import org.apache.activemq.kaha.ContainerId;
import java.util.Map;
import java.io.File;
import org.slf4j.Logger;
import org.apache.activemq.kaha.Store;

public class KahaStore implements Store
{
    private static final String PROPERTY_PREFIX = "org.apache.activemq.kaha.Store";
    private static final boolean BROKEN_FILE_LOCK;
    private static final boolean DISABLE_LOCKING;
    private static final String LOCKSET_MONITOR = "org.apache.activemq.kaha.Store.Lock.Monitor";
    private static final Logger LOG;
    private final File directory;
    private final String mode;
    private IndexRootContainer mapsContainer;
    private IndexRootContainer listsContainer;
    private final Map<ContainerId, ListContainerImpl> lists;
    private final Map<ContainerId, MapContainerImpl> maps;
    private final Map<String, DataManager> dataManagers;
    private final Map<String, IndexManager> indexManagers;
    private boolean closed;
    private boolean initialized;
    private boolean logIndexChanges;
    private boolean useAsyncDataManager;
    private long maxDataFileLength;
    private FileLock lock;
    private boolean persistentIndex;
    private RandomAccessFile lockFile;
    private final AtomicLong storeSize;
    private String defaultContainerName;
    
    public KahaStore(final String name, final String mode) throws IOException {
        this(new File(IOHelper.toFileSystemDirectorySafeName(name)), mode, new AtomicLong());
    }
    
    public KahaStore(final File directory, final String mode) throws IOException {
        this(directory, mode, new AtomicLong());
    }
    
    public KahaStore(final String name, final String mode, final AtomicLong storeSize) throws IOException {
        this(new File(IOHelper.toFileSystemDirectorySafeName(name)), mode, storeSize);
    }
    
    public KahaStore(final File directory, final String mode, final AtomicLong storeSize) throws IOException {
        this.lists = new ConcurrentHashMap<ContainerId, ListContainerImpl>();
        this.maps = new ConcurrentHashMap<ContainerId, MapContainerImpl>();
        this.dataManagers = new ConcurrentHashMap<String, DataManager>();
        this.indexManagers = new ConcurrentHashMap<String, IndexManager>();
        this.maxDataFileLength = 33554432L;
        this.persistentIndex = true;
        this.defaultContainerName = "kaha";
        this.mode = mode;
        this.storeSize = storeSize;
        IOHelper.mkdirs(this.directory = directory);
    }
    
    @Override
    public synchronized void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            if (this.initialized) {
                this.unlock();
                for (final ListContainerImpl container : this.lists.values()) {
                    container.close();
                }
                this.lists.clear();
                for (final MapContainerImpl container2 : this.maps.values()) {
                    container2.close();
                }
                this.maps.clear();
                final Iterator<IndexManager> iter = this.indexManagers.values().iterator();
                while (iter.hasNext()) {
                    final IndexManager im = iter.next();
                    im.close();
                    iter.remove();
                }
                final Iterator<DataManager> iter2 = this.dataManagers.values().iterator();
                while (iter2.hasNext()) {
                    final DataManager dm = iter2.next();
                    dm.close();
                    iter2.remove();
                }
            }
            if (this.lockFile != null) {
                this.lockFile.close();
                this.lockFile = null;
            }
        }
    }
    
    @Override
    public synchronized void force() throws IOException {
        if (this.initialized) {
            for (final IndexManager im : this.indexManagers.values()) {
                im.force();
            }
            for (final DataManager dm : this.dataManagers.values()) {
                dm.force();
            }
        }
    }
    
    @Override
    public synchronized void clear() throws IOException {
        this.initialize();
        for (final ContainerId id : this.mapsContainer.getKeys()) {
            final MapContainer container = this.getMapContainer(id.getKey(), id.getDataContainerName());
            container.clear();
        }
        for (final ContainerId id : this.listsContainer.getKeys()) {
            final ListContainer container2 = this.getListContainer(id.getKey(), id.getDataContainerName());
            container2.clear();
        }
    }
    
    @Override
    public synchronized boolean delete() throws IOException {
        boolean result = true;
        if (this.initialized) {
            this.clear();
            final Iterator<IndexManager> iter = this.indexManagers.values().iterator();
            while (iter.hasNext()) {
                final IndexManager im = iter.next();
                result &= im.delete();
                iter.remove();
            }
            final Iterator<DataManager> iter2 = this.dataManagers.values().iterator();
            while (iter2.hasNext()) {
                final DataManager dm = iter2.next();
                result &= dm.delete();
                iter2.remove();
            }
        }
        if (this.directory != null && this.directory.isDirectory()) {
            result = IOHelper.deleteChildren(this.directory);
            final String str = result ? "successfully deleted" : "failed to delete";
            KahaStore.LOG.info("Kaha Store " + str + " data directory " + this.directory);
        }
        return result;
    }
    
    @Override
    public synchronized boolean isInitialized() {
        return this.initialized;
    }
    
    @Override
    public boolean doesMapContainerExist(final Object id) throws IOException {
        return this.doesMapContainerExist(id, this.defaultContainerName);
    }
    
    @Override
    public synchronized boolean doesMapContainerExist(final Object id, final String containerName) throws IOException {
        this.initialize();
        final ContainerId containerId = new ContainerId(id, containerName);
        return this.maps.containsKey(containerId) || this.mapsContainer.doesRootExist(containerId);
    }
    
    @Override
    public MapContainer getMapContainer(final Object id) throws IOException {
        return this.getMapContainer(id, this.defaultContainerName);
    }
    
    @Override
    public MapContainer getMapContainer(final Object id, final String containerName) throws IOException {
        return this.getMapContainer(id, containerName, this.persistentIndex);
    }
    
    @Override
    public synchronized MapContainer getMapContainer(final Object id, final String containerName, final boolean persistentIndex) throws IOException {
        this.initialize();
        final ContainerId containerId = new ContainerId(id, containerName);
        MapContainerImpl result = this.maps.get(containerId);
        if (result == null) {
            final DataManager dm = this.getDataManager(containerName);
            final IndexManager im = this.getIndexManager(dm, containerName);
            IndexItem root = this.mapsContainer.getRoot(im, containerId);
            if (root == null) {
                root = this.mapsContainer.addRoot(im, containerId);
            }
            result = new MapContainerImpl(this.directory, containerId, root, im, dm, persistentIndex);
            this.maps.put(containerId, result);
        }
        return result;
    }
    
    @Override
    public void deleteMapContainer(final Object id) throws IOException {
        this.deleteMapContainer(id, this.defaultContainerName);
    }
    
    @Override
    public void deleteMapContainer(final Object id, final String containerName) throws IOException {
        final ContainerId containerId = new ContainerId(id, containerName);
        this.deleteMapContainer(containerId);
    }
    
    @Override
    public synchronized void deleteMapContainer(final ContainerId containerId) throws IOException {
        this.initialize();
        final MapContainerImpl container = this.maps.remove(containerId);
        if (container != null) {
            container.clear();
            this.mapsContainer.removeRoot(container.getIndexManager(), containerId);
            container.close();
        }
    }
    
    @Override
    public synchronized Set<ContainerId> getMapContainerIds() throws IOException {
        this.initialize();
        final Set<ContainerId> set = new HashSet<ContainerId>();
        for (final ContainerId id : this.mapsContainer.getKeys()) {
            set.add(id);
        }
        return set;
    }
    
    @Override
    public boolean doesListContainerExist(final Object id) throws IOException {
        return this.doesListContainerExist(id, this.defaultContainerName);
    }
    
    @Override
    public synchronized boolean doesListContainerExist(final Object id, final String containerName) throws IOException {
        this.initialize();
        final ContainerId containerId = new ContainerId(id, containerName);
        return this.lists.containsKey(containerId) || this.listsContainer.doesRootExist(containerId);
    }
    
    @Override
    public ListContainer getListContainer(final Object id) throws IOException {
        return this.getListContainer(id, this.defaultContainerName);
    }
    
    @Override
    public ListContainer getListContainer(final Object id, final String containerName) throws IOException {
        return this.getListContainer(id, containerName, this.persistentIndex);
    }
    
    @Override
    public synchronized ListContainer getListContainer(final Object id, final String containerName, final boolean persistentIndex) throws IOException {
        this.initialize();
        final ContainerId containerId = new ContainerId(id, containerName);
        ListContainerImpl result = this.lists.get(containerId);
        if (result == null) {
            final DataManager dm = this.getDataManager(containerName);
            final IndexManager im = this.getIndexManager(dm, containerName);
            IndexItem root = this.listsContainer.getRoot(im, containerId);
            if (root == null) {
                root = this.listsContainer.addRoot(im, containerId);
            }
            result = new ListContainerImpl(containerId, root, im, dm, persistentIndex);
            this.lists.put(containerId, result);
        }
        return result;
    }
    
    @Override
    public void deleteListContainer(final Object id) throws IOException {
        this.deleteListContainer(id, this.defaultContainerName);
    }
    
    @Override
    public synchronized void deleteListContainer(final Object id, final String containerName) throws IOException {
        final ContainerId containerId = new ContainerId(id, containerName);
        this.deleteListContainer(containerId);
    }
    
    @Override
    public synchronized void deleteListContainer(final ContainerId containerId) throws IOException {
        this.initialize();
        final ListContainerImpl container = this.lists.remove(containerId);
        if (container != null) {
            this.listsContainer.removeRoot(container.getIndexManager(), containerId);
            container.clear();
            container.close();
        }
    }
    
    @Override
    public synchronized Set<ContainerId> getListContainerIds() throws IOException {
        this.initialize();
        final Set<ContainerId> set = new HashSet<ContainerId>();
        for (final ContainerId id : this.listsContainer.getKeys()) {
            set.add(id);
        }
        return set;
    }
    
    public IndexRootContainer getListsContainer() {
        return this.listsContainer;
    }
    
    public IndexRootContainer getMapsContainer() {
        return this.mapsContainer;
    }
    
    public synchronized DataManager getDataManager(final String name) throws IOException {
        DataManager dm = this.dataManagers.get(name);
        if (dm == null) {
            if (this.isUseAsyncDataManager()) {
                final AsyncDataManager t = new AsyncDataManager(this.storeSize);
                t.setDirectory(this.directory);
                t.setFilePrefix("async-data-" + name + "-");
                t.setMaxFileLength((int)this.maxDataFileLength);
                t.start();
                dm = new DataManagerFacade(t, name);
            }
            else {
                final DataManagerImpl t2 = new DataManagerImpl(this.directory, name, this.storeSize);
                t2.setMaxFileLength(this.maxDataFileLength);
                dm = t2;
            }
            if (this.logIndexChanges) {
                this.recover(dm);
            }
            this.dataManagers.put(name, dm);
        }
        return dm;
    }
    
    public synchronized IndexManager getIndexManager(final DataManager dm, final String name) throws IOException {
        IndexManager im = this.indexManagers.get(name);
        if (im == null) {
            im = new IndexManager(this.directory, name, this.mode, this.logIndexChanges ? dm : null, this.storeSize);
            this.indexManagers.put(name, im);
        }
        return im;
    }
    
    private void recover(final DataManager dm) throws IOException {
        dm.recoverRedoItems(new RedoListener() {
            @Override
            public void onRedoItem(final StoreLocation item, final Object o) throws Exception {
                final RedoStoreIndexItem redo = (RedoStoreIndexItem)o;
                final IndexManager im = KahaStore.this.getIndexManager(dm, dm.getName());
                im.redo(redo);
            }
        });
    }
    
    public synchronized boolean isLogIndexChanges() {
        return this.logIndexChanges;
    }
    
    public synchronized void setLogIndexChanges(final boolean logIndexChanges) {
        this.logIndexChanges = logIndexChanges;
    }
    
    @Override
    public synchronized long getMaxDataFileLength() {
        return this.maxDataFileLength;
    }
    
    @Override
    public synchronized void setMaxDataFileLength(final long maxDataFileLength) {
        this.maxDataFileLength = maxDataFileLength;
    }
    
    public synchronized String getIndexTypeAsString() {
        return this.persistentIndex ? "PERSISTENT" : "VM";
    }
    
    public synchronized void setIndexTypeAsString(final String type) {
        if (type.equalsIgnoreCase("VM")) {
            this.persistentIndex = false;
        }
        else {
            this.persistentIndex = true;
        }
    }
    
    @Override
    public boolean isPersistentIndex() {
        return this.persistentIndex;
    }
    
    @Override
    public void setPersistentIndex(final boolean persistentIndex) {
        this.persistentIndex = persistentIndex;
    }
    
    public synchronized boolean isUseAsyncDataManager() {
        return this.useAsyncDataManager;
    }
    
    public synchronized void setUseAsyncDataManager(final boolean useAsyncWriter) {
        this.useAsyncDataManager = useAsyncWriter;
    }
    
    @Override
    public long size() {
        return this.storeSize.get();
    }
    
    @Override
    public String getDefaultContainerName() {
        return this.defaultContainerName;
    }
    
    @Override
    public void setDefaultContainerName(final String defaultContainerName) {
        this.defaultContainerName = defaultContainerName;
    }
    
    @Override
    public synchronized void initialize() throws IOException {
        if (this.closed) {
            throw new IOException("Store has been closed.");
        }
        if (!this.initialized) {
            KahaStore.LOG.info("Kaha Store using data directory " + this.directory);
            this.lockFile = new RandomAccessFile(new File(this.directory, "lock"), "rw");
            this.lock();
            final DataManager defaultDM = this.getDataManager(this.defaultContainerName);
            final IndexManager rootIndexManager = this.getIndexManager(defaultDM, this.defaultContainerName);
            IndexItem mapRoot = new IndexItem();
            IndexItem listRoot = new IndexItem();
            if (rootIndexManager.isEmpty()) {
                mapRoot.setOffset(0L);
                rootIndexManager.storeIndex(mapRoot);
                listRoot.setOffset(51L);
                rootIndexManager.storeIndex(listRoot);
                rootIndexManager.setLength(102L);
            }
            else {
                mapRoot = rootIndexManager.getIndex(0L);
                listRoot = rootIndexManager.getIndex(51L);
            }
            this.initialized = true;
            this.mapsContainer = new IndexRootContainer(mapRoot, rootIndexManager, defaultDM);
            this.listsContainer = new IndexRootContainer(listRoot, rootIndexManager, defaultDM);
            this.generateInterestInMapDataFiles();
            this.generateInterestInListDataFiles();
            for (final DataManager dm : this.dataManagers.values()) {
                dm.consolidateDataFiles();
            }
        }
    }
    
    private void lock() throws IOException {
        synchronized ("org.apache.activemq.kaha.Store.Lock.Monitor") {
            if (!KahaStore.DISABLE_LOCKING && this.directory != null && this.lock == null) {
                final String key = this.getPropertyKey();
                final String property = System.getProperty(key);
                if (null != property) {
                    throw new StoreLockedExcpetion("Kaha Store " + this.directory.getName() + " is already opened by this application.");
                }
                if (!KahaStore.BROKEN_FILE_LOCK) {
                    this.lock = this.lockFile.getChannel().tryLock(0L, this.lockFile.getChannel().size(), false);
                    if (this.lock == null) {
                        throw new StoreLockedExcpetion("Kaha Store " + this.directory.getName() + "  is already opened by another application");
                    }
                    System.setProperty(key, new Date().toString());
                }
            }
        }
    }
    
    private void unlock() throws IOException {
        synchronized ("org.apache.activemq.kaha.Store.Lock.Monitor") {
            if (!KahaStore.DISABLE_LOCKING && null != this.directory && null != this.lock) {
                System.getProperties().remove(this.getPropertyKey());
                if (this.lock.isValid()) {
                    this.lock.release();
                }
                this.lock = null;
            }
        }
    }
    
    private String getPropertyKey() throws IOException {
        return this.getClass().getName() + ".lock." + this.directory.getCanonicalPath();
    }
    
    private void generateInterestInListDataFiles() throws IOException {
        for (final ContainerId id : this.listsContainer.getKeys()) {
            final DataManager dm = this.getDataManager(id.getDataContainerName());
            final IndexManager im = this.getIndexManager(dm, id.getDataContainerName());
            final IndexItem theRoot = this.listsContainer.getRoot(im, id);
            IndexItem item;
            for (long nextItem = theRoot.getNextItem(); nextItem != -1L; nextItem = item.getNextItem()) {
                item = im.getIndex(nextItem);
                item.setOffset(nextItem);
                dm.addInterestInFile(item.getKeyFile());
                dm.addInterestInFile(item.getValueFile());
            }
        }
    }
    
    private void generateInterestInMapDataFiles() throws IOException {
        for (final ContainerId id : this.mapsContainer.getKeys()) {
            final DataManager dm = this.getDataManager(id.getDataContainerName());
            final IndexManager im = this.getIndexManager(dm, id.getDataContainerName());
            final IndexItem theRoot = this.mapsContainer.getRoot(im, id);
            IndexItem item;
            for (long nextItem = theRoot.getNextItem(); nextItem != -1L; nextItem = item.getNextItem()) {
                item = im.getIndex(nextItem);
                item.setOffset(nextItem);
                dm.addInterestInFile(item.getKeyFile());
                dm.addInterestInFile(item.getValueFile());
            }
        }
    }
    
    static {
        BROKEN_FILE_LOCK = "true".equals(System.getProperty("org.apache.activemq.kaha.Store.FileLockBroken", "false"));
        DISABLE_LOCKING = "true".equals(System.getProperty("org.apache.activemq.kaha.Store.DisableLocking", "false"));
        LOG = LoggerFactory.getLogger(KahaStore.class);
    }
}
