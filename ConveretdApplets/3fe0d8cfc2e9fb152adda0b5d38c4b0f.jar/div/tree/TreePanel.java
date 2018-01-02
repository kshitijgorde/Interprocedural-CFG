// 
// Decompiled by Procyon v0.5.30
// 

package div.tree;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Label;
import java.awt.Component;
import java.awt.BorderLayout;
import java.util.Enumeration;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Scrollbar;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.awt.Panel;

public class TreePanel extends Panel implements Runnable, ImageObserver
{
    private static final int TN_NORMAL = 0;
    private static final int TN_SELECTED = 1;
    private static final int TN_PATH = 2;
    private static final int TN_HIGHLIGHT = 4;
    private static final int TN_ERASEBG = 8;
    Frame loading;
    TreeNode rootNode;
    TreeNode selectedNode;
    TreeNode topVisibleNode;
    TreeNode hilightNode;
    Treeable owner;
    Scrollbar sbV;
    Scrollbar sbH;
    int sbVPosition;
    int sbHPosition;
    int sbVWidth;
    int sbHWidth;
    int pathViewHeight;
    long sbVTimer;
    long lastEventTime;
    int idleTimeout;
    private int count;
    private boolean enableCollapseRootNode;
    private int treeWidth;
    private int viewCount;
    private Color bgSelectedColor;
    private Color fgSelectedColor;
    private Color fgHighlightColor;
    private Color fgPathColor;
    private Color bgPathColor;
    private int viewWidest;
    int xShift;
    int cellSize;
    int clickSize;
    int imageInset;
    int textInset;
    int textBaseLine;
    private FontMetrics fm;
    long timeMouseDown;
    int doubleClickResolution;
    boolean animatedMode;
    protected Image im2;
    protected Image im1;
    protected Image pathImage;
    protected Graphics g1;
    private Vector e;
    private Vector v;
    private Thread idleThread;
    private int topNodeNumber;
    
    private void drawDotLine(final int n, final int n2, final int n3, final int n4) {
        if (n2 == n4) {
            for (int i = n; i < n3; i += 2) {
                this.g1.drawLine(i, n2, i, n4);
            }
            return;
        }
        for (int j = n2; j < n4; j += 2) {
            this.g1.drawLine(n, j, n3, j);
        }
    }
    
    public void setFgHilite(final Color fgHighlightColor) {
        this.fgHighlightColor = fgHighlightColor;
    }
    
    private void drawNode(final int n, final TreeNode treeNode, final int n2) {
        TreeNode parent = treeNode;
        int n3 = this.cellSize * (treeNode.depth + 1);
        final int n4 = n * this.cellSize;
        if (!this.enableCollapseRootNode) {
            n3 -= this.cellSize + this.imageInset;
        }
        this.g1.setColor(this.getForeground());
        int n5 = 0;
        if (treeNode == this.rootNode) {
            n5 = this.cellSize / 2;
        }
        int cellSize = this.cellSize;
        if (treeNode.sibling == null) {
            cellSize = this.cellSize / 2;
        }
        this.drawDotLine(n3 - this.cellSize / 2, n4 + n5, n3 - this.cellSize / 2, n4 + n5 + cellSize);
        if (treeNode.parent != null && treeNode.parent.child == treeNode) {
            this.drawDotLine(n3 - this.cellSize / 2, n4, n3 - this.cellSize / 2, n4 + this.cellSize / 2);
        }
        this.g1.setColor(this.getForeground());
        this.drawDotLine(n3 - this.cellSize / 2, n4 + this.cellSize / 2, n3 + this.cellSize / 2 - 3, n4 + this.cellSize / 2);
        if (treeNode.isExpandable()) {
            if ((n2 & 0x2) == 0x0) {
                this.g1.setColor(this.getBackground());
            }
            else {
                this.g1.setColor(this.bgPathColor);
            }
            this.g1.fillRect(n3 - this.cellSize + this.cellSize / 4, n4 + this.clickSize / 2, this.clickSize, this.clickSize);
            this.g1.setColor(this.getForeground());
            this.g1.drawRect(n3 - this.cellSize + this.cellSize / 4, n4 + this.clickSize / 2, this.clickSize, this.clickSize);
            this.g1.drawLine(n3 - this.cellSize + this.cellSize / 4 + 2, n4 + this.cellSize / 2, n3 - this.cellSize + this.cellSize / 4 + this.clickSize - 2, n4 + this.cellSize / 2);
            if (!treeNode.isExpanded()) {
                this.g1.drawLine(n3 - this.cellSize + this.cellSize / 2, n4 + this.clickSize / 2 + 2, n3 - this.cellSize + this.cellSize / 2, n4 + this.clickSize / 2 + this.clickSize - 2);
            }
        }
        Image image;
        if ((n2 & 0x1) != 0x0) {
            image = treeNode.getExpandedImage();
        }
        else {
            image = treeNode.getImage();
        }
        if (image != null) {
            if ((n2 & 0x8) != 0x0) {
                this.g1.clearRect(n3 + this.imageInset, n4, 16, 16);
            }
            this.g1.drawImage(image, n3 + this.imageInset, n4, this);
        }
        while ((parent = parent.parent) != null) {
            if (parent.sibling != null) {
                int n6 = parent.depth * this.cellSize;
                if (!this.enableCollapseRootNode) {
                    n6 -= this.cellSize + this.imageInset;
                }
                this.drawDotLine(n6 + this.cellSize / 2, n4, n6 + this.cellSize / 2, n4 + this.cellSize);
            }
        }
        if ((n2 & 0x2) != 0x0) {
            this.g1.setFont(new Font("Helvetica", 2, 12));
        }
        else {
            this.g1.setFont(new Font("Helvetica", 0, 12));
        }
        if (treeNode.text != null) {
            this.drawNodeText(treeNode, n4, treeNode == this.selectedNode || (n2 & 0x8) != 0x0, n2);
        }
    }
    
