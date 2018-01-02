import java.util.Observer;

// 
// Decompiled by Procyon v0.5.30
// 

class instanceof extends Thread
{
    private this bqa;
    private String url;
    boolean cqa;
    private final implements da;
    
    instanceof(final implements da, final String url, final boolean b) {
        this.da = da;
        this.url = url;
        (this.bqa = new this())._(b);
        this.bqa.addObserver(da);
        this.cqa = false;
    }
    
    public void run() {
        this.cqa = this.bqa.n(this.url);
    }
    
    public boolean f() {
        return this.cqa;
    }
    
    public this a() {
        return this.bqa;
    }
}
