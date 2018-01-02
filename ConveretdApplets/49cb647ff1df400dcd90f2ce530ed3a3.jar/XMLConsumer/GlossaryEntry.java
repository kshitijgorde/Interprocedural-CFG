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
import java.awt.Font;
import java.awt.Color;

public class GlossaryEntry implements IEntry
{
    private String m_name;
    private String m_value;
    private boolean m_bSelect;
    private boolean m_bHighLight;
    private IdxData m_IdxData;
    private static Color GREEN;
    private static final int GLO_INDENT = 2;
    private static Color m_activeColor;
    private static int m_indent;
    private static Font m_normalFont;
    private static Font m_hoverFont;
    private static Color m_normalColor;
    private static Color m_hoverColor;
    private static boolean m_normalUnderline;
    private static boolean m_hoverUnderline;
    
    public int getPrevSpan() {
        return 0;
    }
    
    public String getValue() {
        return this.m_value;
    }
    
    public void appendValue(final String s) {
        this.m_value = this.m_value + "\n" + s;
    }
    
    public static void setHoverUnderline(final boolean hoverUnderline) {
        GlossaryEntry.m_hoverUnderline = hoverUnderline;
    }
    
    public boolean isMainEntry() {
        return true;
    }
    
    public static void setNormalColor(final Color normalColor) {
        GlossaryEntry.m_normalColor = normalColor;
    }
    
    public static void setHoverFont(final Font hoverFont) {
        if (hoverFont.getSize() != GlossaryEntry.m_normalFont.getSize()) {
            GlossaryEntry.m_hoverFont = new Font(hoverFont.getFamily(), hoverFont.getStyle(), GlossaryEntry.m_normalFont.getSize());
            return;
        }
        GlossaryEntry.m_hoverFont = hoverFont;
    }
    
    public GlossaryEntry(final String name, final String value) {
        this.m_name = name;
        this.m_bHighLight = false;
        this.m_bSelect = false;
        this.m_value = value;
    }
    
    public void select(final boolean bSelect) {
        this.m_bSelect = bSelect;
    }
    
    public static void setNormalFont(final Font normalFont) {
        GlossaryEntry.m_normalFont = normalFont;
    }
    
    public static void setNormalUnderline(final boolean normalUnderline) {
        GlossaryEntry.m_normalUnderline = normalUnderline;
    }
    
    public static void setActiveColor(final Color activeColor) {
        GlossaryEntry.m_activeColor = activeColor;
    }
    
    static {
        GlossaryEntry.GREEN = new Color(0, 127, 0);
        GlossaryEntry.m_activeColor = Color.gray;
        GlossaryEntry.m_indent = 2;
        GlossaryEntry.m_normalFont = new Font(BsscFontFixPatch.GetFontName(), 0, BsscFontFixPatch.GetFontSize());
        GlossaryEntry.m_hoverFont = new Font(BsscFontFixPatch.GetFontName(), 0, BsscFontFixPatch.GetFontSize());
        GlossaryEntry.m_normalColor = Color.black;
        GlossaryEntry.m_hoverColor = GlossaryEntry.GREEN;
        GlossaryEntry.m_normalUnderline = false;
        GlossaryEntry.m_hoverUnderline = true;
    }
    
    public String getName() {
        return this.m_name;
    }
    
    public void highLight(final boolean bHighLight) {
        this.m_bHighLight = bHighLight;
    }
    
    public void action(final IActionSink actionSink) {
        final Vector<String> vector = new Vector<String>();
        vector.addElement(this.m_name);
        vector.addElement(this.m_value);
        actionSink.accept(vector);
    }
    
    public int getNextSpan() {
        return 0;
    }
    
    public void display(final Graphics graphics, final int n, final int n2, final Color color, final Image image) {
        final FontMetrics fontMetrics = graphics.getFontMetrics(GlossaryEntry.m_normalFont);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics(GlossaryEntry.m_hoverFont);
        final Color color2 = graphics.getColor();
        final int stringWidth = fontMetrics.stringWidth(this.m_name + " ");
        final int stringWidth2 = fontMetrics2.stringWidth(this.m_name + " ");
        final int n3 = (stringWidth > stringWidth2) ? stringWidth : stringWidth2;
        final int ascent = fontMetrics.getAscent();
        final int leading = fontMetrics.getLeading();
        final int ascent2 = fontMetrics2.getAscent();
        final int leading2 = fontMetrics2.getLeading();
        if (this.m_bSelect) {
            graphics.setColor(GlossaryEntry.m_activeColor);
            graphics.fill3DRect(GlossaryEntry.m_indent, n * n2, n3, n2, true);
        }
        else if (image != null) {
            graphics.drawImage(image, GlossaryEntry.m_indent, n * n2, GlossaryEntry.m_indent + n3, n * n2 + n2, GlossaryEntry.m_indent, n * n2, GlossaryEntry.m_indent + n3, n * n2 + n2, null);
        }
        else {
            graphics.setColor(color);
            graphics.fillRect(GlossaryEntry.m_indent, n * n2, n3, n2);
        }
        graphics.setColor(color2);
        final Font font = graphics.getFont();
        if (this.m_bHighLight) {
            graphics.setFont(GlossaryEntry.m_hoverFont);
            graphics.setColor(GlossaryEntry.m_hoverColor);
            graphics.drawString(this.m_name, GlossaryEntry.m_indent, n * n2 + ascent2 + leading2);
            if (GlossaryEntry.m_hoverUnderline) {
                graphics.drawLine(GlossaryEntry.m_indent, (n + 1) * n2 - 1, GlossaryEntry.m_indent + n3 - 1, (n + 1) * n2 - 1);
            }
            graphics.setFont(font);
            graphics.setColor(color2);
            return;
        }
        graphics.setFont(GlossaryEntry.m_normalFont);
        graphics.setColor(GlossaryEntry.m_normalColor);
        graphics.drawString(this.m_name, GlossaryEntry.m_indent, n * n2 + ascent + leading);
        if (GlossaryEntry.m_normalUnderline) {
            graphics.drawLine(GlossaryEntry.m_indent, (n + 1) * n2 - 1, GlossaryEntry.m_indent + n3 - 1, (n + 1) * n2 - 1);
        }
        graphics.setFont(font);
        graphics.setColor(color2);
    }
    
    public static void setIndent(final int indent) {
        GlossaryEntry.m_indent = indent;
    }
    
    public int getWidth(final Graphics graphics) {
        return GlossaryEntry.m_indent + graphics.getFontMetrics(GlossaryEntry.m_normalFont).stringWidth(this.m_name + " ") + 1;
    }
    
    public static void setHoverColor(final Color hoverColor) {
        GlossaryEntry.m_hoverColor = hoverColor;
    }
}
