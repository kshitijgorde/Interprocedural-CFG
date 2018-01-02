import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.Container;
import java.awt.Event;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Scrollbar;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Image;
import java.util.Vector;
import java.awt.Dimension;
import java.applet.Applet;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class JTreeView extends Panel
{
    public static final int JTree_EVENT = 50;
    public static final int BEFORE_NODE_CHANGE_EVENT = 50;
    public static final int AFTER_NODE_CHANGE_EVENT = 51;
    public static final int BEGIN_LABEL_EDIT = 52;
    public static final int END_LABEL_EDIT = 53;
    public static final int NODE_EXPANDING = 54;
    public static final int NODE_EXPANDED = 55;
    public static final int NODE_COLLAPSING = 56;
    public static final int NODE_COLLAPSED = 57;
    public static final int NODE_CLICK = 59;
    public static final int NODE_DOUBLE_CLICK = 60;
    public static final int NODE_VIEW_DOCUMENT = 61;
    public static final int IMAGE_CHECK = 0;
    public static final int IMAGE_SITE = 1;
    public static final int IMAGE_COLLAPSED = 2;
    public static final int IMAGE_EXPANDED = 3;
    public static final int IMAGE_COLLAPSEDHASCONTENT = 4;
    public static final int IMAGE_EXPANDEDHASCONTENT = 5;
    public static final int IMAGE_PARTIALCOLLAPSED = 6;
    public static final int IMAGE_PARTIALEXPANDED = 7;
    public static final int IMAGE_LEAF = 8;
    public static final int IMAGE_NOTPRESENT = 9;
    public static final int IMAGE_DOCNOTPRESENT = 10;
    public static final int IMAGE_PRELOAD = 11;
    public static final int IMAGE_LOADING = 12;
    public static final int IMAGE_MORE = 13;
    public static final int IMAGE_ERROR = 14;
    public static final int IMAGE_MAX = 15;
    public static final int ENTER_KEY = 10;
    public static final int PLUS_KEY = 43;
    public static final int MINUS_KEY = 45;
    public static final int IMAGE_OFFSET = 21;
    public static final int LEFT_MARGIN = 7;
    public static final int EXPAND_IMAGE_MARGIN = 5;
    public static final int CHECK_BOX_MARGIN = 5;
    public static final int TEXT_MARGIN = 25;
    static final int HSCROLL_PAGE = 40;
    static final int HSCROLL_SCROLL = 10;
    JTreeCanvas m_canvas;
    Applet m_Applet;
    JNode m_RootNode;
    JNode m_SelectedNode;
    JNode m_MouseOverNode;
    JNode m_FirstVisible;
    JNode m_LastVisible;
    Dimension m_Size;
    int m_VisItemCount;
    int m_Count;
    int m_nDefaultExpandLevel;
    boolean m_Sorted;
    boolean m_caseSensitive;
    boolean m_FirstRootNode;
    int m_Indentation;
    int m_ButtonWidth;
    int m_ImageWidth;
    int m_LineHeight;
    int m_LinesPerPage;
    int m_Width;
    int m_MaxLineLength;
    boolean m_Added;
    boolean m_LinesAtRoot;
    boolean m_sync;
    boolean m_bLoading;
    boolean m_bShowChecks;
    boolean m_bShowHitCount;
    boolean m_ShowLines;
    String m_MoreText;
    String m_LoadingText;
    String m_ErrorText;
    Vector m_ImageList;
    Image m_PlusImage;
    Image m_MinusImage;
    MediaTracker m_MediaTracker;
    boolean m_ShowImages;
    boolean m_vscrollshown;
    boolean m_hscrollshown;
    boolean m_bUseAltScrollMethod;
    Color m_BorderColor;
    Color m_LineColor;
    Color m_SelTextColor;
    Color m_SelBackColor;
    Color m_DocViewedTextColor;
    Color m_MouseOverColor;
    Color m_BackgroundColor;
    Color m_ForegroundColor;
    Font m_BranchFont;
    Font m_Font;
    FontMetrics m_fm;
    Scrollbar m_vscroll;
    Scrollbar m_hscroll;
    boolean m_Redraw;
    boolean m_canEdit;
    
    private int getNodeWidth(final Graphics graphics, final JNode node) {
        TocDebug.TraceL3("JTree::getNodeWidth");
        int n = 7 + this.m_Indentation + node.getLevel() * this.m_Indentation + (this.m_MinusImage.getWidth(this) + 5);
        if (this.m_ShowImages) {
            n += 21;
        }
        if (this.m_bShowChecks) {
            n += 15;
        }
        if (graphics != null) {
            Font font = node.getFont();
            if (font == null) {
                font = this.getBranchFont();
            }
            if (font == null) {
                font = this.m_Font;
            }
            graphics.setFont(font);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final String text = node.getText();
            if (text != null) {
                n += fontMetrics.stringWidth(text);
            }
        }
        else {
            n += 25;
        }
        return n;
    }
    
    public synchronized boolean syncTree(final String s) {
        TocDebug.TraceL3("JTree::syncTree");
        final JNode findChild = this.m_RootNode.FindChild(s);
        if (findChild == null) {
            return false;
        }
        if (findChild.getPath(false, false).compareTo(s) == 0) {
            if (findChild.getParent() != null) {
                findChild.getParent().expand(false);
                final Dimension size = this.m_canvas.size();
                this.setMetrics(size.width, size.height);
                this.ensureVisible(findChild);
                this.setSelectedNode(findChild);
                this.repaint();
            }
            if (findChild.getMoreType() > -1) {
                this.setExpanded(findChild, true);
            }
            return true;
        }
        return false;
    }
    
    public synchronized int addImage(final Image image) {
        TocDebug.TraceL3("JTree::addImage");
        if (image == null) {
            return 0;
        }
        this.m_ImageList.addElement(image);
        final int size = this.m_ImageList.size();
        this.m_MediaTracker.addImage(image, size + 2);
        try {
            this.m_MediaTracker.waitForID(size + 2);
        }
        catch (InterruptedException ex) {
            TocDebug.MessageBox(TocUtil.GetFrame(this.m_Applet), ex.toString(), "Exception");
        }
        return size;
    }
    
    public int getVisibleCount() {
        TocDebug.TraceL3("JTree::getVisibleCount");
        int n = 0;
        for (JNode node = this.m_RootNode.getFirstChild(); node != null; node = node.getNextVisible(), ++n) {}
        return n;
    }
    
    public void setBorderColor(final Color borderColor) {
        TocDebug.TraceL3("JTree::setBorderColor");
        this.m_BorderColor = borderColor;
        this.repaint();
    }
    
    public Color getBorderColor() {
        TocDebug.TraceL3("JTree::getBorderColor");
        return this.m_BorderColor;
    }
    
    public void setSelectedTextColor(final Color selTextColor) {
        TocDebug.TraceL3("JTree::setSelectedTextColor");
        this.m_SelTextColor = selTextColor;
        this.m_canvas.repaint();
    }
    
    public Color getSelectedTextColor() {
        TocDebug.TraceL3("JTree::getSelectedTextColor");
        return this.m_SelTextColor;
    }
    
    public void setMouseOverTextColor(final Color mouseOverColor) {
        TocDebug.TraceL3("JTree::setMouseOverTextColor");
        this.m_MouseOverColor = mouseOverColor;
        this.m_canvas.repaint();
    }
    
    public synchronized void setFirstVisible(final JNode firstVisible) {
        TocDebug.TraceL3("JTree::setFirstVisible");
        this.m_FirstVisible = firstVisible;
        this.repaint();
    }
    
    public Color getMouseOverTextColor() {
        TocDebug.TraceL3("JTree::getMouseOverTextColor");
        return this.m_MouseOverColor;
    }
    
    public void setSelectedBackColor(final Color selBackColor) {
        TocDebug.TraceL3("JTree::setSelectedBackColor");
        this.m_SelBackColor = selBackColor;
        this.m_canvas.repaint();
    }
    
    public Color getSelectedBackColor() {
        TocDebug.TraceL3("JTree::getSelectedBackColor");
        return this.m_SelBackColor;
    }
    
    public Dimension preferredSize() {
        TocDebug.TraceL3("JTree::preferredSize");
        return this.m_Size;
    }
    
    public int getLevel(final JNode node) {
        TocDebug.TraceL3("JTree::getLevel");
        return node.getLevel();
    }
    
    public JNode getPrevVisibleNode(final JNode node) {
        TocDebug.TraceL3("JTree::getPrevVisibleNode");
        return node.getPrevVisible();
    }
    
    public synchronized JNode addSiblingNode(final JNode node, final String s, final int n) {
        TocDebug.TraceL3("JTree::addSiblingNode");
        return this.addNode(node, 2, s, n);
    }
    
    public String getSelectedText() {
        TocDebug.TraceL3("JTree::getSelectedText");
        if (this.m_SelectedNode == null) {
            return null;
        }
        return this.m_SelectedNode.getText();
    }
    
    public void reshape(final int n, final int n2, final int width, final int height) {
        TocDebug.TraceL3("JTree::reshape");
        if (width - this.m_Size.width > 0) {
            final int n3 = this.m_hscroll.getValue() - (width - this.m_Size.width);
            this.m_hscroll.setValue((n3 < 0) ? 0 : n3);
        }
        super.reshape(n, n2, this.m_Size.width = width, this.m_Size.height = height);
        this.setMetrics(width, height);
        if (!this.m_Added) {
            this.layout();
        }
        this.repaint();
    }
    
    public synchronized void setBranchFont(final Font branchFont) {
        TocDebug.TraceL3("JTree::setBranchFont");
        this.m_BranchFont = branchFont;
        final Dimension size = this.m_canvas.size();
        this.setMetrics(size.width, size.height);
        this.m_canvas.repaint();
    }
    
    public void addNotify() {
        TocDebug.TraceL3("JTree::addNotify");
        super.addNotify();
        this.m_Added = true;
        if (this.m_MinusImage == null) {
            this.m_MinusImage = this.createImage(9, 9);
            this.m_PlusImage = this.createImage(9, 9);
            final Graphics graphics = this.m_MinusImage.getGraphics();
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, 8, 8);
            graphics.setColor(Color.black);
            graphics.drawRect(0, 0, 8, 8);
            graphics.drawLine(2, 4, 6, 4);
            final Graphics graphics2 = this.m_PlusImage.getGraphics();
            graphics2.setColor(Color.white);
            graphics2.fillRect(0, 0, 8, 8);
            graphics2.setColor(Color.black);
            graphics2.drawRect(0, 0, 8, 8);
            graphics2.drawLine(2, 4, 6, 4);
            graphics2.drawLine(4, 2, 4, 6);
        }
        this.m_FirstVisible = this.m_RootNode;
        final Dimension size = this.m_canvas.size();
        this.setMetrics(size.width, size.height);
        this.layout();
    }
    
    public Font getBranchFont() {
        TocDebug.TraceL3("JTree::getBranchFont");
        return this.m_BranchFont;
    }
    
    public boolean getLines() {
        TocDebug.TraceL3("JTree::getLines");
        return this.m_ShowLines;
    }
    
    public synchronized void setLines(final boolean showLines) {
        TocDebug.TraceL3("JTree::setLines");
        this.m_ShowLines = showLines;
        this.m_canvas.repaint();
    }
    
    public synchronized void setFont(final Font font) {
        TocDebug.TraceL3("JTree::setFont");
        super.setFont(font);
        final Dimension size = this.m_canvas.size();
        this.setMetrics(size.width, size.height);
        this.m_canvas.repaint();
    }
    
    public synchronized String getQueryPaths() {
        TocDebug.TraceL3("JTree::getQueryPaths");
        if (!this.m_bShowChecks) {
            return this.m_SelectedNode.getPath(false, true);
        }
        String queryPaths = this.m_RootNode.getQueryPaths(false);
        if (queryPaths != null && queryPaths.length() <= 1) {
            return queryPaths;
        }
        if (queryPaths != null && queryPaths.substring(0, 1).compareTo(new String("(")) == 0) {
            queryPaths = new String(queryPaths.substring(1));
        }
        if (queryPaths != null && queryPaths.substring(queryPaths.length() - 1).compareTo(new String(")")) == 0) {
            queryPaths = new String(queryPaths.substring(0, queryPaths.length() - 1));
        }
        if (queryPaths == null || queryPaths.length() == 0) {
            return "";
        }
        return queryPaths;
    }
    
    protected synchronized boolean setVScrollbar() {
        TocDebug.TraceL3("JTree::setVScrollBar");
        this.m_VisItemCount = this.getVisibleCount();
        this.m_vscroll.setPageIncrement(this.m_LinesPerPage);
        this.m_vscroll.setLineIncrement(1);
        if (this.m_VisItemCount >= this.m_LinesPerPage - (this.m_bUseAltScrollMethod ? 0 : 1) && this.m_LinesPerPage > 0) {
            if (this.m_bUseAltScrollMethod) {
                this.m_vscroll.setValues(this.m_vscroll.getValue(), this.m_LinesPerPage, 0, this.m_VisItemCount + 1);
            }
            else {
                TocDebug.TraceL2("Scroll::setValues");
                this.m_vscroll.setValues(this.m_vscroll.getValue(), this.m_LinesPerPage, 0, this.m_VisItemCount - this.m_LinesPerPage + 1);
            }
            if (!this.m_vscrollshown) {
                this.m_vscroll.show();
                this.layout();
                this.m_vscrollshown = true;
            }
        }
        else if (this.m_vscrollshown) {
            this.m_FirstVisible = this.m_RootNode;
            this.m_vscroll.hide();
            this.layout();
            this.m_vscrollshown = false;
        }
        return true;
    }
    
    protected synchronized boolean setHScrollbar() {
        TocDebug.TraceL2("JTree::setHScrollbar");
        if (!this.m_Added) {
            return false;
        }
        final Dimension size = this.size();
        this.m_MaxLineLength = this.getMaxLineLength();
        this.m_hscroll.setLineIncrement(10);
        this.m_hscroll.setPageIncrement(40);
        if (this.m_MaxLineLength > size.width) {
            if (this.m_bUseAltScrollMethod) {
                this.m_hscroll.setValues(this.m_hscroll.getValue(), size.width, 0, this.m_MaxLineLength);
            }
            else {
                this.m_hscroll.setValues(this.m_hscroll.getValue(), size.width, 0, this.m_MaxLineLength - size.width);
            }
            if (!this.m_hscrollshown) {
                this.m_hscroll.show();
                this.layout();
                this.m_hscrollshown = true;
            }
        }
        else if (this.m_hscrollshown) {
            this.m_hscroll.setValues(0, size.width, 0, size.width);
            this.m_hscroll.hide();
            this.layout();
            this.m_hscrollshown = false;
        }
        return true;
    }
    
    public void collapseAll() {
        final boolean redraw = this.m_Redraw;
        this.m_Redraw = false;
        this.m_RootNode.collapse(true);
        this.m_Redraw = redraw;
        final Dimension size = this.m_canvas.size();
        this.setMetrics(size.width, size.height);
        this.m_canvas.repaint();
    }
    
    void ToggleNodeExpansion(final JNode node) {
        TocDebug.TraceL3("JTreeView::TogleNodeExpansion");
        if (node.getExpanded()) {
            this.setExpanded(node, false);
        }
        else {
            if (node.getNeedToLoadChildren() || node.getFirstChild().isError()) {
                if (node.getFirstChild() != null) {
                    if (node.getFirstChild().isError()) {
                        node.getFirstChild().setLoading(this.m_LoadingText);
                    }
                }
                else {
                    this.addChildNode(node, this.m_LoadingText, 6, true).setLoading(this.m_LoadingText);
                }
                this.paint(this.getGraphics());
            }
            this.setExpanded(node, true);
        }
        this.repaint();
    }
    
    public synchronized void setIndent(final int indentation) {
        TocDebug.TraceL3("JTree::setIndent");
        this.m_Indentation = indentation;
        this.m_canvas.repaint();
    }
    
    public int getIndent() {
        TocDebug.TraceL3("JTree::getIndent");
        return this.m_Indentation;
    }
    
    public int getCount() {
        TocDebug.TraceL3("JTree::getCount");
        return this.m_Count;
    }
    
    public Color getForeground() {
        return this.m_ForegroundColor;
    }
    
    public void setForeground(final Color color) {
        TocDebug.TraceL3("JTree::setForeground");
        super.setForeground(this.m_ForegroundColor = color);
        this.m_canvas.setForeground(color);
        this.m_canvas.repaint();
    }
    
    public boolean handleEvent(final Event event) {
        TocDebug.TraceL2("JTree::handleEvent " + event.toString());
        if (this.m_sync) {
            return false;
        }
        this.m_sync = true;
        if (event.target == this.m_vscroll) {
            switch (event.id) {
                case 601: {
                    this.m_canvas.scrollLineUp(event);
                    this.m_canvas.requestFocus();
                    this.m_sync = false;
                    return true;
                }
                case 602: {
                    this.m_canvas.scrollLineDown(event);
                    this.m_canvas.requestFocus();
                    this.m_sync = false;
                    return true;
                }
                case 603: {
                    this.m_canvas.scrollPageUp(event);
                    this.m_canvas.requestFocus();
                    this.m_sync = false;
                    return true;
                }
                case 604: {
                    this.m_canvas.scrollPageDown(event);
                    this.m_canvas.requestFocus();
                    this.m_sync = false;
                    return true;
                }
                case 605: {
                    this.m_canvas.scrollAbsolute(event);
                    this.m_canvas.requestFocus();
                    this.m_sync = false;
                    return true;
                }
            }
        }
        else if (event.target == this.m_hscroll) {
            switch (event.id) {
                case 601:
                case 602:
                case 603:
                case 604:
                case 605: {
                    this.m_canvas.requestFocus();
                    this.m_sync = false;
                    this.repaint();
                    return true;
                }
            }
        }
        this.m_sync = false;
        return super.handleEvent(event);
    }
    
    public int getVisibleNodeNumber(final JNode node) {
        TocDebug.TraceL3("JTree::getVisibleNodeNumber");
        int n = 1;
        for (JNode node2 = this.m_RootNode.getFirstChild(); node2 != null; node2 = node2.getNextVisible(), ++n) {
            if (node2 == node) {
                return n;
            }
        }
        return -1;
    }
    
    public void setShowCheckBoxes(final boolean bShowChecks) {
        TocDebug.TraceL3("JTree::setShowCheckBoxes");
        this.m_bShowChecks = bShowChecks;
        this.m_canvas.repaint();
    }
    
    boolean processEvent(final long n, final int n2, final int n3, final int n4, final int n5, final int n6, final Object o) {
        TocDebug.TraceL3("JTree::precessEvent");
        final Container parent = this.getParent();
        if (parent == null) {
            return false;
        }
        final Event event = new Event(this, n, n2, n3, n4, n5, n6, o);
        if (n2 == 1001) {
            return this.postEvent(event);
        }
        return parent.postEvent(event);
    }
    
    public synchronized void setExpanded(final JNode node, final boolean expanded) {
        TocDebug.TraceL3("JTree::setExpanded");
        if (expanded) {
            if (this.processEvent(0L, 54, 0, 0, 0, 0, node)) {
                return;
            }
        }
        else if (this.processEvent(0L, 56, 0, 0, 0, 0, node)) {
            return;
        }
        node.setExpanded(expanded);
        final Dimension size = this.m_canvas.size();
        this.setMetrics(size.width, size.height);
        if (expanded) {
            if (this.processEvent(0L, 55, 0, 0, 0, 0, node)) {
                return;
            }
        }
        else if (this.processEvent(0L, 57, 0, 0, 0, 0, node)) {
            return;
        }
    }
    
    public boolean getExpanded(final JNode node) {
        TocDebug.TraceL3("JTree::getExpanded");
        return node.getExpanded();
    }
    
    public void setDefaultExpandLevel(final int nDefaultExpandLevel) {
        TocDebug.TraceL3("JTree::setDefaultExpandLevel");
        this.m_nDefaultExpandLevel = nDefaultExpandLevel;
    }
    
    public int getDefaultExpandLevel() {
        TocDebug.TraceL3("JTree::getDefaultExpandLevel");
        return this.m_nDefaultExpandLevel;
    }
    
    public JNode getFirstVisibleNode() {
        TocDebug.TraceL3("JTree::getFirstVisibleNode");
        return this.m_RootNode;
    }
    
    private int getMaxLineLength() {
        TocDebug.TraceL3("JTree::getMaxLineLength");
        int max = 0;
        final Graphics graphics = this.getGraphics();
        final JNode rootNode = this.m_RootNode;
        if (rootNode.getFirstChild() != null) {
            for (JNode node = rootNode.getFirstChild(); node != null; node = node.getNextVisible()) {
                max = Math.max(max, this.getNodeWidth(graphics, node));
            }
        }
        TocDebug.TraceL2("Max Line Length is " + new Integer(max).toString());
        return max;
    }
    
    public void update(final Graphics graphics) {
        TocDebug.TraceL3("JTree::update");
        this.paint(graphics);
        this.m_canvas.repaint();
    }
    
    public boolean getRedraw() {
        TocDebug.TraceL3("JTree::getRedraw");
        return this.m_Redraw;
    }
    
    public synchronized void setRedraw(final boolean b) {
        TocDebug.TraceL3("JTree::setRedraw");
        if (b && !this.m_Redraw) {
            this.m_Redraw = b;
            final Dimension size = this.m_canvas.size();
            this.setMetrics(size.width, size.height);
            this.m_canvas.repaint();
            return;
        }
        this.m_Redraw = b;
    }
    
    public synchronized void removeAll() {
        TocDebug.TraceL3("JTree::removeAll");
        this.m_RootNode.remove();
        this.m_RootNode = null;
        (this.m_RootNode = new JNode(null, 1, null, -1)).setRoot(true);
        this.m_RootNode.setExpanded(true);
        this.m_FirstVisible = null;
        this.m_Count = 0;
        final Dimension size = this.m_canvas.size();
        this.setMetrics(size.width, size.height);
        this.m_canvas.repaint();
    }
    
    public void reset() {
        TocDebug.TraceL3("JTree::reset");
        this.removeAll();
    }
    
    public void setBackground(final Color color) {
        TocDebug.TraceL3("JTree::setBackground");
        super.setBackground(this.m_BackgroundColor = color);
        this.m_canvas.setBackground(color);
        this.m_canvas.repaint();
    }
    
    public Color getBackground() {
        return this.m_BackgroundColor;
    }
    
    public void adjustmentValueChanged(final Event event) {
        TocDebug.TraceL3("JTree::adjustmentValueChanged");
    }
    
    public JTreeView(final Applet applet) {
        this.m_RootNode = null;
        this.m_SelectedNode = null;
        this.m_MouseOverNode = null;
        this.m_FirstVisible = null;
        this.m_LastVisible = null;
        this.m_Size = new Dimension(0, 0);
        this.m_VisItemCount = 0;
        this.m_Count = 0;
        this.m_nDefaultExpandLevel = 0;
        this.m_Sorted = false;
        this.m_FirstRootNode = true;
        this.m_Indentation = 15;
        this.m_ButtonWidth = 16;
        this.m_ImageWidth = 16;
        this.m_LineHeight = 16;
        this.m_LinesPerPage = 0;
        this.m_Width = 0;
        this.m_MaxLineLength = 0;
        this.m_Added = false;
        this.m_LinesAtRoot = true;
        this.m_sync = false;
        this.m_bLoading = false;
        this.m_bShowChecks = false;
        this.m_bShowHitCount = false;
        this.m_ShowLines = true;
        this.m_MoreText = "\"More...\"";
        this.m_LoadingText = "Loading...";
        this.m_ErrorText = "Error";
        this.m_PlusImage = null;
        this.m_MinusImage = null;
        this.m_ShowImages = true;
        this.m_vscrollshown = false;
        this.m_hscrollshown = false;
        this.m_bUseAltScrollMethod = false;
        this.m_BorderColor = Color.gray;
        this.m_LineColor = Color.gray;
        this.m_SelTextColor = Color.white;
        this.m_SelBackColor = Color.blue;
        this.m_DocViewedTextColor = Color.red;
        this.m_MouseOverColor = Color.blue;
        this.m_BackgroundColor = Color.white;
        this.m_ForegroundColor = Color.black;
        TocDebug.TraceL3("JTree::JTree");
        this.m_Applet = applet;
        this.m_MediaTracker = new MediaTracker(this);
        (this.m_RootNode = new JNode(null, 1, null, -1)).setRoot(true);
        this.m_RootNode.setExpanded(true);
        this.m_SelectedNode = this.m_RootNode;
        this.m_FirstVisible = null;
        this.m_ImageList = new Vector(15);
        this.m_canvas = new JTreeCanvas(this);
        this.m_vscroll = new Scrollbar(1, 0, 0, 0, 0);
        (this.m_hscroll = new Scrollbar(0, 0, 0, 0, 0)).setPageIncrement(50);
        this.m_hscroll.setLineIncrement(10);
        this.m_canvas.setLayout(null);
        this.m_Font = new Font("Helvetica", 0, 12);
        this.setLayout(new BorderLayout());
        this.add("Center", this.m_canvas);
        this.add("East", this.m_vscroll);
        this.add("South", this.m_hscroll);
        this.m_vscroll.hide();
        this.m_hscroll.hide();
        this.m_Redraw = false;
        this.m_canvas.setBackground(Color.white);
    }
    
    public synchronized void removeSelected() {
        TocDebug.TraceL3("JTree::removeSelected");
        if (this.m_SelectedNode != null) {
            this.removeNode(this.m_SelectedNode);
        }
    }
    
    public JNode getSelectedNode() {
        TocDebug.TraceL3("JTree::getSelectedNode");
        return this.m_SelectedNode;
    }
    
    public void setLineColor(final Color lineColor) {
        TocDebug.TraceL3("JTree::setLineColor");
        this.m_LineColor = lineColor;
        this.m_canvas.repaint();
    }
    
    public Color getViewedColor() {
        TocDebug.TraceL3("JTree::getViewedColor");
        return this.m_DocViewedTextColor;
    }
    
    public void setViewedColor(final Color docViewedTextColor) {
        TocDebug.TraceL3("JTree::setViewedColor");
        this.m_DocViewedTextColor = docViewedTextColor;
        this.m_canvas.repaint();
    }
    
    public void ensureVisible(final JNode node) {
        TocDebug.TraceL3("JTree::ensureVisible");
        final int visibleCount = this.getVisibleCount();
        if (node.lineFromNode(this) == -1) {
            final int visibleNodeNumber = this.getVisibleNodeNumber(node);
            if (visibleNodeNumber <= this.m_LinesPerPage / 2) {
                this.m_FirstVisible = this.m_RootNode;
                this.m_vscroll.setValue(0);
                return;
            }
            int value;
            if (visibleCount - visibleNodeNumber >= this.m_LinesPerPage / 2) {
                value = visibleNodeNumber - this.m_LinesPerPage / 2;
                this.m_vscroll.setValue(value);
            }
            else {
                value = visibleCount - this.m_LinesPerPage + 1;
                this.m_vscroll.setValue(this.m_vscroll.getMaximum() - this.m_LinesPerPage);
            }
            this.m_FirstVisible = this.getVisibleNodeAt(value);
        }
    }
    
    public Color getLineColor() {
        TocDebug.TraceL3("JTree::getLineColor");
        return this.m_LineColor;
    }
    
    public void repaint() {
        TocDebug.TraceL3("JTree::repaint");
        super.repaint();
        this.m_canvas.repaint();
    }
    
    public synchronized void setSelectedNode(final JNode selectedNode) {
        TocDebug.TraceL3("JTree::setSelectedNode");
        if (selectedNode == this.m_SelectedNode) {
            return;
        }
        if (this.m_SelectedNode != null && this.m_SelectedNode.isRemoved()) {
            this.m_SelectedNode = null;
        }
        final JNode selectedNode2 = this.m_SelectedNode;
        if (this.processEvent(0L, 50, 0, 0, 0, 0, selectedNode2)) {
            return;
        }
        int lineFromNode = -1;
        if (selectedNode2 != null) {
            if (selectedNode == selectedNode2) {
                return;
            }
            lineFromNode = selectedNode2.lineFromNode(this);
        }
        int lineFromNode2 = -1;
        if (selectedNode != null) {
            lineFromNode2 = selectedNode.lineFromNode(this);
        }
        this.m_SelectedNode = selectedNode;
        final Graphics graphics = this.m_canvas.getGraphics();
        if (graphics == null) {
            return;
        }
        if (lineFromNode != -1) {
            selectedNode2.paint(this, graphics, lineFromNode);
        }
        if (lineFromNode2 != -1) {
            selectedNode.paint(this, graphics, lineFromNode2);
        }
        this.processEvent(0L, 51, 0, 0, 0, 0, selectedNode);
    }
    
    public synchronized JNode addChildNode(final JNode node, final String s, final int n, final boolean b) {
        TocDebug.TraceL3("JTree::addChildNode");
        JNode node2;
        if (b) {
            node2 = this.addNode(node, 4, s, n);
        }
        else {
            node2 = this.addNode(node, 5, s, n);
        }
        return node2;
    }
    
    public void resize(final int n, final int n2) {
        TocDebug.TraceL3("JTree::resize");
        super.resize(n, n2);
        this.setMetrics(n, n2);
    }
    
    public synchronized void removeNode(final JNode node) {
        TocDebug.TraceL3("JTree::removeNode");
        if (node == this.m_FirstVisible) {
            this.m_FirstVisible = this.m_FirstVisible.getNextParent();
        }
        if (node != null) {
            node.remove();
        }
        if (this.m_Added && this.m_Redraw) {
            this.m_VisItemCount = this.getVisibleCount();
            if (this.m_VisItemCount <= this.m_LinesPerPage + 1) {
                this.m_vscroll.hide();
                this.m_vscrollshown = false;
            }
            this.m_canvas.repaint();
        }
        if (this.m_SelectedNode != null && this.m_SelectedNode.isRemoved()) {
            this.m_SelectedNode = null;
        }
        if (this.m_MouseOverNode != null && this.m_MouseOverNode.isRemoved()) {
            this.m_MouseOverNode = null;
        }
    }
    
    public JNode getVisibleNodeAt(final int n) {
        TocDebug.TraceL3("JTree::getVisibleNodeAt");
        int n2 = 1;
        for (JNode node = this.m_RootNode.getFirstChild(); node != null; node = node.getNextVisible(), ++n2) {
            if (n2 == n) {
                return node;
            }
        }
        return null;
    }
    
    public JNode getNextVisibleNode(final JNode node) {
        TocDebug.TraceL3("JTree::getNextVisibleNode");
        return node.getNextVisible();
    }
    
    public synchronized void setUnderlindedNode(final JNode mouseOverNode) {
        TocDebug.TraceL3("JTree::setUnderlinedNode");
        if (this.m_MouseOverNode != null && this.m_MouseOverNode.isRemoved()) {
            this.m_MouseOverNode = null;
        }
        final JNode mouseOverNode2 = this.m_MouseOverNode;
        int lineFromNode = -1;
        if (mouseOverNode2 != null) {
            if (mouseOverNode == mouseOverNode2) {
                return;
            }
            lineFromNode = mouseOverNode2.lineFromNode(this);
        }
        int lineFromNode2 = -1;
        if (mouseOverNode != null) {
            lineFromNode2 = mouseOverNode.lineFromNode(this);
        }
        this.m_MouseOverNode = mouseOverNode;
        final Graphics graphics = this.m_canvas.getGraphics();
        if (lineFromNode != -1) {
            mouseOverNode2.paint(this, graphics, lineFromNode);
        }
        if (lineFromNode2 != -1) {
            mouseOverNode.paint(this, graphics, lineFromNode2);
        }
    }
    
    public synchronized JNode addNode(final JNode node, final int n, final String text, final int type) {
        TocDebug.TraceL3("JTree::addNode");
        JNode firstVisible;
        if (n == 1 || n == 2) {
            if (node == null || node.getParent() == null) {
                firstVisible = this.m_RootNode.addChild(n, text, type);
                if (this.m_FirstRootNode) {
                    this.m_FirstRootNode = false;
                }
            }
            else {
                firstVisible = node.getParent().addChild(n, text, type);
            }
        }
        else if (node != null) {
            if ((n == 0 || n == 5) && node.isLoading() && node.getFirstChild() != null && node.getFirstChild().isLoading()) {
                firstVisible = node.getFirstChild();
                firstVisible.setText(text);
                firstVisible.setType(type);
            }
            else {
                firstVisible = node.addChild(n, text, type);
            }
        }
        else {
            firstVisible = this.m_RootNode.addChild(n, text, type);
        }
        ++this.m_Count;
        if (this.m_FirstVisible == null) {
            this.m_FirstVisible = firstVisible;
        }
        if (this.m_Added && this.m_Redraw) {
            final Dimension size = this.m_canvas.size();
            this.setMetrics(size.width, size.height);
            this.setVScrollbar();
            ++this.m_LinesPerPage;
            this.m_canvas.repaint();
            --this.m_LinesPerPage;
        }
        return firstVisible;
    }
    
    public synchronized JNode addRootNode(final String s, final int n) {
        TocDebug.TraceL3("JTree::addRootNode");
        return this.addNode(this.m_RootNode, 2, s, n);
    }
    
    public boolean getImages() {
        TocDebug.TraceL3("JTree::getImages");
        return this.m_ShowImages;
    }
    
    public synchronized void setImages(final boolean showImages) {
        TocDebug.TraceL3("JTree::setImages");
        this.m_ShowImages = showImages;
        this.m_canvas.repaint();
    }
    
    public synchronized void removeChildren(final JNode node) {
        TocDebug.TraceL3("JTree::removeChildren");
        if (node.getFirstChild() != null) {
            while (node.getFirstChild().m_Next != null) {
                this.removeNode(node.getFirstChild().m_Next);
            }
            this.removeNode(node.getFirstChild());
        }
        node.m_FirstChild = null;
        node.m_LastChild = null;
    }
    
    private synchronized void setMetrics(final int width, final int n) {
        TocDebug.TraceL3("JTree::setMetrics");
        if (!this.m_Added) {
            return;
        }
        this.setHScrollbar();
        this.m_canvas.size();
        this.m_Width = width;
        final Graphics graphics = this.m_canvas.getGraphics();
        if (graphics == null) {
            return;
        }
        this.m_fm = graphics.getFontMetrics();
        int height = this.m_fm.getHeight();
        if (height < 16) {
            height = 16;
        }
        if (n / height == 0) {
            return;
        }
        this.m_LineHeight = Math.max(16, this.m_fm.getHeight());
        this.m_LinesPerPage = n / this.m_LineHeight;
        TocDebug.Assert(this.m_LinesPerPage > 0, "Lines Per Page was zero!");
        if (this.m_FirstVisible != null) {
            if (this.getVisibleCount() - (this.getVisibleNodeNumber(this.m_FirstVisible) - 1) < this.m_LinesPerPage && this.getVisibleCount() >= this.m_LinesPerPage) {
                this.m_FirstVisible = this.m_FirstVisible.getPrevVisible();
            }
            this.m_LastVisible = this.m_FirstVisible.NodeAtLine(this.m_LinesPerPage, false);
        }
        if (this.m_LinesPerPage > 0) {
            this.setVScrollbar();
        }
    }
}
