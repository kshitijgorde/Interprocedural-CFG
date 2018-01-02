// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadb.plist;

import java.util.ArrayList;
import java.util.List;
import org.apache.kahadb.util.VariableMarshaller;
import java.io.DataOutput;
import java.io.DataInput;
import org.apache.kahadb.util.IntegerMarshaller;
import org.apache.kahadb.util.StringMarshaller;
import org.apache.kahadb.index.BTreeIndex;
import org.slf4j.LoggerFactory;
import org.apache.kahadb.util.ByteSequence;
import java.util.Set;
import java.util.HashSet;
import org.apache.kahadb.journal.Location;
import java.util.Iterator;
import org.apache.activemq.util.ServiceStopper;
import org.apache.kahadb.page.Page;
import org.apache.kahadb.util.Marshaller;
import org.apache.activemq.util.IOHelper;
import org.apache.kahadb.page.Transaction;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.kahadb.util.LockFile;
import org.apache.kahadb.journal.Journal;
import org.apache.kahadb.page.PageFile;
import java.io.File;
import org.slf4j.Logger;
import org.apache.activemq.util.ServiceSupport;

public class PListStore extends ServiceSupport
{
    static final Logger LOG;
    private static final int DATABASE_LOCKED_WAIT_DELAY = 10000;
    static final int CLOSED_STATE = 1;
    static final int OPEN_STATE = 2;
    private File directory;
    PageFile pageFile;
    private Journal journal;
    private LockFile lockFile;
    private boolean failIfDatabaseIsLocked;
    private int journalMaxFileLength;
    private int journalMaxWriteBatchSize;
    private boolean enableIndexWriteAsync;
    private boolean initialized;
    MetaData metaData;
    final MetaDataMarshaller metaDataMarshaller;
    Map<String, PList> persistentLists;
    final Object indexLock;
    
    public PListStore() {
        this.journalMaxFileLength = 33554432;
        this.journalMaxWriteBatchSize = 4194304;
        this.enableIndexWriteAsync = false;
        this.initialized = false;
        this.metaData = new MetaData(this);
        this.metaDataMarshaller = new MetaDataMarshaller(this);
        this.persistentLists = new HashMap<String, PList>();
        this.indexLock = new Object();
    }
    
    public Object getIndexLock() {
        return this.indexLock;
    }
    
    public File getDirectory() {
        return this.directory;
    }
    
    public void setDirectory(final File directory) {
        this.directory = directory;
    }
    
