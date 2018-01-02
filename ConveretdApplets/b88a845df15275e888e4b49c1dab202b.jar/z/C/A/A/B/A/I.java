// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.A;

import java.util.Stack;

public final class I implements N
{
    private static final char J = '\uffff';
    private static final int K = 20;
    private boolean L;
    private boolean Y;
    private boolean d;
    private char _;
    private char[] U;
    private char[] b;
    private C S;
    private int N;
    private int V;
    private int M;
    private int I;
    private int c;
    private char[] H;
    private int X;
    private int T;
    private int a;
    private int[] P;
    private int[] W;
    private Stack Q;
    private O R;
    private static final int Z = -100;
    private int O;
    
    public I() {
        this.L = false;
        this.Y = false;
        this.d = false;
        this.Q = new Stack();
        this.R = null;
        this.O = -100;
    }
    
    private static boolean A(final char[] array, int n, final char[] array2, int n2, final int n3) {
        for (int i = 0; i < n3; ++i, ++n, ++n2) {
            if (n >= array.length) {
                return false;
            }
            if (n2 >= array2.length) {
                return false;
            }
            if (array[n] != array2[n2]) {
                return false;
            }
        }
        return true;
    }
    
    private static int A(final char[] array, int i, final int n, final char[] array2) {
        if (array.length == 0) {
            return n;
        }
        final char c = array2[0];
        while (i < n) {
            if (c == array[i]) {
                final int n2 = i;
                int n3;
                for (n3 = 0; i < n && n3 < array2.length && array2[n3] == array[i]; ++n3, ++i) {}
                i = n2;
                if (n3 >= array2.length) {
                    break;
                }
            }
            ++i;
        }
        return i;
    }
    
    private void C(final int n) {
        int n2 = 3 * (this.X - n);
        int[] array;
        if (n2 <= 0) {
            array = new int[3];
        }
        else {
            array = new int[n2 + 3];
        }
        array[0] = this.X;
        array[1] = this.a;
        array[2] = this.T;
        for (int i = this.X; i > n; --i, n2 -= 3) {
            array[n2] = this.W[i];
            array[n2 + 1] = this.P[i];
            array[n2 + 2] = i;
        }
        this.Q.push(array);
    }
    
    private void E() {
        final int[] array = this.Q.pop();
        this.X = array[0];
        this.a = array[1];
        this.T = array[2];
        for (int i = 3; i < array.length; i += 3) {
            final int n = array[i + 2];
            this.P[n] = array[i + 1];
            if (n <= this.a) {
                this.W[n] = array[i];
            }
        }
        for (int j = this.a + 1; j <= this.N; ++j) {
            if (j > this.X) {
                this.P[j] = -1;
            }
            this.W[j] = -1;
        }
    }
    
    private void B(final D d, final char[] u, final int v, int n, final int i) {
        this.d = d.f;
        this.U = u;
        this.c = n;
        this.S = new C();
        this.S.E = 0;
        this.S.G = null;
        this.H = d.g;
        this.Q.setSize(0);
        if (i == v || i <= 0) {
            this._ = '\n';
        }
        else {
            this._ = u[i - 1];
            if (!this.L && this._ == '\n') {
                this._ = '\0';
            }
        }
        this.N = d.c;
        this.I = i;
        this.V = v;
        this.M = n;
        n = this.N + 1;
        if (this.P == null || n > this.P.length) {
            if (n < 20) {
                n = 20;
            }
            this.P = new int[n];
            this.W = new int[n];
        }
    }
    
