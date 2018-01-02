// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.util;

import org.jruby.org.objectweb.asm.signature.SignatureVisitor;
import org.jruby.org.objectweb.asm.signature.SignatureReader;
import org.jruby.org.objectweb.asm.Type;
import org.jruby.org.objectweb.asm.Label;
import org.jruby.org.objectweb.asm.Attribute;
import org.jruby.org.objectweb.asm.AnnotationVisitor;
import java.util.HashMap;
import java.util.Map;
import org.jruby.org.objectweb.asm.MethodVisitor;

public class TraceMethodVisitor extends TraceAbstractVisitor implements MethodVisitor
{
    protected MethodVisitor mv;
    protected String tab2;
    protected String tab3;
    protected String ltab;
    protected final Map labelNames;
    
    public TraceMethodVisitor() {
        this(null);
    }
    
    public TraceMethodVisitor(final MethodVisitor mv) {
        this.tab2 = "    ";
        this.tab3 = "      ";
        this.ltab = "   ";
        this.labelNames = new HashMap();
        this.mv = mv;
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        final AnnotationVisitor visitAnnotation = super.visitAnnotation(s, b);
        if (this.mv != null) {
            ((TraceAnnotationVisitor)visitAnnotation).av = this.mv.visitAnnotation(s, b);
        }
        return visitAnnotation;
    }
    
    public void visitAttribute(final Attribute attribute) {
        this.buf.setLength(0);
        this.buf.append(this.tab).append("ATTRIBUTE ");
        this.appendDescriptor(-1, attribute.type);
        if (attribute instanceof Traceable) {
            ((Traceable)attribute).trace(this.buf, this.labelNames);
        }
        else {
            this.buf.append(" : unknown\n");
        }
        this.text.add(this.buf.toString());
        if (this.mv != null) {
            this.mv.visitAttribute(attribute);
        }
    }
    
    public AnnotationVisitor visitAnnotationDefault() {
        this.text.add(this.tab2 + "default=");
        final TraceAnnotationVisitor traceAnnotationVisitor = this.createTraceAnnotationVisitor();
        this.text.add(traceAnnotationVisitor.getText());
        this.text.add("\n");
        if (this.mv != null) {
            traceAnnotationVisitor.av = this.mv.visitAnnotationDefault();
        }
        return traceAnnotationVisitor;
    }
    
