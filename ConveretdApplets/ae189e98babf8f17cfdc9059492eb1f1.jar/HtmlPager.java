import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.net.URL;
import java.util.Hashtable;
import java.util.Vector;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.Stack;

// 
// Decompiled by Procyon v0.5.30
// 

class HtmlPager
{
    private static final int BUTTON = -1;
    private static final int MARGIN = 20;
    private static final int[] sizes;
    private int offset;
    private Stack fonts;
    private Font font;
    private int spaceWidth;
    private FontMetrics metrics;
    private Stack anchors;
    private boolean anchor;
    private int leftMargin;
    private int rightMargin;
    private int center;
    private int preformatted;
    private Stack lists;
    private int list;
    private Vector hrefs;
    private Hashtable names;
    private int[] heights;
    private Vector lines;
    private HtmlPagerLine line;
    private Href href;
    private boolean lineEmpty;
    private boolean prevLineEmpty;
    private URL url;
    private HtmlCanvas parent;
    private int width;
    private Color bgColor;
    private Color textColor;
    private Color linkColor;
    private Stack fontColors;
    private Color fontColor;
    
    protected HtmlPager(final HtmlCanvas parent, final int width) {
        this.fonts = new Stack();
        this.anchors = new Stack();
        this.anchor = false;
        this.leftMargin = 20;
        this.rightMargin = -20;
        this.lists = new Stack();
        this.list = -1;
        this.hrefs = new Vector();
        this.names = new Hashtable();
        this.lines = new Vector();
        this.lineEmpty = true;
        this.prevLineEmpty = false;
        this.width = -1;
        this.fontColors = new Stack();
        this.width = width;
        this.parent = parent;
        this.rightMargin += width;
        this.pushFont("Times", 0, HtmlPager.sizes[6]);
        this.drawNewLine(true);
        this.bgColor = parent.getBackground();
        this.textColor = parent.getForeground();
        this.linkColor = Color.blue;
        this.fontColor = this.textColor;
    }
    
    protected void finish() {
        final int size = this.lines.size();
        final int[] array = new int[size + 2];
        array[0] = 20;
        for (int i = 0; i < size; ++i) {
            array[i + 1] = array[i] + ((HtmlPagerLine)this.lines.elementAt(i)).getHeight();
        }
        array[size + 1] = array[size] + 20;
        final Href[] array2 = new Href[this.hrefs.size()];
        this.hrefs.copyInto(array2);
        final Vector vector = new Vector();
        Image image = null;
        if (this.width > 0) {
            image = this.parent.createImage(this.width, array[size + 1]);
            if (image != null) {
                this.parent.setBackground(this.bgColor);
                final Graphics graphics = image.getGraphics();
                graphics.setColor(this.bgColor);
                graphics.fillRect(0, 0, this.width, array[size + 1]);
                for (int j = 0; j < size; ++j) {
                    ((HtmlPagerLine)this.lines.elementAt(j)).draw(graphics, array[j], vector);
                }
            }
        }
        final HtmlImage[] array3 = new HtmlImage[vector.size()];
        vector.copyInto(array3);
        this.parent.setData(array, array2, this.names, array3, image);
    }
    
    protected void setBase(final URL url) {
        this.url = url;
    }
    
    protected void setColors(final Color bgColor, final Color textColor, final Color linkColor) {
        if (bgColor != null) {
            this.bgColor = bgColor;
        }
        if (textColor != null) {
            this.textColor = textColor;
        }
        if (linkColor != null) {
            this.linkColor = linkColor;
        }
        this.pushFontColor(this.anchor ? this.linkColor : this.textColor);
    }
    
    protected void pushStandardFont() {
        this.pushFont("TimesRoman", this.font.getStyle(), this.font.getSize());
    }
    
    protected void pushFixedFont() {
        this.pushFont("Courier", this.font.getStyle(), this.font.getSize());
    }
    
    protected void pushFontSize(int n) {
        if (n < 0) {
            n = 0;
        }
        if (n >= HtmlPager.sizes.length) {
            n = HtmlPager.sizes.length - 1;
        }
        this.pushFont(this.font.getName(), this.font.getStyle(), HtmlPager.sizes[n]);
    }
    
