import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

final class a
{
    int a;
    boolean b;
    int c;
    int d;
    int e;
    int f;
    int g;
    String h;
    String i;
    
    a(final Applet applet, final int n, final String s) {
        this.a = 4;
        this.b = false;
        this.d = 0;
        this.e = 2;
        this.f = 999999;
        this.g = 444;
        this.h = "";
        this.i = "Slideshow licensed to Muhr Design only. Copying is prohibited.";
        s.trim();
        s.toUpperCase();
        this.c = s.charAt(s.length() - 1) - '0';
        if (s.charAt(0) - 'A' > this.c) {
            this.e = s.charAt(0) - 'A' - this.c;
        }
        this.h = applet.getDocumentBase().getHost().toUpperCase();
        System.out.println("ID = " + this.a + ", HostName = '" + this.h + "' (" + this.e + ")");
        if (this.i.length() > 5 && this.i.compareTo(s) == 0) {
            this.b = true;
            return;
        }
        if (this.a(2) == 230) {
            this.b = true;
            return;
        }
        if (this.c + 4 < s.length()) {
            this.f = new Integer(s.substring(this.c, this.c + 3));
            this.g = new Integer(s.substring(s.length() - 4, s.length() - 1));
            if (this.h.length() > 0) {
                this.d = this.a(this.e);
            }
            else {
                this.g = 444;
            }
            if (this.f == n && this.g == (n * this.d + 444) % 1000) {
                this.b = true;
            }
        }
    }
    
    private int a(final int n) {
        char c = '\0';
        int n2 = 0;
        for (int i = this.h.length() - 1; i >= 0; --i) {
            if (this.h.charAt(i) == '.') {
                if (++n2 >= n) {
                    i = -1;
                }
            }
            else if (n2 < n && this.h.charAt(i) >= 'A' && this.h.charAt(i) <= 'Z') {
                c += (char)(this.h.charAt(i) - 'A' + '\n');
            }
            else if (n2 < n && this.h.charAt(i) >= '0' && this.h.charAt(i) <= '9') {
                c += (char)(this.h.charAt(i) - '0');
            }
        }
        return c;
    }
}
