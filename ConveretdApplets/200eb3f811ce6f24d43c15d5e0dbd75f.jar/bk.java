import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class bk extends a4
{
    public static Hashtable a;
    private Hashtable b;
    private Hashtable c;
    public String d;
    private Vector e;
    private Vector f;
    private Vector g;
    private Vector h;
    private Vector i;
    private Vector j;
    private Hashtable k;
    
    public bk(final az az, final String s, final String d, final boolean b) {
        super(az, s, b);
        this.e = new Vector();
        this.f = new Vector();
        this.g = new Vector();
        this.h = new Vector();
        this.i = new Vector();
        this.j = new Vector();
        this.k = new Hashtable();
        this.d = d;
    }
    
    public static bk a(final a3 a3) {
        final String string = a3.k() + a3.n();
        bk a4 = bk.a.get(string);
        if (a4 == null) {
            a4 = a3.a(b(a3));
            final a9 a5 = new a9(1);
            a5.addElement(a4);
            final bd bd = new bd(a3.k, a5, a3.a(), a3.k.aj().a("URI_PREFIX"), a3.k.aj().d("REQUEST_TIMEOUT"), null);
            if (a0.a.j()) {
                a0.a.h(a3.k.as() + " got new meta/descriptor " + a4);
            }
            if (a4.r() == 0) {
                bk.a.put(string, a4);
            }
            else if (a0.a.g()) {
                a0.a.b(a3.k.as() + " got invalid meta/descriptor " + a4 + " with status code " + a4.r() + ", " + a4.t(), a4.u());
            }
        }
        return a4;
    }
    
    public boolean a(final bg bg, final int n, final String s) {
        if (!super.a(bg, n, s) || this.r() != 0) {
            return false;
        }
        System.currentTimeMillis();
        this.b = new Hashtable();
        final Vector vector = new Vector<bj>();
        final int[] array = { 0, 0, 0, 0 };
        final int[] array2 = { 0, 0, 0, 0 };
        try {
            for (int i = 0; i < this.w(); ++i) {
                if (i != 0 || this.b(0) >= 4) {
                    final String a = this.a(i, 0);
                    final String a2 = this.a(i, 2);
                    final int intValue = new Integer(this.a(i, 3));
                    int intValue2 = new Integer(this.a(i, 4));
                    if (intValue2 > 2) {
                        if (a0.a.g()) {
                            a0.a.d("atDetails level too large " + intValue2);
                        }
                        intValue2 = 2;
                    }
                    else if (intValue2 < -1) {
                        intValue2 = -1;
                        if (a0.a.g()) {
                            a0.a.d("atDetails level too small " + intValue2);
                        }
                    }
                    if (intValue >= 0) {
                        final int[] array3 = (intValue == 0) ? array : array2;
                        final int[] array4 = { -1, -1, -1, array3[3] };
                        final int[] array5 = array3;
                        final int n2 = 3;
                        ++array5[n2];
                        if (intValue2 < 2) {
                            array4[2] = array3[2];
                            final int[] array6 = array3;
                            final int n3 = 2;
                            ++array6[n3];
                            if (intValue2 < 1) {
                                array4[1] = array3[1];
                                final int[] array7 = array3;
                                final int n4 = 1;
                                ++array7[n4];
                                if (intValue2 < 0) {
                                    array4[0] = array3[0];
                                    final int[] array8 = array3;
                                    final int n5 = 0;
                                    ++array8[n5];
                                }
                            }
                        }
                        final bj bj = new bj(a, b(this.a(i, 1)), a2, intValue, array4, intValue2);
                        this.b.put(a, bj);
                        Label_0506: {
                            switch (intValue2) {
                                case -1: {
                                    if (bj.d()) {
                                        this.h.addElement(a);
                                        break Label_0506;
                                    }
                                    this.e.addElement(a);
                                    break Label_0506;
                                }
                                case 0: {
                                    if (bj.d()) {
                                        this.i.addElement(a);
                                        break Label_0506;
                                    }
                                    this.f.addElement(a);
                                    break Label_0506;
                                }
                                case 1: {
                                    if (bj.d()) {
                                        this.j.addElement(a);
                                        break;
                                    }
                                    this.g.addElement(a);
                                    break;
                                }
                                default: {
                                    if (a0.a.g()) {
                                        a0.a.d("MetaDescriptor: found unexpected details level " + intValue2);
                                        break;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    else if (intValue == -2) {
                        vector.addElement(new bj(a, b(this.a(i, 1)), a2, intValue, null, intValue2));
                    }
                }
            }
            if (vector.size() > 0) {
                for (int j = 0; j < vector.size(); ++j) {
                    final bj bj2 = vector.elementAt(j);
                    bj2.a(this);
                    final String a3 = bj2.a();
                    final String k = bj2.j();
                    this.b.put(a3, bj2);
                    if (!this.b().containsKey(k)) {
                        final Vector<String> vector2 = new Vector<String>();
                        vector2.addElement(a3);
                        this.b().put(k, vector2);
                    }
                    else {
                        ((Vector<String>)this.b().get(k)).addElement(a3);
                    }
                }
            }
        }
        catch (Exception ex) {
            super.q.a(-2);
            super.l = new am("parsing of meta/descriptor failed", ex);
        }
        return true;
    }
    
    private static int b(final String s) throws Exception {
        final int a = bj.a(s);
        if (n.e()) {
            n.a(a != 0, "MetaDescriptor - unknown type: " + s);
        }
        return a;
    }
    
    public final bj a(final String s) {
        return this.b.get(s);
    }
    
    public final String a(final int n, final int n2, final boolean b) {
        try {
            switch (n2) {
                case 0: {
                    if (b) {
                        return this.i.elementAt(n);
                    }
                    return this.f.elementAt(n);
                }
                case -1: {
                    if (b) {
                        return this.h.elementAt(n);
                    }
                    return this.e.elementAt(n);
                }
                case 1: {
                    if (b) {
                        return this.j.elementAt(n);
                    }
                    return this.g.elementAt(n);
                }
                default: {
                    throw new IllegalArgumentException("Unexpected details level " + n2);
                }
            }
        }
        catch (Exception ex) {
            if (a0.a.g()) {
                a0.a.b("MetaDescriptor: could not translate attribute name from col index " + n + " at details " + n2, ex);
            }
            return null;
        }
    }
    
    public final Vector b(final int n, final int n2, final boolean b) {
        final String a = this.a(n, n2, b);
        Vector<String> vector;
        if (this.c == null || !this.c.containsKey(a)) {
            vector = new Vector<String>();
        }
        else {
            vector = (Vector<String>)this.c.get(a).clone();
        }
        vector.addElement(a);
        return vector;
    }
    
    public final boolean a() {
        return this.c != null;
    }
    
    public final String[] a(final boolean b, final int n) {
        final String string = "all_names:" + b + ":" + n;
        String[] array = this.b.get(string);
        if (array == null) {
            boolean b2 = false;
            if (b) {
                b2 = true;
            }
            final Vector vector = new Vector<String>();
            final Enumeration<String> keys = this.b.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                if (!s.startsWith("all_names")) {
                    final bj bj = this.b.get(s);
                    if (bj.e() != (b2 ? 1 : 0) || bj.f() > n) {
                        continue;
                    }
                    vector.addElement(s);
                }
            }
            array = new String[vector.size()];
            for (int i = 0; i < vector.size(); ++i) {
                array[i] = vector.elementAt(i);
            }
            this.b.put(string, array);
        }
        return array;
    }
    
    public static final String b(final a3 a3) {
        String s = a3.k();
        final int index;
        if ((index = s.indexOf(63)) > -1) {
            s = s.substring(0, index);
        }
        String s2 = "meta/descriptor?NAME=" + URLEncoder.encode(s) + "&VERSION=" + a3.n();
        if (a3.k.k) {
            final a5 j = a3.j();
            String s3 = j.a("XID");
            boolean b = false;
            if (s3 != null) {
                b = true;
            }
            else {
                s3 = j.a("ID_CUST");
            }
            s2 = s2 + (b ? "&XID=" : "&ID_CUST=") + s3;
        }
        return s2;
    }
    
    private Hashtable b() {
        if (this.c == null) {
            this.c = new Hashtable();
        }
        return this.c;
    }
    
    static {
        bk.a = new Hashtable();
    }
}
