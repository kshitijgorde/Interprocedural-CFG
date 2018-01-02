// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net.stream;

import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.net.InetSocketAddress;
import org.xmodel.log.Log;
import org.xmodel.net.ILink;

final class A implements ILink
{
    private static final Log C;
    private TcpBase F;
    private IListener G;
    private InetSocketAddress B;
    private SocketChannel E;
    private ByteBuffer A;
    private Semaphore D;
    
    static {
        C = Log.getLog("org.xmodel.net.stream");
    }
    
    A(final TcpBase f, final SocketChannel e, final IListener g) {
        this.F = f;
        this.E = e;
        this.G = g;
        this.D = new Semaphore(0);
        (this.A = ByteBuffer.allocateDirect(4096)).flip();
    }
    
    public String C() {
        return (this.B != null) ? this.B.getAddress().getHostAddress() : null;
    }
    
    public int D() {
        return (this.B != null) ? this.B.getPort() : 0;
    }
    
    public SocketChannel A() {
        return this.E;
    }
    
    void A(final SocketChannel e) throws IOException {
        this.E = e;
        this.B = (InetSocketAddress)e.socket().getRemoteSocketAddress();
        this.D.release();
    }
    
    boolean A(final int n) throws InterruptedException {
        return this.D.tryAcquire(n, TimeUnit.MILLISECONDS);
    }
    
    public void A(final boolean b) {
        Label_0068: {
            try {
                this.D.release();
                if (this.E != null) {
                    this.E.close();
                }
            }
            catch (IOException ex) {
                org.xmodel.net.stream.A.C.exception(ex);
                break Label_0068;
            }
            finally {
                this.E = null;
                this.B = null;
            }
            this.E = null;
            this.B = null;
        }
        if (this.G != null) {
            this.G.onClose(this);
        }
    }
    
    @Override
    public boolean isOpen() {
        return this.B != null;
    }
    
    int B() throws IOException {
        if (this.E == null) {
            return -1;
        }
        if (this.A.position() < this.A.limit()) {
            this.A.position(this.A.limit());
            this.A.limit(this.A.capacity());
        }
        else {
            this.A.clear();
        }
        this.E();
        final int read = this.E.read(this.A);
        if (read == -1) {
            return read;
        }
        if (this.G != null && read > 0) {
            this.A.flip();
            org.xmodel.net.stream.A.C.debugf("READ\n%s\n", Util.dump(this.A));
            this.G.onReceive(this, this.A);
        }
        return read;
    }
    
    @Override
    public void send(final ByteBuffer byteBuffer) throws IOException {
        org.xmodel.net.stream.A.C.debugf("SEND\n%s\n", Util.dump(byteBuffer));
        this.F.A(this.E, byteBuffer);
    }
    
    private void E() {
        if (this.A.position() == this.A.limit()) {
            final ByteBuffer allocate = ByteBuffer.allocate(this.A.capacity() << 1);
            this.A.flip();
            allocate.put(this.A);
            this.A = allocate;
        }
    }
    
    @Override
    public void close() {
        if (this.E != null) {
            try {
                this.E.close();
            }
            catch (Exception ex) {}
            this.E = null;
        }
    }
    
    @Override
    public String toString() {
        return String.format("%s:%d", this.C(), this.D());
    }
}
