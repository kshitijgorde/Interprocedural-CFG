// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import java.util.Enumeration;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import ji.io.a8;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;
import ji.util.i;
import ji.io.f6;
import ji.res.z;
import ji.v1event.af;
import ji.document.ad;
import ji.util.e;
import ji.io.q;
import java.util.StringTokenizer;
import ji.io.h;
import java.awt.Component;
import ji.document.bd;
import ji.util.d;
import ji.image.dx;
import java.awt.Color;
import java.util.Hashtable;
import ji.io.ac;
import ji.decode.ok;

public class ez extends cj
{
    public static final byte[] a;
    private ok b;
    private String c;
    private ac d;
    private String e;
    private String f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private boolean l;
    private Hashtable m;
    private String[] n;
    
    public ez() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 1;
        this.h = 5;
        this.i = 5;
        this.j = 5;
        this.k = 5;
        this.l = false;
        this.m = new Hashtable();
        this.n = new String[] { "doc", "tif", "tiff", "htm", "html", "image", "jp2", "jp", "djvu", "png", "bmp", "jpeg", "jpg", "pix", "db", "zip", "fob", "xls", "pdf" };
    }
    
    public final String getFilterName() {
        return "jiFilterText";
    }
    
    public void a(final String c) {
        this.c = c;
    }
    
    private final void c() {
        if (this.b == null) {
            this.b = new ok(this.c, 1.0, 1.0, 0, 0.0, this.g, null, true, false, false);
        }
    }
    
    public final dx loadImageHeaderInternal(final fh fh) throws Exception {
        ac ac = fh.b;
        this.c();
        boolean a = false;
        boolean b = false;
        final dx dx = new dx();
        dx.ay = false;
        try {
            final String lowerCase = fh.a.toString().toLowerCase();
            if (ji.util.d.b(ji.util.d.h(lowerCase, this.c), false, this.c)) {
                if (!ji.util.d.ag()) {
                    return null;
                }
                ac = this.a(ac, fh.o, this.c, lowerCase, fh.g);
                b = true;
            }
            final String m = fh.m;
            final String n = fh.n;
            String s = ji.util.d.c(fh.f, m, n);
            boolean b2 = false;
            if (n != null && n.toLowerCase().startsWith("text")) {
                b2 = true;
            }
            if (m != null && m.toLowerCase().startsWith("plain")) {
                b2 = true;
            }
            if (!b2) {
                final String bj = fh.o.bj(1);
                if (s != null) {
                    s = s.toLowerCase();
                    if (!this.a(m, s, bj)) {
                        return null;
                    }
                }
                if (m != null && !this.a(m.toLowerCase(), s, bj)) {
                    return null;
                }
            }
            if (!fh.o.bi(bd.b)) {
                a = this.b.a(ac);
            }
            try {
                if (!a && !fh.o.bi(bd.b)) {
                    return null;
                }
                dx.l = "Text";
                fh.h = Math.max(fh.h, 1);
                if (this.a(fh)) {
                    final String e = fh.e;
                    String s2 = fh.e;
                    if (s2 == null && fh.d != null) {
                        s2 = fh.d.f;
                    }
                    final aax a2 = this.a(s2, fh);
                    if (a2 == null) {
                        return null;
                    }
                    dx.u = a2.a();
                    dx.i = ac.v();
                    final String a3 = a2.a(fh.h);
                    if (a3 != null) {
                        final ac ac2 = new ac(a3, false, false, 0, false, fh.o, this.c);
                        this.b.a(this.j, this.k, this.h, this.i, 0);
                        this.b.a(dx, this.a(ac2, fh.o), fh.o, fh.g, fh.o.bl(0), fh.o.bl(1));
                        if (ac2 != null) {
                            ac2.a(fh.o);
                        }
                    }
                    else {
                        this.b.a(dx, this.a(ac, fh.o), fh.o, fh.g, false, 1, false);
                    }
                }
                else {
                    this.b.a(dx, this.a(ac, fh.o), fh.o, fh.g, false, 1, false);
                    dx.u = 1;
                    dx.i = ac.v();
                    dx.br = this.b.f();
                    dx.b1 = this.b.e();
                }
            }
            catch (Exception ex) {
                ji.util.d.b(ex);
                return null;
            }
            try {
                dx.bk = new Hashtable(2);
                if (!fh.i) {
                    dx.bk.put("Width", "".concat(String.valueOf(String.valueOf(dx.m))));
                    dx.bk.put("Height", "".concat(String.valueOf(String.valueOf(dx.n))));
                    dx.bk.put("Num Lines", "".concat(String.valueOf(String.valueOf(dx.b1))));
                    dx.bk.put("Max Line Width", "".concat(String.valueOf(String.valueOf(dx.br))));
                    if (this.b != null) {
                        dx.bk.put("Source Font", String.valueOf(String.valueOf(new StringBuffer("").append(this.b.a()).append(" ").append(this.b.c()).append(" - ").append(this.b.b()))));
                        dx.bk.put("Font size", "".concat(String.valueOf(String.valueOf(this.b.d()))));
                    }
                }
                if (this.g == 1) {
                    dx.z = 1;
                    dx.aa = 1;
                    dx.am = 1;
                }
                else {
                    dx.z = 8;
                    dx.aa = 3;
                    dx.am = 24;
                }
                dx.as = true;
                dx.an = 0;
                dx.ar = false;
                dx.at = 0;
            }
            catch (Exception ex2) {
                return null;
            }
            finally {
                this.a((Component)fh.o);
            }
        }
        finally {
            if (b) {
                ac.a(fh.o);
            }
        }
        return dx;
    }
    
    private boolean a(final fh fh) {
        boolean b = true;
        if (fh.o.bi(bd.b)) {
            final int bl = fh.o.bl(1);
            final int bl2 = fh.o.bl(0);
            if (bl < 0) {
                if (!this.l) {
                    ji.io.h.d(this.c, String.valueOf(String.valueOf(new StringBuffer("jiFilterText: Row and column wrap enabled but textFilterPageWidth has an invalid value (").append(bl).append("), ignoring"))));
                }
                b = false;
            }
            if (bl2 < 0) {
                if (!this.l) {
                    ji.io.h.d(this.c, String.valueOf(String.valueOf(new StringBuffer("jiFilterText: Row and column wrap enabled but textFilterPageHeight has an invalid value (").append(bl2).append("), ignoring"))));
                }
                b = false;
            }
            this.l = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    private aax a(final String s, final fh fh) {
        if (s != null) {
            synchronized (this.m) {
                final boolean b = false;
                if (this.m.containsKey(s)) {
                    final aax value = this.m.get(s);
                    if (value instanceof aax) {
                        // monitorexit(this.m)
                        return value;
                    }
                }
                if (b || fh == null) {
                    // monitorexit(this.m)
                    return null;
                }
                if (!this.b.a(fh.b)) {
                    // monitorexit(this.m)
                    return null;
                }
                final aax aax4 = new aax(fh, s);
                aax4.b();
                this.m.put(s, aax4);
                // monitorexit(this.m)
                return aax4;
            }
        }
        return null;
    }
    
    private boolean a(final String s, final String s2, final String s3) {
        boolean b = true;
        for (int i = 0; i < this.n.length; ++i) {
            if (s.equals(this.n[i].toLowerCase())) {
                b = false;
                if (!ji.util.d.by(s3)) {
                    int n = 0;
                    for (StringTokenizer stringTokenizer = new StringTokenizer(s3, ","); stringTokenizer.hasMoreTokens() && n == 0; n = 1) {
                        if (s2.equalsIgnoreCase(stringTokenizer.nextToken().trim())) {}
                    }
                    if (n != 0) {
                        b = true;
                        break;
                    }
                }
            }
        }
        return b;
    }
    
    private final ac a(final ac ac, final Component component) {
        try {
            if (this.e != null) {
                return this.d = new ac(this.e, false, false, 0, false, component, this.c);
            }
            if (ji.util.d.fc()) {
                this.e = q.a(component, this.c).n();
                this.d = new ac(this.e, true, false, 0, false, component, this.c);
                int i = (int)ac.v();
                final int min = Math.min(102400, i);
                final byte[] array = new byte[min];
                ac.a(0L);
                while (i > 0) {
                    final int a = ac.a(array, 0, min);
                    if (a < 0) {
                        break;
                    }
                    i -= a;
                    int n = 0;
                    for (int j = 0; j < a; ++j) {
                        if ((array[j] & 0xFF) < 32) {
                            final int n2 = j - n;
                            if (n2 > 0) {
                                this.d.b(array, n, n2);
                            }
                            final int n3 = array[j] & 0xFF;
                            String concat = null;
                            switch (n3) {
                                case 13: {
                                    if (j >= a) {
                                        concat = "CR";
                                        break;
                                    }
                                    if ((array[j + 1] & 0xFF) == 0xA) {
                                        concat = "CRLF";
                                        ++j;
                                        break;
                                    }
                                    concat = "CR";
                                    break;
                                }
                                case 10: {
                                    concat = "LF";
                                    break;
                                }
                                case 8: {
                                    concat = "TAB";
                                    break;
                                }
                                default: {
                                    concat = "".concat(String.valueOf(String.valueOf(n3)));
                                    break;
                                }
                            }
                            this.d.b(String.valueOf(String.valueOf(new StringBuffer("<").append(concat).append(">"))).getBytes());
                            n = j;
                        }
                    }
                    if (n >= a) {
                        continue;
                    }
                    this.d.b(array, n, a - n);
                }
                this.d.a(component);
                this.d = new ac(this.e, false, false, 0, false, component, this.c);
                return ac;
            }
            return ac;
        }
        catch (Exception ex) {
            return ac;
        }
    }
    
    private final void g() {
        try {
            if (this.e != null) {
                ac.c(this.e, this.c);
                this.e = null;
            }
        }
        catch (Exception ex) {}
    }
    
    private final void a(final Component component) {
        try {
            if (this.d != null) {
                this.d.a(component);
                this.d = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean e() {
        return false;
    }
    
    public final int[] getPalette(final ac ac, final dx dx, final String s) throws Exception {
        return null;
    }
    
    public final void fillDibInternal(final fh fh) throws Exception {
        super.c = true;
        try {
            super.b = false;
            boolean b = false;
            ac ac = fh.b;
            try {
                if (this.f != null) {
                    ac = this.a(ac, fh.o, this.c, null, fh.g);
                    b = true;
                }
                if (this.a(fh)) {
                    b = true;
                    String s = fh.e;
                    if (s == null && fh.d != null) {
                        s = fh.d.f;
                    }
                    final aax a = this.a(s, fh);
                    if (a != null) {
                        final String a2 = a.a(fh.h);
                        if (a2 != null) {
                            ac = new ac(a2, false, false, 0, false, fh.o, this.c);
                        }
                        if (ac != null) {
                            this.b.a(ac, fh.c, fh.d, fh.g, fh.o);
                        }
                    }
                    else {
                        ji.io.h.c(this.c, "jiTextFilter: Couldn't find wrapped pages");
                    }
                }
                else {
                    this.b.a(this.a(ac, fh.o), fh.c, fh.d, fh.g, fh.o);
                }
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
            }
            finally {
                if (b) {
                    ac.a(fh.o);
                }
            }
            this.a((Component)fh.o);
        }
        finally {
            super.c = false;
        }
        if (super.b) {
            fh.c.a(fh.o);
        }
    }
    
    public boolean isAborted(final dx dx, final String s) {
        return super.b;
    }
    
    public void clearAbort(final dx dx, final String s) {
        super.b = false;
    }
    
    public final void abort(final dx dx) {
        try {
            if (ji.util.e.ai()) {
                super.b = true;
                this.b.g();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void close(final dx dx, final ad ad) {
        if (this.b != null) {
            this.b.a(dx);
            this.b = null;
        }
        if (dx != null) {
            final aax a = this.a(dx.f, null);
            if (a != null) {
                a.c();
            }
        }
        this.h();
        this.g();
    }
    
    private final ac a(final ac ac, final ad ad, final String s, final String s2, final af af) throws Exception {
        final q a = q.a(ad, s);
        final boolean b = z.b();
        try {
            if (this.f == null) {
                z.b(false);
                this.f = a.n();
                final f6 f6 = new f6(ji.util.e.v != null, ji.util.e.v, s);
                f6.a(ad, af, true, s2, false);
                final String z = f6.z();
                final ac ac2 = new ac(this.f, true, false, 0, ad, s);
                ac2.b(z.getBytes());
                ac2.a(ad);
            }
        }
        finally {
            z.b(b);
        }
        return new ac(this.f, false, false, 0, ad, s);
    }
    
    private final void h() {
        try {
            if (this.f != null) {
                ac.c(this.f, this.c);
                this.f = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public String c(final String s) {
        return "Text";
    }
    
    public int isFileType(final ac ac, final String s, final String s2, final ad ad, final boolean b, final String s3, final String s4, final af af, final boolean b2) {
        if (b) {
            return 1;
        }
        return b(s2, s, ad, b2);
    }
    
    public static final boolean a(final String s, final String s2, final ad ad, final boolean b) {
        if (i.c(256)) {
            return s != null && (s.startsWith("txt") || s.startsWith("plain") || s.startsWith("text"));
        }
        return b(s, s2, ad, b) == 1;
    }
    
    private static final int b(final String s, final String s2, final ad ad, final boolean b) {
        if (!ji.util.d.cz(s2)) {
            return a(ad, s, b);
        }
        return b(ad, s2, b);
    }
    
    private static final int a(final ad ad, final String s, final boolean b) {
        if (!b) {
            return (s.startsWith("txt") || s.startsWith("plain") || s.startsWith("text")) ? 1 : 2;
        }
        final String bj = ad.bj(1);
        if (ji.util.d.by(bj)) {
            return (s.startsWith("txt") || s.startsWith("plain") || s.startsWith("text")) ? 1 : 0;
        }
        if (s == null) {
            return 2;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(bj, ",");
        while (stringTokenizer.hasMoreTokens()) {
            if (s.equalsIgnoreCase(stringTokenizer.nextToken().trim())) {
                return 1;
            }
        }
        return 0;
    }
    
    private static final int b(final ad ad, final String s, final boolean b) {
        if (ad.bi(5) || !ji.util.d.cz(s)) {
            return (s.endsWith("plain") || s.indexOf("text") != -1) ? 1 : 2;
        }
        if (s.indexOf("/") == -1) {
            h.c(ad.iu(), "jiTextFilter: Document MimeType cannot be determined: ".concat(String.valueOf(String.valueOf(s))));
            return 2;
        }
        String[] array;
        if (b && !ji.util.d.by(ad.bj(0))) {
            final StringTokenizer stringTokenizer = new StringTokenizer(ad.bj(0), ",");
            array = new String[stringTokenizer.countTokens() + 1];
            int n = 1;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                final int index = nextToken.indexOf(47);
                if (index == -1 || index == nextToken.length() - 1) {
                    h.c(ad.iu(), "jiTextFilter: Invalid Mimetype not used: ".concat(String.valueOf(String.valueOf(nextToken))));
                }
                else {
                    array[n] = nextToken;
                    ++n;
                }
            }
        }
        else {
            array = new String[] { null };
        }
        array[0] = "text/*";
        if (ji.util.d.a(s, array)) {
            return 1;
        }
        return 0;
    }
    
    public boolean a(final ad ad) {
        return !ad.bi(5);
    }
    
    static {
        a = "\r\n".getBytes();
    }
    
    private class aax
    {
        private fh a;
        private String b;
        private Hashtable c;
        private int d;
        private String e;
        private Component f;
        private boolean g;
        private boolean h;
        int i;
        
        public aax(final ez ez, final fh a, final String s) {
            this.c = new Hashtable();
            this.d = 0;
            this.g = false;
            this.h = false;
            this.i = 1;
            this.a = a;
            this.f = a.o;
            this.e = a.u;
            final ac a2 = ez.a(a.b, this.f);
            this.b = a2.a();
            try {
                a2.a(this.f);
            }
            catch (Exception ex) {}
        }
        
        public int a() {
            return this.i;
        }
        
        public synchronized void b() {
            if (!this.h) {
                boolean b = false;
                final int bl = this.a.o.bl(1);
                int bl2 = this.a.o.bl(0);
                if (bl < 0) {}
                if (bl2 < 0) {
                    bl2 = 0;
                }
                if (this.b != null && ac.d(this.b, this.e)) {
                    final BufferedReader bufferedReader = null;
                    final BufferedWriter bufferedWriter = null;
                    ac ac = null;
                    try {
                        ac = new ac(this.b, false, false, 0, false, this.f, this.e);
                        try {
                            int n = 1;
                            this.i = 0;
                            int n2 = 0;
                            long h = 0L;
                            final boolean b2 = false;
                            long n3 = 0L;
                            int n4 = 0;
                            int n5 = 1;
                            int n6 = 0;
                            final Vector d = new Vector();
                            final adu adu = new adu(ac);
                            String a;
                            while ((a = adu.a()) != null || (n4 == 0 && n6 == 0)) {
                                int j = adu.j ? 1 : 0;
                                if (a == null) {
                                    n4 = 1;
                                    n6 = 1;
                                }
                                if (a != null) {
                                    final String s = a;
                                    if (!b2) {}
                                    s.length();
                                }
                                final Vector b3 = adu.b();
                                if (b3 != null) {
                                    this.a(b3, d);
                                }
                                if (n5 != 0) {
                                    n5 = 0;
                                    h = adu.h;
                                }
                                if (n6 == 0) {
                                    ++n2;
                                }
                                if ((bl2 > 0 && n2 >= bl2) || j != 0 || n6 != 0) {
                                    synchronized (this.c) {
                                        final long n7 = h;
                                        long i = adu.i;
                                        if (n3 > 0) {
                                            i = n3;
                                            n3 = 0L;
                                        }
                                        final adv adv = new adv(n, n7, i);
                                        if (d != null && d.size() > 0) {
                                            adv.d = d;
                                        }
                                        this.c.put(new Integer(n), adv);
                                        if (j == 0) {
                                            h = i;
                                        }
                                        else {
                                            h = i + 1;
                                        }
                                        n5 = 1;
                                    }
                                    // monitorexit(this.c)
                                    j = 0;
                                    boolean b4 = true;
                                    if (n6 != 0 && n2 == 0) {
                                        b4 = false;
                                    }
                                    if (b4) {
                                        ++n;
                                        ++this.i;
                                    }
                                    n2 = 0;
                                }
                                if (!b2 && j == 0) {
                                    continue;
                                }
                            }
                            if (this.i == 0) {
                                this.i = 1;
                            }
                            b = true;
                        }
                        catch (IOException ex) {
                            ji.util.d.a(ex);
                            b = false;
                        }
                        catch (Exception ex2) {
                            ji.util.d.a(ex2);
                            b = false;
                        }
                    }
                    catch (Exception ex3) {
                        ji.util.d.a(ex3);
                        b = false;
                    }
                    finally {
                        try {
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                        }
                        catch (Exception ex4) {}
                        try {
                            if (bufferedWriter != null) {
                                bufferedWriter.close();
                            }
                        }
                        catch (Exception ex5) {}
                        try {
                            if (ac != null) {
                                ac.a(this.f);
                            }
                        }
                        catch (Exception ex6) {}
                    }
                }
                if (b) {
                    this.h = true;
                }
            }
        }
        
        private void a(final Vector vector, final Vector vector2) {
            if (vector != null && vector2 != null) {
                vector2.ensureCapacity(vector2.size() + vector.size() * 2);
                for (int i = 0; i < vector.size(); ++i) {
                    if (!vector2.contains(vector.elementAt(i))) {
                        vector2.addElement(vector.elementAt(i));
                    }
                }
            }
        }
        
        private synchronized boolean d() {
            return this.g;
        }
        
        public String a(final int n) {
            if (!this.d()) {
                synchronized (this.c) {
                    final adv value = this.c.get(new Integer(n));
                    if (value instanceof adv) {
                        if (value.c == null) {
                            final long a = value.a;
                            final long b = value.b;
                            final Vector d = value.d;
                            try {
                                final String n2 = q.a(this.f, this.e).n();
                                final ac ac = new ac(n2, true, false, 0, false, this.f, this.e);
                                final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new a8(ac, this.f));
                                final ac ac2 = new ac(this.b, false, false, 0, false, this.f, this.e);
                                ac2.a(a);
                                final int n3 = 1024;
                                final byte[] array = new byte[n3];
                                long n4 = a;
                                if (d == null) {
                                    while (n4 < b) {
                                        final int n5 = (int)Math.min(n3, b - n4);
                                        ac2.a(array, 0, n5);
                                        bufferedOutputStream.write(array, 0, n5);
                                        n4 += n3;
                                    }
                                }
                                else {
                                    long n6 = 0L;
                                    int n7 = 0;
                                    while (n4 < b) {
                                        final int n8 = (int)Math.min(n3, b - n4);
                                        n6 += ac2.a(array, 0, n8);
                                        long n9 = d.elementAt(n7);
                                        long n10 = 0L;
                                        int n11 = n8;
                                        while (n9 < n6 + n4) {
                                            final long n12 = n9 - n4;
                                            if (n12 <= 0) {
                                                if (++n7 >= d.size()) {
                                                    break;
                                                }
                                                n9 = d.elementAt(n7);
                                            }
                                            else {
                                                if (n10 > array.length) {
                                                    break;
                                                }
                                                final int n13 = (int)n10;
                                                final int min = Math.min((int)(n12 - n10), array.length - (int)n10);
                                                if (min < 1) {
                                                    break;
                                                }
                                                bufferedOutputStream.write(array, n13, min);
                                                bufferedOutputStream.flush();
                                                n11 -= (int)(n12 - n10);
                                                bufferedOutputStream.write(ez.a, 0, ez.a.length);
                                                n10 = n12;
                                                if (++n7 >= d.size()) {
                                                    break;
                                                }
                                                n9 = d.elementAt(n7);
                                            }
                                        }
                                        bufferedOutputStream.write(array, (int)n10, Math.min(n11, array.length - (int)n10));
                                        bufferedOutputStream.flush();
                                        n4 += n3;
                                    }
                                }
                                if (bufferedOutputStream != null) {
                                    bufferedOutputStream.close();
                                }
                                if (ac != null) {
                                    ac.a(this.f);
                                }
                                if (ac2 != null) {
                                    ac2.a(this.f);
                                }
                                value.c = n2;
                            }
                            catch (Exception ex) {
                                ji.util.d.a(ex);
                            }
                        }
                        // monitorexit(this.c)
                        return value.c;
                    }
                }
                // monitorexit(this.c)
            }
            return null;
        }
        
        public synchronized void c() {
            if (this.c != null) {
                synchronized (this.c) {
                    try {
                        this.g = true;
                        final Enumeration<Object> keys = this.c.keys();
                        while (keys.hasMoreElements()) {
                            final Object value = this.c.get(keys.nextElement());
                            if (value instanceof adv) {
                                final adv adv = (adv)value;
                                try {
                                    final String c = adv.c;
                                    if (c != null) {
                                        ac.c(c, this.e);
                                    }
                                    adv.a();
                                }
                                catch (Exception ex) {
                                    ji.util.d.a(ex);
                                }
                            }
                        }
                        this.c.clear();
                        this.c = null;
                    }
                    finally {}
                }
                // monitorexit(this.c)
            }
            this.a = null;
        }
        
        private class adu
        {
            ac a;
            Vector b;
            int c;
            int d;
            int e;
            long f;
            long g;
            long h;
            long i;
            boolean j;
            boolean k;
            int l;
            int m;
            boolean n;
            Vector o;
            
            public adu(final aax aax, final ac a) {
                this.b = new Vector();
                this.c = 0;
                this.d = 0;
                this.e = 0;
                this.f = 0L;
                this.g = 0L;
                this.j = false;
                this.k = false;
                this.l = 0;
                this.m = 0;
                this.n = false;
                this.o = null;
                this.a = a;
                this.m = aax.a.o.bl(1);
                this.n = aax.a.o.bi(bd.c);
            }
            
            public String a() throws Exception {
                String s = null;
                if (this.b.size() > 0) {
                    final aey aey = this.b.remove(0);
                    this.g = aey.a;
                    this.h = aey.a;
                    this.i = aey.b;
                    s = aey.c;
                    this.j = aey.d;
                    this.o = aey.e;
                }
                else {
                    this.b.removeAllElements();
                    this.j = false;
                    this.g = this.a.r();
                    final String m = this.a.m();
                    if (m != null) {
                        int index = -1;
                        int n = 0;
                        if (this.n) {
                            while ((index = m.indexOf(12, index + 1)) > -1) {
                                this.d = ((n < 0) ? 0 : n);
                                this.e = ((index < 0) ? m.length() : index);
                                final String substring = m.substring(this.d, this.e);
                                this.o = this.a(this.g + this.d, substring);
                                if (this.o != null) {
                                    this.a(this.g + this.d, substring, this.o, this.b, true);
                                }
                                else {
                                    this.b.addElement(new aey(this.g + this.d, this.g + this.e, substring, true, this.o));
                                }
                                n = index + 1;
                            }
                        }
                        final String substring2 = m.substring(n, m.length());
                        this.o = this.a(this.g + n, substring2);
                        if (this.o != null) {
                            this.a(this.g + n, substring2, this.o, this.b, false);
                        }
                        else {
                            this.b.addElement(new aey(this.g + n, this.g + m.length(), substring2, false, this.o));
                        }
                        this.o = null;
                        final aey aey2 = this.b.remove(0);
                        if (aey2 != null) {
                            this.g = aey2.a;
                            this.h = aey2.a;
                            this.i = aey2.b;
                            this.j = aey2.d;
                            s = aey2.c;
                        }
                    }
                }
                return s;
            }
            
            private void a(final long n, final String s, final Vector vector, final Vector vector2, final boolean b) {
                if (vector != null) {
                    int n2 = 0;
                    for (int i = 0; i < vector.size(); ++i) {
                        final int n3 = (int)(vector.elementAt(i) - n);
                        vector2.addElement(new aey(n + n2, n + n3, s.substring(n2, n3), false, vector));
                        n2 = n3 + 1;
                    }
                    final int length = s.length();
                    vector2.addElement(new aey(n + n2, n + length, s.substring(n2, length), b, vector));
                }
            }
            
            private Vector a(final long n, final String s) {
                Vector<Long> vector = null;
                int n2 = 0;
                if (s != null) {
                    final int length = s.length();
                    if (this.m == 0 || length < this.m) {
                        return null;
                    }
                    int e = 0;
                    do {
                        if (this.m > 0) {
                            final int min = Math.min(this.m, length - e);
                            if (min <= 0) {
                                if (length == 0) {
                                    ++n2;
                                    break;
                                }
                                break;
                            }
                            else {
                                if (length - e > this.m) {
                                    if (vector == null) {
                                        vector = new Vector<Long>(100);
                                    }
                                    vector.addElement(new Long(n + e + this.m));
                                }
                                ++n2;
                                e += min;
                                this.e = e;
                            }
                        }
                    } while (length - e >= 0);
                }
                return vector;
            }
            
            public Vector b() {
                return this.o;
            }
            
            private class aey
            {
                public long a;
                public long b;
                public String c;
                public boolean d;
                public Vector e;
                
                public aey(final adu adu, final long a, final long b, final String c, final boolean d, final Vector e) {
                    this.a = a;
                    this.b = b;
                    this.c = c;
                    this.d = d;
                    this.e = e;
                }
            }
        }
        
        private class adv
        {
            private long a;
            private long b;
            public String c;
            public Vector d;
            
            public adv(final aax aax, final int n, final long a, final long b) {
                this.a = a;
                this.b = b;
            }
            
            public void a() {
                if (this.d != null) {
                    this.d.removeAllElements();
                }
                this.d = null;
            }
        }
    }
}
