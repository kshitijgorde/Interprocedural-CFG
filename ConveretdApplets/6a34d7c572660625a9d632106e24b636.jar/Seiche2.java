import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Seiche2 extends Applet
{
    InputS2Panel panel1;
    PlotS2Panel graph1;
    public static int jstart;
    public static float H;
    public static float d;
    public static float L;
    public static float T;
    
    public void init() {
        this.setLayout(new BorderLayout());
        this.add("North", new Label("           Two Coupled Basins   "));
        this.add("Center", this.graph1 = new PlotS2Panel());
        this.add("West", this.panel1 = new InputS2Panel(this.graph1));
    }
    
    static {
        Seiche2.d = 1.0f;
    }
}
