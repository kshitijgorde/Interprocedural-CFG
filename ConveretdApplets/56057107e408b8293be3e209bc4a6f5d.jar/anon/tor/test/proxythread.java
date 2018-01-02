// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor.test;

import java.io.IOException;
import anon.tor.TorChannel;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

public class proxythread implements Runnable
{
    private OutputStream torout;
    private InputStream torin;
    private OutputStream out;
    private InputStream in;
    private Socket client;
    private Thread t;
    private TorChannel channel;
    
    public proxythread(final Socket client, final TorChannel channel) throws IOException {
        this.torin = channel.getInputStream();
        this.torout = channel.getOutputStream();
        this.in = client.getInputStream();
        this.out = client.getOutputStream();
        this.client = client;
        this.channel = channel;
    }
    
    public void start() {
        (this.t = new Thread(this, "Tor proxy thread")).start();
    }
    
    public void stop() {
        try {
            while (this.torin.available() > 0) {
                final byte[] array = new byte[this.torin.available()];
                this.out.write(array, 0, this.torin.read(array));
                this.out.flush();
            }
        }
        catch (Exception ex) {}
        this.channel.close();
        try {
            this.client.close();
        }
        catch (Exception ex2) {
            System.out.println("Fehler beim schliessen des kanals");
        }
        System.out.println("kanal wird geschlossen");
        this.t.stop();
    }
    
    public void run() {
    Label_0042:
        while (true) {
            break Label_0042;
            while (true) {
                try {
                    while (true) {
                        final byte[] array = new byte[this.torin.available()];
                        this.out.write(array, 0, this.torin.read(array));
                        this.out.flush();
                        while (this.torin.available() <= 0) {
                            while (this.in.available() > 0) {
                                final byte[] array2 = new byte[this.in.available()];
                                this.torout.write(array2, 0, this.in.read(array2));
                                this.torout.flush();
                            }
                            if (this.channel.isClosedByPeer()) {
                                this.stop();
                            }
                            Thread.sleep(20L);
                        }
                    }
                }
                catch (Exception ex) {
                    System.out.println("Exception catched : " + ex.getLocalizedMessage());
                    ex.printStackTrace();
                    this.stop();
                    continue Label_0042;
                }
                continue Label_0042;
            }
            break;
        }
    }
}
