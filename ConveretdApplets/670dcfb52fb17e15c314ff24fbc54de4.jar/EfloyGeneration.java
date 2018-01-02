// 
// Decompiled by Procyon v0.5.30
// 

public class EfloyGeneration
{
    long num;
    long steps;
    int time;
    int wid;
    int wfitness;
    int wenergy;
    int wsafety;
    int wcooperation;
    String wchrom;
    float wspeed;
    float wacc;
    float speedavg;
    float accavg;
    float dllavg;
    float dlsavg;
    float dslavg;
    float cllavg;
    float clsavg;
    float cslavg;
    
    public EfloyGeneration() {
        this.num = 0L;
        this.steps = 0L;
        this.time = 0;
        this.wid = 0;
        this.wfitness = 0;
        this.wenergy = 0;
        this.wsafety = 0;
        this.wcooperation = 0;
        this.wchrom = "";
        this.wspeed = 0.0f;
        this.wacc = 0.0f;
        this.speedavg = 0.0f;
        this.accavg = 0.0f;
        this.dllavg = 0.0f;
        this.dlsavg = 0.0f;
        this.dslavg = 0.0f;
        this.cllavg = 0.0f;
        this.clsavg = 0.0f;
        this.cslavg = 0.0f;
    }
    
    public EfloyGeneration(final long Gnum, final long Gsteps, final int Gtime, final int Wid, final String Wchrom, final int Wfit, final int We, final int Ws, final int Wc, final float Wspeed, final float Wacc, final float Aspeed, final float Aacc, final float Adll, final float Adls, final float Adsl, final float Acll, final float Acls, final float Acsl) {
        this.num = Gnum;
        this.steps = Gsteps;
        this.time = Gtime;
        this.wid = Wid;
        this.wfitness = Wfit;
        this.wenergy = We;
        this.wsafety = Ws;
        this.wcooperation = Wc;
        this.wchrom = Wchrom;
        this.wspeed = Wspeed;
        this.wacc = Wacc;
        this.speedavg = Aspeed;
        this.accavg = Aacc;
        this.dllavg = Adll;
        this.dlsavg = Adls;
        this.dslavg = Adsl;
        this.cllavg = Acll;
        this.clsavg = Acls;
        this.cslavg = Acsl;
    }
}
