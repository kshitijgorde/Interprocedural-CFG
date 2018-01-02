// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.container;

import org.slf4j.LoggerFactory;
import org.apache.activemq.kaha.IndexMBean;
import org.apache.activemq.kaha.impl.index.IndexLinkedList;
import java.util.Collection;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import org.apache.activemq.kaha.StoreLocation;
import org.apache.activemq.kaha.RuntimeStoreException;
import org.apache.activemq.kaha.StoreEntry;
import org.apache.activemq.kaha.impl.index.VMIndex;
import java.io.IOException;
import org.apache.activemq.kaha.impl.index.hash.HashIndex;
import org.apache.activemq.kaha.Store;
import org.apache.activemq.kaha.impl.DataManager;
import org.apache.activemq.kaha.impl.index.IndexManager;
import org.apache.activemq.kaha.impl.index.IndexItem;
import org.apache.activemq.kaha.ContainerId;
import java.io.File;
import org.apache.activemq.kaha.Marshaller;
import org.apache.activemq.kaha.impl.index.Index;
import org.slf4j.Logger;
import org.apache.activemq.kaha.MapContainer;

public final class MapContainerImpl extends BaseContainerImpl implements MapContainer
{
    private static final Logger LOG;
    protected Index index;
    protected Marshaller keyMarshaller;
    protected Marshaller valueMarshaller;
    protected File directory;
    private int indexBinSize;
    private int indexKeySize;
    private int indexPageSize;
    private int indexMaxBinSize;
    private int indexLoadFactor;
    
    public MapContainerImpl(final File directory, final ContainerId id, final IndexItem root, final IndexManager indexManager, final DataManager dataManager, final boolean persistentIndex) {
        super(id, root, indexManager, dataManager, persistentIndex);
        this.keyMarshaller = Store.OBJECT_MARSHALLER;
        this.valueMarshaller = Store.OBJECT_MARSHALLER;
        this.indexBinSize = HashIndex.DEFAULT_BIN_SIZE;
        this.indexKeySize = HashIndex.DEFAULT_KEY_SIZE;
        this.indexPageSize = HashIndex.DEFAULT_PAGE_SIZE;
        this.indexMaxBinSize = HashIndex.MAXIMUM_CAPACITY;
        this.indexLoadFactor = HashIndex.DEFAULT_LOAD_FACTOR;
        this.directory = directory;
    }
    
    @Override
    public synchronized void init() {
        super.init();
        if (this.index == null) {
            if (this.persistentIndex) {
                final String name = this.containerId.getDataContainerName() + "_" + this.containerId.getKey();
                try {
                    final HashIndex hashIndex = new HashIndex(this.directory, name, this.indexManager);
                    hashIndex.setNumberOfBins(this.getIndexBinSize());
                    hashIndex.setKeySize(this.getIndexKeySize());
                    hashIndex.setPageSize(this.getIndexPageSize());
                    hashIndex.setMaximumCapacity(this.getIndexMaxBinSize());
                    hashIndex.setLoadFactor(this.getIndexLoadFactor());
                    this.index = hashIndex;
                }
                catch (IOException e) {
                    MapContainerImpl.LOG.error("Failed to create HashIndex", e);
                    throw new RuntimeException(e);
                }
            }
            else {
                this.index = new VMIndex(this.indexManager);
            }
        }
        this.index.setKeyMarshaller(this.keyMarshaller);
    }
    
    @Override
    public synchronized void load() {
        this.checkClosed();
        if (!this.loaded && !this.loaded) {
            this.loaded = true;
            try {
                this.init();
                this.index.load();
                IndexItem item;
                for (long nextItem = this.root.getNextItem(); nextItem != -1L; nextItem = item.getNextItem()) {
                    item = this.indexManager.getIndex(nextItem);
                    final StoreLocation data = item.getKeyDataItem();
                    final Object key = this.dataManager.readItem(this.keyMarshaller, data);
                    if (this.index.isTransient()) {
                        this.index.store(key, item);
                    }
                    this.indexList.add(item);
                }
            }
            catch (IOException e) {
                MapContainerImpl.LOG.error("Failed to load container " + this.getId(), e);
                throw new RuntimeStoreException(e);
            }
        }
    }
    
    @Override
    public synchronized void unload() {
        this.checkClosed();
        if (this.loaded) {
            this.loaded = false;
            try {
                this.index.unload();
            }
            catch (IOException e) {
                MapContainerImpl.LOG.warn("Failed to unload the index", e);
            }
            this.indexList.clear();
        }
    }
    
    @Override
    public synchronized void delete() {
        this.unload();
        try {
            this.index.delete();
        }
        catch (IOException e) {
            MapContainerImpl.LOG.warn("Failed to unload the index", e);
        }
    }
    
    @Override
    public synchronized void setKeyMarshaller(final Marshaller keyMarshaller) {
        this.checkClosed();
        this.keyMarshaller = keyMarshaller;
        if (this.index != null) {
            this.index.setKeyMarshaller(keyMarshaller);
        }
    }
    
