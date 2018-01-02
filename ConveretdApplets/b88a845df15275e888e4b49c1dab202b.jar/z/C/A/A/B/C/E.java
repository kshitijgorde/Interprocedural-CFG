// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.C;

import z.C.A.A.B.A.H;
import z.C.A.A.B.A.R;

public final class E implements R
{
    public static final int F = 0;
    public static final int M = 1;
    public static final int I = 2;
    static final char E = '\uffff';
    private boolean A;
    private boolean G;
    private boolean J;
    private boolean P;
    private boolean O;
    private char H;
    private int K;
    private int B;
    private int L;
    private char[] D;
    private int C;
    private int N;
    
    private static boolean A(final char c) {
        return c == '*' || c == '?' || c == '+' || c == '[' || c == ']' || c == '(' || c == ')' || c == '|' || c == '.';
    }
    
    static boolean D(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '_';
    }
    
    static boolean C(final char c) {
        return c >= 'a' && c <= 'z';
    }
    
    static boolean E(final char c) {
        return c >= 'A' && c <= 'Z';
    }
    
    static char B(final char c) {
        if (E(c)) {
            return (char)(c + ' ');
        }
        if (C(c)) {
            return (char)(c - ' ');
        }
        return c;
    }
    
    private void F(final char c) throws H {
        if (c == this.H) {
            if (this.B < this.L) {
                this.H = this.D[this.B++];
            }
            else {
                this.H = '\uffff';
            }
            return;
        }
        throw new H("token: " + c + " does not match lookahead: " + this.H + " at position: " + this.B);
    }
    
    private void A() {
        if (this.H != '\uffff') {
            --this.B;
        }
        this.H = this.D[this.B - 1];
    }
    
    private P B() throws H {
        final P g = this.G();
        if (this.H == '|') {
            this.F('|');
            return new M(g, this.B());
        }
        return g;
    }
    
    private P G() throws H {
        final P d = this.D();
        if (this.H == ')') {
            if (this.C > this.N) {
                return d;
            }
            throw new H("Parse error: close parenthesis without matching open parenthesis at position " + this.B);
        }
        else {
            if (this.H == '|' || this.H == '\uffff') {
                return d;
            }
            final z.C.A.A.B.C.R r2;
            z.C.A.A.B.C.R r = r2 = new z.C.A.A.B.C.R();
            r.K = d;
            while (true) {
                final P d2 = this.D();
                if (this.H == ')') {
                    if (this.C > this.N) {
                        r.L = d2;
                        break;
                    }
                    throw new H("Parse error: close parenthesis without matching open parenthesis at position " + this.B);
                }
                else {
                    if (this.H == '|' || this.H == '\uffff') {
                        r.L = d2;
                        break;
                    }
                    r.L = new z.C.A.A.B.C.R();
                    r = (z.C.A.A.B.C.R)r.L;
                    r.K = d2;
                }
            }
            return r2;
        }
    }
    
    private P D() throws H {
        final P c = this.C();
        switch (this.H) {
            case '+': {
                this.F('+');
                return new F(c);
            }
            case '?': {
                this.F('?');
                return new D(c);
            }
            case '*': {
                this.F('*');
                return new A(c);
            }
            case '{': {
                return this.A(c);
            }
            default: {
                return c;
            }
        }
    }
    
    private int A(final int n, final int n2, final int n3) throws H {
        int n4 = 0;
        final StringBuffer sb = new StringBuffer(4);
        while (Character.digit(this.H, n) != -1 && n4 < n3) {
            sb.append(this.H);
            this.F(this.H);
            ++n4;
        }
        if (n4 < n2 || n4 > n3) {
            throw new H("Parse error: unexpected number of digits at position " + this.B);
        }
        int int1;
        try {
            int1 = Integer.parseInt(sb.toString(), n);
        }
        catch (NumberFormatException ex) {
            throw new H("Parse error: numeric value at position " + this.B + " is invalid");
        }
        return int1;
    }
    
