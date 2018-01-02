// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.vm;

import org.apache.activemq.thread.DefaultThreadPools;
import org.apache.activemq.transport.FutureResponse;
import org.apache.activemq.transport.ResponseCallback;
import org.apache.activemq.command.ShutdownInfo;
import java.io.InterruptedIOException;
import java.io.IOException;
import org.apache.activemq.transport.TransportDisposedIOException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.activemq.thread.Valve;
import org.apache.activemq.thread.TaskRunner;
import java.net.URI;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.activemq.transport.TransportListener;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.activemq.thread.Task;
import org.apache.activemq.transport.Transport;

public class VMTransport implements Transport, Task
{
    private static final Object DISCONNECT;
    private static final AtomicLong NEXT_ID;
    protected VMTransport peer;
    protected TransportListener transportListener;
    protected boolean disposed;
    protected boolean marshal;
    protected boolean network;
    protected boolean async;
    protected int asyncQueueDepth;
    protected LinkedBlockingQueue<Object> messageQueue;
    protected boolean started;
    protected final URI location;
    protected final long id;
    private TaskRunner taskRunner;
    private final Object lazyInitMutext;
    private final Valve enqueueValve;
    protected final AtomicBoolean stopping;
    private volatile int receiveCounter;
    
    public VMTransport(final URI location) {
        this.async = true;
        this.asyncQueueDepth = 2000;
        this.lazyInitMutext = new Object();
        this.enqueueValve = new Valve(true);
        this.stopping = new AtomicBoolean();
        this.location = location;
        this.id = VMTransport.NEXT_ID.getAndIncrement();
    }
    
    public void setPeer(final VMTransport peer) {
        this.peer = peer;
    }
    
    @Override
    public void oneway(final Object command) throws IOException {
        if (this.disposed) {
            throw new TransportDisposedIOException("Transport disposed.");
        }
        if (this.peer == null) {
            throw new IOException("Peer not connected.");
        }
        TransportListener transportListener = null;
        try {
            this.peer.enqueueValve.increment();
            if (this.peer.disposed || this.peer.stopping.get()) {
                throw new TransportDisposedIOException("Peer (" + this.peer.toString() + ") disposed.");
            }
            if (this.peer.started) {
                if (this.peer.async) {
                    this.peer.getMessageQueue().put(command);
                    this.peer.wakeup();
                }
                else {
                    transportListener = this.peer.transportListener;
                }
            }
            else {
                this.peer.getMessageQueue().put(command);
            }
        }
        catch (InterruptedException e) {
            final InterruptedIOException iioe = new InterruptedIOException(e.getMessage());
            iioe.initCause(e);
            throw iioe;
        }
        finally {
            this.peer.enqueueValve.decrement();
        }
        this.dispatch(this.peer, transportListener, command);
    }
    
    public void dispatch(final VMTransport transport, final TransportListener transportListener, final Object command) {
        if (transportListener != null) {
            if (command == VMTransport.DISCONNECT) {
                transportListener.onException(new TransportDisposedIOException("Peer (" + this.peer.toString() + ") disposed."));
            }
            else {
                ++transport.receiveCounter;
                transportListener.onCommand(command);
            }
        }
    }
    
    @Override
    public void start() throws Exception {
        if (this.transportListener == null) {
            throw new IOException("TransportListener not set.");
        }
        try {
            this.enqueueValve.turnOff();
            if (this.messageQueue != null && !this.async) {
                Object command;
                while ((command = this.messageQueue.poll()) != null && !this.stopping.get()) {
                    ++this.receiveCounter;
                    this.dispatch(this, this.transportListener, command);
                }
            }
            this.started = true;
            this.wakeup();
        }
        finally {
            this.enqueueValve.turnOn();
        }
        if (this.stopping.get()) {
            this.stop();
        }
    }
    
    @Override
    public void stop() throws Exception {
        this.stopping.set(true);
        if (this.enqueueValve.isOn()) {
            try {
                this.peer.transportListener.onCommand(new ShutdownInfo());
            }
            catch (Exception ex) {}
            TaskRunner tr = null;
            try {
                this.enqueueValve.turnOff();
                if (!this.disposed) {
                    this.started = false;
                    this.disposed = true;
                    if (this.taskRunner != null) {
                        tr = this.taskRunner;
                        this.taskRunner = null;
                    }
                }
            }
            finally {
                this.stopping.set(false);
                this.enqueueValve.turnOn();
            }
            if (tr != null) {
                tr.shutdown(1000L);
            }
        }
    }
    
