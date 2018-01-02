// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.web;

import java.net.Socket;
import com.postx.util.logging.Level;
import java.net.UnknownHostException;
import java.io.IOException;
import java.util.Enumeration;
import com.postx.util.URLCode;
import java.net.InetAddress;
import java.io.File;
import com.postx.util.MIMETypes;
import com.postx.util.FileMap;
import java.net.ServerSocket;
import com.postx.util.logging.Logger;

public class WebServer implements Runnable
{
    public static final String Ident = "$Id: WebServer.java,v 1.2 2009/06/12 20:34:24 blm Exp $";
    private static final Logger log;
    private static final boolean logInfo;
    private static final boolean logFine;
    private static final String LOCAL_IP = "127.0.0.1";
    private static final int MAX_OUTSTANDING_REQUESTS_MULT = 2;
    private static final int ACCEPT_BACKLOG_MULT = 4;
    private ServerSocket serverSocket;
    private FileMap fileMap;
    private MIMETypes mimeTypes;
    private int successes;
    private int maxOutstandingRequests;
    
    public synchronized void incrementSuccesses() {
        ++this.successes;
    }
    
    public WebServer(final File file, final FileMap fileMap) throws IOException, UnknownHostException {
        this.successes = 0;
        int size = fileMap.size(6);
        if (size > 32) {
            size = 32;
        }
        this.maxOutstandingRequests = 2 * size;
        this.serverSocket = new ServerSocket(0, 4 * size, InetAddress.getByName("127.0.0.1"));
        this.fileMap = fileMap;
        this.mimeTypes = new MIMETypes(file);
        if (WebServer.logFine) {
            WebServer.log.fine("Maximum outstanding requests: " + this.maxOutstandingRequests);
            WebServer.log.fine("Accept backlog: " + 4 * size);
            WebServer.log.fine("The following files are available:");
            final Enumeration keys = fileMap.keys(6);
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                WebServer.log.fine("  " + URLCode.encode(s, 128, ";/?:@=&") + " -> " + fileMap.get(s, 6));
            }
        }
    }
    
    static {
        log = Logger.global;
        logInfo = WebServer.log.isLoggable(Level.INFO);
        logFine = WebServer.log.isLoggable(Level.FINE);
    }
    
    public String getAddr() {
        return "127.0.0.1" + ":" + this.serverSocket.getLocalPort();
    }
    
    public int getSuccesses() {
        return this.successes;
    }
    
    public void run() {
        if (this.serverSocket == null) {
            return;
        }
        int n = 0;
        Thread.currentThread();
        int activeCount = Thread.activeCount();
        while (true) {
            Thread.currentThread();
            int n2;
            if ((n2 = Thread.activeCount() - activeCount) < 0) {
                activeCount += n2;
                n2 = 0;
            }
            if (WebServer.logFine) {
                WebServer.log.fine(String.valueOf(n2) + " request" + ((n2 == 1) ? "" : "s") + " outstanding");
            }
            if (n2 < this.maxOutstandingRequests) {
                Socket accept = null;
                Label_0168: {
                    try {
                        if ((accept = this.serverSocket.accept()) != null) {
                            break Label_0168;
                        }
                    }
                    catch (Exception ex) {
                        if (WebServer.logFine) {
                            WebServer.log.log(Level.FINE, "Accept threw exception", ex);
                        }
                    }
                    break;
                }
                ++n;
                try {
                    try {
                        final InetAddress inetAddress = accept.getInetAddress();
                        if (inetAddress.getHostAddress().equals("127.0.0.1")) {
                            final Thread thread = new Thread(new RequestHandler(this, this.fileMap, this.mimeTypes, accept), "Request handler " + n);
                            accept = null;
                            thread.setDaemon(false);
                            thread.start();
                        }
                        else if (WebServer.logFine) {
                            WebServer.log.fine("Ignoring non-local request from " + inetAddress);
                        }
                    }
                    catch (Exception ex2) {
                        if (WebServer.logFine) {
                            WebServer.log.log(Level.FINE, "Unexpected exception", ex2);
                        }
                    }
                    continue;
                }
                finally {
                    if (accept != null) {
                        try {
                            accept.close();
                        }
                        catch (IOException ex3) {}
                    }
                }
                break;
            }
            WebServer.log.fine("Too many requests outstanding, yielding");
            Thread.currentThread();
            Thread.yield();
        }
        if (WebServer.logInfo) {
            WebServer.log.info("Successfully handled " + this.getSuccesses() + " of " + n + " request" + ((n == 1) ? "" : "s"));
        }
        try {
            this.serverSocket.close();
        }
        catch (IOException ex4) {}
        finally {
            this.serverSocket = null;
        }
    }
}
