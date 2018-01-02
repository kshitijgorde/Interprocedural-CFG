// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.jruby.Ruby;
import org.jruby.ext.posix.LibC;
import java.nio.channels.ByteChannel;

public class FileDescriptorByteChannel implements ByteChannel
{
    private static LibC shared_libc;
    private LibC libc;
    private final int fd;
    private volatile boolean isOpen;
    
    private static synchronized LibC libc(final Ruby runtime) {
        if (FileDescriptorByteChannel.shared_libc != null) {
            return FileDescriptorByteChannel.shared_libc;
        }
        FileDescriptorByteChannel.shared_libc = runtime.getPosix().libc();
        if (FileDescriptorByteChannel.shared_libc == null) {
            throw new IllegalStateException("native not enabled");
        }
        return FileDescriptorByteChannel.shared_libc;
    }
    
    public FileDescriptorByteChannel(final Ruby runtime, final int fd) {
        this.isOpen = true;
        this.fd = fd;
        this.libc = libc(runtime);
    }
    
    public int read(final ByteBuffer dst) throws IOException {
        if (!this.isOpen) {
            throw new IOException("Not open");
        }
        final int n = this.libc.read(this.fd, dst, dst.remaining());
        if (n > 0) {
            dst.position(dst.position() + n);
        }
        else if (n == 0) {
            return -1;
        }
        return n;
    }
    
    public int write(final ByteBuffer src) throws IOException {
        if (!this.isOpen) {
            throw new IOException("Not open");
        }
        final int n = this.libc.write(this.fd, src, src.remaining());
        if (n > 0) {
            src.position(src.position() + n);
        }
        return n;
    }
    
    public boolean isOpen() {
        return this.isOpen;
    }
    
    public void close() throws IOException {
        if (!this.isOpen) {
            throw new IllegalStateException("file already closed");
        }
        this.isOpen = false;
        this.libc.close(this.fd);
    }
}