    protected int getFontSize() {
        final int size = this.font.getSize();
        for (int i = 0; i < HtmlPager.sizes.length; ++i) {
            if (size == HtmlPager.sizes[i]) {
                return i;
            }
        }
        return -1;
    }
    
    protected void pushBold() {
        this.pushFont(this.font.getName(), this.font.getStyle() | 0x1, this.font.getSize());
    }
    
    protected void pushItalic() {
        this.pushFont(this.font.getName(), this.font.getStyle() | 0x2, this.font.getSize());
    }
    
    private void pushFont(final String s, final int n, final int n2) {
        this.fonts.push(this.font);
        this.font = new Font(s, n, n2);
        this.metrics = this.parent.getFontMetrics(this.font);
        this.spaceWidth = this.metrics.stringWidth(" ");
    }
    
    protected void popFont() {
        if (this.fonts.size() > 1) {
            this.font = this.fonts.pop();
            this.metrics = this.parent.getFontMetrics(this.font);
            this.spaceWidth = this.metrics.stringWidth(" ");
        }
    }
    
    protected void pushFontColor(final Color fontColor) {
        this.fontColors.push(this.fontColor);
        this.fontColor = fontColor;
    }
    
    protected void popFontColor() {
        if (this.fontColors.size() > 0) {
            this.fontColor = this.fontColors.pop();
        }
    }
    
    protected Color getFontColor() {
        return this.fontColor;
    }
    
    protected void pushAnchor(final String s, final String s2) {
        this.anchors.push(new Boolean(this.anchor));
        if (s != null) {
            this.anchor = true;
            this.pushFontColor(this.linkColor);
            this.href = new Href();
            this.href.startLine = this.lines.size() - 1;
            this.href.startOffset = this.offset;
            try {
                this.href.url = new URL(this.url, s);
            }
            catch (Exception ex) {
                this.href.url = null;
            }
        }
        if (s2 != null) {
            this.names.put(s2, new Integer(this.lines.size()));
        }
    }
    
    protected void popAnchor() {
        if (this.anchors.size() > 0) {
            if (this.anchor) {
                this.href.endLine = this.lines.size() - 1;
                this.href.endOffset = this.offset;
                this.hrefs.addElement(this.href);
                this.href = null;
            }
            this.anchor = this.anchors.pop();
            this.popFontColor();
        }
    }
    
    protected void pushLeftMargin(final boolean b) {
        this.leftMargin += 20;
        if (b) {
            this.drawNewLine(true);
        }
    }
    
    protected void popLeftMargin(final boolean b) {
        this.leftMargin -= 20;
        if (b) {
            this.drawNewLine(true);
        }
    }
    
    protected void pushRightMargin() {
        this.rightMargin -= 20;
    }
    
    protected void popRightMargin() {
        this.rightMargin += 20;
    }
    
    protected void pushCenter() {
        this.drawNewLine(false);
        ++this.center;
    }
    
    protected void popCenter() {
        this.drawNewLine(false);
        --this.center;
    }
    
    protected void pushPreformatted() {
        this.pushFixedFont();
        this.drawNewLine(true);
        ++this.preformatted;
        this.prevLineEmpty = true;
    }
    
    protected void popPreformatted() {
        --this.preformatted;
        this.drawNewLine(true);
        this.popFont();
    }
    
    protected void pushListButton() {
        this.lists.push(new Integer(this.list));
        this.list = -1;
    }
    
    protected void pushListNumber() {
        this.lists.push(new Integer(this.list));
        this.list += 0;
    }
    
    protected void popList() {
        if (this.lists.size() > 0) {
            this.list = this.lists.pop();
        }
    }
    
    private void finishLine() {
        this.prevLineEmpty = this.lineEmpty;
        if (this.center > 0) {
            this.line.translate((this.rightMargin - this.offset) / 2);
        }
        this.line = new HtmlPagerLine();
        this.lines.addElement(this.line);
        this.offset = 0;
        this.line.addItem(this.metrics, this.offset, new HtmlPagerItem(this.fontColor, this.font, ""));
        this.lineEmpty = false;
        this.offset = this.leftMargin;
        this.lineEmpty = true;
    }
    
