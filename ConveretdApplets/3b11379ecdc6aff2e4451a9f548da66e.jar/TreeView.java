import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.applet.Applet;
import java.net.URL;
import java.awt.Color;
import java.awt.Image;
import java.awt.Event;
import java.awt.Scrollbar;
import java.awt.Dimension;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class TreeView extends Panel
{
    HHCtrl m_applet;
    ElementList m_list;
    int m_itemHeight;
    int m_scrollPos;
    int m_hscrollPos;
    int m_style;
    boolean m_fRedraw;
    int m_autoExpandLevel;
    Dimension m_Size;
    private int m_autoKey;
    private long m_winStyle;
    private long m_exWinStyle;
    static int STYLE_NORMAL;
    static int STYLE_WIN95;
    static int STYLE_EXPLORER;
    TreeCanvas m_canvas;
    Scrollbar m_vScroll;
    Scrollbar m_hScroll;
    String m_targetFrame;
    
    public boolean gotFocus(final Event event, final Object o) {
        this.m_canvas.m_hasFocus = true;
        this.m_canvas.m_oldTop = this.m_canvas.m_top;
        this.m_canvas.repaint();
        return true;
    }
    
    public boolean lostFocus(final Event event, final Object o) {
        this.m_canvas.m_hasFocus = false;
        this.m_canvas.m_oldTop = this.m_canvas.m_top;
        this.m_canvas.repaint();
        return true;
    }
    
    public void setStyles(final long winStyle, final long exWinStyle) {
        this.m_winStyle = winStyle;
        this.m_exWinStyle = exWinStyle;
        this.m_canvas.setStyles(winStyle, exWinStyle);
    }
    
    public void setBackgroundImage(final Image backgroundImage) {
        this.m_canvas.setBackgroundImage(backgroundImage);
    }
    
    public void sync(final String s, final int n) {
        int selectedItemA = -1;
        if (n == 0) {
            selectedItemA = this.m_list.showURL(s);
        }
        else if (n == 1) {
            selectedItemA = this.m_list.showTitle(s);
        }
        if (selectedItemA == -1) {
            return;
        }
        this.m_canvas.setSelectedItemA(selectedItemA);
        if (this.m_scrollPos + this.m_canvas.size().height / this.m_itemHeight <= this.m_canvas.m_selectedItem) {
            this.setTop(this.m_canvas.m_selectedItem - this.m_canvas.size().height / this.m_itemHeight + 1);
        }
        if (this.m_scrollPos > this.m_canvas.m_selectedItem) {
            this.setTop(this.m_canvas.m_selectedItem);
        }
        this.m_list.calcNumVisible();
        this.m_list.nthVisible(0);
        this.m_canvas.m_oldTop = this.m_canvas.m_top;
        this.doValidate();
        this.m_canvas.m_state = TreeCanvas.STATE_SYNC;
        this.m_canvas.repaint();
    }
    
    public void setRedrawMode(final int redrawMode) {
        this.m_canvas.setRedrawMode(redrawMode);
    }
    
    private void moveEnd() {
        this.m_canvas.setSelectedItem2(this.m_list.numVisible() - 1);
        if (this.m_scrollPos + this.m_canvas.size().height / this.m_itemHeight <= this.m_canvas.m_selectedItem) {
            this.setTop(this.m_canvas.m_selectedItem - this.m_canvas.size().height / this.m_itemHeight + 1);
        }
        if (this.m_scrollPos > this.m_canvas.m_selectedItem) {
            this.setTop(this.m_canvas.m_selectedItem);
        }
        this.m_canvas.m_oldTop = this.m_canvas.m_top;
        this.doValidate();
    }
    
    public void setStyle(final int n) {
        if (n < 0 || n > 2) {
            return;
        }
        this.m_style = n;
        this.m_canvas.setStyle(n);
    }
    
    private void setList(final ElementList list) {
        list.calcNumVisible();
        this.m_list = list;
        this.m_canvas.setList(list);
        this.m_scrollPos = 0;
        this.m_vScroll.setValues(this.m_scrollPos, 1, 0, 10);
        this.m_vScroll.hide();
        this.m_hscrollPos = 0;
        this.m_canvas.setHScroll(this.m_hscrollPos);
        this.m_hScroll.setValues(this.m_hscrollPos, 1, 0, 10);
        this.m_hScroll.hide();
        this.validate();
        this.setVScroll();
        if (this.setHScroll()) {
            this.setVScroll();
        }
    }
    
    public void addElement(final Element element) {
        this.m_list.addElement(element);
    }
    
    private void moveParent() {
        if (this.m_canvas.m_selectedItem == 0) {
            return;
        }
        for (int i = this.m_canvas.m_selectedItem - 1; i >= -1; --i) {
            if (i >= 0 && ((Element)this.m_list.elementAt(this.m_list.nthVisible(i))).m_level < this.m_list.elementAt(this.m_list.nthVisible(this.m_canvas.m_selectedItem)).m_level) {
                this.m_canvas.setSelectedItem2(i);
                if (this.m_scrollPos + this.m_canvas.size().height / this.m_itemHeight <= this.m_canvas.m_selectedItem) {
                    this.setTop(this.m_canvas.m_selectedItem - this.m_canvas.size().height / this.m_itemHeight + 1);
                }
                if (this.m_scrollPos > this.m_canvas.m_selectedItem) {
                    this.setTop(this.m_canvas.m_selectedItem);
                }
                this.m_canvas.m_oldTop = this.m_canvas.m_top;
                this.doValidate();
                return;
            }
        }
    }
    
    private boolean showChildren() {
        final int nthVisible = this.m_list.nthVisible(this.m_canvas.m_selectedItem);
        if (!this.m_list.hasChildren(nthVisible)) {
            return false;
        }
        if (!this.m_list.hasVisibleChildren(nthVisible)) {
            this.m_list.showChildren(nthVisible);
            final boolean b = !this.setVScroll();
            if (this.setHScroll()) {
                final boolean b2 = !this.setVScroll();
            }
            if (this.m_scrollPos + this.m_canvas.size().height / this.m_itemHeight <= this.m_canvas.m_selectedItem) {
                this.setTop(this.m_canvas.m_selectedItem - this.m_canvas.size().height / this.m_itemHeight + 1);
            }
            if (this.m_scrollPos > this.m_canvas.m_selectedItem) {
                this.setTop(this.m_canvas.m_selectedItem);
            }
            this.doValidate();
            return true;
        }
        return false;
    }
    
    public void scrollUp(final int n) {
        if (this.m_vScroll.isShowing() && this.m_scrollPos >= n) {
            this.m_scrollPos -= n;
            this.m_vScroll.setValue(this.m_scrollPos);
            this.m_canvas.setTop(this.m_scrollPos);
        }
    }
    
    private void moveNextPageVisible() {
        if (this.m_canvas.m_selectedItem >= this.m_list.numVisible() - 1) {
            return;
        }
        final int n = this.m_canvas.size().height / this.m_itemHeight - 1;
        if (this.m_canvas.m_selectedItem + n >= this.m_list.numVisible() - 1) {
            this.m_canvas.setSelectedItem2(this.m_list.numVisible() - 1);
        }
        else {
            this.m_canvas.setSelectedItem2(this.m_canvas.m_selectedItem + n);
        }
        if (this.m_scrollPos + this.m_canvas.size().height / this.m_itemHeight <= this.m_canvas.m_selectedItem) {
            this.setTop(this.m_canvas.m_selectedItem - this.m_canvas.size().height / this.m_itemHeight + 1);
        }
        if (this.m_scrollPos > this.m_canvas.m_selectedItem) {
            this.setTop(this.m_canvas.m_selectedItem);
        }
        this.m_canvas.m_oldTop = this.m_canvas.m_top;
        this.doValidate();
    }
    
    private void movePrevVisible() {
        if (this.m_canvas.m_selectedItem == 0) {
            return;
        }
        this.m_canvas.setSelectedItem2(this.m_canvas.m_selectedItem - 1);
        if (this.m_scrollPos + this.m_canvas.size().height / this.m_itemHeight <= this.m_canvas.m_selectedItem) {
            this.setTop(this.m_canvas.m_selectedItem - this.m_canvas.size().height / this.m_itemHeight + 1);
        }
        if (this.m_scrollPos > this.m_canvas.m_selectedItem) {
            this.setTop(this.m_canvas.m_selectedItem);
        }
        this.m_canvas.m_oldTop = this.m_canvas.m_top;
        this.doValidate();
    }
    
    public void scrollLeft(final int n) {
        if (this.m_hScroll.isShowing() && this.m_hScroll.getValue() >= n) {
            this.m_hScroll.setValue(this.m_hScroll.getValue() - n);
            this.m_hscrollPos -= n * 10;
            this.m_canvas.setHScroll(this.m_hscrollPos);
            this.m_canvas.repaint();
        }
    }
    
    public Dimension preferredSize() {
        return this.m_Size;
    }
    
    public void setSize(final Dimension size) {
        this.m_Size = size;
    }
    
    public void scrollRight(final int n) {
        if (this.m_hScroll.getValue() + n <= this.m_hScroll.getMaximum() && this.m_hScroll.isShowing()) {
            this.m_hscrollPos += n * 10;
            this.m_hScroll.setValue(this.m_hScroll.getValue() + n);
            this.m_canvas.setHScroll(this.m_hscrollPos);
            this.m_canvas.repaint();
        }
    }
    
    private void showElementName() {
        final Element element = this.m_list.elementAt(this.m_canvas.m_selectedItemA);
        if (element.m_text.compareTo("") != 0) {
            if (element.m_url.compareTo("") != 0) {
                this.m_applet.showStatus(element.m_text + " (" + element.m_url + ")");
                return;
            }
            this.m_applet.showStatus(element.m_text);
        }
    }
    
    public void setItemHeight(final int n) {
        this.m_itemHeight = n;
        this.m_canvas.setItemHeight(n);
    }
    
    public void setClickMode(final int clickMode) {
        this.m_canvas.setClickMode(clickMode);
    }
    
    public void setAutoExpandLevel(final int autoExpandLevel) {
        if (autoExpandLevel > 0) {
            this.m_autoExpandLevel = autoExpandLevel;
        }
    }
    
    public void setRedraw(final boolean b) {
        this.m_fRedraw = b;
        this.m_canvas.m_fRedraw = b;
    }
    
    public boolean keyDown(final Event event, final int n) {
        switch (n) {
            case 1005: {
                if ((event.modifiers & 0x2) > 0) {
                    this.scrollDown(1);
                }
                else {
                    this.moveNextVisible();
                    this.showElementName();
                }
                return true;
            }
            case 1003: {
                this.moveNextPageVisible();
                this.showElementName();
                return true;
            }
            case 1004: {
                if ((event.modifiers & 0x2) > 0) {
                    this.scrollUp(1);
                }
                else {
                    this.movePrevVisible();
                    this.showElementName();
                }
                return true;
            }
            case 1002: {
                this.movePrevPageVisible();
                this.showElementName();
                return true;
            }
            case 1007: {
                if ((event.modifiers & 0x2) > 0) {
                    this.scrollRight(1);
                }
                else {
                    if (!this.showChildren()) {
                        this.moveNextVisible();
                    }
                    this.showElementName();
                }
                return true;
            }
            case 1006: {
                if ((event.modifiers & 0x2) > 0) {
                    this.scrollLeft(1);
                }
                else {
                    if (!this.hideChildren()) {
                        this.movePrevVisible();
                    }
                    this.showElementName();
                }
                return true;
            }
            case 45: {
                this.hideChildren();
                this.showElementName();
                return true;
            }
            case 43: {
                this.showChildren();
                this.showElementName();
                return true;
            }
            case 1000: {
                this.moveHome();
                this.showElementName();
                return true;
            }
            case 1001: {
                this.moveEnd();
                this.showElementName();
                return true;
            }
            case 10: {
                this.postEvent(new Event(this, 1001, this.m_list.elementAt(this.m_canvas.m_selectedItemA)));
                return true;
            }
            case 8: {
                this.moveParent();
                this.showElementName();
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public void scrollDown(final int n) {
        if (this.m_vScroll.isShowing() && this.m_vScroll.getValue() + n <= this.m_vScroll.getMaximum() && this.m_vScroll.isShowing()) {
            this.m_scrollPos += n;
            this.m_vScroll.setValue(this.m_scrollPos);
            this.m_canvas.setTop(this.m_scrollPos);
        }
    }
    
    public void setBackground(final Color background) {
        this.m_canvas.setBackground(background);
    }
    
    public boolean loadFromHHC(final URL url) {
        final ElementList list = new ElementList(this.m_applet);
        list.addElement(new Element(this.m_applet.getString("cnt.load.elementname"), 0, this.m_applet));
        this.setList(list);
        final ElementList list2 = new ElementList(this.m_applet);
        list2.setAutoExpandLevel(this.m_autoExpandLevel);
        final SitemapParser sitemapParser = new SitemapParser(url, list2, this.m_applet);
        if (sitemapParser.success()) {
            this.m_targetFrame = sitemapParser.getFrame();
            this.m_canvas.setImageList(sitemapParser.getImageList(), sitemapParser.getImageWidth(), sitemapParser.useFolders());
            if ((sitemapParser.getStyleSet() & 0x2) > 0) {
                this.m_canvas.setExWinStyle(sitemapParser.getExWinStyle());
            }
            if ((sitemapParser.getStyleSet() & 0x1) > 0) {
                this.m_applet.setWinStyle(sitemapParser.getWinStyle());
            }
            this.setList(list2);
            this.m_applet.showStatus(this.m_applet.getString("cnt.load.success"));
            return true;
        }
        return false;
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void doValidate() {
        this.m_Size.width = this.size().width;
        this.m_Size.height = this.size().height;
        this.validate();
        this.setVScroll();
        if (this.setHScroll()) {
            this.setVScroll();
        }
        this.m_canvas.repaint();
    }
    
    private void moveNextVisible() {
        if (this.m_canvas.m_selectedItem >= this.m_list.numVisible() - 1) {
            return;
        }
        this.m_canvas.setSelectedItem2(this.m_canvas.m_selectedItem + 1);
        if (this.m_scrollPos + this.m_canvas.size().height / this.m_itemHeight <= this.m_canvas.m_selectedItem) {
            this.setTop(this.m_canvas.m_selectedItem - this.m_canvas.size().height / this.m_itemHeight + 1);
        }
        if (this.m_scrollPos > this.m_canvas.m_selectedItem) {
            this.setTop(this.m_canvas.m_selectedItem);
        }
        this.m_canvas.m_oldTop = this.m_canvas.m_top;
        this.doValidate();
    }
    
    public boolean setVScroll() {
        boolean b = false;
        if (this.m_list.numVisible() > this.m_canvas.size().height / this.m_itemHeight) {
            if (!this.m_vScroll.isShowing()) {
                b = true;
                this.m_vScroll.show();
                this.validate();
            }
            this.m_vScroll.setValues(this.m_scrollPos, this.m_canvas.size().height / this.m_itemHeight, 0, this.m_list.numVisible() - this.m_canvas.size().height / this.m_itemHeight);
            this.m_canvas.syncSelectedItem();
        }
        else {
            if (this.m_vScroll.isShowing()) {
                b = true;
            }
            this.m_scrollPos = 0;
            this.m_vScroll.hide();
            this.validate();
            this.m_canvas.setTop(this.m_scrollPos);
        }
        return b;
    }
    
    public boolean setHScroll() {
        boolean b = false;
        final int maxWidth = this.m_canvas.getMaxWidth();
        if (maxWidth > this.size().width) {
            if (!this.m_hScroll.isShowing()) {
                b = true;
                this.m_hScroll.show();
                this.validate();
            }
            this.m_hScroll.setValues(this.m_hscrollPos, this.size().width / 10, 0, (maxWidth - this.size().width + 30) / 10);
        }
        else if (this.m_hScroll.isShowing()) {
            b = true;
            this.m_hscrollPos = 0;
            this.m_canvas.setHScroll(this.m_hscrollPos);
            this.m_hScroll.hide();
            this.validate();
        }
        return b;
    }
    
    public void setTop(final int scrollPos) {
        this.m_scrollPos = scrollPos;
        this.m_canvas.setTop(scrollPos, false);
        this.m_list.nthVisible(0);
    }
    
    public String getFrame() {
        return this.m_targetFrame;
    }
    
    public void setFont(final Font font, final Color color) {
        this.m_canvas.setFont(font, color);
    }
    
    public void addControls() {
        this.setLayout(new BorderLayout());
        (this.m_vScroll = new Scrollbar(1)).setValues(0, 1, 0, 10);
        (this.m_hScroll = new Scrollbar(0)).setValues(0, 1, 0, 10);
        (this.m_canvas = new TreeCanvas(this.m_list, this.m_applet, this)).setItemHeight(this.m_itemHeight);
        this.add("Center", this.m_canvas);
        this.add("East", this.m_vScroll);
        this.add("South", this.m_hScroll);
        this.m_vScroll.hide();
        this.m_hScroll.hide();
    }
    
    static {
        TreeView.STYLE_WIN95 = 1;
        TreeView.STYLE_EXPLORER = 2;
    }
    
    public void moveNext() {
        if (this.m_canvas.m_selectedItemA == this.m_list.size() - 1) {
            return;
        }
        int selectedItemA;
        if (this.m_list.showNext(this.m_canvas.m_selectedItemA).m_url.compareTo("") == 0) {
            this.m_list.showNext(this.m_canvas.m_selectedItemA + 1);
            selectedItemA = this.m_canvas.m_selectedItemA + 2;
        }
        else {
            selectedItemA = this.m_canvas.m_selectedItemA + 1;
        }
        this.m_canvas.setSelectedItemA(selectedItemA);
        if (this.m_scrollPos + this.m_canvas.size().height / this.m_itemHeight <= this.m_canvas.m_selectedItem) {
            this.setTop(this.m_canvas.m_selectedItem - this.m_canvas.size().height / this.m_itemHeight + 1);
        }
        if (this.m_scrollPos > this.m_canvas.m_selectedItem) {
            this.setTop(this.m_canvas.m_selectedItem);
        }
        this.m_canvas.m_oldTop = this.m_canvas.m_top;
        this.doValidate();
        this.postEvent(new Event(this, 1001, this.m_list.elementAt(selectedItemA)));
    }
    
    private void moveHome() {
        this.m_canvas.setSelectedItem2(0);
        if (this.m_scrollPos + this.m_canvas.size().height / this.m_itemHeight <= this.m_canvas.m_selectedItem) {
            this.setTop(this.m_canvas.m_selectedItem - this.m_canvas.size().height / this.m_itemHeight + 1);
        }
        if (this.m_scrollPos > this.m_canvas.m_selectedItem) {
            this.setTop(this.m_canvas.m_selectedItem);
        }
        this.m_canvas.m_oldTop = this.m_canvas.m_top;
        this.doValidate();
    }
    
    public void setSelection(final int selectedItem2) {
        this.m_canvas.setSelectedItem2(selectedItem2);
    }
    
    public void clear() {
        this.m_list.removeAllElements();
    }
    
    public void setColors(final Color color, final Color color2, final Color color3, final Color color4, final Color color5) {
        this.m_canvas.setColors(color, color2, color3, color4, color5);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this.m_vScroll) {
            switch (event.id) {
                case 601:
                case 602:
                case 603:
                case 604:
                case 605: {
                    this.m_scrollPos = this.m_vScroll.getValue();
                    this.m_canvas.setTop(this.m_scrollPos);
                    return true;
                }
            }
        }
        else if (event.target == this.m_hScroll) {
            switch (event.id) {
                case 601:
                case 602:
                case 603:
                case 604:
                case 605: {
                    this.m_hscrollPos = this.m_hScroll.getValue() * 10;
                    this.m_canvas.setHScroll(this.m_hscrollPos);
                    this.m_canvas.repaint();
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean hideChildren() {
        final int nthVisible = this.m_list.nthVisible(this.m_canvas.m_selectedItem);
        if (!this.m_list.hasChildren(nthVisible)) {
            return false;
        }
        if (this.m_list.hasVisibleChildren(nthVisible)) {
            this.m_list.hideChildren(nthVisible);
            final boolean b = !this.setVScroll();
            if (this.setHScroll()) {
                final boolean b2 = !this.setVScroll();
            }
            if (this.m_list.numVisible() - this.m_canvas.m_top < this.size().height / this.m_itemHeight && this.m_list.numVisible() > this.size().height / this.m_itemHeight) {
                this.m_canvas.m_top = this.m_list.numVisible() - this.size().height / this.m_itemHeight;
            }
            this.doValidate();
            return true;
        }
        return false;
    }
    
    public void movePrev() {
        if (this.m_canvas.m_selectedItemA == 0) {
            return;
        }
        final int size = this.m_list.size();
        final Element showPrev = this.m_list.showPrev(this.m_canvas.m_selectedItemA);
        this.m_canvas.syncSelectedItem();
        if (size < this.m_list.size()) {
            this.m_list.showPrev(this.m_canvas.m_selectedItemA);
        }
        int selectedItemA;
        if (showPrev.m_url.compareTo("") == 0 && this.m_canvas.m_selectedItemA > 1) {
            this.m_list.showPrev(this.m_list.indexOf(showPrev) - 1);
            this.m_canvas.syncSelectedItem();
            selectedItemA = this.m_canvas.m_selectedItemA - 2;
        }
        else {
            this.m_canvas.syncSelectedItem();
            selectedItemA = this.m_canvas.m_selectedItemA - 1;
        }
        this.m_canvas.setSelectedItemA(selectedItemA);
        if (this.m_scrollPos + this.m_canvas.size().height / this.m_itemHeight <= this.m_canvas.m_selectedItem) {
            this.setTop(this.m_canvas.m_selectedItem - this.m_canvas.size().height / this.m_itemHeight + 1);
        }
        if (this.m_scrollPos > this.m_canvas.m_selectedItem) {
            this.setTop(this.m_canvas.m_selectedItem);
        }
        this.m_list.calcNumVisible();
        this.m_list.nthVisible(0);
        this.m_canvas.m_oldTop = this.m_canvas.m_top;
        this.doValidate();
        this.postEvent(new Event(this, 1001, this.m_list.elementAt(selectedItemA)));
    }
    
    TreeView() {
        this((Applet)null);
    }
    
    TreeView(final Applet applet) {
        this.m_itemHeight = 16;
        this.m_fRedraw = true;
        this.m_autoExpandLevel = 2;
        this.m_winStyle = 512L;
        this.m_exWinStyle = 8388661L;
        this.m_targetFrame = "";
        this.m_Size = new Dimension(applet.size().width, applet.size().height);
        this.m_list = new ElementList(this.m_applet);
        this.m_applet = (HHCtrl)applet;
    }
    
    private void movePrevPageVisible() {
        if (this.m_canvas.m_selectedItem == 0) {
            return;
        }
        final int n = this.m_canvas.size().height / this.m_itemHeight - 1;
        if (this.m_canvas.m_selectedItem - n < 0) {
            this.m_canvas.setSelectedItem2(0);
        }
        else {
            this.m_canvas.setSelectedItem2(this.m_canvas.m_selectedItem - n);
        }
        if (this.m_scrollPos + this.m_canvas.size().height / this.m_itemHeight <= this.m_canvas.m_selectedItem) {
            this.setTop(this.m_canvas.m_selectedItem - this.m_canvas.size().height / this.m_itemHeight + 1);
        }
        if (this.m_scrollPos > this.m_canvas.m_selectedItem) {
            this.setTop(this.m_canvas.m_selectedItem);
        }
        this.m_canvas.m_oldTop = this.m_canvas.m_top;
        this.doValidate();
    }
}
