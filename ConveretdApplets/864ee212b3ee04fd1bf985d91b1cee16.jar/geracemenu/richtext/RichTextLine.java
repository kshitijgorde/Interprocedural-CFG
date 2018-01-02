// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

import java.awt.Component;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Dimension;
import geracemenu.TTComponent;
import geracemenu.util.VArray;

public class RichTextLine extends RichTextToken
{
    private static /* synthetic */ Class class$Lgeracemenu$richtext$RichTextToken;
    private VArray tokens;
    private TTComponent richTextContainer;
    private int alignment;
    private int maxAscent;
    
    public RichTextLine append(final RichTextToken richTextToken) {
        this.tokens.append(richTextToken);
        return (RichTextLine)(richTextToken.container = this);
    }
    
    public final TTComponent getContainer() {
        return this.richTextContainer;
    }
    
    public Dimension getSize() {
        if (super.size == null) {
            int max = 0;
            int n = 0;
            for (int i = 0; i < this.tokens.length(); ++i) {
                final RichTextToken richTextToken = (RichTextToken)this.tokens.get(i);
                max = Math.max(max, richTextToken.getSize().height);
                this.maxAscent = Math.max(this.maxAscent, richTextToken.getAscent());
                n += richTextToken.getSize().width;
            }
            super.size = new Dimension(n, max);
        }
        return super.size;
    }
    
    protected int getAscent() {
        return this.maxAscent;
    }
    
    public void paint(final Graphics graphics, final Point point) {
        final Dimension size = this.richTextContainer.getSize();
        final Insets insets = this.richTextContainer.getInsets();
        int x = point.x;
        switch (this.alignment) {
            case 1: {
                x += Math.max(size.width - insets.left - insets.right - super.size.width, 0) / 2;
                break;
            }
            case 3: {
                x += Math.max(size.width - insets.left - insets.right - super.size.width, 0);
                break;
            }
        }
        for (int i = 0; i < this.tokens.size(); ++i) {
            final RichTextToken richTextToken = (RichTextToken)this.tokens.get(i);
            richTextToken.paint(graphics, new Point(x, point.y));
            x += richTextToken.getSize().width;
        }
    }
    
    public void setAlignment(final int alignment) {
        switch (alignment) {
            case 1:
            case 2:
            case 3: {
                this.alignment = alignment;
            }
            default: {
                throw new IllegalArgumentException("RichTextLine, setAlignment: unsupported alignment type: " + alignment);
            }
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public RichTextLine(final Component component, final int alignment) {
        super(null);
        this.maxAscent = 0;
        this.tokens = new VArray((RichTextLine.class$Lgeracemenu$richtext$RichTextToken != null) ? RichTextLine.class$Lgeracemenu$richtext$RichTextToken : (RichTextLine.class$Lgeracemenu$richtext$RichTextToken = class$("geracemenu.richtext.RichTextToken")));
        this.richTextContainer = (TTComponent)component;
        this.alignment = alignment;
    }
}
