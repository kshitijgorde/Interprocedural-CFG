import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

final class e
{
    private char[] N;
    private int O;
    private int P;
    private boolean Q;
    private boolean R;
    private boolean S;
    private boolean T;
    private boolean U;
    private int V;
    private c[] W;
    
    public e(String s) {
        if (s == null) {
            s = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        }
        this.c(s);
        this.P = 0;
        this.O = 0;
        this.W = new c[600];
        if (!this.Q) {
            this.Q = true;
            this.a("--", null);
        }
    }
    
    private int c(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        if (stringTokenizer.countTokens() < 4 || stringTokenizer.countTokens() > 6) {
            return -1;
        }
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            switch (n) {
                case 0: {
                    if (!this.d(stringTokenizer.nextToken())) {
                        return -1;
                    }
                    break;
                }
                case 1: {
                    this.Q = stringTokenizer.nextToken().equals("w");
                    break;
                }
                case 2: {
                    if (!this.e(stringTokenizer.nextToken())) {
                        return -1;
                    }
                    break;
                }
                case 3: {
                    final String nextToken = stringTokenizer.nextToken();
                    if (nextToken.charAt(0) != '-') {
                        this.V = ('\b' - (nextToken.charAt(1) - '0')) * '\b' + (nextToken.charAt(0) - 'a');
                        break;
                    }
                    this.V = 0;
                    break;
                }
                default: {
                    stringTokenizer.nextToken();
                    break;
                }
            }
            ++n;
        }
        return 0;
    }
    
    private boolean d(final String s) {
        final char[] n = new char[64];
        int n2 = 0;
        for (int n3 = 0; n3 < s.length() && n2 < 64; ++n3) {
            final char char1 = s.charAt(n3);
            if (Character.isDigit(char1)) {
                for (int i = Character.digit(char1, 10); i > 0; --i) {
                    n[n2++] = '-';
                }
            }
            else if (Character.isLetter(char1)) {
                n[n2++] = char1;
            }
        }
        if (n2 == 64) {
            this.N = n;
            return true;
        }
        return false;
    }
    
    private boolean e(final String s) {
        this.R = false;
        this.S = false;
        this.T = false;
        this.U = false;
        for (int i = 0; i < s.length(); ++i) {
            switch (s.charAt(i)) {
                case 'K': {
                    this.R = true;
                    break;
                }
                case 'Q': {
                    this.S = true;
                    break;
                }
                case 'k': {
                    this.T = true;
                    break;
                }
                case 'q': {
                    this.U = true;
                    break;
                }
                case '-': {
                    break;
                }
                default: {
                    return false;
                }
            }
        }
        return true;
    }
    
    public char[] C() {
        return this.N;
    }
    
    public String D() {
        final StringBuffer sb = new StringBuffer("");
        b b;
        if (this.O == this.P) {
            b = this.d(this.O - 1);
        }
        else {
            b = this.J();
        }
        int n2;
        int n = n2 = 0;
        while (true) {
            if (n2 % 8 == 0 || this.N[n2] != '-') {
                if (n > 0) {
                    sb.append(n);
                    n = 0;
                }
                if (n2 == 64) {
                    break;
                }
            }
            if (n2 > 0 && n2 % 8 == 0) {
                sb.append('/');
            }
            if (this.N[n2] != '-') {
                sb.append(this.N[n2]);
            }
            else {
                ++n;
            }
            ++n2;
        }
        sb.append(' ');
        if (this.F() == 0 && b.j()) {
            sb.append('b');
        }
        else if (this.H()) {
            sb.append('w');
        }
        else {
            sb.append('b');
        }
        sb.append(' ');
        sb.append(this.E());
        sb.append(' ');
        if (this.V > 0) {
            b.a(sb, this.V);
        }
        else {
            sb.append('-');
        }
        return sb.toString();
    }
    
    private String E() {
        final StringBuffer sb = new StringBuffer("");
        if (this.R) {
            sb.append('K');
        }
        if (this.S) {
            sb.append('Q');
        }
        if (this.T) {
            sb.append('k');
        }
        if (this.U) {
            sb.append('q');
        }
        if (sb.length() == 0) {
            sb.append('-');
        }
        return sb.toString();
    }
    
    public int F() {
        return this.O;
    }
    
    public int G() {
        return this.P;
    }
    
    public boolean H() {
        return this.Q;
    }
    
    public Object I() {
        return this.W[0];
    }
    
    public String c(final int n) {
        if (n > this.P) {
            return "error";
        }
        return this.d(n).c();
    }
    
    public b J() {
        return this.d(this.O);
    }
    
    public b d(final int n) {
        return this.W[n].o;
    }
    
    public void b(final StringBuffer sb, final int n) {
        sb.append(n / 2 + 1);
        sb.append('.');
        if (n % 2 == 1) {
            sb.append("..");
        }
    }
    
    private void a(final StringBuffer sb, String s, final boolean b, final c c, final int n, final int n2) {
        boolean b2 = false;
        if (c.r != null) {
            final int length = sb.length();
            if (length > 1 && sb.charAt(length - 1) == ')') {
                sb.append('\n');
                s = s.substring(0, s.length() - 1);
                sb.append(s);
                b2 = true;
            }
        }
        else if (n2 < 600) {
            sb.append('\n');
        }
        if (b) {
            final StringBuffer sb2 = new StringBuffer("");
            if ((c.r != null && (n2 == 600 || n % 2 == 0)) || b2) {
                this.b(sb2, n);
            }
            final String a = c.o.a(sb2.toString(), c.r != null || n2 < 600);
            if (a.length() > 0) {
                sb.append(a);
                sb.append(' ');
            }
        }
        if (c.r == null) {
            if (sb.charAt(sb.length() - 1) == ' ') {
                sb.setLength(sb.length() - 1);
            }
            if (n2 >= 600) {
                sb.append(')');
                if (n2 > 600) {
                    return;
                }
            }
        }
        if (n2 == 600) {
            if (c.r != null) {
                this.a(sb, s, true, c.r, n + 1, n2 + 1);
            }
            if (sb.charAt(sb.length() - 1) == ')') {
                s = s.substring(0, s.length() - 1);
            }
        }
        if (c.q != null) {
            sb.append('\n');
            s += "\t";
            sb.append(s);
            sb.append('(');
            this.a(sb, s, true, c.q, n, 600);
        }
        if (n2 == 600) {
            return;
        }
        if (c.r != null) {
            this.a(sb, s, true, c.r, n + 1, n2 + 1);
        }
    }
    
    public void a(final StringBuffer sb) {
        final StringBuffer sb2 = new StringBuffer("");
        String substring = "";
        boolean b;
        if (this.d(0).j()) {
            final String a = this.d(0).a("", false);
            if (a.length() > 0) {
                sb2.append(a);
                sb2.append(" ");
            }
            this.b(sb2, 1);
            b = false;
        }
        else {
            b = true;
        }
        this.a(sb2, "", b, this.W[0].t(), 0, 0);
        sb2.append('\n');
        final String string = sb2.toString();
        int n = 0;
        int n2 = 0;
        while (true) {
            int n3 = string.indexOf(10, n2);
            if (n3 == -1) {
                break;
            }
            if (n == 0) {
                if (Character.isWhitespace(string.charAt(n2))) {
                    int n4 = n2;
                    while (Character.isWhitespace(string.charAt(++n4))) {}
                    substring = string.substring(n2, n4);
                }
                else {
                    substring = "";
                }
            }
            if (n3 - n2 > 101) {
                n3 = string.lastIndexOf(" ", n2 + 100);
                n = 1;
            }
            else {
                n = 0;
            }
            sb.append(substring);
            sb.append(string.substring(n2, n3).trim());
            sb.append(System.getProperty("line.separator"));
            n2 = n3 + 1;
        }
    }
    
    public boolean e(final int n) {
        if (n >= this.P) {
            return false;
        }
        final c c = this.W[n];
        return c != c.t();
    }
    
    public int f(final int n) {
        if (n >= this.P) {
            return 0;
        }
        c c;
        int n2;
        for (c = this.W[n].t(), n2 = 0; c.q != null; c = c.q, ++n2) {}
        return n2;
    }
    
    public int g(final int n) {
        if (n >= this.P) {
            return -1;
        }
        c c;
        c c2;
        int n2;
        for (c = this.W[n], c2 = c.t(), n2 = 0; c2 != c; c2 = c2.q, ++n2) {}
        return n2;
    }
    
    public synchronized String[] h(final int n) {
        final int f = this.f(n);
        if (f == 0) {
            return new String[0];
        }
        c c = this.W[n].t();
        final String[] array = new String[f + 1];
        for (int n2 = 0; c != null; c = c.q, ++n2) {
            array[n2] = c.o.c();
        }
        return array;
    }
    
    public boolean b(final Object o) {
        c r = (c)o;
        this.P = 0;
        while (r != null) {
            this.W[this.P++] = r;
            r = r.r;
        }
        return ((c)o).u();
    }
    
    public void i(final int n) {
        final int f = this.F();
        if (this.f(f) == 0) {
            return;
        }
        c c = this.W[f].t();
        for (int i = 0; i < n; ++i) {
            c = c.q;
        }
        this.P = f;
        while (c != null) {
            this.W[this.P++] = c;
            c = c.r;
        }
    }
    
    private void j(final int n) {
        this.W[n - 1] = this.W[this.O - 1].p;
        this.k(n - 1);
        final b j = this.J();
        if (j.g() || j.h()) {
            this.Q = !this.Q;
        }
        else {
            this.l(j.a(this.N));
        }
        this.P = n;
        this.O = n;
    }
    
    public void k(final int o) {
        if (o > this.P) {
            return;
        }
        b b;
        if ((this.O = o) == this.P) {
            b = this.d(o - 1);
        }
        else {
            b = this.J();
        }
        this.N = b.e().clone();
        this.l(b.f());
    }
    
    private boolean a(final String s, final String s2) {
        return this.a(s, false, s2);
    }
    
    private boolean a(final String s, final boolean b, final String s2) {
        if (b) {
            if (this.O < 1 || this.P < 1) {
                return true;
            }
            this.k(this.O - 1);
        }
        else if (this.O != this.P) {
            return true;
        }
        final b b2 = new b(this.N.clone(), this.M());
        b2.b(s);
        if (s2 != null && s2.length() > 0) {
            b2.a(-1);
            b2.a(s2);
        }
        this.a(b2, b);
        if (b2.g()) {
            this.Q = !this.Q;
            return true;
        }
        if (b2.h()) {
            this.Q = !this.Q;
            return false;
        }
        this.l(b2.a(this.N));
        return false;
    }
    
    public boolean a(final boolean b) {
        int i = this.O - 1;
        if (i < 0) {
            return false;
        }
        final c p = this.W[i].p;
        if (p != null) {
            while (i != 0) {
                if (p != this.W[--i].p) {
                    this.k(++i);
                    return true;
                }
            }
            this.k(0);
            return true;
        }
        if (b) {
            this.k(0);
            return true;
        }
        return false;
    }
    
    public synchronized boolean K() {
        final int o = this.O;
        if (this.f(o) == 0 || this.g(o) == 0) {
            return false;
        }
        c p = this.W[o].p;
        c q;
        while (true) {
            q = p.q;
            if (q == this.W[o]) {
                break;
            }
            p = q;
        }
        this.i(this.g(o) - 1);
        p.q = q.q;
        if (q.q != null) {
            q.q.a(p, q);
        }
        return true;
    }
    
    public synchronized boolean L() {
        final int o = this.O;
        if (this.f(o) == 0 || this.g(o) == 0) {
            return false;
        }
        c t;
        c q = t = this.W[o].t();
        int n = 0;
        c q2;
        while (true) {
            q2 = t.q;
            if (q2 == this.W[o]) {
                break;
            }
            t = q2;
            if (q.q != t) {
                q = q.q;
            }
            ++n;
        }
        q2.c(t.p);
        t.c(q2);
        t.q = q2.q;
        if (q == (q2.q = t)) {
            if (o > 0) {
                this.W[o - 1].r = q2;
            }
        }
        else {
            q.q = q2;
        }
        this.i(n);
        return true;
    }
    
    public int a(final int n, final int n2, final char c) {
        final char[] array = this.N.clone();
        final StringBuffer sb = new StringBuffer("");
        if (this.O == 0 && this.J().j()) {
            return -1;
        }
        final b o = new b(array, this.M());
        o.a(sb, n);
        o.a(sb, n2);
        if ((array[n] == 'P' && n2 < 8) || (array[n] == 'p' && n2 > 55)) {
            sb.append(c);
        }
        o.b(sb.toString());
        if (o.g()) {
            return -1;
        }
        if (this.O == this.P) {
            this.k(this.P - 1);
        }
        c c2;
        boolean b;
        if (this.O != this.P - 1) {
            c2 = null;
            b = true;
            this.P = this.O + 1;
        }
        else {
            c2 = this.W[this.O];
            b = false;
            ++this.O;
        }
        this.a(o, b);
        this.l(o.a(this.N));
        if (!b) {
            c2.o.a(this.N.clone(), this.M());
            this.W[this.P - 1].o = c2.o;
            c2.o = o;
        }
        else {
            this.a("*", null);
        }
        this.k(this.P - 1);
        return this.P - 1;
    }
    
    public int a(final Object o, final boolean b) {
        c c = (c)o;
        if (b) {
            c = c.r;
        }
        if (this.O == this.P) {
            this.k(this.O - 1);
        }
        c r = this.W[this.O];
        if (this.O == this.P - 1) {
            --this.P;
            if (this.O > 0 && this.W[this.O - 1].p != r.p) {
                c p2;
                for (p2 = r.p; p2.q != r; p2 = p2.q) {}
                c.c(p2);
                p2.q = c;
                if (r.q != null) {
                    r.q.a(c, r);
                    c.a(r.q);
                }
                r.p = p2;
                r.q = null;
            }
            else if (this.P > 0) {
                final c c2 = this.W[this.P - 1];
                (c2.r = c).c(c2.p);
            }
            else {
                c.c(null);
            }
        }
        else {
            this.P = this.O;
            r.a(c);
            c.c(r);
            r = null;
        }
        final int n = this.P + 1;
        while (c != null) {
            this.W[this.P++] = c;
            c = c.r;
        }
        this.k(this.P - (this.W[this.P - 1].o.h() ? 2 : 1));
        this.l(this.J().a(this.N));
        if (this.O == this.P - 1) {
            if (r != null) {
                r.o.a(this.N.clone(), this.M());
                this.W[this.P++ - 1].r = r;
                this.W[this.P - 1] = r;
            }
            else {
                ++this.O;
                this.a("*", null);
            }
        }
        this.k(this.P - 1);
        return n;
    }
    
    public boolean f(String s) {
        StringBuffer sb = new StringBuffer("");
        int n = 0;
        int n2 = 0;
        String s2 = "";
        boolean b = false;
        boolean b2 = false;
        int n3 = 0;
        final int[] array = new int[20];
        s = s.trim();
        s += " ";
        for (int i = 0; i < s.length(); ++i) {
            if (n != 0 || Character.isWhitespace(s.charAt(i))) {
                if (s2.length() > 0 && n2 != 0) {
                    sb.append(s2);
                    if (n == 0) {
                        sb.append(' ');
                    }
                }
                else if (s2.equals("*") || s2.equals("--") || (s2.length() > 1 && (Character.isLetter(s2.charAt(0)) || s2.indexOf("0-0-0") == 0 || s2.indexOf("0-0") == 0 || s2.equals("1-0") || s2.equals("0-1") || s2.equals("1/2-1/2")))) {
                    b2 |= this.a(s2, b, sb.toString());
                    sb = new StringBuffer("");
                    b = false;
                }
                else if (s2.length() > 1 && s2.charAt(0) == '$') {
                    int int1;
                    try {
                        int1 = Integer.parseInt(s2.substring(1));
                    }
                    catch (Exception ex) {
                        int1 = 0;
                    }
                    this.d(this.F() - 1).a(int1);
                }
                s2 = "";
                n = 0;
            }
            else if (s2.length() > 0 && (s.charAt(i) == '{' || s.charAt(i) == '}')) {
                n = 1;
                i -= 2;
            }
            else if (s.charAt(i) == '{') {
                n2 = 1;
            }
            else if (s.charAt(i) == '}') {
                if (sb.length() > 0 && !b && this.F() > 0) {
                    this.d(this.F() - 1).a(sb.toString());
                    sb = new StringBuffer("");
                }
                n2 = 0;
            }
            else if (n2 != 0) {
                s2 += s.charAt(i);
            }
            else if (s2.length() > 0 && (s.charAt(i) == '(' || s.charAt(i) == ')')) {
                n = 1;
                i -= 2;
            }
            else if (s.charAt(i) == '(') {
                array[n3++] = this.F();
                b = true;
            }
            else if (s.charAt(i) == ')') {
                if (!this.d(this.F() - 1).h()) {
                    b2 |= this.a("*", b, sb.toString());
                }
                this.j(array[--n3]);
                sb = new StringBuffer("");
                b = false;
            }
            else if (s.charAt(i) == '.') {
                s2 = "";
            }
            else {
                s2 += s.charAt(i);
            }
        }
        return b2;
    }
    
    private void a(final b b, final boolean b2) {
        final c r = new c(b);
        if (this.P > 0) {
            final c p2 = this.W[this.P - 1];
            if (b2) {
                (r.p = p2).a(r);
            }
            else {
                r.p = p2.p;
                p2.r = r;
            }
        }
        this.W[this.P - (b2 ? 1 : 0)] = r;
        this.P = ++this.O;
    }
    
    private int M() {
        int n = this.V << 1;
        if (this.Q) {
            ++n;
        }
        int n2 = n << 1;
        if (this.R) {
            ++n2;
        }
        int n3 = n2 << 1;
        if (this.S) {
            ++n3;
        }
        int n4 = n3 << 1;
        if (this.T) {
            ++n4;
        }
        int n5 = n4 << 1;
        if (this.U) {
            ++n5;
        }
        return n5;
    }
    
    private void l(int n) {
        this.U = ((n & 0x1) == 0x1);
        n >>= 1;
        this.T = ((n & 0x1) == 0x1);
        n >>= 1;
        this.S = ((n & 0x1) == 0x1);
        n >>= 1;
        this.R = ((n & 0x1) == 0x1);
        n >>= 1;
        this.Q = ((n & 0x1) == 0x1);
        this.V = n >> 1;
    }
}
