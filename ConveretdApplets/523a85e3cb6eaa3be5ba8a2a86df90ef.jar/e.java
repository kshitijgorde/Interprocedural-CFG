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
            n.d(this.p, n.p(this.p, n.p(this.p)));
            DataInputStream dataInputStream = new DataInputStream(new URL(n.p(this.p).getCodeBase(), n.p(this.p)).openStream());
            int n = 1;
            while (dataInputStream.available() < 32 && n != 0) {
                if (dataInputStream.read() >= 32) {
                    System.gc();
                    dataInputStream = new DataInputStream(new URL(n.p(this.p).getCodeBase(), n.p(this.p)).openStream());
                    n = 0;
                }
                else {
                    Thread.sleep(100L);
                }
            }
            final int int1 = dataInputStream.readInt();
            final int int2 = dataInputStream.readInt();
            final int int3 = dataInputStream.readInt();
            final int int4 = dataInputStream.readInt();
            final int n2 = (int1 << 3 | int1 >>> 29) ^ n.d(this.p);
            final int n3 = (int2 << 15 | int2 >>> 17) ^ n.a(this.p);
            final int n4 = (int3 << 19 | int3 >>> 13) ^ n.n(this.p);
            final int n5 = (int4 << 25 | int4 >>> 7) ^ n.i(this.p);
            int n11;
            if (n3 == 1162299211) {
                final int int5 = dataInputStream.readInt();
                final int int6 = dataInputStream.readInt();
                final int int7 = dataInputStream.readInt();
                final int int8 = dataInputStream.readInt();
                final int n6 = (int5 << 3 | int5 >>> 29) ^ n.d(this.p);
                final int n7 = (int6 << 15 | int6 >>> 17) ^ n.a(this.p);
                final int n8 = (int7 << 19 | int7 >>> 13) ^ n.n(this.p);
                final int n9 = (int8 << 25 | int8 >>> 7) ^ n.i(this.p);
                n.n(this.p, n4);
                n.p(this.p, n5);
                n.l(this.p, n6);
                n.v(this.p, n7);
                final int n10 = n8 ^ Integer.MIN_VALUE;
                if (n10 % 4 == 0) {
                    n11 = n10 - 8;
                }
                else {
                    n11 = n10 - n10 % 4 - 4;
                }
                n.d(this.p, n10 - 8);
                n.p(this.p, new byte[n11]);
                n.p(this.p).d(n.v(this.p));
            }
            else {
                final int n12 = n2 ^ Integer.MIN_VALUE;
                if (n12 % 4 == 0) {
                    n11 = n12 - 16;
                }
                else {
                    n11 = n12 - n12 % 4 - 12;
                }
                n.d(this.p, n12 - 8);
                n.p(this.p, new byte[n11]);
                n.p(this.p).d(n.v(this.p));
                n.p(this.p).n(n4);
                n.p(this.p).n(n5);
                n.a(this.p, 8);
            }
            while (n.p(this.p) < n11) {
                final int available = dataInputStream.available();
                if (available > 0) {
                    n.i(this.p, dataInputStream.read(n.p(this.p), n.p(this.p), available));
                }
                else {
                    n.i(this.p, dataInputStream.read(n.p(this.p), n.p(this.p), Math.min(32, n11 - n.p(this.p))));
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
