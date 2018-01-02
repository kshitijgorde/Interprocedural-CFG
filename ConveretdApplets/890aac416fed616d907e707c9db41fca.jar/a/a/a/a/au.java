// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class au
{
    ak d;
    char[] do;
    int p;
    char[] int;
    int if;
    int I;
    char[] A;
    int m;
    static final int v = 0;
    static final int r = 1;
    static final int char = 2;
    static final int k = 3;
    static final int a = 4;
    static final int H = 5;
    static final int n = 6;
    static final int q = 7;
    static final int j = 8;
    static final int else = 9;
    static final char[] D;
    static final char[] z;
    static final char[] F;
    static final char[] w;
    static final char[] G;
    static final char[] y;
    static final char[] x;
    static final char[] b;
    static final char[] new;
    static final char[] long;
    static final int i = 0;
    static final int o = 1;
    static final int l = 2;
    static final int goto = 4;
    static final int g = 5;
    static final int s = 6;
    static final int h = 7;
    static final int J = 8;
    static final int f = 9;
    static final int K = 10;
    static final int C = 11;
    static final int void = 12;
    static final int for = 13;
    static final int u = 14;
    static final int e = 15;
    static final int t = 16;
    static final int c = 17;
    static final int E = 18;
    static final int try = 19;
    static final int byte = 20;
    static final int case = 21;
    static final int B = 22;
    
    static {
        D = new char[] { 'n', 'u', 'l', 'l' };
        z = new char[] { 't', 'r', 'u', 'e' };
        F = new char[] { 'f', 'a', 'l', 's', 'e' };
        w = new char[] { 'i', 'f' };
        G = new char[] { 'e', 'l', 's', 'e' };
        y = new char[] { 'w', 'h', 'i', 'l', 'e' };
        x = new char[] { 'r', 'e', 't', 'u', 'r', 'n' };
        b = new char[] { 'v', 'a', 'r' };
        new = new char[] { 'g', 'l', 'o', 'b', 'a', 'l', 's' };
        long = new char[] { 'f', 'u', 'n', 'c', 't', 'i', 'o', 'n' };
    }
    
    au(final char[] int1) {
        this.p = 0;
        this.if = 0;
        this.m = 0;
        this.do = int1;
        this.A = int1;
        this.int = int1;
        this.d = new ak();
        this.I = 1;
    }
    
    public void a() {
        this.do = null;
        this.A = null;
        this.int = null;
        if (this.d != null) {
            this.d.a();
        }
        this.d = null;
    }
    
    int try() {
        return this.I;
    }
    
    int if() {
        return this.m;
    }
    
    int else() {
        return this.p;
    }
    
    char[] long() {
        return this.do;
    }
    
    void case() throws a5 {
        if (this.do[this.p] == '\0') {
            throw new a5("unexpected end of file");
        }
    }
    
    boolean goto() {
        final char c = this.do[this.p];
        return c == ' ' || c == '(' || c == ')' || c == '{' || c == '}' || c == '\"' || c == '\r' || c == '\n' || c == '\t' || (c == '!' || c == ';' || c == ',' || c == '+' || c == '-' || c == '<' || c == '>' || c == '/' || c == '*') || (c == '\'' || c == '&' || c == '|' || c == '=' || c == '\0') || (c == '[' || c == ']');
    }
    
    void int() throws a5 {
        while (this.do[this.p] == ' ' || this.do[this.p] == '\t' || this.do[this.p] == '\n' || this.do[this.p] == '\r' || (this.do[this.p] == '/' && this.do[this.p + 1] == '*')) {
            if (this.do[this.p] == '/') {
                this.p += 2;
                do {
                    this.case();
                    while (this.do[this.p] != '*') {
                        ++this.p;
                        this.case();
                    }
                    ++this.p;
                } while (this.do[this.p] != '/');
            }
            if (this.do[this.p] == '\n') {
                ++this.I;
                this.m = this.p + 1;
            }
            ++this.p;
        }
    }
    
    boolean byte() throws a5 {
        if (this.do[this.p] == '\"') {
            this.d.do[0] = '\0';
            this.d.for = 8;
            ++this.p;
            int n = 0;
            while (this.do[this.p] != '\"') {
                this.case();
                if (this.do[this.p] == '\r' || this.do[this.p] == '\n') {
                    throw new a5("unexpected end of line");
                }
                if (this.do[this.p] == '\\') {
                    ++this.p;
                    this.case();
                    this.d.do[n++] = this.a(this.do[this.p]);
                }
                else {
                    this.d.do[n++] = this.do[this.p];
                }
                if (n == 1023) {
                    throw new a5("String too long");
                }
                ++this.p;
            }
            this.d.do[n] = '\0';
            return true;
        }
        return false;
    }
    
    char a(final char c) throws a5 {
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
                throw new a5("Invalid escape character");
            }
        }
    }
    
    boolean for() throws a5 {
        if (this.do[this.p] == '\'') {
            ++this.p;
            if (this.do[this.p] == '\\') {
                ++this.p;
                this.d.if = this.a(this.do[this.p]);
            }
            else {
                this.d.if = this.do[this.p];
            }
            ++this.p;
            if (this.do[this.p] != '\'') {
                throw new a5(" ' character expected.");
            }
            this.d.for = 6;
            return true;
        }
        else {
            if (!Character.isDigit(this.do[this.p]) && this.do[this.p] != '#') {
                return false;
            }
            int n = 0;
            do {
                this.d.do[n] = this.do[this.p];
                ++n;
                ++this.p;
                if (n == 1023) {
                    throw new a5("bad number representation");
                }
            } while (!this.goto());
            this.d.do[n] = '\0';
            --this.p;
            if (this.d.do.length <= 2 || ((this.d.do[0] != '0' || this.d.do[1] != 'x') && this.d.do[0] != '#')) {
                int n2 = 1;
                for (int i = 0; i < n - 1; ++i) {
                    if (!Character.isDigit(this.d.do[i])) {
                        if (this.d.do[i] != '.' || n2 == 0) {
                            throw new a5("bad number representation");
                        }
                        n2 = 0;
                    }
                }
                if (n2 != 0) {
                    this.d.if = Long.parseLong(new String(this.d.do, 0, n));
                    this.d.for = 6;
                }
                else {
                    this.d.int = new Double(new String(this.d.do, 0, a.a.a.a.i.a(this.d.do)));
                    this.d.for = 7;
                }
                return true;
            }
            int n3 = 2;
            if (this.d.do[0] == '#') {
                n3 = 1;
            }
            if (n > n3 && n < 11 && n - n3 < 9) {
                this.d.if = 0L;
                for (int j = n3; j < n; ++j) {
                    final char c = this.d.do[j];
                    if (Character.isDigit(c)) {
                        this.d.if = this.d.if * 16L + (c - '0');
                    }
                    else if (Character.isUpperCase(c) && c < 'G') {
                        this.d.if = this.d.if * 16L + (c - '7');
                    }
                    else {
                        if (!Character.isLowerCase(c) || c >= 'g') {
                            throw new a5("bad number representation");
                        }
                        this.d.if = this.d.if * 16L + (c - 'W');
                    }
                }
                this.d.for = 6;
                return true;
            }
            throw new a5("bad number representation");
        }
    }
    
    boolean char() throws a5 {
        if (Character.isLetter(this.do[this.p]) || this.do[this.p] == '_') {
            int n = 0;
            do {
                this.d.do[n++] = this.do[this.p++];
                if (n == 1023) {
                    throw new a5("identifier too long");
                }
            } while (Character.isLetter(this.do[this.p]) || Character.isDigit(this.do[this.p]) || this.do[this.p] == '_');
            --this.p;
            this.d.do[n] = '\0';
            final char[] array = new char[this.d.do.length];
            a.a.a.a.i.for(this.d.do, array);
            if (a.a.a.a.i.if(array, au.D) == 0) {
                this.d.for = 8;
                array[0] = '\0';
            }
            else if (a.a.a.a.i.if(array, au.z) == 0) {
                this.d.for = 5;
                this.d.if = 1L;
            }
            else if (a.a.a.a.i.if(array, au.F) == 0) {
                this.d.for = 5;
                this.d.if = 0L;
            }
            else if (a.a.a.a.i.if(array, au.w) == 0) {
                this.d.for = 2;
                this.d.if = 0L;
            }
            else if (a.a.a.a.i.if(array, au.G) == 0) {
                this.d.for = 2;
                this.d.if = 1L;
            }
            else if (a.a.a.a.i.if(array, au.y) == 0) {
                this.d.for = 2;
                this.d.if = 2L;
            }
            else if (a.a.a.a.i.if(array, au.x) == 0) {
                this.d.for = 2;
                this.d.if = 4L;
            }
            else if (a.a.a.a.i.if(array, au.b) == 0) {
                this.d.for = 2;
                this.d.if = 5L;
            }
            else if (a.a.a.a.i.if(array, au.new) == 0) {
                this.d.for = 2;
                this.d.if = 6L;
            }
            else if (a.a.a.a.i.if(array, au.long) == 0) {
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
    
    ak new() throws a5 {
        this.int();
        if (this.do[this.p] == '\0') {
            this.d.for = 9;
            this.d.do[0] = '\0';
            return this.d;
        }
        if (this.do[this.p] == '+' || this.do[this.p] == '-' || this.do[this.p] == '*' || this.do[this.p] == '/' || this.do[this.p] == '!' || this.do[this.p] == '<' || this.do[this.p] == '>' || this.do[this.p] == '=' || this.do[this.p] == '|' || this.do[this.p] == '&') {
            switch (this.do[this.p]) {
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
                    if (this.do[this.p + 1] == '&') {
                        this.d.do[0] = (this.d.do[1] = '&');
                        this.d.do[2] = '\0';
                        this.d.if = 14L;
                        ++this.p;
                        break;
                    }
                    this.d.do[0] = '&';
                    this.d.do[1] = '\0';
                    this.d.if = 12L;
                    break;
                }
                case '|': {
                    this.d.for = 3;
                    if (this.do[this.p + 1] == '|') {
                        this.d.do[0] = (this.d.do[1] = '|');
                        this.d.do[2] = '\0';
                        this.d.if = 15L;
                        ++this.p;
                        break;
                    }
                    this.d.do[0] = '|';
                    this.d.do[1] = '\0';
                    this.d.if = 13L;
                    break;
                }
                case '!': {
                    this.d.for = 3;
                    if (this.do[this.p + 1] == '=') {
                        this.d.do[0] = '!';
                        this.d.do[1] = '=';
                        this.d.do[2] = '\0';
                        this.d.if = 22L;
                        ++this.p;
                        break;
                    }
                    this.d.do[0] = '!';
                    this.d.do[1] = '\0';
                    this.d.if = 16L;
                    break;
                }
                case '=': {
                    if (this.do[this.p + 1] == '=') {
                        this.d.do[0] = (this.d.do[1] = '=');
                        this.d.do[2] = '\0';
                        this.d.for = 3;
                        this.d.if = 17L;
                        ++this.p;
                        break;
                    }
                    this.d.do[0] = '=';
                    this.d.do[1] = '\0';
                    this.d.for = 4;
                    break;
                }
                case '>': {
                    this.d.for = 3;
                    if (this.do[this.p + 1] == '=') {
                        this.d.do[0] = '>';
                        this.d.do[1] = '=';
                        this.d.do[2] = '\0';
                        this.d.if = 20L;
                        ++this.p;
                        break;
                    }
                    this.d.do[0] = '>';
                    this.d.do[1] = '\0';
                    this.d.if = 18L;
                    break;
                }
                case '<': {
                    this.d.for = 3;
                    if (this.do[this.p + 1] == '=') {
                        this.d.do[0] = '<';
                        this.d.do[1] = '=';
                        this.d.do[2] = '\0';
                        this.d.if = 21L;
                        ++this.p;
                        break;
                    }
                    this.d.do[0] = '<';
                    this.d.do[1] = '\0';
                    this.d.if = 19L;
                    break;
                }
            }
        }
        else if (this.do[this.p] == '{' || this.do[this.p] == '}' || this.do[this.p] == '(' || this.do[this.p] == ')' || this.do[this.p] == ';' || this.do[this.p] == ',' || this.do[this.p] == '[' || this.do[this.p] == ']') {
            this.d.for = 0;
            this.d.do[0] = this.do[this.p];
            this.d.do[1] = '\0';
        }
        else if (!this.byte() && !this.for() && !this.char()) {
            throw new a5("unexpected character");
        }
        ++this.p;
        return this.d;
    }
    
    void do() {
    }
    
    void a(final int n) {
        this.p = this.if;
        this.I = 1;
        for (int i = this.p; i < n; ++i) {
            if (this.do[i] == '\n') {
                ++this.I;
                this.m = i + 1;
            }
            ++this.p;
        }
    }
}
