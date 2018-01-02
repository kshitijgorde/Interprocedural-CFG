// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.commons;

import org.jruby.org.objectweb.asm.Attribute;
import org.jruby.org.objectweb.asm.AnnotationVisitor;
import org.jruby.org.objectweb.asm.FieldVisitor;

public class RemappingFieldAdapter implements FieldVisitor
{
    private final FieldVisitor fv;
    private final Remapper remapper;
    
    public RemappingFieldAdapter(final FieldVisitor fv, final Remapper remapper) {
        this.fv = fv;
        this.remapper = remapper;
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        final AnnotationVisitor visitAnnotation = this.fv.visitAnnotation(this.remapper.mapDesc(s), b);
        return (visitAnnotation == null) ? null : new RemappingAnnotationAdapter(visitAnnotation, this.remapper);
    }
    
    public void visitAttribute(final Attribute attribute) {
        this.fv.visitAttribute(attribute);
    }
    
    public void visitEnd() {
        this.fv.visitEnd();
    }
}
