// 
// Decompiled by Procyon v0.5.30
// 

final class import
{
    public final String dqa;
    public final String name;
    public final boolean eqa;
    private final super da;
    
    import(final super da, final String s) {
        this.da = da;
        if (s == null) {
            final String s2 = "";
            this.name = s2;
            this.dqa = s2;
            this.eqa = false;
        }
        else {
            final try try1 = new try("/");
            try1.l(s);
            if (try1.g() <= 1) {
                if (super.b(da)) {
                    final String upperCase = s.toUpperCase();
                    this.name = upperCase;
                    this.dqa = upperCase;
                }
                else {
                    this.name = s;
                    this.dqa = s;
                }
                this.eqa = (this.dqa != null && this.name != null && this.dqa.length() > 0 && this.name.length() > 0 && super.a(da).c(this.name));
            }
            else {
                if (super.b(da)) {
                    this.dqa = try1.a(0).toUpperCase();
                    this.name = try1.a(1).toUpperCase();
                }
                else {
                    this.dqa = try1.a(0);
                    this.name = try1.a(1);
                }
                this.eqa = (this.dqa != null && this.name != null && this.dqa.length() > 0 && this.name.length() > 0 && super.a(da).c(this.name));
            }
        }
    }
}
