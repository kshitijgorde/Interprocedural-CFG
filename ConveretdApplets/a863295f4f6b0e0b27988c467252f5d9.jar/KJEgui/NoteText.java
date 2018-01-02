// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.Graphics;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;

public class NoteText
{
    String[] lines;
    int numLines;
    int textWidth;
    static Color boldColor;
    static Color normalColor;
    static Font font;
    static FontMetrics fm;
    static Font boldFont;
    static FontMetrics boldfm;
    static int maxWidth;
    
    static {
        NoteText.boldColor = Color.black;
        NoteText.normalColor = Color.black;
    }
    
    public NoteText(final String s) {
        final String[] lines = new String[200];
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        int n = 0;
        int n2 = 0;
        lines[0] = "";
        while (stringTokenizer.hasMoreElements()) {
            try {
                final String string = String.valueOf(stringTokenizer.nextToken()) + " ";
                if ("// ".equals(string)) {
                    this.textWidth = Math.max(NoteText.fm.stringWidth(lines[n]), this.textWidth);
                    n2 = 0;
                    lines[++n] = "";
                }
                else {
                    if (string == null) {
                        continue;
                    }
                    final int stringWidth = ((n == 0) ? NoteText.boldfm : NoteText.fm).stringWidth(string);
                    n2 += stringWidth;
                    if (n2 < NoteText.maxWidth || n2 == stringWidth) {
                        final String[] array = lines;
                        final int n3 = n;
                        array[n3] = String.valueOf(array[n3]) + string;
                    }
                    else {
                        this.textWidth = Math.max(NoteText.fm.stringWidth(lines[n]), this.textWidth);
                        n2 = stringWidth;
                        lines[++n] = string;
                    }
                }
            }
            catch (NoSuchElementException ex) {}
        }
        this.numLines = n + 1;
        this.lines = lines;
    }
    
    public void draw(final Graphics graphics, final int n, final int n2) {
        this.draw(graphics, n, n2, NoteText.boldColor, NoteText.normalColor);
    }
    
    public synchronized void draw(final Graphics graphics, final int n, final int n2, final Color color, final Color color2) {
        int n3 = n2;
        final int height = NoteText.fm.getHeight();
        graphics.setColor(color);
        graphics.setFont(NoteText.boldFont);
        for (int i = 0; i < this.numLines; ++i) {
            graphics.drawString(this.lines[i], n, n3);
            n3 += height;
            if (i == 0) {
                graphics.setColor(color2);
                graphics.setFont(NoteText.font);
                n3 += 2;
            }
        }
    }
    
    public int getHeight() {
        return this.numLines * NoteText.fm.getHeight();
    }
    
    public int getWidth() {
        return this.textWidth;
    }
    
    public static void setAttributes(final Font font, final FontMetrics fm, final Font boldFont, final FontMetrics boldfm, final int maxWidth, final Color boldColor, final Color normalColor) {
        NoteText.font = font;
        NoteText.fm = fm;
        NoteText.boldFont = boldFont;
        NoteText.boldfm = boldfm;
        NoteText.maxWidth = maxWidth;
        NoteText.boldColor = boldColor;
        NoteText.normalColor = normalColor;
    }
}
