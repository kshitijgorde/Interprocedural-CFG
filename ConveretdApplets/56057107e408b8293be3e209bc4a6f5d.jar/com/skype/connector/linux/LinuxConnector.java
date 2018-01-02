// 
// Decompiled by Procyon v0.5.30
// 

package com.skype.connector.linux;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.File;
import com.skype.connector.ConnectorException;
import com.skype.connector.Connector;

public final class LinuxConnector extends Connector
{
    private static LinuxConnector _instance;
    private SkypeFrameworkListener listener;
    
    static {
        LinuxConnector._instance = null;
    }
    
    public static synchronized Connector getInstance() {
        if (LinuxConnector._instance == null) {
            LinuxConnector._instance = new LinuxConnector();
        }
        return LinuxConnector._instance;
    }
    
    private LinuxConnector() {
        this.listener = new SkypeFrameworkListener() {
            public void notificationReceived(final String notificationString) {
                LinuxConnector.this.fireMessageReceived(notificationString);
            }
        };
    }
    
    public boolean isRunning() throws ConnectorException {
        SkypeFramework.init();
        return SkypeFramework.isRunning();
    }
    
    public String getInstalledPath() {
        final File application = new File("/usr/bin/skype");
        if (application.exists()) {
            return application.getAbsolutePath();
        }
        return null;
    }
    
    protected void initializeImpl() throws ConnectorException {
        SkypeFramework.init();
        SkypeFramework.addSkypeFrameworkListener(this.listener);
    }
    
    protected int connect(final int timeout) throws ConnectorException {
        if (!SkypeFramework.isRunning()) {
            this.setStatus(6);
            return this.getStatus();
        }
        try {
            final BlockingQueue queue = new LinkedBlockingQueue();
            final SkypeFrameworkListener initListener = new SkypeFrameworkListener() {
                public void notificationReceived(final String notification) {
                    if (!"OK".equals(notification) && !"CONNSTATUS OFFLINE".equals(notification)) {
                        if (!"ERROR 68".equals(notification)) {
                            return;
                        }
                    }
                    try {
                        queue.put(notification);
                    }
                    catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            };
            System.out.println("SKYPE: LinuxConnector connect()");
            this.setStatus(1);
            SkypeFramework.addSkypeFrameworkListener(initListener);
            System.out.println("SKYPE: send command NAME");
            SkypeFramework.sendCommand("NAME " + this.getApplicationName());
            System.out.println("SKYPE: wait for result of send command NAME");
            final String result = queue.take();
            SkypeFramework.removeSkypeFrameworkListener(initListener);
            if ("OK".equals(result)) {
                this.setStatus(2);
            }
            else if ("CONNSTATUS OFFLINE".equals(result)) {
                this.setStatus(4);
            }
            else if ("ERROR 68".equals(result)) {
                this.setStatus(3);
            }
            return this.getStatus();
        }
        catch (InterruptedException e) {
            throw new ConnectorException("Trying to connect was interrupted.", e);
        }
    }
    
    protected void sendCommand(final String command) {
        SkypeFramework.sendCommand(command);
    }
    
    protected void disposeImpl() {
        SkypeFramework.removeSkypeFrameworkListener(this.listener);
        SkypeFramework.dispose();
    }
}
