import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Waveform
{
    private int[] data;
    private int max;
    private int cur;
    private String name;
    private int xsize;
    private int ysize;
    private int xpos;
    private int ypos;
    private Color backgroundColor;
    
    Waveform(final String name, final int max) {
        this.xsize = 200;
        this.ysize = 20;
        this.xpos = 100;
        this.ypos = 100;
        this.backgroundColor = new Color(183, 183, 183);
        this.name = name;
        this.max = max;
        this.cur = 0;
        this.data = new int[max];
        for (int i = 0; i < this.max; ++i) {
            this.data[i] = 0;
        }
    }
    
    Waveform(final String name, final int max, final int xpos, final int ypos, final int xsize, final int ysize) {
        this.xsize = 200;
        this.ysize = 20;
        this.xpos = 100;
        this.ypos = 100;
        this.backgroundColor = new Color(183, 183, 183);
        this.name = name;
        this.max = max;
        this.cur = 0;
        this.data = new int[max];
        for (int i = 0; i < this.max; ++i) {
            this.data[i] = 0;
        }
        this.xpos = xpos;
        this.ypos = ypos;
        this.xsize = xsize;
        this.ysize = ysize;
    }
    
    public void append(final Voltage voltage) {
        if (this.cur < this.max) {
            this.data[this.cur] = voltage.get();
            ++this.cur;
            return;
        }
        for (int i = 1; i < this.max; ++i) {
            this.data[i - 1] = this.data[i];
        }
        this.data[this.max - 1] = voltage.get();
    }
    
    public int get(final int n) {
        if (n > 0 && n < this.max) {
            return this.data[n];
        }
        return 0;
    }
    
    public void set(final int n, final int n2) {
        if (n > 0 && n < this.max) {
            this.data[n] = n2;
        }
    }
    
    public void drawWaveform(final Graphics graphics) {
        graphics.setColor(this.backgroundColor);
        graphics.fillRect(this.xpos, this.ypos, this.xsize, this.ysize);
        graphics.setColor(Color.black);
        graphics.draw3DRect(this.xpos, this.ypos, this.xsize - 1, this.ysize - 1, false);
        graphics.setColor(Color.black);
        graphics.drawString(this.name, this.xpos + 4, this.ypos + this.ysize - 5);
        graphics.drawLine(this.xpos + 50, this.ypos + 1, this.xpos + 50, this.ypos + this.ysize - 4);
        int n = this.xpos + 51;
        final int n2 = (this.xsize - 51) / this.max;
        final int n3 = this.ypos + 3;
        final int n4 = this.ypos + this.ysize - 4;
        final int n5 = (n3 + n4) / 2;
        final int n6 = n4 - n3;
        int n8;
        int n7 = n8 = n4;
        for (int i = 0; i < this.cur; ++i) {
            if (this.data[i] == 0) {
                n7 = n4;
                graphics.setColor(Color.blue);
                graphics.drawLine(n, n7, n + n2, n7);
            }
            else if (this.data[i] == 1) {
                n7 = n3;
                graphics.setColor(Color.red);
                graphics.drawLine(n, n7, n + n2, n7);
            }
            else if (this.data[i] == 3) {
                n7 = n5;
                graphics.setColor(Color.orange);
                graphics.drawLine(n, n7, n + n2, n7);
            }
            else {
                graphics.setColor(Color.green);
                graphics.fillRect(n, n3, n + n2, n6);
            }
            if (n7 != n8) {
                graphics.setColor(Color.black);
            }
            graphics.drawLine(n, n7, n, n8);
            n8 = n7;
            n += n2;
        }
        graphics.setColor(Color.black);
    }
}
