import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Julia extends Applet implements Runnable
{
    static final int H_SIZE = 600;
    static final int V_SIZE = 330;
    static Thread initRunner;
    JuliaCanvas juliaCanvas;
    MandelCanvas mandelCanvas;
    ControlPanel p;
    Image mandel_img;
    
    public void init() {
        this.resize(600, 330);
        this.setFont(new Font("TimesRoman", 0, 12));
        this.setBackground(Color.white);
        this.setForeground(Color.black);
        this.setLayout(new BorderLayout());
        this.juliaCanvas = new JuliaCanvas(300);
        this.mandel_img = this.getImage(this.getCodeBase(), "mandel.gif");
        this.mandelCanvas = new MandelCanvas(this.mandel_img, this.juliaCanvas, this.p, 300);
        final Panel panel = new Panel();
        this.add("Center", panel);
        panel.setLayout(new GridLayout(1, 2, 2, 2));
        panel.add(this.mandelCanvas);
        panel.add(this.juliaCanvas);
        (this.p = new ControlPanel(this.juliaCanvas, this.mandelCanvas)).resize(400, 30);
        this.add("South", this.p);
        this.show();
    }
    
    public void start() {
        if (Julia.initRunner == null) {
            (Julia.initRunner = new Thread(this)).setPriority(1);
            Julia.initRunner.start();
        }
    }
    
    public synchronized void stop() {
        if (JuliaCanvas.juliaRunner != null) {
            JuliaCanvas.juliaRunner.stop();
        }
        JuliaCanvas.juliaRunner = null;
        if (this.mandelCanvas.canvasRunner != null) {
            this.mandelCanvas.canvasRunner.stop();
        }
        this.mandelCanvas.canvasRunner = null;
        if (Julia.initRunner != null) {
            Julia.initRunner.stop();
        }
        Julia.initRunner = null;
    }
    
    public void run() {
        for (int i = 0; i < 5000; ++i) {
            this.juliaCanvas.points[i] = new ComplexNumber(5.0);
        }
    }
}
