import java.util.Enumeration;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Font;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

final class a implements Cloneable
{
    int a;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private int j;
    private int k;
    private Hashtable l;
    private Hashtable[] m;
    private Vector n;
    private int o;
    static Font[] b;
    static Font[] c;
    
    static {
        a.b = new Font[30];
        a.c = new Font[30];
    }
    
    a() {
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = 0;
        this.k = 0;
        this.m = new Hashtable[10];
        this.n = null;
        this.l = new Hashtable();
    }
    
    a(final int a) {
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = 0;
        this.k = 0;
        this.m = new Hashtable[10];
        this.n = null;
        this.a = a;
    }
    
    a(final String s) {
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = 0;
        this.k = 0;
        this.m = new Hashtable[10];
        this.n = null;
        int index = s.indexOf(9);
        this.a = Integer.parseInt(s.substring(0, index++));
        int index2 = s.indexOf(9, index);
        this.d = s.substring(index, index2++);
        int index3 = s.indexOf(9, index2);
        this.f = s.substring(index2, index3++);
        if (this.f.length() == 0) {
            this.f = null;
        }
        this.e = s.substring(index3);
    }
    
    final boolean a() {
        return this.j > 0;
    }
    
    final void a(final int k) {
        this.k = k;
        this.i = null;
    }
    
    final String b() {
        if (this.h == null) {
            return this.c();
        }
        return String.valueOf(this.h) + " [" + this.c() + "]";
    }
    
    final String c() {
        if (this.i == null) {
            final StringBuffer sb = new StringBuffer(100);
            final String s = (this.k == 0) ? this.e : this.d;
            final String s2 = (this.k == 0) ? this.d : this.e;
            sb.append(s);
            sb.append(" - ");
            if (this.f != null) {
                sb.append(this.f);
                sb.append(" - ");
            }
            sb.append(s2);
            this.i = sb.toString();
        }
        return this.i;
    }
    
    private final void a(final Vector vector, final String s) {
        int i = 0;
        final int length = s.length();
        while (i < length) {
            if (s.charAt(i++) == '{') {
                final int n = i;
                for (int n2 = 1; n2 > 0 && i < length; --n2) {
                    final char char1;
                    if ((char1 = s.charAt(i++)) == '{') {
                        ++n2;
                    }
                    if (char1 == '}') {}
                }
                final Hashtable hashtable = new Hashtable();
                this.m[this.j] = hashtable;
                ++this.j;
                vector.addElement(hashtable);
                this.a(hashtable, s.substring(n, i - 1));
            }
        }
    }
    
    private final void a(final Hashtable hashtable, final String s) {
        int i = 0;
        final int length = s.length();
        while (i >= 0) {
            int j;
            if ((j = s.indexOf(58, i)) >= 0) {
                final String substring = s.substring(i, j);
                final int n = ++j;
                final char char1;
                if ((char1 = s.charAt(j)) == '\'') {
                    final StringBuffer sb = new StringBuffer();
                    while (j < length) {
                        ++j;
                        char c;
                        if ((c = s.charAt(j)) == '\'') {
                            break;
                        }
                        if (c == '\\') {
                            ++j;
                            c = s.charAt(j);
                        }
                        sb.append(c);
                    }
                    if (j > 0) {
                        hashtable.put(substring, sb.toString());
                    }
                    ++j;
                }
                else if (char1 == '[') {
                    j = s.indexOf(93, n);
                    final Vector vector = new Vector();
                    hashtable.put(substring, vector);
                    this.a(vector, s.substring(n + 1, j));
                }
                else {
                    while (j < length) {
                        final char char2;
                        if ((char2 = s.charAt(j)) != '-') {
                            if (char2 < '0') {
                                break;
                            }
                            if (char2 > '9') {
                                break;
                            }
                        }
                        ++j;
                    }
                    if (j > n + 6) {
                        hashtable.put(substring, new Long(Long.parseLong(s.substring(n, j)) * 1000L));
                    }
                    else {
                        hashtable.put(substring, new Integer(s.substring(n, j)));
                    }
                }
                if (j < length && s.charAt(j) == ',') {
                    ++j;
                }
            }
            i = j;
        }
    }
    
    final void a(final String s) {
        this.l = new Hashtable();
        final int index = s.indexOf(123);
        final int lastIndex = s.lastIndexOf(125);
        if (index >= 0 && lastIndex > index) {
            this.a(this.l, s.substring(index + 1, lastIndex));
        }
        this.e = this.l.get("c");
        this.d = this.l.get("f");
        this.f = this.l.get("s");
    }
    
    private static String c(String substring, final int n) {
        if (substring == null || substring.length() == 0) {
            return null;
        }
        if (substring.length() > n) {
            substring = substring.substring(0, n);
        }
        return substring;
    }
    
    final void b(final String s) {
        this.h = c(s, 63);
    }
    
    final void c(final String s) {
        this.g = c(s, 200);
    }
    
    final String d() {
        if (this.h != null) {
            return this.h;
        }
        return this.d;
    }
    
    final String e() {
        return this.e;
    }
    
    final String f() {
        if (this.f != null) {
            return String.valueOf(this.e) + " - " + this.f;
        }
        return this.e;
    }
    
