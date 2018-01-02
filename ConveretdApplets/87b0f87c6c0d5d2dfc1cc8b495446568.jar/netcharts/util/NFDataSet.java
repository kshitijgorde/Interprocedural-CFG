// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.Vector;

public class NFDataSet implements NFCompare
{
    public static final int UndefinedDimensions = 0;
    public static final int OneDimensional = 1;
    public static final int TwoDimensional = 2;
    public static final int ThreeDimensional = 3;
    public static final int XValue = 1;
    public static final int YValue = 2;
    public static final int ZValue = 3;
    public static final int FixedSize = 0;
    public static final int Variable = 1;
    protected int sizeType;
    private boolean a;
    private int b;
    private Vector c;
    private int d;
    private int e;
    private int f;
    private int g;
    
    public NFDataSet() {
        this.a = false;
        this.b = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.c = new Vector();
        this.sizeType = 1;
    }
    
    public NFDataSet(final int f) {
        this.a = false;
        this.b = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.c = new Vector();
        this.sizeType = 0;
        this.f = f;
        for (int i = 0; i < f; ++i) {
            this.c.addElement(new NFDataPoint());
        }
    }
    
    public int getType() {
        return this.b;
    }
    
    public void setType(final int b) {
        this.b = b;
    }
    
    public void addPoint(final double n) {
        if (this.b != 0 && this.b != 1) {
            throw new IllegalArgumentException("This data set is not one dimensional");
        }
        this.b = 1;
        this.a(n, 0.0, 0.0, false);
    }
    
    public void addPoint(final double n, final double n2) {
        if (this.b != 0 && this.b != 2) {
            throw new IllegalArgumentException("This data set is not two dimensional");
        }
        this.b = 2;
        this.a(n, n2, 0.0, false);
    }
    
    public void addPoint(final double n, final double n2, final double n3) {
        if (this.b != 0 && this.b != 3) {
            throw new IllegalArgumentException("This data set is not three dimensional");
        }
        this.b = 3;
        this.a(n, n2, n3, false);
    }
    
    public void addNull() {
        this.a(Double.NaN, Double.NaN, Double.NaN, true);
    }
    
    private void a(final double x, final double y, final double z, final boolean isNull) {
        final NFDataPoint nfDataPoint = new NFDataPoint();
        nfDataPoint.x = x;
        nfDataPoint.y = y;
        nfDataPoint.z = z;
        nfDataPoint.isNull = isNull;
        this.addDataPoint(nfDataPoint);
    }
    
    public void addDataPoint(final NFDataPoint nfDataPoint) {
        if (this.sizeType == 1) {
            if (this.a) {
                NFDebug.print("Add to variable size array");
            }
            this.c.addElement(nfDataPoint);
            return;
        }
        if (this.e >= this.f) {
            this.e = 0;
        }
        if (this.d >= this.f) {
            this.d = 0;
        }
        final NFDataPoint nfDataPoint2 = this.c.elementAt(this.e);
        nfDataPoint2.x = nfDataPoint.x;
        nfDataPoint2.y = nfDataPoint.y;
        nfDataPoint2.z = nfDataPoint.z;
        nfDataPoint2.isNull = nfDataPoint.isNull;
        ++this.e;
        if (this.g >= this.f) {
            ++this.d;
        }
        ++this.g;
        if (this.g > this.f) {
            this.g = this.f;
        }
    }
    
    public void setLabel(final int n, final String activeLabel) {
        if (n >= this.c.size()) {
            throw new IllegalArgumentException("Not enough points in data set");
        }
        if (this.sizeType != 1) {
            return;
        }
        this.c.elementAt(n).activeLabel = activeLabel;
    }
    
    public double getNth(final int n) {
        return this.getNth(n, 1);
    }
    
    public double getNth(final int n, final int n2) {
        if (n < 0) {
            return Double.NaN;
        }
        final NFDataPoint point = this.getPoint(n);
        if (point == null) {
            return 0.0;
        }
        double n3 = 0.0;
        switch (n2) {
            case 2: {
                n3 = point.y;
                break;
            }
            case 3: {
                n3 = point.z;
                break;
            }
            default: {
                n3 = point.x;
                break;
            }
        }
        return n3;
    }
    
    public void deleteNth(final int n) throws IllegalArgumentException {
        if (this.sizeType != 0) {
            try {
                this.c.removeElementAt(n);
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                throw new IllegalArgumentException("Index Out Of Bounds");
            }
            return;
        }
        final NFDataPoint point = this.getPoint(n);
        if (point == null) {
            return;
        }
        point.setNull();
    }
    
    public double shift() throws Exception {
        return this.shift(1);
    }
    
