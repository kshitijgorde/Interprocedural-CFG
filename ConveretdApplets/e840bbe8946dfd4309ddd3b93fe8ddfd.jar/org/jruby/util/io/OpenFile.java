// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.io;

import org.jruby.util.ShellLauncher;
import java.io.IOException;
import org.jruby.Ruby;

public class OpenFile
{
    public static final int READABLE = 1;
    public static final int WRITABLE = 2;
    public static final int READWRITE = 3;
    public static final int APPEND = 64;
    public static final int CREATE = 128;
    public static final int BINMODE = 4;
    public static final int SYNC = 8;
    public static final int WBUF = 16;
    public static final int RBUF = 32;
    public static final int WSPLIT = 512;
    public static final int WSPLIT_INITIALIZED = 1024;
    public static final int SYNCWRITE = 10;
    private Stream mainStream;
    private Stream pipeStream;
    private int mode;
    private Process process;
    private int lineNumber;
    private String path;
    private Finalizer finalizer;
    
    public OpenFile() {
        this.lineNumber = 0;
    }
    
    public Stream getMainStream() {
        return this.mainStream;
    }
    
    public Stream getMainStreamSafe() throws BadDescriptorException {
        final Stream stream = this.mainStream;
        if (stream == null) {
            throw new BadDescriptorException();
        }
        return stream;
    }
    
    public void setMainStream(final Stream mainStream) {
        this.mainStream = mainStream;
    }
    
    public Stream getPipeStream() {
        return this.pipeStream;
    }
    
    public Stream getPipeStreamSafe() throws BadDescriptorException {
        final Stream stream = this.pipeStream;
        if (stream == null) {
            throw new BadDescriptorException();
        }
        return stream;
    }
    
    public void setPipeStream(final Stream pipeStream) {
        this.pipeStream = pipeStream;
    }
    
    public Stream getWriteStream() {
        return (this.pipeStream == null) ? this.mainStream : this.pipeStream;
    }
    
    public Stream getWriteStreamSafe() throws BadDescriptorException {
        final Stream stream = (this.pipeStream == null) ? this.mainStream : this.pipeStream;
        if (stream == null) {
            throw new BadDescriptorException();
        }
        return stream;
    }
    
    public int getMode() {
        return this.mode;
    }
    
    public String getModeAsString(final Ruby runtime) {
        final String modeString = getStringFromMode(this.mode);
        if (modeString == null) {
            throw runtime.newArgumentError("Illegal access modenum " + Integer.toOctalString(this.mode));
        }
        return modeString;
    }
    
    public static String getStringFromMode(final int mode) {
        if ((mode & 0x40) != 0x0) {
            if ((mode & 0x3) != 0x0) {
                return "ab+";
            }
            return "ab";
        }
        else {
            switch (mode & 0x3) {
                case 1: {
                    return "rb";
                }
                case 2: {
                    return "wb";
                }
                case 3: {
                    if ((mode & 0x80) != 0x0) {
                        return "wb+";
                    }
                    return "rb+";
                }
                default: {
                    return null;
                }
            }
        }
    }
    
    public void checkReadable(final Ruby runtime) throws IOException, BadDescriptorException, InvalidValueException {
        this.checkClosed(runtime);
        if ((this.mode & 0x1) == 0x0) {
            throw runtime.newIOError("not opened for reading");
        }
        if (((this.mode & 0x10) != 0x0 || (this.mode & 0x2A) == 0xA) && !this.mainStream.feof() && this.pipeStream == null) {
            try {
                this.seek(0L, 1);
            }
            catch (PipeException p) {}
            catch (IOException ex) {}
        }
    }
    
    public void seek(final long offset, final int whence) throws IOException, InvalidValueException, PipeException, BadDescriptorException {
        final Stream stream = this.getWriteStreamSafe();
        this.seekInternal(stream, offset, whence);
    }
    
    private void seekInternal(final Stream stream, final long offset, final int whence) throws IOException, InvalidValueException, PipeException, BadDescriptorException {
        this.flushBeforeSeek(stream);
        stream.lseek(offset, whence);
    }
    
    private void flushBeforeSeek(final Stream stream) throws BadDescriptorException, IOException {
        if ((this.mode & 0x10) != 0x0) {
            this.fflush(stream);
        }
    }
    
    public void fflush(final Stream stream) throws IOException, BadDescriptorException {
        int n;
        do {
            n = stream.fflush();
        } while (n == -1);
        this.mode &= 0xFFFFFFEF;
    }
    
