// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import com.ms.security.PolicyEngine;
import com.ms.security.PermissionID;
import java.lang.reflect.Method;

public class NFIEPermission
{
    private static final boolean a = false;
    
    private static void a(final String s) {
    }
    
    public static Object invokePrivilegedMethod(final Object o, final Method method, final Object[] array) {
        try {
            PolicyEngine.assertPermission(PermissionID.SYSTEM);
        }
        catch (Exception ex) {
            a("Exception thrown durong method assert of permission: " + ex);
        }
        try {
            return method.invoke(o, array);
        }
        catch (Exception ex2) {
            a("Exception thrown durong method invoke: " + ex2);
            return null;
        }
    }
}
