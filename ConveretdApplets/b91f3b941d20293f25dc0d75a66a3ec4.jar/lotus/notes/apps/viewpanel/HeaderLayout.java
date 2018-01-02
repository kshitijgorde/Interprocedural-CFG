// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewpanel;

import lotus.notes.util.Bidi;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.util.Vector;
import java.awt.LayoutManager;

class HeaderLayout implements LayoutManager
{
    int numHeaders;
    public int hGap;
    int xoffset;
    HeaderPanel headerPanel;
    Vector headers;
    private int direction;
    
    protected HeaderLayout() {
        this.numHeaders = 0;
        this.direction = 0;
    }
    
    public HeaderLayout(final HeaderPanel headerPanel) {
        this(headerPanel, 0);
    }
    
    public HeaderLayout(final HeaderPanel headerPanel, final int hGap) {
        this.numHeaders = 0;
        this.direction = 0;
        this.hGap = hGap;
        this.headerPanel = headerPanel;
        this.headers = headerPanel.getHeaderVector();
    }
    
    public void setDirection(final int direction) {
        this.direction = direction;
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        final Insets insets = container.insets();
        final Dimension dimension = new Dimension(0, 0);
        for (int countComponents = container.countComponents(), i = 0; i < countComponents; ++i) {
            final Component component = container.getComponent(i);
            if (component.isVisible()) {
                final Dimension preferredSize = component.preferredSize();
                dimension.height = Math.max(preferredSize.height, dimension.height);
                final Header header = this.headers.elementAt(i);
                final int width = header.getWidth();
                if (width != -1) {
                    final Dimension dimension2 = dimension;
                    dimension2.width += width;
                }
                else {
                    final Dimension dimension3 = dimension;
                    dimension3.width += preferredSize.width;
                }
                if (i > 0 && !header.isFiller() && header.isResizable()) {
                    final Dimension dimension4 = dimension;
                    dimension4.width += this.hGap;
                }
            }
        }
        final Dimension dimension5 = dimension;
        dimension5.width += insets.left + insets.right;
        final Dimension dimension6 = dimension;
        dimension6.height += insets.top + insets.bottom;
        return dimension;
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return this.preferredLayoutSize(container);
    }
    
    public void layoutContainer(final Container container) {
        final Insets insets = container.insets();
        final int countComponents = container.countComponents();
        int n = insets.left + this.xoffset;
        container.preferredSize();
        final Dimension size = container.size();
        if (countComponents == 0) {
            return;
        }
        final int height = this.preferredLayoutSize(this.headerPanel).height;
        for (int i = 0; i < countComponents - 1; ++i) {
            final Component component = container.getComponent(i);
            if (component.isVisible()) {
                final Dimension preferredSize = component.preferredSize();
                final int n2 = size.height / 2 - height / 2;
                final Header header = this.headers.elementAt(i);
                final int width = header.getWidth();
                int width2;
                if (width == -1) {
                    width2 = preferredSize.width;
                }
                else {
                    width2 = width;
                }
                if (this.direction == 0) {
                    component.reshape(n, n2, width2, height);
                }
                else {
                    component.reshape(Bidi.toggleHorzPos(n, width2, size.width), n2, width2, height);
                }
                if (header.baseX == -1) {
                    header.baseX = n;
                }
                n += width2;
                if (!header.isFiller() && header.isResizable()) {
                    n += this.hGap;
                }
            }
        }
        final Component component2 = container.getComponent(countComponents - 1);
        if (component2.isVisible()) {
            component2.preferredSize();
            final int n3 = size.height / 2 - height / 2;
            if (this.direction == 0) {
                component2.reshape(n, n3, size.width - n, height);
            }
            else {
                component2.reshape(Bidi.toggleHorzPos(n, size.width - n, size.width), n3, size.width - n, height);
            }
            final Header header2 = this.headers.lastElement();
            if (header2.baseX == -1) {
                header2.baseX = n;
            }
        }
    }
    
    public void setSpacing(final int hGap) {
        this.hGap = hGap;
    }
    
    public void setXOffset(final int xoffset) {
        this.xoffset = xoffset;
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public void removeLayoutComponent(final Component component) {
    }
}
