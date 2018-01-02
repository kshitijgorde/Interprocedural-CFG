// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import XMLConsumer.IEntry;

class ErrEntry implements IEntry
{
    private String m_sMsg;
    private static Font m_eFont;
    private static Color m_eColor;
    private static boolean m_eUnderline;
    
    public int getPrevSpan() {
        return 0;
    }
    
    public static void setErrorFont(final Font eFont) {
        ErrEntry.m_eFont = eFont;
    }
    
    public static void setErrorUnderline(final boolean eUnderline) {
        ErrEntry.m_eUnderline = eUnderline;
    }
    
    public ErrEntry(final String sMsg) {
        this.m_sMsg = sMsg;
    }
    
    public boolean isMainEntry() {
        return true;
    }
    
    public void select(final boolean b) {
    }
    
    static {
        ErrEntry.m_eFont = new Font(BsscFontFixPatch.GetFontName(), 1, BsscFontFixPatch.GetFontSize());
        ErrEntry.m_eColor = Color.black;
        ErrEntry.m_eUnderline = false;
    }
    
    public static void setErrorColor(final Color eColor) {
        ErrEntry.m_eColor = eColor;
    }
    
    public String getName() {
        return this.m_sMsg;
    }
    
    public void highLight(final boolean b) {
    }
    
    public void action(final IActionSink actionSink) {
    }
    
    public int getNextSpan() {
        return 0;
    }
    
    public void display(final Graphics graphics, final int n, final int n2, final Color color, final Image image) {
        final FontMetrics fontMetrics = graphics.getFontMetrics(ErrEntry.m_eFont);
        final int stringWidth = fontMetrics.stringWidth(this.getName() + " ");
        final int ascent = fontMetrics.getAscent();
        fontMetrics.getDescent();
        final int leading = fontMetrics.getLeading();
        final Font font = graphics.getFont();
        final Color color2 = graphics.getColor();
        graphics.setFont(ErrEntry.m_eFont);
        graphics.setColor(ErrEntry.m_eColor);
        graphics.drawString(this.getName(), 0, n * n2 + ascent + leading);
        if (ErrEntry.m_eUnderline) {
            graphics.drawLine(0, (n + 1) * n2 - 1, stringWidth - 1, (n + 1) * n2 - 1);
        }
        graphics.setFont(font);
        graphics.setColor(color2);
    }
    
    public int getWidth(final Graphics graphics) {
        return graphics.getFontMetrics(ErrEntry.m_eFont).stringWidth(this.getName() + " ") + 1;
    }
}