    @Override
    public synchronized void setValueMarshaller(final Marshaller valueMarshaller) {
        this.checkClosed();
        this.valueMarshaller = valueMarshaller;
    }
    
    @Override
    public synchronized int size() {
        this.load();
        return this.indexList.size();
    }
    
    @Override
    public synchronized boolean isEmpty() {
        this.load();
        return this.indexList.isEmpty();
    }
    
    @Override
    public synchronized boolean containsKey(final Object key) {
        this.load();
        try {
            return this.index.containsKey(key);
        }
        catch (IOException e) {
            MapContainerImpl.LOG.error("Failed trying to find key: " + key, e);
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public synchronized Object get(final Object key) {
        this.load();
        Object result = null;
        StoreEntry item = null;
        try {
            item = this.index.get(key);
        }
        catch (IOException e) {
            MapContainerImpl.LOG.error("Failed trying to get key: " + key, e);
            throw new RuntimeException(e);
        }
        if (item != null) {
            result = this.getValue(item);
        }
        return result;
    }
    
    @Override
    public synchronized StoreEntry getEntry(final Object key) {
        this.load();
        StoreEntry item = null;
        try {
            item = this.index.get(key);
        }
        catch (IOException e) {
            MapContainerImpl.LOG.error("Failed trying to get key: " + key, e);
            throw new RuntimeException(e);
        }
        return item;
    }
    
    @Override
    public synchronized boolean containsValue(final Object o) {
        this.load();
        boolean result = false;
        if (o != null) {
            for (IndexItem item = this.indexList.getFirst(); item != null; item = this.indexList.getNextEntry(item)) {
                final Object value = this.getValue(item);
                if (value != null && value.equals(o)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
    
    @Override
    public synchronized void putAll(final Map t) {
        this.load();
        if (t != null) {
            for (final Map.Entry entry : t.entrySet()) {
                this.put(entry.getKey(), entry.getValue());
            }
        }
    }
    
    @Override
    public synchronized Set keySet() {
        this.load();
        return new ContainerKeySet(this);
    }
    
    @Override
    public synchronized Collection values() {
        this.load();
        return new ContainerValueCollection(this);
    }
    
    @Override
    public synchronized Set entrySet() {
        this.load();
        return new ContainerEntrySet(this);
    }
    
    @Override
    public synchronized Object put(final Object key, final Object value) {
        this.load();
        final Object result = this.remove(key);
        final IndexItem item = this.write(key, value);
        try {
            this.index.store(key, item);
        }
        catch (IOException e) {
            MapContainerImpl.LOG.error("Failed trying to insert key: " + key, e);
            throw new RuntimeException(e);
        }
        this.indexList.add(item);
        return result;
    }
    
    @Override
    public synchronized Object remove(final Object key) {
        this.load();
        try {
            Object result = null;
            IndexItem item = (IndexItem)this.index.remove(key);
            if (item != null) {
                item = (IndexItem)this.indexList.refreshEntry(item);
                result = this.getValue(item);
                final IndexItem prev = this.indexList.getPrevEntry(item);
                final IndexItem next = this.indexList.getNextEntry(item);
                this.indexList.remove(item);
                this.delete(item, prev, next);
            }
            return result;
        }
        catch (IOException e) {
            MapContainerImpl.LOG.error("Failed trying to remove key: " + key, e);
            throw new RuntimeException(e);
        }
    }
    
    public synchronized boolean removeValue(final Object o) {
        this.load();
        boolean result = false;
        if (o != null) {
            IndexItem item = this.indexList.getFirst();
            while (item != null) {
                final Object value = this.getValue(item);
                if (value != null && value.equals(o)) {
                    result = true;
                    final Object key = this.getKey(item);
                    if (key != null) {
                        this.remove(key);
                        break;
                    }
                    break;
                }
                else {
                    item = this.indexList.getNextEntry(item);
                }
            }
        }
        return result;
    }
    
    @Override
    protected synchronized void remove(final IndexItem item) {
        final Object key = this.getKey(item);
        if (key != null) {
            this.remove(key);
        }
    }
    
    @Override
    public synchronized void clear() {
        this.checkClosed();
        this.loaded = true;
        this.init();
        if (this.index != null) {
            try {
                this.index.clear();
            }
            catch (IOException e) {
                MapContainerImpl.LOG.error("Failed trying clear index", e);
                throw new RuntimeException(e);
            }
        }
        super.clear();
        this.doClear();
    }
    
    @Override
    public synchronized StoreEntry place(final Object key, final Object value) {
        this.load();
        try {
            this.remove(key);
            final IndexItem item = this.write(key, value);
            this.index.store(key, item);
            this.indexList.add(item);
            return item;
        }
        catch (IOException e) {
            MapContainerImpl.LOG.error("Failed trying to place key: " + key, e);
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public synchronized void remove(final StoreEntry entry) {
        this.load();
        final IndexItem item = (IndexItem)entry;
        if (item != null) {
            final Object key = this.getKey(item);
            try {
                this.index.remove(key);
            }
            catch (IOException e) {
                MapContainerImpl.LOG.error("Failed trying to remove entry: " + entry, e);
                throw new RuntimeException(e);
            }
            final IndexItem prev = this.indexList.getPrevEntry(item);
            final IndexItem next = this.indexList.getNextEntry(item);
            this.indexList.remove(item);
            this.delete(item, prev, next);
        }
    }
    
    @Override
    public synchronized StoreEntry getFirst() {
        this.load();
        return this.indexList.getFirst();
    }
    
    @Override
    public synchronized StoreEntry getLast() {
        this.load();
        return this.indexList.getLast();
    }
    
    @Override
    public synchronized StoreEntry getNext(final StoreEntry entry) {
        this.load();
        final IndexItem item = (IndexItem)entry;
        return this.indexList.getNextEntry(item);
    }
    
    @Override
    public synchronized StoreEntry getPrevious(final StoreEntry entry) {
        this.load();
        final IndexItem item = (IndexItem)entry;
        return this.indexList.getPrevEntry(item);
    }
    
    @Override
    public synchronized StoreEntry refresh(final StoreEntry entry) {
        this.load();
        return this.indexList.getEntry(entry);
    }
    
    @Override
    public synchronized Object getValue(final StoreEntry item) {
        this.load();
        Object result = null;
        if (item != null) {
            try {
                final StoreLocation data = item.getValueDataItem();
                result = this.dataManager.readItem(this.valueMarshaller, data);
            }
            catch (IOException e) {
                MapContainerImpl.LOG.error("Failed to get value for " + item, e);
                throw new RuntimeStoreException(e);
            }
        }
        return result;
    }
    
    @Override
    public synchronized Object getKey(final StoreEntry item) {
        this.load();
        Object result = null;
        if (item != null) {
            try {
                final StoreLocation data = item.getKeyDataItem();
                result = this.dataManager.readItem(this.keyMarshaller, data);
            }
            catch (IOException e) {
                MapContainerImpl.LOG.error("Failed to get key for " + item, e);
                throw new RuntimeStoreException(e);
            }
        }
        return result;
    }
    
    protected IndexLinkedList getItemList() {
        return this.indexList;
    }
    
    protected synchronized IndexItem write(final Object key, final Object value) {
        IndexItem index = null;
        try {
            index = this.indexManager.createNewIndex();
            StoreLocation data = this.dataManager.storeDataItem(this.keyMarshaller, key);
            index.setKeyData(data);
            if (value != null) {
                data = this.dataManager.storeDataItem(this.valueMarshaller, value);
                index.setValueData(data);
            }
            IndexItem prev = this.indexList.getLast();
            prev = ((prev != null) ? prev : this.indexList.getRoot());
            final IndexItem next = this.indexList.getNextEntry(prev);
            prev.setNextItem(index.getOffset());
            index.setPreviousItem(prev.getOffset());
            this.updateIndexes(prev);
            if (next != null) {
                next.setPreviousItem(index.getOffset());
                index.setNextItem(next.getOffset());
                this.updateIndexes(next);
            }
            this.storeIndex(index);
        }
        catch (IOException e) {
            MapContainerImpl.LOG.error("Failed to write " + key + " , " + value, e);
            throw new RuntimeStoreException(e);
        }
        return index;
    }
    
    @Override
    public int getIndexBinSize() {
        return this.indexBinSize;
    }
    
    @Override
    public void setIndexBinSize(final int indexBinSize) {
        this.indexBinSize = indexBinSize;
    }
    
    @Override
    public int getIndexKeySize() {
        return this.indexKeySize;
    }
    
    @Override
    public void setIndexKeySize(final int indexKeySize) {
        this.indexKeySize = indexKeySize;
    }
    
    @Override
    public int getIndexPageSize() {
        return this.indexPageSize;
    }
    
    @Override
    public void setIndexPageSize(final int indexPageSize) {
        this.indexPageSize = indexPageSize;
    }
    
    @Override
    public int getIndexLoadFactor() {
        return this.indexLoadFactor;
    }
    
    @Override
    public void setIndexLoadFactor(final int loadFactor) {
        this.indexLoadFactor = loadFactor;
    }
    
    @Override
    public IndexMBean getIndexMBean() {
        return (IndexMBean)this.index;
    }
    
    @Override
    public int getIndexMaxBinSize() {
        return this.indexMaxBinSize;
    }
    
    @Override
    public void setIndexMaxBinSize(final int maxBinSize) {
        this.indexMaxBinSize = maxBinSize;
    }
    
    @Override
    public String toString() {
        this.load();
        final StringBuffer buf = new StringBuffer();
        buf.append("{");
        final Iterator i = this.entrySet().iterator();
        boolean hasNext = i.hasNext();
        while (hasNext) {
            final Map.Entry e = i.next();
            final Object key = e.getKey();
            final Object value = e.getValue();
            buf.append(key);
            buf.append("=");
            buf.append(value);
            hasNext = i.hasNext();
            if (hasNext) {
                buf.append(", ");
            }
        }
        buf.append("}");
        return buf.toString();
    }
    
    static {
        LOG = LoggerFactory.getLogger(MapContainerImpl.class);
    }
}
