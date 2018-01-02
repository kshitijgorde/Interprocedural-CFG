// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.NoSuchElementException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.Color;
import java.applet.AppletContext;
import java.awt.event.MouseListener;
import java.awt.Canvas;

public class MultiLineLabel extends Canvas implements MouseListener
{
    public static final int LEFT = 0;
    public static final int CENTER = 1;
    public static final int RIGHT = 2;
    protected LineInfo lines;
    protected int numLines;
    protected UrlInfo urls;
    protected int numUrls;
    protected AppletContext context;
    protected String htmlTarget;
    protected int marginWidth;
    protected int marginHeight;
    protected int lineHeight;
    protected int lineAscent;
    protected int maxWidth;
    protected int alignment;
    protected int fixedWidth;
    
    public MultiLineLabel(final String s, final AppletContext context, final String htmlTarget, final int marginWidth, final int marginHeight, final int alignment) {
        this.alignment = 0;
        this.fixedWidth = -1;
        this.addMouseListener(this);
        this.newLabel(s);
        this.context = context;
        this.htmlTarget = htmlTarget;
        this.marginWidth = marginWidth;
        this.marginHeight = marginHeight;
        this.alignment = alignment;
    }
    
    public MultiLineLabel(final String s, final AppletContext appletContext, final String s2, final int n, final int n2) {
        this(s, appletContext, s2, n, n2, 0);
    }
    
    public MultiLineLabel(final String s, final AppletContext appletContext, final String s2, final int n) {
        this(s, appletContext, s2, 10, 10, n);
    }
    
    public MultiLineLabel(final String s, final AppletContext appletContext, final String s2) {
        this(s, appletContext, s2, 10, 10, 0);
    }
    
    public MultiLineLabel(final String s) {
        this(s, null, null, 10, 10, 0);
    }
    
