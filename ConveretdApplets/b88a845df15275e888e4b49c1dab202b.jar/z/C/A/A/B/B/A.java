// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.B;

import java.util.Vector;
import java.util.Collection;
import z.C.A.A.B.A.E;
import z.C.A.A.B.A.J;
import z.C.A.A.B.A.N;
import z.C.A.A.B.A.B;
import z.C.A.A.B.A.H;
import z.C.A.A.B.A.L;
import z.C.A.A.B.D;
import z.C.A.A.A.C;
import java.util.ArrayList;
import z.C.A.A.B.A.G;
import z.C.A.A.B.A.I;
import z.C.A.A.B.F;
import z.C.A.A.B.A.M;

public final class A implements M
{
    private static final String F = "m?(\\W)(.*)\\1([imsx]*)";
    private F I;
    private z.C.A.A.A.F N;
    private I K;
    private G O;
    private M M;
    private ArrayList L;
    private Object G;
    private int J;
    private int P;
    private static final String E = "";
    public static final int H = 0;
    
    public A(final F i) {
        this.L = new ArrayList();
        this.K = new I();
        this.I = i;
        this.N = new C(i.B());
        this.D();
    }
    
    public A() {
        this(new D());
    }
    
    private void D() {
        final L l = new L();
        try {
            this.O = l.A("m?(\\W)(.*)\\1([imsx]*)", 16);
        }
        catch (H h) {
            throw new RuntimeException(h.getMessage());
        }
    }
    
    private G A(final String s) throws z.C.A.A.B.B.C {
        final Object a = this.N.A(s);
        try {
            if (a != null) {
                return (G)a;
            }
        }
        catch (ClassCastException ex) {}
        if (!this.K.A(s, this.O)) {
            throw new z.C.A.A.B.B.C("Invalid expression: " + s);
        }
        final M a2 = this.K.A();
        final String d = a2.D(2);
        int n = 0;
        final String d2 = a2.D(3);
        if (d2 != null) {
            int length = d2.length();
            while (length-- > 0) {
                switch (d2.charAt(length)) {
                    case 'i': {
                        n |= 0x1;
                        continue;
                    }
                    case 'm': {
                        n |= 0x8;
                        continue;
                    }
                    case 's': {
                        n |= 0x10;
                        continue;
                    }
                    case 'x': {
                        n |= 0x20;
                        continue;
                    }
                    default: {
                        throw new z.C.A.A.B.B.C("Invalid options: " + d2);
                    }
                }
            }
        }
        final G a3 = this.I.A(d, n);
        this.N.A(s, a3);
        return a3;
    }
    
    public synchronized boolean A(final String s, final char[] g) throws z.C.A.A.B.B.C {
        this.A(s);
        final boolean c = this.K.C(g, this.A(s));
        if (c) {
            this.M = this.K.A();
            this.G = g;
            this.J = 0;
            this.P = g.length;
        }
        return c;
    }
    
    public synchronized boolean C(final String s, final String s2) throws z.C.A.A.B.B.C {
        return this.A(s, s2.toCharArray());
    }
    
    public synchronized boolean A(final String s, final B b) throws z.C.A.A.B.B.C {
        final boolean b2 = this.K.B(b, this.A(s));
        if (b2) {
            this.M = this.K.A();
            this.G = b.C();
            this.J = b.F();
            this.P = b.D();
        }
        return b2;
    }
    
    public synchronized M E() {
        return this.M;
    }
    
