import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Component;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class tli_rbl extends Applet
{
    private int aW;
    private int aH;
    public Component b;
    private boolean bRun;
    
    public void init() {
        this.aW = this.getSize().width;
        this.aH = this.getSize().height;
    }
    
    public void start() {
        this.repaint();
    }
    
    private void loader() {
        this.bRun = true;
        this.setLayout(new GridLayout(1, 1));
        this.add(this.b = new tli(this));
        this.validate();
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(new Color(this.readColor("bgcolor", 13684944)));
        graphics.fillRect(0, 0, this.aW, this.aH);
        graphics.setColor(new Color(this.readColor("clr1", 16777120)));
        graphics.fill3DRect(10, 10, this.aW - 30, 25, true);
        graphics.setColor(new Color(this.readColor("clr2", 0)));
        graphics.drawString("Loading...", 15, 23);
        if (!this.bRun) {
            this.loader();
        }
    }
    
    public int readColor(final String s, int n) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            try {
                if (parameter.startsWith("#")) {
                    n = Integer.parseInt(parameter.substring(1), 16);
                }
                else {
                    n = Integer.parseInt(parameter);
                }
            }
            catch (NumberFormatException ex) {}
        }
        return n;
    }
    
    public tli_rbl() {
        this.bRun = false;
    }
}
