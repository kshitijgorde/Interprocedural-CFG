// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.util;

import org.jruby.org.objectweb.asm.Attribute;
import org.jruby.org.objectweb.asm.AnnotationVisitor;
import org.jruby.org.objectweb.asm.FieldVisitor;

public class CheckFieldAdapter implements FieldVisitor
{
    private final FieldVisitor fv;
    private boolean end;
    
    public CheckFieldAdapter(final FieldVisitor fv) {
        this.fv = fv;
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        this.checkEnd();
        CheckMethodAdapter.checkDesc(s, false);
        return new CheckAnnotationAdapter(this.fv.visitAnnotation(s, b));
    }
    
    public void visitAttribute(final Attribute attribute) {
        this.checkEnd();
        if (attribute == null) {
            throw new IllegalArgumentException("Invalid attribute (must not be null)");
        }
        this.fv.visitAttribute(attribute);
    }
    
    public void visitEnd() {
        this.checkEnd();
        this.end = true;
        this.fv.visitEnd();
    }
    
    private void checkEnd() {
        if (this.end) {
            throw new IllegalStateException("Cannot call a visit method after visitEnd has been called");
        }
    }
}
