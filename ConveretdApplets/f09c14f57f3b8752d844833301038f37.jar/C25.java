import java.net.MalformedURLException;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class C25 extends C35
{
    String i;
    String j;
    URL k;
    String l;
    String m;
    String n;
    boolean o;
    public static URL p;
    boolean q;
    
    public C25(final URL k) {
        this.l = null;
        this.m = null;
        this.n = null;
        this.j = null;
        this.q = false;
        this.o = false;
        this.k = null;
        this.k = k;
    }
    
    public String a() {
        return this.n;
    }
    
    public void b(final C15 c15, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
    }
    
    public String c() {
        return this.j;
    }
    
    public Object clone() {
        return new C25(this.k);
    }
    
    public String e() {
        return this.l;
    }
    
    public String f() {
        return this.i;
    }
    
    public URL g() {
        return this.k;
    }
    
    public String h() {
        return this.m;
    }
    
    public C25(URL url, String s) throws MalformedURLException {
        this.l = null;
        this.m = null;
        this.n = null;
        this.j = null;
        this.q = false;
        this.o = false;
        this.k = null;
        if (C25.p == null) {
            C25.p = new URL("http://www.expocadvr.com");
        }
        if (s.indexOf("|") != -1) {
            if (s.startsWith("'")) {
                s = s.substring(1);
            }
            if (s.endsWith("'")) {
                s = s.substring(0, s.length() - 1);
            }
            this.m = s.substring(0, s.indexOf("="));
            this.n = s.substring(s.indexOf("=") + 1, s.indexOf("|"));
            this.j = s.substring(s.indexOf("|") + 1, s.indexOf("|", s.indexOf("|") + 1));
            s = s.substring(s.indexOf("|", s.indexOf("|") + 1) + 1);
            if (s == null || s.equals("")) {
                this.k = C25.p;
                return;
            }
        }
        if (s.startsWith("'")) {
            s = s.substring(1);
        }
        if (s.endsWith("'")) {
            s = s.substring(0, s.length() - 1);
        }
        if (s.toLowerCase().startsWith("javascript:")) {
            int n = 0;
            this.i = s.substring(11);
            if (this.i.indexOf("\\'") != -1) {
                while (this.i.indexOf("\\'") != -1 && n++ < 10) {
                    final int index = this.i.indexOf("\\'");
                    if (index < this.i.length() - 2) {
                        this.i = this.i.substring(0, index) + this.i.substring(index + 1);
                    }
                    else {
                        this.i = this.i.substring(0, index) + "'";
                    }
                }
            }
            return;
        }
        if (url == null) {
            url = new URL("http://www.expocadvr.com");
        }
        final String host = url.getHost();
        final String protocol = url.getProtocol();
        final String file = url.getFile();
        final int port = url.getPort();
        if (s.length() < 4) {
            this.k = null;
        }
        else if (s.toLowerCase().startsWith("http://")) {
            this.k = new URL(s);
        }
        else if (!s.startsWith("/") && !s.startsWith(".")) {
            this.k = new URL((url == null) ? new URL("http://www.cadviewer.com") : url, s);
        }
        else if (s.startsWith("..")) {
            String s2 = "/" + s;
            String s3 = file;
            if (s3.endsWith("/")) {
                s3 = s3.substring(0, s3.length() - 1);
            }
            while (s2.startsWith("/..") && file.indexOf("/") == -1) {
                System.out.println("pp=" + s3 + " uu=" + s2);
                s2 = s2.substring(3);
                s3 = s3.substring(0, s3.lastIndexOf("/"));
            }
            this.k = new URL(protocol + "://" + host + ((port == -1) ? "" : (":" + port)) + s3 + s2);
        }
        else if (s.startsWith("/")) {
            this.k = new URL(protocol + "://" + host + ((port == -1) ? "" : (":" + port)) + s);
        }
        else {
            this.k = new URL(protocol + "://" + host + ((port == -1) ? "" : (":" + port)) + file + s);
        }
        if (this.k != null && this.k.toString().indexOf("target=") != -1) {
            final int index2 = this.k.toString().indexOf("target=");
            int n2 = this.k.toString().indexOf("&", index2 + 3);
            if (n2 == -1) {
                n2 = this.k.toString().indexOf("/", index2 + 3);
            }
            if (n2 == -1) {
                n2 = this.k.toString().indexOf("\\", index2 + 3);
            }
            if (n2 == -1) {
                this.l = this.k.toString().substring(index2 + 7);
            }
            else {
                this.l = this.k.toString().substring(index2 + 7, n2);
            }
        }
    }
}
