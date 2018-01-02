// 
// Decompiled by Procyon v0.5.30
// 

package XMLConsumer;

import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.util.Vector;
import hhapplet.IActionSink;
import hhapplet.BsscFontFixPatch;
import java.net.MalformedURLException;
import hhapplet.URLFileHandler;
import java.net.URL;
import java.awt.Font;
import java.awt.Color;

public class FtsEntry extends Topic implements IEntry, Cloneable
{
    private static Color GREEN;
    private static final int FTS_INDENT = 2;
    private FtsTData m_FtsData;
    private boolean m_bSelect;
    private boolean m_bHighLight;
    private static int m_indent;
    private static Color m_activeColor;
    private static Font m_normalFont;
    private static Font m_hoverFont;
    private static Color m_normalColor;
    private static Color m_hoverColor;
    private static boolean m_normalUnderline;
    private static boolean m_hoverUnderline;
    
    public boolean equalTo(final FtsEntry ftsEntry) {
        return this.getName().equals(ftsEntry.getName()) && ftsEntry.getFullURL().equals(this.getFullURL());
    }
    
    public int getPrevSpan() {
        return 0;
    }
    
    public static void setHoverUnderline(final boolean hoverUnderline) {
        FtsEntry.m_hoverUnderline = hoverUnderline;
    }
    
    public boolean isMainEntry() {
        return true;
    }
    
    public static void setNormalColor(final Color normalColor) {
        FtsEntry.m_normalColor = normalColor;
    }
    
    public static void setHoverFont(final Font hoverFont) {
        if (hoverFont.getSize() != FtsEntry.m_normalFont.getSize()) {
            FtsEntry.m_hoverFont = new Font(hoverFont.getFamily(), hoverFont.getStyle(), FtsEntry.m_normalFont.getSize());
            return;
        }
        FtsEntry.m_hoverFont = hoverFont;
    }
    
    public FtsEntry(final String s, final String s2, final FtsTData ftsData) {
        super(s, s2);
        this.m_FtsData = ftsData;
    }
    
    private URL getFullURL() {
        URL url = null;
        try {
            url = URLFileHandler.makeURL(this.m_FtsData.getProjURL(), this.getURL(), null);
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return url;
    }
    
    public void select(final boolean bSelect) {
        this.m_bSelect = bSelect;
    }
    
    public void copyFrom(final FtsEntry ftsEntry) {
        super.copyFrom(ftsEntry);
        this.m_FtsData = ftsEntry.m_FtsData;
    }
    
    public static void setNormalFont(final Font normalFont) {
        FtsEntry.m_normalFont = normalFont;
    }
    
    public static void setNormalUnderline(final boolean normalUnderline) {
        FtsEntry.m_normalUnderline = normalUnderline;
    }
    
    public static void setActiveColor(final Color activeColor) {
        FtsEntry.m_activeColor = activeColor;
    }
    
    static {
        FtsEntry.GREEN = new Color(0, 127, 0);
        FtsEntry.m_indent = 2;
        FtsEntry.m_activeColor = Color.gray;
        FtsEntry.m_normalFont = new Font(BsscFontFixPatch.GetFontName(), 0, BsscFontFixPatch.GetFontSize());
        FtsEntry.m_hoverFont = new Font(BsscFontFixPatch.GetFontName(), 0, BsscFontFixPatch.GetFontSize());
        FtsEntry.m_normalColor = Color.black;
        FtsEntry.m_hoverColor = FtsEntry.GREEN;
        FtsEntry.m_normalUnderline = false;
        FtsEntry.m_hoverUnderline = true;
    }
    
    public void highLight(final boolean bHighLight) {
        this.m_bHighLight = bHighLight;
    }
    
    public void action(final IActionSink actionSink) {
        final URL fullURL = this.getFullURL();
        final Vector<URL> vector = new Vector<URL>();
        vector.addElement(fullURL);
        actionSink.accept(vector);
    }
    
    public int getNextSpan() {
        return 0;
    }
    
    public void display(final Graphics graphics, final int n, final int n2, final Color color, final Image image) {
        final Color color2 = graphics.getColor();
        final FontMetrics fontMetrics = graphics.getFontMetrics(FtsEntry.m_normalFont);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics(FtsEntry.m_hoverFont);
        final int stringWidth = fontMetrics.stringWidth(this.getName() + " ");
        final int stringWidth2 = fontMetrics2.stringWidth(this.getName() + " ");
        final int n3 = (stringWidth > stringWidth2) ? stringWidth : stringWidth2;
        final int ascent = fontMetrics.getAscent();
        final int leading = fontMetrics.getLeading();
        final int ascent2 = fontMetrics2.getAscent();
        final int leading2 = fontMetrics2.getLeading();
        if (this.m_bSelect) {
            graphics.setColor(FtsEntry.m_activeColor);
            graphics.fill3DRect(FtsEntry.m_indent, n * n2, n3, n2, true);
        }
        else if (image != null) {
            graphics.drawImage(image, FtsEntry.m_indent, n * n2, 2 + n3, n * n2 + n2, FtsEntry.m_indent, n * n2, FtsEntry.m_indent + n3, n * n2 + n2, null);
        }
        else {
            graphics.setColor(color);
            graphics.fillRect(FtsEntry.m_indent, n * n2, n3, n2);
        }
        graphics.setColor(color2);
        final Font font = graphics.getFont();
        if (this.m_bHighLight) {
            graphics.setColor(FtsEntry.m_hoverColor);
            graphics.setFont(FtsEntry.m_hoverFont);
            graphics.drawString(this.getName(), FtsEntry.m_indent, n * n2 + ascent2 + leading2);
            if (FtsEntry.m_hoverUnderline) {
                graphics.drawLine(FtsEntry.m_indent, (n + 1) * n2 - 1, FtsEntry.m_indent + n3 - 1, (n + 1) * n2 - 1);
            }
            graphics.setColor(color2);
            graphics.setFont(font);
            return;
        }
        graphics.setColor(FtsEntry.m_normalColor);
        graphics.setFont(FtsEntry.m_normalFont);
        graphics.drawString(this.getName(), FtsEntry.m_indent, n * n2 + ascent + leading);
        if (FtsEntry.m_normalUnderline) {
            graphics.drawLine(FtsEntry.m_indent, (n + 1) * n2 - 1, FtsEntry.m_indent + n3 - 1, (n + 1) * n2 - 1);
        }
        graphics.setColor(color2);
        graphics.setFont(font);
    }
    
    public static void setIndent(final int indent) {
        FtsEntry.m_indent = indent;
    }
    
    public int getWidth(final Graphics graphics) {
        return FtsEntry.m_indent + graphics.getFontMetrics(FtsEntry.m_normalFont).stringWidth(this.getName() + " ") + 1;
    }
    
    public FtsEntry cloneEntry() {
        FtsEntry ftsEntry = null;
        try {
            ftsEntry = (FtsEntry)this.clone();
        }
        catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return ftsEntry;
    }
    
    public static void setHoverColor(final Color hoverColor) {
        FtsEntry.m_hoverColor = hoverColor;
    }
}
