// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.codegen;

import org.jruby.internal.runtime.methods.UndefinedMethod;
import org.jruby.runtime.callsite.CacheEntry;
import org.jruby.org.objectweb.asm.Type;
import org.jruby.util.JRubyClassLoader;
import org.jruby.compiler.util.BasicObjectStubGenerator;
import org.jruby.RubyBasicObject;
import java.util.Set;
import java.util.Iterator;
import org.jruby.RubyModule;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.internal.runtime.methods.DynamicMethod;
import java.util.HashSet;
import org.jruby.javasupport.JavaUtil;
import org.jruby.org.objectweb.asm.ClassVisitor;
import org.jruby.compiler.impl.SkinnyMethodAdapter;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.org.objectweb.asm.ClassWriter;
import org.jruby.util.ClassDefiningClassLoader;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import java.util.ArrayList;
import org.jruby.util.CodegenUtils;
import java.util.HashMap;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class RealClassGenerator
{
    private static final boolean DEBUG = false;
    
    private static Map<String, List<Method>> buildSimpleToAllMap(final Class[] interfaces, final String[] superTypeNames) throws SecurityException {
        final Map<String, List<Method>> simpleToAll = new HashMap<String, List<Method>>();
        for (int i = 0; i < interfaces.length; ++i) {
            superTypeNames[i] = CodegenUtils.p(interfaces[i]);
            for (final Method method : interfaces[i].getMethods()) {
                List<Method> methods = simpleToAll.get(method.getName());
                if (methods == null) {
                    simpleToAll.put(method.getName(), methods = new ArrayList<Method>());
                }
                methods.add(method);
            }
        }
        return simpleToAll;
    }
    
    public static Class createOldStyleImplClass(final Class[] superTypes, final RubyClass rubyClass, final Ruby ruby, final String name, final ClassDefiningClassLoader classLoader) {
        final String[] superTypeNames = new String[superTypes.length];
        final Map<String, List<Method>> simpleToAll = buildSimpleToAllMap(superTypes, superTypeNames);
        final Class newClass = defineOldStyleImplClass(ruby, name, superTypeNames, simpleToAll, classLoader);
        return newClass;
    }
    
    public static Class createRealImplClass(final Class superClass, final Class[] interfaces, final RubyClass rubyClass, final Ruby ruby, final String name) {
        final String[] superTypeNames = new String[interfaces.length];
        final Map<String, List<Method>> simpleToAll = buildSimpleToAllMap(interfaces, superTypeNames);
        final Class newClass = defineRealImplClass(ruby, name, superClass, superTypeNames, simpleToAll);
        return newClass;
    }
    
    public static Class defineOldStyleImplClass(final Ruby ruby, final String name, final String[] superTypeNames, final Map<String, List<Method>> simpleToAll, final ClassDefiningClassLoader classLoader) {
        final ClassWriter cw = new ClassWriter(1);
        final String pathName = name.replace('.', '/');
        cw.visit(49, 33, pathName, null, CodegenUtils.p(Object.class), superTypeNames);
        cw.visitSource(pathName + ".gen", null);
        cw.visitField(26, "$runtimeCache", CodegenUtils.ci(RuntimeCache.class), null, null).visitEnd();
        cw.visitField(18, "$self", CodegenUtils.ci(IRubyObject.class), null, null).visitEnd();
        final SkinnyMethodAdapter clinitMethod = new SkinnyMethodAdapter(cw, 9, "<clinit>", CodegenUtils.sig(Void.TYPE, new Class[0]), null, null);
        final SkinnyMethodAdapter initMethod = new SkinnyMethodAdapter(cw, 1, "<init>", CodegenUtils.sig(Void.TYPE, IRubyObject.class), null, null);
        initMethod.aload(0);
        initMethod.invokespecial(CodegenUtils.p(Object.class), "<init>", CodegenUtils.sig(Void.TYPE, new Class[0]));
        initMethod.aload(0);
        initMethod.aload(1);
        initMethod.putfield(pathName, "$self", CodegenUtils.ci(IRubyObject.class));
        initMethod.voidreturn();
        initMethod.end();
        int cacheSize = 0;
        for (final Map.Entry<String, List<Method>> entry : simpleToAll.entrySet()) {
            final String simpleName = entry.getKey();
            final Set<String> nameSet = JavaUtil.getRubyNamesForJavaName(simpleName, entry.getValue());
            final Set<String> implementedNames = new HashSet<String>();
            for (final Method method : entry.getValue()) {
                final Class[] paramTypes = method.getParameterTypes();
                final Class returnType = method.getReturnType();
                final String fullName = simpleName + CodegenUtils.prettyParams(paramTypes);
                if (implementedNames.contains(fullName)) {
                    continue;
                }
                implementedNames.add(fullName);
                int baseIndex = 1;
                for (final Class paramType : paramTypes) {
                    if (paramType == Double.TYPE || paramType == Long.TYPE) {
                        baseIndex += 2;
                    }
                    else {
                        ++baseIndex;
                    }
                }
                final int selfIndex = baseIndex;
                final int rubyIndex = selfIndex + 1;
                final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(cw, 1, simpleName, CodegenUtils.sig(returnType, paramTypes), null, null);
                mv.start();
                mv.line(1);
                if (simpleName.equals("equals") && paramTypes.length == 1 && paramTypes[0] == Object.class && returnType == Boolean.TYPE) {
                    mv.line(2);
                    mv.aload(0);
                    mv.aload(1);
                    mv.invokespecial(CodegenUtils.p(Object.class), "equals", CodegenUtils.sig(Boolean.TYPE, CodegenUtils.params(Object.class)));
                    mv.ireturn();
                }
                else if (simpleName.equals("hashCode") && paramTypes.length == 0 && returnType == Integer.TYPE) {
                    mv.line(3);
                    mv.aload(0);
                    mv.invokespecial(CodegenUtils.p(Object.class), "hashCode", CodegenUtils.sig(Integer.TYPE, new Class[0]));
                    mv.ireturn();
                }
                else if (simpleName.equals("toString") && paramTypes.length == 0 && returnType == String.class) {
                    mv.line(4);
                    mv.aload(0);
                    mv.invokespecial(CodegenUtils.p(Object.class), "toString", CodegenUtils.sig(String.class, new Class[0]));
                    mv.areturn();
                }
                else if (simpleName.equals("__ruby_object") && paramTypes.length == 0 && returnType == IRubyObject.class) {
                    mv.aload(0);
                    mv.getfield(pathName, "$self", CodegenUtils.ci(IRubyObject.class));
                    mv.areturn();
                }
                else {
                    mv.line(5);
                    final int cacheIndex = cacheSize++;
                    mv.aload(0);
                    mv.getfield(pathName, "$self", CodegenUtils.ci(IRubyObject.class));
                    mv.astore(selfIndex);
                    mv.aload(selfIndex);
                    mv.invokeinterface(CodegenUtils.p(IRubyObject.class), "getRuntime", CodegenUtils.sig(Ruby.class, new Class[0]));
                    mv.astore(rubyIndex);
                    mv.getstatic(pathName, "$runtimeCache", CodegenUtils.ci(RuntimeCache.class));
                    mv.aload(selfIndex);
                    mv.ldc(cacheIndex);
                    for (final String eachName : nameSet) {
                        mv.ldc(eachName);
                    }
                    mv.invokevirtual(CodegenUtils.p(RuntimeCache.class), "searchWithCache", CodegenUtils.sig(DynamicMethod.class, CodegenUtils.params(IRubyObject.class, Integer.TYPE, String.class, nameSet.size())));
                    mv.aload(rubyIndex);
                    mv.invokevirtual(CodegenUtils.p(Ruby.class), "getCurrentContext", CodegenUtils.sig(ThreadContext.class, new Class[0]));
                    mv.aloadMany(selfIndex, selfIndex);
                    mv.invokeinterface(CodegenUtils.p(IRubyObject.class), "getMetaClass", CodegenUtils.sig(RubyClass.class, new Class[0]));
                    mv.ldc(simpleName);
                    coerceArgumentsToRuby(mv, paramTypes, rubyIndex);
                    mv.getstatic(CodegenUtils.p(Block.class), "NULL_BLOCK", CodegenUtils.ci(Block.class));
                    mv.line(13);
                    mv.invokevirtual(CodegenUtils.p(DynamicMethod.class), "call", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, RubyModule.class, String.class, IRubyObject[].class, Block.class));
                    coerceResultAndReturn(mv, returnType);
                }
                mv.end();
            }
        }
        clinitMethod.newobj(CodegenUtils.p(RuntimeCache.class));
        clinitMethod.dup();
        clinitMethod.invokespecial(CodegenUtils.p(RuntimeCache.class), "<init>", CodegenUtils.sig(Void.TYPE, new Class[0]));
        clinitMethod.dup();
        clinitMethod.ldc(cacheSize);
        clinitMethod.invokevirtual(CodegenUtils.p(RuntimeCache.class), "initMethodCache", CodegenUtils.sig(Void.TYPE, Integer.TYPE));
        clinitMethod.putstatic(pathName, "$runtimeCache", CodegenUtils.ci(RuntimeCache.class));
        clinitMethod.voidreturn();
        clinitMethod.end();
        cw.visitEnd();
        final byte[] bytes = cw.toByteArray();
        Class newClass;
        synchronized (classLoader) {
            try {
                newClass = classLoader.loadClass(name);
            }
            catch (ClassNotFoundException cnfe) {
                newClass = classLoader.defineClass(name, cw.toByteArray());
            }
        }
        return newClass;
    }
    
    public static Class defineRealImplClass(final Ruby ruby, final String name, final Class superClass, final String[] superTypeNames, final Map<String, List<Method>> simpleToAll) {
        final ClassWriter cw = new ClassWriter(1);
        final String pathName = name.replace('.', '/');
        final boolean isRubyHierarchy = RubyBasicObject.class.isAssignableFrom(superClass);
        if (isRubyHierarchy) {
            cw.visit(49, 33, pathName, null, CodegenUtils.p(superClass), superTypeNames);
        }
        else {
            final String[] plusIRubyObject = new String[superTypeNames.length + 1];
            plusIRubyObject[0] = CodegenUtils.p(IRubyObject.class);
            System.arraycopy(superTypeNames, 0, plusIRubyObject, 1, superTypeNames.length);
            cw.visit(49, 33, pathName, null, CodegenUtils.p(superClass), plusIRubyObject);
        }
        cw.visitSource(pathName + ".gen", null);
        cw.visitField(26, "$runtimeCache", CodegenUtils.ci(RuntimeCache.class), null, null).visitEnd();
        final SkinnyMethodAdapter clinitMethod = new SkinnyMethodAdapter(cw, 9, "<clinit>", CodegenUtils.sig(Void.TYPE, new Class[0]), null, null);
        final SkinnyMethodAdapter initMethod = new SkinnyMethodAdapter(cw, 1, "<init>", CodegenUtils.sig(Void.TYPE, Ruby.class, RubyClass.class), null, null);
        if (isRubyHierarchy) {
            initMethod.aloadMany(0, 1, 2);
            initMethod.invokespecial(CodegenUtils.p(superClass), "<init>", CodegenUtils.sig(Void.TYPE, Ruby.class, RubyClass.class));
        }
        else {
            cw.visitField(18, "$ruby", CodegenUtils.ci(Ruby.class), null, null).visitEnd();
            cw.visitField(18, "$rubyClass", CodegenUtils.ci(RubyClass.class), null, null).visitEnd();
            initMethod.aloadMany(0, 1);
            initMethod.putfield(pathName, "$ruby", CodegenUtils.ci(Ruby.class));
            initMethod.aloadMany(0, 2);
            initMethod.putfield(pathName, "$rubyClass", CodegenUtils.ci(RubyClass.class));
            initMethod.aload(0);
            initMethod.invokespecial(CodegenUtils.p(superClass), "<init>", CodegenUtils.sig(Void.TYPE, new Class[0]));
        }
        initMethod.voidreturn();
        initMethod.end();
        if (isRubyHierarchy) {
            final SkinnyMethodAdapter toJavaMethod = new SkinnyMethodAdapter(cw, 1, "toJava", CodegenUtils.sig(Object.class, Class.class), null, null);
            toJavaMethod.aload(0);
            toJavaMethod.areturn();
            toJavaMethod.end();
        }
        else {
            BasicObjectStubGenerator.addBasicObjectStubsToClass(cw);
            final SkinnyMethodAdapter getRuntimeMethod = new SkinnyMethodAdapter(cw, 1, "getRuntime", CodegenUtils.sig(Ruby.class, new Class[0]), null, null);
            getRuntimeMethod.aload(0);
            getRuntimeMethod.getfield(pathName, "$ruby", CodegenUtils.ci(Ruby.class));
            getRuntimeMethod.areturn();
            getRuntimeMethod.end();
            final SkinnyMethodAdapter getMetaClassMethod = new SkinnyMethodAdapter(cw, 1, "getMetaClass", CodegenUtils.sig(RubyClass.class, new Class[0]), null, null);
            getMetaClassMethod.aload(0);
            getMetaClassMethod.getfield(pathName, "$rubyClass", CodegenUtils.ci(RubyClass.class));
            getMetaClassMethod.areturn();
            getMetaClassMethod.end();
        }
        int cacheSize = 0;
        for (final Map.Entry<String, List<Method>> entry : simpleToAll.entrySet()) {
            final String simpleName = entry.getKey();
            final Set<String> nameSet = JavaUtil.getRubyNamesForJavaName(simpleName, entry.getValue());
            final Set<String> implementedNames = new HashSet<String>();
            for (final Method method : entry.getValue()) {
                final Class[] paramTypes = method.getParameterTypes();
                final Class returnType = method.getReturnType();
                final String fullName = simpleName + CodegenUtils.prettyParams(paramTypes);
                if (implementedNames.contains(fullName)) {
                    continue;
                }
                implementedNames.add(fullName);
                int baseIndex = 1;
                for (final Class paramType : paramTypes) {
                    if (paramType == Double.TYPE || paramType == Long.TYPE) {
                        baseIndex += 2;
                    }
                    else {
                        ++baseIndex;
                    }
                }
                final int rubyIndex = baseIndex + 1;
                final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(cw, 1, simpleName, CodegenUtils.sig(returnType, paramTypes), null, null);
                mv.start();
                mv.line(1);
                if (simpleName.equals("equals") && paramTypes.length == 1 && paramTypes[0] == Object.class && returnType == Boolean.TYPE) {
                    mv.line(2);
                    mv.aload(0);
                    mv.aload(1);
                    mv.invokespecial(CodegenUtils.p(Object.class), "equals", CodegenUtils.sig(Boolean.TYPE, CodegenUtils.params(Object.class)));
                    mv.ireturn();
                }
                else if (simpleName.equals("hashCode") && paramTypes.length == 0 && returnType == Integer.TYPE) {
                    mv.line(3);
                    mv.aload(0);
                    mv.invokespecial(CodegenUtils.p(Object.class), "hashCode", CodegenUtils.sig(Integer.TYPE, new Class[0]));
                    mv.ireturn();
                }
                else if (simpleName.equals("toString") && paramTypes.length == 0 && returnType == String.class) {
                    mv.line(4);
                    mv.aload(0);
                    mv.invokespecial(CodegenUtils.p(Object.class), "toString", CodegenUtils.sig(String.class, new Class[0]));
                    mv.areturn();
                }
                else {
                    mv.line(5);
                    final int cacheIndex = cacheSize++;
                    mv.aload(0);
                    mv.invokeinterface(CodegenUtils.p(IRubyObject.class), "getRuntime", CodegenUtils.sig(Ruby.class, new Class[0]));
                    mv.astore(rubyIndex);
                    mv.getstatic(pathName, "$runtimeCache", CodegenUtils.ci(RuntimeCache.class));
                    mv.aload(0);
                    mv.ldc(cacheIndex);
                    for (final String eachName : nameSet) {
                        mv.ldc(eachName);
                    }
                    mv.invokevirtual(CodegenUtils.p(RuntimeCache.class), "searchWithCache", CodegenUtils.sig(DynamicMethod.class, CodegenUtils.params(IRubyObject.class, Integer.TYPE, String.class, nameSet.size())));
                    mv.aload(rubyIndex);
                    mv.invokevirtual(CodegenUtils.p(Ruby.class), "getCurrentContext", CodegenUtils.sig(ThreadContext.class, new Class[0]));
                    mv.aloadMany(0, 0);
                    mv.invokeinterface(CodegenUtils.p(IRubyObject.class), "getMetaClass", CodegenUtils.sig(RubyClass.class, new Class[0]));
                    mv.ldc(simpleName);
                    coerceArgumentsToRuby(mv, paramTypes, rubyIndex);
                    mv.getstatic(CodegenUtils.p(Block.class), "NULL_BLOCK", CodegenUtils.ci(Block.class));
                    mv.line(13);
                    mv.invokevirtual(CodegenUtils.p(DynamicMethod.class), "call", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, RubyModule.class, String.class, IRubyObject[].class, Block.class));
                    coerceResultAndReturn(mv, returnType);
                }
                mv.end();
            }
        }
        clinitMethod.newobj(CodegenUtils.p(RuntimeCache.class));
        clinitMethod.dup();
        clinitMethod.invokespecial(CodegenUtils.p(RuntimeCache.class), "<init>", CodegenUtils.sig(Void.TYPE, new Class[0]));
        clinitMethod.dup();
        clinitMethod.ldc(cacheSize);
        clinitMethod.invokevirtual(CodegenUtils.p(RuntimeCache.class), "initMethodCache", CodegenUtils.sig(Void.TYPE, Integer.TYPE));
        clinitMethod.putstatic(pathName, "$runtimeCache", CodegenUtils.ci(RuntimeCache.class));
        clinitMethod.voidreturn();
        clinitMethod.end();
        cw.visitEnd();
        final byte[] bytes = cw.toByteArray();
        JRubyClassLoader loader;
        if (superClass.getClassLoader() instanceof JRubyClassLoader) {
            loader = new JRubyClassLoader(superClass.getClassLoader());
        }
        else {
            loader = new JRubyClassLoader(ruby.getJRubyClassLoader());
        }
        Class newClass;
        try {
            newClass = loader.loadClass(name);
        }
        catch (ClassNotFoundException cnfe) {
            newClass = loader.defineClass(name, cw.toByteArray());
        }
        return newClass;
    }
    
    public static void coerceArgumentsToRuby(final SkinnyMethodAdapter mv, final Class[] paramTypes, final int rubyIndex) {
        if (paramTypes.length != 0) {
            mv.pushInt(paramTypes.length);
            mv.anewarray(CodegenUtils.p(IRubyObject.class));
            int i = 0;
            int argIndex = 1;
            while (i < paramTypes.length) {
                final Class paramType = paramTypes[i];
                mv.dup();
                mv.pushInt(i);
                mv.aload(rubyIndex);
                if (paramTypes[i].isPrimitive()) {
                    if (paramType == Byte.TYPE || paramType == Short.TYPE || paramType == Character.TYPE || paramType == Integer.TYPE) {
                        mv.iload(argIndex++);
                        mv.invokestatic(CodegenUtils.p(JavaUtil.class), "convertJavaToRuby", CodegenUtils.sig(IRubyObject.class, Ruby.class, Integer.TYPE));
                    }
                    else if (paramType == Long.TYPE) {
                        mv.lload(argIndex);
                        argIndex += 2;
                        mv.invokestatic(CodegenUtils.p(JavaUtil.class), "convertJavaToRuby", CodegenUtils.sig(IRubyObject.class, Ruby.class, Long.TYPE));
                    }
                    else if (paramType == Float.TYPE) {
                        mv.fload(argIndex++);
                        mv.invokestatic(CodegenUtils.p(JavaUtil.class), "convertJavaToRuby", CodegenUtils.sig(IRubyObject.class, Ruby.class, Float.TYPE));
                    }
                    else if (paramType == Double.TYPE) {
                        mv.dload(argIndex);
                        argIndex += 2;
                        mv.invokestatic(CodegenUtils.p(JavaUtil.class), "convertJavaToRuby", CodegenUtils.sig(IRubyObject.class, Ruby.class, Double.TYPE));
                    }
                    else if (paramType == Boolean.TYPE) {
                        mv.iload(argIndex++);
                        mv.invokestatic(CodegenUtils.p(JavaUtil.class), "convertJavaToRuby", CodegenUtils.sig(IRubyObject.class, Ruby.class, Boolean.TYPE));
                    }
                }
                else {
                    mv.aload(argIndex++);
                    mv.invokestatic(CodegenUtils.p(JavaUtil.class), "convertJavaToUsableRubyObject", CodegenUtils.sig(IRubyObject.class, Ruby.class, Object.class));
                }
                mv.aastore();
                ++i;
            }
        }
        else {
            mv.getstatic(CodegenUtils.p(IRubyObject.class), "NULL_ARRAY", CodegenUtils.ci(IRubyObject[].class));
        }
    }
    
    public static void coerceResultAndReturn(final SkinnyMethodAdapter mv, final Class returnType) {
        if (returnType != Void.TYPE) {
            if (returnType.isPrimitive()) {
                if (returnType == Boolean.TYPE) {
                    mv.getstatic(CodegenUtils.p(Boolean.class), "TYPE", CodegenUtils.ci(Class.class));
                    mv.invokeinterface(CodegenUtils.p(IRubyObject.class), "toJava", CodegenUtils.sig(Object.class, Class.class));
                    mv.checkcast(CodegenUtils.p(Boolean.class));
                    mv.invokevirtual(CodegenUtils.p(Boolean.class), "booleanValue", CodegenUtils.sig(Boolean.TYPE, new Class[0]));
                    mv.ireturn();
                }
                else {
                    mv.getstatic(CodegenUtils.p(CodegenUtils.getBoxType(returnType)), "TYPE", CodegenUtils.ci(Class.class));
                    mv.invokeinterface(CodegenUtils.p(IRubyObject.class), "toJava", CodegenUtils.sig(Object.class, Class.class));
                    if (returnType == Byte.TYPE) {
                        mv.checkcast(CodegenUtils.p(Number.class));
                        mv.invokevirtual(CodegenUtils.p(Number.class), "byteValue", CodegenUtils.sig(Byte.TYPE, new Class[0]));
                        mv.ireturn();
                    }
                    else if (returnType == Short.TYPE) {
                        mv.checkcast(CodegenUtils.p(Number.class));
                        mv.invokevirtual(CodegenUtils.p(Number.class), "shortValue", CodegenUtils.sig(Short.TYPE, new Class[0]));
                        mv.ireturn();
                    }
                    else if (returnType == Character.TYPE) {
                        mv.checkcast(CodegenUtils.p(Character.class));
                        mv.invokevirtual(CodegenUtils.p(Character.class), "charValue", CodegenUtils.sig(Character.TYPE, new Class[0]));
                        mv.ireturn();
                    }
                    else if (returnType == Integer.TYPE) {
                        mv.checkcast(CodegenUtils.p(Number.class));
                        mv.invokevirtual(CodegenUtils.p(Number.class), "intValue", CodegenUtils.sig(Integer.TYPE, new Class[0]));
                        mv.ireturn();
                    }
                    else if (returnType == Long.TYPE) {
                        mv.checkcast(CodegenUtils.p(Number.class));
                        mv.invokevirtual(CodegenUtils.p(Number.class), "longValue", CodegenUtils.sig(Long.TYPE, new Class[0]));
                        mv.lreturn();
                    }
                    else if (returnType == Float.TYPE) {
                        mv.checkcast(CodegenUtils.p(Number.class));
                        mv.invokevirtual(CodegenUtils.p(Number.class), "floatValue", CodegenUtils.sig(Float.TYPE, new Class[0]));
                        mv.freturn();
                    }
                    else if (returnType == Double.TYPE) {
                        mv.checkcast(CodegenUtils.p(Number.class));
                        mv.invokevirtual(CodegenUtils.p(Number.class), "doubleValue", CodegenUtils.sig(Double.TYPE, new Class[0]));
                        mv.dreturn();
                    }
                }
            }
            else {
                mv.ldc(Type.getType(returnType));
                mv.invokeinterface(CodegenUtils.p(IRubyObject.class), "toJava", CodegenUtils.sig(Object.class, Class.class));
                mv.checkcast(CodegenUtils.p(returnType));
                mv.areturn();
            }
        }
        else {
            mv.voidreturn();
        }
    }
    
    public static boolean isCacheOk(final CacheEntry entry, final IRubyObject self) {
        return CacheEntry.typeOk(entry, self.getMetaClass()) && entry.method != UndefinedMethod.INSTANCE;
    }
}
