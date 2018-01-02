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

public class w implements cb
{
    private static f a;
    private Class b;
    private kb c;
    private h d;
    private kb e;
    private h f;
    private Field[] g;
    private i h;
    private h i;
    private static /* synthetic */ Class j;
    private static /* synthetic */ Class k;
    private static String[] z;
    
    private void a(final Class b) {
        if (this.b == b) {
            return;
        }
        this.a();
        this.b = b;
        this.c = kb.a(b.getName());
    }
    
    private void a() {
        this.b = null;
        if (this.c != null) {
            this.c.f();
            this.c = null;
        }
        this.d.c();
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
        this.f.c();
        this.g = null;
        if (this.h != null) {
            this.h.j();
            this.h.f();
            this.h = null;
        }
        if (this.i != null) {
            this.i.c();
            this.i.f();
            this.i = null;
        }
    }
    
    public neat.y a(final kb kb, final Class[] array, final int n) {
        z a = (z)this.d.g(kb);
        if (a == null) {
            a = neat.z.a();
            this.d.a(kb.b(), a);
        }
        return a.a(this.b, kb, array, n);
    }
    
    public x a(final kb kb) {
        x c = (x)this.f.g(kb);
        if (c == null) {
            c = x.c(this.b, kb);
            this.f.a(c.c().b(), c);
        }
        return c;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        throw new NotSerializableException(((w.k != null) ? w.k : (w.k = a(w.z[0]))).getName());
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        throw new NotSerializableException(((w.k != null) ? w.k : (w.k = a(w.z[0]))).getName());
    }
    
    public static w b(final Class clazz) {
        final w w = (w)neat.w.a.a();
        w.a(clazz);
        return w;
    }
    
    public void f() {
        w.a.a(this);
    }
    
    public void g() {
        this.d = neat.h.e();
        this.f = neat.h.e();
    }
    
    public void h() {
        this.a();
        this.d.f();
        this.d = null;
        this.f.f();
        this.f = null;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public w() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "*C\u0016=\u0003=".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = 'D';
                            break;
                        }
                        case 1: {
                            c2 = '&';
                            break;
                        }
                        case 2: {
                            c2 = 'w';
                            break;
                        }
                        case 3: {
                            c2 = 'I';
                            break;
                        }
                        default: {
                            c2 = '-';
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
        final char[] charArray2 = "*C\u0016=\u00033".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = 'D';
                            break;
                        }
                        case 1: {
                            c4 = '&';
                            break;
                        }
                        case 2: {
                            c4 = 'w';
                            break;
                        }
                        case 3: {
                            c4 = 'I';
                            break;
                        }
                        default: {
                            c4 = '-';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                z[n5] = new String(charArray2).intern();
                w.z = z;
                w.a = new f((w.j != null) ? w.j : (w.j = a(w.z[1])));
                return;
            }
            continue;
        }
    }
}
