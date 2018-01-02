// 
// Decompiled by Procyon v0.5.30
// 

package com.skype.connector.osx;

import java.util.Enumeration;
import com.skype.connector.LoadLibraryException;
import com.skype.connector.ConnectorUtils;
import java.util.Vector;

final class SkypeFramework
{
    private static Object initializedFieldMutex;
    private static boolean initialized;
    private static final Vector listeners;
    private static Object sendCommandMutex;
    private static Object notificationReceivedMutex;
    
    static {
        SkypeFramework.initializedFieldMutex = new Object();
        SkypeFramework.initialized = false;
        listeners = new Vector();
        SkypeFramework.sendCommandMutex = new Object();
        SkypeFramework.notificationReceivedMutex = new Object();
    }
    
    static void init(final String applicationName) throws LoadLibraryException {
        ConnectorUtils.checkNotNull("applicationName", applicationName);
        synchronized (SkypeFramework.initializedFieldMutex) {
            if (!SkypeFramework.initialized) {
                ConnectorUtils.loadLibrary("skype");
                setup0(applicationName);
                SkypeFramework.initialized = true;
            }
        }
        // monitorexit(SkypeFramework.initializedFieldMutex)
    }
    
    private static native void setup0(final String p0);
    
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
    
    static boolean isAvailable() {
        return isAvailable0();
    }
    
    private static native boolean isAvailable0();
    
    static void fireBecameAvailable() {
        final Enumeration en = SkypeFramework.listeners.elements();
        while (en.hasMoreElements()) {
            en.nextElement().becameAvailable();
        }
    }
    
    static void fireBecameUnavailable() {
        final Enumeration en = SkypeFramework.listeners.elements();
        while (en.hasMoreElements()) {
            en.nextElement().becameUnavailable();
        }
    }
    
    static void connect() {
        connect0();
    }
    
    private static native void connect0();
    
    static void fireAttachResponse(final int attachResponseCode) {
        final Enumeration en = SkypeFramework.listeners.elements();
        while (en.hasMoreElements()) {
            en.nextElement().attachResponse(attachResponseCode);
        }
    }
    
    static String sendCommand(final String commandString) {
        synchronized (SkypeFramework.sendCommandMutex) {
            // monitorexit(SkypeFramework.sendCommandMutex)
            return sendCommand0(commandString);
        }
    }
    
    private static native String sendCommand0(final String p0);
    
    static void fireNotificationReceived(final String notificationString) {
        final Enumeration en = SkypeFramework.listeners.elements();
        while (en.hasMoreElements()) {
            en.nextElement().notificationReceived(notificationString);
        }
    }
    
    static void dispose() {
        synchronized (SkypeFramework.initializedFieldMutex) {
            if (SkypeFramework.initialized) {
                dispose0();
                SkypeFramework.listeners.clear();
                SkypeFramework.initialized = false;
            }
        }
        // monitorexit(SkypeFramework.initializedFieldMutex)
    }
    
    private static native void dispose0();
    
    static int runCurrentEventLoop(final double inTimeout) {
        return runCurrentEventLoop0(inTimeout);
    }
    
    private static native int runCurrentEventLoop0(final double p0);
    
    static void runApplicationEventLoop() {
        runApplicationEventLoop0();
    }
    
    private static native void runApplicationEventLoop0();
    
    static void quitApplicationEventLoop() {
        quitApplicationEventLoop0();
    }
    
    private static native void quitApplicationEventLoop0();
}
