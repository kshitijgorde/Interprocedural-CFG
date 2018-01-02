// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.deadlock;

import java.util.HashSet;
import java.util.HashMap;

public class DeadlockDetector
{
    public static DeadlockDetector singleton;
    protected HashMap waiting;
    
    public DeadlockDetector() {
        this.waiting = new HashMap();
    }
    
    public void deadlockDetection(final Object holder, final Resource resource) throws ApplicationDeadlockException {
        final HashSet set = new HashSet();
        set.add(holder);
        Object checkHolder = resource.getResourceHolder();
        synchronized (this.waiting) {
            this.addWaiting(holder, resource);
            while (checkHolder != null) {
                final Resource waitingFor = this.waiting.get(checkHolder);
                Object holding = null;
                if (waitingFor != null) {
                    holding = waitingFor.getResourceHolder();
                }
                if (holding != null) {
                    if (set.contains(holding)) {
                        final String msg = "Application deadlock detected, resource=" + resource + ", holder=" + holder + ", waitingResource=" + waitingFor + ", waitingResourceHolder=" + holding;
                        throw new ApplicationDeadlockException(msg, true);
                    }
                    set.add(holding);
                }
                checkHolder = holding;
            }
        }
    }
    
    public void addWaiting(final Object holder, final Resource resource) {
        synchronized (this.waiting) {
            this.waiting.put(holder, resource);
        }
    }
    
    public void removeWaiting(final Object holder) {
        synchronized (this.waiting) {
            this.waiting.remove(holder);
        }
    }
    
    static {
        DeadlockDetector.singleton = new DeadlockDetector();
    }
}
