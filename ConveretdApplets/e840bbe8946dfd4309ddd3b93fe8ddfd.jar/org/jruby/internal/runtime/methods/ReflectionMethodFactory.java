// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.runtime.CompiledBlockCallback19;
import java.lang.reflect.InvocationTargetException;
import org.jruby.runtime.CompiledBlockCallback;
import java.util.Iterator;
import org.jruby.anno.JRubyMethod;
import java.util.ArrayList;
import java.util.List;
import org.jruby.anno.TypePopulator;
import java.lang.reflect.Modifier;
import org.jruby.anno.JavaMethodDescriptor;
import java.lang.reflect.Method;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.Visibility;
import org.jruby.runtime.Arity;
import org.jruby.RubyModule;
import org.jruby.runtime.MethodFactory;

public class ReflectionMethodFactory extends MethodFactory
{
    public DynamicMethod getCompiledMethodLazily(final RubyModule implementationClass, final String methodName, final Arity arity, final Visibility visibility, final StaticScope scope, final Object scriptObject, final CallConfiguration callConfig, final ISourcePosition position, final String parameterDesc) {
        return this.getCompiledMethod(implementationClass, methodName, arity, visibility, scope, scriptObject, callConfig, position, parameterDesc);
    }
    
    public DynamicMethod getCompiledMethod(final RubyModule implementationClass, final String methodName, final Arity arity, final Visibility visibility, final StaticScope scope, final Object scriptObject, final CallConfiguration callConfig, final ISourcePosition position, final String parameterDesc) {
        try {
            final Class scriptClass = scriptObject.getClass();
            final Method method = scriptClass.getMethod(methodName, scriptClass, ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class);
            return new ReflectedCompiledMethod(implementationClass, arity, visibility, scope, scriptObject, method, callConfig, position, parameterDesc);
        }
        catch (NoSuchMethodException nsme) {
            throw new RuntimeException("No method with name " + methodName + " found in " + scriptObject.getClass());
        }
    }
    
    public DynamicMethod getAnnotatedMethod(final RubyModule implementationClass, final JavaMethodDescriptor desc) {
        try {
            if (!Modifier.isPublic(desc.getDeclaringClass().getModifiers())) {
                System.err.println("warning: binding non-public class" + desc.declaringClassName + "; reflected handles won't work");
            }
            final Method method = desc.getDeclaringClass().getDeclaredMethod(desc.name, (Class[])desc.getParameterClasses());
            final JavaMethod ic = new ReflectedJavaMethod(implementationClass, method, desc.anno);
            TypePopulator.populateMethod(ic, ic.getArity().getValue(), method.getName(), Modifier.isStatic(method.getModifiers()), CallConfiguration.getCallConfigByAnno(desc.anno), desc.anno.notImplemented());
            return ic;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public DynamicMethod getAnnotatedMethod(final RubyModule implementationClass, final List<JavaMethodDescriptor> descs) {
        try {
            if (!Modifier.isPublic(descs.get(0).getDeclaringClass().getModifiers())) {
                System.err.println("warning: binding non-public class" + descs.get(0).declaringClassName + "; reflected handles won't work");
            }
            final List<Method> methods = new ArrayList<Method>();
            final List<JRubyMethod> annotations = new ArrayList<JRubyMethod>();
            for (final JavaMethodDescriptor desc : descs) {
                methods.add(desc.getDeclaringClass().getDeclaredMethod(desc.name, (Class[])desc.getParameterClasses()));
                annotations.add(desc.anno);
            }
            final Method method0 = methods.get(0);
            final JRubyMethod anno0 = annotations.get(0);
            final JavaMethod ic = new ReflectedJavaMultiMethod(implementationClass, methods, annotations);
            TypePopulator.populateMethod(ic, ic.getArity().getValue(), method0.getName(), Modifier.isStatic(method0.getModifiers()), CallConfiguration.getCallConfigByAnno(anno0), anno0.notImplemented());
            return ic;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public CompiledBlockCallback getBlockCallback(final String method, final String file, final int line, final Object scriptObject) {
        try {
            final Class scriptClass = scriptObject.getClass();
            final Method blockMethod = scriptClass.getMethod(method, scriptClass, ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class);
            return new CompiledBlockCallback() {
                public IRubyObject call(final ThreadContext context, final IRubyObject self, final IRubyObject args, final Block block) {
                    try {
                        return (IRubyObject)blockMethod.invoke(null, scriptObject, context, self, args, block);
                    }
                    catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                    catch (IllegalArgumentException ex2) {
                        throw new RuntimeException(ex2);
                    }
                    catch (InvocationTargetException ex3) {
                        final Throwable cause = ex3.getCause();
                        if (cause instanceof RuntimeException) {
                            throw (RuntimeException)cause;
                        }
                        if (cause instanceof Error) {
                            throw (Error)cause;
                        }
                        throw new RuntimeException(ex3);
                    }
                }
                
                public String getFile() {
                    return file;
                }
                
                public int getLine() {
                    return line;
                }
            };
        }
        catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        }
    }
    
    public CompiledBlockCallback19 getBlockCallback19(final String method, final String file, final int line, final Object scriptObject) {
        try {
            final Class scriptClass = scriptObject.getClass();
            final Method blockMethod = scriptClass.getMethod(method, scriptClass, ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class);
            return new CompiledBlockCallback19() {
                public IRubyObject call(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
                    try {
                        return (IRubyObject)blockMethod.invoke(null, scriptObject, context, self, args, block);
                    }
                    catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                    catch (IllegalArgumentException ex2) {
                        throw new RuntimeException(ex2);
                    }
                    catch (InvocationTargetException ex3) {
                        final Throwable cause = ex3.getCause();
                        if (cause instanceof RuntimeException) {
                            throw (RuntimeException)cause;
                        }
                        if (cause instanceof Error) {
                            throw (Error)cause;
                        }
                        throw new RuntimeException(ex3);
                    }
                }
                
                public String getFile() {
                    return file;
                }
                
                public int getLine() {
                    return line;
                }
            };
        }
        catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        }
    }
}
