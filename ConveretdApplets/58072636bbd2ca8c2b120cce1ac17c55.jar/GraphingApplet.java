import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class GraphingApplet extends JApplet
{
    GraphingWindow gw;
    
    public void init() {
        this.gw = new GraphingWindow(this);
    }
}
