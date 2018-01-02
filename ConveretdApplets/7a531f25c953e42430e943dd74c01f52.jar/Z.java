import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Event;
import java.io.PrintStream;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Z extends Applet
{
    static final int WIDTH = 550;
    static final int HEIGHT = 370;
    static int DD_width;
    static int RP_width;
    static PrintStream p;
    static Values val;
    static Sampler samp;
    static Distribution dist;
    static DistDraw DD_panel;
    static RightPanel RP_panel;
    
    static {
        Z.DD_width = 400;
        Z.RP_width = 150;
    }
    
    public void get_params() {
        try {
            Values.n = Integer.parseInt(this.getParameter("n"));
        }
        catch (NumberFormatException ex) {
            Values.n = 1;
            Z.p.println("n");
        }
        try {
            Distribution.mu = Double.valueOf(this.getParameter("mu"));
        }
        catch (NullPointerException ex2) {
            Distribution.mu = 0.0;
            Z.p.println("mu");
        }
        try {
            Distribution.alt_mu = Double.valueOf(this.getParameter("alt_mu"));
        }
        catch (NullPointerException ex3) {
            Distribution.alt_mu = 1.0;
            Z.p.println("alt");
        }
        try {
            Distribution.sigma = Double.valueOf(this.getParameter("sigma"));
        }
        catch (NullPointerException ex4) {
            Distribution.sigma = 1.0;
            Z.p.println("sigma");
        }
        try {
            Distribution.alpha = Double.valueOf(this.getParameter("alpha"));
        }
        catch (NullPointerException ex5) {
            Distribution.alpha = 0.05;
            Z.p.println("alpha");
        }
    }
    
    public void start() {
        Z.RP_panel.handleEvent(new Event(DistPanel.setButton, 1001, null));
    }
    
    public void init() {
        this.resize(550, 370);
        this.setLayout(null);
        Z.val = new Values();
        Z.samp = new Sampler();
        Z.dist = new Distribution();
        this.get_params();
        Distribution.full_parameter_change();
        Z.DD_panel = new DistDraw(Z.DD_width, 370);
        Z.RP_panel = new RightPanel(Z.RP_width, 370);
        this.showStatus("Initializing");
        System.out.println("\nClaremont Graduate University's -- Web Interface for Statistics Education (WISE)\nHypothesis testing applet\nVersion 1.0b\nWPA version: Updated April, 1999\nDale.Berger@cgu.edu");
        this.add(Z.DD_panel);
        Z.DD_panel.reshape(0, 0, 400, 370);
        this.add(Z.RP_panel);
        Z.RP_panel.reshape(400, 0, Z.RP_width, 370);
    }
}
