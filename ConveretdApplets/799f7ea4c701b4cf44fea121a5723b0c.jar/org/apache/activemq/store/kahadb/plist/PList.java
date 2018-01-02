// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadb.plist;

import java.util.concurrent.atomic.AtomicReference;
import org.apache.kahadb.journal.Location;
import org.apache.kahadb.util.ByteSequence;
import org.apache.kahadb.page.Page;
import org.apache.kahadb.util.Marshaller;
import org.apache.kahadb.page.Transaction;
import java.io.DataOutput;
import java.io.IOException;
import java.io.DataInput;
import java.util.concurrent.atomic.AtomicBoolean;

public class PList
{
    final PListStore store;
    private String name;
    private long rootId;
    private long lastId;
    private final AtomicBoolean loaded;
    private int size;
    Object indexLock;
    
    PList(final PListStore store) {
        this.rootId = -1L;
        this.lastId = -1L;
        this.loaded = new AtomicBoolean();
        this.size = 0;
        this.store = store;
        this.indexLock = store.getIndexLock();
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public synchronized int size() {
        return this.size;
    }
    
    public synchronized boolean isEmpty() {
        return this.size == 0;
    }
    
    public long getRootId() {
        return this.rootId;
    }
    
    public void setRootId(final long rootId) {
        this.rootId = rootId;
    }
    
    public long getLastId() {
        return this.lastId;
    }
    
    public void setLastId(final long lastId) {
        this.lastId = lastId;
    }
    
    public boolean isLoaded() {
        return this.loaded.get();
    }
    
    void read(final DataInput in) throws IOException {
        this.rootId = in.readLong();
        this.name = in.readUTF();
    }
    
    public void write(final DataOutput out) throws IOException {
        out.writeLong(this.rootId);
        out.writeUTF(this.name);
    }
    
    public synchronized void destroy() throws IOException {
        synchronized (this.indexLock) {
            this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
                @Override
                public void execute(final Transaction tx) throws IOException {
                    PList.this.destroy(tx);
                }
            });
        }
    }
    
    void destroy(final Transaction tx) throws IOException {
        EntryLocation entry = this.getFirst(tx);
        while (entry != null) {
            final EntryLocation toRemove = entry.copy();
            entry = this.getNext(tx, entry.getNext());
            this.doRemove(tx, toRemove);
        }
    }
    
    synchronized void load(final Transaction tx) throws IOException {
        if (this.loaded.compareAndSet(false, true)) {
            final Page<EntryLocation> p = tx.load(this.rootId, (Marshaller<EntryLocation>)null);
            if (p.getType() == 0) {
                final EntryLocation root = this.createEntry(p, "root", -1L, -1L);
                this.storeEntry(tx, root);
                this.lastId = root.getPage().getPageId();
            }
            else {
                EntryLocation next;
                for (long nextId = this.rootId; nextId != -1L; nextId = next.getNext(), ++this.size) {
                    next = this.getNext(tx, nextId);
                    if (next != null) {
                        this.lastId = next.getPage().getPageId();
                    }
                }
            }
        }
    }
    
    public synchronized void unload() {
        if (this.loaded.compareAndSet(true, false)) {
            this.rootId = -1L;
            this.lastId = -1L;
            this.size = 0;
        }
    }
    
    public synchronized void addLast(final String id, final ByteSequence bs) throws IOException {
        final Location location = this.store.write(bs, false);
        synchronized (this.indexLock) {
            this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
                @Override
                public void execute(final Transaction tx) throws IOException {
                    PList.this.addLast(tx, id, bs, location);
                }
            });
        }
    }
    
    private void addLast(final Transaction tx, final String id, final ByteSequence bs, final Location location) throws IOException {
        final EntryLocation entry = this.createEntry(tx, id, this.lastId, -1L);
        entry.setLocation(location);
        this.storeEntry(tx, entry);
        this.store.incrementJournalCount(tx, location);
        final EntryLocation last = this.loadEntry(tx, this.lastId);
        last.setNext(entry.getPage().getPageId());
        this.storeEntry(tx, last);
        this.lastId = entry.getPage().getPageId();
        ++this.size;
    }
    
    public synchronized void addFirst(final String id, final ByteSequence bs) throws IOException {
        final Location location = this.store.write(bs, false);
        synchronized (this.indexLock) {
            this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
                @Override
                public void execute(final Transaction tx) throws IOException {
                    PList.this.addFirst(tx, id, bs, location);
                }
            });
        }
    }
    
    private void addFirst(final Transaction tx, final String id, final ByteSequence bs, final Location location) throws IOException {
        final EntryLocation entry = this.createEntry(tx, id, -1L, -1L);
        entry.setLocation(location);
        final EntryLocation oldFirst = this.getFirst(tx);
        if (oldFirst != null) {
            oldFirst.setPrev(entry.getPage().getPageId());
            this.storeEntry(tx, oldFirst);
            entry.setNext(oldFirst.getPage().getPageId());
        }
        final EntryLocation root = this.getRoot(tx);
        root.setNext(entry.getPage().getPageId());
        this.storeEntry(tx, root);
        this.storeEntry(tx, entry);
        this.store.incrementJournalCount(tx, location);
        ++this.size;
    }
    
    public synchronized boolean remove(final String id) throws IOException {
        final AtomicBoolean result = new AtomicBoolean();
        synchronized (this.indexLock) {
            this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
                @Override
                public void execute(final Transaction tx) throws IOException {
                    result.set(PList.this.remove(tx, id));
                }
            });
        }
        return result.get();
    }
    
    public synchronized boolean remove(final int position) throws IOException {
        final AtomicBoolean result = new AtomicBoolean();
        synchronized (this.indexLock) {
            this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
                @Override
                public void execute(final Transaction tx) throws IOException {
                    result.set(PList.this.remove(tx, position));
                }
            });
        }
        return result.get();
    }
    
    public synchronized boolean remove(final PListEntry entry) throws IOException {
        final AtomicBoolean result = new AtomicBoolean();
        synchronized (this.indexLock) {
            this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
                @Override
                public void execute(final Transaction tx) throws IOException {
                    result.set(PList.this.doRemove(tx, entry.getEntry()));
                }
            });
        }
        return result.get();
    }
    
    public synchronized PListEntry get(final int position) throws IOException {
        PListEntry result = null;
        final AtomicReference<EntryLocation> ref = new AtomicReference<EntryLocation>();
        synchronized (this.indexLock) {
            this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
                @Override
                public void execute(final Transaction tx) throws IOException {
                    ref.set(PList.this.get(tx, position));
                }
            });
        }
        if (ref.get() != null) {
            final ByteSequence bs = this.store.getPayload(ref.get().getLocation());
            result = new PListEntry(ref.get(), bs);
        }
        return result;
    }
    
    public synchronized PListEntry getFirst() throws IOException {
        PListEntry result = null;
        final AtomicReference<EntryLocation> ref = new AtomicReference<EntryLocation>();
        synchronized (this.indexLock) {
            this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
                @Override
                public void execute(final Transaction tx) throws IOException {
                    ref.set(PList.this.getFirst(tx));
                }
            });
            if (ref.get() != null) {
                final ByteSequence bs = this.store.getPayload(ref.get().getLocation());
                result = new PListEntry(ref.get(), bs);
            }
        }
        return result;
    }
    
    public synchronized PListEntry getLast() throws IOException {
        PListEntry result = null;
        final AtomicReference<EntryLocation> ref = new AtomicReference<EntryLocation>();
        synchronized (this.indexLock) {
            this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
                @Override
                public void execute(final Transaction tx) throws IOException {
                    ref.set(PList.this.getLast(tx));
                }
            });
            if (ref.get() != null) {
                final ByteSequence bs = this.store.getPayload(ref.get().getLocation());
                result = new PListEntry(ref.get(), bs);
            }
        }
        return result;
    }
    
    public synchronized PListEntry getNext(final PListEntry entry) throws IOException {
        PListEntry result = null;
        final long nextId = (entry != null) ? entry.getEntry().getNext() : this.rootId;
        if (nextId != -1L) {
            final AtomicReference<EntryLocation> ref = new AtomicReference<EntryLocation>();
            synchronized (this.indexLock) {
                this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
                    @Override
                    public void execute(final Transaction tx) throws IOException {
                        ref.set(PList.this.getNext(tx, nextId));
                    }
                });
                if (ref.get() != null) {
                    final ByteSequence bs = this.store.getPayload(ref.get().getLocation());
                    result = new PListEntry(ref.get(), bs);
                }
            }
        }
        return result;
    }
    
    public synchronized PListEntry refresh(final PListEntry entry) throws IOException {
        PListEntry result = null;
        final AtomicReference<EntryLocation> ref = new AtomicReference<EntryLocation>();
        synchronized (this.indexLock) {
            this.store.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
                @Override
                public void execute(final Transaction tx) throws IOException {
                    ref.set(PList.this.loadEntry(tx, entry.getEntry().getPage().getPageId()));
                }
            });
            if (ref.get() != null) {
                result = new PListEntry(ref.get(), entry.getByteSequence());
            }
        }
        return result;
    }
    
    boolean remove(final Transaction tx, final String id) throws IOException {
        boolean result = false;
        EntryLocation entry;
        for (long nextId = this.rootId; nextId != -1L; nextId = entry.getNext()) {
            entry = this.getNext(tx, nextId);
            if (entry == null) {
                break;
            }
            if (entry.getId().equals(id)) {
                result = this.doRemove(tx, entry);
                break;
            }
        }
        return result;
    }
    
    boolean remove(final Transaction tx, final int position) throws IOException {
        boolean result = false;
        long nextId = this.rootId;
        EntryLocation entry;
        for (int count = 0; nextId != -1L; nextId = entry.getNext(), ++count) {
            entry = this.getNext(tx, nextId);
            if (entry == null) {
                break;
            }
            if (count == position) {
                result = this.doRemove(tx, entry);
                break;
            }
        }
        return result;
    }
    
    EntryLocation get(final Transaction tx, final int position) throws IOException {
        EntryLocation result = null;
        long nextId = this.rootId;
        EntryLocation entry;
        for (int count = -1; nextId != -1L; nextId = entry.getNext(), ++count) {
            entry = this.getNext(tx, nextId);
            if (entry == null) {
                break;
            }
            if (count == position) {
                result = entry;
                break;
            }
        }
        return result;
    }
    
    EntryLocation getFirst(final Transaction tx) throws IOException {
        final long offset = this.getRoot(tx).getNext();
        if (offset != -1L) {
            return this.loadEntry(tx, offset);
        }
        return null;
    }
    
    EntryLocation getLast(final Transaction tx) throws IOException {
        if (this.lastId != -1L) {
            return this.loadEntry(tx, this.lastId);
        }
        return null;
    }
    
    private boolean doRemove(final Transaction tx, final EntryLocation entry) throws IOException {
        boolean result = false;
        if (entry != null) {
            final EntryLocation prev = this.getPrevious(tx, entry.getPrev());
            final EntryLocation next = this.getNext(tx, entry.getNext());
            final long prevId = (prev != null) ? prev.getPage().getPageId() : this.rootId;
            final long nextId = (next != null) ? next.getPage().getPageId() : -1L;
            if (next != null) {
                next.setPrev(prevId);
                this.storeEntry(tx, next);
            }
            else {
                this.lastId = prevId;
            }
            if (prev != null) {
                prev.setNext(nextId);
                this.storeEntry(tx, prev);
            }
            this.store.decrementJournalCount(tx, entry.getLocation());
            entry.reset();
            this.storeEntry(tx, entry);
            tx.free(entry.getPage().getPageId());
            result = true;
            --this.size;
        }
        return result;
    }
    
    private EntryLocation createEntry(final Transaction tx, final String id, final long previous, final long next) throws IOException {
        final Page<EntryLocation> p = tx.allocate();
        final EntryLocation result = new EntryLocation();
        result.setPage(p);
        p.set(result);
        result.setId(id);
        result.setPrev(previous);
        result.setNext(next);
        return result;
    }
    
    private EntryLocation createEntry(final Page<EntryLocation> p, final String id, final long previous, final long next) throws IOException {
        final EntryLocation result = new EntryLocation();
        result.setPage(p);
        p.set(result);
        result.setId(id);
        result.setPrev(previous);
        result.setNext(next);
        return result;
    }
    
    EntryLocation loadEntry(final Transaction tx, final long pageId) throws IOException {
        final Page<EntryLocation> page = tx.load(pageId, (Marshaller<EntryLocation>)EntryLocation.EntryLocationMarshaller.INSTANCE);
        final EntryLocation entry = page.get();
        if (entry != null) {
            entry.setPage(page);
        }
        return entry;
    }
    
    private void storeEntry(final Transaction tx, final EntryLocation entry) throws IOException {
        tx.store(entry.getPage(), EntryLocation.EntryLocationMarshaller.INSTANCE, true);
    }
    
    EntryLocation getNext(final Transaction tx, final long next) throws IOException {
        EntryLocation result = null;
        if (next != -1L) {
            result = this.loadEntry(tx, next);
        }
        return result;
    }
    
    private EntryLocation getPrevious(final Transaction tx, final long previous) throws IOException {
        EntryLocation result = null;
        if (previous != -1L) {
            result = this.loadEntry(tx, previous);
        }
        return result;
    }
    
    private EntryLocation getRoot(final Transaction tx) throws IOException {
        final EntryLocation result = this.loadEntry(tx, this.rootId);
        return result;
    }
    
    ByteSequence getPayload(final EntryLocation entry) throws IOException {
        return this.store.getPayload(entry.getLocation());
    }
}
