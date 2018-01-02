// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.utils;

import java.util.NoSuchElementException;
import java.util.Enumeration;
import java.util.Comparator;

public class MyVector
{
    protected Object[] elementData;
    protected int elementCount;
    protected int capacityIncrement;
    private static Comparator comp;
    private static String synczer;
    
    public static void sort(final Object[] array, final Comparator comp) {
        synchronized (MyVector.synczer) {
            MyVector.comp = comp;
            if (MyVector.comp == null) {
                throw new RuntimeException("Comparator received is null![1]");
            }
            try {
                sort(array, 0, array.length - 1);
            }
            catch (NullPointerException ex) {
                throw new RuntimeException("comp:" + MyVector.comp);
            }
            MyVector.comp = null;
        }
    }
    
    private static void sort(final Object[] array, final int n, final int n2) {
        int i = n;
        int n3 = n2;
        if (i >= n3) {
            return;
        }
        if (i == n3 - 1) {
            final Object o = array[i];
            final Object o2 = array[n3];
            if (MyVector.comp.compare(o, o2) < 0) {
                array[i] = o2;
                array[n3] = o;
            }
        }
        final Object o3 = array[(i + n3) / 2];
        array[(i + n3) / 2] = array[n3];
        array[n3] = o3;
        while (i < n3) {
            while (i < n3 && MyVector.comp.compare(o3, array[i]) >= 0) {
                ++i;
            }
            while (i < n3 && MyVector.comp.compare(o3, array[n3]) <= 0) {
                --n3;
            }
            if (i < n3) {
                final Object o4 = array[i];
                array[i] = array[n3];
                array[n3] = o4;
            }
        }
        array[n2] = array[n3];
        array[n3] = o3;
        sort(array, n, i - 1);
        sort(array, n3 + 1, n2);
    }
    
    public MyVector() {
        this(10);
    }
    
    public MyVector(final int n) {
        this(n, 0);
    }
    
    public MyVector(final int n, final int capacityIncrement) {
        if (n < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + n);
        }
        this.elementData = new Object[n];
        this.capacityIncrement = capacityIncrement;
    }
    
    public MyVector(final Object[] array, final int n, final int n2) {
        if (array != null && n2 < array.length && n <= n2) {
            this.elementCount = n2 - n + 1;
            System.arraycopy(array, n, this.elementData = new Object[this.elementCount], 0, this.elementCount);
            return;
        }
        throw new ArrayIndexOutOfBoundsException(n + " " + n2 + "; Array Size : " + array.length);
    }
    
    public synchronized void addElement(final Object o) {
        this.ensureCapacityHelper(this.elementCount + 1);
        this.elementData[this.elementCount++] = o;
    }
    
    public boolean contains(final Object o) {
        return this.indexOf(o, 0) >= 0;
    }
    
    public synchronized void copyInto(final Object[] array) {
        System.arraycopy(this.elementData, 0, array, 0, this.elementCount);
    }
    
    public synchronized Object elementAt(final int n) {
        if (n >= this.elementCount) {
            return null;
        }
        return this.elementData[n];
    }
    
    public Enumeration elements() {
        return new Enumeration() {
            int count = 0;
            
            @Override
            public boolean hasMoreElements() {
                return this.count < MyVector.this.elementCount;
            }
            
            @Override
            public Object nextElement() {
                synchronized (this) {
                    if (this.count < MyVector.this.elementCount) {
                        return MyVector.this.elementData[this.count++];
                    }
                }
                throw new NoSuchElementException("Vector Enumeration");
            }
        };
    }
    
    public synchronized void ensureCapacity(final int n) {
        this.ensureCapacityHelper(n);
    }
    
    private void ensureCapacityHelper(final int n) {
        final int length = this.elementData.length;
        if (n > length) {
            final Object[] elementData = this.elementData;
            int n2 = (this.capacityIncrement > 0) ? (length + this.capacityIncrement) : (length * 2);
            if (n2 < n) {
                n2 = n;
            }
            System.arraycopy(elementData, 0, this.elementData = new Object[n2], 0, this.elementCount);
        }
    }
    
    public synchronized Object firstElement() {
        if (this.elementCount == 0) {
            throw new NoSuchElementException();
        }
        return this.elementData[0];
    }
    
    public int indexOf(final Object o) {
        return this.indexOf(o, 0);
    }
    
    public synchronized int indexOf(final Object o, final int n) {
        if (o == null) {
            for (int i = n; i < this.elementCount; ++i) {
                if (this.elementData[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int j = n; j < this.elementCount; ++j) {
                if (o.equals(this.elementData[j])) {
                    return j;
                }
            }
        }
        return -1;
    }
    
    public synchronized void insertElementAt(final Object o, final int n) {
        if (n > this.elementCount) {
            throw new ArrayIndexOutOfBoundsException(n + " > " + this.elementCount);
        }
        this.ensureCapacityHelper(this.elementCount + 1);
        System.arraycopy(this.elementData, n, this.elementData, n + 1, this.elementCount - n);
        this.elementData[n] = o;
        ++this.elementCount;
    }
    
    public boolean isEmpty() {
        return this.elementCount == 0;
    }
    
    public synchronized Object lastElement() {
        if (this.elementCount == 0) {
            throw new NoSuchElementException();
        }
        return this.elementData[this.elementCount - 1];
    }
    
    public synchronized void removeAllElements() {
        for (int i = 0; i < this.elementCount; ++i) {
            this.elementData[i] = null;
        }
        this.elementCount = 0;
    }
    
    public synchronized boolean removeElement(final Object o) {
        final int index = this.indexOf(o);
        if (index >= 0) {
            this.removeElementAt(index);
            return true;
        }
        return false;
    }
    
    public synchronized void removeElementAt(final int n) {
        if (n >= this.elementCount) {
            throw new ArrayIndexOutOfBoundsException(n + " >= " + this.elementCount);
        }
        if (n < 0) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        final int n2 = this.elementCount - n - 1;
        if (n2 > 0) {
            System.arraycopy(this.elementData, n + 1, this.elementData, n, n2);
        }
        --this.elementCount;
        this.elementData[this.elementCount] = null;
    }
    
    public synchronized void setElementAt(final Object o, final int n) {
        if (n >= this.elementCount) {
            throw new ArrayIndexOutOfBoundsException(n + " >= " + this.elementCount);
        }
        this.elementData[n] = o;
    }
    
    public synchronized void setSize(final int elementCount) {
        if (elementCount > this.elementCount) {
            this.ensureCapacityHelper(elementCount);
        }
        else {
            for (int i = elementCount; i < this.elementCount; ++i) {
                this.elementData[i] = null;
            }
        }
        this.elementCount = elementCount;
    }
    
    public synchronized int size() {
        return this.elementCount;
    }
    
    public void sort(final Comparator comp) {
        synchronized (MyVector.synczer) {
            MyVector.comp = comp;
            if (MyVector.comp == null) {
                throw new RuntimeException("Comparator received is null![2]");
            }
            try {
                sort(this.elementData, 0, this.elementCount - 1);
            }
            catch (NullPointerException ex) {
                throw new RuntimeException("comp:" + MyVector.comp);
            }
            MyVector.comp = null;
        }
    }
    
    static {
        MyVector.comp = null;
        MyVector.synczer = "sync";
    }
}
