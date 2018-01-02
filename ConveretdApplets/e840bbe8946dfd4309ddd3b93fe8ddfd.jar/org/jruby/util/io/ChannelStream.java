// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.io;

import java.io.FileNotFoundException;
import org.jruby.platform.Platform;
import java.io.File;
import java.io.RandomAccessFile;
import org.jruby.util.JRubyFile;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.nio.channels.Channel;
import java.io.OutputStream;
import java.io.InputStream;
import java.nio.channels.SelectableChannel;
import java.io.EOFException;
import java.nio.channels.FileChannel;
import org.jruby.util.ByteList;
import java.nio.channels.ReadableByteChannel;
import java.io.IOException;
import org.jruby.Ruby;
import java.nio.ByteBuffer;
import org.jruby.Finalizable;

public class ChannelStream implements Stream, Finalizable
{
    private static final boolean DEBUG = false;
    public static final int BUFSIZE = 4096;
    private static final int BULK_READ_SIZE = 16384;
    private static final ByteBuffer EMPTY_BUFFER;
    private volatile Ruby runtime;
    protected ModeFlags modes;
    protected boolean sync;
    protected volatile ByteBuffer buffer;
    protected boolean reading;
    private ChannelDescriptor descriptor;
    private boolean blocking;
    protected int ungotc;
    private volatile boolean closedExplicitly;
    private volatile boolean eof;
    private volatile boolean autoclose;
    
    private ChannelStream(final Ruby runtime, final ChannelDescriptor descriptor, final boolean autoclose) {
        this.sync = false;
        this.blocking = true;
        this.ungotc = -1;
        this.closedExplicitly = false;
        this.eof = false;
        this.autoclose = true;
        this.runtime = runtime;
        this.descriptor = descriptor;
        this.modes = descriptor.getOriginalModes();
        (this.buffer = ByteBuffer.allocate(4096)).flip();
        this.reading = true;
        this.autoclose = autoclose;
        runtime.addInternalFinalizer(this);
    }
    
    private ChannelStream(final Ruby runtime, final ChannelDescriptor descriptor, final ModeFlags modes, final boolean autoclose) {
        this(runtime, descriptor, autoclose);
        this.modes = modes;
    }
    
    public Ruby getRuntime() {
        return this.runtime;
    }
    
    public void checkReadable() throws IOException {
        if (!this.modes.isReadable()) {
            throw new IOException("not opened for reading");
        }
    }
    
    public void checkWritable() throws IOException {
        if (!this.modes.isWritable()) {
            throw new IOException("not opened for writing");
        }
    }
    
    public void checkPermissionsSubsetOf(final ModeFlags subsetModes) {
        subsetModes.isSubsetOf(this.modes);
    }
    
    public ModeFlags getModes() {
        return this.modes;
    }
    
    public boolean isSync() {
        return this.sync;
    }
    
    public void setSync(final boolean sync) {
        this.sync = sync;
    }
    
    public void setBinmode() {
    }
    
    public boolean isAutoclose() {
        return this.autoclose;
    }
    
    public void setAutoclose(final boolean autoclose) {
        this.autoclose = autoclose;
    }
    
    public void waitUntilReady() throws IOException, InterruptedException {
        while (this.ready() == 0) {
            Thread.sleep(10L);
        }
    }
    
    public boolean readDataBuffered() {
        return this.reading && (this.ungotc != -1 || this.buffer.hasRemaining());
    }
    
    public boolean writeDataBuffered() {
        return !this.reading && this.buffer.position() > 0;
    }
    
    private final int refillBuffer() throws IOException {
        this.buffer.clear();
        final int n = ((ReadableByteChannel)this.descriptor.getChannel()).read(this.buffer);
        this.buffer.flip();
        return n;
    }
    
    public synchronized ByteList fgets(final ByteList separatorString) throws IOException, BadDescriptorException {
        this.checkReadable();
        this.ensureRead();
        if (separatorString == null) {
            return this.readall();
        }
        final ByteList separator = (separatorString == ChannelStream.PARAGRAPH_DELIMETER) ? ChannelStream.PARAGRAPH_SEPARATOR : separatorString;
        this.descriptor.checkOpen();
        if (this.feof()) {
            return null;
        }
        int c = this.read();
        if (c == -1) {
            return null;
        }
        this.buffer.position(this.buffer.position() - 1);
        final ByteList buf = new ByteList(40);
        final byte first = separator.getUnsafeBytes()[separator.getBegin()];
        int read;
    Label_0314:
        do {
            final byte[] bytes;
            Label_0099: {
                bytes = this.buffer.array();
            }
            final int offset = this.buffer.position();
            for (int max = this.buffer.limit(), i = offset; i < max; ++i) {
                c = bytes[i];
                if (c == first) {
                    buf.append(bytes, offset, i - offset);
                    if (i >= max) {
                        this.buffer.clear();
                    }
                    else {
                        this.buffer.position(i + 1);
                    }
                    for (int j = 0; j < separator.getRealSize(); ++j) {
                        if (c == -1) {
                            break;
                        }
                        if (c != separator.getUnsafeBytes()[separator.getBegin() + j]) {
                            buf.append(c);
                            continue Label_0099;
                        }
                        buf.append(c);
                        if (j < separator.getRealSize() - 1) {
                            c = this.read();
                        }
                    }
                    break Label_0314;
                }
            }
            buf.append(bytes, offset, this.buffer.remaining());
            read = this.refillBuffer();
        } while (read != -1);
        if (separatorString == ChannelStream.PARAGRAPH_DELIMETER) {
            while (c == separator.getUnsafeBytes()[separator.getBegin()]) {
                c = this.read();
            }
            this.ungetc(c);
        }
        return buf;
    }
    
