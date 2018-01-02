import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

// 
// Decompiled by Procyon v0.5.30
// 

public class ar extends c
{
    static Class a;
    blaze3d b;
    ar c;
    c d;
    boolean e;
    Class f;
    Method[] g;
    Constructor[] h;
    v i;
    int j;
    int k;
    int l;
    int[] m;
    String[] n;
    int p;
    int q;
    static final as r;
    static final as s;
    static final as t;
    static final as u;
    static final as v;
    static /* synthetic */ Class w;
    
    c a(final aa aa) {
        return aa.g;
    }
    
    public ar() {
        this.f = null;
        super.a = 6;
        this.b = blaze3d.a();
    }
    
    ar(final v i, final int j, final int k, final c f, final int q, final int l, final int[] m, final String[] n, final int p9) {
        this();
        this.e = false;
        this.i = i;
        this.j = j;
        this.k = k;
        super.f = f;
        this.q = q;
        this.l = l;
        this.m = m;
        this.n = n;
        this.p = p9;
        this.c = null;
        if (f != null) {
            this.a(new c());
        }
    }
    
    ar(final Class f, final Method[] g) {
        this();
        this.f = f;
        this.g = g;
        this.e = true;
        this.p = 42;
        this.a(new c());
    }
    
    ar(final Class f, final Constructor[] h) {
        this();
        this.f = f;
        this.h = h;
        this.e = true;
        this.p = 42;
        this.a(new c());
    }
    
    public boolean a(final String s, final c d) {
        if (s.toLowerCase().equals("prototype")) {
            (this.d = d).c("constructor", this);
        }
        return true;
    }
    
    void a(final c c) {
        this.c("prototype", c);
    }
    
    c a() {
        c c = d.d;
        try {
            c = (c)this.d.getClass().newInstance();
            if (this.d.c != null) {
                final h c2 = (h)this.d.c.getClass().newInstance();
                if (c2.a() == null) {
                    c2.a(c);
                }
                c = c2.a();
                c.c = c2;
            }
            c.c(this.d);
            c.c("__constructor__", this);
        }
        catch (Exception ex) {}
        return c;
    }
    
    c a(final au au, final boolean b) {
        if (this == this.b.ak.k && au != null && au.b.size() != 0) {
            return au.b(0);
        }
        c c;
        if (this.h != null) {
            c = this.b(au, b);
        }
        else {
            c = this.a();
            this.b(c, au, c, b);
        }
        return c;
    }
    
    c b(final c c, au au, final c c2, final boolean b) {
        if (!this.e) {
            final c c3 = new c();
            c3.f = super.f;
            c a = null;
            if (c2 != null) {
                a = ((ar)c2.d((c)c.k, false).d((c)c.k, false).d((c)ar.v, false)).a();
            }
            c3.a((c)ar.r, c);
            if (au == null) {
                final int a2 = this.b.a3;
                Label_0153: {
                    if (this.b.a2[a2] == null) {
                        this.b.a2[a2] = new au();
                        if (!c.l) {
                            break Label_0153;
                        }
                    }
                    this.b.a2[a2].a();
                }
                au = this.b.a2[a2];
            }
            au.a((c)ar.u, this);
            c3.a((c)ar.s, au);
            final blaze3d b2 = this.b;
            ++b2.a3;
            final c a3 = this.a(c, au, a, c3);
            final blaze3d b3 = this.b;
            --b3.a3;
            this.b.a2[this.b.a3] = null;
            return a3;
        }
        if (this.g != null) {
            return this.d(c, au, b);
        }
        return b ? d.f : d.e;
    }
    
    public c a(final c c, final au au) {
        return this.b(c, au, null, false);
    }
    
    c b(final c c, final au au) {
        return this.a(c, au, null, c);
    }
    
