// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import java.awt.FontMetrics;
import java.awt.Graphics;
import edu.hws.jcm.data.NumUtils;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Color;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.awt.Computable;

public class DrawString extends Drawable implements Computable
{
    public static final int TOP_LEFT = 0;
    public static final int TOP_CENTER = 1;
    public static final int TOP_RIGHT = 2;
    public static final int CENTER_LEFT = 4;
    public static final int CENTER_CENTER = 5;
    public static final int CENTER_RIGHT = 6;
    public static final int BOTTOM_LEFT = 8;
    public static final int BOTTOM_CENTER = 9;
    public static final int BOTTOM_RIGHT = 10;
    public static final int CENTER = 5;
    public static final int LEFT = 0;
    public static final int RIGHT = 2;
    protected int position;
    protected String baseString;
    protected String[] strings;
    protected Value[] values;
    protected Value xPos;
    protected Value yPos;
    protected Color color;
    protected Font font;
    protected int offset;
    protected boolean clamp;
    protected int justification;
    protected int numSize;
    protected Color backgroundColor;
    protected int frameWidth;
    protected Color frameColor;
    private double xRef;
    private double yRef;
    private boolean changed;
    
    public DrawString() {
        this(null, 0, null);
    }
    
    public DrawString(final String s) {
        this(s, 0, null);
    }
    
    public DrawString(final String s, final int n) {
        this(s, n, null);
    }
    
    public DrawString(final String string, final int position, final Value[] values) {
        this.offset = 3;
        this.clamp = true;
        this.justification = 0;
        this.numSize = 10;
        this.changed = true;
        this.position = position;
        this.values = values;
        this.setString(string);
    }
    
    public DrawString(final String string, final int position, final Value value, final Value value2, final Value[] values) {
        this.offset = 3;
        this.clamp = true;
        this.justification = 0;
        this.numSize = 10;
        this.changed = true;
        this.setReferencePoint(value, value2);
        this.position = position;
        this.values = values;
        this.setString(string);
    }
    
    public void setColor(final Color color) {
        this.color = color;
        this.needsRedraw();
    }
    
    public Color getColor() {
        return (this.color == null) ? Color.black : this.color;
    }
    