    public synchronized int getline(final ByteList dst, final byte terminator) throws IOException, BadDescriptorException {
        this.checkReadable();
        this.ensureRead();
        this.descriptor.checkOpen();
        int totalRead = 0;
        boolean found = false;
        if (this.ungotc != -1) {
            dst.append((byte)this.ungotc);
            found = (this.ungotc == terminator);
            this.ungotc = -1;
            ++totalRead;
        }
        while (!found) {
            final byte[] bytes = this.buffer.array();
            final int begin = this.buffer.arrayOffset() + this.buffer.position();
            final int end = begin + this.buffer.remaining();
            int len = 0;
            for (int i = begin; i < end && !found; found = (bytes[i] == terminator), ++len, ++i) {}
            if (len > 0) {
                dst.append(this.buffer, len);
                totalRead += len;
            }
            if (!found) {
                final int n = this.refillBuffer();
                if (n > 0) {
                    continue;
                }
                if (n < 0 && totalRead < 1) {
                    return -1;
                }
                break;
            }
        }
        return totalRead;
    }
    
    public synchronized int getline(final ByteList dst, final byte terminator, long limit) throws IOException, BadDescriptorException {
        this.checkReadable();
        this.ensureRead();
        this.descriptor.checkOpen();
        int totalRead = 0;
        boolean found = false;
        if (this.ungotc != -1) {
            dst.append((byte)this.ungotc);
            found = (this.ungotc == terminator);
            this.ungotc = -1;
            --limit;
            ++totalRead;
        }
        while (!found) {
            final byte[] bytes = this.buffer.array();
            final int begin = this.buffer.arrayOffset() + this.buffer.position();
            final int end = begin + this.buffer.remaining();
            int len = 0;
            for (int i = begin; i < end && limit-- > 0L && !found; found = (bytes[i] == terminator), ++len, ++i) {}
            if (limit < 1L) {
                found = true;
            }
            if (len > 0) {
                dst.append(this.buffer, len);
                totalRead += len;
            }
            if (!found) {
                final int n = this.refillBuffer();
                if (n > 0) {
                    continue;
                }
                if (n < 0 && totalRead < 1) {
                    return -1;
                }
                break;
            }
        }
        return totalRead;
    }
    
    @Deprecated
    public synchronized ByteList readall() throws IOException, BadDescriptorException {
        final long fileSize = (this.descriptor.isSeekable() && this.descriptor.getChannel() instanceof FileChannel) ? ((FileChannel)this.descriptor.getChannel()).size() : 0L;
        if (fileSize > 0L) {
            this.ensureRead();
            final FileChannel channel = (FileChannel)this.descriptor.getChannel();
            final long left = fileSize - channel.position() + this.bufferedInputBytesRemaining();
            if (left <= 0L) {
                this.eof = true;
                return null;
            }
            if (left <= 2147483647L) {
                final ByteList result = new ByteList((int)left);
                final ByteBuffer buf = ByteBuffer.wrap(result.getUnsafeBytes(), result.begin(), (int)left);
                this.copyBufferedBytes(buf);
                while (buf.hasRemaining()) {
                    final int MAX_READ_CHUNK = 1048576;
                    final ByteBuffer tmp = buf.duplicate();
                    if (tmp.remaining() > 1048576) {
                        tmp.limit(tmp.position() + 1048576);
                    }
                    final int n = channel.read(tmp);
                    if (n <= 0) {
                        break;
                    }
                    buf.position(tmp.position());
                }
                this.eof = true;
                result.length(buf.position());
                return result;
            }
            if (this.getRuntime() != null) {
                throw this.getRuntime().newIOError("File too large");
            }
            throw new IOException("File too large");
        }
        else {
            if (this.descriptor.isNull()) {
                return new ByteList(0);
            }
            this.checkReadable();
            final ByteList byteList = new ByteList();
            ByteList read = this.fread(4096);
            if (read == null) {
                this.eof = true;
                return byteList;
            }
            while (read != null) {
                byteList.append(read);
                read = this.fread(4096);
            }
            return byteList;
        }
    }
    
