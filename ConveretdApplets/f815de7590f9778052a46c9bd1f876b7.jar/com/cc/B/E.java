// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.B;

import java.io.ByteArrayInputStream;
import com.cc.C.D;
import java.awt.Toolkit;
import java.lang.reflect.Method;
import com.cc.A.C;
import java.awt.Image;
import java.awt.Color;

public class E
{
    public static final int b = 1;
    public static final int E = 2;
    public static final int S = 4;
    public static final int X = 8;
    public static final char H = '\0';
    public boolean L;
    public boolean K;
    public boolean W;
    public byte Y;
    public boolean I;
    private int G;
    public String B;
    public String P;
    public boolean F;
    public boolean N;
    public String M;
    public Color O;
    public Color U;
    private Image D;
    public String C;
    public String _;
    public boolean c;
    public int a;
    public int Z;
    private boolean T;
    private boolean A;
    private boolean V;
    public String R;
    private B[] Q;
    private C J;
    private static Method d;
    static /* synthetic */ Class class$java$io$ByteArrayInputStream;
    
    public C A() {
        return this.J;
    }
    
    public E(final byte y, final char c) {
        this.L = false;
        this.K = false;
        this.W = false;
        this.I = false;
        this.M = "";
        this.O = null;
        this.U = null;
        this.D = null;
        this.C = null;
        this._ = null;
        this.c = false;
        this.a = 1;
        this.Z = 1;
        this.A = false;
        this.V = false;
        this.R = "";
        this.Q = new B[0];
        this.J = new C();
        this.Y = y;
        this.L = ((y & 0x40) != 0x0);
        this.K = ((y & 0x20) != 0x0);
        this.F = ((y & 0x2) != 0x0);
        this.N = ((y & 0x4) != 0x0);
        if (c == '#') {
            this.G = 1;
        }
        else if (c == ' ') {
            this.G = 4;
        }
        else if (c == '?') {
            this.G = 2;
        }
        else {
            this.I = true;
            this.G = 2;
        }
    }
    
    public E(final int g) {
        this.L = false;
        this.K = false;
        this.W = false;
        this.I = false;
        this.M = "";
        this.O = null;
        this.U = null;
        this.D = null;
        this.C = null;
        this._ = null;
        this.c = false;
        this.a = 1;
        this.Z = 1;
        this.A = false;
        this.V = false;
        this.R = "";
        this.Q = new B[0];
        this.J = new C();
        this.G = g;
    }
    
    public boolean B() {
        return this.G == 2;
    }
    
    public boolean R() {
        return this.G == 4;
    }
    
    public boolean I() {
        return this.G == 1;
    }
    
    public boolean V() {
        return this.G == 8;
    }
    
    public int P() {
        return this.G;
    }
    
    public String G() {
        if (!this.I) {
            throw new IllegalStateException("This square has no hint! Call isHasHint() before.");
        }
        return this.M;
    }
    
    public boolean W() {
        return this.B != null;
    }
    
    public Image K() {
        if (this.D == null) {
            throw new IllegalStateException("Background image has not been set!");
        }
        return this.D;
    }
    
    public void Q() {
        this.W = !this.H();
    }
    
    private static final Method D() {
        try {
            return Class.forName("com.cc.gui.JavaVersion").getDeclaredMethod("bytesToImage", (com.cc.B.E.class$java$io$ByteArrayInputStream == null) ? (com.cc.B.E.class$java$io$ByteArrayInputStream = class$("java.io.ByteArrayInputStream")) : com.cc.B.E.class$java$io$ByteArrayInputStream);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public void A(final String _, final String c) {
        this.C = c;
        this._ = _;
        if (com.cc.B.E.d == null) {
            this.D = Toolkit.getDefaultToolkit().createImage(com.cc.C.D.A(_));
        }
        else {
            try {
                this.D = (Image)com.cc.B.E.d.invoke(this, new ByteArrayInputStream(com.cc.C.D.A(_)));
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this.D = null;
            }
        }
    }
    
    public boolean M() {
        return this.D != null && (!this.c || this.H());
    }
    
    public boolean F() {
        return this.D != null;
    }
    
    public int N() {
        return this.Z;
    }
    
    public void A(final int z) {
        this.Z = z;
    }
    
    public int O() {
        return this.a;
    }
    
    public void B(final int a) {
        this.a = a;
    }
    
    public boolean U() {
        return this.T;
    }
    
    void A(final boolean t) {
        if (this.T != t) {
            this.T = t;
        }
    }
    
    public boolean S() {
        return this.R.length() == 0;
    }
    
    public String L() {
        if (this.S() && this.I) {
            return this.M;
        }
        return this.R;
    }
    
    public boolean C() {
        return this.A;
    }
    
    public void T() {
        this.V = true;
    }
    
    public void B(final boolean v) {
        this.V = v;
    }
    
    public boolean E() {
        return this.V;
    }
    
    public boolean A(final String r, final boolean a) {
        if (r == null) {
            throw new IllegalArgumentException("Letter can not be null!");
        }
        if (r.equalsIgnoreCase(this.R) && this.A == a) {
            return false;
        }
        if (r.length() > 0 && r.trim().length() == 0) {
            return false;
        }
        this.R = r;
        this.A = a;
        this.W = false;
        return true;
    }
    
    public boolean H() {
        return this.L().equalsIgnoreCase(this.M);
    }
    
    public void A(final B b) {
        final B[] q = new B[this.Q.length + 1];
        for (int i = 0; i < this.Q.length; ++i) {
            q[i] = this.Q[i];
        }
        q[q.length - 1] = b;
        this.Q = q;
    }
    
    public B[] J() {
        return this.Q;
    }
    
    public String toString() {
        return this.L() + " " + this.B;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        com.cc.B.E.d = D();
    }
}
