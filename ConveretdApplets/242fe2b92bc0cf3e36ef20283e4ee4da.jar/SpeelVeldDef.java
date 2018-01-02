// 
// Decompiled by Procyon v0.5.30
// 

public class SpeelVeldDef
{
    private int[][] v;
    
    public SpeelVeldDef() {
        this.v = new int[30][20];
    }
    
    public int getVeld(final int n, final int n2) {
        return this.v[n + 4][n2 + 3];
    }
    
    public void setVeld(final int n, final int n2, final int n3) {
        this.v[n + 4][n2 + 3] = n3;
    }
}