    protected void newLabel(final String s) {
        if (s != null) {
            Color color = this.getForeground();
            if (color == null) {
                color = Color.black;
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n ", true);
            LineInfo next;
            if (stringTokenizer.hasMoreTokens()) {
                this.numLines = 1;
                next = (this.lines = new LineInfo());
            }
            else {
                this.numLines = 0;
                next = (this.lines = null);
            }
            UrlInfo next2 = this.urls = null;
            this.numUrls = 0;
            SubstringInfo substringInfo = null;
            int n = 1;
            try {
                while (stringTokenizer.hasMoreTokens()) {
                    final String nextToken = stringTokenizer.nextToken();
                    if (nextToken.startsWith("\n")) {
                        next.next = new LineInfo();
                        next = next.next;
                        ++this.numLines;
                        n = 1;
                        substringInfo = null;
                    }
                    else {
                        try {
                            final URL url = new URL(nextToken);
                            if (url == null) {
                                continue;
                            }
                            if (next2 == null) {
                                next2 = (this.urls = new UrlInfo(url));
                            }
                            else {
                                next2.next = new UrlInfo(url);
                                next2 = next2.next;
                            }
                            ++this.numUrls;
                            if (substringInfo == null) {
                                substringInfo = (next.substrings = new SubstringInfo(Color.blue, nextToken));
                            }
                            else {
                                substringInfo.next = new SubstringInfo(Color.blue, nextToken);
                                substringInfo = substringInfo.next;
                            }
                            substringInfo.url = next2;
                            n = 1;
                            final LineInfo lineInfo = next;
                            ++lineInfo.numSubstrings;
                        }
                        catch (MalformedURLException ex) {
                            if (n != 0) {
                                if (substringInfo == null) {
                                    substringInfo = (next.substrings = new SubstringInfo(color, nextToken));
                                }
                                else {
                                    substringInfo.next = new SubstringInfo(color, nextToken);
                                    substringInfo = substringInfo.next;
                                }
                                final LineInfo lineInfo2 = next;
                                ++lineInfo2.numSubstrings;
                            }
                            else {
                                final SubstringInfo substringInfo2 = substringInfo;
                                substringInfo2.text = String.valueOf(substringInfo2.text) + nextToken;
                            }
                            n = 0;
                        }
                    }
                }
            }
            catch (NoSuchElementException ex2) {}
        }
    }
    
    protected void measure() {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        if (fontMetrics == null) {
            return;
        }
        this.lineHeight = fontMetrics.getHeight();
        this.lineAscent = fontMetrics.getAscent();
        this.maxWidth = 0;
        for (LineInfo lineInfo = this.lines; lineInfo != null; lineInfo = lineInfo.next) {
            for (SubstringInfo substringInfo = lineInfo.substrings; substringInfo != null; substringInfo = substringInfo.next) {
                substringInfo.width = fontMetrics.stringWidth(substringInfo.text);
                final LineInfo lineInfo2 = lineInfo;
                lineInfo2.width += substringInfo.width;
            }
            if (lineInfo.width > this.maxWidth) {
                this.maxWidth = lineInfo.width;
            }
        }
    }
    
    public void setLabel(final String s) {
        this.newLabel(s);
        this.measure();
        this.repaint();
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.measure();
        this.repaint();
    }
    
    public void setForeground(final Color foreground) {
        super.setForeground(foreground);
        this.repaint();
    }
    
    public void setEnvironment(final AppletContext context, final String htmlTarget, final int marginWidth, final int marginHeight, final int alignment) {
        this.context = context;
        this.htmlTarget = htmlTarget;
        this.marginWidth = marginWidth;
        this.marginHeight = marginHeight;
        this.alignment = alignment;
    }
    
    public void setFixedWidth(final int fixedWidth) {
        this.fixedWidth = fixedWidth;
    }
    
    public void addNotify() {
        super.addNotify();
        this.measure();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.maxWidth + 2 * this.marginWidth, this.numLines * this.lineHeight + 2 * this.marginHeight);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this.maxWidth, this.numLines * this.lineHeight);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        int n = this.lineAscent + (size.height - this.numLines * this.lineHeight) / 2;
        for (LineInfo lineInfo = this.lines; lineInfo != null; lineInfo = lineInfo.next, n += this.lineHeight) {
            int marginWidth = 0;
            switch (this.alignment) {
                default: {
                    marginWidth = this.marginWidth;
                    break;
                }
                case 1: {
                    marginWidth = (size.width - lineInfo.width) / 2;
                    break;
                }
                case 2: {
                    final Dimension dimension = size;
                    final int width = lineInfo.width;
                    this.marginWidth = width;
                    dimension.width = width;
                    marginWidth = width;
                    break;
                }
            }
            for (SubstringInfo substringInfo = lineInfo.substrings; substringInfo != null; substringInfo = substringInfo.next) {
                graphics.setColor(substringInfo.color);
                graphics.drawString(substringInfo.text, marginWidth, n);
                if (substringInfo.url != null) {
                    substringInfo.url.rect.x = marginWidth;
                    substringInfo.url.rect.y = n - this.lineHeight;
                    substringInfo.url.rect.width = substringInfo.width;
                    substringInfo.url.rect.height = this.lineHeight;
                }
                marginWidth += substringInfo.width;
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final int modifiers = mouseEvent.getModifiers();
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if ((modifiers & 0x4) == 0x0 && (modifiers & 0x8) == 0x0) {
            UrlInfo urlInfo = this.urls;
            while (urlInfo != null) {
                if (urlInfo.rect.contains(x, y)) {
                    if (this.context == null) {
                        break;
                    }
                    if (this.htmlTarget != null && this.htmlTarget.length() > 1) {
                        this.context.showDocument(urlInfo.url, this.htmlTarget);
                        return;
                    }
                    this.context.showDocument(urlInfo.url);
                }
                else {
                    urlInfo = urlInfo.next;
                }
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
}
