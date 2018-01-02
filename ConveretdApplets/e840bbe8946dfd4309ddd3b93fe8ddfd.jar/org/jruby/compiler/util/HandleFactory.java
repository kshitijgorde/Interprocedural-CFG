// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.util;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
import org.jruby.compiler.JITCompiler;
import org.jruby.org.objectweb.asm.ClassVisitor;
import java.lang.reflect.Modifier;
import org.jruby.compiler.impl.SkinnyMethodAdapter;
import org.jruby.util.CodegenUtils;
import org.jruby.org.objectweb.asm.ClassWriter;
import java.lang.reflect.Method;
import org.jruby.util.JRubyClassLoader;

public class HandleFactory
{
    public static Handle createHandle(final JRubyClassLoader classLoader, final Method method) {
        final String name = createHandleName(method);
        try {
            final Class handleClass = classLoader.loadClass(name);
            return handleClass.newInstance();
        }
        catch (Exception e) {
            final Class handleClass = createHandleClass(classLoader, method, name);
            try {
                return handleClass.newInstance();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public static Class createHandleClass(final JRubyClassLoader classLoader, final Method method, final String name) {
        final byte[] bytes = createHandleBytes(method, name);
        return ((classLoader != null) ? classLoader : new JRubyClassLoader(JRubyClassLoader.class.getClassLoader())).defineClass(name, bytes);
    }
    
    public static byte[] createHandleBytes(final Method method, final String name) {
        final Class returnType = method.getReturnType();
        final Class[] paramTypes = method.getParameterTypes();
        final ClassVisitor cv = new ClassWriter(1);
        cv.visit(49, 49, name, null, CodegenUtils.p(Handle.class), null);
        boolean needsArgsVersion = true;
        String signature = null;
        switch (paramTypes.length) {
            case 0: {
                signature = CodegenUtils.sig(Object.class, Object.class);
                break;
            }
            case 1: {
                signature = CodegenUtils.sig(Object.class, Object.class, Object.class);
                break;
            }
            case 2: {
                signature = CodegenUtils.sig(Object.class, Object.class, Object.class, Object.class);
                break;
            }
            case 3: {
                signature = CodegenUtils.sig(Object.class, Object.class, Object.class, Object.class, Object.class);
                break;
            }
            default: {
                needsArgsVersion = false;
                signature = CodegenUtils.sig(Object.class, Object.class, Object[].class);
                break;
            }
        }
        SkinnyMethodAdapter m = new SkinnyMethodAdapter(cv, 4113, "invoke", signature, null, null);
        m.start();
        if (!Modifier.isStatic(method.getModifiers())) {
            m.aload(1);
            if (method.getDeclaringClass() != Object.class) {
                m.checkcast(CodegenUtils.p(method.getDeclaringClass()));
            }
        }
        switch (paramTypes.length) {
            case 0:
            case 1:
            case 2:
            case 3: {
                for (int i = 0; i < paramTypes.length; ++i) {
                    loadUnboxedArgument(m, i + 2, paramTypes[i]);
                }
                break;
            }
            default: {
                for (int i = 0; i < paramTypes.length; ++i) {
                    m.aload(2);
                    m.pushInt(i);
                    m.aaload();
                    final Class paramClass = paramTypes[i];
                    if (paramClass.isPrimitive()) {
                        final Class boxType = CodegenUtils.getBoxType(paramClass);
                        m.checkcast(CodegenUtils.p(boxType));
                        m.invokevirtual(CodegenUtils.p(boxType), paramClass.toString() + "Value", CodegenUtils.sig(paramClass, new Class[0]));
                    }
                    else if (paramClass != Object.class) {
                        m.checkcast(CodegenUtils.p(paramClass));
                    }
                }
                break;
            }
        }
        if (Modifier.isStatic(method.getModifiers())) {
            m.invokestatic(CodegenUtils.p(method.getDeclaringClass()), method.getName(), CodegenUtils.sig(returnType, paramTypes));
        }
        else if (Modifier.isInterface(method.getDeclaringClass().getModifiers())) {
            m.invokeinterface(CodegenUtils.p(method.getDeclaringClass()), method.getName(), CodegenUtils.sig(returnType, paramTypes));
        }
        else {
            m.invokevirtual(CodegenUtils.p(method.getDeclaringClass()), method.getName(), CodegenUtils.sig(returnType, paramTypes));
        }
        if (returnType == Void.TYPE) {
            m.aconst_null();
        }
        else if (returnType.isPrimitive()) {
            final Class boxType2 = CodegenUtils.getBoxType(returnType);
            m.invokestatic(CodegenUtils.p(boxType2), "valueOf", CodegenUtils.sig(boxType2, returnType));
        }
        m.areturn();
        m.end();
        if (needsArgsVersion) {
            m = new SkinnyMethodAdapter(cv, 4113, "invoke", CodegenUtils.sig(Object.class, Object.class, Object[].class), null, null);
            m.start();
            m.aload(0);
            m.aload(1);
            for (int i = 0; i < paramTypes.length; ++i) {
                m.aload(2);
                m.ldc(i);
                m.aaload();
            }
            m.invokevirtual(name, "invoke", CodegenUtils.sig(Object.class, CodegenUtils.params(Object.class, Object.class, paramTypes.length)));
            m.areturn();
            m.end();
        }
        m = new SkinnyMethodAdapter(cv, 1, "<init>", CodegenUtils.sig(Void.TYPE, new Class[0]), null, null);
        m.start();
        m.aload(0);
        m.invokespecial(CodegenUtils.p(Handle.class), "<init>", CodegenUtils.sig(Void.TYPE, new Class[0]));
        m.voidreturn();
        m.end();
        cv.visitEnd();
        final byte[] bytes = ((ClassWriter)cv).toByteArray();
        return bytes;
    }
    
    private static String createHandleName(final Method method) {
        final Class returnType = method.getReturnType();
        final Class[] paramTypes = method.getParameterTypes();
        return method.getDeclaringClass().getCanonicalName().replaceAll("\\.", "__") + "#" + method.getName() + "#" + JITCompiler.getHashForString(CodegenUtils.pretty(returnType, paramTypes));
    }
    
    public static void loadUnboxedArgument(final SkinnyMethodAdapter m, final int index, final Class type) {
        m.aload(index);
        unboxAndCast(m, type);
    }
    
    public static void unboxAndCast(final SkinnyMethodAdapter m, final Class paramClass) {
        if (paramClass.isPrimitive()) {
            final Class boxType = CodegenUtils.getBoxType(paramClass);
            m.checkcast(CodegenUtils.p(boxType));
            m.invokevirtual(CodegenUtils.p(boxType), paramClass.toString() + "Value", CodegenUtils.sig(paramClass, new Class[0]));
        }
        else if (paramClass != Object.class) {
            m.checkcast(CodegenUtils.p(paramClass));
        }
    }
    
    public static void main(final String[] args) {
        try {
            final Method method = HandleFactory.class.getMethod("dummy", String.class);
            final Handle handle = createHandle(null, method);
            String prop1 = "java.class.path";
            String prop2 = "";
            for (int i = 0; i < 10; ++i) {
                System.out.print("reflected invocation: ");
                long time = System.currentTimeMillis();
                for (int j = 0; j < 50000000; ++j) {
                    final Object result = method.invoke(null, prop1);
                    method.invoke(null, prop2);
                    final String tmp = prop1;
                    prop1 = prop2;
                    prop2 = tmp;
                    if (j % 10000000 == 0) {
                        System.out.println(prop2);
                    }
                }
                System.out.println(System.currentTimeMillis() - time);
                System.out.print("method invocation: ");
                time = System.currentTimeMillis();
                for (int j = 0; j < 50000000; ++j) {
                    final Object result = dummy(prop1);
                    dummy(prop2);
                    final String tmp = prop1;
                    prop1 = prop2;
                    prop2 = tmp;
                    if (j % 10000000 == 0) {
                        System.out.println(prop2);
                    }
                }
                System.out.println(System.currentTimeMillis() - time);
                System.out.print("handle invocation: ");
                time = System.currentTimeMillis();
                for (int j = 0; j < 50000000; ++j) {
                    final Object result = handle.invoke(null, prop1);
                    handle.invoke(null, prop2);
                    final String tmp = prop1;
                    prop1 = prop2;
                    prop2 = tmp;
                    if (j % 10000000 == 0) {
                        System.out.println(prop2);
                    }
                }
                System.out.println(System.currentTimeMillis() - time);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public static String dummy(final String str) {
        if (str.length() == 0) {
            return null;
        }
        return str;
    }
    
    public static int dummy2() {
        return 1;
    }
    
    public static Object dummy3(final Object obj) {
        return obj;
    }
    
    public static class Handle
    {
        private Error fail() {
            return new AbstractMethodError("invalid call signature for target method: " + this.getClass());
        }
        
        public Object invoke(final Object receiver) {
            throw this.fail();
        }
        
        public Object invoke(final Object receiver, final Object arg0) {
            throw this.fail();
        }
        
        public Object invoke(final Object receiver, final Object arg0, final Object arg1) {
            throw this.fail();
        }
        
        public Object invoke(final Object receiver, final Object arg0, final Object arg1, final Object arg2) {
            throw this.fail();
        }
        
        public Object invoke(final Object receiver, final Object... args) {
            throw this.fail();
        }
    }
    
    private static class FakeLoader extends ClassLoader
    {
        public FakeLoader(final ClassLoader parent) {
            super(parent);
        }
        
        public Class<?> loadClass(final String name, final boolean resolve) throws ClassNotFoundException {
            return super.loadClass(name, resolve);
        }
    }
    
    public static class Tool
    {
        public static void main(final String[] args) {
            if (args.length != 2) {
                System.err.println("Usage:\n  tool <java class> <target dir>");
                System.exit(1);
            }
            final String classname = args[0];
            final String target = args[1];
            final FakeLoader loader = new FakeLoader(Tool.class.getClassLoader());
            try {
                final Class klass = loader.loadClass(classname, false);
                for (final Method method : klass.getMethods()) {
                    final String name = createHandleName(method);
                    final byte[] bytes = HandleFactory.createHandleBytes(method, name);
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(new File(target, name + ".class"));
                        fos.write(bytes);
                    }
                    catch (IOException ioe) {
                        throw new RuntimeException(ioe);
                    }
                    finally {
                        try {
                            fos.close();
                        }
                        catch (IOException ex) {}
                    }
                }
            }
            catch (ClassNotFoundException cnfe) {
                throw new RuntimeException(cnfe);
            }
        }
    }
}