    private P A(P k) throws H {
        this.F('{');
        int a = this.A(10, 1, Integer.MAX_VALUE);
        final int[] array = { this.K };
        z.C.A.A.B.C.R r2;
        if (this.H == '}') {
            this.F('}');
            if (a == 0) {
                throw new H("Parse error: Superfluous interval specified at position " + this.B + ".  Number of occurences was set to zero.");
            }
            if (a == 1) {
                return k;
            }
            z.C.A.A.B.C.R r = r2 = new z.C.A.A.B.C.R();
            r.K = k;
            while (--a > 1) {
                k = k.A(array);
                r.L = new z.C.A.A.B.C.R();
                r = (z.C.A.A.B.C.R)r.L;
                r.K = k;
            }
            r.L = k.A(array);
        }
        else {
            if (this.H != ',') {
                throw new H("Parse error: unexpected character " + this.H + " in interval at position " + this.B);
            }
            this.F(',');
            if (this.H == '}') {
                this.F('}');
                if (a == 0) {
                    return new A(k);
                }
                if (a == 1) {
                    return new F(k);
                }
                z.C.A.A.B.C.R r3 = r2 = new z.C.A.A.B.C.R();
                r3.K = k;
                while (--a > 0) {
                    k = k.A(array);
                    r3.L = new z.C.A.A.B.C.R();
                    r3 = (z.C.A.A.B.C.R)r3.L;
                    r3.K = k;
                }
                r3.L = new A(k.A(array));
            }
            else {
                int a2 = this.A(10, 1, Integer.MAX_VALUE);
                this.F('}');
                if (a2 < a) {
                    throw new H("Parse error: invalid interval; " + a2 + " is less than " + a + " at position " + this.B);
                }
                if (a2 == 0) {
                    throw new H("Parse error: Superfluous interval specified at position " + this.B + ".  Number of occurences was set to zero.");
                }
                if (a == 0) {
                    if (a2 == 1) {
                        return new D(k);
                    }
                    z.C.A.A.B.C.R r4 = r2 = new z.C.A.A.B.C.R();
                    k = new D(k);
                    r4.K = k;
                    while (--a2 > 1) {
                        k = k.A(array);
                        r4.L = new z.C.A.A.B.C.R();
                        r4 = (z.C.A.A.B.C.R)r4.L;
                        r4.K = k;
                    }
                    r4.L = k.A(array);
                }
                else if (a == a2) {
                    if (a == 1) {
                        return k;
                    }
                    z.C.A.A.B.C.R r5 = r2 = new z.C.A.A.B.C.R();
                    r5.K = k;
                    while (--a > 1) {
                        k = k.A(array);
                        r5.L = new z.C.A.A.B.C.R();
                        r5 = (z.C.A.A.B.C.R)r5.L;
                        r5.K = k;
                    }
                    r5.L = k.A(array);
                }
                else {
                    z.C.A.A.B.C.R r6 = r2 = new z.C.A.A.B.C.R();
                    r6.K = k;
                    for (int i = 1; i < a; ++i) {
                        k = k.A(array);
                        r6.L = new z.C.A.A.B.C.R();
                        r6 = (z.C.A.A.B.C.R)r6.L;
                        r6.K = k;
                    }
                    k = new D(k.A(array));
                    int n = a2 - a;
                    if (n == 1) {
                        r6.L = k;
                    }
                    else {
                        r6.L = new z.C.A.A.B.C.R();
                        z.C.A.A.B.C.R r7 = (z.C.A.A.B.C.R)r6.L;
                        r7.K = k;
                        while (--n > 1) {
                            k = k.A(array);
                            r7.L = new z.C.A.A.B.C.R();
                            r7 = (z.C.A.A.B.C.R)r7.L;
                            r7.K = k;
                        }
                        r7.L = k.A(array);
                    }
                }
            }
        }
        this.K = array[0];
        return r2;
    }
    
    private P E() throws H {
        this.F('\\');
        P p;
        if (this.H == 'x') {
            this.F('x');
            p = this.A((char)this.A(16, 2, 2), this.K++);
        }
        else if (this.H == 'c') {
            this.F('c');
            final char upperCase = Character.toUpperCase(this.H);
            p = new B((char)((upperCase > '?') ? (upperCase - '@') : (upperCase + '@')), this.K++);
            this.F(this.H);
        }
        else if (this.H >= '0' && this.H <= '9') {
            this.F(this.H);
            if (this.H >= '0' && this.H <= '9') {
                this.A();
                p = this.A((char)Integer.parseInt(Integer.toString(this.A(10, 2, 3)), 8), this.K++);
            }
            else {
                this.A();
                if (this.H == '0') {
                    this.F('0');
                    p = new B('\0', this.K++);
                }
                else {
                    Character.digit(this.H, 10);
                    p = this.A(this.H, this.K++);
                    this.F(this.H);
                }
            }
        }
        else if (this.H == 'b') {
            p = new B('\b', this.K++);
            this.F('b');
        }
        else {
            char h = this.H;
            switch (this.H) {
                case 'n': {
                    h = '\n';
                    break;
                }
                case 'r': {
                    h = '\r';
                    break;
                }
                case 't': {
                    h = '\t';
                    break;
                }
                case 'f': {
                    h = '\f';
                    break;
                }
            }
            switch (h) {
                case 100: {
                    final N n = new N(this.K++);
                    n.A(48, 57);
                    p = n;
                    break;
                }
                case 68: {
                    final G g = new G(this.K++);
                    g.A(48, 57);
                    p = g;
                    break;
                }
                case 119: {
                    final N n2 = new N(this.K++);
                    n2.A(48, 57);
                    n2.A(97, 122);
                    n2.A(65, 90);
                    n2.A(95);
                    p = n2;
                    break;
                }
                case 87: {
                    final G g2 = new G(this.K++);
                    g2.A(48, 57);
                    g2.A(97, 122);
                    g2.A(65, 90);
                    g2.A(95);
                    p = g2;
                    break;
                }
                case 115: {
                    final N n3 = new N(this.K++);
                    n3.A(32);
                    n3.A(12);
                    n3.A(10);
                    n3.A(13);
                    n3.A(9);
                    p = n3;
                    break;
                }
                case 83: {
                    final G g3 = new G(this.K++);
                    g3.A(32);
                    g3.A(12);
                    g3.A(10);
                    g3.A(13);
                    g3.A(9);
                    p = g3;
                    break;
                }
                default: {
                    p = this.A(h, this.K++);
                    break;
                }
            }
            this.F(this.H);
        }
        return p;
    }
    
