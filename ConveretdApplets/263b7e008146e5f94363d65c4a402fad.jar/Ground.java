import java.awt.image.ImageObserver;
import java.awt.Graphics;
import gamelib.PaletteFactory;
import java.awt.Rectangle;
import gamelib.ActionBuffer;
import java.awt.Image;
import gamelib.Pixmap;
import gamelib.OffComponent;

// 
// Decompiled by Procyon v0.5.30
// 

class Ground extends OffComponent
{
    Pixmap map;
    private Image img;
    private int[] hdata;
    private int w;
    private int h;
    private static int[][][] pdata;
    
    static {
        final int[][][] pdata = new int[7][][];
        pdata[0] = new int[][] { { 0, 70, 45, 13 }, { 63, 125, 95, 40 }, { 127, 154, 133, 76 }, { 191, 176, 163, 107 }, { 255, 189, 183, 130 } };
        final int n = 1;
        final int[][] array = { { 0, 255, 0, 0 }, { 63, 205, 0, 0 }, { 127, 138, 0, 0 }, { 191, 62, 0, 0 }, null };
        final int n2 = 4;
        final int[] array2 = new int[4];
        array2[0] = 255;
        array[n2] = array2;
        pdata[n] = array;
        pdata[2] = new int[][] { { 0, 32, 15, 0 }, { 63, 71, 57, 9 }, { 127, 102, 104, 21 }, { 191, 125, 147, 32 }, { 255, 143, 178, 42 } };
        pdata[3] = new int[][] { new int[4], { 63, 18, 19, 13 }, { 127, 53, 54, 44 }, { 191, 93, 93, 83 }, { 255, 126, 126, 116 } };
        pdata[4] = new int[][] { { 0, 0, 0, 255 }, { 63, 0, 255, 255 }, { 127, 0, 255, 0 }, { 191, 255, 255, 0 }, { 255, 255, 0, 0 } };
        pdata[5] = new int[][] { { 0, 255, 255, 255 }, { 127, 210, 210, 255 }, { 255, 90, 90, 255 } };
        pdata[6] = new int[][] { new int[4], { 63, 39, 12, 0 }, { 127, 83, 45, 8 }, { 191, 125, 88, 24 }, { 255, 156, 128, 44 } };
        Ground.pdata = pdata;
    }
    
    Ground(final ActionBuffer actionBuffer, final Rectangle rectangle) {
        super(actionBuffer, rectangle);
        this.w = super.bounds.width;
        this.h = super.bounds.height;
        this.hdata = new int[this.w];
        this.map = new Pixmap(this.w, this.h);
        this.generate();
        this.img = this.map.createImage(PaletteFactory.createPalette(Ground.pdata[((Field)super.buffer).sheme], true), true);
        this.setVisible(true);
    }
    
    final void generate() {
        for (int i = 0; i < this.h; ++i) {
            for (int j = 0; j < this.w; ++j) {
                this.map.setPix(j, i, 0);
            }
        }
        final int n = TankApplet.gen.nextUnsigned(this.h / 2) + this.h / 4;
        final int n2 = TankApplet.gen.nextUnsigned(this.h / 2) + this.h / 4;
        this.map.setPix(0, n, 255);
        this.map.setPix(this.w - 1, n2, 255);
        this.map.setPix(0, this.h - 1, 1);
        this.map.setPix(this.w - 1, this.h - 1, 1);
        this.iGenerate(0, this.w - 1, n, n2, this.h - 1, this.h - 1, true);
        for (int k = 0; k < this.w; ++k) {
            for (int l = 0; l < this.h; ++l) {
                if (this.map.getPix(k, l) != 0) {
                    this.hdata[k] = l;
                    break;
                }
            }
        }
        for (int n3 = 1; n3 < this.w - 1; ++n3) {
            if (this.hdata[n3] > (this.hdata[n3 - 1] + this.hdata[n3 + 1]) / 2 + 1) {
                for (int n4 = this.hdata[n3]; this.map.getPix(n3, n4) != 0 && n4 < this.h - 1; ++n4) {
                    this.map.setPix(n3, n4, 0);
                }
                this.hdata[n3] = this.hdata[n3 - 1];
            }
        }
        for (int n5 = this.hdata[0]; n5 < this.h - 1; ++n5) {
            if (this.map.getPix(0, n5) == 0) {
                this.map.setPix(0, n5, this.map.getPix(0, n5 + 1));
            }
        }
        for (int n6 = 1; n6 < this.w; ++n6) {
            if (this.map.getPix(n6, this.h - 1) == 0) {
                this.map.setPix(n6, this.h - 1, this.map.getPix(n6 - 1, this.h - 1));
            }
        }
        for (int n7 = 1; n7 < this.w - 1; ++n7) {
            for (int n8 = this.hdata[n7]; n8 < this.h - 1; ++n8) {
                if (this.map.getPix(n7, n8) == 0) {
                    if (this.map.getPix(n7, n8 + 1) != 0) {
                        this.map.setPix(n7, n8, this.map.getPix(n7, n8 + 1));
                    }
                    else {
                        this.map.setPix(n7, n8, this.map.getPix(n7 - 1, n8));
                    }
                }
            }
        }
        for (int n9 = 1; n9 < this.w - 1; ++n9) {
            for (int n10 = this.hdata[n9] + 1; n10 < this.h - 1; ++n10) {
                final int n11 = this.map.getPix(n9, n10 - 1) - this.map.getPix(n9, n10);
                final int n12 = this.map.getPix(n9, n10 + 1) - this.map.getPix(n9, n10);
                if (Math.abs(n11) > 12 && Math.abs(n12) > 12) {
                    this.map.setPix(n9, n10, this.map.getPix(n9, n10 - 1));
                }
            }
        }
        int min = 10000;
        for (int n13 = 0; n13 < this.w; ++n13) {
            min = Math.min(min, this.hdata[n13]);
        }
        this.setPosition(0, min);
        this.setSize(this.w, this.h - min);
    }
    
