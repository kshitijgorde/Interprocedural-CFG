import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class WToolTipPanel extends WPanel
{
    public static final Object FLOATING;
    protected Component I;
    public boolean acceptTooltip;
    
    public WToolTipPanel(final Component component) {
        super(null);
        this.I = null;
        this.acceptTooltip = true;
        if (component != null) {
            this.add(component);
        }
    }
    
    protected final void addImpl(final Component i, final Object o, int n) {
        if (o != WToolTipPanel.FLOATING) {
            if (this.I != null) {
                this.remove(this.I);
            }
            this.I = i;
            n = -1;
        }
        else if (n == -1) {
            n = 0;
        }
        super.addImpl(i, o, n);
    }
    
    public final void setLayout(final LayoutManager layoutManager) {
    }
    
    public final void doLayout() {
        final Dimension size = this.getSize();
        if (this.I != null) {
            this.I.setBounds(0, 0, size.width, size.height);
        }
    }
    
    public final Dimension getPreferredSize() {
        if (this.I != null) {
            return this.I.getPreferredSize();
        }
        return new Dimension(10, 10);
    }
    
    public final Dimension getMaximumSize() {
        if (this.I != null) {
            return this.I.getMaximumSize();
        }
        return new Dimension(32767, 32767);
    }
    
    public final Dimension getMinimumSize() {
        if (this.I != null) {
            return this.I.getMinimumSize();
        }
        return new Dimension(0, 0);
    }
    
    public final void remove(final Component component) {
        if (component == this.I) {
            this.I = null;
        }
        super.remove(component);
    }
    
    public final void remove(final int n) {
        if (this.getComponent(n) == this.I) {
            this.I = null;
        }
        super.remove(n);
    }
    
    public final void removeAll() {
        this.I = null;
        super.removeAll();
    }
    
    static {
        FLOATING = new Object();
    }
}
