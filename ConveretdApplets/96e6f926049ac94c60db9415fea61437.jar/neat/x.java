// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import neat.system.f;
import neat.system.cb;

public class x implements cb
{
    private static f a;
    private Class b;
    private kb c;
    private Field d;
    private kb e;
    private static /* synthetic */ Class f;
    private static /* synthetic */ Class g;
    private static String[] z;
    
    private boolean a(final Class b, final kb kb) {
        this.b = b;
        this.c = kb.b();
        this.i();
        return this.a();
    }
    
    private boolean a() {
        this.d = null;
        try {
            this.d = this.b.getField(this.c.toString());
        }
        catch (NoSuchFieldException ex) {
            return false;
        }
        return this.b();
    }
    
    public boolean b() {
        return this.d != null;
    }
    
    public kb c() {
        return this.c;
    }
    
    private RuntimeException a(final Exception ex) {
        ex.printStackTrace();
        if (ex instanceof IllegalAccessException) {
            return new RuntimeException(x.z[2] + this.c + x.z[5]);
        }
        if (ex instanceof IllegalArgumentException) {
            return new RuntimeException(x.z[4] + this.c + x.z[5]);
        }
        if (ex instanceof RuntimeException) {
            return (RuntimeException)ex;
        }
        return new RuntimeException(x.z[3] + this.c + x.z[1] + ex.toString() + ")");
    }
    
    public Class d() {
        return this.d.getType();
    }
    
    public void a(final Object o, final Object o2) {
        try {
            this.d.set(o, o2);
        }
        catch (Exception ex) {
            throw this.a(ex);
        }
    }
    
    public void a(final Object o, final boolean b) {
        try {
            this.d.setBoolean(o, b);
        }
        catch (Exception ex) {
            throw this.a(ex);
        }
    }
    
    public void a(final Object o, final byte b) {
        try {
            this.d.setByte(o, b);
        }
        catch (Exception ex) {
            throw this.a(ex);
        }
    }
    
    public void a(final Object o, final char c) {
        try {
            this.d.setChar(o, c);
        }
        catch (Exception ex) {
            throw this.a(ex);
        }
    }
    
    public void a(final Object o, final double n) {
        try {
            this.d.setDouble(o, n);
        }
        catch (Exception ex) {
            throw this.a(ex);
        }
    }
    
    public void a(final Object o, final float n) {
        try {
            this.d.setFloat(o, n);
        }
        catch (Exception ex) {
            throw this.a(ex);
        }
    }
    
    public void a(final Object o, final int n) {
        try {
            this.d.setInt(o, n);
        }
        catch (Exception ex) {
            throw this.a(ex);
        }
    }
    
    public void a(final Object o, final long n) {
        try {
            this.d.setLong(o, n);
        }
        catch (Exception ex) {
            throw this.a(ex);
        }
    }
    
    public void a(final Object o, final short n) {
        try {
            this.d.setShort(o, n);
        }
        catch (Exception ex) {
            throw this.a(ex);
        }
    }
    
    public static kb b(final Class clazz, final kb kb) {
        final lb a = lb.a();
        a.c(clazz.getName());
        a.a('.');
        a.a(kb);
        return a.b();
    }
    
    public kb e() {
        if (this.e == null) {
            this.e = b(this.b, this.c);
        }
        return this.e;
    }
    
    private void i() {
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
    }
    
    public int hashCode() {
        return this.e().hashCode();
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        throw new NotSerializableException(((x.g != null) ? x.g : (x.g = a(x.z[0]))).getName());
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        throw new NotSerializableException(((x.g != null) ? x.g : (x.g = a(x.z[0]))).getName());
    }
    
    public static x c(final Class clazz, final kb kb) {
        final x x = (x)neat.x.a.a();
        x.a(clazz, kb);
        return x;
    }
    
    public void f() {
        x.a.a(this);
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
        this.i();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public x() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
    }
    
    static {
        final String[] z = new String[7];
        final int n = 0;
        final char[] charArray = "EZvqOR".toCharArray();
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
                            c2 = '+';
                            break;
                        }
                        case 1: {
                            c2 = '?';
                            break;
                        }
                        case 2: {
                            c2 = '\u0017';
                            break;
                        }
                        case 3: {
                            c2 = '\u0005';
                            break;
                        }
                        default: {
                            c2 = 'a';
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
        final char[] charArray2 = "\u000b\u001e7-\u0004S\u0005".toCharArray();
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
                            c4 = '+';
                            break;
                        }
                        case 1: {
                            c4 = '?';
                            break;
                        }
                        case 2: {
                            c4 = '\u0017';
                            break;
                        }
                        case 3: {
                            c4 = '\u0005';
                            break;
                        }
                        default: {
                            c4 = 'a';
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
        final char[] charArray3 = "\u007fW~vAMVri\u0005\u000bRbv\u0015\u000b]r%\u0011^]{l\u0002\u0011".toCharArray();
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
                            c6 = '+';
                            break;
                        }
                        case 1: {
                            c6 = '?';
                            break;
                        }
                        case 2: {
                            c6 = '\u0017';
                            break;
                        }
                        case 3: {
                            c6 = '\u0005';
                            break;
                        }
                        default: {
                            c6 = 'a';
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
        final char[] charArray4 = "nGt`\u0011_VxkADQ7c\bNSs?".toCharArray();
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
                            c8 = '+';
                            break;
                        }
                        case 1: {
                            c8 = '?';
                            break;
                        }
                        case 2: {
                            c8 = '\u0017';
                            break;
                        }
                        case 3: {
                            c8 = '\u0005';
                            break;
                        }
                        default: {
                            c8 = 'a';
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
        final char[] charArray5 = "\u007fW~vAMVri\u0005\u000bQxqAJQ7l\u000fXKvk\u0002N\u001fxcAYZfp\u0004XKraA[^ed\fNKrw[".toCharArray();
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
                            c10 = '+';
                            break;
                        }
                        case 1: {
                            c10 = '?';
                            break;
                        }
                        case 2: {
                            c10 = '\u0017';
                            break;
                        }
                        case 3: {
                            c10 = '\u0005';
                            break;
                        }
                        default: {
                            c10 = 'a';
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
        final char[] charArray6 = "\u000b\u001e".toCharArray();
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
                            c12 = '+';
                            break;
                        }
                        case 1: {
                            c12 = '?';
                            break;
                        }
                        case 2: {
                            c12 = '\u0017';
                            break;
                        }
                        case 3: {
                            c12 = '\u0005';
                            break;
                        }
                        default: {
                            c12 = 'a';
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
        final char[] charArray7 = "EZvqOS".toCharArray();
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
                            c14 = '+';
                            break;
                        }
                        case 1: {
                            c14 = '?';
                            break;
                        }
                        case 2: {
                            c14 = '\u0017';
                            break;
                        }
                        case 3: {
                            c14 = '\u0005';
                            break;
                        }
                        default: {
                            c14 = 'a';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 <= n28) {
                z[n25] = new String(charArray7).intern();
                x.z = z;
                x.a = new f((x.f != null) ? x.f : (x.f = a(x.z[6])));
                return;
            }
            continue;
        }
    }
}
