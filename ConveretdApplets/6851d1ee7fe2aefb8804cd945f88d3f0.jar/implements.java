import java.util.Observable;
import java.awt.Frame;
import java.util.Observer;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class implements implements Observer
{
    protected int[] Ua;
    protected int[] r;
    protected int u;
    protected double[][] t;
    protected byte[] z;
    protected class s;
    protected String name;
    protected double[] A;
    protected boolean y;
    
    public implements(final String name, final int u, final int[] array, final double[] array2, final class s) {
        this.name = name;
        if (array != null) {
            this.Ua = new int[array.length];
            for (int i = 0; i < array.length; ++i) {
                this.Ua[i] = array[i];
            }
        }
        if (array2 != null) {
            this.A = new double[array2.length];
            for (int j = 0; j < array2.length; ++j) {
                this.A[j] = array2[j];
            }
        }
        this.u = u;
        this.r = new int[u];
        this.s = s;
        this.y = true;
        this.s.addObserver(this);
    }
    
    public boolean i() {
        return false;
    }
    
    public boolean b(final Frame frame, final o o) {
        return true;
    }
    
    public void update(final Observable observable, final Object o) {
        this.y = true;
        this.t = null;
    }
    
    public int C() {
        if (this.Ua == null) {
            return 0;
        }
        return this.Ua.length;
    }
    
    public int m() {
        return this.u;
    }
    
    public int _(final int n) {
        return this.r[n];
    }
    
    public double[] b(final int n) {
        if (this.y) {
            this.X();
        }
        this.y = false;
        if (this.t == null) {
            return null;
        }
        return this.t[n];
    }
    
    public byte[] _() {
        if (this.y) {
            this.X();
        }
        this.y = false;
        if (this.t == null || this.z == null) {
            return null;
        }
        return this.z;
    }
    
    public int getParam(final int n) {
        if (this.Ua != null && n >= 0 && n < this.Ua.length) {
            return this.Ua[n];
        }
        return 0;
    }
    
    public String k() {
        if (this.Ua == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.Ua.length; ++i) {
            sb.append(this.Ua[i]);
            if (i < this.Ua.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
    
    public void a(final int n, final int n2) {
        if (this.Ua == null) {
            return;
        }
        this.Ua[n] = n2;
        this.W();
        this.y = true;
    }
    
    public void k(final String s) {
        if (this.Ua == null || s == null) {
            return;
        }
        final u u = new u(",");
        u.m(s);
        if (u.a() == 0) {
            return;
        }
        final int[] array = new int[u.a()];
        boolean b = false;
        try {
            for (int i = 0; i < array.length; ++i) {
                array[i] = Integer.parseInt(u.b(i));
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
            this.W();
            this.y = true;
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
    
    public String I() {
        return this.name;
    }
    
    public int n() {
        if (this.A == null) {
            return 0;
        }
        return this.A.length;
    }
    
    public double _(final int n) {
        if (this.A == null) {
            return 0.0;
        }
        return this.A[n];
    }
    
    protected abstract void X();
    
    protected abstract void W();
}
