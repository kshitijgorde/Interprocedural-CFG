// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.EOFException;
import java.io.DataInput;

public final class f extends Thread
{
    private bp q;
    boolean q;
    
    public f(final bp q) {
        super("ChatReader");
        this.q = false;
        this.q = q;
    }
    
    public final void run() {
        cp cp = null;
        try {
            while (!this.q.a_()) {
                try {
                    final int q;
                    if ((q = this.q.q().q()) > 1048576) {
                        System.out.println("Got incorrect packet size:" + q);
                        continue;
                    }
                    cp = new cp(this.q.q());
                    this.q.t(cp);
                    cp = null;
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
            if (!this.q.a_()) {
                final cp cp2 = new cp(66561, 1);
                String s;
                if (cp == null) {
                    s = a.s.q(ak.q("The connection to the %1 server was unexpectedly broken."), new String[] { cs.e });
                }
                else {
                    s = a.s.q(ak.q("The connection to the %1 server was unexpectedly broken while receiving message type %2."), new String[] { cs.e, Integer.toHexString(cp.q()) });
                }
                cp2.q(0, 0, s);
                cp2.q(0, 1, ex2.toString());
                this.q.t(cp2);
                this.q.w();
            }
        }
        System.out.println("ChatReader stoped:" + this.q.a_());
        this.q = true;
    }
}
