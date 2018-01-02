// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.net.URL;
import java.util.BitSet;
import java.util.Hashtable;

public final class bf
{
    private static Hashtable b;
    private static Hashtable c;
    private static Hashtable d;
    private static BitSet e;
    private static BitSet f;
    private static BitSet g;
    private static BitSet h;
    private static BitSet i;
    private static BitSet j;
    private static BitSet k;
    private static BitSet l;
    private static BitSet m;
    private static BitSet n;
    private static BitSet o;
    private static BitSet p;
    private static BitSet q;
    private static BitSet r;
    private static BitSet s;
    private static BitSet t;
    public static final BitSet a;
    private static BitSet u;
    private static BitSet v;
    private int w;
    private String x;
    private String y;
    private String z;
    private String A;
    private int B;
    private String C;
    private String D;
    private String E;
    private int F;
    private static final char[] G;
    
    public bf(final bf bf, final String s) {
        this.B = -1;
        this.F = -1;
        char[] charArray;
        int n;
        int length;
        for (charArray = s.toCharArray(), n = 0, length = charArray.length; n < length && Character.isWhitespace(charArray[n]); ++n) {}
        while (length > 0 && Character.isWhitespace(charArray[length - 1])) {
            --length;
        }
        if (n < length - 3 && charArray[n + 3] == ':' && (charArray[n] == 'u' || charArray[n] == 'U') && (charArray[n + 1] == 'r' || charArray[n + 1] == 'R') && (charArray[n + 2] == 'i' || charArray[n + 2] == 'I' || charArray[n + 2] == 'l' || charArray[n + 2] == 'L')) {
            n += 4;
        }
        int n2;
        for (n2 = n; n2 < length && charArray[n2] != ':' && charArray[n2] != '/' && charArray[n2] != '?' && charArray[n2] != '#'; ++n2) {}
        if (n2 < length && charArray[n2] == ':') {
            this.x = s.substring(n, n2).trim().toLowerCase();
            n = n2 + 1;
        }
        String s2 = this.x;
        if (this.x == null) {
            if (bf == null) {
                throw new dh("No scheme found");
            }
            s2 = bf.x;
        }
        this.w = (d(s2) ? 2 : bf.d.containsKey(s2.trim().toLowerCase()));
        if (this.w != 0) {
            if (n + 1 < length && charArray[n] == '/' && charArray[n + 1] == '/') {
                n += 2;
                int n3;
                for (n3 = n; n3 < length && charArray[n3] != '/' && charArray[n3] != '?' && charArray[n3] != '#'; ++n3) {}
                this.a(s.substring(n, n3), s2);
                n = n3;
            }
            if (this.w == 1) {
                this.C = a(s.substring(n), bf.i, true);
                if (this.C.length() > 0 && this.C.charAt(0) != '/') {
                    this.C = '/' + this.C;
                }
            }
            else {
                int n4;
                for (n4 = n; n4 < length && charArray[n4] != '?' && charArray[n4] != '#'; ++n4) {}
                this.C = a(s.substring(n, n4), bf.a, true);
                int n5;
                if ((n5 = n4) < length && charArray[n5] == '?') {
                    int n6;
                    for (n6 = ++n5; n6 < length && charArray[n6] != '#'; ++n6) {}
                    this.D = a(s.substring(n5, n6), bf.u, true);
                    n5 = n6;
                }
                if (n5 < length && charArray[n5] == '#') {
                    this.E = a(s.substring(n5 + 1, length), bf.v, true);
                }
            }
            if (bf != null) {
                if (this.x != null && !this.x.equals(bf.x)) {
                    return;
                }
                this.x = bf.x;
                if (this.A != null) {
                    return;
                }
                this.z = bf.z;
                this.A = bf.A;
                this.B = bf.B;
                if (this.w == 1) {
                    return;
                }
                if (this.C.length() == 0 && this.D == null) {
                    this.C = bf.C;
                    this.D = bf.D;
                    return;
                }
                if (this.C.length() == 0 || this.C.charAt(0) != '/') {
                    final int n7;
                    if ((n7 = ((bf.C != null) ? bf.C.lastIndexOf(47) : -1)) < 0) {
                        this.C = '/' + this.C;
                    }
                    else {
                        this.C = bf.C.substring(0, n7 + 1) + this.C;
                    }
                    this.C = b(this.C);
                }
            }
            return;
        }
        if (bf != null && this.x == null) {
            throw new dh("Can't resolve relative URI for scheme " + s2);
        }
        this.y = a(s.substring(n), bf.n, true);
        if (this.y.length() > 0 && this.y.charAt(0) == '/') {
            this.y = "%2F" + this.y.substring(1);
        }
    }
    
