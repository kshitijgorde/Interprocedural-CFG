import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class bq
{
    static int a;
    int b;
    String[] c;
    boolean[] d;
    bw e;
    bf f;
    
    void a(final bf f) {
        this.f = f;
    }
    
    void a(String s) {
        final boolean dx = bm.dX;
        boolean b = false;
        int n = s.indexOf(" ");
        if (s.toLowerCase().startsWith(f("N\u000f"))) {
            b = true;
            final int index = s.indexOf(" ");
            if (index < 0) {
                return;
            }
            s = s.substring(index + 1);
            n = s.indexOf(" ");
        }
        do {
            String substring = null;
            Label_0089: {
                if (n < 0) {
                    substring = s;
                    s = "";
                    if (!dx) {
                        break Label_0089;
                    }
                }
                substring = s.substring(0, n);
                s = s.substring(n + 1);
            }
            Label_0195: {
                if (!b) {
                    final String c = this.c(substring);
                    if (c.length() != 0) {
                        this.e.a(irc.R + c, bn.e, false);
                        if (!dx) {
                            break Label_0195;
                        }
                    }
                    this.e.a(irc.R + substring + bm.cL, bn.e, false);
                    if (!dx) {
                        break Label_0195;
                    }
                }
                this.d(substring);
            }
            this.f.g.d();
            n = s.indexOf(" ");
        } while (s.length() != 0);
    }
    
    boolean b(final String s) {
        final boolean dx = bm.dX;
        int n = 0;
        while (true) {
            while (true) {
                Label_0034: {
                    if (!dx) {
                        break Label_0034;
                    }
                    s.toUpperCase().equals(this.c[n].toUpperCase());
                    final boolean b;
                    if (b) {
                        return true;
                    }
                    ++n;
                }
                if (n != this.b) {
                    continue;
                }
                break;
            }
            final boolean b = false;
            if (!dx) {
                return b;
            }
            continue;
        }
    }
    
    bq() {
        this.b = 0;
        this.c = new String[bq.a];
        this.d = new boolean[bq.a];
    }
    
    String c(final String s) {
        final int b = this.b;
        if (this.b + 1 == bq.a) {
            return new String(bm.cH);
        }
        if (this.b(s)) {
            return new String(s + bm.cI);
        }
        this.c[b] = new String(s);
        this.d[b] = false;
        ++this.b;
        this.f.g.d();
        return "";
    }
    
    void d(String upperCase) {
        final boolean dx = bm.dX;
        upperCase = upperCase.toUpperCase();
        int n = 0;
    Label_0068_Outer:
        while (true) {
            Label_0098: {
                if (!dx) {
                    break Label_0098;
                }
                if (this.c[n].toUpperCase().equals(upperCase)) {
                    int n2 = n;
                    while (true) {
                        while (true) {
                            Label_0071: {
                                if (!dx) {
                                    break Label_0071;
                                }
                                this.c[n2] = this.c[n2 + 1];
                                this.d[n2] = this.d[n2 + 1];
                                ++n2;
                            }
                            if (n2 < this.b) {
                                continue Label_0068_Outer;
                            }
                            break;
                        }
                        --this.b;
                        if (!dx) {
                            return;
                        }
                        continue;
                    }
                }
                else {
                    ++n;
                }
            }
            if (n == this.b) {
                return;
            }
            continue;
        }
    }
    
    void a(final bw e) {
        this.e = e;
    }
    
    void a() {
        final boolean dx = bm.dX;
        String string = null;
        String string2 = null;
        int n = 0;
        String s = null;
        while (true) {
            Label_0134: {
                if (n == this.b) {
                    s = string;
                    if (!dx) {
                        break;
                    }
                }
                else if (this.d[n]) {
                    if (string == null) {
                        string = new String(this.c[n]);
                        if (!dx) {
                            break Label_0134;
                        }
                    }
                    string = string + " " + this.c[n];
                    if (!dx) {
                        break Label_0134;
                    }
                }
                if (s == null) {
                    string2 = new String(this.c[n]);
                    if (!dx) {
                        break Label_0134;
                    }
                }
                string2 = string2 + " " + this.c[n];
            }
            ++n;
        }
        if (s != null) {
            this.e.a(bm.cK + string, bn.e, false);
        }
        if (string2 != null) {
            this.e.a(bm.cJ + string2, bn.e, false);
        }
    }
    
    static {
        bq.a = 30;
    }
    
    String b() {
        final boolean dx = bm.dX;
        String f = f("*.X$");
        int n = 0;
        while (true) {
            while (true) {
                Label_0049: {
                    if (!dx) {
                        break Label_0049;
                    }
                    new StringBuffer().append(f).append(" ").append(this.c[n]).toString();
                    final String s;
                    f = s;
                    ++n;
                }
                if (n != this.b) {
                    continue;
                }
                break;
            }
            final String s = f;
            if (!dx) {
                return s;
            }
            continue;
        }
    }
    
    void e(String s) {
        final boolean dx = bm.dX;
        s += " ";
        s = s.toUpperCase();
        int n = 0;
        while (true) {
            Label_0198: {
                if (!dx) {
                    break Label_0198;
                }
                Label_0195: {
                    if (s.indexOf(this.c[n].toUpperCase() + " ") >= 0) {
                        if (this.d[n]) {
                            break Label_0195;
                        }
                        this.e.a("<" + this.c[n] + ">" + bm.cA, Color.blue, false);
                        this.d[n] = true;
                        if (!dx) {
                            break Label_0195;
                        }
                    }
                    if (this.d[n]) {
                        this.e.a(irc.R + this.c[n] + bm.cz, Color.blue, false);
                        this.d[n] = false;
                    }
                }
                ++n;
            }
            if (n == this.b) {
                return;
            }
            continue;
        }
    }
    
    String[] c() {
        return this.c;
    }
    
    private static String f(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'c';
                    break;
                }
                case 1: {
                    c2 = '}';
                    break;
                }
                case 2: {
                    c2 = '\u0017';
                    break;
                }
                case 3: {
                    c2 = 'j';
                    break;
                }
                default: {
                    c2 = '\u001c';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
