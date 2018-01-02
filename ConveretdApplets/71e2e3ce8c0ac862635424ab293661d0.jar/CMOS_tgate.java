import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CMOS_tgate extends Applet
{
    Color mos_background;
    Image tgatelogo;
    int clicks;
    int x;
    int xsize;
    int ysize;
    String s;
    Voltage v_source;
    Voltage v_drain;
    Voltage v_clk;
    Voltage v_nclk;
    Wire w_nclk;
    Wire w_clk;
    Wire w_source;
    Wire w_drain;
    TGATE tgate;
    
    public void init() {
        this.resize(this.xsize, this.ysize);
        this.initColors();
        this.v_source = new Voltage(1);
        this.v_drain = new Voltage(3);
        this.v_clk = new Voltage(0);
        this.v_nclk = new Voltage(this.v_clk.getinv());
        this.tgate = new TGATE(150, 120, "T1", this.v_source, this.v_clk, this.v_drain);
        (this.w_nclk = new Wire(180, 120, 180, 100, this.v_nclk, " nC")).addSolderDot(180, 100);
        (this.w_clk = new Wire(180, 180, 180, 200, this.v_clk, "  C")).addSolderDot(180, 200);
        (this.w_source = new Wire(100, 150, 150, 150, this.v_source, "  L")).addSolderDot(100, 150);
        (this.w_drain = new Wire(260, 150, 210, 150, this.v_drain, "R")).append(260, 150);
        this.w_drain.addSolderDot(260, 150);
    }
    
    public void initColors() {
        this.setBackground(this.getColor("background", Color.lightGray));
        this.mos_background = this.getColor("mos_background", new Color(183, 183, 183));
    }
    
    public Color getColor(final String s, final Color color) {
        try {
            return new Color(Integer.parseInt(this.getParameter(s), 16));
        }
        catch (Exception ex) {
            return color;
        }
    }
    
    public void drawBorder(final Graphics graphics) {
        graphics.draw3DRect(0, 0, this.xsize - 1, this.ysize - 1, true);
        if (this.tgatelogo == null) {
            this.tgatelogo = this.getImage(this.getCodeBase(), "images/tgate.gif");
        }
        if (this.tgatelogo != null) {
            graphics.drawImage(this.tgatelogo, this.xsize - 86, 6, this);
        }
    }
    
    public void drawExplanation(final Graphics graphics, final int n, final int n2) {
        int n3 = n;
        graphics.setColor(Color.black);
        graphics.drawString("Colors: ", n3, n2);
        n3 += 70;
        graphics.setColor(Color.red);
        graphics.drawString("[VCC] ", n3, n2);
        n3 += 60;
        graphics.setColor(Color.blue);
        graphics.drawString("[GND] ", n3, n2);
        n3 += 60;
        graphics.setColor(Color.orange);
        graphics.drawString("[Z (floating)] ", n3, n2);
        n3 += 100;
        graphics.setColor(Color.green);
        graphics.drawString("[Short-circuit] ", n3, n2);
        n3 += 100;
        graphics.setColor(Color.black);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.drawBorder(graphics);
        this.drawExplanation(graphics, 10, 270);
        graphics.setColor(Color.black);
        graphics.drawString("CMOS transmission gate demo:  if(C): L=R", 10, 20);
        graphics.drawString("[Click mouse to toggle voltages:]", 10, 40);
        this.w_nclk.drawWire(graphics);
        this.w_nclk.drawLabel(graphics, 1);
        this.w_clk.drawWire(graphics);
        this.w_clk.drawLabel(graphics, 1);
        this.tgate.drawTGATE(graphics);
        this.w_source.drawWire(graphics);
        this.w_source.drawLabel(graphics, 0);
        this.w_drain.drawWire(graphics);
        this.w_drain.drawLabel(graphics, 1);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        ++this.clicks;
        if (this.w_clk.nearStartPoint(n, n2, 30)) {
            this.v_clk.invert();
            this.v_nclk.invert();
        }
        else if (this.w_nclk.nearStartPoint(n, n2, 30)) {
            this.v_clk.invert();
            this.v_nclk.invert();
        }
        else if (this.w_source.nearStartPoint(n, n2, 30)) {
            if (this.v_clk.get() == 1) {
                this.v_drain.set(3);
            }
            this.v_source.next01Z();
        }
        else if (this.w_drain.nearStartPoint(n, n2, 30)) {
            if (this.v_clk.get() == 1) {
                this.v_source.set(3);
            }
            this.v_drain.next01Z();
        }
        this.repaint();
        return false;
    }
    
    public CMOS_tgate() {
        this.mos_background = new Color(183, 183, 183);
        this.xsize = 500;
        this.ysize = 315;
    }
}
