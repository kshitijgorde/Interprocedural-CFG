// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.threading;

import java.util.concurrent.ThreadFactory;

public class DaemonThreadFactory implements ThreadFactory
{
    public Thread newThread(final Runnable runnable) {
        final Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        return thread;
    }
}
