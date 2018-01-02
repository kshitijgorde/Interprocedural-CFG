// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.service;

import java.lang.reflect.Constructor;
import org.slf4j.LoggerFactory;
import org.apache.mina.core.session.IoSession;
import java.util.Arrays;
import org.apache.mina.core.RuntimeIoException;
import com.masystem.beergame.debug.Log;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Executor;
import org.apache.mina.core.session.AttributeKey;
import org.slf4j.Logger;
import org.apache.mina.core.session.AbstractIoSession;

public class SimpleIoProcessorPool<S extends AbstractIoSession> implements IoProcessor<S>
{
    private static final Logger LOGGER;
    private static final int DEFAULT_SIZE;
    private static final AttributeKey PROCESSOR;
    private final IoProcessor<S>[] pool;
    private final Executor executor;
    private final boolean createdExecutor;
    private final Object disposalLock;
    private volatile boolean disposing;
    private volatile boolean disposed;
    
    public SimpleIoProcessorPool(final Class<? extends IoProcessor<S>> clazz) {
        this(clazz, null, SimpleIoProcessorPool.DEFAULT_SIZE);
    }
    
    public SimpleIoProcessorPool(final Class<? extends IoProcessor<S>> clazz, final int n) {
        this(clazz, null, n);
    }
    
    public SimpleIoProcessorPool(final Class<? extends IoProcessor<S>> clazz, final Executor executor) {
        this(clazz, executor, SimpleIoProcessorPool.DEFAULT_SIZE);
    }
    
    public SimpleIoProcessorPool(final Class<? extends IoProcessor<S>> clazz, Executor executor, int n) {
        this.disposalLock = new Object();
        if (clazz == null) {
            throw new IllegalArgumentException("processorType");
        }
        if (n <= 0) {
            throw new IllegalArgumentException("size: " + n + " (expected: positive integer)");
        }
        this.createdExecutor = (executor == null);
        if (this.createdExecutor) {
            this.executor = Executors.newCachedThreadPool();
        }
        else {
            this.executor = executor;
        }
        this.pool = (IoProcessor<S>[])new IoProcessor[n];
        executor = null;
        n = 1;
        try {
            try {
                try {
                    executor = (Executor)clazz.getConstructor(ExecutorService.class);
                    this.pool[0] = ((Constructor<IoProcessor<S>>)executor).newInstance(this.executor);
                }
                catch (NoSuchMethodException ex3) {
                    try {
                        executor = (Executor)clazz.getConstructor(Executor.class);
                        this.pool[0] = ((Constructor<IoProcessor<S>>)executor).newInstance(this.executor);
                    }
                    catch (NoSuchMethodException ex4) {
                        try {
                            executor = (Executor)clazz.getConstructor((Class<?>[])new Class[0]);
                            n = 0;
                            this.pool[0] = ((Constructor<IoProcessor<S>>)executor).newInstance(new Object[0]);
                        }
                        catch (NoSuchMethodException ex5) {}
                    }
                }
            }
            catch (RuntimeException ex) {
                final Logger logger = SimpleIoProcessorPool.LOGGER;
                final String s = "Cannot create an IoProcessor :{}";
                (new Object[1])[0] = ex.getMessage();
                Log.debug(s);
                throw ex;
            }
            catch (Exception ex2) {
                final String string = "Failed to create a new instance of " + clazz.getName() + ":" + ex2.getMessage();
                final Logger logger2 = SimpleIoProcessorPool.LOGGER;
                Log.error(string, ex2);
                throw new RuntimeIoException(string, ex2);
            }
            if (executor == null) {
                final String string2 = String.valueOf(clazz) + " must have a public constructor with one " + ExecutorService.class.getSimpleName() + " parameter, a public constructor with one " + Executor.class.getSimpleName() + " parameter or a public default constructor.";
                final Logger logger3 = SimpleIoProcessorPool.LOGGER;
                Log.debug(string2);
                throw new IllegalArgumentException(string2);
            }
            for (int i = 1; i < this.pool.length; ++i) {
                try {
                    if (n != 0) {
                        this.pool[i] = ((Constructor<IoProcessor<S>>)executor).newInstance(this.executor);
                    }
                    else {
                        this.pool[i] = ((Constructor<IoProcessor<S>>)executor).newInstance(new Object[0]);
                    }
                }
                catch (Exception ex6) {}
            }
        }
        finally {
            this.dispose();
        }
    }
    
    @Override
    public final void add(final S n) {
        this.getProcessor(n).add(n);
    }
    
    @Override
    public final void flush(final S n) {
        this.getProcessor(n).flush(n);
    }
    
    @Override
    public final void remove(final S n) {
        this.getProcessor(n).remove(n);
    }
    
    @Override
    public final void updateTrafficControl(final S n) {
        this.getProcessor(n).updateTrafficControl(n);
    }
    
    public boolean isDisposed() {
        return this.disposed;
    }
    
    @Override
    public boolean isDisposing() {
        return this.disposing;
    }
    
    @Override
    public final void dispose() {
        if (this.disposed) {
            return;
        }
        synchronized (this.disposalLock) {
            if (!this.disposing) {
                this.disposing = true;
                IoProcessor<S>[] pool;
                for (int length = (pool = this.pool).length, i = 0; i < length; ++i) {
                    final IoProcessor<S> ioProcessor;
                    if (!(ioProcessor = pool[i]).isDisposing()) {
                        try {
                            ioProcessor.dispose();
                        }
                        catch (Exception ex) {
                            final Logger logger = SimpleIoProcessorPool.LOGGER;
                            Log.warn("Failed to dispose the {} IoProcessor.", ioProcessor.getClass().getSimpleName(), ex);
                        }
                    }
                }
                if (this.createdExecutor) {
                    ((ExecutorService)this.executor).shutdown();
                }
            }
            Arrays.fill(this.pool, null);
            this.disposed = true;
        }
    }
    
    private IoProcessor<S> getProcessor(final S n) {
        IoProcessor<S> ioProcessor;
        if ((ioProcessor = (IoProcessor<S>)n.getAttribute(SimpleIoProcessorPool.PROCESSOR)) == null) {
            if (this.disposed || this.disposing) {
                throw new IllegalStateException("A disposed processor cannot be accessed.");
            }
            ioProcessor = this.pool[Math.abs((int)n.getId()) % this.pool.length];
            n.setAttributeIfAbsent(SimpleIoProcessorPool.PROCESSOR, ioProcessor);
        }
        return ioProcessor;
    }
    
    static {
        LOGGER = LoggerFactory.getLogger$4ecaad6a();
        DEFAULT_SIZE = Runtime.getRuntime().availableProcessors() + 1;
        PROCESSOR = new AttributeKey(SimpleIoProcessorPool.class, "processor");
    }
}
