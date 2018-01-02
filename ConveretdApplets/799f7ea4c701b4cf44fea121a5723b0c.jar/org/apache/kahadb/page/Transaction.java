// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.kahadb.page;

import java.util.Map;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.io.EOFException;
import org.apache.kahadb.util.ByteSequence;
import java.io.InputStream;
import org.apache.kahadb.util.DataByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.DataOutput;
import org.apache.kahadb.util.DataByteArrayOutputStream;
import org.apache.kahadb.util.Marshaller;
import org.apache.kahadb.util.Sequence;
import java.io.IOException;
import org.apache.kahadb.util.SequenceSet;
import java.util.HashMap;

public class Transaction implements Iterable<Page>
{
    private final PageFile pageFile;
    private long writeTransactionId;
    private HashMap<Long, PageFile.PageWrite> writes;
    private final SequenceSet allocateList;
    private final SequenceSet freeList;
    
    Transaction(final PageFile pageFile) {
        this.writeTransactionId = -1L;
        this.writes = new HashMap<Long, PageFile.PageWrite>();
        this.allocateList = new SequenceSet();
        this.freeList = new SequenceSet();
        this.pageFile = pageFile;
    }
    
    public PageFile getPageFile() {
        return this.pageFile;
    }
    
    public <T> Page<T> allocate() throws IOException {
        return this.allocate(1);
    }
    
    public <T> Page<T> allocate(final int count) throws IOException {
        final Page<T> rc = this.pageFile.allocate(count);
        this.allocateList.add(new Sequence(rc.getPageId(), rc.getPageId() + count - 1L));
        return rc;
    }
    
    public void free(final long pageId) throws IOException {
        this.free((Page<Object>)this.load(pageId, (Marshaller<T>)null));
    }
    
    public void free(final long pageId, final int count) throws IOException {
        this.free((Page<Object>)this.load(pageId, (Marshaller<T>)null), count);
    }
    
    public <T> void free(Page<T> page, final int count) throws IOException {
        this.pageFile.assertLoaded();
        final long offsetPage = page.getPageId();
        for (int i = 0; i < count; ++i) {
            if (page == null) {
                page = this.load(offsetPage + i, (Marshaller<T>)null);
            }
            this.free(page);
            page = null;
        }
    }
    
    public <T> void free(Page<T> page) throws IOException {
        this.pageFile.assertLoaded();
        while (page != null) {
            if (page.getType() == 0) {
                return;
            }
            Page<T> next = null;
            if (page.getType() == 1) {
                next = this.load(page.getNext(), (Marshaller<T>)null);
            }
            page.makeFree(this.getWriteTransactionId());
            final DataByteArrayOutputStream out = new DataByteArrayOutputStream(this.pageFile.getPageSize());
            page.write(out);
            this.write(page, out.getData());
            this.freeList.add(page.getPageId());
            page = next;
        }
    }
    
    public <T> void store(final Page<T> page, final Marshaller<T> marshaller, final boolean overflow) throws IOException {
        final DataByteArrayOutputStream out = (DataByteArrayOutputStream)this.openOutputStream(page, overflow);
        if (marshaller != null) {
            marshaller.writePayload(page.get(), out);
        }
        out.close();
    }
    
    public OutputStream openOutputStream(final Page page, final boolean overflow) throws IOException {
        this.pageFile.assertLoaded();
        final Page copy = page.copy();
        this.pageFile.addToCache(copy);
        final DataByteArrayOutputStream out = new DataByteArrayOutputStream(this.pageFile.getPageSize() * 2) {
            Page current = copy;
            
            protected void onWrite() throws IOException {
                final int pageSize = Transaction.this.pageFile.getPageSize();
                if (this.pos >= pageSize) {
                    if (!overflow) {
                        throw new PageOverflowIOException("Page overflow.");
                    }
                    Page next;
                    if (this.current.getType() == 1) {
                        next = Transaction.this.load(this.current.getNext(), (Marshaller<Object>)null);
                    }
                    else {
                        next = Transaction.this.allocate();
                    }
                    next.txId = this.current.txId;
                    final int oldPos = this.pos;
                    this.pos = 0;
                    this.current.makePagePart(next.getPageId(), Transaction.this.getWriteTransactionId());
                    this.current.write(this);
                    final byte[] data = new byte[pageSize];
                    System.arraycopy(this.buf, 0, data, 0, pageSize);
                    Transaction.this.write(this.current, data);
                    this.pos = 0;
                    this.skip(21);
                    System.arraycopy(this.buf, pageSize, this.buf, this.pos, oldPos - pageSize);
                    this.pos += oldPos - pageSize;
                    this.current = next;
                }
            }
            
            public void close() throws IOException {
                super.close();
                if (this.current.getType() == 1) {
                    Transaction.this.free(this.current.getNext());
                }
                this.current.makePageEnd(this.pos, Transaction.this.getWriteTransactionId());
                this.pos = 0;
                this.current.write(this);
                Transaction.this.write(this.current, this.buf);
            }
        };
        out.skip(21);
        return out;
    }
    
