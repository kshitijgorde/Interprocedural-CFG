// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.EOFException;
import java.io.UTFDataFormatException;
import java.io.DataInput;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;

public final class cK extends Thread
{
    private cM q;
    boolean q;
    
    public cK(final cM q) {
        super("ChatReader");
        this.q = false;
        this.q = q;
    }
    
    public final void run() {
        es es = null;
        try {
            while (!this.q.q()) {
                try {
                    final int int1;
                    if ((int1 = this.q.q().readInt()) > 1048576) {
                        System.out.println("Got incorrect packet size:" + int1);
                        continue;
                    }
                    final byte[] array = new byte[int1];
                    for (int i = 0; i < array.length; ++i) {
                        array[i] = (byte)this.q.q().read();
                    }
                    es = new er(new DataInputStream(new ByteArrayInputStream(ev.w(array))));
                    System.out.println("ChatReader got packet:" + Integer.toHexString(es.q()));
                    this.q.w(es);
                    es = null;
                    continue;
                }
                catch (UTFDataFormatException ex3) {
                    continue;
                }
                catch (EOFException ex) {
                    System.out.println("ChatReader got exception:" + ex);
                    ex.printStackTrace();
                    if (!this.q) {
                        try {
                            Thread.sleep(500L);
                            this.q = true;
                        }
                        catch (InterruptedException ex4) {}
                        continue;
                    }
                    throw ex;
                }
                break;
            }
        }
        catch (Exception ex2) {
            System.out.println("ChatReader got exception:" + ex2);
            ex2.printStackTrace();
            if (!this.q.q()) {
                final es es2 = new es(66561, 1);
                String s;
                if (es == null) {
                    s = ec.q(eb.q("The connection to the %1 server was unexpectedly broken."), new String[] { a.e });
                }
                else {
                    s = ec.q(eb.q("The connection to the %1 server was unexpectedly broken while receiving message type %2."), new String[] { a.e, Integer.toHexString(es.q()) });
                }
                es2.q(0, 0, s);
                es2.q(0, 1, ex2.toString());
                this.q.w(es2);
                this.q.e();
            }
        }
        System.out.println("ChatReader stoped:" + this.q.q());
        this.q = true;
    }
}
