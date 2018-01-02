// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.components;

import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.Scrollbar;
import java.awt.Panel;

public class NTreeView extends Panel
{
    public static final int CHILD = 0;
    public static final int NEXT = 1;
    public static final int LAST = 2;
    public static final int NODE_TOGGLED = 2001;
    public static final int NODE_EXPANDED = 2002;
    public static final int NODE_COLLAPSED = 2003;
    public static final int SEL_CHANGED = 1006;
    int sbVPosition;
    int sbVWidth;
    int sbHPosition;
    int sbHHeight;
    long sbVTimer;
    int cellSize;
    int clickSize;
    int imageInset;
    int textInset;
    int textBaseLine;
    int doubleClickResolution;
    protected NTreeNode rootNode;
    protected NTreeNode selectedNode;
    protected NTreeNode topVisibleNode;
    protected Scrollbar verticalScrollBar;
    protected boolean sbVShow;
    protected int count;
    protected int viewCount;
    protected Scrollbar horizontalScrollBar;
    protected int sbHSize;
    protected int newWidth;
    protected boolean sbHShow;
    protected boolean needResetVector;
    protected int sbHLineIncrement;
    protected int viewHeight;
    protected int viewWidth;
    protected int viewWidest;
    protected Vector v;
    protected Vector e;
    protected transient boolean redrawTriggered;
    transient boolean hasFocus;
    protected transient FontMetrics fm;
    transient long timeMouseDown;
    protected transient Image im1;
    protected transient Graphics g1;
    
    public NTreeView() {
        this.sbVTimer = -1L;
        this.cellSize = 16;
        this.clickSize = 8;
        this.imageInset = 3;
        this.textInset = 6;
        this.textBaseLine = 3;
        this.doubleClickResolution = 333;
        this.sbVShow = false;
        this.sbHShow = false;
        this.sbHLineIncrement = 4;
        this.viewHeight = 300;
        this.viewWidth = 300;
        this.redrawTriggered = false;
        this.hasFocus = false;
        super.setLayout(null);
        (this.verticalScrollBar = new Scrollbar(1)).hide();
        this.add(this.verticalScrollBar);
        (this.horizontalScrollBar = new Scrollbar(0)).hide();
        this.add(this.horizontalScrollBar);
        this.needResetVector = true;
    }
    
