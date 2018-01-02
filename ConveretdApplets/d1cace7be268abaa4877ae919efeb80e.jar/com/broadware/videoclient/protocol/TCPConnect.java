// 
// Decompiled by Procyon v0.5.30
// 

package com.broadware.videoclient.protocol;

import java.net.Socket;

public class TCPConnect extends Thread
{
    private String host;
    private int port;
    private Socket socket;
    
    public TCPConnect(final String host, final int port) {
        this.host = host;
        this.port = port;
    }
    
    public void run() {
        try {
            this.socket = new Socket(this.host, this.port);
        }
        catch (Exception e) {
            if (this.socket != null) {
                try {
                    this.socket.close();
                    this.socket = null;
                }
                catch (Exception ee) {
                    this.socket = null;
                }
            }
        }
    }
    
    public Socket getSocket() {
        return this.socket;
    }
}