    protected void collapse(final TreeNode treeNode, final boolean b) {
        if (treeNode.isExpanded()) {
            for (TreeNode treeNode2 = treeNode.child; treeNode2 != null; treeNode2 = treeNode2.sibling) {
                if (treeNode2.isExpandable() && treeNode2.isExpanded()) {
                    this.collapse(treeNode2, false);
                }
            }
            treeNode.toggle();
            this.collapseAnimated(treeNode);
            if (b) {
                this.sendActionEvent(null);
            }
        }
        this.setScrollbarProperties();
        this.requestFocus();
    }
    
    protected synchronized void drawPathImage(TreeNode parent) {
        final int depth = parent.depth;
        final int pathViewHeight = depth * this.cellSize;
        if (pathViewHeight == 0) {
            this.pathImage = null;
            this.pathViewHeight = pathViewHeight;
            return;
        }
        synchronized (this.pathImage = this.createImage(this.treeWidth, pathViewHeight + 2)) {
            final TreeNode selectedNode = this.selectedNode;
            this.selectedNode = null;
            final Graphics g1 = this.g1;
            (this.g1 = this.pathImage.getGraphics()).setColor(this.bgPathColor);
            this.g1.fillRect(0, 0, this.treeWidth, pathViewHeight + 2);
            this.g1.setColor(Color.black);
            this.g1.drawLine(0, pathViewHeight + 1, this.treeWidth, pathViewHeight + 1);
            this.g1.setColor(Color.gray);
            this.g1.drawLine(0, pathViewHeight + 2, this.treeWidth, pathViewHeight + 2);
            for (int i = depth - 1; i >= 0; --i) {
                parent = parent.parent;
                this.drawNode(i, parent, 2);
            }
            this.g1 = g1;
            this.selectedNode = selectedNode;
        }
        // monitorexit(this.pathImage)
        this.pathViewHeight = pathViewHeight + 2;
    }
    
    public void setBgSelect(final Color bgSelectedColor) {
        this.bgSelectedColor = bgSelectedColor;
    }
    
    public void setFgSelect(final Color fgSelectedColor) {
        this.fgSelectedColor = fgSelectedColor;
    }
    
    protected void verticalScrollTo(final int sbVPosition, final boolean b) {
        if (this.sbVPosition == sbVPosition) {
            return;
        }
        this.drawPathImage(this.v.elementAt(sbVPosition));
        if (!b) {
            this.sbVPosition = sbVPosition;
            this.paint();
            return;
        }
        if (this.sbVPosition < sbVPosition) {
            final int n = (sbVPosition - this.sbVPosition) * this.cellSize;
            final Graphics graphics = this.getGraphics();
            if (graphics != null) {
                for (int i = 0; i <= n; ++i) {
                    try {
                        Thread.sleep(5L);
                    }
                    catch (Exception ex) {}
                    graphics.drawImage(this.im2, -this.sbHPosition * this.clickSize, -this.sbVPosition * this.cellSize - i, this.getBackground(), this);
                }
            }
            this.sbVPosition = sbVPosition;
            return;
        }
        final int n2 = (this.sbVPosition - sbVPosition) * this.cellSize;
        final Graphics graphics2 = this.getGraphics();
        if (graphics2 != null) {
            for (int j = 0; j <= n2; ++j) {
                try {
                    Thread.sleep(5L);
                }
                catch (Exception ex2) {}
                graphics2.drawImage(this.im2, -this.sbHPosition * this.clickSize, -this.sbVPosition * this.cellSize + j, this.getBackground(), this);
            }
        }
        this.sbVPosition = sbVPosition;
    }
    
    public void drawTree() {
        this.prepareToPaint();
    }
    
