// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

import java.util.Vector;

class w extends v
{
    final u d;
    private Vector e;
    private static String[] z;
    
    w(final u d) {
        super(null);
        this.d = d;
        this.e = new Vector();
    }
    
    boolean c(final String s) {
        int h;
        final int n = h = this.h(s);
        if (RotationImageFilter.a == 0) {
            if (n < 0) {
                h = (false ? 1 : 0);
            }
            else {
                h = (true ? 1 : 0);
            }
        }
        return h != 0;
    }
    
    String d(final String s) {
        return this.a(s, "");
    }
    
    int e(final String s) {
        return this.a(s, 0);
    }
    
    double f(final String s) {
        return this.a(s, 0.0);
    }
    
    boolean g(final String s) {
        return this.a(s, false);
    }
    
    String a(final String s, final String s2) {
        final v i = this.i(s);
        return (RotationImageFilter.a == 0 && i == null) ? s2 : i.b();
    }
    
    int a(final String s, final int n) {
        final v i = this.i(s);
        return (RotationImageFilter.a == 0 && i == null) ? n : i.c();
    }
    
    double a(final String s, final double n) {
        final v i = this.i(s);
        return (RotationImageFilter.a == 0 && i == null) ? n : i.d();
    }
    
    boolean a(final String s, final boolean b) {
        final v i = this.i(s);
        return (RotationImageFilter.a == 0 && i == null) ? b : i.e();
    }
    
    int f() {
        return this.e.size();
    }
    
    v b(final int n) {
        return this.e.elementAt(n);
    }
    
    int h(final String s) {
        final int a = RotationImageFilter.a;
        final int size = this.e.size();
        int i = 0;
        while (i < size) {
            final v v = this.e.elementAt(i);
            if (a == 0) {
                final int equals;
                final boolean b = (equals = (v.a().equals(s) ? 1 : 0)) != 0;
                if (a != 0) {
                    return equals;
                }
                if (b) {
                    return i;
                }
                ++i;
            }
            if (a != 0) {
                break;
            }
        }
        return -1;
    }
    
    v i(final String s) {
        final int h = this.h(s);
        return (h < 0) ? null : ((v)this.e.elementAt(h));
    }
    
    void g() {
        this.e.removeAllElements();
    }
    
    void b(final String s, final String s2) {
        this.j(s).b(s2);
    }
    
    void b(final String s, final int n) {
        this.j(s).a(n);
    }
    
    void b(final String s, final double n) {
        this.j(s).a(n);
    }
    
    void b(final String s, final boolean b) {
        this.j(s).a(b);
    }
    
    v j(final String s) {
        final v v = new v(null);
        this.e.addElement(v);
        v.a(s);
        return v;
    }
    
    protected void k(final String s) {
        final int h = this.h(s);
        if (RotationImageFilter.a == 0) {
            if (h < 0) {
                return;
            }
            this.e.removeElementAt(h);
        }
    }
    
    protected int l(final String s) {
        final int i = RotationImageFilter.a;
        final int index;
        final int n = index = s.indexOf(60);
        if (i == 0) {
            if (index < 0) {
                return -1;
            }
            s.lastIndexOf(62);
        }
        final int n2 = index;
        if (i == 0) {
            if (n2 < 0) {
                return -1;
            }
            s.indexOf(62);
        }
        int n3 = n2;
        boolean b = false;
        final String substring = s.substring(n + 1, n3);
        final int index2 = substring.indexOf(61);
        Label_0142: {
            Label_0136: {
                if (i == 0) {
                    if (index2 < 0) {
                        break Label_0136;
                    }
                    substring.indexOf(32);
                }
                final int n5;
                final int n4 = n5 = index2;
                Label_0131: {
                    Label_0125: {
                        if (i == 0) {
                            if (n5 < 0) {
                                break Label_0125;
                            }
                            b = true;
                        }
                        n3 = n5;
                        this.a(substring.substring(0, n4));
                        if (i == 0) {
                            break Label_0131;
                        }
                    }
                    this.a(substring);
                }
                if (i == 0) {
                    break Label_0142;
                }
            }
            this.a(substring);
        }
        int index3;
        final boolean b2 = (index3 = (b ? 1 : 0)) != 0;
        if (i == 0) {
            if (b2) {
                final String substring2 = substring.substring(n3 + 1);
                int n6 = 0;
                do {
                    final int index4 = substring2.indexOf(61, n6);
                    if (index4 < 0 && (i != 0 || i == 0)) {
                        break;
                    }
                    final String substring3 = substring2.substring(n6, index4);
                    final int index5;
                    final int n7 = index5 = substring2.indexOf(34, n6);
                    if (i == 0) {
                        if (index5 < 0 && i == 0) {
                            break;
                        }
                        substring2.indexOf(34, n7 + 1);
                    }
                    final int n8 = index5;
                    if (n8 < 0 && i == 0) {
                        break;
                    }
                    this.e.addElement(new v(null, substring3, u.c(substring2.substring(n7 + 1, n8))));
                    n6 = n8 + 2;
                } while (i == 0);
                n3 += n6;
            }
            index3 = s.indexOf(62, n3);
        }
        return index3;
    }
    
