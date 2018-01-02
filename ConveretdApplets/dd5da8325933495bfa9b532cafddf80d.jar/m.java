import java.io.InputStream;
import java.util.StringTokenizer;
import java.io.DataInputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public final class m
{
    public static final int a = 16;
    public static final int b;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static final int f = 4;
    public static final int g = 5;
    public static final int h = 6;
    public static final int i = 7;
    private static d j;
    private static int[] k;
    private static int[] l;
    private static int[] m;
    private static int[] n;
    private static int[] o;
    private static e[] p;
    private static e[] q;
    private static int r;
    private static int s;
    private static int t;
    private static int u;
    private static int v;
    private static int w;
    private static int x;
    private static int y;
    private static int z;
    private static int A;
    private static int B;
    private static int C;
    private static int D;
    private static int E;
    private static int F;
    private static int G;
    private static int H;
    public static boolean I;
    public static boolean J;
    
    public static void a() {
        m.r = 0;
        m.s = 0;
    }
    
    public static void a(final d j) {
        final boolean dj = p.dJ;
        m.j = j;
        a();
        boolean b = false;
        int n = 0;
    Label_0210:
        while (true) {
            Label_0205: {
                if (!dj) {
                    break Label_0205;
                }
                try {
                    final InputStream openStream = new URL(m.j.S(), a("\\0`X;D'w")).openStream();
                    final DataInputStream dataInputStream;
                    final String line = (dataInputStream = new DataInputStream(openStream)).readLine();
                    dataInputStream.close();
                    openStream.close();
                    final StringTokenizer stringTokenizer;
                    final int countTokens = (stringTokenizer = new StringTokenizer(line, ",")).countTokens();
                    int n2 = 0;
                    final char[] array = new char[countTokens];
                    while (true) {
                        Label_0121: {
                            if (!dj) {
                                break Label_0121;
                            }
                            array[n2++] = (char)Integer.parseInt(stringTokenizer.nextToken());
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            continue;
                        }
                        break;
                    }
                    final String a = new c(m.j).a(new String(array), a("\u0006f2\u0007Xq\u0012F\u000b%\u0000nAqVr"));
                    m.j.q(true);
                    m.j.bd(a);
                    if (!(b = !m.j.bl())) {
                        break Label_0210;
                    }
                }
                catch (Exception ex) {
                    b = true;
                }
                ++n;
            }
            if (n < 3) {
                continue;
            }
            break;
        }
        if (b) {
            m.j.o(m.j.X(a("X0pGpB-")), a("o,f_s"));
        }
    }
    
    public static void a(final int f, final int g, final int d, final int e) {
        m.F = f;
        m.G = g;
        m.D = d;
        m.E = e;
    }
    
    public static void a(final int t, final int u) {
        m.t = t;
        m.u = u;
        m.y = m.j.ca[m.t];
    }
    
    public static void a(final int v, final int w, final int x) {
        m.v = v;
        m.w = w;
        m.x = x;
        m.z = m.j.ca[m.v];
        m.A = m.j.ca[m.w];
        m.B = m.j.ca[m.x];
        m.C = m.j.bZ[m.w];
    }
    
    public static void a(final int h) {
        m.H = h;
    }
    
    public static int a(final e e) {
        if (!m.J) {
            b();
        }
        m.k[m.r] = 0;
        m.p[m.r] = e;
        m.l[m.r] = m.A;
        return m.r++;
    }
    
    public static int a(final e e, final e e2) {
        if (!m.J) {
            b();
        }
        m.k[m.r] = 7;
        m.p[m.r] = e;
        m.q[m.r] = e2;
        m.l[m.r] = m.A;
        return m.r++;
    }
    
    public static int b(final e e) {
        if (m.J) {
            c();
        }
        m.k[m.r] = 3;
        m.p[m.r] = e;
        m.l[m.r] = m.y;
        return m.r++;
    }
    
    public static void b(final int n) {
        m.k[m.r] = 1;
        m.m[m.r] = n;
        final int n2 = m.j.ca[n];
        Label_0067: {
            if (m.J) {
                m.l[m.r] = (n2 + m.A - 1) / m.A * m.A;
                if (!p.dJ) {
                    break Label_0067;
                }
            }
            m.l[m.r] = n2;
        }
        ++m.r;
    }
    
    public static void b() {
        m.k[m.r] = 4;
        m.l[m.r] = m.z;
        ++m.r;
        m.J = true;
    }
    
    public static void c() {
        m.k[m.r] = 5;
        m.l[m.r] = m.B;
        ++m.r;
        m.J = false;
    }
    
    public static void c(final int n) {
        if (!m.J) {
            m.k[m.r] = 2;
            m.l[m.r] = n;
            ++m.r;
            return;
        }
        int n2 = 0;
        while (true) {
            Label_0044: {
                if (!p.dJ) {
                    break Label_0044;
                }
                m.k[m.r] = 6;
                m.l[m.r] = m.A;
                ++m.r;
                ++n2;
            }
            if (n2 >= n) {
                return;
            }
            continue;
        }
    }
    
    public static void d() {
        c(m.J ? 1 : m.E);
    }
    
    private static int b(final int n, final int n2, final int n3) {
        final int a = m.j.a("s", m.F, n2, n, false, 0);
        m.j.i(a, m.H + n3);
        return m.n[m.s++] = a;
    }
    
    public static void e() {
        final boolean dj = p.dJ;
        if (m.r > 0) {
            if (m.J) {
                c();
            }
            int n = 0;
            int n2 = 0;
            int n3;
            int n4;
            while (true) {
                while (true) {
                    Label_0040: {
                        if (!dj) {
                            break Label_0040;
                        }
                        n += m.l[n2];
                        ++n2;
                    }
                    if (n2 < m.r) {
                        continue;
                    }
                    break;
                }
                n3 = m.G - n / 2;
                n4 = 0;
                if (dj) {
                    continue;
                }
                break;
            }
            int n5;
            int n6;
            while (true) {
                while (true) {
                    Label_0078: {
                        if (!dj) {
                            break Label_0078;
                        }
                        m.o[n4] = -1;
                        ++n4;
                    }
                    if (n4 < m.o.length) {
                        continue;
                    }
                    break;
                }
                n5 = 0;
                n6 = 0;
                if (dj) {
                    if (dj) {
                        continue;
                    }
                }
                break;
            }
            int i = 0;
            while (true) {
                if (n6 >= m.r) {
                    i = 1;
                    if (!dj) {
                        break;
                    }
                }
                else {
                    final int n7 = n3 + m.l[n6] / 2;
                }
                final int n8 = i;
                Label_0487: {
                    switch (m.k[n6]) {
                        case 1: {
                            if (n5 != 0) {
                                int n9 = 0;
                                while (true) {
                                    Label_0215: {
                                        if (!dj) {
                                            break Label_0215;
                                        }
                                        b(m.w, n8 - m.l[n6] / 2 + m.A / 2 + n9 * m.A, 0);
                                        ++n9;
                                    }
                                    if (n9 < m.l[n6] / m.A) {
                                        continue;
                                    }
                                    break;
                                }
                            }
                            b(m.m[n6], n8, 1);
                            if (dj) {
                                break Label_0487;
                            }
                            break;
                        }
                        case 6: {
                            b(m.w, n8, 0);
                            if (dj) {
                                break Label_0487;
                            }
                            break;
                        }
                        case 4: {
                            n5 = 1;
                            b(m.v, n8, 0);
                            if (dj) {
                                break Label_0487;
                            }
                            break;
                        }
                        case 5: {
                            n5 = 0;
                            b(m.x, n8, 0);
                            if (dj) {
                                break Label_0487;
                            }
                            break;
                        }
                        case 0: {
                            b(m.w, n8, 0);
                            m.p[n6].a(m.F, n8);
                            m.o[m.s] = n6;
                            m.n[m.s++] = m.j.a("s", m.p[n6], m.H + 1);
                            if (dj) {
                                break Label_0487;
                            }
                            break;
                        }
                        case 7: {
                            b(m.w, n8, 0);
                            m.p[n6].b(m.F - m.C / 2 + m.D, n8);
                            m.n[m.s++] = m.j.a("s", m.p[n6], m.H + 1);
                            m.q[n6].c(m.F + m.C / 2 - m.D, n8);
                            m.n[m.s++] = m.j.a("s", m.q[n6], m.H + 1);
                            if (dj) {
                                break Label_0487;
                            }
                            break;
                        }
                        case 3: {
                            final int b = b(m.t, n8, 0);
                            m.j.c(b, true);
                            m.j.k(b, m.u);
                            m.n[m.s] = b;
                            m.o[m.s] = n6;
                            ++m.s;
                            m.p[n6].a(m.F, n8);
                            m.n[m.s++] = m.j.a("s", m.p[n6], m.H + 1);
                            break;
                        }
                    }
                }
                n3 += m.l[n6];
                ++n6;
            }
            m.I = (i != 0);
        }
    }
    
    public static void f() {
        final boolean dj = p.dJ;
        if (m.I) {
            int n = 0;
            boolean i;
            while (true) {
                while (true) {
                    Label_0031: {
                        if (!dj) {
                            break Label_0031;
                        }
                        m.j.i(m.n[n]);
                        ++n;
                    }
                    if (n < m.s) {
                        continue;
                    }
                    break;
                }
                i = false;
                if (dj) {
                    continue;
                }
                break;
            }
            m.I = i;
        }
    }
    
    public static int g() {
        if (m.I && m.j.bk) {
            int n = 0;
            while (true) {
                Label_0079: {
                    if (!p.dJ) {
                        break Label_0079;
                    }
                    if (m.o[n] != -1 && m.k[m.o[n]] == 3 && m.n[n] == m.j.dm) {
                        p.a(18);
                        m.j.o();
                        return m.o[n];
                    }
                    ++n;
                }
                if (n < m.s) {
                    continue;
                }
                break;
            }
        }
        return -1;
    }
    
    public static void a(final int n, final e e) {
        final boolean dj = p.dJ;
        int n2 = 0;
        while (true) {
            Label_0090: {
                if (!dj) {
                    break Label_0090;
                }
                final int n3;
                if ((n3 = m.o[n2]) == n) {
                    final int c = m.p[n3].c;
                    final int d = m.p[n3].d;
                    m.j.i(m.n[n2]);
                    e.a(c, d);
                    m.n[n2] = m.j.a("s", e, m.H + 1);
                    if (!dj) {
                        return;
                    }
                }
                ++n2;
            }
            if (n2 < m.s) {
                continue;
            }
            break;
        }
    }
    
    static {
        m.j = null;
        m.k = new int[16];
        m.l = new int[16];
        m.m = new int[16];
        m.n = new int[32];
        m.o = new int[32];
        m.p = new e[16];
        m.q = new e[16];
        m.I = false;
        m.J = false;
    }
    
    private static String a(final String s) {
        char[] charArray;
        for (int length = (charArray = s.toCharArray()).length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '0';
                    break;
                }
                case 1: {
                    c2 = '_';
                    break;
                }
                case 2: {
                    c2 = '\u0003';
                    break;
                }
                case 3: {
                    c2 = '3';
                    break;
                }
                default: {
                    c2 = '\u0015';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
