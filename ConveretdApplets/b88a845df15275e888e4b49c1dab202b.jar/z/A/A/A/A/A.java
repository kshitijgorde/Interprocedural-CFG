// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.A;

import z.A.A.A.E;
import java.io.InputStream;
import z.A.A.B.A.B;
import java.io.File;
import z.A.A.A.D;

public class A implements D
{
    private final byte[] Y;
    static /* synthetic */ Class class$z$A$A$A$A$G;
    
    public A(final File file) throws B {
        this(new z.A.A.B.A.A(file).A((byte)(-2)));
    }
    
    public A(final InputStream inputStream) throws B {
        this(new z.A.A.B.A.A(inputStream).A((byte)(-19)));
    }
    
    public A(final byte[] y) {
        this.Y = y;
    }
    
    public E A() {
        return this.A(new E());
    }
    
    public E A(final E e) {
        if (this.Y == null) {
            return e;
        }
        ((G)e.B((A.class$z$A$A$A$A$G == null) ? (A.class$z$A$A$A$A$G = class$("z.A.A.A.A.G")) : A.class$z$A$A$A$A$G)).A(0, new String(this.Y));
        return e;
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
