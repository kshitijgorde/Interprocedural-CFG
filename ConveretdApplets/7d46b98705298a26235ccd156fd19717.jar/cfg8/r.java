// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

class r
{
    final g a;
    private w b;
    private String c;
    private String d;
    private String e;
    private static String[] z;
    
    r(final g a, final w b) {
        this.a = a;
        this.b = null;
        this.c = "";
        this.d = "";
        this.e = "";
        this.b = b;
        this.d = b.d(r.z[2]);
        this.e = b.d(r.z[1]);
    }
    
    protected w a() {
        return this.b;
    }
    
    protected String b() {
        return this.b.b();
    }
    
    protected boolean c() {
        final int a = RotationImageFilter.a;
        final int length = this.e.length();
        final int n = 0;
        int length2 = 0;
        int o = 0;
        Label_0068: {
            Label_0052: {
                if (a == 0) {
                    if (length > n) {
                        final boolean m = this.a.m(this.e);
                        final boolean b = false;
                        if (a != 0) {
                            break Label_0052;
                        }
                        if (m == b) {
                            return true;
                        }
                    }
                    final int n2;
                    o = (n2 = (length2 = this.d.length()));
                    if (a != 0) {
                        break Label_0068;
                    }
                }
            }
            if (length < n) {
                return false;
            }
            length2 = (o = (this.a.o(this.d) ? 1 : 0));
        }
        if (a == 0) {
            if (o == 0) {
                length2 = (true ? 1 : 0);
            }
            else {
                length2 = (false ? 1 : 0);
            }
        }
        return length2 != 0;
    }
    
    protected boolean a(final String s) {
        final int a = RotationImageFilter.a;
        final String d;
        final String s2 = d = this.b.d(r.z[0]);
        final int length;
        final int n;
        Label_0083: {
            if (a != 0 || d != null) {
                length = d.length();
                n = 0;
                if (a != 0) {
                    break Label_0083;
                }
                if (length > n) {
                    final boolean a2 = this.a.a(this.b, s2, true);
                    if (a != 0 || a2) {
                        return a2;
                    }
                    if (a == 0) {
                        return false;
                    }
                }
            }
            final boolean a3;
            final boolean b = a3 = this.a.a(this.b, s, 0 != 0);
            if (a != 0) {
                return b;
            }
        }
        if (length == n) {
            return true;
        }
        return false;
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "<9j?".toCharArray();
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
                            c2 = 'Y';
                            break;
                        }
                        case 1: {
                            c2 = 'O';
                            break;
                        }
                        case 2: {
                            c2 = '\u000b';
                            break;
                        }
                        case 3: {
                            c2 = 'S';
                            break;
                        }
                        default: {
                            c2 = 'z';
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
        final char[] charArray2 = "5.r6\b".toCharArray();
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
                            c4 = 'Y';
                            break;
                        }
                        case 1: {
                            c4 = 'O';
                            break;
                        }
                        case 2: {
                            c4 = '\u000b';
                            break;
                        }
                        case 3: {
                            c4 = 'S';
                            break;
                        }
                        default: {
                            c4 = 'z';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "?&g'\u001f+".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = 'Y';
                            break;
                        }
                        case 1: {
                            c6 = 'O';
                            break;
                        }
                        case 2: {
                            c6 = '\u000b';
                            break;
                        }
                        case 3: {
                            c6 = 'S';
                            break;
                        }
                        default: {
                            c6 = 'z';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                z[n9] = new String(charArray3).intern();
                r.z = z;
                return;
            }
            continue;
        }
    }
}
