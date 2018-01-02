// 
// Decompiled by Procyon v0.5.30
// 

package DCL.util;

import java.io.IOException;
import java.net.Socket;
import java.net.InetAddress;

class FirewallTest implements Runnable
{
    static final int a = 0;
    static final int b = 1;
    static final int c = 2;
    private String d;
    private boolean e;
    private int f;
    
    FirewallTest(final String d) {
        this.e = false;
        this.f = 1;
        this.d = d;
    }
    
    public void run() {
        for (int i = 0; i < 3; ++i) {
            try {
                new Socket(InetAddress.getByName(this.d), 1627).close();
                this.f = 0;
                break;
            }
            catch (IOException ex) {}
            catch (SecurityException ex2) {
                this.f = 2;
                break;
            }
        }
        this.e = true;
    }
    
    final boolean a() {
        return this.e;
    }
    
    final int b() {
        return this.f;
    }
}