    public double shift(final int n) throws Exception {
        NFDataPoint nfDataPoint = null;
        Label_0111: {
            if (this.sizeType == 1) {
                try {
                    nfDataPoint = this.c.elementAt(0);
                    this.c.removeElementAt(0);
                    break Label_0111;
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    throw new Exception("DataSet Empty: Can't shift");
                }
            }
            if (this.g <= 0) {
                throw new Exception("DataSet Empty: Can't shift");
            }
            nfDataPoint = this.c.elementAt(this.d);
            ++this.d;
            --this.g;
            if (this.d >= this.f) {
                this.d = 0;
            }
        }
        if (nfDataPoint == null) {
            return 0.0;
        }
        double n2 = 0.0;
        switch (n) {
            case 2: {
                n2 = nfDataPoint.y;
                break;
            }
            case 3: {
                n2 = nfDataPoint.z;
                break;
            }
            default: {
                n2 = nfDataPoint.x;
                break;
            }
        }
        if (this.sizeType == 0) {
            nfDataPoint.setNull();
        }
        return n2;
    }
    
    public double[] getDoubleArray() {
        return this.getDoubleArray(1);
    }
    
    public double[] getDoubleArray(final int n) {
        final int size = this.size();
        final double[] array = new double[size];
        for (int i = 0; i < size; ++i) {
            array[i] = this.getNth(i, n);
        }
        return array;
    }
    
    public int[] getIntegerArray() {
        return this.getIntegerArray(1);
    }
    
    public int[] getIntegerArray(final int n) {
        final int size = this.size();
        final int[] array = new int[size];
        for (int i = 0; i < size; ++i) {
            array[i] = (int)this.getNth(i, n);
        }
        return array;
    }
    
    public String[] getLabelArray() {
        final int size = this.size();
        final String[] array = new String[size];
        for (int i = 0; i < size; ++i) {
            array[i] = this.getLabel(i);
        }
        return array;
    }
    
    public int getIndex(final NFDataPoint nfDataPoint) {
        if (this.c == null) {
            return -1;
        }
        return this.c.indexOf(nfDataPoint);
    }
    
    public NFDataPoint getPoint(final int n) {
        int n2;
        if (this.sizeType == 1) {
            n2 = n;
            if (this.c.size() == 0) {
                return null;
            }
            if (n2 >= this.c.size()) {
                n2 = this.c.size() - 1;
            }
        }
        else {
            final int n3 = this.f - this.d;
            if (n > this.g) {
                n2 = this.e - 1;
                if (n2 < 0) {
                    n2 = this.f - 1;
                }
            }
            else if (n < n3) {
                n2 = n + this.d;
            }
            else {
                n2 = n - n3;
            }
        }
        return (NFDataPoint)this.c.elementAt(n2);
    }
    
    public String getLabel(final int n) {
        final NFDataPoint point = this.getPoint(n);
        if (point == null) {
            return "";
        }
        return point.activeLabel;
    }
    
    public double[] getMinMax(final int n) {
        final double[] array = { Double.MAX_VALUE, -1.7976931348623157E308 };
        for (int i = 0; i < this.c.size(); ++i) {
            final NFDataPoint nfDataPoint = this.c.elementAt(i);
            double n2 = 0.0;
            switch (n) {
                default: {
                    n2 = nfDataPoint.x;
                    break;
                }
                case 2: {
                    n2 = nfDataPoint.y;
                    break;
                }
                case 3: {
                    n2 = nfDataPoint.z;
                    break;
                }
            }
            if (!Double.isNaN(n2)) {
                if (n2 < array[0]) {
                    array[0] = n2;
                }
                if (n2 > array[1]) {
                    array[1] = n2;
                }
            }
        }
        return array;
    }
    
    public int size() {
        if (this.sizeType == 1) {
            return this.c.size();
        }
        return this.g;
    }
    
    public int compare(final Object o, final Object o2) {
        final double x = ((NFDataPoint)o).x;
        final double x2 = ((NFDataPoint)o2).x;
        if (x < x2) {
            return -1;
        }
        if (x > x2) {
            return 1;
        }
        return 0;
    }
    
    public void sort() {
        if (this.b != 1) {
            throw new IllegalArgumentException("This data set is not one dimensional");
        }
        NFUtil.qsort(this.c, 0, this.c.size() - 1, this);
    }
    
    public void clear() {
        if (this.sizeType == 1) {
            this.c = new Vector();
        }
        else {
            this.g = 0;
            this.d = 0;
            this.e = 0;
        }
    }
    
    public static NFDataSet createCopy(final NFDataSet set) {
        if (set == null) {
            return null;
        }
        final NFDataSet set2 = new NFDataSet();
        set2.sizeType = set.sizeType;
        set2.a = set.a;
        set2.b = set.b;
        set2.c = ((set.c == null) ? null : ((Vector)set.c.clone()));
        set2.d = set.d;
        set2.e = set.e;
        set2.f = set.f;
        set2.g = set.g;
        return set2;
    }
}
