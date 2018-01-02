// 
// Decompiled by Procyon v0.5.30
// 

package newstick.menu;

import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Menu implements MouseMotionListener, MouseListener, Runnable
{
    private Component component;
    private Image image;
    private Font font;
    private Graphics graphics;
    private Color backColor;
    private Color foreColor;
    private Color outlineColor;
    private Color highliteBackColor;
    private Color highliteForeColor;
    private IMenuLinkPainter menuLinkPainter;
    private IMenuItemAction menuItemAction;
    private float align;
    private Dimension size;
    private Point anchorPoint;
    private Rectangle area;
    private Rectangle expandArea;
    private Rectangle linkArea;
    private Rectangle bridgeArea;
    private FontMetrics fontM;
    private Vector items;
    private MenuItem highliteItem;
    private Thread scrollThread;
    private int scrollArrowHeight;
    private boolean showed;
    private boolean inited;
    private ScrollInfo scroll;
    private ArrowInfo arrow;
    private boolean buttonPressing;
    private boolean scrolledPress;
    private boolean scrollTypeUpDown;
    private int hMarg;
    private int vMarg;
    private int hMarg3d;
    private int vMarg3d;
    
    private void createImage() {
        this.size = this.autoDimension();
        if (this.size.height > this.area.height) {
            int n = 0;
            while (this.size.height > this.area.height) {
                final Dimension size = this.size;
                size.height -= this.fontM.getHeight();
                ++n;
            }
            if (this.area.height - this.size.height >= this.fontM.getHeight() / 3) {
                this.scroll.arrowBlink = true;
                final Dimension size2 = this.size;
                size2.height += this.fontM.getHeight() / 3;
            }
            else {
                this.scroll.arrowBlink = false;
            }
            this.scroll.scroll = true;
            this.scroll.firstVisibleItem = 0;
            this.scroll.scrollItems = this.items.size() - n - 1;
            if (this.scroll.arrowBlink) {
                this.scroll.arrowUpVisible = false;
            }
            else {
                this.scroll.arrowUpVisible = true;
            }
            this.scroll.arrowDownVisible = true;
            this.arrow.height = this.fontM.getHeight() / 3;
            this.arrow.width = this.fontM.stringWidth("W") / 2;
            this.arrow.space = this.fontM.stringWidth("W");
            this.arrow.count = 1;
            this.arrow.left = (this.size.width - (this.arrow.width * 2 * this.arrow.count + this.arrow.space * (this.arrow.count - 1))) / 2;
        }
        this.image = this.component.createImage(this.size.width, this.size.height);
        if (this.align == 1.0f) {
            this.anchorPoint = new Point(this.area.x + this.area.width - this.size.width + 1, this.area.y);
        }
        else {
            this.anchorPoint = new Point(this.area.x, this.area.y);
        }
        if (this.scroll.scroll) {
            this.scroll.areaUp = new Rectangle(this.anchorPoint.x + this.hMarg3d, this.anchorPoint.y + this.vMarg3d + 2, this.size.width - 2 * this.hMarg3d, this.fontM.getHeight() / 2);
            this.scroll.areaDown = new Rectangle(this.anchorPoint.x + this.hMarg3d, this.anchorPoint.y + (this.size.height - this.vMarg3d - 3) - this.arrow.height, this.size.width - 2 * this.hMarg3d, this.fontM.getHeight() / 2);
        }
        this.expandArea = new Rectangle(this.anchorPoint.x, this.anchorPoint.y, this.size.width, this.size.height);
        this.bridgeArea = this.createBridgeArea(this.linkArea, this.expandArea);
        this.graphics = this.image.getGraphics();
    }
    
    private void sleep(final int n) {
        if (n > 0) {
            try {
                Thread.currentThread();
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.buttonPressing = false;
    }
    
    public void add(final MenuItem menuItem) {
        this.items.addElement(menuItem);
    }
    
    private void paintArrowUp(final Color color) {
        this.graphics.setColor(color);
        int left = this.arrow.left;
        final int height = this.arrow.height;
        final int n = this.vMarg3d + 3 + height;
        for (int i = 0; i < this.arrow.count; ++i) {
            this.graphics.drawLine(left, n - 1, left + this.arrow.width, n - height - 1);
            this.graphics.drawLine(left, n + 1, left + this.arrow.width, n - height + 1);
            final int n2 = left + this.arrow.width;
            this.graphics.drawLine(n2, n - height - 1, n2 + this.arrow.width, n - 1);
            this.graphics.drawLine(n2, n - height + 1, n2 + this.arrow.width, n + 1);
            left = n2 + (this.arrow.space + this.arrow.width);
        }
    }
    
    public void setAlign(final float align) {
        this.align = align;
    }
    
    public float getAlign() {
        return this.align;
    }
    
    private Rectangle createBridgeArea(final Rectangle rectangle, final Rectangle rectangle2) {
        final Rectangle rectangle3 = new Rectangle(rectangle.x, rectangle.y + rectangle.height - 1, rectangle.width, 10);
        while (!rectangle2.intersects(rectangle3)) {
            rectangle3.grow(0, 10);
        }
        return rectangle3;
    }
    
    public void scrollThreadRun(final boolean scrollTypeUpDown) {
        this.buttonPressing = true;
        this.scrollTypeUpDown = scrollTypeUpDown;
        if (this.scrollThread != null) {
            this.scrollThread.stop();
        }
        (this.scrollThread = new Thread(this)).start();
    }
    
    public void highlite(final int n) {
        this.highlite(this.items.elementAt(n));
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.show(false);
    }
    
    public void highlite(final MenuItem highliteItem) {
        if (this.inited) {
            if (this.highliteItem != null) {
                this.internalHighlite(this.highliteItem, this.graphics, this.backColor, this.foreColor);
            }
            if (this.items.contains(highliteItem)) {
                this.internalHighlite(highliteItem, this.graphics, this.highliteBackColor, this.highliteForeColor);
                this.highliteItem = highliteItem;
            }
        }
    }
    
    public void setBackColor(final Color backColor) {
        this.backColor = backColor;
    }
    
    public void setHighliteForeColor(final Color highliteForeColor) {
        this.highliteForeColor = highliteForeColor;
    }
    
    private void scrollUp() {
        if (this.scroll.firstVisibleItem > 0) {
            final ScrollInfo scroll = this.scroll;
            --scroll.firstVisibleItem;
            if (this.scroll.arrowBlink && this.scroll.firstVisibleItem == 0) {
                this.scroll.arrowUpVisible = false;
            }
            else {
                this.scroll.arrowUpVisible = true;
            }
            this.scroll.arrowDownVisible = true;
            this.repaint();
            if (this.highliteItem != null) {
                this.highlite(this.scroll.firstVisibleItem);
            }
        }
    }
    
    public void setHighliteBackColor(final Color highliteBackColor) {
        this.highliteBackColor = highliteBackColor;
    }
    
    public Dimension getSize() {
        return this.size;
    }
    
    public void setMenuLinkPainter(final IMenuLinkPainter menuLinkPainter) {
        this.menuLinkPainter = menuLinkPainter;
    }
    
    public IMenuLinkPainter getMenuLinkPainter() {
        return this.menuLinkPainter;
    }
    
    private void internalPaintItems(final Graphics graphics) {
        if (this.font != null) {
            graphics.setFont(this.font);
        }
        graphics.setColor(this.foreColor);
        final int n = this.hMarg + this.hMarg3d;
        int n2 = this.vMarg + this.vMarg3d + this.fontM.getHeight() - this.fontM.getDescent();
        if (this.scroll.scroll) {
            n2 += this.fontM.getHeight() / 2 - 1;
        }
        int firstVisibleItem = 0;
        int n3 = this.items.size();
        if (this.scroll.scroll) {
            firstVisibleItem = this.scroll.firstVisibleItem;
            n3 = this.scroll.scrollItems;
            if (this.scroll.arrowBlink) {
                if (!this.scroll.arrowUpVisible) {
                    n2 -= this.fontM.getHeight() / 2;
                    ++n3;
                }
                if (!this.scroll.arrowDownVisible) {
                    --firstVisibleItem;
                    ++n3;
                }
                if (this.scroll.arrowUpVisible && this.scroll.arrowDownVisible) {
                    n2 += this.fontM.getHeight() / 3 / 2;
                }
            }
        }
        for (int i = firstVisibleItem; i < n3 + firstVisibleItem; ++i) {
            final MenuItem menuItem = this.items.elementAt(i);
            graphics.drawString(menuItem.Display, n, n2);
            menuItem.Location = new Point(n, n2);
            menuItem.Area = new Rectangle(this.hMarg3d + 1, n2 - this.fontM.getHeight() + this.fontM.getDescent() + 1, this.size.width - (2 * this.hMarg3d + 2), this.fontM.getHeight());
            n2 += this.fontM.getHeight();
        }
    }
    
    private void paintMenu() {
        this.graphics.setColor(this.backColor);
        this.graphics.fillRect(0, 0, this.size.width - 1, this.size.height - 1);
        this.graphics.setColor(this.outlineColor);
        this.graphics.drawRect(0, 0, this.size.width - 1, this.size.height - 1);
        this.graphics.setColor(this.backColor);
        this.graphics.draw3DRect(this.hMarg3d, this.vMarg3d, this.size.width - (2 * this.hMarg3d + 1), this.size.height - (2 * this.vMarg3d + 1), true);
    }
    
    private void scrollDown() {
        if (this.scroll.firstVisibleItem + this.scroll.scrollItems < this.items.size()) {
            final ScrollInfo scroll = this.scroll;
            ++scroll.firstVisibleItem;
            if (this.scroll.arrowBlink && this.scroll.firstVisibleItem + this.scroll.scrollItems == this.items.size()) {
                this.scroll.arrowDownVisible = false;
            }
            else {
                this.scroll.arrowDownVisible = true;
            }
            this.scroll.arrowUpVisible = true;
            this.repaint();
            if (this.highliteItem != null) {
                this.highlite(this.scroll.firstVisibleItem + this.scroll.scrollItems - 1);
            }
        }
    }
    
    private Dimension autoDimension() {
        final Graphics graphics = this.component.createImage(10, 10).getGraphics();
        if (this.font != null) {
            graphics.setFont(this.font);
        }
        this.fontM = graphics.getFontMetrics();
        int stringWidth = 0;
        int n = 0;
        for (int i = 0; i < this.items.size(); ++i) {
            final String display = this.items.elementAt(i).Display;
            if (this.fontM.stringWidth(display) > stringWidth) {
                stringWidth = this.fontM.stringWidth(display);
            }
            n += this.fontM.getHeight();
        }
        final int n2 = stringWidth + (2 * this.hMarg + 2 * this.hMarg3d);
        final int n3 = n + (2 * this.vMarg + 2 * this.vMarg3d);
        if (this.area == null) {
            this.area = new Rectangle(0, 0, this.component.getSize().width, this.component.getSize().height);
        }
        return new Dimension(Math.min(n2, this.area.width), n3);
    }
    
    public void setVertMargin(final int vMarg) {
        this.vMarg = vMarg;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (!this.showed) {
            return;
        }
        this.buttonPressing = false;
        if (this.scrolledPress) {
            this.scrolledPress = false;
            mouseEvent.consume();
            return;
        }
        MenuItem highliteItem = null;
        if (this.highliteItem != null) {
            highliteItem = this.highliteItem;
        }
        if (highliteItem != null && highliteItem == this.getItemByPoint(new Point(mouseEvent.getPoint().x - this.anchorPoint.x, mouseEvent.getPoint().y - this.anchorPoint.y))) {
            mouseEvent.consume();
            if (this.menuItemAction != null) {
                this.menuItemAction.menuItemAction(highliteItem);
            }
            this.show(false);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!this.showed) {
            return;
        }
        if (this.scroll.scroll) {
            if (this.scroll.areaUp.contains(mouseEvent.getPoint()) && this.scroll.arrowUpVisible) {
                this.scrollThreadRun(this.scrolledPress = true);
                mouseEvent.consume();
                return;
            }
            if (this.scroll.areaDown.contains(mouseEvent.getPoint()) && this.scroll.arrowDownVisible) {
                this.scrolledPress = true;
                this.scrollThreadRun(false);
                mouseEvent.consume();
            }
        }
    }
    
    public int getVertMargin() {
        return this.vMarg;
    }
    
    public Menu(final Component component, final Rectangle linkArea) {
        this.backColor = Color.lightGray;
        this.foreColor = Color.black;
        this.outlineColor = Color.black;
        this.highliteBackColor = Color.darkGray;
        this.highliteForeColor = Color.white;
        this.hMarg = 4;
        this.vMarg = 2;
        this.hMarg3d = 2;
        this.vMarg3d = 2;
        this.component = component;
        if (this.component instanceof IMenuLinkPainter) {
            this.menuLinkPainter = (IMenuLinkPainter)this.component;
        }
        if (this.component instanceof IMenuItemAction) {
            this.menuItemAction = (IMenuItemAction)this.component;
        }
        this.linkArea = linkArea;
        this.component.addMouseMotionListener(this);
        this.component.addMouseListener(this);
        this.items = new Vector();
        this.scroll = new ScrollInfo();
        this.scroll.scroll = false;
        this.arrow = new ArrowInfo();
        this.showed = false;
        this.inited = false;
        this.buttonPressing = false;
        this.align = 1.0f;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (!this.inited) {
            return;
        }
        if (!this.showed) {
            if (this.linkArea.contains(mouseEvent.getPoint())) {
                this.repaint();
                this.show(true);
            }
        }
        else if (!this.expandArea.contains(mouseEvent.getPoint()) && !this.bridgeArea.contains(mouseEvent.getPoint()) && !this.linkArea.contains(mouseEvent.getPoint())) {
            this.show(false);
        }
        else {
            mouseEvent.consume();
            final MenuItem itemByPoint = this.getItemByPoint(new Point(mouseEvent.getX() - this.anchorPoint.x, mouseEvent.getY() - this.anchorPoint.y));
            if (itemByPoint != null) {
                this.highlite(itemByPoint);
            }
            this.component.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.inited && this.isShowed()) {
            graphics.drawImage(this.image, this.anchorPoint.x, this.anchorPoint.y, this.component);
        }
    }
    
    private void repaint() {
        if (this.highliteItem != null) {
            this.internalHighlite(this.highliteItem, this.graphics, this.backColor, this.foreColor);
        }
        this.paintItems();
    }
    
    public MenuItem getItemByPoint(final Point point) {
        int firstVisibleItem = 0;
        int n = this.items.size();
        if (this.scroll.scroll) {
            firstVisibleItem = this.scroll.firstVisibleItem;
            n = this.scroll.scrollItems;
            if (this.scroll.arrowBlink) {
                if (!this.scroll.arrowUpVisible || !this.scroll.arrowDownVisible) {
                    ++n;
                }
                if (!this.scroll.arrowDownVisible) {
                    --firstVisibleItem;
                }
            }
        }
        for (int i = firstVisibleItem; i < n + firstVisibleItem; ++i) {
            final MenuItem menuItem = this.items.elementAt(i);
            if (menuItem.Area != null && menuItem.Area.contains(point)) {
                return menuItem;
            }
        }
        return null;
    }
    
    public void setForeColor(final Color foreColor) {
        this.foreColor = foreColor;
    }
    
    public void setOutlineColor(final Color outlineColor) {
        this.outlineColor = outlineColor;
    }
    
    public boolean isShowed() {
        return this.showed;
    }
    
    public void addRange(final MenuItem[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.add(array[i]);
        }
    }
    
    public void setFont(final Font font) {
        this.font = font;
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public void setHorizMargin(final int hMarg) {
        this.hMarg = hMarg;
    }
    
    public int getHorizMargin() {
        return this.hMarg;
    }
    
    private void show(final boolean showed) {
        if (!(this.showed = showed)) {
            if (this.scrollThread != null) {
                this.scrollThread.stop();
            }
            this.highliteItem = null;
            this.scroll.firstVisibleItem = 0;
            if (this.scroll.arrowBlink) {
                this.scroll.arrowUpVisible = false;
            }
            else {
                this.scroll.arrowUpVisible = true;
            }
            this.scroll.arrowDownVisible = true;
            if (this.menuLinkPainter != null) {
                this.menuLinkPainter.menuLinkPaint();
            }
            this.component.repaint();
        }
    }
    
    public void setMenuItemAction(final IMenuItemAction menuItemAction) {
        this.menuItemAction = menuItemAction;
    }
    
    public IMenuItemAction getMenuItemAction() {
        return this.menuItemAction;
    }
    
    public void clear() {
        this.items.removeAllElements();
    }
    
    public void setArea(final Rectangle area) {
        this.area = area;
    }
    
    public Rectangle getArea() {
        return this.area;
    }
    
    private void paintArrowDown(final Color color) {
        this.graphics.setColor(color);
        int left = this.arrow.left;
        final int height = this.arrow.height;
        final int n = this.size.height - this.vMarg3d - 4 - height;
        for (int i = 0; i < this.arrow.count; ++i) {
            this.graphics.drawLine(left, n + 1, left + this.arrow.width, n + height + 1);
            this.graphics.drawLine(left, n - 1, left + this.arrow.width, n + height - 1);
            final int n2 = left + this.arrow.width;
            this.graphics.drawLine(n2, n + height + 1, n2 + this.arrow.width, n + 1);
            this.graphics.drawLine(n2, n + height - 1, n2 + this.arrow.width, n - 1);
            left = n2 + (this.arrow.space + this.arrow.width);
        }
    }
    
    public void run() {
        while (this.buttonPressing && (this.scroll.firstVisibleItem > 0 || this.scroll.scrollItems - this.scroll.firstVisibleItem > 0)) {
            if (this.scrollTypeUpDown) {
                this.scrollUp();
            }
            else {
                this.scrollDown();
            }
            this.sleep(260);
        }
    }
    
    public void init() {
        this.inited = false;
        this.createImage();
        this.paintMenu();
        this.inited = true;
    }
    
    private void internalHighlite(final MenuItem menuItem, final Graphics graphics, final Color color, final Color color2) {
        graphics.setColor(color);
        graphics.fillRect(menuItem.Area.x, menuItem.Area.y, menuItem.Area.width, menuItem.Area.height);
        graphics.setColor(color2);
        graphics.drawString(menuItem.Display, menuItem.Location.x, menuItem.Location.y);
    }
    
    public Point getAnchorPoint() {
        return this.anchorPoint;
    }
    
    private void paintItems() {
        this.graphics.setColor(this.backColor);
        this.graphics.fillRect(this.hMarg3d + 1, this.vMarg3d + 1, this.size.width - (2 * this.hMarg3d + 2), this.size.height - (2 * this.vMarg3d + 2));
        if (this.scroll.scroll && (!this.scroll.arrowBlink || this.scroll.arrowUpVisible)) {
            this.paintArrowUp((this.scroll.firstVisibleItem == 0) ? this.foreColor : this.highliteForeColor);
        }
        this.internalPaintItems(this.graphics);
        if (this.scroll.scroll && (!this.scroll.arrowBlink || this.scroll.arrowDownVisible)) {
            this.paintArrowDown((this.scroll.firstVisibleItem + this.scroll.scrollItems == this.items.size()) ? this.foreColor : this.highliteForeColor);
        }
    }
}
