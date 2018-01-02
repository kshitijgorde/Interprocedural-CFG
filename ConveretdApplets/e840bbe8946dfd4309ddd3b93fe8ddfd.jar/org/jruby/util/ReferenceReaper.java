// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public final class ReferenceReaper
{
    public final ReferenceQueue referenceQueue;
    private final Thread reaperThread;
    private final Runnable reaper;
    
    private ReferenceReaper() {
        this.referenceQueue = new ReferenceQueue();
        this.reaper = new Runnable() {
            public void run() {
                while (true) {
                    try {
                        while (true) {
                            final Reference r = ReferenceReaper.this.referenceQueue.remove();
                            try {
                                if (!(r instanceof Runnable)) {
                                    continue;
                                }
                                ((Runnable)r).run();
                            }
                            finally {
                                r.clear();
                            }
                        }
                    }
                    catch (InterruptedException ex) {}
                    catch (Throwable t) {
                        continue;
                    }
                    break;
                }
            }
        };
        (this.reaperThread = new Thread(this.reaper, "ReferenceReaper")).setDaemon(true);
        this.reaperThread.start();
    }
    
    public static final ReferenceReaper getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    private static final class SingletonHolder
    {
        private static final ReferenceReaper INSTANCE;
        
        static {
            INSTANCE = new ReferenceReaper(null);
        }
    }
}
