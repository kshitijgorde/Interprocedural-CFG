// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.net.SocketException;
import java.net.InetAddress;
import java.net.Socket;
import java.io.IOException;

final class bB extends Thread
{
    private String d;
    private int e;
    IOException a;
    Socket b;
    private cE f;
    boolean c;
    private final aj g;
    
    bB(final aj g, final String d, final int e, final cE f) {
        this.g = g;
        super("EstablishConnection (" + d + ":" + e + ")");
        try {
            this.setDaemon(true);
        }
        catch (SecurityException ex) {}
        this.d = d;
        this.e = e;
        this.f = f;
        this.a = null;
        this.b = null;
        this.c = false;
    }
    
    public final void run() {
        try {
            if (this.f != null) {
                this.b = this.f.a(this.d, this.e);
            }
            else {
                final InetAddress[] allByName = InetAddress.getAllByName(this.d);
                int i = 0;
                while (i < allByName.length) {
                    try {
                        if (aj.a(this.g) == null) {
                            this.b = new Socket(allByName[i], this.e);
                        }
                        else {
                            this.b = new Socket(allByName[i], this.e, aj.a(this.g), aj.b(this.g));
                        }
                    }
                    catch (SocketException ex) {
                        if (i == allByName.length - 1 || this.c) {
                            throw ex;
                        }
                        ++i;
                        continue;
                    }
                    break;
                }
            }
        }
        catch (IOException a) {
            this.a = a;
        }
        if (this.c && this.b != null) {
            try {
                this.b.close();
            }
            catch (IOException ex2) {}
            this.b = null;
        }
    }
}
