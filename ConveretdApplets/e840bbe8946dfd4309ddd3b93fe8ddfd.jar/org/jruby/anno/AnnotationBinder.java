// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.anno;

import org.jruby.util.CodegenUtils;
import com.sun.mirror.declaration.ParameterDeclaration;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.Map;
import com.sun.mirror.declaration.Modifier;
import com.sun.mirror.type.ReferenceType;
import java.util.HashSet;
import java.util.HashMap;
import org.jruby.CompatVersion;
import com.sun.mirror.declaration.MethodDeclaration;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import com.sun.mirror.declaration.ClassDeclaration;
import java.io.PrintStream;
import com.sun.mirror.util.SimpleDeclarationVisitor;
import java.util.Iterator;
import java.io.FileWriter;
import com.sun.mirror.util.DeclarationVisitor;
import com.sun.mirror.util.DeclarationVisitors;
import com.sun.mirror.declaration.TypeDeclaration;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import java.util.Set;
import java.util.Collection;
import com.sun.mirror.apt.AnnotationProcessorFactory;

public class AnnotationBinder implements AnnotationProcessorFactory
{
    private static final Collection<String> supportedAnnotations;
    private static final Collection<String> supportedOptions;
    
    public Collection<String> supportedAnnotationTypes() {
        return AnnotationBinder.supportedAnnotations;
    }
    
    public Collection<String> supportedOptions() {
        return AnnotationBinder.supportedOptions;
    }
    
    public AnnotationProcessor getProcessorFor(final Set<AnnotationTypeDeclaration> atds, final AnnotationProcessorEnvironment env) {
        return (AnnotationProcessor)new AnnotationBindingProcessor(env);
    }
    
    static {
        supportedAnnotations = Collections.unmodifiableCollection((Collection<? extends String>)Arrays.asList("org.jruby.anno.JRubyMethod", "org.jruby.anno.JRubyClass"));
        supportedOptions = Collections.emptySet();
    }
    
    private static class AnnotationBindingProcessor implements AnnotationProcessor
    {
        private final AnnotationProcessorEnvironment env;
        private final List<String> classNames;
        
        AnnotationBindingProcessor(final AnnotationProcessorEnvironment env) {
            this.classNames = new ArrayList<String>();
            this.env = env;
        }
        
