// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.util;

import org.jruby.org.objectweb.asm.Label;
import org.jruby.org.objectweb.asm.AnnotationVisitor;
import java.util.HashMap;
import org.jruby.org.objectweb.asm.MethodVisitor;

public class ASMifierMethodVisitor extends ASMifierAbstractVisitor implements MethodVisitor
{
    public ASMifierMethodVisitor() {
        super("mv");
        this.labelNames = new HashMap();
    }
    
    public AnnotationVisitor visitAnnotationDefault() {
        this.buf.setLength(0);
        this.buf.append("{\n").append("av0 = mv.visitAnnotationDefault();\n");
        this.text.add(this.buf.toString());
        final ASMifierAnnotationVisitor asMifierAnnotationVisitor = new ASMifierAnnotationVisitor(0);
        this.text.add(asMifierAnnotationVisitor.getText());
        this.text.add("}\n");
        return asMifierAnnotationVisitor;
    }
    
    public AnnotationVisitor visitParameterAnnotation(final int n, final String s, final boolean b) {
        this.buf.setLength(0);
        this.buf.append("{\n").append("av0 = mv.visitParameterAnnotation(").append(n).append(", ");
        this.appendConstant(s);
        this.buf.append(", ").append(b).append(");\n");
        this.text.add(this.buf.toString());
        final ASMifierAnnotationVisitor asMifierAnnotationVisitor = new ASMifierAnnotationVisitor(0);
        this.text.add(asMifierAnnotationVisitor.getText());
        this.text.add("}\n");
        return asMifierAnnotationVisitor;
    }
    
    public void visitCode() {
        this.text.add("mv.visitCode();\n");
    }
    
