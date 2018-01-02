// 
// Decompiled by Procyon v0.5.30
// 

final class Wh
{
    public final String nja;
    public final String name;
    public final String oja;
    public final boolean pja;
    private final n ta;
    
    Wh(final n ta, final String s) {
        this.ta = ta;
        if (s == null) {
            final String nja = "";
            this.oja = nja;
            this.name = nja;
            this.nja = nja;
            this.pja = false;
        }
        else {
            final u u = new u("/");
            u.m(s);
            if (u.a() <= 1) {
                if (n.f(ta)) {
                    final String upperCase = s.toUpperCase();
                    this.name = upperCase;
                    this.nja = upperCase;
                }
                else {
                    this.name = s;
                    this.nja = s;
                }
                this.oja = "";
                this.pja = (this.nja != null && this.name != null && this.nja.length() > 0 && this.name.length() > 0 && n._(ta).g(this.name));
            }
            else {
                if (n.f(ta)) {
                    this.nja = u.b(0).toUpperCase();
                    this.name = u.b(1).toUpperCase();
                }
                else {
                    this.nja = u.b(0);
                    this.name = u.b(1);
                }
                if (u.a() > 2) {
                    this.oja = u.b(2);
                }
                else {
                    this.oja = "";
                }
                this.pja = (this.nja != null && this.name != null && this.nja.length() > 0 && this.name.length() > 0 && n._(ta).g(this.name));
            }
        }
    }
}
