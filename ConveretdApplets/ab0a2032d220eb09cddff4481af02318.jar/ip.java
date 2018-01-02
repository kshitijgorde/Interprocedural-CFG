import java.util.Observable;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.util.Observer;

// 
// Decompiled by Procyon v0.5.30
// 

public class ip extends Vo implements Observer
{
    private int PFb;
    
    public ip() {
        this.PFb = 0;
    }
    
    public ip(final int n, final Insets insets) {
        super(n, insets);
        this.PFb = 0;
    }
    
    public ip(final LayoutManager layoutManager) {
        super(layoutManager);
        this.PFb = 0;
    }
    
    public ip(final LayoutManager layoutManager, final int n, final Insets insets) {
        super(layoutManager, n, insets);
        this.PFb = 0;
    }
    
    public void g(final Graphics graphics) {
        super.g(graphics);
        graphics.setColor(this.getBackground());
        graphics.fillRect(super.wGb.left, super.wGb.top, this.getSize().width - 2 - (super.wGb.left + super.wGb.right - 2), this.getSize().height - 2 - (super.wGb.bottom + super.wGb.top - 2));
        final int min = Math.min(this.getSize().width - 2 - (super.wGb.left + super.wGb.right - 2), (int)((this.getSize().width - 2 - (super.wGb.left + super.wGb.right - 2)) * (this.PFb / 100.0)));
        graphics.setColor(this.getForeground());
        graphics.fillRect(super.wGb.left, super.wGb.top, min, this.getSize().height - 2 - (super.wGb.bottom + super.wGb.top - 2));
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
        this.PFb = (int)o;
        this.repaint();
    }
}
