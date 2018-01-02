// 
// Decompiled by Procyon v0.5.30
// 

class Node
{
    protected int position;
    protected int status;
    protected Node LU;
    protected Node L;
    protected Node LD;
    protected Node RU;
    protected Node R;
    protected Node RD;
    
    public Node L() {
        return this.L;
    }
    
    public Node RD() {
        return this.RD;
    }
    
    public Node RU() {
        return this.RU;
    }
    
    public Node R() {
        return this.R;
    }
    
    public Node LD() {
        return this.LD;
    }
    
    public Node LU() {
        return this.LU;
    }
    
    public void setStatus(final int s) {
        this.status = s;
    }
    
    public int getStatus() {
        return this.status;
    }
}
