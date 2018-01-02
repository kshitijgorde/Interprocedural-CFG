// 
// Decompiled by Procyon v0.5.30
// 

package com.skype.connector.linux;

import java.util.Enumeration;
import com.skype.connector.LoadLibraryException;
import com.skype.connector.ConnectorUtils;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

final class SkypeFramework
{
    private static Object initializedFieldMutex;
    private static boolean initialized;
    private static CountDownLatch eventLoopFinishedLatch;
    private static Thread eventLoop;
    private static final Vector listeners;
    
    static {
        SkypeFramework.initializedFieldMutex = new Object();
        SkypeFramework.initialized = false;
        listeners = new Vector();
    }
    
    static void init() throws LoadLibraryException {
        synchronized (SkypeFramework.initializedFieldMutex) {
            if (!SkypeFramework.initialized) {
                ConnectorUtils.loadLibrary("skype");
                setup0();
                SkypeFramework.eventLoopFinishedLatch = new CountDownLatch(1);
                (SkypeFramework.eventLoop = new Thread(new Runnable() {
                    public void run() {
                        runEventLoop0();
                        SkypeFramework.eventLoopFinishedLatch.countDown();
                    }
                }, "Skype4Java Event Loop")).setDaemon(true);
                SkypeFramework.eventLoop.start();
                SkypeFramework.initialized = true;
            }
        }
        // monitorexit(SkypeFramework.initializedFieldMutex)
    }
    
    private static native void setup0();
    
    private static native void runEventLoop0();
    
    static void addSkypeFrameworkListener(final SkypeFrameworkListener listener) {
        SkypeFramework.listeners.add(listener);
    }
    
    static void removeSkypeFrameworkListener(final SkypeFrameworkListener listener) {
        SkypeFramework.listeners.remove(listener);
    }
    
    static boolean isRunning() {
        return isRunning0();
    }
    
    private static native boolean isRunning0();
    
    static void sendCommand(final String commandString) {
        sendCommand0(commandString);
    }
    
    private static native void sendCommand0(final String p0);
    
    static void fireNotificationReceived(final String notificationString) {
        final Enumeration en = SkypeFramework.listeners.elements();
        while (en.hasMoreElements()) {
            en.nextElement().notificationReceived(notificationString);
        }
    }
    
    static void dispose() {
        synchronized (SkypeFramework.initializedFieldMutex) {
            if (SkypeFramework.initialized) {
                SkypeFramework.listeners.clear();
                stopEventLoop0();
                try {
                    SkypeFramework.eventLoopFinishedLatch.await();
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                closeDisplay0();
                SkypeFramework.initialized = false;
            }
        }
        // monitorexit(SkypeFramework.initializedFieldMutex)
    }
    
    private static native void stopEventLoop0();
    
    private static native void closeDisplay0();
}
