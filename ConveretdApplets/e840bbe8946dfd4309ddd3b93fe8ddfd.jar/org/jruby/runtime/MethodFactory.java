// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import java.io.InputStream;
import org.jruby.util.SafePropertyAccessor;
import org.jruby.RubyInstanceConfig;
import org.jruby.util.ClassCache;
import java.io.ByteArrayOutputStream;
import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JavaMethodDescriptor;
import java.util.List;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.parser.StaticScope;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.InvocationMethodFactory;
import org.jruby.internal.runtime.methods.DumpingInvocationMethodFactory;
import org.jruby.internal.runtime.methods.ReflectionMethodFactory;

public abstract class MethodFactory
{
    public static final Class[] COMPILED_METHOD_PARAMS;
    public static final boolean CAN_LOAD_BYTECODE;
    private static final boolean reflection;
    private static final boolean dumping;
    private static final String dumpingPath;
    
    public static MethodFactory createFactory(final ClassLoader classLoader) {
        if (MethodFactory.reflection || !MethodFactory.CAN_LOAD_BYTECODE) {
            return new ReflectionMethodFactory();
        }
        if (MethodFactory.dumping) {
            return new DumpingInvocationMethodFactory(MethodFactory.dumpingPath, classLoader);
        }
        return new InvocationMethodFactory(classLoader);
    }
    
    public abstract DynamicMethod getCompiledMethod(final RubyModule p0, final String p1, final Arity p2, final Visibility p3, final StaticScope p4, final Object p5, final CallConfiguration p6, final ISourcePosition p7, final String p8);
    
    public byte[] getCompiledMethodOffline(final String method, final String classPath, final String invokerPath, final Arity arity, final StaticScope scope, final CallConfiguration callConfig, final String filename, final int line) {
        return null;
    }
    
    public abstract DynamicMethod getCompiledMethodLazily(final RubyModule p0, final String p1, final Arity p2, final Visibility p3, final StaticScope p4, final Object p5, final CallConfiguration p6, final ISourcePosition p7, final String p8);
    
    public abstract DynamicMethod getAnnotatedMethod(final RubyModule p0, final List<JavaMethodDescriptor> p1);
    
    public abstract DynamicMethod getAnnotatedMethod(final RubyModule p0, final JavaMethodDescriptor p1);
    
    public abstract CompiledBlockCallback getBlockCallback(final String p0, final String p1, final int p2, final Object p3);
    
    public abstract CompiledBlockCallback19 getBlockCallback19(final String p0, final String p1, final int p2, final Object p3);
    
    public byte[] getBlockCallbackOffline(final String method, final String file, final int line, final String classPath) {
        return null;
    }
    
    public byte[] getBlockCallback19Offline(final String method, final String file, final int line, final String classPath) {
        return null;
    }
    
    static {
        COMPILED_METHOD_PARAMS = new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class };
        boolean can = false;
        try {
            final InputStream unloaderStream = Ruby.getClassLoader().getResourceAsStream("org/jruby/util/JDBCDriverUnloader.class");
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final byte[] buf = new byte[4096];
            int bytesRead;
            while ((bytesRead = unloaderStream.read(buf)) != -1) {
                baos.write(buf, 0, bytesRead);
            }
            final ClassCache.OneShotClassLoader oscl = new ClassCache.OneShotClassLoader(Ruby.getClassLoader());
            final Class unloaderClass = oscl.defineClass("org.jruby.util.JDBCDriverUnloader", baos.toByteArray());
            unloaderClass.newInstance();
            can = true;
        }
        catch (Throwable t) {
            System.err.println("MethodFactory: failed to load bytecode at runtime, falling back on reflection");
        }
        CAN_LOAD_BYTECODE = can;
        boolean reflection_ = false;
        boolean dumping_ = false;
        String dumpingPath_ = null;
        if (Ruby.isSecurityRestricted()) {
            reflection_ = true;
        }
        else {
            reflection_ = RubyInstanceConfig.REFLECTED_HANDLES;
            if (SafePropertyAccessor.getProperty("jruby.dump_invocations") != null) {
                dumping_ = true;
                dumpingPath_ = SafePropertyAccessor.getProperty("jruby.dump_invocations");
            }
        }
        reflection = reflection_;
        dumping = dumping_;
        dumpingPath = dumpingPath_;
    }
    
    @Deprecated
    public interface MethodDefiningCallback
    {
        void define(final RubyModule p0, final JavaMethodDescriptor p1, final DynamicMethod p2);
    }
}
