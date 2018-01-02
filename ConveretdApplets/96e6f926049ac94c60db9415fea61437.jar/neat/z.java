// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import neat.system.f;
import neat.system.cb;

public class z implements cb
{
    private static f a;
    private Class b;
    private h c;
    private neat.y d;
    private static /* synthetic */ Class e;
    private static /* synthetic */ Class f;
    private static String[] z;
    
    public neat.y a(final Class clazz, final kb kb, final Class[] array, final int n) {
        z z = this;
        for (final Class b : array) {
            z a = (z)z.c.g(b);
            if (a == null) {
                a = a();
                a.b = b;
                z.c.a(b, a);
            }
            z = a;
        }
        if (z.d == null) {
            z.d = neat.y.c(clazz, kb, array, n);
        }
        return z.d;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        throw new NotSerializableException(((neat.z.f != null) ? neat.z.f : (neat.z.f = a(neat.z.z[0]))).getName());
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        throw new NotSerializableException(((neat.z.f != null) ? neat.z.f : (neat.z.f = a(neat.z.z[0]))).getName());
    }
    
    static z a() {
        return (z)neat.z.a.a();
    }
    
    public void f() {
        neat.z.a.a(this);
    }
    
    public void g() {
        this.c = h.e();
    }
    
    public void h() {
        this.b = null;
        this.c.c();
        this.c.f();
        this.c = null;
        if (this.d != null) {
            this.d.f();
            this.d = null;
        }
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public z() {
        this.b = null;
        this.c = null;
        this.d = null;
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "p\u0015;?bg".toCharArray();
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
                            c2 = '\u001e';
                            break;
                        }
                        case 1: {
                            c2 = 'p';
                            break;
                        }
                        case 2: {
                            c2 = 'Z';
                            break;
                        }
                        case 3: {
                            c2 = 'K';
                            break;
                        }
                        default: {
                            c2 = 'L';
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
        final char[] charArray2 = "p\u0015;?bd".toCharArray();
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
                            c4 = '\u001e';
                            break;
                        }
                        case 1: {
                            c4 = 'p';
                            break;
                        }
                        case 2: {
                            c4 = 'Z';
                            break;
                        }
                        case 3: {
                            c4 = 'K';
                            break;
                        }
                        default: {
                            c4 = 'L';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                z[n5] = new String(charArray2).intern();
                neat.z.z = z;
                neat.z.a = new f((neat.z.e != null) ? neat.z.e : (neat.z.e = a(neat.z.z[1])));
                return;
            }
            continue;
        }
    }
}
