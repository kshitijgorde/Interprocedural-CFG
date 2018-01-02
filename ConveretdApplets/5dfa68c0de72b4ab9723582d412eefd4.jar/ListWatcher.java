import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

class ListWatcher implements Drawable
{
    public static final Font LABEL_FONT;
    public static final Font LABEL_FONT_SMALL;
    public static final Font CELL_NUM_FONT;
    public static final int TOP_MARGIN = 23;
    public static final int BOTTOM_MARGIN = 20;
    public static final int LEFT_MARGIN = 5;
    public static final int RIGHT_MARGIN = 5;
    LContext lc;
    PlayerCanvas canvas;
    StretchyBox box;
    ListWatcherPane pane;
    ListWatcherScrollBar scrollBar;
    String listTitle;
    Object[] list;
    ArrayList highlightedIndices;
    int mouseOffset;
    int paneHeight;
    int scroll;
    int scrollForIndex;
    int scrollBarHeight;
    float scrollRatio;
    boolean isShowing;
    boolean useScrollForIndex;
    
    ListWatcher(final LContext lc) {
        this.listTitle = "listTitle";
        this.list = null;
        this.highlightedIndices = new ArrayList();
        this.mouseOffset = 0;
        this.paneHeight = 0;
        this.scroll = 0;
        this.scrollForIndex = 0;
        this.scrollBarHeight = 0;
        this.scrollRatio = 0.0f;
        this.isShowing = true;
        this.useScrollForIndex = false;
        if (lc != null) {
            this.canvas = lc.canvas;
            this.lc = lc;
        }
        (this.box = new StretchyBox()).setFrameImage(Skin.listWatcherOuterFrame);
        this.pane = new ListWatcherPane(this);
    }
    
    public void setList(final Object[] list) {
        this.list = list;
        this.pane.setList(this.list);
        this.paneHeight = this.pane.totalHeight;
        this.initScrollBar();
    }
    
    public void initScrollBar() {
        this.scrollBarHeight = this.box.h - 23 - 20;
        this.scrollRatio = this.paneHeight / this.scrollBarHeight;
        if (this.scrollRatio < 1.0) {
            this.scrollRatio = 0.0f;
            this.scrollBar = null;
            return;
        }
        if (this.scrollBar == null) {
            this.scrollBar = new ListWatcherScrollBar();
        }
        this.scrollBar.nubBox.h = (int)(this.scrollBarHeight / this.scrollRatio);
        if (this.scrollBar.nubBox.h < 23) {
            this.scrollBar.nubBox.h = 23;
            this.scrollRatio = (float)(this.paneHeight / (this.scrollBarHeight - 23.0));
        }
    }
    
    void show() {
        this.isShowing = true;
        this.inval();
    }
    
    void hide() {
        this.isShowing = false;
        this.inval();
    }
    
    public boolean isShowing() {
        return this.isShowing;
    }
    
    public Rectangle rect() {
        return new Rectangle(this.box.x, this.box.y, this.box.w, this.box.h);
    }
    
    public Rectangle fullRect() {
        return this.rect();
    }
    
    void inval() {
        this.canvas.inval(this.rect());
    }
    
    public void paintBubble(final Graphics graphics) {
    }
    
    public void paint(final Graphics graphics) {
        this.box.paint(graphics);
        graphics.setColor(Color.BLACK);
        graphics.setFont(ListWatcher.LABEL_FONT);
        graphics.drawString(this.listTitle, this.box.x + (this.box.w - WatcherReadout.stringWidth(this.listTitle, ListWatcher.LABEL_FONT, graphics)) / 2, this.box.y + 23 - 8);
        if (this.scrollBar != null) {
            this.scrollBar.paint(graphics, this.box.x + this.box.w, this.box.y + 23, this.scrollBarHeight, this.box.y + 23 + this.scroll);
        }
        this.pane.paint(graphics.create(), this.box.x + 5, this.useScrollForIndex ? this.scrollForIndex : ((int)(this.scroll * this.scrollRatio)), this.box.y + 23, this.scrollBarHeight);
        graphics.setColor(Color.BLACK);
        graphics.setFont(ListWatcher.LABEL_FONT_SMALL);
        final String string = "length: " + this.list.length;
        graphics.drawString(string, this.box.x + (this.box.w - WatcherReadout.stringWidth(string, ListWatcher.LABEL_FONT_SMALL, graphics)) / 2, this.box.y + this.box.h - 5);
    }
    
    boolean inScrollbar(final int n, final int n2) {
        return this.scrollBar != null && n >= this.box.x + this.box.w - 20 && n <= this.box.x + this.box.w && n2 >= this.box.y + 23 + this.scroll && n2 <= this.box.y + 23 + this.scroll + this.scrollBar.nubBox.h;
    }
    
    public void mouseDown(final int n, final int n2) {
        if (this.scrollBar != null) {
            this.mouseOffset = n2 - (this.box.y + 23) - this.scroll;
        }
    }
    
    public void dragTo(final int n, final int n2) {
        if (this.scrollBar != null) {
            this.setScroll(n2 - this.mouseOffset - (this.box.y + 23));
        }
    }
    
    void setScroll(final int scroll) {
        if (this.scrollBar != null) {
            if (scroll < 0) {
                this.scroll = 0;
            }
            else if (scroll > this.scrollBarHeight - this.scrollBar.nubBox.h) {
                this.scroll = this.scrollBarHeight - this.scrollBar.nubBox.h;
            }
            else {
                this.scroll = scroll;
            }
            this.useScrollForIndex = false;
            this.inval();
        }
    }
    
    void setScrollForHighlightIndex(final int n) {
        if (this.scrollBar != null) {
            this.scrollForIndex = this.pane.getYPositionAtIndex(n) - this.scrollBarHeight / 2;
            this.useScrollForIndex = true;
            final int scroll = (int)(this.scrollForIndex / this.scrollRatio);
            if (scroll < 0) {
                this.scroll = 0;
            }
            else if (scroll > this.scrollBarHeight - this.scrollBar.nubBox.h) {
                this.scroll = this.scrollBarHeight - this.scrollBar.nubBox.h;
            }
            else {
                this.scroll = scroll;
            }
            this.inval();
        }
    }
    
    void highlightIndex(final int scrollForHighlightIndex) {
        if (scrollForHighlightIndex < 1 || scrollForHighlightIndex > this.list.length) {
            this.box.setFrameImage(Skin.listWatcherOuterFrameError);
        }
        else {
            this.setScrollForHighlightIndex(scrollForHighlightIndex);
            this.highlightedIndices.add(new Integer(scrollForHighlightIndex));
            this.box.setFrameImage(Skin.listWatcherOuterFrame);
        }
    }
    
    void clearHighlights() {
        this.highlightedIndices = new ArrayList();
        this.box.setFrameImage(Skin.listWatcherOuterFrame);
    }
    
    static {
        LABEL_FONT = new Font("Arial Unicode MS", 1, 10);
        LABEL_FONT_SMALL = new Font("Arial Unicode MS", 0, 10);
        CELL_NUM_FONT = new Font("Arial Unicode MS", 0, 9);
    }
}
