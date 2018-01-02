// 
// Decompiled by Procyon v0.5.30
// 

class e
{
    protected int[] k;
    protected int[] i;
    protected int[] j;
    protected float[] a;
    protected float[] b;
    protected float[] c;
    protected int[] m;
    protected char[] o;
    protected String[] n;
    protected String[] l;
    protected int d;
    protected int e;
    protected boolean f;
    protected boolean g;
    protected String h;
    
    protected e a(final int n, final int n2) {
        final e e = new e(this.h, n, n2);
        System.arraycopy(this.l, 0, e.l, 0, (n < this.e) ? n : this.e);
        System.arraycopy(this.c, 0, e.c, 0, (n < this.e) ? n : this.e);
        System.arraycopy(this.b, 0, e.b, 0, (n < this.e) ? n : this.e);
        System.arraycopy(this.a, 0, e.a, 0, (n < this.e) ? n : this.e);
        if (this.g) {
            System.arraycopy(this.n, 0, e.n, 0, (n < this.e) ? n : this.e);
            System.arraycopy(this.o, 0, e.o, 0, (n < this.e) ? n : this.e);
            System.arraycopy(this.m, 0, e.m, 0, (n < this.e) ? n : this.e);
        }
        System.arraycopy(this.j, 0, e.j, 0, (n2 < this.d) ? n2 : this.d);
        System.arraycopy(this.i, 0, e.i, 0, (n2 < this.d) ? n2 : this.d);
        System.arraycopy(this.k, 0, e.k, 0, (n2 < this.d) ? n2 : this.d);
        return e;
    }
    
    e(final String h, final int e, final int d) {
        this.e = e;
        this.d = d;
        this.h = h;
        this.g = (this.h.toLowerCase().endsWith("pdb") || this.h.toLowerCase().endsWith("ent"));
        this.f = this.h.toLowerCase().endsWith("xyz");
        this.l = new String[this.e];
        if (this.g) {
            this.n = new String[this.e];
            this.o = new char[this.e];
            this.m = new int[this.e];
        }
        this.c = new float[this.e];
        this.b = new float[this.e];
        this.a = new float[this.e];
        this.j = new int[this.d];
        this.i = new int[this.d];
        this.k = new int[this.d];
    }
    
    e() {
    }
}
