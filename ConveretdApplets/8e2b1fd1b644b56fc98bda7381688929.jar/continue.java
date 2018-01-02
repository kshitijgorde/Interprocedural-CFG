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

public class continue extends Panel
{
    public static final int Lqa = 1;
    public static final int Mqa = 2;
    public static final int Nqa = 3;
    public static final int Oqa = 4;
    private int Pqa;
    protected Insets y;
    private int width;
    protected boolean Qqa;
    protected Dimension ea;
    protected Graphics fa;
    protected Image ga;
    
    public continue() {
        this.Pqa = 1;
        this.y = new Insets(1, 3, 0, 3);
        this.width = 0;
        this.Qqa = true;
    }
    
    public continue(final int pqa, final Insets y) {
        this.Pqa = 1;
        this.y = new Insets(1, 3, 0, 3);
        this.width = 0;
        this.Qqa = true;
        this.Pqa = pqa;
        this.y = y;
    }
    
    public continue(final LayoutManager layoutManager) {
        super(layoutManager);
        this.Pqa = 1;
        this.y = new Insets(1, 3, 0, 3);
        this.width = 0;
        this.Qqa = true;
    }
    
    public continue(final LayoutManager layoutManager, final int pqa, final Insets y) {
        super(layoutManager);
        this.Pqa = 1;
        this.y = new Insets(1, 3, 0, 3);
        this.width = 0;
        this.Qqa = true;
        this.Pqa = pqa;
        this.y = y;
    }
    
    public Insets getInsets() {
        return this.y;
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.Qqa) {
            final Dimension size = this.getSize();
            if (size.width > 0 && size.height > 0) {
                if (this.ga == null || this.fa == null || this.ea == null || size.width != this.ea.width || size.height != this.ea.height) {
                    if (this.fa != null) {
                        this.fa.dispose();
                    }
                    if (this.ga != null) {
                        this.ga.flush();
                    }
                    this.ga = this.createImage(size.width, size.height);
                    this.fa = this.ga.getGraphics();
                    this.ea = size;
                }
                this._(this.fa);
                graphics.drawImage(this.ga, 0, 0, this);
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
        switch (this.Pqa) {
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
                graphics.draw3DRect(this.y.left - 1, this.y.top - 1, this.getSize().width - 1 - (this.y.left + this.y.right - 2), this.getSize().height - 1 - (this.y.bottom + this.y.top - 2), false);
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
    
    public void H(final int width) {
        this.width = width;
    }
}
