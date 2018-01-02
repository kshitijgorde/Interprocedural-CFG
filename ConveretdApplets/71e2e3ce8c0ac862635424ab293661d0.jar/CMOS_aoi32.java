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

public class CMOS_aoi32 extends Applet
{
    int clicks;
    Color mos_background;
    int xt;
    int yt;
    int xsize;
    int ysize;
    Image symbol;
    Font cfont;
    FuncTable FT;
    Voltage v_vcc;
    Voltage v_gnd;
    Voltage v_in_a;
    Voltage v_in_b;
    Voltage v_in_c;
    Voltage v_in_d;
    Voltage v_in_e;
    Voltage v_out;
    Voltage v_n_ab;
    Voltage v_n_cd;
    Voltage v_n_de;
    Voltage v_p_ac_def;
    Wire w_in_ap;
    Wire w_in_bp;
    Wire w_in_cp;
    Wire w_in_dp;
    Wire w_in_ep;
    Wire w_in_an;
    Wire w_in_bn;
    Wire w_in_cn;
    Wire w_in_dn;
    Wire w_in_en;
    Wire w_n_ac_def;
    Wire w_out;
    Wire w_VCC;
    Wire w_GND;
    MOS pmos_a;
    MOS pmos_b;
    MOS pmos_c;
    MOS pmos_d;
    MOS pmos_e;
    MOS nmos_a;
    MOS nmos_b;
    MOS nmos_c;
    MOS nmos_d;
    MOS nmos_e;
    
    public void init_FT() {
        this.FT = new FuncTable(32, " E D C B A   Y ", 500, 80, 13);
        for (int i = 0; i < 32; ++i) {
            final boolean b = (i & 0x10) > 0;
            final boolean b2 = (i & 0x8) > 0;
            final boolean b3 = (i & 0x4) > 0;
            final boolean b4 = (i & 0x2) > 0;
            final boolean b5 = (i & 0x1) > 0;
            this.FT.setEntry(i, " " + (b ? "1 " : "0 ") + (b2 ? "1 " : "0 ") + (b3 ? "1 " : "0 ") + (b4 ? "1 " : "0 ") + (b5 ? "1 " : "0 ") + "  " + (((b5 && b4) || (b3 && b2 && b)) ? "0 " : "1 "));
        }
    }
    