    private static String b(final String s) {
        int length = s.length();
        final int index;
        if ((index = s.indexOf("/.")) == -1 || (index != length - 2 && s.charAt(index + 2) != '/' && (s.charAt(index + 2) != '.' || (index != length - 3 && s.charAt(index + 3) != '/')))) {
            return s;
        }
        final char[] array = new char[s.length()];
        s.getChars(0, array.length, array, 0);
        int n = 0;
        for (int i = 1; i < length; ++i) {
            if (array[i] == '.' && array[i - 1] == '/') {
                int n2;
                if (i == length - 1) {
                    n2 = i;
                    ++i;
                }
                else if (array[i + 1] == '/') {
                    n2 = i - 1;
                    ++i;
                }
                else {
                    if (array[i + 1] != '.' || (i != length - 2 && array[i + 2] != '/')) {
                        continue;
                    }
                    if (i < n + 2) {
                        n = i + 2;
                        continue;
                    }
                    for (n2 = i - 2; n2 > n && array[n2] != '/'; --n2) {}
                    if (array[n2] != '/') {
                        continue;
                    }
                    if (i == length - 2) {
                        ++n2;
                    }
                    i += 2;
                }
                System.arraycopy(array, i, array, n2, length - i);
                length -= i - n2;
                i = n2;
            }
        }
        return new String(array, 0, length);
    }
    
    private void a(final String s, final String s2) {
        final char[] charArray = s.toCharArray();
        int n = 0;
        int length;
        int n2;
        for (length = charArray.length, n2 = 0; n2 < length && charArray[n2] != '@'; ++n2) {}
        if (n2 < length && charArray[n2] == '@') {
            this.z = a(s.substring(0, n2), bf.k, true);
            n = n2 + 1;
        }
        int n3;
        if ((n3 = n) < length && charArray[n3] == '[') {
            while (n3 < length && charArray[n3] != ']') {
                ++n3;
            }
            if (n3 == length) {
                throw new dh("No closing ']' found for opening '[' at position " + n + " in authority `" + s + "'");
            }
            this.A = s.substring(n + 1, n3);
            ++n3;
        }
        else {
            while (n3 < length && charArray[n3] != ':') {
                ++n3;
            }
            this.A = a(s.substring(n, n3), bf.i, true);
        }
        final int n4;
        if ((n4 = n3) < length - 1 && charArray[n4] == ':') {
            int int1;
            try {
                if ((int1 = Integer.parseInt(a(s.substring(n4 + 1, length), (BitSet)null))) < 0) {
                    throw new NumberFormatException();
                }
            }
            catch (NumberFormatException ex) {
                throw new dh(s.substring(n4 + 1, length) + " is an invalid port number");
            }
            if (int1 == a(s2)) {
                this.B = -1;
                return;
            }
            this.B = int1;
        }
    }
    
    public bf(final String s, final String s2, final int n, final String s3) {
        this(s, null, s2, n, s3, null, null);
    }
    
    public bf(final String s, final String s2, String trim, final int b, final String s3, final String s4, final String s5) {
        this.B = -1;
        this.F = -1;
        if (s == null) {
            throw new dh("missing scheme");
        }
        this.x = a(s.trim().toLowerCase(), bf.l, true);
        if (trim != null) {
            trim = trim.trim();
            this.A = (c(trim) ? trim : a(trim, bf.m, true));
        }
        if (b != a(s)) {
            this.B = b;
        }
        if (s3 != null) {
            this.C = a(s3.trim(), bf.a, true);
        }
        if (s4 != null) {
            this.D = a(s4.trim(), bf.u, true);
        }
        this.w = (d(s) ? 2 : 1);
    }
    
    private static final boolean c(final String s) {
        if (s.indexOf(58) < 0) {
            return false;
        }
        for (int i = 0; i < s.length(); ++i) {
            final char char1;
            if (((char1 = s.charAt(i)) < '0' || char1 > '9') && char1 != ':') {
                return false;
            }
        }
        return true;
    }
    
    private static boolean d(final String s) {
        return bf.c.containsKey(s.trim().toLowerCase());
    }
    
    public static final int a(final String s) {
        final Integer n;
        if ((n = bf.b.get(s.trim().toLowerCase())) != null) {
            return n;
        }
        return 0;
    }
    
