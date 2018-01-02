// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.io.EOFException;
import java.io.DataInput;

public class ak extends Thread
{
    private t h;
    boolean m;
    
    public void run() {
        cD cd = null;
        try {
            while (!this.h.x) {
                try {
                    try {
                        cd = new cD(this.h.a);
                        this.h.n(cd);
                        cd = null;
                    }
                    catch (EOFException ex3) {
                        if (this.m) {
                            throw new Exception();
                        }
                        try {
                            Thread.sleep(500L);
                            this.m = true;
                        }
                        catch (InterruptedException ex4) {}
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    throw ex;
                }
            }
        }
        catch (Exception ex2) {
            if (!this.h.x) {
                final cD cd2 = new cD(66561, 1);
                String s;
                if (cd == null) {
                    s = am.a(ao.e("The connection to the %1 server was unexpectedly broken."), new String[] { z.G });
                }
                else {
                    s = am.a(ao.e("The connection to the %1 server was unexpectedly broken while receiving message type %2."), new String[] { z.G, Integer.toHexString(cd.b()) });
                }
                cd2.a(0, 0, s);
                cd2.a(0, 1, ex2.toString());
                this.h.n(cd2);
                this.h.a();
            }
        }
    }
    
    public ak(final t h) {
        super("ChatReader");
        this.m = false;
        this.h = h;
    }
}
