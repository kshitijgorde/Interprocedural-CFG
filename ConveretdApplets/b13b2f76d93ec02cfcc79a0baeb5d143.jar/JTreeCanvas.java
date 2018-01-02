import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class JTreeCanvas extends Panel
{
    JTreeView m_tree;
    JNode m_hoverNode;
    long m_hoverCount;
    long m_mouseTime;
    int oldx;
    int oldy;
    static final int ZONE_PARENTS = 1;
    static final int ZONE_EXPANSION = 2;
    static final int ZONE_IMAGE = 3;
    static final int ZONE_CHECKBOX = 4;
    static final int ZONE_TITLE = 5;
    
    public boolean keyDown(final Event event, final int n) {
        TocDebug.TraceL3("JTreeCanvas::keyDown");
        this.m_tree.processEvent(event.when, 401, 0, 0, n, event.modifiers, null);
        switch (n) {
            case 43: {
                if (this.m_tree.m_SelectedNode != null && (this.m_tree.m_SelectedNode.getFirstChild() != null || this.m_tree.m_SelectedNode.canExpand()) && !this.m_tree.m_SelectedNode.getExpanded()) {
                    this.m_tree.ToggleNodeExpansion(this.m_tree.m_SelectedNode);
                }
                return true;
            }
            case 45: {
                if (this.m_tree.m_SelectedNode != null && this.m_tree.m_SelectedNode.getExpanded()) {
                    this.m_tree.ToggleNodeExpansion(this.m_tree.m_SelectedNode);
                }
                return true;
            }
            case 1007: {
                if (this.m_tree.m_SelectedNode != null) {
                    if ((this.m_tree.m_SelectedNode.getFirstChild() != null || this.m_tree.m_SelectedNode.canExpand()) && !this.m_tree.m_SelectedNode.getExpanded()) {
                        this.m_tree.ToggleNodeExpansion(this.m_tree.m_SelectedNode);
                    }
                    else {
                        final int n2 = this.m_tree.m_hscroll.getValue() + this.m_tree.m_hscroll.getLineIncrement();
                        this.m_tree.m_hscroll.setValue((n2 < 0) ? 0 : n2);
                        this.repaint();
                    }
                }
                return true;
            }
            case 1006: {
                if (this.m_tree.m_SelectedNode != null) {
                    if (this.m_tree.m_SelectedNode.getExpanded() && !this.m_tree.m_SelectedNode.isRoot()) {
                        this.m_tree.ToggleNodeExpansion(this.m_tree.m_SelectedNode);
                    }
                    else {
                        final int n3 = this.m_tree.m_hscroll.getValue() - this.m_tree.m_hscroll.getLineIncrement();
                        this.m_tree.m_hscroll.setValue((n3 < 0) ? 0 : n3);
                        this.repaint();
                    }
                }
                return true;
            }
            case 1005: {
                if (!this.isNodeVisible(this.m_tree.m_SelectedNode)) {
                    this.m_tree.setSelectedNode(this.m_tree.m_FirstVisible);
                    return true;
                }
                JNode selectedNode;
                if (this.m_tree.m_SelectedNode == this.m_tree.m_LastVisible) {
                    this.scrollLineDown(event);
                    this.m_tree.m_vscroll.setValue(this.m_tree.m_vscroll.getValue() + 1);
                    selectedNode = this.m_tree.m_LastVisible;
                }
                else {
                    selectedNode = this.m_tree.m_SelectedNode.getNextVisible();
                }
                if (selectedNode != null) {
                    this.m_tree.setSelectedNode(selectedNode);
                }
                return true;
            }
            case 1004: {
                if (!this.isNodeVisible(this.m_tree.m_SelectedNode)) {
                    this.m_tree.setSelectedNode(this.m_tree.m_LastVisible);
                    return true;
                }
                if (this.m_tree.m_SelectedNode == this.m_tree.m_FirstVisible) {
                    this.scrollLineUp(event);
                    this.m_tree.m_vscroll.setValue(this.m_tree.m_vscroll.getValue() - 1);
                }
                final JNode prevVisible = this.m_tree.m_SelectedNode.getPrevVisible();
                if (prevVisible != null) {
                    this.m_tree.setSelectedNode(prevVisible);
                }
                return true;
            }
            case 1003: {
                this.scrollPageDown(event);
                this.m_tree.setSelectedNode(this.m_tree.m_LastVisible);
                this.m_tree.m_vscroll.setValue(this.m_tree.m_vscroll.getValue() + this.m_tree.m_LinesPerPage);
                return true;
            }
            case 1002: {
                this.scrollPageUp(event);
                this.m_tree.m_vscroll.setValue(this.m_tree.m_vscroll.getValue() - this.m_tree.m_LinesPerPage);
                this.m_tree.setSelectedNode(this.m_tree.m_FirstVisible);
                return true;
            }
            case 10: {
                this.m_tree.m_SelectedNode.setForeground(this.m_tree.m_DocViewedTextColor);
                this.m_tree.processEvent(event.when, 61, 0, 0, 0, event.modifiers, this.m_tree.m_SelectedNode);
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        TocDebug.TraceL3("JTreeCanvas::mouseMove");
        if (this.m_tree.getRedraw()) {
            final int n3 = n2 / this.m_tree.m_LineHeight + 1;
            if (this.m_tree.m_FirstVisible == null) {
                return true;
            }
            final JNode nodeAtLine = this.m_tree.m_FirstVisible.NodeAtLine(n3, true);
            this.m_tree.setUnderlindedNode(nodeAtLine);
            if (nodeAtLine != null) {
                if (TocDebug.getDebug()) {
                    this.m_tree.m_Applet.showStatus(nodeAtLine.getPath(false, true));
                }
                else {
                    this.m_tree.m_Applet.showStatus(nodeAtLine.getText());
                }
            }
            else {
                this.m_tree.m_Applet.showStatus("");
            }
        }
        return true;
    }
    
    public boolean lostFocus(final Event event, final Object o) {
        TocDebug.TraceL3("JTreeCanvas::lostFocus");
        return true;
    }
    
    protected void scrollPageDown(final Event event) {
        TocDebug.TraceL3("JTreeCanvas::scrollPageDown");
        final Graphics graphics = this.getGraphics();
        final JNode firstVisible = this.m_tree.m_FirstVisible;
        for (int i = 1; i <= this.m_tree.m_LinesPerPage; ++i) {
            final JNode nextVisible = this.m_tree.m_LastVisible.getNextVisible();
            if (nextVisible == null) {
                break;
            }
            this.m_tree.m_LastVisible = nextVisible;
            final JNode nextVisible2 = this.m_tree.m_FirstVisible.getNextVisible();
            if (nextVisible2 != null) {
                this.m_tree.m_FirstVisible = nextVisible2;
            }
        }
        if (firstVisible == this.m_tree.m_FirstVisible) {
            return;
        }
        this.paintNodes(graphics);
    }
    
    protected void blankLine(final Graphics graphics, final int n) {
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, n * this.m_tree.m_LineHeight, this.m_tree.m_Size.width, this.m_tree.m_LineHeight);
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        TocDebug.TraceL3("JTreeCanvas::mouseExit");
        this.m_tree.setUnderlindedNode(null);
        this.m_tree.m_Applet.showStatus("");
        return true;
    }
    
    JTreeCanvas(final JTreeView tree) {
        this.m_tree = null;
        this.m_hoverNode = null;
        this.m_hoverCount = 0L;
        this.m_mouseTime = 0L;
        TocDebug.TraceL3("JTreeCanvas::");
        this.m_tree = tree;
    }
    
    public void paint(final Graphics graphics) {
        TocDebug.TraceL3("JTreeCanvas::paint");
        if (this.m_tree.m_Redraw) {
            this.paintNodes(graphics);
            return;
        }
        if (this.m_tree.m_Count == 0) {
            final Image image = this.m_tree.m_ImageList.elementAt(11);
            graphics.drawImage(image, this.m_tree.m_Size.width / 2 - image.getWidth(this.m_tree) / 2, this.m_tree.m_Size.height / 2 - image.getHeight(this.m_tree) / 2, this.m_tree);
            return;
        }
        this.validate();
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        TocDebug.TraceL3("JTreeCanvas::mouseUp");
        final int n3 = n2 / this.m_tree.m_LineHeight + 1;
        if (this.m_tree.m_FirstVisible == null) {
            return true;
        }
        final JNode nodeAtLine = this.m_tree.m_FirstVisible.NodeAtLine(n3, false);
        if (nodeAtLine != null && nodeAtLine == this.m_tree.m_SelectedNode) {
            this.m_tree.processEvent(event.when, 59, n, n2, 0, event.modifiers, nodeAtLine);
        }
        return true;
    }
    
    public void update(final Graphics graphics) {
        TocDebug.TraceL3("JTreeCanvas::update");
        this.paint(graphics);
    }
    
    protected void scrollLineUp(final Event event) {
        TocDebug.TraceL3("JTreeCanvas::scrollLineUp");
        final Graphics graphics = this.getGraphics();
        final Dimension size = this.size();
        final JNode prevVisible = this.m_tree.m_FirstVisible.getPrevVisible();
        if (prevVisible != null) {
            this.m_tree.m_FirstVisible = prevVisible;
            final JNode prevVisible2 = this.m_tree.m_LastVisible.getPrevVisible();
            if (prevVisible2 != null) {
                this.m_tree.m_LastVisible = prevVisible2;
            }
            if (prevVisible != null) {
                graphics.copyArea(0, 0, size.width, size.height, 0, this.m_tree.m_LineHeight);
                prevVisible.paint(this.m_tree, graphics, 1);
            }
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        TocDebug.TraceL3("JTreeCanvas::mouseDown");
        this.requestFocus();
        final int n3 = n2 / this.m_tree.m_LineHeight + 1;
        if (this.m_tree.m_FirstVisible == null) {
            return true;
        }
        final JNode nodeAtLine = this.m_tree.m_FirstVisible.NodeAtLine(n3, false);
        if (nodeAtLine != null) {
            if (n3 != nodeAtLine.lineFromNode(this.m_tree)) {
                return true;
            }
            final int getZoneItem = this._GetZoneItemAt(nodeAtLine, n);
            if (getZoneItem <= 2) {
                if (nodeAtLine.canExpand()) {
                    this.m_tree.ToggleNodeExpansion(nodeAtLine);
                }
                return true;
            }
            if (getZoneItem == 4) {
                nodeAtLine.SetCheck(!nodeAtLine.isInQuery());
                this.repaint();
                return true;
            }
            if (getZoneItem == 3 || getZoneItem == 5) {
                nodeAtLine.setForeground(this.m_tree.m_DocViewedTextColor);
                this.m_tree.processEvent(event.when, 61, 0, 0, 0, event.modifiers, nodeAtLine);
                if (this.m_tree.m_SelectedNode != nodeAtLine) {
                    this.m_tree.setSelectedNode(nodeAtLine);
                }
            }
        }
        return true;
    }
    
    void paintNodes(final Graphics graphics) {
        TocDebug.TraceL3("JTreeCanvas::paintNodes");
        JNode lastVisible = this.m_tree.m_FirstVisible;
        final Dimension size = this.size();
        final Image image = this.createImage(size.width, size.height);
        final Graphics graphics2 = image.getGraphics();
        int n;
        int n2;
        int i;
        for (n = this.m_tree.m_LinesPerPage + 1, n2 = 1, i = 0; lastVisible != null && n2 <= n; lastVisible = lastVisible.getNextVisible(), ++n2, ++i) {
            lastVisible.paint(this.m_tree, graphics2, n2);
            if (n2 == n - 1) {
                this.m_tree.m_LastVisible = lastVisible;
            }
        }
        if (i < this.m_tree.m_LinesPerPage + 1) {
            while (i <= this.m_tree.m_LinesPerPage + 1) {
                this.blankLine(graphics2, i);
                ++i;
            }
        }
        graphics.drawImage(image, 0, 0, this);
    }
    
    protected void scrollAbsolute(final Event event) {
        TocDebug.TraceL3("JTreeCanvas::scrollAbsolute");
        int value;
        int n;
        JNode firstVisible;
        for (value = this.m_tree.m_vscroll.getValue(), n = 0, firstVisible = this.m_tree.m_RootNode; firstVisible != null && n < value; ++n, firstVisible = firstVisible.getNextVisible()) {}
        if (firstVisible != null) {
            this.m_tree.m_FirstVisible = firstVisible;
            this.repaint();
        }
    }
    
    int _GetZoneItemAt(final JNode node, int n) {
        TocDebug.TraceL3("JTreeCanvas::_GetZoneItemAt");
        final int n2 = node.getLevel() * this.m_tree.m_Indentation;
        final int n3 = n2 + this.m_tree.m_MinusImage.getWidth(this.m_tree) + 5;
        n += this.m_tree.m_hscroll.getValue();
        int n4;
        if (this.m_tree.m_ShowImages) {
            n4 = n3 + 21;
        }
        else {
            n4 = n3;
        }
        int n5;
        if (this.m_tree.m_bShowChecks && node.getMoreType() == -1) {
            n5 = n4 + 10 + 2;
        }
        else {
            n5 = n4;
        }
        int n6;
        if (n <= n2) {
            n6 = 1;
        }
        else if (n > n2 && n <= n3) {
            n6 = 2;
        }
        else if (n > n3 && n <= n4) {
            n6 = 3;
        }
        else if (n > n4 && n <= n5) {
            n6 = 4;
        }
        else {
            n6 = 5;
        }
        return n6;
    }
    
    protected void scrollPageUp(final Event event) {
        TocDebug.TraceL3("JTreeCanvas::scrollPageUp");
        final Graphics graphics = this.getGraphics();
        for (int i = 1; i <= this.m_tree.m_LinesPerPage; ++i) {
            final JNode prevVisible = this.m_tree.m_FirstVisible.getPrevVisible();
            if (prevVisible != null) {
                this.m_tree.m_FirstVisible = prevVisible;
                final JNode prevVisible2 = this.m_tree.m_LastVisible.getPrevVisible();
                if (prevVisible2 != null) {
                    this.m_tree.m_LastVisible = prevVisible2;
                }
            }
            else {
                if (i == 1) {
                    return;
                }
                i = this.m_tree.m_LinesPerPage + 1;
            }
        }
        this.paintNodes(graphics);
    }
    
    protected boolean isNodeVisible(final JNode node) {
        TocDebug.TraceL3("JTreeCanvas::isNodeVisible");
        JNode node2 = this.m_tree.m_FirstVisible;
        if (node == null) {
            return false;
        }
        for (int i = 1; i <= this.m_tree.m_LinesPerPage; ++i) {
            if (node2 == node) {
                return true;
            }
            if (node2 == null) {
                return false;
            }
            node2 = node2.getNextVisible();
        }
        return false;
    }
    
    protected void scrollLineDown(final Event event) {
        TocDebug.TraceL3("JTreeCanvas::scrollLineDown");
        final Graphics graphics = this.getGraphics();
        final Dimension size = this.size();
        final JNode lastVisible = this.m_tree.m_LastVisible;
        final JNode nextVisible = this.m_tree.m_LastVisible.getNextVisible();
        if (nextVisible != null) {
            this.m_tree.m_LastVisible = nextVisible;
            final JNode nextVisible2 = this.m_tree.m_FirstVisible.getNextVisible();
            if (nextVisible2 != null) {
                this.m_tree.m_FirstVisible = nextVisible2;
            }
            graphics.copyArea(0, this.m_tree.m_LineHeight, size.width, size.height - this.m_tree.m_LineHeight, 0, -this.m_tree.m_LineHeight);
            lastVisible.paint(this.m_tree, graphics, this.m_tree.m_LinesPerPage - 1);
            nextVisible.paint(this.m_tree, graphics, this.m_tree.m_LinesPerPage);
            final JNode nextVisible3 = nextVisible.getNextVisible();
            if (nextVisible3 != null) {
                nextVisible3.paint(this.m_tree, graphics, this.m_tree.m_LinesPerPage + 1);
                return;
            }
            this.blankLine(graphics, this.m_tree.m_LinesPerPage);
        }
    }
}
