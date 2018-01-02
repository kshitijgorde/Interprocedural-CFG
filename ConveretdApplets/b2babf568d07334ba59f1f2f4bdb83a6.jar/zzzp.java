import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class zzzp
{
    String zzzd;
    Color zzze;
    int[] zzzf;
    int[] zzzg;
    boolean[] zzzh;
    double zzzi;
    double zzzj;
    double zzzk;
    double zzzl;
    int zzzm;
    int zzzn;
    int zzzo;
    
    public zzzp(final String zzzd, final int[] zzzf, final int[] zzzg, final boolean[] zzzh, final Color zzze, final double zzzi, final double zzzj, final double zzzk, final double zzzl, final int zzzm, final int zzzn, final int zzzo) {
        this.zzzd = "";
        this.zzze = Color.white;
        this.zzzd = zzzd;
        this.zzzf = zzzf;
        this.zzzg = zzzg;
        this.zzzh = zzzh;
        this.zzze = zzze;
        this.zzzi = zzzi;
        this.zzzj = zzzj;
        this.zzzk = zzzk;
        this.zzzl = zzzl;
        this.zzzm = zzzm;
        this.zzzn = zzzn;
        this.zzzo = zzzo;
    }
    
    public boolean zzzc(final double n, final double n2, final double n3, final double n4, final int n5, final int n6, final int n7) {
        return n6 != this.zzzn || n7 != this.zzzo || n5 != this.zzzm || n != this.zzzi || n2 != this.zzzj || n3 != this.zzzk || n4 != this.zzzl;
    }
    
    public boolean zzzb(final int n, final int n2) {
        for (int i = 0; i < this.zzzm; ++i) {
            if (n >= this.zzzf[i] - 3 && n <= this.zzzf[i] + 3 && n2 >= this.zzzg[i] - 3 && n2 <= this.zzzg[i] + 3) {
                return true;
            }
        }
        return false;
    }
    
    public String toString() {
        return this.zzzd;
    }
}
