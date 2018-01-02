// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.container;

import org.slf4j.LoggerFactory;
import org.apache.activemq.kaha.StoreLocation;
import java.util.ListIterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.activemq.kaha.RuntimeStoreException;
import org.apache.activemq.kaha.StoreEntry;
import java.io.IOException;
import org.apache.activemq.kaha.Store;
import org.apache.activemq.kaha.impl.DataManager;
import org.apache.activemq.kaha.impl.index.IndexManager;
import org.apache.activemq.kaha.impl.index.IndexItem;
import org.apache.activemq.kaha.ContainerId;
import org.apache.activemq.kaha.Marshaller;
import org.slf4j.Logger;
import org.apache.activemq.kaha.ListContainer;

public class ListContainerImpl extends BaseContainerImpl implements ListContainer
{
    private static final Logger LOG;
    protected Marshaller marshaller;
    
    public ListContainerImpl(final ContainerId id, final IndexItem root, final IndexManager indexManager, final DataManager dataManager, final boolean persistentIndex) throws IOException {
        super(id, root, indexManager, dataManager, persistentIndex);
        this.marshaller = Store.OBJECT_MARSHALLER;
    }
    
    @Override
    public synchronized void load() {
        this.checkClosed();
        if (!this.loaded && !this.loaded) {
            this.loaded = true;
            try {
                this.init();
                IndexItem item;
                for (long nextItem = this.root.getNextItem(); nextItem != -1L; nextItem = item.getNextItem()) {
                    item = this.indexManager.getIndex(nextItem);
                    this.indexList.add(item);
                    this.itemAdded(item, this.indexList.size() - 1, this.getValue(item));
                }
            }
            catch (IOException e) {
                ListContainerImpl.LOG.error("Failed to load container " + this.getId(), e);
                throw new RuntimeStoreException(e);
            }
        }
    }
    
    @Override
    public synchronized void unload() {
        this.checkClosed();
        if (this.loaded) {
            this.loaded = false;
            this.indexList.clear();
        }
    }
    
    @Override
    public synchronized void setMarshaller(final Marshaller marshaller) {
        this.checkClosed();
        this.marshaller = marshaller;
    }
    
