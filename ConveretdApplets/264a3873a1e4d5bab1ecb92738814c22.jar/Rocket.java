import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

class Rocket
{
    public boolean sleep;
    private int energy;
    private int patch;
    private int length;
    private int mx;
    private int my;
    private int gravity;
    private int ox;
    private int oy;
    private int[] vx;
    private int[] vy;
    private int x;
    private int y;
    private int red;
    private int blue;
    private int green;
    private int t;
    private Random random;
    
    public Rocket(final int mx, final int my, final int gravity) {
        this.sleep = true;
        this.mx = mx;
        this.my = my;
        this.gravity = gravity;
    }
    
    public void init(final int energy, final int patch, final int length, final long n) {
        this.energy = energy;
        this.patch = patch;
        this.length = length;
        this.random = new Random(n);
        this.vx = new int[this.patch];
        this.vy = new int[this.patch];
        this.red = (int)(this.random.nextDouble() * 128.0) + 128;
        this.blue = (int)(this.random.nextDouble() * 128.0) + 128;
        this.green = (int)(this.random.nextDouble() * 128.0) + 128;
        this.ox = (int)(Math.random() * this.mx / 2.0) + this.mx / 4;
        this.oy = (int)(Math.random() * this.my / 2.0) + this.my / 4;
        for (int i = 0; i < this.patch; ++i) {
            this.vx[i] = (int)(Math.random() * this.energy) - this.energy / 2;
            this.vy[i] = (int)(Math.random() * this.energy * 7.0 / 8.0) - this.energy / 8;
        }
    }
    
    public void start() {
        this.t = 0;
        this.sleep = false;
    }
    
    public void show(final Graphics graphics) {
        if (!this.sleep) {
            if (this.t < this.length) {
                final int red = (int)(this.random.nextDouble() * 64.0) - 32 + this.red;
                if (red >= 0 && red < 256) {
                    this.red = red;
                }
                final int blue = (int)(this.random.nextDouble() * 64.0) - 32 + this.blue;
                if (blue >= 0 && blue < 256) {
                    this.blue = blue;
                }
                final int green = (int)(this.random.nextDouble() * 64.0) - 32 + this.green;
                if (green >= 0 && green < 256) {
                    this.green = green;
                }
                final Color color = new Color(this.red, this.blue, this.green);
                for (int i = 0; i < this.patch; ++i) {
                    final double n = this.t / 100.0;
                    this.x = (int)(this.vx[i] * n);
                    this.y = (int)(this.vy[i] * n - this.gravity * n * n);
                    graphics.setColor(color);
                    graphics.drawLine(this.ox + this.x, this.oy - this.y, this.ox + this.x, this.oy - this.y);
                    if (this.t >= this.length / 2) {
                        for (int j = 0; j < 2; ++j) {
                            final double n2 = ((this.t - this.length / 2) * 2 + j) / 100.0;
                            this.x = (int)(this.vx[i] * n2);
                            this.y = (int)(this.vy[i] * n2 - this.gravity * n2 * n2);
                            graphics.setColor(Color.black);
                            graphics.drawLine(this.ox + this.x, this.oy - this.y, this.ox + this.x, this.oy - this.y);
                        }
                    }
                }
                ++this.t;
                return;
            }
            this.sleep = true;
        }
    }
}
