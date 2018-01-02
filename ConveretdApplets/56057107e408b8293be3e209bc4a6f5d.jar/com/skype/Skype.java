// 
// Decompiled by Procyon v0.5.30
// 

package com.skype;

import java.io.File;
import com.skype.connector.ConnectorException;
import com.skype.connector.Connector;

public final class Skype
{
    public static final String LIBRARY_VERSION = "1.0.0.0";
    private static Object userThreadFieldMutex;
    private static Thread userThread;
    private static SkypeExceptionHandler defaultExceptionHandler;
    private static SkypeExceptionHandler exceptionHandler;
    
    static {
        Skype.userThreadFieldMutex = new Object();
        Skype.defaultExceptionHandler = new SkypeExceptionHandler() {
            public void uncaughtExceptionHappened(final Throwable e) {
                e.printStackTrace();
            }
        };
        Skype.exceptionHandler = Skype.defaultExceptionHandler;
    }
    
    public static void setDeamon(final boolean on) {
        setDaemon(on);
    }
    
    public static void setDaemon(final boolean on) {
        synchronized (Skype.userThreadFieldMutex) {
            if (!on && Skype.userThread == null) {
                (Skype.userThread = new Thread() {
                    public void run() {
                        final Object wait = new Object();
                        synchronized (wait) {
                            try {
                                wait.wait();
                            }
                            catch (InterruptedException ex) {}
                        }
                        // monitorexit(wait)
                    }
                }).start();
            }
            else if (on && Skype.userThread != null) {
                Skype.userThread.interrupt();
                Skype.userThread = null;
            }
        }
        // monitorexit(Skype.userThreadFieldMutex)
    }
    
    public static void setDebug(final boolean on) throws SkypeException {
        try {
            System.out.println("SKYPE: Try to get Instance");
            final Connector con = Connector.getInstance();
            System.out.println("SKYPE: got soemthing");
            if (con != null) {
                System.out.println("SKYPE: start debug on");
                con.setDebug(on);
                System.out.println("SKYPE: end debug on");
            }
        }
        catch (ConnectorException e) {
            System.out.println("SKYPE: exception in etDebugOn");
            e.printStackTrace();
            Utils.convertToSkypeException(e);
        }
    }
    
    public static String getVersion() throws SkypeException {
        return Utils.getProperty("SKYPEVERSION");
    }
    
    public static boolean isInstalled() {
        final String path = getInstalledPath();
        return path != null && new File(path).exists();
    }
    
    public static String getInstalledPath() {
        return Connector.getInstance().getInstalledPath();
    }
    
    public static boolean isRunning() throws SkypeException {
        try {
            return Connector.getInstance().isRunning();
        }
        catch (ConnectorException e) {
            Utils.convertToSkypeException(e);
            return false;
        }
    }
    
    public static Application addApplication(final String name) throws SkypeException {
        Utils.checkNotNull("name", name);
        return Application.getInstance(name);
    }
    
    private static String convertNullToDefaultDevice(final String deviceName) {
        if (deviceName == null) {
            return "";
        }
        return deviceName;
    }
    
    public static void setSkypeExceptionHandler(SkypeExceptionHandler handler) {
        if (handler == null) {
            handler = Skype.defaultExceptionHandler;
        }
        Skype.exceptionHandler = handler;
    }
    
    static void handleUncaughtException(final Throwable e) {
        Skype.exceptionHandler.uncaughtExceptionHappened(e);
    }
}
