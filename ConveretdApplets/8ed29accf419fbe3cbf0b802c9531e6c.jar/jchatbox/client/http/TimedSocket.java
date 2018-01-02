// 
// Decompiled by Procyon v0.5.30
// 

package jchatbox.client.http;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.net.InetAddress;

public class TimedSocket
{
    private static final int _$15545 = 100;
    
    public static Socket getSocket(final InetAddress inetAddress, final int n, final int n2) throws IOException, InterruptedIOException {
        final SocketThread socketThread = new SocketThread(inetAddress, n);
        socketThread.start();
        int n3 = 0;
        while (!socketThread.isConnected()) {
            if (socketThread.isError()) {
                throw socketThread.getException();
            }
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            n3 += 100;
            if (n3 > n2) {
                throw new InterruptedIOException(String.valueOf(String.valueOf(new StringBuffer("Could not connect for ").append(n2).append(" milliseconds"))));
            }
        }
        return socketThread.getSocket();
    }
    
    public static Socket getSocket(final String s, final int n, final int n2) throws IOException, InterruptedIOException {
        return getSocket(InetAddress.getByName(s), n, n2);
    }
    
    static class SocketThread extends Thread
    {
        private volatile Socket _$15548;
        private String _$15549;
        private InetAddress _$15550;
        private int _$15551;
        private IOException _$15552;
        
        public SocketThread(final String $15549, final int $15550) {
            this._$15548 = null;
            this._$15549 = null;
            this._$15550 = null;
            this._$15551 = 0;
            this._$15552 = null;
            this._$15549 = $15549;
            this._$15551 = $15550;
        }
        
        public SocketThread(final InetAddress $15550, final int $15551) {
            this._$15548 = null;
            this._$15549 = null;
            this._$15550 = null;
            this._$15551 = 0;
            this._$15552 = null;
            this._$15550 = $15550;
            this._$15551 = $15551;
        }
        
        public void run() {
            Socket $15548;
            try {
                if (this._$15549 != null) {
                    $15548 = new Socket(this._$15549, this._$15551);
                }
                else {
                    $15548 = new Socket(this._$15550, this._$15551);
                }
            }
            catch (IOException $15549) {
                this._$15552 = $15549;
                return;
            }
            this._$15548 = $15548;
        }
        
        public boolean isConnected() {
            return this._$15548 != null;
        }
        
        public boolean isError() {
            return this._$15552 != null;
        }
        
        public Socket getSocket() {
            return this._$15548;
        }
        
        public IOException getException() {
            return this._$15552;
        }
    }
}
