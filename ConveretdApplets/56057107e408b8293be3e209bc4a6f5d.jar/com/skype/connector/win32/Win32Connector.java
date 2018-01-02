// 
// Decompiled by Procyon v0.5.30
// 

package com.skype.connector.win32;

import com.skype.connector.ConnectorException;
import java.io.File;
import com.skype.connector.ConnectorUtils;
import com.skype.connector.Connector;

public final class Win32Connector extends Connector
{
    private static final int ATTACH_SUCCESS = 0;
    private static final int ATTACH_PENDING_AUTHORIZATION = 1;
    private static final int ATTACH_REFUSED = 2;
    private static final int ATTACH_NOT_AVAILABLE = 3;
    private static final int ATTACH_API_AVAILABLE = 32769;
    private static final String LIBFILENAME = "JNIConnector.dll";
    private static Win32Connector instance;
    private Thread eventDispatcher;
    
    static {
        Win32Connector.instance = null;
    }
    
    public static synchronized Connector getInstance() {
        if (Win32Connector.instance == null) {
            Win32Connector.instance = new Win32Connector();
        }
        return Win32Connector.instance;
    }
    
    private Win32Connector() {
        this.eventDispatcher = null;
    }
    
    public String getInstalledPath() {
        return this.jni_getInstalledPath();
    }
    
    protected void initializeImpl() {
        try {
            System.loadLibrary("JNIConnector");
        }
        catch (Throwable e) {
            if (!ConnectorUtils.checkLibraryInPath("JNIConnector.dll")) {
                ConnectorUtils.extractFromJarToTemp("JNIConnector.dll");
                String tmpDir = System.getProperty("java.io.tmpdir");
                if (!tmpDir.endsWith(new StringBuffer().append(File.separatorChar).toString())) {
                    tmpDir = String.valueOf(tmpDir) + File.separatorChar;
                }
                System.load(String.valueOf(tmpDir) + "JNIConnector.dll");
            }
        }
        this.jni_init();
        (this.eventDispatcher = new Thread(new Runnable() {
            public void run() {
                Win32Connector.this.jni_windowProc();
            }
        }, "SkypeBridge WindowProc Thread")).setDaemon(true);
        this.eventDispatcher.start();
    }
    
    protected int connect(final int timeout) throws ConnectorException {
        try {
            int status;
            while (true) {
                this.jni_connect();
                final long start = System.currentTimeMillis();
                if (timeout <= System.currentTimeMillis() - start) {
                    this.setStatus(6);
                }
                status = this.getStatus();
                if (status != 1 && status != 6) {
                    break;
                }
                Thread.sleep(1000L);
            }
            return status;
        }
        catch (InterruptedException e) {
            throw new ConnectorException("Trying to connect was interrupted.", e);
        }
    }
    
    protected void sendApplicationName(final String applicationName) throws ConnectorException {
        final String command = "NAME " + applicationName;
        this.execute(command, new String[] { command }, false);
    }
    
    public void jni_onAttach(final int status) {
        switch (status) {
            case 1: {
                this.setStatus(1);
                break;
            }
            case 0: {
                this.setStatus(2);
                break;
            }
            case 2: {
                this.setStatus(3);
                break;
            }
            case 3: {
                this.setStatus(4);
                break;
            }
            case 32769: {
                this.setStatus(5);
                break;
            }
            default: {
                this.setStatus(6);
                break;
            }
        }
    }
    
    public void jni_onSkypeMessage(final String message) {
        this.fireMessageReceived(message);
    }
    
    protected void disposeImpl() {
        throw new UnsupportedOperationException("WindowsConnector#disposeImpl() is not implemented yet.");
    }
    
    protected void sendCommand(final String command) {
        this.jni_sendMessage(command);
    }
    
    private native void jni_init();
    
    private native void jni_windowProc();
    
    private native void jni_sendMessage(final String p0);
    
    private native void jni_connect();
    
    private native String jni_getInstalledPath();
}
