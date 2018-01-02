import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CMOS_dff extends Applet
{
    Color mos_background;
    Image dfflogo;
    int clicks;
    int x;
    int xsize;
    int ysize;
    String s;
    Waveform wv_clk;
    Waveform wv_d;
    Waveform wv_q;
    Voltage v_d;
    Voltage v_q;
    Voltage v_clk;
    Voltage v_nclk;
    Voltage v_i1;
    Voltage v_i2;
    Voltage v_vcc;
    Voltage v_gnd;
    Wire w_d;
    Wire w_q;
    Wire w_clk;
    Wire w_clk1;
    Wire w_clk2;
    Wire w_nclk;
    Wire w_nclk1;
    Wire w_nclk2;
    Wire w_vcc;
    Wire w_gnd;
    Wire w_i1;
    Wire w_i2;
    TGATE t_in;
    TGATE t_feedback;
    MOS n1;
    MOS n2;
    MOS p1;
    MOS p2;
    
    public void init() {
        this.resize(this.xsize, this.ysize);
        this.initColors();
        this.v_vcc = new Voltage(1);
        this.v_gnd = new Voltage(0);
        this.v_d = new Voltage(1);
        this.v_q = new Voltage(0);
        this.v_clk = new Voltage(0);
        this.v_nclk = new Voltage(this.v_clk.getinv());
        this.v_i1 = new Voltage(0);
        this.v_i2 = new Voltage(1);
        this.t_in = new TGATE(100, 230, "T_IN", this.v_d, this.v_clk, this.v_i1);
        this.t_feedback = new TGATE(200, 100, "T_Feedback", this.v_q, this.v_nclk, this.v_i1);
        this.p1 = new MOS(200, 200, 2, "P1", this.v_vcc, this.v_i1, this.v_i2);
        this.n1 = new MOS(200, 260, 1, "N1", this.v_gnd, this.v_i1, this.v_i2);
        this.p2 = new MOS(280, 200, 2, "P2", this.v_vcc, this.v_i2, this.v_q);
        this.n2 = new MOS(280, 260, 1, "N2", this.v_gnd, this.v_i2, this.v_q);
        this.n1.setBackground(this.mos_background);
        this.n2.setBackground(this.mos_background);
        this.p1.setBackground(this.mos_background);
        this.p2.setBackground(this.mos_background);
        (this.w_vcc = new Wire(230, 200, 340, 200, this.v_vcc, "VCC")).addSolderDot(250, 200);
        this.w_vcc.addSolderDot(330, 200);
        (this.w_gnd = new Wire(230, 320, 340, 320, this.v_gnd, "GND")).addSolderDot(250, 320);
        this.w_gnd.addSolderDot(330, 320);
        (this.w_d = new Wire(70, 260, 100, 260, this.v_d, "  D")).addSolderDot(70, 260);
        (this.w_clk = new Wire(70, 100, 89, 100, this.v_clk, "  C")).addSolderDot(70, 100);
        this.w_clk1 = new Wire(130, 290, 130, 300, this.v_clk, "  C");
        this.w_clk2 = new Wire(230, 100, 230, 90, this.v_clk, "  C");
        (this.w_nclk = new Wire(120, 100, 140, 100, this.v_nclk, " nC")).addSolderDot(140, 100);
        this.w_nclk1 = new Wire(130, 230, 130, 220, this.v_nclk, " nC");
        this.w_nclk2 = new Wire(230, 160, 230, 170, this.v_nclk, " nC");
        (this.w_i1 = new Wire(160, 260, 180, 260, this.v_i1, "")).append(180, 130);
        this.w_i1.append(200, 130);
        this.w_i1.append(180, 130);
        this.w_i1.append(180, 260);
        this.w_i1.append(200, 260);
        this.w_i1.append(200, 230);
        this.w_i1.append(200, 290);
        this.w_i1.addSolderDot(180, 260);
        this.w_i1.addSolderDot(200, 260);
        (this.w_i2 = new Wire(250, 260, 280, 260, this.v_i2, " QN")).append(280, 230);
        this.w_i2.append(280, 290);
        this.w_i2.append(280, 260);
        this.w_i2.addSolderDot(280, 260);
        (this.w_q = new Wire(260, 130, 360, 130, this.v_q, "  Q")).append(360, 260);
        this.w_q.append(330, 260);
        this.w_q.append(380, 260);
        this.w_q.addSolderDot(360, 260);
        this.w_q.addSolderDot(380, 260);
        this.wv_clk = new Waveform("C", 20, 100, 380, 300, 20);
        this.wv_d = new Waveform("D", 20, 100, 400, 300, 20);
        this.wv_q = new Waveform("Q", 20, 100, 420, 300, 20);
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
        if (this.dfflogo == null) {
            this.dfflogo = this.getImage(this.getCodeBase(), "images/dlatch.gif");
        }
        if (this.dfflogo != null) {
            graphics.drawImage(this.dfflogo, this.xsize - 136 - 6, 6, this);
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
        graphics.setColor(Color.black);
        graphics.drawLine(90, 100, 90, 86);
        graphics.drawLine(90, 86, 110, 100);
        graphics.drawLine(90, 100, 90, 114);
        graphics.drawLine(90, 114, 110, 100);
        graphics.drawLine(110, 100, 119, 100);
        graphics.fillRect(112, 98, 6, 5);
        graphics.fillRect(113, 97, 4, 7);
    }
    
    private void invclk() {
        this.v_clk.invert();
        this.v_nclk.invert();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.drawBorder(graphics);
        this.drawExplanation(graphics, 10, 480);
        graphics.setColor(Color.black);
        this.wv_clk.drawWaveform(graphics);
        this.wv_d.drawWaveform(graphics);
        this.wv_q.drawWaveform(graphics);
        graphics.setColor(Color.black);
        graphics.drawString("CMOS D-latch gate demo:  if (C) { Q = D }", 10, 20);
        graphics.drawString("[Click mouse to toggle D and C voltages:]", 10, 40);
        this.w_vcc.drawWire(graphics);
        this.w_vcc.drawLabel(graphics, 0);
        this.w_gnd.drawWire(graphics);
        this.w_gnd.drawLabel(graphics, 0);
        this.w_d.drawWire(graphics);
        this.w_d.drawLabel(graphics, 0);
        this.w_clk.drawWire(graphics);
        this.w_clk.drawLabel(graphics, 0);
        this.w_clk1.drawWire(graphics);
        this.w_clk1.drawLabel(graphics, 1);
        this.w_clk2.drawWire(graphics);
        this.w_clk2.drawLabel(graphics, 1);
        this.w_nclk.drawWire(graphics);
        this.w_nclk.drawLabel(graphics, 1);
        this.w_nclk1.drawWire(graphics);
        this.w_nclk1.drawLabel(graphics, 1);
        this.w_nclk2.drawWire(graphics);
        this.w_nclk2.drawLabel(graphics, 1);
        this.w_q.drawWire(graphics);
        this.w_q.drawLabel(graphics, 1);
        this.w_i1.drawWire(graphics);
        this.w_i2.drawWire(graphics);
        this.t_in.drawTGATE(graphics);
        this.t_feedback.drawTGATE(graphics);
        this.n1.drawMOS(graphics);
        this.p1.drawMOS(graphics);
        this.n2.drawMOS(graphics);
        this.p2.drawMOS(graphics);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        ++this.clicks;
        if (this.w_d.nearStartPoint(n, n2, 30)) {
            this.v_d.invert();
        }
        else if (this.w_clk.nearStartPoint(n, n2, 30)) {
            this.invclk();
        }
        else if (this.w_nclk.nearStartPoint(n, n2, 30)) {
            this.invclk();
        }
        else if (this.w_clk1.nearStartPoint(n, n2, 30)) {
            this.invclk();
        }
        else if (this.w_clk2.nearStartPoint(n, n2, 30)) {
            this.invclk();
        }
        else if (this.w_nclk1.nearStartPoint(n, n2, 30)) {
            this.invclk();
        }
        else if (this.w_nclk2.nearStartPoint(n, n2, 30)) {
            this.invclk();
        }
        if (this.v_clk.get() == 1) {
            this.v_i1.set(this.v_d.get());
        }
        else {
            this.v_i1.set(this.v_q.get());
        }
        this.v_i2.setinv(this.v_i1.get());
        this.v_q.setinv(this.v_i2.get());
        this.n1.setVoltages(this.v_gnd, this.v_i1, this.v_i2);
        this.p1.setVoltages(this.v_vcc, this.v_i1, this.v_i2);
        this.n2.setVoltages(this.v_gnd, this.v_i2, this.v_q);
        this.p2.setVoltages(this.v_vcc, this.v_i2, this.v_q);
        this.wv_clk.append(this.v_clk);
        this.wv_d.append(this.v_d);
        this.wv_q.append(this.v_q);
        this.repaint();
        return false;
    }
    
    public CMOS_dff() {
        this.mos_background = new Color(183, 183, 183);
        this.xsize = 500;
        this.ysize = 500;
    }
}
