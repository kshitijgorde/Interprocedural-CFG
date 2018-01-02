// 
// Decompiled by Procyon v0.5.30
// 

package daeja4.filter.msg;

import ji.res.s;
import ji.util.t;
import java.util.Hashtable;
import java.io.StringReader;
import ji.util.d;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
import daeja4.cbf.nq;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import ji.io.a8;
import ji.io.q;
import java.io.File;
import ji.io.h;
import ji.io.ac;
import daeja4.cbf.ni;
import ji.document.gm;
import daeja4.cbf.ne;

public class nd extends ne
{
    private static final String[] a;
    private static final int[] b;
    private static final String[] c;
    private static final String[] d;
    private static final String[] e;
    private static final String[] f;
    private static final String[] g;
    private Object h;
    private String i;
    private static final gm[] j;
    private ni[] k;
    private ni[] l;
    private gm[] m;
    private boolean n;
    private ni o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    
    public nd(final ac ac) throws Exception {
        super(ac);
        this.k = null;
        this.n = false;
        this.p = null;
        this.t = "001F";
    }
    
    public void a() throws Exception {
        super.a();
        if (super.h.a("__substg1.0_0C1F001E") != null) {
            this.t = "001E";
        }
        else if (super.h.a("__substg1.0_0037001E") != null) {
            this.t = "001E";
        }
    }
    
    public String b() {
        try {
            this.i();
        }
        catch (Exception ex) {
            ji.io.h.a(this.i, ex);
        }
        return this.p;
    }
    
    public void c() {
        if (this.m != null) {
            for (int i = 0; i < this.m.length; ++i) {
                try {
                    final String k = this.m[i].k;
                    if (k != null) {
                        if (ac.d(k, this.i)) {
                            ac.c(k, this.i);
                        }
                    }
                }
                catch (Exception ex) {
                    ji.io.h.a(this.i, ex);
                }
            }
        }
        this.m = null;
        if (this.r != null) {
            try {
                ac.c(this.r, this.i);
            }
            catch (Exception ex2) {
                ji.io.h.a(this.i, ex2);
            }
        }
    }
    
    public String a(final String s) {
        String k = null;
        try {
            if (s != null) {
                boolean b = false;
                int n = 1;
                this.g();
                gm gm = null;
                for (int i = 0; i < this.m.length; ++i) {
                    if (s.startsWith("cid:") && s.length() > 4) {
                        if (s.substring(4).equals(this.m[i].j)) {
                            b = true;
                            gm = this.m[i];
                            break;
                        }
                    }
                    else if (s.equals(this.m[i].b)) {
                        b = true;
                        gm = this.m[i];
                        break;
                    }
                }
                if (b) {
                    if (gm.k != null) {
                        n = (new File(gm.k).exists() ? 0 : 1);
                    }
                    if (n != 0) {
                        ac ac = null;
                        a8 a8 = null;
                        try {
                            k = ji.io.q.a(this.h, this.i).a(true);
                            ac = new ac(k, true, false, 0, false, this.h, this.i);
                            ac.a(true);
                            a8 = new a8(ac, this.h);
                            this.a(gm, a8);
                            gm.k = k;
                        }
                        catch (Exception ex) {
                            k = null;
                            throw ex;
                        }
                        finally {
                            if (a8 != null) {
                                a8.flush();
                                a8.close();
                            }
                            if (ac != null) {
                                ac.a(this.h);
                            }
                        }
                    }
                    k = "jifile:///".concat(String.valueOf(String.valueOf(gm.k)));
                }
            }
        }
        catch (Exception ex2) {
            ji.io.h.a(this.i, ex2);
            this.m = null;
        }
        return k;
    }
    
    public String d() {
        return this.i;
    }
    
    public void a(final gm gm, final OutputStream outputStream) throws Exception {
        final ni ni = super.e.elementAt(gm.e);
        if (ni != null) {
            if (gm.i == 2) {
                this.b(ni, outputStream);
            }
            else {
                this.a(ni, outputStream);
            }
            return;
        }
        throw new IllegalArgumentException("Invalid attachment info:  ".concat(String.valueOf(String.valueOf(gm))));
    }
    