    @Override
    public synchronized boolean equals(final Object obj) {
        this.load();
        boolean result = false;
        if (obj != null && obj instanceof List) {
            final List other = (List)obj;
            result = (other.size() == this.size());
            if (result) {
                for (int i = 0; i < this.indexList.size(); ++i) {
                    final Object o1 = other.get(i);
                    final Object o2 = this.get(i);
                    result = (o1 == o2 || (o1 != null && o2 != null && o1.equals(o2)));
                    if (!result) {
                        break;
                    }
                }
            }
        }
        return result;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    @Override
    public synchronized int size() {
        this.load();
        return this.indexList.size();
    }
    
    @Override
    public synchronized void addFirst(final Object o) {
        this.internalAddFirst(o);
    }
    
    @Override
    public synchronized void addLast(final Object o) {
        this.internalAddLast(o);
    }
    
    @Override
    public synchronized Object removeFirst() {
        this.load();
        Object result = null;
        IndexItem item = this.indexList.getFirst();
        if (item != null) {
            this.itemRemoved(0);
            result = this.getValue(item);
            final IndexItem prev = this.root;
            final IndexItem next = (this.indexList.size() > 1) ? this.indexList.get(1) : null;
            this.indexList.removeFirst();
            this.delete(item, prev, next);
            item = null;
        }
        return result;
    }
    
    @Override
    public synchronized Object removeLast() {
        this.load();
        Object result = null;
        final IndexItem last = this.indexList.getLast();
        if (last != null) {
            this.itemRemoved(this.indexList.size() - 1);
            result = this.getValue(last);
            final IndexItem prev = this.indexList.getPrevEntry(last);
            final IndexItem next = null;
            this.indexList.removeLast();
            this.delete(last, prev, next);
        }
        return result;
    }
    
    @Override
    public synchronized boolean isEmpty() {
        this.load();
        return this.indexList.isEmpty();
    }
    
    @Override
    public synchronized boolean contains(final Object o) {
        this.load();
        boolean result = false;
        if (o != null) {
            for (IndexItem next = this.indexList.getFirst(); next != null; next = this.indexList.getNextEntry(next)) {
                final Object value = this.getValue(next);
                if (value != null && value.equals(o)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
    
    @Override
    public synchronized Iterator iterator() {
        this.load();
        return this.listIterator();
    }
    
    @Override
    public synchronized Object[] toArray() {
        this.load();
        final List<Object> tmp = new ArrayList<Object>(this.indexList.size());
        for (IndexItem next = this.indexList.getFirst(); next != null; next = this.indexList.getNextEntry(next)) {
            final Object value = this.getValue(next);
            tmp.add(value);
        }
        return tmp.toArray();
    }
    
    @Override
    public synchronized Object[] toArray(final Object[] a) {
        this.load();
        final List<Object> tmp = new ArrayList<Object>(this.indexList.size());
        for (IndexItem next = this.indexList.getFirst(); next != null; next = this.indexList.getNextEntry(next)) {
            final Object value = this.getValue(next);
            tmp.add(value);
        }
        return tmp.toArray(a);
    }
    
    @Override
    public synchronized boolean add(final Object o) {
        this.load();
        this.addLast(o);
        return true;
    }
    
    @Override
    public synchronized boolean remove(final Object o) {
        this.load();
        boolean result = false;
        int pos = 0;
        for (IndexItem next = this.indexList.getFirst(); next != null; next = this.indexList.getNextEntry(next), ++pos) {
            final Object value = this.getValue(next);
            if (value != null && value.equals(o)) {
                this.remove(next);
                this.itemRemoved(pos);
                result = true;
                break;
            }
        }
        return result;
    }
    
    @Override
    protected synchronized void remove(final IndexItem item) {
        final IndexItem prev = this.indexList.getPrevEntry(item);
        final IndexItem next = this.indexList.getNextEntry(item);
        this.indexList.remove(item);
        this.delete(item, prev, next);
    }
    
    @Override
    public synchronized boolean containsAll(final Collection c) {
        this.load();
        for (final Object obj : c) {
            if (!this.contains(obj)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public synchronized boolean addAll(final Collection c) {
        this.load();
        final Iterator i = c.iterator();
        while (i.hasNext()) {
            this.add(i.next());
        }
        return true;
    }
    
    @Override
    public synchronized boolean addAll(final int index, final Collection c) {
        this.load();
        boolean result = false;
        final ListIterator e1 = this.listIterator(index);
        final Iterator e2 = c.iterator();
        while (e2.hasNext()) {
            e1.add(e2.next());
            result = true;
        }
        return result;
    }
    
    @Override
    public synchronized boolean removeAll(final Collection c) {
        this.load();
        boolean result = true;
        for (final Object obj : c) {
            result &= this.remove(obj);
        }
        return result;
    }
    
    @Override
    public synchronized boolean retainAll(final Collection c) {
        this.load();
        final List<Object> tmpList = new ArrayList<Object>();
        for (IndexItem next = this.indexList.getFirst(); next != null; next = this.indexList.getNextEntry(next)) {
            final Object o = this.getValue(next);
            if (!c.contains(o)) {
                tmpList.add(o);
            }
        }
        final Iterator<Object> i = tmpList.iterator();
        while (i.hasNext()) {
            this.remove(i.next());
        }
        return !tmpList.isEmpty();
    }
    
    @Override
    public synchronized void clear() {
        this.checkClosed();
        super.clear();
        this.doClear();
    }
    
    @Override
    public synchronized Object get(final int index) {
        this.load();
        Object result = null;
        final IndexItem item = this.indexList.get(index);
        if (item != null) {
            result = this.getValue(item);
        }
        return result;
    }
    
    @Override
    public synchronized Object set(final int index, final Object element) {
        this.load();
        Object result = null;
        final IndexItem replace = this.indexList.isEmpty() ? null : this.indexList.get(index);
        final IndexItem prev = (this.indexList.isEmpty() || index - 1 < 0) ? null : this.indexList.get(index - 1);
        final IndexItem next = (this.indexList.isEmpty() || index + 1 >= this.size()) ? null : this.indexList.get(index + 1);
        result = this.getValue(replace);
        this.indexList.remove(index);
        this.delete(replace, prev, next);
        this.itemRemoved(index);
        this.add(index, element);
        return result;
    }
    
    protected synchronized IndexItem internalSet(final int index, final Object element) {
        final IndexItem replace = this.indexList.isEmpty() ? null : this.indexList.get(index);
        final IndexItem prev = (this.indexList.isEmpty() || index - 1 < 0) ? null : this.indexList.get(index - 1);
        final IndexItem next = (this.indexList.isEmpty() || index + 1 >= this.size()) ? null : this.indexList.get(index + 1);
        this.indexList.remove(index);
        this.delete(replace, prev, next);
        this.itemRemoved(index);
        return this.internalAdd(index, element);
    }
    
    @Override
    public synchronized void add(final int index, final Object element) {
        this.load();
        final IndexItem item = this.insert(index, element);
        this.indexList.add(index, item);
        this.itemAdded(item, index, element);
    }
    
    protected synchronized StoreEntry internalAddLast(final Object o) {
        this.load();
        final IndexItem item = this.writeLast(o);
        this.indexList.addLast(item);
        this.itemAdded(item, this.indexList.size() - 1, o);
        return item;
    }
    
    protected synchronized StoreEntry internalAddFirst(final Object o) {
        this.load();
        final IndexItem item = this.writeFirst(o);
        this.indexList.addFirst(item);
        this.itemAdded(item, 0, o);
        return item;
    }
    
    protected synchronized IndexItem internalAdd(final int index, final Object element) {
        this.load();
        final IndexItem item = this.insert(index, element);
        this.indexList.add(index, item);
        this.itemAdded(item, index, element);
        return item;
    }
    
    protected synchronized StoreEntry internalGet(final int index) {
        this.load();
        if (index >= 0 && index < this.indexList.size()) {
            return this.indexList.get(index);
        }
        return null;
    }
    
    @Override
    public synchronized boolean doRemove(final int index) {
        this.load();
        boolean result = false;
        final IndexItem item = this.indexList.get(index);
        if (item != null) {
            result = true;
            IndexItem prev = this.indexList.getPrevEntry(item);
            prev = ((prev != null) ? prev : this.root);
            final IndexItem next = this.indexList.getNextEntry(prev);
            this.indexList.remove(index);
            this.itemRemoved(index);
            this.delete(item, prev, next);
        }
        return result;
    }
    
    @Override
    public synchronized Object remove(final int index) {
        this.load();
        Object result = null;
        final IndexItem item = this.indexList.get(index);
        if (item != null) {
            this.itemRemoved(index);
            result = this.getValue(item);
            IndexItem prev = this.indexList.getPrevEntry(item);
            prev = ((prev != null) ? prev : this.root);
            final IndexItem next = this.indexList.getNextEntry(item);
            this.indexList.remove(index);
            this.delete(item, prev, next);
        }
        return result;
    }
    
    @Override
    public synchronized int indexOf(final Object o) {
        this.load();
        int result = -1;
        if (o != null) {
            int count = 0;
            for (IndexItem next = this.indexList.getFirst(); next != null; next = this.indexList.getNextEntry(next)) {
                final Object value = this.getValue(next);
                if (value != null && value.equals(o)) {
                    result = count;
                    break;
                }
                ++count;
            }
        }
        return result;
    }
    
    @Override
    public synchronized int lastIndexOf(final Object o) {
        this.load();
        int result = -1;
        if (o != null) {
            int count = this.indexList.size() - 1;
            for (IndexItem next = this.indexList.getLast(); next != null; next = this.indexList.getPrevEntry(next)) {
                final Object value = this.getValue(next);
                if (value != null && value.equals(o)) {
                    result = count;
                    break;
                }
                --count;
            }
        }
        return result;
    }
    
    @Override
    public synchronized ListIterator listIterator() {
        this.load();
        return new ContainerListIterator(this, this.indexList, this.indexList.getRoot());
    }
    
    @Override
    public synchronized ListIterator listIterator(final int index) {
        this.load();
        final IndexItem start = (index - 1 > 0) ? this.indexList.get(index - 1) : this.indexList.getRoot();
        return new ContainerListIterator(this, this.indexList, start);
    }
    
    @Override
    public synchronized List<Object> subList(final int fromIndex, final int toIndex) {
        this.load();
        final List<Object> result = new ArrayList<Object>();
        int count = fromIndex;
        for (IndexItem next = this.indexList.get(fromIndex); next != null && count++ < toIndex; next = this.indexList.getNextEntry(next)) {
            result.add(this.getValue(next));
        }
        return result;
    }
    
    @Override
    public synchronized StoreEntry placeLast(final Object object) {
        final StoreEntry item = this.internalAddLast(object);
        return item;
    }
    
    @Override
    public synchronized StoreEntry placeFirst(final Object object) {
        final StoreEntry item = this.internalAddFirst(object);
        return item;
    }
    
    @Override
    public synchronized void update(final StoreEntry entry, final Object object) {
        try {
            this.dataManager.updateItem(entry.getValueDataItem(), this.marshaller, object);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public synchronized Object get(final StoreEntry entry) {
        this.load();
        final StoreEntry entryToUse = this.refresh(entry);
        return this.getValue(entryToUse);
    }
    
    @Override
    public synchronized boolean remove(final StoreEntry entry) {
        final IndexItem item = (IndexItem)entry;
        this.load();
        boolean result = false;
        if (item != null) {
            this.remove(item);
            result = true;
        }
        return result;
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
    
    protected synchronized IndexItem writeLast(final Object value) {
        IndexItem index = null;
        try {
            if (value != null) {
                final StoreLocation data = this.dataManager.storeDataItem(this.marshaller, value);
                index = this.indexManager.createNewIndex();
                index.setValueData(data);
                IndexItem prev = this.indexList.getLast();
                prev = ((prev != null) ? prev : this.root);
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
        }
        catch (IOException e) {
            ListContainerImpl.LOG.error("Failed to write " + value, e);
            throw new RuntimeStoreException(e);
        }
        return index;
    }
    
    protected synchronized IndexItem writeFirst(final Object value) {
        IndexItem index = null;
        try {
            if (value != null) {
                final StoreLocation data = this.dataManager.storeDataItem(this.marshaller, value);
                index = this.indexManager.createNewIndex();
                index.setValueData(data);
                final IndexItem prev = this.root;
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
        }
        catch (IOException e) {
            ListContainerImpl.LOG.error("Failed to write " + value, e);
            throw new RuntimeStoreException(e);
        }
        return index;
    }
    
    protected synchronized IndexItem insert(final int insertPos, final Object value) {
        IndexItem index = null;
        try {
            if (value != null) {
                final StoreLocation data = this.dataManager.storeDataItem(this.marshaller, value);
                index = this.indexManager.createNewIndex();
                index.setValueData(data);
                IndexItem prev = null;
                IndexItem next = null;
                if (insertPos <= 0) {
                    prev = this.root;
                    next = this.indexList.getNextEntry(this.root);
                }
                else if (insertPos >= this.indexList.size()) {
                    prev = this.indexList.getLast();
                    if (prev == null) {
                        prev = this.root;
                    }
                    next = null;
                }
                else {
                    prev = this.indexList.get(insertPos);
                    prev = ((prev != null) ? prev : this.root);
                    next = this.indexList.getNextEntry(prev);
                }
                prev.setNextItem(index.getOffset());
                index.setPreviousItem(prev.getOffset());
                this.updateIndexes(prev);
                if (next != null) {
                    next.setPreviousItem(index.getOffset());
                    index.setNextItem(next.getOffset());
                    this.updateIndexes(next);
                }
                this.storeIndex(index);
                this.indexList.setRoot(this.root);
            }
        }
        catch (IOException e) {
            ListContainerImpl.LOG.error("Failed to insert " + value, e);
            throw new RuntimeStoreException(e);
        }
        return index;
    }
    
    @Override
    protected synchronized Object getValue(final StoreEntry item) {
        Object result = null;
        if (item != null) {
            try {
                final StoreLocation data = item.getValueDataItem();
                result = this.dataManager.readItem(this.marshaller, data);
            }
            catch (IOException e) {
                ListContainerImpl.LOG.error("Failed to get value for " + item, e);
                throw new RuntimeStoreException(e);
            }
        }
        return result;
    }
    
    @Override
    public synchronized String toString() {
        final StringBuffer result = new StringBuffer();
        result.append("[");
        final Iterator i = this.iterator();
        boolean hasNext = i.hasNext();
        while (hasNext) {
            final Object o = i.next();
            result.append(String.valueOf(o));
            hasNext = i.hasNext();
            if (hasNext) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
    
    protected synchronized void itemAdded(final IndexItem item, final int pos, final Object value) {
    }
    
    protected synchronized void itemRemoved(final int pos) {
    }
    
    static {
        LOG = LoggerFactory.getLogger(ListContainerImpl.class);
    }
}
