// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net.stream;

import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.ByteBuffer;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.nio.channels.spi.SelectorProvider;
import java.util.Collections;
import java.util.HashMap;
import java.io.IOException;
import java.util.Queue;
import java.nio.channels.Channel;
import java.util.Map;
import java.nio.channels.Selector;
import org.xmodel.net.ILink;
import org.xmodel.log.Log;

public abstract class TcpBase
{
    private static final Log E;
    private ILink.IListener F;
    protected Selector selector;
    private Map<Channel, A> D;
    private Queue<Request> B;
    private Map<Channel, Request> G;
    private Thread A;
    private boolean C;
    
    static {
        E = Log.getLog("org.xmodel.net.stream");
    }
    
    public TcpBase() throws IOException {
        this(null);
    }
    
    public TcpBase(final ILink.IListener f) throws IOException {
        this.F = f;
        this.D = Collections.synchronizedMap(new HashMap<Channel, A>());
        this.selector = SelectorProvider.provider().openSelector();
        this.B = new ConcurrentLinkedQueue<Request>();
        this.G = new HashMap<Channel, Request>();
    }
    
    public void start(final boolean daemon) {
        (this.A = new Thread(new Runnable() {
            @Override
            public void run() {
                TcpBase.this.A();
            }
        }, this.getClass().getSimpleName())).setDaemon(daemon);
        this.A.start();
    }
    
    public void stop() {
        this.C = true;
        this.selector.wakeup();
    }
    
    protected A getConnection(final Channel channel) {
        return this.D.get(channel);
    }
    
    protected A connect(final String s, final int n, final int n2, final ILink.IListener listener) throws IOException {
        final SocketChannel open = SocketChannel.open();
        open.configureBlocking(false);
        open.connect(new InetSocketAddress(s, n));
        final A connection = this.createConnection(open, listener);
        final Request request = new Request();
        request.channel = open;
        request.buffer = null;
        request.ops = 8;
        this.A(request);
        try {
            if (!connection.A(n2)) {
                return null;
            }
        }
        catch (InterruptedException ex) {}
        return connection;
    }
    
    protected boolean reconnect(final A a, final int n) throws IOException {
        if (a.isOpen()) {
            a.close();
        }
        final Request request = new Request();
        request.channel = a.A();
        request.buffer = null;
        request.ops = 8;
        this.A(request);
        try {
            return a.A(n);
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void A(final SocketChannel channel, final ByteBuffer byteBuffer) {
        final ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.remaining());
        allocate.put(byteBuffer);
        allocate.flip();
        final Request request = new Request();
        request.channel = channel;
        request.buffer = allocate;
        request.ops = 5;
        this.A(request);
    }
    
    private void A(final Request request) {
        this.B.offer(request);
        this.selector.wakeup();
    }
    
    protected boolean process(final int n) throws IOException {
        if (this.selector.select(n) == 0) {
            return false;
        }
        final Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
        while (iterator.hasNext()) {
            final SelectionKey selectionKey = iterator.next();
            iterator.remove();
            if (!selectionKey.isValid()) {
                continue;
            }
            try {
                if (selectionKey.isReadable()) {
                    this.read(selectionKey);
                }
                if (selectionKey.isWritable()) {
                    this.write(selectionKey);
                }
                if (selectionKey.isAcceptable()) {
                    this.C(selectionKey);
                }
                if (!selectionKey.isConnectable()) {
                    continue;
                }
                this.B(selectionKey);
            }
            catch (IOException ex) {
                TcpBase.E.verbose(ex.getMessage());
            }
            catch (CancelledKeyException ex2) {
                TcpBase.E.verbose(ex2.getMessage());
            }
        }
        return true;
    }
    
    protected A createConnection(final SocketChannel socketChannel, final ILink.IListener listener) throws IOException {
        final A a = new A(this, socketChannel, listener);
        this.D.put(a.A(), a);
        return a;
    }
    
    private void B(final SelectionKey selectionKey) throws IOException {
        if (this.G.remove(selectionKey.channel()) != null) {
            final SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
            final A connection = this.getConnection(socketChannel);
            try {
                socketChannel.finishConnect();
                socketChannel.register(this.selector, 1);
                connection.A(socketChannel);
            }
            catch (IOException ex) {
                connection.A(false);
            }
        }
    }
    
    private void C(final SelectionKey selectionKey) throws IOException {
        final A connection = this.createConnection(((ServerSocketChannel)selectionKey.channel()).accept(), this.F);
        connection.A().configureBlocking(false);
        connection.A().register(this.selector, 1);
    }
    
    protected void read(final SelectionKey selectionKey) throws IOException {
        final A a = this.D.get(selectionKey.channel());
        try {
            if (a.B() == -1) {
                this.A(selectionKey);
            }
        }
        catch (Exception ex) {
            this.A(selectionKey);
        }
    }
    
    protected void write(final SelectionKey selectionKey) throws IOException {
        final Request request = this.G.remove(selectionKey.channel());
        if (request != null) {
            request.channel.write(request.buffer);
            final Request request2 = this.B.peek();
            if (request2 == null || request2.channel != selectionKey.channel()) {
                selectionKey.channel().register(this.selector, 1);
            }
        }
    }
    
    private void A(final SelectionKey selectionKey) {
        final A a = this.D.remove(selectionKey.channel());
        if (a != null) {
            a.A(false);
        }
        selectionKey.cancel();
    }
    
    private void A() {
        this.C = false;
        while (!this.C) {
            try {
                this.process(0);
                final Request request = this.B.poll();
                if (request == null) {
                    continue;
                }
                try {
                    this.G.put(request.channel, request);
                    if (request.channel == null) {
                        continue;
                    }
                    request.channel.register(this.selector, request.ops);
                }
                catch (CancelledKeyException ex) {
                    this.G.remove(request.channel);
                    TcpBase.E.exception(ex);
                }
            }
            catch (IOException ex2) {
                TcpBase.E.exception(ex2);
                break;
            }
        }
    }
    
    protected static class Request
    {
        public SocketChannel channel;
        public ByteBuffer buffer;
        public int ops;
    }
}
