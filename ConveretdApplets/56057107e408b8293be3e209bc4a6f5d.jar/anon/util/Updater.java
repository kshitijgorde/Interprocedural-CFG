// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import java.util.Observable;
import logging.LogHolder;
import logging.LogType;
import java.util.Observer;

public abstract class Updater implements Observer
{
    private static final long MIN_WAITING_TIME_MS = 20000L;
    private IUpdateInterval m_updateInterval;
    private Thread m_updateThread;
    private boolean m_bAutoUpdateChanged;
    private boolean m_bInitialRun;
    private boolean m_interrupted;
    private boolean m_bUpdating;
    private Object UPDATE_SYNC;
    private ObservableInfo m_observable;
    
    public Updater(final IUpdateInterval updateInterval, final ObservableInfo observable) {
        this.m_bAutoUpdateChanged = false;
        this.m_bInitialRun = true;
        this.m_interrupted = false;
        this.m_bUpdating = false;
        this.UPDATE_SYNC = new Object();
        if (updateInterval == null) {
            throw new IllegalArgumentException("No update interval specified!");
        }
        if (observable == null) {
            throw new IllegalArgumentException("No ObservableInfo specified!");
        }
        this.m_observable = observable;
        this.m_updateInterval = updateInterval;
        this.init();
    }
    
    private final void init() {
        this.m_observable.getObservable().addObserver(this);
        (this.m_updateThread = new Thread(new Runnable() {
            public void run() {
                long n = System.currentTimeMillis();
                LogHolder.log(6, LogType.THREAD, Updater.this.getUpdatedClassName() + "update thread started.");
                while (!Thread.currentThread().isInterrupted() && !Updater.this.m_interrupted) {
                    synchronized (Thread.currentThread()) {
                        Updater.this.m_bAutoUpdateChanged = true;
                        while (Updater.this.m_bAutoUpdateChanged) {
                            Updater.this.m_bAutoUpdateChanged = false;
                            try {
                                Thread.currentThread().notify();
                                if (Updater.this.m_observable.isUpdateDisabled() || Updater.this.m_bInitialRun) {
                                    Thread.currentThread().wait();
                                }
                                else {
                                    final long max = Math.max(Updater.this.m_updateInterval.getUpdateInterval() - (System.currentTimeMillis() - n), 20000L);
                                    LogHolder.log(5, LogType.THREAD, "Update waiting time for " + Updater.this.getUpdatedClass().getName() + ": " + max);
                                    Thread.currentThread().wait(max);
                                }
                            }
                            catch (InterruptedException ex) {
                                Thread.currentThread().notifyAll();
                                break;
                            }
                            if (Thread.currentThread().isInterrupted()) {
                                Thread.currentThread().notifyAll();
                                break;
                            }
                            if (Updater.this.m_interrupted) {
                                break;
                            }
                        }
                    }
                    if (!Thread.currentThread().isInterrupted() && !Updater.this.m_interrupted && !Updater.this.isUpdatePaused()) {
                        LogHolder.log(6, LogType.THREAD, "Updating " + Updater.this.getUpdatedClassName() + "list.");
                        n = System.currentTimeMillis();
                        Updater.this.updateInternal();
                    }
                }
                LogHolder.log(6, LogType.THREAD, Updater.this.getUpdatedClassName() + "update thread stopped.");
            }
        }, this.getUpdatedClassName() + "Update Thread")).setPriority(1);
        this.m_updateThread.setDaemon(true);
        this.m_updateThread.start();
    }
    
    protected ObservableInfo getObservableInfo() {
        return this.m_observable;
    }
    
    public void update(final Observable observable, final Object o) {
        if (!(o instanceof Integer) || !((Integer)o).equals(this.m_observable.getUpdateChanged())) {
            return;
        }
        if (!this.m_observable.isUpdateDisabled()) {
            new Thread(new Runnable() {
                public void run() {
                    if (Updater.this.m_observable.updateImmediately()) {
                        Updater.this.update(false);
                    }
                    else {
                        Updater.this.start(false);
                    }
                }
            }).start();
        }
    }
    
