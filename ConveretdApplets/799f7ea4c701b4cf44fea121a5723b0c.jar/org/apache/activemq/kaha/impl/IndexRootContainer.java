// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl;

import org.slf4j.LoggerFactory;
import org.apache.activemq.kaha.Store;
import org.apache.activemq.kaha.ContainerId;
import java.util.Set;
import java.io.IOException;
import org.apache.activemq.kaha.StoreLocation;
import java.util.concurrent.ConcurrentHashMap;
import java.util.LinkedList;
import org.apache.activemq.kaha.StoreEntry;
import java.util.Map;
import org.apache.activemq.kaha.impl.index.IndexManager;
import org.apache.activemq.kaha.impl.index.IndexItem;
import org.slf4j.Logger;
import org.apache.activemq.kaha.Marshaller;

class IndexRootContainer
{
    protected static final Marshaller ROOT_MARSHALLER;
    private static final Logger LOG;
    protected IndexItem root;
    protected IndexManager indexManager;
    protected DataManager dataManager;
    protected Map<Object, StoreEntry> map;
    protected LinkedList<StoreEntry> list;
    
    IndexRootContainer(final IndexItem root, final IndexManager im, final DataManager dfm) throws IOException {
        this.map = new ConcurrentHashMap<Object, StoreEntry>();
        this.list = new LinkedList<StoreEntry>();
        this.root = root;
        this.indexManager = im;
        this.dataManager = dfm;
        long nextItem = root.getNextItem();
        while (nextItem != -1L) {
            final StoreEntry item = this.indexManager.getIndex(nextItem);
            final StoreLocation data = item.getKeyDataItem();
            final Object key = this.dataManager.readItem(IndexRootContainer.ROOT_MARSHALLER, data);
            this.map.put(key, item);
            this.list.add(item);
            nextItem = item.getNextItem();
            this.dataManager.addInterestInFile(item.getKeyFile());
        }
    }
    
    Set<Object> getKeys() {
        return this.map.keySet();
    }
    
    IndexItem addRoot(final IndexManager containerIndexManager, final ContainerId key) throws IOException {
        if (this.map.containsKey(key)) {
            this.removeRoot(containerIndexManager, key);
        }
        final StoreLocation data = this.dataManager.storeDataItem(IndexRootContainer.ROOT_MARSHALLER, key);
        final IndexItem newRoot = this.indexManager.createNewIndex();
        newRoot.setKeyData(data);
        final IndexItem containerRoot = containerIndexManager.createNewIndex();
        containerIndexManager.storeIndex(containerRoot);
        newRoot.setValueOffset(containerRoot.getOffset());
        IndexItem last = this.list.isEmpty() ? null : this.list.getLast();
        last = ((last == null) ? this.root : last);
        final long prev = last.getOffset();
        newRoot.setPreviousItem(prev);
        this.indexManager.storeIndex(newRoot);
        last.setNextItem(newRoot.getOffset());
        this.indexManager.storeIndex(last);
        this.map.put(key, newRoot);
        this.list.add(newRoot);
        return containerRoot;
    }
    
    void removeRoot(final IndexManager containerIndexManager, final ContainerId key) throws IOException {
        final StoreEntry oldRoot = this.map.remove(key);
        if (oldRoot != null) {
            this.dataManager.removeInterestInFile(oldRoot.getKeyFile());
            final IndexItem containerRoot = containerIndexManager.getIndex(oldRoot.getValueOffset());
            if (containerRoot != null) {
                containerIndexManager.freeIndex(containerRoot);
            }
            final int index = this.list.indexOf(oldRoot);
            IndexItem prev = (index > 0) ? this.list.get(index - 1) : this.root;
            prev = ((prev == null) ? this.root : prev);
            final IndexItem next = (index < this.list.size() - 1) ? this.list.get(index + 1) : null;
            if (next != null) {
                prev.setNextItem(next.getOffset());
                next.setPreviousItem(prev.getOffset());
                this.indexManager.updateIndexes(next);
            }
            else {
                prev.setNextItem(-1L);
            }
            this.indexManager.updateIndexes(prev);
            this.list.remove(oldRoot);
            this.indexManager.freeIndex((IndexItem)oldRoot);
        }
    }
    
    IndexItem getRoot(final IndexManager containerIndexManager, final ContainerId key) throws IOException {
        final StoreEntry index = this.map.get(key);
        if (index != null) {
            return containerIndexManager.getIndex(index.getValueOffset());
        }
        return null;
    }
    
    boolean doesRootExist(final Object key) {
        return this.map.containsKey(key);
    }
    
    static {
        ROOT_MARSHALLER = Store.OBJECT_MARSHALLER;
        LOG = LoggerFactory.getLogger(IndexRootContainer.class);
    }
}
