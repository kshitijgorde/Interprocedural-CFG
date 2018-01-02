// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.io.PrintWriter;
import java.io.PrintStream;
import org.jboss.logging.Logger;
import org.jboss.util.platform.Java;
import java.io.Serializable;

public interface NestedThrowable extends Serializable
{
    public static final boolean PARENT_TRACE_ENABLED = Util.getBoolean("parentTraceEnabled", true);
    public static final boolean NESTED_TRACE_ENABLED = Util.getBoolean("nestedTraceEnabled", (Java.isCompatible(5) && !NestedThrowable.PARENT_TRACE_ENABLED) || !Java.isCompatible(5));
    public static final boolean DETECT_DUPLICATE_NESTING = Util.getBoolean("detectDuplicateNesting", true);
    
    Throwable getNested();
    
    Throwable getCause();
    
    public static final class Util
    {
        private static Logger log;
        
        private static Logger getLogger() {
            if (Util.log == null) {
                Util.log = Logger.getLogger((NestedThrowable$1.class$org$jboss$util$NestedThrowable == null) ? (NestedThrowable$1.class$org$jboss$util$NestedThrowable = NestedThrowable$1.class$("org.jboss.util.NestedThrowable")) : NestedThrowable$1.class$org$jboss$util$NestedThrowable);
            }
            return Util.log;
        }
        
        protected static boolean getBoolean(String name, final boolean defaultValue) {
            name = ((NestedThrowable$1.class$org$jboss$util$NestedThrowable == null) ? (NestedThrowable$1.class$org$jboss$util$NestedThrowable = NestedThrowable$1.class$("org.jboss.util.NestedThrowable")) : NestedThrowable$1.class$org$jboss$util$NestedThrowable).getName() + "." + name;
            final String value = System.getProperty(name, String.valueOf(defaultValue));
            (Util.log = getLogger()).debug(name + "=" + value);
            return new Boolean(value);
        }
        
        public static void checkNested(final NestedThrowable parent, final Throwable child) {
            if (!NestedThrowable.DETECT_DUPLICATE_NESTING || parent == null || child == null) {
                return;
            }
            final Class parentType = parent.getClass();
            final Class childType = child.getClass();
            if (parentType.isAssignableFrom(childType)) {
                (Util.log = getLogger()).warn("Duplicate throwable nesting of same base type: " + parentType + " is assignable from: " + childType);
            }
        }
        
        public static String getMessage(final String msg, final Throwable nested) {
            final StringBuffer buff = new StringBuffer((msg == null) ? "" : msg);
            if (nested != null) {
                buff.append((msg == null) ? "- " : "; - ").append("nested throwable: (").append(nested).append(")");
            }
            return buff.toString();
        }
        
        public static void print(final Throwable nested, final PrintStream stream) {
            if (stream == null) {
                throw new NullArgumentException("stream");
            }
            if (NestedThrowable.NESTED_TRACE_ENABLED && nested != null) {
                synchronized (stream) {
                    if (NestedThrowable.PARENT_TRACE_ENABLED) {
                        stream.print(" + nested throwable: ");
                    }
                    else {
                        stream.print("[ parent trace omitted ]: ");
                    }
                    nested.printStackTrace(stream);
                }
            }
        }
        
        public static void print(final Throwable nested, final PrintWriter writer) {
            if (writer == null) {
                throw new NullArgumentException("writer");
            }
            if (NestedThrowable.NESTED_TRACE_ENABLED && nested != null) {
                synchronized (writer) {
                    if (NestedThrowable.PARENT_TRACE_ENABLED) {
                        writer.print(" + nested throwable: ");
                    }
                    else {
                        writer.print("[ parent trace omitted ]: ");
                    }
                    nested.printStackTrace(writer);
                }
            }
        }
        
        static {
            Util.log = Logger.getLogger((NestedThrowable$1.class$org$jboss$util$NestedThrowable == null) ? (NestedThrowable$1.class$org$jboss$util$NestedThrowable = NestedThrowable$1.class$("org.jboss.util.NestedThrowable")) : NestedThrowable$1.class$org$jboss$util$NestedThrowable);
        }
    }
}
