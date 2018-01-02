// 
// Decompiled by Procyon v0.5.30
// 

package com.javathings.math;

public final class zzztm
{
    public static int zzzhm;
    public static int zzzim;
    public static int zzzjm;
    public static int zzzkm;
    public static int zzzlm;
    private String zzzmm;
    private zzztm zzznm;
    private zzztm zzzom;
    private int zzzpm;
    private int zzzqm;
    private double zzzrm;
    private String zzzsm;
    
    public zzztm(final String zzzmm, final zzztm zzznm, final zzztm zzzom) {
        this.zzzmm = "";
        this.zzzqm = zzztm.zzzlm;
        this.zzzrm = Double.NaN;
        this.zzzsm = "";
        this.zzznm = zzznm;
        this.zzzom = zzzom;
        this.zzzmm = zzzmm;
        this.zzzpm = 2;
        this.zzzqm = zzztm.zzzjm;
    }
    
    public zzztm(final String zzzmm, final zzztm zzznm) {
        this.zzzmm = "";
        this.zzzqm = zzztm.zzzlm;
        this.zzzrm = Double.NaN;
        this.zzzsm = "";
        this.zzznm = zzznm;
        this.zzzom = this.zzzom;
        this.zzzmm = zzzmm;
        this.zzzpm = 1;
        this.zzzqm = zzztm.zzzjm;
    }
    
    public zzztm(final String zzzsm) {
        this.zzzmm = "";
        this.zzzqm = zzztm.zzzlm;
        this.zzzrm = Double.NaN;
        this.zzzsm = "";
        this.zzzsm = zzzsm;
        this.zzzqm = zzztm.zzzhm;
    }
    
    public zzztm(final double zzzrm) {
        this.zzzmm = "";
        this.zzzqm = zzztm.zzzlm;
        this.zzzrm = Double.NaN;
        this.zzzsm = "";
        this.zzzrm = zzzrm;
        this.zzzqm = zzztm.zzzim;
    }
    
    public String zzzfm() {
        return this.zzzmm;
    }
    
    public double zzzem() {
        return this.zzzrm;
    }
    
    public String zzzdm() {
        return this.zzzsm;
    }
    
    public int zzzgm() {
        return this.zzzpm;
    }
    
    public int zzzcm() {
        return this.zzzqm;
    }
    
    public zzztm zzzbm() {
        return this.zzznm;
    }
    
    public zzztm zzzam() {
        return this.zzzom;
    }
    
    static {
        zzztm.zzzhm = 1;
        zzztm.zzzim = 2;
        zzztm.zzzjm = 3;
        zzztm.zzzkm = 4;
        zzztm.zzzlm = -1;
    }
}
