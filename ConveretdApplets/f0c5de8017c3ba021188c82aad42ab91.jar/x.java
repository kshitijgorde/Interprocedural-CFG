import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class x
{
    public Hashtable a;
    public String b;
    public String c;
    public String d;
    public String e;
    
    public x() {
        this.a = new Hashtable();
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
    }
    
    public x(String b) {
        this.a = new Hashtable();
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        if (b == null) {
            return;
        }
        final int index = b.indexOf("#");
        if (index > -1) {
            this.d = b.substring(index + 1, b.length());
            b = b.substring(0, index);
        }
        final int index2 = b.indexOf(63);
        if (index2 > -1) {
            this.b = b.substring(0, index2);
            b = b.substring(index2 + 1);
        }
        else if (b.indexOf("=") == -1) {
            this.b = b;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(b, "&");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final int index3;
            if ((index3 = nextToken.indexOf("=")) > 0) {
                this.a.put(nextToken.substring(0, index3), nextToken.substring(index3 + 1));
            }
        }
    }
    
    public x(final x x) {
        this.a = new Hashtable();
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        if (x.e != null) {
            this.e = new String(x.e);
        }
        if (x.b != null) {
            this.b = new String(x.b);
        }
        if (x.d != null) {
            this.d = new String(x.d);
        }
        this.a = (Hashtable)x.a.clone();
        if (x.c != null) {
            this.c = new String(x.c);
        }
    }
    
    public final String a(final String s) {
        return this.a.get(s);
    }
    
    public final String b(final String s) {
        final String s2 = this.a.remove(s);
        if (s2 != null) {
            this.e = null;
        }
        return s2;
    }
    
    public final void a(final String s, final String s2) {
        this.e = null;
        this.a.put(s, s2);
    }
    
    public final boolean c(final String s) {
        return this.a.containsKey(s);
    }
    
    public final String c() {
        return this.b;
    }
    
    public void d(final String b) {
        this.b = b;
        this.e = null;
    }
    
    public final Enumeration d() {
        return this.a.keys();
    }
    
    public String toString() {
        if (this.e != null) {
            return this.e;
        }
        final String[] array = new String[this.a.size()];
        final Enumeration<String> keys = (Enumeration<String>)this.a.keys();
        int n = 0;
        while (keys.hasMoreElements()) {
            array[n] = keys.nextElement();
            ++n;
        }
        for (int i = 0; i < array.length; ++i) {
            for (int j = i + 1; j < array.length; ++j) {
                if (array[i].compareTo(array[j]) > 0) {
                    final String s = array[i];
                    array[i] = array[j];
                    array[j] = s;
                }
            }
        }
        final StringBuffer sb = new StringBuffer();
        if (array.length > 0) {
            sb.append(array[0] + "=" + this.a.get(array[0]));
            for (int k = 1; k < array.length; ++k) {
                sb.append("&" + array[k] + "=" + (String)this.a.get(array[k]));
            }
        }
        this.e = ((this.b != null) ? (this.b + "?") : "") + sb.toString();
        if (this.d != null) {
            this.e = this.e + "#" + this.d;
        }
        if (this.c != null) {
            this.e = this.c + this.e;
        }
        return this.e;
    }
    
    public int e() {
        if (this.e != null) {
            return this.e.length();
        }
        return this.toString().length();
    }
    
    public boolean a(final x x, final boolean b) {
        if (b) {
            if (this.c != null) {
                if (x.c == null || !this.c.equals(x.c)) {
                    return false;
                }
            }
            else if (x.c != null) {
                return false;
            }
        }
        if (this.b != null) {
            if (x.b == null || !this.b.equals(x.b)) {
                return false;
            }
        }
        else if (x.b != null) {
            return false;
        }
        if (this.d != null) {
            if (x.d == null || !this.d.equals(x.d)) {
                return false;
            }
        }
        else if (x.d != null) {
            return false;
        }
        final Enumeration<String> keys = x.a.keys();
        int n = 0;
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            if (!x.a(s).equals(this.a.get(s))) {
                return false;
            }
            ++n;
        }
        return true;
    }
}
