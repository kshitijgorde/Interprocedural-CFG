// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.async;

import org.slf4j.LoggerFactory;
import java.util.HashSet;
import java.util.Set;
import java.io.DataOutput;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import org.apache.activemq.util.IOHelper;
import java.util.Iterator;
import java.util.List;
import org.apache.activemq.util.ByteSequence;
import java.io.IOException;
import org.apache.activemq.util.LinkedNode;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.io.FilenameFilter;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.io.File;
import java.util.Map;
import org.apache.activemq.thread.Scheduler;
import org.slf4j.Logger;

public class AsyncDataManager
{
    public static final int CONTROL_RECORD_MAX_LENGTH = 1024;
    public static final int ITEM_HEAD_RESERVED_SPACE = 21;
    public static final int ITEM_HEAD_SPACE = 29;
    public static final int ITEM_HEAD_OFFSET_TO_SOR = 26;
    public static final int ITEM_FOOT_SPACE = 3;
    public static final int ITEM_HEAD_FOOT_SPACE = 32;
    public static final byte[] ITEM_HEAD_SOR;
    public static final byte[] ITEM_HEAD_EOR;
    public static final byte DATA_ITEM_TYPE = 1;
    public static final byte REDO_ITEM_TYPE = 2;
    public static final String DEFAULT_DIRECTORY = "data";
    public static final String DEFAULT_ARCHIVE_DIRECTORY = "data-archive";
    public static final String DEFAULT_FILE_PREFIX = "data-";
    public static final int DEFAULT_MAX_FILE_LENGTH = 33554432;
    public static final int DEFAULT_CLEANUP_INTERVAL = 30000;
    public static final int PREFERED_DIFF = 524288;
    private static final Logger LOG;
    protected Scheduler scheduler;
    protected final Map<DataFileAppender.WriteKey, DataFileAppender.WriteCommand> inflightWrites;
    protected File directory;
    protected File directoryArchive;
    protected String filePrefix;
    protected ControlFile controlFile;
    protected boolean started;
    protected boolean useNio;
    protected int maxFileLength;
    protected int preferedFileLength;
    protected DataFileAppender appender;
    protected DataFileAccessorPool accessorPool;
    protected Map<Integer, DataFile> fileMap;
    protected Map<File, DataFile> fileByFileMap;
    protected DataFile currentWriteFile;
    protected Location mark;
    protected final AtomicReference<Location> lastAppendLocation;
    protected Runnable cleanupTask;
    protected final AtomicLong storeSize;
    protected boolean archiveDataLogs;
    
    public AsyncDataManager(final AtomicLong storeSize) {
        this.inflightWrites = new ConcurrentHashMap<DataFileAppender.WriteKey, DataFileAppender.WriteCommand>();
        this.directory = new File("data");
        this.directoryArchive = new File("data-archive");
        this.filePrefix = "data-";
        this.useNio = true;
        this.maxFileLength = 33554432;
        this.preferedFileLength = 33030144;
        this.fileMap = new HashMap<Integer, DataFile>();
        this.fileByFileMap = new LinkedHashMap<File, DataFile>();
        this.lastAppendLocation = new AtomicReference<Location>();
        this.storeSize = storeSize;
    }
    
    public AsyncDataManager() {
        this(new AtomicLong());
    }
    