    private void F() {
        int n = 0;
        this.R = new O(this.N + 1);
        if (this.W[0] > this.b.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.R.B = this.P[0];
        while (this.N >= 0) {
            final int n2 = this.P[this.N];
            if (n2 >= 0) {
                this.R.A[this.N] = n2 - this.R.B;
            }
            else {
                this.R.A[this.N] = -1;
            }
            final int n3 = this.W[this.N];
            if (n3 >= 0) {
                this.R.C[this.N] = n3 - this.R.B;
                if (n3 > n && n3 <= this.b.length) {
                    n = n3;
                }
            }
            else {
                this.R.C[this.N] = -1;
            }
            --this.N;
        }
        this.R.D = new String(this.b, this.P[0], n - this.P[0]);
        this.b = null;
    }
    
    private boolean A(final D d, final char[] array, final int n, int n2, final int i) {
        int length = 0;
        int n3 = 0;
        this.B(d, array, n, n2, i);
        boolean y = false;
        final char[] s = d.S;
        Label_1711: {
            if (s != null && ((d.b & 0x3) == 0x0 || ((this.L || (d.b & 0x2) != 0x0) && d.V >= 0))) {
                this.I = A(this.U, this.I, n2, s);
                if (this.I >= n2) {
                    if ((d.a & 0x8000) == 0x0) {
                        ++d.Z;
                    }
                    y = false;
                    break Label_1711;
                }
                if (d.V >= 0) {
                    this.I -= d.V;
                    if (this.I < i) {
                        this.I = i;
                    }
                    length = d.V + s.length;
                }
                else if (!d.i && (d.a & 0x8000) == 0x0 && --d.Z < 0) {
                    d.S = null;
                    this.I = i;
                }
                else {
                    this.I = i;
                    length = s.length;
                }
            }
            if ((d.b & 0x3) != 0x0) {
                if (this.I == n && this.A(n)) {
                    y = true;
                }
                else if (this.L || (d.b & 0x2) != 0x0 || (d.b & 0x8) != 0x0) {
                    if (length > 0) {
                        n3 = length - 1;
                    }
                    n2 -= n3;
                    if (this.I > i) {
                        --this.I;
                    }
                    while (this.I < n2) {
                        if (this.U[this.I++] == '\n' && this.I < n2 && this.A(this.I)) {
                            y = true;
                            break;
                        }
                    }
                }
            }
            else if (d.h != null) {
                final char[] h = d.h;
                if ((d.b & 0x4) != 0x0) {
                    final char c = h[0];
                    while (this.I < n2) {
                        if (c == this.U[this.I]) {
                            if (this.A(this.I)) {
                                y = true;
                                break;
                            }
                            ++this.I;
                            while (this.I < n2 && this.U[this.I] == c) {
                                ++this.I;
                            }
                        }
                        ++this.I;
                    }
                }
                else {
                    while ((this.I = A(this.U, this.I, n2, h)) < n2) {
                        if (this.A(this.I)) {
                            y = true;
                            break;
                        }
                        ++this.I;
                    }
                }
            }
            else {
                final int _;
                if ((_ = d._) != -1) {
                    final boolean b = (d.b & 0x4) == 0x0;
                    if (length > 0) {
                        n3 = length - 1;
                    }
                    n2 -= n3;
                    int n4 = 1;
                    final char c2;
                    switch (c2 = this.H[_]) {
                        case '\t': {
                            final int b2 = z.C.A.A.B.A.Q.B(_);
                            while (this.I < n2) {
                                final char c3 = this.U[this.I];
                                if (c3 < '\u0100' && (this.H[b2 + (c3 >> 4)] & 1 << (c3 & '\u000f')) == 0x0) {
                                    if (n4 != 0 && this.A(this.I)) {
                                        y = true;
                                        break;
                                    }
                                    n4 = (b ? 1 : 0);
                                }
                                else {
                                    n4 = 1;
                                }
                                ++this.I;
                            }
                            break;
                        }
                        case '#':
                        case '$': {
                            final int b3 = z.C.A.A.B.A.Q.B(_);
                            while (this.I < n2) {
                                if (this.A(this.U[this.I], this.H, b3, c2)) {
                                    if (n4 != 0 && this.A(this.I)) {
                                        y = true;
                                        break;
                                    }
                                    n4 = (b ? 1 : 0);
                                }
                                else {
                                    n4 = 1;
                                }
                                ++this.I;
                            }
                            break;
                        }
                        case '\u0014': {
                            if (length > 0) {
                                ++n3;
                                --n2;
                            }
                            boolean b4;
                            if (this.I != n) {
                                b4 = z.C.A.A.B.A.Q.A(this.U[this.I - 1]);
                            }
                            else {
                                b4 = z.C.A.A.B.A.Q.A(this._);
                            }
                            while (this.I < n2) {
                                if (b4 != z.C.A.A.B.A.Q.A(this.U[this.I])) {
                                    b4 = !b4;
                                    if (this.A(this.I)) {
                                        y = true;
                                        break Label_1711;
                                    }
                                }
                                ++this.I;
                            }
                            if ((length > 0 || b4) && this.A(this.I)) {
                                y = true;
                                break;
                            }
                            break;
                        }
                        case '\u0015': {
                            if (length > 0) {
                                ++n3;
                                --n2;
                            }
                            boolean b5;
                            if (this.I != n) {
                                b5 = z.C.A.A.B.A.Q.A(this.U[this.I - 1]);
                            }
                            else {
                                b5 = z.C.A.A.B.A.Q.A(this._);
                            }
                            while (this.I < n2) {
                                if (b5 != z.C.A.A.B.A.Q.A(this.U[this.I])) {
                                    b5 = !b5;
                                }
                                else if (this.A(this.I)) {
                                    y = true;
                                    break Label_1711;
                                }
                                ++this.I;
                            }
                            if ((length > 0 || !b5) && this.A(this.I)) {
                                y = true;
                                break;
                            }
                            break;
                        }
                        case '\u0012': {
                            while (this.I < n2) {
                                if (z.C.A.A.B.A.Q.A(this.U[this.I])) {
                                    if (n4 != 0 && this.A(this.I)) {
                                        y = true;
                                        break;
                                    }
                                    n4 = (b ? 1 : 0);
                                }
                                else {
                                    n4 = 1;
                                }
                                ++this.I;
                            }
                            break;
                        }
                        case '\u0013': {
                            while (this.I < n2) {
                                if (!z.C.A.A.B.A.Q.A(this.U[this.I])) {
                                    if (n4 != 0 && this.A(this.I)) {
                                        y = true;
                                        break;
                                    }
                                    n4 = (b ? 1 : 0);
                                }
                                else {
                                    n4 = 1;
                                }
                                ++this.I;
                            }
                            break;
                        }
                        case '\u0016': {
                            while (this.I < n2) {
                                if (Character.isWhitespace(this.U[this.I])) {
                                    if (n4 != 0 && this.A(this.I)) {
                                        y = true;
                                        break;
                                    }
                                    n4 = (b ? 1 : 0);
                                }
                                else {
                                    n4 = 1;
                                }
                                ++this.I;
                            }
                            break;
                        }
                        case '\u0017': {
                            while (this.I < n2) {
                                if (!Character.isWhitespace(this.U[this.I])) {
                                    if (n4 != 0 && this.A(this.I)) {
                                        y = true;
                                        break;
                                    }
                                    n4 = (b ? 1 : 0);
                                }
                                else {
                                    n4 = 1;
                                }
                                ++this.I;
                            }
                            break;
                        }
                        case '\u0018': {
                            while (this.I < n2) {
                                if (Character.isDigit(this.U[this.I])) {
                                    if (n4 != 0 && this.A(this.I)) {
                                        y = true;
                                        break;
                                    }
                                    n4 = (b ? 1 : 0);
                                }
                                else {
                                    n4 = 1;
                                }
                                ++this.I;
                            }
                            break;
                        }
                        case '\u0019': {
                            while (this.I < n2) {
                                if (!Character.isDigit(this.U[this.I])) {
                                    if (n4 != 0 && this.A(this.I)) {
                                        y = true;
                                        break;
                                    }
                                    n4 = (b ? 1 : 0);
                                }
                                else {
                                    n4 = 1;
                                }
                                ++this.I;
                            }
                            break;
                        }
                    }
                }
                else {
                    if (length > 0) {
                        n3 = length - 1;
                    }
                    n2 -= n3;
                    while (!this.A(this.I)) {
                        if (this.I++ >= n2) {
                            break Label_1711;
                        }
                    }
                    y = true;
                }
            }
        }
        this.Y = y;
        this.R = null;
        return y;
    }
    
    private boolean A(final char c, final char[] array, int n, final char c2) {
        boolean b = c2 == '#';
        while (array[n] != '\0') {
            if (array[n] == '%') {
                ++n;
                if (c >= array[n] && c <= array[n + 1]) {
                    return b;
                }
                n += 2;
            }
            else if (array[n] == '1') {
                ++n;
                if (array[n++] == c) {
                    return b;
                }
                continue;
            }
            else {
                b = ((array[n] == '/') ? b : (!b));
                ++n;
                switch (array[n++]) {
                    case '\u0012': {
                        if (z.C.A.A.B.A.Q.A(c)) {
                            return b;
                        }
                        continue;
                    }
                    case '\u0013': {
                        if (!z.C.A.A.B.A.Q.A(c)) {
                            return b;
                        }
                        continue;
                    }
                    case '\u0016': {
                        if (Character.isWhitespace(c)) {
                            return b;
                        }
                        continue;
                    }
                    case '\u0017': {
                        if (!Character.isWhitespace(c)) {
                            return b;
                        }
                        continue;
                    }
                    case '\u0018': {
                        if (Character.isDigit(c)) {
                            return b;
                        }
                        continue;
                    }
                    case '\u0019': {
                        if (!Character.isDigit(c)) {
                            return b;
                        }
                        continue;
                    }
                    case '2': {
                        if (Character.isLetterOrDigit(c)) {
                            return b;
                        }
                        continue;
                    }
                    case '&': {
                        if (Character.isLetter(c)) {
                            return b;
                        }
                        continue;
                    }
                    case '\'': {
                        if (Character.isSpaceChar(c)) {
                            return b;
                        }
                        continue;
                    }
                    case '(': {
                        if (Character.isISOControl(c)) {
                            return b;
                        }
                        continue;
                    }
                    case '*': {
                        if (Character.isLowerCase(c)) {
                            return b;
                        }
                        if (this.d && Character.isUpperCase(c)) {
                            return b;
                        }
                        continue;
                    }
                    case '-': {
                        if (Character.isUpperCase(c)) {
                            return b;
                        }
                        if (this.d && Character.isLowerCase(c)) {
                            return b;
                        }
                        continue;
                    }
                    case '+': {
                        if (Character.isSpaceChar(c)) {
                            return b;
                        }
                    }
                    case ')': {
                        if (Character.isLetterOrDigit(c)) {
                            return b;
                        }
                    }
                    case ',': {
                        switch (Character.getType(c)) {
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27: {
                                return b;
                            }
                            default: {
                                continue;
                            }
                        }
                        break;
                    }
                    case '.': {
                        if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')) {
                            return b;
                        }
                        continue;
                    }
                    case '3': {
                        if (c < '\u0080') {
                            return b;
                        }
                        continue;
                    }
                }
            }
        }
        return !b;
    }
    