    public final String a() {
        return this.x;
    }
    
    public final String b() {
        return this.A;
    }
    
    public final int c() {
        return this.B;
    }
    
    public final String d() {
        if (this.D == null) {
            return this.C;
        }
        if (this.C == null) {
            return "?" + this.D;
        }
        return this.C + "?" + this.D;
    }
    
    private final String a(final boolean b) {
        final StringBuffer sb = new StringBuffer(100);
        if (this.x != null) {
            sb.append(b ? b(this.x, bf.p) : this.x);
            sb.append(':');
        }
        if (this.y != null) {
            sb.append(b ? b(this.y, (BitSet)null) : this.y);
            return sb.toString();
        }
        if (this.z != null || this.A != null || this.B != -1) {
            sb.append("//");
        }
        if (this.z != null) {
            sb.append(b ? b(this.z, bf.q) : this.z);
            sb.append('@');
        }
        if (this.A != null) {
            if (this.A.indexOf(58) < 0) {
                sb.append(b ? b(this.A, bf.r) : this.A);
            }
            else {
                sb.append('[').append(this.A).append(']');
            }
        }
        if (this.B != -1) {
            sb.append(':');
            sb.append(this.B);
        }
        final StringBuffer sb2 = sb;
        if (this.C != null) {
            this.C.length();
        }
        if (this.C != null) {
            sb2.append(b ? b(this.C, bf.s) : this.C);
        }
        if (this.D != null) {
            sb2.append('?');
            sb2.append(b ? b(this.D, bf.t) : this.D);
        }
        if (this.E != null) {
            sb2.append('#');
            sb2.append(b ? b(this.E, (BitSet)null) : this.E);
        }
        return sb.toString();
    }
    
    public final String e() {
        return this.a(false);
    }
    
    public final String toString() {
        return this.a(true);
    }
    
    public final boolean equals(final Object o) {
        if (o instanceof bf) {
            final bf bf = (bf)o;
            return this.x.equals(bf.x) && ((this.w == 0 && b(this.y, bf.y)) || (this.w == 1 && b(this.z, bf.z) && c(this.A, bf.A) && this.B == bf.B && b(this.C, bf.C)) || (this.w == 2 && b(this.z, bf.z) && c(this.A, bf.A) && this.B == bf.B && d(this.C, bf.C) && b(this.D, bf.D) && b(this.E, bf.E)));
        }
        if (o instanceof URL) {
            final URL url = (URL)o;
            String s;
            if (this.z != null) {
                s = this.z + "@" + this.A;
            }
            else {
                s = this.A;
            }
            final String d = this.d();
            return this.x.equalsIgnoreCase(url.getProtocol()) && ((this.w == 0 && this.y.equals(url.getFile())) || (this.w == 1 && c(s, url.getHost()) && (this.B == url.getPort() || url.getPort() == a(this.x)) && b(d, url.getFile())) || (this.w == 2 && c(s, url.getHost()) && (this.B == url.getPort() || url.getPort() == a(this.x)) && d(d, url.getFile()) && b(this.E, url.getRef())));
        }
        return false;
    }
    
    private static final boolean b(final String s, final String s2) {
        return (s == null && s2 == null) || (s != null && s2 != null && (s.equals(s2) || b(s, (BitSet)null).equals(b(s2, (BitSet)null))));
    }
    
    private static final boolean c(final String s, final String s2) {
        return (s == null && s2 == null) || (s != null && s2 != null && (s.equalsIgnoreCase(s2) || b(s, (BitSet)null).equalsIgnoreCase(b(s2, (BitSet)null))));
    }
    
    private static final boolean d(final String s, final String s2) {
        if (s == null && s2 == null) {
            return true;
        }
        if (s == null || s2 == null) {
            return false;
        }
        if (s.equals(s2)) {
            return true;
        }
        int n;
        int length;
        int n2;
        int length2;
        for (n = 0, length = s.length(), n2 = 0, length2 = s2.length(); n < length && n2 < length2; ++n, ++n2) {
            final int n3 = n;
            final int n4 = n2;
            char char1;
            while (n < length && (char1 = s.charAt(n)) != '/' && char1 != ';') {
                ++n;
            }
            char char2;
            while (n2 < length2 && (char2 = s2.charAt(n2)) != '/' && char2 != ';') {
                ++n2;
            }
            if ((n == length && n2 < length2) || (n2 == length2 && n < length) || (n < length && n2 < length2 && s.charAt(n) != s2.charAt(n2))) {
                return false;
            }
            if ((!s.regionMatches(n3, s2, n4, n - n3) || n - n3 != n2 - n4) && !b(s.substring(n3, n), (BitSet)null).equals(b(s2.substring(n4, n2), (BitSet)null))) {
                return false;
            }
        }
        return n == length && n2 == length2;
    }
    
