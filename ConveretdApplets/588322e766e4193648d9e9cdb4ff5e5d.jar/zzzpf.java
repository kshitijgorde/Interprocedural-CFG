import java.util.NoSuchElementException;

// 
// Decompiled by Procyon v0.5.30
// 

public final class zzzpf
{
    private zzzsb zzzmf;
    private zzzsb zzznf;
    private zzzsb zzzof;
    
    public void zzzif() {
        this.zzzof = null;
    }
    
    public String zzzhf() {
        if (this.zzzof == null) {
            this.zzzof = this.zzzmf;
        }
        else {
            this.zzzof = this.zzzof.zzzrb;
        }
        if (this.zzzof == null) {
            throw new NoSuchElementException();
        }
        return this.zzzof.zzzqb;
    }
    
    public zzzpf() {
        this.zzzmf = null;
        this.zzznf = null;
        this.zzzof = null;
    }
    
    public boolean zzzlf() {
        return this.zzzjf() != null;
    }
    
    public void zzzkf(final String s) {
        final zzzsb zzzsb = new zzzsb(s, (zzzsb)null);
        if (this.zzzmf == null) {
            final zzzsb zzzsb2 = zzzsb;
            this.zzznf = zzzsb2;
            this.zzzmf = zzzsb2;
            return;
        }
        this.zzznf.zzzrb = zzzsb;
        this.zzznf = zzzsb;
    }
    
    private zzzsb zzzjf() {
        if (this.zzzof == null) {
            return this.zzzmf;
        }
        return this.zzzof.zzzrb;
    }
}