    private boolean A(final int t) {
        this.T = t;
        this.a = 0;
        this.X = 0;
        if (this.N > 0) {
            for (int i = 0; i <= this.N; ++i) {
                this.P[i] = -1;
                this.W[i] = -1;
            }
        }
        if (this.B(1)) {
            this.P[0] = t;
            this.W[0] = this.T;
            return true;
        }
        return false;
    }
    
    private int A(final int n, final int n2) {
        int t = this.T;
        int m = this.M;
        if (n2 != 65535 && n2 < m - t) {
            m = t + n2;
        }
        int b = z.C.A.A.B.A.Q.B(n);
        final char c;
        switch (c = this.H[n]) {
            case '\u0007': {
                while (t < m && this.U[t] != '\n') {
                    ++t;
                }
                break;
            }
            case '\b': {
                t = m;
                break;
            }
            case '\u000e': {
                ++b;
                while (t < m && this.H[b] == this.U[t]) {
                    ++t;
                }
                break;
            }
            case '\t': {
                char c2;
                if (t < m && (c2 = this.U[t]) < '\u0100') {
                    while (c2 < '\u0100' && (this.H[b + (c2 >> 4)] & 1 << (c2 & '\u000f')) == 0x0 && ++t < m) {
                        c2 = this.U[t];
                    }
                    break;
                }
                break;
            }
            case '#':
            case '$': {
                if (t < m) {
                    for (char c3 = this.U[t]; this.A(c3, this.H, b, c) && ++t < m; c3 = this.U[t]) {}
                    break;
                }
                break;
            }
            case '\u0012': {
                while (t < m && z.C.A.A.B.A.Q.A(this.U[t])) {
                    ++t;
                }
                break;
            }
            case '\u0013': {
                while (t < m && !z.C.A.A.B.A.Q.A(this.U[t])) {
                    ++t;
                }
                break;
            }
            case '\u0016': {
                while (t < m && Character.isWhitespace(this.U[t])) {
                    ++t;
                }
                break;
            }
            case '\u0017': {
                while (t < m && !Character.isWhitespace(this.U[t])) {
                    ++t;
                }
                break;
            }
            case '\u0018': {
                while (t < m && Character.isDigit(this.U[t])) {
                    ++t;
                }
                break;
            }
            case '\u0019': {
                while (t < m && !Character.isDigit(this.U[t])) {
                    ++t;
                }
                break;
            }
        }
        final int n3 = t - this.T;
        this.T = t;
        return n3;
    }
    