    private int getIndexFromInputVoltages() {
        int n = 0;
        if (this.v_in_e.get() == 1) {
            n += 16;
        }
        if (this.v_in_d.get() == 1) {
            n += 8;
        }
        if (this.v_in_c.get() == 1) {
            n += 4;
        }
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
        }
        else {
            this.v_in_b.set(0);
        }
        if ((n & 0x4) > 0) {
            this.v_in_c.set(1);
        }
        else {
            this.v_in_c.set(0);
        }
        if ((n & 0x8) > 0) {
            this.v_in_d.set(1);
        }
        else {
            this.v_in_d.set(0);
        }
        if ((n & 0x10) > 0) {
            this.v_in_e.set(1);
            return;
        }
        this.v_in_e.set(0);
    }
    
    public void init() {
        this.resize(this.xsize, this.ysize);
        this.initColors();
        this.cfont = new Font("Courier", 0, 12);
        this.init_FT();
        this.FT.setFonts(this.cfont, this.cfont);
        this.v_vcc = new Voltage(1);
        this.v_gnd = new Voltage(0);
        this.v_in_a = new Voltage(0);
        this.v_in_b = new Voltage(0);
        this.v_in_c = new Voltage(0);
        this.v_in_d = new Voltage(0);
        this.v_in_e = new Voltage(0);
        this.v_out = new Voltage(1);
        this.v_n_ab = new Voltage(3);
        this.v_n_cd = new Voltage(3);
        this.v_n_de = new Voltage(3);
        this.v_p_ac_def = new Voltage(1);
        this.pmos_a = new MOS(150, 100, 2, "pA", this.v_vcc, this.v_in_a, this.v_p_ac_def);
        this.pmos_b = new MOS(350, 100, 2, "pB", this.v_vcc, this.v_in_b, this.v_p_ac_def);
        this.pmos_c = new MOS(150, 160, 2, "pC", this.v_p_ac_def, this.v_in_c, this.v_out);
        this.pmos_d = new MOS(250, 160, 2, "pD", this.v_p_ac_def, this.v_in_d, this.v_out);
        this.pmos_e = new MOS(350, 160, 2, "pE", this.v_p_ac_def, this.v_in_e, this.v_out);
        this.nmos_e = new MOS(350, 240, 1, "nE", this.v_n_de, this.v_in_e, this.v_out);
        this.nmos_d = new MOS(350, 300, 1, "nD", this.v_n_cd, this.v_in_d, this.v_n_de);
        this.nmos_c = new MOS(350, 360, 1, "nC", this.v_gnd, this.v_in_c, this.v_n_cd);
        this.nmos_b = new MOS(150, 300, 1, "nB", this.v_n_ab, this.v_in_b, this.v_out);
        this.nmos_a = new MOS(150, 360, 1, "nA", this.v_gnd, this.v_in_a, this.v_n_ab);
        this.pmos_a.setBackground(this.mos_background);
        this.pmos_b.setBackground(this.mos_background);
        this.pmos_c.setBackground(this.mos_background);
        this.pmos_d.setBackground(this.mos_background);
        this.pmos_e.setBackground(this.mos_background);
        this.nmos_a.setBackground(this.mos_background);
        this.nmos_b.setBackground(this.mos_background);
        this.nmos_c.setBackground(this.mos_background);
        this.nmos_d.setBackground(this.mos_background);
        this.nmos_e.setBackground(this.mos_background);
        (this.w_VCC = new Wire(100, 100, 450, 100, this.v_vcc, "+5V")).addSolderDot(200, 100);
        this.w_VCC.addSolderDot(400, 100);
        (this.w_GND = new Wire(100, 420, 450, 420, this.v_gnd, " 0V")).addSolderDot(200, 420);
        this.w_GND.addSolderDot(400, 420);
        (this.w_n_ac_def = new Wire(200, 160, 400, 160, this.v_p_ac_def, "")).addSolderDot(200, 160);
        this.w_n_ac_def.addSolderDot(300, 160);
        this.w_n_ac_def.addSolderDot(400, 160);
        (this.w_in_an = new Wire(140, 130, 150, 130, this.v_in_a, "  A")).addSolderDot(140, 130);
        (this.w_in_ap = new Wire(140, 390, 150, 390, this.v_in_a, "  A")).addSolderDot(140, 390);
        (this.w_in_bn = new Wire(340, 130, 350, 130, this.v_in_b, "  B")).addSolderDot(340, 130);
        (this.w_in_bp = new Wire(140, 330, 150, 330, this.v_in_b, "  B")).addSolderDot(140, 330);
        (this.w_in_cn = new Wire(140, 190, 150, 190, this.v_in_c, "  C")).addSolderDot(140, 190);
        (this.w_in_cp = new Wire(340, 390, 350, 390, this.v_in_c, "  C")).addSolderDot(340, 390);
        (this.w_in_dn = new Wire(250, 190, 250, 190, this.v_in_d, "  D")).addSolderDot(250, 190);
        (this.w_in_dp = new Wire(340, 330, 350, 330, this.v_in_d, "  D")).addSolderDot(340, 330);
        (this.w_in_en = new Wire(350, 190, 350, 190, this.v_in_e, "  E")).addSolderDot(350, 190);
        (this.w_in_ep = new Wire(340, 270, 350, 270, this.v_in_e, "  E")).addSolderDot(340, 270);
        (this.w_out = new Wire(200, 300, 200, 220, this.v_out, "Y")).append(400, 220);
        this.w_out.append(400, 240);
        this.w_out.append(400, 220);
        this.w_out.append(450, 220);
        this.w_out.addSolderDot(200, 220);
        this.w_out.addSolderDot(300, 220);
        this.w_out.addSolderDot(400, 220);
        this.w_out.addSolderDot(450, 220);
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
        if (this.symbol == null) {
            this.symbol = this.getImage(this.getCodeBase(), "images/aoi32.gif");
        }
        if (this.symbol != null) {
            graphics.drawImage(this.symbol, this.xsize - 135 - 6, 6, this);
        }
    }
    
    public void drawExplanation(final Graphics graphics, int n, final int n2) {
        graphics.setColor(Color.black);
        graphics.drawString("Colors: ", n, n2);
        n += 70;
        graphics.setColor(Color.red);
        graphics.drawString("1 [+5V] ", n, n2);
        n += 60;
        graphics.setColor(Color.blue);
        graphics.drawString("0 [0V] ", n, n2);
        n += 60;
        graphics.setColor(Color.orange);
        graphics.drawString("[Z (floating)] ", n, n2);
        n += 110;
        graphics.setColor(Color.green);
        graphics.drawString("[Short-circuit] ", n, n2);
        n += 200;
        graphics.setColor(Color.black);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.drawBorder(graphics);
        this.drawExplanation(graphics, 10, 510);
        graphics.setColor(Color.black);
        graphics.drawString("CMOS complex AOI32 (and-or-invert) gate demo:  Y = !((A ^ B) v (C ^ D ^ E))", 10, 20);
        graphics.drawString("[Click mouse near inputs to toggle input voltages:] ", 10, 40);
        this.FT.drawFuncTable(graphics);
        this.FT.drawHighlightEntry(graphics, this.getIndexFromInputVoltages());
        this.w_VCC.drawWire(graphics);
        this.w_VCC.drawLabel(graphics, 0);
        this.w_GND.drawWire(graphics);
        this.w_GND.drawLabel(graphics, 0);
        this.w_in_an.drawWire(graphics);
        this.w_in_an.drawLabel(graphics, 0);
        this.w_in_bn.drawWire(graphics);
        this.w_in_bn.drawLabel(graphics, 0);
        this.w_in_cn.drawWire(graphics);
        this.w_in_cn.drawLabel(graphics, 0);
        this.w_in_dn.drawWire(graphics);
        this.w_in_dn.drawLabel(graphics, 0);
        this.w_in_en.drawWire(graphics);
        this.w_in_en.drawLabel(graphics, 0);
        this.w_in_ap.drawWire(graphics);
        this.w_in_ap.drawLabel(graphics, 0);
        this.w_in_bp.drawWire(graphics);
        this.w_in_bp.drawLabel(graphics, 0);
        this.w_in_cp.drawWire(graphics);
        this.w_in_cp.drawLabel(graphics, 0);
        this.w_in_dp.drawWire(graphics);
        this.w_in_dp.drawLabel(graphics, 0);
        this.w_in_ep.drawWire(graphics);
        this.w_in_ep.drawLabel(graphics, 0);
        this.w_out.drawWire(graphics);
        this.w_out.drawLabel(graphics, 1);
        this.w_n_ac_def.drawWire(graphics);
        this.pmos_a.drawMOS(graphics);
        this.pmos_b.drawMOS(graphics);
        this.pmos_c.drawMOS(graphics);
        this.pmos_d.drawMOS(graphics);
        this.pmos_e.drawMOS(graphics);
        this.nmos_a.drawMOS(graphics);
        this.nmos_b.drawMOS(graphics);
        this.nmos_c.drawMOS(graphics);
        this.nmos_d.drawMOS(graphics);
        this.nmos_e.drawMOS(graphics);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        ++this.clicks;
        if (this.w_in_an.nearStartPoint(n, n2, 30)) {
            this.v_in_a.invert();
        }
        else if (this.w_in_ap.nearStartPoint(n, n2, 30)) {
            this.v_in_a.invert();
        }
        else if (this.w_in_bn.nearStartPoint(n, n2, 30)) {
            this.v_in_b.invert();
        }
        else if (this.w_in_bp.nearStartPoint(n, n2, 30)) {
            this.v_in_b.invert();
        }
        else if (this.w_in_cn.nearStartPoint(n, n2, 30)) {
            this.v_in_c.invert();
        }
        else if (this.w_in_cp.nearStartPoint(n, n2, 30)) {
            this.v_in_c.invert();
        }
        else if (this.w_in_dn.nearStartPoint(n, n2, 30)) {
            this.v_in_d.invert();
        }
        else if (this.w_in_dp.nearStartPoint(n, n2, 30)) {
            this.v_in_d.invert();
        }
        else if (this.w_in_en.nearStartPoint(n, n2, 30)) {
            this.v_in_e.invert();
        }
        else if (this.w_in_ep.nearStartPoint(n, n2, 30)) {
            this.v_in_e.invert();
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
        if ((this.v_in_a.get() == 1 && this.v_in_b.get() == 1) || (this.v_in_c.get() == 1 && this.v_in_d.get() == 1 && this.v_in_e.get() == 1)) {
            this.v_out.set(0);
        }
        else {
            this.v_out.set(1);
        }
        this.v_n_ab.set(3);
        if (this.v_in_b.get() == 1) {
            this.v_n_ab.set(this.v_out.get());
        }
        if (this.v_in_a.get() == 1) {
            this.v_n_ab.set(0);
        }
        this.v_n_de.set(3);
        if (this.v_in_e.get() == 1) {
            this.v_n_de.set(this.v_out.get());
        }
        else if (this.v_in_d.get() == 1 && this.v_in_c.get() == 1) {
            this.v_n_de.set(0);
        }
        this.v_n_cd.set(3);
        if (this.v_in_c.get() == 1) {
            this.v_n_cd.set(0);
        }
        else if (this.v_in_d.get() == 1 && this.v_in_e.get() == 1) {
            this.v_n_cd.set(this.v_out.get());
        }
        this.v_p_ac_def.set(3);
        if (this.v_in_a.get() == 0 || this.v_in_b.get() == 0) {
            this.v_p_ac_def.set(1);
        }
        else if (this.v_in_c.get() == 0 || this.v_in_d.get() == 0 || this.v_in_e.get() == 0) {
            this.v_p_ac_def.set(this.v_out.get());
        }
        this.pmos_a.setVoltages(this.v_vcc, this.v_in_a, this.v_p_ac_def);
        this.pmos_b.setVoltages(this.v_vcc, this.v_in_b, this.v_p_ac_def);
        this.pmos_c.setVoltages(this.v_p_ac_def, this.v_in_c, this.v_out);
        this.pmos_d.setVoltages(this.v_p_ac_def, this.v_in_d, this.v_out);
        this.pmos_e.setVoltages(this.v_p_ac_def, this.v_in_e, this.v_out);
        this.nmos_a.setVoltages(this.v_gnd, this.v_in_a, this.v_n_ab);
        this.nmos_b.setVoltages(this.v_n_ab, this.v_in_b, this.v_out);
        this.nmos_c.setVoltages(this.v_gnd, this.v_in_c, this.v_n_cd);
        this.nmos_d.setVoltages(this.v_n_cd, this.v_in_d, this.v_n_de);
        this.nmos_e.setVoltages(this.v_n_de, this.v_in_e, this.v_out);
        this.repaint();
        return false;
    }
    
    public CMOS_aoi32() {
        this.mos_background = new Color(183, 183, 183);
        this.xsize = 630;
        this.ysize = 540;
    }
}
