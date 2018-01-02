// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public final class Q
{
    private String a;
    private String b;
    private cU[] c;
    
    public Q(final String a) {
        this.a = a;
        this.b = null;
        this.c = new cU[0];
    }
    
    public Q(final String a, final String b, final cU[] array) {
        this.a = a;
        this.b = b;
        if (array != null) {
            System.arraycopy(array, 0, this.c = new cU[array.length], 0, array.length);
            return;
        }
        this.c = new cU[0];
    }
    
    public final String a() {
        return this.a;
    }
    
    public final String b() {
        return this.b;
    }
    
    public final cU[] c() {
        return this.c;
    }
    
    public final boolean equals(final Object o) {
        return o != null && o instanceof Q && this.a.equalsIgnoreCase(((Q)o).a);
    }
    
    public final String toString() {
        final StringBuffer sb = new StringBuffer();
        this.a(sb);
        return sb.toString();
    }
    
    public final void a(final StringBuffer sb) {
        sb.append(this.a);
        if (this.b != null) {
            if (bz.b(this.b)) {
                sb.append("=\"");
                sb.append(bz.a(this.b, "\\\""));
                sb.append('\"');
            }
            else {
                sb.append('=');
                sb.append(this.b);
            }
        }
        for (int i = 0; i < this.c.length; ++i) {
            sb.append(";");
            sb.append(this.c[i].a());
            final String b;
            if ((b = this.c[i].b()) != null) {
                if (bz.b(b)) {
                    sb.append("=\"");
                    sb.append(bz.a(b, "\\\""));
                    sb.append('\"');
                }
                else {
                    sb.append('=');
                    sb.append(b);
                }
            }
        }
    }
}
