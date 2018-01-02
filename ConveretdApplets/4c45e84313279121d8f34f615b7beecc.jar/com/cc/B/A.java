// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.B;

import java.util.StringTokenizer;
import java.awt.Point;
import com.cc.C.B;
import com.cc.D.C;
import java.awt.Color;
import java.util.Observable;

public class A extends Observable
{
    public static final int m = 0;
    public static final int R = 1;
    public static final int Z = 3;
    public static final int ¥ = 2;
    public boolean j;
    public int A;
    public int D;
    public boolean F;
    public String[] I;
    public String[] H;
    public double q;
    public boolean r;
    public boolean J;
    public boolean ª;
    public boolean £;
    public boolean n;
    public int p;
    public int L;
    public boolean c;
    public double s;
    public double W;
    public double K;
    public double g;
    public double Q;
    public double S;
    public Color E;
    public Color _;
    public Color v;
    public Color e;
    public Color u;
    public Color x;
    public Color X;
    public Color a;
    public Color o;
    public boolean N;
    public boolean y;
    public boolean h;
    public boolean d;
    public String Y;
    public boolean G;
    public boolean i;
    private E[][] C;
    private int[][] V;
    private int[][] U;
    private C B;
    private B ¤;
    public D O;
    private boolean w;
    private boolean l;
    private boolean z;
    private E b;
    private E f;
    public int T;
    public int M;
    public boolean t;
    public boolean P;
    private D k;
    private D ¢;
    
    public A() {
        this.j = false;
        this.F = false;
        this.q = 1.0;
        this.r = false;
        this.J = false;
        this.ª = false;
        this.£ = false;
        this.p = 0;
        this.L = 25;
        this.c = false;
        this.s = 0.1;
        this.W = 0.1;
        this.K = 0.1;
        this.g = 0.1;
        this.Q = 0.1;
        this.S = 0.2;
        this.E = Color.black;
        this._ = Color.black;
        this.v = Color.black;
        this.e = Color.black;
        this.u = Color.blue.darker();
        this.x = Color.black;
        this.X = Color.lightGray;
        this.a = Color.yellow;
        this.o = Color.white;
        this.N = false;
        this.i = true;
        this.¤ = new B();
        this.O = new D("wordsearch");
        this.l = true;
        this.z = true;
        this.T = 3;
        this.M = 3;
        this.t = false;
        this.P = false;
        this.k = new D("null");
        this.¢ = null;
    }
    
    public void A(final C b) {
        this.B = b;
        if (this.h) {
            for (int i = 0; i < this.C.length; ++i) {
                for (int j = 0; j < this.C[i].length; ++j) {
                    if (this.C[i][j] != null && this.C[i][j].B() && this.C[i][j].W()) {
                        boolean k = false;
                        final B e = this.E(this.C[i][j].B);
                        for (int n = 0; n < e.A() && !k; k = ((E)e.A(n)).I, ++n) {}
                        for (int l = 0; l < e.A(); ++l) {
                            ((E)e.A(l)).I = k;
                        }
                    }
                }
            }
        }
        this.t = false;
        int n2 = 0;
        for (int n3 = 0; n3 < this.C.length; ++n3) {
            for (int n4 = 0; n4 < this.C[n3].length; ++n4) {
                if (this.C[n3][n4] != null) {
                    if (this.C[n3][n4].V()) {
                        this.t = true;
                    }
                    else if (n2 == 0 && this.C[n3][n4].B()) {
                        this.B(this.C[n3][n4]);
                        if (this.A(this.I()).A() > 0) {
                            this.A((D)this.A(this.I()).A(0));
                        }
                        n2 = 1;
                    }
                }
            }
        }
    }
    
    public void B(final D d) {
        this.¤.A(d);
    }
    
    public B H() {
        return this.¤;
    }
    
