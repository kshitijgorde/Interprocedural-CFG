// 
// Decompiled by Procyon v0.5.30
// 

package com.kaon.meson;

public class FastHashtable implements Cloneable
{
    private int a;
    private Object[][] b;
    private int c;
    public boolean d;
    public StringPair[] e;
    
    public FastHashtable(final int a) {
        this.a = a;
        this.b = new Object[a][];
    }
    
    public Object clone() {
        final FastHashtable fastHashtable = new FastHashtable(this.a);
        for (int i = 0; i < this.a; ++i) {
            final Object[] array = this.b[i];
            if (array != null) {
                fastHashtable.b[i] = array.clone();
            }
        }
        fastHashtable.c = this.c;
        return fastHashtable;
    }
    
    public void clear() {
        this.c = 0;
        for (int i = 0; i < this.b.length; ++i) {
            final Object[] array = this.b[i];
            if (array != null) {
                if (this.d) {
                    for (int j = 0; j < array.length; j += 2) {
                        if (array[j] instanceof StringPair) {
                            ((StringPair)array[j]).d = null;
                        }
                        array[j + 1] = (array[j] = null);
                    }
                }
                else {
                    for (int k = 0; k < array.length; ++k) {
                        array[k] = null;
                    }
                }
            }
        }
    }
    
    public int size() {
        return this.c;
    }
    
    public long iterator() {
        return this.advance(-1L);
    }
    
    public long advance(final long n) {
        int i = (int)(n & -1L);
        if (i != -1) {
            int n2 = (int)(n >>> 32);
            final Object[] array = this.b[i];
            n2 += 2;
            if (n2 < array.length && array[n2] != null) {
                return n2 << 32 | i;
            }
        }
        ++i;
        while (i < this.a) {
            final Object[] array2 = this.b[i];
            if (array2 != null && array2[0] != null) {
                return i;
            }
            ++i;
        }
        return -1L;
    }
    
    public String getKey(final long n) {
        return this.b[(int)(n & -1L)][(int)(n >>> 32)].toString();
    }
    
    public Object getValue(final long n) {
        return this.b[(int)(n & -1L)][(int)(n >>> 32) + 1];
    }
    
    public Object getKey_(final long n) {
        return this.b[(int)(n & -1L)][(int)(n >>> 32)];
    }
    
    public void put_(final Object o, final Object o2) {
        if (o instanceof StringPair) {
            this.put((StringPair)o, o2);
        }
        this.put(o.toString(), o2);
    }
    
    public Object get_(final Object o) {
        if (o instanceof StringPair) {
            return this.get((StringPair)o);
        }
        return this.get(o.toString());
    }
    
    public void put(final String s, final Object o) {
        final int n = (Integer.MAX_VALUE & s.hashCode()) % this.a;
        final Object[] array = this.b[n];
        if (array == null) {
            final Object[] array2 = this.b[n] = new Object[2];
            array2[0] = s;
            array2[1] = o;
            ++this.c;
            return;
        }
        for (int i = 0; i < array.length; i += 2) {
            final Object o2 = array[i];
            if (o2 == null) {
                array[i] = s;
                array[i + 1] = o;
                ++this.c;
                return;
            }
            if (o2 == s || o2.equals(s)) {
                if (this.d && o2 instanceof StringPair) {
                    ((StringPair)o2).d = null;
                }
                array[i + 1] = o;
                return;
            }
        }
        final Object[] array3 = new Object[array.length + 2];
        int length = array.length;
        System.arraycopy(array, 0, array3, 0, length);
        array3[length++] = s;
        array3[length++] = o;
        ++this.c;
        this.b[n] = array3;
    }
    
