// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import java.util.Vector;
import java.net.Socket;

public class SocketFactory
{
    public static Socket createSocket(final String s, final int n, final long n2) throws Exception {
        final Vector vector = new Vector<Socket>();
        final Vector vector2 = new Vector();
        final Thread thread = new Thread(new Runnable() {
            private final /* synthetic */ Vector val$socketStore = vector;
            
            public void run() {
                try {
                    final Socket socket = new Socket(s, n);
                    synchronized (this.val$socketStore) {
                        if (Thread.interrupted()) {
                            socket.close();
                        }
                        else {
                            this.val$socketStore.addElement(socket);
                        }
                    }
                }
                catch (Exception ex) {
                    synchronized (vector2) {
                        vector2.addElement(ex);
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        try {
            thread.join(n2);
        }
        catch (Exception ex) {
            thread.interrupt();
            synchronized (vector) {
                if (vector.size() > 0) {
                    try {
                        vector.firstElement().close();
                    }
                    catch (Exception ex2) {}
                }
            }
            throw ex;
        }
        Socket socket = null;
        synchronized (vector) {
            synchronized (vector2) {
                if (vector2.size() > 0) {
                    throw (Exception)vector2.firstElement();
                }
                if (vector.size() <= 0) {
                    thread.interrupt();
                    throw new Exception("SocketFactory: createSocket: Timeout occured.");
                }
                socket = vector.firstElement();
            }
        }
        return socket;
    }
}
