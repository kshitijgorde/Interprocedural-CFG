// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm;

import java.util.List;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.GregorianCalendar;
import java.util.Date;
import com.stonewall.cornerstone.remoteServer.CommManager;
import com.stonewall.cornerstone.dsp.command.Result;
import com.stonewall.cornerstone.entity.Device;
import java.util.Iterator;
import java.util.Collection;
import java.lang.reflect.Method;
import com.stonewall.cornerstone.remoteServer.Correlation;
import com.stonewall.cornerstone.dsp.command.DeviceOperation;
import com.stonewall.cornerstone.component.ComponentServer;
import org.xmodel.log.Log;
import java.util.concurrent.locks.ReentrantLock;
import com.stonewall.cornerstone.thread.ThreadPool;
import com.stonewall.cornerstone.thread.ThreadDispatch;
import java.util.Hashtable;
import com.stonewall.cornerstone.component.Component;

public class DeviceRouter implements Component
{
    public static final String componentName = "device.router";
    private Hashtable<String, Entry> entries;
    private ThreadDispatch dispatch;
    private ThreadPool threadPool;
    final ReentrantLock lock;
    private HandlerIdleMonitor handlerIdleMonitor;
    static final Log log;
    
    static {
        log = Log.getLog(DeviceRouter.class);
    }
    
    public DeviceRouter() {
        this.lock = new ReentrantLock();
    }
    
    @Override
    public void init(final ComponentServer server) throws Exception {
        this.entries = new Hashtable<String, Entry>();
        this.handlerIdleMonitor = new HandlerIdleMonitor(this);
        this.threadPool = new ThreadPool("DeviceOperation", 100);
        final Method method = Entry.class.getMethod("execute", DeviceOperation.class, Correlation.class);
        this.dispatch = new ThreadDispatch(this.threadPool, method);
    }
    
    @Override
    public void shutdown() {
        this.handlerIdleMonitor.cancel();
        final Collection<Entry> l = this.entries.values();
        this.entries = null;
        for (final Entry entry : l) {
            entry.shutdown();
        }
    }
    
    @Override
    public void trace() {
        DeviceRouter.log.warn("Not-Implemented");
    }
    
    public void addDevice(final Device device) throws Exception {
        final DeviceHandler handler = new DeviceHandler(device);
        final Entry entry = new Entry(handler);
        this.entries.put(device.getId(), entry);
    }
    
    public boolean hasDevice(final Device device) {
        return this.entries.get(device.getId()) != null;
    }
    
    public Device getDevice(final Device device) {
        final Entry entry = this.entries.get(device.getId());
        if (entry == null) {
            return null;
        }
        return entry.handler.getDevice();
    }
    
    public void removeDevice(final String deviceId) {
        if (deviceId == null) {
            return;
        }
        final Entry entry = this.entries.remove(deviceId);
        if (entry == null) {
            return;
        }
        entry.handler.shutdown();
    }
    
    public void executeOperation(final String deviceId, final DeviceOperation operation, final Correlation c) {
        final Entry entry = this.entries.get(deviceId);
        if (entry != null) {
            this.lock.lock();
            try {
                this.dispatch.bind(deviceId);
                this.dispatch.submit(entry, operation, c);
            }
            finally {
                this.lock.unlock();
            }
            this.lock.unlock();
        }
        else {
            DeviceRouter.log.info("DeviceRouter : cannot process DeviceOperation for Device " + deviceId);
            try {
                operation.setStatus(Result.Status.failed);
                final CommManager cm = (CommManager)ComponentServer.getInstance().getComponent(CommManager.class);
                cm.respond(c, operation);
            }
            catch (Exception e) {
                DeviceRouter.log.error(e);
            }
        }
    }
    
    private long deviceIdleTimeout() {
        return 0L;
    }
    
    @Override
    public String toString() {
        if (this.entries != null) {
            return "DeviceRouter entries: " + this.entries.toString();
        }
        return "Device Router";
    }
    
    public class Entry
    {
        private DeviceHandler handler;
        private Date timestamp;
        private boolean shutdown;
        final ReentrantLock entityLock;
        
        protected Entry(final DeviceHandler handler) {
            this.shutdown = true;
            this.entityLock = new ReentrantLock();
            this.handler = handler;
        }
        
        public void execute(final DeviceOperation operation, final Correlation c) {
            this.entityLock.lock();
            try {
                this.shutdown = false;
                this.timestamp = new GregorianCalendar().getTime();
                this.handler.executeOperation(operation, c);
            }
            finally {
                this.entityLock.unlock();
            }
            this.entityLock.unlock();
        }
        
        protected boolean isIdle() {
            this.entityLock.lock();
            try {
                if (this.shutdown) {
                    return false;
                }
                final GregorianCalendar d1 = new GregorianCalendar();
                d1.setTime(this.timestamp);
                final GregorianCalendar d2 = new GregorianCalendar();
                final long dif = d2.getTimeInMillis() - d1.getTimeInMillis();
                return dif / 1000L > 5L;
            }
            finally {
                this.entityLock.unlock();
            }
        }
        
        protected void shutdown() {
            this.entityLock.lock();
            try {
                this.shutdown = true;
                this.handler.shutdown();
            }
            finally {
                this.entityLock.unlock();
            }
            this.entityLock.unlock();
        }
    }
    
    class HandlerIdleMonitor extends TimerTask
    {
        protected HandlerIdleMonitor(final DeviceRouter router) {
            final long timeout = router.deviceIdleTimeout();
            if (timeout != 0L) {
                final Timer timer = new Timer("DeviceHandlerMonitor");
                timer.schedule(this, timeout, timeout);
            }
        }
        
        @Override
        public void run() {
            DeviceRouter.log.info("In timer task" + DeviceRouter.this.entries);
            final List<Entry> l = new ArrayList<Entry>(DeviceRouter.this.entries.values());
            for (final Entry entry : l) {
                if (entry.isIdle()) {
                    entry.shutdown();
                }
            }
        }
    }
}
