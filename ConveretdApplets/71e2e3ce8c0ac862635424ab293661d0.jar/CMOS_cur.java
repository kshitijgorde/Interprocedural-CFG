import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CMOS_cur extends Applet implements Runnable
{
    Color mos_background;
    Image invlogo;
    Image electron;
    int ex;
    int ey;
    private Graphics gr;
    private Image im;
    private boolean up;
    private boolean down;
    private long t0;
    private int time;
    Thread switch_up;
    Thread switch_down;
    int ux;
    int uy;
    int uw;
    int uh;
    int curx;
    int cury;
    ImageMover m1;
    ImageMover m2;
    ImageMover m3;
    int clicks;
    int xsize;
    int ysize;
    int tsleep;
    int startMove;
    int step;
    boolean draw_all;
    boolean moving;
    boolean switch_to_Z;
    boolean switch_to_GND;
    boolean switch_to_VCC;
    boolean electron_on_gate;
    Voltage v_vcc;
    Voltage v_gnd;
    Voltage v_in;
    Voltage v_out;
    Wire w_in;
    Wire w_out;
    Wire w_VCC;
    Wire w_GND;
    Wire w_switch_up;
    Wire w_switch_down;
    Wire w_switch_middle;
    Wire w_switch_gnd;
    Wire w_switch_vcc;
    MOS pmos1;
    MOS nmos1;
    int[] x1;
    int[] y1;
    int[] t1;
    int[] f1;
    int[] x2;
    int[] y2;
    int[] t2;
    int[] f2;
    int[] x3;
    int[] y3;
    int[] t3;
    int[] f3;
    
    public CMOS_cur() {
        this.mos_background = new Color(183, 183, 183);
        this.ex = 10;
        this.ey = 10;
        this.up = false;
        this.down = false;
        this.clicks = 0;
        this.xsize = 500;
        this.ysize = 300;
        this.tsleep = 100;
        this.startMove = 0;
        this.step = 0;
        this.draw_all = true;
        this.moving = false;
        this.switch_to_Z = false;
        this.switch_to_GND = false;
        this.switch_to_VCC = false;
        this.electron_on_gate = true;
        this.x1 = new int[] { 210, 210, 210 };
        this.y1 = new int[] { 230, 85, 85 };
        this.t1 = new int[] { 0, 1400, 1500 };
        this.f1 = new int[] { 0, 1, 0 };
        this.x2 = new int[] { 115, 115, 115, 130, 155, 155 };
        this.y2 = new int[] { 230, 190, 175, 165, 165, 165 };
        this.t2 = new int[] { 0, 2000, 3000, 3500, 4000, 4100 };
        this.f2 = new int[] { 0, 1, 1, 1, 1, 1 };
        this.x3 = new int[] { 155, 140, 130, 115, 115, 115 };
        this.y3 = new int[] { 165, 165, 165, 155, 85, 85 };
        this.t3 = new int[] { 0, 1500, 2000, 2500, 4000, 4100 };
        this.f3 = new int[] { 0, 1, 1, 1, 1, 0 };
    }
    
    public void init() {
        this.resize(this.xsize, this.ysize);
        this.initColors();
        this.im = this.createImage(this.xsize, this.ysize);
        (this.gr = this.im.getGraphics()).setFont(this.getFont());
        this.gr.setColor(this.getBackground());
        this.gr.fillRect(0, 0, this.xsize, this.ysize);
        this.invlogo = this.getImage(this.getCodeBase(), "images/inv.gif");
        this.electron = this.getImage(this.getCodeBase(), "images/electron10.gif");
        this.v_vcc = new Voltage(1);
        this.v_gnd = new Voltage(0);
        this.v_in = new Voltage(0);
        this.v_out = new Voltage(1);
        (this.w_in = new Wire(130, 160, 150, 160, this.v_in, "A")).append(150, 130);
        this.w_in.append(150, 189);
        this.w_in.addSolderDot(150, 160);
        this.w_in.addSolderDot(130, 160);
        (this.w_out = new Wire(200, 160, 250, 160, this.v_out, "Y")).addSolderDot(200, 160);
        this.w_out.addSolderDot(250, 160);
        (this.w_VCC = new Wire(100, 100, 250, 100, this.v_vcc, "VCC")).addSolderDot(200, 100);
        this.w_VCC.addSolderDot(100, 100);
        this.w_VCC.addSolderDot(250, 100);
        (this.w_GND = new Wire(100, 220, 250, 220, this.v_gnd, "GND")).addSolderDot(200, 220);
        this.w_GND.addSolderDot(100, 220);
        this.w_GND.addSolderDot(250, 220);
        (this.pmos1 = new MOS(150, 100, 2, "T1", this.v_vcc, this.v_in, this.v_out)).setBackground(this.mos_background);
        (this.nmos1 = new MOS(150, 160, 1, "T2", this.v_gnd, this.v_in, this.v_out)).setBackground(this.mos_background);
        this.w_switch_up = new Wire(110, 150, 130, 160, this.v_in, " ");
        this.w_switch_down = new Wire(110, 170, 130, 160, this.v_in, " ");
        this.w_switch_middle = new Wire(105, 160, 130, 160, this.v_in, " ");
        (this.w_switch_gnd = new Wire(110, 220, 110, 170, this.v_gnd, " ")).addSolderDot(110, 220);
        this.w_switch_gnd.addSolderDot(110, 170);
        (this.w_switch_vcc = new Wire(110, 100, 110, 150, this.v_vcc, " ")).addSolderDot(110, 100);
        this.w_switch_vcc.addSolderDot(110, 150);
        this.m1 = new ImageMover(this.x1, this.y1, this.t1, this.f1);
        this.m2 = new ImageMover(this.x2, this.y2, this.t2, this.f2);
        this.m3 = new ImageMover(this.x3, this.y3, this.t3, this.f3);
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
        graphics.setColor(Color.black);
        graphics.drawString("CMOS inverter power consumption demo: ", 10, 20);
        graphics.drawString("[Click mouse to toggle input voltage:]", 10, 40);
    }
    
    public void drawBorder(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.draw3DRect(0, 0, this.xsize - 1, this.ysize - 1, true);
        if (this.invlogo == null) {
            this.invlogo = this.getImage(this.getCodeBase(), "images/inv.gif");
        }
        else {
            graphics.drawImage(this.invlogo, this.xsize - 129 - 6, 6, this);
        }
    }
    
    public void drawGate(final Graphics graphics) {
        this.w_in.drawWire(graphics);
        this.w_out.drawWire(graphics);
        this.w_out.drawLabel(graphics, 1);
        this.w_VCC.drawWire(graphics);
        this.w_VCC.drawLabel(graphics, 0);
        this.w_GND.drawWire(graphics);
        this.w_GND.drawLabel(graphics, 0);
        this.pmos1.drawMOS(graphics);
        this.nmos1.drawMOS(graphics);
    }
    
    public void drawSwitch(final Graphics graphics, final int n) {
        this.w_switch_gnd.drawWire(graphics);
        this.w_switch_vcc.drawWire(graphics);
        if (this.up) {
            if (n > 1400) {
                this.v_in.set(1);
                this.v_out.set(0);
                this.w_switch_up.drawWire(graphics);
            }
            else if (n < 100) {
                this.v_in.set(0);
                this.v_out.set(1);
                this.w_switch_down.drawWire(graphics);
            }
            else {
                this.v_in.set(3);
                this.v_out.set(4);
                graphics.setColor(Color.orange);
                graphics.drawLine(110, 170 - 20 * n / 1400, 130, 160);
            }
        }
        else if (n > 1400) {
            this.v_in.set(0);
            this.v_out.set(1);
            this.w_switch_down.drawWire(graphics);
        }
        else if (n < 100) {
            this.v_in.set(1);
            this.v_out.set(0);
            this.w_switch_up.drawWire(graphics);
        }
        else {
            this.v_in.set(3);
            this.v_out.set(4);
            graphics.setColor(Color.orange);
            graphics.drawLine(110, 150 + 20 * n / 1400, 130, 160);
        }
        this.pmos1.setVoltages(this.v_vcc, this.v_in, this.v_out);
        this.nmos1.setVoltages(this.v_gnd, this.v_in, this.v_out);
    }
    
    public void paint(final Graphics graphics) {
        this.draw_all = true;
        this.gr.setColor(this.getBackground());
        this.gr.fillRect(0, 0, this.xsize, this.ysize);
        this.drawSwitch(this.gr, this.time);
        this.drawBorder(this.gr);
        this.drawExplanation(this.gr, 10, 270);
        this.drawGate(this.gr);
        if (this.m1.isVisible) {
            this.gr.drawImage(this.electron, this.m1.xcur, this.m1.ycur, null);
        }
        this.m1.needsRedraw = false;
        if (this.m2.isVisible && this.down) {
            this.gr.drawImage(this.electron, this.m2.xcur, this.m2.ycur, null);
        }
        if (this.m3.isVisible && this.up) {
            this.gr.drawImage(this.electron, this.m3.xcur, this.m3.ycur, null);
        }
        graphics.drawImage(this.im, 0, 0, null);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void up() {
        if (this.switch_up != null && this.switch_up.isAlive()) {
            return;
        }
        if (this.switch_up == null || !this.switch_up.isAlive()) {
            this.switch_up = new Thread(this);
        }
        this.up = true;
        this.switch_up.start();
    }
    
    public synchronized void down() {
        if (this.switch_down != null && this.switch_down.isAlive()) {
            return;
        }
        if (this.switch_down == null || !this.switch_down.isAlive()) {
            this.switch_down = new Thread(this);
        }
        this.down = true;
        this.switch_down.start();
    }
    
    public void run() {
        while (this.step < 100) {
            this.time = (int)(System.currentTimeMillis() - this.t0);
            this.draw_all = false;
            ++this.step;
            this.repaint();
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {
                break;
            }
        }
        this.draw_all = true;
        this.repaint();
        this.step = 0;
    }
    
    public void stop() {
        this.step = 0;
        this.down = false;
        this.up = false;
        if (this.switch_up != null) {
            this.switch_up = null;
        }
        if (this.switch_down != null) {
            this.switch_down = null;
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        ++this.clicks;
        this.stop();
        this.v_in.invert();
        this.v_out.invert();
        this.pmos1.setVoltages(this.v_vcc, this.v_in, this.v_out);
        this.nmos1.setVoltages(this.v_gnd, this.v_in, this.v_out);
        this.m1.start();
        this.t0 = System.currentTimeMillis();
        this.up = false;
        this.down = false;
        if (this.v_in.get() == 0) {
            this.down();
            this.m2.start();
            this.m2.isVisible = false;
        }
        else {
            this.up();
            this.m3.start();
        }
        this.repaint();
        return false;
    }
}
