// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.async;

import java.nio.channels.FileChannel;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.nio.ByteBuffer;

class NIODataFileAppender extends DataFileAppender
{
    public NIODataFileAppender(final AsyncDataManager fileManager) {
        super(fileManager);
    }
    
    @Override
    protected void processQueue() {
        DataFile dataFile = null;
        RandomAccessFile file = null;
        FileChannel channel = null;
        WriteBatch wb = null;
        try {
            final ByteBuffer header = ByteBuffer.allocateDirect(29);
            final ByteBuffer footer = ByteBuffer.allocateDirect(3);
            final ByteBuffer buffer = ByteBuffer.allocateDirect(this.maxWriteBatchSize);
            header.putInt(0);
            header.put((byte)0);
            header.put(NIODataFileAppender.RESERVED_SPACE);
            header.put(AsyncDataManager.ITEM_HEAD_SOR);
            footer.put(AsyncDataManager.ITEM_HEAD_EOR);
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
                    channel = file.getChannel();
                }
                WriteCommand write = wb.first;
                file.seek(write.location.getOffset());
                boolean forceToDisk = false;
                if (wb.size == write.location.getSize()) {
                    forceToDisk = (write.sync | write.onComplete != null);
                    header.clear();
                    header.putInt(write.location.getSize());
                    header.put(write.location.getType());
                    header.clear();
                    this.transfer(header, channel);
                    final ByteBuffer source = ByteBuffer.wrap(write.data.getData(), write.data.getOffset(), write.data.getLength());
                    this.transfer(source, channel);
                    footer.clear();
                    this.transfer(footer, channel);
                }
                else {
                    while (write != null) {
                        forceToDisk |= (write.sync | write.onComplete != null);
                        header.clear();
                        header.putInt(write.location.getSize());
                        header.put(write.location.getType());
                        header.clear();
                        this.copy(header, buffer);
                        assert !header.hasRemaining();
                        final ByteBuffer source = ByteBuffer.wrap(write.data.getData(), write.data.getOffset(), write.data.getLength());
                        this.copy(source, buffer);
                        assert !source.hasRemaining();
                        footer.clear();
                        this.copy(footer, buffer);
                        assert !footer.hasRemaining();
                        write = (WriteCommand)write.getNext();
                    }
                    buffer.flip();
                    this.transfer(buffer, channel);
                    buffer.clear();
                }
                if (forceToDisk) {
                    file.getChannel().force(false);
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
                    dataFile = null;
                    file.close();
                    file = null;
                }
                if (channel != null) {
                    channel.close();
                    channel = null;
                }
            }
            catch (IOException ex) {}
            this.shutdownDone.countDown();
            this.running = false;
        }
    }
    
    private void transfer(final ByteBuffer header, final FileChannel channel) throws IOException {
        while (header.hasRemaining()) {
            channel.write(header);
        }
    }
    
    private int copy(final ByteBuffer src, final ByteBuffer dest) {
        final int rc = Math.min(dest.remaining(), src.remaining());
        if (rc > 0) {
            final int limit = src.limit();
            src.limit(src.position() + rc);
            dest.put(src);
            src.limit(limit);
        }
        return rc;
    }
}