    protected void drawNewLine(final boolean b) {
        if (this.lineEmpty) {
            this.offset = this.leftMargin;
        }
        if (this.lineEmpty && this.lines.size() == 1) {
            return;
        }
        if (this.lineEmpty) {
            if (this.preformatted > 0 || b) {
                if (this.prevLineEmpty) {
                    return;
                }
                this.finishLine();
            }
            return;
        }
        this.finishLine();
        if (b) {
            this.finishLine();
        }
    }
    
    protected void drawRule() {
        this.drawNewLine(false);
        this.line.addItem(this.metrics, this.offset, new HtmlPagerItem(this.fontColor, this.rightMargin - this.leftMargin));
        this.drawNewLine(this.lineEmpty = false);
    }
    
    protected void drawListItem() {
        final int offset = this.offset;
        this.offset = this.leftMargin - 20;
        if (this.list == -1) {
            this.line.addItem(this.metrics, this.offset, new HtmlPagerItem(this.fontColor, this.font, "-"));
            this.lineEmpty = false;
        }
        else {
            this.line.addItem(this.metrics, this.offset, new HtmlPagerItem(this.fontColor, this.font, String.valueOf(++this.list)));
            this.lineEmpty = false;
        }
        this.offset = offset;
    }
    
    protected synchronized void drawImage(final HtmlImage htmlImage, final String s) {
        final Color color = this.anchor ? this.linkColor : null;
        if (this.offset + htmlImage.w >= this.rightMargin) {
            this.drawNewLine(false);
        }
        if ("TOP".equalsIgnoreCase(s)) {
            this.line.addItem(this.metrics, this.offset, new HtmlPagerItem(color, htmlImage, 1));
            this.lineEmpty = false;
        }
        else if ("MIDDLE".equalsIgnoreCase(s)) {
            this.line.addItem(this.metrics, this.offset, new HtmlPagerItem(color, htmlImage, 0));
            this.lineEmpty = false;
        }
        else {
            this.line.addItem(this.metrics, this.offset, new HtmlPagerItem(color, htmlImage, -1));
            this.lineEmpty = false;
        }
        this.offset += htmlImage.w;
    }
    
    protected void drawText(final String s) {
        if (this.preformatted > 0) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n", true);
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.equals("\n")) {
                    this.drawNewLine(false);
                    this.prevLineEmpty = false;
                }
                else {
                    this.line.addItem(this.metrics, this.offset, new HtmlPagerItem(this.fontColor, this.font, nextToken));
                    this.lineEmpty = false;
                    this.offset += this.metrics.stringWidth(nextToken);
                }
            }
            return;
        }
        if (this.offset > this.leftMargin) {
            this.offset += this.spaceWidth;
        }
        final StringTokenizer stringTokenizer2 = new StringTokenizer(s);
        while (stringTokenizer2.hasMoreTokens()) {
            final String nextToken2 = stringTokenizer2.nextToken();
            final int stringWidth = this.metrics.stringWidth(nextToken2);
            if (this.offset + stringWidth >= this.rightMargin) {
                this.drawNewLine(false);
            }
            this.line.addItem(this.metrics, this.offset, new HtmlPagerItem(this.fontColor, this.font, nextToken2));
            this.lineEmpty = false;
            this.offset += stringWidth + this.spaceWidth;
        }
        if (stringTokenizer2.countTokens() > 0) {
            this.offset -= this.spaceWidth;
        }
    }
    
    private void drawNewLine(final int n) {
        if (this.offset + n >= this.rightMargin) {
            this.drawNewLine(false);
        }
    }
    
    private void addItem(final HtmlPagerItem htmlPagerItem) {
        this.line.addItem(this.metrics, this.offset, htmlPagerItem);
        this.lineEmpty = false;
    }
    
    static {
        sizes = new int[] { 32, 28, 24, 20, 17, 15, 14 };
    }
}
