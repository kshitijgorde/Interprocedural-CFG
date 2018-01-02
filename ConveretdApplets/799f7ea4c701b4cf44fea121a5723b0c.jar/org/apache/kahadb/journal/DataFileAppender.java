// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.kahadb.journal;

import org.apache.kahadb.util.LinkedNode;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.kahadb.util.LinkedNodeList;
import java.util.zip.Checksum;
import java.io.RandomAccessFile;
import java.util.zip.Adler32;
import org.apache.kahadb.util.DataByteArrayOutputStream;
import java.io.InterruptedIOException;
import org.apache.kahadb.util.ByteSequence;
import java.util.concurrent.CountDownLatch;
import java.io.IOException;
import java.util.Map;

class DataFileAppender
{
    protected final Journal journal;
    protected final Map<WriteKey, WriteCommand> inflightWrites;
    protected final Object enqueueMutex;
    protected WriteBatch nextWriteBatch;
    protected boolean shutdown;
    protected IOException firstAsyncException;
    protected final CountDownLatch shutdownDone;
    protected int maxWriteBatchSize;
    private boolean running;
    private Thread thread;
    public static final String PROPERTY_LOG_WRITE_STAT_WINDOW = "org.apache.kahadb.journal.appender.WRITE_STAT_WINDOW";
    public static final int maxStat;
    int statIdx;
    int[] stats;
    
    public DataFileAppender(final Journal dataManager) {
        this.enqueueMutex = new Object() {};
        this.shutdownDone = new CountDownLatch(1);
        this.statIdx = 0;
        this.stats = new int[DataFileAppender.maxStat];
        this.journal = dataManager;
        this.inflightWrites = this.journal.getInflightWrites();
        this.maxWriteBatchSize = this.journal.getWriteBatchSize();
    }
    
    public Location storeItem(final ByteSequence data, final byte type, final boolean sync) throws IOException {
        final int size = data.getLength() + 5;
        final Location location = new Location();
        location.setSize(size);
        location.setType(type);
        final WriteCommand write = new WriteCommand(location, data, sync);
        final WriteBatch batch = this.enqueue(write);
        location.setLatch(batch.latch);
        if (sync) {
            try {
                batch.latch.await();
            }
            catch (InterruptedException e) {
                throw new InterruptedIOException();
            }
            final IOException exception = batch.exception.get();
            if (exception != null) {
                throw exception;
            }
        }
        return location;
    }
    
    public Location storeItem(final ByteSequence data, final byte type, final Runnable onComplete) throws IOException {
        final int size = data.getLength() + 5;
        final Location location = new Location();
        location.setSize(size);
        location.setType(type);
        final WriteCommand write = new WriteCommand(location, data, onComplete);
        final WriteBatch batch = this.enqueue(write);
        location.setLatch(batch.latch);
        return location;
    }
    
    private WriteBatch enqueue(final WriteCommand write) throws IOException {
        synchronized (this.enqueueMutex) {
            if (this.shutdown) {
                throw new IOException("Async Writter Thread Shutdown");
            }
            if (!this.running) {
                this.running = true;
                (this.thread = new Thread() {
                    public void run() {
                        DataFileAppender.this.processQueue();
                    }
                }).setPriority(10);
                this.thread.setDaemon(true);
                this.thread.setName("ActiveMQ Data File Writer");
                this.thread.start();
                this.firstAsyncException = null;
            }
            if (this.firstAsyncException != null) {
                throw this.firstAsyncException;
            }
            while (true) {
                while (this.nextWriteBatch != null) {
                    if (this.nextWriteBatch.canAppend(write)) {
                        this.nextWriteBatch.append(write);
                        if (!write.sync) {
                            this.inflightWrites.put(new WriteKey(write.location), write);
                        }
                        return this.nextWriteBatch;
                    }
                    try {
                        while (this.nextWriteBatch != null) {
                            final long start = System.currentTimeMillis();
                            this.enqueueMutex.wait();
                            if (DataFileAppender.maxStat > 0) {
                                System.err.println("Watiting for write to finish with full batch... millis: " + (System.currentTimeMillis() - start));
                            }
                        }
                    }
                    catch (InterruptedException e) {
                        throw new InterruptedIOException();
                    }
                    if (this.shutdown) {
                        throw new IOException("Async Writter Thread Shutdown");
                    }
                }
                DataFile file = this.journal.getCurrentWriteFile();
                if (file.getLength() > this.journal.getMaxFileLength()) {
                    file = this.journal.rotateWriteFile();
                }
                this.nextWriteBatch = new WriteBatch(file, file.getLength(), write);
                this.enqueueMutex.notifyAll();
                continue;
            }
        }
    }
    
