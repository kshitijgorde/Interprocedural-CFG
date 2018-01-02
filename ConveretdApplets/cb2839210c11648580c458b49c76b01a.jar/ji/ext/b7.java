// 
// Decompiled by Procyon v0.5.30
// 

package ji.ext;

class b7
{
    public String a;
    public String b;
    public String c;
    public int d;
    public int e;
    
    b7() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = 0;
        this.e = 0;
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.b))).append(" (").append(this.d).append(") - lib=").append(this.c).append(", class=").append(this.a).append(", win=").append(w.a(this.e)).append(", linux=").append(w.b(this.e)).append(", macOS=").append(w.c(this.e)).append(", macOSX=").append(w.d(this.e)).append(", intelSol=").append(w.e(this.e))));
    }
}
