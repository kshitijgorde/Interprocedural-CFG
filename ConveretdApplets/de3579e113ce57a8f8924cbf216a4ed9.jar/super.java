import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class super
{
    private Vector Dpa;
    private int Epa;
    private int Fpa;
    
    super(final int fpa) {
        this.Fpa = fpa;
        this.Dpa = new Vector(this.Fpa);
        for (int i = 0; i < this.Fpa; ++i) {
            this.Dpa.addElement(new Vector<Vector>());
        }
    }
    
    void _(final String[] array, final int n) {
        this.Dpa.elementAt(n).addElement(array);
    }
    
    private e[] a(final String[] array) {
        final e[] array2 = new e[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = new e(array[i]);
        }
        return array2;
    }
    
    public e[] _(final int n, final int n2) {
        return this.a(this.Dpa.elementAt(n2).elementAt(n));
    }
    
    public int _(final int n) {
        return this.Dpa.elementAt(n).size();
    }
    
    public int m() {
        return this.Fpa;
    }
}
