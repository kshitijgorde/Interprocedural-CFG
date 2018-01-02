import java.util.Observer;
import java.util.Vector;
import java.util.Enumeration;
import java.awt.Color;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class break
{
    private bi U;
    private class s;
    private String Wra;
    private String Xra;
    private ci bb;
    private Hashtable Yra;
    private v Zra;
    private abstract _sa;
    private o Ca;
    private di asa;
    private String bsa;
    private Object csa;
    private else dsa;
    public case toa;
    
    public void a(final di asa) {
        this.asa = asa;
    }
    
    public double[] o() {
        if (this.asa != null) {
            return this.asa.m();
        }
        return new double[0];
    }
    
    public double[] p() {
        if (this.asa != null) {
            return this.asa.k();
        }
        return new double[0];
    }
    
    public double[] q() {
        if (this.asa != null) {
            return this.asa.l();
        }
        return new double[0];
    }
    
    public double[] r() {
        if (this.asa != null) {
            return this.asa.j();
        }
        return new double[0];
    }
    
    public double[] s() {
        if (this.asa != null) {
            return this.asa.c();
        }
        return new double[0];
    }
    
    public double[] t() {
        if (this.asa != null) {
            return this.asa.n();
        }
        return new double[0];
    }
    
    public double[] u() {
        if (this.asa != null) {
            return this.asa.d();
        }
        return new double[0];
    }
    
    public int n(final int n) {
        if (this.asa != null) {
            return this.asa.getParam(n);
        }
        return 0;
    }
    
    public void _(final double[] array, final int n) {
        if (this.asa != null) {
            this.asa.b(array, n);
        }
    }
    
    public void a(final double n) {
        if (this.asa != null) {
            this.asa.b(n);
        }
    }
    
    public void ba(final int n) {
        if (this.asa != null) {
            this.asa.addBuySignal(n);
        }
    }
    
    public void ca(final int n) {
        if (this.asa != null) {
            this.asa.addSellSignal(n);
        }
    }
    
    public void l(final String s) {
        if (this.asa != null) {
            this.asa.l(s);
        }
    }
    
    public Object _() {
        return this.csa;
    }
    
    public break(final String bsa, final Object csa, final o ca, final String s, final String s2, final int n, final String s3, final Hashtable hashtable, final String s4) {
        this.bsa = bsa;
        this.csa = csa;
        this.dsa = new else(bsa, csa);
        this.Ca = ca;
        this.s = new class(s, s2, this, n, s3);
        this.U = new bi();
        this.bb = new ci();
        this.Yra = new Hashtable();
        this.Wra = null;
        this.Xra = null;
        this.Zra = new v();
        this._sa = new abstract();
        this.toa = new case();
        this._(hashtable, s4);
    }
    
    public o a() {
        return this.Ca;
    }
    
    public void _(final String s, final Color[] array) {
        this.Zra.a(s, array);
        if ("TrendLine".equals(s) && array != null && array.length > 0) {
            Mh._(array[0]);
        }
    }
    
    public void _(final v v) {
        this.Zra.a(v);
    }
    
    public void b(final abstract abstract1) {
        this._sa._(abstract1);
    }
    
    public void a(final String s, final int[] array) {
        this._sa.a(s, array);
    }
    
    public Color[] a(final String s) {
        return this.Zra.a(s);
    }
    
    public int[] _(final String s) {
        return this._sa._(s);
    }
    
    public class _() {
        return this.s;
    }
    
    public String[] _() {
        return this.U.n();
    }
    
    public String[] e() {
        return this.bb.n();
    }
    
    public void n(final String wra) {
        this.Wra = wra;
    }
    
    public void c(final String xra) {
        this.Xra = xra;
    }
    
    public void d(final String s) {
        this.Yra.put(s, s);
    }
    
    public void Hb() {
        this.Yra.clear();
    }
    
    public void e(final String s) {
        this.Yra.remove(s);
    }
    
    public implements[] a() {
        if (this.Yra.size() == 0) {
            return null;
        }
        final implements[] array = new implements[this.Yra.size()];
        int n = 0;
        for (Enumeration keys = this.Yra.keys(); keys.hasMoreElements() && n < array.length; ++n) {
            array[n] = this.bb._(keys.nextElement());
        }
        return array;
    }
    
    public implements a() {
        return this.U._(this.Wra);
    }
    
    public implements b() {
        return this.U._(this.Xra);
    }
    
    public implements a(final String s) {
        return this.U._(s);
    }
    
    public implements b(final String s) {
        return this.bb._(s);
    }
    
    private void _(final Hashtable hashtable, final String s) {
        final ei ei = new ei(new int[] { 5, 5 }, this.s);
        if (hashtable.get(ei.toString()) == null) {
            this.U._(ei);
        }
        final fi fi = new fi(null, this.s);
        if (hashtable.get(fi.toString()) == null) {
            this.U._(fi);
        }
        final gi gi = new gi(new int[] { 14 }, this.s);
        if (hashtable.get(gi.toString()) == null) {
            this.U._(gi);
        }
        final hi hi = new hi(new int[] { 14 }, this.s);
        if (hashtable.get(hi.toString()) == null) {
            this.U._(hi);
        }
        final ii ii = new ii(new int[] { 14 }, this.s);
        if (hashtable.get(ii.toString()) == null) {
            this.U._(ii);
        }
        final ji ji = new ji(new int[] { 3, 10 }, this.s);
        if (hashtable.get(ji.toString()) == null) {
            this.U._(ji);
        }
        final ki ki = new ki(new int[] { 21 }, this.s);
        if (hashtable.get(ki.toString()) == null) {
            this.U._(ki);
        }
        final li li = new li(new int[] { 3, 10 }, this.s);
        if (hashtable.get(li.toString()) == null) {
            this.U._(li);
        }
        final mi mi = new mi(new int[] { 10, 10 }, this.s);
        if (hashtable.get(mi.toString()) == null) {
            this.U._(mi);
        }
        final ni ni = new ni(new int[] { 14, 9 }, this.s);
        if (hashtable.get(ni.toString()) == null) {
            this.U._(ni);
        }
        final oi oi = new oi(new int[] { 14, 14 }, this.s);
        if (hashtable.get(oi.toString()) == null) {
            this.U._(oi);
        }
        final pi pi = new pi(new int[] { 7 }, this.s);
        if (hashtable.get(pi.toString()) == null) {
            this.U._(pi);
        }
        final static static1 = new static(new int[] { 13 }, this.s);
        if (hashtable.get(static1.toString()) == null) {
            this.U._(static1);
        }
        final super super1 = new super(new int[] { 13 }, this.s);
        if (hashtable.get(super1.toString()) == null) {
            this.U._(super1);
        }
        final qi qi = new qi(new int[] { 14 }, this.s);
        if (hashtable.get(qi.toString()) == null) {
            this.U._(qi);
        }
        final ri ri = new ri(new int[] { 5, 20, 80 }, this.s);
        if (hashtable.get(ri.toString()) == null) {
            this.U._(ri);
        }
        final si si = new si(new int[] { 5, 3 }, this.s);
        if (hashtable.get(si.toString()) == null) {
            this.U._(si);
        }
        final ti ti = new ti(new int[] { 9 }, this.s);
        if (hashtable.get(ti.toString()) == null) {
            this.U._(ti);
        }
        final ui ui = new ui(new int[] { 14, 30, 70 }, this.s);
        if (hashtable.get(ui.toString()) == null) {
            this.U._(ui);
        }
        final return return1 = new return(new int[] { 12, 26, 9 }, this.s);
        if (hashtable.get(return1.toString()) == null) {
            this.U._(return1);
        }
        final vi vi = new vi(new int[] { 12, 26, 9 }, this.s);
        if (hashtable.get(vi.toString()) == null) {
            this.U._(vi);
        }
        final wi wi = new wi(new int[] { 5, 9 }, this.s);
        if (hashtable.get(wi.toString()) == null) {
            this.U._(wi);
        }
        final xi xi = new xi(new int[] { 14, 20, 80 }, this.s);
        if (hashtable.get(xi.toString()) == null) {
            this.U._(xi);
        }
        final yi yi = new yi(null, this.s);
        if (hashtable.get(yi.toString()) == null) {
            this.U._(yi);
        }
        final zi zi = new zi(new int[] { 5, 9 }, this.s);
        if (hashtable.get(zi.toString()) == null) {
            this.U._(zi);
        }
        final Ai ai = new Ai(new int[] { 9, 255 }, this.s);
        if (hashtable.get(ai.toString()) == null) {
            this.U._(ai);
        }
        final Bi bi = new Bi(null, this.s);
        if (hashtable.get(bi.toString()) == null) {
            this.U._(bi);
        }
        final Ci ci = new Ci(new int[] { 10, 20, 80 }, this.s);
        if (hashtable.get(ci.toString()) == null) {
            this.U._(ci);
        }
        final Di di = new Di(new int[] { 5, 10, 9 }, this.s);
        if (hashtable.get(di.toString()) == null) {
            this.U._(di);
        }
        final Ei ei2 = new Ei(new int[] { 9, 255 }, this.s);
        if (hashtable.get(ei2.toString()) == null) {
            this.U._(ei2);
        }
        final Fi fi2 = new Fi(new int[] { 12, 26 }, this.s);
        if (hashtable.get(fi2.toString()) == null) {
            this.U._(fi2);
        }
        final Gi gi2 = new Gi(null, this.s);
        if (hashtable.get(gi2.toString()) == null) {
            this.U._(gi2);
        }
        final Hi hi2 = new Hi(new int[] { 8, 8 }, this.s);
        if (hashtable.get(hi2.toString()) == null) {
            this.U._(hi2);
        }
        final Ii ii2 = new Ii(new int[] { 5, 9 }, this.s);
        if (hashtable.get(ii2.toString()) == null) {
            this.U._(ii2);
        }
        final Ji ji2 = new Ji(new int[] { 14, 30, 70 }, this.s);
        if (hashtable.get(ji2.toString()) == null) {
            this.U._(ji2);
        }
        final Ki ki2 = new Ki(new int[] { 14, 10 }, this.s);
        if (hashtable.get(ki2.toString()) == null) {
            this.U._(ki2);
        }
        final Li li2 = new Li(new int[] { 5, 3, 3 }, this.s);
        if (hashtable.get(li2.toString()) == null) {
            this.U._(li2);
        }
        final Mi mi2 = new Mi(new int[] { 20 }, this.s);
        if (hashtable.get(mi2.toString()) == null) {
            this.U._(mi2);
        }
        final Ni ni2 = new Ni(new int[] { 15, 3, 5 }, this.s);
        if (hashtable.get(ni2.toString()) == null) {
            this.U._(ni2);
        }
        final Oi oi2 = new Oi(new int[] { 15 }, this.s);
        if (hashtable.get(oi2.toString()) == null) {
            this.U._(oi2);
        }
        final Pi pi2 = new Pi(new int[] { 15, 9 }, this.s);
        if (hashtable.get(pi2.toString()) == null) {
            this.U._(pi2);
        }
        final Qi qi2 = new Qi(new int[] { 9 }, this.s);
        if (hashtable.get(qi2.toString()) == null) {
            this.U._(qi2);
        }
        final Ri ri2 = new Ri(new int[] { 7, 14, 28 }, this.s);
        if (hashtable.get(ri2.toString()) == null) {
            this.U._(ri2);
        }
        final Si si2 = new Si(new int[] { 5, 10, 9 }, this.s);
        if (hashtable.get(si2.toString()) == null) {
            this.U._(si2);
        }
        final Ti ti2 = new Ti(new int[] { 7 }, this.s);
        if (hashtable.get(ti2.toString()) == null) {
            this.U._(ti2);
        }
        final Ui ui2 = new Ui(new int[] { 12 }, this.s);
        if (hashtable.get(ui2.toString()) == null) {
            this.U._(ui2);
        }
        final Vi vi2 = new Vi(null, this.s);
        if (hashtable.get(vi2.toString()) == null) {
            this.U._(vi2);
        }
        final Wi wi2 = new Wi(null, this.s);
        if (hashtable.get(wi2.toString()) == null) {
            this.U._(wi2);
        }
        if (s != null) {
            this.K(s);
        }
        final protected protected1 = new protected("ParabolicSAR", null, this.s);
        this.Zra.b(protected1.toString(), new Color[] { Color.red });
        this.bb._(protected1);
        final package package1 = new package("PivotPoints", null, this.s);
        this.Zra.b(package1.toString(), new Color[] { Color.blue });
        this.bb._(package1);
        final Xi xi2 = new Xi("DMA", null, this.s);
        this.Zra.b(xi2.toString(), new Color[] { Color.pink });
        this.bb._(xi2);
        final Yi yi2 = new Yi("BOL", new int[] { 9, 2 }, this.s);
        this.Zra.b(yi2.toString(), new Color[] { Color.lightGray });
        this.bb._(yi2);
        final Zi zi2 = new Zi("EMA1", new int[] { 5 }, this.s);
        this.Zra.b(zi2.toString(), new Color[] { Color.cyan });
        this.bb._(zi2);
        final Zi zi3 = new Zi("EMA2", new int[] { 10 }, this.s);
        this.Zra.b(zi3.toString(), new Color[] { Color.magenta.darker() });
        this.bb._(zi3);
        final Zi zi4 = new Zi("EMA3", new int[] { 20 }, this.s);
        this.Zra.b(zi4.toString(), new Color[] { Color.blue.brighter() });
        this.bb._(zi4);
        final _j j = new _j("SMA1", new int[] { 15 }, this.s);
        this.Zra.b(j.toString(), new Color[] { Color.red });
        this.bb._(j);
        final _j i = new _j("SMA2", new int[] { 30 }, this.s);
        this.Zra.b(i.toString(), new Color[] { Color.magenta });
        this.bb._(i);
        final _j k = new _j("SMA3", new int[] { 45 }, this.s);
        this.Zra.b(k.toString(), new Color[] { Color.blue });
        this.bb._(k);
        final interface interface1 = new interface("VOLEMA", new int[] { 10 }, this.s);
        this.Zra.b(interface1.toString(), new Color[] { Color.red });
        this.bb._(interface1);
        this.Zra.b("Indicator", new Color[] { Color.red, Color.cyan, Color.blue });
        this.Zra.b("Price", new Color[] { Color.yellow });
        this.Zra.b("Volume", new Color[] { Color.green });
    }
    
    public void b(final implements implements1) {
        if (implements1 instanceof di) {
            this.U.a(implements1);
            if (implements1.toString().equals(this.Wra)) {
                this.Wra = null;
            }
            if (implements1.toString().equals(this.Xra)) {
                this.Xra = null;
            }
        }
    }
    
    public void K(final String s) {
        final Vector a = aj.a(s);
        for (int i = 0; i < a.size(); ++i) {
            this._(a.elementAt(i));
        }
    }
    
    public implements _(final bj bj) {
        final String name = bj.getName();
        String l = bj.l();
        final int[] _ = bj._();
        final String m = bj.m();
        if (name == null || name.length() == 0) {
            return null;
        }
        final String string = "_" + name;
        final di di = (di)this.U._(string);
        if (di != null) {
            this.U.a(di);
            this.s.deleteObserver(di);
        }
        if (m == null || m.length() == 0) {
            return null;
        }
        if (l == null || l.length() == 0) {
            l = name;
        }
        final di di2 = new di(string, l, _, m, this);
        this.U._(di2);
        return di2;
    }
    
    public String[] g() {
        final Vector vector = new Vector<String>();
        final String[] _ = this._();
        for (int i = 0; i < _.length; ++i) {
            final implements a = this.a(_[i]);
            if (a != null && a instanceof di) {
                vector.addElement(((di)a)._().getName());
            }
        }
        final String[] array = new String[vector.size()];
        for (int j = 0; j < vector.size(); ++j) {
            array[j] = vector.elementAt(j);
        }
        return array;
    }
    
    private Vector b() {
        final Vector<bj> vector = new Vector<bj>();
        final String[] _ = this._();
        for (int i = 0; i < _.length; ++i) {
            final implements a = this.a(_[i]);
            if (a != null && a instanceof di) {
                vector.addElement(((di)a)._());
            }
        }
        return vector;
    }
    
    public String P() {
        return aj.b(this.b());
    }
    
    public String H() {
        return this.bsa;
    }
    
    public else b() {
        return this.dsa;
    }
}
