// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ah
{
    z d;
    char[] do;
    int r;
    char[] int;
    int if;
    int K;
    char[] C;
    int n;
    static final int x = 0;
    static final int t = 1;
    static final int char = 2;
    static final int l = 3;
    static final int a = 4;
    static final int J = 5;
    static final int p = 6;
    static final int s = 7;
    static final int k = 8;
    static final int else = 9;
    static final char[] F;
    static final char[] B;
    static final char[] H;
    static final char[] y;
    static final char[] I;
    static final char[] A;
    static final char[] o;
    static final char[] z;
    static final char[] b;
    static final char[] new;
    static final char[] long;
    static final int j = 0;
    static final int q = 1;
    static final int m = 2;
    static final int i = 3;
    static final int goto = 4;
    static final int g = 5;
    static final int u = 6;
    static final int h = 7;
    static final int L = 8;
    static final int f = 9;
    static final int M = 10;
    static final int E = 11;
    static final int void = 12;
    static final int for = 13;
    static final int w = 14;
    static final int e = 15;
    static final int v = 16;
    static final int c = 17;
    static final int G = 18;
    static final int try = 19;
    static final int byte = 20;
    static final int case = 21;
    static final int D = 22;
    
    static {
        F = new char[] { 'n', 'u', 'l', 'l' };
        B = new char[] { 't', 'r', 'u', 'e' };
        H = new char[] { 'f', 'a', 'l', 's', 'e' };
        y = new char[] { 'i', 'f' };
        I = new char[] { 'e', 'l', 's', 'e' };
        A = new char[] { 'w', 'h', 'i', 'l', 'e' };
        o = new char[] { 'b', 'r', 'e', 'a', 'k' };
        z = new char[] { 'r', 'e', 't', 'u', 'r', 'n' };
        b = new char[] { 'v', 'a', 'r' };
        new = new char[] { 'g', 'l', 'o', 'b', 'a', 'l', 's' };
        long = new char[] { 'f', 'u', 'n', 'c', 't', 'i', 'o', 'n' };
    }
    
    ah(final char[] int1) {
        this.r = 0;
        this.if = 0;
        this.n = 0;
        this.do = int1;
        this.C = int1;
        this.int = int1;
        this.d = new z();
        this.K = 1;
    }
    
    int new() {
        return this.K;
    }
    
    int a() {
        return this.n;
    }
    
    int char() {
        return this.r;
    }
    
    char[] goto() {
        return this.do;
    }
    
    void try() throws ar {
        if (this.do[this.r] == '\0') {
            throw new ar("unexpected end of file");
        }
    }
    
    boolean else() {
        final char c = this.do[this.r];
        return c == ' ' || c == '(' || c == ')' || c == '{' || c == '}' || c == '\"' || c == '\r' || c == '\n' || c == '\t' || (c == '!' || c == ';' || c == ',' || c == '+' || c == '-' || c == '<' || c == '>' || c == '/' || c == '*') || (c == '\'' || c == '&' || c == '|' || c == '=' || c == '\0') || (c == '[' || c == ']');
    }
    
    void for() throws ar {
        while (this.do[this.r] == ' ' || this.do[this.r] == '\t' || this.do[this.r] == '\n' || this.do[this.r] == '\r' || (this.do[this.r] == '/' && this.do[this.r + 1] == '*')) {
            if (this.do[this.r] == '/') {
                this.r += 2;
                do {
                    this.try();
                    while (this.do[this.r] != '*') {
                        ++this.r;
                        this.try();
                    }
                    ++this.r;
                } while (this.do[this.r] != '/');
            }
            if (this.do[this.r] == '\n') {
                ++this.K;
                this.n = this.r + 1;
            }
            ++this.r;
        }
    }
    
    boolean byte() throws ar {
        if (this.do[this.r] == '\"') {
            this.d.do[0] = '\0';
            this.d.for = 8;
            ++this.r;
            int n = 0;
            while (this.do[this.r] != '\"') {
                this.try();
                if (this.do[this.r] == '\r' || this.do[this.r] == '\n') {
                    throw new ar("unexpected end of line");
                }
                if (this.do[this.r] == '\\') {
                    ++this.r;
                    this.try();
                    this.d.do[n++] = this.a(this.do[this.r]);
                }
                else {
                    this.d.do[n++] = this.do[this.r];
                }
                if (n == 1023) {
                    throw new ar("String too long");
                }
                ++this.r;
            }
            this.d.do[n] = '\0';
            return true;
        }
        return false;
    }
    
    char a(final char c) throws ar {
        switch (c) {
            case '0': {
                return '\0';
            }
            case 'b': {
                return '\b';
            }
            case 'f': {
                return '\f';
            }
            case 'r': {
                return '\r';
            }
            case '\'': {
                return '\'';
            }
            case '?': {
                return '?';
            }
            case 'n': {
                return '\n';
            }
            case 't': {
                return '\t';
            }
            case '\"': {
                return '\"';
            }
            case '\\': {
                return '\\';
            }
            default: {
                throw new ar("Invalid escape character");
            }
        }
    }
    
    boolean do() throws ar {
        if (this.do[this.r] == '\'') {
            ++this.r;
            if (this.do[this.r] == '\\') {
                ++this.r;
                this.d.if = this.a(this.do[this.r]);
            }
            else {
                this.d.if = this.do[this.r];
            }
            ++this.r;
            if (this.do[this.r] != '\'') {
                throw new ar(" ' character expected.");
            }
            this.d.for = 6;
            return true;
        }
        else {
            if (!Character.isDigit(this.do[this.r])) {
                return false;
            }
            int n = 0;
            do {
                this.d.do[n] = this.do[this.r];
                ++n;
                ++this.r;
                if (n == 1023) {
                    throw new ar("bad number representation");
                }
            } while (!this.else());
            this.d.do[n] = '\0';
            --this.r;
            if (this.d.do.length <= 2 || this.d.do[0] != '0' || this.d.do[1] != 'x') {
                int n2 = 1;
                for (int i = 0; i < n - 1; ++i) {
                    if (!Character.isDigit(this.d.do[i])) {
                        if (this.d.do[i] != '.' || n2 == 0) {
                            throw new ar("bad number representation");
                        }
                        n2 = 0;
                    }
                }
                if (n2 != 0) {
                    this.d.if = Long.parseLong(new String(this.d.do, 0, n));
                    this.d.for = 6;
                }
                else {
                    this.d.int = new Double(new String(this.d.do, 0, a.a.a.a.g.a(this.d.do)));
                    this.d.for = 7;
                }
                return true;
            }
            if (n > 2 && n < 11) {
                this.d.if = 0L;
                for (int j = 2; j < n; ++j) {
                    final char c = this.d.do[j];
                    if (Character.isDigit(c)) {
                        this.d.if = this.d.if * 16L + (c - '0');
                    }
                    else if (Character.isUpperCase(c) && c < 'G') {
                        this.d.if = this.d.if * 16L + (c - '7');
                    }
                    else {
                        if (!Character.isLowerCase(c) || c >= 'g') {
                            throw new ar("bad number representation");
                        }
                        this.d.if = this.d.if * 16L + (c - 'W');
                    }
                }
                this.d.for = 6;
                return true;
            }
            throw new ar("bad number representation");
        }
    }
    
    boolean case() throws ar {
        if (Character.isLetter(this.do[this.r]) || this.do[this.r] == '_') {
            int n = 0;
            do {
                this.d.do[n++] = this.do[this.r++];
                if (n == 1023) {
                    throw new ar("identifier too long");
                }
            } while (Character.isLetter(this.do[this.r]) || Character.isDigit(this.do[this.r]) || this.do[this.r] == '_');
            --this.r;
            this.d.do[n] = '\0';
            final char[] array = new char[this.d.do.length];
            a.a.a.a.g.do(this.d.do, array);
            if (a.a.a.a.g.a(array, ah.F) == 0) {
                this.d.for = 8;
                array[0] = '\0';
            }
            else if (a.a.a.a.g.a(array, ah.B) == 0) {
                this.d.for = 5;
                this.d.if = 1L;
            }
            else if (a.a.a.a.g.a(array, ah.H) == 0) {
                this.d.for = 5;
                this.d.if = 0L;
            }
            else if (a.a.a.a.g.a(array, ah.y) == 0) {
                this.d.for = 2;
                this.d.if = 0L;
            }
            else if (a.a.a.a.g.a(array, ah.I) == 0) {
                this.d.for = 2;
                this.d.if = 1L;
            }
            else if (a.a.a.a.g.a(array, ah.A) == 0) {
                this.d.for = 2;
                this.d.if = 2L;
            }
            else if (a.a.a.a.g.a(array, ah.o) == 0) {
                this.d.for = 2;
                this.d.if = 3L;
            }
            else if (a.a.a.a.g.a(array, ah.z) == 0) {
                this.d.for = 2;
                this.d.if = 4L;
            }
            else if (a.a.a.a.g.a(array, ah.b) == 0) {
                this.d.for = 2;
                this.d.if = 5L;
            }
            else if (a.a.a.a.g.a(array, ah.new) == 0) {
                this.d.for = 2;
                this.d.if = 6L;
            }
            else if (a.a.a.a.g.a(array, ah.long) == 0) {
                this.d.for = 2;
                this.d.if = 7L;
            }
            else {
                this.d.for = 1;
            }
            return true;
        }
        return false;
    }
    
    z int() throws ar {
        this.for();
        if (this.do[this.r] == '\0') {
            this.d.for = 9;
            this.d.do[0] = '\0';
            return this.d;
        }
        if (this.do[this.r] == '+' || this.do[this.r] == '-' || this.do[this.r] == '*' || this.do[this.r] == '/' || this.do[this.r] == '!' || this.do[this.r] == '<' || this.do[this.r] == '>' || this.do[this.r] == '=' || this.do[this.r] == '|' || this.do[this.r] == '&') {
            switch (this.do[this.r]) {
                case '+': {
                    this.d.do[0] = '+';
                    this.d.do[1] = '\0';
                    this.d.for = 3;
                    this.d.if = 8L;
                    break;
                }
                case '-': {
                    this.d.do[0] = '-';
                    this.d.do[1] = '\0';
                    this.d.for = 3;
                    this.d.if = 9L;
                    break;
                }
                case '*': {
                    this.d.do[0] = '*';
                    this.d.do[1] = '\0';
                    this.d.for = 3;
                    this.d.if = 10L;
                    break;
                }
                case '/': {
                    this.d.do[0] = '/';
                    this.d.do[1] = '\0';
                    this.d.for = 3;
                    this.d.if = 11L;
                    break;
                }
                case '&': {
                    this.d.for = 3;
                    if (this.do[this.r + 1] == '&') {
                        this.d.do[0] = (this.d.do[1] = '&');
                        this.d.do[2] = '\0';
                        this.d.if = 14L;
                        ++this.r;
                        break;
                    }
                    this.d.do[0] = '&';
                    this.d.do[1] = '\0';
                    this.d.if = 12L;
                    break;
                }
                case '|': {
                    this.d.for = 3;
                    if (this.do[this.r + 1] == '|') {
                        this.d.do[0] = (this.d.do[1] = '|');
                        this.d.do[2] = '\0';
                        this.d.if = 15L;
                        ++this.r;
                        break;
                    }
                    this.d.do[0] = '|';
                    this.d.do[1] = '\0';
                    this.d.if = 13L;
                    break;
                }
                case '!': {
                    this.d.for = 3;
                    if (this.do[this.r + 1] == '=') {
                        this.d.do[0] = '!';
                        this.d.do[1] = '=';
                        this.d.do[2] = '\0';
                        this.d.if = 22L;
                        ++this.r;
                        break;
                    }
                    this.d.do[0] = '!';
                    this.d.do[1] = '\0';
                    this.d.if = 16L;
                    break;
                }
                case '=': {
                    if (this.do[this.r + 1] == '=') {
                        this.d.do[0] = (this.d.do[1] = '=');
                        this.d.do[2] = '\0';
                        this.d.for = 3;
                        this.d.if = 17L;
                        ++this.r;
                        break;
                    }
                    this.d.do[0] = '=';
                    this.d.do[1] = '\0';
                    this.d.for = 4;
                    break;
                }
                case '>': {
                    this.d.for = 3;
                    if (this.do[this.r + 1] == '=') {
                        this.d.do[0] = '>';
                        this.d.do[1] = '=';
                        this.d.do[2] = '\0';
                        this.d.if = 20L;
                        ++this.r;
                        break;
                    }
                    this.d.do[0] = '>';
                    this.d.do[1] = '\0';
                    this.d.if = 18L;
                    break;
                }
                case '<': {
                    this.d.for = 3;
                    if (this.do[this.r + 1] == '=') {
                        this.d.do[0] = '<';
                        this.d.do[1] = '=';
                        this.d.do[2] = '\0';
                        this.d.if = 21L;
                        ++this.r;
                        break;
                    }
                    this.d.do[0] = '<';
                    this.d.do[1] = '\0';
                    this.d.if = 19L;
                    break;
                }
            }
        }
        else if (this.do[this.r] == '{' || this.do[this.r] == '}' || this.do[this.r] == '(' || this.do[this.r] == ')' || this.do[this.r] == ';' || this.do[this.r] == ',' || this.do[this.r] == '[' || this.do[this.r] == ']') {
            this.d.for = 0;
            this.d.do[0] = this.do[this.r];
            this.d.do[1] = '\0';
        }
        else if (!this.byte() && !this.do() && !this.case()) {
            throw new ar("unexpected character");
        }
        ++this.r;
        return this.d;
    }
    
    void if() {
    }
    
    void a(final int n) {
        this.r = this.if;
        this.K = 1;
        for (int i = this.r; i < n; ++i) {
            if (this.do[i] == '\n') {
                ++this.K;
                this.n = i + 1;
            }
            ++this.r;
        }
    }
}
