// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.util;

import org.jruby.org.objectweb.asm.Type;
import org.jruby.org.objectweb.asm.Attribute;
import org.jruby.org.objectweb.asm.AnnotationVisitor;
import java.util.Map;

public class ASMifierAbstractVisitor extends AbstractVisitor
{
    protected String name;
    Map labelNames;
    
    protected ASMifierAbstractVisitor(final String name) {
        this.name = name;
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        this.buf.setLength(0);
        this.buf.append("{\n").append("av0 = ").append(this.name).append(".visitAnnotation(");
        this.appendConstant(s);
        this.buf.append(", ").append(b).append(");\n");
        this.text.add(this.buf.toString());
        final ASMifierAnnotationVisitor asMifierAnnotationVisitor = new ASMifierAnnotationVisitor(0);
        this.text.add(asMifierAnnotationVisitor.getText());
        this.text.add("}\n");
        return asMifierAnnotationVisitor;
    }
    
    public void visitAttribute(final Attribute attribute) {
        this.buf.setLength(0);
        this.buf.append("// ATTRIBUTE ").append(attribute.type).append('\n');
        if (attribute instanceof ASMifiable) {
            this.buf.append("{\n");
            ((ASMifiable)attribute).asmify(this.buf, "attr", this.labelNames);
            this.buf.append(this.name).append(".visitAttribute(attr);\n");
            this.buf.append("}\n");
        }
        this.text.add(this.buf.toString());
    }
    
    public void visitEnd() {
        this.buf.setLength(0);
        this.buf.append(this.name).append(".visitEnd();\n");
        this.text.add(this.buf.toString());
    }
    
    void appendConstant(final Object o) {
        appendConstant(this.buf, o);
    }
    
    static void appendConstant(final StringBuffer sb, final Object o) {
        if (o == null) {
            sb.append("null");
        }
        else if (o instanceof String) {
            AbstractVisitor.appendString(sb, (String)o);
        }
        else if (o instanceof Type) {
            sb.append("Type.getType(\"");
            sb.append(((Type)o).getDescriptor());
            sb.append("\")");
        }
        else if (o instanceof Byte) {
            sb.append("new Byte((byte)").append(o).append(')');
        }
        else if (o instanceof Boolean) {
            sb.append(o ? "Boolean.TRUE" : "Boolean.FALSE");
        }
        else if (o instanceof Short) {
            sb.append("new Short((short)").append(o).append(')');
        }
        else if (o instanceof Character) {
            sb.append("new Character((char)").append((int)(char)o).append(')');
        }
        else if (o instanceof Integer) {
            sb.append("new Integer(").append(o).append(')');
        }
        else if (o instanceof Float) {
            sb.append("new Float(\"").append(o).append("\")");
        }
        else if (o instanceof Long) {
            sb.append("new Long(").append(o).append("L)");
        }
        else if (o instanceof Double) {
            sb.append("new Double(\"").append(o).append("\")");
        }
        else if (o instanceof byte[]) {
            final byte[] array = (byte[])o;
            sb.append("new byte[] {");
            for (int i = 0; i < array.length; ++i) {
                sb.append((i == 0) ? "" : ",").append(array[i]);
            }
            sb.append('}');
        }
        else if (o instanceof boolean[]) {
            final boolean[] array2 = (boolean[])o;
            sb.append("new boolean[] {");
            for (int j = 0; j < array2.length; ++j) {
                sb.append((j == 0) ? "" : ",").append(array2[j]);
            }
            sb.append('}');
        }
        else if (o instanceof short[]) {
            final short[] array3 = (short[])o;
            sb.append("new short[] {");
            for (int k = 0; k < array3.length; ++k) {
                sb.append((k == 0) ? "" : ",").append("(short)").append(array3[k]);
            }
            sb.append('}');
        }
        else if (o instanceof char[]) {
            final char[] array4 = (char[])o;
            sb.append("new char[] {");
            for (int l = 0; l < array4.length; ++l) {
                sb.append((l == 0) ? "" : ",").append("(char)").append((int)array4[l]);
            }
            sb.append('}');
        }
        else if (o instanceof int[]) {
            final int[] array5 = (int[])o;
            sb.append("new int[] {");
            for (int n = 0; n < array5.length; ++n) {
                sb.append((n == 0) ? "" : ",").append(array5[n]);
            }
            sb.append('}');
        }
        else if (o instanceof long[]) {
            final long[] array6 = (long[])o;
            sb.append("new long[] {");
            for (int n2 = 0; n2 < array6.length; ++n2) {
                sb.append((n2 == 0) ? "" : ",").append(array6[n2]).append('L');
            }
            sb.append('}');
        }
        else if (o instanceof float[]) {
            final float[] array7 = (float[])o;
            sb.append("new float[] {");
            for (int n3 = 0; n3 < array7.length; ++n3) {
                sb.append((n3 == 0) ? "" : ",").append(array7[n3]).append('f');
            }
            sb.append('}');
        }
        else if (o instanceof double[]) {
            final double[] array8 = (double[])o;
            sb.append("new double[] {");
            for (int n4 = 0; n4 < array8.length; ++n4) {
                sb.append((n4 == 0) ? "" : ",").append(array8[n4]).append('d');
            }
            sb.append('}');
        }
    }
}
