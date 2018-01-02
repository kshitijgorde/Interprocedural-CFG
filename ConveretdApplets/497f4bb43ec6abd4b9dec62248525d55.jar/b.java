import java.util.Enumeration;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

final class b
{
    static final String[] a;
    static final String[] b;
    static final int[] c;
    private int d;
    private int e;
    private char f;
    private char[] g;
    private int h;
    private char i;
    private String j;
    private int k;
    private Vector l;
    private String m;
    private int n;
    
    public b(final char[] array, final int n) {
        this.d = -1;
        this.e = -1;
        this.j = "";
        this.f = '\0';
        this.i = '\0';
        this.k = 0;
        this.l = null;
        this.m = "";
        this.n = -1;
        this.a(array, n);
    }
    
    public void a(final char[] g, final int h) {
        this.g = g;
        this.h = h;
    }
    
    public int a() {
        return this.d;
    }
    
    public int b() {
        return this.e;
    }
    
    public void a(final StringBuffer sb, final int n) {
        final int n2 = 8 - n / 8;
        sb.append((char)(97 + n % 8));
        sb.append((char)(48 + n2));
    }
    
    public String c() {
        if (this.j.length() == 0) {
            final StringBuffer sb = new StringBuffer("");
            final boolean o = this.o();
            if (this.f != 'P') {
                sb.append(this.f);
                this.a(sb, o);
            }
            if (this.k == 2 || this.k == 3) {
                if (this.f == 'P') {
                    sb.append((char)(97 + this.d % 8));
                }
                sb.append("x");
            }
            this.a(sb, this.e);
            this.j = sb.toString();
        }
        return this.h() ? this.j : (this.j + this.m);
    }
    
    public String d() {
        return this.h() ? this.m : "";
    }
    
    public char[] e() {
        return this.g;
    }
    
    public int f() {
        return this.h;
    }
    
    public boolean g() {
        return this.k == -1;
    }
    
    public boolean h() {
        return this.k == 1;
    }
    
    public boolean i() {
        return this.f == 'K' && this.k >= 4;
    }
    
    public boolean j() {
        return this.m.equals("--");
    }
    
    private boolean a(final int n, final int n2, final int n3) {
        for (int n4 = n; this.g[n4] == '-'; n4 += n3) {
            if (n4 == n2) {
                return true;
            }
        }
        return false;
    }
    
    public void a(final String s) {
        if (this.l != null) {
            if (this.l.size() > 1) {
                final Integer lastElement = this.l.lastElement();
                if (lastElement instanceof Integer && lastElement == -1) {
                    this.l.insertElementAt(lastElement, 0);
                    this.l.insertElementAt(s, 1);
                    this.l.setSize(this.l.size() - 1);
                    return;
                }
            }
        }
        else {
            this.l = new Vector();
        }
        this.l.addElement(s);
    }
    
    public void a(final int n) {
        if (n < -1 || n > 139) {
            return;
        }
        if (this.l == null) {
            this.l = new Vector();
        }
        this.l.addElement(new Integer(n));
        if (this.n >= 0) {
            return;
        }
        for (int i = 0; i < b.c.length; ++i) {
            if (n == b.c[i]) {
                this.n = i;
                this.m += b.b[i];
                return;
            }
        }
    }
    
    private void k() {
        if (this.f != 'K') {
            return;
        }
        if (this.d == 60) {
            if (this.e == 62) {
                this.j = "0-0";
                this.k = 4;
            }
            else if (this.e == 58) {
                this.j = "0-0-0";
                this.k = 5;
            }
        }
        else if (this.d == 4) {
            if (this.e == 6) {
                this.j = "0-0";
                this.k = 6;
            }
            else if (this.e == 2) {
                this.j = "0-0-0";
                this.k = 7;
            }
        }
    }
    
    public boolean l() {
        return this.l != null;
    }
    
    public String m() {
        final StringBuffer sb = new StringBuffer("");
        if (this.l == null) {
            return sb.toString();
        }
        final Integer element = this.l.elementAt(0);
        if (element instanceof Integer && element == -1) {
            sb.append("\u25ba ");
            sb.append(this.l.elementAt(1));
        }
        return sb.toString();
    }
    
