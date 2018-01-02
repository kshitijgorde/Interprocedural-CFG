// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.async;

import org.apache.activemq.util.LinkedNode;
import java.util.concurrent.atomic.AtomicReference;
import java.io.RandomAccessFile;
import org.apache.activemq.util.DataByteArrayOutputStream;
import java.io.InterruptedIOException;
import org.apache.activemq.util.ByteSequence;
import java.util.concurrent.CountDownLatch;
import java.io.IOException;
import java.util.Map;

class DataFileAppender
{
    protected static final byte[] RESERVED_SPACE;
    protected static final int DEFAULT_MAX_BATCH_SIZE = 4194304;
    protected final AsyncDataManager dataManager;
    protected final Map<WriteKey, WriteCommand> inflightWrites;
    protected final Object enqueueMutex;
    protected WriteBatch nextWriteBatch;
    protected boolean shutdown;
    protected IOException firstAsyncException;
    protected final CountDownLatch shutdownDone;
    protected int maxWriteBatchSize;
    protected boolean running;
    private Thread thread;
    
    public DataFileAppender(final AsyncDataManager dataManager) {
        this.enqueueMutex = new Object() {};
        this.shutdownDone = new CountDownLatch(1);
        this.maxWriteBatchSize = 4194304;
        this.dataManager = dataManager;
        this.inflightWrites = this.dataManager.getInflightWrites();
    }
    
    public Location storeItem(final ByteSequence data, final byte type, final boolean sync) throws IOException {
        final int size = data.getLength() + 32;
        final Location location = new Location();
        location.setSize(size);
        location.setType(type);
        final WriteCommand write = new WriteCommand(location, data, sync);
        final WriteBatch batch;
        synchronized (this) {
            final DataFile dataFile = this.dataManager.allocateLocation(location);
            if (!sync) {
                this.inflightWrites.put(new WriteKey(location), write);
            }
            batch = this.enqueue(dataFile, write);
        }
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
        final int size = data.getLength() + 32;
        final Location location = new Location();
        location.setSize(size);
        location.setType(type);
        final WriteCommand write = new WriteCommand(location, data, onComplete);
        final WriteBatch batch;
        synchronized (this) {
            final DataFile dataFile = this.dataManager.allocateLocation(location);
            this.inflightWrites.put(new WriteKey(location), write);
            batch = this.enqueue(dataFile, write);
        }
        location.setLatch(batch.latch);
        return location;
    }
    
    private WriteBatch enqueue(final DataFile dataFile, final WriteCommand write) throws IOException {
        synchronized (this.enqueueMutex) {
            WriteBatch rc = null;
            if (this.shutdown) {
                throw new IOException("Async Writter Thread Shutdown");
            }
            if (!this.running) {
                this.running = true;
                (this.thread = new Thread() {
                    @Override
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
            if (this.nextWriteBatch == null) {
                this.nextWriteBatch = new WriteBatch(dataFile, write);
                rc = this.nextWriteBatch;
                this.enqueueMutex.notify();
            }
            else if (this.nextWriteBatch.canAppend(dataFile, write)) {
                this.nextWriteBatch.append(write);
                rc = this.nextWriteBatch;
            }
            else {
                try {
                    while (this.nextWriteBatch != null) {
                        this.enqueueMutex.wait();
                    }
                }
                catch (InterruptedException e) {
                    throw new InterruptedIOException();
                }
                if (this.shutdown) {
                    throw new IOException("Async Writter Thread Shutdown");
                }
                this.nextWriteBatch = new WriteBatch(dataFile, write);
                rc = this.nextWriteBatch;
                this.enqueueMutex.notify();
            }
            return rc;
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
                    this.enqueueMutex.notify();
                }
                wb = (WriteBatch)o;
                if (dataFile != wb.dataFile) {
                    if (file != null) {
                        dataFile.closeRandomAccessFile(file);
                    }
                    dataFile = wb.dataFile;
                    file = dataFile.openRandomAccessFile(true);
                }
                WriteCommand write = wb.first;
                file.seek(write.location.getOffset());
                boolean forceToDisk = false;
                if (wb.size == write.location.getSize()) {
                    forceToDisk = (write.sync | write.onComplete != null);
                    file.writeInt(write.location.getSize());
                    file.writeByte(write.location.getType());
                    file.write(DataFileAppender.RESERVED_SPACE);
                    file.write(AsyncDataManager.ITEM_HEAD_SOR);
                    file.write(write.data.getData(), write.data.getOffset(), write.data.getLength());
                    file.write(AsyncDataManager.ITEM_HEAD_EOR);
                }
                else {
                    while (write != null) {
                        forceToDisk |= (write.sync | write.onComplete != null);
                        buff.writeInt(write.location.getSize());
                        buff.writeByte(write.location.getType());
                        buff.write(DataFileAppender.RESERVED_SPACE);
                        buff.write(AsyncDataManager.ITEM_HEAD_SOR);
                        buff.write(write.data.getData(), write.data.getOffset(), write.data.getLength());
                        buff.write(AsyncDataManager.ITEM_HEAD_EOR);
                        write = (WriteCommand)write.getNext();
                    }
                    final ByteSequence sequence = buff.toByteSequence();
                    file.write(sequence.getData(), sequence.getOffset(), sequence.getLength());
                    buff.reset();
                }
                if (forceToDisk) {
                    file.getFD().sync();
                }
                final WriteCommand lastWrite = (WriteCommand)wb.first.getTailNode();
                this.dataManager.setLastAppendLocation(lastWrite.location);
                for (write = wb.first; write != null; write = (WriteCommand)write.getNext()) {
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
                    wb.latch.countDown();
                    wb.exception.set(e2);
                }
                if (this.nextWriteBatch != null) {
                    this.nextWriteBatch.latch.countDown();
                    this.nextWriteBatch.exception.set(e2);
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
        }
    }
    
    static {
        RESERVED_SPACE = new byte[21];
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
        
        @Override
        public int hashCode() {
            return this.hash;
        }
        
        @Override
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
        public final WriteCommand first;
        public final CountDownLatch latch;
        public int size;
        public AtomicReference<IOException> exception;
        
        public WriteBatch(final DataFile dataFile, final WriteCommand write) throws IOException {
            this.latch = new CountDownLatch(1);
            this.exception = new AtomicReference<IOException>();
            this.dataFile = dataFile;
            this.first = write;
            this.size += write.location.getSize();
        }
        
        public boolean canAppend(final DataFile dataFile, final WriteCommand write) {
            return dataFile == this.dataFile && this.size + write.location.getSize() < DataFileAppender.this.maxWriteBatchSize;
        }
        
        public void append(final WriteCommand write) throws IOException {
            this.first.getTailNode().linkAfter(write);
            this.size += write.location.getSize();
        }
    }
    
    public static class WriteCommand extends LinkedNode
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
