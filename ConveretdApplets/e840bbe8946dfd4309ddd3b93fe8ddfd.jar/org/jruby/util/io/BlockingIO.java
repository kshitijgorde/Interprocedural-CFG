// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.io;

import java.util.Iterator;
import java.util.Set;
import java.util.LinkedList;
import java.util.List;
import java.util.Collection;
import java.nio.channels.SelectionKey;
import java.util.HashSet;
import org.jruby.Ruby;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.nio.channels.Selector;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ConcurrentHashMap;
import java.io.InterruptedIOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.Channel;
import java.io.IOException;
import java.nio.channels.spi.SelectorProvider;
import java.util.Map;

public class BlockingIO
{
    private static final Map<SelectorProvider, IOSelector> selectors;
    
    private static IOSelector getSelector(final SelectorProvider provider) throws IOException {
        IOSelector sel = BlockingIO.selectors.get(provider);
        if (sel != null) {
            return sel;
        }
        synchronized (provider) {
            sel = BlockingIO.selectors.get(provider);
            if (sel == null) {
                sel = new IOSelector(provider);
                BlockingIO.selectors.put(provider, sel);
                final Thread t = new Thread(sel);
                t.setDaemon(true);
                t.start();
            }
        }
        return sel;
    }
    
    private static IOSelector getSelector(final Channel channel) throws IOException {
        if (!(channel instanceof SelectableChannel)) {
            throw new IllegalArgumentException("channel must be a SelectableChannel");
        }
        return getSelector(((SelectableChannel)channel).provider());
    }
    
    public static final Condition newCondition(final Channel channel, final int ops, final Object monitor) throws IOException {
        return getSelector(channel).add(channel, ops, monitor);
    }
    
    public static final Condition newCondition(final Channel channel, final int ops) throws IOException {
        return newCondition(channel, ops, new Object());
    }
    
    public static void waitForIO(final Channel channel, final int op) throws InterruptedException, IOException {
        getSelector(channel).await(channel, op);
    }
    
    public static void awaitReadable(final ReadableByteChannel channel) throws InterruptedException, IOException {
        waitForIO(channel, 1);
    }
    
    public static void awaitWritable(final WritableByteChannel channel) throws InterruptedException, IOException {
        waitForIO(channel, 4);
    }
    
    public static int read(final ReadableByteChannel channel, final ByteBuffer buf, final boolean blocking) throws IOException {
        int n;
        while (true) {
            n = channel.read(buf);
            if (n != 0 || !blocking || !(channel instanceof SelectableChannel) || !buf.hasRemaining()) {
                break;
            }
            try {
                awaitReadable(channel);
            }
            catch (InterruptedException ex) {
                throw new InterruptedIOException(ex.getMessage());
            }
        }
        return n;
    }
    
    public static int write(final WritableByteChannel channel, final ByteBuffer buf, final boolean blocking) throws IOException {
        int n;
        while (true) {
            n = channel.write(buf);
            if (n != 0 || !blocking || !(channel instanceof SelectableChannel) || !buf.hasRemaining()) {
                break;
            }
            try {
                awaitWritable(channel);
            }
            catch (InterruptedException ex) {
                throw new InterruptedIOException(ex.getMessage());
            }
        }
        return n;
    }
    
    public static int blockingRead(final ReadableByteChannel channel, final ByteBuffer buf) throws IOException {
        return read(channel, buf, true);
    }
    
    public static int blockingWrite(final WritableByteChannel channel, final ByteBuffer buf) throws IOException {
        return write(channel, buf, true);
    }
    
    static {
        selectors = new ConcurrentHashMap<SelectorProvider, IOSelector>();
    }
    
    public static final class Condition
    {
        private final IOChannel channel;
        
        Condition(final IOChannel channel) {
            this.channel = channel;
        }
        
        public void cancel() {
            this.channel.wakeup(false);
        }
        
        public void interrupt() {
            this.channel.interrupt();
        }
        
        public boolean await() throws InterruptedException {
            return this.channel.await();
        }
        
        public boolean await(final long timeout, final TimeUnit unit) throws InterruptedException {
            return this.channel.await(timeout, unit);
        }
    }
    
    static final class IOChannel
    {
        final SelectableChannel channel;
        final int ops;
        private final Object monitor;
        private boolean woken;
        private boolean ready;
        private boolean interrupted;
        
        IOChannel(final SelectableChannel channel, final int ops, final Object monitor) {
            this.woken = false;
            this.ready = false;
            this.interrupted = false;
            this.channel = channel;
            this.ops = ops;
            this.monitor = monitor;
        }
        
        public final void wakeup(final boolean ready) {
            synchronized (this.monitor) {
                this.woken = true;
                this.ready = ready;
                this.monitor.notifyAll();
            }
        }
        
        public final void interrupt() {
            synchronized (this.monitor) {
                this.woken = true;
                this.interrupted = true;
                this.monitor.notifyAll();
            }
        }
        
        public final boolean await() throws InterruptedException {
            return this.await(0L, TimeUnit.MILLISECONDS);
        }
        
        public final boolean await(final long timeout, final TimeUnit unit) throws InterruptedException {
            synchronized (this.monitor) {
                if (!this.woken) {
                    this.monitor.wait(TimeUnit.MILLISECONDS.convert(timeout, unit));
                }
                if (this.interrupted) {
                    throw new InterruptedException("Interrupted");
                }
                return this.ready;
            }
        }
    }
    
    static final class IOSelector implements Runnable
    {
        private final Selector selector;
        private final ConcurrentLinkedQueue<IOChannel> registrationQueue;
        
        public IOSelector(final SelectorProvider provider) throws IOException {
            this.selector = SelectorFactory.openWithRetryFrom(null, provider);
            this.registrationQueue = new ConcurrentLinkedQueue<IOChannel>();
        }
        
        public void run() {
            while (true) {
                try {
                    while (true) {
                        final Set<SelectionKey> selected = new HashSet<SelectionKey>(this.selector.selectedKeys());
                        for (final SelectionKey k : selected) {
                            final List<IOChannel> waitq = (List<IOChannel>)k.attachment();
                            for (final IOChannel ch : waitq) {
                                ch.wakeup(true);
                            }
                            waitq.clear();
                        }
                        final Set<SelectableChannel> added = new HashSet<SelectableChannel>();
                        IOChannel ch2;
                        while ((ch2 = this.registrationQueue.poll()) != null) {
                            final SelectionKey i = ch2.channel.keyFor(this.selector);
                            final List<IOChannel> waitq2 = (i == null) ? new LinkedList<IOChannel>() : ((List)i.attachment());
                            ch2.channel.register(this.selector, ch2.ops, waitq2);
                            waitq2.add(ch2);
                            added.add(ch2.channel);
                        }
                        for (final SelectionKey j : selected) {
                            if (!added.contains(j.channel())) {
                                j.cancel();
                            }
                        }
                        this.selector.select();
                    }
                }
                catch (IOException ex) {
                    continue;
                }
                break;
            }
        }
        
        Condition add(final Channel channel, final int ops, final Object monitor) {
            final IOChannel io = new IOChannel((SelectableChannel)channel, ops, monitor);
            this.registrationQueue.add(io);
            this.selector.wakeup();
            return new Condition(io);
        }
        
        public void await(final Channel channel, final int op) throws InterruptedException {
            this.add(channel, op, new Object()).await();
        }
    }
}
