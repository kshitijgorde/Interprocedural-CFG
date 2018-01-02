// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.util;

import org.jruby.org.objectweb.asm.Attribute;
import org.jruby.org.objectweb.asm.AnnotationVisitor;
import org.jruby.org.objectweb.asm.FieldVisitor;

public class TraceFieldVisitor extends TraceAbstractVisitor implements FieldVisitor
{
    protected FieldVisitor fv;
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        final AnnotationVisitor visitAnnotation = super.visitAnnotation(s, b);
        if (this.fv != null) {
            ((TraceAnnotationVisitor)visitAnnotation).av = this.fv.visitAnnotation(s, b);
        }
        return visitAnnotation;
    }
    
    public void visitAttribute(final Attribute attribute) {
        super.visitAttribute(attribute);
        if (this.fv != null) {
            this.fv.visitAttribute(attribute);
        }
    }
    
    public void visitEnd() {
        super.visitEnd();
        if (this.fv != null) {
            this.fv.visitEnd();
        }
    }
}