    public D D(final String s) {
        for (int i = 0; i < this.¤.A(); ++i) {
            if (((D)this.¤.A(i)).H().equals(s)) {
                return (D)this.¤.A(i);
            }
        }
        throw new IllegalArgumentException("Can not find word with id=" + s);
    }
    
    public B A(final E e) {
        final B b = new B();
        for (int i = 0; i < this.¤.A(); ++i) {
            if (!((D)this.¤.A(i)).A() && !((D)this.¤.A(i)).D() && ((D)this.¤.A(i)).A(e)) {
                b.A(this.¤.A(i));
            }
        }
        return b;
    }
    
    public void A(final int a, final int d) {
        this.A = a;
        this.D = d;
        this.C = new E[a][d];
        this.V = new int[a][d];
        this.U = new int[a][d];
        for (int i = 0; i < a; ++i) {
            for (int j = 0; j < d; ++j) {
                this.C[i][j] = new E(4);
                this.V[i][j] = 0;
                this.U[i][j] = 0;
            }
        }
    }
    
    public void A(final E e, final int n, final int n2) {
        this.C[n][n2] = e;
        for (int i = 0; i < e.O(); ++i) {
            for (int j = 0; j < e.N(); ++j) {
                this.V[n + i][n2 + j] = i;
                this.U[n + i][n2 + j] = j;
            }
        }
    }
    
    public E A(final int n, final int n2, final boolean b) {
        final int n3 = this.V[n][n2];
        final int n4 = this.U[n][n2];
        if ((n3 > 0 || n4 > 0) && b) {
            return null;
        }
        return this.C[n - n3][n2 - n4];
    }
    
    public E[][] L() {
        return this.C;
    }
    
    public void B(final E b) {
        if (this.b == b || this.d) {
            return;
        }
        if (this.b != null) {
            this.b.A(false);
        }
        b.A(true);
        this.f = this.b;
        this.b = b;
        super.setChanged();
        super.notifyObservers(com.cc.B.F.A(this.f, b));
        if (!this.M().A(b)) {
            this.A(this.A(b), true);
        }
    }
    
    public void A(final B b, final boolean b2) {
        if (b.A() == 0) {
            this.A(this.k);
        }
        else if (b.A() == 1) {
            this.A((D)b.A(0));
        }
        else {
            final int b3 = b.B(this.M());
            if (b3 > -1) {
                this.A((D)b.A(b2 ? b3 : (1 - b3)));
            }
            else {
                final com.cc.D.D a = this.B.A((D)b.A(0));
                final com.cc.D.D a2 = this.B.A(this.M());
                final int n = (a != null) ? a.D() : -1;
                final int n2 = (a2 != null) ? a2.D() : -1;
                if ((n == n2 && b2) || (n != n2 && !b2)) {
                    this.A((D)b.A(0));
                }
                else {
                    this.A((D)b.A(1));
                }
            }
        }
    }
    
    public E I() {
        return this.b;
    }
    
    public Point C(final E e) {
        for (int i = 0; i < this.C.length; ++i) {
            for (int j = 0; j < this.C[i].length; ++j) {
                if (e == this.C[i][j]) {
                    return new Point(i, j);
                }
            }
        }
        throw new IllegalArgumentException("Cell [" + e + "] does not exist");
    }
    
    public void D(final E e) {
        this.A(e, e.M, false);
        e.T();
    }
    
    public void Q() {
        this.l = false;
        this.z = false;
        if (this.d) {
            this.J();
        }
        else {
            for (int i = 0; i < this.A; ++i) {
                for (int j = 0; j < this.D; ++j) {
                    final E a = this.A(i, j, false);
                    if (a.B()) {
                        this.D(a);
                    }
                }
            }
        }
        this.A(com.cc.B.F.B());
    }
    
    public void A(final F f) {
        this.setChanged();
        this.notifyObservers(f);
    }
    