    private final int copyBufferedBytes(final ByteBuffer dst) {
        final int bytesToCopy = dst.remaining();
        if (this.ungotc != -1 && dst.hasRemaining()) {
            dst.put((byte)this.ungotc);
            this.ungotc = -1;
        }
        if (this.buffer.hasRemaining() && dst.hasRemaining()) {
            if (dst.remaining() >= this.buffer.remaining()) {
                dst.put(this.buffer);
            }
            else {
                final ByteBuffer tmp = this.buffer.duplicate();
                tmp.limit(tmp.position() + dst.remaining());
                dst.put(tmp);
                this.buffer.position(tmp.position());
            }
        }
        return bytesToCopy - dst.remaining();
    }
    
    private final int copyBufferedBytes(final byte[] dst, int off, final int len) {
        int bytesCopied = 0;
        if (this.ungotc != -1 && len > 0) {
            dst[off++] = (byte)this.ungotc;
            this.ungotc = -1;
            ++bytesCopied;
        }
        final int n = Math.min(len - bytesCopied, this.buffer.remaining());
        this.buffer.get(dst, off, n);
        bytesCopied += n;
        return bytesCopied;
    }
    
    private final int copyBufferedBytes(final ByteList dst, final int len) {
        int bytesCopied = 0;
        dst.ensure(Math.min(len, this.bufferedInputBytesRemaining()));
        if (bytesCopied < len && this.ungotc != -1) {
            ++bytesCopied;
            dst.append((byte)this.ungotc);
            this.ungotc = -1;
        }
        if (bytesCopied < len && this.buffer.hasRemaining()) {
            final int n = Math.min(this.buffer.remaining(), len - bytesCopied);
            dst.append(this.buffer, n);
            bytesCopied += n;
        }
        return bytesCopied;
    }
    
    private final int bufferedInputBytesRemaining() {
        return this.reading ? (this.buffer.remaining() + ((this.ungotc != -1) ? 1 : 0)) : 0;
    }
    
    private final boolean hasBufferedInputBytes() {
        return this.reading && (this.buffer.hasRemaining() || this.ungotc != -1);
    }
    
    private final int bufferedOutputSpaceRemaining() {
        return this.reading ? 0 : this.buffer.remaining();
    }
    
    private final boolean hasBufferedOutputSpace() {
        return !this.reading && this.buffer.hasRemaining();
    }
    
    public void fclose() throws IOException, BadDescriptorException {
        try {
            synchronized (this) {
                this.closedExplicitly = true;
                this.close();
            }
        }
        finally {
            final Ruby localRuntime = this.getRuntime();
            if (localRuntime != null) {
                localRuntime.removeInternalFinalizer(this);
            }
            this.runtime = null;
        }
    }
    
    private void close() throws IOException, BadDescriptorException {
        this.finish(true);
    }
    
    private void finish(final boolean close) throws BadDescriptorException, IOException {
        try {
            this.flushWrite();
        }
        finally {
            this.buffer = ChannelStream.EMPTY_BUFFER;
            this.runtime = null;
            this.descriptor.finish(close);
        }
    }
    
    public synchronized int fflush() throws IOException, BadDescriptorException {
        this.checkWritable();
        try {
            this.flushWrite();
        }
        catch (EOFException eofe) {
            return -1;
        }
        return 0;
    }
    
    private void flushWrite() throws IOException, BadDescriptorException {
        if (this.reading || !this.modes.isWritable() || this.buffer.position() == 0) {
            return;
        }
        final int len = this.buffer.position();
        this.buffer.flip();
        final int n = this.descriptor.write(this.buffer);
        if (n != len) {}
        this.buffer.clear();
    }
    
    private boolean flushWrite(final boolean block) throws IOException, BadDescriptorException {
        if (this.reading || !this.modes.isWritable() || this.buffer.position() == 0) {
            return false;
        }
        final int len = this.buffer.position();
        int nWritten = 0;
        this.buffer.flip();
        if (this.descriptor.getChannel() instanceof SelectableChannel) {
            final SelectableChannel selectableChannel = (SelectableChannel)this.descriptor.getChannel();
            synchronized (selectableChannel.blockingLock()) {
                final boolean oldBlocking = selectableChannel.isBlocking();
                try {
                    if (oldBlocking != block) {
                        selectableChannel.configureBlocking(block);
                    }
                    nWritten = this.descriptor.write(this.buffer);
                }
                finally {
                    if (oldBlocking != block) {
                        selectableChannel.configureBlocking(oldBlocking);
                    }
                }
            }
        }
        else {
            nWritten = this.descriptor.write(this.buffer);
        }
        if (nWritten != len) {
            this.buffer.compact();
            return false;
        }
        this.buffer.clear();
        return true;
    }
    
    public InputStream newInputStream() {
        final InputStream in = this.descriptor.getBaseInputStream();
        return (in == null) ? new InputStreamAdapter(this) : in;
    }
    
    public OutputStream newOutputStream() {
        return new OutputStreamAdapter(this);
    }
    
