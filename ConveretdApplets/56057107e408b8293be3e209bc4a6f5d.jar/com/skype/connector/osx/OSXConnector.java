// 
// Decompiled by Procyon v0.5.30
// 

package com.skype.connector.osx;

import java.util.concurrent.CountDownLatch;
import java.io.File;
import com.skype.connector.ConnectorException;
import com.skype.connector.Connector;

public final class OSXConnector extends Connector
{
    private static OSXConnector _instance;
    private static boolean _skypeEventLoopEnabled;
    private SkypeFrameworkListener listener;
    
    static {
        OSXConnector._instance = null;
        OSXConnector._skypeEventLoopEnabled = true;
    }
    
    public static synchronized Connector getInstance() {
        if (OSXConnector._instance == null) {
            OSXConnector._instance = new OSXConnector();
        }
        return OSXConnector._instance;
    }
    
    public static void disableSkypeEventLoop() {
        OSXConnector._skypeEventLoopEnabled = false;
    }
    
    private OSXConnector() {
        this.listener = new AbstractSkypeFrameworkListener() {
            public void notificationReceived(final String notificationString) {
                OSXConnector.this.fireMessageReceived(notificationString);
            }
            
            public void becameUnavailable() {
                OSXConnector.this.setStatus(4);
            }
            
            public void becameAvailable() {
            }
        };
    }
    
    public boolean isRunning() throws ConnectorException {
        ((OSXConnector)getInstance()).initialize();
        return SkypeFramework.isRunning();
    }
    
    public String getInstalledPath() {
        final File application = new File("/Applications/Skype.app/Contents/MacOS/Skype");
        if (application.exists()) {
            return application.getAbsolutePath();
        }
        return null;
    }
    
    protected void initializeImpl() throws ConnectorException {
        SkypeFramework.init(this.getApplicationName());
        SkypeFramework.addSkypeFrameworkListener(this.listener);
        if (OSXConnector._skypeEventLoopEnabled) {
            final CountDownLatch latch = new CountDownLatch(1);
            final Thread eventLoop = new Thread("SkypeEventLoop") {
                public void run() {
                    latch.countDown();
                    SkypeFramework.runApplicationEventLoop();
                }
            };
            eventLoop.setDaemon(true);
            eventLoop.start();
            try {
                latch.await();
            }
            catch (InterruptedException e) {
                SkypeFramework.quitApplicationEventLoop();
                throw new ConnectorException("The connector initialization was interrupted.", e);
            }
        }
    }
    
    protected int connect(final int timeout) throws ConnectorException {
        if (!SkypeFramework.isRunning()) {
            this.setStatus(6);
            return this.getStatus();
        }
        try {
            final CountDownLatch latch = new CountDownLatch(1);
            final SkypeFrameworkListener listener = new AbstractSkypeFrameworkListener() {
                public void attachResponse(final int attachResponseCode) {
                    SkypeFramework.removeSkypeFrameworkListener(this);
                    switch (attachResponseCode) {
                        case 0: {
                            OSXConnector.this.setStatus(3);
                            latch.countDown();
                            break;
                        }
                        case 1: {
                            OSXConnector.this.setStatus(2);
                            latch.countDown();
                            break;
                        }
                        default: {
                            throw new IllegalStateException("not supported attachResponseCode");
                        }
                    }
                }
            };
            this.setStatus(1);
            SkypeFramework.addSkypeFrameworkListener(listener);
            SkypeFramework.connect();
            latch.await();
            return this.getStatus();
        }
        catch (InterruptedException e) {
            throw new ConnectorException("Trying to connect was interrupted.", e);
        }
    }
    
    protected void sendProtocol() throws ConnectorException {
    }
    
    protected void sendCommand(final String command) {
        final String result = SkypeFramework.sendCommand(command);
        if (result != null) {
            this.fireMessageReceived(result);
        }
    }
    
    protected void disposeImpl() {
        SkypeFramework.removeSkypeFrameworkListener(this.listener);
        SkypeFramework.dispose();
        if (OSXConnector._skypeEventLoopEnabled) {
            SkypeFramework.quitApplicationEventLoop();
        }
    }
}