    public synchronized void start() throws IOException {
        if (this.started) {
            return;
        }
        this.started = true;
        this.preferedFileLength = Math.max(524288, this.getMaxFileLength() - 524288);
        this.lock();
        this.accessorPool = new DataFileAccessorPool(this);
        final ByteSequence sequence = this.controlFile.load();
        if (sequence != null && sequence.getLength() > 0) {
            this.unmarshallState(sequence);
        }
        if (this.useNio) {
            this.appender = new NIODataFileAppender(this);
        }
        else {
            this.appender = new DataFileAppender(this);
        }
        final File[] files = this.directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(final File dir, final String n) {
                return dir.equals(AsyncDataManager.this.directory) && n.startsWith(AsyncDataManager.this.filePrefix);
            }
        });
        if (files != null) {
            for (int i = 0; i < files.length; ++i) {
                try {
                    final File file = files[i];
                    final String n = file.getName();
                    final String numStr = n.substring(this.filePrefix.length(), n.length());
                    final int num = Integer.parseInt(numStr);
                    final DataFile dataFile = new DataFile(file, num, this.preferedFileLength);
                    this.fileMap.put(dataFile.getDataFileId(), dataFile);
                    this.storeSize.addAndGet(dataFile.getLength());
                }
                catch (NumberFormatException ex) {}
            }
            final List<DataFile> l = new ArrayList<DataFile>(this.fileMap.values());
            Collections.sort(l);
            this.currentWriteFile = null;
            for (final DataFile df : l) {
                if (this.currentWriteFile != null) {
                    this.currentWriteFile.linkAfter(df);
                }
                this.currentWriteFile = df;
                this.fileByFileMap.put(df.getFile(), df);
            }
        }
        if (this.currentWriteFile != null) {
            Location j = this.lastAppendLocation.get();
            if (j != null && j.getDataFileId() != this.currentWriteFile.getDataFileId()) {
                j = null;
            }
            try {
                j = this.recoveryCheck(this.currentWriteFile, j);
                this.lastAppendLocation.set(j);
            }
            catch (IOException e) {
                AsyncDataManager.LOG.warn("recovery check failed", e);
            }
        }
        this.storeState(false);
        this.cleanupTask = new Runnable() {
            @Override
            public void run() {
                AsyncDataManager.this.cleanup();
            }
        };
        this.scheduler = new Scheduler("AsyncDataManager Scheduler");
        try {
            this.scheduler.start();
        }
        catch (Exception e2) {
            final IOException ioe = new IOException("scheduler start: " + e2);
            ioe.initCause(e2);
            throw ioe;
        }
        this.scheduler.executePeriodically(this.cleanupTask, 30000L);
    }
    
    public void lock() throws IOException {
        synchronized (this) {
            if (this.controlFile == null || this.controlFile.isDisposed()) {
                IOHelper.mkdirs(this.directory);
                this.controlFile = new ControlFile(new File(this.directory, this.filePrefix + "control"), 1024);
            }
            this.controlFile.lock();
        }
    }
    
    protected Location recoveryCheck(final DataFile dataFile, Location location) throws IOException {
        if (location == null) {
            location = new Location();
            location.setDataFileId(dataFile.getDataFileId());
            location.setOffset(0);
        }
        final DataFileAccessor reader = this.accessorPool.openDataFileAccessor(dataFile);
        try {
            reader.readLocationDetails(location);
            while (reader.readLocationDetailsAndValidate(location)) {
                location.setOffset(location.getOffset() + location.getSize());
            }
        }
        finally {
            this.accessorPool.closeDataFileAccessor(reader);
        }
        dataFile.setLength(location.getOffset());
        return location;
    }
    
    protected void unmarshallState(final ByteSequence sequence) throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(sequence.getData(), sequence.getOffset(), sequence.getLength());
        final DataInputStream dis = new DataInputStream(bais);
        if (dis.readBoolean()) {
            (this.mark = new Location()).readExternal(dis);
        }
        else {
            this.mark = null;
        }
        if (dis.readBoolean()) {
            final Location l = new Location();
            l.readExternal(dis);
            this.lastAppendLocation.set(l);
        }
        else {
            this.lastAppendLocation.set(null);
        }
    }
    
    private synchronized ByteSequence marshallState() throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final DataOutputStream dos = new DataOutputStream(baos);
        if (this.mark != null) {
            dos.writeBoolean(true);
            this.mark.writeExternal(dos);
        }
        else {
            dos.writeBoolean(false);
        }
        final Location l = this.lastAppendLocation.get();
        if (l != null) {
            dos.writeBoolean(true);
            l.writeExternal(dos);
        }
        else {
            dos.writeBoolean(false);
        }
        final byte[] bs = baos.toByteArray();
        return new ByteSequence(bs, 0, bs.length);
    }
    
    synchronized DataFile allocateLocation(final Location location) throws IOException {
        if (this.currentWriteFile == null || this.currentWriteFile.getLength() + location.getSize() > this.maxFileLength) {
            final int nextNum = (this.currentWriteFile != null) ? (this.currentWriteFile.getDataFileId() + 1) : 1;
            final String fileName = this.filePrefix + nextNum;
            final File file = new File(this.directory, fileName);
            final DataFile nextWriteFile = new DataFile(file, nextNum, this.preferedFileLength);
            nextWriteFile.closeRandomAccessFile(nextWriteFile.openRandomAccessFile(true));
            this.fileMap.put(nextWriteFile.getDataFileId(), nextWriteFile);
            this.fileByFileMap.put(file, nextWriteFile);
            if (this.currentWriteFile != null) {
                this.currentWriteFile.linkAfter(nextWriteFile);
                if (this.currentWriteFile.isUnused()) {
                    this.removeDataFile(this.currentWriteFile);
                }
            }
            this.currentWriteFile = nextWriteFile;
        }
        location.setOffset(this.currentWriteFile.getLength());
        location.setDataFileId(this.currentWriteFile.getDataFileId());
        final int size = location.getSize();
        this.currentWriteFile.incrementLength(size);
        this.currentWriteFile.increment();
        this.storeSize.addAndGet(size);
        return this.currentWriteFile;
    }
    
    public synchronized void removeLocation(final Location location) throws IOException {
        final DataFile dataFile = this.getDataFile(location);
        dataFile.decrement();
    }
    
    synchronized DataFile getDataFile(final Location item) throws IOException {
        final Integer key = item.getDataFileId();
        final DataFile dataFile = this.fileMap.get(key);
        if (dataFile == null) {
            AsyncDataManager.LOG.error("Looking for key " + key + " but not found in fileMap: " + this.fileMap);
            throw new IOException("Could not locate data file " + this.filePrefix + item.getDataFileId());
        }
        return dataFile;
    }
    
    synchronized File getFile(final Location item) throws IOException {
        final Integer key = item.getDataFileId();
        final DataFile dataFile = this.fileMap.get(key);
        if (dataFile == null) {
            AsyncDataManager.LOG.error("Looking for key " + key + " but not found in fileMap: " + this.fileMap);
            throw new IOException("Could not locate data file " + this.filePrefix + item.getDataFileId());
        }
        return dataFile.getFile();
    }
    
    private DataFile getNextDataFile(final DataFile dataFile) {
        return (DataFile)dataFile.getNext();
    }
    
    public synchronized void close() throws IOException {
        if (!this.started) {
            return;
        }
        this.scheduler.cancel(this.cleanupTask);
        try {
            this.scheduler.stop();
        }
        catch (Exception e) {
            final IOException ioe = new IOException("scheduler stop: " + e);
            ioe.initCause(e);
            throw ioe;
        }
        this.accessorPool.close();
        this.storeState(false);
        this.appender.close();
        this.fileMap.clear();
        this.fileByFileMap.clear();
        this.controlFile.unlock();
        this.controlFile.dispose();
        this.started = false;
    }
    
    synchronized void cleanup() {
        if (this.accessorPool != null) {
            this.accessorPool.disposeUnused();
        }
    }
    
    public synchronized boolean delete() throws IOException {
        this.appender.close();
        this.accessorPool.close();
        boolean result = true;
        for (final DataFile dataFile : this.fileMap.values()) {
            this.storeSize.addAndGet(-dataFile.getLength());
            result &= dataFile.delete();
        }
        this.fileMap.clear();
        this.fileByFileMap.clear();
        this.lastAppendLocation.set(null);
        this.mark = null;
        this.currentWriteFile = null;
        this.accessorPool = new DataFileAccessorPool(this);
        if (this.useNio) {
            this.appender = new NIODataFileAppender(this);
        }
        else {
            this.appender = new DataFileAppender(this);
        }
        return result;
    }
    
    public synchronized void addInterestInFile(final int file) throws IOException {
        if (file >= 0) {
            final Integer key = file;
            final DataFile dataFile = this.fileMap.get(key);
            if (dataFile == null) {
                throw new IOException("That data file does not exist");
            }
            this.addInterestInFile(dataFile);
        }
    }
    
    synchronized void addInterestInFile(final DataFile dataFile) {
        if (dataFile != null) {
            dataFile.increment();
        }
    }
    
    public synchronized void removeInterestInFile(final int file) throws IOException {
        if (file >= 0) {
            final Integer key = file;
            final DataFile dataFile = this.fileMap.get(key);
            this.removeInterestInFile(dataFile);
        }
    }
    
    synchronized void removeInterestInFile(final DataFile dataFile) throws IOException {
        if (dataFile != null && dataFile.decrement() <= 0) {
            this.removeDataFile(dataFile);
        }
    }
    
    public synchronized void consolidateDataFilesNotIn(final Set<Integer> inUse, final Set<Integer> inProgress) throws IOException {
        final Set<Integer> unUsed = new HashSet<Integer>(this.fileMap.keySet());
        unUsed.removeAll(inUse);
        unUsed.removeAll(inProgress);
        final List<DataFile> purgeList = new ArrayList<DataFile>();
        for (final Integer key : unUsed) {
            final DataFile dataFile = this.fileMap.get(key);
            purgeList.add(dataFile);
        }
        for (final DataFile dataFile2 : purgeList) {
            if (dataFile2.getDataFileId() != this.currentWriteFile.getDataFileId()) {
                this.forceRemoveDataFile(dataFile2);
            }
        }
    }
    
    public synchronized void consolidateDataFilesNotIn(final Set<Integer> inUse, final Integer lastFile) throws IOException {
        final Set<Integer> unUsed = new HashSet<Integer>(this.fileMap.keySet());
        unUsed.removeAll(inUse);
        final List<DataFile> purgeList = new ArrayList<DataFile>();
        for (final Integer key : unUsed) {
            if (key < lastFile) {
                final DataFile dataFile = this.fileMap.get(key);
                purgeList.add(dataFile);
            }
        }
        if (AsyncDataManager.LOG.isDebugEnabled()) {
            AsyncDataManager.LOG.debug("lastFileId=" + lastFile + ", purgeList: (" + purgeList.size() + ") " + purgeList);
        }
        for (final DataFile dataFile2 : purgeList) {
            this.forceRemoveDataFile(dataFile2);
        }
    }
    
    public synchronized void consolidateDataFiles() throws IOException {
        final List<DataFile> purgeList = new ArrayList<DataFile>();
        for (final DataFile dataFile : this.fileMap.values()) {
            if (dataFile.isUnused()) {
                purgeList.add(dataFile);
            }
        }
        for (final DataFile dataFile : purgeList) {
            this.removeDataFile(dataFile);
        }
    }
    
    private synchronized void removeDataFile(final DataFile dataFile) throws IOException {
        if (dataFile == this.currentWriteFile || this.mark == null || dataFile.getDataFileId() >= this.mark.getDataFileId()) {
            AsyncDataManager.LOG.debug("Won't remove DataFile" + dataFile);
            return;
        }
        this.forceRemoveDataFile(dataFile);
    }
    
    private synchronized void forceRemoveDataFile(final DataFile dataFile) throws IOException {
        this.accessorPool.disposeDataFileAccessors(dataFile);
        this.fileByFileMap.remove(dataFile.getFile());
        this.fileMap.remove(dataFile.getDataFileId());
        this.storeSize.addAndGet(-dataFile.getLength());
        dataFile.unlink();
        if (this.archiveDataLogs) {
            dataFile.move(this.getDirectoryArchive());
            AsyncDataManager.LOG.debug("moved data file " + dataFile + " to " + this.getDirectoryArchive());
        }
        else {
            final boolean result = dataFile.delete();
            if (!result) {
                AsyncDataManager.LOG.info("Failed to discard data file " + dataFile);
            }
        }
    }
    
    public int getMaxFileLength() {
        return this.maxFileLength;
    }
    
    public void setMaxFileLength(final int maxFileLength) {
        this.maxFileLength = maxFileLength;
    }
    
    @Override
    public String toString() {
        return "DataManager:(" + this.filePrefix + ")";
    }
    
    public synchronized Location getMark() throws IllegalStateException {
        return this.mark;
    }
    
    public synchronized Location getNextLocation(final Location location) throws IOException, IllegalStateException {
        Location cur = null;
        while (true) {
            if (cur == null) {
                if (location == null) {
                    final DataFile head = (DataFile)this.currentWriteFile.getHeadNode();
                    cur = new Location();
                    cur.setDataFileId(head.getDataFileId());
                    cur.setOffset(0);
                }
                else if (location.getSize() == -1) {
                    cur = new Location(location);
                }
                else {
                    cur = new Location(location);
                    cur.setOffset(location.getOffset() + location.getSize());
                }
            }
            else {
                cur.setOffset(cur.getOffset() + cur.getSize());
            }
            DataFile dataFile = this.getDataFile(cur);
            if (dataFile.getLength() <= cur.getOffset()) {
                dataFile = this.getNextDataFile(dataFile);
                if (dataFile == null) {
                    return null;
                }
                cur.setDataFileId(dataFile.getDataFileId());
                cur.setOffset(0);
            }
            final DataFileAccessor reader = this.accessorPool.openDataFileAccessor(dataFile);
            try {
                reader.readLocationDetails(cur);
            }
            finally {
                this.accessorPool.closeDataFileAccessor(reader);
            }
            if (cur.getType() == 0) {
                return null;
            }
            if (cur.getType() > 0) {
                return cur;
            }
        }
    }
    
    public synchronized Location getNextLocation(final File file, final Location lastLocation, final boolean thisFileOnly) throws IllegalStateException, IOException {
        final DataFile df = this.fileByFileMap.get(file);
        return this.getNextLocation(df, lastLocation, thisFileOnly);
    }
    
    public synchronized Location getNextLocation(DataFile dataFile, final Location lastLocation, final boolean thisFileOnly) throws IOException, IllegalStateException {
        Location cur = null;
        while (true) {
            if (cur == null) {
                if (lastLocation == null) {
                    final DataFile head = (DataFile)dataFile.getHeadNode();
                    cur = new Location();
                    cur.setDataFileId(head.getDataFileId());
                    cur.setOffset(0);
                }
                else {
                    cur = new Location(lastLocation);
                    cur.setOffset(cur.getOffset() + cur.getSize());
                }
            }
            else {
                cur.setOffset(cur.getOffset() + cur.getSize());
            }
            if (dataFile.getLength() <= cur.getOffset()) {
                if (thisFileOnly) {
                    return null;
                }
                dataFile = this.getNextDataFile(dataFile);
                if (dataFile == null) {
                    return null;
                }
                cur.setDataFileId(dataFile.getDataFileId());
                cur.setOffset(0);
            }
            final DataFileAccessor reader = this.accessorPool.openDataFileAccessor(dataFile);
            try {
                reader.readLocationDetails(cur);
            }
            finally {
                this.accessorPool.closeDataFileAccessor(reader);
            }
            if (cur.getType() == 0) {
                return null;
            }
            if (cur.getType() > 0) {
                return cur;
            }
        }
    }
    
    public synchronized ByteSequence read(final Location location) throws IOException, IllegalStateException {
        final DataFile dataFile = this.getDataFile(location);
        final DataFileAccessor reader = this.accessorPool.openDataFileAccessor(dataFile);
        ByteSequence rc = null;
        try {
            rc = reader.readRecord(location);
        }
        finally {
            this.accessorPool.closeDataFileAccessor(reader);
        }
        return rc;
    }
    
    public void setMark(final Location location, final boolean sync) throws IOException, IllegalStateException {
        synchronized (this) {
            this.mark = location;
        }
        this.storeState(sync);
    }
    
    protected synchronized void storeState(final boolean sync) throws IOException {
        final ByteSequence state = this.marshallState();
        this.appender.storeItem(state, (byte)(-1), sync);
        this.controlFile.store(state, sync);
    }
    
    public synchronized Location write(final ByteSequence data, final boolean sync) throws IOException, IllegalStateException {
        final Location loc = this.appender.storeItem(data, (byte)1, sync);
        return loc;
    }
    
    public synchronized Location write(final ByteSequence data, final Runnable onComplete) throws IOException, IllegalStateException {
        final Location loc = this.appender.storeItem(data, (byte)1, onComplete);
        return loc;
    }
    
    public synchronized Location write(final ByteSequence data, final byte type, final boolean sync) throws IOException, IllegalStateException {
        return this.appender.storeItem(data, type, sync);
    }
    
    public void update(final Location location, final ByteSequence data, final boolean sync) throws IOException {
        final DataFile dataFile = this.getDataFile(location);
        final DataFileAccessor updater = this.accessorPool.openDataFileAccessor(dataFile);
        try {
            updater.updateRecord(location, data, sync);
        }
        finally {
            this.accessorPool.closeDataFileAccessor(updater);
        }
    }
    
    public File getDirectory() {
        return this.directory;
    }
    
    public void setDirectory(final File directory) {
        this.directory = directory;
    }
    
    public String getFilePrefix() {
        return this.filePrefix;
    }
    
    public void setFilePrefix(final String filePrefix) {
        this.filePrefix = IOHelper.toFileSystemSafeName(filePrefix);
    }
    
    public Map<DataFileAppender.WriteKey, DataFileAppender.WriteCommand> getInflightWrites() {
        return this.inflightWrites;
    }
    
    public Location getLastAppendLocation() {
        return this.lastAppendLocation.get();
    }
    
    public void setLastAppendLocation(final Location lastSyncedLocation) {
        this.lastAppendLocation.set(lastSyncedLocation);
    }
    
    public boolean isUseNio() {
        return this.useNio;
    }
    
    public void setUseNio(final boolean useNio) {
        this.useNio = useNio;
    }
    
    public File getDirectoryArchive() {
        return this.directoryArchive;
    }
    
    public void setDirectoryArchive(final File directoryArchive) {
        this.directoryArchive = directoryArchive;
    }
    
    public boolean isArchiveDataLogs() {
        return this.archiveDataLogs;
    }
    
    public void setArchiveDataLogs(final boolean archiveDataLogs) {
        this.archiveDataLogs = archiveDataLogs;
    }
    
    public synchronized Integer getCurrentDataFileId() {
        if (this.currentWriteFile == null) {
            return null;
        }
        return this.currentWriteFile.getDataFileId();
    }
    
    public Set<File> getFiles() {
        return this.fileByFileMap.keySet();
    }
    
    public synchronized long getDiskSize() {
        long rc = 0L;
        for (DataFile cur = (DataFile)this.currentWriteFile.getHeadNode(); cur != null; cur = (DataFile)cur.getNext()) {
            rc += cur.getLength();
        }
        return rc;
    }
    
    public synchronized long getDiskSizeUntil(final Location startPosition) {
        long rc = 0L;
        for (DataFile cur = (DataFile)this.currentWriteFile.getHeadNode(); cur != null; cur = (DataFile)cur.getNext()) {
            if (cur.getDataFileId() >= startPosition.getDataFileId()) {
                return rc + startPosition.getOffset();
            }
            rc += cur.getLength();
        }
        return rc;
    }
    
    static {
        ITEM_HEAD_SOR = new byte[] { 83, 79, 82 };
        ITEM_HEAD_EOR = new byte[] { 69, 79, 82 };
        LOG = LoggerFactory.getLogger(AsyncDataManager.class);
    }
}