    public <T> Page<T> load(final long pageId, final Marshaller<T> marshaller) throws IOException {
        this.pageFile.assertLoaded();
        final Page<T> page = new Page<T>(pageId);
        this.load(page, marshaller);
        return page;
    }
    
    public <T> void load(final Page<T> page, final Marshaller<T> marshaller) throws IOException {
        this.pageFile.assertLoaded();
        final long pageId = page.getPageId();
        if (pageId < 0L) {
            throw new InvalidPageIOException("Page id is not valid", pageId);
        }
        final PageFile.PageWrite update = this.writes.get(pageId);
        if (update != null) {
            page.copy(update.getPage());
            return;
        }
        final Page<T> t = this.pageFile.getFromCache(pageId);
        if (t != null) {
            page.copy(t);
            return;
        }
        if (marshaller != null) {
            final InputStream is = this.openInputStream(page);
            final DataInputStream dataIn = new DataInputStream(is);
            page.set(marshaller.readPayload(dataIn));
            is.close();
        }
        else {
            final DataByteArrayInputStream in = new DataByteArrayInputStream(new byte[21]);
            this.pageFile.readPage(pageId, in.getRawData());
            page.read(in);
            page.set(null);
        }
        if (marshaller != null) {
            this.pageFile.addToCache(page);
        }
    }
    
    public InputStream openInputStream(final Page p) throws IOException {
        return new InputStream() {
            private ByteSequence chunk = new ByteSequence(new byte[Transaction.this.pageFile.getPageSize()]);
            private Page page = this.readPage(p);
            private int pageCount = 1;
            private Page markPage;
            private ByteSequence markChunk;
            
            private Page readPage(final Page page) throws IOException {
                Transaction.this.pageFile.readPage(page.getPageId(), this.chunk.getData());
                this.chunk.setOffset(0);
                this.chunk.setLength(Transaction.this.pageFile.getPageSize());
                final DataByteArrayInputStream in = new DataByteArrayInputStream(this.chunk);
                page.read(in);
                this.chunk.setOffset(21);
                if (page.getType() == 2) {
                    this.chunk.setLength((int)page.getNext());
                }
                if (page.getType() == 0) {
                    throw new EOFException("Chunk stream does not exist at page: " + page.getPageId());
                }
                return page;
            }
            
            public int read() throws IOException {
                if (!this.atEOF()) {
                    return this.chunk.data[this.chunk.offset++] & 0xFF;
                }
                return -1;
            }
            
            private boolean atEOF() throws IOException {
                if (this.chunk.offset < this.chunk.length) {
                    return false;
                }
                if (this.page.getType() == 2) {
                    return true;
                }
                this.fill();
                return this.chunk.offset >= this.chunk.length;
            }
            
            private void fill() throws IOException {
                this.page = this.readPage(new Page(this.page.getNext()));
                ++this.pageCount;
            }
            
            public int read(final byte[] b) throws IOException {
                return this.read(b, 0, b.length);
            }
            
            public int read(final byte[] b, final int off, int len) throws IOException {
                if (!this.atEOF()) {
                    int rc;
                    for (rc = 0; !this.atEOF() && rc < len; rc += len) {
                        len = Math.min(len, this.chunk.length - this.chunk.offset);
                        if (len > 0) {
                            System.arraycopy(this.chunk.data, this.chunk.offset, b, off, len);
                            final ByteSequence chunk = this.chunk;
                            chunk.offset += len;
                        }
                    }
                    return rc;
                }
                return -1;
            }
            
            public long skip(long len) throws IOException {
                if (this.atEOF()) {
                    int rc;
                    for (rc = 0; !this.atEOF() && rc < len; rc += (int)len) {
                        len = Math.min(len, this.chunk.length - this.chunk.offset);
                        if (len > 0L) {
                            final ByteSequence chunk = this.chunk;
                            chunk.offset += (int)len;
                        }
                    }
                    return rc;
                }
                return -1L;
            }
            
            public int available() {
                return this.chunk.length - this.chunk.offset;
            }
            
            public boolean markSupported() {
                return true;
            }
            
            public void mark(final int markpos) {
                this.markPage = this.page;
                final byte[] data = new byte[Transaction.this.pageFile.getPageSize()];
                System.arraycopy(this.chunk.getData(), 0, data, 0, Transaction.this.pageFile.getPageSize());
                this.markChunk = new ByteSequence(data, this.chunk.getOffset(), this.chunk.getLength());
            }
            
            public void reset() {
                this.page = this.markPage;
                this.chunk = this.markChunk;
            }
        };
    }
    
