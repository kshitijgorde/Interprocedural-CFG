// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.openmbean;

import java.io.Serializable;

public class ArrayType extends OpenType implements Serializable
{
    private int dimension;
    private OpenType elementType;
    private transient int cachedHashCode;
    private transient String cachedToString;
    private static final long serialVersionUID = 720504429830309770L;
    
    public ArrayType(final int dimension, final OpenType elementType) throws OpenDataException {
        super(genName(dimension, elementType), genName(dimension, elementType), genDesc(dimension, elementType));
        this.dimension = 0;
        this.elementType = null;
        this.cachedHashCode = 0;
        this.cachedToString = null;
        this.dimension = dimension;
        this.elementType = elementType;
    }
    
    public int getDimension() {
        return this.dimension;
    }
    
    public OpenType getElementOpenType() {
        return this.elementType;
    }
    
    public boolean isValue(final Object obj) {
        if (obj == null) {
            return false;
        }
        final Class clazz = obj.getClass();
        if (!clazz.isArray()) {
            return false;
        }
        if (this.elementType instanceof SimpleType) {
            return this.getClassName().equals(clazz.getName());
        }
        if (this.elementType instanceof TabularType || this.elementType instanceof CompositeType) {
            Class thisClass = null;
            try {
                thisClass = Thread.currentThread().getContextClassLoader().loadClass(this.getClassName());
            }
            catch (ClassNotFoundException e) {
                return false;
            }
            return thisClass.isAssignableFrom(clazz) && this.recursiveCheck((Object[])obj, this.dimension);
        }
        return false;
    }
    
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ArrayType)) {
            return false;
        }
        final ArrayType other = (ArrayType)obj;
        return this.getDimension() == other.getDimension() && this.getElementOpenType().equals(other.getElementOpenType());
    }
    
    public int hashCode() {
        if (this.cachedHashCode != 0) {
            return this.cachedHashCode;
        }
        return this.cachedHashCode = this.getDimension() + this.getElementOpenType().hashCode();
    }
    
    public String toString() {
        if (this.cachedToString != null) {
            return this.cachedToString;
        }
        final StringBuffer buffer = new StringBuffer(ArrayType.class.getName());
        buffer.append("\n");
        buffer.append(this.getTypeName());
        buffer.append("\n");
        buffer.append(new Integer(this.dimension));
        buffer.append("-dimensional array of\n");
        buffer.append(this.elementType);
        return this.cachedToString = buffer.toString();
    }
    
    private static String genName(final int dimension, final OpenType elementType) throws OpenDataException {
        if (dimension < 1) {
            throw new IllegalArgumentException("negative dimension");
        }
        if (elementType == null) {
            throw new IllegalArgumentException("null element type");
        }
        if (elementType instanceof ArrayType) {
            throw new OpenDataException("array type cannot be an element of an array type");
        }
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < dimension; ++i) {
            buffer.append('[');
        }
        buffer.append('L');
        buffer.append(elementType.getClassName());
        buffer.append(';');
        return buffer.toString();
    }
    
    private static String genDesc(final int dimension, final OpenType elementType) {
        final StringBuffer buffer = new StringBuffer();
        buffer.append(new Integer(dimension));
        buffer.append("-dimension array of ");
        buffer.append(elementType.getClassName());
        return buffer.toString();
    }
    
    private boolean recursiveCheck(final Object[] elements, final int dimension) {
        if (dimension == 1) {
            for (int i = 0; i < elements.length; ++i) {
                if (elements[i] != null && !this.elementType.isValue(elements[i])) {
                    return false;
                }
            }
        }
        else {
            for (int i = 0; i < elements.length; ++i) {
                if (!this.recursiveCheck((Object[])elements[i], dimension - 1)) {
                    return false;
                }
            }
        }
        return true;
    }
}
