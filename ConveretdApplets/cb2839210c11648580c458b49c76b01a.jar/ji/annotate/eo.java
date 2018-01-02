// 
// Decompiled by Procyon v0.5.30
// 

package ji.annotate;

import ji.util.d;
import ji.util.e;
import java.util.Enumeration;
import ji.image.dx;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayOutputStream;
import java.awt.Component;
import java.util.Hashtable;
import java.io.InputStream;
import ji.util.i;
import ji.awt.ax;
import ji.document.ad;

public class eo implements ep
{
    protected ad a;
    protected df b;
    
    public eo(final ad a, final df b) {
        this.a = a;
        this.b = b;
    }
    
    public final void a() {
        this.a = null;
        this.b = null;
    }
    
    public boolean b() {
        return this.a(true);
    }
    
    public boolean c() {
        return this.a(false);
    }
    
    private boolean a(final boolean b) {
        try {
            if (this.b == null) {
                return false;
            }
            final ax g = this.b.g();
            boolean b2 = false;
            final boolean b3 = this.a.dm().ce() && this.a.bi(3);
            while (g.a()) {
                final dg b4 = this.b.b(g.b());
                if (dg.a(b4, b3, this.a)) {
                    if (!b4.d()) {
                        b2 = true;
                        break;
                    }
                    if (b4.e() && b) {
                        b2 = true;
                        break;
                    }
                    continue;
                }
            }
            return b2;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean d() {
        try {
            if (this.b == null) {
                return false;
            }
            if (this.a != null && this.a.bi(20)) {
                return true;
            }
            final ax g = this.b.g();
            final boolean b = this.a.dm().ce() && this.a.bi(3);
            boolean b2 = false;
            while (g.a()) {
                if (!dg.a(this.b.b(g.b()), b, this.a)) {
                    b2 = true;
                    break;
                }
            }
            return b2;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean e() {
        return i.c(129);
    }
    
    public InputStream f() {
        try {
            final StringBuffer sb = new StringBuffer(1024);
            final Hashtable hashtable = new Hashtable<Integer, acr>();
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            sb.append("<burnspec version=\"1.0\">\n");
            if (i.c(128)) {
                final int j8 = this.a.j8();
                if (this.a.ez() && !this.a.ju()) {
                    for (int i = 1; i <= j8; ++i) {
                        final Integer n = new Integer(i);
                        if (!hashtable.containsKey(n)) {
                            final dx bg = this.a.bg(i);
                            String s = this.a.be(i - 1);
                            boolean b = false;
                            if (this.a.f9()) {
                                b = true;
                                s = this.a(s, bg);
                                if (s == null) {
                                    throw new Exception("Unable to generate burn XML");
                                }
                            }
                            hashtable.put(n, new acr(s, i, this.a(bg, i, s), b));
                        }
                    }
                }
            }
            final ax g = this.b.g();
            final boolean b2 = this.a.dm().ce() && this.a.bi(3);
            final acs acs = new acs();
            while (g.a()) {
                final dg b3 = this.b.b(g.b());
                if (dg.a(b3, b2, this.a) && (b3.e() || !b3.d())) {
                    final int k = b3.i(0);
                    final Integer n2 = new Integer(k);
                    String s2 = this.a.be(n2 - 1);
                    final dx bg2 = this.a.bg(n2);
                    final int a = this.a(bg2, k, s2);
                    long bd = 0L;
                    if (bg2 != null) {
                        bd = bg2.bd;
                    }
                    if (hashtable.containsKey(n2)) {
                        hashtable.get(n2).a(bd);
                    }
                    else {
                        boolean b4 = false;
                        if (this.a.f9()) {
                            b4 = true;
                            s2 = this.a(s2, bg2);
                            if (s2 == null) {
                                throw new Exception("Unable to generate burn XML");
                            }
                        }
                        final acr acr = new acr(s2, k, a, b4);
                        acr.a(bd);
                        hashtable.put(n2, acr);
                    }
                    if (!i.c(128)) {
                        if (i.c(86)) {
                            acs.b("CRC32checksumFile", String.valueOf(bd));
                        }
                        acs.b("subPage", String.valueOf(a));
                        acs.b("page", String.valueOf(n2));
                    }
                    new ee(this.a.iu()).a(b3, b3.i(-1), this.a, this.a, false, false, acs, false);
                }
            }
            if (!acs.c()) {
                return null;
            }
            if (i.c(128)) {
                sb.append("<pages merge=\"true\">\n");
            }
            else {
                sb.append("<pages>\n");
            }
            final Enumeration<Integer> keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                sb.append(String.valueOf(String.valueOf(hashtable.get(keys.nextElement()).a())).concat("\n"));
            }
            sb.append("</pages>\n");
            sb.append("<annotations>\n");
            sb.append(acs.b().toString());
            sb.append("</annotations>\n");
            sb.append("</burnspec>");
            if (i.c(129)) {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(sb.toString().getBytes());
                final byte[] array = new byte[1024];
                int read;
                while ((read = byteArrayInputStream.read(array)) != -1) {
                    gzipOutputStream.write(array, 0, read);
                }
                gzipOutputStream.close();
                return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            }
            return new ByteArrayInputStream(sb.toString().getBytes());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private String a(final String s, final dx dx) {
        return e.b(s, "file");
    }
    
    private int a(dx dx, final int n, final String s) {
        boolean az = false;
        boolean g = false;
        if (dx == null) {
            dx = this.a.kn();
        }
        if (dx != null) {
            az = dx.az;
            g = dx.g();
        }
        else {
            dx = this.a.kn();
        }
        int b0;
        if (d.b0(s) > 1) {
            b0 = d.b0(s);
        }
        else if (!this.a.ez() && az && g) {
            b0 = n;
        }
        else {
            b0 = 1;
        }
        return b0;
    }
    
    private String a(final String s, final String s2) {
        return new String(String.valueOf(String.valueOf(new StringBuffer(" ").append(s).append("=\"").append(d.ct(s2)).append("\""))));
    }
    
    private class acr
    {
        String a;
        int b;
        int c;
        Long d;
        boolean e;
        
        acr(final eo eo, final String a, final int b, final int c, final boolean e) {
            this.a = null;
            this.b = 0;
            this.c = 0;
            this.d = null;
            this.e = false;
            this.a = a;
            this.b = b;
            this.c = c;
            this.e = e;
        }
        
        public void a(final long n) {
            this.d = new Long(n);
        }
        
        public String a() {
            final StringBuffer sb = new StringBuffer();
            if (!this.e) {
                sb.append(String.valueOf(String.valueOf(new StringBuffer("<page number=\"").append(this.b).append("\" url=\"").append(ji.util.d.ct(this.a)).append("\" subPage=\"").append(this.c).append("\""))));
                if (this.d != null) {
                    sb.append(String.valueOf(String.valueOf(new StringBuffer(" CRC32checksumFile=\"").append((long)this.d).append("\""))));
                }
                sb.append("/>");
            }
            else {
                sb.append(String.valueOf(String.valueOf(new StringBuffer("<page number=\"").append(this.b).append("\" file=\"").append(ji.util.d.ct(this.a)).append("\" subPage=\"").append(this.c).append("\""))));
                if (this.d != null) {
                    sb.append(String.valueOf(String.valueOf(new StringBuffer(" CRC32checksumFile=\"").append((long)this.d).append("\""))));
                }
                sb.append("/>");
            }
            return sb.toString();
        }
    }
    
    private class acs implements ef
    {
        StringBuffer a;
        boolean b;
        boolean c;
        Hashtable d;
        
        public acs() {
            this.b = false;
            this.c = false;
            this.d = new Hashtable();
            this.a = new StringBuffer();
        }
        
        public void b(String lowerCase) {
            if (lowerCase == null) {
                this.b = true;
            }
            else if (lowerCase.length() == 0) {
                this.b = true;
            }
            else if (lowerCase.equalsIgnoreCase("custom")) {
                this.b = true;
            }
            else if (lowerCase.equalsIgnoreCase("document")) {
                this.b = true;
            }
            else {
                this.b = false;
            }
            if (!this.b) {
                this.c = true;
                if (lowerCase != null) {
                    lowerCase = lowerCase.toLowerCase();
                }
                this.a.append(String.valueOf(String.valueOf(new StringBuffer("<annotation type=\"").append(lowerCase).append("\""))));
                if (this.d.size() > 0) {
                    final Enumeration<String> keys = this.d.keys();
                    while (keys.hasMoreElements()) {
                        final String s = keys.nextElement();
                        this.a(s, (String)this.d.get(s));
                    }
                    this.d.clear();
                }
            }
        }
        
        public void a(String lowerCase, final String s) {
            boolean b = false;
            if (lowerCase == null) {
                b = true;
            }
            else if (lowerCase.length() == 0) {
                b = true;
            }
            else if (lowerCase.equalsIgnoreCase("pageurl")) {
                b = true;
            }
            if (!this.b && !b) {
                if (lowerCase != null) {
                    int n = 0;
                    for (int n2 = 0; n == 0 && n2 < lowerCase.length(); ++n2) {
                        if (Character.isLowerCase(lowerCase.charAt(n2))) {
                            n = 1;
                        }
                    }
                    if (n == 0) {
                        lowerCase = lowerCase.toLowerCase();
                    }
                }
                this.a.append(eo.this.a(lowerCase, s));
            }
        }
        
        public void a() {
            if (!this.b) {
                this.a.append("/>\n");
            }
            this.b = false;
        }
        
        public void a(final String s) {
            if (!this.b) {}
        }
        
        public StringBuffer b() {
            return this.a;
        }
        
        public boolean c() {
            return this.c;
        }
        
        public void a(final double n, final double n2) {
            this.a("XRES", "".concat(String.valueOf(String.valueOf(n))));
            this.a("YRES", "".concat(String.valueOf(String.valueOf(n2))));
        }
        
        void b(final String s, final String s2) {
            this.d.put(s, s2);
        }
    }
}