    public final void start(final boolean b) {
        synchronized (this.UPDATE_SYNC) {
            if (this.m_bUpdating) {
                return;
            }
            this.m_bUpdating = true;
        }
        synchronized (this) {
            synchronized (this.m_updateThread) {
                this.m_bAutoUpdateChanged = true;
                this.m_bInitialRun = false;
                this.m_updateThread.notifyAll();
                if (b) {
                    try {
                        this.m_updateThread.wait();
                    }
                    catch (InterruptedException ex) {}
                }
            }
        }
        synchronized (this.UPDATE_SYNC) {
            this.m_bUpdating = false;
        }
    }
    
    public final boolean update() {
        return this.update(true);
    }
    
    public final void updateAsync() {
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                Updater.this.update(false);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    
    private final boolean update(final boolean b) {
        if (this.m_bInitialRun) {
            this.start(true);
        }
        synchronized (this) {
            synchronized (this.m_updateThread) {
                this.m_bAutoUpdateChanged = false;
                this.m_updateThread.notifyAll();
                if (b) {
                    try {
                        this.m_updateThread.wait();
                    }
                    catch (InterruptedException ex) {
                        return false;
                    }
                    return this.wasUpdateSuccessful();
                }
                return true;
            }
        }
    }
    
    public final void stop() {
        this.m_observable.getObservable().deleteObserver(this);
        if (this.m_updateThread == null) {
            return;
        }
        while (this.m_updateThread.isAlive()) {
            this.m_updateThread.interrupt();
            synchronized (this.m_updateThread) {
                this.m_bAutoUpdateChanged = false;
                this.m_bInitialRun = false;
                this.m_interrupted = true;
                this.m_updateThread.notifyAll();
                this.m_updateThread.interrupt();
            }
            try {
                this.m_updateThread.join(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public final IUpdateInterval getUpdateInterval() {
        return this.m_updateInterval;
    }
    
    public abstract Class getUpdatedClass();
    
    protected abstract boolean wasUpdateSuccessful();
    
    public abstract boolean isFirstUpdateDone();
    
    protected abstract void updateInternal();
    
    protected boolean isUpdatePaused() {
        return false;
    }
    
    protected final String getUpdatedClassName() {
        return ClassUtil.getShortClassName(this.getUpdatedClass()) + " ";
    }
    
    public abstract static class ObservableInfo
    {
        private Observable m_observable;
        
        public ObservableInfo(final Observable observable) {
            if (observable == null) {
                throw new IllegalArgumentException("No Observable specified!");
            }
            this.m_observable = observable;
        }
        
        public void notifyAdditionalObserversOnUpdate(final Class clazz) {
        }
        
        public boolean updateImmediately() {
            return false;
        }
        
        public final Observable getObservable() {
            return this.m_observable;
        }
        
        public abstract Integer getUpdateChanged();
        
        public abstract boolean isUpdateDisabled();
    }
    
    protected static class DynamicUpdateInterval implements IUpdateInterval
    {
        private long m_updateInterval;
        
        public DynamicUpdateInterval(final long updateInterval) {
            this.setUpdateInterval(updateInterval);
        }
        
        public void setUpdateInterval(final long updateInterval) {
            this.m_updateInterval = updateInterval;
        }
        
        public long getUpdateInterval() {
            return this.m_updateInterval;
        }
    }
    
    protected static final class ConstantUpdateInterval implements IUpdateInterval
    {
        private long m_updateInterval;
        
        public ConstantUpdateInterval(final long updateInterval) {
            this.m_updateInterval = updateInterval;
        }
        
        public long getUpdateInterval() {
            return this.m_updateInterval;
        }
    }
    
    protected interface IUpdateInterval
    {
        long getUpdateInterval();
    }
}
