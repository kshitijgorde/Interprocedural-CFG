import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class BioGraph2 extends Canvas implements Runnable
{
    Thread graphThread;
    boolean running;
    int emot;
    int phys;
    int intel;
    int emotT;
    int physT;
    int intelT;
    boolean emoDone;
    boolean intelDone;
    boolean physDone;
    int delay;
    Graphics gbuff;
    Image buff;
    BioCeleb par;
    int frame;
    int width;
    int height;
    boolean alive;
    String comment;
    int type;
    double h;
    double w;
    int f;
    
    public BioGraph2(final BioCeleb par, final int width, final int height, final double h, final double w, final int type) {
        this.frame = 0;
        this.alive = true;
        this.type = 1;
        this.f = 1;
        this.par = par;
        this.type = type;
        this.graphThread = new Thread(this);
        this.width = width;
        this.height = height;
        this.h = h;
        this.w = w;
        this.delay = 20;
        this.buff = par.createImage(width, height);
        this.gbuff = this.buff.getGraphics();
        this.graphThread.start();
    }
    
    public void animate() {
        this.running = true;
    }
    
    private String getComment() {
        final int n = this.intel + this.phys + this.emot;
        if (n < 1) {
            return this.par.str[18];
        }
        if (n < 50) {
            return this.par.str[19];
        }
        if (n < 100) {
            return this.par.str[20];
        }
        if (n < 150) {
            return this.par.str[21];
        }
        if (n < 200) {
            return this.par.str[22];
        }
        if (n < 250) {
            return this.par.str[23];
        }
        if (n < 300) {
            return this.par.str[24];
        }
        if (n == 300) {
            return this.par.str[25];
        }
        return "Error";
    }
    
    public void kill() {
        this.alive = false;
        this.graphThread = null;
    }
    
    public void paint(final Graphics graphics) {
        if (this.type == 1) {
            this.gbuff.setFont(new Font("Helvetica", 0, 14));
            this.gbuff.setColor(this.par.getBackground());
            this.gbuff.fillRect(0, 0, this.width, this.height);
            this.gbuff.setColor(Color.red);
            this.gbuff.drawString(this.par.str[15], 30, 220);
            this.gbuff.setColor(Color.red.darker());
            this.gbuff.fillRect(40, (198 - this.emotT) * this.f, 42, this.emotT * this.f);
            this.gbuff.setColor(Color.red);
            this.gbuff.fillRect(40, (200 - this.emotT) * this.f, 40, this.emotT);
            this.gbuff.drawString(this.emotT + "%", 50, 180 - this.emotT);
            this.gbuff.setColor(Color.yellow);
            this.gbuff.drawString(this.par.str[16], 135, 220);
            this.gbuff.setColor(Color.yellow.darker());
            this.gbuff.fillRect(140, (198 - this.physT) * this.f, 42, this.physT);
            this.gbuff.setColor(Color.yellow);
            this.gbuff.fillRect(140, (200 - this.physT) * this.f, 40, this.physT);
            this.gbuff.drawString(this.physT + "%", 150, 180 - this.physT);
            this.gbuff.setColor(Color.green);
            this.gbuff.drawString(this.par.str[17], 240, 220);
            this.gbuff.setColor(Color.green.darker());
            this.gbuff.fillRect(240, (198 - this.intelT) * this.f, 42, this.intelT);
            this.gbuff.setColor(Color.green);
            this.gbuff.fillRect(240, (200 - this.intelT) * this.f, 40, this.intelT);
            this.gbuff.drawString(this.intelT + "%", 250, 180 - this.intelT);
            this.gbuff.setColor(this.par.getForeground());
            this.gbuff.setFont(new Font("Helvetica", 0, 14));
            this.gbuff.drawString(this.comment, 20, 20);
        }
        else if (this.type == 2) {
            this.gbuff.setFont(new Font("Helvetica", 0, 14));
            this.gbuff.setColor(this.par.getBackground());
            this.gbuff.fillRect(0, 0, this.width, this.height);
            this.gbuff.setColor(Color.red);
            this.gbuff.drawString("Emotional", 10, 35);
            this.gbuff.setColor(Color.gray);
            this.gbuff.fillArc(86, 21, 50, 50, 90, (int)(this.emotT * 3.6));
            this.gbuff.setColor(Color.red);
            this.gbuff.fillArc(85, 20, 50, 50, 90, (int)(this.emotT * 3.6));
            this.gbuff.drawString(this.emotT + "%", 135, 30);
            this.gbuff.setColor(Color.yellow);
            this.gbuff.drawString("Physical", 10, 75);
            this.gbuff.setColor(Color.gray);
            this.gbuff.fillArc(181, 61, 50, 50, 90, (int)(this.physT * 3.6));
            this.gbuff.setColor(Color.yellow);
            this.gbuff.fillArc(180, 60, 50, 50, 90, (int)(this.physT * 3.6));
            this.gbuff.drawString(this.physT + "%", 135, 80);
            this.gbuff.setColor(Color.green);
            this.gbuff.drawString("Intellectual", 10, 115);
            this.gbuff.setColor(Color.gray);
            this.gbuff.fillArc(86, 86, 50, 50, 90, (int)(this.intelT * 3.6));
            this.gbuff.setColor(Color.green);
            this.gbuff.fillArc(85, 85, 50, 50, 90, (int)(this.intelT * 3.6));
            this.gbuff.drawString(this.intelT + "%", 140, 130);
            this.gbuff.setColor(this.par.getForeground());
            this.gbuff.setFont(new Font("Helvetica", 0, 14));
            this.gbuff.drawString(this.comment, 20, 170);
        }
        graphics.drawImage(this.buff, 0, 0, (int)(this.width * this.w), (int)(this.height * this.h), this);
    }
    
    public void processData() {
        if (this.emotT != this.emot) {
            ++this.emotT;
        }
        else {
            this.emoDone = true;
        }
        if (this.physT != this.phys) {
            ++this.physT;
        }
        else {
            this.physDone = true;
        }
        if (this.intelT != this.intel) {
            ++this.intelT;
        }
        else {
            this.intelDone = true;
        }
        if (this.emoDone && this.physDone && this.intelDone) {
            this.running = false;
            this.comment = this.getComment();
            this.repaint();
        }
    }
    
    public void reset() {
        this.emoDone = false;
        this.physDone = false;
        this.intelDone = false;
        this.emotT = 0;
        this.physT = 0;
        this.intelT = 0;
        this.frame = 0;
        this.comment = this.par.str[14];
    }
    
    public void run() {
        while (this.alive) {
            while (this.running) {
                this.processData();
                ++this.frame;
                try {
                    Thread.sleep(this.delay);
                }
                catch (Exception ex) {}
                this.repaint();
            }
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex2) {}
        }
    }
    
    public void setProperties(final int emot, final int intel, final int phys) {
        this.emot = emot;
        this.intel = intel;
        this.phys = phys;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
