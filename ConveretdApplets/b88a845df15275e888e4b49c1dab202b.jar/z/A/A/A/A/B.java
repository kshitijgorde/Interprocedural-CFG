// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.A;

import z.A.A.A.C;
import z.A.A.A.E;
import java.io.InputStream;
import z.A.A.B.A.A;
import java.io.File;
import z.A.A.A.D;

public class B implements D
{
    private final byte[] A;
    static /* synthetic */ Class class$z$A$A$A$A$D;
    
    public B(final File file) throws z.A.A.B.A.B {
        this(new A(file).A((byte)(-64)));
    }
    
    public B(final InputStream inputStream) throws z.A.A.B.A.B {
        this(new A(inputStream).A((byte)(-19)));
    }
    
    public B(final byte[] a) {
        this.A = a;
    }
    
    public E A() {
        return this.A(new E());
    }
    
    public E A(final E e) {
        if (this.A == null) {
            return e;
        }
        final z.A.A.A.A.D d = (z.A.A.A.A.D)e.B((B.class$z$A$A$A$A$D == null) ? (B.class$z$A$A$A$A$D = class$("z.A.A.A.A.D")) : B.class$z$A$A$A$A$D);
        try {
            d.A(0, this.A(0));
            d.A(1, this.B(1));
            d.A(3, this.B(3));
            final int a = this.A(5);
            d.A(5, a);
            int n = 6;
            for (int i = 0; i < a; ++i) {
                d.B(6 + i, new z.A.A.A.A.E(this.A(n++), this.A(n++), this.A(n++)));
            }
        }
        catch (C c) {
            d.A("MetadataException: " + c);
        }
        return e;
    }
    
    private int B(final int n) throws C {
        if (n + 1 >= this.A.length) {
            throw new C("Attempt to read bytes from outside Jpeg segment data buffer");
        }
        return (this.A[n] & 0xFF) << 8 | (this.A[n + 1] & 0xFF);
    }
    
    private int A(final int n) throws C {
        if (n >= this.A.length) {
            throw new C("Attempt to read bytes from outside Jpeg segment data buffer");
        }
        return this.A[n] & 0xFF;
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
