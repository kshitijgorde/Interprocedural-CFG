import java.awt.Event;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class TowerPanel extends Panel implements Runnable
{
    Thread going;
    int width;
    int height;
    int tower_height;
    int tower_width;
    int num_rings;
    int pick_ring;
    int tempx;
    int tempy;
    boolean win;
    boolean reset;
    String s;
    char[] separated;
    int x_coord;
    int y_coord;
    Towering towers;
    ring rings;
    
    TowerPanel() {
        this.width = 500;
        this.height = 400;
        this.tower_height = 350;
        this.tower_width = 20;
        this.num_rings = 8;
        this.pick_ring = 1000;
        this.win = false;
        this.s = new String("Tower of Hanoi");
        this.towers = new Towering(this.num_rings);
        this.rings = new ring(this.num_rings);
        this.setFont(new Font("TimesRoman", 1, 40));
        this.setBackground(new Color(170, 170, 170));
        this.separated = new char[this.s.length()];
        this.s.getChars(0, this.s.length(), this.separated, 0);
    }
    
    public void paint(final Graphics graphics) {
        for (int i = 0; i < this.s.length(); ++i) {
            this.x_coord = (int)(Math.random() * 10.0 + 15 * i + 100.0);
            this.y_coord = (int)(Math.random() * 10.0 + 36.0);
            graphics.setColor(new Color(200 - 10 * i, 10 * i, 10 * i));
            graphics.drawChars(this.separated, i, 1, this.x_coord, this.y_coord);
        }
        graphics.setColor(Color.red);
        graphics.fill3DRect(90, this.height - this.tower_height, this.tower_width, this.tower_height, true);
        graphics.setColor(Color.blue);
        graphics.fill3DRect(240, this.height - this.tower_height, this.tower_width, this.tower_height, true);
        graphics.setColor(Color.yellow);
        graphics.fill3DRect(390, this.height - this.tower_height, this.tower_width, this.tower_height, true);
        graphics.setColor(Color.black);
        for (int j = 0; j < this.num_rings; ++j) {
            graphics.setColor(new Color(20 * j + 55, 200 - 20 * j, 20 * j + 55));
            graphics.fill3DRect(this.rings.which_x[j] - this.rings.ring_size[j] / 2, this.rings.which_y[j], this.rings.ring_size[j], 30, true);
        }
        if (this.win) {
            graphics.setColor(Color.white);
            graphics.drawString("Congratulation!!!", 150, 150);
        }
    }
    
    public synchronized boolean mouseDown(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.num_rings; ++i) {
            if (this.rings.order[i] == this.towers.num_rings[this.rings.which_tower[i]] && n >= this.rings.which_x[i] - this.rings.ring_size[i] / 2 && n <= this.rings.which_x[i] - this.rings.ring_size[i] / 2 + this.rings.ring_size[i] && n2 >= this.rings.which_y[i] && n2 <= this.rings.which_y[i] + 30) {
                this.pick_ring = i;
            }
        }
        return true;
    }
    
    public synchronized boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.pick_ring != 1000) {
            this.rings.which_x[this.pick_ring] = n;
            this.rings.which_y[this.pick_ring] = n2;
            this.repaint();
        }
        return true;
    }
    
    public synchronized boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.pick_ring != 1000) {
            if (this.rings.which_x[this.pick_ring] <= 140 && this.rings.which_x[this.pick_ring] + this.rings.ring_size[this.pick_ring] >= 100 && this.rings.ring_size[this.pick_ring] < this.towers.top_size[0]) {
                final int[] num_rings = this.towers.num_rings;
                final int n3 = 0;
                ++num_rings[n3];
                final int[] num_rings2 = this.towers.num_rings;
                final int n4 = this.rings.which_tower[this.pick_ring];
                --num_rings2[n4];
                this.rings.which_tower[this.pick_ring] = 0;
                this.rings.which_x[this.pick_ring] = 100;
                this.rings.order[this.pick_ring] = this.towers.num_rings[0];
                this.rings.which_y[this.pick_ring] = 400 - 30 * this.towers.num_rings[0];
            }
            else if (this.rings.which_x[this.pick_ring] <= 280 && this.rings.which_x[this.pick_ring] + this.rings.ring_size[this.pick_ring] >= 240 && this.rings.ring_size[this.pick_ring] < this.towers.top_size[1]) {
                final int[] num_rings3 = this.towers.num_rings;
                final int n5 = 1;
                ++num_rings3[n5];
                final int[] num_rings4 = this.towers.num_rings;
                final int n6 = this.rings.which_tower[this.pick_ring];
                --num_rings4[n6];
                this.rings.which_tower[this.pick_ring] = 1;
                this.rings.which_x[this.pick_ring] = 250;
                this.rings.order[this.pick_ring] = this.towers.num_rings[1];
                this.rings.which_y[this.pick_ring] = 400 - 30 * this.towers.num_rings[1];
            }
            else if (this.rings.which_x[this.pick_ring] <= 430 && this.rings.which_x[this.pick_ring] + this.rings.ring_size[this.pick_ring] >= 390 && this.rings.ring_size[this.pick_ring] < this.towers.top_size[2]) {
                final int[] num_rings5 = this.towers.num_rings;
                final int n7 = 2;
                ++num_rings5[n7];
                final int[] num_rings6 = this.towers.num_rings;
                final int n8 = this.rings.which_tower[this.pick_ring];
                --num_rings6[n8];
                this.rings.which_tower[this.pick_ring] = 2;
                this.rings.which_x[this.pick_ring] = 400;
                this.rings.order[this.pick_ring] = this.towers.num_rings[2];
                this.rings.which_y[this.pick_ring] = 400 - 30 * this.towers.num_rings[2];
            }
            else {
                this.rings.which_x[this.pick_ring] = this.rings.old_x[this.pick_ring];
                this.rings.which_y[this.pick_ring] = 400 - 30 * this.towers.num_rings[this.rings.which_tower[this.pick_ring]];
            }
        }
        this.rings.old_x[this.pick_ring] = this.rings.which_x[this.pick_ring];
        this.pick_ring = 1000;
        for (int i = 0; i < this.num_rings; ++i) {
            if (this.rings.order[i] == this.towers.num_rings[this.rings.which_tower[i]]) {
                this.towers.top_size[this.rings.which_tower[i]] = this.rings.ring_size[i];
            }
        }
        for (int j = 0; j < 3; ++j) {
            if (j != 0 && this.towers.num_rings[j] == this.num_rings) {
                this.win = true;
            }
            if (this.towers.num_rings[j] == 0) {
                this.towers.top_size[j] = 1000;
            }
        }
        this.repaint();
        return true;
    }
    
    public void start() {
        if (this.going == null) {
            (this.going = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.going = null;
    }
    
    public void run() {
        while (this.going != null) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            this.repaint(1L, 0, 0, 500, 45);
        }
        this.going = null;
    }
    
    public void reset(final boolean b, final int num_rings) {
        this.win = false;
        this.num_rings = num_rings;
        this.towers.reset(this.num_rings);
        this.rings.reset(this.num_rings);
        this.repaint();
    }
}
