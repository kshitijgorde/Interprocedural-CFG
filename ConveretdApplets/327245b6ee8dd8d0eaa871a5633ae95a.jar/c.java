import java.util.StringTokenizer;
import java.util.Enumeration;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.io.Serializable;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class c extends d
{
    int a;
    boolean b;
    h c;
    at d;
    c e;
    c f;
    ar g;
    ar h;
    c4 i;
    private static Vector j;
    static final as k;
    public static boolean l;
    
    c a(final aa aa) {
        return aa.b;
    }
    
    c c(final String s) {
        return null;
    }
    
    boolean a(final String s, final c c) {
        return true;
    }
    
    void a(final au au) {
    }
    
    public c() {
        this.e = null;
        this.f = null;
        this.a = 5;
    }
    
    void ad() {
        this.b = true;
        this.d = new at();
        c c = null;
        final aa ak = blaze3d.a().ak;
        if (this.c == null) {
            c = this.a(ak);
            if (c == null || c == this) {
                c = this.b(ak);
            }
        }
        else {
            Serializable s = this.c.getClass();
            while (c == null || c == this) {
                final String gpc = blaze3d.b.gpc(((Class)s).getName());
                while (true) {
                    Label_0117: {
                        if (gpc == null) {
                            break Label_0117;
                        }
                        final c c2;
                        c = (c2 = ak.a.get(gpc));
                        final c b;
                        if (b == null) {
                            c = ak.d(gpc).f("prototype");
                        }
                    }
                    s = ((Class<? extends h>)s).getSuperclass();
                    if (s == null) {
                        final c b = ak.b;
                        if (c.l) {
                            continue;
                        }
                        c = b;
                    }
                    break;
                }
            }
        }
        this.c(c);
    }
    
    c b(final aa aa) {
        final Class<? super c> superclass = this.getClass().getSuperclass();
        d d = null;
        try {
            d = (d)superclass.newInstance();
        }
        catch (Exception ex) {}
        return d.a(aa);
    }
    
    c d(final String s) {
        if (c.j.indexOf(s) == -1) {
            final String gc = blaze3d.b.gc(s);
            if (gc != null) {
                try {
                    final c c = new c();
                    blaze3d.a().ak.a.put(s, c);
                    final Object instance = Class.forName(gc).newInstance();
                    if (instance instanceof h) {
                        final h c2 = (h)instance;
                        c2.a((t)null);
                        c.c = c2;
                        return c.b(s, this);
                    }
                    return ((c)instance).b(s, this);
                }
                catch (Throwable t) {}
            }
            c.j.addElement(s);
        }
        return d.e;
    }
    
    void c(final c c) {
        this.a((c)c.k, c);
    }
    
    c b(final String s, final c c) {
        final boolean l = c.l;
        Serializable s2 = null;
        Label_0030: {
            if (this.c != null) {
                s2 = this.c.getClass();
                if (!l) {
                    break Label_0030;
                }
            }
            s2 = this.getClass();
        }
        final Method[] declaredMethods = ((Class)s2).getDeclaredMethods();
        final Hashtable<String, Vector<Method>> hashtable = new Hashtable<String, Vector<Method>>();
        final Hashtable<String, Vector<Method>> hashtable2 = new Hashtable<String, Vector<Method>>();
        final Hashtable<String, Vector<Method>> hashtable3 = new Hashtable<String, Vector<Method>>();
        int n = 0;
    Label_0132_Outer:
        while (true) {
            Label_0401: {
                if (!l) {
                    break Label_0401;
                }
                final Method method = declaredMethods[n];
                Label_0398: {
                    if (Modifier.isPublic(method.getModifiers())) {
                        final String name = method.getName();
                        final Class<?>[] parameterTypes = method.getParameterTypes();
                        final String[] array = new String[parameterTypes.length];
                        int n2 = 0;
                        String gm;
                        while (true) {
                            while (true) {
                                Label_0135: {
                                    if (!l) {
                                        break Label_0135;
                                    }
                                    array[n2] = parameterTypes[n2].getName();
                                    ++n2;
                                }
                                if (n2 < parameterTypes.length) {
                                    continue Label_0132_Outer;
                                }
                                break;
                            }
                            gm = blaze3d.b.gm(((Class)s2).getName(), name, array);
                            if (l) {
                                continue;
                            }
                            break;
                        }
                        if (gm.startsWith("_g_") || gm.startsWith("_s_")) {
                            final String substring = gm.substring(3, gm.length());
                            if (!hashtable3.containsKey(substring)) {
                                final Vector<Method> vector = new Vector<Method>();
                                vector.addElement(method);
                                hashtable3.put(substring, vector);
                                if (!l) {
                                    break Label_0398;
                                }
                            }
                            hashtable3.get(substring).addElement(method);
                            if (!l) {
                                break Label_0398;
                            }
                        }
                        if (Modifier.isStatic(method.getModifiers())) {
                            if (!hashtable2.containsKey(gm)) {
                                final Vector<Method> vector2 = new Vector<Method>();
                                vector2.addElement(method);
                                hashtable2.put(gm, vector2);
                                if (!l) {
                                    break Label_0398;
                                }
                            }
                            hashtable2.get(gm).addElement(method);
                            if (!l) {
                                break Label_0398;
                            }
                        }
                        if (!hashtable.containsKey(gm)) {
                            final Vector<Method> vector3 = new Vector<Method>();
                            vector3.addElement(method);
                            hashtable.put(gm, vector3);
                            if (!l) {
                                break Label_0398;
                            }
                        }
                        hashtable.get(gm).addElement(method);
                    }
                }
                ++n;
            }
            if (n < declaredMethods.length) {
                continue;
            }
            break;
        }
        final Enumeration<String> keys = hashtable.keys();
        Vector vector5;
        Vector vector6;
        Enumeration<String> enumeration;
        while (true) {
            while (true) {
                Label_0515: {
                    if (!l) {
                        break Label_0515;
                    }
                    final Object o = keys.nextElement();
                    final String s3 = (String)o;
                    final Vector<Method> vector4 = hashtable.get(s3);
                    final Method[] array2 = new Method[vector4.size()];
                    int n3 = 0;
                    while (true) {
                        Label_0481: {
                            if (!l) {
                                break Label_0481;
                            }
                            array2[n3] = vector4.elementAt(n3);
                            ++n3;
                        }
                        if (n3 < array2.length) {
                            continue;
                        }
                        break;
                    }
                    this.a((c)new as(s3), new ar((Class)s2, array2));
                }
                if (keys.hasMoreElements()) {
                    continue;
                }
                break;
            }
            vector5 = new Vector<Method>();
            vector6 = new Vector<Method>();
            Object o;
            enumeration = (Enumeration<String>)(o = hashtable3.keys());
            if (l) {
                continue;
            }
            break;
        }
        final Enumeration<String> enumeration2 = enumeration;
        Constructor[] constructors;
        Vector vector8;
        while (true) {
        Label_0599_Outer:
            while (true) {
                Label_0889: {
                    if (!l) {
                        break Label_0889;
                    }
                    final String nextElement = enumeration2.nextElement();
                    final String s4 = nextElement;
                    final Vector<Method> vector7 = hashtable3.get(s4);
                    int n4 = 0;
                    int size;
                    Method method3;
                    while (true) {
                    Label_0640_Outer:
                        while (true) {
                            Label_0719: {
                                if (!l) {
                                    break Label_0719;
                                }
                                final Object element = vector7.elementAt(n4);
                                final Method method2 = (Method)element;
                                final Class<?>[] parameterTypes2 = method2.getParameterTypes();
                                final String[] array3 = new String[parameterTypes2.length];
                                int n5 = 0;
                                String gm2;
                                while (true) {
                                    while (true) {
                                        Label_0643: {
                                            if (!l) {
                                                break Label_0643;
                                            }
                                            array3[n5] = parameterTypes2[n5].getName();
                                            ++n5;
                                        }
                                        if (n5 < parameterTypes2.length) {
                                            continue Label_0640_Outer;
                                        }
                                        break;
                                    }
                                    gm2 = blaze3d.b.gm(((Class)s2).getName(), method2.getName(), array3);
                                    (new Method[1])[0] = method2;
                                    if (l) {
                                        continue;
                                    }
                                    break;
                                }
                                Label_0716: {
                                    if (gm2.startsWith("_g_")) {
                                        vector5.addElement(method2);
                                        if (!l) {
                                            break Label_0716;
                                        }
                                    }
                                    vector6.addElement(method2);
                                }
                                ++n4;
                            }
                            if (n4 < vector7.size()) {
                                continue Label_0599_Outer;
                            }
                            break;
                        }
                        size = vector5.size();
                        Object element;
                        method3 = (Method)(element = vector6);
                        if (l) {
                            continue;
                        }
                        break;
                    }
                    final int size2 = ((Vector)method3).size();
                    Method[] array4 = null;
                    Method[] array5 = null;
                    if (size != 0) {
                        array4 = new Method[size];
                        int n6 = 0;
                        while (true) {
                            Label_0792: {
                                if (!l) {
                                    break Label_0792;
                                }
                                array4[n6] = vector5.elementAt(n6);
                                ++n6;
                            }
                            if (n6 < size) {
                                continue;
                            }
                            break;
                        }
                    }
                    if (size2 != 0) {
                        array5 = new Method[size2];
                        int n7 = 0;
                        while (true) {
                            Label_0837: {
                                if (!l) {
                                    break Label_0837;
                                }
                                array5[n7] = vector6.elementAt(n7);
                                ++n7;
                            }
                            if (n7 < size2) {
                                continue;
                            }
                            break;
                        }
                    }
                    vector5.removeAllElements();
                    vector6.removeAllElements();
                    this.a(s4, new ar((Class)s2, array4), new ar((Class)s2, array5));
                }
                if (enumeration2.hasMoreElements()) {
                    continue;
                }
                break;
            }
            constructors = ((Class)s2).getConstructors();
            String nextElement;
            vector8 = (Vector)(nextElement = (String)new Vector());
            if (l) {
                continue;
            }
            break;
        }
        final Vector vector9 = vector8;
        int n8 = 0;
        while (true) {
            Label_0964: {
                if (!l) {
                    break Label_0964;
                }
                final Constructor constructor = constructors[n8];
                if (Modifier.isPublic(constructor.getModifiers()) && constructor.getDeclaringClass() == s2) {
                    vector9.addElement(constructor);
                }
                ++n8;
            }
            if (n8 < constructors.length) {
                continue;
            }
            break;
        }
        if (vector9.isEmpty()) {
            if (c != null) {
                c.a((c)new as(s), this);
            }
            return this;
        }
        final Constructor[] h = new Constructor[vector9.size()];
        int n9 = 0;
        c f;
        while (true) {
            while (true) {
                Label_1035: {
                    if (!l) {
                        break Label_1035;
                    }
                    h[n9] = vector9.elementAt(n9);
                    ++n9;
                }
                if (n9 < h.length) {
                    continue;
                }
                break;
            }
            f = c.f(s);
            if (l) {
                continue;
            }
            break;
        }
        ar ar = null;
        Label_1109: {
            if (f.a == 6) {
                ar = (ar)f;
                ar.h = h;
                if (!l) {
                    break Label_1109;
                }
            }
            c.a((c)new as(s), ar = new ar((Class)s2, h));
        }
        ar.a(this);
        final Enumeration<String> keys2 = hashtable2.keys();
        while (true) {
            while (true) {
                Label_1222: {
                    if (!l) {
                        break Label_1222;
                    }
                    final Object o2 = keys2.nextElement();
                    final String s5 = (String)o2;
                    final Vector<Method> vector10 = hashtable2.get(s5);
                    final Method[] array6 = new Method[vector10.size()];
                    int n10 = 0;
                    while (true) {
                        Label_1187: {
                            if (!l) {
                                break Label_1187;
                            }
                            array6[n10] = vector10.elementAt(n10);
                            ++n10;
                        }
                        if (n10 < array6.length) {
                            continue;
                        }
                        break;
                    }
                    ar.a((c)new as(s5), new ar((Class)s2, array6));
                }
                if (keys2.hasMoreElements()) {
                    continue;
                }
                break;
            }
            Object o2;
            final h h2 = (h)(o2 = this.c);
            if (!l) {
                if (h2 instanceof ax) {
                    ((ax)this.c).a(ar);
                }
                return ar;
            }
            continue;
        }
    }
    
    boolean a(final as as, final boolean b) {
        if (!this.b) {
            this.ad();
        }
        return this.d.a(as, b) != null || (this.e != d.d && this.e.a(as, b));
    }
    
    c b(final as as, final boolean b) {
        if (!this.b) {
            this.ad();
        }
        final c c = this.c(as.a);
        if (c != null) {
            return c;
        }
        final c a = this.d.a(as, b);
        if (a != null || this.e == d.d || this.e == null) {
            return a;
        }
        return this.e.b(as, b);
    }
    
    void a(final as as, c a) {
        if (as.a.equals("")) {
            return;
        }
        if (!this.b) {
            this.ad();
        }
        if (as.a.equals("__proto__")) {
            this.e = a;
        }
        if (this.a(as.a, a)) {
            if (this.i != null) {
                c4 c4 = this.i;
                while (true) {
                    Label_0146: {
                        if (!c.l) {
                            break Label_0146;
                        }
                        if (c4.a.equals(as.a)) {
                            final au au = new au();
                            au.a(as);
                            au.a(this.b(as, false));
                            au.a(a);
                            au.a(c4.c);
                            a = c4.b.a(this, au);
                        }
                        c4 = c4.d;
                    }
                    if (c4 != null) {
                        continue;
                    }
                    break;
                }
            }
            this.d.a(as, a);
        }
    }
    
    boolean c(final as as, final boolean b) {
        if (!this.b) {
            this.ad();
        }
        if (this.d.a(as, b) == null) {
            return true;
        }
        this.d.a(as);
        return true;
    }
    
    void a(final String s, final c c, final boolean b) {
        this.a(new as(s), c, b);
    }
    
    void a(final c c, final c c2, final boolean b) {
        this.a(new as(c.toString()), c2, b);
    }
    
    void a(final as as, final c c, final boolean b) {
        final boolean l = c.l;
        if (!this.b) {
            this.ad();
        }
        final c b2 = this.b(as, b);
        if (b2 != null || this.a == 7) {
            if (b2 != null && b2.h != null) {
                final au au = new au();
                au.a(c);
                b2.h.b(this, au, null, false);
                if (!l) {
                    return;
                }
            }
            if (as.a.indexOf(".") != -1) {
                this.b(as, c, b);
                if (!l) {
                    return;
                }
            }
            this.a(as, c);
            if (!l) {
                return;
            }
        }
        this.f.a(as, c, b);
    }
    
    void c(final String s, final c c) {
        this.a((c)new as(s), c);
    }
    
    void a(final c c, final c c2) {
        final boolean l = c.l;
        if (c == d.d || c.a == 0) {
            return;
        }
        as as = null;
        Label_0050: {
            if (c.a == 4) {
                as = (as)c;
                if (!l) {
                    break Label_0050;
                }
            }
            as = new as(c.toString());
        }
        final c b = this.b(as, false);
        if (b != null && b.h != null) {
            final au au = new au();
            au.a(c2);
            b.h.b(this, au, null, false);
            if (!l) {
                return;
            }
        }
        this.a(as, c2);
    }
    
    c a(final c c, final boolean b) {
        return this.d(new as(c.toString()), b);
    }
    
    c d(final as as, final boolean b) {
        if (!this.b) {
            this.ad();
        }
        if (this.d.a(as, b) == null && this.a != 7 && this.f != d.d) {
            return this.f.d(as, b);
        }
        if (this.c(as, b)) {
            return d.a;
        }
        return d.b;
    }
    
    c e(final String s) {
        return this.a(s, false);
    }
    
    c a(final String s, final boolean b) {
        return this.e(new as(s), b);
    }
    
    c b(final c c, final boolean b) {
        if (c == d.d || c.a == 0) {
            return d.b;
        }
        return this.e(new as(c.toString()), b);
    }
    
    c e(final as as, final boolean b) {
        if (this.c(as, b)) {
            return d.a;
        }
        return d.b;
    }
    
    c b(final String s, final boolean b) {
        return this.f(new as(s), b);
    }
    
    c c(final c c, final boolean b) {
        return this.f(new as(c.toString()), b);
    }
    
    c f(final as as, final boolean b) {
        if (this.a(as, b)) {
            return this.d((c)as, b);
        }
        if (this.f == null) {
            if (b) {
                return d.f;
            }
            return d.e;
        }
        else {
            final c f = this.f.f(as, b);
            if (f.a == 0 && as.a.indexOf(".") != -1) {
                return this.g(as, b);
            }
            return f;
        }
    }
    
    c g(final as as, final boolean b) {
        final boolean l = c.l;
        final StringTokenizer stringTokenizer = new StringTokenizer(as.a, ".");
        int n = 1;
        c c = b ? d.f : d.e;
        while (true) {
            Label_0084: {
                if (!l) {
                    break Label_0084;
                }
                final String nextToken = stringTokenizer.nextToken();
                if (n != 0) {
                    c = this.b(nextToken, b);
                    n = 0;
                    if (!l) {
                        break Label_0084;
                    }
                }
                c = c.c(nextToken, b);
            }
            if (!stringTokenizer.hasMoreTokens()) {
                return c;
            }
            continue;
        }
    }
    
    void b(final as as, final c c, final boolean b) {
        final boolean l = c.l;
        final StringTokenizer stringTokenizer = new StringTokenizer(as.a, ".");
        int n = 1;
        c c2 = b ? d.f : d.e;
        while (true) {
            Label_0107: {
                if (!l) {
                    break Label_0107;
                }
                final String nextToken = stringTokenizer.nextToken();
                if (stringTokenizer.hasMoreTokens()) {
                    if (n != 0) {
                        c2 = this.b(nextToken, b);
                        n = 0;
                        if (!l) {
                            break Label_0107;
                        }
                    }
                    c2 = c2.c(nextToken, b);
                    if (!l) {
                        break Label_0107;
                    }
                }
                c2.c(nextToken, c);
            }
            if (!stringTokenizer.hasMoreTokens()) {
                return;
            }
            continue;
        }
    }
    
    c f(final String s) {
        return this.c(s, false);
    }
    
    c c(final String s, final boolean b) {
        return this.d((c)new as(s), b);
    }
    
    c d(final c c, final boolean b) {
        as as = null;
        Label_0031: {
            if (c.a == 4) {
                as = (as)c;
                if (!c.l) {
                    break Label_0031;
                }
            }
            as = new as(c.toString());
        }
        final c b2 = this.b(as, b);
        if (b2 == null) {
            if (!as.toString().equals("__resolve")) {
                final c c2 = this.c("__resolve", b);
                if (c2 != null && c2.a == 6) {
                    final au au = new au();
                    au.a(as);
                    return ((ar)c2).b(this, au, null, b);
                }
            }
            if (b) {
                return d.f;
            }
            return d.e;
        }
        else {
            if (b2.g == null) {
                return b2;
            }
            c a = null;
            if ((b2.g.p & 0x20) == 0x0) {
                a = ((ar)this.h(as, b).f("constructor")).a();
            }
            return b2.g.b(this, null, a, b);
        }
    }
    
    c a(final c c, final au au, final boolean b) {
        return this.a(new as(c.toString()), au, b);
    }
    
    c a(final as as, final au au, final boolean b) {
        final c f = this.f(as, b);
        if (f.a == 6) {
            return ((ar)f).a(au, b);
        }
        if (b) {
            return d.f;
        }
        return d.e;
    }
    
    c a(final String s, final au au, final boolean b) {
        return this.b((c)new as(s), au, b);
    }
    
    c b(final c c, final au au, final boolean b) {
        as as = null;
        Label_0033: {
            if (c.a == 4) {
                as = (as)c;
                if (!c.l) {
                    break Label_0033;
                }
            }
            as = new as(c.toString());
        }
        if (this.a(as, b)) {
            return this.c(as, au, b);
        }
        if (this.f != null) {
            return this.f.b((c)as, au, b);
        }
        if (b) {
            return d.f;
        }
        return d.e;
    }
    
    c a(final String s, final au au) {
        return this.b(s, au, false);
    }
    
    c b(final String s, final au au, final boolean b) {
        return this.c(new as(s), au, b);
    }
    
    c c(final c c, final au au, final boolean b) {
        as as = null;
        Label_0033: {
            if (c.a == 4) {
                as = (as)c;
                if (!c.l) {
                    break Label_0033;
                }
            }
            as = new as(c.toString());
        }
        final c d = this.d((c)as, b);
        if (d.a == 6) {
            c a = null;
            final ar ar = (ar)d;
            if ((ar.p & 0x20) == 0x0) {
                a = ((ar)this.h(as, b).f("constructor")).a();
            }
            return ar.b(this, au, a, b);
        }
        if (b) {
            return d.f;
        }
        return d.e;
    }
    
    c h(final as as, final boolean b) {
        if (this.c(as.a) != null) {
            return this;
        }
        if (this.d.a(as, b) != null) {
            return this;
        }
        if (this.e != d.d) {
            return this.e.h(as, b);
        }
        if (b) {
            return d.f;
        }
        return d.e;
    }
    
    c a(final c c, final au au, final c c2, final boolean b) {
        if (c.a == 6) {
            return ((ar)c).b(this, au, c2, b);
        }
        if (b) {
            return d.f;
        }
        return d.e;
    }
    
    av d(final c c) {
        if (c.a != 6) {
            return d.b;
        }
        if (!this.b) {
            this.ad();
        }
        if (this.e == d.d) {
            return d.b;
        }
        if (this.e.c("constructor", false) == c) {
            return d.a;
        }
        return this.e.d(c);
    }
    
    as ae() {
        if (this.d == null) {
            return null;
        }
        final String b = this.d.b();
        if (b == null) {
            return null;
        }
        return new as(b);
    }
    
    String r() {
        return "object";
    }
    
    c h(final int n) {
        if (n == 3) {
            return new aq(Float.NaN);
        }
        if (this.c != null) {
            return new as(this.c.toString());
        }
        return new as(this.toString());
    }
    
    public String toString() {
        if (this.c != null) {
            return this.c.toString();
        }
        return "object";
    }
    
    float af() {
        return Float.NaN;
    }
    
    boolean ag() {
        return true;
    }
    
    public boolean a(final String s, final ar g, final ar h) {
        final c c = new c();
        c.g = g;
        c.h = h;
        this.a(new as(s), c);
        return true;
    }
    
    static {
        c.j = new Vector();
        k = new as("__proto__");
    }
}
