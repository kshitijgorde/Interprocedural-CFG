import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

public class e
{
    public int a;
    public int b;
    public int c;
    public int d;
    public int[] e;
    public int[] f;
    public int[] g;
    public boolean[] h;
    public boolean i;
    private d j;
    private String k;
    
    public e() {
    }
    
    public e(final d d, final String s, final String s2, final boolean b, final int n) {
        this.a(d, s, s2, b, n);
    }
    
    public e(final d d, final String s, final String s2, final boolean b) {
        this.a(d, s, s2, b, 128);
    }
    
    private void a(final d j, final String k, final String s, final boolean b, final int b2) {
        this.j = j;
        this.k = k;
        this.b = b2;
        this.e = new int[b2];
        this.f = new int[b2];
        this.g = new int[b2];
        this.h = new boolean[b2];
        this.b(s, b);
    }
    
    public void a() {
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
    }
    
    public int b() {
        final boolean dj = p.dJ;
        int n = 0;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0032: {
                    if (!dj) {
                        break Label_0032;
                    }
                    final int n3 = n + this.j.bZ[this.e[n2]];
                    final int n4;
                    n = n4;
                    ++n2;
                }
                if (n2 < this.a) {
                    continue;
                }
                break;
            }
            final int n4 = n;
            if (!dj) {
                return n4;
            }
            continue;
        }
    }
    
    public void c() {
        this.a(this.c, this.d);
    }
    
    public void a(final int c, final int d) {
        final boolean dj = p.dJ;
        this.c = c;
        this.d = d;
        final int b = this.b();
        final int n = d - this.j.ca[this.e[0]] / 2;
        int n2 = c - b / 2;
        int n3 = 0;
        while (true) {
            while (true) {
                Label_0096: {
                    if (!dj) {
                        break Label_0096;
                    }
                    this.f[n3] = n2;
                    this.g[n3] = n;
                    n2 += this.j.bZ[this.e[n3]];
                    ++n3;
                }
                if (n3 < this.a) {
                    continue;
                }
                break;
            }
            this.j.e();
            if (!dj) {
                return;
            }
            continue;
        }
    }
    
    public void b(final int n, final int n2) {
        this.a(n + this.b() / 2, n2);
    }
    
    public void c(final int n, final int n2) {
        this.a(n - (this.b() + 1) / 2, n2);
    }
    
    public void a(final String s, final boolean b) {
        final boolean dj = p.dJ;
        final String s2 = b ? s.toUpperCase() : s;
        final StringBuffer sb = new StringBuffer(256);
        final StringBuffer sb2;
        final int length = (sb2 = new StringBuffer(s2)).length();
        final char c = ',';
        int n = 0;
        while (true) {
            while (true) {
                Label_0091: {
                    if (!dj) {
                        break Label_0091;
                    }
                    sb.append(sb2.charAt(n));
                    if (n < length - 1) {
                        sb.append(c);
                    }
                    ++n;
                }
                if (n < length) {
                    continue;
                }
                break;
            }
            this.b(sb.toString(), false);
            if (!dj) {
                return;
            }
            continue;
        }
    }
    
    public void a(final String s) {
        this.a(s, true);
    }
    
    public void a(final long n) {
        final StringBuffer sb;
        (sb = new StringBuffer(16)).append(n);
        this.a(sb.toString(), true);
    }
    
    public void a(final long n, final int[] array) {
        final boolean dj = p.dJ;
        long abs;
        long n2 = abs = Math.abs(n);
        this.i = false;
        this.a = 1;
        int n3;
        while (true) {
            while (true) {
                Label_0047: {
                    if (!dj) {
                        break Label_0047;
                    }
                    ++this.a;
                    abs /= 10L;
                }
                if (abs >= 10L) {
                    continue;
                }
                break;
            }
            n3 = this.a - 1;
            if (dj) {
                continue;
            }
            break;
        }
        while (true) {
            while (true) {
                Label_0109: {
                    if (!dj) {
                        break Label_0109;
                    }
                    this.e[n3] = array[(int)(n2 % 10L)];
                    this.h[n3] = false;
                    n2 /= 10L;
                    --n3;
                }
                if (n3 >= 0) {
                    continue;
                }
                break;
            }
            this.j.e();
            if (!dj) {
                return;
            }
            continue;
        }
    }
    
    private void b(final String s, final boolean i) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        this.a = Math.min(i ? (stringTokenizer.countTokens() / 3) : stringTokenizer.countTokens(), this.b);
        this.i = i;
        int n = 0;
        while (true) {
            Label_0166: {
                if (!p.dJ) {
                    break Label_0166;
                }
                final String nextToken = stringTokenizer.nextToken();
                this.e[n] = this.j.d(this.k + (nextToken.equals(b("2\u0012\u0018\t")) ? "" : nextToken));
                this.h[n] = nextToken.equals(" ");
                if (i) {
                    this.f[n] = Integer.parseInt(stringTokenizer.nextToken());
                    this.g[n] = Integer.parseInt(stringTokenizer.nextToken());
                }
                ++n;
            }
            if (n >= this.a) {
                return;
            }
            continue;
        }
    }
    
    private static String b(final String s) {
        char[] charArray;
        for (int length = (charArray = s.toCharArray()).length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '\\';
                    break;
                }
                case 1: {
                    c2 = 'g';
                    break;
                }
                case 2: {
                    c2 = 't';
                    break;
                }
                case 3: {
                    c2 = 'e';
                    break;
                }
                default: {
                    c2 = 'r';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