    private void J() {
        final B b = new B();
        for (int i = 0; i < this.¤.A(); ++i) {
            if (((D)this.¤.A(i)).B()) {
                b.A(this.¤.A(i));
            }
            ((D)this.¤.A(i)).C(false);
        }
        this.¤.B(b);
    }
    
    public void B() {
        if (this.d) {
            this.E();
        }
        else {
            for (int i = 0; i < this.D; ++i) {
                for (int j = 0; j < this.A; ++j) {
                    final E a = this.A(j, i, false);
                    if (a.B()) {
                        a.Q();
                    }
                }
            }
        }
        this.A(com.cc.B.F.B());
    }
    
    private void E() {
        for (int i = this.¤.A() - 1; i >= 0; --i) {
            if (((D)this.¤.A(i)).B() && !((D)this.¤.A(i)).D()) {
                this.D((D)this.¤.A(i));
            }
        }
    }
    
    public boolean G() {
        if (this.d) {
            return this.F();
        }
        for (int i = 0; i < this.D; ++i) {
            for (int j = 0; j < this.A; ++j) {
                final E a = this.A(j, i, false);
                if (a.B()) {
                    if (a.S()) {
                        return false;
                    }
                    if (!a.H()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private boolean F() {
        int n = 0;
        for (int i = 0; i < this.¤.A(); ++i) {
            if (!((D)this.¤.A(i)).D() && !((D)this.¤.A(i)).B()) {
                ++n;
            }
        }
        return n == this.¤.A() && this.¤.A() > 0;
    }
    
    public boolean P() {
        if (!this.d) {
            for (int i = 0; i < this.D; ++i) {
                for (int j = 0; j < this.A; ++j) {
                    final E a = this.A(j, i, false);
                    if (a.B() && a.S()) {
                        return false;
                    }
                }
            }
            return true;
        }
        return this.A();
    }
    
    private boolean A() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        for (int i = 0; i < this.¤.A(); ++i) {
            if (((D)this.¤.A(i)).B()) {
                ++n;
            }
            else {
                if (((D)this.¤.A(i)).D()) {
                    return false;
                }
                ++n2;
                ++n3;
            }
        }
        return n3 > 0;
    }
    
    public void C(final String s) {
        if (this.d) {
            this.B(s);
        }
        else {
            for (int i = 0; i < this.D; ++i) {
                for (int j = 0; j < this.A; ++j) {
                    final E a = this.A(j, i, true);
                    if (a != null && a.B()) {
                        if (a.I) {
                            this.A(a, a.G(), false);
                        }
                        else {
                            this.A(a, "", false);
                        }
                    }
                }
            }
            if (s != null && s.length() > 0) {
                int n = 0;
                for (int k = 0; k < this.D; ++k) {
                    for (int l = 0; l < this.A; ++l) {
                        final E a2 = this.A(l, k, true);
                        if (a2 != null) {
                            if (a2.B()) {
                                final char char1 = s.charAt(n);
                                ++n;
                                String s2 = String.valueOf(char1);
                                if (char1 == '|') {
                                    final int index = s.indexOf(124, n);
                                    s2 = s.substring(n, index);
                                    n = index + 1;
                                }
                                boolean b = false;
                                if (s2.startsWith("*")) {
                                    b = true;
                                    s2 = s2.substring(1);
                                }
                                if (s2.equals("-")) {
                                    s2 = "";
                                }
                                if (!a2.I) {
                                    this.A(a2, s2, b);
                                }
                            }
                        }
                    }
                }
            }
        }
        this.l = true;
        this.z = true;
        this.j = false;
        this.A(com.cc.B.F.B());
    }
    
    private void B(final String s) {
        for (int i = this.¤.A() - 1; i >= 0; --i) {
            final D d = (D)this.¤.A(i);
            if (d.B() && !d.D()) {
                this.D(d);
            }
            else {
                d.C(true);
            }
        }
        if (s != null) {
            int index;
            for (int j = s.indexOf(40); j > -1; j = s.indexOf(40, index)) {
                index = s.indexOf(41, j);
                final StringTokenizer stringTokenizer = new StringTokenizer(s.substring(j + 1, index), ",");
                final int intValue = Integer.valueOf(stringTokenizer.nextToken());
                final int intValue2 = Integer.valueOf(stringTokenizer.nextToken());
                final int intValue3 = Integer.valueOf(stringTokenizer.nextToken());
                final int intValue4 = Integer.valueOf(stringTokenizer.nextToken());
                final D d2 = new D("tmp-word");
                d2.B = this.B(new Point(intValue, intValue2), new Point(intValue3, intValue4));
                this.C(d2);
            }
        }
        super.setChanged();
        super.notifyObservers(new F(7));
    }
    
    public B B(final Point point, final Point point2) {
        final B b = new B();
        final int a = this.A(point2.x - point.x);
        final int a2 = this.A(point2.y - point.y);
        if (a == 0 || a2 == 0 || Math.abs(point.x - point2.x) == Math.abs(point.y - point2.y)) {
            for (int max = Math.max(Math.abs(point2.x - point.x), Math.abs(point2.y - point.y)), i = 0; i <= max; ++i) {
                final E a3 = this.A(point.x + a * i, point.y + a2 * i, true);
                if (a3 != null) {
                    b.A(a3);
                }
            }
        }
        return b;
    }
    
    private B A(final B b) {
        final B b2 = new B();
        if (b.A() < 2) {
            return b2;
        }
        final Point c = this.C((E)b.A(0));
        final Point c2 = this.C((E)b.A(b.A() - 1));
        final int a = this.A(c2.x - c.x);
        final int a2 = this.A(c2.y - c.y);
        if (a == 0 || a2 == 0 || Math.abs(c.x - c2.x) == Math.abs(c.y - c2.y)) {
            for (int max = Math.max(Math.abs(c2.x - c.x), Math.abs(c2.y - c.y)), i = 0; i <= max; ++i) {
                final int n = c.x + a * i;
                final int n2 = c.y + a2 * i;
                if (a != 0 && n + a >= 0 && n + a < this.A) {
                    final E a3 = this.A(n + a, n2, true);
                    if (a3 != null) {
                        b2.A(a3);
                    }
                }
                if (a2 != 0 && n2 + a2 >= 0 && n2 + a2 < this.D) {
                    final E a4 = this.A(n, n2 + a2, true);
                    if (a4 != null) {
                        b2.A(a4);
                    }
                }
            }
        }
        return b2;
    }
    
    private int A(final int n) {
        return (n == 0) ? 0 : ((n > 0) ? 1 : -1);
    }
    
    public boolean A(final E e, final String s, final boolean b) {
        if (b) {
            this.P = true;
        }
        if (this.y && !this.B(e, s, b)) {
            return false;
        }
        if (this.h && !this.C(e, s, b)) {
            return false;
        }
        this.D(e, s, b);
        if (this.h && e.W()) {
            for (int i = 0; i < this.A; ++i) {
                for (int j = 0; j < this.D; ++j) {
                    final E a = this.A(i, j, true);
                    if (a != null && a.W() && a.B.equals(e.B)) {
                        this.D(a, s, b);
                    }
                }
            }
        }
        this.O();
        return true;
    }
    
    private B E(final String s) {
        final B b = new B();
        for (int i = 0; i < this.A; ++i) {
            for (int j = 0; j < this.D; ++j) {
                final E a = this.A(i, j, true);
                if (a != null && a.W() && a.B.equals(s)) {
                    b.A(a);
                }
            }
        }
        return b;
    }
    
    public void O() {
        F f = null;
        if (this.z && this.G()) {
            f = com.cc.B.F.C();
            this.z = false;
            this.l = false;
        }
        else if (this.l && this.P()) {
            f = com.cc.B.F.A();
            this.l = false;
        }
        if (f != null) {
            this.A(f);
        }
    }
    
    private void D(final E e, final String s, final boolean b) {
        if (e.A(s, b)) {
            this.j = true;
            super.setChanged();
            super.notifyObservers(com.cc.B.F.A(e, s));
        }
    }
    
    private boolean C(final E e, final String s, final boolean b) {
        if (s.length() == 0 || b || e.L().equals(s)) {
            return true;
        }
        for (int i = 0; i < this.A; ++i) {
            for (int j = 0; j < this.D; ++j) {
                if (this.A(e, this.A(i, j, false), s)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean A(final E e, final E e2, final String s) {
        return !e.equals(e2) && !e2.S() && !e2.C() && e2.L().equals(s);
    }
    
    private boolean B(final E e, final String s, final boolean b) {
        if (s.length() == 0 || b) {
            return true;
        }
        if (s.length() > 1) {
            return false;
        }
        final Point c = this.C(e);
        final int n = c.x / this.T;
        final int n2 = c.y / this.M;
        for (int i = 0; i < this.T; ++i) {
            for (int j = 0; j < this.M; ++j) {
                if (this.B(e, this.A(n * this.T + i, n2 * this.M + j, false), s)) {
                    return false;
                }
            }
        }
        for (int k = 0; k < this.A; ++k) {
            if (this.B(e, this.A(k, c.y, false), s)) {
                return false;
            }
        }
        for (int l = 0; l < this.D; ++l) {
            if (this.B(e, this.A(c.x, l, false), s)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean B(final E e, final E e2, final String s) {
        return !e.equals(e2) && !e2.S() && !e2.C() && e2.L().equals(s);
    }
    
    public String B(final int n, final boolean b) {
        if (this.d) {
            return this.A(n, b);
        }
        if (n != 0 && !this.y) {
            return this.A(n == 2, b);
        }
        return this.C();
    }
    
    private String A(final int n, final boolean b) {
        String string = "";
        String string2 = "";
        for (int i = 0; i < this.¤.A(); ++i) {
            if (!((D)this.¤.A(i)).D()) {
                string2 += (char)(((D)this.¤.A(i)).B() ? 48 : 49);
                String s = string + '+';
                final Point c = this.C(((D)this.¤.A(i)).G());
                final Point c2 = this.C(((D)this.¤.A(i)).C());
                if (n != 0) {
                    s += this.A(((D)this.¤.A(i)).K(), b);
                }
                string = s + "(" + c.x + "," + c.y + "," + c2.x + "," + c2.y + ")";
            }
        }
        if (string.length() > 0) {
            return (n == 2) ? (string2 + string) : string.substring(1);
        }
        return "";
    }
    
    private String C() {
        String string = new String();
        for (int i = 0; i < this.D; ++i) {
            for (int j = 0; j < this.A; ++j) {
                final E a = this.A(j, i, true);
                if (a != null && a.B()) {
                    String s = a.S() ? "-" : a.L();
                    if (a.C()) {
                        s = "*" + s;
                    }
                    if (s.length() > 1) {
                        s = "|" + s + "|";
                    }
                    string += s;
                }
            }
        }
        return string;
    }
    
    private String A(final boolean b, final boolean b2) {
        String string = "";
        String string2 = "";
        for (int i = 0; i < this.¤.A(); ++i) {
            string = string + '+' + this.A(((D)this.¤.A(i)).K(), b2);
            string2 += (char)(((D)this.¤.A(i)).F() ? 49 : 48);
        }
        return b ? (string2 + string) : string.substring(1);
    }
    
    public D M() {
        return (this.¢ != null) ? this.¢ : this.k;
    }
    
    public void A(final D ¢) {
        if (this.M().equals(¢)) {
            return;
        }
        final D m = this.M();
        this.¢ = ¢;
        super.setChanged();
        super.notifyObservers(com.cc.B.F.A(m, ¢));
    }
    
    public D E(final D d) {
        for (int i = 0; i < this.¤.A(); ++i) {
            if (((D)this.¤.A(i)).A(d)) {
                return (D)this.¤.A(i);
            }
        }
        return null;
    }
    
    private void D(final D d) {
        this.¤.C(d);
    }
    
    public boolean R() {
        return this.w;
    }
    
    public void A(final boolean w) {
        this.P = false;
        this.w = w;
    }
    
    public D K() {
        for (int i = 0; i < this.¤.A(); ++i) {
            if (((D)this.¤.A(i)).A()) {
                return (D)this.¤.A(i);
            }
        }
        return null;
    }
    
    public E N() {
        return this.f;
    }
    
    public void C(final D d) {
        final D e = this.E(d);
        if (e != null) {
            if (e.B()) {
                this.D(e);
            }
            else {
                e.C(!e.D());
            }
        }
        else if (d.G() != d.C() || this.£) {
            this.B(d);
        }
        this.setChanged();
        this.notifyObservers(com.cc.B.F.A(d));
    }
    
    public void A(final Point point, final Point point2) {
        final B b = this.O.B;
        this.O.B = this.B(point, point2);
        final F a = com.cc.B.F.A(this.O);
        a.G.A(b);
        a.G.A(this.A(b));
        super.setChanged();
        this.notifyObservers(a);
    }
    
    public void D() {
        final B b = this.O.B;
        this.O.B = new B();
        final F a = com.cc.B.F.A(this.O);
        a.G.A(b);
        super.setChanged();
        this.notifyObservers(a);
    }
    
    public String A(final com.cc.C.A a, final boolean b) {
        return a.\u00c8 = this.B(a.r ? 1 : 0, b);
    }
    
    private static String A(final String s) {
        final StringBuffer sb = new StringBuffer(s.length());
        try {
            final byte[] bytes = s.getBytes("UTF8");
            for (int i = 0; i < bytes.length; ++i) {
                final byte b = bytes[i];
                if ((b >= 97 && b <= 122) || (b >= 65 && b <= 90) || (b >= 48 && b <= 57)) {
                    sb.append((char)b);
                }
                else {
                    sb.append("%");
                    sb.append(Integer.toHexString(b >> 4 & 0xF));
                    sb.append(Integer.toHexString(b & 0xF));
                }
            }
        }
        catch (Exception ex) {}
        return sb.toString();
    }
    
    private String A(final String s, final boolean b) {
        if (b) {
            return A(s);
        }
        return s;
    }
    
    public String A(final com.cc.C.A a, final String s, final int n, final boolean b) {
        final int index = s.indexOf("%SUBMIT%");
        String s2;
        if (index != -1) {
            s2 = s.substring(0, index) + this.A(a, b) + s.substring(index + 8);
        }
        else {
            s2 = s;
        }
        final int index2 = s2.indexOf("%PROGRESS%");
        if (index2 != -1) {
            s2 = s2.substring(0, index2) + this.B(0, b) + s2.substring(index2 + 10);
        }
        final int index3 = s2.indexOf("%MARKED%");
        if (index3 != -1) {
            s2 = s2.substring(0, index3) + this.B(2, b) + s2.substring(index3 + 8);
        }
        final int index4 = s2.indexOf("%CHARSET%");
        if (index4 != -1) {
            s2 = s2.substring(0, index4) + a.D + s2.substring(index4 + 9);
        }
        final int index5 = s2.indexOf("%TIME%");
        if (index5 != -1) {
            s2 = s2.substring(0, index5) + Integer.toString(n) + s2.substring(index5 + 6);
        }
        final int index6 = s2.indexOf("%KEYWORD%");
        if (index6 != -1 && this.K() != null) {
            s2 = s2.substring(0, index6) + this.K().K() + s2.substring(index6 + "%KEYWORD%".length());
        }
        return s2;
    }
}
