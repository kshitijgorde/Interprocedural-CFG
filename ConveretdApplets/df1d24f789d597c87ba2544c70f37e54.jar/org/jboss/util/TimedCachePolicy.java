// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TimedCachePolicy extends TimerTask implements CachePolicy
{
    protected static Timer resolutionTimer;
    protected Map entryMap;
    protected int defaultLifetime;
    protected boolean threadSafe;
    protected long now;
    protected int resolution;
    protected ResolutionTimer theTimer;
    
    public TimedCachePolicy() {
        this(1800, false, 0);
    }
    
    public TimedCachePolicy(final int defaultLifetime) {
        this(defaultLifetime, false, 0);
    }
    
    public TimedCachePolicy(final int defaultLifetime, final boolean threadSafe, int resolution) {
        this.defaultLifetime = defaultLifetime;
        this.threadSafe = threadSafe;
        if (resolution <= 0) {
            resolution = 60;
        }
        this.resolution = resolution;
    }
    
    public void create() {
        if (this.threadSafe) {
            this.entryMap = Collections.synchronizedMap(new HashMap<Object, Object>());
        }
        else {
            this.entryMap = new HashMap();
        }
        this.now = System.currentTimeMillis();
    }
    
    public void start() {
        this.theTimer = new ResolutionTimer();
        TimedCachePolicy.resolutionTimer.scheduleAtFixedRate(this.theTimer, 0L, 1000 * this.resolution);
    }
    
    public void stop() {
        this.theTimer.cancel();
        this.flush();
    }
    
    public void destroy() {
        this.entryMap.clear();
    }
    
    public Object get(final Object key) {
        final TimedEntry entry = this.entryMap.get(key);
        if (entry == null) {
            return null;
        }
        if (!entry.isCurrent(this.now) && !entry.refresh()) {
            entry.destroy();
            this.entryMap.remove(key);
            return null;
        }
        final Object value = entry.getValue();
        return value;
    }
    
    public Object peek(final Object key) {
        final TimedEntry entry = this.entryMap.get(key);
        Object value = null;
        if (entry != null) {
            value = entry.getValue();
        }
        return value;
    }
    
    public void insert(final Object key, final Object value) {
        if (this.entryMap.containsKey(key)) {
            throw new IllegalStateException("Attempt to insert duplicate entry");
        }
        TimedEntry entry = null;
        if (!(value instanceof TimedEntry)) {
            entry = new DefaultTimedEntry(this.defaultLifetime, value);
        }
        else {
            entry = (TimedEntry)value;
        }
        entry.init(this.now);
        this.entryMap.put(key, entry);
    }
    
    public void remove(final Object key) {
        final TimedEntry entry = this.entryMap.remove(key);
        if (entry != null) {
            entry.destroy();
        }
    }
    
    public void flush() {
        Map tmpMap = null;
        synchronized (this) {
            tmpMap = this.entryMap;
            if (this.threadSafe) {
                this.entryMap = Collections.synchronizedMap(new HashMap<Object, Object>());
            }
            else {
                this.entryMap = new HashMap();
            }
        }
        for (final TimedEntry entry : tmpMap.values()) {
            entry.destroy();
        }
        tmpMap.clear();
    }
    
    public int size() {
        return this.entryMap.size();
    }
    
    public List getValidKeys() {
        final ArrayList validKeys = new ArrayList();
        synchronized (this.entryMap) {
            for (final Map.Entry entry : this.entryMap.entrySet()) {
                final TimedEntry value = entry.getValue();
                if (value.isCurrent(this.now)) {
                    validKeys.add(entry.getKey());
                }
            }
        }
        return validKeys;
    }
    
    public int getDefaultLifetime() {
        return this.defaultLifetime;
    }
    
    public synchronized void setDefaultLifetime(final int defaultLifetime) {
        this.defaultLifetime = defaultLifetime;
    }
    
    public int getResolution() {
        return this.resolution;
    }
    
    public synchronized void setResolution(int resolution) {
        if (resolution <= 0) {
            resolution = 60;
        }
        if (resolution != this.resolution) {
            this.resolution = resolution;
            this.theTimer.cancel();
            this.theTimer = new ResolutionTimer();
            TimedCachePolicy.resolutionTimer.scheduleAtFixedRate(this.theTimer, 0L, 1000 * resolution);
        }
    }
    
    public void run() {
        this.now = System.currentTimeMillis();
    }
    
    public long currentTimeMillis() {
        return this.now;
    }
    
    public TimedEntry peekEntry(final Object key) {
        final TimedEntry entry = this.entryMap.get(key);
        return entry;
    }
    
    static {
        TimedCachePolicy.resolutionTimer = new Timer(true);
    }
    
    static class DefaultTimedEntry implements TimedEntry
    {
        long expirationTime;
        Object value;
        
        DefaultTimedEntry(final long lifetime, final Object value) {
            this.expirationTime = 1000L * lifetime;
            this.value = value;
        }
        
        public void init(final long now) {
            this.expirationTime += now;
        }
        
        public boolean isCurrent(final long now) {
            return this.expirationTime > now;
        }
        
        public boolean refresh() {
            return false;
        }
        
        public void destroy() {
        }
        
        public Object getValue() {
            return this.value;
        }
    }
    
    private class ResolutionTimer extends TimerTask
    {
        public void run() {
            TimedCachePolicy.this.run();
        }
    }
    
    public interface TimedEntry
    {
        void init(final long p0);
        
        boolean isCurrent(final long p0);
        
        boolean refresh();
        
        void destroy();
        
        Object getValue();
    }
}