    c a(final c c, final au au, final c c2, final c c3) {
        final boolean l = c.l;
        c[] array = null;
        if (this.q > 0) {
            array = new c[this.q];
        }
        int n = 0;
        while (true) {
            while (true) {
                Label_0087: {
                    if (!l) {
                        break Label_0087;
                    }
                    final int n2 = this.m[n];
                    final int n4;
                    final int n3 = n4;
                    final String s = this.n[n];
                    Label_0084: {
                        if (n3 != 0) {
                            array[n3] = au.b(n);
                            if (!l) {
                                break Label_0084;
                            }
                        }
                        c3.c(s, au.b(n));
                    }
                    ++n;
                }
                if (n < this.l) {
                    continue;
                }
                break;
            }
            int n5 = 1;
            final int n4 = this.p & 0x1;
            if (!l) {
                if (n4 != 0) {
                    array[n5++] = c;
                }
                if ((this.p & 0x4) != 0x0) {
                    array[n5++] = au;
                }
                if ((this.p & 0x10) != 0x0) {
                    array[n5++] = c2;
                }
                if ((this.p & 0x40) != 0x0) {
                    array[n5++] = c3.b("_root", false);
                }
                if ((this.p & 0x80) != 0x0) {
                    array[n5++] = c3.b("_parent", false);
                }
                if ((this.p & 0x100) != 0x0) {
                    array[n5++] = this.b.ak;
                }
                return this.i.a(c, array, c2, c3, this.j, this.k, 0);
            }
            continue;
        }
    }
    
    private c d(final c c, final au au, final boolean b) {
        Method c2 = this.g[0];
        if (this.g.length > 1) {
            c2 = this.c(au);
        }
        if (c2 == null) {
            return b ? d.f : d.e;
        }
        final Object[] array = new Object[c2.getParameterTypes().length];
        Object c3 = c.c;
        if (c3 == null) {
            c3 = c;
        }
        Label_0171: {
            if (Modifier.isStatic(c2.getModifiers())) {
                c3 = null;
            }
            else if (!this.f.isAssignableFrom(c3.getClass())) {
                if (c.c != null && c2.getName().equals("toString")) {
                    return new as("object");
                }
                if (this.f.isAssignableFrom(c.getClass())) {
                    c3 = c;
                    if (!c.l) {
                        break Label_0171;
                    }
                }
                return b ? d.f : d.e;
            }
        }
        final Object[] a = a(c2.getParameterTypes(), au, b);
        Object invoke = null;
        try {
            invoke = c2.invoke(c3, a);
        }
        catch (IllegalAccessException ex) {}
        catch (InvocationTargetException ex2) {}
        if (invoke == null) {
            return b ? d.f : d.e;
        }
        final Class<?> returnType = c2.getReturnType();
        if (returnType == Integer.TYPE || returnType == Float.TYPE || returnType == Byte.TYPE || returnType == Double.TYPE || returnType == Short.TYPE || returnType == Long.TYPE) {
            return new aq(((Number)invoke).floatValue());
        }
        if (returnType == Character.TYPE) {
            return new as(((Character)invoke).toString());
        }
        if (returnType == Boolean.TYPE) {
            return new av((boolean)invoke);
        }
        if (returnType == Void.TYPE) {
            return b ? d.f : d.e;
        }
        if (returnType == ((ar.w == null) ? (ar.w = class$("java.lang.String")) : ar.w)) {
            return new as((String)invoke);
        }
        c a2;
        if (invoke instanceof h) {
            final h c4 = (h)invoke;
            a2 = c4.a();
            if (a2 == null) {
                a2 = new c();
                (a2.c = c4).a(a2);
            }
        }
        else {
            a2 = (c)invoke;
        }
        return a2;
    }
    
    private c b(final au au, final boolean b) {
        Object instance = null;
        Constructor a = this.h[0];
        if (this.h.length > 1) {
            a = a(this.h, au, b);
        }
        if (a == null) {
            return b ? d.f : d.e;
        }
        final Object[] a2 = a(a.getParameterTypes(), au, b);
        try {
            instance = a.newInstance(a2);
        }
        catch (InstantiationException ex) {}
        catch (IllegalAccessException ex2) {}
        catch (InvocationTargetException ex3) {}
        c a3;
        if (instance instanceof h) {
            final h c = (h)instance;
            if (c.a() == null) {
                c.a(new c());
            }
            a3 = c.a();
            a3.c = c;
            a3.e = this.d;
        }
        else {
            a3 = (c)instance;
        }
        return a3;
    }
    
