// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.io.EOFException;
import java.io.DataInput;

public class bd extends Thread
{
    private be l;
    boolean h;
    
    public void run() {
        V v = null;
        try {
            while (!this.l.G) {
                try {
                    try {
                        v = new V(this.l.a);
                        this.l.C(v);
                        v = null;
                    }
                    catch (EOFException ex4) {
                        if (this.h) {
                            throw new Exception();
                        }
                        try {
                            Thread.sleep(500L);
                            this.h = true;
                        }
                        catch (InterruptedException ex5) {}
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        throw ex;
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                    throw ex2;
                }
            }
        }
        catch (Exception ex3) {
            if (!this.l.G) {
                final V v2 = new V(66561, 1);
                String s;
                if (v == null) {
                    s = aC.a(aG.a("The connection to the %1 server was unexpectedly broken."), new String[] { t.a });
                }
                else {
                    s = aC.a(aG.a("The connection to the %1 server was unexpectedly broken while receiving message type %2."), new String[] { t.a, Integer.toHexString(v.a()) });
                }
                v2.a(0, 0, s);
                v2.a(0, 1, ex3.toString());
                this.l.C(v2);
                this.l.a();
            }
        }
    }
    
    public bd(final be l) {
        super("ChatReader");
        this.h = false;
        this.l = l;
    }
}