    final int getHorizon(int n) {
        if (n < 0) {
            n = 0;
        }
        if (n >= this.w) {
            n = this.w - 1;
        }
        return this.hdata[n];
    }
    
    private final void iGenerate(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b) {
        if (n2 - n < 2 || (!b && n2 - n < 3)) {
            return;
        }
        final int n7 = n3 + n5 >> 1;
        int n8 = n3 + n4 >> 1;
        final int n9 = n5 + n6 >> 1;
        final int n10 = n4 + n6 >> 1;
        final int n11 = n + n2 >> 1;
        if (b) {
            n8 += TankApplet.gen.nextInt() % (n2 - n) / 6;
            if (n8 >= this.h - 4) {
                n8 = this.h - 4;
            }
            else if (n8 < 50) {
                n8 = 50;
            }
        }
        int n12 = (n8 + n9) / 2 + TankApplet.gen.nextInt() % (n2 - n) / 8;
        if (n12 >= this.h - 4) {
            n12 = this.h - 4;
        }
        else if (n12 < 50) {
            n12 = 50;
        }
        this.map.setPix(n, n7, (this.map.getPix(n, n3) + this.map.getPix(n, n5)) / 2);
        this.map.setPix(n2, n10, (this.map.getPix(n2, n4) + this.map.getPix(n2, n6)) / 2);
        this.map.setPix(n11, n8, (this.map.getPix(n, n3) + this.map.getPix(n2, n4)) / 2);
        this.map.setPix(n11, n9, (this.map.getPix(n, n5) + this.map.getPix(n2, n6)) / 2);
        this.map.setBoundedPix(n11, n12, (this.map.getPix(n, n7) + this.map.getPix(n2, n10) + this.map.getPix(n11, n8) + this.map.getPix(n11, n9)) / 4 + TankApplet.gen.nextInt() % (n2 - n) / 4);
        this.iGenerate(n, n11, n3, n8, n7, n12, b);
        this.iGenerate(n11, n2, n8, n4, n12, n10, b);
        this.iGenerate(n, n11, n7, n12, n5, n9, false);
        this.iGenerate(n11, n2, n12, n10, n9, n6, false);
    }
    
    public void paint(final Graphics graphics) {
        if (this.img != null) {
            graphics.drawImage(this.img, 0, 0, super.buffer);
        }
    }
    
    final void setHorizon(final int n, int n2) {
        if (n >= 0 && n < this.w && n2 > this.hdata[n]) {
            if (n2 >= this.h) {
                n2 = this.h - 1;
            }
            final int n3 = this.hdata[n];
            this.hdata[n] = n2;
            for (int i = n3; i <= n2; ++i) {
                this.map.setPix(n, i, 0);
            }
        }
    }
    
    final void updateImage(final int n, final int n2) {
        this.map.updateImage(new Rectangle(n, 0, n2 - n + 1, this.h));
        this.repaint();
    }
}
