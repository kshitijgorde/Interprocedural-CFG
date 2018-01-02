// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.util;

import org.jruby.org.objectweb.asm.MethodVisitor;
import org.jruby.org.objectweb.asm.FieldVisitor;
import org.jruby.org.objectweb.asm.Attribute;
import org.jruby.org.objectweb.asm.AnnotationVisitor;
import org.jruby.org.objectweb.asm.signature.SignatureVisitor;
import org.jruby.org.objectweb.asm.signature.SignatureReader;
import java.io.OutputStream;
import java.io.InputStream;
import org.jruby.org.objectweb.asm.ClassReader;
import java.io.FileInputStream;
import java.io.PrintWriter;
import org.jruby.org.objectweb.asm.ClassVisitor;

public class TraceClassVisitor extends TraceAbstractVisitor implements ClassVisitor
{
    protected final ClassVisitor cv;
    protected final PrintWriter pw;
    
    public static void main(final String[] array) throws Exception {
        int n = 0;
        int n2 = 2;
        int n3 = 1;
        if (array.length < 1 || array.length > 2) {
            n3 = 0;
        }
        if (n3 != 0 && "-debug".equals(array[0])) {
            n = 1;
            n2 = 0;
            if (array.length != 2) {
                n3 = 0;
            }
        }
        if (n3 == 0) {
            System.err.println("Prints a disassembled view of the given class.");
            System.err.println("Usage: TraceClassVisitor [-debug] <fully qualified class name or class file name>");
            return;
        }
        ClassReader classReader;
        if (array[n].endsWith(".class") || array[n].indexOf(92) > -1 || array[n].indexOf(47) > -1) {
            classReader = new ClassReader(new FileInputStream(array[n]));
        }
        else {
            classReader = new ClassReader(array[n]);
        }
        classReader.accept(new TraceClassVisitor(new PrintWriter(System.out)), AbstractVisitor.getDefaultAttributes(), n2);
    }
    
    public TraceClassVisitor(final PrintWriter printWriter) {
        this(null, printWriter);
    }
    
    public TraceClassVisitor(final ClassVisitor cv, final PrintWriter pw) {
        this.cv = cv;
        this.pw = pw;
    }
    
    public void visit(final int n, final int n2, final String s, final String s2, final String s3, final String[] array) {
        final int n3 = n & 0xFFFF;
        final int n4 = n >>> 16;
        this.buf.setLength(0);
        this.buf.append("// class version ").append(n3).append('.').append(n4).append(" (").append(n).append(")\n");
        if ((n2 & 0x20000) != 0x0) {
            this.buf.append("// DEPRECATED\n");
        }
        this.buf.append("// access flags 0x").append(Integer.toHexString(n2).toUpperCase()).append('\n');
        this.appendDescriptor(5, s2);
        if (s2 != null) {
            final TraceSignatureVisitor traceSignatureVisitor = new TraceSignatureVisitor(n2);
            new SignatureReader(s2).accept(traceSignatureVisitor);
            this.buf.append("// declaration: ").append(s).append(traceSignatureVisitor.getDeclaration()).append('\n');
        }
        this.appendAccess(n2 & 0xFFFFFFDF);
        if ((n2 & 0x2000) != 0x0) {
            this.buf.append("@interface ");
        }
        else if ((n2 & 0x200) != 0x0) {
            this.buf.append("interface ");
        }
        else if ((n2 & 0x4000) == 0x0) {
            this.buf.append("class ");
        }
        this.appendDescriptor(0, s);
        if (s3 != null && !"java/lang/Object".equals(s3)) {
            this.buf.append(" extends ");
            this.appendDescriptor(0, s3);
            this.buf.append(' ');
        }
        if (array != null && array.length > 0) {
            this.buf.append(" implements ");
            for (int i = 0; i < array.length; ++i) {
                this.appendDescriptor(0, array[i]);
                this.buf.append(' ');
            }
        }
        this.buf.append(" {\n\n");
        this.text.add(this.buf.toString());
        if (this.cv != null) {
            this.cv.visit(n, n2, s, s2, s3, array);
        }
    }
    
    public void visitSource(final String s, final String s2) {
        this.buf.setLength(0);
        if (s != null) {
            this.buf.append(this.tab).append("// compiled from: ").append(s).append('\n');
        }
        if (s2 != null) {
            this.buf.append(this.tab).append("// debug info: ").append(s2).append('\n');
        }
        if (this.buf.length() > 0) {
            this.text.add(this.buf.toString());
        }
        if (this.cv != null) {
            this.cv.visitSource(s, s2);
        }
    }
    
    public void visitOuterClass(final String s, final String s2, final String s3) {
        this.buf.setLength(0);
        this.buf.append(this.tab).append("OUTERCLASS ");
        this.appendDescriptor(0, s);
        this.buf.append(' ');
        if (s2 != null) {
            this.buf.append(s2).append(' ');
        }
        this.appendDescriptor(3, s3);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
        if (this.cv != null) {
            this.cv.visitOuterClass(s, s2, s3);
        }
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        this.text.add("\n");
        final AnnotationVisitor visitAnnotation = super.visitAnnotation(s, b);
        if (this.cv != null) {
            ((TraceAnnotationVisitor)visitAnnotation).av = this.cv.visitAnnotation(s, b);
        }
        return visitAnnotation;
    }
    
    public void visitAttribute(final Attribute attribute) {
        this.text.add("\n");
        super.visitAttribute(attribute);
        if (this.cv != null) {
            this.cv.visitAttribute(attribute);
        }
    }
    
