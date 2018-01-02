// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import java.io.FileOutputStream;
import java.io.File;
import org.jruby.org.objectweb.asm.ClassWriter;

public class DumpingInvocationMethodFactory extends InvocationMethodFactory
{
    private String dumpPath;
    
    public DumpingInvocationMethodFactory(final String path, final ClassLoader classLoader) {
        super(classLoader);
        this.dumpPath = path;
    }
    
    protected Class endClass(final ClassWriter cw, final String name) {
        cw.visitEnd();
        final byte[] code = cw.toByteArray();
        final String cname = name.replace('.', '/');
        final File f = new File(this.dumpPath, cname + ".class");
        f.getParentFile().mkdirs();
        try {
            final FileOutputStream fos = new FileOutputStream(f);
            fos.write(code);
            fos.close();
        }
        catch (Exception ex) {}
        return this.classLoader.defineClass(name, code);
    }
}
