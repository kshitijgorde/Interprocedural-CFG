// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.D;

import java.awt.Point;
import java.util.Enumeration;
import com.cc.C.B;

public class A
{
    private _B[] A;
    
    public A() {
        this(new _A[0]);
    }
    
    public A(final _A[] array) {
        this.A = new _B[] { new _B() };
        this.A[0].A = new B(array);
    }
    
    public A(final String s) {
        this(new _A[] { new _A(s) });
    }
    
    public _A[] C() {
        int n = 0;
        for (int i = 0; i < this.A.length; ++i) {
            n += this.A[i].A.A();
        }
        final _A[] array = new _A[n];
        int n2 = 0;
        for (int j = 0; j < this.A.length; ++j) {
            for (int k = 0; k < this.A[j].A.A(); ++k) {
                array[n2] = (_A)this.A[j].A.A(k);
                ++n2;
            }
        }
        return array;
    }
    
    public _B[] B() {
        return this.A;
    }
    
    public A A(final String s, final boolean b) {
        final B b2 = new B();
        for (int i = 0; i < this.A.length; ++i) {
            b2.A(this.A[i].A(s, b));
        }
        final A a = new A();
        a.A = new _B[b2.A()];
        for (int j = 0; j < a.A.length; ++j) {
            a.A[j] = (_B)b2.A(j);
        }
        return a;
    }
    
    public String A() {
        String string = "";
        for (int i = 0; i < this.A.length; ++i) {
            string += this.A[i].A();
        }
        return string;
    }
    
    public void A(final _A a) {
        this.A[this.A.length - 1].A.A(a);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.A.length; ++i) {
            sb.append(this.A[i].toString());
        }
        return sb.toString();
    }
    
    public static A A(final A.A.B b) {
        final A a = new A();
        A(b, a, new _A());
        return a;
    }
    
    public void B(final A.A.B b) {
        final _A[] c = this.C();
        for (int i = 0; i < c.length; ++i) {
            b.A((Object)c[i].toString());
        }
    }
    
    private static void A(final A.A.B b, final A a, final _A a2) {
        final _A a3 = new _A(a2);
        if (b.D().equals("b")) {
            a3.A(true);
        }
        else if (b.D().equals("i")) {
            a3.B(true);
        }
        else if (b.D().equals("sub")) {
            a3.A(_A.H);
        }
        else if (b.D().equals("sup")) {
            a3.A(_A.B);
        }
        final Enumeration c = b.C();
        if (!c.hasMoreElements()) {
            a3.A(b.E());
            a.A(a3);
        }
        else {
            while (c.hasMoreElements()) {
                A(c.nextElement(), a, a3);
            }
        }
    }
    
    public static class _A
    {
        public static int E;
        public static int B;
        public static int H;
        private String D;
        private boolean C;
        private boolean G;
        private int F;
        public Point A;
        
        public _A(final String d, final boolean c, final boolean g, final int f) {
            this.A = new Point();
            this.C = c;
            this.G = g;
            this.D = d;
            this.F = f;
        }
        
        public _A(final String s, final _A a) {
            this(s, a.C, a.G, a.F);
        }
        
        public _A(final String s) {
            this(s, false, false, _A.E);
        }
        
        public _A() {
            this("");
        }
        
        public _A(final _A a) {
            this(a.D, a.C, a.G, a.F);
        }
        
        public void A(final boolean c) {
            this.C = c;
        }
        
        public void B(final boolean g) {
            this.G = g;
        }
        
        public void A(final String d) {
            this.D = d;
        }
        
        public void A(final int f) {
            this.F = f;
        }
        
        public String A() {
            return this.D;
        }
        
        public boolean C() {
            return this.C;
        }
        
        public boolean D() {
            return this.G;
        }
        
        public int B() {
            return this.F;
        }
        
        public String toString() {
            String s = "";
            String s2 = "";
            if (this.C) {
                s += "<b>";
                s2 = "</b>" + s2;
            }
            if (this.G) {
                s += "<i>";
                s2 = "</i>" + s2;
            }
            if (!this.C && !this.G) {
                s = "<span>";
                s2 = "</span>";
            }
            return s + A.A.B.C(this.D) + s2;
        }
        
        static {
            _A.E = 0;
            _A.B = 1;
            _A.H = 2;
        }
    }
    
    public static class _B
    {
        public B A;
        
        public _B() {
            this.A = new B();
        }
        
        public String A() {
            String string = "";
            for (int i = 0; i < this.A.A(); ++i) {
                string += ((_A)this.A.A(i)).D;
            }
            return string;
        }
        
        public String toString() {
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < this.A.A(); ++i) {
                sb.append(this.A.A(i).toString());
            }
            return sb.toString();
        }
        
        public B A(final String s, final boolean b) {
            final B b2 = new B();
            _B b3 = new _B();
            b2.A(b3);
            for (int i = 0; i < this.A.A(); ++i) {
                final B a = this.A(this.A(i), s, b);
                b3.A.A(a.A(0));
                for (int j = 1; j < a.A(); ++j) {
                    b3 = new _B();
                    b3.A.A(a.A(j));
                    b2.A(b3);
                }
            }
            return b2;
        }
        
        private B A(final _A a, final String s, final boolean b) {
            final B b2 = new B();
            int i;
            int n;
            for (i = a.A().indexOf(s), n = (b ? 0 : (-s.length())); i > -1; i = a.A().indexOf(s, n + s.length())) {
                String s2;
                if (b) {
                    s2 = a.A().substring(n, i);
                }
                else {
                    s2 = a.A().substring(n + s.length(), i);
                }
                b2.A(new _A(s2, a));
                n = i;
            }
            String s3;
            if (b) {
                s3 = a.A().substring(n);
            }
            else {
                s3 = a.A().substring(n + s.length());
            }
            b2.A(new _A(s3, a));
            return b2;
        }
        
        public _A A(final int n) {
            return (_A)this.A.A(n);
        }
    }
}
