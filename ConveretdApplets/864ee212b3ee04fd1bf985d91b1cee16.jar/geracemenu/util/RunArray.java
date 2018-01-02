// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.util;

import java.util.Enumeration;
import java.io.Serializable;

public class RunArray implements Cloneable, Serializable
{
    private static /* synthetic */ Class class$Ljava$lang$Object;
    protected VArray runs;
    protected VArray values;
    protected int count;
    protected int cacheRunIndex;
    protected int cacheRunStart;
    
    public final int size() {
        return this.count;
    }
    
    public final int length() {
        return this.count;
    }
    
    public final boolean isEmpty() {
        return this.count == 0;
    }
    
    public final Enumeration elements() {
        return new RunArrayEnumerator(this);
    }
    
    public final Enumeration elements(final int n, final int n2) {
        if (n < 0 || n2 > this.count || n > n2) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return new RunArrayEnumerator(this, n, n2);
    }
    
    public final Class getComponentType() {
        return this.values.getComponentType();
    }
    
    public final int getValueCount() {
        return this.values.count;
    }
    
    public final Object[] getValues() {
        return (Object[])this.values.getTrimmedArray();
    }
    
    public final Object[] getValues(final int n, final int n2) {
        if (n < 0 || n2 > this.count || n > n2) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (n == n2) {
            return (Object[])this.values.subarray(0, 0).getTrimmedArray();
        }
        return (Object[])this.values.subarray(this.getRunAndOffset(n)[0], this.getRunAndOffset(n2 - 1)[0] + 1).getTrimmedArray();
    }
    