    public void visitFrame(final int n, final int n2, final Object[] array, final int n3, final Object[] array2) {
        this.buf.setLength(0);
        switch (n) {
            case -1:
            case 0: {
                this.declareFrameTypes(n2, array);
                this.declareFrameTypes(n3, array2);
                if (n == -1) {
                    this.buf.append("mv.visitFrame(Opcodes.F_NEW, ");
                }
                else {
                    this.buf.append("mv.visitFrame(Opcodes.F_FULL, ");
                }
                this.buf.append(n2).append(", new Object[] {");
                this.appendFrameTypes(n2, array);
                this.buf.append("}, ").append(n3).append(", new Object[] {");
                this.appendFrameTypes(n3, array2);
                this.buf.append('}');
                break;
            }
            case 1: {
                this.declareFrameTypes(n2, array);
                this.buf.append("mv.visitFrame(Opcodes.F_APPEND,").append(n2).append(", new Object[] {");
                this.appendFrameTypes(n2, array);
                this.buf.append("}, 0, null");
                break;
            }
            case 2: {
                this.buf.append("mv.visitFrame(Opcodes.F_CHOP,").append(n2).append(", null, 0, null");
                break;
            }
            case 3: {
                this.buf.append("mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null");
                break;
            }
            case 4: {
                this.declareFrameTypes(1, array2);
                this.buf.append("mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {");
                this.appendFrameTypes(1, array2);
                this.buf.append('}');
                break;
            }
        }
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitInsn(final int n) {
        this.buf.setLength(0);
        this.buf.append("mv.visitInsn(").append(ASMifierMethodVisitor.OPCODES[n]).append(");\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitIntInsn(final int n, final int n2) {
        this.buf.setLength(0);
        this.buf.append("mv.visitIntInsn(").append(ASMifierMethodVisitor.OPCODES[n]).append(", ").append((n == 188) ? ASMifierMethodVisitor.TYPES[n2] : Integer.toString(n2)).append(");\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitVarInsn(final int n, final int n2) {
        this.buf.setLength(0);
        this.buf.append("mv.visitVarInsn(").append(ASMifierMethodVisitor.OPCODES[n]).append(", ").append(n2).append(");\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitTypeInsn(final int n, final String s) {
        this.buf.setLength(0);
        this.buf.append("mv.visitTypeInsn(").append(ASMifierMethodVisitor.OPCODES[n]).append(", ");
        this.appendConstant(s);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitFieldInsn(final int n, final String s, final String s2, final String s3) {
        this.buf.setLength(0);
        this.buf.append("mv.visitFieldInsn(").append(ASMifierMethodVisitor.OPCODES[n]).append(", ");
        this.appendConstant(s);
        this.buf.append(", ");
        this.appendConstant(s2);
        this.buf.append(", ");
        this.appendConstant(s3);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitMethodInsn(final int n, final String s, final String s2, final String s3) {
        this.buf.setLength(0);
        this.buf.append("mv.visitMethodInsn(").append(ASMifierMethodVisitor.OPCODES[n]).append(", ");
        this.appendConstant(s);
        this.buf.append(", ");
        this.appendConstant(s2);
        this.buf.append(", ");
        this.appendConstant(s3);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitJumpInsn(final int n, final Label label) {
        this.buf.setLength(0);
        this.declareLabel(label);
        this.buf.append("mv.visitJumpInsn(").append(ASMifierMethodVisitor.OPCODES[n]).append(", ");
        this.appendLabel(label);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitLabel(final Label label) {
        this.buf.setLength(0);
        this.declareLabel(label);
        this.buf.append("mv.visitLabel(");
        this.appendLabel(label);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitLdcInsn(final Object o) {
        this.buf.setLength(0);
        this.buf.append("mv.visitLdcInsn(");
        this.appendConstant(o);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitIincInsn(final int n, final int n2) {
        this.buf.setLength(0);
        this.buf.append("mv.visitIincInsn(").append(n).append(", ").append(n2).append(");\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitTableSwitchInsn(final int n, final int n2, final Label label, final Label[] array) {
        this.buf.setLength(0);
        for (int i = 0; i < array.length; ++i) {
            this.declareLabel(array[i]);
        }
        this.declareLabel(label);
        this.buf.append("mv.visitTableSwitchInsn(").append(n).append(", ").append(n2).append(", ");
        this.appendLabel(label);
        this.buf.append(", new Label[] {");
        for (int j = 0; j < array.length; ++j) {
            this.buf.append((j == 0) ? " " : ", ");
            this.appendLabel(array[j]);
        }
        this.buf.append(" });\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitLookupSwitchInsn(final Label label, final int[] array, final Label[] array2) {
        this.buf.setLength(0);
        for (int i = 0; i < array2.length; ++i) {
            this.declareLabel(array2[i]);
        }
        this.declareLabel(label);
        this.buf.append("mv.visitLookupSwitchInsn(");
        this.appendLabel(label);
        this.buf.append(", new int[] {");
        for (int j = 0; j < array.length; ++j) {
            this.buf.append((j == 0) ? " " : ", ").append(array[j]);
        }
        this.buf.append(" }, new Label[] {");
        for (int k = 0; k < array2.length; ++k) {
            this.buf.append((k == 0) ? " " : ", ");
            this.appendLabel(array2[k]);
        }
        this.buf.append(" });\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitMultiANewArrayInsn(final String s, final int n) {
        this.buf.setLength(0);
        this.buf.append("mv.visitMultiANewArrayInsn(");
        this.appendConstant(s);
        this.buf.append(", ").append(n).append(");\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitTryCatchBlock(final Label label, final Label label2, final Label label3, final String s) {
        this.buf.setLength(0);
        this.declareLabel(label);
        this.declareLabel(label2);
        this.declareLabel(label3);
        this.buf.append("mv.visitTryCatchBlock(");
        this.appendLabel(label);
        this.buf.append(", ");
        this.appendLabel(label2);
        this.buf.append(", ");
        this.appendLabel(label3);
        this.buf.append(", ");
        this.appendConstant(s);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitLocalVariable(final String s, final String s2, final String s3, final Label label, final Label label2, final int n) {
        this.buf.setLength(0);
        this.buf.append("mv.visitLocalVariable(");
        this.appendConstant(s);
        this.buf.append(", ");
        this.appendConstant(s2);
        this.buf.append(", ");
        this.appendConstant(s3);
        this.buf.append(", ");
        this.appendLabel(label);
        this.buf.append(", ");
        this.appendLabel(label2);
        this.buf.append(", ").append(n).append(");\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitLineNumber(final int n, final Label label) {
        this.buf.setLength(0);
        this.buf.append("mv.visitLineNumber(").append(n).append(", ");
        this.appendLabel(label);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitMaxs(final int n, final int n2) {
        this.buf.setLength(0);
        this.buf.append("mv.visitMaxs(").append(n).append(", ").append(n2).append(");\n");
        this.text.add(this.buf.toString());
    }
    
    private void declareFrameTypes(final int n, final Object[] array) {
        for (int i = 0; i < n; ++i) {
            if (array[i] instanceof Label) {
                this.declareLabel((Label)array[i]);
            }
        }
    }
    
    private void appendFrameTypes(final int n, final Object[] array) {
        for (int i = 0; i < n; ++i) {
            if (i > 0) {
                this.buf.append(", ");
            }
            if (array[i] instanceof String) {
                this.appendConstant(array[i]);
            }
            else if (array[i] instanceof Integer) {
                switch ((int)array[i]) {
                    case 0: {
                        this.buf.append("Opcodes.TOP");
                        break;
                    }
                    case 1: {
                        this.buf.append("Opcodes.INTEGER");
                        break;
                    }
                    case 2: {
                        this.buf.append("Opcodes.FLOAT");
                        break;
                    }
                    case 3: {
                        this.buf.append("Opcodes.DOUBLE");
                        break;
                    }
                    case 4: {
                        this.buf.append("Opcodes.LONG");
                        break;
                    }
                    case 5: {
                        this.buf.append("Opcodes.NULL");
                        break;
                    }
                    case 6: {
                        this.buf.append("Opcodes.UNINITIALIZED_THIS");
                        break;
                    }
                }
            }
            else {
                this.appendLabel((Label)array[i]);
            }
        }
    }
    
    private void declareLabel(final Label label) {
        if (this.labelNames.get(label) == null) {
            final String string = "l" + this.labelNames.size();
            this.labelNames.put(label, string);
            this.buf.append("Label ").append(string).append(" = new Label();\n");
        }
    }
    
    private void appendLabel(final Label label) {
        this.buf.append(this.labelNames.get(label));
    }
}