    final String g() {
        return this.h;
    }
    
    final String h() {
        return this.d;
    }
    
    final String i() {
        return this.g;
    }
    
    private final Hashtable d(final long n) {
        Hashtable hashtable = this.m[0];
        Hashtable hashtable2;
        for (int n2 = 0; n2 < this.j && n >= (long)(hashtable2 = this.m[n2]).get("t"); ++n2) {
            hashtable = hashtable2;
        }
        if (hashtable == null) {
            hashtable = new Hashtable();
        }
        return hashtable;
    }
    
    final long a(final long n) {
        final Integer n2;
        if ((n2 = this.d(n).get("o")) == null) {
            return 0L;
        }
        return n + n2 * 1000L;
    }
    
    final String b(final long n) {
        String s;
        if ((s = this.d(n).get("a")) == null) {
            s = "";
        }
        return s;
    }
    
    final boolean c(final long n) {
        final Integer n2;
        return (n2 = this.d(n).get("d")) != null && n2 == 1;
    }
    
    final void a(final Graphics graphics, final int n, final int n2) {
        if (this.n == null) {
            return;
        }
        a(graphics, n, n2, this.n, this.o);
    }
    
    final int a(final FontMetrics fontMetrics, final int n) {
        if (this.g == null) {
            return 0;
        }
        this.n = a(this.g, fontMetrics, n);
        this.o = fontMetrics.getHeight();
        return this.n.size();
    }
    
    public final Object clone() {
        final a a;
        (a = new a()).d = this.d;
        a.e = this.e;
        a.f = this.f;
        a.g = this.g;
        a.h = this.h;
        a.a = this.a;
        return a;
    }
    
    public final a j() {
        final a a;
        (a = new a()).l = (Hashtable)this.l.clone();
        return a;
    }
    
    static final a[] a(final a[] array) {
        final int length;
        final a[] array2 = new a[length = array.length];
        for (int i = 0; i < length; ++i) {
            final a a = array[i];
            array2[i] = ((a == null) ? null : ((a)a.clone()));
        }
        return array2;
    }
    
    final boolean a(final a a) {
        final Enumeration<Object> keys = this.l.keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            if (!this.l.get(nextElement).equals(a.l.get(nextElement))) {
                return false;
            }
        }
        return true;
    }
    
    static final boolean a(final a[] array, final a[] array2) {
        final int length = array.length;
        if (array2.length != length) {
            return false;
        }
        for (int i = 0; i < length; ++i) {
            final a a = array[i];
            final a a2 = array2[i];
            if ((a == null && a2 != null) || (a != null && a2 == null)) {
                return false;
            }
            if (a != null && (a.a != a2.a || !a(a.h, a2.h) || !a(a.g, a2.g))) {
                return false;
            }
        }
        return true;
    }
    
    private static final boolean a(final String s, final String s2) {
        if (s == null) {
            return s2 == null;
        }
        return s.equals(s2);
    }
    
    final void a(final String s, final Object o) {
        this.l.put(s, o);
    }
    
    final void a(final String s, final int n) {
        this.l.put(s, new Integer(n));
    }
    
    final int d(final String s) {
        final Integer value;
        if ((value = this.l.get(s)) != null) {
            return value;
        }
        return 0;
    }
    
    final boolean b(final String s, final int n) {
        return (this.d(s) & n) > 0;
    }
    
    static final Font a(final int n, final boolean b) {
        final Font[] array;
        if ((array = (b ? a.c : a.b))[n] == null) {
            array[n] = new Font("SansSerif", b ? 1 : 0, n);
        }
        return array[n];
    }
    
    static final void a(final Graphics graphics, final int n, int n2, final Vector vector, final int n3) {
        final int size = vector.size();
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        for (int i = 0; i < size; ++i) {
            final String string = vector.elementAt(i).toString();
            graphics.drawString(string, n - fontMetrics.stringWidth(string) / 2, n2);
            n2 += n3;
        }
    }
    
    static final Vector a(final String s, final FontMetrics fontMetrics, final int n) {
        final Vector<String> vector = new Vector<String>();
        final int length = s.length();
        int i = 0;
        int n2 = 0;
        while (i < length) {
            int n3 = i;
            int n4 = 0;
            if (++n2 > 200) {
                break;
            }
            while (n4 <= n && n3 < length) {
                int index;
                if ((index = s.indexOf(32, n3)) < 0) {
                    index = (n3 = length);
                }
                else {
                    n3 = index + 1;
                }
                n4 = fontMetrics.stringWidth(s.substring(i, index));
            }
            if (n4 > n) {
                int lastIndex;
                if ((lastIndex = s.lastIndexOf(32, Math.max(n3 - 2, 0))) <= i) {
                    for (lastIndex = n3; lastIndex > i && n4 > n; --lastIndex, n4 = fontMetrics.stringWidth(s.substring(i, lastIndex))) {}
                }
                vector.addElement(s.substring(i, lastIndex));
                for (i = lastIndex; i < length; ++i) {
                    if (s.charAt(i) != ' ') {
                        break;
                    }
                }
            }
            else {
                vector.addElement(s.substring(i));
                i = length;
            }
        }
        return vector;
    }
}
