// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.io.Serializable;

public class AbstractObjectList implements Cloneable, Serializable
{
    public static final int DEFAULT_INITIAL_CAPACITY = 8;
    private transient Object[] objects;
    private int size;
    private int increment;
    
    protected AbstractObjectList() {
        this(8);
    }
    
    protected AbstractObjectList(final int n) {
        this(n, n);
    }
    
    protected AbstractObjectList(final int n, final int increment) {
        this.size = 0;
        this.increment = 8;
        this.objects = new Object[n];
        this.increment = increment;
    }
    
    protected Object get(final int n) {
        Object o = null;
        if (n >= 0 && n < this.size) {
            o = this.objects[n];
        }
        return o;
    }
    
    protected void set(final int n, final Object o) {
        if (n < 0) {
            throw new IllegalArgumentException("ObjectList.set(...): index must be >= 0.");
        }
        if (n >= this.objects.length) {
            final Object[] objects = new Object[n + this.increment];
            System.arraycopy(this.objects, 0, objects, 0, this.objects.length);
            this.objects = objects;
        }
        this.objects[n] = o;
        this.size = Math.max(this.size, n + 1);
    }
    
    public void clear() {
        Arrays.fill(this.objects, null);
        this.size = 0;
    }
    
    public int size() {
        return this.size;
    }
    
    protected int indexOf(final Object o) {
        for (int i = 0; i < this.size; ++i) {
            if (this.objects[i] == o) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof AbstractObjectList)) {
            return false;
        }
        final AbstractObjectList list = (AbstractObjectList)o;
        for (int size = this.size(), i = 0; i < size; ++i) {
            if (!ObjectUtils.equal(this.get(i), list.get(i))) {
                return false;
            }
        }
        return true;
    }
    
    public int hashCode() {
        return super.hashCode();
    }
    
    public Object clone() throws CloneNotSupportedException {
        final AbstractObjectList list = (AbstractObjectList)super.clone();
        if (this.objects != null) {
            list.objects = new Object[this.objects.length];
            System.arraycopy(this.objects, 0, list.objects, 0, this.objects.length);
        }
        return list;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        final int size = this.size();
        objectOutputStream.writeInt(size);
        for (int i = 0; i < size; ++i) {
            final Object value = this.get(i);
            if (value != null && value instanceof Serializable) {
                objectOutputStream.writeInt(i);
                objectOutputStream.writeObject(value);
            }
            else {
                objectOutputStream.writeInt(-1);
            }
        }
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.objects = new Object[this.size];
        for (int int1 = objectInputStream.readInt(), i = 0; i < int1; ++i) {
            final int int2 = objectInputStream.readInt();
            if (int2 != -1) {
                this.set(int2, objectInputStream.readObject());
            }
        }
    }
}
