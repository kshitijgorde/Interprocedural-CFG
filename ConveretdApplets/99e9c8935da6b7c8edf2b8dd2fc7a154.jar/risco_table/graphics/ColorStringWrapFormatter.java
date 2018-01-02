// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.graphics;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;

public class ColorStringWrapFormatter extends StringWrapFormatter
{
    private Color[] colors;
    private int toggleCount;
    private int[] toggleVector;
    private int[] colorVector;
    
    public ColorStringWrapFormatter() {
        this.colors = new Color[] { null, Color.red, Color.green, Color.blue, Color.magenta, Color.cyan, Color.yellow, Color.darkGray, Color.gray, Color.lightGray };
        this.toggleVector = new int[8];
        this.colorVector = new int[8];
    }
    
    public ColorStringWrapFormatter(final int hAlignment, final int vAlignment) {
        super(hAlignment, vAlignment);
        this.colors = new Color[] { null, Color.red, Color.green, Color.blue, Color.magenta, Color.cyan, Color.yellow, Color.darkGray, Color.gray, Color.lightGray };
        this.toggleVector = new int[8];
        this.colorVector = new int[8];
    }
    
    public ColorStringWrapFormatter(final int hAlignment, final int vAlignment, final int[] tabstops, final int decTabstop) {
        super(hAlignment, vAlignment, tabstops, decTabstop);
        this.colors = new Color[] { null, Color.red, Color.green, Color.blue, Color.magenta, Color.cyan, Color.yellow, Color.darkGray, Color.gray, Color.lightGray };
        this.toggleVector = new int[8];
        this.colorVector = new int[8];
    }
    
    private int colorParse(final String s, final char[] chars) {
        int copyIndex = 0;
        boolean escape = false;
        this.toggleCount = 0;
        for (int i = 0; i < s.length(); ++i) {
            final char c = s.charAt(i);
            if (escape) {
                if (c == '~') {
                    chars[copyIndex++] = '~';
                }
                else {
                    if (this.toggleVector.length == this.toggleCount) {
                        this.increaseCapacity();
                    }
                    chars[copyIndex++] = '\n';
                    this.toggleVector[this.toggleCount] = copyIndex;
                    this.colorVector[this.toggleCount] = Character.digit(c, 10);
                    ++this.toggleCount;
                }
                escape = false;
            }
            else if (c == '~') {
                escape = true;
            }
            else {
                chars[copyIndex++] = c;
            }
        }
        return copyIndex;
    }
    
    public void formatString(final Graphics g, final FontMetrics m, final String s, final int x, final int y, final int width, final int height) {
        int toggleIndex = 0;
        final char[] chars = new char[s.length()];
        final int length = this.colorParse(s, chars);
        if (g != null) {
            this.colors[0] = g.getColor();
        }
        this.wrap(m, chars, 0, length, width);
        final int fontHeight = m.getHeight();
        final int blockHeight = super.lineCount * fontHeight;
        int maxWidth = 0;
        int curBaseline;
        if (super.vAlignment == 4) {
            curBaseline = (height + blockHeight) / 2 - blockHeight + fontHeight;
        }
        else if (super.vAlignment == 8) {
            curBaseline = height - blockHeight + fontHeight;
        }
        else {
            curBaseline = fontHeight;
        }
        curBaseline = y + curBaseline - m.getDescent();
        for (int i = 0; i < super.lineCount; ++i) {
            final int nextToggleIndex = (toggleIndex < this.toggleCount) ? this.toggleVector[toggleIndex] : length;
            if (nextToggleIndex == super.startIndexes[i] && g != null) {
                g.setColor(this.colors[this.colorVector[toggleIndex++]]);
            }
            this.drawChars(g, m, chars, super.startIndexes[i], super.pastIndexes[i] - super.startIndexes[i], x, curBaseline, width);
            curBaseline += fontHeight;
            if (super.preferredWidth > maxWidth) {
                maxWidth = super.preferredWidth;
            }
        }
        super.preferredHeight = blockHeight;
        super.preferredWidth = maxWidth;
    }
    
    private void increaseCapacity() {
        final int capacity = this.toggleVector.length;
        final int[] newToggleVector = new int[capacity * 2];
        final int[] newColorVector = new int[capacity * 2];
        System.arraycopy(this.toggleVector, 0, newToggleVector, 0, capacity);
        System.arraycopy(this.colorVector, 0, newColorVector, 0, capacity);
        this.toggleVector = newToggleVector;
        this.colorVector = newColorVector;
    }
    
    public boolean isWrappingFormatter() {
        return true;
    }
    
    public void setColors(final Color[] colors) {
        this.colors = new Color[colors.length + 1];
        for (int i = 0; i < colors.length; ++i) {
            this.colors[i + 1] = colors[i];
        }
    }
}
