import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.Panel;
import java.awt.event.AdjustmentListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class RGBCirs extends Applet implements AdjustmentListener
{
    Panel p;
    Scrollbar sc1;
    Scrollbar sc2;
    Scrollbar sc3;
    Font f;
    Font fonsig;
    int sr;
    int sg;
    int sb;
    
    public RGBCirs() {
        this.p = new Panel(new GridLayout(1, 3, 2, 2));
        this.sc1 = new Scrollbar(0, 0, 10, 0, 100);
        this.sc2 = new Scrollbar(0, 0, 10, 0, 100);
        this.sc3 = new Scrollbar(0, 0, 10, 0, 100);
        this.f = new Font("TimesRoman", 1, 20);
        this.fonsig = new Font("TimesRoman", 2, 10);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.sr = this.sc1.getValue();
        this.sg = this.sc2.getValue();
        this.sb = this.sc3.getValue();
        this.repaint();
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        this.p.add(this.sc1);
        this.p.add(this.sc2);
        this.p.add(this.sc3);
        this.sc1.setBackground(Color.red);
        this.sc2.setBackground(Color.green);
        this.sc3.setBackground(Color.blue);
        this.setSize(400, 400);
        this.add(this.p, "North");
        this.sc1.addAdjustmentListener(this);
        this.sc2.addAdjustmentListener(this);
        this.sc3.addAdjustmentListener(this);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(50, 50, 300, 300);
        graphics.setXORMode(Color.red);
        graphics.fillOval(150, 60 + this.sr, 100, 100);
        final int n = (int)(200.0 - (90 - this.sg) * 0.866) - 50;
        final int n2 = (int)(200.0 + (90 - this.sg) * 0.5) - 50;
        graphics.setXORMode(Color.green);
        graphics.fillOval(n, n2, 100, 100);
        final int n3 = (int)(200.0 + (90 - this.sb) * 0.866) - 50;
        final int n4 = (int)(200.0 + (90 - this.sb) * 0.5) - 50;
        graphics.setXORMode(Color.blue);
        graphics.fillOval(n3, n4, 100, 100);
        graphics.setColor(Color.red);
        graphics.setFont(this.fonsig);
        graphics.drawString("R.Mukundan/FIT/MMU.", 1, 380);
    }
}
