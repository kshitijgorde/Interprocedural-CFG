// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.util.Arrays;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectTable implements Serializable
{
    private int rows;
    private int columns;
    private transient Object[][] data;
    private int rowIncrement;
    private int columnIncrement;
    
    public ObjectTable() {
        this(1);
    }
    
    public ObjectTable(final int n) {
        this(n, n);
    }
    
    public ObjectTable(final int rowIncrement, final int columnIncrement) {
        if (rowIncrement < 1) {
            throw new IllegalArgumentException("Increment must be positive.");
        }
        if (columnIncrement < 1) {
            throw new IllegalArgumentException("Increment must be positive.");
        }
        this.rows = 0;
        this.columns = 0;
        this.rowIncrement = rowIncrement;
        this.columnIncrement = columnIncrement;
        this.data = new Object[rowIncrement][];
    }
    
    public int getColumnIncrement() {
        return this.columnIncrement;
    }
    
    public int getRowIncrement() {
        return this.rowIncrement;
    }
    
    protected void ensureRowCapacity(final int n) {
        if (n >= this.data.length) {
            final Object[][] data = new Object[n + this.rowIncrement][];
            System.arraycopy(this.data, 0, data, 0, this.data.length);
            for (int i = this.data.length; i <= n; ++i) {
                data[i] = new Object[0];
            }
            this.data = data;
            this.rows = n + 1;
        }
    }
    
    public void ensureCapacity(final int n, final int n2) {
        if (n < 0) {
            throw new IndexOutOfBoundsException("Row is invalid.");
        }
        if (n2 < 0) {
            throw new IndexOutOfBoundsException("Column is invalid.");
        }
        this.ensureRowCapacity(n);
        final Object[] array = this.data[n];
        if (n2 >= array.length) {
            final Object[] array2 = new Object[n2 + this.columnIncrement];
            System.arraycopy(array, 0, array2, 0, array.length);
            this.data[n] = array2;
        }
    }
    
    public int getRowCount() {
        return this.rows;
    }
    
    public int getColumnCount() {
        return this.columns;
    }
    
    protected Object getObject(final int n, final int n2) {
        Object o = null;
        if (n < this.data.length) {
            final Object[] array = this.data[n];
            if (n2 < array.length) {
                o = array[n2];
            }
        }
        return o;
    }
    
    protected void setObject(final int n, final int n2, final Object o) {
        this.ensureCapacity(n, n2);
        this.data[n][n2] = o;
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObjectTable)) {
            return false;
        }
        final ObjectTable objectTable = (ObjectTable)o;
        if (this.getRowCount() != objectTable.getRowCount()) {
            return false;
        }
        if (this.getColumnCount() != objectTable.getColumnCount()) {
            return false;
        }
        for (int i = 0; i < this.getRowCount(); ++i) {
            for (int j = 0; j < this.getColumnCount(); ++j) {
                if (!ObjectUtils.equal(this.getObject(i, j), objectTable.getObject(i, j))) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public int hashCode() {
        return 29 * this.rows + this.columns;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        final int length = this.data.length;
        objectOutputStream.writeInt(length);
        for (int i = 0; i < length; ++i) {
            final Object[] array = this.data[i];
            final int length2 = array.length;
            objectOutputStream.writeInt(length2);
            for (int j = 0; j < length2; ++j) {
                this.writeSerializedData(objectOutputStream, array[j]);
            }
        }
    }
    
    protected void writeSerializedData(final ObjectOutputStream objectOutputStream, final Object o) throws IOException {
        objectOutputStream.writeObject(o);
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        final int int1 = objectInputStream.readInt();
        this.data = new Object[int1][];
        for (int i = 0; i < int1; ++i) {
            final int int2 = objectInputStream.readInt();
            final Object[] array = new Object[int2];
            this.data[i] = array;
            for (int j = 0; j < int2; ++j) {
                array[j] = this.readSerializedData(objectInputStream);
            }
        }
    }
    
    protected Object readSerializedData(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        return objectInputStream.readObject();
    }
    
    public void clear() {
        this.rows = 0;
        this.columns = 0;
        for (int i = 0; i < this.data.length; ++i) {
            if (this.data[i] != null) {
                Arrays.fill(this.data[i], null);
            }
        }
    }
}
