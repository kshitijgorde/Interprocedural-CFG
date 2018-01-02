// 
// Decompiled by Procyon v0.5.30
// 

package misc;

import proxy.DirectProxy;
import logging.LogType;
import logging.Log;
import logging.LogHolder;
import jap.JAPDebug;
import java.net.ServerSocket;

public final class JAPHTTPProxy
{
    boolean runFlag;
    int port;
    ServerSocket server;
    
    public JAPHTTPProxy(final int port) {
        this.port = 4008;
        this.port = port;
        LogHolder.setLogInstance(JAPDebug.getInstance());
        JAPDebug.getInstance().setLogType(LogType.NET + LogType.GUI + LogType.THREAD + LogType.MISC);
        JAPDebug.getInstance().setLogLevel(7);
    }
    
    public void startService() {
        this.server = null;
        this.runFlag = true;
        System.out.println("Service on port " + this.port + " started.");
        try {
            this.server = new ServerSocket(this.port);
            new DirectProxy(this.server).startService();
        }
        catch (Exception ex) {
            try {
                this.server.close();
            }
            catch (Exception ex2) {}
            LogHolder.log(2, LogType.NET, "Exception: " + ex);
        }
    }
    
    public void stopService() {
        this.runFlag = false;
        try {
            this.server.close();
        }
        catch (Exception ex) {}
    }
    
    public static void help() {
        System.out.println("HTTPProxy");
        System.out.println(" Options: -debug -port <port>");
    }
    
    public static void main(final String[] array) {
        int int1 = 4001;
        boolean b = false;
        if (array.length == 0) {
            b = true;
        }
        try {
            for (int i = 0; i < array.length; ++i) {
                if (array[i].equalsIgnoreCase("-port")) {
                    int1 = Integer.parseInt(array[i + 1]);
                    ++i;
                }
                else {
                    b = true;
                }
            }
        }
        catch (Exception ex) {
            b = true;
        }
        if (b) {
            help();
        }
        new JAPHTTPProxy(int1).startService();
    }
}
