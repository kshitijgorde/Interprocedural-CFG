// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.openmbean;

import javax.management.ObjectName;
import java.util.Date;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.io.ObjectStreamException;
import java.io.InvalidClassException;
import java.io.Serializable;

public final class SimpleType extends OpenType implements Serializable
{
    private transient int cachedHashCode;
    private transient String cachedToString;
    private static final long serialVersionUID = 2215577471957694503L;
    public static final SimpleType BIGDECIMAL;
    public static final SimpleType BIGINTEGER;
    public static final SimpleType BOOLEAN;
    public static final SimpleType BYTE;
    public static final SimpleType CHARACTER;
    public static final SimpleType DATE;
    public static final SimpleType DOUBLE;
    public static final SimpleType FLOAT;
    public static final SimpleType INTEGER;
    public static final SimpleType LONG;
    public static final SimpleType OBJECTNAME;
    public static final SimpleType SHORT;
    public static final SimpleType STRING;
    public static final SimpleType VOID;
    
    private SimpleType(final String className) throws OpenDataException {
        super(className, className, className);
        this.cachedHashCode = 0;
        this.cachedToString = null;
    }
    
    public boolean isValue(final Object obj) {
        return obj != null && obj.getClass().getName().equals(this.getClassName());
    }
    
    public Object readResolve() throws ObjectStreamException {
        final String className = this.getClassName();
        if (className.equals(SimpleType.STRING.getClassName())) {
            return SimpleType.STRING;
        }
        if (className.equals(SimpleType.INTEGER.getClassName())) {
            return SimpleType.INTEGER;
        }
        if (className.equals(SimpleType.BOOLEAN.getClassName())) {
            return SimpleType.BOOLEAN;
        }
        if (className.equals(SimpleType.OBJECTNAME.getClassName())) {
            return SimpleType.OBJECTNAME;
        }
        if (className.equals(SimpleType.LONG.getClassName())) {
            return SimpleType.LONG;
        }
        if (className.equals(SimpleType.BYTE.getClassName())) {
            return SimpleType.BYTE;
        }
        if (className.equals(SimpleType.CHARACTER.getClassName())) {
            return SimpleType.CHARACTER;
        }
        if (className.equals(SimpleType.DOUBLE.getClassName())) {
            return SimpleType.DOUBLE;
        }
        if (className.equals(SimpleType.FLOAT.getClassName())) {
            return SimpleType.FLOAT;
        }
        if (className.equals(SimpleType.SHORT.getClassName())) {
            return SimpleType.SHORT;
        }
        if (className.equals(SimpleType.BIGDECIMAL.getClassName())) {
            return SimpleType.BIGDECIMAL;
        }
        if (className.equals(SimpleType.BIGINTEGER.getClassName())) {
            return SimpleType.BIGINTEGER;
        }
        if (className.equals(SimpleType.VOID.getClassName())) {
            return SimpleType.VOID;
        }
        if (className.equals(SimpleType.DATE.getClassName())) {
            return SimpleType.DATE;
        }
        throw new InvalidClassException(className);
    }
    
    public boolean equals(final Object obj) {
        return this == obj || (obj != null && obj instanceof SimpleType && this.getClassName().equals(((SimpleType)obj).getClassName()));
    }
    
    public int hashCode() {
        if (this.cachedHashCode != 0) {
            return this.cachedHashCode;
        }
        return this.cachedHashCode = this.getClassName().hashCode();
    }
    
    public String toString() {
        if (this.cachedToString != null) {
            return this.cachedToString;
        }
        final StringBuffer buffer = new StringBuffer(SimpleType.class.getName());
        buffer.append(":");
        buffer.append(this.getClassName());
        return this.cachedToString = buffer.toString();
    }
    
    static {
        try {
            BIGDECIMAL = new SimpleType(BigDecimal.class.getName());
            BIGINTEGER = new SimpleType(BigInteger.class.getName());
            BOOLEAN = new SimpleType(Boolean.class.getName());
            BYTE = new SimpleType(Byte.class.getName());
            CHARACTER = new SimpleType(Character.class.getName());
            DATE = new SimpleType(Date.class.getName());
            DOUBLE = new SimpleType(Double.class.getName());
            FLOAT = new SimpleType(Float.class.getName());
            INTEGER = new SimpleType(Integer.class.getName());
            LONG = new SimpleType(Long.class.getName());
            OBJECTNAME = new SimpleType(ObjectName.class.getName());
            SHORT = new SimpleType(Short.class.getName());
            STRING = new SimpleType(String.class.getName());
            VOID = new SimpleType(Void.class.getName());
        }
        catch (OpenDataException e) {
            throw new RuntimeException(e.toString());
        }
    }
}
