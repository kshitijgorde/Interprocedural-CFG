import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CMOS_tran extends Applet
{
    Color mos_background;
    Image techlogo;
    int clicks;
    int xsize;
    int ysize;
    int x;
    String s;
    Voltage v_source_p;
    Voltage v_gate_p;
    Voltage v_drain_p;
    Voltage v_source_n;
    Voltage v_gate_n;
    Voltage v_drain_n;
    Wire w_gate_p;
    Wire w_source_p;
    Wire w_drain_p;
    Wire w_gate_n;
    Wire w_source_n;
    Wire w_drain_n;
    MOS pmos1;
    MOS nmos1;
    
    public void init() {
        this.resize(this.xsize, this.ysize);
        this.initColors();
        this.v_source_p = new Voltage(1);
        this.v_gate_p = new Voltage(1);
        this.v_drain_p = new Voltage(3);
        this.v_source_n = new Voltage(0);
        this.v_gate_n = new Voltage(0);
        this.v_drain_n = new Voltage(3);
        (this.pmos1 = new MOS(150, 100, 2, "TP", this.v_source_p, this.v_gate_p, this.v_drain_p)).setBackground(this.mos_background);
        (this.nmos1 = new MOS(300, 100, 1, "TN", this.v_source_n, this.v_gate_n, this.v_drain_n)).setBackground(this.mos_background);
        (this.w_source_p = new Wire(200, 90, 200, 100, this.v_source_p, "  S")).addSolderDot(200, 90);
        (this.w_gate_p = new Wire(140, 130, 150, 130, this.v_gate_p, "  G")).addSolderDot(140, 130);
        (this.w_drain_p = new Wire(200, 170, 200, 160, this.v_drain_p, "  D")).addSolderDot(200, 170);
        (this.w_source_n = new Wire(350, 170, 350, 160, this.v_source_n, "  S")).addSolderDot(350, 170);
        (this.w_gate_n = new Wire(290, 130, 300, 130, this.v_gate_n, "  G")).addSolderDot(290, 130);
        (this.w_drain_n = new Wire(350, 90, 350, 100, this.v_drain_n, "  D")).addSolderDot(350, 90);
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
    }
    
    public void drawExplanation(final Graphics graphics, int n, final int n2) {
        graphics.setColor(Color.black);
        graphics.drawString("Colors: ", n, n2);
        n += 70;
        graphics.setColor(Color.red);
        graphics.drawString("[VCC] ", n, n2);
        n += 60;
        graphics.setColor(Color.blue);
        graphics.drawString("[GND] ", n, n2);
        n += 60;
        graphics.setColor(Color.orange);
        graphics.drawString("[Z (floating)] ", n, n2);
        n += 100;
        graphics.setColor(Color.green);
        graphics.drawString("[Short-circuit] ", n, n2);
        n += 100;
        graphics.setColor(Color.black);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.size().width, this.size().height);
        this.drawBorder(graphics);
        this.drawExplanation(graphics, 10, 270);
        graphics.setColor(Color.black);
        graphics.drawString("CMOS transistor demo:", 10, 20);
        graphics.drawString("[Click mouse near gate and source pins to toggle voltages:]", 10, 40);
        graphics.drawString("P-type transistor", 130, 70);
        graphics.drawString("N-type transistor", 280, 70);
        this.w_source_p.drawWire(graphics);
        this.w_source_p.drawLabel(graphics, 0);
        this.w_gate_p.drawWire(graphics);
        this.w_gate_p.drawLabel(graphics, 0);
        this.w_drain_p.drawWire(graphics);
        this.w_drain_p.drawLabel(graphics, 0);
        this.w_source_n.drawWire(graphics);
        this.w_source_n.drawLabel(graphics, 0);
        this.w_gate_n.drawWire(graphics);
        this.w_gate_n.drawLabel(graphics, 0);
        this.w_drain_n.drawWire(graphics);
        this.w_drain_n.drawLabel(graphics, 0);
        this.pmos1.drawMOS(graphics);
        this.nmos1.drawMOS(graphics);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        ++this.clicks;
        if (this.w_gate_p.nearStartPoint(n, n2, 30)) {
            this.v_gate_p.invert();
        }
        if (this.w_source_p.nearStartPoint(n, n2, 30)) {
            this.v_source_p.invert();
        }
        if (this.w_gate_n.nearStartPoint(n, n2, 30)) {
            this.v_gate_n.invert();
        }
        if (this.w_source_n.nearStartPoint(n, n2, 30)) {
            this.v_source_n.invert();
        }
        this.v_drain_p.set(3);
        this.v_drain_n.set(3);
        switch (this.v_gate_n.get()) {
            case 1: {
                if (this.v_source_n.get() == 0) {
                    this.w_drain_n.setVoltage(0);
                    break;
                }
                this.w_drain_n.setVoltage(3);
                break;
            }
            case 0: {
                this.w_drain_n.setVoltage(3);
                break;
            }
            case 3: {
                this.w_drain_n.setVoltage(4);
                break;
            }
        }
        this.v_drain_p.set(3);
        switch (this.v_gate_p.get()) {
            case 0: {
                if (this.v_source_p.get() == 1) {
                    this.w_drain_p.setVoltage(1);
                    break;
                }
                this.w_drain_p.setVoltage(3);
                break;
            }
            case 1: {
                this.w_drain_p.setVoltage(3);
                break;
            }
            case 3: {
                this.w_drain_p.setVoltage(4);
                break;
            }
        }
        this.pmos1.setVoltages(this.v_source_p, this.v_gate_p, this.v_drain_p);
        this.nmos1.setVoltages(this.v_source_n, this.v_gate_n, this.v_drain_n);
        this.repaint();
        return false;
    }
    
    public CMOS_tran() {
        this.mos_background = new Color(183, 183, 183);
        this.xsize = 500;
        this.ysize = 300;
    }
}
