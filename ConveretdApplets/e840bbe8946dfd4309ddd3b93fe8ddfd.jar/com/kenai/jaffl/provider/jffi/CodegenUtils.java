// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import java.util.Iterator;
import org.jruby.org.objectweb.asm.Type;
import java.util.Map;
import org.jruby.org.objectweb.asm.AnnotationVisitor;
import java.util.Arrays;

public class CodegenUtils
{
    public static String c(final String p) {
        return p.replace('/', '.');
    }
    
    public static String p(final Class n) {
        return n.getName().replace('.', '/');
    }
    
    public static String ci(Class n) {
        if (n.isArray()) {
            n = n.getComponentType();
            if (!n.isPrimitive()) {
                return "[" + ci(n);
            }
            if (n == Byte.TYPE) {
                return "[B";
            }
            if (n == Boolean.TYPE) {
                return "[Z";
            }
            if (n == Short.TYPE) {
                return "[S";
            }
            if (n == Character.TYPE) {
                return "[C";
            }
            if (n == Integer.TYPE) {
                return "[I";
            }
            if (n == Float.TYPE) {
                return "[F";
            }
            if (n == Double.TYPE) {
                return "[D";
            }
            if (n == Long.TYPE) {
                return "[J";
            }
            throw new RuntimeException("Unrecognized type in compiler: " + n.getName());
        }
        else {
            if (!n.isPrimitive()) {
                return "L" + p(n) + ";";
            }
            if (n == Byte.TYPE) {
                return "B";
            }
            if (n == Boolean.TYPE) {
                return "Z";
            }
            if (n == Short.TYPE) {
                return "S";
            }
            if (n == Character.TYPE) {
                return "C";
            }
            if (n == Integer.TYPE) {
                return "I";
            }
            if (n == Float.TYPE) {
                return "F";
            }
            if (n == Double.TYPE) {
                return "D";
            }
            if (n == Long.TYPE) {
                return "J";
            }
            if (n == Void.TYPE) {
                return "V";
            }
            throw new RuntimeException("Unrecognized type in compiler: " + n.getName());
        }
    }
    
    public static String human(final Class n) {
        return n.getCanonicalName();
    }
    
    public static String sig(final Class retval, final Class... params) {
        return sigParams(params) + ci(retval);
    }
    
    public static String sig(final Class retval, final String descriptor, final Class... params) {
        return sigParams(descriptor, params) + ci(retval);
    }
    
    public static String sigParams(final Class... params) {
        final StringBuilder signature = new StringBuilder("(");
        for (int i = 0; i < params.length; ++i) {
            signature.append(ci(params[i]));
        }
        signature.append(")");
        return signature.toString();
    }
    
    public static String sigParams(final String descriptor, final Class... params) {
        final StringBuilder signature = new StringBuilder("(");
        signature.append(descriptor);
        for (int i = 0; i < params.length; ++i) {
            signature.append(ci(params[i]));
        }
        signature.append(")");
        return signature.toString();
    }
    
    public static String pretty(final Class retval, final Class... params) {
        return prettyParams(params) + human(retval);
    }
    
    public static String prettyParams(final Class... params) {
        final StringBuilder signature = new StringBuilder("(");
        for (int i = 0; i < params.length; ++i) {
            signature.append(human(params[i]));
            if (i < params.length - 1) {
                signature.append(',');
            }
        }
        signature.append(")");
        return signature.toString();
    }
    
    public static Class[] params(final Class... classes) {
        return classes;
    }
    
    public static Class[] params(final Class cls, final int times) {
        final Class[] classes = new Class[times];
        Arrays.fill(classes, cls);
        return classes;
    }
    
    public static Class[] params(final Class cls1, final Class clsFill, final int times) {
        final Class[] classes = new Class[times + 1];
        Arrays.fill(classes, clsFill);
        classes[0] = cls1;
        return classes;
    }
    
    public static String getAnnotatedBindingClassName(final String javaMethodName, final String typeName, final boolean isStatic, final int required, final int optional, final boolean multi, final boolean framed) {
        final String marker = framed ? "$RUBYFRAMEDINVOKER$" : "$RUBYINVOKER$";
        String commonClassSuffix;
        if (multi) {
            commonClassSuffix = (isStatic ? "$s" : "$i") + "_method_multi" + marker + javaMethodName;
        }
        else {
            commonClassSuffix = (isStatic ? "$s" : "$i") + "_method_" + required + "_" + optional + marker + javaMethodName;
        }
        return typeName + commonClassSuffix;
    }
    
    public static void visitAnnotationFields(final AnnotationVisitor visitor, final Map<String, Object> fields) {
        for (final Map.Entry<String, Object> fieldEntry : fields.entrySet()) {
            final Object value = fieldEntry.getValue();
            if (value.getClass().isArray()) {
                final Object[] values = (Object[])value;
                final AnnotationVisitor arrayV = visitor.visitArray(fieldEntry.getKey());
                for (int i = 0; i < values.length; ++i) {
                    arrayV.visit(null, values[i]);
                }
                arrayV.visitEnd();
            }
            else if (value.getClass().isEnum()) {
                visitor.visitEnum(fieldEntry.getKey(), ci(value.getClass()), value.toString());
            }
            else if (value instanceof Class) {
                visitor.visit(fieldEntry.getKey(), Type.getType((Class)value));
            }
            else {
                visitor.visit(fieldEntry.getKey(), value);
            }
        }
    }
}
