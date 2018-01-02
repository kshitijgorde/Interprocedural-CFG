// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.text;

import org.jfree.util.Log;
import org.jfree.ui.TextAnchor;
import org.jfree.util.ShapeUtils;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import java.util.Iterator;
import org.jfree.ui.Size2D;
import java.awt.Graphics2D;
import java.util.Collections;
import java.awt.Paint;
import java.awt.Font;
import java.util.ArrayList;
import org.jfree.util.LogContext;
import java.util.List;
import java.io.Serializable;

public class TextBlock implements Serializable
{
    private List lines;
    protected static final LogContext logger;
    static /* synthetic */ Class class$org$jfree$text$TextBlock;
    
    public TextBlock() {
        this.lines = new ArrayList();
    }
    
    public void addLine(final String s, final Font font, final Paint paint) {
        this.addLine(new TextLine(s, font, paint));
    }
    
    public void addLine(final TextLine textLine) {
        this.lines.add(textLine);
    }
    
    public TextLine getLastLine() {
        TextLine textLine = null;
        final int n = this.lines.size() - 1;
        if (n >= 0) {
            textLine = (TextLine)this.lines.get(n);
        }
        return textLine;
    }
    
    public List getLines() {
        return Collections.unmodifiableList((List<?>)this.lines);
    }
    
    public Size2D calculateDimensions(final Graphics2D graphics2D) {
        double max = 0.0;
        double n = 0.0;
        final Iterator<TextLine> iterator = this.lines.iterator();
        while (iterator.hasNext()) {
            final Size2D calculateDimensions = iterator.next().calculateDimensions(graphics2D);
            max = Math.max(max, calculateDimensions.getWidth());
            n += calculateDimensions.getHeight();
        }
        if (TextBlock.logger.isDebugEnabled()) {
            TextBlock.logger.debug("width = " + max + ", height = " + n);
        }
        return new Size2D(max, n);
    }
    
    public Shape calculateBounds(final Graphics2D graphics2D, final float n, final float n2, final TextBlockAnchor textBlockAnchor, final float n3, final float n4, final double n5) {
        final Size2D calculateDimensions = this.calculateDimensions(graphics2D);
        final float[] calculateOffsets = this.calculateOffsets(textBlockAnchor, calculateDimensions.getWidth(), calculateDimensions.getHeight());
        return ShapeUtils.rotateShape(new Rectangle2D.Double(n + calculateOffsets[0], n2 + calculateOffsets[1], calculateDimensions.getWidth(), calculateDimensions.getHeight()), n5, n3, n4);
    }
    
    public void draw(final Graphics2D graphics2D, final float n, final float n2, final TextBlockAnchor textBlockAnchor) {
        this.draw(graphics2D, n, n2, textBlockAnchor, 0.0f, 0.0f, 0.0);
    }
    
    public void draw(final Graphics2D graphics2D, final float n, final float n2, final TextBlockAnchor textBlockAnchor, final float n3, final float n4, final double n5) {
        final Size2D calculateDimensions = this.calculateDimensions(graphics2D);
        final float[] calculateOffsets = this.calculateOffsets(textBlockAnchor, calculateDimensions.getWidth(), calculateDimensions.getHeight());
        final Iterator<TextLine> iterator = this.lines.iterator();
        float n6 = 0.0f;
        while (iterator.hasNext()) {
            final TextLine textLine = iterator.next();
            textLine.draw(graphics2D, n + calculateOffsets[0], n2 + calculateOffsets[1] + n6, TextAnchor.TOP_LEFT, n3, n4, n5);
            n6 += (float)textLine.calculateDimensions(graphics2D).getHeight();
        }
    }
    
    private float[] calculateOffsets(final TextBlockAnchor textBlockAnchor, final double n, final double n2) {
        final float[] array = new float[2];
        float n3 = 0.0f;
        float n4 = 0.0f;
        if (textBlockAnchor == TextBlockAnchor.TOP_CENTER || textBlockAnchor == TextBlockAnchor.CENTER || textBlockAnchor == TextBlockAnchor.BOTTOM_CENTER) {
            n3 = (float)(-n) / 2.0f;
        }
        else if (textBlockAnchor == TextBlockAnchor.TOP_RIGHT || textBlockAnchor == TextBlockAnchor.CENTER_RIGHT || textBlockAnchor == TextBlockAnchor.BOTTOM_RIGHT) {
            n3 = (float)(-n);
        }
        if (textBlockAnchor == TextBlockAnchor.TOP_LEFT || textBlockAnchor == TextBlockAnchor.TOP_CENTER || textBlockAnchor == TextBlockAnchor.TOP_RIGHT) {
            n4 = 0.0f;
        }
        else if (textBlockAnchor == TextBlockAnchor.CENTER_LEFT || textBlockAnchor == TextBlockAnchor.CENTER || textBlockAnchor == TextBlockAnchor.CENTER_RIGHT) {
            n4 = (float)(-n2) / 2.0f;
        }
        else if (textBlockAnchor == TextBlockAnchor.BOTTOM_LEFT || textBlockAnchor == TextBlockAnchor.BOTTOM_CENTER || textBlockAnchor == TextBlockAnchor.BOTTOM_RIGHT) {
            n4 = (float)(-n2);
        }
        array[0] = n3;
        array[1] = n4;
        return array;
    }
    
    public boolean equals(final Object o) {
        return o != null && (o == this || (o instanceof TextBlock && this.lines.equals(((TextBlock)o).lines)));
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        logger = Log.createContext((TextBlock.class$org$jfree$text$TextBlock == null) ? (TextBlock.class$org$jfree$text$TextBlock = class$("org.jfree.text.TextBlock")) : TextBlock.class$org$jfree$text$TextBlock);
    }
}