    public void clearerr() {
        this.eof = false;
    }
    
    public boolean feof() throws IOException, BadDescriptorException {
        this.checkReadable();
        return this.eof;
    }
    
    public synchronized long fgetpos() throws IOException, PipeException, InvalidValueException, BadDescriptorException {
        if (this.descriptor.isSeekable()) {
            final FileChannel fileChannel = (FileChannel)this.descriptor.getChannel();
            long pos = fileChannel.position();
            if (this.reading) {
                pos -= this.buffer.remaining();
                return pos - ((pos > 0L && this.ungotc != -1) ? 1 : 0);
            }
            return pos + this.buffer.position();
        }
        else {
            if (this.descriptor.isNull()) {
                return 0L;
            }
            throw new PipeException();
        }
    }
    
    public synchronized void lseek(final long offset, final int type) throws IOException, InvalidValueException, PipeException, BadDescriptorException {
        if (this.descriptor.isSeekable()) {
            final FileChannel fileChannel = (FileChannel)this.descriptor.getChannel();
            this.ungotc = -1;
            int adj = 0;
            if (this.reading) {
                adj = this.buffer.remaining();
                this.buffer.clear();
                this.buffer.flip();
            }
            else {
                this.flushWrite();
            }
            try {
                switch (type) {
                    case 0: {
                        fileChannel.position(offset);
                        break;
                    }
                    case 1: {
                        fileChannel.position(fileChannel.position() - adj + offset);
                        break;
                    }
                    case 2: {
                        fileChannel.position(fileChannel.size() + offset);
                        break;
                    }
                }
            }
            catch (IllegalArgumentException e) {
                throw new InvalidValueException();
            }
            catch (IOException ioe) {
                throw ioe;
            }
        }
        else if (this.descriptor.getChannel() instanceof SelectableChannel) {
            throw new PipeException();
        }
    }
    
    public synchronized void sync() throws IOException, BadDescriptorException {
        this.flushWrite();
    }
    
    private void ensureRead() throws IOException, BadDescriptorException {
        if (this.reading) {
            return;
        }
        this.flushWrite();
        this.buffer.clear();
        this.buffer.flip();
        this.reading = true;
    }
    
    private void ensureReadNonBuffered() throws IOException, BadDescriptorException {
        if (this.reading) {
            if (this.buffer.hasRemaining()) {
                final Ruby localRuntime = this.getRuntime();
                if (localRuntime != null) {
                    throw localRuntime.newIOError("sysread for buffered IO");
                }
                throw new IOException("sysread for buffered IO");
            }
        }
        else {
            this.flushWrite();
            this.buffer.clear();
            this.buffer.flip();
            this.reading = true;
        }
    }
    
    private void resetForWrite() throws IOException {
        if (this.descriptor.isSeekable()) {
            final FileChannel fileChannel = (FileChannel)this.descriptor.getChannel();
            if (this.buffer.hasRemaining()) {
                fileChannel.position(fileChannel.position() - this.buffer.remaining());
            }
        }
        this.buffer.clear();
        this.reading = false;
    }
    
    private void ensureWrite() throws IOException {
        if (!this.reading) {
            return;
        }
        this.resetForWrite();
    }
    
    public synchronized ByteList read(final int number) throws IOException, BadDescriptorException {
        this.checkReadable();
        this.ensureReadNonBuffered();
        final ByteList byteList = new ByteList(number);
        final int bytesRead = this.descriptor.read(number, byteList);
        if (bytesRead == -1) {
            this.eof = true;
        }
        return byteList;
    }
    
    private ByteList bufferedRead(final int number) throws IOException, BadDescriptorException {
        this.checkReadable();
        this.ensureRead();
        int resultSize = 0;
        final int BULK_THRESHOLD = 131072;
        if (number >= 131072 && this.descriptor.isSeekable() && this.descriptor.getChannel() instanceof FileChannel) {
            final FileChannel fileChannel = (FileChannel)this.descriptor.getChannel();
            resultSize = (int)Math.min(fileChannel.size() - fileChannel.position() + this.bufferedInputBytesRemaining(), number);
        }
        else {
            resultSize = Math.min(this.bufferedInputBytesRemaining(), number);
        }
        final ByteList result = new ByteList(resultSize);
        this.bufferedRead(result, number);
        return result;
    }
    
    private int bufferedRead(final ByteList dst, final int number) throws IOException, BadDescriptorException {
        int bytesRead = 0;
        bytesRead += this.copyBufferedBytes(dst, number);
        boolean done = false;
        while (number - bytesRead >= 4096) {
            final int bytesToRead = Math.min(16384, number - bytesRead);
            final int n = this.descriptor.read(bytesToRead, dst);
            if (n == -1) {
                this.eof = true;
                done = true;
                break;
            }
            if (n == 0) {
                done = true;
                break;
            }
            bytesRead += n;
        }
        while (!done && bytesRead < number) {
            final int read = this.refillBuffer();
            if (read == -1) {
                this.eof = true;
                break;
            }
            if (read == 0) {
                break;
            }
            final int len = Math.min(this.buffer.remaining(), number - bytesRead);
            dst.append(this.buffer, len);
            bytesRead += len;
        }
        if (bytesRead == 0 && number != 0 && this.eof) {
            throw new EOFException();
        }
        return bytesRead;
    }
    
