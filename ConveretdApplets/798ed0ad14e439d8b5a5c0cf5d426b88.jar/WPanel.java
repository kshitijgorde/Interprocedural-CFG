import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

public class WPanel extends Container implements WToolContainer
{
    public static boolean lightweight;
    public static boolean forceSwing;
    private boolean insideNotify;
    protected WToolTip addNotify;
    
    public WPanel() {
        this.insideNotify = false;
        this.addNotify = null;
    }
    
    public WPanel(final LayoutManager layout) {
        this.insideNotify = false;
        this.addNotify = null;
        this.setLayout(layout);
    }
    
    public void update(final Graphics graphics) {
        if (this.isShowing()) {
            this.paint(graphics);
        }
    }
    
    public WToolTip getToolTipComp() {
        return this.addNotify;
    }
    
    public void setToolTipComp(final WToolTip addNotify) {
        this.addNotify = addNotify;
    }
    
    public final Toolkit getToolkit() {
        return super.getToolkit();
    }
    
    public void addNotify() {
        this.insideNotify = true;
        super.addNotify();
        this.insideNotify = false;
    }
    
    static {
        WPanel.lightweight = true;
        WPanel.forceSwing = true;
    }
}