    public InputStream a(final ni ni) throws IOException {
        final boolean[] array = { false };
        final PipedOutputStream pipedOutputStream = new PipedOutputStream();
        final PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);
        new Thread(new zf(this, ni, pipedOutputStream, array), this.i).start();
        return pipedInputStream;
    }
    
    private void b(final ni ni, final OutputStream outputStream) throws Exception {
        InputStream a = null;
        try {
            a = this.a(ni);
            final nq nq = new nq(a, "{\\rtf1\\ansi\\mac\\deff0\\deftab720{\\fonttbl;}{\\f0\\fnil \\froman \\fswiss \\fmodern \\fscript \\fdecor MS Sans SerifSymbolArialTimes New RomanCourier{\\colortbl\\red0\\green0\\blue0\n\r\\par \\pard\\plain\\f0\\fs20\\b\\i\\u\\tab\\tx");
            if (nq.a()) {
                nq.a(outputStream);
            }
            else {
                try {
                    a.close();
                }
                catch (Exception ex) {
                    ji.io.h.d(this.i, "TNEF input stream closed");
                }
                this.a(ni, outputStream);
            }
        }
        finally {
            if (a != null) {
                a.close();
            }
        }
    }
    
    public void finalize() {
        this.c();
    }
    
    boolean e() {
        return this.f().length > 0;
    }
    
    ni[] f() {
        if (this.k == null) {
            final Vector vector = new Vector();
            super.h.e().a("__attach_version1.0_", vector);
            this.k = vector.toArray(new ni[vector.size()]);
        }
        return this.k;
    }
    
    private ni[] k() {
        if (this.l == null) {
            final Vector vector = new Vector();
            super.h.e().a("__recip_version1.0_#", vector);
            this.l = vector.toArray(new ni[vector.size()]);
        }
        return this.l;
    }
    
    gm[] g() throws Exception {
        if (this.m == null) {
            this.f();
            if (this.f().length == 0) {
                this.m = nd.j;
            }
            else {
                this.m = new gm[this.k.length];
                for (int i = 0; i < this.k.length; ++i) {
                    this.m[i] = new gm(this.k[i].a(), this.k[i].b(), this.a(this.k[i], "__substg1.0_3707".concat(String.valueOf(String.valueOf(this.t))), true));
                    ni ni = this.k[i].a("__substg1.0_37010102");
                    if (ni == null) {
                        ni = this.k[i].a("__substg1.0_3701000D");
                        final ni a = ni.a("__substg1.0_10090102");
                        if (a != null) {
                            this.m[i].b = this.a(this.k[i], "__substg1.0_3001".concat(String.valueOf(String.valueOf(this.t))), true);
                            ni = a;
                            this.m[i].d = ".rtf";
                            this.m[i].g = false;
                            this.m[i].h = true;
                            this.m[i].i = 2;
                        }
                        else {
                            this.m[i].g = false;
                            this.m[i].h = false;
                        }
                    }
                    else {
                        this.m[i].d = this.a(this.k[i], "__substg1.0_3703".concat(String.valueOf(String.valueOf(this.t))), true);
                        this.m[i].g = true;
                        this.m[i].h = false;
                    }
                    if (ni != null) {
                        this.m[i].e = ni.a();
                        this.m[i].f = ni.b();
                        this.m[i].j = this.a(this.k[i], "__substg1.0_3712".concat(String.valueOf(String.valueOf(this.t))), true);
                    }
                }
            }
        }
        return this.m;
    }
    
    void a(final Object h, final String i) {
        this.h = h;
        this.i = i;
    }
    
    synchronized ac h() throws Exception {
        ac ac = null;
        if (this.r != null) {
            ac = new ac(this.r, false, false, 64, true, this.h, this.i);
            ac.a(true);
            if (!ji.io.ac.d(ac.a(), this.i)) {
                this.r = null;
            }
        }
        if (this.r == null) {
            final boolean b = false;
            this.r = ji.io.q.a(this.h, this.i).n();
            ac = new ac(this.r, true, false, 64, true, this.h, this.i);
            ac.a(true);
            BufferedReader bufferedReader = null;
            if (this.o == null) {
                this.o = super.h.a("__substg1.0_10130102");
                if (this.o == null) {
                    this.o = super.h.a("__substg1.0_1013".concat(String.valueOf(String.valueOf(this.t))));
                }
            }
            if (this.o != null && !b) {
                final StringBuffer sb = new StringBuffer();
                final StringBuffer sb2 = new StringBuffer();
                if (this.q == null) {
                    this.q = "ISO-8859-1";
                    bufferedReader = new BufferedReader(new InputStreamReader(this.a(this.o)));
                    int n = 0;
                    while (n++ < 50) {
                        final String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb2.append(line);
                        final int index = line.indexOf("charset=");
                        if (index == -1) {
                            continue;
                        }
                        final StringTokenizer stringTokenizer = new StringTokenizer(line.substring(index + 8), "\" ");
                        if (stringTokenizer.countTokens() > 0) {
                            this.q = stringTokenizer.nextToken();
                            break;
                        }
                    }
                    bufferedReader.close();
                }
                if ("unicode".equalsIgnoreCase(this.q)) {
                    this.q = "US-ASCII";
                }
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(this.a(this.o), this.q));
                }
                catch (UnsupportedEncodingException ex) {
                    ji.io.h.d(this.i, "Unsupported encoding ".concat(String.valueOf(String.valueOf(this.q))));
                    this.q = "ISO-8859-1";
                }
                if (this.p == null) {
                    this.p = "text/html charset=".concat(String.valueOf(String.valueOf(this.q)));
                }
                String line2;
                while ((line2 = bufferedReader.readLine()) != null) {
                    ac.b(line2.getBytes());
                }
                this.r = null;
            }
            else {
                this.p = "text/html";
                ac.b("<html><body>".getBytes());
                this.o = super.h.a("__substg1.0_1000");
                bufferedReader = new BufferedReader(new InputStreamReader(this.a(this.o), "UTF-16LE"));
                String s = bufferedReader.readLine();
                final byte[] bytes = "<p>".getBytes();
                final byte[] bytes2 = "</p>".getBytes();
                while (s != null) {
                    final String trim = s.trim();
                    if (trim.length() > 0) {
                        final String ct = ji.util.d.ct(trim);
                        ac.b(bytes);
                        ac.b(ct.getBytes());
                        ac.b(bytes2);
                    }
                    s = bufferedReader.readLine();
                }
                ac.b("</body></html>".getBytes());
            }
            bufferedReader.close();
        }
        if (ac != null) {
            ac.a(0L);
        }
        return ac;
    }
    
    String i() throws Exception {
        if (this.s == null) {
            final ni a = super.h.a("__substg1.0_007D".concat(String.valueOf(String.valueOf(this.t))));
            String s;
            if (this.t.equals("001E")) {
                s = this.a(a, (String)null);
            }
            else {
                s = this.a(a, true);
            }
            final BufferedReader bufferedReader = new BufferedReader(new StringReader(s));
            String upperCase = null;
            final char c = '\0';
            final char c2 = '\t';
            final char c3 = ' ';
            final Hashtable hashtable = new Hashtable<String, String>();
            final StringBuffer sb = new StringBuffer();
            try {
                String s2 = bufferedReader.readLine();
                while (s2 != null) {
                    s2 = ji.util.t.a(ji.util.t.a(s2, "<", "&lt;"), ">", "&gt;");
                    if (!"".equals(s2)) {
                        if (s2.charAt(0) == c2 || s2.charAt(0) == c || s2.charAt(0) == c3) {
                            if (upperCase == null) {
                                continue;
                            }
                            hashtable.put(upperCase, String.valueOf(String.valueOf(hashtable.get(upperCase))).concat(String.valueOf(String.valueOf(s2))));
                        }
                        else {
                            final int index = s2.indexOf(":");
                            if (index != -1) {
                                upperCase = s2.substring(0, index).toUpperCase();
                                hashtable.put(upperCase, s2.substring(index + 1));
                            }
                        }
                    }
                    s2 = bufferedReader.readLine();
                }
                for (int i = 0; i < nd.a.length; ++i) {
                    if (!hashtable.containsKey(nd.a[i]) || hashtable.get(nd.a[i]) == null) {
                        this.a(i, hashtable);
                    }
                }
                if (hashtable.containsKey("CONTENT-TYPE")) {
                    final String p = hashtable.get("CONTENT-TYPE");
                    this.p = p;
                    final StringTokenizer stringTokenizer = new StringTokenizer(p, ";= ");
                    while (stringTokenizer.hasMoreTokens()) {
                        if ("charset".equalsIgnoreCase(stringTokenizer.nextToken()) && stringTokenizer.hasMoreTokens()) {
                            this.q = stringTokenizer.nextToken();
                        }
                    }
                }
                sb.append("<table class=\"messageHeader\">");
                final boolean b = !hashtable.containsKey(nd.a[3]);
                for (int j = 0; j < nd.a.length; ++j) {
                    final String value = hashtable.get(nd.a[j]);
                    if (value != null) {
                        if (j == 0) {
                            sb.append("<tr><td class=\"messageHeaderTitleF\">").append(ji.res.s.a(nd.b[j], this.i)).append("</tr><td class=\"messageHeaderDataF\" >").append((Object)value).append("</td></tr>");
                        }
                        else if (j == nd.a.length - 1) {
                            sb.append("<tr><td class=\"messageHeaderTitleL\">").append(ji.res.s.a(nd.b[j], this.i)).append("</tr><td class=\"messageHeaderDataL\" >").append((Object)value).append("</td></tr>");
                        }
                        else if (b) {
                            sb.append("<tr><td class=\"messageHeaderTitleE\">").append(ji.res.s.a(nd.b[j], this.i)).append("</td><td class=\"messageHeaderDataE\" >").append((Object)value).append("</td></tr>");
                        }
                        else {
                            sb.append((j % 2 == 0) ? "<tr><td class=\"messageHeaderTitleE\">" : "<tr><td class=\"messageHeaderTitleO\">").append(ji.res.s.a(nd.b[j], this.i)).append((j % 2 == 0) ? "</td><td class=\"messageHeaderDataE\" >" : "</td><td class=\"messageHeaderDataO\" >").append((Object)value).append("</td></tr>");
                        }
                    }
                }
                sb.append("</table>");
            }
            catch (IOException ex) {
                ji.io.h.a(this.i, ex);
            }
            this.s = sb.toString();
        }
        return this.s;
    }
    
    private void a(final int n, final Hashtable hashtable) throws Exception {
        switch (n) {
            case 0: {
                this.a(hashtable, nd.a[n], nd.c, nd.d, super.h);
                break;
            }
            case 2: {
                this.a(hashtable, nd.a[n], "__substg1.0_0E04", true);
                break;
            }
            case 4: {
                this.a(hashtable, nd.a[n], nd.g, null, super.h);
                break;
            }
            case 3: {
                this.a(hashtable, nd.a[n], "__substg1.0_0E03", false);
                break;
            }
            case 1: {
                this.a(hashtable, nd.a[n], "__substg1.0_800B0014");
                break;
            }
        }
    }
    
    private void a(final Hashtable hashtable, final String s, final String[] array, final String[] array2, final ni ni) throws Exception {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.a(ni, array));
        if (array2 != null) {
            final String a = this.a(ni, array2);
            if (a.indexOf("@") != -1) {
                sb.append(a);
            }
        }
        hashtable.put(s, sb.toString());
    }
    
    private String a(final ni ni, final String[] array) throws Exception {
        String a = null;
        for (int i = 0; i < array.length; ++i) {
            final ni a2 = ni.a(String.valueOf(String.valueOf(array[i])).concat(String.valueOf(String.valueOf(this.t))));
            if (a2 != null) {
                a = this.a(a2, true);
                if (a != null) {
                    break;
                }
            }
        }
        if (a == null) {
            a = "";
        }
        return a;
    }
    
    private void a(final Hashtable hashtable, final String s, final String s2, final boolean b) throws Exception {
        final StringBuffer sb = new StringBuffer();
        final String a = this.a(super.h, s2, true);
        int n = 0;
        if (a != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(a, ";");
            while (stringTokenizer.hasMoreTokens()) {
                if (n != 0) {
                    sb.append("; ");
                }
                n = 1;
                final String nextToken = stringTokenizer.nextToken();
                sb.append(nextToken);
                sb.append(this.b(nextToken));
            }
        }
        if (sb.length() == 0) {
            if (b) {
                hashtable.put(s, sb.toString());
            }
        }
        else {
            hashtable.put(s, sb.toString());
        }
    }
    
    private String b(final String s) throws Exception {
        String a = null;
        this.k();
        for (int i = 0; i < this.l.length; ++i) {
            if (s.equals(this.a(this.l[i], nd.e))) {
                a = this.a(this.l[i], nd.f);
                break;
            }
        }
        if (a == null) {
            a = "";
        }
        else if (a.indexOf("@") == -1) {
            a = "";
        }
        return a;
    }
    
    private void a(final Hashtable hashtable, final String s, final String s2) throws Exception {
        hashtable.put(s, this.a(super.h, s2, true));
    }
    
    static {
        a = new String[] { "FROM", "DATE", "TO", "CC", "SUBJECT" };
        b = new int[] { 1315, 1317, 1316, 1318, 1319 };
        c = new String[] { "__substg1.0_0042", "__substg1.0_0C1A" };
        d = new String[] { "__subst1.0_0065", "__substg1.0_0C1F" };
        e = new String[] { "__substg1.0_3001", "__substg1.03A20", "__substg1.0_5FF6" };
        f = new String[] { "__substg1.0_3003", "__substg1.0_39FE" };
        g = new String[] { "__substg1.0_0037", "__substg1.0_0E1D", "__substg1.0_0070" };
        j = new gm[0];
    }
}