    private int bufferedRead(final ByteBuffer dst, final boolean partial) throws IOException, BadDescriptorException {
        this.checkReadable();
        this.ensureRead();
        boolean done = false;
        int bytesRead;
        ByteBuffer tmpDst;
        int bytesToRead;
        int n;
        for (bytesRead = 0, bytesRead += this.copyBufferedBytes(dst); (bytesRead < 1 || !partial) && (dst.remaining() >= 4096 || dst.isDirect()); bytesRead += n) {
            tmpDst = dst;
            if (!dst.isDirect()) {
                bytesToRead = Math.min(16384, dst.remaining());
                if (bytesToRead < dst.remaining()) {
                    tmpDst = dst.duplicate();
                    tmpDst.limit(tmpDst.position() + bytesToRead);
                }
            }
            n = this.descriptor.read(tmpDst);
            if (n == -1) {
                this.eof = true;
                done = true;
                break;
            }
            if (n == 0) {
                done = true;
                break;
            }
        }
        while (!done && dst.hasRemaining() && (bytesRead < 1 || !partial)) {
            final int read = this.refillBuffer();
            if (read == -1) {
                this.eof = true;
                done = true;
                break;
            }
            if (read == 0) {
                done = true;
                break;
            }
            bytesRead += this.copyBufferedBytes(dst);
        }
        if (this.eof && bytesRead == 0 && dst.remaining() != 0) {
            throw new EOFException();
        }
        return bytesRead;
    }
    
    private int bufferedRead() throws IOException, BadDescriptorException {
        this.ensureRead();
        if (!this.buffer.hasRemaining()) {
            final int len = this.refillBuffer();
            if (len == -1) {
                this.eof = true;
                return -1;
            }
            if (len == 0) {
                return -1;
            }
        }
        return this.buffer.get() & 0xFF;
    }
    
    private int bufferedWrite(final ByteList buf) throws IOException, BadDescriptorException {
        this.checkWritable();
        this.ensureWrite();
        if (buf == null || buf.length() == 0) {
            return 0;
        }
        if (buf.length() > this.buffer.capacity()) {
            this.flushWrite();
            final int n = this.descriptor.write(ByteBuffer.wrap(buf.getUnsafeBytes(), buf.begin(), buf.length()));
            if (n != buf.length()) {}
        }
        else {
            if (buf.length() > this.buffer.remaining()) {
                this.flushWrite();
            }
            this.buffer.put(buf.getUnsafeBytes(), buf.begin(), buf.length());
        }
        if (this.isSync()) {
            this.flushWrite();
        }
        return buf.getRealSize();
    }
    
    private int bufferedWrite(final ByteBuffer buf) throws IOException, BadDescriptorException {
        this.checkWritable();
        this.ensureWrite();
        if (buf == null || !buf.hasRemaining()) {
            return 0;
        }
        final int nbytes = buf.remaining();
        if (nbytes >= this.buffer.capacity()) {
            this.flushWrite();
            this.descriptor.write(buf);
        }
        else {
            if (nbytes > this.buffer.remaining()) {
                this.flushWrite();
            }
            this.buffer.put(buf);
        }
        if (this.isSync()) {
            this.flushWrite();
        }
        return nbytes - buf.remaining();
    }
    
    private int bufferedWrite(final int c) throws IOException, BadDescriptorException {
        this.checkWritable();
        this.ensureWrite();
        if (!this.buffer.hasRemaining()) {
            this.flushWrite();
        }
        this.buffer.put((byte)c);
        if (this.isSync()) {
            this.flushWrite();
        }
        return 1;
    }
    
    public synchronized void ftruncate(final long newLength) throws IOException, BadDescriptorException, InvalidValueException {
        final Channel ch = this.descriptor.getChannel();
        if (!(ch instanceof FileChannel)) {
            throw new InvalidValueException();
        }
        this.invalidateBuffer();
        final FileChannel fileChannel = (FileChannel)ch;
        if (newLength > fileChannel.size()) {
            final long position = fileChannel.position();
            final int difference = (int)(newLength - fileChannel.size());
            fileChannel.position(fileChannel.size());
            fileChannel.write(ByteBuffer.allocate(difference));
            fileChannel.position(position);
        }
        else {
            fileChannel.truncate(newLength);
        }
    }
    
