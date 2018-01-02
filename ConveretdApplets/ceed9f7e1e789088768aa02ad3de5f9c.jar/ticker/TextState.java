// 
// Decompiled by Procyon v0.5.30
// 

package ticker;

import java.awt.Graphics;
import java.awt.Font;

public class TextState
{
    Font f;
    StringBuffer s;
    int x;
    int y;
    
    public TextState() {
        this.f = null;
        this.s = null;
        this.x = 0;
        this.y = 0;
        this.s = new StringBuffer();
    }
    
    public TextState copyAll() {
        final TextState copyState = this.copyState();
        if (this.s.length() == 0) {
            return copyState;
        }
        for (int i = 0; i < this.s.length(); ++i) {
            copyState.s.append(this.s.charAt(i));
        }
        return copyState;
    }
    
    public TextState copyState() {
        final TextState textState = new TextState();
        textState.f = this.f;
        textState.x = this.x;
        textState.y = this.y;
        return textState;
    }
    
    public String toString() {
        return this.s.toString();
    }
    
    public boolean isEmpty() {
        return this.s.length() == 0;
    }
    
    public int getWidth(final Graphics graphics) {
        if (graphics == null || this.f == null || this.s.length() == 0) {
            return 0;
        }
        return graphics.getFontMetrics(this.f).stringWidth(this.s.toString());
    }
    
    public int getHeight(final Graphics graphics) {
        if (graphics == null || this.f == null) {
            return 0;
        }
        return graphics.getFontMetrics(this.f).getHeight();
    }
    
    public int getAscent(final Graphics graphics) {
        if (graphics == null || this.f == null) {
            return 0;
        }
        return graphics.getFontMetrics(this.f).getAscent();
    }
    
    public int getDescent(final Graphics graphics) {
        if (graphics == null || this.f == null) {
            return 0;
        }
        return graphics.getFontMetrics(this.f).getDescent();
    }
    
    public int getMaxAscent(final Graphics graphics) {
        if (graphics == null || this.f == null) {
            return 0;
        }
        return graphics.getFontMetrics(this.f).getMaxAscent();
    }
    
    public int getMaxDescent(final Graphics graphics) {
        if (graphics == null || this.f == null) {
            return 0;
        }
        return graphics.getFontMetrics(this.f).getMaxDescent();
    }
    
    public int getLeading(final Graphics graphics) {
        if (graphics == null || this.f == null) {
            return 0;
        }
        return graphics.getFontMetrics(this.f).getLeading();
    }
}
