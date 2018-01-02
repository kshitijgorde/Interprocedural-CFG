import java.awt.Event;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.Scrollbar;
import java.awt.LayoutManager;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class EfloyScrollPanel extends Panel implements LayoutManager
{
    Panel viewPort;
    Scrollbar vbar;
    Scrollbar hbar;
    Component scrolledComponent;
    String bars;
    
    public Dimension preferredLayoutSize(final Container parent) {
        final Dimension dimension;
        final Dimension d = dimension = new Dimension(this.scrolledComponent.preferredSize());
        dimension.height += this.hbar.preferredSize().height;
        final Dimension dimension2 = d;
        dimension2.width += this.vbar.preferredSize().width;
        return d;
    }
    
    public void addLayoutComponent(final String name, final Component comp) {
    }
    
    private void manageScrollbars() {
        final Dimension v = this.vbar.size();
        final Dimension h = this.hbar.size();
        final Dimension sc = this.scrolledComponent.size();
        this.vbar.setValues(0, v.height, 0, sc.height - v.height);
        this.hbar.setValues(0, h.width, 0, sc.width - h.width);
        this.hbar.setLineIncrement(sc.width / 10);
        this.hbar.setPageIncrement(sc.width);
        this.vbar.setLineIncrement(sc.height / 10);
        this.vbar.setPageIncrement(sc.height);
    }
    
    public Dimension minimumSize() {
        return this.preferredSize();
    }
    
    public Dimension preferredSize() {
        final Dimension dimension;
        final Dimension d = dimension = new Dimension(this.scrolledComponent.preferredSize());
        dimension.height += this.hbar.preferredSize().height;
        final Dimension dimension2 = d;
        dimension2.width += this.vbar.preferredSize().width;
        return d;
    }
    
    public EfloyScrollPanel(final Component scrolledComponent, final String barsType) {
        this.scrolledComponent = scrolledComponent;
        this.bars = barsType;
        this.viewPort = new Panel();
        this.hbar = new Scrollbar(0);
        this.vbar = new Scrollbar(1);
        this.setLayout(this);
        this.add(this.viewPort);
        if (this.bars.equals("V")) {
            this.add(this.vbar);
        }
        else if (this.bars.equals("H")) {
            this.add(this.hbar);
        }
        else if (this.bars.equals("HV")) {
            this.add(this.vbar);
            this.add(this.hbar);
        }
        this.viewPort.add(scrolledComponent);
    }
    
    public void removeLayoutComponent(final Component comp) {
    }
    
    public Dimension minimumLayoutSize(final Container parent) {
        final Dimension dimension;
        final Dimension d = dimension = new Dimension(this.scrolledComponent.minimumSize());
        dimension.height += this.hbar.preferredSize().height;
        final Dimension dimension2 = d;
        dimension2.width += this.vbar.preferredSize().width;
        return d;
    }
    
    public void layoutContainer(final Container parent) {
        final Insets insets = parent.insets();
        final Dimension parentSize = parent.size();
        final int top = insets.top;
        final int left = insets.left;
        final int bottom = parentSize.height - insets.bottom;
        final int right = parentSize.width - insets.right;
        final Dimension pv = this.vbar.preferredSize();
        final Dimension ph = this.hbar.preferredSize();
        this.vbar.reshape(right - pv.width, top, pv.width, bottom - top - ph.height);
        this.hbar.reshape(left, bottom - ph.height, right - left - pv.width, ph.height);
        this.viewPort.reshape(left, top, right - this.vbar.size().width, bottom - this.hbar.size().height);
        this.manageScrollbars();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 601:
            case 602:
            case 603:
            case 604:
            case 605: {
                this.scroll();
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void scroll() {
        if (this.bars.equals("HV")) {
            this.scrolledComponent.move(-this.hbar.getValue(), -this.vbar.getValue());
        }
        else if (this.bars.equals("V")) {
            this.scrolledComponent.move(0, -this.vbar.getValue());
        }
        else if (this.bars.equals("H")) {
            this.scrolledComponent.move(-this.hbar.getValue(), 0);
        }
    }
}