    public synchronized int A(final StringBuffer sb, final String s, final String s2) throws z.C.A.A.B.B.C {
        final Object a = this.N.A(s);
        Label_0072: {
            if (a != null) {
                z.C.A.A.B.B.B b;
                try {
                    b = (z.C.A.A.B.B.B)a;
                }
                catch (ClassCastException ex) {
                    break Label_0072;
                }
                final int a2 = z.C.A.A.B.A.F.A(sb, this.K, b.A, b.C, s2, b.B);
                this.M = this.K.A();
                return a2;
            }
        }
        final char[] charArray = s.toCharArray();
        if (charArray.length < 4 || charArray[0] != 's' || Character.isLetterOrDigit(charArray[1]) || charArray[1] == '-') {
            throw new z.C.A.A.B.B.C("Invalid expression: " + s);
        }
        final char c = charArray[1];
        final int n = 2;
        int n3;
        int n2 = n3 = -1;
        boolean b2 = false;
        for (int i = n; i < charArray.length; ++i) {
            if (charArray[i] == '\\') {
                b2 = !b2;
            }
            else {
                if (charArray[i] == c && !b2) {
                    n3 = i;
                    break;
                }
                if (b2) {
                    b2 = !b2;
                }
            }
        }
        if (n3 == -1 || n3 == charArray.length - 1) {
            throw new z.C.A.A.B.B.C("Invalid expression: " + s);
        }
        int n4 = 0;
        int n5 = 1;
        final StringBuffer sb2 = new StringBuffer(charArray.length - n3);
        for (int j = n3 + 1; j < charArray.length; ++j) {
            if (charArray[j] == '\\') {
                n4 = ((n4 == 0) ? 1 : 0);
                if (n4 != 0 && j + 1 < charArray.length && charArray[j + 1] == c && s.lastIndexOf(c, charArray.length - 1) != j + 1) {
                    n5 = 0;
                    continue;
                }
            }
            else {
                if (charArray[j] == c && n5 != 0) {
                    n2 = j;
                    break;
                }
                n4 = 0;
                n5 = 1;
            }
            sb2.append(charArray[j]);
        }
        if (n2 == -1) {
            throw new z.C.A.A.B.B.C("Invalid expression: " + s);
        }
        int n6 = 0;
        int n7 = 1;
        int n8;
        if (c != '\'') {
            n8 = 0;
        }
        else {
            n8 = -1;
        }
        for (int k = n2 + 1; k < charArray.length; ++k) {
            switch (charArray[k]) {
                case 'i': {
                    n6 |= 0x1;
                    break;
                }
                case 'm': {
                    n6 |= 0x8;
                    break;
                }
                case 's': {
                    n6 |= 0x10;
                    break;
                }
                case 'x': {
                    n6 |= 0x20;
                    break;
                }
                case 'g': {
                    n7 = -1;
                    break;
                }
                case 'o': {
                    n8 = 1;
                    break;
                }
                default: {
                    throw new z.C.A.A.B.B.C("Invalid option: " + charArray[k]);
                }
            }
        }
        final G a3 = this.I.A(new String(charArray, n, n3 - n), n6);
        final E e = new E(sb2.toString(), n8);
        this.N.A(s, new z.C.A.A.B.B.B(a3, e, n7));
        final int a4 = z.C.A.A.B.A.F.A(sb, this.K, a3, e, s2, n7);
        this.M = this.K.A();
        return a4;
    }
    
    public synchronized String B(final String s, final String s2) throws z.C.A.A.B.B.C {
        final StringBuffer sb = new StringBuffer();
        this.A(sb, s, s2);
        return sb.toString();
    }
    
    public synchronized void A(final Collection collection, final String s, final String s2, int n) throws z.C.A.A.B.B.C {
        M a = null;
        final G a2 = this.A(s);
        final B b = new B(s2);
        int b2 = 0;
        while (--n != 0 && this.K.B(b, a2)) {
            a = this.K.A();
            this.L.add(s2.substring(b2, a.C(0)));
            final int a3;
            if ((a3 = a.A()) > 1) {
                for (int i = 1; i < a3; ++i) {
                    final String d = a.D(i);
                    if (d != null && d.length() > 0) {
                        this.L.add(d);
                    }
                }
            }
            b2 = a.B(0);
        }
        this.L.add(s2.substring(b2, s2.length()));
        for (int n2 = this.L.size() - 1; n2 >= 0 && ((String)this.L.get(n2)).length() == 0; --n2) {
            this.L.remove(n2);
        }
        collection.addAll(this.L);
        this.L.clear();
        this.M = a;
    }
    