    static Object[] a(final Class[] array, final au au, final boolean b) {
        final boolean l = c.l;
        final int length = array.length;
        final Object[] array2 = new Object[length];
        if (length == 1 && array[0] == ar.a) {
            array2[0] = au;
            return array2;
        }
        int n = 0;
        while (true) {
            Label_0478: {
                if (!l) {
                    break Label_0478;
                }
                final Class clazz = array[n];
                c b2 = b ? d.f : d.e;
                if (au != null) {
                    b2 = au.b(n);
                }
                Label_0475: {
                    if (clazz == Integer.TYPE) {
                        array2[n] = new Integer((int)b2.af());
                        if (!l) {
                            break Label_0475;
                        }
                    }
                    if (clazz == Float.TYPE) {
                        array2[n] = new Float(b2.af());
                        if (!l) {
                            break Label_0475;
                        }
                    }
                    if (clazz == Long.TYPE) {
                        array2[n] = new Long((long)b2.af());
                        if (!l) {
                            break Label_0475;
                        }
                    }
                    if (clazz == Boolean.TYPE) {
                        array2[n] = new Boolean(b2.ag());
                        if (!l) {
                            break Label_0475;
                        }
                    }
                    if (clazz == Double.TYPE) {
                        array2[n] = new Double(b2.af());
                        if (!l) {
                            break Label_0475;
                        }
                    }
                    if (clazz == Character.TYPE) {
                        array2[n] = new Character((char)b2.af());
                        if (!l) {
                            break Label_0475;
                        }
                    }
                    if (clazz == Short.TYPE) {
                        array2[n] = new Short((short)b2.af());
                        if (!l) {
                            break Label_0475;
                        }
                    }
                    if (clazz == Byte.TYPE) {
                        array2[n] = new Byte((byte)b2.af());
                        if (!l) {
                            break Label_0475;
                        }
                    }
                    if (clazz == Void.TYPE) {
                        array2[n] = null;
                        if (!l) {
                            break Label_0475;
                        }
                    }
                    if (b2.a == 0) {
                        array2[n] = null;
                        if (!l) {
                            break Label_0475;
                        }
                    }
                    if (clazz == ((ar.w == null) ? (ar.w = class$("java.lang.String")) : ar.w)) {
                        array2[n] = b2.toString();
                        if (!l) {
                            break Label_0475;
                        }
                    }
                    if (b2.c != null && clazz.isAssignableFrom(b2.c.getClass())) {
                        array2[n] = b2.c;
                        if (!l) {
                            break Label_0475;
                        }
                    }
                    if (clazz.isAssignableFrom(((bn)b2).getClass())) {
                        array2[n] = b2;
                        if (!l) {
                            break Label_0475;
                        }
                    }
                    array2[n] = null;
                }
                ++n;
            }
            if (n >= length) {
                return array2;
            }
            continue;
        }
    }
    
    private Method c(final au au) {
        final boolean l = c.l;
        Method method = null;
        Method method2 = null;
        int size = 0;
        if (au != null) {
            size = au.b.size();
        }
        int n = -1;
        int n2 = 0;
        Method method3 = null;
        while (true) {
            if (n2 >= this.g.length) {
                method3 = method;
                if (!l) {
                    break;
                }
            }
            else {
                final Method method4 = this.g[n2];
            }
            final Method method5 = method3;
            final Class<?>[] parameterTypes = method5.getParameterTypes();
            final int length = parameterTypes.length;
            Label_0314: {
                if (length == 1 && parameterTypes[0] == ar.a) {
                    method2 = method5;
                    if (!l) {
                        break Label_0314;
                    }
                }
                int n3 = 0;
                int n4 = 0;
                while (true) {
                    Label_0276: {
                        if (!l) {
                            break Label_0276;
                        }
                        final Class<?> clazz = parameterTypes[n4];
                        c c = d.e;
                        if (au != null) {
                            c = au.b(n4);
                        }
                        final h c2 = c.c;
                        if (clazz.isPrimitive() && (c instanceof aq || c instanceof av)) {
                            ++n3;
                        }
                        Label_0273: {
                            if (clazz == ((ar.w == null) ? (ar.w = class$("java.lang.String")) : ar.w) && c instanceof as) {
                                ++n3;
                                if (!l) {
                                    break Label_0273;
                                }
                            }
                            if (!clazz.isPrimitive() && c2 == null && clazz.isAssignableFrom(((bn)c).getClass())) {
                                ++n3;
                                if (!l) {
                                    break Label_0273;
                                }
                            }
                            if (!clazz.isPrimitive() && c2 != null && clazz.isAssignableFrom(c2.getClass())) {
                                ++n3;
                                if (!l) {
                                    break Label_0273;
                                }
                            }
                            if (!clazz.isPrimitive()) {
                                n3 = -100;
                            }
                        }
                        ++n4;
                    }
                    if (n4 < length) {
                        continue;
                    }
                    break;
                }
                if (n3 == length && length == size) {
                    n3 += 100;
                }
                if (n3 > n) {
                    method = method5;
                    n = n3;
                }
            }
            ++n2;
        }
        if (method3 == null) {
            return method2;
        }
        if (method.getParameterTypes().length < size && method2 != null) {
            return method2;
        }
        return method;
    }
    