    public Iterator<Page> iterator() {
        return this.iterator(false);
    }
    
    public Iterator<Page> iterator(final boolean includeFreePages) {
        this.pageFile.assertLoaded();
        return new Iterator<Page>() {
            long nextId;
            Page nextPage;
            Page lastPage;
            
            private void findNextPage() {
                if (!Transaction.this.pageFile.isLoaded()) {
                    throw new IllegalStateException("Cannot iterate the pages when the page file is not loaded");
                }
                if (this.nextPage != null) {
                    return;
                }
                try {
                    while (this.nextId < Transaction.this.pageFile.getPageCount()) {
                        final Page page = Transaction.this.load(this.nextId, (Marshaller<Object>)null);
                        if (includeFreePages || page.getType() != 0) {
                            this.nextPage = page;
                            return;
                        }
                        ++this.nextId;
                    }
                }
                catch (IOException ex) {}
            }
            
            public boolean hasNext() {
                this.findNextPage();
                return this.nextPage != null;
            }
            
            public Page next() {
                this.findNextPage();
                if (this.nextPage != null) {
                    this.lastPage = this.nextPage;
                    this.nextPage = null;
                    ++this.nextId;
                    return this.lastPage;
                }
                throw new NoSuchElementException();
            }
            
            public void remove() {
                if (this.lastPage == null) {
                    throw new IllegalStateException();
                }
                try {
                    Transaction.this.free((Page<Object>)this.lastPage);
                    this.lastPage = null;
                }
                catch (IOException e) {
                    new RuntimeException(e);
                }
            }
        };
    }
    
    public void commit() throws IOException {
        if (this.writeTransactionId != -1L) {
            this.pageFile.write(this.writes.entrySet());
            this.freePages(this.freeList);
            this.freeList.clear();
            this.allocateList.clear();
            this.writes.clear();
            this.writeTransactionId = -1L;
        }
    }
    
    public void rollback() throws IOException {
        if (this.writeTransactionId != -1L) {
            this.freePages(this.allocateList);
            this.freeList.clear();
            this.allocateList.clear();
            this.writes.clear();
            this.writeTransactionId = -1L;
        }
    }
    
    private long getWriteTransactionId() {
        if (this.writeTransactionId == -1L) {
            this.writeTransactionId = this.pageFile.getNextWriteTransactionId();
        }
        return this.writeTransactionId;
    }
    
    private void write(final Page page, final byte[] data) throws IOException {
        final Long key = page.getPageId();
        this.writes.put(key, new PageFile.PageWrite(page, data));
    }
    
    private void freePages(final SequenceSet list) throws RuntimeException {
        for (Sequence seq = list.getHead(); seq != null; seq = seq.getNext()) {
            seq.each((Sequence.Closure<Throwable>)new Sequence.Closure<RuntimeException>() {
                public void execute(final long value) {
                    Transaction.this.pageFile.freePage(value);
                }
            });
        }
    }
    
    public boolean isReadOnly() {
        return this.writeTransactionId == -1L;
    }
    
    public <T extends Throwable> void execute(final Closure<T> closure) throws T, IOException, Throwable {
        boolean success = false;
        try {
            closure.execute(this);
            success = true;
        }
        finally {
            if (success) {
                this.commit();
            }
            else {
                this.rollback();
            }
        }
    }
    
    public <R, T extends Throwable> R execute(final CallableClosure<R, T> closure) throws T, IOException, Throwable {
        boolean success = false;
        try {
            final R rc = closure.execute(this);
            success = true;
            return rc;
        }
        finally {
            if (success) {
                this.commit();
            }
            else {
                this.rollback();
            }
        }
    }
    
    public class PageOverflowIOException extends IOException
    {
        public PageOverflowIOException(final String message) {
            super(message);
        }
    }
    
    public class InvalidPageIOException extends IOException
    {
        private final long page;
        
        public InvalidPageIOException(final String message, final long page) {
            super(message);
            this.page = page;
        }
        
        public long getPage() {
            return this.page;
        }
    }
    
    public interface CallableClosure<R, T extends Throwable>
    {
        R execute(final Transaction p0) throws T, Throwable;
    }
    
    public interface Closure<T extends Throwable>
    {
        void execute(final Transaction p0) throws T, Throwable;
    }
}