    public long size() {
        synchronized (this) {
            if (!this.initialized) {
                return 0L;
            }
        }
        try {
            return this.journal.getDiskSize() + this.pageFile.getDiskSize();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public synchronized PList getPList(final String name) throws Exception {
        if (!this.isStarted()) {
            throw new IllegalStateException("Not started");
        }
        this.intialize();
        PList result = this.persistentLists.get(name);
        if (result == null) {
            final PList pl = new PList(this);
            pl.setName(name);
            this.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
                @Override
                public void execute(final Transaction tx) throws IOException {
                    pl.setRootId(tx.allocate().getPageId());
                    pl.load(tx);
                    PListStore.this.metaData.storedSchedulers.put(tx, name, pl);
                }
            });
            result = pl;
            this.persistentLists.put(name, pl);
        }
        final PList load = result;
        this.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
            @Override
            public void execute(final Transaction tx) throws IOException {
                load.load(tx);
            }
        });
        return result;
    }
    
    public synchronized boolean removePList(final String name) throws Exception {
        boolean result = false;
        final PList pl = this.persistentLists.remove(name);
        result = (pl != null);
        if (result) {
            this.getPageFile().tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
                @Override
                public void execute(final Transaction tx) throws IOException {
                    PListStore.this.metaData.storedSchedulers.remove(tx, name);
                    pl.destroy(tx);
                }
            });
        }
        return result;
    }
    
    protected synchronized void intialize() throws Exception {
        if (this.isStarted() && !this.initialized) {
            this.initialized = true;
            if (this.directory == null) {
                this.directory = new File(IOHelper.getDefaultDataDirectory() + File.pathSeparator + "delayedDB");
            }
            IOHelper.mkdirs(this.directory);
            this.lock();
            (this.journal = new Journal()).setDirectory(this.directory);
            this.journal.setMaxFileLength(this.getJournalMaxFileLength());
            this.journal.setWriteBatchSize(this.getJournalMaxWriteBatchSize());
            this.journal.start();
            (this.pageFile = new PageFile(this.directory, "tmpDB")).load();
            this.pageFile.tx().execute((Transaction.Closure<Throwable>)new Transaction.Closure<IOException>() {
                @Override
                public void execute(final Transaction tx) throws IOException {
                    if (PListStore.this.pageFile.getPageCount() == 0L) {
                        final Page<MetaData> page = tx.allocate();
                        assert page.getPageId() == 0L;
                        page.set(PListStore.this.metaData);
                        PListStore.this.metaData.page = page;
                        PListStore.this.metaData.createIndexes(tx);
                        tx.store(PListStore.this.metaData.page, PListStore.this.metaDataMarshaller, true);
                    }
                    else {
                        final Page<MetaData> page = tx.load(0L, (Marshaller<MetaData>)PListStore.this.metaDataMarshaller);
                        PListStore.this.metaData = page.get();
                        PListStore.this.metaData.page = page;
                    }
                    PListStore.this.metaData.load(tx);
                    PListStore.this.metaData.loadLists(tx, PListStore.this.persistentLists);
                }
            });
            this.pageFile.flush();
            PListStore.LOG.info(this + " initialized");
        }
    }
    
    @Override
    protected synchronized void doStart() throws Exception {
        PListStore.LOG.info(this + " started");
    }
    
    @Override
    protected synchronized void doStop(final ServiceStopper stopper) throws Exception {
        for (final PList pl : this.persistentLists.values()) {
            pl.unload();
        }
        if (this.pageFile != null) {
            this.pageFile.unload();
        }
        if (this.journal != null) {
            this.journal.close();
        }
        if (this.lockFile != null) {
            this.lockFile.unlock();
        }
        this.lockFile = null;
        this.initialized = false;
        PListStore.LOG.info(this + " stopped");
    }
    
    synchronized void incrementJournalCount(final Transaction tx, final Location location) throws IOException {
        final int logId = location.getDataFileId();
        final Integer val = this.metaData.journalRC.get(tx, logId);
        final int refCount = (val != null) ? (val + 1) : 1;
        this.metaData.journalRC.put(tx, logId, refCount);
    }
    
    synchronized void decrementJournalCount(final Transaction tx, final Location location) throws IOException {
        final int logId = location.getDataFileId();
        if (logId != -1) {
            int refCount = this.metaData.journalRC.get(tx, logId);
            if (--refCount <= 0) {
                this.metaData.journalRC.remove(tx, logId);
                final Set<Integer> set = new HashSet<Integer>();
                set.add(logId);
                this.journal.removeDataFiles(set);
            }
            else {
                this.metaData.journalRC.put(tx, logId, refCount);
            }
        }
    }
    
    synchronized ByteSequence getPayload(final Location location) throws IllegalStateException, IOException {
        ByteSequence result = null;
        result = this.journal.read(location);
        return result;
    }
    
    synchronized Location write(final ByteSequence payload, final boolean sync) throws IllegalStateException, IOException {
        return this.journal.write(payload, sync);
    }
    
    private void lock() throws IOException {
        if (this.lockFile == null) {
            final File lockFileName = new File(this.directory, "lock");
            this.lockFile = new LockFile(lockFileName, true);
            if (this.failIfDatabaseIsLocked) {
                this.lockFile.lock();
            }
            else {
                while (true) {
                    try {
                        this.lockFile.lock();
                    }
                    catch (IOException e) {
                        PListStore.LOG.info("Database " + lockFileName + " is locked... waiting " + 10 + " seconds for the database to be unlocked. Reason: " + e);
                        try {
                            Thread.sleep(10000L);
                        }
                        catch (InterruptedException ex) {}
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    PageFile getPageFile() {
        this.pageFile.isLoaded();
        return this.pageFile;
    }
    
    public boolean isFailIfDatabaseIsLocked() {
        return this.failIfDatabaseIsLocked;
    }
    
    public void setFailIfDatabaseIsLocked(final boolean failIfDatabaseIsLocked) {
        this.failIfDatabaseIsLocked = failIfDatabaseIsLocked;
    }
    
    public int getJournalMaxFileLength() {
        return this.journalMaxFileLength;
    }
    
    public void setJournalMaxFileLength(final int journalMaxFileLength) {
        this.journalMaxFileLength = journalMaxFileLength;
    }
    
    public int getJournalMaxWriteBatchSize() {
        return this.journalMaxWriteBatchSize;
    }
    
    public void setJournalMaxWriteBatchSize(final int journalMaxWriteBatchSize) {
        this.journalMaxWriteBatchSize = journalMaxWriteBatchSize;
    }
    
    public boolean isEnableIndexWriteAsync() {
        return this.enableIndexWriteAsync;
    }
    
    public void setEnableIndexWriteAsync(final boolean enableIndexWriteAsync) {
        this.enableIndexWriteAsync = enableIndexWriteAsync;
    }
    
    @Override
    public String toString() {
        return "PListStore:" + this.directory;
    }
    
    static {
        LOG = LoggerFactory.getLogger(PListStore.class);
    }
    
    protected class MetaData
    {
        private final PListStore store;
        Page<MetaData> page;
        BTreeIndex<Integer, Integer> journalRC;
        BTreeIndex<String, PList> storedSchedulers;
        
        protected MetaData(final PListStore store) {
            this.store = store;
        }
        
        void createIndexes(final Transaction tx) throws IOException {
            this.storedSchedulers = new BTreeIndex<String, PList>(PListStore.this.pageFile, tx.allocate().getPageId());
            this.journalRC = new BTreeIndex<Integer, Integer>(PListStore.this.pageFile, tx.allocate().getPageId());
        }
        
        void load(final Transaction tx) throws IOException {
            this.storedSchedulers.setKeyMarshaller(StringMarshaller.INSTANCE);
            this.storedSchedulers.setValueMarshaller(new JobSchedulerMarshaller(this.store));
            this.storedSchedulers.load(tx);
            this.journalRC.setKeyMarshaller(IntegerMarshaller.INSTANCE);
            this.journalRC.setValueMarshaller(IntegerMarshaller.INSTANCE);
            this.journalRC.load(tx);
        }
        
        void loadLists(final Transaction tx, final Map<String, PList> schedulers) throws IOException {
            final Iterator<Map.Entry<String, PList>> i = this.storedSchedulers.iterator(tx);
            while (i.hasNext()) {
                final Map.Entry<String, PList> entry = i.next();
                entry.getValue().load(tx);
                schedulers.put(entry.getKey(), entry.getValue());
            }
        }
        
        public void read(final DataInput is) throws IOException {
            (this.storedSchedulers = new BTreeIndex<String, PList>(PListStore.this.pageFile, is.readLong())).setKeyMarshaller(StringMarshaller.INSTANCE);
            this.storedSchedulers.setValueMarshaller(new JobSchedulerMarshaller(this.store));
            (this.journalRC = new BTreeIndex<Integer, Integer>(PListStore.this.pageFile, is.readLong())).setKeyMarshaller(IntegerMarshaller.INSTANCE);
            this.journalRC.setValueMarshaller(IntegerMarshaller.INSTANCE);
        }
        
        public void write(final DataOutput os) throws IOException {
            os.writeLong(this.storedSchedulers.getPageId());
            os.writeLong(this.journalRC.getPageId());
        }
    }
    
    class MetaDataMarshaller extends VariableMarshaller<MetaData>
    {
        private final PListStore store;
        
        MetaDataMarshaller(final PListStore store) {
            this.store = store;
        }
        
        @Override
        public MetaData readPayload(final DataInput dataIn) throws IOException {
            final MetaData rc = new MetaData(this.store);
            rc.read(dataIn);
            return rc;
        }
        
        @Override
        public void writePayload(final MetaData object, final DataOutput dataOut) throws IOException {
            object.write(dataOut);
        }
    }
    
    class ValueMarshaller extends VariableMarshaller<List<EntryLocation>>
    {
        @Override
        public List<EntryLocation> readPayload(final DataInput dataIn) throws IOException {
            final List<EntryLocation> result = new ArrayList<EntryLocation>();
            for (int size = dataIn.readInt(), i = 0; i < size; ++i) {
                final EntryLocation jobLocation = new EntryLocation();
                jobLocation.readExternal(dataIn);
                result.add(jobLocation);
            }
            return result;
        }
        
        @Override
        public void writePayload(final List<EntryLocation> value, final DataOutput dataOut) throws IOException {
            dataOut.writeInt(value.size());
            for (final EntryLocation jobLocation : value) {
                jobLocation.writeExternal(dataOut);
            }
        }
    }
    
    class JobSchedulerMarshaller extends VariableMarshaller<PList>
    {
        private final PListStore store;
        
        JobSchedulerMarshaller(final PListStore store) {
            this.store = store;
        }
        
        @Override
        public PList readPayload(final DataInput dataIn) throws IOException {
            final PList result = new PList(this.store);
            result.read(dataIn);
            return result;
        }
        
        @Override
        public void writePayload(final PList js, final DataOutput dataOut) throws IOException {
            js.write(dataOut);
        }
    }
}