    private void invalidateBuffer() throws IOException, BadDescriptorException {
        if (!this.reading) {
            this.flushWrite();
        }
        final int posOverrun = this.buffer.remaining();
        this.buffer.clear();
        if (this.reading) {
            this.buffer.flip();
            final FileChannel fileChannel = (FileChannel)this.descriptor.getChannel();
            if (posOverrun != 0) {
                fileChannel.position(fileChannel.position() - posOverrun);
            }
        }
    }
    
    public void finalize() throws Throwable {
        super.finalize();
        if (this.closedExplicitly) {
            return;
        }
        if (this.descriptor != null && this.descriptor.isOpen()) {
            this.finish(this.autoclose);
        }
    }
    
    public int ready() throws IOException {
        if (this.descriptor.getChannel() instanceof SelectableChannel) {
            int ready_stat = 0;
            final Selector sel = SelectorFactory.openWithRetryFrom(null, SelectorProvider.provider());
            final SelectableChannel selchan = (SelectableChannel)this.descriptor.getChannel();
            synchronized (selchan.blockingLock()) {
                final boolean is_block = selchan.isBlocking();
                try {
                    selchan.configureBlocking(false);
                    selchan.register(sel, 1);
                    ready_stat = sel.selectNow();
                    sel.close();
                }
                catch (Throwable ex) {}
                finally {
                    if (sel != null) {
                        try {
                            sel.close();
                        }
                        catch (Exception ex2) {}
                    }
                    selchan.configureBlocking(is_block);
                }
            }
            return ready_stat;
        }
        return this.newInputStream().available();
    }
    
    public synchronized void fputc(final int c) throws IOException, BadDescriptorException {
        this.bufferedWrite(c);
    }
    
    public int ungetc(final int c) {
        if (c == -1) {
            return -1;
        }
        this.eof = false;
        return this.ungotc = c;
    }
    
    public synchronized int fgetc() throws IOException, BadDescriptorException {
        if (this.eof) {
            return -1;
        }
        this.checkReadable();
        final int c = this.read();
        if (c == -1) {
            this.eof = true;
            return c;
        }
        return c & 0xFF;
    }
    
    public synchronized int fwrite(final ByteList string) throws IOException, BadDescriptorException {
        return this.bufferedWrite(string);
    }
    
    public synchronized int write(final ByteBuffer buf) throws IOException, BadDescriptorException {
        return this.bufferedWrite(buf);
    }
    
    public synchronized int writenonblock(final ByteList buf) throws IOException, BadDescriptorException {
        this.checkWritable();
        this.ensureWrite();
        if (buf == null || buf.length() == 0) {
            return 0;
        }
        if (this.buffer.position() != 0 && !this.flushWrite(false)) {
            return 0;
        }
        if (this.descriptor.getChannel() instanceof SelectableChannel) {
            final SelectableChannel selectableChannel = (SelectableChannel)this.descriptor.getChannel();
            synchronized (selectableChannel.blockingLock()) {
                final boolean oldBlocking = selectableChannel.isBlocking();
                try {
                    if (oldBlocking) {
                        selectableChannel.configureBlocking(false);
                    }
                    return this.descriptor.write(ByteBuffer.wrap(buf.getUnsafeBytes(), buf.begin(), buf.length()));
                }
                finally {
                    if (oldBlocking) {
                        selectableChannel.configureBlocking(oldBlocking);
                    }
                }
            }
        }
        return this.descriptor.write(ByteBuffer.wrap(buf.getUnsafeBytes(), buf.begin(), buf.length()));
    }
    
    public synchronized ByteList fread(final int number) throws IOException, BadDescriptorException {
        try {
            if (number != 0) {
                return this.bufferedRead(number);
            }
            if (this.eof) {
                return null;
            }
            return new ByteList(0);
        }
        catch (EOFException e) {
            this.eof = true;
            return null;
        }
    }
    
    public synchronized ByteList readnonblock(final int number) throws IOException, BadDescriptorException, EOFException {
        assert number >= 0;
        if (number == 0) {
            return null;
        }
        if (this.descriptor.getChannel() instanceof SelectableChannel) {
            final SelectableChannel selectableChannel = (SelectableChannel)this.descriptor.getChannel();
            synchronized (selectableChannel.blockingLock()) {
                final boolean oldBlocking = selectableChannel.isBlocking();
                try {
                    selectableChannel.configureBlocking(false);
                    return this.readpartial(number);
                }
                finally {
                    selectableChannel.configureBlocking(oldBlocking);
                }
            }
        }
        if (this.descriptor.getChannel() instanceof FileChannel) {
            return this.fread(number);
        }
        return null;
    }
    
    public synchronized ByteList readpartial(final int number) throws IOException, BadDescriptorException, EOFException {
        assert number >= 0;
        if (number == 0) {
            return null;
        }
        if (this.descriptor.getChannel() instanceof FileChannel) {
            return this.fread(number);
        }
        if (this.hasBufferedInputBytes()) {
            return this.bufferedRead(Math.min(this.bufferedInputBytesRemaining(), number));
        }
        return this.read(number);
    }
    
