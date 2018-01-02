// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.ArrayList;
import org.jruby.runtime.builtin.IRubyObject;
import java.util.Collections;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantLock;
import org.jruby.RubyThread;
import java.util.Map;
import java.lang.ref.SoftReference;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;

public class ThreadService
{
    private Ruby runtime;
    private ThreadContext mainContext;
    private ThreadLocal<SoftReference<ThreadContext>> localContext;
    private ThreadGroup rubyThreadGroup;
    private final Map<Object, RubyThread> rubyThreadMap;
    private final ReentrantLock criticalLock;
    
    public ThreadService(final Ruby runtime) {
        this.criticalLock = new ReentrantLock();
        this.runtime = runtime;
        this.localContext = new ThreadLocal<SoftReference<ThreadContext>>();
        try {
            this.rubyThreadGroup = new ThreadGroup("Ruby Threads#" + runtime.hashCode());
        }
        catch (SecurityException e) {
            this.rubyThreadGroup = Thread.currentThread().getThreadGroup();
        }
        this.rubyThreadMap = Collections.synchronizedMap(new WeakHashMap<Object, RubyThread>());
    }
    
    public void disposeCurrentThread() {
        this.localContext.set(null);
        this.rubyThreadMap.remove(Thread.currentThread());
    }
    
    public void initMainThread() {
        this.mainContext = ThreadContext.newContext(this.runtime);
        this.localContext.set(new SoftReference<ThreadContext>(this.mainContext));
    }
    
    public ThreadContext getCurrentContext() {
        SoftReference sr = null;
        ThreadContext context = null;
        while (context == null) {
            if ((sr = this.localContext.get()) == null) {
                sr = this.adoptCurrentThread();
                context = sr.get();
            }
            else {
                context = sr.get();
            }
            if (context == null) {
                this.localContext.set(null);
            }
        }
        return context;
    }
    
    private SoftReference adoptCurrentThread() {
        final Thread current = Thread.currentThread();
        RubyThread.adopt(this.runtime.getThread(), current);
        return this.localContext.get();
    }
    
    public RubyThread getMainThread() {
        return this.mainContext.getThread();
    }
    
    public void setMainThread(final Thread thread, final RubyThread rubyThread) {
        this.mainContext.setThread(rubyThread);
        this.rubyThreadMap.put(thread, rubyThread);
    }
    
    public synchronized RubyThread[] getActiveRubyThreads() {
        synchronized (this.rubyThreadMap) {
            final List<RubyThread> rtList = new ArrayList<RubyThread>(this.rubyThreadMap.size());
            for (final Map.Entry<Object, RubyThread> entry : this.rubyThreadMap.entrySet()) {
                final Object key = entry.getKey();
                if (key == null) {
                    continue;
                }
                if (key instanceof Thread) {
                    final Thread t = (Thread)key;
                    if (!t.isAlive()) {
                        continue;
                    }
                }
                else if (key instanceof Future) {
                    final Future f = (Future)key;
                    if (f.isDone()) {
                        continue;
                    }
                    if (f.isCancelled()) {
                        continue;
                    }
                }
                rtList.add(entry.getValue());
            }
            final RubyThread[] rubyThreads = new RubyThread[rtList.size()];
            rtList.toArray(rubyThreads);
            return rubyThreads;
        }
    }
    
    public ThreadGroup getRubyThreadGroup() {
        return this.rubyThreadGroup;
    }
    
    public ThreadContext getThreadContextForThread(final RubyThread thread) {
        return thread.getContext();
    }
    
    public synchronized ThreadContext registerNewThread(final RubyThread thread) {
        final ThreadContext context = ThreadContext.newContext(this.runtime);
        this.localContext.set(new SoftReference<ThreadContext>(context));
        context.setThread(thread);
        return context;
    }
    
    public synchronized void associateThread(final Object threadOrFuture, final RubyThread rubyThread) {
        this.rubyThreadMap.put(threadOrFuture, rubyThread);
    }
    
    public synchronized void dissociateThread(final Object threadOrFuture) {
        this.rubyThreadMap.remove(threadOrFuture);
    }
    
    public synchronized void unregisterThread(final RubyThread thread) {
        this.rubyThreadMap.remove(Thread.currentThread());
        this.getCurrentContext().setThread(null);
        this.localContext.set(null);
    }
    
    public void setCritical(final boolean critical) {
        if (critical && !this.criticalLock.isHeldByCurrentThread()) {
            this.acquireCritical();
        }
        else if (!critical && this.criticalLock.isHeldByCurrentThread()) {
            this.releaseCritical();
        }
    }
    
    private void acquireCritical() {
        this.criticalLock.lock();
    }
    
    private void releaseCritical() {
        this.criticalLock.unlock();
    }
    
    public boolean getCritical() {
        return this.criticalLock.isHeldByCurrentThread();
    }
    
    public synchronized void deliverEvent(final Event event) {
        event.sender.checkMail(this.getCurrentContext());
        event.target.receiveMail(event);
    }
    
    public Map<Object, RubyThread> getRubyThreadMap() {
        return this.rubyThreadMap;
    }
    
    public static class Event
    {
        public final RubyThread sender;
        public final RubyThread target;
        public final Type type;
        public final IRubyObject exception;
        
        public Event(final RubyThread sender, final RubyThread target, final Type type) {
            this(sender, target, type, null);
        }
        
        public Event(final RubyThread sender, final RubyThread target, final Type type, final IRubyObject exception) {
            this.sender = sender;
            this.target = target;
            this.type = type;
            this.exception = exception;
        }
        
        public enum Type
        {
            KILL, 
            RAISE, 
            WAKEUP;
        }
    }
}
