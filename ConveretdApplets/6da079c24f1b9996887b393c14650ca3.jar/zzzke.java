import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class zzzke
{
    public int zzzbe;
    public int zzzce;
    public int zzzde;
    public int zzzee;
    public int zzzfe;
    public int zzzge;
    public int zzzhe;
    public String zzzie;
    public Rectangle zzzje;
    
    public zzzke(final String zzzie, final int zzzfe, final int zzzge, final int zzzhe) {
        this.zzzfe = zzzfe;
        this.zzzge = zzzge;
        this.zzzhe = zzzhe;
        this.zzzie = zzzie;
        this.zzzce = this.zzzie.length();
        this.zzzje = new Rectangle(zzzfe, zzzge, this.zzzce * zzzhe, zzzhe);
    }
    
    public Rectangle zzzyd() {
        return new Rectangle(this.zzzfe + this.zzzbe * this.zzzhe, this.zzzge, this.zzzhe, this.zzzhe);
    }
    
    public boolean zzzsd(final int n, final int n2) {
        return n == this.zzzde && n2 >= this.zzzee && n2 <= this.zzzee + this.zzzce;
    }
    
    public void zzzae(final int n) {
        this.zzzbe += n;
        if (this.zzzbe < 0) {
            this.zzzbe = this.zzzce - 1;
            return;
        }
        if (this.zzzbe >= this.zzzce) {
            this.zzzbe = 0;
        }
    }
    
    public Rectangle zzzxd() {
        return this.zzzje;
    }
    
    public boolean zzzvd(final int n, final int n2) {
        return this.zzzje.inside(n, n2);
    }
    
    public int zzzwd() {
        return this.zzzee + this.zzzbe;
    }
    
    public int zzzud() {
        return this.zzzce;
    }
    
    public String zzztd() {
        return this.zzzie;
    }
    
    public void zzzzd(final int zzzee) {
        this.zzzee = zzzee;
    }
}
