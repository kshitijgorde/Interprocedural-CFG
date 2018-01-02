// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewpanel;

import java.util.Enumeration;
import java.awt.Container;
import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.Color;
import java.util.Vector;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Panel;

public class HeaderPanel extends Panel
{
    public HeaderLayout hl;
    Rectangle r;
    Rectangle selectedHeaderBounds;
    boolean mouseUpHandled;
    boolean processingMouseDown;
    int originalCursorId;
    Frame parentFrame;
    Header selectedHeader;
    int selectedHeaderIndex;
    Vector headers;
    ViewPanel viewPanel;
    private int direction;
    private static final int DEFAULT_HGAP = 2;
    private static final int SELECTION_FUDGE = 0;
    private static final int MIN_WIDTH = 10;
    
    public HeaderPanel() {
        this.r = new Rectangle();
        this.selectedHeaderBounds = null;
        this.mouseUpHandled = false;
        this.processingMouseDown = false;
        this.originalCursorId = -1;
        this.parentFrame = null;
        this.selectedHeader = null;
        this.selectedHeaderIndex = -1;
        this.headers = new Vector();
        this.viewPanel = null;
        this.direction = 0;
        this.setBackground(Color.gray);
        this.setForeground(Color.lightGray);
        this.setLayout(this.hl = new HeaderLayout(this, 2));
    }
    
    public HeaderPanel(final int n) {
        this.r = new Rectangle();
        this.selectedHeaderBounds = null;
        this.mouseUpHandled = false;
        this.processingMouseDown = false;
        this.originalCursorId = -1;
        this.parentFrame = null;
        this.selectedHeader = null;
        this.selectedHeaderIndex = -1;
        this.headers = new Vector();
        this.viewPanel = null;
        this.direction = 0;
        this.setBackground(Color.gray);
        this.setForeground(Color.lightGray);
        this.setLayout(this.hl = new HeaderLayout(this, 2));
    }
    
    public void addHeader(final Header header) {
        this.headers.addElement(header);
        this.add(header.getComponent());
        header.getComponent().setBackground(this.getForeground());
    }
    
    public void addNotify() {
        super.addNotify();
        this.parentFrame = this.getParentFrame();
        if (this.parentFrame != null) {
            this.originalCursorId = this.parentFrame.getCursorType();
        }
        try {
            this.viewPanel = (ViewPanel)this.getParent();
        }
        catch (Exception ex) {}
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.processingMouseDown) {
            this.processingMouseDown = true;
            if (event.target == this) {
                this.requestFocus();
                final Header selectedHeader = this.getSelectedHeader(n, n2);
                if (selectedHeader != null && selectedHeader.isResizable()) {
                    this.viewPanel.startColumnResize(n);
                    this.selectedHeader = selectedHeader;
                    this.selectedHeaderIndex = this.headers.indexOf(selectedHeader);
                }
            }
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.viewPanel.endColumnResize();
        if (this.selectedHeader != null) {
            this.resizeColumn(this.selectedHeader, this.selectedHeaderIndex, n);
        }
        this.selectedHeader = null;
        this.selectedHeaderIndex = -1;
        this.processingMouseDown = false;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (event.target == this && this.parentFrame != null) {
            final Header selectedHeader = this.getSelectedHeader(n, n2);
            if (selectedHeader != null && selectedHeader.isResizable()) {
                this.parentFrame.setCursor(2);
            }
            else if (this.parentFrame.getCursorType() != this.originalCursorId) {
                this.parentFrame.setCursor(this.originalCursorId);
            }
        }
        return false;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (event.target == this && this.parentFrame != null) {
            this.parentFrame.setCursor(this.originalCursorId);
        }
        return false;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.selectedHeader == null) {
            return false;
        }
        final Rectangle bounds = this.selectedHeader.getComponent().bounds();
        if (this.direction == 0 && n - bounds.x > 10) {
            this.viewPanel.drawColumnResizeLine(n);
        }
        else if (this.direction == 1 && bounds.x + bounds.width - n > 10) {
            this.viewPanel.drawColumnResizeLine(n);
        }
        return false;
    }
    
    Vector getHeaderVector() {
        return this.headers;
    }
    
    private void resizeColumn(final Header header, final int n, final int n2) {
        this.selectedHeaderBounds = header.getComponent().bounds();
        if (this.direction == 0) {
            this.selectedHeaderBounds.width = n2 - this.selectedHeaderBounds.x - 1;
        }
        else {
            this.selectedHeaderBounds.width = this.selectedHeaderBounds.x + this.selectedHeaderBounds.width - n2 - 1;
        }
        if (this.selectedHeaderBounds.width < 10) {
            this.selectedHeaderBounds.width = 10;
        }
        header.setWidth(this.selectedHeaderBounds.width);
        this.invalidate();
        this.layout();
    }
    
    private Header getSelectedHeader(final int n, final int n2) {
        for (int n3 = this.headers.size() - 1, i = 0; i < n3; ++i) {
            final Header header = this.headers.elementAt(i);
            final Header header2 = this.headers.elementAt(i + 1);
            Rectangle rectangle;
            Rectangle rectangle2;
            if (this.direction == 0) {
                rectangle = header.getComponent().bounds();
                rectangle2 = header2.getComponent().bounds();
            }
            else {
                rectangle2 = header.getComponent().bounds();
                rectangle = header2.getComponent().bounds();
            }
            this.r.reshape(rectangle.x + rectangle.width, rectangle.y, rectangle2.x - rectangle.x - rectangle.width + 0, rectangle2.height);
            if (this.r.inside(n, n2)) {
                return header;
            }
        }
        return null;
    }
    
    private Frame getParentFrame() {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        return (Frame)container;
    }
    
    void resetSortState(final Header header) {
        final Enumeration<Header> elements = this.headers.elements();
        while (elements.hasMoreElements()) {
            final Header header2 = elements.nextElement();
            if (header2 != header && !header2.isFiller()) {
                header2.resetSortState();
                header2.repaint();
            }
        }
    }
    
    public void setDirection(final int n) {
        this.hl.setDirection(n);
        this.direction = n;
    }
    
    public void repaint() {
        super.repaint();
        final Enumeration<Header> elements = this.headers.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().repaint();
        }
    }
}
