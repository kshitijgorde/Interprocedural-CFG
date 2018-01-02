// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.util;

import org.jruby.org.objectweb.asm.AnnotationVisitor;
import org.jruby.org.objectweb.asm.MethodVisitor;
import org.jruby.org.objectweb.asm.FieldVisitor;
import java.io.OutputStream;
import java.io.InputStream;
import org.jruby.org.objectweb.asm.ClassReader;
import java.io.FileInputStream;
import java.io.PrintWriter;
import org.jruby.org.objectweb.asm.ClassVisitor;

public class ASMifierClassVisitor extends ASMifierAbstractVisitor implements ClassVisitor
{
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
            System.err.println("Prints the ASM code to generate the given class.");
            System.err.println("Usage: ASMifierClassVisitor [-debug] <fully qualified class name or class file name>");
            return;
        }
        ClassReader classReader;
        if (array[n].endsWith(".class") || array[n].indexOf(92) > -1 || array[n].indexOf(47) > -1) {
            classReader = new ClassReader(new FileInputStream(array[n]));
        }
        else {
            classReader = new ClassReader(array[n]);
        }
        classReader.accept(new ASMifierClassVisitor(new PrintWriter(System.out)), AbstractVisitor.getDefaultAttributes(), n2);
    }
    
    public ASMifierClassVisitor(final PrintWriter pw) {
        super("cw");
        this.pw = pw;
    }
    
    public void visit(final int n, final int n2, final String s, final String s2, final String s3, final String[] array) {
        final int lastIndex = s.lastIndexOf(47);
        String substring;
        if (lastIndex == -1) {
            substring = s;
        }
        else {
            this.text.add("package asm." + s.substring(0, lastIndex).replace('/', '.') + ";\n");
            substring = s.substring(lastIndex + 1);
        }
        this.text.add("import java.util.*;\n");
        this.text.add("import org.objectweb.asm.*;\n");
        this.text.add("import org.objectweb.asm.attrs.*;\n");
        this.text.add("public class " + substring + "Dump implements Opcodes {\n\n");
        this.text.add("public static byte[] dump () throws Exception {\n\n");
        this.text.add("ClassWriter cw = new ClassWriter(0);\n");
        this.text.add("FieldVisitor fv;\n");
        this.text.add("MethodVisitor mv;\n");
        this.text.add("AnnotationVisitor av0;\n\n");
        this.buf.setLength(0);
        this.buf.append("cw.visit(");
        switch (n) {
            case 196653: {
                this.buf.append("V1_1");
                break;
            }
            case 46: {
                this.buf.append("V1_2");
                break;
            }
            case 47: {
                this.buf.append("V1_3");
                break;
            }
            case 48: {
                this.buf.append("V1_4");
                break;
            }
            case 49: {
                this.buf.append("V1_5");
                break;
            }
            case 50: {
                this.buf.append("V1_6");
                break;
            }
            default: {
                this.buf.append(n);
                break;
            }
        }
        this.buf.append(", ");
        this.appendAccess(n2 | 0x40000);
        this.buf.append(", ");
        this.appendConstant(s);
        this.buf.append(", ");
        this.appendConstant(s2);
        this.buf.append(", ");
        this.appendConstant(s3);
        this.buf.append(", ");
        if (array != null && array.length > 0) {
            this.buf.append("new String[] {");
            for (int i = 0; i < array.length; ++i) {
                this.buf.append((i == 0) ? " " : ", ");
                this.appendConstant(array[i]);
            }
            this.buf.append(" }");
        }
        else {
            this.buf.append("null");
        }
        this.buf.append(");\n\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitSource(final String s, final String s2) {
        this.buf.setLength(0);
        this.buf.append("cw.visitSource(");
        this.appendConstant(s);
        this.buf.append(", ");
        this.appendConstant(s2);
        this.buf.append(");\n\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitOuterClass(final String s, final String s2, final String s3) {
        this.buf.setLength(0);
        this.buf.append("cw.visitOuterClass(");
        this.appendConstant(s);
        this.buf.append(", ");
        this.appendConstant(s2);
        this.buf.append(", ");
        this.appendConstant(s3);
        this.buf.append(");\n\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitInnerClass(final String s, final String s2, final String s3, final int n) {
        this.buf.setLength(0);
        this.buf.append("cw.visitInnerClass(");
        this.appendConstant(s);
        this.buf.append(", ");
        this.appendConstant(s2);
        this.buf.append(", ");
        this.appendConstant(s3);
        this.buf.append(", ");
        this.appendAccess(n | 0x100000);
        this.buf.append(");\n\n");
        this.text.add(this.buf.toString());
    }
    
    public FieldVisitor visitField(final int n, final String s, final String s2, final String s3, final Object o) {
        this.buf.setLength(0);
        this.buf.append("{\n");
        this.buf.append("fv = cw.visitField(");
        this.appendAccess(n | 0x80000);
        this.buf.append(", ");
        this.appendConstant(s);
        this.buf.append(", ");
        this.appendConstant(s2);
        this.buf.append(", ");
        this.appendConstant(s3);
        this.buf.append(", ");
        this.appendConstant(o);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
        final ASMifierFieldVisitor asMifierFieldVisitor = new ASMifierFieldVisitor();
        this.text.add(asMifierFieldVisitor.getText());
        this.text.add("}\n");
        return asMifierFieldVisitor;
    }
    
    public MethodVisitor visitMethod(final int n, final String s, final String s2, final String s3, final String[] array) {
        this.buf.setLength(0);
        this.buf.append("{\n");
        this.buf.append("mv = cw.visitMethod(");
        this.appendAccess(n);
        this.buf.append(", ");
        this.appendConstant(s);
        this.buf.append(", ");
        this.appendConstant(s2);
        this.buf.append(", ");
        this.appendConstant(s3);
        this.buf.append(", ");
        if (array != null && array.length > 0) {
            this.buf.append("new String[] {");
            for (int i = 0; i < array.length; ++i) {
                this.buf.append((i == 0) ? " " : ", ");
                this.appendConstant(array[i]);
            }
            this.buf.append(" }");
        }
        else {
            this.buf.append("null");
        }
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
        final ASMifierMethodVisitor asMifierMethodVisitor = this.createASMifierMethodVisitor();
        this.text.add(asMifierMethodVisitor.getText());
        this.text.add("}\n");
        return asMifierMethodVisitor;
    }
    
    protected ASMifierMethodVisitor createASMifierMethodVisitor() {
        return new ASMifierMethodVisitor();
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        this.buf.setLength(0);
        this.buf.append("{\n");
        this.buf.append("av0 = cw.visitAnnotation(");
        this.appendConstant(s);
        this.buf.append(", ");
        this.buf.append(b);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
        final ASMifierAnnotationVisitor asMifierAnnotationVisitor = new ASMifierAnnotationVisitor(0);
        this.text.add(asMifierAnnotationVisitor.getText());
        this.text.add("}\n");
        return asMifierAnnotationVisitor;
    }
    
    public void visitEnd() {
        this.text.add("cw.visitEnd();\n\n");
        this.text.add("return cw.toByteArray();\n");
        this.text.add("}\n");
        this.text.add("}\n");
        AbstractVisitor.printList(this.pw, this.text);
        this.pw.flush();
    }
    
    void appendAccess(final int n) {
        int n2 = 1;
        if ((n & 0x1) != 0x0) {
            this.buf.append("ACC_PUBLIC");
            n2 = 0;
        }
        if ((n & 0x2) != 0x0) {
            this.buf.append("ACC_PRIVATE");
            n2 = 0;
        }
        if ((n & 0x4) != 0x0) {
            this.buf.append("ACC_PROTECTED");
            n2 = 0;
        }
        if ((n & 0x10) != 0x0) {
            if (n2 == 0) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_FINAL");
            n2 = 0;
        }
        if ((n & 0x8) != 0x0) {
            if (n2 == 0) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_STATIC");
            n2 = 0;
        }
        if ((n & 0x20) != 0x0) {
            if (n2 == 0) {
                this.buf.append(" + ");
            }
            if ((n & 0x40000) == 0x0) {
                this.buf.append("ACC_SYNCHRONIZED");
            }
            else {
                this.buf.append("ACC_SUPER");
            }
            n2 = 0;
        }
        if ((n & 0x40) != 0x0 && (n & 0x80000) != 0x0) {
            if (n2 == 0) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_VOLATILE");
            n2 = 0;
        }
        if ((n & 0x40) != 0x0 && (n & 0x40000) == 0x0 && (n & 0x80000) == 0x0) {
            if (n2 == 0) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_BRIDGE");
            n2 = 0;
        }
        if ((n & 0x80) != 0x0 && (n & 0x40000) == 0x0 && (n & 0x80000) == 0x0) {
            if (n2 == 0) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_VARARGS");
            n2 = 0;
        }
        if ((n & 0x80) != 0x0 && (n & 0x80000) != 0x0) {
            if (n2 == 0) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_TRANSIENT");
            n2 = 0;
        }
        if ((n & 0x100) != 0x0 && (n & 0x40000) == 0x0 && (n & 0x80000) == 0x0) {
            if (n2 == 0) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_NATIVE");
            n2 = 0;
        }
        if ((n & 0x4000) != 0x0 && ((n & 0x40000) != 0x0 || (n & 0x80000) != 0x0 || (n & 0x100000) != 0x0)) {
            if (n2 == 0) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_ENUM");
            n2 = 0;
        }
        if ((n & 0x2000) != 0x0 && (n & 0x40000) != 0x0) {
            if (n2 == 0) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_ANNOTATION");
            n2 = 0;
        }
        if ((n & 0x400) != 0x0) {
            if (n2 == 0) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_ABSTRACT");
            n2 = 0;
        }
        if ((n & 0x200) != 0x0) {
            if (n2 == 0) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_INTERFACE");
            n2 = 0;
        }
        if ((n & 0x800) != 0x0) {
            if (n2 == 0) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_STRICT");
            n2 = 0;
        }
        if ((n & 0x1000) != 0x0) {
            if (n2 == 0) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_SYNTHETIC");
            n2 = 0;
        }
        if ((n & 0x20000) != 0x0) {
            if (n2 == 0) {
                this.buf.append(" + ");
            }
            this.buf.append("ACC_DEPRECATED");
            n2 = 0;
        }
        if (n2 != 0) {
            this.buf.append('0');
        }
    }
}