    private int m(final String s) {
        final int l;
        final int n = l = this.l(s);
        if (RotationImageFilter.a == 0 && l >= 0) {
            final int lastIndex = s.lastIndexOf(String.valueOf(String.valueOf(w.z[0]).concat(String.valueOf(this.a()))).concat(String.valueOf(">")));
            this.b(u.c(s.substring(n + 1, lastIndex)));
            return lastIndex;
        }
        return l;
    }
    
    v n(final String s) {
        final v i = this.i(s);
        if (RotationImageFilter.a == 0 && i == null) {
            this.j(s);
            goto Label_0023;
        }
        return i;
    }
    
    void c(final String s, final String s2) {
        this.n(s).b(s2);
    }
    
    void c(final String s, final int n) {
        this.n(s).a(n);
    }
    
    void c(final String s, final boolean b) {
        this.n(s).a(b);
    }
    
    protected String h() {
        final int a = RotationImageFilter.a;
        String s = String.valueOf("<").concat(String.valueOf(this.a()));
        final int f = this.f();
        int i = 0;
        while (i < f) {
            final v b = this.b(i);
            if (a != 0) {
                return s;
            }
            if (b != null || a != 0) {
                s = String.valueOf(s).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(" ").concat(String.valueOf(b.a()))).concat(String.valueOf(w.z[1]))).concat(String.valueOf(u.b(b.b())))).concat(String.valueOf("\""))));
            }
            ++i;
            if (a != 0) {
                break;
            }
        }
        s = String.valueOf(s).concat(String.valueOf(">"));
        return s;
    }
    
    protected String i() {
        return String.valueOf(String.valueOf(this.h()).concat(String.valueOf(u.b(this.b())))).concat(String.valueOf(String.valueOf(String.valueOf(w.z[0]).concat(String.valueOf(this.a()))).concat(String.valueOf(">"))));
    }
    
    void a(final w w) {
        final int a = RotationImageFilter.a;
        this.a(w.a());
        this.b(w.b());
        final int f = w.f();
        int i = 0;
        while (i < f) {
            final v b = w.b(i);
            this.b(b.a(), b.b());
            ++i;
            if (a != 0) {
                break;
            }
        }
    }
    
    static int a(final w w, final String s) {
        return w.m(s);
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "}[".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = 'A';
                            break;
                        }
                        case 1: {
                            c2 = 't';
                            break;
                        }
                        case 2: {
                            c2 = 'f';
                            break;
                        }
                        case 3: {
                            c2 = '\r';
                            break;
                        }
                        default: {
                            c2 = 'C';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "|V".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = 'A';
                            break;
                        }
                        case 1: {
                            c4 = 't';
                            break;
                        }
                        case 2: {
                            c4 = 'f';
                            break;
                        }
                        case 3: {
                            c4 = '\r';
                            break;
                        }
                        default: {
                            c4 = 'C';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                z[n5] = new String(charArray2).intern();
                w.z = z;
                return;
            }
            continue;
        }
    }
}