    public AnnotationVisitor visitParameterAnnotation(final int n, final String s, final boolean b) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append('@');
        this.appendDescriptor(1, s);
        this.buf.append('(');
        this.text.add(this.buf.toString());
        final TraceAnnotationVisitor traceAnnotationVisitor = this.createTraceAnnotationVisitor();
        this.text.add(traceAnnotationVisitor.getText());
        this.text.add(b ? ") // parameter " : ") // invisible, parameter ");
        this.text.add(new Integer(n));
        this.text.add("\n");
        if (this.mv != null) {
            traceAnnotationVisitor.av = this.mv.visitParameterAnnotation(n, s, b);
        }
        return traceAnnotationVisitor;
    }
    
    public void visitCode() {
        if (this.mv != null) {
            this.mv.visitCode();
        }
    }
    
    public void visitFrame(final int n, final int n2, final Object[] array, final int n3, final Object[] array2) {
        this.buf.setLength(0);
        this.buf.append(this.ltab);
        this.buf.append("FRAME ");
        switch (n) {
            case -1:
            case 0: {
                this.buf.append("FULL [");
                this.appendFrameTypes(n2, array);
                this.buf.append("] [");
                this.appendFrameTypes(n3, array2);
                this.buf.append(']');
                break;
            }
            case 1: {
                this.buf.append("APPEND [");
                this.appendFrameTypes(n2, array);
                this.buf.append(']');
                break;
            }
            case 2: {
                this.buf.append("CHOP ").append(n2);
                break;
            }
            case 3: {
                this.buf.append("SAME");
                break;
            }
            case 4: {
                this.buf.append("SAME1 ");
                this.appendFrameTypes(1, array2);
                break;
            }
        }
        this.buf.append('\n');
        this.text.add(this.buf.toString());
        if (this.mv != null) {
            this.mv.visitFrame(n, n2, array, n3, array2);
        }
    }
    
    public void visitInsn(final int n) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append(TraceMethodVisitor.OPCODES[n]).append('\n');
        this.text.add(this.buf.toString());
        if (this.mv != null) {
            this.mv.visitInsn(n);
        }
    }
    
    public void visitIntInsn(final int n, final int n2) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append(TraceMethodVisitor.OPCODES[n]).append(' ').append((n == 188) ? TraceMethodVisitor.TYPES[n2] : Integer.toString(n2)).append('\n');
        this.text.add(this.buf.toString());
        if (this.mv != null) {
            this.mv.visitIntInsn(n, n2);
        }
    }
    
    public void visitVarInsn(final int n, final int n2) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append(TraceMethodVisitor.OPCODES[n]).append(' ').append(n2).append('\n');
        this.text.add(this.buf.toString());
        if (this.mv != null) {
            this.mv.visitVarInsn(n, n2);
        }
    }
    
    public void visitTypeInsn(final int n, final String s) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append(TraceMethodVisitor.OPCODES[n]).append(' ');
        this.appendDescriptor(0, s);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
        if (this.mv != null) {
            this.mv.visitTypeInsn(n, s);
        }
    }
    
    public void visitFieldInsn(final int n, final String s, final String s2, final String s3) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append(TraceMethodVisitor.OPCODES[n]).append(' ');
        this.appendDescriptor(0, s);
        this.buf.append('.').append(s2).append(" : ");
        this.appendDescriptor(1, s3);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
        if (this.mv != null) {
            this.mv.visitFieldInsn(n, s, s2, s3);
        }
    }
    
    public void visitMethodInsn(final int n, final String s, final String s2, final String s3) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append(TraceMethodVisitor.OPCODES[n]).append(' ');
        this.appendDescriptor(0, s);
        this.buf.append('.').append(s2).append(' ');
        this.appendDescriptor(3, s3);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
        if (this.mv != null) {
            this.mv.visitMethodInsn(n, s, s2, s3);
        }
    }
    
    public void visitJumpInsn(final int n, final Label label) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append(TraceMethodVisitor.OPCODES[n]).append(' ');
        this.appendLabel(label);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
        if (this.mv != null) {
            this.mv.visitJumpInsn(n, label);
        }
    }
    
    public void visitLabel(final Label label) {
        this.buf.setLength(0);
        this.buf.append(this.ltab);
        this.appendLabel(label);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
        if (this.mv != null) {
            this.mv.visitLabel(label);
        }
    }
    
    public void visitLdcInsn(final Object o) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("LDC ");
        if (o instanceof String) {
            AbstractVisitor.appendString(this.buf, (String)o);
        }
        else if (o instanceof Type) {
            this.buf.append(((Type)o).getDescriptor()).append(".class");
        }
        else {
            this.buf.append(o);
        }
        this.buf.append('\n');
        this.text.add(this.buf.toString());
        if (this.mv != null) {
            this.mv.visitLdcInsn(o);
        }
    }
    
    public void visitIincInsn(final int n, final int n2) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("IINC ").append(n).append(' ').append(n2).append('\n');
        this.text.add(this.buf.toString());
        if (this.mv != null) {
            this.mv.visitIincInsn(n, n2);
        }
    }
    
    public void visitTableSwitchInsn(final int n, final int n2, final Label label, final Label[] array) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("TABLESWITCH\n");
        for (int i = 0; i < array.length; ++i) {
            this.buf.append(this.tab3).append(n + i).append(": ");
            this.appendLabel(array[i]);
            this.buf.append('\n');
        }
        this.buf.append(this.tab3).append("default: ");
        this.appendLabel(label);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
        if (this.mv != null) {
            this.mv.visitTableSwitchInsn(n, n2, label, array);
        }
    }
    
    public void visitLookupSwitchInsn(final Label label, final int[] array, final Label[] array2) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("LOOKUPSWITCH\n");
        for (int i = 0; i < array2.length; ++i) {
            this.buf.append(this.tab3).append(array[i]).append(": ");
            this.appendLabel(array2[i]);
            this.buf.append('\n');
        }
        this.buf.append(this.tab3).append("default: ");
        this.appendLabel(label);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
        if (this.mv != null) {
            this.mv.visitLookupSwitchInsn(label, array, array2);
        }
    }
    
    public void visitMultiANewArrayInsn(final String s, final int n) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("MULTIANEWARRAY ");
        this.appendDescriptor(1, s);
        this.buf.append(' ').append(n).append('\n');
        this.text.add(this.buf.toString());
        if (this.mv != null) {
            this.mv.visitMultiANewArrayInsn(s, n);
        }
    }
    
    public void visitTryCatchBlock(final Label label, final Label label2, final Label label3, final String s) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("TRYCATCHBLOCK ");
        this.appendLabel(label);
        this.buf.append(' ');
        this.appendLabel(label2);
        this.buf.append(' ');
        this.appendLabel(label3);
        this.buf.append(' ');
        this.appendDescriptor(0, s);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
        if (this.mv != null) {
            this.mv.visitTryCatchBlock(label, label2, label3, s);
        }
    }
    
    public void visitLocalVariable(final String s, final String s2, final String s3, final Label label, final Label label2, final int n) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("LOCALVARIABLE ").append(s).append(' ');
        this.appendDescriptor(1, s2);
        this.buf.append(' ');
        this.appendLabel(label);
        this.buf.append(' ');
        this.appendLabel(label2);
        this.buf.append(' ').append(n).append('\n');
        if (s3 != null) {
            this.buf.append(this.tab2);
            this.appendDescriptor(2, s3);
            final TraceSignatureVisitor traceSignatureVisitor = new TraceSignatureVisitor(0);
            new SignatureReader(s3).acceptType(traceSignatureVisitor);
            this.buf.append(this.tab2).append("// declaration: ").append(traceSignatureVisitor.getDeclaration()).append('\n');
        }
        this.text.add(this.buf.toString());
        if (this.mv != null) {
            this.mv.visitLocalVariable(s, s2, s3, label, label2, n);
        }
    }
    
    public void visitLineNumber(final int n, final Label label) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("LINENUMBER ").append(n).append(' ');
        this.appendLabel(label);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
        if (this.mv != null) {
            this.mv.visitLineNumber(n, label);
        }
    }
    
    public void visitMaxs(final int n, final int n2) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("MAXSTACK = ").append(n).append('\n');
        this.text.add(this.buf.toString());
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("MAXLOCALS = ").append(n2).append('\n');
        this.text.add(this.buf.toString());
        if (this.mv != null) {
            this.mv.visitMaxs(n, n2);
        }
    }
    
    public void visitEnd() {
        super.visitEnd();
        if (this.mv != null) {
            this.mv.visitEnd();
        }
    }
    
    private void appendFrameTypes(final int n, final Object[] array) {
        for (int i = 0; i < n; ++i) {
            if (i > 0) {
                this.buf.append(' ');
            }
            if (array[i] instanceof String) {
                final String s = (String)array[i];
                if (s.startsWith("[")) {
                    this.appendDescriptor(1, s);
                }
                else {
                    this.appendDescriptor(0, s);
                }
            }
            else if (array[i] instanceof Integer) {
                switch ((int)array[i]) {
                    case 0: {
                        this.appendDescriptor(1, "T");
                        break;
                    }
                    case 1: {
                        this.appendDescriptor(1, "I");
                        break;
                    }
                    case 2: {
                        this.appendDescriptor(1, "F");
                        break;
                    }
                    case 3: {
                        this.appendDescriptor(1, "D");
                        break;
                    }
                    case 4: {
                        this.appendDescriptor(1, "J");
                        break;
                    }
                    case 5: {
                        this.appendDescriptor(1, "N");
                        break;
                    }
                    case 6: {
                        this.appendDescriptor(1, "U");
                        break;
                    }
                }
            }
            else {
                this.appendLabel((Label)array[i]);
            }
        }
    }
    
    protected void appendLabel(final Label label) {
        String string = this.labelNames.get(label);
        if (string == null) {
            string = "L" + this.labelNames.size();
            this.labelNames.put(label, string);
        }
        this.buf.append(string);
    }
}
