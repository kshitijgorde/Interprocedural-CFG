import java.awt.List;

// 
// Decompiled by Procyon v0.5.30
// 

class z8 extends Thread
{
    private String z0;
    
    public synchronized void run() {
        z1.z180 = new List();
        z1.z134 = new List();
        z1.z113 = new List();
        z1.z2(this.z0.trim().replace(' ', '_'));
    }
    
    public z8(final String z0) {
        this.z0 = "";
        this.z0 = z0;
    }
}
