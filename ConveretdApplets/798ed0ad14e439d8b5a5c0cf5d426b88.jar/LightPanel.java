import java.awt.LayoutManager;
import java.awt.FlowLayout;

// 
// Decompiled by Procyon v0.5.30
// 

public class LightPanel extends WPanel implements WToolContainer
{
    protected WToolTip setLayout;
    
    public LightPanel() {
        this(new FlowLayout(1, 0, 0));
    }
    
    public LightPanel(final LayoutManager layout) {
        this.setLayout = null;
        this.setLayout(layout);
    }
    
    public WToolTip getToolTipComp() {
        return this.setLayout;
    }
    
    public void setToolTipComp(final WToolTip setLayout) {
        this.setLayout = setLayout;
    }
}
