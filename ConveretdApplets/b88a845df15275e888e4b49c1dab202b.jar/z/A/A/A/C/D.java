// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.C;

import java.util.GregorianCalendar;
import z.A.A.A.C;
import z.A.A.A.E;
import java.io.InputStream;
import z.A.A.B.A.B;
import z.A.A.B.A.A;
import java.io.File;

public class D implements z.A.A.A.D
{
    private final byte[] X;
    static /* synthetic */ Class class$z$A$A$A$C$B;
    
    public D(final File file) throws B {
        this(new A(file).A((byte)(-19)));
    }
    
    public D(final InputStream inputStream) throws B {
        this(new A(inputStream).A((byte)(-19)));
    }
    
    public D(final byte[] x) {
        this.X = x;
    }
    
    public E A() {
        return this.A(new E());
    }
    
    public E A(final E e) {
        if (this.X == null) {
            return e;
        }
        final z.A.A.A.A b = e.B((D.class$z$A$A$A$C$B == null) ? (D.class$z$A$A$A$C$B = class$("z.A.A.A.C.B")) : D.class$z$A$A$A$C$B);
        int i = 0;
        try {
            while (i < this.X.length - 1 && this.E(i) != 7170) {
                ++i;
            }
        }
        catch (C c) {
            b.A("Couldn't find start of Iptc data (invalid segment)");
            return e;
        }
        while (i < this.X.length) {
            if (this.X[i] != 28) {
                break;
            }
            if (i + 5 >= this.X.length) {
                break;
            }
            ++i;
            byte b2;
            byte b3;
            int e2;
            try {
                b2 = this.X[i++];
                b3 = this.X[i++];
                e2 = this.E(i);
            }
            catch (C c2) {
                b.A("Iptc data segment ended mid-way through tag descriptor");
                return e;
            }
            i += 2;
            if (i + e2 > this.X.length) {
                b.A("data for tag extends beyond end of iptc segment");
                break;
            }
            this.B(b, b2, b3, i, e2);
            i += e2;
        }
        return e;
    }
    
    private int E(final int n) throws C {
        if (n >= this.X.length) {
            throw new C("Attempt to read bytes from outside Iptc data buffer");
        }
        return (this.X[n] & 0xFF) << 8 | (this.X[n + 1] & 0xFF);
    }
    
    private void B(final z.A.A.A.A a, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n2 | n << 8;
        switch (n5) {
            case 512: {
                a.A(n5, (short)(this.X[n3] << 8 | this.X[n3 + 1]));
                return;
            }
            case 522: {
                a.A(n5, this.X[n3]);
                return;
            }
            case 542:
            case 567: {
                if (n4 >= 8) {
                    final String s = new String(this.X, n3, n4);
                    try {
                        a.A(n5, new GregorianCalendar(Integer.parseInt(s.substring(0, 4)), Integer.parseInt(s.substring(4, 6)) - 1, Integer.parseInt(s.substring(6, 8))).getTime());
                        return;
                    }
                    catch (NumberFormatException ex) {}
                    break;
                }
                break;
            }
        }
        String s2;
        if (n4 < 1) {
            s2 = "";
        }
        else {
            s2 = new String(this.X, n3, n4);
        }
        if (a.N(n5)) {
            String[] d;
            try {
                d = a.D(n5);
            }
            catch (C c) {
                d = null;
            }
            String[] array;
            if (d == null) {
                array = new String[] { null };
            }
            else {
                array = new String[d.length + 1];
                for (int i = 0; i < d.length; ++i) {
                    array[i] = d[i];
                }
            }
            array[array.length - 1] = s2;
            a.A(n5, array);
        }
        else {
            a.A(n5, s2);
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
