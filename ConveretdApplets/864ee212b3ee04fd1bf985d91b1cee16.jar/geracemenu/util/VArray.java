// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.util;

import java.lang.reflect.Array;
import java.util.Enumeration;
import java.io.Serializable;

public class VArray implements Cloneable, Serializable
{
    protected static final int DEFAULT_MAX_CAPACITY_INCREMENT = 4096;
    protected static final int DEFAULT_STRING_MAX_CAPACITY_INCREMENT = 65536;
    protected Object array;
    protected int count;
    protected int maxCapacityIncrement;
    protected boolean componentIsObject;
    
    public final Object getArray() {
        return this.array;
    }
    
    public final Object getTrimmedArray() {
        final Object array = this.newArray(this.count);
        System.arraycopy(this.array, 0, array, 0, this.count);
        return array;
    }
    
    public final Class getComponentType() {
        return this.array.getClass().getComponentType();
    }
    
    public final int size() {
        return this.count;
    }
    
    public final int getSize() {
        return this.count;
    }
    
    public final int length() {
        return this.count;
    }
    
    public final int getLength() {
        return this.count;
    }
    
    public final boolean isEmpty() {
        return this.count == 0;
    }
    
    public int hashCode() {
        int n = 0;
        int n2 = 0;
        final int count = this.count;
        final int n3 = (count >= 16) ? (count / 8) : 1;
        final Class componentType = this.getComponentType();
        if (componentType.equals(Boolean.TYPE)) {
            final boolean[] array = (boolean[])this.array;
            for (int i = count; i > 0; i -= n3, n2 += n3) {
                n = n * 39 + (array[n2] ? 1231 : 1237);
            }
        }
        else if (componentType.equals(Byte.TYPE)) {
            final byte[] array2 = (byte[])this.array;
            for (int j = count; j > 0; j -= n3, n2 += n3) {
                n = n * 39 + array2[n2];
            }
        }
        else if (componentType.equals(Character.TYPE)) {
            final char[] array3 = (char[])this.array;
            for (int k = count; k > 0; k -= n3, n2 += n3) {
                n = n * 39 + array3[n2];
            }
        }
        else if (componentType.equals(Short.TYPE)) {
            final short[] array4 = (short[])this.array;
            for (int l = count; l > 0; l -= n3, n2 += n3) {
                n = n * 39 + array4[n2];
            }
        }
        else if (componentType.equals(Integer.TYPE)) {
            final int[] array5 = (int[])this.array;
            for (int n4 = count; n4 > 0; n4 -= n3, n2 += n3) {
                n = n * 39 + array5[n2];
            }
        }
        else if (componentType.equals(Long.TYPE)) {
            final long[] array6 = (long[])this.array;
            for (int n5 = count; n5 > 0; n5 -= n3, n2 += n3) {
                n = n * 39 + (int)(array6[n2] ^ array6[n2] >> 32);
            }
        }
        else if (componentType.equals(Float.TYPE)) {
            final float[] array7 = (float[])this.array;
            for (int n6 = count; n6 > 0; n6 -= n3, n2 += n3) {
                n = n * 39 + Float.floatToIntBits(array7[n2]);
            }
        }
        else if (componentType.equals(Double.TYPE)) {
            final double[] array8 = (double[])this.array;
            for (int n7 = count; n7 > 0; n7 -= n3, n2 += n3) {
                final long doubleToLongBits = Double.doubleToLongBits(array8[n2]);
                n = n * 39 + (int)(doubleToLongBits ^ doubleToLongBits >> 32);
            }
        }
        else {
            final Object[] array9 = (Object[])this.array;
            for (int n8 = count; n8 > 0; n8 -= n3, n2 += n3) {
                n = n * 39 + array9[n2].hashCode();
            }
        }
        return n;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o instanceof VArray) {
            final VArray vArray = (VArray)o;
            int count = this.count;
            final Class componentType = this.getComponentType();
            if (count == vArray.count && componentType.equals(vArray.getComponentType())) {
                int n = 0;
                int n2 = 0;
                if (componentType.equals(Boolean.TYPE)) {
                    while (count-- != 0) {
                        if (this.getBoolean(n++) != vArray.getBoolean(n2++)) {
                            return false;
                        }
                    }
                    return true;
                }
                if (componentType.equals(Byte.TYPE)) {
                    while (count-- != 0) {
                        if (this.getByte(n++) != vArray.getByte(n2++)) {
                            return false;
                        }
                    }
                    return true;
                }
                if (componentType.equals(Character.TYPE)) {
                    while (count-- != 0) {
                        if (this.getChar(n++) != vArray.getChar(n2++)) {
                            return false;
                        }
                    }
                    return true;
                }
                if (componentType.equals(Short.TYPE)) {
                    while (count-- != 0) {
                        if (this.getShort(n++) != vArray.getShort(n2++)) {
                            return false;
                        }
                    }
                    return true;
                }
                if (componentType.equals(Integer.TYPE)) {
                    while (count-- != 0) {
                        if (this.getInt(n++) != vArray.getInt(n2++)) {
                            return false;
                        }
                    }
                    return true;
                }
                if (componentType.equals(Long.TYPE)) {
                    while (count-- != 0) {
                        if (this.getLong(n++) != vArray.getLong(n2++)) {
                            return false;
                        }
                    }
                    return true;
                }
                if (componentType.equals(Float.TYPE)) {
                    while (count-- != 0) {
                        if (this.getFloat(n++) != vArray.getFloat(n2++)) {
                            return false;
                        }
                    }
                    return true;
                }
                if (componentType.equals(Double.TYPE)) {
                    while (count-- != 0) {
                        if (this.getDouble(n++) != vArray.getDouble(n2++)) {
                            return false;
                        }
                    }
                    return true;
                }
                while (count-- != 0) {
                    if (!this.get(n++).equals(vArray.get(n2++))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public final Enumeration elements() {
        return new VArrayEnumerator(this);
    }
    
    public final Object get(final int n) {
        if (n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        return Array.get(this.array, n);
    }
    
    public final boolean getBoolean(final int n) {
        if (n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        return Array.getBoolean(this.array, n);
    }
    
    public final byte getByte(final int n) {
        if (n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        return Array.getByte(this.array, n);
    }
    
    public final char getChar(final int n) {
        if (n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        return Array.getChar(this.array, n);
    }
    
    public final short getShort(final int n) {
        if (n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        return Array.getShort(this.array, n);
    }
    
    public final int getInt(final int n) {
        if (n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        return Array.getInt(this.array, n);
    }
    
    public final long getLong(final int n) {
        if (n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        return Array.getLong(this.array, n);
    }
    
    public final float getFloat(final int n) {
        if (n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        return Array.getFloat(this.array, n);
    }
    
    public final double getDouble(final int n) {
        if (n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        return Array.getDouble(this.array, n);
    }
    
    public final void set(final int n, final Object o) {
        if (n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        Array.set(this.array, n, o);
    }
    
    public final void setBoolean(final int n, final boolean b) {
        if (n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        Array.setBoolean(this.array, n, b);
    }
    
    public final void setByte(final int n, final byte b) {
        if (n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        Array.setByte(this.array, n, b);
    }
    
    public final void setChar(final int n, final char c) {
        if (n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        Array.setChar(this.array, n, c);
    }
    
    public final void setShort(final int n, final short n2) {
        if (n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        Array.setShort(this.array, n, n2);
    }
    
    public final void setInt(final int n, final int n2) {
        if (n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        Array.setInt(this.array, n, n2);
    }
    
    public final void setLong(final int n, final long n2) {
        if (n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        Array.setLong(this.array, n, n2);
    }
    
    public final void setFloat(final int n, final float n2) {
        if (n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        Array.setFloat(this.array, n, n2);
    }
    
    public final void setDouble(final int n, final double n2) {
        if (n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        Array.setDouble(this.array, n, n2);
    }
    
    public final void setLength(final int count) {
        if (count < 0) {
            throw new ArrayIndexOutOfBoundsException(count);
        }
        this.ensureCapacity(count);
        this.count = count;
    }
    
    public final void trim() {
        if (this.count < this.capacity()) {
            final Object array = this.newArray(this.count);
            System.arraycopy(this.array, 0, array, 0, this.count);
            this.array = array;
        }
    }
    
    public final int indexOf(final Object o) {
        return this.indexOf(o, 0);
    }
    
    public final int indexOf(final Object o, final int n) {
        final int length = this.length();
        final Object[] array = (Object[])this.getArray();
        for (int i = (n < 0) ? 0 : n; i < length; ++i) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }
    
    public final int lastIndexOf(final Object o) {
        return this.lastIndexOf(o, this.length() - 1);
    }
    
    public final int lastIndexOf(final Object o, final int n) {
        final int length = this.length();
        final Object[] array = (Object[])this.getArray();
        for (int i = (n >= length) ? (length - 1) : n; i >= 0; --i) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }
    
    public final int indexOf(final boolean b) {
        return this.indexOf(b, 0);
    }
    
    public final int indexOf(final boolean b, final int n) {
        final int length = this.length();
        final boolean[] array = (boolean[])this.getArray();
        for (int i = (n < 0) ? 0 : n; i < length; ++i) {
            if (array[i] == b) {
                return i;
            }
        }
        return -1;
    }
    
    public final int lastIndexOf(final boolean b) {
        return this.lastIndexOf(b, this.length() - 1);
    }
    
    public final int lastIndexOf(final boolean b, final int n) {
        final int length = this.length();
        final boolean[] array = (boolean[])this.getArray();
        for (int i = (n >= length) ? (length - 1) : n; i >= 0; --i) {
            if (array[i] == b) {
                return i;
            }
        }
        return -1;
    }
    
    public final int indexOf(final byte b) {
        return this.indexOf(b, 0);
    }
    
    public final int indexOf(final byte b, final int n) {
        final int length = this.length();
        final byte[] array = (byte[])this.getArray();
        for (int i = (n < 0) ? 0 : n; i < length; ++i) {
            if (array[i] == b) {
                return i;
            }
        }
        return -1;
    }
    
    public final int lastIndexOf(final byte b) {
        return this.lastIndexOf(b, this.length() - 1);
    }
    
    public final int lastIndexOf(final byte b, final int n) {
        final int length = this.length();
        final byte[] array = (byte[])this.getArray();
        for (int i = (n >= length) ? (length - 1) : n; i >= 0; --i) {
            if (array[i] == b) {
                return i;
            }
        }
        return -1;
    }
    
    public final int indexOf(final char c) {
        return this.indexOf(c, 0);
    }
    
    public final int indexOf(final char c, final int n) {
        final int length = this.length();
        final char[] array = (char[])this.getArray();
        for (int i = (n < 0) ? 0 : n; i < length; ++i) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1;
    }
    
    public final int lastIndexOf(final char c) {
        return this.lastIndexOf(c, this.length() - 1);
    }
    
    public final int lastIndexOf(final char c, final int n) {
        final int length = this.length();
        final char[] array = (char[])this.getArray();
        for (int i = (n >= length) ? (length - 1) : n; i >= 0; --i) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1;
    }
    
    public final int indexOf(final short n) {
        return this.indexOf(n, 0);
    }
    
    public final int indexOf(final short n, final int n2) {
        final int length = this.length();
        final short[] array = (short[])this.getArray();
        for (int i = (n2 < 0) ? 0 : n2; i < length; ++i) {
            if (array[i] == n) {
                return i;
            }
        }
        return -1;
    }
    
    public final int lastIndexOf(final short n) {
        return this.lastIndexOf(n, this.length() - 1);
    }
    
    public final int lastIndexOf(final short n, final int n2) {
        final int length = this.length();
        final short[] array = (short[])this.getArray();
        for (int i = (n2 >= length) ? (length - 1) : n2; i >= 0; --i) {
            if (array[i] == n) {
                return i;
            }
        }
        return -1;
    }
    
    public final int indexOf(final int n) {
        return this.indexOf(n, 0);
    }
    
    public final int indexOf(final int n, final int n2) {
        final int length = this.length();
        final int[] array = (int[])this.getArray();
        for (int i = (n2 < 0) ? 0 : n2; i < length; ++i) {
            if (array[i] == n) {
                return i;
            }
        }
        return -1;
    }
    
    public final int lastIndexOf(final int n) {
        return this.lastIndexOf(n, this.length() - 1);
    }
    
    public final int lastIndexOf(final int n, final int n2) {
        final int length = this.length();
        final int[] array = (int[])this.getArray();
        for (int i = (n2 >= length) ? (length - 1) : n2; i >= 0; --i) {
            if (array[i] == n) {
                return i;
            }
        }
        return -1;
    }
    
    public final int indexOf(final long n) {
        return this.indexOf(n, 0);
    }
    
    public final int indexOf(final long n, final int n2) {
        final int length = this.length();
        final long[] array = (long[])this.getArray();
        for (int i = (n2 < 0) ? 0 : n2; i < length; ++i) {
            if (array[i] == n) {
                return i;
            }
        }
        return -1;
    }
    
    public final int lastIndexOf(final long n) {
        return this.lastIndexOf(n, this.length() - 1);
    }
    
    public final int lastIndexOf(final long n, final int n2) {
        final int length = this.length();
        final long[] array = (long[])this.getArray();
        for (int i = (n2 >= length) ? (length - 1) : n2; i >= 0; --i) {
            if (array[i] == n) {
                return i;
            }
        }
        return -1;
    }
    
    public final int indexOf(final float n) {
        return this.indexOf(n, 0);
    }
    
    public final int indexOf(final float n, final int n2) {
        final int length = this.length();
        final float[] array = (float[])this.getArray();
        for (int i = (n2 < 0) ? 0 : n2; i < length; ++i) {
            if (array[i] == n) {
                return i;
            }
        }
        return -1;
    }
    
    public final int lastIndexOf(final float n) {
        return this.lastIndexOf(n, this.length() - 1);
    }
    
    public final int lastIndexOf(final float n, final int n2) {
        final int length = this.length();
        final float[] array = (float[])this.getArray();
        for (int i = (n2 >= length) ? (length - 1) : n2; i >= 0; --i) {
            if (array[i] == n) {
                return i;
            }
        }
        return -1;
    }
    
    public final int indexOf(final double n) {
        return this.indexOf(n, 0);
    }
    
    public final int indexOf(final double n, final int n2) {
        final int length = this.length();
        final double[] array = (double[])this.getArray();
        for (int i = (n2 < 0) ? 0 : n2; i < length; ++i) {
            if (array[i] == n) {
                return i;
            }
        }
        return -1;
    }
    
    public final int lastIndexOf(final double n) {
        return this.lastIndexOf(n, this.length() - 1);
    }
    
    public final int lastIndexOf(final double n, final int n2) {
        final int length = this.length();
        final double[] array = (double[])this.getArray();
        for (int i = (n2 >= length) ? (length - 1) : n2; i >= 0; --i) {
            if (array[i] == n) {
                return i;
            }
        }
        return -1;
    }
    
    public final void removeAll() {
        this.array = this.newArray(16);
        this.count = 0;
    }
    
    public final void remove(final int n, final int n2) {
        if (n < 0 || n + n2 > this.count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (n2 > 0) {
            System.arraycopy(this.array, n + n2, this.array, n, this.count - (n + n2));
            this.count -= n2;
            this.shrinkCapacity();
            if (this.componentIsObject) {
                final int min = Math.min(this.capacity(), this.count + n2);
                final Object[] array = (Object[])this.array;
                for (int i = this.count; i < min; ++i) {
                    array[i] = null;
                }
            }
        }
    }
    
    public final VArray subarray(final int n) {
        return this.subarray(n, this.count);
    }
    
    public final VArray subarray(final int n, final int n2) {
        if (n < 0 || n2 > this.count || n > n2) {
            throw new ArrayIndexOutOfBoundsException();
        }
        final int n3 = n2 - n;
        final Object array = this.newArray(n3);
        System.arraycopy(this.array, n, array, 0, n3);
        return new VArray(array, this.maxCapacityIncrement);
    }
    
    public final VArray append(final Object o) {
        this.ensureCapacity(this.count + 1);
        Array.set(this.array, this.count++, o);
        return this;
    }
    
    public final VArray append(final boolean b) {
        this.ensureCapacity(this.count + 1);
        Array.setBoolean(this.array, this.count++, b);
        return this;
    }
    
    public final VArray append(final byte b) {
        this.ensureCapacity(this.count + 1);
        Array.setByte(this.array, this.count++, b);
        return this;
    }
    
    public final VArray append(final char c) {
        this.ensureCapacity(this.count + 1);
        Array.setChar(this.array, this.count++, c);
        return this;
    }
    
    public final VArray append(final short n) {
        this.ensureCapacity(this.count + 1);
        Array.setShort(this.array, this.count++, n);
        return this;
    }
    
    public final VArray append(final int n) {
        this.ensureCapacity(this.count + 1);
        Array.setInt(this.array, this.count++, n);
        return this;
    }
    
    public final VArray append(final long n) {
        this.ensureCapacity(this.count + 1);
        Array.setLong(this.array, this.count++, n);
        return this;
    }
    
    public final VArray append(final float n) {
        this.ensureCapacity(this.count + 1);
        Array.setFloat(this.array, this.count++, n);
        return this;
    }
    
    public final VArray append(final double n) {
        this.ensureCapacity(this.count + 1);
        Array.setDouble(this.array, this.count++, n);
        return this;
    }
    
    public final VArray append(final String s) {
        if (this.getComponentType() == Character.TYPE) {
            return this.append(s, 0, s.length());
        }
        this.ensureCapacity(this.count + 1);
        Array.set(this.array, this.count++, s);
        return this;
    }
    
    public final VArray append(final String s, final int n, final int n2) {
        final char[] array = new char[n2 - n];
        s.getChars(n, n2, array, 0);
        return this.append(array, n, n2);
    }
    
    public final VArray append(final char[] array) {
        return this.append(array, 0, array.length);
    }
    
    public final VArray append(final char[] array, final int n, final int n2) {
        if (n < 0 || n2 > array.length || n > n2) {
            throw new ArrayIndexOutOfBoundsException();
        }
        final int n3 = n2 - n;
        this.ensureCapacity(this.count + n3);
        System.arraycopy(array, n, this.array, this.count, n3);
        this.count += n3;
        return this;
    }
    
    public final VArray append(final VArray vArray) {
        return this.append(vArray, 0, vArray.length());
    }
    
    public final VArray append(final VArray vArray, final int n, final int n2) {
        if (n < 0 || n2 > vArray.count || n > n2) {
            throw new ArrayIndexOutOfBoundsException();
        }
        final int n3 = n2 - n;
        this.ensureCapacity(this.count + n3);
        System.arraycopy(vArray.array, n, this.array, this.count, n3);
        this.count += n3;
        return this;
    }
    
    public final VArray insert(final int n, final VArray vArray) {
        return this.insert(n, vArray, 0, vArray.length());
    }
    
    public final VArray insert(final int n, final VArray vArray, final int n2, final int n3) {
        if (n < 0 || n > this.count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (n2 < 0 || n3 > vArray.count || n2 > n3) {
            throw new ArrayIndexOutOfBoundsException();
        }
        final int n4 = n3 - n2;
        if (n4 > 0) {
            this.ensureCapacity(this.count + n4);
            System.arraycopy(this.array, n, this.array, n + n4, this.count - n);
            System.arraycopy(vArray.array, n2, this.array, n, n4);
            this.count += n4;
        }
        return this;
    }
    
    protected final VArray insertSpace(final int n, final int n2) {
        if (n < 0 || n > this.count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (n2 > 0) {
            this.ensureCapacity(this.count + n2);
            System.arraycopy(this.array, n, this.array, n + n2, this.count - n);
            this.count += n2;
        }
        return this;
    }
    
    public final VArray replace(final int n, final int n2, final VArray vArray) {
        return this.replace(n, n2, vArray, 0, vArray.length());
    }
    
    public final VArray replace(final int n, final int n2, final VArray vArray, final int n3, final int n4) {
        if (n < 0 || n2 > this.count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (n3 < 0 || n4 > vArray.count || n3 > n4) {
            throw new ArrayIndexOutOfBoundsException();
        }
        final int n5 = n2 - n;
        final int n6 = n4 - n3;
        final int count = this.count - n5 + n6;
        this.ensureCapacity(count);
        final int n7 = n + n6;
        if (n2 != n7) {
            System.arraycopy(this.array, n2, this.array, n7, count - n7);
        }
        System.arraycopy(vArray.array, n3, this.array, n, n6);
        final int count2 = this.count;
        if ((this.count = count) < count2) {
            this.shrinkCapacity();
            if (this.componentIsObject) {
                final int min = Math.min(this.capacity(), count2);
                final Object[] array = (Object[])this.array;
                for (int i = count; i < min; ++i) {
                    array[i] = null;
                }
            }
        }
        return this;
    }
    
    public Object clone() {
        try {
            final VArray vArray = (VArray)super.clone();
            vArray.count = this.count;
            vArray.maxCapacityIncrement = this.maxCapacityIncrement;
            vArray.array = this.newArray(this.count);
            System.arraycopy(this.array, 0, vArray.array, 0, this.count);
            return vArray;
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public String toString() {
        final Class componentType = this.getComponentType();
        final int n = this.length() - 1;
        final StringBuffer sb = new StringBuffer();
        sb.append("[");
        if (componentType.equals(Boolean.TYPE)) {
            for (int i = 0; i <= n; ++i) {
                sb.append(this.getInt(i));
                if (i < n) {
                    sb.append(", ");
                }
            }
        }
        else if (componentType.equals(Byte.TYPE)) {
            for (int j = 0; j <= n; ++j) {
                sb.append(this.getByte(j));
                if (j < n) {
                    sb.append(", ");
                }
            }
        }
        else if (componentType.equals(Character.TYPE)) {
            for (int k = 0; k <= n; ++k) {
                sb.append(this.getChar(k));
                if (k < n) {
                    sb.append(", ");
                }
            }
        }
        else if (componentType.equals(Short.TYPE)) {
            for (int l = 0; l <= n; ++l) {
                sb.append(this.getShort(l));
                if (l < n) {
                    sb.append(", ");
                }
            }
        }
        else if (componentType.equals(Integer.TYPE)) {
            for (int n2 = 0; n2 <= n; ++n2) {
                sb.append(this.getInt(n2));
                if (n2 < n) {
                    sb.append(", ");
                }
            }
        }
        else if (componentType.equals(Long.TYPE)) {
            for (int n3 = 0; n3 <= n; ++n3) {
                sb.append(this.getLong(n3));
                if (n3 < n) {
                    sb.append(", ");
                }
            }
        }
        else if (componentType.equals(Float.TYPE)) {
            for (int n4 = 0; n4 <= n; ++n4) {
                sb.append(this.getFloat(n4));
                if (n4 < n) {
                    sb.append(", ");
                }
            }
        }
        else if (componentType.equals(Double.TYPE)) {
            for (int n5 = 0; n5 <= n; ++n5) {
                sb.append(this.getDouble(n5));
                if (n5 < n) {
                    sb.append(", ");
                }
            }
        }
        else {
            for (int n6 = 0; n6 <= n; ++n6) {
                sb.append(this.get(n6));
                if (n6 < n) {
                    sb.append(", ");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    protected final int capacity() {
        return Array.getLength(this.array);
    }
    
    protected final void ensureCapacity(final int n) {
        final int capacity = this.capacity();
        if (n > capacity) {
            int maxCapacityIncrement = capacity;
            if (this.maxCapacityIncrement > 0 && maxCapacityIncrement > this.maxCapacityIncrement) {
                maxCapacityIncrement = this.maxCapacityIncrement;
            }
            int n2 = capacity + maxCapacityIncrement;
            if (n > n2) {
                n2 = n;
            }
            final Object array = this.newArray(n2);
            System.arraycopy(this.array, 0, array, 0, this.count);
            this.array = array;
        }
    }
    
    protected final void shrinkCapacity() {
        final int capacity = this.capacity();
        if (capacity < 32) {
            return;
        }
        final int n = capacity / 4;
        if (this.count < n) {
            final Object array = this.newArray(n);
            System.arraycopy(this.array, 0, array, 0, this.count);
            this.array = array;
        }
    }
    
    protected final Object newArray(final int n) {
        return Array.newInstance(this.getComponentType(), n);
    }
    
    protected boolean isObjectType(final Class clazz) {
        return clazz != Boolean.TYPE && clazz != Byte.TYPE && clazz != Character.TYPE && clazz != Short.TYPE && clazz != Integer.TYPE && clazz != Long.TYPE && clazz != Float.TYPE && clazz != Double.TYPE;
    }
    
    public VArray(final Class clazz) {
        this(clazz, 16);
    }
    
    public VArray(final Class clazz, final int n) {
        this(clazz, n, 4096);
    }
    
    public VArray(final Class clazz, final int n, final int maxCapacityIncrement) {
        this.componentIsObject = false;
        if (clazz == null) {
            throw new NullPointerException();
        }
        this.array = Array.newInstance(clazz, n);
        this.count = 0;
        this.maxCapacityIncrement = maxCapacityIncrement;
        this.componentIsObject = this.isObjectType(clazz);
    }
    
    public VArray(final Object o) {
        this(o, 4096);
    }
    
    public VArray(final Object array, final int maxCapacityIncrement) {
        this.componentIsObject = false;
        if (array == null) {
            throw new NullPointerException();
        }
        this.array = array;
        this.count = Array.getLength(array);
        this.maxCapacityIncrement = maxCapacityIncrement;
        this.componentIsObject = this.isObjectType(array.getClass().getComponentType());
    }
    
    public VArray(final String s) {
        this(s, 65536);
    }
    
    public VArray(final String s, final int maxCapacityIncrement) {
        this.componentIsObject = false;
        if (s == null) {
            throw new NullPointerException();
        }
        this.count = s.length();
        this.array = new char[this.count];
        s.getChars(0, this.count, (char[])this.array, 0);
        this.maxCapacityIncrement = maxCapacityIncrement;
        this.componentIsObject = false;
    }
}