    public void visitInnerClass(final String s, final String s2, final String s3, final int n) {
        this.buf.setLength(0);
        this.buf.append(this.tab).append("// access flags 0x");
        this.buf.append(Integer.toHexString(n & 0xFFFFFFDF).toUpperCase()).append('\n');
        this.buf.append(this.tab);
        this.appendAccess(n);
        this.buf.append("INNERCLASS ");
        this.appendDescriptor(0, s);
        this.buf.append(' ');
        this.appendDescriptor(0, s2);
        this.buf.append(' ');
        this.appendDescriptor(0, s3);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
        if (this.cv != null) {
            this.cv.visitInnerClass(s, s2, s3, n);
        }
    }
    
    public FieldVisitor visitField(final int n, final String s, final String s2, final String s3, final Object o) {
        this.buf.setLength(0);
        this.buf.append('\n');
        if ((n & 0x20000) != 0x0) {
            this.buf.append(this.tab).append("// DEPRECATED\n");
        }
        this.buf.append(this.tab).append("// access flags 0x").append(Integer.toHexString(n).toUpperCase()).append('\n');
        if (s3 != null) {
            this.buf.append(this.tab);
            this.appendDescriptor(2, s3);
            final TraceSignatureVisitor traceSignatureVisitor = new TraceSignatureVisitor(0);
            new SignatureReader(s3).acceptType(traceSignatureVisitor);
            this.buf.append(this.tab).append("// declaration: ").append(traceSignatureVisitor.getDeclaration()).append('\n');
        }
        this.buf.append(this.tab);
        this.appendAccess(n);
        this.appendDescriptor(1, s2);
        this.buf.append(' ').append(s);
        if (o != null) {
            this.buf.append(" = ");
            if (o instanceof String) {
                this.buf.append('\"').append(o).append('\"');
            }
            else {
                this.buf.append(o);
            }
        }
        this.buf.append('\n');
        this.text.add(this.buf.toString());
        final TraceFieldVisitor traceFieldVisitor = this.createTraceFieldVisitor();
        this.text.add(traceFieldVisitor.getText());
        if (this.cv != null) {
            traceFieldVisitor.fv = this.cv.visitField(n, s, s2, s3, o);
        }
        return traceFieldVisitor;
    }
    
    public MethodVisitor visitMethod(final int n, final String s, final String s2, final String s3, final String[] array) {
        this.buf.setLength(0);
        this.buf.append('\n');
        if ((n & 0x20000) != 0x0) {
            this.buf.append(this.tab).append("// DEPRECATED\n");
        }
        this.buf.append(this.tab).append("// access flags 0x").append(Integer.toHexString(n).toUpperCase()).append('\n');
        if (s3 != null) {
            this.buf.append(this.tab);
            this.appendDescriptor(4, s3);
            final TraceSignatureVisitor traceSignatureVisitor = new TraceSignatureVisitor(0);
            new SignatureReader(s3).accept(traceSignatureVisitor);
            final String declaration = traceSignatureVisitor.getDeclaration();
            final String returnType = traceSignatureVisitor.getReturnType();
            final String exceptions = traceSignatureVisitor.getExceptions();
            this.buf.append(this.tab).append("// declaration: ").append(returnType).append(' ').append(s).append(declaration);
            if (exceptions != null) {
                this.buf.append(" throws ").append(exceptions);
            }
            this.buf.append('\n');
        }
        this.buf.append(this.tab);
        this.appendAccess(n);
        if ((n & 0x100) != 0x0) {
            this.buf.append("native ");
        }
        if ((n & 0x80) != 0x0) {
            this.buf.append("varargs ");
        }
        if ((n & 0x40) != 0x0) {
            this.buf.append("bridge ");
        }
        this.buf.append(s);
        this.appendDescriptor(3, s2);
        if (array != null && array.length > 0) {
            this.buf.append(" throws ");
            for (int i = 0; i < array.length; ++i) {
                this.appendDescriptor(0, array[i]);
                this.buf.append(' ');
            }
        }
        this.buf.append('\n');
        this.text.add(this.buf.toString());
        final TraceMethodVisitor traceMethodVisitor = this.createTraceMethodVisitor();
        this.text.add(traceMethodVisitor.getText());
        if (this.cv != null) {
            traceMethodVisitor.mv = this.cv.visitMethod(n, s, s2, s3, array);
        }
        return traceMethodVisitor;
    }
    
    public void visitEnd() {
        this.text.add("}\n");
        this.print(this.pw);
        this.pw.flush();
        if (this.cv != null) {
            this.cv.visitEnd();
        }
    }
    
    protected TraceFieldVisitor createTraceFieldVisitor() {
        return new TraceFieldVisitor();
    }
    
    protected TraceMethodVisitor createTraceMethodVisitor() {
        return new TraceMethodVisitor();
    }
    
    private void appendAccess(final int n) {
        if ((n & 0x1) != 0x0) {
            this.buf.append("public ");
        }
        if ((n & 0x2) != 0x0) {
            this.buf.append("private ");
        }
        if ((n & 0x4) != 0x0) {
            this.buf.append("protected ");
        }
        if ((n & 0x10) != 0x0) {
            this.buf.append("final ");
        }
        if ((n & 0x8) != 0x0) {
            this.buf.append("static ");
        }
        if ((n & 0x20) != 0x0) {
            this.buf.append("synchronized ");
        }
        if ((n & 0x40) != 0x0) {
            this.buf.append("volatile ");
        }
        if ((n & 0x80) != 0x0) {
            this.buf.append("transient ");
        }
        if ((n & 0x400) != 0x0) {
            this.buf.append("abstract ");
        }
        if ((n & 0x800) != 0x0) {
            this.buf.append("strictfp ");
        }
        if ((n & 0x4000) != 0x0) {
            this.buf.append("enum ");
        }
    }
}
