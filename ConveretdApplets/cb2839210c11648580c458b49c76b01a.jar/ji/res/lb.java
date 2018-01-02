// 
// Decompiled by Procyon v0.5.30
// 

package ji.res;

import java.io.RandomAccessFile;
import java.io.File;
import ji.util.d;
import java.util.Hashtable;

public class lb extends ClassLoader
{
    String a;
    Hashtable b;
    boolean c;
    Object[] d;
    
    public void a() {
        this.d = null;
        while (this.b.size() > 0) {
            this.b.remove(this.b.keys().nextElement());
        }
    }
    
    public lb(final Object o, final String a) {
        this.a = null;
        this.b = new Hashtable(200);
        this.c = false;
        this.d = null;
        this.d = o.getClass().getSigners();
        if (a.endsWith("/") || a.endsWith("\\")) {
            this.a = a;
        }
        else {
            this.a = String.valueOf(String.valueOf(a)).concat("/");
        }
    }
    
    public final void a(final boolean c) {
        this.c = c;
    }
    
    public Class loadClass(final String s, final boolean b) throws ClassNotFoundException {
        Class<?> clazz = this.b.get(s);
        if (clazz == null) {
            if (!this.c || s.toLowerCase().startsWith("java")) {
                clazz = this.findSystemClass(s);
            }
            else {
                this.c = false;
                clazz = this.b.get(s);
                if (clazz == null && clazz == null) {
                    clazz = (Class<?>)this.findClass(s);
                }
            }
            if (clazz != null) {
                this.b.put(s, clazz);
            }
        }
        if (clazz != null) {
            if (b) {
                this.resolveClass(clazz);
            }
            return clazz;
        }
        throw new ClassNotFoundException(s);
    }
    
    public Class findClass(final String s) throws ClassNotFoundException {
        Class<?> defineClass = null;
        final byte[] a = this.a(s);
        if (a != null) {
            defineClass = this.defineClass(s, a, 0, a.length);
            if (defineClass != null && this.d != null) {
                this.setSigners(defineClass, this.d);
            }
        }
        return defineClass;
    }
    
    byte[] a(final String s) {
        byte[] array = null;
        try {
            final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a))).append(ji.util.d.b(s, ".", "/")).append(".class")));
            if (new File(value).exists()) {
                final RandomAccessFile randomAccessFile = new RandomAccessFile(value, "r");
                array = new byte[(int)randomAccessFile.length()];
                randomAccessFile.read(array);
                randomAccessFile.close();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return array;
    }
}
