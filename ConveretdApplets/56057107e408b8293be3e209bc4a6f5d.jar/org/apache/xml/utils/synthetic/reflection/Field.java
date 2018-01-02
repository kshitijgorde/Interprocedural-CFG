// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils.synthetic.reflection;

import java.lang.reflect.Modifier;
import org.apache.xml.utils.synthetic.SynthesisException;
import org.apache.xml.utils.synthetic.Class;

public class Field implements Member
{
    public String name;
    public String initializer;
    int modifiers;
    java.lang.reflect.Field realfield;
    Class declaringClass;
    Class type;
    
    public Field(final String name, final Class declaringClass) {
        this.initializer = null;
        this.realfield = null;
        this.name = name;
        this.declaringClass = declaringClass;
    }
    
    public Field(final java.lang.reflect.Field realfield, final Class declaringClass) {
        this(realfield.getName(), declaringClass);
        this.realfield = realfield;
        this.type = Class.forClass(realfield.getType());
    }
    
    public boolean equals(final Object obj) {
        if (this.realfield != null) {
            return this.realfield.equals(obj);
        }
        if (obj instanceof Field) {
            final Field objf = (Field)obj;
            return this.declaringClass.equals(objf.declaringClass) && this.name.equals(objf.name) && this.type.equals(objf.type);
        }
        return false;
    }
    
    public Object get(final Object obj) throws IllegalArgumentException, IllegalAccessException {
        if (this.realfield != null) {
            return this.realfield.get(obj);
        }
        throw new IllegalStateException();
    }
    
    public boolean getBoolean(final Object obj) throws IllegalArgumentException, IllegalAccessException {
        if (this.realfield != null) {
            return this.realfield.getBoolean(obj);
        }
        throw new IllegalStateException();
    }
    
    public byte getByte(final Object obj) throws IllegalArgumentException, IllegalAccessException {
        if (this.realfield != null) {
            return this.realfield.getByte(obj);
        }
        throw new IllegalStateException();
    }
    
    public char getChar(final Object obj) throws IllegalArgumentException, IllegalAccessException {
        if (this.realfield != null) {
            return this.realfield.getChar(obj);
        }
        throw new IllegalStateException();
    }
    
    public Class getDeclaringClass() {
        if (this.realfield != null) {
            return Class.forClass(this.realfield.getDeclaringClass());
        }
        throw new IllegalStateException();
    }
    
    public double getDouble(final Object obj) throws IllegalArgumentException, IllegalAccessException {
        if (this.realfield != null) {
            return this.realfield.getDouble(obj);
        }
        throw new IllegalStateException();
    }
    
    public float getFloat(final Object obj) throws IllegalArgumentException, IllegalAccessException {
        if (this.realfield != null) {
            return this.realfield.getFloat(obj);
        }
        throw new IllegalStateException();
    }
    
    public String getInitializer() {
        return this.initializer;
    }
    
    public int getInt(final Object obj) throws IllegalArgumentException, IllegalAccessException {
        if (this.realfield != null) {
            return this.realfield.getInt(obj);
        }
        throw new IllegalStateException();
    }
    
    public long getLong(final Object obj) throws IllegalArgumentException, IllegalAccessException {
        if (this.realfield != null) {
            return this.realfield.getLong(obj);
        }
        throw new IllegalStateException();
    }
    
    public int getModifiers() {
        if (this.realfield != null) {
            this.modifiers = this.realfield.getModifiers();
        }
        return this.modifiers;
    }
    
    public String getName() {
        return this.name;
    }
    
    public short getShort(final Object obj) throws IllegalArgumentException, IllegalAccessException {
        if (this.realfield != null) {
            return this.realfield.getShort(obj);
        }
        throw new IllegalStateException();
    }
    
    public Class getType() {
        if (this.realfield != null) {
            this.type = Class.forClass(this.realfield.getType());
        }
        return this.type;
    }
    
    public int hashCode() {
        if (this.realfield != null) {
            return this.realfield.hashCode();
        }
        return this.declaringClass.getName().hashCode() ^ this.name.hashCode();
    }
    
    public void set(final Object obj, final Object value) throws IllegalArgumentException, IllegalAccessException {
        if (this.realfield != null) {
            this.realfield.set(obj, value);
        }
        throw new IllegalStateException();
    }
    
    public void setBoolean(final Object obj, final boolean z) throws IllegalArgumentException, IllegalAccessException {
        if (this.realfield != null) {
            this.realfield.setBoolean(obj, z);
        }
        throw new IllegalStateException();
    }
    
    public void setByte(final Object obj, final byte b) throws IllegalArgumentException, IllegalAccessException {
        if (this.realfield != null) {
            this.realfield.setByte(obj, b);
        }
        throw new IllegalStateException();
    }
    
    public void setChar(final Object obj, final char c) throws IllegalArgumentException, IllegalAccessException {
        if (this.realfield != null) {
            this.realfield.setChar(obj, c);
        }
        throw new IllegalStateException();
    }
    
    public void setDeclaringClass(final Class declaringClass) {
        this.declaringClass = declaringClass;
    }
    
    public void setDouble(final Object obj, final double d) throws IllegalArgumentException, IllegalAccessException {
        if (this.realfield != null) {
            this.realfield.setDouble(obj, d);
        }
        throw new IllegalStateException();
    }
    
    public void setFloat(final Object obj, final float f) throws IllegalArgumentException, IllegalAccessException {
        if (this.realfield != null) {
            this.realfield.setFloat(obj, f);
        }
        throw new IllegalStateException();
    }
    
    public void setInitializer(final String i) throws SynthesisException {
        if (this.realfield != null) {
            throw new SynthesisException(2);
        }
        this.initializer = i;
    }
    
    public void setInt(final Object obj, final int i) throws IllegalArgumentException, IllegalAccessException {
        if (this.realfield != null) {
            this.realfield.setInt(obj, i);
        }
        throw new IllegalStateException();
    }
    
    public void setLong(final Object obj, final long l) throws IllegalArgumentException, IllegalAccessException {
        if (this.realfield != null) {
            this.realfield.setLong(obj, l);
        }
        throw new IllegalStateException();
    }
    
    public void setModifiers(final int modifiers) throws SynthesisException {
        if (this.realfield != null) {
            throw new SynthesisException(2);
        }
        this.modifiers = modifiers;
    }
    
    public void setShort(final Object obj, final short s) throws IllegalArgumentException, IllegalAccessException {
        if (this.realfield != null) {
            this.realfield.setShort(obj, s);
        }
        throw new IllegalStateException();
    }
    
    public void setType(final Class type) throws SynthesisException {
        if (this.realfield != null) {
            throw new SynthesisException(2);
        }
        this.type = type;
    }
    
    public String toSource() {
        final StringBuffer sb = new StringBuffer(Modifier.toString(this.getModifiers())).append(' ').append(this.getType().getJavaName()).append(' ').append(this.getName());
        final String i = this.getInitializer();
        if (i != null && i.length() > 0) {
            sb.append('=').append(i);
        }
        sb.append(';');
        return sb.toString();
    }
    
    public String toString() {
        if (this.realfield != null) {
            return this.realfield.toString();
        }
        throw new IllegalStateException();
    }
}