    public void setFont(final Font font) {
        this.font = font;
        this.needsRedraw();
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public void setValues(final Value[] values) {
        this.values = values;
        this.changed = true;
        this.needsRedraw();
    }
    
    public Value[] getValues() {
        return this.values;
    }
    
    public void setPositioning(final int position) {
        this.position = position;
        this.needsRedraw();
    }
    
    public int getPositioning() {
        return this.position;
    }
    
    public void setReferencePoint(final Value xPos, final Value yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        try {
            if (this.xPos != null) {
                this.xRef = this.xPos.getVal();
            }
            if (this.yPos != null) {
                this.yRef = this.yPos.getVal();
            }
        }
        catch (RuntimeException ex) {}
        this.needsRedraw();
    }
    
    public Value getXPos() {
        return this.xPos;
    }
    
    public Value getYPos() {
        return this.yPos;
    }
    
    public void setString(final String baseString) {
        this.baseString = baseString;
        this.strings = null;
        this.changed = true;
        this.needsRedraw();
    }
    
    public String getString() {
        return this.baseString;
    }
    
    public void setOffset(final int offset) {
        this.offset = offset;
        this.needsRedraw();
    }
    
    public int getOffset() {
        return this.offset;
    }
    
    public void setClamp(final boolean clamp) {
        this.clamp = clamp;
        this.needsRedraw();
    }
    
    public boolean getClamp() {
        return this.clamp;
    }
    
    public void setJustification(final int justification) {
        if (justification == 2 || justification == 5) {
            this.justification = justification;
        }
        else {
            this.justification = 0;
        }
        this.needsRedraw();
    }
    
    public int getJustification() {
        return this.justification;
    }
    
    public void setNumSize(final int n) {
        this.numSize = Math.min(Math.max(n, 6), 25);
        this.changed = true;
        this.needsRedraw();
    }
    
    public int getNumSize() {
        return this.numSize;
    }
    
    public Color getBackgroundColor() {
        return this.backgroundColor;
    }
    
    public void setBackgroundColor(final Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.needsRedraw();
    }
    
    public Color getFrameColor() {
        return this.frameColor;
    }
    
    public void setFrameColor(final Color frameColor) {
        this.frameColor = frameColor;
        this.needsRedraw();
    }
    
    public int getFrameWidth() {
        return this.frameWidth;
    }
    
    public void setFrameWidth(final int frameWidth) {
        if (frameWidth < 0) {
            this.frameWidth = 0;
        }
        else if (frameWidth > 25) {
            this.frameWidth = 25;
        }
        else {
            this.frameWidth = frameWidth;
        }
        this.needsRedraw();
    }
    
    public void compute() {
        this.changed = true;
        this.needsRedraw();
    }
    
    private void getSubstitutedText() {
        this.changed = false;
        if (this.xPos != null) {
            this.xRef = this.xPos.getVal();
        }
        if (this.yPos != null) {
            this.yRef = this.yPos.getVal();
        }
        if (this.values == null && this.strings != null) {
            return;
        }
        if (this.baseString == null || this.baseString.trim().length() == 0) {
            this.strings = null;
            return;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(this.baseString, "\n");
        final int countTokens = stringTokenizer.countTokens();
        this.strings = new String[countTokens];
        if (this.values == null) {
            for (int i = 0; i < countTokens; ++i) {
                this.strings[i] = stringTokenizer.nextToken();
            }
            return;
        }
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        for (int j = 0; j < countTokens; ++j) {
            final String nextToken = stringTokenizer.nextToken();
            for (int k = 0; k < nextToken.length(); ++k) {
                if (nextToken.charAt(k) == '#') {
                    if (k != nextToken.length() - 1 && nextToken.charAt(k + 1) == '#') {
                        sb.append('#');
                        ++k;
                    }
                    else if (n < this.values.length) {
                        try {
                            sb.append(NumUtils.realToString(this.values[n].getVal(), this.numSize));
                        }
                        catch (RuntimeException ex) {
                            sb.append("(error)");
                        }
                        ++n;
                    }
                    else {
                        sb.append("undefined");
                    }
                }
                else {
                    sb.append(nextToken.charAt(k));
                }
            }
            this.strings[j] = sb.toString();
            sb.setLength(0);
        }
    }
    
    public void draw(final Graphics graphics, final boolean b) {
        if (this.changed) {
            this.getSubstitutedText();
        }
        if (this.strings == null) {
            return;
        }
        if (this.xPos != null && (Double.isNaN(this.xRef) || Double.isInfinite(this.xRef))) {
            return;
        }
        if (this.yPos != null && (Double.isNaN(this.yRef) || Double.isInfinite(this.yRef))) {
            return;
        }
        int offset = this.offset;
        if (this.backgroundColor != null || this.frameWidth > 0) {
            offset += 3;
        }
        final int n = offset + this.frameWidth;
        Font font = null;
        FontMetrics fontMetrics;
        if (this.font != null) {
            font = graphics.getFont();
            graphics.setFont(this.font);
            fontMetrics = graphics.getFontMetrics(this.font);
        }
        else {
            fontMetrics = graphics.getFontMetrics(graphics.getFont());
        }
        final int height = fontMetrics.getHeight();
        final int left = super.coords.getLeft();
        final int width = super.coords.getWidth();
        final int top = super.coords.getTop();
        final int height2 = super.coords.getHeight();
        final int n2 = left + width;
        final int n3 = top + height2;
        int max = 0;
        for (int i = 0; i < this.strings.length; ++i) {
            max = Math.max(max, fontMetrics.stringWidth(this.strings[i]));
        }
        int n4 = this.strings.length * height;
        if (this.backgroundColor == null && this.frameWidth <= 0) {
            n4 = n4 - fontMetrics.getLeading() - fontMetrics.getDescent();
        }
        int n5 = this.position % 4;
        int n6 = this.position / 4;
        if (this.position < 0 || n5 > 2 || n6 > 2) {
            n5 = 0;
            n6 = 0;
        }
        int n7;
        if (this.xPos == null) {
            if (n5 == 0) {
                n7 = left + n;
            }
            else if (n5 == 1) {
                n7 = (left + n2 - max) / 2;
            }
            else {
                n7 = n2 - max - n;
            }
        }
        else if (n5 == 0) {
            n7 = super.coords.xToPixel(this.xRef) + n;
        }
        else if (n5 == 1) {
            n7 = super.coords.xToPixel(this.xRef) - max / 2;
        }
        else {
            n7 = super.coords.xToPixel(this.xRef) - max - n;
        }
        int n8;
        if (this.yPos == null) {
            if (n6 == 0) {
                n8 = top + n;
            }
            else if (n6 == 1) {
                n8 = (top + n3 - n4) / 2;
            }
            else {
                n8 = n3 - n4 - n;
            }
        }
        else if (n6 == 0) {
            n8 = super.coords.yToPixel(this.yRef) + n;
        }
        else if (n6 == 1) {
            n8 = super.coords.yToPixel(this.yRef) - n4 / 2;
        }
        else {
            n8 = super.coords.yToPixel(this.yRef) - n4 - n;
        }
        if (this.clamp) {
            if (n7 + max > n2) {
                n7 = n2 - max;
            }
            if (n7 < left) {
                n7 = left;
            }
            if (n8 + n4 > n3) {
                n8 = n3 - n4;
            }
            if (n8 < top) {
                n8 = top;
            }
        }
        if (this.backgroundColor != null) {
            graphics.setColor(this.backgroundColor);
            graphics.fillRect(n7 - 3, n8 - 3, max + 6, n4 + 6);
        }
        if (this.frameWidth > 0) {
            if (this.frameColor != null) {
                graphics.setColor(this.frameColor);
            }
            else if (this.color != null) {
                graphics.setColor(this.color);
            }
            else {
                graphics.setColor(Color.black);
            }
            for (int j = 1; j <= this.frameWidth; ++j) {
                graphics.drawRect(n7 - 3 - j, n8 - 3 - j, max + 5 + 2 * j, n4 + 5 + 2 * j);
            }
        }
        if (this.color != null) {
            graphics.setColor(this.color);
        }
        else {
            graphics.setColor(Color.black);
        }
        int n9 = n8 + fontMetrics.getAscent();
        for (int k = 0; k < this.strings.length; ++k) {
            int n10 = n7;
            if (this.justification == 5) {
                n10 += (max - fontMetrics.stringWidth(this.strings[k])) / 2;
            }
            else if (this.justification == 2) {
                n10 = n10 + max - fontMetrics.stringWidth(this.strings[k]);
            }
            graphics.drawString(this.strings[k], n10, n9);
            n9 += height;
        }
        if (font != null) {
            graphics.setFont(font);
        }
    }
}
