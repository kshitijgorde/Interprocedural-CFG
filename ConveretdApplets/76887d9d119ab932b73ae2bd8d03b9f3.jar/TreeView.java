import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Event;
import java.util.Vector;
import java.awt.Polygon;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Scrollbar;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class TreeView extends Panel
{
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    public static final int CHILD = 0;
    public static final int NEXT = 1;
    public static final int LAST = 2;
    private TreeNode rootNode;
    private TreeNode mouseOverNode;
    private TreeNode selectedNode;
    private TreeNode topVisibleNode;
    Scrollbar sbV;
    Scrollbar sbH;
    int sbVPosition;
    int sbVWidth;
    int sbHPosition;
    int sbHHeight;
    boolean bsbH;
    long sbVTimer;
    int count;
    int viewCount;
    private Color bgHighlightColor;
    private Color fgHighlightColor;
    private Color fgMouseOverColor;
    private Color cLineColor;
    private Color cFolderCColor;
    private Color cFolderOColor;
    private Color cLeafColor;
    private Color cToolTipFC;
    private Color cToolTipBC;
    private Color cMouseOverFrame;
    private int viewHeight;
    private int viewWidth;
    private int viewWidest;
    int cellSize;
    int clickSize;
    int imageInset;
    int textInset;
    int textBaseLine;
    private FontMetrics fm;
    long timeMouseDown;
    int doubleClickResolution;
    protected Image im1;
    protected Image imgBG;
    protected Graphics g1;
    private String[] treeStructure;
    int[] xPts;
    int[] yPts;
    int iStep;
    String sStyle;
    String sMouseOver;
    Font fFont;
    String dblClick;
    String sToolTip;
    TreeNode newNode;
    TreeNode oldNode;
    TreeNode oldSNode;
    String flgBorder;
    boolean mouseOver;
    boolean flgToolTip;
    boolean flgMouseOverRect;
    private Polygon poly;
    private Vector e;
    private Vector v;
    
    void TreeView_MouseExit(final Event event) {
        if (this.oldSNode != null) {
            if (this.flgToolTip) {
                this.flgToolTip = false;
                this.redraw();
            }
            this.mouseOver = false;
            this.drawNodeText(this.oldSNode, (this.v.indexOf(this.oldSNode) - this.sbVPosition) * this.cellSize, true);
            this.repaint();
            this.oldNode = null;
        }
    }
    
    public TreeView() {
        this.sbVWidth = 16;
        this.sbHHeight = 10;
        this.bsbH = false;
        this.sbVTimer = -1L;
        this.bgHighlightColor = Color.blue;
        this.fgHighlightColor = Color.white;
        this.fgMouseOverColor = Color.red;
        this.cLineColor = Color.black;
        this.cFolderCColor = Color.black;
        this.cFolderOColor = Color.black;
        this.cLeafColor = Color.black;
        this.cToolTipFC = Color.black;
        this.cToolTipBC = Color.yellow;
        this.cMouseOverFrame = Color.red;
        this.viewHeight = 300;
        this.viewWidth = 300;
        this.cellSize = 16;
        this.clickSize = 8;
        this.imageInset = 3;
        this.textInset = 6;
        this.textBaseLine = 3;
        this.doubleClickResolution = 333;
        this.iStep = 3;
        this.sStyle = "AXAA";
        this.sMouseOver = "none";
        this.fFont = new Font("Dialog", 0, 12);
        this.dblClick = "no";
        this.sToolTip = "yes";
        this.mouseOver = false;
        this.flgToolTip = false;
        this.flgMouseOverRect = false;
        super.setLayout(new BorderLayout());
        this.add("East", this.sbV = new Scrollbar(1));
        this.sbV.hide();
        this.add("South", this.sbH = new Scrollbar(0));
        this.sbH.hide();
        this.sbH.setValues(0, 100, 0, this.viewWidth);
    }
    
    public TreeView(final TreeNode head) {
        this();
        this.rootNode = head;
        this.selectedNode = head;
        this.count = 1;
    }
    
    public void setBackground(final Color c) {
        super.setBackground(c);
        this.invalidate();
    }
    
    public void setStyle(final String s) {
        this.sStyle = s;
        this.invalidate();
    }
    
    public void setMouseOverStyle(final String s) {
        this.sMouseOver = s;
        this.invalidate();
    }
    
    public void setNode(final String s) {
        this.recount();
        for (int i = 0; i < this.count; ++i) {
            final TreeNode tn = this.e.elementAt(i);
            if (tn.text != null && s.equals(tn.text)) {
                this.selectedNode = tn;
                if (tn.depth > 0) {
                    TreeNode tn2 = tn.parent;
                    tn2.expand();
                    while (tn2.parent != null) {
                        tn2 = tn2.parent;
                        tn2.expand();
                    }
                    tn.parent.expand();
                    this.resetVector();
                    this.selectedNode = tn;
                }
                if (this.viewable(tn)) {
                    this.sbVPosition = this.v.indexOf(tn);
                    this.sbV.setValue(this.sbVPosition);
                }
                this.redraw();
            }
        }
    }
    
    public void setForeground(final Color c) {
        super.setForeground(c);
        this.invalidate();
    }
    
    public void setToolTipForeColor(final Color c) {
        this.cToolTipFC = c;
        this.invalidate();
    }
    
    public void setToolTipBackColor(final Color c) {
        this.cToolTipBC = c;
        this.invalidate();
    }
    
    public void setFgHilite(final Color c) {
        this.fgHighlightColor = c;
        this.invalidate();
    }
    
    public void setMouseOverFrameColor(final Color c) {
        this.cMouseOverFrame = c;
        this.invalidate();
    }
    
    public void setBgHilite(final Color c) {
        this.bgHighlightColor = c;
        this.invalidate();
    }
    
    public void setMOHilite(final Color c) {
        this.fgMouseOverColor = c;
        this.invalidate();
    }
    
    public void setLineColor(final Color c) {
        this.cLineColor = c;
        this.invalidate();
    }
    
    public void setLineStep(final int i) {
        this.iStep = i;
        this.invalidate();
    }
    
    public void setFolderOColor(final Color c) {
        this.cFolderOColor = c;
        this.invalidate();
    }
    
    public void setFolderCColor(final Color c) {
        this.cFolderCColor = c;
        this.invalidate();
    }
    
    public void setLeafColor(final Color c) {
        this.cLeafColor = c;
        this.invalidate();
    }
    
    public void setBorder(final String s) {
        this.flgBorder = s;
        this.invalidate();
    }
    
    public void setFont(final Font f) {
        if (f != null) {
            this.fFont = f;
        }
        this.invalidate();
    }
    
    public void setDblClick(final String s) {
        this.dblClick = s;
        this.invalidate();
    }
    
    public void setToolTip(final String s) {
        this.sToolTip = s;
        this.invalidate();
    }
    
    public void setBGImage(final Image img) {
        this.imgBG = img;
        this.invalidate();
    }
    
    public void insert(final TreeNode newNode, final TreeNode relativeNode, final int position, final boolean expand) {
        if (newNode == null || relativeNode == null) {
            return;
        }
        if (!this.exists(relativeNode)) {
            return;
        }
        switch (position) {
            case 0: {
                this.addChild(newNode, relativeNode, expand);
            }
            case 1: {
                this.addSibling(newNode, relativeNode, expand);
            }
            case 2: {
                this.addSibling(newNode, relativeNode, expand);
            }
            default: {}
        }
    }
    
    public TreeNode getRootNode() {
        return this.rootNode;
    }
    
    public TreeNode getMouseOverNode() {
        return this.mouseOverNode;
    }
    
    public int getCount() {
        return this.count;
    }
    
    public int getViewCount() {
        return this.viewCount;
    }
    
    boolean viewable(final TreeNode node) {
        for (int i = 0; i < this.viewCount; ++i) {
            if (node == this.v.elementAt(i)) {
                return true;
            }
        }
        return false;
    }
    
    boolean viewable(final String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0; i < this.viewCount; ++i) {
            final TreeNode tn = this.v.elementAt(i);
            if (tn.text != null && s.equals(tn.text)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean exists(final TreeNode node) {
        this.recount();
        for (int i = 0; i < this.count; ++i) {
            if (node == this.e.elementAt(i)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean exists(final String s) {
        this.recount();
        if (s == null) {
            return false;
        }
        for (int i = 0; i < this.count; ++i) {
            final TreeNode tn = this.e.elementAt(i);
            if (tn.text != null && s.equals(tn.text)) {
                return true;
            }
        }
        return false;
    }
    
    public void append(final TreeNode newNode, final boolean expand) {
        if (this.rootNode == null) {
            this.rootNode = newNode;
            this.selectedNode = this.rootNode;
            this.count = 1;
            return;
        }
        this.addSibling(newNode, this.rootNode, expand);
    }
    
    void addChild(final TreeNode newNode, final TreeNode relativeNode, final boolean expand) {
        if (relativeNode.child == null) {
            relativeNode.child = newNode;
            newNode.parent = relativeNode;
            if (expand) {
                relativeNode.expand();
            }
            ++this.count;
        }
        else {
            this.addSibling(newNode, relativeNode.child, expand);
        }
        ++relativeNode.numberOfChildren;
    }
    
    void addSibling(final TreeNode newNode, final TreeNode siblingNode, final boolean expand) {
        TreeNode tempNode;
        for (tempNode = siblingNode; tempNode.sibling != null; tempNode = tempNode.sibling) {}
        tempNode.sibling = newNode;
        newNode.parent = tempNode.parent;
        if (expand) {
            newNode.expand();
        }
        ++this.count;
    }
    
    public TreeNode remove(final String s) {
        this.recount();
        for (int i = 0; i < this.count; ++i) {
            final TreeNode tn = this.e.elementAt(i);
            if (tn.text != null && s.equals(tn.text)) {
                this.remove(tn);
                return tn;
            }
        }
        return null;
    }
    
    public void removeSelected() {
        if (this.selectedNode != null) {
            this.remove(this.selectedNode);
        }
    }
    
    public void remove(final TreeNode node) {
        if (!this.exists(node)) {
            return;
        }
        if (node == this.selectedNode) {
            int index = this.v.indexOf(this.selectedNode);
            if (index == -1) {
                index = this.e.indexOf(this.selectedNode);
            }
            if (index > this.viewCount - 1) {
                index = this.viewCount - 1;
            }
            if (index > 0) {
                this.changeSelection(this.v.elementAt(index - 1));
            }
            else if (this.viewCount > 0) {
                this.changeSelection(this.v.elementAt(1));
            }
        }
        if (node.parent != null) {
            if (node.parent.child == node) {
                if (node.sibling != null) {
                    node.parent.child = node.sibling;
                }
                else {
                    node.parent.child = null;
                    node.parent.collapse();
                }
            }
            else {
                TreeNode tn;
                for (tn = node.parent.child; tn.sibling != node; tn = tn.sibling) {}
                if (node.sibling != null) {
                    tn.sibling = node.sibling;
                }
                else {
                    tn.sibling = null;
                }
            }
        }
        else if (node == this.rootNode) {
            if (node.sibling == null) {
                this.rootNode = null;
            }
            else {
                this.rootNode = node.sibling;
            }
        }
        else {
            TreeNode tn;
            for (tn = this.rootNode; tn.sibling != node; tn = tn.sibling) {}
            if (node.sibling != null) {
                tn.sibling = node.sibling;
            }
            else {
                tn.sibling = null;
            }
        }
        this.recount();
    }
    
    private void recount() {
        this.count = 0;
        this.e = new Vector();
        if (this.rootNode != null) {
            this.rootNode.depth = 0;
            this.traverse(this.rootNode);
        }
    }
    
    protected void drawToolTip(final Graphics g2, int x, int y, final String s, final FontMetrics fm, final Color bc, final Color fc) {
        if (!this.sToolTip.equalsIgnoreCase("yes")) {
            return;
        }
        final Dimension d = this.size();
        if (x + fm.stringWidth(s) > d.width - this.sbVWidth && y > 0) {
            String sWord = "";
            String tmpS = "";
            String tmpS2 = "";
            String tmpS3 = "";
            String tmpS4 = "";
            int pos = 0;
            x = 0;
            pos = s.indexOf(32, pos);
            if (pos == -1) {
                for (int i = 0; i < s.length(); ++i) {
                    if (x + fm.stringWidth(tmpS2) < d.width - (this.sbVWidth + 10)) {
                        tmpS2 += s.charAt(i);
                    }
                    else {
                        tmpS3 += s.charAt(i);
                    }
                }
            }
            else {
                final StringTokenizer parser = new StringTokenizer(s.trim(), " ");
                while (parser.hasMoreTokens()) {
                    try {
                        sWord = parser.nextToken();
                        if (tmpS.equals("")) {
                            tmpS = sWord;
                        }
                        else {
                            tmpS = tmpS + " " + sWord;
                        }
                        if (x + fm.stringWidth(tmpS) > d.width - (this.sbVWidth + 10)) {
                            if (!tmpS2.trim().equals("")) {
                                tmpS3 = tmpS4;
                                break;
                            }
                            tmpS2 = tmpS4;
                            tmpS = "";
                            tmpS4 = "";
                        }
                        if (tmpS4.equals("")) {
                            tmpS4 = sWord;
                        }
                        else {
                            tmpS4 = tmpS4 + " " + sWord;
                        }
                    }
                    catch (Exception ex) {}
                }
                if (!tmpS4.equals("")) {
                    if (tmpS2.trim().equals("")) {
                        tmpS2 = tmpS4;
                    }
                    else {
                        tmpS3 = tmpS4;
                    }
                }
            }
            if (y + fm.getHeight() > d.height) {
                y = d.height - fm.getHeight() - 2;
            }
            g2.setColor(bc);
            if (tmpS3.equals("")) {
                g2.fillRect(x, y, d.width - 1 - x - this.sbVWidth, fm.getHeight() + 2);
                g2.setColor(fc);
                g2.drawRect(x, y, d.width - 1 - x - this.sbVWidth, fm.getHeight() + 2);
                this.g1.drawString(tmpS2, x + 5, y + this.cellSize - this.textBaseLine);
            }
            else {
                g2.fillRect(x, y, d.width - 1 - x - this.sbVWidth, fm.getHeight() * 2 + 2);
                g2.setColor(fc);
                g2.drawRect(x, y, d.width - 1 - x - this.sbVWidth, fm.getHeight() * 2 + 2);
                this.g1.drawString(tmpS2, x + 5, y + this.cellSize - this.textBaseLine);
                this.g1.drawString(tmpS3, x + 5, y + fm.getHeight() + this.cellSize - this.textBaseLine);
            }
            this.flgToolTip = true;
        }
    }
    
    private void traverse(final TreeNode node) {
        ++this.count;
        this.e.addElement(node);
        if (node.child != null) {
            node.child.depth = node.depth + 1;
            this.traverse(node.child);
        }
        if (node.sibling != null) {
            node.sibling.depth = node.depth;
            this.traverse(node.sibling);
        }
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
        this.viewCount = this.v.size();
    }
    
    private void vectorize(final TreeNode node) {
        if (node == null) {
            return;
        }
        this.v.addElement(node);
        if (node.isExpanded()) {
            node.child.depth = node.depth + 1;
            this.vectorize(node.child);
        }
        if (node.sibling != null) {
            node.sibling.depth = node.depth;
            this.vectorize(node.sibling);
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this.sbV) {
            if (event.arg == null) {
                return false;
            }
            if (this.sbVPosition != (int)event.arg) {
                this.sbVPosition = (int)event.arg;
                if (this.sbVPosition < 0) {
                    this.sbVPosition = 0;
                }
                else {
                    this.redraw();
                }
            }
        }
        if (event.target == this.sbH) {
            if (event.arg == null) {
                return false;
            }
            if (this.sbHPosition != this.sbH.getValue()) {
                this.sbHPosition = this.sbH.getValue();
                this.sbHPosition -= this.sbHPosition * 2;
                this.redraw();
            }
        }
        if (event.target == this && event.id == 505) {
            this.TreeView_MouseExit(event);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public boolean mouseMove(final Event event, final int x, final int y) {
        try {
            final int index = y / this.cellSize + this.sbVPosition;
            if (index > this.viewCount - 1) {
                if (this.oldSNode != null) {
                    this.mouseOver = false;
                    this.drawNodeText(this.oldSNode, (this.v.indexOf(this.oldSNode) - this.sbVPosition) * this.cellSize, true);
                    this.repaint();
                }
                this.oldNode = null;
                this.mouseOverNode = null;
                this.flgToolTip = false;
                this.redraw();
            }
            this.newNode = this.v.elementAt(index);
            if (this.newNode != this.oldNode) {
                if (this.flgToolTip) {
                    this.flgToolTip = false;
                    this.redraw();
                }
                if (this.flgMouseOverRect) {
                    this.flgMouseOverRect = false;
                    this.redraw();
                }
                if (this.oldSNode != null) {
                    this.mouseOver = false;
                    this.drawNodeText(this.oldSNode, (this.v.indexOf(this.oldSNode) - this.sbVPosition) * this.cellSize, true);
                }
                this.mouseOver = true;
                this.drawNodeText(this.newNode, (this.v.indexOf(this.newNode) - this.sbVPosition) * this.cellSize, true);
                this.repaint();
                this.oldNode = this.newNode;
            }
            this.repaint();
        }
        catch (Exception ex) {}
        return super.mouseMove(event, x, y);
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        final int index = y / this.cellSize + this.sbVPosition;
        if (index > this.viewCount - 1) {
            this.mouseOverNode = null;
            return false;
        }
        final TreeNode oldNode = this.selectedNode;
        final TreeNode newNode = this.v.elementAt(index);
        final int newDepth = newNode.getDepth();
        final Rectangle toggleBox = new Rectangle(this.cellSize * newDepth + this.cellSize / 4, (index - this.sbVPosition) * this.cellSize + this.clickSize / 2, this.clickSize, this.clickSize);
        if (toggleBox.inside(x, y)) {
            newNode.toggle();
            this.redraw();
        }
        else {
            this.changeSelection(newNode);
            if (this.dblClick.equalsIgnoreCase("yes")) {
                if (newNode == oldNode && event.when - this.timeMouseDown < this.doubleClickResolution) {
                    newNode.toggle();
                    this.redraw();
                    this.sendActionEvent(event);
                    return false;
                }
                this.timeMouseDown = event.when;
                this.redraw();
            }
            else {
                if (newNode != oldNode || event.when - this.timeMouseDown >= this.doubleClickResolution) {
                    newNode.toggle();
                    this.redraw();
                    this.sendActionEvent(event);
                    return false;
                }
                this.timeMouseDown = event.when;
            }
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int key) {
        int index = this.v.indexOf(this.selectedNode);
        switch (key) {
            case 10: {
                final TreeNode newNode = this.v.elementAt(index);
                this.changeSelection(newNode);
                newNode.toggle();
                this.redraw();
                event.id = 501;
                event.arg = new String(this.selectedNode.getText());
                return false;
            }
            case 1006: {
                if (this.selectedNode.isExpanded()) {
                    this.selectedNode.toggle();
                    this.redraw();
                    break;
                }
            }
            case 1004: {
                if (index > 0) {
                    --index;
                    this.changeSelection(this.v.elementAt(index));
                    this.redraw();
                    this.requestFocus();
                    break;
                }
                break;
            }
            case 1007: {
                if (this.selectedNode.isExpandable() && !this.selectedNode.isExpanded()) {
                    this.selectedNode.toggle();
                    this.redraw();
                    break;
                }
                if (!this.selectedNode.isExpandable()) {
                    break;
                }
            }
            case 1005: {
                if (index < this.viewCount - 1) {
                    ++index;
                    this.changeSelection(this.v.elementAt(index));
                    this.redraw();
                    this.requestFocus();
                    break;
                }
                break;
            }
        }
        return false;
    }
    
    private void sendActionEvent(final Event event) {
        final int id = event.id;
        final Object arg = event.arg;
        event.id = 1001;
        event.arg = new String(this.selectedNode.getText());
        this.postEvent(event);
        event.id = id;
        event.arg = arg;
    }
    
    public TreeNode getSelectedNode() {
        return this.selectedNode;
    }
    
    public String getSelectedText() {
        if (this.selectedNode == null) {
            return null;
        }
        return this.selectedNode.getText();
    }
    
    private void changeSelection(final TreeNode node) {
        if (node == this.selectedNode) {
            return;
        }
        final TreeNode oldNode = this.selectedNode;
        this.selectedNode = node;
        this.drawNodeText(oldNode, (this.v.indexOf(oldNode) - this.sbVPosition) * this.cellSize, true);
        this.drawNodeText(node, (this.v.indexOf(node) - this.sbVPosition) * this.cellSize, true);
        final int index = this.v.indexOf(this.selectedNode);
        if (index < this.sbVPosition) {
            --this.sbVPosition;
            this.sbV.setValue(this.sbVPosition);
            this.redraw();
            return;
        }
        if (index >= this.sbVPosition + (this.viewHeight - this.cellSize / 2) / this.cellSize) {
            ++this.sbVPosition;
            this.sbV.setValue(this.sbVPosition);
            this.redraw();
            return;
        }
        this.repaint();
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        final Dimension d = this.size();
        if (d.width != this.viewWidth || d.height != this.viewHeight) {
            this.redraw();
        }
        else if (Beans.isDesignTime()) {
            this.resetVector();
            this.layout();
            this.drawTree();
        }
        g.drawImage(this.im1, 0, 0, this);
    }
    
    public void redraw() {
        this.resetVector();
        this.bsbH = false;
        this.drawTree();
        if (this.bsbH) {
            this.sbH.show();
        }
        else {
            this.sbH.hide();
        }
        int iHeight = this.viewHeight / this.cellSize;
        if (this.bsbH) {
            iHeight = (this.viewHeight - this.sbHHeight) / this.cellSize;
        }
        if (this.viewCount > iHeight) {
            this.sbV.setValues(this.sbVPosition, iHeight, 0, this.viewCount);
            this.sbV.setPageIncrement(1);
            this.sbVWidth = 16;
            this.getParent().paintAll(this.getParent().getGraphics());
            this.sbV.show();
            this.layout();
        }
        else {
            this.sbV.hide();
            this.sbVWidth = 0;
            this.sbVPosition = 0;
            this.layout();
        }
        this.repaint();
    }
    
    public void drawTree() {
        final Dimension d = this.size();
        if (d.width != this.viewWidth || d.height != this.viewHeight || this.g1 == null) {
            this.im1 = this.createImage(d.width, d.height);
            if (this.g1 != null) {
                this.g1.dispose();
            }
            this.g1 = this.im1.getGraphics();
            this.viewWidth = d.width;
            this.viewHeight = d.height;
        }
        this.g1.setFont(this.fFont);
        this.setFont(this.fFont);
        this.fm = this.getFontMetrics(this.fFont);
        this.g1.setColor(this.getBackground());
        if (this.imgBG != null) {
            for (int x = 0; x < d.width; x += this.imgBG.getWidth(this)) {
                for (int i = 0; i < d.height; i += this.imgBG.getHeight(this)) {
                    this.g1.drawImage(this.imgBG, x, i, this.imgBG.getWidth(this), this.imgBG.getHeight(this), Color.black, this);
                }
            }
            this.repaint();
        }
        else {
            this.g1.fillRect(0, 0, this.viewWidth, this.viewHeight);
        }
        int lastOne = this.sbVPosition + this.viewHeight / this.cellSize;
        if (lastOne > this.viewCount) {
            lastOne = this.viewCount;
        }
        for (int i = this.sbVPosition; i < lastOne; ++i) {
            final TreeNode node = this.v.elementAt(i);
            int x2 = this.sbHPosition + this.cellSize * (node.depth + 1);
            final int y = (i - this.sbVPosition) * this.cellSize;
            if (this.sStyle.charAt(2) != 'X') {
                this.g1.setColor(this.getForeground());
                if (node.sibling != null) {
                    int k = this.v.indexOf(node.sibling) - i;
                    if (k > lastOne) {
                        k = lastOne;
                    }
                    this.drawDotLine(x2 - this.cellSize / 2, y + this.cellSize / 2, x2 - this.cellSize / 2, y + this.cellSize / 2 + k * this.cellSize);
                }
                for (int m = 0; m < i; ++m) {
                    final TreeNode sib = this.v.elementAt(m);
                    if (sib.sibling == node && m < this.sbVPosition) {
                        this.drawDotLine(x2 - this.cellSize / 2, 0, x2 - this.cellSize / 2, y + this.cellSize / 2);
                    }
                }
                if (node.isExpanded()) {
                    this.drawDotLine(x2 + this.cellSize / 2, y + this.cellSize, x2 + this.cellSize / 2, y + this.cellSize + this.cellSize / 2);
                }
                this.g1.setColor(this.getForeground());
                this.drawDotLine(x2 - this.cellSize / 2, y + this.cellSize / 2, x2 + this.cellSize / 2, y + this.cellSize / 2);
            }
            if (this.sStyle.charAt(0) == 'A') {
                if (node.isExpandable()) {
                    this.g1.setColor(this.getBackground());
                    this.g1.fillRect(this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4, y + this.clickSize / 2, this.clickSize, this.clickSize);
                    this.g1.setColor(this.cLineColor);
                    this.g1.drawRect(this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4, y + this.clickSize / 2, this.clickSize, this.clickSize);
                    this.g1.drawLine(this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4 + 2, y + this.cellSize / 2, this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4 + this.clickSize - 2, y + this.cellSize / 2);
                    if (!node.isExpanded()) {
                        this.g1.drawLine(this.sbHPosition + this.cellSize * node.depth + this.cellSize / 2, y + this.clickSize / 2 + 2, this.sbHPosition + this.cellSize * node.depth + this.cellSize / 2, y + this.clickSize / 2 + this.clickSize - 2);
                    }
                }
            }
            else if (node.isExpandable()) {
                this.xPts = new int[4];
                this.yPts = new int[4];
                if (!node.isExpanded()) {
                    if (this.sStyle.charAt(0) == 'E' || this.sStyle.charAt(0) == 'F') {
                        this.g1.setColor(this.cFolderCColor);
                        this.xPts[0] = this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4 + 1;
                        this.xPts[1] = this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4 + this.clickSize;
                        this.xPts[2] = this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4 + 1;
                        this.xPts[3] = this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4 + 1;
                        this.yPts[0] = y + 1;
                        this.yPts[1] = y + this.cellSize / 2;
                        this.yPts[2] = y + this.cellSize - 1;
                        this.yPts[3] = y + 1;
                        this.poly = new Polygon(this.xPts, this.yPts, this.xPts.length);
                        this.g1.fillPolygon(this.poly);
                    }
                    if (this.sStyle.charAt(0) == 'B' || this.sStyle.charAt(0) == 'C' || this.sStyle.charAt(0) == 'D') {
                        this.g1.setColor(this.cFolderCColor);
                        this.g1.fillRect(this.cellSize * node.depth + this.cellSize / 4, y + this.clickSize / 2, this.clickSize + 2, this.clickSize + 2);
                    }
                }
                else {
                    if (this.sStyle.charAt(0) == 'C' || this.sStyle.charAt(0) == 'E') {
                        this.g1.setColor(this.cFolderOColor);
                        this.xPts[0] = this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4 - 2;
                        this.xPts[1] = this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4 - 2 + this.clickSize + 2;
                        this.xPts[2] = this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4 - 2 + (this.clickSize + 2) / 2;
                        this.xPts[3] = this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4 - 2;
                        this.yPts[0] = y + 3;
                        this.yPts[1] = y + 3;
                        this.yPts[2] = y + this.cellSize - 3;
                        this.yPts[3] = y + 3;
                        this.poly = new Polygon(this.xPts, this.yPts, this.xPts.length);
                        this.g1.fillPolygon(this.poly);
                    }
                    if (this.sStyle.charAt(0) == 'D' || this.sStyle.charAt(0) == 'F') {
                        this.g1.setColor(this.cFolderOColor);
                        this.xPts[0] = this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4 - 2 + (this.clickSize + 2) / 2;
                        this.xPts[1] = this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4 - 2 + this.clickSize + 2;
                        this.xPts[2] = this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4 - 2;
                        this.xPts[3] = this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4 - 2 + (this.clickSize + 2) / 2;
                        this.yPts[0] = y + 3;
                        this.yPts[1] = y + this.cellSize - 3;
                        this.yPts[2] = y + this.cellSize - 3;
                        this.yPts[3] = y + 3;
                        this.poly = new Polygon(this.xPts, this.yPts, this.xPts.length);
                        this.g1.fillPolygon(this.poly);
                    }
                    if (this.sStyle.charAt(0) == 'B') {
                        this.g1.setColor(this.cFolderOColor);
                        this.xPts[0] = this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4 + 1;
                        this.xPts[1] = this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4 + this.clickSize;
                        this.xPts[2] = this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4 + 1;
                        this.xPts[3] = this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4 + 1;
                        this.yPts[0] = y + 1;
                        this.yPts[1] = y + this.cellSize / 2;
                        this.yPts[2] = y + this.cellSize - 1;
                        this.yPts[3] = y + 1;
                        this.poly = new Polygon(this.xPts, this.yPts, this.xPts.length);
                        this.g1.fillPolygon(this.poly);
                    }
                }
            }
            else if (this.sStyle.charAt(1) == 'A') {
                this.g1.setColor(this.cLeafColor);
                this.g1.fillRect(this.sbHPosition + this.cellSize * node.depth + this.cellSize / 4, y + this.clickSize / 2, this.clickSize, this.clickSize);
            }
            final Image nodeImage = node.getImage();
            if (this.sStyle.charAt(3) == 'A') {
                if (this.sStyle.charAt(0) != 'X' && this.sStyle.charAt(2) == 'X') {
                    --x2;
                }
                if (this.sStyle.charAt(2) == 'X' && this.sStyle.charAt(0) == 'X') {
                    x2 -= 10;
                }
                if (nodeImage != null) {
                    this.g1.drawImage(nodeImage, x2 + this.imageInset, y, this);
                }
            }
            if (node.text != null) {
                this.drawNodeText(node, y, node == this.selectedNode);
            }
        }
        if (this.flgBorder.equalsIgnoreCase("yes")) {
            this.g1.setColor(this.getForeground());
            this.g1.drawRect(0, 0, this.viewWidth - this.sbVWidth - 1, this.viewHeight - 1);
        }
    }
    
    private void drawNodeText(final TreeNode node, final int yPosition, final boolean eraseBackground) {
        final int depth = node.depth;
        int textOffset = this.sbHPosition + (depth + 1) * this.cellSize + this.cellSize + this.textInset;
        final Dimension d = this.size();
        if (this.sbHPosition < 0) {
            this.bsbH = true;
        }
        int iWidth = d.width;
        if (this.sbV.isShowing()) {
            iWidth += this.sbVWidth;
        }
        if (textOffset + this.fm.stringWidth(node.text) > iWidth && yPosition > -1 && !node.text.startsWith("____")) {
            this.bsbH = true;
        }
        if (this.sStyle.charAt(3) != 'A') {
            textOffset -= 15;
            if (this.sStyle.charAt(2) == 'X') {
                textOffset -= 5;
            }
        }
        else if (this.sStyle.charAt(0) == 'X' && this.sStyle.charAt(2) == 'X') {
            textOffset -= 5;
        }
        Color fg;
        if (node == this.selectedNode) {
            fg = this.fgHighlightColor;
            final Color bg = this.bgHighlightColor;
            this.mouseOverNode = node;
            if (eraseBackground) {
                this.g1.setColor(bg);
                this.g1.fillRect(textOffset - 1, yPosition + 1, this.fm.stringWidth(node.text) + 4, this.cellSize - 1);
            }
        }
        else {
            fg = this.getForeground();
            final Color bg = this.getBackground();
            if (this.mouseOver) {
                fg = this.fgMouseOverColor;
                this.mouseOverNode = node;
                this.oldSNode = node;
            }
        }
        this.g1.setColor(fg);
        this.g1.drawString(node.text, textOffset, yPosition + this.cellSize - this.textBaseLine);
        if (this.mouseOver) {
            if (this.sMouseOver.equalsIgnoreCase("rect")) {
                this.g1.setColor(this.cMouseOverFrame);
                this.g1.drawRect(textOffset - 1, yPosition + 1, this.fm.stringWidth(node.text) + 1, this.cellSize - 1);
                this.flgMouseOverRect = true;
            }
            if (this.sMouseOver.equalsIgnoreCase("line")) {
                this.g1.setColor(this.cMouseOverFrame);
                this.g1.drawRect(textOffset - 1, yPosition + 1 + this.fm.getHeight(), this.fm.stringWidth(node.text) + 1, 1);
                this.flgMouseOverRect = true;
            }
            this.drawToolTip(this.g1, textOffset - 1, yPosition + 1, node.text, this.fm, this.cToolTipBC, this.cToolTipFC);
            this.mouseOver = false;
        }
    }
    
    private void drawDotLine(final int x0, final int y0, final int x1, final int y1) {
        this.g1.setColor(this.cLineColor);
        int iStep1;
        if (this.sStyle.charAt(2) == 'A') {
            iStep1 = this.iStep;
        }
        else {
            iStep1 = 1;
        }
        if (y0 == y1) {
            for (int i = x0; i < x1; i += iStep1) {
                this.g1.drawLine(i, y0, i, y1);
            }
            return;
        }
        for (int i = y0; i < y1; i += iStep1) {
            this.g1.drawLine(x0, i, x1, i);
        }
    }
    
    public void setTreeStructure(final String[] s) {
        final TreeNode rootNode = null;
        this.topVisibleNode = rootNode;
        this.selectedNode = rootNode;
        this.rootNode = rootNode;
        this.treeStructure = s;
        try {
            this.parseTreeStructure();
        }
        catch (InvalidTreeNodeException e) {
            System.out.println(e);
        }
    }
    
    public String[] getTreeStructure() {
        return this.treeStructure;
    }
    
    private void parseTreeStructure() throws InvalidTreeNodeException {
        String[] tempStructure = null;
        String entry = null;
        tempStructure = this.treeStructure;
        entry = tempStructure[0];
        if (this.findLastPreSpace(entry) > -1) {
            throw new InvalidTreeNodeException();
        }
        TreeNode node = new TreeNode(entry.trim());
        node.setDepth(0);
        this.append(node, false);
        for (int i = 1; i < tempStructure.length; ++i) {
            entry = tempStructure[i];
            final int indentLevel = this.findLastPreSpace(entry);
            if (indentLevel == -1) {
                throw new InvalidTreeNodeException();
            }
            TreeNode currentNode = this.rootNode;
            for (int j = 0; j < indentLevel; ++j) {
                final int numberOfChildren = currentNode.numberOfChildren;
                TreeNode tempNode = null;
                if (numberOfChildren > 0) {
                    for (tempNode = currentNode.child; tempNode.sibling != null; tempNode = tempNode.sibling) {}
                }
                if (tempNode == null) {
                    break;
                }
                currentNode = tempNode;
            }
            final int diff = indentLevel - currentNode.getDepth();
            if (diff > 1) {
                throw new InvalidTreeNodeException();
            }
            node = new TreeNode(entry.trim());
            node.setDepth(indentLevel);
            if (diff == 1) {
                this.insert(node, currentNode, 0, false);
            }
            else {
                this.insert(node, currentNode, 1, false);
            }
        }
    }
    
    private int findLastPreSpace(final String s) {
        final int length = s.length();
        if (s.charAt(0) != ' ') {
            return -1;
        }
        for (int i = 1; i < length; ++i) {
            if (s.charAt(i) != ' ') {
                return i;
            }
        }
        return -1;
    }
    
    public synchronized Dimension preferredSize() {
        return new Dimension(175, 125);
    }
    
    public synchronized Dimension minimumSize() {
        return new Dimension(50, 50);
    }
    
    public void setLayout(final LayoutManager lm) {
    }
}