    public void checkWritable(final Ruby runtime) throws IOException, BadDescriptorException, InvalidValueException {
        this.checkClosed(runtime);
        if ((this.mode & 0x2) == 0x0) {
            throw runtime.newIOError("not opened for writing");
        }
        if ((this.mode & 0x20) != 0x0 && !this.mainStream.feof() && this.pipeStream == null) {
            try {
                this.seek(0L, 1);
            }
            catch (PipeException p) {}
            catch (IOException ex) {}
        }
        if (this.pipeStream == null) {
            this.mode &= 0xFFFFFFDF;
        }
    }
    
    public void checkClosed(final Ruby runtime) {
        if (this.mainStream == null && this.pipeStream == null) {
            throw runtime.newIOError("closed stream");
        }
    }
    
    public boolean isBinmode() {
        return (this.mode & 0x4) != 0x0;
    }
    
    public void setBinmode() {
        this.mode |= 0x4;
        if (this.mainStream != null) {
            this.mainStream.setBinmode();
        }
    }
    
    public boolean isOpen() {
        return this.mainStream != null || this.pipeStream != null;
    }
    
    public boolean isReadable() {
        return (this.mode & 0x1) != 0x0;
    }
    
    public boolean isWritable() {
        return (this.mode & 0x2) != 0x0;
    }
    
    public boolean isReadBuffered() {
        return (this.mode & 0x20) != 0x0;
    }
    
    public void setReadBuffered() {
        this.mode |= 0x20;
    }
    
    public boolean isWriteBuffered() {
        return (this.mode & 0x10) != 0x0;
    }
    
    public void setWriteBuffered() {
        this.mode |= 0x10;
    }
    
    public void setSync(final boolean sync) {
        if (sync) {
            this.mode |= 0x8;
        }
        else {
            this.mode &= 0xFFFFFFF7;
        }
    }
    
    public boolean isSync() {
        return (this.mode & 0x8) != 0x0;
    }
    
    public boolean areBothEOF() throws IOException, BadDescriptorException {
        return this.mainStream.feof() && (this.pipeStream == null || this.pipeStream.feof());
    }
    
    public void setMode(final int modes) {
        this.mode = modes;
    }
    
    public Process getProcess() {
        return this.process;
    }
    
    public void setProcess(final Process process) {
        this.process = process;
    }
    
    public long getPid() {
        return ShellLauncher.getPidFromProcess(this.process);
    }
    
    public int getLineNumber() {
        return this.lineNumber;
    }
    
    public void setLineNumber(final int lineNumber) {
        this.lineNumber = lineNumber;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public void setPath(final String path) {
        this.path = path;
    }
    
    public boolean isAutoclose() {
        boolean autoclose = true;
        final Stream myMain;
        if ((myMain = this.mainStream) != null) {
            autoclose &= myMain.isAutoclose();
        }
        final Stream myPipe;
        if ((myPipe = this.pipeStream) != null) {
            autoclose &= myPipe.isAutoclose();
        }
        return autoclose;
    }
    
    public void setAutoclose(final boolean autoclose) {
        final Stream myMain;
        if ((myMain = this.mainStream) != null) {
            myMain.setAutoclose(autoclose);
        }
        final Stream myPipe;
        if ((myPipe = this.pipeStream) != null) {
            myPipe.setAutoclose(autoclose);
        }
    }
    
    public Finalizer getFinalizer() {
        return this.finalizer;
    }
    
    public void setFinalizer(final Finalizer finalizer) {
        this.finalizer = finalizer;
    }
    
    public void cleanup(final Ruby runtime, final boolean raise) {
        if (this.finalizer != null) {
            this.finalizer.finalize(runtime, raise);
        }
        else {
            this.finalize(runtime, raise);
        }
    }
    
    public void finalize(final Ruby runtime, final boolean raise) {
        try {
            ChannelDescriptor main = null;
            ChannelDescriptor pipe = null;
            synchronized (this) {
                final Stream ps = this.pipeStream;
                if (ps != null) {
                    pipe = ps.getDescriptor();
                    try {
                        ps.fflush();
                        ps.fclose();
                    }
                    finally {
                        this.pipeStream = null;
                    }
                }
                final Stream ms = this.mainStream;
                if (ms != null) {
                    main = ms.getDescriptor();
                    try {
                        if (pipe == null && this.isWriteBuffered()) {
                            ms.fflush();
                        }
                        ms.fclose();
                    }
                    catch (BadDescriptorException bde) {
                        if (main != pipe) {
                            throw bde;
                        }
                    }
                    finally {
                        this.mainStream = null;
                    }
                }
            }
        }
        catch (IOException ex) {
            if (raise) {
                throw runtime.newIOErrorFromException(ex);
            }
        }
        catch (BadDescriptorException ex2) {
            if (raise) {
                throw runtime.newErrnoEBADFError();
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public interface Finalizer
    {
        void finalize(final Ruby p0, final boolean p1);
    }
}
