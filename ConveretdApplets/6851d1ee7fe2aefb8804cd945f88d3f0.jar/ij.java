import java.util.Observable;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.util.Observer;

// 
// Decompiled by Procyon v0.5.30
// 

public class ij extends a implements Observer
{
    private int gb;
    
    public ij() {
        this.gb = 0;
    }
    
    public ij(final int n, final Insets insets) {
        super(n, insets);
        this.gb = 0;
    }
    
    public ij(final LayoutManager layoutManager) {
        super(layoutManager);
        this.gb = 0;
    }
    
    public ij(final LayoutManager layoutManager, final int n, final Insets insets) {
        super(layoutManager, n, insets);
        this.gb = 0;
    }
    
    public void _(final Graphics graphics) {
        super._(graphics);
        graphics.setColor(this.getBackground());
        graphics.fillRect(super.hb.left, super.hb.top, this.getSize().width - 2 - (super.hb.left + super.hb.right - 2), this.getSize().height - 2 - (super.hb.bottom + super.hb.top - 2));
        final int min = Math.min(this.getSize().width - 2 - (super.hb.left + super.hb.right - 2), (int)((this.getSize().width - 2 - (super.hb.left + super.hb.right - 2)) * (this.gb / 100.0)));
        graphics.setColor(this.getForeground());
        graphics.fillRect(super.hb.left, super.hb.top, min, this.getSize().height - 2 - (super.hb.bottom + super.hb.top - 2));
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public Dimension getMinimumSize() {
        final Dimension minimumSize = super.getMinimumSize();
        minimumSize.width = 100;
        return minimumSize;
    }
    
    public synchronized void update(final Observable observable, final Object o) {
        if (this.getGraphics() == null) {
            return;
        }
        this.gb = (int)o;
        this.repaint();
    }
}
