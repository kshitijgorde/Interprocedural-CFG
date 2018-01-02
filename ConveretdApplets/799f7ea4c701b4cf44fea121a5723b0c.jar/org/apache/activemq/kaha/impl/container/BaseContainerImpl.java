// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.container;

import org.slf4j.LoggerFactory;
import java.util.List;
import org.apache.activemq.kaha.RuntimeStoreException;
import java.util.ArrayList;
import java.io.IOException;
import org.apache.activemq.kaha.StoreEntry;
import org.apache.activemq.kaha.impl.index.VMIndexLinkedList;
import org.apache.activemq.kaha.impl.index.DiskIndexLinkedList;
import org.apache.activemq.kaha.ContainerId;
import org.apache.activemq.kaha.impl.DataManager;
import org.apache.activemq.kaha.impl.index.IndexManager;
import org.apache.activemq.kaha.impl.index.IndexLinkedList;
import org.apache.activemq.kaha.impl.index.IndexItem;
import org.slf4j.Logger;

public abstract class BaseContainerImpl
{
    private static final Logger LOG;
    protected IndexItem root;
    protected IndexLinkedList indexList;
    protected IndexManager indexManager;
    protected DataManager dataManager;
    protected ContainerId containerId;
    protected boolean loaded;
    protected boolean closed;
    protected boolean initialized;
    protected boolean persistentIndex;
    
    protected BaseContainerImpl(final ContainerId id, final IndexItem root, final IndexManager indexManager, final DataManager dataManager, final boolean persistentIndex) {
        this.containerId = id;
        this.root = root;
        this.indexManager = indexManager;
        this.dataManager = dataManager;
        this.persistentIndex = persistentIndex;
    }
    
    public ContainerId getContainerId() {
        return this.containerId;
    }
    
    public synchronized void init() {
        if (!this.initialized && !this.initialized) {
            this.initialized = true;
            if (this.indexList == null) {
                if (this.persistentIndex) {
                    this.indexList = new DiskIndexLinkedList(this.indexManager, this.root);
                }
                else {
                    this.indexList = new VMIndexLinkedList(this.root);
                }
            }
        }
    }
    
    public synchronized void clear() {
        if (this.indexList != null) {
            this.indexList.clear();
        }
    }
    
    public IndexLinkedList getList() {
        return this.indexList;
    }
    
    public void setList(final IndexLinkedList indexList) {
        this.indexList = indexList;
    }
    
    public abstract void unload();
    
    public abstract void load();
    
    public abstract int size();
    
    protected abstract Object getValue(final StoreEntry p0);
    
    protected abstract void remove(final IndexItem p0);
    
    protected final synchronized IndexLinkedList getInternalList() {
        return this.indexList;
    }
    
    public final synchronized void close() {
        this.unload();
        this.closed = true;
    }
    
    public final synchronized boolean isLoaded() {
        this.checkClosed();
        return this.loaded;
    }
    
    public final Object getId() {
        this.checkClosed();
        return this.containerId.getKey();
    }
    
    public DataManager getDataManager() {
        return this.dataManager;
    }
    
    public IndexManager getIndexManager() {
        return this.indexManager;
    }
    
    public final synchronized void expressDataInterest() throws IOException {
        IndexItem item;
        for (long nextItem = this.root.getNextItem(); nextItem != -1L; nextItem = item.getNextItem()) {
            item = this.indexManager.getIndex(nextItem);
            item.setOffset(nextItem);
            this.dataManager.addInterestInFile(item.getKeyFile());
            this.dataManager.addInterestInFile(item.getValueFile());
        }
    }
    
    protected final void doClear() {
        this.checkClosed();
        this.loaded = true;
        final List<IndexItem> indexList = new ArrayList<IndexItem>();
        try {
            this.init();
            IndexItem item;
            for (long nextItem = this.root.getNextItem(); nextItem != -1L; nextItem = item.getNextItem()) {
                item = new IndexItem();
                item.setOffset(nextItem);
                indexList.add(item);
            }
            this.root.setNextItem(-1L);
            this.storeIndex(this.root);
            for (int i = 0; i < indexList.size(); ++i) {
                final IndexItem item2 = indexList.get(i);
                this.dataManager.removeInterestInFile(item2.getKeyFile());
                this.dataManager.removeInterestInFile(item2.getValueFile());
                this.indexManager.freeIndex(item2);
            }
            indexList.clear();
        }
        catch (IOException e) {
            BaseContainerImpl.LOG.error("Failed to clear Container " + this.getId(), e);
            throw new RuntimeStoreException(e);
        }
    }
    
    protected final void delete(final IndexItem keyItem, final IndexItem prevItem, final IndexItem nextItem) {
        if (keyItem != null) {
            try {
                this.root = this.indexList.getRoot();
                final IndexItem prev = (prevItem == null) ? this.root : prevItem;
                final IndexItem next = (nextItem == null || !nextItem.equals(this.root)) ? nextItem : null;
                this.dataManager.removeInterestInFile(keyItem.getKeyFile());
                this.dataManager.removeInterestInFile(keyItem.getValueFile());
                if (next != null) {
                    prev.setNextItem(next.getOffset());
                    next.setPreviousItem(prev.getOffset());
                    this.updateIndexes(next);
                }
                else {
                    prev.setNextItem(-1L);
                }
                this.updateIndexes(prev);
                this.indexManager.freeIndex(keyItem);
            }
            catch (IOException e) {
                BaseContainerImpl.LOG.error("Failed to delete " + keyItem, e);
                throw new RuntimeStoreException(e);
            }
        }
    }
    
    protected final void checkClosed() {
        if (this.closed) {
            throw new RuntimeStoreException("The store is closed");
        }
    }
    
    protected void storeIndex(final IndexItem item) throws IOException {
        this.indexManager.storeIndex(item);
    }
    
    protected void updateIndexes(final IndexItem item) throws IOException {
        this.indexManager.updateIndexes(item);
    }
    
    protected final boolean isRoot(final StoreEntry item) {
        return item != null && this.root != null && (this.root == item || this.root.getOffset() == item.getOffset());
    }
    
    static {
        LOG = LoggerFactory.getLogger(BaseContainerImpl.class);
    }
}
