// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.anno;

import java.util.Iterator;
import org.jruby.Ruby;
import org.jruby.compiler.ASTInspector;
import java.util.List;
import java.util.Map;
import org.jruby.runtime.MethodFactory;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.RubyModule;
import org.jruby.runtime.Arity;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.internal.runtime.methods.JavaMethod;

public abstract class TypePopulator
{
    public static final TypePopulator DEFAULT;
    
    public static void populateMethod(final JavaMethod javaMethod, final int arity, final String simpleName, final boolean isStatic, final CallConfiguration callConfig, final boolean notImplemented) {
        javaMethod.setIsBuiltin(true);
        javaMethod.setArity(Arity.createArity(arity));
        javaMethod.setJavaName(simpleName);
        javaMethod.setSingleton(isStatic);
        javaMethod.setCallConfig(callConfig);
        javaMethod.setNotImplemented(notImplemented);
    }
    
    public static DynamicMethod populateModuleMethod(final RubyModule cls, final JavaMethod javaMethod) {
        final DynamicMethod moduleMethod = javaMethod.dup();
        moduleMethod.setImplementationClass(cls.getSingletonClass());
        moduleMethod.setVisibility(Visibility.PUBLIC);
        return moduleMethod;
    }
    
    public abstract void populate(final RubyModule p0, final Class p1);
    
    static {
        DEFAULT = new DefaultTypePopulator();
    }
    
    public static class DefaultTypePopulator extends TypePopulator
    {
        public void populate(final RubyModule clsmod, final Class clazz) {
            final MethodFactory methodFactory = MethodFactory.createFactory(clsmod.getRuntime().getJRubyClassLoader());
            final Ruby runtime = clsmod.getRuntime();
            final RubyModule.MethodClumper clumper = new RubyModule.MethodClumper();
            clumper.clump(clazz);
            for (final Map.Entry<String, List<JavaMethodDescriptor>> entry : clumper.getAllAnnotatedMethods().entrySet()) {
                for (final JavaMethodDescriptor desc : entry.getValue()) {
                    final JRubyMethod anno = desc.anno;
                    if (anno.frame() || (anno.reads() != null && anno.reads().length >= 1) || (anno.writes() != null && anno.writes().length >= 1)) {
                        ASTInspector.addFrameAwareMethods(anno.name());
                        ASTInspector.addScopeAwareMethods(anno.name());
                    }
                }
            }
            for (final Map.Entry<String, List<JavaMethodDescriptor>> entry : clumper.getStaticAnnotatedMethods().entrySet()) {
                clsmod.defineAnnotatedMethod(entry.getKey(), entry.getValue(), methodFactory);
                for (final JavaMethodDescriptor desc : entry.getValue()) {
                    if (!desc.anno.omit()) {
                        runtime.addBoundMethod(desc.declaringClassName + "." + desc.name, entry.getKey());
                    }
                }
            }
            for (final Map.Entry<String, List<JavaMethodDescriptor>> entry : clumper.getAnnotatedMethods().entrySet()) {
                clsmod.defineAnnotatedMethod(entry.getKey(), entry.getValue(), methodFactory);
                for (final JavaMethodDescriptor desc : entry.getValue()) {
                    if (!desc.anno.omit()) {
                        runtime.addBoundMethod(desc.declaringClassName + "." + desc.name, entry.getKey());
                    }
                }
            }
            for (final Map.Entry<String, List<JavaMethodDescriptor>> entry : clumper.getStaticAnnotatedMethods1_8().entrySet()) {
                clsmod.defineAnnotatedMethod(entry.getKey(), entry.getValue(), methodFactory);
                for (final JavaMethodDescriptor desc : entry.getValue()) {
                    if (!desc.anno.omit()) {
                        runtime.addBoundMethod(desc.declaringClassName + "." + desc.name, entry.getKey());
                    }
                }
            }
            for (final Map.Entry<String, List<JavaMethodDescriptor>> entry : clumper.getAnnotatedMethods1_8().entrySet()) {
                clsmod.defineAnnotatedMethod(entry.getKey(), entry.getValue(), methodFactory);
                for (final JavaMethodDescriptor desc : entry.getValue()) {
                    if (!desc.anno.omit()) {
                        runtime.addBoundMethod(desc.declaringClassName + "." + desc.name, entry.getKey());
                    }
                }
            }
            for (final Map.Entry<String, List<JavaMethodDescriptor>> entry : clumper.getStaticAnnotatedMethods1_9().entrySet()) {
                clsmod.defineAnnotatedMethod(entry.getKey(), entry.getValue(), methodFactory);
                for (final JavaMethodDescriptor desc : entry.getValue()) {
                    if (!desc.anno.omit()) {
                        runtime.addBoundMethod(desc.declaringClassName + "." + desc.name, entry.getKey());
                    }
                }
            }
            for (final Map.Entry<String, List<JavaMethodDescriptor>> entry : clumper.getAnnotatedMethods1_9().entrySet()) {
                clsmod.defineAnnotatedMethod(entry.getKey(), entry.getValue(), methodFactory);
                for (final JavaMethodDescriptor desc : entry.getValue()) {
                    if (!desc.anno.omit()) {
                        runtime.addBoundMethod(desc.declaringClassName + "." + desc.name, entry.getKey());
                    }
                }
            }
        }
    }
}
