import java.awt.FontMetrics;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.applet.Applet;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class TreeCanvas extends Panel
{
    ElementList m_list;
    Color m_backColor;
    Color m_textColor;
    Color m_lineColor;
    Color m_squareColor;
    Color m_highlightText;
    Color m_highlightBack;
    Color m_highlightBackNoFocus;
    int m_itemHeight;
    int m_selectedItem;
    int m_selectedItemA;
    int m_oldSelection;
    long m_dblClkTimer;
    Image m_gBuff;
    int m_state;
    int m_top;
    int m_oldTop;
    Image offScrImg;
    Image m_bgImage;
    Image plusImg;
    Image minusImg;
    Image[] m_icons;
    Font m_font;
    int m_style;
    int m_redrawMode;
    int m_clickMode;
    int m_maxWidth;
    int m_hscrollPos;
    private String m_loadedImgList;
    private boolean m_locked;
    private int m_fPlatform;
    private int m_useFolders;
    private long m_winStyle;
    private long m_exWinStyle;
    private int m_imageWidth;
    boolean m_hasFocus;
    boolean m_fRedraw;
    Applet m_applet;
    TreeView m_tview;
    static int STATE_NONE;
    static int STATE_NEWSEL;
    static int STATE_UPDATING;
    static int STATE_SYNC;
    public static int TIME_DBLCLICK;
    private boolean fShowSplash;
    static int m_lastOver;
    private Image[] imgArray;
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final int lastOver = n2 / this.m_itemHeight + this.m_top;
        if (lastOver != TreeCanvas.m_lastOver) {
            if (lastOver < this.m_list.numVisible()) {
                TreeCanvas.m_lastOver = lastOver;
                final Element element = this.m_list.elementAt(this.m_list.nthVisible(lastOver));
                if (element.m_text.compareTo("") != 0) {
                    if (element.m_url.compareTo("") != 0) {
                        this.m_applet.showStatus(element.m_text + " (" + element.m_url + ")");
                    }
                    else {
                        this.m_applet.showStatus(element.m_text);
                    }
                }
            }
            else {
                this.m_applet.showStatus("");
            }
        }
        return true;
    }
    
    public void setSelectedItem2(final int selectedItem) {
        this.m_oldSelection = this.m_selectedItem;
        this.m_selectedItem = selectedItem;
        this.m_selectedItemA = this.m_list.nthVisible(this.m_selectedItem);
    }
    
    public void setStyles(final long winStyle, final long exWinStyle) {
        this.m_winStyle = winStyle;
        this.m_exWinStyle = exWinStyle;
        if ((exWinStyle & 0x1L) == 0x0L && (exWinStyle & 0x4L) == 0x0L) {
            this.m_style = TreeView.STYLE_NORMAL;
            this.m_clickMode = 1;
        }
        else if ((exWinStyle & 0x4L) == 0x0L) {
            this.m_style = TreeView.STYLE_EXPLORER;
            this.m_clickMode = 0;
        }
        else {
            this.m_style = TreeView.STYLE_WIN95;
            this.m_clickMode = 0;
        }
        this.repaint();
    }
    
    public void setBackgroundImage(final Image bgImage) {
        this.m_bgImage = bgImage;
    }
    
    public int getMaxWidth() {
        this.m_maxWidth = 0;
        if (this.offScrImg == null) {
            this.offScrImg = this.createImage(this.size().width + 20, this.size().height);
        }
        for (int i = 0; i < this.m_list.size(); ++i) {
            final Element element = this.m_list.elementAt(i);
            if (element.isVisible() && element.m_textWidth > this.m_maxWidth) {
                this.m_maxWidth = element.m_textWidth;
            }
        }
        return this.m_maxWidth;
    }
    
    public void setRedrawMode(final int redrawMode) {
        if (redrawMode < 1 || redrawMode > 2) {
            return;
        }
        this.m_redrawMode = redrawMode;
    }
    
    public int calcMaxWidth() {
        final Font font = this.m_font;
        this.m_maxWidth = 0;
        if (this.offScrImg == null) {
            this.offScrImg = this.createImage(this.size().width + 20, Math.max(this.size().height, 10));
        }
        final Graphics graphics = this.offScrImg.getGraphics();
        for (int i = 0; i < this.m_list.size(); ++i) {
            final Element element = this.m_list.elementAt(i);
            int level = element.m_level;
            if (this.m_style == TreeView.STYLE_WIN95) {
                ++level;
            }
            int textWidth = element.m_textWidth;
            if (textWidth == 0) {
                graphics.setFont(font);
                textWidth = graphics.getFontMetrics().stringWidth(element.m_text) + level * this.m_itemHeight + 35;
                element.m_textWidth = textWidth;
            }
            if (textWidth > this.m_maxWidth) {
                this.m_maxWidth = textWidth;
            }
        }
        graphics.dispose();
        return this.m_maxWidth;
    }
    
    public void setSelectedItem(final int n) {
        this.m_oldSelection = this.m_selectedItem;
        this.m_selectedItem = n / this.m_itemHeight + this.m_top;
        this.m_selectedItemA = this.m_list.nthVisible(this.m_selectedItem);
    }
    
    public void setStyle(final int style) {
        if (style < 0 || style > 2) {
            return;
        }
        this.m_style = style;
    }
    
    public boolean mouseDblClk(final Event event, final int n, final int n2, final boolean b) {
        boolean b2 = false;
        final int nthVisible = this.m_list.nthVisible(n2 / this.m_itemHeight + this.m_top);
        if ((this.m_clickMode != 1 || !b || this.m_fPlatform != 0) && this.m_list.hasChildren(nthVisible)) {
            if (this.m_list.hasVisibleChildren(nthVisible)) {
                this.m_list.hideChildren(nthVisible);
            }
            else {
                this.m_list.showChildren(nthVisible);
                b2 = true;
            }
            this.m_maxWidth = 0;
        }
        this.m_state = TreeCanvas.STATE_NONE;
        if (b) {
            this.getParent().postEvent(new Event(this, 1001, this.m_list.elementAt(this.m_list.nthVisible(n2 / this.m_itemHeight + this.m_top))));
        }
        final boolean b3 = !this.m_tview.setVScroll();
        if (this.m_tview.setHScroll()) {
            final boolean b4 = !this.m_tview.setVScroll();
        }
        if (this.m_list.numVisible() - this.m_top < this.size().height / this.m_itemHeight && this.m_list.numVisible() > this.size().height / this.m_itemHeight) {
            final int top = this.m_top;
            this.m_top = this.m_list.numVisible() - this.size().height / this.m_itemHeight;
            final TreeView tview = this.m_tview;
            tview.m_scrollPos += this.m_top - top;
            this.m_oldTop = this.m_top;
        }
        if (b2) {
            this.m_list.countChildren(nthVisible);
            if (n2 / this.m_itemHeight + this.m_list.countChildren(nthVisible) + 1 > this.size().height / this.m_itemHeight) {
                int n3 = this.size().height / this.m_itemHeight - this.m_list.countChildren(nthVisible) - n2 / this.m_itemHeight - 1;
                if (Math.abs(n3) > n2 / this.m_itemHeight) {
                    n3 = -n2 / this.m_itemHeight;
                }
                this.m_tview.scrollUp(n3);
                this.m_oldTop = this.m_top;
            }
        }
        return true;
    }
    
    public void setList(final ElementList list) {
        this.m_list = list;
        if (list.size() > 1) {
            this.fShowSplash = false;
        }
        this.calcMaxWidth();
        this.m_selectedItem = 0;
        this.m_selectedItemA = 0;
        this.m_oldSelection = 0;
        this.m_top = 0;
        if (list.size() == 1) {
            this.setImageList(null, 16, false);
        }
    }
    
    public void update(final Graphics graphics) {
        boolean b = true;
        if (!this.m_fRedraw) {
            return;
        }
        if (this.offScrImg == null) {
            this.offScrImg = this.createImage(this.size().width + 20, this.size().height);
            b = false;
        }
        final Graphics graphics2 = this.offScrImg.getGraphics();
        if (this.m_state == TreeCanvas.STATE_SYNC) {
            b = false;
            this.m_state = TreeCanvas.STATE_NONE;
        }
        if (this.m_state == TreeCanvas.STATE_NEWSEL) {
            if (this.m_oldSelection != this.m_selectedItem) {
                graphics.clipRect(0, (Math.min(this.m_oldSelection, this.m_selectedItem) - this.m_top) * this.m_itemHeight, this.size().width, (Math.abs(this.m_oldSelection - this.m_selectedItem) + 1) * this.m_itemHeight);
            }
            else {
                graphics.clipRect(0, 0, 0, 0);
            }
            this.imgPaint(graphics2, b);
            this.m_state = TreeCanvas.STATE_NONE;
        }
        else {
            this.imgPaint(graphics2, b);
        }
        graphics.drawImage(this.offScrImg, 0, 0, this);
        graphics2.dispose();
    }
    
    private void drawDashedVertLine(final Graphics graphics, int n, final int n2, final int n3, final boolean b) {
        if (n % 2 != 0 && b) {
            --n;
        }
        for (int i = 0; i <= n3 - n2; ++i) {
            if ((n2 + i) % 2 == 0) {
                graphics.drawLine(n, n2 + i, n, n2 + i);
            }
        }
    }
    
    public void setItemHeight(final int itemHeight) {
        this.m_itemHeight = itemHeight;
    }
    
    public void setImageList(String loadedImgList, int imageWidth, final boolean b) {
        this.m_imageWidth = imageWidth;
        if (loadedImgList == null) {
            loadedImgList = "cntimage.gif";
        }
        if (b) {
            this.m_useFolders = 4;
        }
        if (this.m_loadedImgList != null && loadedImgList.equalsIgnoreCase(this.m_loadedImgList)) {
            return;
        }
        try {
            Image image = this.getImageStrip(loadedImgList, true);
            if (image == null) {
                image = this.getImageStrip(loadedImgList, false);
            }
            if (image == null) {
                final int n = 24;
                imageWidth = (this.m_imageWidth = 16);
                this.imgArray = new Image[n];
                this.m_applet.createImage(480, imageWidth);
                for (int i = 0; i < n; ++i) {
                    this.imgArray[i] = this.m_applet.createImage(imageWidth, 16);
                    final Graphics graphics = this.imgArray[i].getGraphics();
                    graphics.setColor(this.m_backColor);
                    graphics.fillRect(0, 0, imageWidth, 16);
                    graphics.setColor(Color.black);
                    graphics.drawRect(0, 0, 15, 15);
                    graphics.dispose();
                }
                return;
            }
            image.getHeight(this.m_applet);
            final int n2 = image.getWidth(this.m_applet) / imageWidth;
            this.imgArray = new Image[n2];
            for (int j = 0; j < n2; ++j) {
                this.imgArray[j] = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(j * imageWidth, 0, imageWidth, 16)));
            }
            this.m_loadedImgList = loadedImgList;
        }
        catch (Exception ex) {}
    }
    
    public synchronized void clearLock() {
        this.m_locked = false;
    }
    
    public void setClickMode(final int clickMode) {
        if (clickMode < 0 || clickMode > 1) {
            return;
        }
        this.m_clickMode = clickMode;
    }
    
    public boolean setSelectedItemA(final int selectedItemA) {
        this.m_oldSelection = this.m_selectedItem;
        if (this.m_list.elementAt(selectedItemA).isVisible()) {
            this.m_selectedItemA = selectedItemA;
            this.m_selectedItem = this.m_list.whichVisible(selectedItemA) - 1;
            return true;
        }
        return false;
    }
    
    public synchronized void setLock() {
        this.m_locked = true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.m_applet.showStatus("");
        return true;
    }
    
    public void setBackground(final Color backColor) {
        this.m_backColor = backColor;
        this.createPlusMinus();
    }
    
    public void syncSelectedItem() {
        this.m_selectedItem = this.m_list.whichVisible(this.m_selectedItemA) - 1;
    }
    
    public void paint(final Graphics graphics) {
        boolean b = true;
        if (!this.m_fRedraw) {
            return;
        }
        if (this.offScrImg == null || this.offScrImg.getWidth(this) < this.size().width || this.offScrImg.getHeight(this) < this.size().height) {
            this.offScrImg = this.createImage(this.size().width + 20, this.size().height);
            b = false;
        }
        final Graphics graphics2 = this.offScrImg.getGraphics();
        if (this.m_state == TreeCanvas.STATE_SYNC) {
            b = false;
            this.m_state = TreeCanvas.STATE_NONE;
        }
        this.imgPaint(graphics2, b);
        graphics.drawImage(this.offScrImg, 0, 0, this);
        graphics2.dispose();
    }
    
    private void drawDashedHorzLine(final Graphics graphics, final int n, int n2, final int n3, final boolean b) {
        if (n2 % 2 != 0 && b) {
            --n2;
        }
        for (int i = 0; i <= n3 - n; ++i) {
            if ((n + i) % 2 == 0) {
                graphics.drawLine(n + i, n2, n + i, n2);
            }
        }
    }
    
    public void setHScroll(final int hscrollPos) {
        this.m_hscrollPos = hscrollPos;
    }
    
    public void setTop(final int n) {
        this.setTop(n, true);
    }
    
    private void createPlusMinus() {
        if (this.plusImg == null) {
            this.plusImg = this.createImage(9, 9);
        }
        if (this.minusImg == null) {
            this.minusImg = this.createImage(9, 9);
        }
        final Graphics graphics = this.plusImg.getGraphics();
        graphics.setColor(this.m_backColor);
        graphics.fillRect(0, 0, this.plusImg.getWidth(this), this.plusImg.getHeight(this));
        this.drawPlus(graphics, 0, 0);
        graphics.dispose();
        final Graphics graphics2 = this.minusImg.getGraphics();
        graphics2.setColor(this.m_backColor);
        graphics2.fillRect(0, 0, this.plusImg.getWidth(this), this.plusImg.getHeight(this));
        this.drawMinus(graphics2, 0, 0);
        graphics2.dispose();
    }
    
    private void drawPlus(final Graphics graphics, final int n, final int n2) {
        graphics.setColor(this.m_squareColor);
        graphics.drawRect(n, n2, 8, 8);
        graphics.setColor(this.m_lineColor);
        graphics.drawLine(n + 2, n2 + 4, n + 6, n2 + 4);
        graphics.drawLine(n + 4, n2 + 2, n + 4, n2 + 6);
    }
    
    private void drawMinus(final Graphics graphics, final int n, final int n2) {
        graphics.setColor(this.m_squareColor);
        graphics.drawRect(n, n2, 8, 8);
        graphics.setColor(this.m_lineColor);
        graphics.drawLine(n + 2, n2 + 4, n + 6, n2 + 4);
    }
    
    public void setTop(final int top, final boolean b) {
        this.m_top = top;
        if (b) {
            this.repaint();
        }
    }
    
    private Image getImageStrip(final String s, final boolean b) {
        try {
            final Image image = this.m_applet.getImage(b ? this.m_applet.getCodeBase() : this.m_applet.getDocumentBase(), s);
            this.m_applet.prepareImage(image, this.m_applet);
            final MediaTracker mediaTracker = new MediaTracker(this.m_applet);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
            if (mediaTracker.isErrorAny()) {
                return null;
            }
            return image;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public void setFont(final Font font, final Color textColor) {
        if (font != null && font instanceof Font) {
            this.m_font = font;
        }
        this.m_textColor = textColor;
    }
    
    static {
        TreeCanvas.STATE_NEWSEL = 1;
        TreeCanvas.STATE_UPDATING = 2;
        TreeCanvas.STATE_SYNC = 3;
        TreeCanvas.TIME_DBLCLICK = 350;
        TreeCanvas.m_lastOver = -1;
    }
    
    public void setExWinStyle(final long exWinStyle) {
        this.m_exWinStyle = exWinStyle;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        boolean b = true;
        if (n2 / this.m_itemHeight + this.m_top > this.m_list.numVisible() - 1) {
            return true;
        }
        final Element element = this.m_list.elementAt(this.m_list.nthVisible(n2 / this.m_itemHeight + this.m_top));
        if (this.m_clickMode == 1 && !this.m_locked) {
            if ((n > element.m_level * this.m_itemHeight - this.m_hscrollPos && n < (element.m_level + 1) * this.m_itemHeight - this.m_hscrollPos && this.m_style == TreeView.STYLE_WIN95) || (n > (element.m_level - 1) * this.m_itemHeight - this.m_hscrollPos && n < element.m_level * this.m_itemHeight - this.m_hscrollPos && this.m_style == TreeView.STYLE_EXPLORER) || event.clickCount == 2) {
                final int nthVisible = this.m_list.nthVisible(n2 / this.m_itemHeight + this.m_top);
                b = this.mouseDblClk(event, n, n2, false);
                if (!this.m_list.elementAt(this.m_selectedItemA).isVisible()) {
                    this.m_oldSelection = this.m_selectedItem;
                    this.m_selectedItemA = nthVisible;
                }
                this.syncSelectedItem();
            }
            else {
                this.m_state = TreeCanvas.STATE_NEWSEL;
                this.setSelectedItem(n2);
                b = this.mouseDblClk(event, n, n2, true);
            }
        }
        else if (!this.m_locked) {
            if (element.m_url.compareTo("") != 0) {
                this.m_applet.showStatus(element.m_url);
            }
            else {
                this.m_applet.showStatus(element.m_text);
            }
            if (((n > element.m_level * this.m_itemHeight - this.m_hscrollPos && n < (element.m_level + 1) * this.m_itemHeight - this.m_hscrollPos && this.m_style == TreeView.STYLE_WIN95) || (n > (element.m_level - 1) * this.m_itemHeight - this.m_hscrollPos && n < element.m_level * this.m_itemHeight - this.m_hscrollPos && this.m_style == TreeView.STYLE_EXPLORER)) && !this.m_locked) {
                final int nthVisible2 = this.m_list.nthVisible(n2 / this.m_itemHeight + this.m_top);
                b = this.mouseDblClk(event, n, n2, false);
                if (!this.m_list.elementAt(this.m_selectedItemA).isVisible()) {
                    this.m_oldSelection = this.m_selectedItem;
                    this.m_selectedItemA = nthVisible2;
                }
                this.syncSelectedItem();
            }
            else {
                this.m_state = TreeCanvas.STATE_NEWSEL;
                this.setSelectedItem(n2);
            }
        }
        if (b) {
            if (this.m_redrawMode == 1) {
                this.repaint();
            }
            else {
                this.repaint(TreeCanvas.TIME_DBLCLICK + 20);
            }
        }
        return true;
    }
    
    TreeCanvas() {
        this.m_backColor = Color.white;
        this.m_textColor = Color.black;
        this.m_lineColor = Color.black;
        this.m_squareColor = Color.gray;
        this.m_highlightText = Color.white;
        this.m_highlightBack = new Color(0, 0, 128);
        this.m_highlightBackNoFocus = Color.gray;
        this.m_itemHeight = 16;
        this.m_redrawMode = 1;
        this.m_imageWidth = 16;
        this.m_fRedraw = true;
    }
    
    TreeCanvas(final ElementList list, final Applet applet, final TreeView tview) {
        this.m_backColor = Color.white;
        this.m_textColor = Color.black;
        this.m_lineColor = Color.black;
        this.m_squareColor = Color.gray;
        this.m_highlightText = Color.white;
        this.m_highlightBack = new Color(0, 0, 128);
        this.m_highlightBackNoFocus = Color.gray;
        this.m_itemHeight = 16;
        this.m_redrawMode = 1;
        this.m_imageWidth = 16;
        this.m_fRedraw = true;
        this.m_list = list;
        this.m_applet = applet;
        this.m_tview = tview;
        this.m_icons = new Image[20];
        this.m_font = new Font("MS Sans Serif", 0, 12);
        this.m_fPlatform = System.getProperty("java.vendor").compareTo("Microsoft Corp.");
    }
    
    public void imgPaint(final Graphics graphics, final boolean b) {
        try {
            if (this.plusImg == null || this.minusImg == null) {
                this.createPlusMinus();
            }
            graphics.setPaintMode();
            int n = 0;
            int min = Math.min(this.size().height / this.m_itemHeight + 1, this.m_list.numVisible());
            if (!b || this.m_bgImage != null || this.m_oldTop == this.m_top) {
                graphics.setColor(this.m_backColor);
                graphics.fillRect(0, 0, this.size().width, this.size().height);
                if (this.m_bgImage != null) {
                    try {
                        if (this.m_bgImage.getWidth(this) != -1 && this.m_bgImage.getHeight(this) != -1) {
                            for (int i = 0; i < this.size().width; i += this.m_bgImage.getWidth(this)) {
                                graphics.drawImage(this.m_bgImage, i, 0, this.m_backColor, this);
                                for (int j = 0; j < this.size().height; j += this.m_bgImage.getHeight(this)) {
                                    graphics.drawImage(this.m_bgImage, i, j, this.m_backColor, this);
                                }
                            }
                        }
                    }
                    catch (Exception ex) {}
                }
            }
            else {
                graphics.copyArea(0, 0, this.size().width, this.size().height, 0, (this.m_oldTop - this.m_top) * this.m_itemHeight);
                graphics.setColor(this.m_backColor);
                if (this.m_top > this.m_oldTop) {
                    n = min - (this.m_top - this.m_oldTop) - 1;
                    graphics.fillRect(0, n * this.m_itemHeight, this.size().width, (min - n) * this.m_itemHeight);
                }
                else {
                    min = this.m_oldTop - this.m_top;
                    graphics.fillRect(0, 0, this.size().width, min * this.m_itemHeight);
                }
                this.m_oldTop = this.m_top;
            }
            graphics.setColor(this.m_lineColor);
            for (int k = n; k < min; ++k) {
                final int n2 = k + this.m_top;
                if (n2 >= this.m_list.numVisible()) {
                    break;
                }
                final int nthVisible = this.m_list.nthVisible(n2);
                final Element element = this.m_list.elementAt(nthVisible);
                if (element.isVisible()) {
                    int level = element.m_level;
                    if (this.m_style == TreeView.STYLE_WIN95) {
                        ++level;
                    }
                    graphics.setFont(this.m_font);
                    final FontMetrics fontMetrics = graphics.getFontMetrics();
                    int imgNum;
                    if (element.m_imgNum < 0) {
                        imgNum = 10;
                        if (this.m_list.hasChildren(nthVisible)) {
                            int n3 = 0;
                            if (this.m_list.hasVisibleChildren(nthVisible)) {
                                n3 = 1;
                            }
                            if (element.m_isNew == 1) {
                                n3 += 2;
                            }
                            imgNum = n3 + this.m_useFolders;
                        }
                        else if (element.m_isNew == 1) {
                            ++imgNum;
                        }
                    }
                    else {
                        imgNum = element.m_imgNum;
                    }
                    if (this.imgArray[imgNum] != null) {
                        graphics.drawImage(this.imgArray[imgNum], level * this.m_itemHeight - this.m_hscrollPos, k * this.m_itemHeight, this);
                    }
                    if (this.m_selectedItem == n2) {
                        if (this.m_hasFocus) {
                            graphics.setColor(this.m_highlightBack);
                        }
                        else {
                            graphics.setColor(this.m_highlightBackNoFocus);
                        }
                        final int n4 = level * this.m_itemHeight + this.m_imageWidth + 3 - this.m_hscrollPos;
                        final int n5 = k * this.m_itemHeight;
                        graphics.fillRect(n4 + 1, n5 + 1, fontMetrics.stringWidth(element.m_text) + 3, this.m_itemHeight - 2);
                        graphics.setColor(this.m_lineColor);
                        if (this.m_hasFocus) {
                            this.drawDashedVertLine(graphics, n4, n5, n5 + this.m_itemHeight - 1, false);
                            this.drawDashedVertLine(graphics, n4 + fontMetrics.stringWidth(element.m_text) + 4, n5, n5 + this.m_itemHeight - 1, false);
                            this.drawDashedHorzLine(graphics, n4, n5, n4 + fontMetrics.stringWidth(element.m_text) + 4, false);
                            this.drawDashedHorzLine(graphics, n4, n5 + this.m_itemHeight - 1, n4 + fontMetrics.stringWidth(element.m_text) + 4, false);
                        }
                        if (this.m_hasFocus) {
                            graphics.setColor(this.m_highlightText);
                        }
                        else {
                            graphics.setColor(this.m_textColor);
                        }
                    }
                    else {
                        graphics.setColor(this.m_textColor);
                    }
                    graphics.drawString(element.m_text, level * this.m_itemHeight + this.m_imageWidth + 5 - this.m_hscrollPos, (k + 1) * this.m_itemHeight - fontMetrics.getMaxDescent() - 1);
                    graphics.setColor(this.m_lineColor);
                    if ((this.m_exWinStyle & 0x2L) == 0x2L) {
                        for (int l = (this.m_style == TreeView.STYLE_WIN95 && false) ? 1 : 0; l < level; ++l) {
                            if (this.m_list.moreVisibleAtLevel((this.m_style == TreeView.STYLE_WIN95) ? l : (l + 1), this.m_list.nthVisible(k + this.m_top))) {
                                if (this.m_list.indexOf(element) > 0) {
                                    this.drawDashedVertLine(graphics, l * this.m_itemHeight + this.m_itemHeight / 2 - this.m_hscrollPos, k * this.m_itemHeight, (k + 1) * this.m_itemHeight, true);
                                }
                                else {
                                    this.drawDashedVertLine(graphics, l * this.m_itemHeight + this.m_itemHeight / 2 - this.m_hscrollPos, k * this.m_itemHeight + this.m_itemHeight / 2 - 1, (k + 1) * this.m_itemHeight, true);
                                }
                            }
                        }
                        if (level > 0) {
                            final int n6 = (level - 1) * this.m_itemHeight + this.m_itemHeight / 2 - this.m_hscrollPos;
                            final int n7 = k * this.m_itemHeight;
                            if (this.m_list.indexOf(element) > 0) {
                                if (this.m_list.moreVisibleAtLevel(element.m_level, this.m_list.nthVisible(k + this.m_top))) {
                                    this.drawDashedVertLine(graphics, n6, n7, n7 + this.m_itemHeight, true);
                                }
                                else {
                                    this.drawDashedVertLine(graphics, n6, n7, n7 + this.m_itemHeight / 2, true);
                                }
                            }
                            this.drawDashedHorzLine(graphics, n6, n7 + this.m_itemHeight / 2, n6 + this.m_itemHeight / 2, true);
                        }
                    }
                    if (this.m_style == TreeView.STYLE_WIN95 || this.m_style == TreeView.STYLE_EXPLORER) {
                        final int nthVisible2 = this.m_list.nthVisible(n2);
                        if (level > 0 && this.m_list.hasChildren(nthVisible2)) {
                            int n8 = (level - 1) * this.m_itemHeight + (this.m_itemHeight / 2 - 4) - this.m_hscrollPos;
                            if (n8 % 2 != 0) {
                                --n8;
                            }
                            int n9 = k * this.m_itemHeight + (this.m_itemHeight / 2 - 4);
                            if (n9 % 2 != 0) {
                                --n9;
                            }
                            if (!this.m_list.hasVisibleChildren(nthVisible2)) {
                                if (this.m_bgImage == null) {
                                    graphics.drawImage(this.plusImg, n8, n9, this);
                                }
                                else {
                                    this.drawPlus(graphics, n8, n9);
                                }
                            }
                            else if (this.m_bgImage == null) {
                                graphics.drawImage(this.minusImg, n8, n9, this);
                            }
                            else {
                                this.drawMinus(graphics, n8, n9);
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex2) {}
        this.m_oldTop = this.m_top;
    }
    
    public void setColors(final Color textColor, final Color lineColor, final Color squareColor, final Color highlightBack, final Color highlightText) {
        this.m_textColor = textColor;
        this.m_lineColor = lineColor;
        this.m_highlightBack = highlightBack;
        this.m_highlightText = highlightText;
        this.m_squareColor = squareColor;
        this.createPlusMinus();
    }
    
    public void setWinStyle(final long winStyle) {
        this.m_winStyle = winStyle;
    }
}
