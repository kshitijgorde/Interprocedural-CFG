import java.awt.image.ImageObserver;
import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class a extends Panel
{
    public static final int _la = 1;
    public static final int ala = 2;
    public static final int bla = 3;
    public static final int cla = 4;
    private int dla;
    protected Insets hb;
    private int width;
    protected boolean ela;
    protected Dimension Gja;
    protected Graphics Hja;
    protected Image Ija;
    
    public a() {
        this.dla = 1;
        this.hb = new Insets(1, 3, 0, 3);
        this.width = 0;
        this.ela = true;
    }
    
    public a(final int dla, final Insets hb) {
        this.dla = 1;
        this.hb = new Insets(1, 3, 0, 3);
        this.width = 0;
        this.ela = true;
        this.dla = dla;
        this.hb = hb;
    }
    
    public a(final LayoutManager layoutManager) {
        super(layoutManager);
        this.dla = 1;
        this.hb = new Insets(1, 3, 0, 3);
        this.width = 0;
        this.ela = true;
    }
    
    public a(final LayoutManager layoutManager, final int dla, final Insets hb) {
        super(layoutManager);
        this.dla = 1;
        this.hb = new Insets(1, 3, 0, 3);
        this.width = 0;
        this.ela = true;
        this.dla = dla;
        this.hb = hb;
    }
    
    public Insets getInsets() {
        return this.hb;
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.ela) {
            final Dimension size = this.getSize();
            if (size.width > 0 && size.height > 0) {
                if (this.Ija == null || this.Hja == null || this.Gja == null || size.width != this.Gja.width || size.height != this.Gja.height) {
                    if (this.Hja != null) {
                        this.Hja.dispose();
                    }
                    if (this.Ija != null) {
                        this.Ija.flush();
                    }
                    this.Ija = this.createImage(size.width, size.height);
                    this.Hja = this.Ija.getGraphics();
                    this.Gja = size;
                }
                this._(this.Hja);
                graphics.drawImage(this.Ija, 0, 0, this);
            }
        }
        else {
            this._(graphics);
        }
        super.paint(graphics);
    }
    
    public void _(final Graphics graphics) {
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        switch (this.dla) {
            case 2: {
                graphics.draw3DRect(0, 0, this.getSize().width - 1, this.getSize().height - 1, false);
                break;
            }
            case 1: {
                graphics.draw3DRect(0, 0, this.getSize().width - 1, this.getSize().height - 1, true);
                break;
            }
            case 3: {
                graphics.draw3DRect(0, 0, this.getSize().width - 1, this.getSize().height - 1, true);
                graphics.draw3DRect(this.hb.left - 1, this.hb.top - 1, this.getSize().width - 1 - (this.hb.left + this.hb.right - 2), this.getSize().height - 1 - (this.hb.bottom + this.hb.top - 2), false);
                break;
            }
        }
        super.paint(graphics);
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = super.getPreferredSize();
        if (this.width > 0) {
            preferredSize.width = this.width;
        }
        return preferredSize;
    }
    
    public void v(final int width) {
        this.width = width;
    }
}