    public final Object get(final int n) {
        if (n < 0 || n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        if (this.values.count == 1) {
            return this.values.get(0);
        }
        return this.values.get(this.getRunAndOffset(n)[0]);
    }
    
    public final int getRunLengthAt(final int n) {
        if (n < 0 || n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        if (this.runs.count == 1) {
            return this.runs.getInt(0) - n;
        }
        final int[] runAndOffset = this.getRunAndOffset(n);
        return this.runs.getInt(runAndOffset[0]) - runAndOffset[1];
    }
    
    public final int getRunOffsetAt(final int n) {
        if (n < 0 || n >= this.count) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        if (this.runs.count == 1) {
            return n;
        }
        return this.getRunAndOffset(n)[1];
    }
    
    public int hashCode() {
        return this.count ^ this.runs.hashCode() ^ this.values.hashCode();
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o instanceof RunArray) {
            final RunArray runArray = (RunArray)o;
            if (this.count == runArray.count && this.runs.equals(runArray.runs) && this.values.equals(runArray.values)) {
                return true;
            }
        }
        return false;
    }
    
    public final void removeAll() {
        this.runs.removeAll();
        this.values.removeAll();
        this.count = 0;
        this.cacheRunIndex = 0;
        this.cacheRunStart = 0;
    }
    
    public final void remove(final int n, final int n2) {
        if (n < 0 || n + n2 > this.count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (n2 <= 0) {
            return;
        }
        if (this.count == 0) {
            return;
        }
        this.replace(n, n + n2, new RunArray());
    }
    
    public final RunArray subarray(final int n) {
        return this.subarray(n, this.count);
    }
    
    public final RunArray subarray(final int n, final int n2) {
        if (n < 0 || n2 > this.count || n > n2) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (n == n2) {
            return new RunArray();
        }
        final int[] runAndOffset = this.getRunAndOffset(n);
        final int n3 = runAndOffset[0];
        final int n4 = runAndOffset[1];
        final int[] runAndOffset2 = this.getRunAndOffset(n2 - 1);
        final int n5 = runAndOffset2[0];
        final int n6 = runAndOffset2[1];
        final VArray subarray = this.runs.subarray(n3, n5 + 1);
        final VArray subarray2 = this.values.subarray(n3, n5 + 1);
        if (n3 == n5) {
            subarray.setInt(0, n6 - n4 + 1);
        }
        else {
            subarray.setInt(0, this.runs.getInt(n3) - n4);
            subarray.setInt(n5 - n3, n6 + 1);
        }
        return new RunArray(subarray, subarray2, n2 - n);
    }
    
    public final RunArray append(final RunArray runArray) {
        if (runArray.count == 0) {
            return this;
        }
        if (this.count == 0) {
            this.copyFrom(runArray);
            return this;
        }
        final int n = this.runs.count - 1;
        this.runs.append(runArray.runs);
        this.values.append(runArray.values);
        this.count += runArray.count;
        return this;
    }
    
    public final RunArray append(final Object o) {
        final int n = this.runs.count - 1;
        if (n >= 0 && this.values.get(n).equals(o)) {
            this.runs.setInt(n, this.runs.getInt(n) + 1);
        }
        else {
            this.runs.append(1);
            this.values.append(o);
        }
        ++this.count;
        return this;
    }
    
    public final RunArray append(final int n, final Object o) {
        final int n2 = this.runs.count - 1;
        if (n2 >= 0) {
            this.runs.setInt(n2, this.runs.getInt(n2) + n);
        }
        else {
            this.runs.append(n);
            this.values.append(o);
        }
        this.count += n;
        return this;
    }
    
    public final RunArray insert(final int n, final RunArray runArray) {
        if (n < 0 || n > this.count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.replace(n, n, runArray);
    }
    
    public final RunArray replace(final int n, final int n2, final RunArray runArray) {
        if (n < 0 || n2 > this.count || n > n2) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (n == n2 && runArray.count == 0) {
            return this;
        }
        if ((n == 0 && n2 == this.count) || this.count == 0) {
            this.copyFrom(runArray);
            return this;
        }
        int n3 = -1;
        int n4 = 0;
        int n5 = -1;
        int n6 = 0;
        int cacheRunIndex = 0;
        int cacheRunStart = 0;
        if (n > 0) {
            final int[] runAndOffset = this.getRunAndOffset(n - 1);
            cacheRunIndex = this.cacheRunIndex;
            cacheRunStart = this.cacheRunStart;
            n3 = runAndOffset[0];
            n4 = runAndOffset[1];
        }
        if (n2 < this.count) {
            final int[] runAndOffset2 = this.getRunAndOffset(n2);
            n5 = runAndOffset2[0];
            n6 = runAndOffset2[1];
        }
        this.replace(n3, n4, n5, n6, runArray.runs, runArray.values);
        this.count += runArray.count - (n2 - n);
        this.cacheRunIndex = cacheRunIndex;
        this.cacheRunStart = cacheRunStart;
        return this;
    }
    
    public String toString() {
        final int n = this.count - 1;
        final StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i <= n; ++i) {
            sb.append(this.get(i));
            if (i < n) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public Object clone() {
        try {
            final RunArray runArray = (RunArray)super.clone();
            runArray.runs = (VArray)this.runs.clone();
            runArray.values = (VArray)this.values.clone();
            runArray.count = this.count;
            runArray.cacheRunIndex = this.cacheRunIndex;
            runArray.cacheRunStart = this.cacheRunStart;
            return runArray;
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    protected final int[] getRunAndOffset(final int n) {
        int cacheRunIndex;
        int cacheRunStart;
        if (n >= this.cacheRunStart) {
            cacheRunIndex = this.cacheRunIndex;
            cacheRunStart = this.cacheRunStart;
        }
        else {
            cacheRunIndex = 0;
            cacheRunStart = 0;
        }
        final int n2 = this.runs.count - 1;
        int n3 = n - cacheRunStart;
        int n4;
        for (int[] array = (int[])this.runs.array; cacheRunIndex < n2 && (n4 = array[cacheRunIndex]) <= n3; n3 -= n4, ++cacheRunIndex, cacheRunStart += n4) {}
        this.cacheRunIndex = cacheRunIndex;
        this.cacheRunStart = cacheRunStart;
        return new int[] { cacheRunIndex, n3 };
    }
    
    protected final void replace(final int n, final int n2, final int n3, final int n4, final VArray vArray, final VArray vArray2) {
        if ((n < 0 && n3 < 0) || this.runs.count == 0) {
            this.runs.replace(0, this.runs.count, vArray);
            this.values.replace(0, this.values.count, vArray2);
            return;
        }
        final int count = this.runs.count;
        final int count2 = vArray.count;
        if (n < 0) {
            final int n5 = this.runs.getInt(n3) - n4;
            if (count2 == 0) {
                this.runs.setInt(n3, n5);
                if (n3 > 0) {
                    this.runs.remove(0, n3);
                    this.values.remove(0, n3);
                }
            }
            else if (vArray2.get(count2 - 1).equals(this.values.get(n3))) {
                this.runs.setInt(n3, n5 + vArray.getInt(count2 - 1));
                if (count2 == 1) {
                    if (n3 > 0) {
                        this.runs.remove(0, n3);
                        this.values.remove(0, n3);
                    }
                }
                else {
                    this.runs.replace(0, n3, vArray, 0, count2 - 1);
                    this.values.replace(0, n3, vArray2, 0, count2 - 1);
                }
            }
            else {
                this.runs.setInt(n3, n5);
                this.runs.replace(0, n3, vArray, 0, count2);
                this.values.replace(0, n3, vArray2, 0, count2);
            }
            return;
        }
        if (n3 < 0) {
            if (count2 == 0) {
                this.runs.setInt(n, n2 + 1);
                if (count > n + 1) {
                    this.runs.remove(n + 1, count - (n + 1));
                    this.values.remove(n + 1, count - (n + 1));
                }
            }
            else if (this.values.get(n).equals(vArray2.get(0))) {
                this.runs.setInt(n, n2 + 1 + vArray.getInt(0));
                if (count2 == 1) {
                    if (count > n + 1) {
                        this.runs.remove(n + 1, count - (n + 1));
                        this.values.remove(n + 1, count - (n + 1));
                    }
                }
                else {
                    this.runs.replace(n + 1, count, vArray, 1, count2);
                    this.values.replace(n + 1, count, vArray2, 1, count2);
                }
            }
            else {
                this.runs.setInt(n, n2 + 1);
                this.runs.replace(n + 1, count, vArray, 0, count2);
                this.values.replace(n + 1, count, vArray2, 0, count2);
            }
            return;
        }
        final int n6 = n2 + 1;
        final int n7 = this.runs.getInt(n3) - n4;
        if (count2 == 0) {
            if (n == n3) {
                this.runs.setInt(n, n6 + n7);
            }
            else if (n + 1 < n3 && this.values.get(n).equals(this.values.get(n3))) {
                this.runs.setInt(n, n6 + n7);
                this.runs.remove(n + 1, n3 - n);
                this.values.remove(n + 1, n3 - n);
            }
            else {
                this.runs.setInt(n, n6);
                this.runs.setInt(n3, n7);
                this.runs.remove(n + 1, n3 - (n + 1));
                this.values.remove(n + 1, n3 - (n + 1));
            }
            return;
        }
        if (n == n3) {
            final Object value = this.values.get(n);
            final boolean equals = value.equals(vArray2.get(0));
            final boolean b = (count2 == 1) ? equals : value.equals(vArray2.get(count2 - 1));
            if (equals) {
                if (b) {
                    if (count2 == 1) {
                        this.runs.setInt(n, n6 + n7 + vArray.getInt(0));
                    }
                    else {
                        this.runs.insertSpace(n + 1, count2 - 1);
                        this.values.insertSpace(n + 1, count2 - 1);
                        this.runs.setInt(n, n6 + vArray.getInt(0));
                        this.runs.setInt(n + count2 - 1, n7 + vArray.getInt(count2 - 1));
                        this.values.set(n + count2 - 1, value);
                        this.runs.replace(n + 1, n + count2 - 1, vArray, 1, count2 - 1);
                        this.values.replace(n + 1, n + count2 - 1, vArray2, 1, count2 - 1);
                    }
                }
                else {
                    this.runs.insertSpace(n + 1, count2);
                    this.values.insertSpace(n + 1, count2);
                    this.runs.setInt(n, n6 + vArray.getInt(0));
                    this.runs.setInt(n + count2, n7);
                    this.values.set(n + count2, value);
                    this.runs.replace(n + 1, n + count2, vArray, 1, count2);
                    this.values.replace(n + 1, n + count2, vArray2, 1, count2);
                }
            }
            else if (b) {
                this.runs.insertSpace(n + 1, count2);
                this.values.insertSpace(n + 1, count2);
                this.runs.setInt(n, n6);
                this.runs.setInt(n + count2, n7 + vArray.getInt(count2 - 1));
                this.values.set(n + count2, value);
                this.runs.replace(n + 1, n + count2, vArray, 0, count2 - 1);
                this.values.replace(n + 1, n + count2, vArray2, 0, count2 - 1);
            }
            else {
                this.runs.insertSpace(n + 1, count2 + 1);
                this.values.insertSpace(n + 1, count2 + 1);
                this.runs.setInt(n, n6);
                this.runs.setInt(n + count2 + 1, n7);
                this.values.set(n + count2 + 1, value);
                this.runs.replace(n + 1, n + count2 + 1, vArray, 0, count2);
                this.values.replace(n + 1, n + count2 + 1, vArray2, 0, count2);
            }
        }
        else {
            final boolean equals2 = this.values.get(n).equals(vArray2.get(0));
            final boolean equals3 = this.values.get(n3).equals(vArray2.get(count2 - 1));
            if (equals2) {
                if (equals3) {
                    if (count2 == 1) {
                        this.runs.setInt(n, n6 + n7 + vArray.getInt(0));
                        this.runs.remove(n + 1, n3 - n);
                        this.values.remove(n + 1, n3 - n);
                    }
                    else {
                        this.runs.setInt(n, n6 + vArray.getInt(0));
                        this.runs.setInt(n3, n7 + vArray.getInt(count2 - 1));
                        if (count2 > 2) {
                            this.runs.replace(n + 1, n3, vArray, 1, count2 - 1);
                            this.values.replace(n + 1, n3, vArray2, 1, count2 - 1);
                        }
                    }
                }
                else {
                    this.runs.setInt(n, n6 + vArray.getInt(0));
                    this.runs.setInt(n3, n7);
                    this.runs.replace(n + 1, n3, vArray, 1, count2);
                    this.values.replace(n + 1, n3, vArray2, 1, count2);
                }
            }
            else if (equals3) {
                this.runs.setInt(n, n6);
                this.runs.setInt(n3, n7 + vArray.getInt(count2 - 1));
                this.runs.replace(n + 1, n3, vArray, 0, count2 - 1);
                this.values.replace(n + 1, n3, vArray2, 0, count2 - 1);
            }
            else {
                this.runs.setInt(n, n6);
                this.runs.setInt(n3, n7);
                this.runs.replace(n + 1, n3, vArray, 0, count2);
                this.values.replace(n + 1, n3, vArray2, 0, count2);
            }
        }
    }
    
    protected final void copyFrom(final RunArray runArray) {
        this.runs.replace(0, this.runs.count, runArray.runs);
        this.values.replace(0, this.values.count, runArray.values);
        this.count = runArray.count;
        this.cacheRunIndex = runArray.cacheRunIndex;
        this.cacheRunStart = runArray.cacheRunStart;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public RunArray() {
        this((RunArray.class$Ljava$lang$Object != null) ? RunArray.class$Ljava$lang$Object : (RunArray.class$Ljava$lang$Object = class$("java.lang.Object")));
    }
    
    public RunArray(final Class clazz) {
        this.values = new VArray(clazz);
        if (!this.values.componentIsObject) {
            throw new IllegalArgumentException();
        }
        this.runs = new VArray(Integer.TYPE);
        this.count = 0;
        this.cacheRunIndex = 0;
        this.cacheRunStart = 0;
    }
    
    public RunArray(final int n, final Object o) {
        this(n, o, (RunArray.class$Ljava$lang$Object != null) ? RunArray.class$Ljava$lang$Object : (RunArray.class$Ljava$lang$Object = class$("java.lang.Object")));
    }
    
    public RunArray(final int count, final Object o, final Class clazz) {
        this(clazz);
        if (o == null) {
            throw new NullPointerException();
        }
        this.runs.append(count);
        this.values.append(o);
        this.count = count;
    }
    
    protected RunArray(final VArray runs, final VArray values, final int count) {
        if (runs == null || values == null) {
            throw new NullPointerException();
        }
        if (!values.componentIsObject) {
            throw new IllegalArgumentException();
        }
        this.runs = runs;
        this.values = values;
        this.count = count;
        this.cacheRunIndex = 0;
        this.cacheRunStart = 0;
    }
}
