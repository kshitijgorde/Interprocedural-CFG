// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.util;

import org.jruby.org.objectweb.asm.Attribute;
import org.jruby.org.objectweb.asm.AnnotationVisitor;
import org.jruby.org.objectweb.asm.FieldVisitor;
import java.util.HashMap;
import org.jruby.org.objectweb.asm.tree.analysis.Frame;
import org.jruby.org.objectweb.asm.tree.TryCatchBlockNode;
import org.jruby.org.objectweb.asm.MethodVisitor;
import java.util.Iterator;
import org.jruby.org.objectweb.asm.tree.analysis.Interpreter;
import org.jruby.org.objectweb.asm.tree.analysis.Analyzer;
import java.util.List;
import org.jruby.org.objectweb.asm.tree.analysis.SimpleVerifier;
import org.jruby.org.objectweb.asm.tree.MethodNode;
import java.util.ArrayList;
import org.jruby.org.objectweb.asm.Type;
import org.jruby.org.objectweb.asm.ClassVisitor;
import org.jruby.org.objectweb.asm.tree.ClassNode;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.InputStream;
import org.jruby.org.objectweb.asm.ClassReader;
import java.io.FileInputStream;
import java.util.Map;
import org.jruby.org.objectweb.asm.ClassAdapter;

public class CheckClassAdapter extends ClassAdapter
{
    private int version;
    private boolean start;
    private boolean source;
    private boolean outer;
    private boolean end;
    private Map labels;
    private boolean checkDataFlow;
    
    public static void main(final String[] array) throws Exception {
        if (array.length != 1) {
            System.err.println("Verifies the given class.");
            System.err.println("Usage: CheckClassAdapter <fully qualified class name or class file name>");
            return;
        }
        ClassReader classReader;
        if (array[0].endsWith(".class")) {
            classReader = new ClassReader(new FileInputStream(array[0]));
        }
        else {
            classReader = new ClassReader(array[0]);
        }
        verify(classReader, false, new PrintWriter(System.err));
    }
    
