import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class e extends d
{
    Font d;
    private int e;
    private Color f;
    private Color g;
    private int h;
    private Vector i;
    private String j;
    private int k;
    private boolean l;
    int m;
    private boolean n;
    private boolean o;
    private boolean p;
    private char[] q;
    public static int r;
    private static final String[] z;
    
    public e(final String s, final int n, final int n2) {
        super(n, n2);
        this.e = -1;
        this.g = Color.white;
        this.j = "";
        this.k = 0;
        this.l = false;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = new char[0];
        this.i = new Vector();
        this.j = a(s, 0);
        this.k = 0;
        this.h = -3;
        this.f = Color.black;
        this.g = Color.white;
        this.e = -1;
        this.m = 12;
        this.n = false;
    }
    
    public void a() {
        final int c = d.c;
        if (this.b == null) {
            return;
        }
        if (this.g == null) {
            this.g = Color.white;
        }
        this.b.setColor(this.g);
        this.b.fillRect(0, 0, this.getSize().width, this.getSize().height);
        if (this.o) {
            this.b.setColor(Color.red);
            this.b.drawRect(1, 1, this.getSize().width - 2, this.getSize().height - 2);
        }
        final FontMetrics b = this.b();
        this.b.setColor(this.f);
        if (this.n) {
            this.a(b);
            return;
        }
        final Vector vector = new Vector<gb>();
        String string = new String("");
        final StringTokenizer stringTokenizer = new StringTokenizer(this.j, " ", false);
        int n = -3;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals(e.z[1])) {
                final gb gb = new gb(string, n);
                vector.addElement(gb);
                n = gb.b();
                string = new String("");
                if (c == 0) {
                    continue;
                }
            }
            if ((string + nextToken + " ").length() * e.r >= this.getSize().width) {
                final gb gb2 = new gb(string, n);
                vector.addElement(gb2);
                n = gb2.b();
                string = new String("" + nextToken + " ");
                if (c == 0) {
                    continue;
                }
            }
            string = string + nextToken + " ";
            if (c != 0) {
                break;
            }
        }
        if (!string.equals("")) {
            vector.addElement(new gb(string, n));
        }
        final int size = vector.size();
        b.getHeight();
        int i = 0;
        while (i < size) {
            final fb[] a = vector.elementAt(i).a();
            int n2 = 1;
            if (this.e != -1) {
                if (this.e == -2) {
                    n2 = (this.getSize().width - a.length * e.r) / 2;
                }
                if (this.e == -3) {
                    n2 = this.getSize().width - a.length * e.r;
                }
            }
            int j = 0;
            while (j < a.length) {
                int n3 = b.getHeight() + i * 25;
                final Font font = this.b.getFont();
                final fb fb = a[j];
                if (fb.a == -1) {
                    this.b.setFont(new Font(font.getName(), this.l ? 1 : font.getStyle(), font.getSize() - 3));
                    n3 += b.getMaxDescent();
                }
                Label_0784: {
                    if (fb.a == -2) {
                        this.b.setFont(new Font(font.getName(), this.l ? 1 : font.getStyle(), font.getSize() - 3));
                        n3 -= b.getAscent() / 2 - this.a(fb.b, b);
                        if (c == 0) {
                            break Label_0784;
                        }
                    }
                    if (this.a(fb.b)) {
                        this.b.setFont(new Font(font.getName(), this.l ? 1 : font.getStyle(), font.getSize() + 4));
                        if (c == 0) {
                            break Label_0784;
                        }
                    }
                    this.b.setFont(new Font(font.getName(), this.l ? 1 : font.getStyle(), font.getSize()));
                }
                this.b.drawString("" + fb.b, n2, n3 + this.k);
                n2 += e.r;
                if (this.a(fb.b)) {
                    n2 += this.a();
                }
                this.b.setFont(font);
                ++j;
                if (c != 0) {
                    break;
                }
            }
            ++i;
            if (c != 0) {
                break;
            }
        }
        this.repaint();
    }
    
    private int a() {
        return 3;
    }
    
    private boolean a(final char c) {
        final int c2 = d.c;
        int i = 0;
        while (i < this.q.length) {
            if (this.q[i] == c) {
                return true;
            }
            ++i;
            if (c2 != 0) {
                break;
            }
        }
        return false;
    }
    
    private void a(FontMetrics fontMetrics) {
        final int c = d.c;
        if (this.o) {
            System.out.println(e.z[4] + this.j);
        }
        final Vector vector = new Vector<gb>();
        int b = -3;
        Label_0184: {
            if (this.p) {
                vector.addElement(new gb(this.j, b));
                if (c == 0) {
                    break Label_0184;
                }
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(this.j, e.z[1]);
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (this.o) {
                    System.out.println(e.z[3] + nextToken);
                }
                final gb gb = new gb(nextToken, b);
                vector.addElement(gb);
                b = gb.b();
                if (c != 0) {
                    break;
                }
            }
        }
        final int size = vector.size();
        if (this.o) {
            System.out.println(e.z[5] + size);
        }
        if (size == 3 || size != 0) {}
        fontMetrics.getHeight();
        int i = 0;
        while (i < size) {
            final int m = this.m;
            fontMetrics = this.b();
            final fb[] a = vector.elementAt(i).a();
            if (i == 1 && a.length * e.r > this.getSize().width) {
                if (this.m == 16) {
                    this.a(12, false);
                    fontMetrics = this.b();
                }
                if (this.m == 20) {
                    this.a(16, false);
                    if (a.length * e.r > this.getSize().width) {
                        this.a(12, false);
                    }
                }
                fontMetrics = this.b();
            }
            int n = 1;
            if (this.e != -1) {
                if (this.e == -2) {
                    n = (this.getSize().width - a.length * e.r) / 2;
                }
                if (this.e == -3) {
                    n = this.getSize().width - a.length * e.r;
                }
            }
            int j = 0;
            while (j < a.length) {
                int n2 = fontMetrics.getHeight() + i * 25;
                final Font font = this.b.getFont();
                final fb fb = a[j];
                Label_0635: {
                    if (fb.a == -1) {
                        this.b.setFont(new Font(font.getName(), font.getStyle(), font.getSize() - 3));
                        n2 += fontMetrics.getMaxDescent();
                        if (c == 0) {
                            break Label_0635;
                        }
                    }
                    if (fb.a == -2) {
                        this.b.setFont(new Font(font.getName(), font.getStyle(), font.getSize() - 3));
                        n2 -= fontMetrics.getAscent() / 2 - this.a(fb.b, fontMetrics);
                        if (c == 0) {
                            break Label_0635;
                        }
                    }
                    if (this.a(fb.b)) {
                        this.b.setFont(new Font(font.getName(), font.getStyle(), font.getSize() + 4));
                    }
                }
                this.b.drawString("" + fb.b, n, n2 + this.k);
                n += e.r;
                if (this.a(fb.b)) {
                    n += this.a();
                }
                this.b.setFont(font);
                ++j;
                if (c != 0) {
                    break;
                }
            }
            this.a(m, false);
            ++i;
            if (c != 0) {
                break;
            }
        }
        this.repaint();
    }
    
    private int a(final char c, final FontMetrics fontMetrics) {
        int n = 0;
        if (c == '-') {
            n = fontMetrics.getAscent() / 4;
        }
        if (ub.a(c)) {
            n = 2;
        }
        return n;
    }
    
    private FontMetrics b() {
        e.r = this.m / 2;
        this.d = new Font(e.z[2], this.l ? 1 : 0, this.m);
        this.b.setFont(this.d);
        return this.b.getFontMetrics();
    }
    
    public void a(final int m, final boolean b) {
        this.m = m;
        e.r = m / 2;
        if (b) {
            this.a();
        }
    }
    
    protected static String a(final String s, int n) {
        final int c = d.c;
        String s2 = "";
        int i = 0;
        while (i < s.length()) {
            final char char1 = s.charAt(i);
            Label_0083: {
                if (char1 == '\n') {
                    s2 += e.z[0];
                    ++n;
                    if (c == 0) {
                        break Label_0083;
                    }
                }
                s2 += char1;
            }
            ++i;
            if (c != 0) {
                break;
            }
        }
        return s2;
    }
    
    static {
        final String[] z2 = new String[6];
        final int n = 0;
        final char[] charArray = "d\u0000bP1\u0006\u001en_?d".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'D';
                    break;
                }
                case 1: {
                    c2 = 'L';
                    break;
                }
                case 2: {
                    c2 = '+';
                    break;
                }
                case 3: {
                    c2 = '\u001e';
                    break;
                }
                default: {
                    c2 = 't';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z2[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\b\u0005e[6\u0016\tjU".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'D';
                    break;
                }
                case 1: {
                    c4 = 'L';
                    break;
                }
                case 2: {
                    c4 = '+';
                    break;
                }
                case 3: {
                    c4 = '\u001e';
                    break;
                }
                default: {
                    c4 = 't';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z2[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = ")#Eq\u00074-H{\u0010".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'D';
                    break;
                }
                case 1: {
                    c6 = 'L';
                    break;
                }
                case 2: {
                    c6 = '+';
                    break;
                }
                case 3: {
                    c6 = '\u001e';
                    break;
                }
                default: {
                    c6 = 't';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z2[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "\n)SjT0#@{\u001ad%X>".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'D';
                    break;
                }
                case 1: {
                    c8 = 'L';
                    break;
                }
                case 2: {
                    c8 = '+';
                    break;
                }
                case 3: {
                    c8 = '\u001e';
                    break;
                }
                default: {
                    c8 = 't';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z2[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "))Xm\u0015#)\u000b#T".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'D';
                    break;
                }
                case 1: {
                    c10 = 'L';
                    break;
                }
                case 2: {
                    c10 = '+';
                    break;
                }
                case 3: {
                    c10 = '\u001e';
                    break;
                }
                default: {
                    c10 = 't';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z2[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "*9Fr\u001d*)X>Id".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'D';
                    break;
                }
                case 1: {
                    c12 = 'L';
                    break;
                }
                case 2: {
                    c12 = '+';
                    break;
                }
                case 3: {
                    c12 = '\u001e';
                    break;
                }
                default: {
                    c12 = 't';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z2[n16] = new String(charArray6).intern();
        z = z2;
        e.r = 6;
    }
}
