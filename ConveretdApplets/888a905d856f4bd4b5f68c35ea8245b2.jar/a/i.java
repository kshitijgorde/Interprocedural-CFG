// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.EOFException;
import java.io.DataInput;

public final class i extends Thread
{
    private cs q;
    boolean q;
    
    public i(final cs q) {
        super("ChatReader");
        this.q = false;
        this.q = q;
    }
    
    public final void run() {
        dI di = null;
        try {
            while (!this.q.q()) {
                try {
                    final int q;
                    if ((q = this.q.q().q()) > 1048576) {
                        System.out.println("Got incorrect packet size:" + q);
                        continue;
                    }
                    di = new dI(this.q.q());
                    this.q.p(di);
                    di = null;
                    continue;
                }
                catch (EOFException ex) {
                    System.out.println("ChatReader got exception:" + ex);
                    if (!this.q) {
                        try {
                            Thread.sleep(500L);
                            this.q = true;
                        }
                        catch (InterruptedException ex3) {}
                        continue;
                    }
                    throw ex;
                }
                break;
            }
        }
        catch (Exception ex2) {
            System.out.println("ChatReader got exception:" + ex2);
            if (!this.q.q()) {
                final dI di2 = new dI(66561, 1);
                String s;
                if (di == null) {
                    s = B.q(be.w("The connection to the %1 server was unexpectedly broken."), new String[] { dN.e });
                }
                else {
                    s = B.q(be.w("The connection to the %1 server was unexpectedly broken while receiving message type %2."), new String[] { dN.e, Integer.toHexString(di.q()) });
                }
                di2.q(0, 0, s);
                di2.q(0, 1, ex2.toString());
                this.q.p(di2);
                this.q.w();
            }
        }
        System.out.println("ChatReader stoped:" + this.q.q());
        this.q = true;
    }
}
