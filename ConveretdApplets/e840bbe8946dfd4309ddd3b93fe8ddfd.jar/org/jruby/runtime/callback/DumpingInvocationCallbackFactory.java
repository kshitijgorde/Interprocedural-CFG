// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.callback;

import org.jruby.org.objectweb.asm.MethodVisitor;
import org.jruby.org.objectweb.asm.ClassWriter;
import org.jruby.Ruby;

public class DumpingInvocationCallbackFactory extends InvocationCallbackFactory
{
    public DumpingInvocationCallbackFactory(final Ruby runtime, final Class type, final ClassLoader classLoader) {
        super(runtime, type, classLoader);
    }
    
    @Deprecated
    protected Class endCall(final ClassWriter cw, final MethodVisitor mv, final String name) {
        mv.visitEnd();
        cw.visitEnd();
        final byte[] code = cw.toByteArray();
        return this.classLoader.defineClass(name, code, this.protectionDomain);
    }
}
