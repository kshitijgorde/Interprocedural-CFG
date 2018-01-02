import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class w extends Panel implements ActionListener
{
    private final esChat a;
    public y b;
    Vector c;
    private static String[] z;
    
    w(final esChat a) {
        this.a = a;
        this.c = new Vector(50, 50);
        this.setLayout(new BorderLayout());
        a.getClass();
        this.b = new y(a, null, true);
        this.b.b.a(false);
        this.add(this.b, w.z[1]);
        this.b.b.addMouseListener(new n(this));
    }
    
    public String a(final ib ib) {
        final int m = fb.m;
        boolean b2;
        final boolean b = b2 = ib.b;
        if (m == 0) {
            if (b) {
                return w.z[0] + ib.e;
            }
            final boolean c;
            b2 = (c = ib.c);
        }
        if (m == 0) {
            if (b) {
                return "%" + ib.e;
            }
            final ib ib2 = ib;
            if (m != 0) {
                return ib2.e;
            }
            b2 = ib.d;
        }
        if (b2) {
            return "+" + ib.e;
        }
        final ib ib2 = ib;
        return ib2.e;
    }
    
    static esChat a(final w w) {
        return w.a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
    }
    
    public void a(String s, final boolean b) {
        final int m = fb.m;
        try {
            final int f = this.b.b.f(s) ? 1 : 0;
            String s3 = null;
            final String s2;
            Label_0037: {
                if (m == 0) {
                    if (f != 0) {
                        return;
                    }
                    s2 = (s3 = s);
                    if (m != 0) {
                        break Label_0037;
                    }
                    s2.length();
                }
                if (f <= 0) {
                    return;
                }
                final String s4;
                s3 = (s4 = s);
            }
            if (m == 0) {
                if (s2 == null) {
                    return;
                }
                s3 = s;
            }
            if (s3 != "") {
                boolean b2 = false;
                boolean b3 = false;
                boolean b4 = false;
                Label_0096: {
                    Label_0085: {
                        if (m == 0) {
                            if (!b) {
                                break Label_0085;
                            }
                            this.b.b.a(s);
                        }
                        if (m == 0) {
                            break Label_0096;
                        }
                    }
                    this.b.b.b(s);
                }
                boolean b7;
                boolean equals;
                boolean b6;
                final boolean b5 = b6 = (equals = (b7 = s.substring(0, 1).equals("@")));
                if (m == 0) {
                    if (b5) {
                        b2 = true;
                        s = s.substring(1);
                        this.a.yb = true;
                    }
                    final boolean b8;
                    b6 = (b8 = (equals = (b7 = s.substring(0, 1).equals("+"))));
                }
                if (m == 0) {
                    if (b5) {
                        b3 = true;
                        s = s.substring(1);
                    }
                    equals = (b6 = (b7 = s.substring(0, 1).equals("%")));
                }
                if (m == 0) {
                    if (b6) {
                        b4 = true;
                        s = s.substring(1);
                    }
                    b7 = (equals = s.substring(0, 1).equals("~"));
                }
                Label_0237: {
                    final String substring;
                    Label_0236: {
                        if (m == 0) {
                            if (equals) {
                                s = s.substring(1);
                            }
                            substring = s.substring(0, 1);
                            if (m != 0) {
                                break Label_0236;
                            }
                            b7 = substring.equals("&");
                        }
                        if (!b7) {
                            break Label_0237;
                        }
                        s.substring(1);
                    }
                    s = substring;
                }
                final ib ib = new ib(this, s, b2, b3, b4);
                final int contains = this.c.contains(ib) ? 1 : 0;
                w w = null;
                Label_0296: {
                    if (m == 0) {
                        if (contains == 0) {
                            this.c.addElement(ib);
                        }
                        w = this;
                        if (m != 0) {
                            break Label_0296;
                        }
                        this.c.size();
                    }
                    if (contains >= 4) {
                        return;
                    }
                    w = this;
                }
                w.b();
            }
        }
        catch (Exception ex) {}
    }
    
    public boolean a(final String s) {
        final int m = fb.m;
        int n = 0;
        while (true) {
            while (true) {
                Label_0098: {
                    if (m == 0) {
                        break Label_0098;
                    }
                    Object o;
                    final String s2 = (String)(o = this.c.elementAt(n).e);
                    Label_0046: {
                        if (m != 0) {
                            break Label_0046;
                        }
                        s2.equals(s);
                        final boolean b;
                        if (!b) {
                            ++n;
                            break Label_0098;
                        }
                        o = this.c.elementAt(n);
                    }
                    this.b.b.e(this.a((ib)o));
                    this.c.removeElementAt(n);
                    int size;
                    final int n2 = size = this.c.size();
                    if (m == 0) {
                        if (n2 < 4) {
                            this.b();
                        }
                        size = (true ? 1 : 0);
                    }
                    return size != 0;
                }
                if (n < this.c.size()) {
                    continue;
                }
                break;
            }
            final boolean b = false;
            if (m == 0) {
                return b;
            }
            continue;
        }
    }
    
    public String b(final String s) {
        final int m = fb.m;
        int n = 0;
        while (true) {
            while (true) {
                Label_0048: {
                    if (m == 0) {
                        break Label_0048;
                    }
                    final Object element = this.c.elementAt(n);
                    final ib ib = (ib)element;
                    final String e = ib.e;
                    if (m == 0) {
                        if (!e.equals(s)) {
                            ++n;
                            break Label_0048;
                        }
                        final String f = ib.f;
                    }
                    return e;
                }
                if (n < this.c.size()) {
                    continue;
                }
                break;
            }
            Object element;
            final String s2 = (String)(element = "");
            if (m == 0) {
                return s2;
            }
            continue;
        }
    }
    
    public Dimension getPreferredSize() {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final int n = fontMetrics.charWidth('a') * 12;
        if (n > 100) {
            return new Dimension(n, fontMetrics.getHeight());
        }
        return new Dimension(100, fontMetrics.getHeight());
    }
    
    public String a() {
        return this.b.b.e();
    }
    
    public void b() {
        final int m = fb.m;
        this.b.b.b();
        int n = 0;
        while (true) {
            while (true) {
                Label_0050: {
                    if (m == 0) {
                        break Label_0050;
                    }
                    final Object o = this.c.elementAt(n);
                    this.b.b.b(this.a((ib)o));
                    ++n;
                }
                if (n < this.c.size()) {
                    continue;
                }
                break;
            }
            Object o;
            final d d = (d)(o = this.b.b);
            if (m == 0) {
                d.a();
                return;
            }
            continue;
        }
    }
    
    public boolean a(final String s, final String e) {
        final int m = fb.m;
        int n = 0;
        while (true) {
            Label_0105: {
                if (m == 0) {
                    break Label_0105;
                }
                final ib ib = this.c.elementAt(n);
                int equals;
                final int n2 = equals = (ib.e.equals(s) ? 1 : 0);
                if (m == 0) {
                    if (n2 == 0) {
                        ++n;
                        break Label_0105;
                    }
                    this.b.b.e(this.a(ib));
                    ib.e = e;
                    this.b.b.a(this.a(ib));
                    final int size;
                    equals = (size = this.c.size());
                }
                if (m == 0) {
                    if (n2 < 4) {
                        this.b();
                    }
                    equals = 1;
                }
                return equals != 0;
            }
            if (n >= this.c.size()) {
                return false;
            }
            continue;
        }
    }
    
    public void b(final String s, final String f) {
        final int m = fb.m;
        int n = 0;
        while (true) {
            Label_0052: {
                if (m == 0) {
                    break Label_0052;
                }
                final ib ib = this.c.elementAt(n);
                if (m != 0 || ib.e.equals(s)) {
                    ib.f = f;
                    return;
                }
                ++n;
            }
            if (n >= this.c.size()) {
                return;
            }
            continue;
        }
    }
    
    public void b(final String s, final boolean c) {
        final int m = fb.m;
        int n = 0;
        while (true) {
            Label_0104: {
                if (m == 0) {
                    break Label_0104;
                }
                final ib ib = this.c.elementAt(n);
                final int equals = ib.e.equals(s) ? 1 : 0;
                w w = null;
                Label_0097: {
                    if (m == 0) {
                        if (equals == 0) {
                            ++n;
                            break Label_0104;
                        }
                        this.b.b.e(this.a(ib));
                        ib.c = c;
                        this.b.b.a(this.a(ib));
                        w = this;
                        if (m != 0) {
                            break Label_0097;
                        }
                        this.c.size();
                    }
                    if (equals >= 4) {
                        return;
                    }
                    w = this;
                }
                w.b();
                return;
            }
            if (n >= this.c.size()) {
                return;
            }
            continue;
        }
    }
    
    public void c(final String s, final boolean b) {
        final int m = fb.m;
        int equals;
        final int n = equals = (s.equals(this.a.n) ? 1 : 0);
        if (m == 0) {
            if (n != 0) {
                this.a.yb = true;
            }
            equals = 0;
        }
        int n2 = equals;
        while (true) {
            Label_0131: {
                if (m == 0) {
                    break Label_0131;
                }
                final ib ib = this.c.elementAt(n2);
                final int equals2 = ib.e.equals(s) ? 1 : 0;
                w w = null;
                Label_0124: {
                    if (m == 0) {
                        if (equals2 == 0) {
                            ++n2;
                            break Label_0131;
                        }
                        this.b.b.e(this.a(ib));
                        ib.b = b;
                        this.b.b.a(this.a(ib));
                        w = this;
                        if (m != 0) {
                            break Label_0124;
                        }
                        this.c.size();
                    }
                    if (equals2 >= 4) {
                        return;
                    }
                    w = this;
                }
                w.b();
                return;
            }
            if (n2 >= this.c.size()) {
                return;
            }
            continue;
        }
    }
    
    public void d(final String s, final boolean d) {
        final int m = fb.m;
        int n = 0;
        while (true) {
            Label_0104: {
                if (m == 0) {
                    break Label_0104;
                }
                final ib ib = this.c.elementAt(n);
                final int equals = ib.e.equals(s) ? 1 : 0;
                w w = null;
                Label_0097: {
                    if (m == 0) {
                        if (equals == 0) {
                            ++n;
                            break Label_0104;
                        }
                        this.b.b.e(this.a(ib));
                        ib.d = d;
                        this.b.b.a(this.a(ib));
                        w = this;
                        if (m != 0) {
                            break Label_0097;
                        }
                        this.c.size();
                    }
                    if (equals >= 4) {
                        return;
                    }
                    w = this;
                }
                w.b();
                return;
            }
            if (n >= this.c.size()) {
                return;
            }
            continue;
        }
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "\f%u".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u000f';
                    break;
                }
                case 1: {
                    c2 = '\u0011';
                    break;
                }
                case 2: {
                    c2 = '5';
                    break;
                }
                case 3: {
                    c2 = '\u0015';
                    break;
                }
                default: {
                    c2 = '^';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "Lt[a;}".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u000f';
                    break;
                }
                case 1: {
                    c4 = '\u0011';
                    break;
                }
                case 2: {
                    c4 = '5';
                    break;
                }
                case 3: {
                    c4 = '\u0015';
                    break;
                }
                default: {
                    c4 = '^';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        w.z = z;
    }
}
