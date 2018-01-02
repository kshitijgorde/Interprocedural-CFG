import java.util.Observable;
import java.util.Observer;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class o implements Observer
{
    protected int[] DDb;
    protected int[] qIb;
    protected int tIb;
    protected double[][] sIb;
    protected byte[] uIb;
    protected a rIb;
    protected String name;
    protected double[] vIb;
    protected boolean wIb;
    
    public o(final String name, final int tIb, final int[] array, final double[] array2, final a rIb) {
        this.name = name;
        if (array != null) {
            this.DDb = new int[array.length];
            for (int i = 0; i < array.length; ++i) {
                this.DDb[i] = array[i];
            }
        }
        if (array2 != null) {
            this.vIb = new double[array2.length];
            for (int j = 0; j < array2.length; ++j) {
                this.vIb[j] = array2[j];
            }
        }
        this.tIb = tIb;
        this.qIb = new int[tIb];
        this.rIb = rIb;
        this.wIb = true;
        this.rIb.addObserver(this);
    }
    
    public void update(final Observable observable, final Object o) {
        this.wIb = true;
        this.sIb = null;
    }
    
    public int S() {
        if (this.DDb == null) {
            return 0;
        }
        return this.DDb.length;
    }
    
    public int J() {
        return this.tIb;
    }
    
    public int _(final int n) {
        return this.qIb[n];
    }
    
    public double[] _(final int n) {
        if (this.wIb) {
            this.D();
        }
        this.wIb = false;
        if (this.sIb == null) {
            return null;
        }
        return this.sIb[n];
    }
    
    public byte[] _() {
        if (this.wIb) {
            this.D();
        }
        this.wIb = false;
        if (this.sIb == null || this.uIb == null) {
            return null;
        }
        return this.uIb;
    }
    
    public int getParam(final int n) {
        if (this.DDb != null && n >= 0 && n < this.DDb.length) {
            return this.DDb[n];
        }
        return 0;
    }
    
    public String g() {
        if (this.DDb == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.DDb.length; ++i) {
            sb.append(this.DDb[i]);
            if (i < this.DDb.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
    
    public void j(final int n, final int n2) {
        if (this.DDb == null) {
            return;
        }
        this.DDb[n] = n2;
        this.C();
        this.wIb = true;
    }
    
    public void B(final String s) {
        if (this.DDb == null || s == null) {
            return;
        }
        final d d = new d(",");
        d.e(s);
        if (d.z() == 0) {
            return;
        }
        final int[] array = new int[d.z()];
        boolean b = false;
        try {
            for (int i = 0; i < array.length; ++i) {
                array[i] = Integer.parseInt(d._(i));
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
                this.j(j, array[j]);
            }
            this.C();
            this.wIb = true;
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
    
    public String l() {
        return this.name;
    }
    
    public int K() {
        if (this.vIb == null) {
            return 0;
        }
        return this.vIb.length;
    }
    
    public double f(final int n) {
        if (this.vIb == null) {
            return 0.0;
        }
        return this.vIb[n];
    }
    
    protected abstract void D();
    
    protected abstract void C();
}