    public String n() {
        final StringBuffer sb = new StringBuffer("");
        if (this.l == null) {
            return sb.toString();
        }
        final Enumeration<Integer> elements = this.l.elements();
        while (elements.hasMoreElements()) {
            final Integer nextElement = elements.nextElement();
            if (nextElement instanceof Integer) {
                final int intValue = nextElement;
                if (intValue == -1) {
                    sb.append("\u25c4 ");
                    sb.append((String)elements.nextElement());
                }
                else {
                    sb.append("\u25b2 ");
                    sb.append(b.a[intValue]);
                }
            }
            else {
                sb.append("\u25bc ");
                sb.append((String)nextElement);
            }
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
    
    public String a(String s, final boolean b) {
        final StringBuffer sb = new StringBuffer("");
        int i = 0;
        s += (this.h() ? this.d() : this.c());
        if (this.n >= 0 && s.endsWith(b.b[this.n])) {
            s = s.substring(0, s.length() - b.b[this.n].length());
        }
        if (this.l == null) {
            return b ? s : "";
        }
        final Object element = this.l.elementAt(i);
        if (element instanceof Integer && (int)element == -1) {
            ++i;
            sb.append('{');
            sb.append((String)this.l.elementAt(i++));
            sb.append("} ");
        }
        if (b) {
            sb.append(s);
            sb.append(' ');
        }
        while (i < this.l.size()) {
            final Object element2 = this.l.elementAt(i);
            if (element2 instanceof Integer) {
                sb.append('$');
                sb.append((int)element2);
                sb.append(' ');
            }
            else {
                sb.append('{');
                sb.append((String)element2);
                sb.append("} ");
            }
            ++i;
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
    
    public boolean o() {
        return (this.h & 0x10) == 0x10;
    }
    
    public int a(final char[] array) {
        int n = 0;
        if (this.i()) {
            if (this.k == 4) {
                array[63] = '-';
                array[61] = 'R';
            }
            else if (this.k == 5) {
                array[56] = '-';
                array[59] = 'R';
            }
            else if (this.k == 6) {
                array[7] = '-';
                array[5] = 'r';
            }
            else if (this.k == 7) {
                array[0] = '-';
                array[3] = 'r';
            }
        }
        if (this.i != '\0') {
            array[this.e] = (this.o() ? this.i : Character.toLowerCase(this.i));
        }
        else {
            array[this.e] = array[this.d];
        }
        if (this.k == 3) {
            array[this.e + (this.o() ? 8 : -8)] = '-';
        }
        array[this.d] = '-';
        if (this.f == 'P') {
            final int n2 = this.e - this.d;
            if (n2 == 16 || n2 == -16) {
                n = this.d + n2 / 2;
            }
        }
        int n3 = n << 5 | (this.h & 0x1F);
        if ((this.h & 0x8) == 0x8 && (this.d == 60 || this.d == 63 || this.e == 63)) {
            n3 ^= 0x8;
        }
        if ((this.h & 0x4) == 0x4 && (this.d == 60 || this.d == 56 || this.e == 56)) {
            n3 ^= 0x4;
        }
        if ((this.h & 0x2) == 0x2 && (this.d == 4 || this.d == 7 || this.e == 7)) {
            n3 ^= 0x2;
        }
        if ((this.h & 0x1) == 0x1 && (this.d == 4 || this.d == 0 || this.e == 0)) {
            n3 ^= 0x1;
        }
        return n3 ^ 0x10;
    }
    
    private boolean a(final boolean b, final char[] array, final int n) {
        final b b2 = new b(array, n);
        final int n2 = n >> 5;
        for (int i = 0; i < 64; ++i) {
            if (array[i] != '-') {
                if (b != Character.isLowerCase(array[i])) {
                    b2.d = i;
                    b2.f = Character.toUpperCase(array[i]);
                    for (int j = 0; j < 64; ++j) {
                        if (i != j) {
                            b2.k = 0;
                            b2.e = j;
                            b2.k();
                            if (b2.a(true, b, n, n2)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private boolean a(final int d, final boolean b) {
        final char c = b ? this.f : Character.toLowerCase(this.f);
        final int n = this.h >> 5;
        if (c != this.g[d] || d == this.e) {
            this.k = -1;
            return false;
        }
        this.d = d;
        this.k();
        if (!this.a(true, b, this.h, n)) {
            this.k = -1;
            return false;
        }
        if (!this.i() && this.k != 3) {
            this.k = ((this.g[this.e] != '-') ? 2 : 0);
        }
        final char[] array = this.g.clone();
        final int a = this.a(array);
        final boolean b2 = !b;
        if ((b2 || !this.a(array, true)) && (!b2 || !this.a(array, false))) {
            return true;
        }
        if (this.a(b2, array, a)) {
            this.m = "+" + this.m;
        }
        else {
            this.m = "#" + this.m;
        }
        return true;
    }
    
    private boolean a(final int n, final int n2, final int n3, final boolean b) {
        int d = -1;
        int k = 0;
        String m = null;
        for (int i = n; i < n2; i += n3) {
            if (this.a(i, b)) {
                if (d >= 0) {
                    this.k = -1;
                    return false;
                }
                d = this.d;
                k = this.k;
                m = this.m;
            }
        }
        if (d < 0) {
            return false;
        }
        this.d = d;
        this.k = k;
        this.m = m;
        return true;
    }
    
    private void a(final StringBuffer sb, final boolean b) {
        final int d = this.d;
        final int k = this.k;
        final String m = this.m;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        for (int i = 0; i < 64; ++i) {
            if (i != d && this.a(i, b)) {
                if (this.d / 8 == d / 8) {
                    b2 = true;
                }
                else if ((this.d - d) % 8 == 0) {
                    b3 = true;
                }
                else {
                    b4 = true;
                }
            }
        }
        this.d = d;
        this.k = k;
        this.m = m;
        if (b2 || (!b3 && b4)) {
            sb.append((char)(97 + (d & 0x7)));
        }
        if (b3) {
            sb.append((char)(56 - (d >> 3)));
        }
    }
    
    public void b(String s) {
        final boolean o = this.o();
        if (s.equals("--")) {
            this.j = "- - -";
            this.m = s;
            this.k = 1;
            return;
        }
        if (s.equals("*")) {
            this.j = "~~~";
            this.m = s;
            this.k = 1;
            return;
        }
        if (s.equals("1-0") || s.equals("0-1") || s.equals("1/2-1/2")) {
            this.j = s;
            this.m = s;
            this.k = 1;
            return;
        }
        for (int i = 0; i < b.b.length; ++i) {
            if (s.endsWith(b.b[i])) {
                s = s.substring(0, s.length() - b.b[i].length());
                this.a(b.c[i]);
            }
        }
        if (s.equals("O-O") || s.equals("0-0") || s.equals("o-o")) {
            this.f = 'K';
            if (o) {
                this.d = 60;
                this.e = 62;
            }
            else {
                this.d = 4;
                this.e = 6;
            }
        }
        else if (s.equals("O-O-O") || s.equals("0-0-0") || s.equals("o-o-o")) {
            this.f = 'K';
            if (o) {
                this.d = 60;
                this.e = 58;
            }
            else {
                this.d = 4;
                this.e = 2;
            }
        }
        if (this.f == 'K' && (this.d == 4 || this.d == 60)) {
            this.a(this.d, o);
            return;
        }
        int n = s.length() - 1;
        if (Character.isLetter(s.charAt(n))) {
            this.i = s.charAt(n);
            if (s.charAt(n - 1) == '=') {
                --n;
            }
            s = s.substring(0, n);
        }
        else {
            ++n;
        }
        if (n < 2 || n > 6) {
            this.j = s;
            this.k = -1;
            return;
        }
        final String substring = s.substring(n - 2);
        final char c = (char)(substring.charAt(0) - 'a');
        final char c2 = (char)(substring.charAt(1) - '0');
        if (c < '\0' || c > '\u0007' || c2 < '\u0001' || c2 > '\b') {
            this.j = s;
            this.k = -1;
            return;
        }
        int n2 = 0;
        int n3 = 0;
        if (Character.isLowerCase(s.charAt(0))) {
            this.f = 'P';
        }
        if (n == 6) {
            this.f = s.charAt(0);
            n3 = s.charAt(1);
            n2 = s.charAt(2);
        }
        else if (n == 5 && this.f == 'P') {
            n3 = s.charAt(0);
            n2 = s.charAt(1);
        }
        else if ((n == 4 && s.charAt(1) != 'x') || n == 5) {
            if (this.f == 'P') {
                n3 = s.charAt(0);
                n2 = s.charAt(1);
            }
            else {
                this.f = s.charAt(0);
                if (!Character.isDigit(s.charAt(1))) {
                    n3 = s.charAt(1);
                    if (n == 5 && s.charAt(2) != 'x') {
                        n2 = s.charAt(2);
                    }
                }
                else {
                    n2 = s.charAt(1);
                }
            }
        }
        else if (n == 3 || n == 4) {
            if (this.f == 'P') {
                n3 = s.charAt(0);
            }
            else {
                this.f = s.charAt(0);
            }
        }
        else {
            this.f = 'P';
        }
        if (this.f != 'P' && this.i != '\0') {
            this.j = s + this.i;
            this.k = -1;
            return;
        }
        this.e = ('\b' - c2) * '\b' + c;
        if (n2 != 0) {
            final int n4 = 8 - (n2 - 48);
            if (n4 < 0 || n4 > 7) {
                this.j = s;
                this.k = -1;
                return;
            }
            if (n3 != 0) {
                final int n5 = n3 - 97;
                if (n5 < 0 || n5 > 7) {
                    this.j = s;
                    this.k = -1;
                    return;
                }
                this.d = n4 * 8 + n5;
                if (this.f == 'P') {
                    this.f = Character.toUpperCase(this.g[this.d]);
                    if (this.f != 'P' && this.i != '\0') {
                        this.j = s + this.i;
                        this.k = -1;
                        return;
                    }
                }
                if (!this.a(this.d, o)) {
                    this.j = s;
                    return;
                }
            }
            else if (!this.a(n4 * 8, n4 * 8 + 8, 1, o)) {
                this.j = s;
                return;
            }
        }
        else if (n3 != 0) {
            if (!this.a(n3 - 97, 64, 8, o)) {
                this.j = s;
                return;
            }
        }
        else if (!this.a(0, 64, 1, o)) {
            this.j = s;
            return;
        }
        if (!this.g() && this.i()) {
            return;
        }
        if (this.f != 'P') {
            final StringBuffer sb = new StringBuffer("");
            sb.append(this.f);
            if (n3 != 0 || n2 != 0) {
                this.a(sb, o);
            }
            this.j = sb.toString();
        }
        else {
            this.j = "";
        }
        if (this.k == 2 || this.k == 3) {
            if (this.f == 'P') {
                this.j = String.valueOf((char)(97 + (this.d & 0x7)));
            }
            this.j += "x";
        }
        this.j += substring;
        if (this.i != '\0') {
            if (this.k != 2 || this.m.length() == 0) {
                this.j += "=";
            }
            this.j += this.i;
        }
    }
    
    private boolean a(final boolean b, final boolean b2, final int n) {
        final int n2 = this.e - this.d;
        int n3;
        int n4;
        if (b2) {
            n3 = -8;
            n4 = 6;
        }
        else {
            n3 = 8;
            n4 = 1;
        }
        if (n2 == n3) {
            return this.g[this.e] == '-';
        }
        if (n2 == n3 << 1) {
            return this.d >> 3 == n4 && this.a(this.d + n3, this.e, n3);
        }
        if (n2 != n3 - 1 && n2 != n3 + 1) {
            return false;
        }
        if (Math.abs((this.d & 0x7) - (this.e & 0x7)) > 1) {
            return false;
        }
        if (this.g[this.e] != '-') {
            return true;
        }
        if (n > 0 && this.e == n) {
            this.k = 3;
            return true;
        }
        return !b;
    }
    
    private boolean p() {
        if (Math.abs((this.d & 0x7) - (this.e & 0x7)) > 2) {
            return false;
        }
        final int abs = Math.abs(this.d - this.e);
        return abs == 6 || abs == 10 || abs == 15 || abs == 17;
    }
    
    private boolean q() {
        final int n = this.e - this.d;
        if (Math.abs((this.d >> 3) - (this.e >> 3)) != Math.abs((this.d & 0x7) - (this.e & 0x7))) {
            return false;
        }
        int n2;
        if (n % 9 == 0) {
            n2 = ((this.e > this.d) ? 9 : -9);
        }
        else {
            if (n % 7 != 0) {
                return false;
            }
            n2 = ((this.e > this.d) ? 7 : -7);
        }
        return this.d + n2 == this.e || this.a(this.d + n2, this.e - n2, n2);
    }
    
    private boolean r() {
        int n;
        if (this.d >> 3 == this.e >> 3) {
            n = ((this.e > this.d) ? 1 : -1);
        }
        else {
            if ((this.d - this.e & 0x7) != 0x0) {
                return false;
            }
            n = ((this.e > this.d) ? 8 : -8);
        }
        return this.d + n == this.e || this.a(this.d + n, this.e - n, n);
    }
    
    private boolean s() {
        return this.r() || this.q();
    }
    
    private boolean a(final boolean b, final int n) {
        if (b) {
            if (this.k == 4 && (n & 0x8) == 0x8 && this.a(61, 62, 1)) {
                return true;
            }
            if (this.k == 5 && (n & 0x4) == 0x4 && this.a(57, 59, 1)) {
                return true;
            }
        }
        else {
            if (this.k == 6 && (n & 0x2) == 0x2 && this.a(5, 6, 1)) {
                return true;
            }
            if (this.k == 7 && (n & 0x1) == 0x1 && this.a(1, 3, 1)) {
                return true;
            }
        }
        if (Math.abs((this.d & 0x7) - (this.e & 0x7)) > 1) {
            return false;
        }
        final int abs = Math.abs(this.d - this.e);
        return abs == 1 || abs == 7 || abs == 8 || abs == 9;
    }
    
    private boolean a(final int e, final char[] array, final boolean b) {
        final b b2 = new b(array, 0);
        b2.e = e;
        for (int i = 0; i < 64; ++i) {
            if (array[i] != '-') {
                if (b != Character.isLowerCase(array[i])) {
                    b2.d = i;
                    b2.k = 0;
                    b2.f = Character.toUpperCase(array[i]);
                    b2.k();
                    if (b2.a(false, b, 0, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean a(final char[] array, final boolean b) {
        final char c = b ? 'k' : 'K';
        for (int i = 0; i < 64; ++i) {
            if (array[i] == c) {
                return this.a(i, array, b);
            }
        }
        return false;
    }
    
    private boolean a(final boolean b, final boolean b2, final int n, final int n2) {
        final char c = this.g[this.d];
        if (b2) {
            if (Character.isLowerCase(c) || Character.isUpperCase(this.g[this.e])) {
                return false;
            }
            if (this.i()) {
                if (this.a(this.g, false)) {
                    return false;
                }
                if (this.k == 4 && this.a(61, this.g, false)) {
                    return false;
                }
                if (this.k == 5 && this.a(59, this.g, false)) {
                    return false;
                }
            }
        }
        else {
            if (Character.isUpperCase(c) || Character.isLowerCase(this.g[this.e])) {
                return false;
            }
            if (this.i()) {
                if (this.a(this.g, true)) {
                    return false;
                }
                if (this.k == 6 && this.a(5, this.g, true)) {
                    return false;
                }
                if (this.k == 7 && this.a(3, this.g, true)) {
                    return false;
                }
            }
        }
        switch (Character.toUpperCase(c)) {
            case 'P': {
                if (!this.a(b, b2, n2)) {
                    return false;
                }
                break;
            }
            case 'N': {
                if (!this.p()) {
                    return false;
                }
                break;
            }
            case 'B': {
                if (!this.q()) {
                    return false;
                }
                break;
            }
            case 'R': {
                if (!this.r()) {
                    return false;
                }
                break;
            }
            case 'Q': {
                if (!this.s()) {
                    return false;
                }
                break;
            }
            case 'K': {
                if (!this.a(b2, n)) {
                    return false;
                }
                break;
            }
            default: {
                return false;
            }
        }
        if (b) {
            final char[] array = this.g.clone();
            this.a(array);
            return !this.a(array, !b2);
        }
        return true;
    }
    
    static {
        a = new String[] { "null annotation", "A good move (!)", "A poor move (?)", "A very good move (!!)", "A very poor move (??)", "A speculative move (!?)", "A questionable move (?!)", "A forced move (all others lose quickly)", "A singular move (no reasonable alternatives)", "Worst move", "Drawish position", "Equal chances, quiet position (=)", "Equal chances, active position", "Unclear position", "White has a slight advantage (+=)", "Black has a slight advantage (=+)", "White has a moderate advantage (+/-)", "Black has a moderate advantage (-/+)", "White has a decisive advantage (+-)", "Black has a decisive advantage (-+)", "White has a crushing advantage (Black should resign)", "Black has a crushing advantage (White should resign)", "White is in zugzwang", "Black is in zugzwang", "White has a slight space advantage", "Black has a slight space advantage", "White has a moderate space advantage", "Black has a moderate space advantage", "White has a decisive space advantage", "Black has a decisive space advantage", "White has a slight time (development) advantage", "Black has a slight time (development) advantage", "White has a moderate time (development) advantage", "Black has a moderate time (development) advantage", "White has a decisive time (development) advantage", "Black has a decisive time (development) advantage", "White has the initiative", "Black has the initiative", "White has a lasting initiative", "Black has a lasting initiative", "White has the attack", "Black has the attack", "White has insufficient compensation for material deficit", "Black has insufficient compensation for material deficit", "White has sufficient compensation for material deficit", "Black has sufficient compensation for material deficit", "White has more than adequate compensation for material deficit", "Black has more than adequate compensation for material deficit", "White has a slight center control advantage", "Black has a slight center control advantage", "White has a moderate center control advantage", "Black has a moderate center control advantage", "White has a decisive center control advantage", "Black has a decisive center control advantage", "White has a slight kingside control advantage", "Black has a slight kingside control advantage", "White has a moderate kingside control advantage", "Black has a moderate kingside control advantage", "White has a decisive kingside control advantage", "Black has a decisive kingside control advantage", "White has a slight queenside control advantage", "Black has a slight queenside control advantage", "White has a moderate queenside control advantage", "Black has a moderate queenside control advantage", "White has a decisive queenside control advantage", "Black has a decisive queenside control advantage", "White has a vulnerable first rank", "Black has a vulnerable first rank", "White has a well protected first rank", "Black has a well protected first rank", "White has a poorly protected king", "Black has a poorly protected king", "White has a well protected king", "Black has a well protected king", "White has a poorly placed king", "Black has a poorly placed king", "White has a well placed king", "Black has a well placed king", "White has a very weak pawn structure", "Black has a very weak pawn structure", "White has a moderately weak pawn structure", "Black has a moderately weak pawn structure", "White has a moderately strong pawn structure", "Black has a moderately strong pawn structure", "White has a very strong pawn structure", "Black has a very strong pawn structure", "White has poor knight placement", "Black has poor knight placement", "White has good knight placement", "Black has good knight placement", "White has poor bishop placement", "Black has poor bishop placement", "White has good bishop placement", "Black has good bishop placement", "White has poor rook placement", "Black has poor rook placement", "White has good rook placement", "Black has good rook placement", "White has poor queen placement", "Black has poor queen placement", "White has good queen placement", "Black has good queen placement", "White has poor piece coordination", "Black has poor piece coordination", "White has good piece coordination", "Black has good piece coordination", "White has played the opening very poorly", "Black has played the opening very poorly", "White has played the opening poorly", "Black has played the opening poorly", "White has played the opening well", "Black has played the opening well", "White has played the opening very well", "Black has played the opening very well", "White has played the middlegame very poorly", "Black has played the middlegame very poorly", "White has played the middlegame poorly", "Black has played the middlegame poorly", "White has played the middlegame well", "Black has played the middlegame well", "White has played the middlegame very well", "Black has played the middlegame very well", "White has played the ending very poorly", "Black has played the ending very poorly", "White has played the ending poorly", "Black has played the ending poorly", "White has played the ending well", "Black has played the ending well", "White has played the ending very well", "Black has played the ending very well", "White has slight counterplay", "Black has slight counterplay", "White has moderate counterplay", "Black has moderate counterplay", "White has decisive counterplay", "Black has decisive counterplay", "White has moderate time control pressure", "Black has moderate time control pressure", "White has severe time control pressure", "Black has severe time control pressure" };
        b = new String[] { "+-", "-+", "+=", "=+", "=", "!!", "??", "!?", "?!", "!", "?", "+", "#" };
        c = new int[] { 18, 19, 14, 15, 11, 3, 4, 5, 6, 1, 2, -2, -2 };
    }
}