    public synchronized boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) == 0x0) {
            return true;
        }
        this.prepareToPaint();
        this.paint();
        return false;
    }
    
    public synchronized void prepareToPaint() {
        this.size();
        final int n = this.cellSize * this.viewCount;
        if (this.treeWidth <= 0 || n <= 0) {
            this.im1 = this.createImage(1, 1);
            if (this.im1 == null) {
                return;
            }
            (this.g1 = this.im1.getGraphics()).setColor(this.getBackground());
            this.treeWidth = 0;
            this.g1.fillRect(0, 0, 1, 1);
            this.im2 = this.im1;
            this.paint();
        }
        else {
            this.im1 = this.createImage(this.treeWidth, n);
            if (this.im1 == null) {
                return;
            }
            if (this.g1 != null) {
                this.g1.dispose();
            }
            this.g1 = this.im1.getGraphics();
            final Font font = this.getFont();
            if (font == null || !font.getName().equals("Helvetica")) {
                final Font font2 = new Font("Helvetica", 0, 12);
                this.g1.setFont(font2);
                this.setFont(font2);
            }
            this.fm = this.g1.getFontMetrics();
            this.g1.setColor(this.getBackground());
            this.g1.fillRect(0, 0, this.treeWidth, n);
            for (int viewCount = this.viewCount, i = 0; i < viewCount; ++i) {
                final TreeNode treeNode = this.v.elementAt(i);
                int n2 = 0;
                if (treeNode == this.selectedNode) {
                    n2 |= 0x1;
                }
                this.drawNode(i, treeNode, n2);
            }
            this.im2 = this.im1;
            this.paint();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void addChild(final TreeNode child, final TreeNode parent) {
        if (parent.child == null) {
            parent.child = child;
            child.parent = parent;
            ++this.count;
        }
        else {
            this.addSibling(child, parent.child);
        }
        ++parent.numberOfChildren;
    }
    
    public synchronized Dimension preferredSize() {
        return new Dimension(125, 175);
    }
    
    private void vectorize(final TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        this.v.addElement(treeNode);
        if (treeNode.isExpanded()) {
            treeNode.child.depth = treeNode.depth + 1;
            this.vectorize(treeNode.child);
        }
        if (treeNode.sibling != null) {
            treeNode.sibling.depth = treeNode.depth;
            this.vectorize(treeNode.sibling);
        }
    }
    
    public void setLayout(final LayoutManager layoutManager) {
    }
    
    void addSibling(final TreeNode sibling, final TreeNode treeNode) {
        TreeNode sibling2;
        for (sibling2 = treeNode; sibling2.sibling != null; sibling2 = sibling2.sibling) {}
        sibling2.sibling = sibling;
        sibling.parent = sibling2.parent;
        ++this.count;
    }
    
    private void calcWidth() {
        this.treeWidth = 0;
        final FontMetrics fontMetrics = this.getFontMetrics(new Font("Helvetica", 2, 12));
        final Enumeration<TreeNode> elements = this.v.elements();
        while (elements.hasMoreElements()) {
            final TreeNode treeNode = elements.nextElement();
            final int treeWidth = (treeNode.depth + 1) * this.cellSize + this.cellSize + this.textInset + fontMetrics.stringWidth(treeNode.text) + 1;
            if (treeWidth > this.treeWidth) {
                this.treeWidth = treeWidth;
            }
        }
    }
    
    public void focusNode(final TreeNode selectedNode) {
        if (this.g1 != null) {
            TreeNode parent = selectedNode;
            final Vector<TreeNode> vector = new Vector<TreeNode>();
            while (parent.parent != null && !parent.parent.isExpanded()) {
                vector.insertElementAt(parent, 0);
                parent = parent.parent;
            }
            final Enumeration<TreeNode> elements = vector.elements();
            while (elements.hasMoreElements()) {
                this.expandAnimated(elements.nextElement());
            }
            this.changeSelection(selectedNode);
            return;
        }
        for (TreeNode parent2 = selectedNode; parent2.parent != null; parent2 = parent2.parent) {
            parent2.parent.expand();
        }
        this.selectedNode = selectedNode;
    }
    
    public TreePanel(final Treeable owner) {
        this.sbVWidth = 16;
        this.sbHWidth = 16;
        this.sbVTimer = -1L;
        this.idleTimeout = 50;
        this.enableCollapseRootNode = true;
        this.bgSelectedColor = Color.blue;
        this.fgSelectedColor = Color.white;
        this.fgHighlightColor = Color.red;
        this.fgPathColor = Color.gray;
        this.bgPathColor = Color.lightGray;
        this.xShift = 5;
        this.cellSize = 16;
        this.clickSize = 8;
        this.imageInset = 3;
        this.textInset = 6;
        this.textBaseLine = 3;
        this.doubleClickResolution = 333;
        this.owner = owner;
        super.setLayout(new BorderLayout());
        this.add("East", this.sbV = new Scrollbar(1));
        this.add("South", this.sbH = new Scrollbar(0));
        this.idleThread = new Thread(this);
        (this.loading = new Frame("Please wait")).setResizable(false);
        final Label label = new Label("Loading images. Please wait...", 1);
        label.setBackground(Color.lightGray);
        this.loading.add("Center", label);
        this.loading.resize(200, 70);
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        this.loading.move((defaultToolkit.getScreenSize().width - this.size().width) / 2, (defaultToolkit.getScreenSize().height - this.size().height) / 2);
        this.loading.setBackground(Color.lightGray);
        this.loading.setCursor(3);
    }
    
    void remove(final TreeNode treeNode) {
        this.recount();
        while (this.isSelected(treeNode)) {
            int n = this.v.indexOf(this.selectedNode);
            if (n == -1) {
                n = this.e.indexOf(this.selectedNode);
            }
            if (n > this.viewCount - 1) {
                n = this.viewCount - 1;
            }
            if (n > 0) {
                this.changeSelection((TreeNode)this.v.elementAt(n - 1));
            }
            else {
                if (this.viewCount <= 0) {
                    continue;
                }
                this.changeSelection(treeNode.sibling);
            }
        }
        if (treeNode.parent != null) {
            if (treeNode.parent.child == treeNode) {
                if (treeNode.sibling != null) {
                    treeNode.parent.child = treeNode.sibling;
                }
                else {
                    treeNode.parent.child = null;
                    treeNode.parent.collapse();
                }
            }
            else {
                TreeNode treeNode2;
                for (treeNode2 = treeNode.parent.child; treeNode2.sibling != treeNode; treeNode2 = treeNode2.sibling) {}
                if (treeNode.sibling != null) {
                    treeNode2.sibling = treeNode.sibling;
                }
                else {
                    treeNode2.sibling = null;
                }
            }
        }
        else if (treeNode == this.rootNode) {
            if (treeNode.sibling == null) {
                this.rootNode = null;
                this.selectedNode = null;
            }
            else {
                this.rootNode = treeNode.sibling;
            }
        }
        else {
            TreeNode treeNode3;
            for (treeNode3 = this.rootNode; treeNode3.sibling != treeNode; treeNode3 = treeNode3.sibling) {}
            if (treeNode.sibling != null) {
                treeNode3.sibling = treeNode.sibling;
            }
            else {
                treeNode3.sibling = null;
            }
        }
        this.recount();
        this.redraw();
    }
    
    public String getSelectedText() {
        if (this.selectedNode == null) {
            return null;
        }
        return this.selectedNode.getText();
    }
    
    public boolean keyDown(final Event event, final int n) {
        int index = this.v.indexOf(this.selectedNode);
        final Dimension size = this.size();
        Label_0190: {
            switch (n) {
                case 10: {
                    this.sendActionEvent(event);
                    this.requestFocus();
                    break;
                }
                case 45: {
                    if (this.enableCollapseRootNode || this.selectedNode != this.rootNode) {
                        this.collapse(this.selectedNode, true);
                        break;
                    }
                    break;
                }
                case 1006: {
                    if (event.modifiers == 2) {
                        break;
                    }
                    if (!this.selectedNode.isExpanded()) {
                        break Label_0190;
                    }
                    if (this.enableCollapseRootNode || this.selectedNode != this.rootNode) {
                        this.collapse(this.selectedNode, true);
                        break;
                    }
                    break;
                }
                case 1004: {
                    if (index < this.sbVPosition || index > this.sbVPosition + size.height / this.cellSize) {
                        this.verticalScrollTo(index, false);
                        this.requestFocus();
                    }
                    if (index == this.sbVPosition && this.sbVPosition > 0) {
                        this.verticalScrollTo(this.sbVPosition - 1, false);
                        this.paint();
                    }
                    if (index > 0) {
                        --index;
                        this.changeSelection((TreeNode)this.v.elementAt(index));
                        this.requestFocus();
                        break;
                    }
                    break;
                }
                case 43: {
                    this.expand(this.selectedNode);
                    break;
                }
                case 1007: {
                    if (event.modifiers == 2) {
                        break;
                    }
                    if (this.selectedNode.isExpandable() && !this.selectedNode.isExpanded()) {
                        this.expand(this.selectedNode);
                        break;
                    }
                }
                case 1005: {
                    if (index < this.sbVPosition || index > this.sbVPosition + size.height / this.cellSize) {
                        this.verticalScrollTo(index, false);
                        this.requestFocus();
                    }
                    if (index == this.sbVPosition + size.height / this.cellSize - 1 && index < this.viewCount - 1) {
                        this.verticalScrollTo(this.sbVPosition + 1, false);
                    }
                    if (index < this.viewCount - 1) {
                        ++index;
                        this.changeSelection((TreeNode)this.v.elementAt(index));
                        this.requestFocus();
                        break;
                    }
                    break;
                }
                case 1002: {
                    final Dimension size2 = this.size();
                    if (index <= 0) {
                        break;
                    }
                    final int n2 = index - (size2.height / this.cellSize - 1);
                    if (n2 < 0) {
                        this.changeSelection(this.v.elementAt(0));
                        break;
                    }
                    this.changeSelection((TreeNode)this.v.elementAt(n2));
                    break;
                }
                case 1003: {
                    final Dimension size3 = this.size();
                    if (index >= this.v.size() - 1) {
                        break;
                    }
                    final int n3 = index + (size3.height / this.cellSize - 1);
                    if (n3 > this.v.size() - 1) {
                        this.changeSelection(this.v.elementAt(this.v.size() - 1));
                        break;
                    }
                    this.changeSelection((TreeNode)this.v.elementAt(n3));
                    break;
                }
            }
        }
        return false;
    }
    
    private void drawNodeText(final TreeNode treeNode, final int n, final boolean b, final int n2) {
        int n3 = (treeNode.depth + 1) * this.cellSize + this.cellSize + this.textInset;
        if (!this.enableCollapseRootNode) {
            n3 -= this.cellSize + this.imageInset;
        }
        Color color = this.getForeground();
        Color color2 = this.getBackground();
        if ((n2 & 0x1) != 0x0) {
            color2 = this.bgSelectedColor;
            color = this.fgSelectedColor;
        }
        else if ((n2 & 0x4) != 0x0) {
            color = this.fgHighlightColor;
        }
        else if ((n2 & 0x2) != 0x0) {
            color = this.fgPathColor;
            color2 = this.bgPathColor;
        }
        if (this.g1 == null) {
            return;
        }
        if (b) {
            this.g1.setColor(color2);
            this.g1.fillRect(n3 - 1, n + 1, this.fm.stringWidth(treeNode.text) + 2, this.cellSize - 1);
        }
        this.g1.setColor(color);
        this.g1.drawString(treeNode.text, n3, n + this.cellSize - this.textBaseLine);
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        System.out.println("TreePanel size = " + n + " " + n2 + " " + n3 + " " + n4);
        super.reshape(n, n2, n3, n4);
        this.resetVector();
        this.calcWidth();
        this.setScrollbarProperties();
    }
    
    public boolean exists(final TreeNode treeNode) {
        this.recount();
        for (int i = 0; i < this.count; ++i) {
            if (treeNode == this.e.elementAt(i)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.hilightNode != null) {
            int n3 = 0;
            if (this.hilightNode == this.selectedNode) {
                n3 |= 0x1;
            }
            this.drawNodeText(this.hilightNode, this.v.indexOf(this.hilightNode) * this.cellSize, false, n3);
        }
        this.paint();
        return true;
    }
    
    public void setBackground(final Color background) {
        super.setBackground(background);
    }
    
    public void redraw() {
        this.drawTree();
    }
    
    public void paint() {
        final Graphics graphics = this.getGraphics();
        if (graphics != null && this.im2 != null) {
            final Dimension size = this.size();
            final Image image = this.createImage(size.width, size.height);
            final Graphics graphics2 = image.getGraphics();
            graphics2.setColor(this.getBackground());
            if (size.height > this.cellSize * this.viewCount - this.sbVPosition * this.cellSize) {
                graphics2.fillRect(0, this.cellSize * this.viewCount - this.sbVPosition * this.cellSize, size.width, size.height);
            }
            graphics2.drawImage(this.im2, this.xShift - this.sbHPosition * this.clickSize, this.pathViewHeight - this.sbVPosition * this.cellSize, this.getBackground(), this);
            if (this.pathImage != null && this.pathViewHeight > 0) {
                graphics2.drawImage(this.pathImage, this.xShift - this.sbHPosition * this.clickSize, 0, null);
                graphics2.setColor(Color.lightGray);
                graphics2.fillRect(this.xShift + this.treeWidth - this.sbHPosition * this.clickSize, 0, size.width, this.pathViewHeight - 1);
                graphics2.fillRect(0, 0, this.xShift, this.pathViewHeight - 1);
                graphics2.setColor(Color.black);
                graphics2.drawLine(0, this.pathViewHeight - 1, size.width, this.pathViewHeight - 1);
                graphics2.setColor(Color.gray);
                graphics2.drawLine(0, this.pathViewHeight, size.width, this.pathViewHeight);
            }
            graphics.drawImage(image, 0, 0, null);
            graphics2.dispose();
            image.flush();
            graphics.dispose();
        }
    }
    
    public void enableAnimatedMode(final boolean animatedMode) {
        this.animatedMode = animatedMode;
    }
    
    public void expand(final TreeNode treeNode) {
        if (treeNode.isExpandable() && !treeNode.isExpanded()) {
            if (!this.v.contains(treeNode)) {
                this.expand(treeNode.parent);
            }
            treeNode.toggle();
            this.expandAnimated(treeNode);
            this.owner.expanded(treeNode.key);
        }
        this.setScrollbarProperties();
        this.requestFocus();
    }
    
    public synchronized void expandAnimated(TreeNode treeNode) {
        final int index = this.v.indexOf(treeNode);
        final int size = this.v.size();
        Object o = null;
        if (index < this.v.size() - 1) {
            o = this.v.elementAt(index + 1);
        }
        this.resetVector();
        this.calcWidth();
        if (treeNode == this.selectedNode) {
            this.drawNode(index, treeNode, 9);
        }
        else {
            this.drawNode(index, treeNode, 8);
        }
        final Image image = this.createImage(this.treeWidth, (index + 1) * this.cellSize);
        final Graphics graphics = image.getGraphics();
        graphics.drawImage(this.im1, 0, 0, null);
        final Image image2 = this.createImage(this.treeWidth, (this.v.size() - (index + 1)) * this.cellSize);
        graphics.dispose();
        final Graphics graphics2 = image2.getGraphics();
        final int n = this.v.size() - size;
        if (o != null) {
            this.v.size();
            this.v.indexOf(o);
            graphics2.drawImage(this.im1, 0, -(index + 1 - n) * this.cellSize, null);
        }
        graphics2.clearRect(0, 0, this.treeWidth, n * this.cellSize);
        final int n2 = n + index + 1;
        if (this.g1 != null) {
            this.g1.dispose();
        }
        this.g1 = graphics2;
        for (int i = index + 1; i < n2; ++i) {
            treeNode = (TreeNode)this.v.elementAt(i);
            this.drawNode(i - 1 - index, treeNode, 0);
        }
        this.im1 = this.createImage(this.treeWidth, this.v.size() * this.cellSize);
        if (this.im1 == null) {
            return;
        }
        if (this.g1 != null) {
            this.g1.dispose();
        }
        this.g1 = this.im1.getGraphics();
        final Graphics graphics3 = this.getGraphics();
        if (this.animatedMode) {
            for (int j = n * this.cellSize; j >= 0; --j) {
                this.g1.drawImage(image2, 0, (index + 1) * this.cellSize - j, null);
                this.g1.drawImage(image, 0, 0, null);
                this.im2 = this.im1;
                if (graphics3 != null) {
                    graphics3.drawImage(this.im2, this.xShift - this.sbHPosition * this.clickSize, this.pathViewHeight - this.sbVPosition * this.cellSize, this.getBackground(), this);
                }
            }
        }
        this.g1.drawImage(image2, 0, (index + 1) * this.cellSize, null);
        this.g1.drawImage(image, 0, 0, null);
        this.im2 = this.im1;
        graphics3.dispose();
        image.flush();
        image2.flush();
        this.paint();
    }
    
    public TreeNode getSelectedNode() {
        return this.selectedNode;
    }
    
    public boolean isSelected(TreeNode treeNode) {
        if (treeNode == this.selectedNode) {
            return true;
        }
        for (treeNode = treeNode.child; treeNode != null; treeNode = treeNode.sibling) {
            if (this.isSelected(treeNode)) {
                return true;
            }
        }
        return false;
    }
    
    public void paint(final Graphics graphics) {
        this.size();
        if (this.im2 == null) {
            this.redraw();
            return;
        }
        this.layout();
        this.paint();
    }
    
    private void resetVector() {
        this.v = new Vector(this.count);
        this.viewWidest = 30;
        if (this.count < 1) {
            this.viewCount = 0;
            return;
        }
        this.rootNode.depth = 0;
        this.vectorize(this.rootNode);
        for (TreeNode treeNode = this.selectedNode; !this.v.contains(treeNode); treeNode = treeNode.parent) {}
        this.viewCount = this.v.size();
    }
    
    private void sendActionEvent(final Event event) {
        this.owner.check(new String(this.selectedNode.getKey() + "," + this.selectedNode.state));
    }
    
    public void enableCollapseRoot(final boolean enableCollapseRootNode) {
        if (this.enableCollapseRootNode != enableCollapseRootNode) {
            this.enableCollapseRootNode = enableCollapseRootNode;
            if (!this.enableCollapseRootNode && this.rootNode.isExpandable() && !this.rootNode.isExpanded()) {
                this.rootNode.expand();
            }
            this.paint();
        }
    }
    
    public synchronized Dimension minimumSize() {
        return new Dimension(50, 50);
    }
    
    private void traverse(final TreeNode treeNode) {
        ++this.count;
        this.e.addElement(treeNode);
        if (treeNode.child != null) {
            treeNode.child.depth = treeNode.depth + 1;
            this.traverse(treeNode.child);
        }
        if (treeNode.sibling != null) {
            treeNode.sibling.depth = treeNode.depth;
            this.traverse(treeNode.sibling);
        }
    }
    
    protected void toggle(final TreeNode treeNode) {
        if (treeNode.isExpanded()) {
            this.collapse(treeNode, true);
            return;
        }
        this.expand(treeNode);
    }
    
    protected void setScrollbarProperties() {
        final Dimension size = this.size();
        if (this.viewCount > (size.height - this.pathViewHeight - this.sbHWidth) / this.cellSize) {
            this.sbVWidth = 16;
            this.sbV.setValues(this.sbVPosition, size.height / this.cellSize, 0, this.viewCount - 1);
            this.sbV.setPageIncrement(size.height / this.cellSize - 1);
            this.sbV.show();
            this.layout();
        }
        else {
            this.sbV.hide();
            this.verticalScrollTo(this.sbVWidth = 0, false);
        }
        if (this.treeWidth > size.width - this.sbVWidth) {
            this.sbHWidth = 16;
            this.sbH.setValues(this.sbHPosition, this.clickSize, 0, size.width / this.clickSize + 1);
            this.sbH.setPageIncrement(size.width / this.clickSize - 1);
            this.sbH.show();
            this.layout();
            return;
        }
        this.sbH.hide();
        this.horizontalScrollTo(this.sbHWidth = 0, false);
    }
    
    void changeSelection(final TreeNode selectedNode) {
        final Dimension size = this.size();
        if (selectedNode == this.selectedNode) {
            return;
        }
        if (this.v == null) {
            this.resetVector();
        }
        if (selectedNode == null) {
            this.selectedNode = null;
            this.repaint();
            return;
        }
        if (this.v.indexOf(selectedNode) == -1) {
            TreeNode treeNode = selectedNode.parent;
            if (treeNode == null) {
                return;
            }
            this.expand(treeNode);
            while (treeNode != null && this.v.indexOf(treeNode) == -1) {
                treeNode = treeNode.parent;
                this.expand(treeNode);
            }
            if (treeNode == null) {
                return;
            }
            this.resetVector();
            this.redraw();
        }
        final TreeNode selectedNode2 = this.selectedNode;
        final int index = this.v.indexOf(this.selectedNode);
        this.selectedNode = selectedNode;
        final int i = this.v.indexOf(this.selectedNode);
        this.drawNode(index, selectedNode2, 8);
        this.drawNode(i, selectedNode, 9);
        while (i < this.sbVPosition) {
            this.verticalScrollTo(this.sbVPosition - 1, false);
            this.sbV.setValue(this.sbVPosition);
        }
        while (i >= this.sbVPosition + (size.height - this.sbHWidth - this.pathViewHeight - this.cellSize / 2) / this.cellSize && this.sbV.isVisible()) {
            this.verticalScrollTo(this.sbVPosition + 1, false);
            this.sbV.setValue(this.sbVPosition);
        }
        this.paint(this.getGraphics());
    }
    
    public boolean mouseDown(final Event event, final int n, int n2) {
        n2 -= this.pathViewHeight;
        final int n3 = n2 / this.cellSize + this.sbVPosition;
        boolean b = false;
        this.requestFocus();
        if (n3 > this.viewCount - 1) {
            return false;
        }
        final TreeNode treeNode = this.v.elementAt(n3);
        final int depth = treeNode.getDepth();
        this.fm = this.getFontMetrics(new Font("Helvetica", 0, 12));
        final Rectangle rectangle = new Rectangle(this.xShift + (depth + 1) * this.cellSize + this.cellSize + this.textInset - this.sbHPosition * this.clickSize, (n3 - this.sbVPosition) * this.cellSize, this.fm.stringWidth(treeNode.text), this.cellSize);
        if (!this.enableCollapseRootNode) {
            rectangle.move(this.xShift + (depth + 1) * this.cellSize + this.textInset - this.sbHPosition * this.clickSize - this.imageInset, (n3 - this.sbVPosition) * this.cellSize);
        }
        if (rectangle.inside(n, n2)) {
            this.changeSelection(treeNode);
            b = true;
        }
        final Rectangle rectangle2 = new Rectangle(this.xShift + this.cellSize * depth + this.cellSize / 4 - this.sbHPosition * this.clickSize, (n3 - this.sbVPosition) * this.cellSize + this.clickSize / 2, this.clickSize, this.clickSize);
        if (!this.enableCollapseRootNode) {
            rectangle2.move(this.xShift + this.cellSize * depth - this.cellSize - this.imageInset + this.cellSize / 4 - this.sbHPosition * this.clickSize, (n3 - this.sbVPosition) * this.cellSize + this.clickSize / 2);
        }
        if (rectangle2.inside(n, n2)) {
            this.toggle(treeNode);
        }
        else if (b) {
            this.sendActionEvent(event);
            return false;
        }
        return true;
    }
    
    protected void horizontalScrollTo(final int sbHPosition, final boolean b) {
        if (this.sbHPosition == sbHPosition) {
            return;
        }
        this.sbHPosition = sbHPosition;
        this.paint();
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.idleTimeout / 3);
            }
            catch (InterruptedException ex) {}
            if (System.currentTimeMillis() - this.lastEventTime > this.idleTimeout && this.topNodeNumber != this.sbVPosition) {
                System.out.println("! " + System.currentTimeMillis());
                final TreeNode treeNode;
                synchronized (this.v) {
                    treeNode = this.v.elementAt(this.sbVPosition);
                }
                // monitorexit(this.v)
                this.drawPathImage(treeNode);
                this.paint();
                this.topNodeNumber = this.sbVPosition;
            }
        }
    }
    
    public void append(final TreeNode rootNode) {
        if (this.rootNode == null) {
            this.rootNode = rootNode;
            this.selectedNode = this.rootNode;
            this.count = 1;
            return;
        }
        this.addSibling(rootNode, this.rootNode);
    }
    
    protected synchronized void collapseAnimated(TreeNode treeNode) {
        final int index = this.v.indexOf(treeNode);
        final int size = this.v.size();
        TreeNode treeNode2 = treeNode.sibling;
        for (TreeNode parent = treeNode; treeNode2 == null && parent.parent != null; treeNode2 = parent.parent.sibling, parent = parent.parent) {}
        if (treeNode == this.selectedNode) {
            this.drawNode(index, treeNode, 9);
        }
        else {
            this.drawNode(index, treeNode, 8);
        }
        final int n = (index + 1) * this.cellSize;
        final int n2 = (this.v.size() - (index + 1)) * this.cellSize;
        final Image image = this.createImage(this.treeWidth, n);
        final Graphics graphics = image.getGraphics();
        graphics.drawImage(this.im1, 0, 0, null);
        final Image image2 = this.createImage(this.treeWidth, n2);
        graphics.dispose();
        final Graphics graphics2 = image2.getGraphics();
        if (treeNode2 != null) {
            this.v.size();
            graphics2.drawImage(this.im1, 0, -(index + 1) * this.cellSize, null);
        }
        final int n3 = this.v.size() * this.cellSize;
        this.resetVector();
        final int n4 = size - this.v.size();
        this.im1 = this.createImage(this.treeWidth, n3);
        final int treeWidth = this.treeWidth;
        this.calcWidth();
        if (this.im1 == null) {
            return;
        }
        if (this.g1 != null) {
            this.g1.dispose();
        }
        this.g1 = this.im1.getGraphics();
        if (this.animatedMode) {
            for (int i = 0; i <= n4 * this.cellSize; ++i) {
                this.g1.drawImage(image2, 0, (index + 1) * this.cellSize - i, null);
                this.g1.clearRect(0, n + n2 - i, treeWidth, n + n2 - i + 1);
                this.g1.drawImage(image, 0, 0, null);
                this.im2 = this.im1;
                final Graphics graphics3 = this.getGraphics();
                if (graphics3 != null) {
                    graphics3.drawImage(this.im2, this.xShift - this.sbHPosition * this.clickSize, this.pathViewHeight - this.sbVPosition * this.cellSize, this.getBackground(), this);
                }
            }
        }
        else {
            this.g1.drawImage(image2, 0, (index + 1) * this.cellSize - n4 * this.cellSize, null);
            this.g1.clearRect(0, n + n2 - n4 * this.cellSize, treeWidth, n + n2);
            this.g1.drawImage(image, 0, 0, null);
            this.im2 = this.im1;
        }
        this.paint();
        image.flush();
        image2.flush();
        for (treeNode = this.selectedNode; this.v.indexOf(treeNode) == -1; treeNode = treeNode.parent) {}
        this.changeSelection(treeNode);
    }
    
    public boolean handleEvent(final Event event) {
        this.lastEventTime = System.currentTimeMillis();
        if (event.target == this.sbV) {
            if (event.arg == null) {
                return false;
            }
            this.verticalScrollTo((int)event.arg, false);
        }
        if (event.target == this.sbH) {
            if (event.arg == null) {
                return false;
            }
            this.horizontalScrollTo((int)event.arg, false);
        }
        return super.handleEvent(event);
    }
    
    private void recount() {
        this.count = 0;
        this.e = new Vector();
        if (this.rootNode != null) {
            this.rootNode.depth = 0;
            this.traverse(this.rootNode);
        }
    }
    
    public void setForeground(final Color foreground) {
        super.setForeground(foreground);
    }
    
    boolean viewable(final TreeNode treeNode) {
        for (int i = 0; i < this.viewCount; ++i) {
            if (treeNode == this.v.elementAt(i)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean mouseMove(final Event event, final int n, int n2) {
        n2 -= this.pathViewHeight;
        final int n3 = n2 / this.cellSize + this.sbVPosition;
        if (this.hilightNode != null) {
            int n4 = 0;
            if (this.hilightNode == this.selectedNode) {
                n4 |= 0x1;
            }
            this.drawNode(this.v.indexOf(this.hilightNode), this.hilightNode, n4);
        }
        if (n3 > this.viewCount - 1) {
            this.paint();
            return false;
        }
        final TreeNode treeNode = this.v.elementAt(n3);
        final int depth = treeNode.getDepth();
        this.fm = this.getFontMetrics(new Font("Helvetica", 0, 12));
        final Rectangle rectangle = new Rectangle(this.xShift + (depth + 1) * this.cellSize + this.cellSize + this.textInset - this.sbHPosition * this.clickSize, (n3 - this.sbVPosition) * this.cellSize, this.fm.stringWidth(treeNode.text), this.cellSize);
        if (!this.enableCollapseRootNode) {
            rectangle.move(this.xShift + (depth + 1) * this.cellSize + this.textInset - this.sbHPosition * this.clickSize - this.imageInset, (n3 - this.sbVPosition) * this.cellSize);
        }
        if (rectangle.inside(n, n2)) {
            this.hilightNode = (TreeNode)this.v.elementAt(n3);
            int n5 = 4;
            if (this.hilightNode == this.selectedNode) {
                n5 |= 0x1;
            }
            this.drawNode(n3, this.hilightNode, n5);
        }
        this.paint();
        return false;
    }
}
