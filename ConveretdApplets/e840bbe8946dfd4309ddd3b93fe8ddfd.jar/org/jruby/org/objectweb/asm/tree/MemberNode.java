// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.tree;

import org.jruby.org.objectweb.asm.Attribute;
import java.util.ArrayList;
import org.jruby.org.objectweb.asm.AnnotationVisitor;
import java.util.List;

public abstract class MemberNode
{
    public List visibleAnnotations;
    public List invisibleAnnotations;
    public List attrs;
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        final AnnotationNode annotationNode = new AnnotationNode(s);
        if (b) {
            if (this.visibleAnnotations == null) {
                this.visibleAnnotations = new ArrayList(1);
            }
            this.visibleAnnotations.add(annotationNode);
        }
        else {
            if (this.invisibleAnnotations == null) {
                this.invisibleAnnotations = new ArrayList(1);
            }
            this.invisibleAnnotations.add(annotationNode);
        }
        return annotationNode;
    }
    
    public void visitAttribute(final Attribute attribute) {
        if (this.attrs == null) {
            this.attrs = new ArrayList(1);
        }
        this.attrs.add(attribute);
    }
    
    public void visitEnd() {
    }
}