    public NTreeView(final NTreeNode nTreeNode) {
        this();
        this.rootNode = nTreeNode;
        this.selectedNode = nTreeNode;
        this.count = 1;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this.verticalScrollBar) {
            if (this.sbVPosition != this.verticalScrollBar.getValue()) {
                this.sbVPosition = this.verticalScrollBar.getValue();
                this.triggerRedraw();
            }
            return true;
        }
        if (event.target == this.horizontalScrollBar) {
            if (this.sbHPosition != this.horizontalScrollBar.getValue()) {
                this.sbHPosition = this.horizontalScrollBar.getValue();
                this.triggerRedraw();
            }
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void insert(final NTreeNode nTreeNode, final NTreeNode nTreeNode2, final int n) {
        if (nTreeNode == null || nTreeNode2 == null) {
            return;
        }
        if (this.exists(nTreeNode2)) {
            return;
        }
        switch (n) {
            case 0: {
                this.addChild(nTreeNode, nTreeNode2);
            }
            case 1: {
                this.addSibling(nTreeNode, nTreeNode2, false);
            }
            case 2: {
                this.addSibling(nTreeNode, nTreeNode2, true);
                break;
            }
        }
    }
    
    public void clear() {
        final NTreeNode nTreeNode = null;
        this.selectedNode = nTreeNode;
        this.rootNode = nTreeNode;
        this.count = 0;
        this.v = new Vector();
        this.e = new Vector();
        this.triggerRedraw();
        this.invalidate();
    }
    
    public NTreeNode getRootNode() {
        return this.rootNode;
    }
    
    public int getCount() {
        return this.count;
    }
    
    public int getViewCount() {
        return this.viewCount;
    }
    
    public boolean exists(final NTreeNode nTreeNode) {
        this.recount();
        for (int i = 0; i < this.count; ++i) {
            if (nTreeNode == this.e.elementAt(i)) {
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
            final NTreeNode nTreeNode = this.e.elementAt(i);
            if (nTreeNode.text != null && s.equals(nTreeNode.text)) {
                return true;
            }
        }
        return false;
    }
    
    public void append(final NTreeNode rootNode) {
        if (this.rootNode == null) {
            this.rootNode = rootNode;
            this.selectedNode = this.rootNode;
            this.count = 1;
            this.redrawTriggered = true;
            return;
        }
        this.recount();
        if (!this.e.contains(rootNode)) {
            this.addSibling(rootNode, this.rootNode, true);
        }
    }
    
    public void addChild(final NTreeNode child, final NTreeNode parent) {
        if (parent.child == null) {
            parent.child = child;
            child.parent = parent;
            ++this.count;
            this.redrawTriggered = true;
        }
        else {
            this.addSibling(child, parent.child, true);
        }
        ++parent.numberOfChildren;
    }
    
    public void addSibling(final NTreeNode nTreeNode, final NTreeNode nTreeNode2) {
        this.addSibling(nTreeNode, nTreeNode2, true);
    }
    
    public void addSibling(final NTreeNode nTreeNode, final NTreeNode nTreeNode2, final boolean b) {
        if (b) {
            NTreeNode sibling;
            for (sibling = nTreeNode2; sibling.sibling != null; sibling = sibling.sibling) {}
            sibling.sibling = nTreeNode;
        }
        else {
            nTreeNode.sibling = nTreeNode2.sibling;
            nTreeNode2.sibling = nTreeNode;
        }
        nTreeNode.parent = nTreeNode2.parent;
        ++this.count;
        this.redrawTriggered = true;
    }
    
    public NTreeNode getSelectedNode() {
        return this.selectedNode;
    }
    
    public String getSelectedText() {
        if (this.selectedNode == null) {
            return null;
        }
        return this.selectedNode.getText();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.redrawTriggered || size.width != this.viewWidth || size.height != this.viewHeight) {
            this.redraw(graphics);
        }
        graphics.translate(-this.sbHPosition, 0);
        graphics.clearRect(this.sbHPosition, 0, size.width - this.sbVWidth, size.height - this.sbHHeight);
        if (this.sbVShow && this.sbHShow) {
            graphics.setColor(Color.lightGray);
            graphics.fillRect(this.sbHPosition + size.width - this.sbVWidth, size.height - this.sbHHeight, this.sbVWidth, this.sbHHeight);
        }
        graphics.clipRect(this.sbHPosition, 0, size.width - this.sbVWidth, size.height - this.sbHHeight);
        graphics.drawImage(this.im1, 0, 0, this);
        graphics.setColor(Color.black);
        graphics.drawRect(this.sbHPosition, 0, size.width - this.sbVWidth - 1, size.height - this.sbHHeight - 1);
    }
    
    public void redraw() {
        this.triggerRedraw();
    }
    
    public void redraw(final Graphics graphics) {
        final Dimension size = this.size();
        this.redrawTriggered = false;
        if (this.needResetVector) {
            this.resetVector();
        }
        else {
            this.needResetVector = true;
        }
        this.newWidth = this.compWidth(graphics);
        final int n = (size.height - this.sbHHeight) / this.cellSize;
        if (this.viewCount > n) {
            this.sbVShow = true;
            this.sbVWidth = this.verticalScrollBar.preferredSize().width;
        }
        else {
            this.sbVShow = false;
            this.sbVWidth = 0;
            this.sbVPosition = 0;
        }
        if (this.newWidth > size.width - this.sbVWidth) {
            this.sbHShow = true;
            this.sbHHeight = this.horizontalScrollBar.preferredSize().height;
        }
        else {
            this.sbHShow = false;
            this.sbHHeight = 0;
            this.sbHPosition = 0;
        }
        this.drawTree();
        if (this.sbVShow) {
            this.verticalScrollBar.reshape(size.width - this.sbVWidth, 0, this.sbVWidth, size.height - this.sbHHeight);
            this.verticalScrollBar.setValues(this.sbVPosition, n, 0, this.viewCount - n);
            this.verticalScrollBar.setPageIncrement(n - 1);
            this.verticalScrollBar.show();
        }
        else {
            this.verticalScrollBar.hide();
        }
        if (this.sbHShow) {
            this.horizontalScrollBar.reshape(0, size.height - this.sbHHeight, size.width - this.sbVWidth, this.sbHHeight);
            this.horizontalScrollBar.setValues(this.sbHPosition, size.width - this.sbVWidth, 0, this.sbHSize - (size.width - this.sbVWidth));
            this.horizontalScrollBar.setPageIncrement(size.width - this.sbVWidth);
            this.horizontalScrollBar.setLineIncrement(this.sbHLineIncrement);
            this.horizontalScrollBar.show();
            return;
        }
        this.horizontalScrollBar.hide();
    }
    
    public void drawTree() {
        final Dimension size = this.size();
        if (this.needResetVector) {
            this.resetVector();
        }
        if (size.width != this.viewWidth || size.height != this.viewHeight || this.g1 == null || this.sbHSize != this.newWidth) {
            final int newWidth = this.newWidth;
            this.sbHSize = newWidth;
            this.im1 = this.createImage(Math.max(newWidth, size.width), size.height);
            if (this.g1 != null) {
                this.g1.dispose();
            }
            this.g1 = this.im1.getGraphics();
            this.viewWidth = size.width;
            this.viewHeight = size.height;
        }
        Font font = this.getFont();
        if (font == null) {
            font = new Font("Serif", 0, 13);
            this.g1.setFont(font);
            this.setFont(font);
        }
        if (this.g1.getFont() == null) {
            this.g1.setFont(font);
        }
        this.fm = this.g1.getFontMetrics();
        this.g1.setColor(this.getBackground());
        this.g1.fillRect(0, 0, this.im1.getWidth(this), size.height);
        int viewCount = this.sbVPosition + this.viewHeight / this.cellSize + 1;
        if (viewCount > this.viewCount) {
            viewCount = this.viewCount;
        }
        NTreeNode parent = null;
        if (!this.v.isEmpty()) {
            parent = this.v.elementAt(this.sbVPosition);
        }
        for (int i = this.sbVPosition; i < viewCount; ++i) {
            final NTreeNode nTreeNode = this.v.elementAt(i);
            final int n = this.cellSize * (nTreeNode.depth + 1);
            final int n2 = (i - this.sbVPosition) * this.cellSize;
            this.g1.setColor(this.getForeground());
            if (nTreeNode.sibling != null) {
                int n3 = this.v.indexOf(nTreeNode.sibling) - i;
                if (n3 > viewCount) {
                    n3 = viewCount;
                }
                this.drawDotLine(n - this.cellSize / 2, n2 + this.cellSize / 2, n - this.cellSize / 2, n2 + this.cellSize / 2 + n3 * this.cellSize);
            }
            for (int j = 0; j < i; ++j) {
                if (((NTreeNode)this.v.elementAt(j)).sibling == nTreeNode && j < this.sbVPosition) {
                    this.drawDotLine(n - this.cellSize / 2, 0, n - this.cellSize / 2, n2 + this.cellSize / 2);
                }
            }
            if (nTreeNode.isExpanded()) {
                this.drawDotLine(n + this.cellSize / 2, n2 + this.cellSize - 2, n + this.cellSize / 2, n2 + this.cellSize + this.cellSize / 2);
            }
            this.g1.setColor(this.getForeground());
            this.drawDotLine(n - this.cellSize / 2, n2 + this.cellSize / 2, n + this.cellSize / 2, n2 + this.cellSize / 2);
            this.drawNodeToggle(nTreeNode, n, n2);
            final Image image = nTreeNode.getImage();
            if (image != null) {
                this.g1.drawImage(image, n + this.imageInset, n2, this);
            }
            if (nTreeNode.text != null) {
                this.drawNodeText(nTreeNode, n2, nTreeNode == this.selectedNode);
            }
            if (parent.depth > nTreeNode.depth) {
                parent = nTreeNode;
            }
        }
        if (parent != null) {
            while ((parent = parent.parent) != null) {
                if (parent.sibling != null) {
                    this.drawDotLine(this.cellSize * (parent.depth + 1) - this.cellSize / 2, 0, this.cellSize * (parent.depth + 1) - this.cellSize / 2, size.height);
                }
            }
        }
        this.needResetVector = true;
    }
    
    protected void drawNodeToggle(final NTreeNode nTreeNode, final int n, final int n2) {
        if (nTreeNode.isExpandable()) {
            this.g1.setColor(this.getBackground());
            this.g1.fillRect(this.cellSize * nTreeNode.depth + this.cellSize / 4, n2 + this.clickSize / 2, this.clickSize, this.clickSize);
            this.g1.setColor(this.getForeground());
            this.g1.drawRect(this.cellSize * nTreeNode.depth + this.cellSize / 4, n2 + this.clickSize / 2, this.clickSize, this.clickSize);
            this.g1.drawLine(this.cellSize * nTreeNode.depth + this.cellSize / 4 + 2, n2 + this.cellSize / 2, this.cellSize * nTreeNode.depth + this.cellSize / 4 + this.clickSize - 2, n2 + this.cellSize / 2);
            if (!nTreeNode.isExpanded()) {
                this.g1.drawLine(this.cellSize * nTreeNode.depth + this.cellSize / 2, n2 + this.clickSize / 2 + 2, this.cellSize * nTreeNode.depth + this.cellSize / 2, n2 + this.clickSize / 2 + this.clickSize - 2);
            }
        }
    }
    
    public synchronized Dimension getPreferredSize() {
        final Dimension size = this.size();
        final Dimension minimumSize = this.getMinimumSize();
        return new Dimension(Math.max(size.width, minimumSize.width), Math.max(size.height, minimumSize.height));
    }
    
    public synchronized Dimension preferredSize() {
        return this.getPreferredSize();
    }
    
    public synchronized Dimension getMinimumSize() {
        return new Dimension(20, 40);
    }
    
    public synchronized Dimension minimumSize() {
        return this.getMinimumSize();
    }
    
    public void triggerRedraw() {
        this.redrawTriggered = true;
        this.repaint();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.requestFocus();
        final int n3 = n2 / this.cellSize + this.sbVPosition;
        if (n3 > this.viewCount - 1) {
            return false;
        }
        final NTreeNode selectedNode = this.selectedNode;
        final NTreeNode nTreeNode = this.v.elementAt(n3);
        if (new Rectangle(this.cellSize * nTreeNode.getDepth() + this.cellSize / 4, (n3 - this.sbVPosition) * this.cellSize + this.clickSize / 2, this.clickSize, this.clickSize).inside(n, n2)) {
            nTreeNode.toggle();
            this.resetVector();
            if (!nTreeNode.isExpanded() && !this.v.contains(this.selectedNode)) {
                this.changeSelection(nTreeNode);
            }
            this.triggerRedraw();
            this.invalidate();
            this.sendActionEvent();
        }
        else {
            this.changeSelection(nTreeNode);
            if (nTreeNode == selectedNode && event.when - this.timeMouseDown < this.doubleClickResolution) {
                nTreeNode.toggle();
                this.resetVector();
                this.triggerRedraw();
                this.invalidate();
                this.sendActionEvent();
                return false;
            }
            this.timeMouseDown = event.when;
        }
        return false;
    }
    
    public boolean keyDown(final Event event, final int n) {
        int index = this.v.indexOf(this.selectedNode);
        Label_0290: {
            switch (n) {
                case 13: {
                    this.sendActionEvent();
                    this.requestFocus();
                    break;
                }
                case 1006: {
                    if (event.controlDown()) {
                        if (this.sbHPosition > 0) {
                            final Scrollbar horizontalScrollBar = this.horizontalScrollBar;
                            final int sbHPosition = this.sbHPosition - this.sbHLineIncrement;
                            this.sbHPosition = sbHPosition;
                            horizontalScrollBar.setValue(Math.max(sbHPosition, 0));
                            this.repaint();
                            break;
                        }
                        break;
                    }
                    else {
                        if (this.selectedNode.isExpanded()) {
                            this.selectedNode.toggle();
                            this.triggerRedraw();
                            break;
                        }
                        break Label_0290;
                    }
                    break;
                }
                case 1004: {
                    if (index > 0) {
                        --index;
                        this.changeSelection((NTreeNode)this.v.elementAt(index));
                        this.requestFocus();
                        break;
                    }
                    break;
                }
                case 1007: {
                    if (event.controlDown()) {
                        final int maximum = this.horizontalScrollBar.getMaximum();
                        if (this.sbHShow && this.sbHPosition < maximum) {
                            final Scrollbar horizontalScrollBar2 = this.horizontalScrollBar;
                            final int sbHPosition2 = this.sbHPosition + this.sbHLineIncrement;
                            this.sbHPosition = sbHPosition2;
                            horizontalScrollBar2.setValue(Math.min(sbHPosition2, maximum));
                            this.repaint();
                            break;
                        }
                        break;
                    }
                    else {
                        if (this.selectedNode.isExpandable() && !this.selectedNode.isExpanded()) {
                            this.selectedNode.toggle();
                            this.sendActionEvent();
                            this.triggerRedraw();
                            break;
                        }
                        if (!this.selectedNode.isExpandable()) {
                            break;
                        }
                        break Label_0290;
                    }
                    break;
                }
                case 1005: {
                    if (index < this.viewCount - 1) {
                        ++index;
                        this.changeSelection((NTreeNode)this.v.elementAt(index));
                        this.requestFocus();
                        break;
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    protected void drawNodeText(final NTreeNode nTreeNode, final int n, final boolean b) {
        if (nTreeNode == null) {
            return;
        }
        final int n2 = (nTreeNode.depth + 1) * this.cellSize + this.cellSize + this.textInset - ((nTreeNode.getImage() == null) ? 12 : 0);
        Color color;
        Color background;
        if (nTreeNode == this.selectedNode && this.hasFocus) {
            color = Color.white;
            background = new Color(0, 0, 128);
        }
        else {
            color = this.getForeground();
            background = this.getBackground();
        }
        if (b) {
            this.g1.setColor(background);
            this.g1.fillRect(n2 - 1, n + 1, this.fm.stringWidth(nTreeNode.text) + 4, this.cellSize - 1);
        }
        if (nTreeNode == this.selectedNode) {
            this.g1.setColor(this.getForeground());
            this.g1.drawRect(n2 - 1, n + 1, this.fm.stringWidth(nTreeNode.text) + 3, this.cellSize - 2);
            this.repaint(Math.max(0, n2 - 1 - this.sbHPosition), n + 1, this.fm.stringWidth(nTreeNode.text) + 4, this.cellSize - 1);
        }
        this.g1.setColor(color);
        this.g1.drawString(nTreeNode.text, n2, n + this.cellSize - this.textBaseLine);
    }
    
    protected void sendActionEvent() {
    }
    
    protected int compWidth(final Graphics graphics) {
        int n = 0;
        Font font = this.getFont();
        if (font == null) {
            font = new Font("Serif", 0, 13);
            if (graphics != null) {
                graphics.setFont(font);
            }
            this.setFont(font);
        }
        if (graphics == null) {
            this.fm = null;
        }
        else {
            this.fm = graphics.getFontMetrics();
        }
        if (this.fm == null) {
            this.fm = this.getFontMetrics(font);
        }
        if (this.fm == null || this.v == null) {
            n = 100;
        }
        else {
            for (int i = 0; i < this.v.size(); ++i) {
                final NTreeNode nTreeNode = this.v.elementAt(i);
                final int n2 = (nTreeNode.depth + 1) * this.cellSize + this.cellSize + this.textInset - ((nTreeNode.getImage() == null) ? 12 : 0);
                if (n < n2 + this.fm.stringWidth(nTreeNode.text) + 6) {
                    n = n2 + this.fm.stringWidth(nTreeNode.text) + 6;
                }
            }
        }
        return n;
    }
    
    protected void drawDotLine(final int n, final int n2, final int n3, final int n4) {
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
    
    protected void changeSelection(final NTreeNode selectedNode) {
        if (selectedNode == this.selectedNode) {
            return;
        }
        final NTreeNode selectedNode2 = this.selectedNode;
        this.selectedNode = selectedNode;
        this.drawNodeText(selectedNode2, (this.v.indexOf(selectedNode2) - this.sbVPosition) * this.cellSize, true);
        this.drawNodeText(selectedNode, (this.v.indexOf(selectedNode) - this.sbVPosition) * this.cellSize, true);
        final int index = this.v.indexOf(this.selectedNode);
        if (index < this.sbVPosition) {
            --this.sbVPosition;
            this.verticalScrollBar.setValue(this.sbVPosition);
            this.triggerRedraw();
            return;
        }
        if (index >= this.sbVPosition + (this.viewHeight - this.cellSize / 2) / this.cellSize) {
            ++this.sbVPosition;
            this.verticalScrollBar.setValue(this.sbVPosition);
            this.triggerRedraw();
            return;
        }
        this.repaint();
    }
    
    protected NTreeNode newTreeNode(final String s, final NTreeView nTreeView) {
        return new NTreeNode(s, nTreeView);
    }
    
    protected void parseTreeStructure(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            final String s = array[i];
            final int lastPreSpace = this.findLastPreSpace(s);
            if (lastPreSpace == -1) {
                throw new ArrayIndexOutOfBoundsException("parseTreeStructure: EmptyStrings");
            }
            final NTreeNode treeNode = this.newTreeNode(s.trim(), this);
            treeNode.setDepth(lastPreSpace);
            if (this.rootNode == null) {
                if (lastPreSpace != 0) {
                    throw new ArrayIndexOutOfBoundsException("parseTreeStructure: EmptyStrings");
                }
                this.append(treeNode);
            }
            else {
                NTreeNode nTreeNode;
                for (nTreeNode = this.rootNode; nTreeNode.sibling != null; nTreeNode = nTreeNode.sibling) {}
                for (int j = 1; j < lastPreSpace; ++j) {
                    final int numberOfChildren = nTreeNode.numberOfChildren;
                    NTreeNode nTreeNode2 = null;
                    if (numberOfChildren > 0) {
                        for (nTreeNode2 = nTreeNode.child; nTreeNode2.sibling != null; nTreeNode2 = nTreeNode2.sibling) {}
                    }
                    if (nTreeNode2 == null) {
                        break;
                    }
                    nTreeNode = nTreeNode2;
                }
                final int n = lastPreSpace - nTreeNode.getDepth();
                if (n > 1) {
                    throw new ArrayIndexOutOfBoundsException("parseTreeStructure: no root");
                }
                if (n == 1) {
                    this.insert(treeNode, nTreeNode, 0);
                }
                else {
                    this.insert(treeNode, nTreeNode, 1);
                }
            }
        }
    }
    
    protected void recount() {
        this.count = 0;
        this.e = new Vector();
        if (this.rootNode != null) {
            this.rootNode.depth = 0;
            this.traverse(this.rootNode);
        }
    }
    
    protected void traverse(final NTreeNode nTreeNode) {
        ++this.count;
        this.e.addElement(nTreeNode);
        if (nTreeNode.child != null) {
            nTreeNode.child.depth = nTreeNode.depth + 1;
            this.traverse(nTreeNode.child);
        }
        if (nTreeNode.sibling != null) {
            nTreeNode.sibling.depth = nTreeNode.depth;
            this.traverse(nTreeNode.sibling);
        }
    }
    
    protected void resetVector() {
        this.v = new Vector(this.count);
        this.viewWidest = 30;
        if (this.count < 1) {
            this.viewCount = 0;
            return;
        }
        this.rootNode.depth = 0;
        this.vectorize(this.rootNode, true, this.v);
        this.viewCount = this.v.size();
        this.needResetVector = false;
    }
    
    protected void vectorize(final NTreeNode nTreeNode, final boolean b, final Vector vector) {
        if (nTreeNode == null) {
            return;
        }
        vector.addElement(nTreeNode);
        if ((!b && nTreeNode.child != null) || nTreeNode.isExpanded()) {
            nTreeNode.child.depth = nTreeNode.depth + 1;
            this.vectorize(nTreeNode.child, b, vector);
        }
        if (nTreeNode.sibling != null) {
            nTreeNode.sibling.depth = nTreeNode.depth;
            this.vectorize(nTreeNode.sibling, b, vector);
        }
    }
    
    protected void debugVector() {
        this.v.size();
        for (int i = 0; i < this.count; ++i) {
            System.out.println(((NTreeNode)this.v.elementAt(i)).text);
        }
    }
    
    protected int findLastPreSpace(final String s) {
        if (s != null && s.length() > 0) {
            final int length = s.length();
            if (s.charAt(0) != ' ' && s.charAt(0) != '\t') {
                return 0;
            }
            for (int i = 1; i < length; ++i) {
                if (s.charAt(i) != ' ' && s.charAt(i) != '\t') {
                    return i;
                }
            }
        }
        return -1;
    }
}
