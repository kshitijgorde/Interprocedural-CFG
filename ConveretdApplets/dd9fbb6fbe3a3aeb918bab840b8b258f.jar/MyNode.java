// 
// Decompiled by Procyon v0.5.30
// 

class MyNode
{
    protected int position;
    protected int status;
    public MyNode LU;
    public MyNode L;
    public MyNode LD;
    public MyNode RU;
    public MyNode R;
    public MyNode RD;
    
    public MyNode() {
        this.LU = null;
        this.L = null;
        this.LD = null;
        this.RU = null;
        this.R = null;
        this.RD = null;
    }
    
    public void setStatus(final int s) {
        this.status = s;
    }
    
    public void setPosition(final int p) {
        this.position = p;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public int getPosition() {
        return this.position;
    }
}
