import java.util.Observable;
import java.util.Observer;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class public implements Observer
{
    protected int[] Oa;
    protected int[] Uoa;
    protected int Xoa;
    protected double[][] Woa;
    protected byte[] Yoa;
    protected b Voa;
    protected String name;
    protected double[] Zoa;
    protected boolean _pa;
    
    public public(final String name, final int xoa, final int[] array, final double[] array2, final b voa) {
        this.name = name;
        if (array != null) {
            this.Oa = new int[array.length];
            for (int i = 0; i < array.length; ++i) {
                this.Oa[i] = array[i];
            }
        }
        if (array2 != null) {
            this.Zoa = new double[array2.length];
            for (int j = 0; j < array2.length; ++j) {
                this.Zoa[j] = array2[j];
            }
        }
        this.Xoa = xoa;
        this.Uoa = new int[xoa];
        this.Voa = voa;
        this._pa = true;
        this.Voa.addObserver(this);
    }
    
    public void update(final Observable observable, final Object o) {
        this._pa = true;
        this.Woa = null;
    }
    
    public int L() {
        if (this.Oa == null) {
            return 0;
        }
        return this.Oa.length;
    }
    
    public int I() {
        return this.Xoa;
    }
    
    public int b(final int n) {
        return this.Uoa[n];
    }
    
    public double[] a(final int n) {
        if (this._pa) {
            this.H();
        }
        this._pa = false;
        if (this.Woa == null) {
            return null;
        }
        return this.Woa[n];
    }
    
    public byte[] b() {
        if (this._pa) {
            this.H();
        }
        this._pa = false;
        if (this.Woa == null || this.Yoa == null) {
            return null;
        }
        return this.Yoa;
    }
    
    public int getParam(final int n) {
        if (this.Oa != null && n >= 0 && n < this.Oa.length) {
            return this.Oa[n];
        }
        return 0;
    }
    
    public String h() {
        if (this.Oa == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.Oa.length; ++i) {
            sb.append(this.Oa[i]);
            if (i < this.Oa.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
    
    public void a(final int n, final int n2) {
        if (this.Oa == null) {
            return;
        }
        this.Oa[n] = n2;
        this.G();
        this._pa = true;
    }
    
    public void r(final String s) {
        if (this.Oa == null || s == null) {
            return;
        }
        final try try1 = new try(",");
        try1.l(s);
        if (try1.g() == 0) {
            return;
        }
        final int[] array = new int[try1.g()];
        boolean b = false;
        try {
            for (int i = 0; i < array.length; ++i) {
                array[i] = Integer.parseInt(try1.a(i));
                if (array[i] < 2 || array[i] > 999) {
                    b = true;
                    break;
                }
            }
        }
        catch (Exception ex) {
            b = true;
        }
        if (!b) {
            for (int j = 0; j < array.length; ++j) {
                this.a(j, array[j]);
            }
            this.G();
            this._pa = true;
        }
    }
    
    public String toString() {
        if (this.name == null) {
            return null;
        }
        final int index = this.name.indexOf(45);
        if (index == -1) {
            return this.name;
        }
        return this.name.substring(0, index).trim();
    }
    
    public String c() {
        return this.name;
    }
    
    public int J() {
        if (this.Zoa == null) {
            return 0;
        }
        return this.Zoa.length;
    }
    
    public double b(final int n) {
        if (this.Zoa == null) {
            return 0.0;
        }
        return this.Zoa[n];
    }
    
    protected abstract void H();
    
    protected abstract void G();
}