    public synchronized int read(final ByteBuffer dst) throws IOException, BadDescriptorException, EOFException {
        return this.read(dst, !(this.descriptor.getChannel() instanceof FileChannel));
    }
    
    public synchronized int read(final ByteBuffer dst, final boolean partial) throws IOException, BadDescriptorException, EOFException {
        assert dst.hasRemaining();
        return this.bufferedRead(dst, partial);
    }
    
    public synchronized int read() throws IOException, BadDescriptorException {
        try {
            this.descriptor.checkOpen();
            if (this.ungotc >= 0) {
                final int c = this.ungotc;
                this.ungotc = -1;
                return c;
            }
            return this.bufferedRead();
        }
        catch (EOFException e) {
            this.eof = true;
            return -1;
        }
    }
    
    public ChannelDescriptor getDescriptor() {
        return this.descriptor;
    }
    
    public void setBlocking(final boolean block) throws IOException {
        if (!(this.descriptor.getChannel() instanceof SelectableChannel)) {
            return;
        }
        synchronized (((SelectableChannel)this.descriptor.getChannel()).blockingLock()) {
            this.blocking = block;
            try {
                ((SelectableChannel)this.descriptor.getChannel()).configureBlocking(block);
            }
            catch (IllegalBlockingModeException ex) {}
        }
    }
    
    public boolean isBlocking() {
        return this.blocking;
    }
    
    public synchronized void freopen(final Ruby runtime, final String path, final ModeFlags modes) throws DirectoryAsFileException, IOException, InvalidValueException, PipeException, BadDescriptorException {
        this.flushWrite();
        this.buffer.clear();
        if (this.reading) {
            this.buffer.flip();
        }
        this.modes = modes;
        if (this.descriptor.isOpen()) {
            this.descriptor.close();
        }
        if (path.equals("/dev/null") || path.equalsIgnoreCase("nul:") || path.equalsIgnoreCase("nul")) {
            this.descriptor = this.descriptor.reopen(new NullChannel(), modes);
        }
        else {
            final String cwd = runtime.getCurrentDirectory();
            final JRubyFile theFile = JRubyFile.create(cwd, path);
            if (theFile.isDirectory() && modes.isWritable()) {
                throw new DirectoryAsFileException();
            }
            if (modes.isCreate()) {
                if (theFile.exists() && modes.isExclusive()) {
                    throw runtime.newErrnoEEXISTError("File exists - " + path);
                }
                theFile.createNewFile();
            }
            else if (!theFile.exists()) {
                throw runtime.newErrnoENOENTError("file not found - " + path);
            }
            final RandomAccessFile file = new RandomAccessFile(theFile, modes.toJavaModeString());
            if (modes.isTruncate()) {
                file.setLength(0L);
            }
            this.descriptor = this.descriptor.reopen(file, modes);
            if (modes.isAppendable()) {
                this.lseek(0L, 2);
            }
        }
    }
    
    public static Stream open(final Ruby runtime, final ChannelDescriptor descriptor) {
        return maybeWrapWithLineEndingWrapper(new ChannelStream(runtime, descriptor, true), descriptor.getOriginalModes());
    }
    
    public static Stream fdopen(final Ruby runtime, final ChannelDescriptor descriptor, final ModeFlags modes) throws InvalidValueException {
        descriptor.checkNewModes(modes);
        return maybeWrapWithLineEndingWrapper(new ChannelStream(runtime, descriptor, modes, true), modes);
    }
    
    public static Stream open(final Ruby runtime, final ChannelDescriptor descriptor, final boolean autoclose) {
        return maybeWrapWithLineEndingWrapper(new ChannelStream(runtime, descriptor, autoclose), descriptor.getOriginalModes());
    }
    
    public static Stream fdopen(final Ruby runtime, final ChannelDescriptor descriptor, final ModeFlags modes, final boolean autoclose) throws InvalidValueException {
        descriptor.checkNewModes(modes);
        return maybeWrapWithLineEndingWrapper(new ChannelStream(runtime, descriptor, modes, autoclose), modes);
    }
    
    private static Stream maybeWrapWithLineEndingWrapper(final Stream stream, final ModeFlags modes) {
        if (Platform.IS_WINDOWS && stream.getDescriptor().getChannel() instanceof FileChannel && !modes.isBinary()) {
            return new CRLFStreamWrapper(stream);
        }
        return stream;
    }
    
    public static Stream fopen(final Ruby runtime, final String path, final ModeFlags modes) throws FileNotFoundException, DirectoryAsFileException, FileExistsException, IOException, InvalidValueException, PipeException, BadDescriptorException {
        final ChannelDescriptor descriptor = ChannelDescriptor.open(runtime.getCurrentDirectory(), path, modes, Ruby.getClassLoader());
        final Stream stream = fdopen(runtime, descriptor, modes);
        if (modes.isAppendable()) {
            stream.lseek(0L, 2);
        }
        return stream;
    }
    
