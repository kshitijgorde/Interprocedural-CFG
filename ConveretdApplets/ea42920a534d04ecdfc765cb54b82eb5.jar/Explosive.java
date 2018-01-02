import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.applet.AudioClip;
import gamelib.PaletteFactory;
import gamelib.ActionBuffer;
import java.awt.image.IndexColorModel;
import java.awt.Image;
import gamelib.Pixmap;
import gamelib.AVEntry;

// 
// Decompiled by Procyon v0.5.30
// 

class Explosive extends Warhead
{
    static AVEntry snd;
    private Pixmap map;
    private Image img;
    protected int x;
    protected int y;
    protected int cnt;
    private static IndexColorModel pal;
    private static int[][] pdata;
    
    static {
        Explosive.snd = null;
        Explosive.pdata = new int[][] { new int[4], { 85, 140, 0, 0 }, { 170, 255, 220, 0 }, { 255, 255, 255, 0 } };
    }
    
    Explosive(final ActionBuffer actionBuffer, final int n) {
        super(actionBuffer);
        this.setSize(n, n);
        if (Explosive.snd == null) {
            Explosive.snd = new AVEntry("Explosive.au", 1);
        }
        (this.map = new Pixmap(n, n)).fillRandom();
        for (float n2 = n, n3 = 0.0f; n3 < n2; ++n3) {
            for (float n4 = 0.0f; n4 < n2; ++n4) {
                final float n5 = (n4 - n2 / 2.0f) / (n2 / 2.0f);
                final float n6 = (n3 - n2 / 2.0f) / (n2 / 2.0f);
                this.map.mixPix((int)n4, (int)n3, (3 * (int)(255.0f - 255.0f * (n5 * n5 + n6 * n6)) + 256) / 5);
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (this.map.getPix(j, i) < 111) {
                    this.map.setPix(j, i, 0);
                }
            }
        }
        if (Explosive.pal == null) {
            Explosive.pal = PaletteFactory.createPalette(Explosive.pdata, true);
        }
        this.img = this.map.createImage(Explosive.pal, true);
    }
    
    public Object clone() {
        return new Explosive(super.buffer, super.bounds.width);
    }
    
    private void doDamage() {
        final AudioClip clip = Explosive.snd.getClip();
        if (clip != null) {
            clip.play();
        }
        final Field field = (Field)super.buffer;
        final int width = super.bounds.width;
        for (int i = -width / 3; i <= width / 3; ++i) {
            final int n = (int)Math.sqrt(width * width / 9 - i * i);
            final int horizon = field.ground.getHorizon(this.x + i);
            int n2 = this.y + n;
            if (n2 - horizon > 2 * n) {
                n2 = horizon + 2 * n;
            }
            field.ground.setHorizon(this.x + i, n2);
        }
        field.ground.updateImage(this.x - width / 3, this.x + width / 3);
        for (int j = 0; j < field.tanks.length; ++j) {
            final Tank tank = field.tanks[j];
            tank.addDamage(5.6f * width - ((this.x - tank.x) * (this.x - tank.x) + (this.y - tank.y) * (this.y - tank.y)) / 3);
        }
    }
    
    protected void go() {
        for (int i = 0; i < this.map.w; ++i) {
            for (int j = 0; j < this.map.h; ++j) {
                final int pix = this.map.getPix(i, j);
                if (pix != 0) {
                    this.map.setBoundedPix(i, j, pix - 2);
                }
            }
        }
        this.map.updateImage();
        this.repaint();
        if (this.cnt == 0) {
            this.doDamage();
        }
        if (this.cnt++ > 16) {
            this.removeSelf();
        }
    }
    
    void ignite(final int x, final int y) {
        super.ignite(x, y);
        this.x = x;
        this.y = y;
    }
    
    protected void paint(final Graphics graphics) {
        if (this.img != null) {
            graphics.drawImage(this.img, super.bounds.x, super.bounds.y, super.buffer);
        }
    }
}
