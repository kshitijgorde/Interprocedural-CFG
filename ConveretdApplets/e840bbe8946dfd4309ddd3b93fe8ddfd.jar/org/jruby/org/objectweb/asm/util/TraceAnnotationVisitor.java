// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.util;

import org.jruby.org.objectweb.asm.Type;
import org.jruby.org.objectweb.asm.AnnotationVisitor;

public class TraceAnnotationVisitor extends TraceAbstractVisitor implements AnnotationVisitor
{
    protected AnnotationVisitor av;
    private int valueNumber;
    
    public TraceAnnotationVisitor() {
        this.valueNumber = 0;
    }
    
    public void visit(final String s, final Object o) {
        this.buf.setLength(0);
        this.appendComa(this.valueNumber++);
        if (s != null) {
            this.buf.append(s).append('=');
        }
        if (o instanceof String) {
            this.visitString((String)o);
        }
        else if (o instanceof Type) {
            this.visitType((Type)o);
        }
        else if (o instanceof Byte) {
            this.visitByte((byte)o);
        }
        else if (o instanceof Boolean) {
            this.visitBoolean((boolean)o);
        }
        else if (o instanceof Short) {
            this.visitShort((short)o);
        }
        else if (o instanceof Character) {
            this.visitChar((char)o);
        }
        else if (o instanceof Integer) {
            this.visitInt((int)o);
        }
        else if (o instanceof Float) {
            this.visitFloat((float)o);
        }
        else if (o instanceof Long) {
            this.visitLong((long)o);
        }
        else if (o instanceof Double) {
            this.visitDouble((double)o);
        }
        else if (o.getClass().isArray()) {
            this.buf.append('{');
            if (o instanceof byte[]) {
                final byte[] array = (byte[])o;
                for (int i = 0; i < array.length; ++i) {
                    this.appendComa(i);
                    this.visitByte(array[i]);
                }
            }
            else if (o instanceof boolean[]) {
                final boolean[] array2 = (boolean[])o;
                for (int j = 0; j < array2.length; ++j) {
                    this.appendComa(j);
                    this.visitBoolean(array2[j]);
                }
            }
            else if (o instanceof short[]) {
                final short[] array3 = (short[])o;
                for (int k = 0; k < array3.length; ++k) {
                    this.appendComa(k);
                    this.visitShort(array3[k]);
                }
            }
            else if (o instanceof char[]) {
                final char[] array4 = (char[])o;
                for (int l = 0; l < array4.length; ++l) {
                    this.appendComa(l);
                    this.visitChar(array4[l]);
                }
            }
            else if (o instanceof int[]) {
                final int[] array5 = (int[])o;
                for (int n = 0; n < array5.length; ++n) {
                    this.appendComa(n);
                    this.visitInt(array5[n]);
                }
            }
            else if (o instanceof long[]) {
                final long[] array6 = (long[])o;
                for (int n2 = 0; n2 < array6.length; ++n2) {
                    this.appendComa(n2);
                    this.visitLong(array6[n2]);
                }
            }
            else if (o instanceof float[]) {
                final float[] array7 = (float[])o;
                for (int n3 = 0; n3 < array7.length; ++n3) {
                    this.appendComa(n3);
                    this.visitFloat(array7[n3]);
                }
            }
            else if (o instanceof double[]) {
                final double[] array8 = (double[])o;
                for (int n4 = 0; n4 < array8.length; ++n4) {
                    this.appendComa(n4);
                    this.visitDouble(array8[n4]);
                }
            }
            this.buf.append('}');
        }
        this.text.add(this.buf.toString());
        if (this.av != null) {
            this.av.visit(s, o);
        }
    }
    
    private void visitInt(final int n) {
        this.buf.append(n);
    }
    
    private void visitLong(final long n) {
        this.buf.append(n).append('L');
    }
    
    private void visitFloat(final float n) {
        this.buf.append(n).append('F');
    }
    
    private void visitDouble(final double n) {
        this.buf.append(n).append('D');
    }
    
    private void visitChar(final char c) {
        this.buf.append("(char)").append((int)c);
    }
    
    private void visitShort(final short n) {
        this.buf.append("(short)").append(n);
    }
    
    private void visitByte(final byte b) {
        this.buf.append("(byte)").append(b);
    }
    
    private void visitBoolean(final boolean b) {
        this.buf.append(b);
    }
    
    private void visitString(final String s) {
        AbstractVisitor.appendString(this.buf, s);
    }
    
    private void visitType(final Type type) {
        this.buf.append(type.getClassName()).append(".class");
    }
    
    public void visitEnum(final String s, final String s2, final String s3) {
        this.buf.setLength(0);
        this.appendComa(this.valueNumber++);
        if (s != null) {
            this.buf.append(s).append('=');
        }
        this.appendDescriptor(1, s2);
        this.buf.append('.').append(s3);
        this.text.add(this.buf.toString());
        if (this.av != null) {
            this.av.visitEnum(s, s2, s3);
        }
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final String s2) {
        this.buf.setLength(0);
        this.appendComa(this.valueNumber++);
        if (s != null) {
            this.buf.append(s).append('=');
        }
        this.buf.append('@');
        this.appendDescriptor(1, s2);
        this.buf.append('(');
        this.text.add(this.buf.toString());
        final TraceAnnotationVisitor traceAnnotationVisitor = this.createTraceAnnotationVisitor();
        this.text.add(traceAnnotationVisitor.getText());
        this.text.add(")");
        if (this.av != null) {
            traceAnnotationVisitor.av = this.av.visitAnnotation(s, s2);
        }
        return traceAnnotationVisitor;
    }
    
    public AnnotationVisitor visitArray(final String s) {
        this.buf.setLength(0);
        this.appendComa(this.valueNumber++);
        if (s != null) {
            this.buf.append(s).append('=');
        }
        this.buf.append('{');
        this.text.add(this.buf.toString());
        final TraceAnnotationVisitor traceAnnotationVisitor = this.createTraceAnnotationVisitor();
        this.text.add(traceAnnotationVisitor.getText());
        this.text.add("}");
        if (this.av != null) {
            traceAnnotationVisitor.av = this.av.visitArray(s);
        }
        return traceAnnotationVisitor;
    }
    
    public void visitEnd() {
        if (this.av != null) {
            this.av.visitEnd();
        }
    }
    
    private void appendComa(final int n) {
        if (n != 0) {
            this.buf.append(", ");
        }
    }
}