    public Channel getChannel() {
        return this.getDescriptor().getChannel();
    }
    
    static {
        EMPTY_BUFFER = ByteBuffer.allocate(0);
    }
    
    private static final class InputStreamAdapter extends InputStream
    {
        private final ChannelStream stream;
        
        public InputStreamAdapter(final ChannelStream stream) {
            this.stream = stream;
        }
        
        public int read() throws IOException {
            synchronized (this.stream) {
                if (this.stream.hasBufferedInputBytes()) {
                    try {
                        return this.stream.read();
                    }
                    catch (BadDescriptorException ex) {
                        throw new IOException(ex.getMessage());
                    }
                }
            }
            final byte[] b = { 0 };
            return (this.read(b, 0, 1) == 1) ? (b[0] & 0xFF) : -1;
        }
        
        public int read(final byte[] bytes, final int off, final int len) throws IOException {
            if (bytes == null) {
                throw new NullPointerException("null destination buffer");
            }
            if ((len | off | off + len | bytes.length - (off + len)) < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (len == 0) {
                return 0;
            }
            try {
                synchronized (this.stream) {
                    final int available = this.stream.bufferedInputBytesRemaining();
                    if (available >= len) {
                        return this.stream.copyBufferedBytes(bytes, off, len);
                    }
                    if (this.stream.getDescriptor().getChannel() instanceof SelectableChannel) {
                        final SelectableChannel ch = (SelectableChannel)this.stream.getDescriptor().getChannel();
                        synchronized (ch.blockingLock()) {
                            final boolean oldBlocking = ch.isBlocking();
                            try {
                                if (!oldBlocking) {
                                    ch.configureBlocking(true);
                                }
                                return this.stream.bufferedRead(ByteBuffer.wrap(bytes, off, len), true);
                            }
                            finally {
                                if (!oldBlocking) {
                                    ch.configureBlocking(oldBlocking);
                                }
                            }
                        }
                    }
                    return this.stream.bufferedRead(ByteBuffer.wrap(bytes, off, len), true);
                }
            }
            catch (BadDescriptorException ex) {
                throw new IOException(ex.getMessage());
            }
            catch (EOFException ex2) {
                return -1;
            }
        }
        
        public int available() throws IOException {
            synchronized (this.stream) {
                return this.stream.eof ? 0 : this.stream.bufferedInputBytesRemaining();
            }
        }
        
        public void close() throws IOException {
            try {
                synchronized (this.stream) {
                    this.stream.fclose();
                }
            }
            catch (BadDescriptorException ex) {
                throw new IOException(ex.getMessage());
            }
        }
    }
    
    private static final class OutputStreamAdapter extends OutputStream
    {
        private final ChannelStream stream;
        
        public OutputStreamAdapter(final ChannelStream stream) {
            this.stream = stream;
        }
        
        public void write(final int i) throws IOException {
            synchronized (this.stream) {
                if (!this.stream.isSync() && this.stream.hasBufferedOutputSpace()) {
                    this.stream.buffer.put((byte)i);
                    return;
                }
            }
            final byte[] b = { (byte)i };
            this.write(b, 0, 1);
        }
        
        public void write(final byte[] bytes, final int off, final int len) throws IOException {
            if (bytes == null) {
                throw new NullPointerException("null source buffer");
            }
            if ((len | off | off + len | bytes.length - (off + len)) < 0) {
                throw new IndexOutOfBoundsException();
            }
            try {
                synchronized (this.stream) {
                    if (!this.stream.isSync() && this.stream.bufferedOutputSpaceRemaining() >= len) {
                        this.stream.buffer.put(bytes, off, len);
                    }
                    else if (this.stream.getDescriptor().getChannel() instanceof SelectableChannel) {
                        final SelectableChannel ch = (SelectableChannel)this.stream.getDescriptor().getChannel();
                        synchronized (ch.blockingLock()) {
                            final boolean oldBlocking = ch.isBlocking();
                            try {
                                if (!oldBlocking) {
                                    ch.configureBlocking(true);
                                }
                                this.stream.bufferedWrite(ByteBuffer.wrap(bytes, off, len));
                            }
                            finally {
                                if (!oldBlocking) {
                                    ch.configureBlocking(oldBlocking);
                                }
                            }
                        }
                    }
                    else {
                        this.stream.bufferedWrite(ByteBuffer.wrap(bytes, off, len));
                    }
                }
            }
            catch (BadDescriptorException ex) {
                throw new IOException(ex.getMessage());
            }
        }
        
        public void close() throws IOException {
            try {
                synchronized (this.stream) {
                    this.stream.fclose();
                }
            }
            catch (BadDescriptorException ex) {
                throw new IOException(ex.getMessage());
            }
        }
        
        public void flush() throws IOException {
            try {
                synchronized (this.stream) {
                    this.stream.flushWrite(true);
                }
            }
            catch (BadDescriptorException ex) {
                throw new IOException(ex.getMessage());
            }
        }
    }
}
