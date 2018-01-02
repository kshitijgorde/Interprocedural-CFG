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

public class Vo extends Panel
{
    public static final int fIb = 1;
    public static final int gIb = 2;
    public static final int hIb = 3;
    public static final int iIb = 4;
    private int jIb;
    protected Insets wGb;
    private int width;
    protected boolean kIb;
    protected Dimension MDb;
    protected Graphics NDb;
    protected Image ODb;
    
    public Vo() {
        this.jIb = 1;
        this.wGb = new Insets(1, 3, 0, 3);
        this.width = 0;
        this.kIb = true;
    }
    
    public Vo(final int jIb, final Insets wGb) {
        this.jIb = 1;
        this.wGb = new Insets(1, 3, 0, 3);
        this.width = 0;
        this.kIb = true;
        this.jIb = jIb;
        this.wGb = wGb;
    }
    
    public Vo(final LayoutManager layoutManager) {
        super(layoutManager);
        this.jIb = 1;
        this.wGb = new Insets(1, 3, 0, 3);
        this.width = 0;
        this.kIb = true;
    }
    
    public Vo(final LayoutManager layoutManager, final int jIb, final Insets wGb) {
        super(layoutManager);
        this.jIb = 1;
        this.wGb = new Insets(1, 3, 0, 3);
        this.width = 0;
        this.kIb = true;
        this.jIb = jIb;
        this.wGb = wGb;
    }
    
    public Insets getInsets() {
        return this.wGb;
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.kIb) {
            final Dimension size = this.getSize();
            if (size.width > 0 && size.height > 0) {
                if (this.ODb == null || this.NDb == null || this.MDb == null || size.width != this.MDb.width || size.height != this.MDb.height) {
                    if (this.NDb != null) {
                        this.NDb.dispose();
                    }
                    if (this.ODb != null) {
                        this.ODb.flush();
                    }
                    this.ODb = this.createImage(size.width, size.height);
                    this.NDb = this.ODb.getGraphics();
                    this.MDb = size;
                }
                this.g(this.NDb);
                graphics.drawImage(this.ODb, 0, 0, this);
            }
        }
        else {
            this.g(graphics);
        }
        super.paint(graphics);
    }
    
    public void g(final Graphics graphics) {
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        switch (this.jIb) {
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
                graphics.draw3DRect(this.wGb.left - 1, this.wGb.top - 1, this.getSize().width - 1 - (this.wGb.left + this.wGb.right - 2), this.getSize().height - 1 - (this.wGb.bottom + this.wGb.top - 2), false);
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
    
    public void ba(final int width) {
        this.width = width;
    }
}
