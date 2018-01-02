// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.util;

import java.util.Map;
import org.jruby.org.objectweb.asm.Attribute;
import org.jruby.org.objectweb.asm.AnnotationVisitor;

public abstract class TraceAbstractVisitor extends AbstractVisitor
{
    public static final int INTERNAL_NAME = 0;
    public static final int FIELD_DESCRIPTOR = 1;
    public static final int FIELD_SIGNATURE = 2;
    public static final int METHOD_DESCRIPTOR = 3;
    public static final int METHOD_SIGNATURE = 4;
    public static final int CLASS_SIGNATURE = 5;
    public static final int TYPE_DECLARATION = 6;
    public static final int CLASS_DECLARATION = 7;
    public static final int PARAMETERS_DECLARATION = 8;
    protected String tab;
    
    public TraceAbstractVisitor() {
        this.tab = "  ";
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        this.buf.setLength(0);
        this.buf.append(this.tab).append('@');
        this.appendDescriptor(1, s);
        this.buf.append('(');
        this.text.add(this.buf.toString());
        final TraceAnnotationVisitor traceAnnotationVisitor = this.createTraceAnnotationVisitor();
        this.text.add(traceAnnotationVisitor.getText());
        this.text.add(b ? ")\n" : ") // invisible\n");
        return traceAnnotationVisitor;
    }
    
    public void visitAttribute(final Attribute attribute) {
        this.buf.setLength(0);
        this.buf.append(this.tab).append("ATTRIBUTE ");
        this.appendDescriptor(-1, attribute.type);
        if (attribute instanceof Traceable) {
            ((Traceable)attribute).trace(this.buf, null);
        }
        else {
            this.buf.append(" : unknown\n");
        }
        this.text.add(this.buf.toString());
    }
    
    public void visitEnd() {
    }
    
    protected TraceAnnotationVisitor createTraceAnnotationVisitor() {
        return new TraceAnnotationVisitor();
    }
    
    protected void appendDescriptor(final int n, final String s) {
        if (n == 5 || n == 2 || n == 4) {
            if (s != null) {
                this.buf.append("// signature ").append(s).append('\n');
            }
        }
        else {
            this.buf.append(s);
        }
    }
}