    public static void verify(final ClassReader classReader, final ClassLoader classLoader, final boolean b, final PrintWriter printWriter) {
        final ClassNode classNode = new ClassNode();
        classReader.accept(new CheckClassAdapter(classNode, false), 2);
        final Type type = (classNode.superName == null) ? null : Type.getObjectType(classNode.superName);
        final List methods = classNode.methods;
        final ArrayList<Type> list = new ArrayList<Type>();
        final Iterator iterator = classNode.interfaces.iterator();
        while (iterator.hasNext()) {
            list.add(Type.getObjectType(iterator.next().toString()));
        }
        for (int i = 0; i < methods.size(); ++i) {
            final MethodNode methodNode = methods.get(i);
            final SimpleVerifier simpleVerifier = new SimpleVerifier(Type.getObjectType(classNode.name), type, list, (classNode.access | 0x200) != 0x0);
            final Analyzer analyzer = new Analyzer(simpleVerifier);
            if (classLoader != null) {
                simpleVerifier.setClassLoader(classLoader);
            }
            try {
                analyzer.analyze(classNode.name, methodNode);
                if (!b) {
                    continue;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace(printWriter);
            }
            printAnalyzerResult(methodNode, analyzer, printWriter);
        }
        printWriter.flush();
    }
    
    public static void verify(final ClassReader classReader, final boolean b, final PrintWriter printWriter) {
        verify(classReader, null, b, printWriter);
    }
    
    static void printAnalyzerResult(final MethodNode methodNode, final Analyzer analyzer, final PrintWriter printWriter) {
        final Frame[] frames = analyzer.getFrames();
        final TraceMethodVisitor traceMethodVisitor = new TraceMethodVisitor();
        printWriter.println(methodNode.name + methodNode.desc);
        for (int i = 0; i < methodNode.instructions.size(); ++i) {
            methodNode.instructions.get(i).accept(traceMethodVisitor);
            final StringBuffer sb = new StringBuffer();
            final Frame frame = frames[i];
            if (frame == null) {
                sb.append('?');
            }
            else {
                for (int j = 0; j < frame.getLocals(); ++j) {
                    sb.append(getShortName(frame.getLocal(j).toString())).append(' ');
                }
                sb.append(" : ");
                for (int k = 0; k < frame.getStackSize(); ++k) {
                    sb.append(getShortName(frame.getStack(k).toString())).append(' ');
                }
            }
            while (sb.length() < methodNode.maxStack + methodNode.maxLocals + 1) {
                sb.append(' ');
            }
            printWriter.print(Integer.toString(i + 100000).substring(1));
            printWriter.print(" " + (Object)sb + " : " + (Object)traceMethodVisitor.buf);
        }
        for (int l = 0; l < methodNode.tryCatchBlocks.size(); ++l) {
            ((TryCatchBlockNode)methodNode.tryCatchBlocks.get(l)).accept(traceMethodVisitor);
            printWriter.print(" " + (Object)traceMethodVisitor.buf);
        }
        printWriter.println();
    }
    
    private static String getShortName(final String s) {
        final int lastIndex = s.lastIndexOf(47);
        int length = s.length();
        if (s.charAt(length - 1) == ';') {
            --length;
        }
        return (lastIndex == -1) ? s : s.substring(lastIndex + 1, length);
    }
    
    public CheckClassAdapter(final ClassVisitor classVisitor) {
        this(classVisitor, true);
    }
    
    public CheckClassAdapter(final ClassVisitor classVisitor, final boolean checkDataFlow) {
        super(classVisitor);
        this.labels = new HashMap();
        this.checkDataFlow = checkDataFlow;
    }
    
    public void visit(final int version, final int n, final String s, final String s2, final String s3, final String[] array) {
        if (this.start) {
            throw new IllegalStateException("visit must be called only once");
        }
        this.start = true;
        this.checkState();
        checkAccess(n, 423473);
        if (s == null || !s.endsWith("package-info")) {
            CheckMethodAdapter.checkInternalName(s, "class name");
        }
        if ("java/lang/Object".equals(s)) {
            if (s3 != null) {
                throw new IllegalArgumentException("The super class name of the Object class must be 'null'");
            }
        }
        else {
            CheckMethodAdapter.checkInternalName(s3, "super class name");
        }
        if (s2 != null) {
            CheckMethodAdapter.checkClassSignature(s2);
        }
        if ((n & 0x200) != 0x0 && !"java/lang/Object".equals(s3)) {
            throw new IllegalArgumentException("The super class name of interfaces must be 'java/lang/Object'");
        }
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                CheckMethodAdapter.checkInternalName(array[i], "interface name at index " + i);
            }
        }
        this.version = version;
        this.cv.visit(version, n, s, s2, s3, array);
    }
    
    public void visitSource(final String s, final String s2) {
        this.checkState();
        if (this.source) {
            throw new IllegalStateException("visitSource can be called only once.");
        }
        this.source = true;
        this.cv.visitSource(s, s2);
    }
    
    public void visitOuterClass(final String s, final String s2, final String s3) {
        this.checkState();
        if (this.outer) {
            throw new IllegalStateException("visitOuterClass can be called only once.");
        }
        this.outer = true;
        if (s == null) {
            throw new IllegalArgumentException("Illegal outer class owner");
        }
        if (s3 != null) {
            CheckMethodAdapter.checkMethodDesc(s3);
        }
        this.cv.visitOuterClass(s, s2, s3);
    }
    
    public void visitInnerClass(final String s, final String s2, final String s3, final int n) {
        this.checkState();
        CheckMethodAdapter.checkInternalName(s, "class name");
        if (s2 != null) {
            CheckMethodAdapter.checkInternalName(s2, "outer class name");
        }
        if (s3 != null) {
            CheckMethodAdapter.checkIdentifier(s3, "inner class name");
        }
        checkAccess(n, 30239);
        this.cv.visitInnerClass(s, s2, s3, n);
    }
    
    public FieldVisitor visitField(final int n, final String s, final String s2, final String s3, final Object o) {
        this.checkState();
        checkAccess(n, 413919);
        CheckMethodAdapter.checkUnqualifiedName(this.version, s, "field name");
        CheckMethodAdapter.checkDesc(s2, false);
        if (s3 != null) {
            CheckMethodAdapter.checkFieldSignature(s3);
        }
        if (o != null) {
            CheckMethodAdapter.checkConstant(o);
        }
        return new CheckFieldAdapter(this.cv.visitField(n, s, s2, s3, o));
    }
    
    public MethodVisitor visitMethod(final int n, final String s, final String s2, final String s3, final String[] array) {
        this.checkState();
        checkAccess(n, 400895);
        CheckMethodAdapter.checkMethodIdentifier(this.version, s, "method name");
        CheckMethodAdapter.checkMethodDesc(s2);
        if (s3 != null) {
            CheckMethodAdapter.checkMethodSignature(s3);
        }
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                CheckMethodAdapter.checkInternalName(array[i], "exception name at index " + i);
            }
        }
        CheckMethodAdapter checkMethodAdapter;
        if (this.checkDataFlow) {
            checkMethodAdapter = new CheckMethodAdapter(n, s, s2, this.cv.visitMethod(n, s, s2, s3, array), this.labels);
        }
        else {
            checkMethodAdapter = new CheckMethodAdapter(this.cv.visitMethod(n, s, s2, s3, array), this.labels);
        }
        checkMethodAdapter.version = this.version;
        return checkMethodAdapter;
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        this.checkState();
        CheckMethodAdapter.checkDesc(s, false);
        return new CheckAnnotationAdapter(this.cv.visitAnnotation(s, b));
    }
    
    public void visitAttribute(final Attribute attribute) {
        this.checkState();
        if (attribute == null) {
            throw new IllegalArgumentException("Invalid attribute (must not be null)");
        }
        this.cv.visitAttribute(attribute);
    }
    
    public void visitEnd() {
        this.checkState();
        this.end = true;
        this.cv.visitEnd();
    }
    
    private void checkState() {
        if (!this.start) {
            throw new IllegalStateException("Cannot visit member before visit has been called.");
        }
        if (this.end) {
            throw new IllegalStateException("Cannot visit member after visitEnd has been called.");
        }
    }
    
    static void checkAccess(final int n, final int n2) {
        if ((n & ~n2) != 0x0) {
            throw new IllegalArgumentException("Invalid access flags: " + n);
        }
        if (((((n & 0x1) != 0x0) + ((n & 0x2) != 0x0) + ((n & 0x4) != 0x0)) ? 1 : 0) > 1) {
            throw new IllegalArgumentException("public private and protected are mutually exclusive: " + n);
        }
        if (((((n & 0x10) != 0x0) + ((n & 0x400) != 0x0)) ? 1 : 0) > 1) {
            throw new IllegalArgumentException("final and abstract are mutually exclusive: " + n);
        }
    }
}