    private P C() throws H {
        P p;
        if (this.H == '(') {
            this.F('(');
            ++this.C;
            p = this.B();
            this.F(')');
            ++this.N;
        }
        else if (this.H == '[') {
            p = this.F();
        }
        else if (this.H == '.') {
            this.F('.');
            final G g = new G(this.K++);
            if (this.J) {
                g.A(10);
            }
            p = g;
        }
        else if (this.H == '\\') {
            p = this.E();
        }
        else {
            if (A(this.H)) {
                throw new H("Parse error: unexpected character " + this.H + " at position " + this.B);
            }
            p = this.A(this.H, this.K++);
            this.F(this.H);
        }
        return p;
    }
    
    private P F() throws H {
        this.F('[');
        this.A = true;
        N n;
        if (this.H == '^') {
            this.F('^');
            n = new G(this.K++);
        }
        else {
            n = new N(this.K++);
        }
        while (this.H != ']' && this.H != '\uffff') {
            char c2;
            if (this.H == '\\') {
                final P e = this.E();
                --this.K;
                if (!(e instanceof B)) {
                    final N n2 = (N)e;
                    for (char c = '\0'; c < '\u0100'; ++c) {
                        if (n2.A(c)) {
                            n.A((int)c);
                        }
                    }
                    continue;
                }
                c2 = ((B)e).F;
                n.A((int)c2);
                if (!this.G) {
                    n.A((int)B(c2));
                }
            }
            else {
                c2 = this.H;
                n.A((int)this.H);
                if (!this.G) {
                    n.A((int)B(this.H));
                }
                this.F(this.H);
            }
            if (this.H == '-') {
                this.F('-');
                if (this.H == ']') {
                    n.A(45);
                    break;
                }
                char c3;
                if (this.H == '\\') {
                    final P e2 = this.E();
                    --this.K;
                    if (!(e2 instanceof B)) {
                        throw new H("Parse error: invalid range specified at position " + this.B);
                    }
                    c3 = ((B)e2).F;
                }
                else {
                    c3 = this.H;
                    this.F(this.H);
                }
                if (c3 < c2) {
                    throw new H("Parse error: invalid range specified at position " + this.B);
                }
                n.A(c2 + '\u0001', c3);
                if (this.G) {
                    continue;
                }
                n.A(B((char)(c2 + '\u0001')), B(c3));
            }
        }
        this.F(']');
        this.A = false;
        return n;
    }
    
    P A(final char c, final int n) {
        if (!this.A && !this.G && (E(c) || C(c))) {
            final N n2 = new N(n);
            n2.A((int)c);
            n2.A((int)B(c));
            return n2;
        }
        return new B(c, n);
    }
    
    K B(final char[] d) throws H {
        final boolean b = false;
        this.N = (b ? 1 : 0);
        this.C = (b ? 1 : 0);
        this.D = d;
        this.B = 0;
        this.L = d.length;
        this.A = false;
        this.K = 0;
        this.F(this.H);
        if (this.H == '^') {
            this.P = true;
            this.F(this.H);
        }
        if (this.L > 0 && d[this.L - 1] == '$') {
            --this.L;
            this.O = true;
        }
        K k;
        if (this.L > 1 || (this.L == 1 && !this.P)) {
            final z.C.A.A.B.C.R r = new z.C.A.A.B.C.R();
            r.K = this.B();
            r.L = new B('\u0100', this.K++);
            k = new K(r, this.K);
        }
        else {
            k = new K(new B('\u0100', 0), 1);
        }
        k.B();
        return k;
    }
    
    public z.C.A.A.B.A.G A(final char[] array, final int k) throws H {
        final boolean b = false;
        this.O = b;
        this.P = b;
        this.G = ((k & 0x1) == 0x0);
        this.J = ((k & 0x2) != 0x0);
        final C c = new C(new String(array), this.B(array));
        c.K = k;
        c.L = this.P;
        c.Q = this.O;
        return c;
    }
    
    public z.C.A.A.B.A.G A(final String s, final int k) throws H {
        final boolean b = false;
        this.O = b;
        this.P = b;
        this.G = ((k & 0x1) == 0x0);
        this.J = ((k & 0x2) != 0x0);
        final C c = new C(s, this.B(s.toCharArray()));
        c.K = k;
        c.L = this.P;
        c.Q = this.O;
        return c;
    }
    
    public z.C.A.A.B.A.G A(final char[] array) throws H {
        return this.A(array, 0);
    }
    
    public z.C.A.A.B.A.G A(final String s) throws H {
        return this.A(s, 0);
    }
}