    public void put(final StringPair stringPair, final Object d) {
        final int n = (Integer.MAX_VALUE & stringPair.hashCode()) % this.a;
        final Object[] array = this.b[n];
        if (array == null) {
            final Object[] array2 = this.b[n] = new Object[2];
            array2[0] = stringPair;
            array2[1] = d;
            if (this.d) {
                stringPair.d = d;
            }
            ++this.c;
            return;
        }
        for (int i = 0; i < array.length; i += 2) {
            final Object o = array[i];
            if (o == null) {
                array[i] = stringPair;
                array[i + 1] = d;
                ++this.c;
                if (this.d) {
                    stringPair.d = d;
                }
                return;
            }
            if (o == stringPair || stringPair.equals(o)) {
                array[i + 1] = d;
                if (this.d) {
                    if (o == stringPair) {
                        stringPair.d = d;
                    }
                    else if (o instanceof StringPair) {
                        ((StringPair)o).d = d;
                    }
                }
                return;
            }
        }
        final Object[] array3 = new Object[array.length + 2];
        int length = array.length;
        System.arraycopy(array, 0, array3, 0, length);
        array3[length++] = stringPair;
        array3[length++] = d;
        ++this.c;
        if (this.d) {
            stringPair.d = d;
        }
        this.b[n] = array3;
    }
    
    public Object get(final String s) {
        final Object[] array = this.b[(Integer.MAX_VALUE & s.hashCode()) % this.a];
        if (array == null) {
            return null;
        }
        for (int i = 0; i < array.length; i += 2) {
            final Object o = array[i];
            if (o == null) {
                return null;
            }
            if (o == s || o.equals(s)) {
                return array[i + 1];
            }
        }
        return null;
    }
    
    public Object get(final StringPair stringPair) {
        if (this.d && stringPair.d != null) {
            return stringPair.d;
        }
        final Object[] array = this.b[(Integer.MAX_VALUE & stringPair.hashCode()) % this.a];
        if (array == null) {
            return null;
        }
        for (int i = 0; i < array.length; i += 2) {
            final Object o = array[i];
            if (o == null) {
                return null;
            }
            if (o == stringPair || stringPair.equals(o)) {
                return array[i + 1];
            }
        }
        return null;
    }
    
    public Object get(final String s, final String s2) {
        final Object[] array = this.b[(Integer.MAX_VALUE & StringPair.hashCode(s, s2)) % this.a];
        if (array == null) {
            return null;
        }
        for (int i = 0; i < array.length; i += 2) {
            final Object o = array[i];
            if (o == null) {
                return null;
            }
            if (o instanceof StringPair) {
                if (((StringPair)o).equals(s, s2)) {
                    return array[i + 1];
                }
            }
            else {
                final String s3 = (String)o;
                if (s.length() + s2.length() == s3.length() && s3.startsWith(s) && s3.endsWith(s2)) {
                    return array[i + 1];
                }
            }
        }
        return null;
    }
    
    public StringPair newStringPair(final String s, final String s2) {
        final int hashCode = StringPair.hashCode(s, s2);
        if (this.e == null) {
            this.e = new StringPair[512];
        }
        final StringPair stringPair = this.e[hashCode & 0x1FF];
        if (stringPair != null && stringPair.equals(s, s2)) {
            return stringPair;
        }
        final Object[] array = this.b[(Integer.MAX_VALUE & hashCode) % this.a];
        if (array != null) {
            for (int i = 0; i < array.length; i += 2) {
                final Object o = array[i];
                if (o == null) {
                    break;
                }
                if (o instanceof StringPair) {
                    final StringPair stringPair2 = (StringPair)o;
                    if (stringPair2.equals(s, s2)) {
                        return stringPair2;
                    }
                }
            }
        }
        return this.e[hashCode & 0x1FF] = new StringPair(s, s2, hashCode);
    }
    
    public void remove(final String s) {
        final Object[] array = this.b[(Integer.MAX_VALUE & s.hashCode()) % this.a];
        if (array == null) {
            return;
        }
        int n = 0;
        for (int i = 0; i < array.length; i += 2) {
            final Object o = array[i];
            if (o == null) {
                break;
            }
            if (o.equals(s)) {
                --this.c;
                if (this.d && o instanceof StringPair) {
                    ((StringPair)o).d = null;
                }
            }
            else {
                if (n != i) {
                    array[n] = o;
                    array[n + 1] = array[i + 1];
                }
                n += 2;
            }
        }
        if (n < array.length) {
            array[n] = (array[n + 1] = null);
        }
    }
}
