import java.util.Observer;
import java.util.Vector;
import java.util.Enumeration;
import java.awt.Color;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class g
{
    private l KIb;
    private a rIb;
    private String RIb;
    private String SIb;
    private m TIb;
    private Hashtable UIb;
    private e VIb;
    private f WIb;
    private volatile yDb;
    private n XIb;
    private String YIb;
    private Object ZIb;
    private k _Jb;
    public h tGb;
    
    public void b(final n xIb) {
        this.XIb = xIb;
    }
    
    public double[] w() {
        if (this.XIb != null) {
            return this.XIb.t();
        }
        return new double[0];
    }
    
    public double[] x() {
        if (this.XIb != null) {
            return this.XIb.r();
        }
        return new double[0];
    }
    
    public double[] y() {
        if (this.XIb != null) {
            return this.XIb.s();
        }
        return new double[0];
    }
    
    public double[] z() {
        if (this.XIb != null) {
            return this.XIb.q();
        }
        return new double[0];
    }
    
    public double[] A() {
        if (this.XIb != null) {
            return this.XIb.v();
        }
        return new double[0];
    }
    
    public double[] B() {
        if (this.XIb != null) {
            return this.XIb.u();
        }
        return new double[0];
    }
    
    public int l(final int n) {
        if (this.XIb != null) {
            return this.XIb.getParam(n);
        }
        return 0;
    }
    
    public void _(final double[] array, final int n) {
        if (this.XIb != null) {
            this.XIb.b(array, n);
        }
    }
    
    public void b(final double n) {
        if (this.XIb != null) {
            this.XIb.a(n);
        }
    }
    
    public void ca(final int n) {
        if (this.XIb != null) {
            this.XIb.addBuySignal(n);
        }
    }
    
    public void da(final int n) {
        if (this.XIb != null) {
            this.XIb.addSellSignal(n);
        }
    }
    
    public void C(final String s) {
        if (this.XIb != null) {
            this.XIb.C(s);
        }
    }
    
    public Object _() {
        return this.ZIb;
    }
    
    public g(final String yIb, final Object zIb, final volatile yDb, final String s, final String s2, final int n, final String s3) {
        this.YIb = yIb;
        this.ZIb = zIb;
        this._Jb = new k(yIb, zIb);
        this.yDb = yDb;
        this.rIb = new a(s, s2, this, n);
        this.KIb = new l();
        this.TIb = new m();
        this.UIb = new Hashtable();
        this.RIb = null;
        this.SIb = null;
        this.VIb = new e();
        this.WIb = new f();
        this.tGb = new h();
        this.D(s3);
    }
    
    public volatile b() {
        return this.yDb;
    }
    
    public void a(final String s, final Color[] array) {
        this.VIb.b(s, array);
    }
    
    public void b(final e e) {
        this.VIb._(e);
    }
    
    public void a(final f f) {
        this.WIb.b(f);
    }
    
    public void b(final String s, final int[] array) {
        this.WIb.b(s, array);
    }
    
    public Color[] _(final String s) {
        return this.VIb._(s);
    }
    
    public int[] b(final String s) {
        return this.WIb.b(s);
    }
    
    public a _() {
        return this.rIb;
    }
    
    public String[] a() {
        return this.KIb.m();
    }
    
    public String[] l() {
        return this.TIb.m();
    }
    
    public void i(final String rIb) {
        this.RIb = rIb;
    }
    
    public void j(final String sIb) {
        this.SIb = sIb;
    }
    
    public void k(final String s) {
        this.UIb.put(s, s);
    }
    
    public void F() {
        this.UIb.clear();
    }
    
    public void l(final String s) {
        this.UIb.remove(s);
    }
    
    public o[] b() {
        if (this.UIb.size() == 0) {
            return null;
        }
        final o[] array = new o[this.UIb.size()];
        int n = 0;
        for (Enumeration keys = this.UIb.keys(); keys.hasMoreElements() && n < array.length; ++n) {
            array[n] = this.TIb._(keys.nextElement());
        }
        return array;
    }
    
    public o b() {
        return this.KIb._(this.RIb);
    }
    
    public o _() {
        return this.KIb._(this.SIb);
    }
    
    public o a(final String s) {
        return this.KIb._(s);
    }
    
    public o b(final String s) {
        return this.TIb._(s);
    }
    
    private void D(final String s) {
        this.KIb.b(new p(new int[] { 5, 5 }, this.rIb));
        this.KIb.b(new q(null, this.rIb));
        this.KIb.b(new r(new int[] { 14 }, this.rIb));
        this.KIb.b(new s(new int[] { 14 }, this.rIb));
        this.KIb.b(new t(new int[] { 14 }, this.rIb));
        this.KIb.b(new u(new int[] { 3, 10 }, this.rIb));
        this.KIb.b(new v(new int[] { 21 }, this.rIb));
        this.KIb.b(new abstract(new int[] { 3, 10 }, this.rIb));
        this.KIb.b(new break(new int[] { 10, 10 }, this.rIb));
        this.KIb.b(new case(new int[] { 14, 9 }, this.rIb));
        this.KIb.b(new catch(new int[] { 14 }, this.rIb));
        this.KIb.b(new class(new int[] { 7 }, this.rIb));
        this.KIb.b(new const(new int[] { 14 }, this.rIb));
        this.KIb.b(new continue(new int[] { 5, 20, 80 }, this.rIb));
        this.KIb.b(new default(new int[] { 5, 3 }, this.rIb));
        this.KIb.b(new do(new int[] { 9 }, this.rIb));
        this.KIb.b(new else(new int[] { 14, 30, 70 }, this.rIb));
        this.KIb.b(new extends(new int[] { 12, 26, 9 }, this.rIb));
        this.KIb.b(new final(new int[] { 12, 26, 9 }, this.rIb));
        this.KIb.b(new finally(new int[] { 5, 9 }, this.rIb));
        this.KIb.b(new for(new int[] { 14, 20, 80 }, this.rIb));
        this.KIb.b(new goto(null, this.rIb));
        this.KIb.b(new if(new int[] { 5, 9 }, this.rIb));
        this.KIb.b(new implements(new int[] { 9, 255 }, this.rIb));
        this.KIb.b(new import(null, this.rIb));
        this.KIb.b(new instanceof(new int[] { 10, 20, 80 }, this.rIb));
        this.KIb.b(new interface(new int[] { 5, 10, 9 }, this.rIb));
        this.KIb.b(new native(new int[] { 9, 255 }, this.rIb));
        this.KIb.b(new new(new int[] { 12, 26 }, this.rIb));
        this.KIb.b(new null(null, this.rIb));
        this.KIb.b(new package(new int[] { 8, 8 }, this.rIb));
        this.KIb.b(new private(new int[] { 5, 9 }, this.rIb));
        this.KIb.b(new protected(new int[] { 14, 30, 70 }, this.rIb));
        this.KIb.b(new public(new int[] { 14, 9 }, this.rIb));
        this.KIb.b(new return(new int[] { 5, 3, 3 }, this.rIb));
        this.KIb.b(new static(new int[] { 20 }, this.rIb));
        this.KIb.b(new super(new int[] { 15, 3, 5 }, this.rIb));
        this.KIb.b(new switch(new int[] { 15 }, this.rIb));
        this.KIb.b(new synchronized(new int[] { 15, 9 }, this.rIb));
        this.KIb.b(new this(new int[] { 9 }, this.rIb));
        this.KIb.b(new throw(new int[] { 7, 14, 28 }, this.rIb));
        this.KIb.b(new throws(new int[] { 10 }, this.rIb));
        this.KIb.b(new transient(new int[] { 5, 10, 9 }, this.rIb));
        this.KIb.b(new try(new int[] { 12 }, this.rIb));
        this.KIb.b(new Lo(null, this.rIb));
        this.KIb.b(new Mo(null, this.rIb));
        if (s != null) {
            this.E(s);
        }
        final No no = new No("ParabolicSAR", null, this.rIb);
        this.VIb._(no.toString(), new Color[] { Color.red });
        this.TIb.b(no);
        final Oo oo = new Oo("BOL", new int[] { 9, 2 }, this.rIb);
        this.VIb._(oo.toString(), new Color[] { Color.lightGray });
        this.TIb.b(oo);
        final Po po = new Po("EMA", new int[] { 5 }, this.rIb);
        this.VIb._(po.toString(), new Color[] { Color.cyan });
        this.TIb.b(po);
        final Qo qo = new Qo("SMA1", new int[] { 15 }, this.rIb);
        this.VIb._(qo.toString(), new Color[] { Color.red });
        this.TIb.b(qo);
        final Qo qo2 = new Qo("SMA2", new int[] { 30 }, this.rIb);
        this.VIb._(qo2.toString(), new Color[] { Color.magenta });
        this.TIb.b(qo2);
        final Qo qo3 = new Qo("SMA3", new int[] { 45 }, this.rIb);
        this.VIb._(qo3.toString(), new Color[] { Color.blue });
        this.TIb.b(qo3);
        final Ro ro = new Ro("VOLEMA", new int[] { 10 }, this.rIb);
        this.VIb._(ro.toString(), new Color[] { Color.red });
        this.TIb.b(ro);
        this.VIb._("Indicator", new Color[] { Color.red, Color.cyan, Color.blue });
        this.VIb._("Price", new Color[] { Color.yellow });
        this.VIb._("Volume", new Color[] { Color.green });
    }
    
    public void a(final o o) {
        if (o instanceof n) {
            this.KIb._(o);
            if (o.toString().equals(this.RIb)) {
                this.RIb = null;
            }
            if (o.toString().equals(this.SIb)) {
                this.SIb = null;
            }
        }
    }
    
    public void E(final String s) {
        final Vector _ = So._(s);
        for (int i = 0; i < _.size(); ++i) {
            this._(_.elementAt(i));
        }
    }
    
    public o _(final To to) {
        final String name = to.getName();
        String b = to.b();
        final int[] a = to.a();
        final String h = to.h();
        if (name == null || name.length() == 0) {
            return null;
        }
        final String string = "_" + name;
        final n n = (n)this.KIb._(string);
        if (n != null) {
            this.KIb._(n);
            this.rIb.deleteObserver(n);
        }
        if (h == null || h.length() == 0) {
            return null;
        }
        if (b == null || b.length() == 0) {
            b = name;
        }
        final n n2 = new n(string, b, a, h, this);
        this.KIb.b(n2);
        return n2;
    }
    
    public String[] i() {
        final Vector vector = new Vector<String>();
        final String[] a = this.a();
        for (int i = 0; i < a.length; ++i) {
            final o a2 = this.a(a[i]);
            if (a2 != null && a2 instanceof n) {
                vector.addElement(((n)a2).b().getName());
            }
        }
        final String[] array = new String[vector.size()];
        for (int j = 0; j < vector.size(); ++j) {
            array[j] = vector.elementAt(j);
        }
        return array;
    }
    
    private Vector b() {
        final Vector<To> vector = new Vector<To>();
        final String[] a = this.a();
        for (int i = 0; i < a.length; ++i) {
            final o a2 = this.a(a[i]);
            if (a2 != null && a2 instanceof n) {
                vector.addElement(((n)a2).b());
            }
        }
        return vector;
    }
    
    public String B() {
        return So._(this.b());
    }
    
    public String A() {
        return this.YIb;
    }
    
    public k b() {
        return this._Jb;
    }
}
