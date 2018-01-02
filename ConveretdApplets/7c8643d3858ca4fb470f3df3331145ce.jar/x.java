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

class x extends Panel implements ActionListener
{
    private final esChat a;
    public z b;
    Vector c;
    private static String[] z;
    
    x(final esChat a) {
        this.a = a;
        this.c = new Vector(50, 50);
        this.setLayout(new BorderLayout());
        a.getClass();
        this.b = new z(a, null, true);
        this.b.b.a(false);
        this.add(this.b, x.z[0]);
        this.b.b.addMouseListener(new o(this));
    }
    
    public String a(final jb jb) {
        final boolean r = d.r;
        boolean b2;
        final boolean b = b2 = jb.b;
        if (!r) {
            if (b) {
                return x.z[1] + jb.e;
            }
            final boolean c;
            b2 = (c = jb.c);
        }
        if (!r) {
            if (b) {
                return "%" + jb.e;
            }
            final jb jb2 = jb;
            if (r) {
                return jb2.e;
            }
            b2 = jb.d;
        }
        if (b2) {
            return "+" + jb.e;
        }
        final jb jb2 = jb;
        return jb2.e;
    }
    
    static esChat a(final x x) {
        return x.a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
    }
    
    public void a(String s, final boolean b) {
        final boolean r = d.r;
        try {
            if (s != null) {
                final int f = this.b.b.f(s) ? 1 : 0;
                final String s2;
                Label_0041: {
                    if (!r) {
                        if (f != 0) {
                            return;
                        }
                        s2 = s;
                        if (r) {
                            break Label_0041;
                        }
                        s2.length();
                    }
                    if (f <= 0) {
                        return;
                    }
                }
                if (s2 != "") {
                    boolean b2 = false;
                    boolean b3 = false;
                    boolean b4 = false;
                    Label_0091: {
                        Label_0080: {
                            if (!r) {
                                if (!b) {
                                    break Label_0080;
                                }
                                this.b.b.a(s);
                            }
                            if (!r) {
                                break Label_0091;
                            }
                        }
                        this.b.b.b(s);
                    }
                    boolean b7;
                    boolean equals;
                    boolean b6;
                    final boolean b5 = b6 = (equals = (b7 = s.substring(0, 1).equals("@")));
                    if (!r) {
                        if (b5) {
                            b2 = true;
                            s = s.substring(1);
                            this.a.Eb = true;
                        }
                        final boolean b8;
                        b6 = (b8 = (equals = (b7 = s.substring(0, 1).equals("+"))));
                    }
                    if (!r) {
                        if (b5) {
                            b3 = true;
                            s = s.substring(1);
                        }
                        equals = (b6 = (b7 = s.substring(0, 1).equals("%")));
                    }
                    if (!r) {
                        if (b6) {
                            b4 = true;
                            s = s.substring(1);
                        }
                        b7 = (equals = s.substring(0, 1).equals("~"));
                    }
                    Label_0232: {
                        final String substring;
                        Label_0231: {
                            if (!r) {
                                if (equals) {
                                    s = s.substring(1);
                                }
                                substring = s.substring(0, 1);
                                if (r) {
                                    break Label_0231;
                                }
                                b7 = substring.equals("&");
                            }
                            if (!b7) {
                                break Label_0232;
                            }
                            s.substring(1);
                        }
                        s = substring;
                    }
                    final jb jb = new jb(this, s, b2, b3, b4);
                    final int contains = this.c.contains(jb) ? 1 : 0;
                    x x = null;
                    Label_0291: {
                        if (!r) {
                            if (contains == 0) {
                                this.c.addElement(jb);
                            }
                            x = this;
                            if (r) {
                                break Label_0291;
                            }
                            this.c.size();
                        }
                        if (contains >= 4) {
                            return;
                        }
                        x = this;
                    }
                    x.b();
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public boolean a(final String s) {
        final boolean r = d.r;
        int n = 0;
        while (true) {
            while (true) {
                Label_0098: {
                    if (!r) {
                        break Label_0098;
                    }
                    Object o;
                    final String s2 = (String)(o = this.c.elementAt(n).e);
                    Label_0046: {
                        if (r) {
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
                    this.b.b.e(this.a((jb)o));
                    this.c.removeElementAt(n);
                    int size;
                    final int n2 = size = this.c.size();
                    if (!r) {
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
            if (!r) {
                return b;
            }
            continue;
        }
    }
    
    public String b(final String s) {
        final boolean r = d.r;
        int n = 0;
        while (true) {
            while (true) {
                Label_0048: {
                    if (!r) {
                        break Label_0048;
                    }
                    final Object element = this.c.elementAt(n);
                    final jb jb = (jb)element;
                    final String e = jb.e;
                    if (!r) {
                        if (!e.equals(s)) {
                            ++n;
                            break Label_0048;
                        }
                        final String f = jb.f;
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
            if (!r) {
                return s2;
            }
            continue;
        }
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(130, 30);
    }
    
    public String a() {
        return this.b.b.e();
    }
    
    public boolean c(final String s) {
        final boolean r = d.r;
        int n = 0;
        while (true) {
            Label_0057: {
                if (!r) {
                    break Label_0057;
                }
                final jb jb = this.c.elementAt(n);
                boolean equals;
                final boolean b = equals = jb.e.equals(s);
                Label_0054: {
                    if (!r) {
                        if (!b) {
                            break Label_0054;
                        }
                        final boolean b2;
                        equals = (b2 = jb.b);
                    }
                    if (!r) {
                        if (!b) {
                            break Label_0054;
                        }
                        equals = true;
                    }
                    return equals;
                }
                ++n;
            }
            if (n >= this.c.size()) {
                return false;
            }
            continue;
        }
    }
    
    public void b() {
        final boolean r = d.r;
        this.b.b.b();
        int n = 0;
        while (true) {
            while (true) {
                Label_0050: {
                    if (!r) {
                        break Label_0050;
                    }
                    final Object o = this.c.elementAt(n);
                    this.b.b.b(this.a((jb)o));
                    ++n;
                }
                if (n < this.c.size()) {
                    continue;
                }
                break;
            }
            Object o;
            final e e = (e)(o = this.b.b);
            if (!r) {
                e.a();
                return;
            }
            continue;
        }
    }
    
    public boolean a(final String s, final String e) {
        final boolean r = d.r;
        int n = 0;
        while (true) {
            Label_0105: {
                if (!r) {
                    break Label_0105;
                }
                final jb jb = this.c.elementAt(n);
                int equals;
                final int n2 = equals = (jb.e.equals(s) ? 1 : 0);
                if (!r) {
                    if (n2 == 0) {
                        ++n;
                        break Label_0105;
                    }
                    this.b.b.e(this.a(jb));
                    jb.e = e;
                    this.b.b.a(this.a(jb));
                    final int size;
                    equals = (size = this.c.size());
                }
                if (!r) {
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
        final boolean r = d.r;
        int n = 0;
        while (true) {
            Label_0052: {
                if (!r) {
                    break Label_0052;
                }
                final jb jb = this.c.elementAt(n);
                if (r || jb.e.equals(s)) {
                    jb.f = f;
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
        final boolean r = d.r;
        int n = 0;
        while (true) {
            Label_0104: {
                if (!r) {
                    break Label_0104;
                }
                final jb jb = this.c.elementAt(n);
                final int equals = jb.e.equals(s) ? 1 : 0;
                x x = null;
                Label_0097: {
                    if (!r) {
                        if (equals == 0) {
                            ++n;
                            break Label_0104;
                        }
                        this.b.b.e(this.a(jb));
                        jb.c = c;
                        this.b.b.a(this.a(jb));
                        x = this;
                        if (r) {
                            break Label_0097;
                        }
                        this.c.size();
                    }
                    if (equals >= 4) {
                        return;
                    }
                    x = this;
                }
                x.b();
                return;
            }
            if (n >= this.c.size()) {
                return;
            }
            continue;
        }
    }
    
    public void c(final String s, final boolean b) {
        final boolean r = d.r;
        int equals;
        final int n = equals = (s.equals(this.a.n) ? 1 : 0);
        if (!r) {
            if (n != 0) {
                this.a.Eb = true;
            }
            equals = 0;
        }
        int n2 = equals;
        while (true) {
            Label_0131: {
                if (!r) {
                    break Label_0131;
                }
                final jb jb = this.c.elementAt(n2);
                final int equals2 = jb.e.equals(s) ? 1 : 0;
                x x = null;
                Label_0124: {
                    if (!r) {
                        if (equals2 == 0) {
                            ++n2;
                            break Label_0131;
                        }
                        this.b.b.e(this.a(jb));
                        jb.b = b;
                        this.b.b.a(this.a(jb));
                        x = this;
                        if (r) {
                            break Label_0124;
                        }
                        this.c.size();
                    }
                    if (equals2 >= 4) {
                        return;
                    }
                    x = this;
                }
                x.b();
                return;
            }
            if (n2 >= this.c.size()) {
                return;
            }
            continue;
        }
    }
    
    public void d(final String s, final boolean d) {
        final boolean r = d.r;
        int n = 0;
        while (true) {
            Label_0104: {
                if (!r) {
                    break Label_0104;
                }
                final jb jb = this.c.elementAt(n);
                final int equals = jb.e.equals(s) ? 1 : 0;
                x x = null;
                Label_0097: {
                    if (!r) {
                        if (equals == 0) {
                            ++n;
                            break Label_0104;
                        }
                        this.b.b.e(this.a(jb));
                        jb.d = d;
                        this.b.b.a(this.a(jb));
                        x = this;
                        if (r) {
                            break Label_0097;
                        }
                        this.c.size();
                    }
                    if (equals >= 4) {
                        return;
                    }
                    x = this;
                }
                x.b();
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
        final char[] charArray = "\"U)!3\u0013".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'a';
                    break;
                }
                case 1: {
                    c2 = '0';
                    break;
                }
                case 2: {
                    c2 = 'G';
                    break;
                }
                case 3: {
                    c2 = 'U';
                    break;
                }
                default: {
                    c2 = 'V';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "b\u0004\u0007".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'a';
                    break;
                }
                case 1: {
                    c4 = '0';
                    break;
                }
                case 2: {
                    c4 = 'G';
                    break;
                }
                case 3: {
                    c4 = 'U';
                    break;
                }
                default: {
                    c4 = 'V';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        x.z = z;
    }
}