    private boolean B(final int n) {
        boolean h = false;
        int t = this.T;
        boolean b = t < this.c;
        char c = b ? this.U[t] : '\uffff';
        int a;
        for (int i = n; i < this.H.length; i = a) {
            a = z.C.A.A.B.A.Q.B(this.H, i);
            final char c2;
            switch (c2 = this.H[i]) {
                case '\u0001': {
                    if (t == this.V) {
                        if (this._ == '\n') {
                            break;
                        }
                    }
                    else if (this.L && (b || t < this.M) && this.U[t - '\u0001'] == '\n') {
                        break;
                    }
                    return false;
                }
                case '\u0002': {
                    if (t == this.V) {
                        if (this._ == '\n') {
                            break;
                        }
                    }
                    else if ((b || t < this.M) && this.U[t - '\u0001'] == '\n') {
                        break;
                    }
                    return false;
                }
                case '\u0003': {
                    if (t == this.V && this._ == '\n') {
                        break;
                    }
                    return false;
                }
                case '\u001e': {
                    if (t == this.V) {
                        break;
                    }
                    return true;
                }
                case '\u0004': {
                    if ((b || t < this.M) && c != '\n') {
                        return false;
                    }
                    if (!this.L && this.M - t > 1) {
                        return false;
                    }
                    break;
                }
                case '\u0005': {
                    if ((b || t < this.M) && c != '\n') {
                        return false;
                    }
                    break;
                }
                case '\u0006': {
                    if ((b || t < this.M) && c != '\n') {
                        return false;
                    }
                    if (this.M - t > 1) {
                        return false;
                    }
                    break;
                }
                case '\b': {
                    if (!b && t >= this.M) {
                        return false;
                    }
                    b = (++t < this.c);
                    c = (b ? this.U[t] : '\uffff');
                    break;
                }
                case '\u0007': {
                    if ((!b && t >= this.M) || c == '\n') {
                        return false;
                    }
                    b = (++t < this.c);
                    c = (b ? this.U[t] : '\uffff');
                    break;
                }
                case '\u000e': {
                    int b2 = z.C.A.A.B.A.Q.B(i);
                    final char c3 = this.H[b2++];
                    if (this.H[b2] != c) {
                        return false;
                    }
                    if (this.M - t < c3) {
                        return false;
                    }
                    if (c3 > '\u0001' && !A(this.H, b2, this.U, t, c3)) {
                        return false;
                    }
                    t += c3;
                    b = (t < this.c);
                    c = (b ? this.U[t] : '\uffff');
                    break;
                }
                case '\t': {
                    final int b3 = z.C.A.A.B.A.Q.B(i);
                    if (c == '\uffff' && b) {
                        c = this.U[t];
                    }
                    if (c >= '\u0100' || (this.H[b3 + (c >> 4)] & 1 << (c & '\u000f')) != 0x0) {
                        return false;
                    }
                    if (!b && t >= this.M) {
                        return false;
                    }
                    b = (++t < this.c);
                    c = (b ? this.U[t] : '\uffff');
                    break;
                }
                case '#':
                case '$': {
                    final int b4 = z.C.A.A.B.A.Q.B(i);
                    if (c == '\uffff' && b) {
                        c = this.U[t];
                    }
                    if (!this.A(c, this.H, b4, c2)) {
                        return false;
                    }
                    if (!b && t >= this.M) {
                        return false;
                    }
                    b = (++t < this.c);
                    c = (b ? this.U[t] : '\uffff');
                    break;
                }
                case '\u0012': {
                    if (!b) {
                        return false;
                    }
                    if (!z.C.A.A.B.A.Q.A(c)) {
                        return false;
                    }
                    b = (++t < this.c);
                    c = (b ? this.U[t] : '\uffff');
                    break;
                }
                case '\u0013': {
                    if (!b && t >= this.M) {
                        return false;
                    }
                    if (z.C.A.A.B.A.Q.A(c)) {
                        return false;
                    }
                    b = (++t < this.c);
                    c = (b ? this.U[t] : '\uffff');
                    break;
                }
                case '\u0014':
                case '\u0015': {
                    boolean b5;
                    if (t == this.V) {
                        b5 = z.C.A.A.B.A.Q.A(this._);
                    }
                    else {
                        b5 = z.C.A.A.B.A.Q.A(this.U[t - '\u0001']);
                    }
                    if (b5 == z.C.A.A.B.A.Q.A(c) == (this.H[i] == '\u0014')) {
                        return false;
                    }
                    break;
                }
                case '\u0016': {
                    if (!b && t >= this.M) {
                        return false;
                    }
                    if (!Character.isWhitespace(c)) {
                        return false;
                    }
                    b = (++t < this.c);
                    c = (b ? this.U[t] : '\uffff');
                    break;
                }
                case '\u0017': {
                    if (!b) {
                        return false;
                    }
                    if (Character.isWhitespace(c)) {
                        return false;
                    }
                    b = (++t < this.c);
                    c = (b ? this.U[t] : '\uffff');
                    break;
                }
                case '\u0018': {
                    if (!Character.isDigit(c)) {
                        return false;
                    }
                    b = (++t < this.c);
                    c = (b ? this.U[t] : '\uffff');
                    break;
                }
                case '\u0019': {
                    if (!b && t >= this.M) {
                        return false;
                    }
                    if (Character.isDigit(c)) {
                        return false;
                    }
                    b = (++t < this.c);
                    c = (b ? this.U[t] : '\uffff');
                    break;
                }
                case '\u001a': {
                    final char d = z.C.A.A.B.A.Q.D(this.H, i);
                    final int n2 = this.P[d];
                    if (n2 == -1) {
                        return false;
                    }
                    if (this.W[d] == -1) {
                        return false;
                    }
                    if (n2 == this.W[d]) {
                        break;
                    }
                    if (this.U[n2] != c) {
                        return false;
                    }
                    final int n3 = this.W[d] - n2;
                    if (t + n3 > this.M) {
                        return false;
                    }
                    if (n3 > '\u0001' && !A(this.U, n2, this.U, t, n3)) {
                        return false;
                    }
                    t += n3;
                    b = (t < this.c);
                    c = (b ? this.U[t] : '\uffff');
                    break;
                }
                case '\u000f': {}
                case '\u001b': {
                    final char d2 = z.C.A.A.B.A.Q.D(this.H, i);
                    this.P[d2] = t;
                    if (d2 > this.X) {
                        this.X = d2;
                        break;
                    }
                    break;
                }
                case '\u001c': {
                    final char d3 = z.C.A.A.B.A.Q.D(this.H, i);
                    this.W[d3] = t;
                    if (d3 > this.a) {
                        this.a = d3;
                        break;
                    }
                    break;
                }
                case '\u000b': {
                    final C s = new C();
                    s.G = this.S;
                    this.S = s;
                    s.C = this.a;
                    s.E = -1;
                    s.D = z.C.A.A.B.A.Q.D(this.H, i);
                    s.I = z.C.A.A.B.A.Q.A(this.H, i);
                    s.F = z.C.A.A.B.A.Q.A(i) + 2;
                    s.A = a;
                    s.H = h;
                    s.B = -1;
                    this.T = t;
                    final boolean b6 = this.B(z.C.A.A.B.A.Q.C(a));
                    this.S = s.G;
                    return b6;
                }
                case '\"': {
                    final C s2 = this.S;
                    final int e = s2.E + 1;
                    this.T = t;
                    if (t == s2.B) {
                        this.S = s2.G;
                        final int e2 = this.S.E;
                        if (this.B(s2.A)) {
                            return true;
                        }
                        this.S.E = e2;
                        this.S = s2;
                        return false;
                    }
                    else if (e < s2.D) {
                        s2.E = e;
                        s2.B = t;
                        if (this.B(s2.F)) {
                            return true;
                        }
                        s2.E = e - 1;
                        return false;
                    }
                    else if (s2.H) {
                        this.S = s2.G;
                        final int e3 = this.S.E;
                        if (this.B(s2.A)) {
                            return true;
                        }
                        this.S.E = e3;
                        this.S = s2;
                        if (e >= s2.I) {
                            return false;
                        }
                        this.T = t;
                        s2.E = e;
                        s2.B = t;
                        if (this.B(s2.F)) {
                            return true;
                        }
                        s2.E = e - 1;
                        return false;
                    }
                    else {
                        if (e < s2.I) {
                            this.C(s2.C);
                            s2.E = e;
                            s2.B = t;
                            if (this.B(s2.F)) {
                                return true;
                            }
                            this.E();
                            this.T = t;
                        }
                        this.S = s2.G;
                        final int e4 = this.S.E;
                        if (this.B(s2.A)) {
                            return true;
                        }
                        s2.E = e4;
                        this.S = s2;
                        s2.E = e - 1;
                        return false;
                    }
                    break;
                }
                case '\f': {
                    if (this.H[a] != '\f') {
                        a = z.C.A.A.B.A.Q.A(i);
                        break;
                    }
                    final int a2 = this.a;
                    do {
                        this.T = t;
                        if (this.B(z.C.A.A.B.A.Q.A(i))) {
                            return true;
                        }
                        int j;
                        for (j = this.a; j > a2; --j) {
                            this.W[j] = -1;
                        }
                        this.a = j;
                        i = z.C.A.A.B.A.Q.B(this.H, i);
                    } while (i != -1 && this.H[i] == '\f');
                    return false;
                }
                case '\u001d': {
                    h = true;
                    break;
                }
                case '\n':
                case '\u0010':
                case '\u0011': {
                    char d4;
                    char a3;
                    int n4;
                    if (c2 == '\n') {
                        d4 = z.C.A.A.B.A.Q.D(this.H, i);
                        a3 = z.C.A.A.B.A.Q.A(this.H, i);
                        n4 = z.C.A.A.B.A.Q.A(i) + 2;
                    }
                    else if (c2 == '\u0010') {
                        d4 = '\0';
                        a3 = '\uffff';
                        n4 = z.C.A.A.B.A.Q.A(i);
                    }
                    else {
                        d4 = '\u0001';
                        a3 = '\uffff';
                        n4 = z.C.A.A.B.A.Q.A(i);
                    }
                    char c4;
                    int n5;
                    if (this.H[a] == '\u000e') {
                        c4 = this.H[z.C.A.A.B.A.Q.B(a) + 1];
                        n5 = 0;
                    }
                    else {
                        c4 = '\uffff';
                        n5 = -1000;
                    }
                    this.T = t;
                    if (h) {
                        if (d4 > '\0' && this.A(n4, d4) < d4) {
                            return false;
                        }
                        while (a3 >= d4 || (a3 == '\uffff' && d4 > '\0')) {
                            if ((n5 == -1000 || this.T >= this.c || this.U[this.T] == c4) && this.B(a)) {
                                return true;
                            }
                            this.T = t + d4;
                            if (this.A(n4, 1) == 0) {
                                return false;
                            }
                            ++d4;
                            this.T = t + d4;
                        }
                    }
                    else {
                        int k = this.A(n4, a3);
                        if (d4 < k && z.C.A.A.B.A.Q.l[this.H[a]] == '\u0004' && ((!this.L && this.H[a] != '\u0005') || this.H[a] == '\u0006')) {
                            d4 = (char)k;
                        }
                        while (k >= d4) {
                            if ((n5 == -1000 || this.T >= this.c || this.U[this.T] == c4) && this.B(a)) {
                                return true;
                            }
                            --k;
                            this.T = t + k;
                        }
                    }
                    return false;
                }
                case '\0':
                case '!': {
                    this.T = t;
                    return this.T != this.O;
                }
                case '\u001f': {
                    this.T = t;
                    if (!this.B(z.C.A.A.B.A.Q.A(i))) {
                        return false;
                    }
                    break;
                }
                case ' ': {
                    this.T = t;
                    if (this.B(z.C.A.A.B.A.Q.A(i))) {
                        return false;
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    public void A(final boolean l) {
        this.L = l;
    }
    
    public boolean D() {
        return this.L;
    }
    
    char[] A(char[] array) {
        final char[] array2 = new char[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);
        array = array2;
        for (int i = 0; i < array.length; ++i) {
            if (Character.isUpperCase(array[i])) {
                array[i] = Character.toLowerCase(array[i]);
            }
        }
        return array;
    }
    
    public boolean A(char[] a, final G g, final int n) {
        final D d = (D)g;
        this.b = a;
        if (d.f) {
            a = this.A(a);
        }
        this.B(d, a, 0, a.length, n);
        this.Y = this.A(n);
        this.R = null;
        return this.Y;
    }
    
    public boolean B(final char[] array, final G g) {
        return this.A(array, g, 0);
    }
    
    public boolean B(final String s, final G g) {
        return this.A(s.toCharArray(), g, 0);
    }
    
    public boolean C(final B b, final G g) {
        final D d = (D)g;
        this.b = b.H;
        char[] array;
        if (d.f) {
            if (b.E == null) {
                b.E = this.A(this.b);
            }
            array = b.E;
        }
        else {
            array = this.b;
        }
        this.B(d, array, b.G, b.F, b.A);
        this.Y = this.A(b.A);
        this.R = null;
        return this.Y;
    }
    
    public boolean A(char[] a, final G g) {
        final D d = (D)g;
        this.b = a;
        if (d.f) {
            a = this.A(a);
        }
        this.B(d, a, 0, a.length, 0);
        this.Y = (this.A(0) && this.W[0] == a.length);
        this.R = null;
        return this.Y;
    }
    
    public boolean A(final String s, final G g) {
        return this.A(s.toCharArray(), g);
    }
    
    public boolean A(final B b, final G g) {
        final D d = (D)g;
        this.b = b.H;
        char[] array;
        if (d.f) {
            if (b.E == null) {
                b.E = this.A(this.b);
            }
            array = b.E;
        }
        else {
            array = this.b;
        }
        this.B(d, array, b.G, b.F, b.G);
        this.R = null;
        if (this.A(b.G) && (this.W[0] == b.F || b.L() == 0 || b.G == b.F)) {
            return this.Y = true;
        }
        return this.Y = false;
    }
    
    public boolean C(final String s, final G g) {
        return this.C(s.toCharArray(), g);
    }
    
    public boolean C(char[] a, final G g) {
        final D d = (D)g;
        this.b = a;
        if (d.f) {
            a = this.A(a);
        }
        return this.A(d, a, 0, a.length, 0);
    }
    
    public boolean B(final B b, final G g) {
        if (b.A > b.F) {
            return false;
        }
        final D d = (D)g;
        this.b = b.H;
        this.b = b.H;
        char[] array;
        if (d.f) {
            if (b.E == null) {
                b.E = this.A(this.b);
            }
            array = b.E;
        }
        else {
            array = this.b;
        }
        this.O = b.E();
        final boolean a = this.A(d, array, b.G, b.F, b.A);
        if (a) {
            b.C(this.W[0]);
            b.B(this.P[0], this.W[0]);
        }
        else {
            b.C(b.F + 1);
        }
        this.O = -100;
        return a;
    }
    
    public M A() {
        if (!this.Y) {
            return null;
        }
        if (this.R == null) {
            this.F();
        }
        return this.R;
    }
}
