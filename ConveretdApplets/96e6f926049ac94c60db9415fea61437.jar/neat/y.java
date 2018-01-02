// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import neat.system.rb;
import java.lang.reflect.Method;
import neat.system.f;
import neat.system.cb;

public class y implements cb
{
    private static f a;
    private Class b;
    private kb c;
    private Method d;
    private int e;
    private Class[] f;
    private kb g;
    private static /* synthetic */ Class h;
    private static /* synthetic */ Class i;
    private static String[] z;
    
    private boolean a(final Class b, final kb kb, final Class[] array, final int e) {
        this.b = b;
        this.c = kb.b();
        if (this.f.length != e) {
            y.a.a(this.f);
            this.f = y.a.e(e);
        }
        this.e = e;
        for (int i = 0; i < e; ++i) {
            if (array[i] == null) {
                throw new RuntimeException(y.z[1] + i + ")");
            }
            this.f[i] = array[i];
        }
        this.d();
        return this.a();
    }
    
    private boolean a() {
        this.d = null;
        try {
            this.d = this.b.getMethod(this.c.toString(), (Class[])this.f);
        }
        catch (NoSuchMethodException ex) {
            return false;
        }
        return this.b();
    }
    
    public boolean b() {
        return this.d != null;
    }
    
    public Object a(final Object o, final Object[] array) {
        if (!this.b()) {
            throw new RuntimeException(y.z[6] + this.c + y.z[7]);
        }
        try {
            return this.d.invoke(o, array);
        }
        catch (InvocationTargetException ex) {
            final Throwable targetException = ex.getTargetException();
            if (targetException.getClass() == ((y.i != null) ? y.i : (y.i = a(y.z[3])))) {
                throw (rb)targetException;
            }
            throw new RuntimeException(y.z[8] + this.c + y.z[2] + targetException + y.z[4] + nb.a(targetException));
        }
        catch (IllegalAccessException ex2) {
            throw new RuntimeException(y.z[5] + this.c + y.z[7]);
        }
    }
    
    public static kb b(final Class clazz, final kb kb, final Class[] array, final int n) {
        final lb a = lb.a();
        a.c(clazz.getName());
        a.a('.');
        a.a(kb);
        a.a('(');
        for (int i = 0; i < n; ++i) {
            if (i > 0) {
                a.a(';');
            }
            a.c(array[i].getName());
        }
        a.a(')');
        return a.b();
    }
    
    public kb c() {
        if (this.g == null) {
            this.g = b(this.b, this.c, this.f, this.e);
        }
        return this.g;
    }
    
    private void d() {
        if (this.g != null) {
            this.g.f();
            this.g = null;
        }
    }
    
    public int hashCode() {
        return this.c().hashCode();
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        throw new NotSerializableException(((y.h != null) ? y.h : (y.h = a(y.z[0]))).getName());
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        throw new NotSerializableException(((y.h != null) ? y.h : (y.h = a(y.z[0]))).getName());
    }
    
    public static y c(final Class clazz, final kb kb, final Class[] array, final int n) {
        final y y = (y)neat.y.a.a();
        y.a(clazz, kb, array, n);
        return y;
    }
    
    public void f() {
        y.a.a(this);
    }
    
    public void g() {
    }
    
    public void h() {
        this.b = null;
        if (this.c != null) {
            this.c.f();
            this.c = null;
        }
        this.d = null;
        if (this.f != null) {
            for (int i = 0; i < this.f.length; ++i) {
                this.f[i] = null;
            }
        }
        this.d();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public y() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = 0;
        this.f = y.a.e(0);
        this.g = null;
    }
    