    static Constructor a(final Constructor[] array, final au au, final boolean b) {
        final boolean l = c.l;
        Constructor constructor = null;
        Constructor constructor2 = null;
        int size = 0;
        if (au != null) {
            size = au.b.size();
        }
        int n = -1;
        int n2 = 0;
        Constructor constructor3 = null;
        while (true) {
            if (n2 >= array.length) {
                constructor3 = constructor;
                if (!l) {
                    break;
                }
            }
            else {
                final Constructor constructor4 = array[n2];
            }
            final Constructor constructor5 = constructor3;
            final Class[] parameterTypes = constructor5.getParameterTypes();
            final int length = parameterTypes.length;
            Label_0370: {
                if (length == 1 && parameterTypes[0] == ar.a) {
                    constructor2 = constructor5;
                    if (!l) {
                        break Label_0370;
                    }
                }
                int n3 = 0;
                if (length == size) {
                    ++n3;
                }
                int n4 = 0;
                while (true) {
                    Label_0337: {
                        if (!l) {
                            break Label_0337;
                        }
                        final Class clazz = parameterTypes[n4];
                        c b2 = b ? d.f : d.e;
                        if (au != null) {
                            b2 = au.b(n4);
                        }
                        final h c = b2.c;
                        if (clazz.isPrimitive() && (b2 instanceof aq || b2 instanceof av)) {
                            ++n3;
                        }
                        Label_0334: {
                            if (clazz == ((ar.w == null) ? (ar.w = class$("java.lang.String")) : ar.w) && b2 instanceof as) {
                                ++n3;
                                if (!l) {
                                    break Label_0334;
                                }
                            }
                            if (!clazz.isPrimitive() && c == null && clazz.isAssignableFrom(((bm)b2).getClass())) {
                                ++n3;
                                if (!l) {
                                    break Label_0334;
                                }
                            }
                            if (!clazz.isPrimitive() && c != null && clazz.isAssignableFrom(c.getClass())) {
                                ++n3;
                                if (!l) {
                                    break Label_0334;
                                }
                            }
                            if (!clazz.isPrimitive() && clazz != ((ar.w == null) ? (ar.w = class$("java.lang.String")) : ar.w) && b2.a != 0 && b2 != d.d) {
                                n3 = -100;
                            }
                        }
                        ++n4;
                    }
                    if (n4 < length) {
                        continue;
                    }
                    break;
                }
                if (n3 == length + 1) {
                    n3 += 100;
                }
                if (n3 > n) {
                    constructor = constructor5;
                    n = n3;
                }
            }
            ++n2;
        }
        if (constructor3 == null) {
            return constructor2;
        }
        if (constructor.getParameterTypes().length < size && constructor2 != null) {
            return constructor2;
        }
        return constructor;
    }
    
    String r() {
        return "function";
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        ar.a = new au().getClass();
        r = new as("this");
        s = new as("arguments");
        t = new as("super");
        u = new as("callee");
        v = new as("constructor");
    }
}
