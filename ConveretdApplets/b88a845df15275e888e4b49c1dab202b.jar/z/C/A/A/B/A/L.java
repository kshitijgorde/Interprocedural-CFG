// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.A;

import java.util.HashMap;

public final class L implements R
{
    private static final int h = 0;
    private static final int b = 1;
    private static final int _ = 2;
    private static final int n = 4;
    private static final int p = 8;
    private static final char v = '\u0001';
    private static final char Y = '\u0002';
    private static final char g = '\u0004';
    private static final char o = '\b';
    private static final char m = '\u0010';
    private static final char s = ' ';
    private static final char a = '\u8000';
    private static final String u = "0123456789abcdef0123456789ABCDEFx";
    private K j;
    private boolean t;
    private char[] q;
    private int c;
    private int d;
    private int X;
    private char[] W;
    private static final HashMap Z;
    public static final int f = 0;
    public static final int i = 1;
    public static final int l = 8;
    public static final int e = 16;
    public static final int r = 32;
    public static final int k = 32768;
    
    public L() {
        this.q = new char[] { '\0' };
    }
    
    public static final String C(final char[] array) {
        final StringBuffer sb = new StringBuffer(2 * array.length);
        for (int i = 0; i < array.length; ++i) {
            if (!Q.A(array[i])) {
                sb.append('\\');
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }
    
    public static final String B(final String s) {
        return C(s.toCharArray());
    }
    
    private static boolean K(final char c) {
        return c == '*' || c == '+' || c == '?';
    }
    
    private static boolean D(final char[] array, final int n) {
        return n < array.length && n >= 0 && (array[n] == '*' || array[n] == '+' || array[n] == '?' || (array[n] == '{' && C(array, n)));
    }
    
    private static boolean C(final char[] array, int n) {
        if (array[n] != '{') {
            return false;
        }
        if (++n >= array.length || !Character.isDigit(array[n])) {
            return false;
        }
        while (n < array.length && Character.isDigit(array[n])) {
            ++n;
        }
        if (n < array.length && array[n] == ',') {
            ++n;
        }
        while (n < array.length && Character.isDigit(array[n])) {
            ++n;
        }
        return n < array.length && array[n] == '}';
    }
    
    private static int B(final char[] array, int n, int n2, final int[] array2) {
        int n3 = 0;
        array2[0] = 0;
        int index;
        while (n < array.length && n2-- > 0 && (index = "0123456789abcdef0123456789ABCDEFx".indexOf(array[n])) != -1) {
            n3 = (n3 << 4 | (index & 0xF));
            ++n;
            final int n4 = 0;
            ++array2[n4];
        }
        return n3;
    }
    
    private static int A(final char[] array, int n, int n2, final int[] array2) {
        int n3 = 0;
        array2[0] = 0;
        while (n < array.length && n2 > 0 && array[n] >= '0' && array[n] <= '7') {
            n3 = (n3 << 3 | array[n] - '0');
            --n2;
            ++n;
            final int n4 = 0;
            ++array2[n4];
        }
        return n3;
    }
    
    private static void A(final char[] array, final char c) {
        switch (c) {
            case 'i': {
                final int n = 0;
                array[n] |= '\u0001';
            }
            case 'g': {
                final int n2 = 0;
                array[n2] |= '\u0002';
            }
            case 'o': {
                final int n3 = 0;
                array[n3] |= '\u0004';
            }
            case 'm': {
                final int n4 = 0;
                array[n4] |= '\b';
            }
            case 's': {
                final int n5 = 0;
                array[n5] |= '\u0010';
            }
            case 'x': {
                final int n6 = 0;
                array[n6] |= ' ';
            }
            default: {}
        }
    }
    
    private void I(final char c) {
        if (this.W != null) {
            this.W[this.d] = c;
        }
        ++this.d;
    }
    
    private int J(final char c) {
        final int d = this.d;
        if (this.W == null) {
            this.d += 2;
        }
        else {
            this.W[this.d++] = c;
            this.W[this.d++] = '\0';
        }
        return d;
    }
    
    private int A(final char c, final char c2) {
        final int d = this.d;
        if (this.W == null) {
            this.d += 3;
        }
        else {
            this.W[this.d++] = c;
            this.W[this.d++] = '\0';
            this.W[this.d++] = c2;
        }
        return d;
    }
    
    private void B(final char c, int n) {
        int n2 = (Q.l[c] == '\n') ? 2 : 0;
        if (this.W == null) {
            this.d += 2 + n2;
            return;
        }
        int i = this.d;
        this.d += 2 + n2;
        for (int d = this.d; i > n; --i, --d, this.W[d] = this.W[i]) {}
        this.W[n++] = c;
        this.W[n++] = '\0';
        while (n2-- > 0) {
            this.W[n++] = '\0';
        }
    }
    
    private void B(final int n, final int n2) {
        if (this.W == null || n == -1) {
            return;
        }
        int n3 = n;
        while (true) {
            final int b = Q.B(this.W, n3);
            if (b == -1) {
                break;
            }
            n3 = b;
        }
        int n4;
        if (this.W[n3] == '\r') {
            n4 = n3 - n2;
        }
        else {
            n4 = n2 - n3;
        }
        this.W[n3 + 1] = (char)n4;
    }
    
    private void A(final int n, final int n2) {
        if (this.W == null || n == -1 || Q.l[this.W[n]] != '\f') {
            return;
        }
        this.B(Q.A(n), n2);
    }
    
    private char H() {
        final char e = this.j.E();
        while (true) {
            char c = this.j.B();
            if (c == '(' && this.j.C(1) == '?' && this.j.C(2) == '#') {
                while (c != '\uffff' && c != ')') {
                    c = this.j.H();
                }
                this.j.H();
            }
            else {
                if ((this.q[0] & ' ') == '\0') {
                    break;
                }
                if (Character.isWhitespace(c)) {
                    this.j.H();
                }
                else {
                    if (c != '#') {
                        break;
                    }
                    while (c != '\uffff' && c != '\n') {
                        c = this.j.H();
                    }
                    this.j.H();
                }
            }
        }
        return e;
    }
    
    private int A(final int[] array) throws H {
        int n = 0;
        array[0] = 0;
        final int j = this.J('\f');
        int n2 = -1;
        if (this.j.C() == 0) {
            this.j.D(-1);
            this.H();
        }
        else {
            this.j.G();
            this.H();
        }
        for (char c = this.j.B(); c != '\uffff' && c != '|' && c != ')'; c = this.j.B()) {
            n &= 0xFFFFFFF7;
            final int c2 = this.C(array);
            if (c2 == -1) {
                if ((n & 0x8) == 0x0) {
                    return -1;
                }
            }
            else {
                final int n3 = 0;
                array[n3] |= (n & 0x1);
                if (n2 == -1) {
                    final int n4 = 0;
                    array[n4] |= (n & 0x4);
                }
                else {
                    ++this.X;
                    this.B(n2, c2);
                }
                n2 = c2;
            }
        }
        if (n2 == -1) {
            this.J('\u000f');
        }
        return j;
    }
    
    private int B(final int[] array) throws H {
        final int[] array2 = { 0 };
        array[0] = 0;
        boolean b = false;
        int n = -1;
        Label_1299: {
        Label_1286:
            while (true) {
                switch (this.j.B()) {
                    case '^': {
                        this.H();
                        if ((this.q[0] & '\b') != '\0') {
                            n = this.J('\u0002');
                            break Label_1299;
                        }
                        if ((this.q[0] & '\u0010') != '\0') {
                            n = this.J('\u0003');
                            break Label_1299;
                        }
                        n = this.J('\u0001');
                        break Label_1299;
                    }
                    case '$': {
                        this.H();
                        if ((this.q[0] & '\b') != '\0') {
                            n = this.J('\u0005');
                            break Label_1299;
                        }
                        if ((this.q[0] & '\u0010') != '\0') {
                            n = this.J('\u0006');
                            break Label_1299;
                        }
                        n = this.J('\u0004');
                        break Label_1299;
                    }
                    case '.': {
                        this.H();
                        if ((this.q[0] & '\u0010') != '\0') {
                            n = this.J('\b');
                        }
                        else {
                            n = this.J('\u0007');
                        }
                        ++this.X;
                        final int n2 = 0;
                        array[n2] |= 0x3;
                        break Label_1299;
                    }
                    case '[': {
                        this.j.H();
                        n = this.I();
                        final int n3 = 0;
                        array[n3] |= 0x3;
                        break Label_1299;
                    }
                    case '(': {
                        this.H();
                        n = this.A(true, array2);
                        if (n != -1) {
                            final int n4 = 0;
                            array[n4] |= (array2[0] & 0x5);
                            break Label_1299;
                        }
                        if ((array2[0] & 0x8) != 0x0) {
                            continue;
                        }
                        return -1;
                    }
                    case ')':
                    case '|': {
                        if ((array2[0] & 0x8) != 0x0) {
                            final int n5 = 0;
                            array[n5] |= 0x8;
                            return -1;
                        }
                        throw new H("Error in expression at " + this.j.E(this.j.C()));
                    }
                    case '*':
                    case '+':
                    case '?': {
                        throw new H("?+* follows nothing in expression");
                    }
                    case '\\': {
                        Label_1225: {
                            switch (this.j.H()) {
                                case 'A': {
                                    n = this.J('\u0003');
                                    final int n6 = 0;
                                    array[n6] |= 0x2;
                                    this.H();
                                    break Label_1225;
                                }
                                case 'G': {
                                    n = this.J('\u001e');
                                    final int n7 = 0;
                                    array[n7] |= 0x2;
                                    this.H();
                                    break Label_1225;
                                }
                                case 'Z': {
                                    n = this.J('\u0006');
                                    final int n8 = 0;
                                    array[n8] |= 0x2;
                                    this.H();
                                    break Label_1225;
                                }
                                case 'w': {
                                    n = this.J('\u0012');
                                    final int n9 = 0;
                                    array[n9] |= 0x3;
                                    this.H();
                                    break Label_1225;
                                }
                                case 'W': {
                                    n = this.J('\u0013');
                                    final int n10 = 0;
                                    array[n10] |= 0x3;
                                    this.H();
                                    break Label_1225;
                                }
                                case 'b': {
                                    n = this.J('\u0014');
                                    final int n11 = 0;
                                    array[n11] |= 0x2;
                                    this.H();
                                    break Label_1225;
                                }
                                case 'B': {
                                    n = this.J('\u0015');
                                    final int n12 = 0;
                                    array[n12] |= 0x2;
                                    this.H();
                                    break Label_1225;
                                }
                                case 's': {
                                    n = this.J('\u0016');
                                    final int n13 = 0;
                                    array[n13] |= 0x3;
                                    this.H();
                                    break Label_1225;
                                }
                                case 'S': {
                                    n = this.J('\u0017');
                                    final int n14 = 0;
                                    array[n14] |= 0x3;
                                    this.H();
                                    break Label_1225;
                                }
                                case 'd': {
                                    n = this.J('\u0018');
                                    final int n15 = 0;
                                    array[n15] |= 0x3;
                                    this.H();
                                    break Label_1225;
                                }
                                case 'D': {
                                    n = this.J('\u0019');
                                    final int n16 = 0;
                                    array[n16] |= 0x3;
                                    this.H();
                                    break Label_1225;
                                }
                                case '0':
                                case 'a':
                                case 'c':
                                case 'e':
                                case 'f':
                                case 'n':
                                case 'r':
                                case 't':
                                case 'x': {
                                    b = true;
                                    break Label_1299;
                                }
                                case '1':
                                case '2':
                                case '3':
                                case '4':
                                case '5':
                                case '6':
                                case '7':
                                case '8':
                                case '9': {
                                    final StringBuffer sb = new StringBuffer(10);
                                    int n17 = 0;
                                    for (char c = this.j.C(n17); Character.isDigit(c); c = this.j.C(n17)) {
                                        sb.append(c);
                                        ++n17;
                                    }
                                    int int1;
                                    try {
                                        int1 = Integer.parseInt(sb.toString());
                                    }
                                    catch (NumberFormatException ex) {
                                        throw new H("Unexpected number format exception.  Please report this bug.NumberFormatException message: " + ex.getMessage());
                                    }
                                    if (int1 > 9 && int1 >= this.c) {
                                        b = true;
                                        break Label_1299;
                                    }
                                    if (int1 >= this.c) {
                                        throw new H("Invalid backreference: \\" + int1);
                                    }
                                    this.t = true;
                                    n = this.A('\u001a', (char)int1);
                                    final int n18 = 0;
                                    array[n18] |= 0x1;
                                    for (char c2 = this.j.B(); Character.isDigit(c2); c2 = this.j.H()) {}
                                    this.j.G();
                                    this.H();
                                    break Label_1225;
                                }
                                case '\0':
                                case '\uffff': {
                                    if (this.j.A()) {
                                        throw new H("Trailing \\ in expression.");
                                    }
                                    break;
                                }
                            }
                            b = true;
                        }
                        break Label_1299;
                    }
                    case '#': {
                        if ((this.q[0] & ' ') == '\0') {
                            break Label_1286;
                        }
                        while (!this.j.A() && this.j.B() != '\n') {
                            this.j.H();
                        }
                        if (!this.j.A()) {
                            continue;
                        }
                        break Label_1286;
                    }
                    default: {
                        break Label_1286;
                    }
                }
            }
            this.j.H();
            b = true;
        }
        if (b) {
            n = this.J('\u000e');
            this.I('\uffff');
            int n19 = 0;
            int n20 = this.j.C() - 1;
        Label_2324:
            for (int d = this.j.D(); n19 < 127 && n20 < d; ++n19) {
                final int n21 = n20;
                char c3 = '\0';
                Label_2240: {
                    switch (this.j.A(n20)) {
                        case '$':
                        case '(':
                        case ')':
                        case '.':
                        case '[':
                        case '^':
                        case '|': {
                            break Label_2324;
                        }
                        case '\\': {
                            Label_2163: {
                                switch (this.j.A(++n20)) {
                                    case 'A':
                                    case 'B':
                                    case 'D':
                                    case 'G':
                                    case 'S':
                                    case 'W':
                                    case 'Z':
                                    case 'b':
                                    case 'd':
                                    case 's':
                                    case 'w': {
                                        --n20;
                                        break Label_2324;
                                    }
                                    case 'n': {
                                        c3 = '\n';
                                        ++n20;
                                        break Label_2163;
                                    }
                                    case 'r': {
                                        c3 = '\r';
                                        ++n20;
                                        break Label_2163;
                                    }
                                    case 't': {
                                        c3 = '\t';
                                        ++n20;
                                        break Label_2163;
                                    }
                                    case 'f': {
                                        c3 = '\f';
                                        ++n20;
                                        break Label_2163;
                                    }
                                    case 'e': {
                                        c3 = '\u001b';
                                        ++n20;
                                        break Label_2163;
                                    }
                                    case 'a': {
                                        c3 = '\u0007';
                                        ++n20;
                                        break Label_2163;
                                    }
                                    case 'x': {
                                        final int[] array3 = { 0 };
                                        c3 = (char)B(this.j.C, ++n20, 2, array3);
                                        n20 += array3[0];
                                        break Label_2163;
                                    }
                                    case 'c': {
                                        ++n20;
                                        char c4 = this.j.A(n20++);
                                        if (Character.isLowerCase(c4)) {
                                            c4 = Character.toUpperCase(c4);
                                        }
                                        c3 = (char)(c4 ^ '@');
                                        break Label_2163;
                                    }
                                    case '0':
                                    case '1':
                                    case '2':
                                    case '3':
                                    case '4':
                                    case '5':
                                    case '6':
                                    case '7':
                                    case '8':
                                    case '9': {
                                        boolean b2 = false;
                                        if (this.j.A(n20) == '0') {
                                            b2 = true;
                                        }
                                        if (Character.isDigit(this.j.A(n20 + 1))) {
                                            final StringBuffer sb2 = new StringBuffer(10);
                                            int n22 = n20;
                                            for (char c5 = this.j.A(n22); Character.isDigit(c5); c5 = this.j.A(n22)) {
                                                sb2.append(c5);
                                                ++n22;
                                            }
                                            int int2;
                                            try {
                                                int2 = Integer.parseInt(sb2.toString());
                                            }
                                            catch (NumberFormatException ex2) {
                                                throw new H("Unexpected number format exception.  Please report this bug.NumberFormatException message: " + ex2.getMessage());
                                            }
                                            if (!b2) {
                                                b2 = (int2 >= this.c);
                                            }
                                        }
                                        if (b2) {
                                            final int[] array4 = { 0 };
                                            c3 = (char)A(this.j.C, n20, 3, array4);
                                            n20 += array4[0];
                                            break Label_2163;
                                        }
                                        --n20;
                                        break Label_2324;
                                    }
                                    case '\0':
                                    case '\uffff': {
                                        if (n20 >= d) {
                                            throw new H("Trailing \\ in expression.");
                                        }
                                        break;
                                    }
                                }
                                c3 = this.j.A(n20++);
                            }
                            break Label_2240;
                        }
                        case '#': {
                            if ((this.q[0] & ' ') != '\0') {
                                while (n20 < d && this.j.A(n20) != '\n') {
                                    ++n20;
                                }
                            }
                        }
                        case '\t':
                        case '\n':
                        case '\u000b':
                        case '\f':
                        case '\r':
                        case ' ': {
                            if ((this.q[0] & ' ') != '\0') {
                                ++n20;
                                --n19;
                                continue;
                            }
                            break;
                        }
                    }
                    c3 = this.j.A(n20++);
                }
                if ((this.q[0] & '\u0001') != '\0' && Character.isUpperCase(c3)) {
                    c3 = Character.toLowerCase(c3);
                }
                if (n20 < d && D(this.j.C, n20)) {
                    if (n19 > 0) {
                        n20 = n21;
                        break;
                    }
                    ++n19;
                    this.I(c3);
                    break;
                }
                else {
                    this.I(c3);
                }
            }
            this.j.D(n20 - 1);
            this.H();
            if (n19 < 0) {
                throw new H("Unexpected compilation failure.  Please report this bug!");
            }
            if (n19 > 0) {
                final int n23 = 0;
                array[n23] |= 0x1;
            }
            if (n19 == 1) {
                final int n24 = 0;
                array[n24] |= 0x2;
            }
            if (this.W != null) {
                this.W[Q.B(n)] = (char)n19;
            }
            this.I('\uffff');
        }
        return n;
    }
    
    private int I() throws H {
        int n = 0;
        char c = '\uffff';
        final int[] array = { 0 };
        final boolean[] array2 = { false };
        int n2;
        if (this.j.B() == '^') {
            n2 = this.J('$');
            this.j.H();
        }
        else {
            n2 = this.J('#');
        }
        char c2 = this.j.B();
        int n3;
        if (c2 == ']' || c2 == '-') {
            n3 = 1;
        }
        else {
            n3 = 0;
        }
        while ((!this.j.A() && (c2 = this.j.B()) != ']') || n3 != 0) {
            n3 = 0;
            int n4 = 0;
            this.j.H();
            if (c2 == '\\' || c2 == '[') {
                if (c2 == '\\') {
                    c2 = this.j.E();
                }
                else {
                    final char a = this.A(array2);
                    if (a != '\0') {
                        n4 = 1;
                        c2 = a;
                    }
                }
                if (n4 != 1) {
                    switch (c2) {
                        case 119: {
                            n4 = 1;
                            c2 = '\u0012';
                            c = '\uffff';
                            break;
                        }
                        case 87: {
                            n4 = 1;
                            c2 = '\u0013';
                            c = '\uffff';
                            break;
                        }
                        case 115: {
                            n4 = 1;
                            c2 = '\u0016';
                            c = '\uffff';
                            break;
                        }
                        case 83: {
                            n4 = 1;
                            c2 = '\u0017';
                            c = '\uffff';
                            break;
                        }
                        case 100: {
                            n4 = 1;
                            c2 = '\u0018';
                            c = '\uffff';
                            break;
                        }
                        case 68: {
                            n4 = 1;
                            c2 = '\u0019';
                            c = '\uffff';
                            break;
                        }
                        case 110: {
                            c2 = '\n';
                            break;
                        }
                        case 114: {
                            c2 = '\r';
                            break;
                        }
                        case 116: {
                            c2 = '\t';
                            break;
                        }
                        case 102: {
                            c2 = '\f';
                            break;
                        }
                        case 98: {
                            c2 = '\b';
                            break;
                        }
                        case 101: {
                            c2 = '\u001b';
                            break;
                        }
                        case 97: {
                            c2 = '\u0007';
                            break;
                        }
                        case 120: {
                            c2 = (char)B(this.j.C, this.j.C(), 2, array);
                            this.j.B(array[0]);
                            break;
                        }
                        case 99: {
                            char c3 = this.j.E();
                            if (Character.isLowerCase(c3)) {
                                c3 = Character.toUpperCase(c3);
                            }
                            c2 = (char)(c3 ^ '@');
                            break;
                        }
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            c2 = (char)A(this.j.C, this.j.C() - 1, 3, array);
                            this.j.B(array[0] - 1);
                            break;
                        }
                    }
                }
            }
            if (n != 0) {
                if (c > c2) {
                    throw new H("Invalid [] range in expression.");
                }
                n = 0;
            }
            else {
                c = c2;
                if (n4 == 0 && this.j.B() == '-' && this.j.C() + 1 < this.j.D() && this.j.C(1) != ']') {
                    this.j.H();
                    n = 1;
                    continue;
                }
            }
            if (c == c2) {
                if (n4 == 1) {
                    if (!array2[0]) {
                        this.I('/');
                    }
                    else {
                        this.I('0');
                    }
                }
                else {
                    this.I('1');
                }
                this.I(c2);
                if ((this.q[0] & '\u0001') != '\0' && Character.isUpperCase(c2) && Character.isUpperCase(c)) {
                    --this.d;
                    this.I(Character.toLowerCase(c2));
                }
            }
            if (c < c2) {
                this.I('%');
                this.I(c);
                this.I(c2);
                if ((this.q[0] & '\u0001') != '\0' && Character.isUpperCase(c2) && Character.isUpperCase(c)) {
                    this.d -= 2;
                    this.I(Character.toLowerCase(c));
                    this.I(Character.toLowerCase(c2));
                }
                n = 0;
            }
            c = c2;
        }
        if (this.j.B() != ']') {
            throw new H("Unmatched [] in expression.");
        }
        this.H();
        this.I('\0');
        return n2;
    }
    
    private char A(final boolean[] array) throws H {
        final int c = this.j.C();
        final int d = this.j.D();
        int n = c;
        if (this.j.A(n++) != ':') {
            return '\0';
        }
        if (this.j.A(n) == '^') {
            array[0] = true;
            ++n;
        }
        else {
            array[0] = false;
        }
        final StringBuffer sb = new StringBuffer();
        try {
            char a;
            while ((a = this.j.A(n++)) != ':' && n < d) {
                sb.append(a);
            }
        }
        catch (Exception ex) {
            return '\0';
        }
        if (this.j.A(n++) != ']') {
            return '\0';
        }
        final Object value = L.Z.get(sb.toString());
        if (value == null) {
            return '\0';
        }
        this.j.D(n);
        return (char)value;
    }
    
    private int C(final int[] array) throws H {
        boolean b = false;
        boolean b2 = false;
        final int[] array2 = { 0 };
        int int1 = 0;
        int int2 = 65535;
        final int b3 = this.B(array2);
        if (b3 == -1) {
            if ((array2[0] & 0x8) != 0x0) {
                final int n = 0;
                array[n] |= 0x8;
            }
            return -1;
        }
        char c = this.j.B();
        if (c == '(' && this.j.C(1) == '?' && this.j.C(2) == '#') {
            while (c != '\uffff' && c != ')') {
                c = this.j.H();
            }
            if (c != '\uffff') {
                this.H();
                c = this.j.B();
            }
        }
        if (c == '{' && C(this.j.C, this.j.C())) {
            int n2 = this.j.C() + 1;
            int n4;
            final int n3 = n4 = this.j.D();
            char c2;
            for (c2 = this.j.A(n2); Character.isDigit(c2) || c2 == ','; c2 = this.j.A(n2)) {
                if (c2 == ',') {
                    if (n4 != n3) {
                        break;
                    }
                    n4 = n2;
                }
                ++n2;
            }
            if (c2 == '}') {
                final StringBuffer sb = new StringBuffer(10);
                if (n4 == n3) {
                    n4 = n2;
                }
                this.j.H();
                int c3 = this.j.C();
                for (char c4 = this.j.A(c3); Character.isDigit(c4); c4 = this.j.A(c3)) {
                    sb.append(c4);
                    ++c3;
                }
                try {
                    int1 = Integer.parseInt(sb.toString());
                }
                catch (NumberFormatException ex) {
                    throw new H("Unexpected number format exception.  Please report this bug.NumberFormatException message: " + ex.getMessage());
                }
                if (this.j.A(n4) == ',') {
                    ++n4;
                }
                else {
                    n4 = this.j.C();
                }
                int n5 = n4;
                final StringBuffer sb2 = new StringBuffer(10);
                for (char c5 = this.j.A(n5); Character.isDigit(c5); c5 = this.j.A(n5)) {
                    sb2.append(c5);
                    ++n5;
                }
                try {
                    if (n5 != n4) {
                        int2 = Integer.parseInt(sb2.toString());
                    }
                }
                catch (NumberFormatException ex2) {
                    throw new H("Unexpected number format exception.  Please report this bug.NumberFormatException message: " + ex2.getMessage());
                }
                if (int2 == 0 && this.j.A(n4) != '0') {
                    int2 = 65535;
                }
                this.j.D(n2);
                this.H();
                b = true;
                b2 = true;
            }
        }
        if (!b) {
            b2 = false;
            if (!K(c)) {
                array[0] = array2[0];
                return b3;
            }
            this.H();
            array[0] = ((c != '+') ? 4 : 1);
            if (c == '*' && (array2[0] & 0x2) != 0x0) {
                this.B('\u0010', b3);
                this.X += 4;
            }
            else if (c == '*') {
                int1 = 0;
                b2 = true;
            }
            else if (c == '+' && (array2[0] & 0x2) != 0x0) {
                this.B('\u0011', b3);
                this.X += 3;
            }
            else if (c == '+') {
                int1 = 1;
                b2 = true;
            }
            else if (c == '?') {
                int1 = 0;
                int2 = 1;
                b2 = true;
            }
        }
        if (b2) {
            if ((array2[0] & 0x2) != 0x0) {
                this.X += (2 + this.X) / 2;
                this.B('\n', b3);
            }
            else {
                this.X += 4 + this.X;
                this.B(b3, this.J('\"'));
                this.B('\u000b', b3);
                this.B(b3, this.J('\u000f'));
            }
            if (int1 > 0) {
                array[0] = 1;
            }
            if (int2 != 0 && int2 < int1) {
                throw new H("Invalid interval {" + int1 + "," + int2 + "}");
            }
            if (this.W != null) {
                this.W[b3 + 2] = (char)int1;
                this.W[b3 + 3] = (char)int2;
            }
        }
        if (this.j.B() == '?') {
            this.H();
            this.B('\u001d', b3);
            this.B(b3, b3 + 2);
        }
        if (D(this.j.C, this.j.C())) {
            throw new H("Nested repetitions *?+ in expression");
        }
        return b3;
    }
    
    private int A(final boolean b, final int[] array) throws H {
        final char[] array2 = { '\0' };
        final char[] array3 = { '\0' };
        int a = -1;
        int c = 0;
        final int[] array4 = { 0 };
        final String s = "iogmsx-";
        char[] array5 = array2;
        array[0] = 1;
        char e;
        if (b) {
            e = '\u0001';
            if (this.j.B() == '?') {
                this.j.H();
                switch (e = this.j.E()) {
                    case '!':
                    case ':':
                    case '=': {
                        break;
                    }
                    case '#': {
                        char c2;
                        for (c2 = this.j.B(); c2 != '\uffff' && c2 != ')'; c2 = this.j.H()) {}
                        if (c2 != ')') {
                            throw new H("Sequence (?#... not terminated");
                        }
                        this.H();
                        array[0] = 8;
                        return -1;
                    }
                    default: {
                        this.j.G();
                        char c3;
                        for (c3 = this.j.B(); c3 != '\uffff' && s.indexOf(c3) != -1; c3 = this.j.H()) {
                            if (c3 == '-') {
                                array5 = array3;
                            }
                            else {
                                A(array5, c3);
                            }
                        }
                        final char[] q = this.q;
                        final int n = 0;
                        q[n] |= array2[0];
                        final char[] q2 = this.q;
                        final int n2 = 0;
                        q2[n2] &= (char)~array3[0];
                        if (c3 != ')') {
                            throw new H("Sequence (?" + c3 + "...) not recognized");
                        }
                        this.H();
                        array[0] = 8;
                        return -1;
                    }
                }
            }
            else {
                c = this.c;
                ++this.c;
                a = this.A('\u001b', (char)c);
            }
        }
        else {
            e = '\0';
        }
        final int a2 = this.A(array4);
        if (a2 == -1) {
            return -1;
        }
        if (a != -1) {
            this.B(a, a2);
        }
        else {
            a = a2;
        }
        if ((array4[0] & 0x1) == 0x0) {
            final int n3 = 0;
            array[n3] &= 0xFFFFFFFE;
        }
        final int n4 = 0;
        array[n4] |= (array4[0] & 0x4);
        while (this.j.B() == '|') {
            this.H();
            final int a3 = this.A(array4);
            if (a3 == -1) {
                return -1;
            }
            this.B(a, a3);
            if ((array4[0] & 0x1) == 0x0) {
                final int n5 = 0;
                array[n5] &= 0xFFFFFFFE;
            }
            final int n6 = 0;
            array[n6] |= (array4[0] & 0x4);
        }
        int n7 = 0;
        switch (e) {
            case 58: {
                n7 = this.J('\u000f');
                break;
            }
            case 1: {
                n7 = this.A('\u001c', (char)c);
                break;
            }
            case 33:
            case 61: {
                n7 = this.J('!');
                final int n8 = 0;
                array[n8] &= 0xFFFFFFFE;
                break;
            }
            default: {
                n7 = this.J('\0');
                break;
            }
        }
        this.B(a, n7);
        for (int i = a; i != -1; i = Q.B(this.W, i)) {
            this.A(i, n7);
        }
        if (e == '=') {
            this.B('\u001f', a);
            this.B(a, this.J('\u000f'));
        }
        else if (e == '!') {
            this.B(' ', a);
            this.B(a, this.J('\u000f'));
        }
        if (e != '\0' && (this.j.A() || this.H() != ')')) {
            throw new H("Unmatched parentheses.");
        }
        if (e != '\0' || this.j.A()) {
            return a;
        }
        if (this.j.B() == ')') {
            throw new H("Unmatched parentheses.");
        }
        throw new H("Unreached characters at end of expression.  Please report this bug!");
    }
    
    public G A(final char[] array, final int a) throws H {
        final int[] array2 = { 0 };
        boolean b = false;
        boolean b2 = false;
        char d = '\0';
        this.j = new K(array);
        this.q[0] = (char)a;
        this.t = false;
        this.c = 1;
        this.d = 0;
        this.X = 0;
        this.W = null;
        this.I('\0');
        if (this.A(false, array2) == -1) {
            throw new H("Unknown compilation error.");
        }
        if (this.d >= 65534) {
            throw new H("Expression is too large.");
        }
        this.W = new char[this.d];
        final D d2 = new D();
        d2.g = this.W;
        d2.X = new String(array);
        this.j.D(0);
        this.c = 1;
        this.d = 0;
        this.X = 0;
        this.I('\0');
        if (this.A(false, array2) == -1) {
            throw new H("Unknown compilation error.");
        }
        final char c = (char)(this.q[0] & '\u0001');
        d2.i = (this.X >= 10);
        d2._ = -1;
        d2.b = 0;
        d2.V = -1;
        d2.a = a;
        d2.h = null;
        d2.S = null;
        String string = null;
        String s = null;
        final int n = 1;
        if (this.W[Q.B(this.W, n)] == '\0') {
            int n3;
            int n2 = n3 = Q.A(n);
            for (char c2 = this.W[n3]; (c2 == '\u001b' && (b = true)) || (c2 == '\f' && this.W[Q.B(this.W, n3)] != '\f') || c2 == '\u0011' || c2 == '\u001d' || (Q.l[c2] == '\n' && Q.D(this.W, n3) > '\0'); n3 = Q.A(n3), c2 = this.W[n3]) {
                if (c2 == '\u0011') {
                    b2 = true;
                }
                else {
                    n3 += Q.N[c2];
                }
            }
            int i = 1;
            while (i != 0) {
                i = 0;
                final char c3 = this.W[n3];
                if (c3 == '\u000e') {
                    s = new String(this.W, Q.B(n3 + 1), this.W[Q.B(n3)]);
                }
                else if (Q.A(c3, Q.g, 2)) {
                    d2._ = n3;
                }
                else if (c3 == '\u0014' || c3 == '\u0015') {
                    d2._ = n3;
                }
                else if (Q.l[c3] == '\u0001') {
                    if (c3 == '\u0001') {
                        d2.b = 1;
                    }
                    else if (c3 == '\u0002') {
                        d2.b = 2;
                    }
                    else {
                        d2.b = 3;
                    }
                    n3 = Q.A(n3);
                    i = 1;
                }
                else {
                    if (c3 != '\u0010' || Q.l[this.W[Q.A(n3)]] != '\u0007' || (d2.b & 0x3) == 0x0) {
                        continue;
                    }
                    d2.b = 11;
                    n3 = Q.A(n3);
                    i = 1;
                }
            }
            if (b2 && (!b || !this.t)) {
                final D d3 = d2;
                d3.b |= 0x4;
            }
            StringBuffer sb = new StringBuffer();
            StringBuffer sb2 = new StringBuffer();
            int n4 = 0;
            d = '\0';
            int n5 = 0;
            char c4 = '\0';
            int v = 0;
            char c5;
            while (n2 > 0 && (c5 = this.W[n2]) != '\0') {
                if (c5 == '\f') {
                    if (this.W[Q.B(this.W, n2)] == '\f') {
                        n5 = -30000;
                        while (this.W[n2] == '\f') {
                            n2 = Q.B(this.W, n2);
                        }
                    }
                    else {
                        n2 = Q.A(n2);
                    }
                }
                else if (c5 == ' ') {
                    n5 = -30000;
                    n2 = Q.B(this.W, n2);
                }
                else {
                    if (c5 == '\u000e') {
                        n3 = n2;
                        int b3;
                        while (this.W[b3 = Q.B(this.W, n2)] == '\u001c') {
                            n2 = b3;
                        }
                        d += this.W[Q.B(n3)];
                        final char c6 = this.W[Q.B(n3)];
                        if (n5 - c4 == n4) {
                            sb.append(new String(this.W, Q.B(n3) + 1, c6));
                            n4 += c6;
                            n5 += c6;
                            n3 = Q.B(this.W, n2);
                        }
                        else if (c6 >= n4 + ((n5 >= '\0') ? 1 : 0)) {
                            n4 = c6;
                            sb = new StringBuffer(new String(this.W, Q.B(n3) + 1, c6));
                            c4 = (char)n5;
                            n5 += n4;
                            n3 = Q.B(this.W, n2);
                        }
                        else {
                            n5 += c6;
                        }
                    }
                    else if (Q.A(c5, Q.¥, 0)) {
                        n5 = -30000;
                        n4 = '\0';
                        if (sb.length() > sb2.length()) {
                            sb2 = sb;
                            v = c4;
                        }
                        sb = new StringBuffer();
                        if (c5 == '\u0011' && Q.A(this.W[Q.A(n2)], Q.g, 0)) {
                            ++d;
                        }
                        else if (Q.l[c5] == '\n' && Q.A(this.W[Q.A(n2) + 2], Q.g, 0)) {
                            d += Q.D(this.W, n2);
                        }
                    }
                    else if (Q.A(c5, Q.g, 0)) {
                        ++n5;
                        ++d;
                        n4 = '\0';
                        if (sb.length() > sb2.length()) {
                            sb2 = sb;
                            v = c4;
                        }
                        sb = new StringBuffer();
                    }
                    n2 = Q.B(this.W, n2);
                }
            }
            if (sb.length() + ((Q.l[this.W[n3]] == '\u0004') ? 1 : 0) > sb2.length()) {
                sb2 = sb;
                v = c4;
            }
            else {
                final StringBuffer sb3 = new StringBuffer();
            }
            if (sb2.length() > 0 && s == null) {
                string = sb2.toString();
                if (v < 0) {
                    v = -1;
                }
                d2.V = v;
            }
        }
        d2.f = ((c & '\u0001') != '\0');
        d2.c = this.c - 1;
        d2.d = d;
        if (string != null) {
            d2.S = string.toCharArray();
            d2.Z = 100;
        }
        if (s != null) {
            d2.h = s.toCharArray();
        }
        return d2;
    }
    
    public G A(final char[] array) throws H {
        return this.A(array, 0);
    }
    
    public G A(final String s) throws H {
        return this.A(s.toCharArray(), 0);
    }
    
    public G A(final String s, final int n) throws H {
        return this.A(s.toCharArray(), n);
    }
    
    static {
        (Z = new HashMap()).put("alnum", new Character('2'));
        L.Z.put("word", new Character('\u0012'));
        L.Z.put("alpha", new Character('&'));
        L.Z.put("blank", new Character('\''));
        L.Z.put("cntrl", new Character('('));
        L.Z.put("digit", new Character('\u0018'));
        L.Z.put("graph", new Character(')'));
        L.Z.put("lower", new Character('*'));
        L.Z.put("print", new Character('+'));
        L.Z.put("punct", new Character(','));
        L.Z.put("space", new Character('\u0016'));
        L.Z.put("upper", new Character('-'));
        L.Z.put("xdigit", new Character('.'));
        L.Z.put("ascii", new Character('3'));
    }
}
