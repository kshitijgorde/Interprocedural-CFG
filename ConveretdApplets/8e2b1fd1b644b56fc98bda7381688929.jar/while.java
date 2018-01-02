import java.util.Observer;
import java.util.Vector;
import java.util.Enumeration;
import java.awt.Color;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class while
{
    private package tpa;
    private b Voa;
    private String lwa;
    private String mwa;
    private private Wa;
    private Hashtable nwa;
    private var owa;
    private volatile pwa;
    private switch xa;
    private protected qwa;
    private String rwa;
    private Object swa;
    private f twa;
    public _ u;
    
    public void _(final protected qwa) {
        this.qwa = qwa;
    }
    
    public double[] ea() {
        if (this.qwa != null) {
            return this.qwa.T();
        }
        return new double[0];
    }
    
    public double[] fa() {
        if (this.qwa != null) {
            return this.qwa.R();
        }
        return new double[0];
    }
    
    public double[] ga() {
        if (this.qwa != null) {
            return this.qwa.S();
        }
        return new double[0];
    }
    
    public double[] ha() {
        if (this.qwa != null) {
            return this.qwa.Q();
        }
        return new double[0];
    }
    
    public double[] ia() {
        if (this.qwa != null) {
            return this.qwa.V();
        }
        return new double[0];
    }
    
    public double[] o() {
        if (this.qwa != null) {
            return this.qwa.U();
        }
        return new double[0];
    }
    
    public double[] p() {
        if (this.qwa != null) {
            return this.qwa.W();
        }
        return new double[0];
    }
    
    public int g(final int n) {
        if (this.qwa != null) {
            return this.qwa.getParam(n);
        }
        return 0;
    }
    
    public void b(final double[] array, final int n) {
        if (this.qwa != null) {
            this.qwa.a(array, n);
        }
    }
    
    public void a(final double n) {
        if (this.qwa != null) {
            this.qwa.b(n);
        }
    }
    
    public void Z(final int n) {
        if (this.qwa != null) {
            this.qwa.addBuySignal(n);
        }
    }
    
    public void _a(final int n) {
        if (this.qwa != null) {
            this.qwa.addSellSignal(n);
        }
    }
    
    public void s(final String s) {
        if (this.qwa != null) {
            this.qwa.s(s);
        }
    }
    
    public Object b() {
        return this.swa;
    }
    
    public while(final String rwa, final Object swa, final switch xa, final String s, final String s2, final int n, final String s3, final Hashtable hashtable, final String s4) {
        this.rwa = rwa;
        this.swa = swa;
        this.twa = new f(rwa, swa);
        this.xa = xa;
        this.Voa = new b(s, s2, this, n, s3);
        this.tpa = new package();
        this.Wa = new private();
        this.nwa = new Hashtable();
        this.lwa = null;
        this.mwa = null;
        this.owa = new var();
        this.pwa = new volatile();
        this.u = new _();
        this.b(hashtable, s4);
    }
    
    public switch a() {
        return this.xa;
    }
    
    public void _(final String s, final Color[] array) {
        this.owa.a(s, array);
    }
    
    public void a(final var var) {
        this.owa.b(var);
    }
    
    public void _(final volatile volatile1) {
        this.pwa.a(volatile1);
    }
    
    public void _(final String s, final int[] array) {
        this.pwa._(s, array);
    }
    
    public Color[] b(final String s) {
        return this.owa.b(s);
    }
    
    public int[] _(final String s) {
        return this.pwa._(s);
    }
    
    public b _() {
        return this.Voa;
    }
    
    public String[] b() {
        return this.tpa.c();
    }
    
    public String[] n() {
        return this.Wa.c();
    }
    
    public void m(final String lwa) {
        this.lwa = lwa;
    }
    
    public void n(final String mwa) {
        this.mwa = mwa;
    }
    
    public void c(final String s) {
        this.nwa.put(s, s);
    }
    
    public void _b() {
        this.nwa.clear();
    }
    
    public void d(final String s) {
        this.nwa.remove(s);
    }
    
    public public[] a() {
        if (this.nwa.size() == 0) {
            return null;
        }
        final public[] array = new public[this.nwa.size()];
        int n = 0;
        for (Enumeration keys = this.nwa.keys(); keys.hasMoreElements() && n < array.length; ++n) {
            array[n] = this.Wa.a(keys.nextElement());
        }
        return array;
    }
    
    public public _() {
        return this.tpa.a(this.lwa);
    }
    
    public public a() {
        return this.tpa.a(this.mwa);
    }
    
    public public b(final String s) {
        return this.tpa.a(s);
    }
    
    public public _(final String s) {
        return this.Wa.a(s);
    }
    
    private void b(final Hashtable hashtable, final String s) {
        final return return1 = new return(new int[] { 5, 5 }, this.Voa);
        if (hashtable.get(return1.toString()) == null) {
            this.tpa.b(return1);
        }
        final static static1 = new static(null, this.Voa);
        if (hashtable.get(static1.toString()) == null) {
            this.tpa.b(static1);
        }
        final jda jda = new jda(new int[] { 14 }, this.Voa);
        if (hashtable.get(jda.toString()) == null) {
            this.tpa.b(jda);
        }
        final kda kda = new kda(new int[] { 14 }, this.Voa);
        if (hashtable.get(kda.toString()) == null) {
            this.tpa.b(kda);
        }
        final lda lda = new lda(new int[] { 14 }, this.Voa);
        if (hashtable.get(lda.toString()) == null) {
            this.tpa.b(lda);
        }
        final mda mda = new mda(new int[] { 3, 10 }, this.Voa);
        if (hashtable.get(mda.toString()) == null) {
            this.tpa.b(mda);
        }
        final nda nda = new nda(new int[] { 21 }, this.Voa);
        if (hashtable.get(nda.toString()) == null) {
            this.tpa.b(nda);
        }
        final oda oda = new oda(new int[] { 3, 10 }, this.Voa);
        if (hashtable.get(oda.toString()) == null) {
            this.tpa.b(oda);
        }
        final pda pda = new pda(new int[] { 10, 10 }, this.Voa);
        if (hashtable.get(pda.toString()) == null) {
            this.tpa.b(pda);
        }
        final qda qda = new qda(new int[] { 14, 9 }, this.Voa);
        if (hashtable.get(qda.toString()) == null) {
            this.tpa.b(qda);
        }
        final rda rda = new rda(new int[] { 14, 14 }, this.Voa);
        if (hashtable.get(rda.toString()) == null) {
            this.tpa.b(rda);
        }
        final sda sda = new sda(new int[] { 7 }, this.Voa);
        if (hashtable.get(sda.toString()) == null) {
            this.tpa.b(sda);
        }
        final tda tda = new tda(new int[] { 14 }, this.Voa);
        if (hashtable.get(tda.toString()) == null) {
            this.tpa.b(tda);
        }
        final uda uda = new uda(new int[] { 5, 20, 80 }, this.Voa);
        if (hashtable.get(uda.toString()) == null) {
            this.tpa.b(uda);
        }
        final vda vda = new vda(new int[] { 5, 3 }, this.Voa);
        if (hashtable.get(vda.toString()) == null) {
            this.tpa.b(vda);
        }
        final wda wda = new wda(new int[] { 9 }, this.Voa);
        if (hashtable.get(wda.toString()) == null) {
            this.tpa.b(wda);
        }
        final xda xda = new xda(new int[] { 14, 30, 70 }, this.Voa);
        if (hashtable.get(xda.toString()) == null) {
            this.tpa.b(xda);
        }
        final yda yda = new yda(new int[] { 12, 26, 9 }, this.Voa);
        if (hashtable.get(yda.toString()) == null) {
            this.tpa.b(yda);
        }
        final zda zda = new zda(new int[] { 12, 26, 9 }, this.Voa);
        if (hashtable.get(zda.toString()) == null) {
            this.tpa.b(zda);
        }
        final Ada ada = new Ada(new int[] { 5, 9 }, this.Voa);
        if (hashtable.get(ada.toString()) == null) {
            this.tpa.b(ada);
        }
        final Bda bda = new Bda(new int[] { 14, 20, 80 }, this.Voa);
        if (hashtable.get(bda.toString()) == null) {
            this.tpa.b(bda);
        }
        final Cda cda = new Cda(null, this.Voa);
        if (hashtable.get(cda.toString()) == null) {
            this.tpa.b(cda);
        }
        final Dda dda = new Dda(new int[] { 5, 9 }, this.Voa);
        if (hashtable.get(dda.toString()) == null) {
            this.tpa.b(dda);
        }
        final Eda eda = new Eda(new int[] { 9, 255 }, this.Voa);
        if (hashtable.get(eda.toString()) == null) {
            this.tpa.b(eda);
        }
        final Fda fda = new Fda(null, this.Voa);
        if (hashtable.get(fda.toString()) == null) {
            this.tpa.b(fda);
        }
        final Gda gda = new Gda(new int[] { 10, 20, 80 }, this.Voa);
        if (hashtable.get(gda.toString()) == null) {
            this.tpa.b(gda);
        }
        final Hda hda = new Hda(new int[] { 5, 10, 9 }, this.Voa);
        if (hashtable.get(hda.toString()) == null) {
            this.tpa.b(hda);
        }
        final Ida ida = new Ida(new int[] { 9, 255 }, this.Voa);
        if (hashtable.get(ida.toString()) == null) {
            this.tpa.b(ida);
        }
        final Jda jda2 = new Jda(new int[] { 12, 26 }, this.Voa);
        if (hashtable.get(jda2.toString()) == null) {
            this.tpa.b(jda2);
        }
        final Kda kda2 = new Kda(null, this.Voa);
        if (hashtable.get(kda2.toString()) == null) {
            this.tpa.b(kda2);
        }
        final Lda lda2 = new Lda(new int[] { 8, 8 }, this.Voa);
        if (hashtable.get(lda2.toString()) == null) {
            this.tpa.b(lda2);
        }
        final Mda mda2 = new Mda(new int[] { 5, 9 }, this.Voa);
        if (hashtable.get(mda2.toString()) == null) {
            this.tpa.b(mda2);
        }
        final Nda nda2 = new Nda(new int[] { 14, 30, 70 }, this.Voa);
        if (hashtable.get(nda2.toString()) == null) {
            this.tpa.b(nda2);
        }
        final Oda oda2 = new Oda(new int[] { 14, 10 }, this.Voa);
        if (hashtable.get(oda2.toString()) == null) {
            this.tpa.b(oda2);
        }
        final Pda pda2 = new Pda(new int[] { 5, 3, 3 }, this.Voa);
        if (hashtable.get(pda2.toString()) == null) {
            this.tpa.b(pda2);
        }
        final Qda qda2 = new Qda(new int[] { 20 }, this.Voa);
        if (hashtable.get(qda2.toString()) == null) {
            this.tpa.b(qda2);
        }
        final Rda rda2 = new Rda(new int[] { 15, 3, 5 }, this.Voa);
        if (hashtable.get(rda2.toString()) == null) {
            this.tpa.b(rda2);
        }
        final Sda sda2 = new Sda(new int[] { 15 }, this.Voa);
        if (hashtable.get(sda2.toString()) == null) {
            this.tpa.b(sda2);
        }
        final Tda tda2 = new Tda(new int[] { 15, 9 }, this.Voa);
        if (hashtable.get(tda2.toString()) == null) {
            this.tpa.b(tda2);
        }
        final Uda uda2 = new Uda(new int[] { 9 }, this.Voa);
        if (hashtable.get(uda2.toString()) == null) {
            this.tpa.b(uda2);
        }
        final Vda vda2 = new Vda(new int[] { 7, 14, 28 }, this.Voa);
        if (hashtable.get(vda2.toString()) == null) {
            this.tpa.b(vda2);
        }
        final Wda wda2 = new Wda(new int[] { 5, 10, 9 }, this.Voa);
        if (hashtable.get(wda2.toString()) == null) {
            this.tpa.b(wda2);
        }
        final Xda xda2 = new Xda(new int[] { 7 }, this.Voa);
        if (hashtable.get(xda2.toString()) == null) {
            this.tpa.b(xda2);
        }
        final Yda yda2 = new Yda(new int[] { 12 }, this.Voa);
        if (hashtable.get(yda2.toString()) == null) {
            this.tpa.b(yda2);
        }
        final Zda zda2 = new Zda(null, this.Voa);
        if (hashtable.get(zda2.toString()) == null) {
            this.tpa.b(zda2);
        }
        final _ea ea = new _ea(null, this.Voa);
        if (hashtable.get(ea.toString()) == null) {
            this.tpa.b(ea);
        }
        if (s != null) {
            this.G(s);
        }
        final aea aea = new aea("ParabolicSAR", null, this.Voa);
        this.owa.b(aea.toString(), new Color[] { Color.red });
        this.Wa.b(aea);
        final bea bea = new bea("BOL", new int[] { 9, 2 }, this.Voa);
        this.owa.b(bea.toString(), new Color[] { Color.lightGray });
        this.Wa.b(bea);
        final cea cea = new cea("EMA", new int[] { 5 }, this.Voa);
        this.owa.b(cea.toString(), new Color[] { Color.cyan });
        this.Wa.b(cea);
        final dea dea = new dea("SMA1", new int[] { 15 }, this.Voa);
        this.owa.b(dea.toString(), new Color[] { Color.red });
        this.Wa.b(dea);
        final dea dea2 = new dea("SMA2", new int[] { 30 }, this.Voa);
        this.owa.b(dea2.toString(), new Color[] { Color.magenta });
        this.Wa.b(dea2);
        final dea dea3 = new dea("SMA3", new int[] { 45 }, this.Voa);
        this.owa.b(dea3.toString(), new Color[] { Color.blue });
        this.Wa.b(dea3);
        final eea eea = new eea("VOLEMA", new int[] { 10 }, this.Voa);
        this.owa.b(eea.toString(), new Color[] { Color.red });
        this.Wa.b(eea);
        this.owa.b("Indicator", new Color[] { Color.red, Color.cyan, Color.blue });
        this.owa.b("Price", new Color[] { Color.yellow });
        this.owa.b("Volume", new Color[] { Color.green });
    }
    
    public void a(final public public1) {
        if (public1 instanceof protected) {
            this.tpa._(public1);
            if (public1.toString().equals(this.lwa)) {
                this.lwa = null;
            }
            if (public1.toString().equals(this.mwa)) {
                this.mwa = null;
            }
        }
    }
    
    public void G(final String s) {
        final Vector _ = fea._(s);
        for (int i = 0; i < _.size(); ++i) {
            this._(_.elementAt(i));
        }
    }
    
    public public _(final gea gea) {
        final String name = gea.getName();
        String i = gea.i();
        final int[] b = gea.b();
        final String j = gea.j();
        if (name == null || name.length() == 0) {
            return null;
        }
        final String string = "_" + name;
        final protected protected1 = (protected)this.tpa.a(string);
        if (protected1 != null) {
            this.tpa._(protected1);
            this.Voa.deleteObserver(protected1);
        }
        if (j == null || j.length() == 0) {
            return null;
        }
        if (i == null || i.length() == 0) {
            i = name;
        }
        final protected protected2 = new protected(string, i, b, j, this);
        this.tpa.b(protected2);
        return protected2;
    }
    
    public String[] d() {
        final Vector vector = new Vector<String>();
        final String[] b = this.b();
        for (int i = 0; i < b.length; ++i) {
            final public b2 = this.b(b[i]);
            if (b2 != null && b2 instanceof protected) {
                vector.addElement(((protected)b2).b().getName());
            }
        }
        final String[] array = new String[vector.size()];
        for (int j = 0; j < vector.size(); ++j) {
            array[j] = vector.elementAt(j);
        }
        return array;
    }
    
    private Vector _() {
        final Vector<gea> vector = new Vector<gea>();
        final String[] b = this.b();
        for (int i = 0; i < b.length; ++i) {
            final public b2 = this.b(b[i]);
            if (b2 != null && b2 instanceof protected) {
                vector.addElement(((protected)b2).b());
            }
        }
        return vector;
    }
    
    public String J() {
        return fea._(this._());
    }
    
    public String C() {
        return this.rwa;
    }
    
    public f _() {
        return this.twa;
    }
}
