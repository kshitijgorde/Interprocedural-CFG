// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.unsafe;

import java.io.FileOutputStream;
import org.jruby.org.objectweb.asm.ClassVisitor;
import org.jruby.compiler.impl.SkinnyMethodAdapter;
import org.jruby.util.CodegenUtils;
import org.jruby.org.objectweb.asm.ClassWriter;

public class UnsafeGenerator
{
    public static void main(final String[] args) {
        if (args.length != 2) {
            System.err.println("Two args please, the target package and directory");
            System.exit(1);
        }
        final String pkg = args[0].replace('.', '/');
        final String dir = args[1];
        final String classname = pkg + "/GeneratedUnsafe";
        final String classpath = dir + "/GeneratedUnsafe.class";
        final ClassWriter cw = new ClassWriter(3);
        cw.visit(49, 33, classname, null, CodegenUtils.p(Object.class), new String[] { CodegenUtils.p(Unsafe.class) });
        cw.visitSource("<generated>", null);
        SkinnyMethodAdapter method = new SkinnyMethodAdapter(cw, 1, "<init>", CodegenUtils.sig(Void.TYPE, new Class[0]), null, null);
        method.start();
        method.line(0);
        method.aload(0);
        method.invokespecial(CodegenUtils.p(Object.class), "<init>", CodegenUtils.sig(Void.TYPE, new Class[0]));
        method.voidreturn();
        method.end();
        method = new SkinnyMethodAdapter(cw, 1, "throwException", CodegenUtils.sig(Void.TYPE, Throwable.class), null, null);
        method.line(0);
        method.start();
        method.aload(1);
        method.athrow();
        method.end();
        cw.visitEnd();
        final byte[] bytecode = cw.toByteArray();
        try {
            final FileOutputStream fos = new FileOutputStream(classpath);
            fos.write(bytecode);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
