// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.util;

import org.jruby.org.objectweb.asm.AnnotationVisitor;

public class ASMifierAnnotationVisitor extends AbstractVisitor implements AnnotationVisitor
{
    protected final int id;
    
    public ASMifierAnnotationVisitor(final int id) {
        this.id = id;
    }
    
    public void visit(final String s, final Object o) {
        this.buf.setLength(0);
        this.buf.append("av").append(this.id).append(".visit(");
        ASMifierAbstractVisitor.appendConstant(this.buf, s);
        this.buf.append(", ");
        ASMifierAbstractVisitor.appendConstant(this.buf, o);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitEnum(final String s, final String s2, final String s3) {
        this.buf.setLength(0);
        this.buf.append("av").append(this.id).append(".visitEnum(");
        ASMifierAbstractVisitor.appendConstant(this.buf, s);
        this.buf.append(", ");
        ASMifierAbstractVisitor.appendConstant(this.buf, s2);
        this.buf.append(", ");
        ASMifierAbstractVisitor.appendConstant(this.buf, s3);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final String s2) {
        this.buf.setLength(0);
        this.buf.append("{\n");
        this.buf.append("AnnotationVisitor av").append(this.id + 1).append(" = av");
        this.buf.append(this.id).append(".visitAnnotation(");
        ASMifierAbstractVisitor.appendConstant(this.buf, s);
        this.buf.append(", ");
        ASMifierAbstractVisitor.appendConstant(this.buf, s2);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
        final ASMifierAnnotationVisitor asMifierAnnotationVisitor = new ASMifierAnnotationVisitor(this.id + 1);
        this.text.add(asMifierAnnotationVisitor.getText());
        this.text.add("}\n");
        return asMifierAnnotationVisitor;
    }
    
    public AnnotationVisitor visitArray(final String s) {
        this.buf.setLength(0);
        this.buf.append("{\n");
        this.buf.append("AnnotationVisitor av").append(this.id + 1).append(" = av");
        this.buf.append(this.id).append(".visitArray(");
        ASMifierAbstractVisitor.appendConstant(this.buf, s);
        this.buf.append(");\n");
        this.text.add(this.buf.toString());
        final ASMifierAnnotationVisitor asMifierAnnotationVisitor = new ASMifierAnnotationVisitor(this.id + 1);
        this.text.add(asMifierAnnotationVisitor.getText());
        this.text.add("}\n");
        return asMifierAnnotationVisitor;
    }
    
    public void visitEnd() {
        this.buf.setLength(0);
        this.buf.append("av").append(this.id).append(".visitEnd();\n");
        this.text.add(this.buf.toString());
    }
}
