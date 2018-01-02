import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class BioGraph extends Canvas implements Runnable
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
    String[] names;
    int[] matches;
    int[] matchesT;
    boolean[] done;
    Color[] colors;
    double w;
    double h;
    
    public BioGraph(final BioCeleb par, final int width, final int height, final double w, final double h) {
        this.frame = 0;
        this.alive = true;
        this.names = new String[6];
        this.matches = new int[6];
        this.matchesT = new int[6];
        this.done = new boolean[6];
        this.colors = new Color[6];
        this.par = par;
        this.graphThread = new Thread(this);
        this.width = width;
        this.height = height;
        this.w = w;
        this.h = h;
        this.delay = 30;
        this.buff = par.createImage(width, height);
        this.gbuff = this.buff.getGraphics();
        this.colors[0] = Color.green;
        this.colors[1] = Color.red;
        this.colors[2] = Color.yellow;
        this.colors[3] = Color.blue;
        this.colors[4] = Color.cyan;
        this.colors[5] = Color.orange;
        this.graphThread.start();
    }
    
    public void animate() {
        this.running = true;
    }
    
    private String getComment() {
        return String.valueOf(this.names[0]) + " " + this.par.str[13];
    }
    
    public void kill() {
        this.alive = false;
        this.graphThread = null;
    }
    
    public void paint(final Graphics graphics) {
        this.gbuff.setFont(new Font("Helvetica", 0, 14));
        this.gbuff.setColor(this.par.getBackground());
        this.gbuff.fillRect(0, 0, this.width, this.height);
        for (int i = 0; i < 6; ++i) {
            this.gbuff.setColor(this.colors[i]);
            this.gbuff.drawString(this.names[i], 10, 10 + i * 40);
            this.gbuff.setColor(Color.black);
            this.gbuff.fillRect(171, i * 40 + 1, this.matchesT[i] * 2, 20);
            this.gbuff.setColor(this.colors[i]);
            this.gbuff.fillRect(170, i * 40, this.matchesT[i] * 2, 20);
            this.gbuff.drawString(this.matchesT[i] + "%", this.matchesT[i] * 2 + 180, 15 + i * 40);
        }
        this.gbuff.setColor(this.par.getForeground());
        this.gbuff.setFont(new Font("Helvetica", 1, 12));
        this.gbuff.drawString(this.comment, 25, 235);
        graphics.drawImage(this.buff, 0, 0, (int)(this.width * this.w), (int)(this.height * this.h), this);
    }
    
    public void processData() {
        for (int i = 0; i < 6; ++i) {
            if (this.matchesT[i] < this.matches[i]) {
                final int[] matchesT = this.matchesT;
                final int n = i;
                ++matchesT[n];
            }
            else {
                this.done[i] = true;
            }
        }
        if (this.done[0] && this.done[1] && this.done[2] && this.done[3] && this.done[4] && this.done[5]) {
            this.running = false;
            this.comment = this.getComment();
            this.repaint();
        }
    }
    
    public void reset() {
        for (int i = 0; i < 6; ++i) {
            this.matches[i] = -1;
            this.matchesT[i] = -1;
            this.done[i] = false;
            this.names[i] = "na";
        }
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
    
    public void setProperties(final String s, final int n, final int n2) {
        this.matches[n2] = n / 3;
        this.names[n2] = s;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
