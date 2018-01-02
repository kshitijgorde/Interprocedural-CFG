import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class interface
{
    private String name;
    private String bb;
    private String ia;
    private float ja;
    private float ka;
    private boolean la;
    private int ma;
    private boolean na;
    public new[] ab;
    private package oa;
    private if pa;
    private int Za;
    public boolean eb;
    private static String Ea = "\u752a\u7530";
    private static String Fa = "\u754f\u7578\u7578\u7565\u7578\u752b";
    private static String Ga = "\u7541";
    private static String Ha = "\u7549";
    private static String Ia = "\u753a\u753b\u7538\u7539\u753e\u753f\u753c\u753d\u7532\u7533\u7524\u7526\u7527\u7521\u756f\u754f";
    
    public interface(final String s, final String s2, final boolean la, final int za, final int n, final new[] ab, final Font font) {
        this.eb = false;
        this.name = new String(s);
        this.bb = new String(s2);
        this.ia = s2 + interface.Ea;
        this.ma = n;
        this.ab = ab;
        this.Za = za;
        this.oa = new package(this, this.Za, font);
        this.pa = new if(interface.Fa, this);
        this.na = false;
        if (!(this.la = la)) {
            this.oa._();
        }
        this.a(this.ma = n);
    }
    
    public interface(final String s, final String s2, final boolean la, final int za, final int ma, final Font font) {
        this.eb = false;
        this.name = new String(s);
        this.bb = new String(s2);
        this.ia = s2 + interface.Ea;
        this.la = la;
        this.Za = za;
        this.ma = ma;
        this.oa = new package(this, this.Za, font);
        this.pa = new if(interface.Fa, this);
        this.na = true;
    }
    
    public final boolean a() {
        return this.la;
    }
    
    public final void _(final boolean la) {
        if (!la) {
            this.oa._();
        }
        else {
            this.oa.a();
        }
        this.la = la;
    }
    
    public final String getName() {
        return this.name;
    }
    
    public final float _() {
        return Float.valueOf(this.oa.a());
    }
    
    public final float a() {
        if (!this.eb) {
            return this._() * this.oa.b();
        }
        if (this.oa.f().equals(interface.Ga)) {
            return this._();
        }
        if (this.oa.f().equals(interface.Ha)) {
            return this._() + 273.0f;
        }
        return (this._() - 32.0f) / 1.8f + 273.0f;
    }
    
    public final String g() {
        return this.oa.a();
    }
    
    public final void a(final String s) {
        this.oa._(synchronized.b(s));
    }
    
    public final void b(final String s) {
        if (!this.eb) {
            this.oa._(synchronized.b(String.valueOf(Float.valueOf(s) / this.oa.b())));
            return;
        }
        if (this.oa.f().equals(interface.Ga)) {
            this.oa._(String.valueOf((float)Float.valueOf(s)));
            return;
        }
        if (this.oa.f().equals(interface.Ha)) {
            this.oa._(String.valueOf(Float.valueOf(s) - 273.0f));
            return;
        }
        this.oa._(String.valueOf(32.0f + (Float.valueOf(s) - 273.0f) * 1.8f));
    }
    
    public final String h() {
        return this.bb;
    }
    
    public final String _() {
        return this.ia;
    }
    
    public void b(final float n, final float n2) {
        if (this._(this.g())) {
            this.ka = this._();
            if (this.eb) {
                if (this.oa.f().equals(interface.Ga)) {
                    if (this.oa.b().equals(interface.Ha)) {
                        this.ka += 273.0f;
                    }
                    else {
                        this.ka = (this.ka - 32.0f) / 1.8f + 273.0f;
                    }
                }
                else if (this.oa.f().equals(interface.Ha)) {
                    if (this.oa.b().equals(interface.Ga)) {
                        this.ja = this.ka;
                    }
                    else {
                        this.ja = (this.ka - 32.0f) / 1.8f + 273.0f;
                    }
                    this.ka = this.ja - 273.0f;
                }
                else {
                    if (this.oa.b().equals(interface.Ga)) {
                        this.ja = this.ka;
                    }
                    else {
                        this.ja = this.ka + 273.0f;
                    }
                    this.ka = 32.0f + (this.ja - 273.0f) * 1.8f;
                }
            }
            else {
                this.ja = this.ka * n2;
                this.ka = this.ja / n;
            }
            this.oa._(synchronized.b(String.valueOf(this.ka)));
        }
    }
    
    public final package b() {
        return this.oa;
    }
    
    private void a(final int n) {
        this.oa._(n);
    }
    
    public boolean _(String trim) {
        final String ia = interface.Ia;
        trim = trim.trim();
        if (!synchronized._(ia, trim, true)) {
            this.b();
            return false;
        }
        this.oa._(synchronized.b(trim));
        return true;
    }
    
    private void b() {
        this.pa.b(this);
        this.pa.pack();
        this.pa.show();
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x1750A);
        }
        return new String(array);
    }
    
    static {
        interface.Ea = _(interface.Ea);
        interface.Fa = _(interface.Fa);
        interface.Ga = _(interface.Ga);
        interface.Ha = _(interface.Ha);
        interface.Ia = _(interface.Ia);
    }
}