    public void close() throws IOException {
        synchronized (this.enqueueMutex) {
            if (!this.shutdown) {
                this.shutdown = true;
                if (this.running) {
                    this.enqueueMutex.notifyAll();
                }
                else {
                    this.shutdownDone.countDown();
                }
            }
        }
        try {
            this.shutdownDone.await();
        }
        catch (InterruptedException e) {
            throw new InterruptedIOException();
        }
    }
    
    protected void processQueue() {
        DataFile dataFile = null;
        RandomAccessFile file = null;
        WriteBatch wb = null;
        try {
            final DataByteArrayOutputStream buff = new DataByteArrayOutputStream(this.maxWriteBatchSize);
            while (true) {
                Object o = null;
                synchronized (this.enqueueMutex) {
                    while (this.nextWriteBatch == null) {
                        if (this.shutdown) {
                            return;
                        }
                        this.enqueueMutex.wait();
                    }
                    o = this.nextWriteBatch;
                    this.nextWriteBatch = null;
                    this.enqueueMutex.notifyAll();
                }
                wb = (WriteBatch)o;
                if (dataFile != wb.dataFile) {
                    if (file != null) {
                        file.setLength(dataFile.getLength());
                        dataFile.closeRandomAccessFile(file);
                    }
                    dataFile = wb.dataFile;
                    file = dataFile.openRandomAccessFile();
                    if (file.length() < this.journal.preferedFileLength) {
                        file.setLength(this.journal.preferedFileLength);
                    }
                }
                WriteCommand write = wb.writes.getHead();
                buff.reset();
                buff.writeInt(Journal.BATCH_CONTROL_RECORD_SIZE);
                buff.writeByte(2);
                buff.write(Journal.BATCH_CONTROL_RECORD_MAGIC);
                buff.writeInt(0);
                buff.writeLong(0L);
                boolean forceToDisk = false;
                while (write != null) {
                    forceToDisk |= (write.sync | write.onComplete != null);
                    buff.writeInt(write.location.getSize());
                    buff.writeByte(write.location.getType());
                    buff.write(write.data.getData(), write.data.getOffset(), write.data.getLength());
                    write = write.getNext();
                }
                final ByteSequence sequence = buff.toByteSequence();
                buff.reset();
                buff.skip(5 + Journal.BATCH_CONTROL_RECORD_MAGIC.length);
                buff.writeInt(sequence.getLength() - Journal.BATCH_CONTROL_RECORD_SIZE);
                if (this.journal.isChecksum()) {
                    final Checksum checksum = new Adler32();
                    checksum.update(sequence.getData(), sequence.getOffset() + Journal.BATCH_CONTROL_RECORD_SIZE, sequence.getLength() - Journal.BATCH_CONTROL_RECORD_SIZE);
                    buff.writeLong(checksum.getValue());
                }
                file.seek(wb.offset);
                if (DataFileAppender.maxStat > 0) {
                    if (this.statIdx < DataFileAppender.maxStat) {
                        this.stats[this.statIdx++] = sequence.getLength();
                    }
                    else {
                        long all = 0L;
                        while (this.statIdx > 0) {
                            final long n = all;
                            final int[] stats = this.stats;
                            final int statIdx = this.statIdx - 1;
                            this.statIdx = statIdx;
                            all = n + stats[statIdx];
                        }
                        System.err.println("Ave writeSize: " + all / DataFileAppender.maxStat);
                    }
                }
                file.write(sequence.getData(), sequence.getOffset(), sequence.getLength());
                final ReplicationTarget replicationTarget = this.journal.getReplicationTarget();
                if (replicationTarget != null) {
                    replicationTarget.replicate(wb.writes.getHead().location, sequence, forceToDisk);
                }
                if (forceToDisk) {
                    file.getFD().sync();
                }
                final WriteCommand lastWrite = wb.writes.getTail();
                this.journal.setLastAppendLocation(lastWrite.location);
                for (write = wb.writes.getHead(); write != null; write = write.getNext()) {
                    if (!write.sync) {
                        this.inflightWrites.remove(new WriteKey(write.location));
                    }
                    if (write.onComplete != null) {
                        try {
                            write.onComplete.run();
                        }
                        catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }
                }
                wb.latch.countDown();
            }
        }
        catch (IOException e2) {
            synchronized (this.enqueueMutex) {
                this.firstAsyncException = e2;
                if (wb != null) {
                    wb.exception.set(e2);
                    wb.latch.countDown();
                }
                if (this.nextWriteBatch != null) {
                    this.nextWriteBatch.exception.set(e2);
                    this.nextWriteBatch.latch.countDown();
                }
            }
        }
        catch (InterruptedException e3) {}
        finally {
            try {
                if (file != null) {
                    dataFile.closeRandomAccessFile(file);
                }
            }
            catch (Throwable t) {}
            this.shutdownDone.countDown();
            this.running = false;
        }
    }
    
