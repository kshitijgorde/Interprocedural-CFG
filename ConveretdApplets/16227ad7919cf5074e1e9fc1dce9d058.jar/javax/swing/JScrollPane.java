// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.swing.event.ChangeEvent;
import javax.accessibility.AccessibleRole;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.UIResource;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.plaf.ComponentUI;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.plaf.ScrollPaneUI;
import javax.accessibility.AccessibleContext;
import java.awt.LayoutManager;
import java.awt.Component;
import javax.swing.border.Border;
import javax.accessibility.Accessible;

public class JScrollPane extends JComponent implements ScrollPaneConstants, Accessible
{
    private Border viewportBorder;
    private static final String uiClassID = "ScrollPaneUI";
    protected int verticalScrollBarPolicy;
    protected int horizontalScrollBarPolicy;
    protected JViewport viewport;
    protected JScrollBar verticalScrollBar;
    protected JScrollBar horizontalScrollBar;
    protected JViewport rowHeader;
    protected JViewport columnHeader;
    protected Component lowerLeft;
    protected Component lowerRight;
    protected Component upperLeft;
    protected Component upperRight;
    
    public JScrollPane() {
        this(null, 20, 30);
    }
    
    public JScrollPane(final int n, final int n2) {
        this(null, n, n2);
    }
    
    public JScrollPane(final Component component) {
        this(component, 20, 30);
    }
    
    public JScrollPane(final Component viewportView, final int verticalScrollBarPolicy, final int horizontalScrollBarPolicy) {
        this.verticalScrollBarPolicy = 20;
        this.horizontalScrollBarPolicy = 30;
        this.setLayout(new ScrollPaneLayout.UIResource());
        this.setVerticalScrollBarPolicy(verticalScrollBarPolicy);
        this.setHorizontalScrollBarPolicy(horizontalScrollBarPolicy);
        this.setViewport(this.createViewport());
        this.setVerticalScrollBar(this.createVerticalScrollBar());
        this.setHorizontalScrollBar(this.createHorizontalScrollBar());
        if (viewportView != null) {
            this.setViewportView(viewportView);
        }
        this.updateUI();
    }
    
    public JScrollBar createHorizontalScrollBar() {
        return new ScrollBar(0);
    }
    
    public JScrollBar createVerticalScrollBar() {
        return new ScrollBar(1);
    }
    
