// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.A;

public class E extends A
{
    public static final int C = 0;
    public static final int G = -1;
    private static final int J = 32;
    private static final int O = 65535;
    static final int P = -1;
    static final int M = -2;
    static final int F = -3;
    static final int E = -4;
    static final int N = -5;
    static final int L = -6;
    int Q;
    int[] K;
    int D;
    char[] I;
    transient String H;
    
    private static final boolean A(final char c) {
        return Character.isDigit(c) || c == '&';
    }
    
    private void A(final int n) {
        final int length = this.K.length;
        if (this.D == length) {
            final int[] k = new int[length + 32];
            System.arraycopy(this.K, 0, k, 0, length);
            this.K = k;
        }
        this.K[this.D++] = n;
    }
    
    private void B(final String s) {
        final char[] charArray = s.toCharArray();
        this.I = charArray;
        final char[] array = charArray;
        final int length = array.length;
        this.K = new int[32];
        this.D = 0;
        int n = 0;
        int n2 = -1;
        int a = 0;
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < length; ++i) {
            final char c = array[i];
            final int n5 = i + 1;
            if (a != 0) {
                final int digit = Character.digit(c, 10);
                if (digit > -1) {
                    if (n <= 65535) {
                        n = n * 10 + digit;
                    }
                    if (n5 == length) {
                        this.A(n);
                    }
                    continue;
                }
                else {
                    if (c == '&' && array[i - 1] == '$') {
                        this.A(0);
                        n = 0;
                        a = 0;
                        continue;
                    }
                    this.A(n);
                    n = 0;
                    a = 0;
                }
            }
            if ((c != '$' && c != '\\') || n3 != 0) {
                n3 = 0;
                if (n2 < 0) {
                    n2 = i;
                    this.A(-1);
                    this.A(n2);
                }
                if (n5 == length) {
                    this.A(n5 - n2);
                }
            }
            else {
                if (n2 >= 0) {
                    this.A(i - n2);
                    n2 = -1;
                }
                if (n5 != length) {
                    final char c2 = array[n5];
                    if (c == '$') {
                        a = (A(c2) ? 1 : 0);
                    }
                    else if (c == '\\') {
                        if (c2 == 'l') {
                            if (n4 == 0) {
                                this.A(-2);
                                ++i;
                            }
                        }
                        else if (c2 == 'u') {
                            if (n4 == 0) {
                                this.A(-3);
                                ++i;
                            }
                        }
                        else if (c2 == 'L') {
                            this.A(-4);
                            ++i;
                            n4 = 1;
                        }
                        else if (c2 == 'U') {
                            this.A(-5);
                            ++i;
                            n4 = 1;
                        }
                        else if (c2 == 'E') {
                            this.A(-6);
                            ++i;
                            n4 = 0;
                        }
                        else {
                            n3 = 1;
                        }
                    }
                }
            }
        }
    }
    
    String A(final M m) {
        final StringBuffer sb = new StringBuffer(10);
        this.A(sb, m);
        return sb.toString();
    }
    
    void A(final StringBuffer sb, final M m) {
        final int[] k = this.K;
        int n = 0;
        final char[] i = this.I;
        final char[] charArray = m.D(0).toCharArray();
        for (int d = this.D, j = 0; j < d; ++j) {
            final int n2 = k[j];
            int l;
            int n3;
            char[] array;
            if (n2 >= 0 && n2 < m.A()) {
                l = m.E(n2);
                if (l < 0) {
                    continue;
                }
                final int a = m.A(n2);
                if (a < 0) {
                    continue;
                }
                final int b = m.B();
                if (l >= b || a > b) {
                    continue;
                }
                if (l >= a) {
                    continue;
                }
                n3 = a - l;
                array = charArray;
            }
            else if (n2 == -1) {
                if (++j >= d) {
                    continue;
                }
                l = k[j];
                if (++j >= d) {
                    continue;
                }
                n3 = k[j];
                array = i;
            }
            else if (n2 == -2 || n2 == -3) {
                if (n != -4 && n != -5) {
                    n = n2;
                }
                continue;
            }
            else {
                if (n2 == -4 || n2 == -5) {
                    n = n2;
                    continue;
                }
                if (n2 == -6) {
                    n = 0;
                }
                continue;
            }
            if (n == -2) {
                sb.append(Character.toLowerCase(array[l++]));
                sb.append(array, l, --n3);
                n = 0;
            }
            else if (n == -3) {
                sb.append(Character.toUpperCase(array[l++]));
                sb.append(array, l, --n3);
                n = 0;
            }
            else if (n == -4) {
                while (l < l + n3) {
                    sb.append(Character.toLowerCase(array[l++]));
                }
            }
            else if (n == -5) {
                while (l < l + n3) {
                    sb.append(Character.toUpperCase(array[l++]));
                }
            }
            else {
                sb.append(array, l, n3);
            }
        }
    }
    
    public E() {
        this("", 0);
    }
    
    public E(final String s) {
        this(s, 0);
    }
    
    public E(final String s, final int n) {
        this.A(s, n);
    }
    
    public void A(final String s) {
        this.A(s, 0);
    }
    
    public void A(final String s, final int q) {
        super.A(s);
        this.Q = q;
        if (q != -1 && (s.indexOf(36) != -1 || s.indexOf(92) != -1)) {
            this.B(s);
        }
        else {
            this.K = null;
        }
        this.H = null;
    }
    
    public void A(final StringBuffer sb, final M m, final int n, final B b, final N n2, final G g) {
        if (this.K == null) {
            super.A(sb, m, n, b, n2, g);
            return;
        }
        if (this.Q < 1 || n < this.Q) {
            this.A(sb, m);
        }
        else {
            if (n == this.Q) {
                this.H = this.A(m);
            }
            sb.append(this.H);
        }
    }
}