    static {
        final String[] z = new String[9];
        final int n = 0;
        final char[] charArray = "rfv\u0006\u0016e".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0097: {
                if (n2 > 1) {
                    break Label_0097;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u001c';
                            break;
                        }
                        case 1: {
                            c2 = '\u0003';
                            break;
                        }
                        case 2: {
                            c2 = '\u0017';
                            break;
                        }
                        case 3: {
                            c2 = 'r';
                            break;
                        }
                        default: {
                            c2 = '8';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "Lbe\u0013Uywr\u0000\u001fo#c\u000bHy#t\u0013V<mx\u0006\u0018~f7\u0013\u0018rv{\u001e\u0018_ov\u0001K<\"7ZQrgr\n\u0002".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0213: {
                if (n6 > 1) {
                    break Label_0213;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u001c';
                            break;
                        }
                        case 1: {
                            c4 = '\u0003';
                            break;
                        }
                        case 2: {
                            c4 = '\u0017';
                            break;
                        }
                        case 3: {
                            c4 = 'r';
                            break;
                        }
                        default: {
                            c4 = '8';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "<\"\u001dRltf7\u0017@\u007ffg\u0006Qsm7\u0005Yo9".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0329: {
                if (n10 > 1) {
                    break Label_0329;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\u001c';
                            break;
                        }
                        case 1: {
                            c6 = '\u0003';
                            break;
                        }
                        case 2: {
                            c6 = '\u0017';
                            break;
                        }
                        case 3: {
                            c6 = 'r';
                            break;
                        }
                        default: {
                            c6 = '8';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "rfv\u0006\u0016ozd\u0006]q-e\u0010".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0445: {
                if (n14 > 1) {
                    break Label_0445;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '\u001c';
                            break;
                        }
                        case 1: {
                            c8 = '\u0003';
                            break;
                        }
                        case 2: {
                            c8 = '\u0017';
                            break;
                        }
                        case 3: {
                            c8 = 'r';
                            break;
                        }
                        default: {
                            c8 = '8';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        z[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "2#D\u0006Y\u007fh7\u0006J}`rRQo#g\u0000Qrwr\u0016\u0018svcH2".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0561: {
                if (n18 > 1) {
                    break Label_0561;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '\u001c';
                            break;
                        }
                        case 1: {
                            c10 = '\u0003';
                            break;
                        }
                        case 2: {
                            c10 = '\u0017';
                            break;
                        }
                        case 3: {
                            c10 = 'r';
                            break;
                        }
                        default: {
                            c10 = '8';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        z[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "Hk~\u0001\u0018qfc\u001aWx#z\u0007Kh#u\u0017\u0018lvu\u001eQ\u007f9".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0677: {
                if (n22 > 1) {
                    break Label_0677;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = '\u001c';
                            break;
                        }
                        case 1: {
                            c12 = '\u0003';
                            break;
                        }
                        case 2: {
                            c12 = '\u0017';
                            break;
                        }
                        case 3: {
                            c12 = 'r';
                            break;
                        }
                        default: {
                            c12 = '8';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        z[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "Hk~\u0001\u0018qfc\u001aWx#~\u0001\u0018rlcRYjb~\u001eY~orH".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0793: {
                if (n26 > 1) {
                    break Label_0793;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = '\u001c';
                            break;
                        }
                        case 1: {
                            c14 = '\u0003';
                            break;
                        }
                        case 2: {
                            c14 = '\u0017';
                            break;
                        }
                        case 3: {
                            c14 = 'r';
                            break;
                        }
                        default: {
                            c14 = '8';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        z[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "<\"".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0909: {
                if (n30 > 1) {
                    break Label_0909;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = '\u001c';
                            break;
                        }
                        case 1: {
                            c16 = '\u0003';
                            break;
                        }
                        case 2: {
                            c16 = '\u0017';
                            break;
                        }
                        case 3: {
                            c16 = 'r';
                            break;
                        }
                        default: {
                            c16 = '8';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        z[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "Y{t\u0017Hhjx\u001c\u0018up7\u0006Pnl`\u001c\u0018um7\u001f]hkx\u0016\u0002".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1025: {
                if (n34 > 1) {
                    break Label_1025;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = '\u001c';
                            break;
                        }
                        case 1: {
                            c18 = '\u0003';
                            break;
                        }
                        case 2: {
                            c18 = '\u0017';
                            break;
                        }
                        case 3: {
                            c18 = 'r';
                            break;
                        }
                        default: {
                            c18 = '8';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 <= n36) {
                z[n33] = new String(charArray9).intern();
                y.z = z;
                y.a = new f((y.h != null) ? y.h : (y.h = a(y.z[0])));
                return;
            }
            continue;
        }
    }
}
