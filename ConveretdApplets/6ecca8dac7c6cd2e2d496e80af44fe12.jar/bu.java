import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

class bu
{
    String a;
    String b;
    String c;
    String d;
    String e;
    String f;
    String g;
    String h;
    
    String a() {
        return this.a;
    }
    
    bu() {
        this.e = "";
        this.f = new String(f("\u000fzM\\J\u0000\u0019ZYL\u000bwM\\K\bv9SL\u0000~\\G%\u001epWR%\u001apTP%\u001bj\\GL\u0000\u007fV5S\u000bkJ\\J\u0000"));
        this.g = new String("");
        this.h = new String(irc.j + " " + irc.a + " " + p.a);
    }
    
    void a(final String s) {
        if (s.equals("+")) {
            this.e = "";
            return;
        }
        this.e = "(" + s + ")";
    }
    
    String b() {
        return this.e;
    }
    
    String c() {
        return new Date().toString();
    }
    
    void b(final String s) {
        this.b = new String(s.trim());
    }
    
    String d() {
        return this.b;
    }
    
    void c(final String s) {
        this.d = new String(s);
    }
    
    String e() {
        return this.d;
    }
    
    String f() {
        return this.h;
    }
    
    String g() {
        return this.f;
    }
    
    String h() {
        return this.g;
    }
    
    void d(final String s) {
        this.c = new String(s.trim());
    }
    
    String i() {
        return this.c;
    }
    
    void e(final String s) {
        this.a = new String(s.trim());
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
                    c2 = 'N';
                    break;
                }
                case 1: {
                    c2 = '9';
                    break;
                }
                case 2: {
                    c2 = '\u0019';
                    break;
                }
                case 3: {
                    c2 = '\u0015';
                    break;
                }
                default: {
                    c2 = '\u0005';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
