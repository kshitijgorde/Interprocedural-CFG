import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class e extends Thread
{
    private final n p;
    
    public e(final n p) {
        this.p = p;
    }
    
    public final void run() {
        try {
            final DataInputStream dataInputStream = new DataInputStream(new URL(n.p(this.p).getDocumentBase(), n.p(this.p, n.p(this.p))).openStream());
            while (dataInputStream.available() < 32) {
                Thread.sleep(100L);
            }
            final int int1 = dataInputStream.readInt();
            final int int2 = dataInputStream.readInt();
            final int int3 = dataInputStream.readInt();
            final int int4 = dataInputStream.readInt();
            final int n = (int1 << 3 | int1 >>> 29) ^ n.n(this.p);
            final int n2 = (int2 << 15 | int2 >>> 17) ^ n.d(this.p);
            final int n3 = (int3 << 19 | int3 >>> 13) ^ n.v(this.p);
            final int n4 = (int4 << 25 | int4 >>> 7) ^ n.a(this.p);
            int n10;
            if (n2 == 1162299211) {
                final int int5 = dataInputStream.readInt();
                final int int6 = dataInputStream.readInt();
                final int int7 = dataInputStream.readInt();
                final int int8 = dataInputStream.readInt();
                final int n5 = (int5 << 3 | int5 >>> 29) ^ n.n(this.p);
                final int n6 = (int6 << 15 | int6 >>> 17) ^ n.d(this.p);
                final int n7 = (int7 << 19 | int7 >>> 13) ^ n.v(this.p);
                final int n8 = (int8 << 25 | int8 >>> 7) ^ n.a(this.p);
                n.a(this.p, n3);
                n.p(this.p, n4);
                n.n(this.p, n5);
                n.v(this.p, n6);
                final int n9 = n7 ^ Integer.MIN_VALUE;
                if (n9 % 4 == 0) {
                    n10 = n9 - 8;
                }
                else {
                    n10 = n9 - n9 % 4 - 4;
                }
                n.l(this.p, n9 - 8);
                n.p(this.p, new byte[n10]);
                n.p(this.p).a(n.p(this.p));
            }
            else {
                final int n11 = n ^ Integer.MIN_VALUE;
                if (n11 % 4 == 0) {
                    n10 = n11 - 16;
                }
                else {
                    n10 = n11 - n11 % 4 - 12;
                }
                n.l(this.p, n11 - 8);
                n.p(this.p, new byte[n10]);
                n.p(this.p).a(n.p(this.p));
                n.p(this.p).d(n3);
                n.p(this.p).d(n4);
                n.d(this.p, 8);
            }
            while (n.i(this.p) < n10) {
                final int available = dataInputStream.available();
                if (available > 0) {
                    n.i(this.p, dataInputStream.read(n.p(this.p), n.i(this.p), available));
                }
            }
        }
        catch (IOException ex) {
            System.out.println("Caught IOException: ".concat(String.valueOf(String.valueOf(ex))));
        }
        catch (InterruptedException ex2) {
            System.out.println("Caught InterruptedException: ".concat(String.valueOf(String.valueOf(ex2))));
        }
        n.p(this.p);
    }
}
