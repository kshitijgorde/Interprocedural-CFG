// 
// Decompiled by Procyon v0.5.30
// 

class dse
{
    public int als;
    public int ali;
    public int alo;
    public int[] alp;
    public int[] alz;
    public int[] ala;
    public int[] ale;
    public int[] alw;
    public int[] alf;
    public int[] alg;
    
    public dse(final int n) {
        this.alp = new int[n];
        this.ala = new int[n];
        this.ale = new int[n];
        this.alw = new int[n];
        this.alf = new int[n];
        this.alg = new int[n];
        this.alz = new int[n];
        for (int i = 0; i < n; ++i) {
            this.alz[i] = 0;
        }
    }
}
