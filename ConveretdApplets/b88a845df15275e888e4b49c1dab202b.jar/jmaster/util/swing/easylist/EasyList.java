// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.easylist;

import java.awt.Graphics;
import javax.swing.ListCellRenderer;
import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.Point;
import javax.swing.Icon;
import java.awt.Component;
import java.awt.Insets;
import jmaster.util.log.B;
import jmaster.util.swing.AutoscrollSupport;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import jmaster.util.swing.GUIHelper;
import jmaster.util.log.A;
import java.awt.dnd.Autoscroll;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JList;

public class EasyList extends JList implements MouseListener, MouseMotionListener, Autoscroll
{
    private static final long h = -4661821908649857527L;
    protected A j;
    protected GUIHelper l;
    protected EasyListCellRenderer m;
    protected JLabel i;
    protected MouseEvent g;
    protected EasyListCellRendererComponent n;
    protected Object k;
    private AutoscrollSupport f;
    
    public EasyList() {
        this.j = B.getInstance().getLog(this.getClass());
        this.l = GUIHelper.getInstance();
        this.i = new JLabel();
        this.f = new AutoscrollSupport(this, new Insets(8, 8, 8, 8));
        this.i.setHorizontalAlignment(0);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public String getBackgroundText() {
        return this.i.getText();
    }
    
    public void setBackgroundText(final String text) {
        this.i.setText(text);
        this.repaint();
    }
    
    public void setBackgroundVisible(final boolean visible) {
        if (this.isBackgroundVisible() ^ visible) {
            this.i.setVisible(visible);
            this.repaint();
        }
    }
    
    public boolean isBackgroundVisible() {
        return this.i.isVisible();
    }
    
    public void setBackgroundIcon(final Icon icon) {
        this.i.setIcon(icon);
    }
    
    public MouseEvent getLastMouseEvent() {
        return this.g;
    }
    
    public EasyListCellRendererComponent getActiveComponent() {
        return this.n;
    }
    
    public Object getActiveItem() {
        return this.k;
    }
    
    public void autoscroll(final Point point) {
        this.f.autoscroll(point);
    }
    
    public Insets getAutoscrollInsets() {
        return this.f.getAutoscrollInsets();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.B(mouseEvent);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.B(mouseEvent);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.B(mouseEvent);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.B(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.B(mouseEvent);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.B(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.B(mouseEvent);
    }
    
    public void setActiveObject(final EasyListCellRendererComponent n, final Object k) {
        if (n == null && this.n == null) {
            return;
        }
        if ((this.n == null && n != null) || (this.n != null && n == null) || (this.n != null && !this.n.equals(n)) || (this.k == null && k != null) || (this.k != null && k == null) || (this.k != null && !this.k.equals(k))) {
            this.n = n;
            this.k = k;
            Cursor cursor = this.l.getDefaultCursor();
            if (this.n != null && this.n.isVisible() && this.n.isActionEnabled()) {
                cursor = this.n.getCursor();
            }
            this.setCursor(cursor);
            this.setToolTipText((this.n != null && this.n.isVisible()) ? this.n.getToolTipText() : null);
            this.repaint();
        }
    }
    
    private void B(final MouseEvent g) {
        this.g = g;
        if (this.m != null) {
            final int cellAtLocation = this.getCellAtLocation(g.getPoint());
            if (cellAtLocation >= 0) {
                final Point point = g.getPoint();
                final Rectangle cellBounds = this.getCellBounds(cellAtLocation, cellAtLocation);
                if (cellBounds.contains(g.getPoint())) {
                    this.m.handleMouseEvent(g, this, cellAtLocation, cellBounds, new Point(point.x - cellBounds.x, point.y - cellBounds.y));
                }
            }
        }
    }
    
    public int getCellAtLocation(final Point point) {
        int locationToIndex = this.locationToIndex(point);
        if (locationToIndex != -1 && !this.getCellBounds(locationToIndex, locationToIndex).contains(point)) {
            locationToIndex = -1;
        }
        return locationToIndex;
    }
    
    public void repaintCell(final int n) {
        this.repaint(this.getCellBounds(n, n));
    }
    
    public void setCellRenderer(final ListCellRenderer cellRenderer) {
        if (cellRenderer instanceof EasyListCellRenderer) {
            this.m = (EasyListCellRenderer)cellRenderer;
        }
        else {
            this.m = null;
        }
        super.setCellRenderer(cellRenderer);
    }
    
    public Object getItem(final Point point) {
        Object element = null;
        final int cellAtLocation = this.getCellAtLocation(point);
        if (cellAtLocation != -1) {
            element = this.getModel().getElementAt(cellAtLocation);
        }
        return element;
    }
    
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        if (this.i.isVisible() && this.i.getText() != null) {
            this.i.setBounds(this.getBounds());
            this.i.paint(graphics);
        }
    }
}
