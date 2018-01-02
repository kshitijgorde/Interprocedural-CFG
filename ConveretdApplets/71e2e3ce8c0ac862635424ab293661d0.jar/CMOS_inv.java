import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CMOS_inv extends Applet
{
    Color mos_background;
    Image techlogo;
    int clicks;
    int x;
    int xsize;
    int ysize;
    String s;
    Voltage v_vcc;
    Voltage v_gnd;
    Voltage v_in;
    Voltage v_out;
    Wire w_in;
    Wire w_out;
    Wire w_VCC;
    Wire w_GND;
    MOS pmos1;
    MOS nmos1;
    
    public void init() {
        this.resize(this.xsize, this.ysize);
        this.initColors();
        this.v_vcc = new Voltage(1);
        this.v_gnd = new Voltage(0);
        this.v_in = new Voltage(0);
        this.v_out = new Voltage(1);
        (this.w_in = new Wire(100, 160, 150, 160, this.v_in, "A")).append(150, 130);
        this.w_in.append(150, 189);
        this.w_in.addSolderDot(150, 160);
        this.w_in.addSolderDot(100, 160);
        (this.w_out = new Wire(200, 160, 250, 160, this.v_out, "Y")).addSolderDot(200, 160);
        this.w_out.addSolderDot(250, 160);
        (this.w_VCC = new Wire(100, 100, 250, 100, this.v_vcc, "VCC")).addSolderDot(200, 100);
        (this.w_GND = new Wire(100, 220, 250, 220, this.v_gnd, "GND")).addSolderDot(200, 220);
        (this.pmos1 = new MOS(150, 100, 2, "T1", this.v_vcc, this.v_in, this.v_out)).setBackground(this.mos_background);
        (this.nmos1 = new MOS(150, 160, 1, "T2", this.v_gnd, this.v_in, this.v_out)).setBackground(this.mos_background);
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
        if (this.techlogo == null) {
            this.techlogo = this.getImage(this.getCodeBase(), "images/inv.gif");
        }
        if (this.techlogo != null) {
            graphics.drawImage(this.techlogo, this.xsize - 129 - 6, 6, this);
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
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.drawBorder(graphics);
        this.drawExplanation(graphics, 10, 270);
        graphics.setColor(Color.black);
        graphics.drawString("CMOS inverter demo:  Y = !A", 10, 20);
        graphics.drawString("[Click mouse to toggle input voltage:]", 10, 40);
        this.w_in.drawWire(graphics);
        this.w_in.drawLabel(graphics, 0);
        this.w_out.drawWire(graphics);
        this.w_out.drawLabel(graphics, 1);
        this.w_VCC.drawWire(graphics);
        this.w_VCC.drawLabel(graphics, 0);
        this.w_GND.drawWire(graphics);
        this.w_GND.drawLabel(graphics, 0);
        this.pmos1.drawMOS(graphics);
        this.nmos1.drawMOS(graphics);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        ++this.clicks;
        if (this.v_in.get() == 1) {
            this.v_in.set(0);
            this.v_out.set(1);
        }
        else if (this.v_in.get() == 0) {
            this.v_in.set(3);
            this.v_out.set(4);
        }
        else {
            this.v_in.set(1);
            this.v_out.set(0);
        }
        this.pmos1.setVoltages(this.v_vcc, this.v_in, this.v_out);
        this.nmos1.setVoltages(this.v_gnd, this.v_in, this.v_out);
        this.repaint();
        return false;
    }
    
    public CMOS_inv() {
        this.mos_background = new Color(183, 183, 183);
        this.xsize = 500;
        this.ysize = 315;
    }
}
