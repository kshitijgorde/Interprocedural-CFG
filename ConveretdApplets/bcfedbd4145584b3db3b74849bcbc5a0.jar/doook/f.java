// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.io.EOFException;
import java.io.DataInput;

public class f extends Thread
{
    private as b;
    boolean c;
    
    public void run() {
        aJ aj = null;
        try {
            while (!this.b.A) {
                try {
                    try {
                        aj = new aJ(this.b.a);
                        this.b.n(aj);
                        aj = null;
                    }
                    catch (EOFException ex4) {
                        if (this.c) {
                            throw new Exception();
                        }
                        try {
                            Thread.sleep(500L);
                            this.c = true;
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
            if (!this.b.A) {
                final aJ aj2 = new aJ(66561, 1);
                String s;
                if (aj == null) {
                    s = H.a(ar.b("The connection to the %1 server was unexpectedly broken."), new String[] { bi.Q });
                }
                else {
                    s = H.a(ar.b("The connection to the %1 server was unexpectedly broken while receiving message type %2."), new String[] { bi.Q, Integer.toHexString(aj.b()) });
                }
                aj2.a(0, 0, s);
                aj2.a(0, 1, ex3.toString());
                this.b.n(aj2);
                this.b.k();
            }
        }
    }
    
    public f(final as b) {
        super("ChatReader");
        this.c = false;
        this.b = b;
    }
}