    @Override
    public boolean iterate() {
        TransportListener tl;
        try {
            this.enqueueValve.increment();
            tl = this.transportListener;
            if (!this.started || this.disposed || tl == null || this.stopping.get()) {
                if (this.stopping.get()) {
                    this.getMessageQueue().clear();
                }
                return false;
            }
        }
        catch (InterruptedException e) {
            return false;
        }
        finally {
            this.enqueueValve.decrement();
        }
        final LinkedBlockingQueue<Object> mq = this.getMessageQueue();
        final Object command = mq.poll();
        if (command != null) {
            if (command == VMTransport.DISCONNECT) {
                tl.onException(new TransportDisposedIOException("Peer (" + this.peer.toString() + ") disposed."));
            }
            else {
                tl.onCommand(command);
            }
            return !mq.isEmpty();
        }
        return false;
    }
    
    @Override
    public void setTransportListener(final TransportListener commandListener) {
        try {
            try {
                this.enqueueValve.turnOff();
                this.transportListener = commandListener;
                this.wakeup();
            }
            finally {
                this.enqueueValve.turnOn();
            }
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    private LinkedBlockingQueue<Object> getMessageQueue() {
        synchronized (this.lazyInitMutext) {
            if (this.messageQueue == null) {
                this.messageQueue = new LinkedBlockingQueue<Object>(this.asyncQueueDepth);
            }
            return this.messageQueue;
        }
    }
    
    @Override
    public FutureResponse asyncRequest(final Object command, final ResponseCallback responseCallback) throws IOException {
        throw new AssertionError((Object)"Unsupported Method");
    }
    
    @Override
    public Object request(final Object command) throws IOException {
        throw new AssertionError((Object)"Unsupported Method");
    }
    
    @Override
    public Object request(final Object command, final int timeout) throws IOException {
        throw new AssertionError((Object)"Unsupported Method");
    }
    
    @Override
    public TransportListener getTransportListener() {
        return this.transportListener;
    }
    
    @Override
    public <T> T narrow(final Class<T> target) {
        if (target.isAssignableFrom(this.getClass())) {
            return target.cast(this);
        }
        return null;
    }
    
    public boolean isMarshal() {
        return this.marshal;
    }
    
    public void setMarshal(final boolean marshal) {
        this.marshal = marshal;
    }
    
    public boolean isNetwork() {
        return this.network;
    }
    
    public void setNetwork(final boolean network) {
        this.network = network;
    }
    
    @Override
    public String toString() {
        return this.location + "#" + this.id;
    }
    
    @Override
    public String getRemoteAddress() {
        if (this.peer != null) {
            return this.peer.toString();
        }
        return null;
    }
    
    public boolean isAsync() {
        return this.async;
    }
    
    public void setAsync(final boolean async) {
        this.async = async;
    }
    
    public int getAsyncQueueDepth() {
        return this.asyncQueueDepth;
    }
    
    public void setAsyncQueueDepth(final int asyncQueueDepth) {
        this.asyncQueueDepth = asyncQueueDepth;
    }
    
    protected void wakeup() {
        if (this.async) {
            synchronized (this.lazyInitMutext) {
                if (this.taskRunner == null) {
                    this.taskRunner = DefaultThreadPools.getDefaultTaskRunnerFactory().createTaskRunner(this, "VMTransport: " + this.toString());
                }
            }
            try {
                this.taskRunner.wakeup();
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    @Override
    public boolean isFaultTolerant() {
        return false;
    }
    
    @Override
    public boolean isDisposed() {
        return this.disposed;
    }
    
    @Override
    public boolean isConnected() {
        return this.started;
    }
    
    @Override
    public void reconnect(final URI uri) throws IOException {
        throw new IOException("Not supported");
    }
    
    @Override
    public boolean isReconnectSupported() {
        return false;
    }
    
    @Override
    public boolean isUpdateURIsSupported() {
        return false;
    }
    
    @Override
    public void updateURIs(final boolean reblance, final URI[] uris) throws IOException {
        throw new IOException("Not supported");
    }
    
    @Override
    public int getReceiveCounter() {
        return this.receiveCounter;
    }
    
    static {
        DISCONNECT = new Object();
        NEXT_ID = new AtomicLong(0L);
    }
}