    protected JViewport createViewport() {
        return new JViewport();
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJScrollPane();
        }
        return super.accessibleContext;
    }
    
    public JViewport getColumnHeader() {
        return this.columnHeader;
    }
    
    public Component getCorner(final String s) {
        if (s.equals("LOWER_LEFT_CORNER")) {
            return this.lowerLeft;
        }
        if (s.equals("LOWER_RIGHT_CORNER")) {
            return this.lowerRight;
        }
        if (s.equals("UPPER_LEFT_CORNER")) {
            return this.upperLeft;
        }
        if (s.equals("UPPER_RIGHT_CORNER")) {
            return this.upperRight;
        }
        return null;
    }
    
    public JScrollBar getHorizontalScrollBar() {
        return this.horizontalScrollBar;
    }
    
    public int getHorizontalScrollBarPolicy() {
        return this.horizontalScrollBarPolicy;
    }
    
    public JViewport getRowHeader() {
        return this.rowHeader;
    }
    
    public ScrollPaneUI getUI() {
        return (ScrollPaneUI)super.ui;
    }
    
    public String getUIClassID() {
        return "ScrollPaneUI";
    }
    
    public JScrollBar getVerticalScrollBar() {
        return this.verticalScrollBar;
    }
    
    public int getVerticalScrollBarPolicy() {
        return this.verticalScrollBarPolicy;
    }
    
    public JViewport getViewport() {
        return this.viewport;
    }
    
    public Border getViewportBorder() {
        return this.viewportBorder;
    }
    
    public Rectangle getViewportBorderBounds() {
        final Rectangle rectangle = new Rectangle(this.getSize());
        final Insets insets = this.getInsets();
        rectangle.x = insets.left;
        rectangle.y = insets.top;
        final Rectangle rectangle2 = rectangle;
        rectangle2.width -= insets.left + insets.right;
        final Rectangle rectangle3 = rectangle;
        rectangle3.height -= insets.top + insets.bottom;
        final JViewport columnHeader = this.getColumnHeader();
        if (columnHeader != null && columnHeader.isVisible()) {
            final int height = columnHeader.getHeight();
            final Rectangle rectangle4 = rectangle;
            rectangle4.y += height;
            final Rectangle rectangle5 = rectangle;
            rectangle5.height -= height;
        }
        final JViewport rowHeader = this.getRowHeader();
        if (rowHeader != null && rowHeader.isVisible()) {
            final int width = rowHeader.getWidth();
            final Rectangle rectangle6 = rectangle;
            rectangle6.x += width;
            final Rectangle rectangle7 = rectangle;
            rectangle7.width -= width;
        }
        final JScrollBar verticalScrollBar = this.getVerticalScrollBar();
        if (verticalScrollBar != null && verticalScrollBar.isVisible()) {
            final Rectangle rectangle8 = rectangle;
            rectangle8.width -= verticalScrollBar.getWidth();
        }
        final JScrollBar horizontalScrollBar = this.getHorizontalScrollBar();
        if (horizontalScrollBar != null && horizontalScrollBar.isVisible()) {
            final Rectangle rectangle9 = rectangle;
            rectangle9.height -= horizontalScrollBar.getHeight();
        }
        return rectangle;
    }
    
    public boolean isOpaque() {
        final JViewport viewport;
        final Component view;
        return (viewport = this.getViewport()) != null && (view = viewport.getView()) != null && view instanceof JComponent && ((JComponent)view).isOpaque() && ((JComponent)view).getWidth() >= viewport.getWidth() && ((JComponent)view).getHeight() >= viewport.getHeight();
    }
    
    public boolean isValidateRoot() {
        return true;
    }
    
    protected String paramString() {
        final String s = (this.viewportBorder != null) ? this.viewportBorder.toString() : "";
        final String s2 = (this.viewport != null) ? this.viewport.toString() : "";
        String s3;
        if (this.verticalScrollBarPolicy == 20) {
            s3 = "VERTICAL_SCROLLBAR_AS_NEEDED";
        }
        else if (this.verticalScrollBarPolicy == 21) {
            s3 = "VERTICAL_SCROLLBAR_NEVER";
        }
        else if (this.verticalScrollBarPolicy == 22) {
            s3 = "VERTICAL_SCROLLBAR_ALWAYS";
        }
        else {
            s3 = "";
        }
        String s4;
        if (this.horizontalScrollBarPolicy == 30) {
            s4 = "HORIZONTAL_SCROLLBAR_AS_NEEDED";
        }
        else if (this.horizontalScrollBarPolicy == 31) {
            s4 = "HORIZONTAL_SCROLLBAR_NEVER";
        }
        else if (this.horizontalScrollBarPolicy == 32) {
            s4 = "HORIZONTAL_SCROLLBAR_ALWAYS";
        }
        else {
            s4 = "";
        }
        return String.valueOf(super.paramString()) + ",columnHeader=" + ((this.columnHeader != null) ? this.columnHeader.toString() : "") + ",horizontalScrollBar=" + ((this.horizontalScrollBar != null) ? this.horizontalScrollBar.toString() : "") + ",horizontalScrollBarPolicy=" + s4 + ",lowerLeft=" + ((this.lowerLeft != null) ? this.lowerLeft.toString() : "") + ",lowerRight=" + ((this.lowerRight != null) ? this.lowerRight.toString() : "") + ",rowHeader=" + ((this.rowHeader != null) ? this.rowHeader.toString() : "") + ",upperLeft=" + ((this.upperLeft != null) ? this.upperLeft.toString() : "") + ",upperRight=" + ((this.upperRight != null) ? this.upperRight.toString() : "") + ",verticalScrollBar=" + ((this.verticalScrollBar != null) ? this.verticalScrollBar.toString() : "") + ",verticalScrollBarPolicy=" + s3 + ",viewport=" + s2 + ",viewportBorder=" + s;
    }
    
    public void setColumnHeader(final JViewport columnHeader) {
        final JViewport columnHeader2 = this.getColumnHeader();
        this.columnHeader = columnHeader;
        if (columnHeader != null) {
            this.add(columnHeader, "COLUMN_HEADER");
        }
        else if (columnHeader2 != null) {
            this.remove(columnHeader);
        }
        this.firePropertyChange("columnHeader", columnHeader2, columnHeader);
        this.revalidate();
        this.repaint();
    }
    
    public void setColumnHeaderView(final Component view) {
        if (this.getColumnHeader() == null) {
            this.setColumnHeader(this.createViewport());
        }
        this.getColumnHeader().setView(view);
    }
    
    public void setCorner(final String s, final Component component) {
        Component component2;
        if (s.equals("LOWER_LEFT_CORNER")) {
            component2 = this.lowerLeft;
            this.lowerLeft = component;
        }
        else if (s.equals("LOWER_RIGHT_CORNER")) {
            component2 = this.lowerRight;
            this.lowerRight = component;
        }
        else if (s.equals("UPPER_LEFT_CORNER")) {
            component2 = this.upperLeft;
            this.upperLeft = component;
        }
        else {
            if (!s.equals("UPPER_RIGHT_CORNER")) {
                throw new IllegalArgumentException("invalid corner key");
            }
            component2 = this.upperRight;
            this.upperRight = component;
        }
        this.add(component, s);
        this.firePropertyChange(s, component2, component);
    }
    
    public void setHorizontalScrollBar(final JScrollBar horizontalScrollBar) {
        final JScrollBar horizontalScrollBar2 = this.getHorizontalScrollBar();
        this.add(this.horizontalScrollBar = horizontalScrollBar, "HORIZONTAL_SCROLLBAR");
        this.firePropertyChange("horizontalScrollBar", horizontalScrollBar2, horizontalScrollBar);
    }
    
    public void setHorizontalScrollBarPolicy(final int horizontalScrollBarPolicy) {
        switch (horizontalScrollBarPolicy) {
            default: {
                throw new IllegalArgumentException("invalid horizontalScrollBarPolicy");
            }
            case 30:
            case 31:
            case 32: {
                this.firePropertyChange("horizontalScrollBarPolicy", this.horizontalScrollBarPolicy, this.horizontalScrollBarPolicy = horizontalScrollBarPolicy);
            }
        }
    }
    
    public void setLayout(final LayoutManager layout) {
        if (layout == null || layout instanceof ScrollPaneLayout) {
            super.setLayout(layout);
            return;
        }
        throw new ClassCastException("layout of JScrollPane must be a ScrollPaneLayout");
    }
    
    public void setRowHeader(final JViewport rowHeader) {
        final JViewport rowHeader2 = this.getRowHeader();
        this.rowHeader = rowHeader;
        if (rowHeader != null) {
            this.add(rowHeader, "ROW_HEADER");
        }
        else if (rowHeader2 != null) {
            this.remove(rowHeader2);
        }
        this.firePropertyChange("rowHeader", rowHeader2, rowHeader);
    }
    
    public void setRowHeaderView(final Component view) {
        if (this.getRowHeader() == null) {
            this.setRowHeader(this.createViewport());
        }
        this.getRowHeader().setView(view);
    }
    
    public void setUI(final ScrollPaneUI ui) {
        super.setUI(ui);
    }
    
    public void setVerticalScrollBar(final JScrollBar verticalScrollBar) {
        final JScrollBar verticalScrollBar2 = this.getVerticalScrollBar();
        this.add(this.verticalScrollBar = verticalScrollBar, "VERTICAL_SCROLLBAR");
        this.firePropertyChange("verticalScrollBar", verticalScrollBar2, verticalScrollBar);
    }
    
    public void setVerticalScrollBarPolicy(final int verticalScrollBarPolicy) {
        switch (verticalScrollBarPolicy) {
            default: {
                throw new IllegalArgumentException("invalid verticalScrollBarPolicy");
            }
            case 20:
            case 21:
            case 22: {
                this.firePropertyChange("verticalScrollBarPolicy", this.verticalScrollBarPolicy, this.verticalScrollBarPolicy = verticalScrollBarPolicy);
            }
        }
    }
    
    public void setViewport(final JViewport viewport) {
        final JViewport viewport2 = this.getViewport();
        this.viewport = viewport;
        if (viewport != null) {
            this.add(viewport, "VIEWPORT");
        }
        else if (viewport2 != null) {
            this.remove(viewport2);
        }
        this.firePropertyChange("viewport", viewport2, viewport);
        if (super.accessibleContext != null) {
            ((AccessibleJScrollPane)super.accessibleContext).resetViewPort();
        }
        this.revalidate();
        this.repaint();
    }
    
    public void setViewportBorder(final Border viewportBorder) {
        this.firePropertyChange("viewportBorder", this.viewportBorder, this.viewportBorder = viewportBorder);
    }
    
    public void setViewportView(final Component view) {
        if (this.getViewport() == null) {
            this.setViewport(this.createViewport());
        }
        this.getViewport().setView(view);
    }
    
    public void updateUI() {
        this.setUI((ScrollPaneUI)UIManager.getUI(this));
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("ScrollPaneUI")) {
            super.ui.installUI(this);
        }
    }
    
    protected class ScrollBar extends JScrollBar implements UIResource
    {
        private boolean unitIncrementSet;
        private boolean blockIncrementSet;
        
        public ScrollBar(final int n) {
            super(n);
        }
        
        public int getBlockIncrement(final int n) {
            final JViewport viewport = JScrollPane.this.getViewport();
            if (this.blockIncrementSet || viewport == null) {
                return super.getBlockIncrement(n);
            }
            if (viewport.getView() instanceof Scrollable) {
                return ((Scrollable)viewport.getView()).getScrollableBlockIncrement(viewport.getViewRect(), this.getOrientation(), n);
            }
            if (this.getOrientation() == 1) {
                return viewport.getExtentSize().height;
            }
            return viewport.getExtentSize().width;
        }
        
        public int getUnitIncrement(final int n) {
            final JViewport viewport = JScrollPane.this.getViewport();
            if (!this.unitIncrementSet && viewport != null && viewport.getView() instanceof Scrollable) {
                return ((Scrollable)viewport.getView()).getScrollableUnitIncrement(viewport.getViewRect(), this.getOrientation(), n);
            }
            return super.getUnitIncrement(n);
        }
        
        public void setBlockIncrement(final int blockIncrement) {
            this.blockIncrementSet = true;
            super.setBlockIncrement(blockIncrement);
        }
        
        public void setUnitIncrement(final int unitIncrement) {
            this.unitIncrementSet = true;
            super.setUnitIncrement(unitIncrement);
        }
    }
    
    protected class AccessibleJScrollPane extends AccessibleJComponent implements ChangeListener
    {
        protected JViewport viewPort;
        
        public AccessibleJScrollPane() {
            this.viewPort = null;
            if (this.viewPort == null) {
                this.viewPort = JScrollPane.this.getViewport();
            }
            this.viewPort.addChangeListener(this);
        }
        
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.SCROLL_PANE;
        }
        
        public void resetViewPort() {
            this.viewPort.removeChangeListener(this);
            (this.viewPort = JScrollPane.this.getViewport()).addChangeListener(this);
        }
        
        public void stateChanged(final ChangeEvent changeEvent) {
            final AccessibleContext accessibleContext = JScrollPane.this.getAccessibleContext();
            if (accessibleContext != null) {
                accessibleContext.firePropertyChange("AccessibleVisibleData", new Boolean(false), new Boolean(true));
            }
        }
    }
}
