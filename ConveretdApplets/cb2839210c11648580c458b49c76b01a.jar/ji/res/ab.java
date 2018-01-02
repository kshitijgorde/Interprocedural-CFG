// 
// Decompiled by Procyon v0.5.30
// 

package ji.res;

import java.util.StringTokenizer;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import ji.util.b6;
import ji.util.y;
import ji.v1event.af;
import ji.awt.ax;
import ji.io.h;
import ji.util.d;
import ji.io.ac;
import ji.awt.c;

public class ab
{
    boolean a;
    c b;
    byte[] c;
    int[] d;
    int[] e;
    String[] f;
    boolean[] g;
    String h;
    long i;
    String j;
    String k;
    String l;
    String m;
    boolean n;
    String o;
    int p;
    String q;
    String r;
    ac s;
    String t;
    long u;
    String v;
    
    public ab(final String k, final String r) {
        this.a = false;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = 0L;
        this.j = null;
        this.k = null;
        this.l = "Messages";
        this.m = null;
        this.n = false;
        this.o = null;
        this.p = 0;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = -1L;
        this.v = null;
        this.k = k;
        this.r = r;
    }
    
    public final String a() {
        return this.v;
    }
    
    public final String a(final String s) {
        String s2 = "";
        if (s != null) {
            if (s.length() <= 0) {
                return "";
            }
            final int c = ji.util.d.c(s, -1);
            if (this.n && c >= 0) {
                return this.a(c);
            }
            if (this.n) {
                ji.io.h.d(this.k, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.r))).append(": ").append("Localization error").append(" #11, can't find message with key \"").append(s).append("\", wrong method used!"))));
                ji.util.d.eu();
                return "Localization error #11!";
            }
            try {
                final Object d = this.b.d(s.toLowerCase());
                if (d != null) {
                    s2 = (String)d;
                }
            }
            catch (Exception ex) {}
        }
        return s2;
    }
    
    private final String a(final int n, String s) {
        if (n == 965) {
            if (s != null) {
                s = ji.util.d.b(s, "2004", "".concat(String.valueOf(String.valueOf(ji.util.d.e3()))));
            }
        }
        else if (n == 75 && s != null) {
            s = ji.util.d.b(s, "2003", "".concat(String.valueOf(String.valueOf(ji.util.d.e3()))));
        }
        return s;
    }
    
    public final String a(final int n) {
        if (!ji.util.d.c6) {
            ji.io.h.d(this.k, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.r))).append(": ").append("Localization error").append(" #6! (index = ").append(n).append("/").append(this.n).append("/").append(ji.util.d.c6).append(")"))));
            return "Localization error #6";
        }
        if (n < 0) {
            return "";
        }
        try {
            if (this.f != null) {
                if (this.f.length <= n) {
                    ji.io.h.d(this.k, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.r))).append(": ").append("Localization error").append(" #6A! (index = ").append(n).append(")"))));
                    ji.util.d.eu();
                    return "Localization error #6A";
                }
                if (this.f[n] != null) {
                    return this.a(n, this.f[n]);
                }
            }
            this.a(this.t, this.u);
            if (this.s != null) {
                int min = Math.min(n + 5, this.d.length - 1);
                try {
                    while (this.f[min] != null) {
                        --min;
                    }
                }
                catch (Exception ex) {}
                final int max = Math.max(n - 1, 0);
                int n2 = 0;
                try {
                    for (int i = max; i <= min; ++i) {
                        n2 += this.e[i];
                    }
                }
                catch (Exception ex2) {}
                final byte[] array = new byte[n2];
                this.s.a((long)this.d[max]);
                this.s.a(array);
                int n3 = 0;
                for (int j = max; j < min; ++j) {
                    if (this.f[j + 1] == null && this.e[j] > 0) {
                        this.f[j + 1] = new String(array, n3, this.e[j], this.v);
                        n3 += this.e[j];
                    }
                }
                this.u = this.s.r();
                return this.a(n, this.f[n]);
            }
        }
        catch (Exception ex3) {}
        if (n == 232) {
            return this.a(n, "       OK       ");
        }
        ji.io.h.d(this.k, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.r))).append(": ").append("Localization error").append(" #4 (index = ").append(n).append(")"))));
        ji.util.d.eu();
        return String.valueOf(String.valueOf(new StringBuffer("Localization error #4 (index = ").append(n).append(")")));
    }
    
    public final long b() {
        long n = 0L;
        try {
            if (this.n) {
                ji.io.h.d(this.k, "**** ERROR: jiMessages: Method getHashOfMessages not support for quick-Lookup mode!");
            }
            else {
                for (int b = this.b.b(), i = 0; i < b; ++i) {
                    final Object b2 = this.b.b(i);
                    if (b2 != null) {
                        final char[] charArray = ((String)b2).toCharArray();
                        for (int length = charArray.length, j = 0; j < length; ++j) {
                            n += charArray[j];
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
        return n;
    }
    
    public final String c() {
        String s = null;
        try {
            if (this.n) {
                ji.io.h.d(this.k, "**** ERROR: jiMessages: Method getMessages not support for quick-Lookup mode!");
            }
            else {
                for (int b = this.b.b(), i = 0; i < b; ++i) {
                    final Object b2 = this.b.b(i);
                    final Object c = this.b.c(i);
                    if (b2 != null) {
                        if (s != null) {
                            s = String.valueOf(String.valueOf(s)).concat("\n");
                        }
                        else {
                            s = "";
                        }
                        s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("{ \"").append((String)c).append("\", \"").append((String)b2).append("\" }"))))));
                    }
                }
            }
        }
        catch (Exception ex) {}
        return s;
    }
    
    public final void a(final String s, final String s2) {
        try {
            if (this.n) {
                ji.io.h.d(this.k, "**** ERROR: jiMessages: Method setElement not support for quick-Lookup mode!");
            }
            else if (s != null) {
                if (this.b == null) {
                    this.b = new c(String.valueOf(String.valueOf(this.l)).concat("-messages1a"), 1);
                }
                final int d = this.d(s);
                if (d >= 0) {
                    this.b.a((Object)s2, d);
                }
                else {
                    this.b.a(s.toLowerCase(), s2);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void b(final String s) {
        try {
            if (this.n) {
                ji.io.h.d(this.k, "**** ERROR: jiMessages: Method setElement not support for quick-Lookup mode!");
            }
            else if (s != null && this.b != null) {
                this.b.a(s.toLowerCase());
            }
        }
        catch (Exception ex) {}
    }
    
    private final int d(final String s) {
        if (s != null) {
            try {
                if (this.n) {
                    ji.io.h.d(this.k, "**** ERROR: jiMessages: Method getKeyIndex not support for quick-Lookup mode!");
                }
                else if (this.b != null) {
                    final int b = this.b.b();
                    final String lowerCase = s.toLowerCase();
                    for (int i = 0; i < b; ++i) {
                        final Object c = this.b.c(i);
                        if (c != null && ((String)c).toLowerCase().equals(lowerCase)) {
                            return i;
                        }
                    }
                    return -1;
                }
            }
            catch (Exception ex) {}
        }
        return -1;
    }
    
    public final String[] c(final String s) {
        final c c = new c("jiMessages1-Match");
        String[] array = null;
        if (s != null) {
            try {
                if (this.n) {
                    ji.io.h.d(this.k, "**** ERROR: jiMessages: Method matchMessages not support for quick-Lookup mode!");
                }
                else {
                    final String lowerCase = s.toLowerCase();
                    final ax d = this.b.d();
                    while (d.a()) {
                        final String b = d.b();
                        if (b != null) {
                            final String s2 = b;
                            if (!s2.toLowerCase().startsWith(lowerCase)) {
                                continue;
                            }
                            c.c(this.a(s2));
                        }
                    }
                }
            }
            catch (Exception ex) {}
        }
        final int b2 = c.b();
        if (b2 > 0) {
            array = new String[b2];
            for (int i = 0; i < b2; ++i) {
                array[i] = (String)c.b(b2 - i - 1);
            }
        }
        return array;
    }
    
    public final void d() {
        try {
            if (this.n) {
                ji.io.h.d(this.k, "**** ERROR: jiMessages: Method reset not support for quick-Lookup mode!");
            }
            else if (this.a) {
                this.a = false;
                this.i();
            }
        }
        catch (Exception ex) {}
    }
    
    public final String a(final String j, final String s, final String s2, final boolean b, final af af, final String o, final boolean b2, final int n) {
        String h = null;
        if (!this.a) {
            this.o = o;
            try {
                this.n = (b2 && ji.util.d.c6);
                boolean b3 = false;
                this.j = j;
                this.a = true;
                byte[] array = null;
                if (b2 && ji.util.d.c6) {
                    if (ji.util.d.by(z.a(this.k))) {
                        final String c = ji.util.d.c(ji.util.d.w(this.k), this.k);
                        if (c != null) {
                            ji.util.d.g7 = c;
                        }
                        z.c(this.k);
                    }
                    if (!ji.util.d.by(z.a(this.k))) {
                        this.q = this.e(s);
                        if (!ji.util.d.by(this.q) && ac.d(this.q, this.k)) {
                            h = this.a(this.q, array, o, true);
                            b3 = true;
                        }
                    }
                }
                if (!b3) {
                    if (ji.util.d.dr()) {
                        ji.io.h.d(this.k, String.valueOf(String.valueOf(new StringBuffer("Loading ").append(j).append("..."))));
                    }
                    boolean b4 = false;
                    int n2 = 1;
                    do {
                        final boolean b5 = b4;
                        if (b) {
                            try {
                                final byte[] a = z.a(j, s2, 3, af, null, this.k, true, new y(), b5);
                                if (a == null) {
                                    array = z.a(s, s2, 3, af, null, this.k, true, new y(), b5);
                                }
                                else {
                                    array = ji.util.d.a(a, s, ji.util.d.t, new y(), this.k);
                                }
                            }
                            catch (b6 b6) {
                                b4 = true;
                            }
                        }
                        else {
                            array = z.a(s, s2, 3, af, null, this.k, true, new y(), b5);
                        }
                    } while (n2-- > 0);
                    if (ji.util.d.dr()) {
                        ji.io.h.d(this.k, String.valueOf(String.valueOf(new StringBuffer("Loaded byte data ").append(j).append("..."))));
                    }
                    h = ji.util.d.es();
                    if (h == null) {
                        this.i = ji.util.d.b8();
                        h = this.a(this.q, array, ji.util.d.ev());
                        if (this.b == null && this.f == null) {
                            h = String.valueOf(String.valueOf(new StringBuffer("Corrupt message file #1 ! (").append(j).append(")")));
                        }
                        else if (this.b != null && this.b.b() < n) {
                            h = String.valueOf(String.valueOf(new StringBuffer("Corrupt or empty message file! (").append(j).append(")")));
                            ji.io.h.d(this.k, h);
                            if (!ji.util.d.by(z.a(this.k))) {
                                ji.io.h.d(this.k, "Clearing repository as a precaution (to clear potential corrupt files).");
                                z.a(z.a(this.k), this.k);
                            }
                            ji.util.d.c7 = true;
                        }
                        if (ji.util.d.by(h) && this.b != null) {
                            this.b.f();
                            if (ji.util.d.dr()) {
                                ji.io.h.d(this.k, String.valueOf(String.valueOf(new StringBuffer("Loaded ").append(j).append("."))));
                            }
                        }
                    }
                    else if (b) {
                        h = ji.util.d.b(h, s, j);
                    }
                }
            }
            catch (Exception ex) {
                ji.util.d.b(ex);
                h = String.valueOf(String.valueOf(new StringBuffer("'").append(j).append("' message file failed (").append(ex).append(")")));
            }
        }
        return this.h = h;
    }
    
    public final void a(final boolean n) {
        this.n = n;
    }
    
    public final long e() {
        return this.i;
    }
    
    public final String a(final String s, final byte[] array, final String s2) throws Exception {
        if (this.n && ji.util.d.c6) {
            return this.a(s, array, s2, false);
        }
        return this.a(array, s2);
    }
    
    private final int a(final byte[] array) {
        return (array[this.p++] & 0xFF) << 24 | (array[this.p++] & 0xFF) << 16 | (array[this.p++] & 0xFF) << 8 | (array[this.p++] & 0xFF);
    }
    
    private final void a(final String s, final long n) throws Exception {
        boolean b = false;
        if (s != null) {
            if (this.s == null) {
                b = true;
            }
            else if (!this.s.aa()) {
                b = true;
            }
            if (b) {
                this.s = new ac(s, false, false, 0, false, ji.util.d.w(this.k), this.k);
            }
            if (this.s != null && n >= 0) {
                this.s.a(n);
            }
        }
    }
    
    public final String a(final String t, byte[] c, String ev, final boolean b) throws Exception {
        final String s = null;
        try {
            if (b) {
                if (ji.util.d.dr()) {
                    ji.io.h.d(this.k, String.valueOf(String.valueOf(new StringBuffer("Loading quick message file ").append(t).append("..."))));
                }
                if (ev == null) {
                    ev = ji.util.d.ev();
                }
                this.a(t, 0L);
                this.t = t;
                this.v = ev;
                final int p4 = this.s.p();
                if (p4 >= 16 || p4 <= 0) {
                    return "Corrupt message file #2 (missing marker)!";
                }
                final byte[] array = new byte[p4];
                this.s.a(array);
                if (!new String(array, "UTF8").toUpperCase().equals("QUICKLOOKUP")) {
                    return "Corrupt message file #2 (incorrect marker)!";
                }
                ev = "UTF8";
                final int p5 = this.s.p();
                c = new byte[p5];
                this.s.a(c, 4, p5 - 4);
                this.p += 4;
                this.u = this.s.r();
            }
            else {
                if (ji.util.d.dr()) {
                    ji.io.h.d(this.k, "Loading whole quick message file...");
                }
                final int a = this.a(c);
                if (a >= 16 || a <= 0) {
                    return "Corrupt message file #2 (missing marker)!";
                }
                final byte[] array2 = new byte[a];
                System.arraycopy(c, this.p, array2, 0, a);
                this.p += a;
                if (!new String(array2, "UTF8").toUpperCase().equals("QUICKLOOKUP")) {
                    return "Corrupt message file #2 (incorrect marker)!";
                }
                ev = "UTF8";
                this.a(c);
            }
            this.v = ev;
            final int a2 = this.a(c);
            if (a2 <= 0 || a2 > 65535) {
                return "Corrupt message file #3 !";
            }
            this.c = c;
            this.f = new String[a2 + 1];
            this.g = new boolean[a2 + 1];
            this.d = new int[a2 + 1];
            this.e = new int[a2 + 1];
            for (int i = 0; i < a2; ++i) {
                this.d[i] = this.a(c);
                this.e[i] = this.a(c);
            }
            if (!b) {
                for (int j = 0; j < a2; ++j) {
                    this.f[j + 1] = new String(c, this.d[j], this.e[j], this.v);
                }
                if (ji.util.d.dr()) {
                    ji.io.h.d(this.k, "Loaded quick message file.");
                }
                if (!ji.util.d.by(t)) {
                    if (ji.util.d.dr()) {
                        ji.io.h.d(this.k, "Saving message file as ".concat(String.valueOf(String.valueOf(t))));
                    }
                    final Object w = ji.util.d.w(this.k);
                    final ac ac = new ac(t, true, true, 20480, false, w, this.k);
                    ac.b(this.c);
                    ac.a(w);
                    if (ji.util.d.dr()) {
                        ji.io.h.d(this.k, "Saved message file.");
                    }
                }
            }
            else if (ji.util.d.dr()) {
                ji.io.h.d(this.k, "Loaded quick message file.");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return s;
    }
    
    private final String e(final String s) {
        final String a = z.a(this.k);
        if (!ji.util.d.by(a) && !ji.util.d.by(s)) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a))).append("/").append(ji.util.d.b(s, ".".concat(String.valueOf(String.valueOf(ji.util.d.bh(s)))), ".dat"))));
        }
        return null;
    }
    
    private final String a(final byte[] array, String bm) throws Exception {
        String s = null;
        String s2 = null;
        Object o = null;
        final String s3 = new String(new char[] { '\"' });
        if (array != null) {
            String s4 = null;
            int i = 1;
            int n = 0;
            while (i != 0) {
                try {
                    ++n;
                    Label_0177: {
                        if (bm != null) {
                            try {
                                final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
                                final InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream);
                                s4 = new String(array, bm);
                                inputStreamReader.close();
                                byteArrayInputStream.close();
                                break Label_0177;
                            }
                            catch (IOException ex2) {
                                try {
                                    s4 = new String(array, "Cp1252");
                                }
                                catch (UnsupportedEncodingException ex3) {
                                    bm = null;
                                    s4 = new String(array);
                                    ji.util.d.av(true);
                                }
                            }
                        }
                        try {
                            s4 = new String(array, "Cp1252");
                        }
                        catch (UnsupportedEncodingException ex4) {
                            bm = null;
                            s4 = new String(array);
                            ji.util.d.av(true);
                        }
                    }
                    i = 0;
                    if (n <= 1) {
                        continue;
                    }
                    ji.util.d.bv(bm);
                    ji.io.h.d(this.k, "Default encoding OK");
                }
                catch (Exception ex) {
                    ji.io.h.d(this.k, "Encoding failed: ".concat(String.valueOf(String.valueOf(bm))));
                    if (n > 1) {
                        throw ex;
                    }
                    bm = ji.util.d.bm(this.k);
                    ji.io.h.d(this.k, "Trying default encoding: ".concat(String.valueOf(String.valueOf(bm))));
                }
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(s4, "{");
            final int countTokens = stringTokenizer.countTokens();
            if (countTokens < 1) {
                s = "No entries in message text file";
            }
            else {
                this.b = new c(String.valueOf(String.valueOf(this.l)).concat("-messages2"), countTokens);
                String s5 = stringTokenizer.nextToken();
                int j = 0;
                while (j < countTokens) {
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(s5, s3);
                    if (stringTokenizer2 != null) {
                        int k = 0;
                        final int countTokens2 = stringTokenizer2.countTokens();
                        if (countTokens2 >= 5) {
                            while (k < countTokens2) {
                                final String nextToken = stringTokenizer2.nextToken();
                                if (k == 1) {
                                    s2 = nextToken;
                                }
                                else if (k == 3) {
                                    o = nextToken;
                                    break;
                                }
                                ++k;
                            }
                            if (s2 != null && o != null) {
                                try {
                                    this.b.a(s2.toLowerCase(), o);
                                }
                                catch (Exception ex5) {
                                    try {
                                        this.b.a(s2.toLowerCase());
                                        this.b.a(s2.toLowerCase(), o);
                                    }
                                    catch (Exception ex6) {}
                                }
                            }
                        }
                    }
                    if (++j < countTokens) {
                        s5 = stringTokenizer.nextToken();
                    }
                }
            }
        }
        else {
            s = "Message text read failed 2 (message file corrupt)";
        }
        return s;
    }
    
    public final void f() {
        this.i();
    }
    
    public final int g() {
        try {
            if (this.n) {
                return this.f.length;
            }
            return this.b.b();
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public final void h() {
        try {
            if (this.s != null) {
                this.s.a((Object)this.k);
                this.s = null;
            }
        }
        catch (Exception ex) {}
    }
    
    private final void i() {
        try {
            if (this.s != null) {
                this.u = -1L;
                this.s.a((Object)this.k);
                this.s = null;
            }
        }
        catch (Exception ex) {}
        try {
            if (this.b != null) {
                while (this.b.b() > 0) {
                    this.b.c();
                }
            }
        }
        catch (Exception ex2) {}
        this.c = null;
        try {
            if (this.f != null) {
                for (int length = this.f.length, i = 0; i < length; ++i) {
                    this.f[i] = null;
                }
                this.f = null;
            }
        }
        catch (Exception ex3) {}
        this.g = null;
        this.d = null;
        this.e = null;
    }
}
