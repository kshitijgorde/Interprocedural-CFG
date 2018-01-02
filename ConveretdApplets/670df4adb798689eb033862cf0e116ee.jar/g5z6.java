import java.security.AllPermission;
import java.security.Permission;
import java.security.Permissions;
import java.security.PermissionCollection;
import java.security.CodeSource;
import java.security.Policy;

// 
// Decompiled by Procyon v0.5.30
// 

class g5z6 extends Policy
{
    @Override
    public PermissionCollection getPermissions(final CodeSource codeSource) {
        return __X(codeSource);
    }
    
    public static PermissionCollection __X(final CodeSource codeSource) {
        final AllPermission _a = __A();
        final Permissions permissions = new Permissions();
        permissions.add(_a);
        return permissions;
    }
    
    @Override
    public void refresh() {
    }
    
    private static AllPermission __A() {
        return new AllPermission();
    }
}
