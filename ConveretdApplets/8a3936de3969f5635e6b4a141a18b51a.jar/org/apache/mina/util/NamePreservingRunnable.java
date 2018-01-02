// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.util;

import org.slf4j.LoggerFactory;
import com.masystem.beergame.debug.Log;
import org.slf4j.Logger;

public class NamePreservingRunnable implements Runnable
{
    private static final Logger LOGGER;
    private final String newName;
    private final Runnable runnable;
    
    public NamePreservingRunnable(final Runnable runnable, final String newName) {
        this.runnable = runnable;
        this.newName = newName;
    }
    
    @Override
    public void run() {
        final Thread currentThread;
        final String name = (currentThread = Thread.currentThread()).getName();
        if (this.newName != null) {
            setName(currentThread, this.newName);
        }
        try {
            this.runnable.run();
        }
        finally {
            setName(currentThread, name);
        }
    }
    
    private static void setName(final Thread thread, final String name) {
        try {
            thread.setName(name);
        }
        catch (SecurityException ex) {
            final Logger logger = NamePreservingRunnable.LOGGER;
            final Logger logger2 = NamePreservingRunnable.LOGGER;
            Log.warn("Failed to set the thread name.", ex);
        }
    }
    
    static {
        LOGGER = LoggerFactory.getLogger$4ecaad6a();
    }
}