    public synchronized void A(final Collection collection, final String s, final String s2) throws z.C.A.A.B.B.C {
        this.A(collection, s, s2, 0);
    }
    
    public synchronized void A(final Collection collection, final String s) throws z.C.A.A.B.B.C {
        this.A(collection, "/\\s+/", s);
    }
    
    public synchronized Vector A(final String s, final String s2, final int n) throws z.C.A.A.B.B.C {
        final Vector vector = new Vector(20);
        this.A(vector, s, s2, n);
        return vector;
    }
    
    public synchronized Vector A(final String s, final String s2) throws z.C.A.A.B.B.C {
        return this.A(s, s2, 0);
    }
    
    public synchronized Vector B(final String s) throws z.C.A.A.B.B.C {
        return this.A("/\\s+/", s);
    }
    
    public synchronized int B() {
        return this.M.B();
    }
    
    public synchronized int A() {
        return this.M.A();
    }
    
    public synchronized String D(final int n) {
        return this.M.D(n);
    }
    
    public synchronized int E(final int n) {
        return this.M.E(n);
    }
    
    public synchronized int A(final int n) {
        return this.M.A(n);
    }
    
    public synchronized int C(final int n) {
        return this.M.C(n);
    }
    
    public synchronized int B(final int n) {
        return this.M.B(n);
    }
    
    public synchronized String toString() {
        if (this.M == null) {
            return null;
        }
        return this.M.toString();
    }
    
    public synchronized String C() {
        if (this.G == null) {
            return "";
        }
        int n = this.M.C(0);
        if (n <= 0) {
            return "";
        }
        if (this.G instanceof char[]) {
            final char[] array = (char[])this.G;
            if (n > array.length) {
                n = array.length;
            }
            return new String(array, this.J, n);
        }
        if (this.G instanceof String) {
            final String s = (String)this.G;
            if (n > s.length()) {
                n = s.length();
            }
            return s.substring(this.J, n);
        }
        return "";
    }
    
    public synchronized String G() {
        if (this.G == null) {
            return "";
        }
        final int b = this.M.B(0);
        if (b < 0) {
            return "";
        }
        if (this.G instanceof char[]) {
            final char[] array = (char[])this.G;
            if (b >= array.length) {
                return "";
            }
            return new String(array, b, this.P - b);
        }
        else {
            if (!(this.G instanceof String)) {
                return "";
            }
            final String s = (String)this.G;
            if (b >= s.length()) {
                return "";
            }
            return s.substring(b, this.P);
        }
    }
    
    public synchronized char[] F() {
        char[] array = null;
        if (this.G == null) {
            return null;
        }
        int n = this.M.C(0);
        if (n <= 0) {
            return null;
        }
        if (this.G instanceof char[]) {
            final char[] array2 = (char[])this.G;
            if (n >= array2.length) {
                n = array2.length;
            }
            array = new char[n - this.J];
            System.arraycopy(array2, this.J, array, 0, array.length);
        }
        else if (this.G instanceof String) {
            final String s = (String)this.G;
            if (n >= s.length()) {
                n = s.length();
            }
            array = new char[n - this.J];
            s.getChars(this.J, n, array, 0);
        }
        return array;
    }
    
    public synchronized char[] H() {
        char[] array = null;
        if (this.G == null) {
            return null;
        }
        final int b = this.M.B(0);
        if (b < 0) {
            return null;
        }
        if (this.G instanceof char[]) {
            final char[] array2 = (char[])this.G;
            if (b >= array2.length) {
                return null;
            }
            final int n = this.P - b;
            array = new char[n];
            System.arraycopy(array2, b, array, 0, n);
        }
        else if (this.G instanceof String) {
            final String s = (String)this.G;
            if (b >= this.P) {
                return null;
            }
            array = new char[this.P - b];
            s.getChars(b, this.P, array, 0);
        }
        return array;
    }
}
