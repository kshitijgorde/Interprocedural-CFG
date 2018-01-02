import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.PermissionCollection;
import java.security.CodeSource;
import java.security.cert.Certificate;
import java.net.URL;
import java.security.Permission;
import java.security.AllPermission;
import java.security.Permissions;
import java.util.Hashtable;
import java.security.ProtectionDomain;

// 
// Decompiled by Procyon v0.5.30
// 

public final class a extends ClassLoader
{
    private final ProtectionDomain d;
    private final Hashtable c;
    private final Hashtable e;
    public static boolean b;
    public static boolean a;
    private static final String z;
    
    public a(final Hashtable hashtable) {
        final boolean n = loader.n;
        final Permissions permissions = new Permissions();
        permissions.add(new AllPermission());
        this.d = new ProtectionDomain(new CodeSource(null, (Certificate[])null), permissions);
        Hashtable e = hashtable;
        if (!n) {
            if (hashtable == null) {
                e = new Hashtable();
            }
            else {
                e = hashtable;
            }
        }
        this.e = e;
        this.c = new Hashtable();
        if (n) {
            a.b = !a.b;
        }
    }
    
    protected synchronized Class loadClass(final String s, final boolean b) throws ClassNotFoundException {
        final boolean n = loader.n;
        Class<?> defineClass = this.c.get(s);
        Object value;
        final Class<?> clazz = (Class<?>)(value = defineClass);
        if (!n) {
            if (clazz != null) {
                return defineClass;
            }
            value = this.e.get(s.replace('.', '/') + a.z);
        }
        final byte[] array = (byte[])value;
        Label_0096: {
            if (n) {
                break Label_0096;
            }
            if (array == null) {
                break Label_0096;
            }
            try {
                defineClass = this.defineClass(s, array, 0, array.length, this.d);
                if (!n) {
                    if (b) {
                        this.resolveClass(defineClass);
                    }
                    this.c.put(s, defineClass);
                }
            }
            catch (LinkageError linkageError) {}
            catch (Exception ex) {}
        }
        Class<?> systemClass;
        final Class<?> clazz2 = systemClass = defineClass;
        if (!n) {
            if (clazz2 == null) {
                systemClass = this.findSystemClass(s);
            }
            else {
                systemClass = defineClass;
            }
        }
        return systemClass;
    }
    
    public InputStream getResourceAsStream(final String s) {
        final byte[] array = this.e.get(s);
        return (array == null) ? ClassLoader.getSystemResourceAsStream(s) : new ByteArrayInputStream(array, 0, array.length);
    }
    
    public void a(final Class clazz) {
        this.c.put(clazz.getName(), clazz);
    }
    
    static {
        final char[] charArray = "q\r_\u0003R,".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0094: {
                if (n > 1) {
                    break Label_0094;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '_';
                            break;
                        }
                        case 1: {
                            c2 = 'n';
                            break;
                        }
                        case 2: {
                            c2 = '3';
                            break;
                        }
                        case 3: {
                            c2 = 'b';
                            break;
                        }
                        default: {
                            c2 = '!';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                z = new String(charArray).intern();
                return;
            }
            continue;
        }
    }
}