    public final int hashCode() {
        if (this.F == -1) {
            this.F = ((this.x != null) ? b(this.x, (BitSet)null).hashCode() : 0) + ((this.w == 0) ? (((this.y != null) ? b(this.y, (BitSet)null).hashCode() : 0) * 7) : (((this.A != null) ? b(this.A, (BitSet)null).toLowerCase().hashCode() : 0) * 7 + ((this.C != null) ? b(this.C, (BitSet)null).hashCode() : 0) * 13 + ((this.D != null) ? b(this.D, (BitSet)null).hashCode() : 0) * 17));
        }
        return this.F;
    }
    
    public static String a(final String s, final BitSet set, final boolean b) {
        return new String(a(s.toCharArray(), set, b));
    }
    
    private static char[] a(final char[] array, final BitSet set, final boolean b) {
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            if (!set.get(array[i])) {
                n += 2;
                if (b) {
                    if (array[i] >= '\u0080') {
                        n += 3;
                    }
                    if (array[i] >= '\u0800') {
                        n += 3;
                    }
                    if ((array[i] & '\ufc00') == '\ud800' && i + 1 < array.length && (array[i + 1] & '\ufc00') == '\udc00') {
                        n -= 6;
                    }
                }
            }
        }
        if (n == 0) {
            return array;
        }
        final char[] array2 = new char[array.length + n];
        int j = 0;
        int n2 = 0;
        while (j < array.length) {
            final char c = array[j];
            if (set.get(c)) {
                array2[n2++] = c;
            }
            else if (b && c > '\u007f') {
                if (c <= '\u07ff') {
                    n2 = a(array2, a(array2, n2, '\u00c0' | (c >> 6 & '\u001f')), '\u0080' | (c & '?'));
                }
                else if ((c & '\ufc00') != '\ud800' || j + 1 >= array.length || (array[j + 1] & '\ufc00') != '\udc00') {
                    n2 = a(array2, a(array2, a(array2, n2, '\u00e0' | (c >> 12 & '\u000f')), '\u0080' | (c >> 6 & '?')), '\u0080' | (c & '?'));
                }
                else {
                    final int n3 = ((c & '\u03ff') << 10 | (array[++j] & '\u03ff')) + 65536;
                    n2 = a(array2, a(array2, a(array2, a(array2, n2, 0xF0 | (n3 >> 18 & 0x7)), 0x80 | (n3 >> 12 & 0x3F)), 0x80 | (n3 >> 6 & 0x3F)), 0x80 | (n3 & 0x3F));
                }
            }
            else {
                n2 = a(array2, n2, c);
            }
            ++j;
        }
        return array2;
    }
    
    private static final int a(final char[] array, int n, final int n2) {
        array[n++] = '%';
        array[n++] = bf.G[n2 >> 4 & 0xF];
        array[n++] = bf.G[n2 & 0xF];
        return n;
    }
    
    private static String a(final String s, final BitSet set) {
        if (s == null || s.indexOf(37) == -1) {
            return s;
        }
        final char[] charArray;
        final char[] array = new char[(charArray = s.toCharArray()).length];
        final char[] array2 = new char[4];
        int n = 0;
        int n2 = -1;
        int n3 = 0;
        for (int i = 0; i < charArray.length; ++i) {
            if (charArray[i] == '%') {
                int int1;
                try {
                    if (i + 3 > charArray.length) {
                        throw new NumberFormatException();
                    }
                    if ((int1 = Integer.parseInt(s.substring(i + 1, i + 3), 16)) < 0) {
                        throw new NumberFormatException();
                    }
                    i += 2;
                }
                catch (NumberFormatException ex) {
                    int1 = charArray[i];
                }
                if (n2 > 0) {
                    if ((int1 & 0xC0) != 0x80) {
                        n3 = a(array2, n, int1, array, n3, set, false);
                        n2 = -1;
                    }
                    else if (n == n2 - 1) {
                        int n4;
                        if ((array2[0] & '\u00e0') == '\u00c0') {
                            n4 = ((array2[0] & '\u001f') << 6 | (int1 & 0x3F));
                        }
                        else if ((array2[0] & '\u00f0') == '\u00e0') {
                            n4 = ((array2[0] & '\u000f') << 12 | (array2[1] & '?') << 6 | (int1 & 0x3F));
                        }
                        else {
                            n4 = ((array2[0] & '\u0007') << 18 | (array2[1] & '?') << 12 | (array2[2] & '?') << 6 | (int1 & 0x3F));
                        }
                        if (set != null && set.get(n4)) {
                            n3 = a(array2, n, n4, array, n3, null, true);
                        }
                        else if (n2 < 4) {
                            array[n3++] = (char)n4;
                        }
                        else {
                            final int n5 = n4 - 65536;
                            array[n3++] = (char)(n5 >> 10 | 0xD800);
                            array[n3++] = (char)((n5 & 0x3FF) | 0xDC00);
                        }
                        n2 = -1;
                    }
                    else {
                        array2[n++] = (char)int1;
                    }
                }
                else if ((int1 & 0xE0) == 0xC0 || (int1 & 0xF0) == 0xE0 || (int1 & 0xF8) == 0xF0) {
                    if ((int1 & 0xE0) == 0xC0) {
                        n2 = 2;
                    }
                    else if ((int1 & 0xF0) == 0xE0) {
                        n2 = 3;
                    }
                    else {
                        n2 = 4;
                    }
                    array2[0] = (char)int1;
                    n = 1;
                }
                else if (set != null && set.get(int1)) {
                    array[n3++] = charArray[i];
                    i -= 2;
                }
                else {
                    array[n3++] = (char)int1;
                }
            }
            else if (n2 > 0) {
                n3 = a(array2, n, charArray[i], array, n3, set, false);
                n2 = -1;
            }
            else {
                array[n3++] = charArray[i];
            }
        }
        if (n2 > 0) {
            n3 = a(array2, n, -1, array, n3, set, false);
        }
        return new String(array, 0, n3);
    }
    
    private static final int a(final char[] array, int n, int i, final char[] array2, int a, final BitSet set, final boolean b) {
        if (i >= 0) {
            array[n++] = (char)i;
        }
        for (i = 0; i < n; ++i) {
            if ((set != null && set.get(array[i])) || b) {
                a = a(array2, a, array[i]);
            }
            else {
                array2[a++] = array[i];
            }
        }
        return a;
    }
    
    private static final String b(final String s, final BitSet set) {
        try {
            return a(s, set);
        }
        catch (dh dh) {
            return s;
        }
    }
    
    static {
        bf.b = new Hashtable();
        bf.c = new Hashtable();
        bf.d = new Hashtable();
        bf.b.put("http", new Integer(80));
        bf.b.put("shttp", new Integer(80));
        bf.b.put("http-ng", new Integer(80));
        bf.b.put("coffee", new Integer(80));
        bf.b.put("https", new Integer(443));
        bf.b.put("ftp", new Integer(21));
        bf.b.put("telnet", new Integer(23));
        bf.b.put("nntp", new Integer(119));
        bf.b.put("news", new Integer(119));
        bf.b.put("snews", new Integer(563));
        bf.b.put("hnews", new Integer(80));
        bf.b.put("smtp", new Integer(25));
        bf.b.put("gopher", new Integer(70));
        bf.b.put("wais", new Integer(210));
        bf.b.put("whois", new Integer(43));
        bf.b.put("whois++", new Integer(63));
        bf.b.put("rwhois", new Integer(4321));
        bf.b.put("imap", new Integer(143));
        bf.b.put("pop", new Integer(110));
        bf.b.put("prospero", new Integer(1525));
        bf.b.put("irc", new Integer(194));
        bf.b.put("ldap", new Integer(389));
        bf.b.put("nfs", new Integer(2049));
        bf.b.put("z39.50r", new Integer(210));
        bf.b.put("z39.50s", new Integer(210));
        bf.b.put("vemmi", new Integer(575));
        bf.b.put("videotex", new Integer(516));
        bf.b.put("cmp", new Integer(829));
        bf.c.put("http", Boolean.TRUE);
        bf.c.put("https", Boolean.TRUE);
        bf.c.put("shttp", Boolean.TRUE);
        bf.c.put("coffee", Boolean.TRUE);
        bf.c.put("ftp", Boolean.TRUE);
        bf.c.put("file", Boolean.TRUE);
        bf.c.put("nntp", Boolean.TRUE);
        bf.c.put("news", Boolean.TRUE);
        bf.c.put("snews", Boolean.TRUE);
        bf.c.put("hnews", Boolean.TRUE);
        bf.c.put("imap", Boolean.TRUE);
        bf.c.put("wais", Boolean.TRUE);
        bf.c.put("nfs", Boolean.TRUE);
        bf.c.put("sip", Boolean.TRUE);
        bf.c.put("sips", Boolean.TRUE);
        bf.c.put("sipt", Boolean.TRUE);
        bf.c.put("sipu", Boolean.TRUE);
        bf.d.put("ldap", Boolean.TRUE);
        bf.d.put("irc", Boolean.TRUE);
        bf.d.put("gopher", Boolean.TRUE);
        bf.d.put("videotex", Boolean.TRUE);
        bf.d.put("rwhois", Boolean.TRUE);
        bf.d.put("whois++", Boolean.TRUE);
        bf.d.put("smtp", Boolean.TRUE);
        bf.d.put("telnet", Boolean.TRUE);
        bf.d.put("prospero", Boolean.TRUE);
        bf.d.put("pop", Boolean.TRUE);
        bf.d.put("vemmi", Boolean.TRUE);
        bf.d.put("z39.50r", Boolean.TRUE);
        bf.d.put("z39.50s", Boolean.TRUE);
        bf.d.put("stream", Boolean.TRUE);
        bf.d.put("cmp", Boolean.TRUE);
        bf.e = new BitSet(128);
        for (int i = 48; i <= 57; ++i) {
            bf.e.set(i);
        }
        for (int j = 65; j <= 90; ++j) {
            bf.e.set(j);
        }
        for (int k = 97; k <= 122; ++k) {
            bf.e.set(k);
        }
        (bf.f = new BitSet(128)).set(45);
        bf.f.set(95);
        bf.f.set(46);
        bf.f.set(33);
        bf.f.set(126);
        bf.f.set(42);
        bf.f.set(39);
        bf.f.set(40);
        bf.f.set(41);
        (bf.g = new BitSet(128)).set(59);
        bf.g.set(47);
        bf.g.set(63);
        bf.g.set(58);
        bf.g.set(64);
        bf.g.set(38);
        bf.g.set(61);
        bf.g.set(43);
        bf.g.set(36);
        bf.g.set(44);
        (bf.h = new BitSet(128)).or(bf.e);
        bf.h.or(bf.f);
        (bf.i = new BitSet(128)).or(bf.h);
        bf.i.or(bf.g);
        bf.i.set(37);
        (bf.j = new BitSet(128)).or(bf.h);
        bf.j.set(37);
        bf.j.set(58);
        bf.j.set(64);
        bf.j.set(38);
        bf.j.set(61);
        bf.j.set(43);
        bf.j.set(36);
        bf.j.set(44);
        (bf.k = new BitSet(128)).or(bf.h);
        bf.k.set(37);
        bf.k.set(59);
        bf.k.set(58);
        bf.k.set(38);
        bf.k.set(61);
        bf.k.set(43);
        bf.k.set(36);
        bf.k.set(44);
        (bf.l = new BitSet(128)).or(bf.e);
        bf.l.set(43);
        bf.l.set(45);
        bf.l.set(46);
        (bf.n = new BitSet(128)).or(bf.i);
        (bf.m = new BitSet(128)).or(bf.e);
        bf.m.set(45);
        bf.m.set(46);
        (bf.o = new BitSet(128)).or(bf.h);
        bf.o.set(36);
        bf.o.set(44);
        bf.o.set(59);
        bf.o.set(58);
        bf.o.set(64);
        bf.o.set(38);
        bf.o.set(61);
        bf.o.set(43);
        (bf.p = new BitSet(128)).set(58);
        (bf.q = new BitSet(128)).set(64);
        (bf.r = new BitSet(128)).set(58);
        bf.r.set(47);
        bf.r.set(63);
        bf.r.set(35);
        (bf.s = new BitSet(128)).set(47);
        bf.s.set(59);
        bf.s.set(63);
        bf.s.set(35);
        (bf.t = new BitSet(128)).set(35);
        (a = new BitSet(128)).or(bf.j);
        bf.a.set(37);
        bf.a.set(47);
        bf.a.set(59);
        (bf.u = new BitSet(128)).or(bf.i);
        bf.u.clear(35);
        (bf.v = new BitSet(128)).or(bf.i);
        G = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        System.getProperty("line.separator");
    }
}