    static {
        maxStat = Integer.parseInt(System.getProperty("org.apache.kahadb.journal.appender.WRITE_STAT_WINDOW", "0"));
    }
    
    public static class WriteKey
    {
        private final int file;
        private final long offset;
        private final int hash;
        
        public WriteKey(final Location item) {
            this.file = item.getDataFileId();
            this.offset = item.getOffset();
            this.hash = (int)(this.file ^ this.offset);
        }
        
        public int hashCode() {
            return this.hash;
        }
        
        public boolean equals(final Object obj) {
            if (obj instanceof WriteKey) {
                final WriteKey di = (WriteKey)obj;
                return di.file == this.file && di.offset == this.offset;
            }
            return false;
        }
    }
    
    public class WriteBatch
    {
        public final DataFile dataFile;
        public final LinkedNodeList<WriteCommand> writes;
        public final CountDownLatch latch;
        private final int offset;
        public int size;
        public AtomicReference<IOException> exception;
        
        public WriteBatch(final DataFile dataFile, final int offset, final WriteCommand write) throws IOException {
            this.writes = new LinkedNodeList<WriteCommand>();
            this.latch = new CountDownLatch(1);
            this.size = Journal.BATCH_CONTROL_RECORD_SIZE;
            this.exception = new AtomicReference<IOException>();
            this.dataFile = dataFile;
            this.offset = offset;
            this.dataFile.incrementLength(Journal.BATCH_CONTROL_RECORD_SIZE);
            this.size = Journal.BATCH_CONTROL_RECORD_SIZE;
            DataFileAppender.this.journal.addToTotalLength(Journal.BATCH_CONTROL_RECORD_SIZE);
            this.append(write);
        }
        
        public boolean canAppend(final WriteCommand write) {
            final int newSize = this.size + write.location.getSize();
            return newSize < DataFileAppender.this.maxWriteBatchSize && this.offset + newSize <= DataFileAppender.this.journal.getMaxFileLength();
        }
        
        public void append(final WriteCommand write) throws IOException {
            this.writes.addLast(write);
            write.location.setDataFileId(this.dataFile.getDataFileId());
            write.location.setOffset(this.offset + this.size);
            final int s = write.location.getSize();
            this.size += s;
            this.dataFile.incrementLength(s);
            DataFileAppender.this.journal.addToTotalLength(s);
        }
    }
    
    public static class WriteCommand extends LinkedNode<WriteCommand>
    {
        public final Location location;
        public final ByteSequence data;
        final boolean sync;
        public final Runnable onComplete;
        
        public WriteCommand(final Location location, final ByteSequence data, final boolean sync) {
            this.location = location;
            this.data = data;
            this.sync = sync;
            this.onComplete = null;
        }
        
        public WriteCommand(final Location location, final ByteSequence data, final Runnable onComplete) {
            this.location = location;
            this.data = data;
            this.onComplete = onComplete;
            this.sync = false;
        }
    }
}
