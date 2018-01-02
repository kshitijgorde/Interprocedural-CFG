import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CMOS_nand2 extends Applet
{
    Color mos_background;
    int clicks;
    int xt;
    int yt;
    int xsize;
    int ysize;
    String s;
    Image techlogo;
    FuncTable FT;
    Voltage v_vcc;
    Voltage v_gnd;
    Voltage v_in_a;
    Voltage v_in_b;
    Voltage v_out;
    Voltage v_int;
    Wire w_in_a;
    Wire w_in_b;
    Wire w_out;
    Wire w_VCC;
    Wire w_GND;
    MOS pmos1;
    MOS pmos2;
    MOS nmos1;
    MOS nmos2;
    
    public void init_FT() {
        this.FT = new FuncTable(4, " B A   Y ", 400, 200, 13);
        for (int i = 0; i < 8; ++i) {
            final boolean b = (i & 0x2) > 0;
            final boolean b2 = (i & 0x1) > 0;
            this.FT.setEntry(i, " " + (b ? "1 " : "0 ") + (b2 ? "1 " : "0 ") + "  " + ((!b2 || !b) ? "1 " : "0 "));
        }
    }
    
    private int getIndexFromInputVoltages() {
        int n = 0;
        if (this.v_in_b.get() == 1) {
            n += 2;
        }
        if (this.v_in_a.get() == 1) {
            ++n;
        }
        return n;
    }
    
    private void setInputVoltagesFromIndex(final int n) {
        if ((n & 0x1) > 0) {
            this.v_in_a.set(1);
        }
        else {
            this.v_in_a.set(0);
        }
        if ((n & 0x2) > 0) {
            this.v_in_b.set(1);
            return;
        }
        this.v_in_b.set(0);
    }
    
    public void init() {
        this.resize(this.xsize, this.ysize);
        this.initColors();
        this.init_FT();
        this.FT.setFonts(new Font("Times", 0, 12), new Font("Courier", 0, 12));
        this.v_vcc = new Voltage(1);
        this.v_gnd = new Voltage(0);
        this.v_in_a = new Voltage(0);
        this.v_in_b = new Voltage(0);
        this.v_int = new Voltage(3);
        this.v_out = new Voltage(1);
        this.pmos1 = new MOS(150, 100, 2, "T1", this.v_vcc, this.v_in_a, this.v_out);
        this.pmos2 = new MOS(230, 100, 2, "T2", this.v_vcc, this.v_in_b, this.v_out);
        this.nmos1 = new MOS(230, 180, 1, "T3", this.v_int, this.v_in_a, this.v_out);
        this.nmos2 = new MOS(230, 240, 1, "T4", this.v_gnd, this.v_in_b, this.v_int);
        this.pmos1.setBackground(this.mos_background);
        this.pmos2.setBackground(this.mos_background);
        this.nmos1.setBackground(this.mos_background);
        this.nmos2.setBackground(this.mos_background);
        (this.w_VCC = new Wire(100, 100, 350, 100, this.v_vcc, "VCC")).addSolderDot(200, 100);
        this.w_VCC.addSolderDot(280, 100);
        (this.w_GND = new Wire(100, 300, 350, 300, this.v_gnd, "GND")).addSolderDot(280, 300);
        (this.w_in_a = new Wire(100, 210, 150, 210, this.v_in_a, "A")).append(150, 130);
        this.w_in_a.append(150, 210);
        this.w_in_a.append(280, 210);
        this.w_in_a.addSolderDot(150, 210);
        this.w_in_a.addSolderDot(100, 210);
        (this.w_in_b = new Wire(100, 270, 230, 270, this.v_in_b, "B")).append(230, 270);
        this.w_in_b.append(230, 130);
        this.w_in_b.addSolderDot(230, 270);
        this.w_in_b.addSolderDot(100, 270);
        (this.w_out = new Wire(200, 160, 280, 160, this.v_out, "Y")).append(280, 180);
        this.w_out.append(280, 160);
        this.w_out.append(350, 160);
        this.w_out.addSolderDot(280, 160);
        this.w_out.addSolderDot(350, 160);
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
            this.techlogo = this.getImage(this.getCodeBase(), "images/nand2.gif");
        }
        if (this.techlogo != null) {
            graphics.drawImage(this.techlogo, this.xsize - 138 - 6, 6, this);
        }
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
        this.drawBorder(graphics);
        this.drawExplanation(graphics, 10, 370);
        graphics.setColor(Color.black);
        graphics.drawString("CMOS 2-input NAND gate demo:   Y = !(A ^ B)", 10, 20);
        graphics.drawString("[Click mouse near inputs to toggle input voltages:]", 10, 40);
        this.FT.drawFuncTable(graphics);
        this.FT.drawHighlightEntry(graphics, this.getIndexFromInputVoltages());
        this.w_VCC.drawWire(graphics);
        this.w_VCC.drawLabel(graphics, 0);
        this.w_GND.drawWire(graphics);
        this.w_GND.drawLabel(graphics, 0);
        this.w_in_a.drawWire(graphics);
        this.w_in_a.drawLabel(graphics, 0);
        this.w_in_b.drawWire(graphics);
        this.w_in_b.drawLabel(graphics, 0);
        this.w_out.drawWire(graphics);
        this.w_out.drawLabel(graphics, 1);
        this.pmos1.drawMOS(graphics);
        this.pmos2.drawMOS(graphics);
        this.nmos1.drawMOS(graphics);
        this.nmos2.drawMOS(graphics);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        ++this.clicks;
        if (this.w_in_a.nearStartPoint(n, n2, 30)) {
            this.v_in_a.invert();
        }
        else if (this.w_in_b.nearStartPoint(n, n2, 30)) {
            this.v_in_b.invert();
        }
        else {
            final int clickedEntry = this.FT.getClickedEntry(n, n2);
            if (clickedEntry >= 0) {
                this.setInputVoltagesFromIndex(clickedEntry);
            }
            else if (clickedEntry == -1) {
                int indexFromInputVoltages = this.getIndexFromInputVoltages();
                ++indexFromInputVoltages;
                this.setInputVoltagesFromIndex(indexFromInputVoltages);
            }
        }
        if (this.v_in_a.get() == 1 && this.v_in_b.get() == 1) {
            this.v_out.set(0);
        }
        else {
            this.v_out.set(1);
        }
        if (this.v_in_b.get() == 1) {
            this.v_int.set(0);
        }
        else if (this.v_in_a.get() == 1) {
            this.v_int.set(this.v_out.get());
        }
        else {
            this.v_int.set(3);
        }
        this.pmos1.setVoltages(this.v_vcc, this.v_in_a, this.v_out);
        this.pmos2.setVoltages(this.v_vcc, this.v_in_b, this.v_out);
        this.nmos1.setVoltages(this.v_int, this.v_in_a, this.v_out);
        this.nmos2.setVoltages(this.v_gnd, this.v_in_b, this.v_int);
        this.repaint();
        return false;
    }
    
    public CMOS_nand2() {
        this.mos_background = new Color(183, 183, 183);
        this.xsize = 500;
        this.ysize = 400;
    }
}
