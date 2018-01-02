import java.util.Observable;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.util.Observer;

// 
// Decompiled by Procyon v0.5.30
// 

public class lea extends continue implements Observer
{
    private int x;
    
    public lea() {
        this.x = 0;
    }
    
    public lea(final int n, final Insets insets) {
        super(n, insets);
        this.x = 0;
    }
    
    public lea(final LayoutManager layoutManager) {
        super(layoutManager);
        this.x = 0;
    }
    
    public lea(final LayoutManager layoutManager, final int n, final Insets insets) {
        super(layoutManager, n, insets);
        this.x = 0;
    }
    
    public void _(final Graphics graphics) {
        super._(graphics);
        graphics.setColor(this.getBackground());
        graphics.fillRect(super.y.left, super.y.top, this.getSize().width - 2 - (super.y.left + super.y.right - 2), this.getSize().height - 2 - (super.y.bottom + super.y.top - 2));
        final int min = Math.min(this.getSize().width - 2 - (super.y.left + super.y.right - 2), (int)((this.getSize().width - 2 - (super.y.left + super.y.right - 2)) * (this.x / 100.0)));
        graphics.setColor(this.getForeground());
        graphics.fillRect(super.y.left, super.y.top, min, this.getSize().height - 2 - (super.y.bottom + super.y.top - 2));
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
        this.x = (int)o;
        this.repaint();
    }
}