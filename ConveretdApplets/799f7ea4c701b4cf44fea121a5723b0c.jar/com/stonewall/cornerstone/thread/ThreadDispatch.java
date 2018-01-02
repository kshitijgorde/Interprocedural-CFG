// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.thread;

import com.stonewall.cornerstone.utility.Transaction;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.security.User;
import java.lang.reflect.Method;

public class ThreadDispatch implements Cloneable, Comparable
{
    private ThreadPool pool;
    private Object object;
    protected Method method;
    private Object[] args;
    int bind;
    Object group;
    long queuePriority;
    private ExceptionListener listener;
    private short runCount;
    private User user;
    private String tid;
    static final Log log;
    
    static {
        log = Log.getLog(ThreadDispatch.class);
    }
    
    public ThreadDispatch(final ThreadPool pool, final Method method) {
        this.pool = null;
        this.object = null;
        this.method = null;
        this.args = null;
        this.bind = -1;
        this.group = null;
        this.queuePriority = 0L;
        this.listener = null;
        this.runCount = 0;
        this.user = User.getCurrent();
        this.tid = getTransactionId();
        this.pool = pool;
        this.method = method;
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (Exception e) {
            ThreadDispatch.log.error(this, e);
            return null;
        }
    }
    
    @Override
    public int compareTo(final Object o) {
        final ThreadDispatch other = (ThreadDispatch)o;
        if (this.queuePriority > other.queuePriority) {
            return 1;
        }
        if (this.queuePriority < other.queuePriority) {
            return -1;
        }
        return 0;
    }
    
    public final void submit(final Object object, final Object... args) {
        this.object = object;
        this.args = args;
        this.user = User.getCurrent();
        this.tid = getTransactionId();
        this.pool.submit(this);
    }
    
    public final void submit() {
        this.user = User.getCurrent();
        this.tid = getTransactionId();
        this.pool.submit(this);
    }
    
    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("dispatch: ");
        sb.append("userid=" + this.user.getId());
        sb.append(", gsn=" + this.user.getSessionId());
        sb.append(", tid=" + this.tid);
        sb.append(", bind=" + this.bind);
        sb.append(", group=" + this.group);
        sb.append(", qPty=" + this.queuePriority);
        sb.append(", object=" + this.object);
        sb.append(", method=" + this.method.getName());
        sb.append(", args=" + this.args);
        return sb.toString();
    }
    
    public final void bind(final int index) {
        if (index < 0 || index > this.pool.maxThreads) {
            final String msg = "Bind index must be > 0 < ThreadPool.maxThreads";
            throw new IllegalArgumentException(msg);
        }
        this.bind = index;
    }
    
    public final void bind(final Object group) {
        if (this.bind != -1) {
            final String msg = "already bound using bind(int)";
            new IllegalStateException(msg);
        }
        this.group = group;
    }
    
    public final void setListener(final ExceptionListener lnr) {
        this.listener = lnr;
    }
    
    public final short getRunCount() {
        return this.runCount;
    }
    
    protected void beforeRun() {
    }
    
    protected void afterRun(final Object result) {
    }
    
    protected Object invoke(final Object object, final Object[] args) throws Exception {
        return this.method.invoke(object, args);
    }
    
    final void run() {
        try {
            ++this.runCount;
            this.beforeRun();
            User.setCurrent(this.user);
            Transaction.clearAll();
            if (this.tid != null) {
                new Transaction(this.tid).begin();
            }
            final Object result = this.invoke(this.object, this.args);
            Transaction.commitAll();
            this.afterRun(result);
        }
        catch (Exception e) {
            this.notifyListener(this, e);
            return;
        }
        finally {
            Transaction.clearAll();
        }
        Transaction.clearAll();
    }
    
    final void notifyListener(final ThreadDispatch dispatch, final Exception ex) {
        if (this.listener == null) {
            ThreadDispatch.log.debug(dispatch, ex);
            return;
        }
        try {
            this.listener.onException(dispatch, ex);
        }
        catch (Exception e) {
            ThreadDispatch.log.debug(this.listener, e);
        }
    }
    
    private void beginTransaction() {
        if (this.tid != null) {
            final Transaction tr = new Transaction(this.tid);
            tr.begin();
        }
    }
    
    private static String getTransactionId() {
        final Transaction t = Transaction.getCurrent();
        return (t != null) ? t.getId() : null;
    }
}