        public void process() {
            for (final TypeDeclaration typeDecl : this.env.getSpecifiedTypeDeclarations()) {
                typeDecl.accept(DeclarationVisitors.getDeclarationScanner((DeclarationVisitor)new RubyClassVisitor(), DeclarationVisitors.NO_OP));
            }
            try {
                final FileWriter fw = new FileWriter("src_gen/annotated_classes.txt");
                for (final String name : this.classNames) {
                    fw.write(name);
                    fw.write(10);
                }
                fw.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        
        public static int getArityValue(final JRubyMethod anno, final int actualRequired) {
            if (anno.optional() > 0 || anno.rest()) {
                return -(actualRequired + 1);
            }
            return actualRequired;
        }
        
        public static String getCallConfigNameByAnno(final JRubyMethod anno) {
            return getCallConfigName(anno.frame(), anno.scope(), anno.backtrace());
        }
        
        public static String getCallConfigName(final boolean frame, final boolean scope, final boolean backtrace) {
            if (frame) {
                if (scope) {
                    return "FrameFullScopeFull";
                }
                return "FrameFullScopeNone";
            }
            else if (scope) {
                if (backtrace) {
                    return "FrameBacktraceScopeFull";
                }
                return "FrameNoneScopeFull";
            }
            else {
                if (backtrace) {
                    return "FrameBacktraceScopeNone";
                }
                return "FrameNoneScopeNone";
            }
        }
        
        private class RubyClassVisitor extends SimpleDeclarationVisitor
        {
            private PrintStream out;
            private static final boolean DEBUG = false;
            
            public void visitClassDeclaration(final ClassDeclaration cd) {
                try {
                    final String qualifiedName = cd.getQualifiedName().replace('.', '$');
                    if (!qualifiedName.contains("org$jruby")) {
                        return;
                    }
                    final ByteArrayOutputStream bytes = new ByteArrayOutputStream(1024);
                    (this.out = new PrintStream(bytes)).println("/* THIS FILE IS GENERATED. DO NOT EDIT */");
                    this.out.println("package org.jruby.gen;");
                    this.out.println("import org.jruby.Ruby;");
                    this.out.println("import org.jruby.RubyModule;");
                    this.out.println("import org.jruby.RubyClass;");
                    this.out.println("import org.jruby.CompatVersion;");
                    this.out.println("import org.jruby.anno.TypePopulator;");
                    this.out.println("import org.jruby.internal.runtime.methods.CallConfiguration;");
                    this.out.println("import org.jruby.internal.runtime.methods.JavaMethod;");
                    this.out.println("import org.jruby.internal.runtime.methods.DynamicMethod;");
                    this.out.println("import org.jruby.runtime.Arity;");
                    this.out.println("import org.jruby.runtime.Visibility;");
                    this.out.println("import org.jruby.compiler.ASTInspector;");
                    this.out.println("import java.util.Arrays;");
                    this.out.println("import java.util.List;");
                    this.out.println("public class " + qualifiedName + "$Populator extends TypePopulator {");
                    this.out.println("    public void populate(RubyModule cls, Class clazz) {");
                    boolean hasMeta = false;
                    boolean hasModule = false;
                    boolean hasCompat = false;
                    for (final MethodDeclaration md : cd.getMethods()) {
                        final JRubyMethod anno = (JRubyMethod)md.getAnnotation((Class)JRubyMethod.class);
                        if (anno == null) {
                            continue;
                        }
                        hasMeta |= anno.meta();
                        hasModule |= anno.module();
                        hasCompat |= (anno.compat() != CompatVersion.BOTH);
                    }
                    this.out.println("        JavaMethod javaMethod;");
                    this.out.println("        DynamicMethod moduleMethod;");
                    if (hasMeta || hasModule) {
                        this.out.println("        RubyClass singletonClass = cls.getSingletonClass();");
                    }
                    if (hasCompat) {
                        this.out.println("        CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();");
                    }
                    this.out.println("        Ruby runtime = cls.getRuntime();");
                    final Map<String, List<MethodDeclaration>> annotatedMethods = new HashMap<String, List<MethodDeclaration>>();
                    final Map<String, List<MethodDeclaration>> staticAnnotatedMethods = new HashMap<String, List<MethodDeclaration>>();
                    final Map<String, List<MethodDeclaration>> annotatedMethods1_8 = new HashMap<String, List<MethodDeclaration>>();
                    final Map<String, List<MethodDeclaration>> staticAnnotatedMethods1_8 = new HashMap<String, List<MethodDeclaration>>();
                    final Map<String, List<MethodDeclaration>> annotatedMethods1_9 = new HashMap<String, List<MethodDeclaration>>();
                    final Map<String, List<MethodDeclaration>> staticAnnotatedMethods1_9 = new HashMap<String, List<MethodDeclaration>>();
                    final Set<String> frameAwareMethods = new HashSet<String>();
                    final Set<String> scopeAwareMethods = new HashSet<String>();
                    int methodCount = 0;
                    for (final MethodDeclaration md2 : cd.getMethods()) {
                        final JRubyMethod anno2 = (JRubyMethod)md2.getAnnotation((Class)JRubyMethod.class);
                        if (anno2 == null) {
                            continue;
                        }
                        ++methodCount;
                        if (md2.getThrownTypes().size() != 0) {
                            System.err.print("Method " + cd.toString() + "." + md2.toString() + " should not throw exceptions: ");
                            boolean comma = false;
                            for (final ReferenceType thrownType : md2.getThrownTypes()) {
                                if (comma) {
                                    System.err.print(", ");
                                }
                                System.err.print(thrownType);
                                comma = true;
                            }
                            System.err.print("\n");
                        }
                        final String name = (anno2.name().length == 0) ? md2.getSimpleName() : anno2.name()[0];
                        Map<String, List<MethodDeclaration>> methodsHash = null;
                        if (md2.getModifiers().contains(Modifier.STATIC)) {
                            if (anno2.compat() == CompatVersion.RUBY1_8) {
                                methodsHash = staticAnnotatedMethods1_8;
                            }
                            else if (anno2.compat() == CompatVersion.RUBY1_9) {
                                methodsHash = staticAnnotatedMethods1_9;
                            }
                            else {
                                methodsHash = staticAnnotatedMethods;
                            }
                        }
                        else if (anno2.compat() == CompatVersion.RUBY1_8) {
                            methodsHash = annotatedMethods1_8;
                        }
                        else if (anno2.compat() == CompatVersion.RUBY1_9) {
                            methodsHash = annotatedMethods1_9;
                        }
                        else {
                            methodsHash = annotatedMethods;
                        }
                        List<MethodDeclaration> methodDescs = methodsHash.get(name);
                        if (methodDescs == null) {
                            methodDescs = new ArrayList<MethodDeclaration>();
                            methodsHash.put(name, methodDescs);
                        }
                        methodDescs.add(md2);
                        boolean frame = false;
                        boolean scope = false;
                        if (anno2.frame()) {
                            frame = true;
                        }
                        if (anno2.reads() != null) {
                            for (final FrameField read : anno2.reads()) {
                                switch (read) {
                                    case BACKREF:
                                    case LASTLINE: {
                                        scope = true;
                                        break;
                                    }
                                    default: {
                                        frame = true;
                                        break;
                                    }
                                }
                            }
                        }
                        if (anno2.writes() != null) {
                            for (final FrameField write : anno2.writes()) {
                                switch (write) {
                                    case BACKREF:
                                    case LASTLINE: {
                                        scope = true;
                                        break;
                                    }
                                    default: {
                                        frame = true;
                                        break;
                                    }
                                }
                            }
                        }
                        if (frame) {
                            frameAwareMethods.addAll(Arrays.asList(anno2.name()));
                        }
                        if (!scope) {
                            continue;
                        }
                        scopeAwareMethods.addAll(Arrays.asList(anno2.name()));
                    }
                    if (methodCount == 0) {
                        return;
                    }
                    AnnotationBindingProcessor.this.classNames.add(this.getActualQualifiedName((TypeDeclaration)cd));
                    this.processMethodDeclarations(staticAnnotatedMethods);
                    for (final Map.Entry<String, List<MethodDeclaration>> entry : staticAnnotatedMethods.entrySet()) {
                        final MethodDeclaration decl = entry.getValue().get(0);
                        if (!((JRubyMethod)decl.getAnnotation((Class)JRubyMethod.class)).omit()) {
                            this.addCoreMethodMapping(entry.getKey(), decl, this.out);
                        }
                    }
                    if (!staticAnnotatedMethods1_8.isEmpty()) {
                        this.out.println("        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {");
                        this.processMethodDeclarations(staticAnnotatedMethods1_8);
                        for (final Map.Entry<String, List<MethodDeclaration>> entry : staticAnnotatedMethods1_8.entrySet()) {
                            final MethodDeclaration decl = entry.getValue().get(0);
                            if (!((JRubyMethod)decl.getAnnotation((Class)JRubyMethod.class)).omit()) {
                                this.addCoreMethodMapping(entry.getKey(), decl, this.out);
                            }
                        }
                        this.out.println("        }");
                    }
                    if (!staticAnnotatedMethods1_9.isEmpty()) {
                        this.out.println("        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {");
                        this.processMethodDeclarations(staticAnnotatedMethods1_9);
                        for (final Map.Entry<String, List<MethodDeclaration>> entry : staticAnnotatedMethods1_9.entrySet()) {
                            final MethodDeclaration decl = entry.getValue().get(0);
                            if (!((JRubyMethod)decl.getAnnotation((Class)JRubyMethod.class)).omit()) {
                                this.addCoreMethodMapping(entry.getKey(), decl, this.out);
                            }
                        }
                        this.out.println("        }");
                    }
                    this.processMethodDeclarations(annotatedMethods);
                    for (final Map.Entry<String, List<MethodDeclaration>> entry : annotatedMethods.entrySet()) {
                        final MethodDeclaration decl = entry.getValue().get(0);
                        if (!((JRubyMethod)decl.getAnnotation((Class)JRubyMethod.class)).omit()) {
                            this.addCoreMethodMapping(entry.getKey(), decl, this.out);
                        }
                    }
                    if (!annotatedMethods1_8.isEmpty()) {
                        this.out.println("        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {");
                        this.processMethodDeclarations(annotatedMethods1_8);
                        for (final Map.Entry<String, List<MethodDeclaration>> entry : annotatedMethods1_8.entrySet()) {
                            final MethodDeclaration decl = entry.getValue().get(0);
                            if (!((JRubyMethod)decl.getAnnotation((Class)JRubyMethod.class)).omit()) {
                                this.addCoreMethodMapping(entry.getKey(), decl, this.out);
                            }
                        }
                        this.out.println("        }");
                    }
                    if (!annotatedMethods1_9.isEmpty()) {
                        this.out.println("        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {");
                        this.processMethodDeclarations(annotatedMethods1_9);
                        for (final Map.Entry<String, List<MethodDeclaration>> entry : annotatedMethods1_9.entrySet()) {
                            final MethodDeclaration decl = entry.getValue().get(0);
                            if (!((JRubyMethod)decl.getAnnotation((Class)JRubyMethod.class)).omit()) {
                                this.addCoreMethodMapping(entry.getKey(), decl, this.out);
                            }
                        }
                        this.out.println("        }");
                    }
                    this.out.println("    }");
                    this.out.println("    static {");
                    if (!frameAwareMethods.isEmpty()) {
                        final StringBuffer frameMethodsString = new StringBuffer();
                        boolean first = true;
                        for (final String name : frameAwareMethods) {
                            if (!first) {
                                frameMethodsString.append(',');
                            }
                            first = false;
                            frameMethodsString.append('\"').append(name).append('\"');
                        }
                        this.out.println("        ASTInspector.addFrameAwareMethods(" + (Object)frameMethodsString + ");");
                    }
                    if (!scopeAwareMethods.isEmpty()) {
                        final StringBuffer scopeMethodsString = new StringBuffer();
                        boolean first = true;
                        for (final String name : scopeAwareMethods) {
                            if (!first) {
                                scopeMethodsString.append(',');
                            }
                            first = false;
                            scopeMethodsString.append('\"').append(name).append('\"');
                        }
                        this.out.println("        ASTInspector.addScopeAwareMethods(" + (Object)scopeMethodsString + ");");
                    }
                    this.out.println("     }");
                    this.out.println("}");
                    this.out.close();
                    this.out = null;
                    final FileOutputStream fos = new FileOutputStream("src_gen/" + qualifiedName + "$Populator.java");
                    fos.write(bytes.toByteArray());
                    fos.close();
                }
                catch (IOException ioe) {
                    System.err.println("FAILED TO GENERATE:");
                    ioe.printStackTrace();
                    System.exit(1);
                }
            }
            
            public void processMethodDeclarations(final Map<String, List<MethodDeclaration>> declarations) {
                for (final Map.Entry<String, List<MethodDeclaration>> entry : declarations.entrySet()) {
                    final List<MethodDeclaration> list = entry.getValue();
                    if (list.size() == 1) {
                        this.processMethodDeclaration(list.get(0));
                    }
                    else {
                        this.processMethodDeclarationMulti(list.get(0));
                    }
                }
            }
            
            public void processMethodDeclaration(final MethodDeclaration md) {
                final JRubyMethod anno = (JRubyMethod)md.getAnnotation((Class)JRubyMethod.class);
                if (anno != null && this.out != null) {
                    final boolean isStatic = md.getModifiers().contains(Modifier.STATIC);
                    final String qualifiedName = this.getActualQualifiedName(md.getDeclaringType());
                    boolean hasContext = false;
                    boolean hasBlock = false;
                    final StringBuffer buffer = new StringBuffer();
                    boolean first = true;
                    for (final ParameterDeclaration pd : md.getParameters()) {
                        if (!first) {
                            buffer.append(", ");
                        }
                        first = false;
                        buffer.append(pd.getType().toString());
                        buffer.append(".class");
                        hasContext |= pd.getType().toString().equals("org.jruby.runtime.ThreadContext");
                        hasBlock |= pd.getType().toString().equals("org.jruby.runtime.Block");
                    }
                    final int actualRequired = this.calculateActualRequired(md, md.getParameters().size(), anno.optional(), anno.rest(), isStatic, hasContext, hasBlock);
                    final String annotatedBindingName = CodegenUtils.getAnnotatedBindingClassName(md.getSimpleName(), qualifiedName, isStatic, actualRequired, anno.optional(), false, anno.frame());
                    final String implClass = anno.meta() ? "singletonClass" : "cls";
                    this.out.println("        javaMethod = new " + annotatedBindingName + "(" + implClass + ", Visibility." + anno.visibility() + ");");
                    this.out.println("        populateMethod(javaMethod, " + AnnotationBindingProcessor.getArityValue(anno, actualRequired) + ", \"" + md.getSimpleName() + "\", " + isStatic + ", " + "CallConfiguration." + AnnotationBindingProcessor.getCallConfigNameByAnno(anno) + ", " + anno.notImplemented() + ");");
                    this.out.println("        javaMethod.setNativeCall(" + md.getDeclaringType().getQualifiedName() + ".class, " + "\"" + md.getSimpleName() + "\", " + md.getReturnType().toString() + ".class, " + "new Class[] {" + buffer.toString() + "}, " + md.getModifiers().contains(Modifier.STATIC) + ");");
                    this.generateMethodAddCalls(md, anno);
                }
            }
            
            public void processMethodDeclarationMulti(final MethodDeclaration md) {
                final JRubyMethod anno = (JRubyMethod)md.getAnnotation((Class)JRubyMethod.class);
                if (anno != null && this.out != null) {
                    final boolean isStatic = md.getModifiers().contains(Modifier.STATIC);
                    final String qualifiedName = this.getActualQualifiedName(md.getDeclaringType());
                    boolean hasContext = false;
                    boolean hasBlock = false;
                    for (final ParameterDeclaration pd : md.getParameters()) {
                        hasContext |= pd.getType().toString().equals("org.jruby.runtime.ThreadContext");
                        hasBlock |= pd.getType().toString().equals("org.jruby.runtime.Block");
                    }
                    final int actualRequired = this.calculateActualRequired(md, md.getParameters().size(), anno.optional(), anno.rest(), isStatic, hasContext, hasBlock);
                    final String annotatedBindingName = CodegenUtils.getAnnotatedBindingClassName(md.getSimpleName(), qualifiedName, isStatic, actualRequired, anno.optional(), true, anno.frame());
                    final String implClass = anno.meta() ? "singletonClass" : "cls";
                    this.out.println("        javaMethod = new " + annotatedBindingName + "(" + implClass + ", Visibility." + anno.visibility() + ");");
                    this.out.println("        populateMethod(javaMethod, -1, \"" + md.getSimpleName() + "\", " + isStatic + ", " + "CallConfiguration." + AnnotationBindingProcessor.getCallConfigNameByAnno(anno) + ", " + anno.notImplemented() + ");");
                    this.generateMethodAddCalls(md, anno);
                }
            }
            
            private void addCoreMethodMapping(final String rubyName, final MethodDeclaration decl, final PrintStream out) {
                out.println("        runtime.addBoundMethod(\"" + decl.getDeclaringType().getQualifiedName() + "." + decl.getSimpleName() + "\", \"" + rubyName + "\");");
            }
            
            private String getActualQualifiedName(final TypeDeclaration td) {
                String qualifiedName;
                if (td.getDeclaringType() != null) {
                    if (td.getDeclaringType().getDeclaringType() != null) {
                        qualifiedName = td.getDeclaringType().getDeclaringType().getQualifiedName() + "$" + td.getDeclaringType().getSimpleName() + "$" + td.getSimpleName();
                    }
                    else {
                        qualifiedName = td.getDeclaringType().getQualifiedName() + "$" + td.getSimpleName();
                    }
                }
                else {
                    qualifiedName = td.getQualifiedName();
                }
                return qualifiedName;
            }
            
            private int calculateActualRequired(final MethodDeclaration md, final int paramsLength, final int optional, final boolean rest, final boolean isStatic, final boolean hasContext, final boolean hasBlock) {
                int actualRequired;
                if (optional == 0 && !rest) {
                    int args = paramsLength;
                    if (args == 0) {
                        actualRequired = 0;
                    }
                    else {
                        if (isStatic) {
                            --args;
                        }
                        if (hasContext) {
                            --args;
                        }
                        if (hasBlock) {
                            --args;
                        }
                        actualRequired = args;
                    }
                }
                else {
                    int args = paramsLength;
                    if (args == 0) {
                        actualRequired = 0;
                    }
                    else {
                        if (isStatic) {
                            --args;
                        }
                        if (hasContext) {
                            --args;
                        }
                        if (hasBlock) {
                            --args;
                        }
                        actualRequired = --args;
                    }
                    if (actualRequired != 0) {
                        throw new RuntimeException("Combining specific args with IRubyObject[] is not yet supported: " + md.getDeclaringType().getQualifiedName() + "." + md.toString());
                    }
                }
                return actualRequired;
            }
            
            public void generateMethodAddCalls(final MethodDeclaration md, final JRubyMethod jrubyMethod) {
                if (jrubyMethod.meta()) {
                    this.defineMethodOnClass("javaMethod", "singletonClass", jrubyMethod, md);
                }
                else {
                    this.defineMethodOnClass("javaMethod", "cls", jrubyMethod, md);
                    if (jrubyMethod.module()) {
                        this.out.println("        moduleMethod = populateModuleMethod(cls, javaMethod);");
                        this.defineMethodOnClass("moduleMethod", "singletonClass", jrubyMethod, md);
                    }
                }
            }
            
            private void defineMethodOnClass(final String methodVar, final String classVar, final JRubyMethod jrubyMethod, final MethodDeclaration md) {
                String baseName;
                if (jrubyMethod.name().length == 0) {
                    baseName = md.getSimpleName();
                    this.out.println("        " + classVar + ".addMethodAtBootTimeOnly(\"" + baseName + "\", " + methodVar + ");");
                }
                else {
                    baseName = jrubyMethod.name()[0];
                    for (final String name : jrubyMethod.name()) {
                        this.out.println("        " + classVar + ".addMethodAtBootTimeOnly(\"" + name + "\", " + methodVar + ");");
                    }
                }
                if (jrubyMethod.alias().length > 0) {
                    for (final String alias : jrubyMethod.alias()) {
                        this.out.println("        " + classVar + ".defineAlias(\"" + alias + "\", \"" + baseName + "\");");
                    }
                }
            }
        }
    }
}
