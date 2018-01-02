// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.util.Date;
import java.io.IOException;
import java.util.Hashtable;

class s
{
    private static final int Q = -1;
    private static final int c = -1;
    private static final int F = 2;
    private static final int C = 3;
    private static final int I = -1;
    private static final int B = -1;
    private static final int L = -1;
    private static final int G = -1;
    private static final int S = -1;
    private static final int X = -1;
    private static final int Z = -1;
    private static final int U = -1;
    private static final int b = -1;
    private static final int a = -1;
    private static final int _ = 19;
    private static final int A = -1;
    private static final int W = -1;
    private static final int d = 30;
    private static final int J = 31;
    private static final int R = 64;
    private static final int M = 65;
    private static final int Y = -1;
    private static final int E = -1;
    private static final int D = -1;
    private static final int H = -1;
    private static final int O = -1;
    private static final int V = 71;
    private static final int P = 72;
    private static final int T = 4096;
    X K;
    Hashtable N;
    
    public s(final X k) throws IOException {
        this.N = new Hashtable();
        (this.K = k).A(44L);
        final int e = k.E();
        k.A(e);
        k.E();
        for (int e2 = k.E(), i = 0; i < e2; ++i) {
            k.A(e + 8 * i + 8);
            final int e3 = k.E();
            final int e4 = k.E();
            k.A(e + e4);
            this.N.put(new Integer(e3), new N(k.E(), e + e4 + 4));
        }
    }
    
    public boolean E(final int n) {
        return this.N.get(new Integer(n)) != null;
    }
    
    public int G(final int n) {
        final N n2 = this.N.get(new Integer(n));
        try {
            this.K.A(n2.A());
            return this.K.E();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    public int H(final int n) {
        final N n2 = this.N.get(new Integer(n));
        try {
            this.K.A(n2.A());
            return this.K.readUnsignedByte();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    public int F(final int n) {
        final N n2 = this.N.get(new Integer(n));
        try {
            this.K.A(n2.A());
            return this.K.C();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    public long P(final int n) {
        final N n2 = this.N.get(new Integer(n));
        try {
            this.K.A(n2.A());
            return this.K.F();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return -1L;
        }
    }
    
    public long A(final int n, final long n2) {
        final N n3 = this.N.get(new Integer(n));
        if (n3 == null) {
            return n2;
        }
        try {
            this.K.A(n3.A());
            return this.K.F();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return -1L;
        }
    }
    
    public String O(final int n) {
        final N n2 = this.N.get(new Integer(n));
        if (n2 == null) {
            return null;
        }
        try {
            this.K.A(n2.A());
            final int e = this.K.E();
            final StringBuffer sb = new StringBuffer(e);
            for (int i = 0; i < e; ++i) {
                sb.append((char)this.K.read());
            }
            return sb.toString();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public String N(final int n) {
        final N n2 = this.N.get(new Integer(n));
        try {
            this.K.A(n2.A());
            final int e = this.K.E();
            final StringBuffer sb = new StringBuffer(e);
            for (int i = 0; i < e; ++i) {
                sb.append(this.K.D());
            }
            return sb.toString();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public float D(final int n) {
        final N n2 = this.N.get(new Integer(n));
        try {
            this.K.A(n2.A());
            return this.K.J();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return -1.0f;
        }
    }
    
    public Date B(final int n) {
        throw new RuntimeException(m.A("PropertySet0"));
    }
    
    public Date K(final int n) {
        throw new RuntimeException(m.A("PropertySet0"));
    }
    
    public byte[] M(final int n) {
        final N n2 = this.N.get(new Integer(n));
        try {
            final int a = n2.A();
            this.K.A(a);
            final byte[] array = new byte[this.K.E()];
            this.K.A(a + 4);
            this.K.readFully(array);
            return array;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public int[] J(final int n) {
        throw new RuntimeException(m.A("PropertySet0"));
    }
    
    public int[] I(final int n) {
        throw new RuntimeException(m.A("PropertySet0"));
    }
    
    public long[] C(final int n) {
        throw new RuntimeException(m.A("PropertySet0"));
    }
    
    public float[] A(final int n) {
        throw new RuntimeException(m.A("PropertySet0"));
    }
    
    public String[] L(final int n) {
        throw new RuntimeException(m.A("PropertySet0"));
    }
}
