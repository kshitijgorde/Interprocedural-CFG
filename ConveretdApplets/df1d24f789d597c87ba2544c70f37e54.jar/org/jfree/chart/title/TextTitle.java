// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.title;

import org.jfree.util.Log;
import java.awt.Color;
import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.ObjectUtils;
import org.jfree.text.TextBlockAnchor;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import org.jfree.ui.Size2D;
import org.jfree.text.TextBlock;
import org.jfree.text.TextMeasurer;
import org.jfree.text.TextUtilities;
import org.jfree.text.G2TextMeasurer;
import java.awt.Graphics2D;
import org.jfree.chart.event.TitleChangeEvent;
import org.jfree.ui.Spacer;
import org.jfree.ui.VerticalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.util.LogContext;
import java.awt.Paint;
import java.awt.Font;
import java.io.Serializable;

public class TextTitle extends Title implements Serializable, Cloneable
{
    public static final Font DEFAULT_FONT;
    public static final Paint DEFAULT_TEXT_PAINT;
    private String text;
    private Font font;
    private transient Paint paint;
    private transient Paint backgroundPaint;
    protected static final LogContext logger;
    static /* synthetic */ Class class$org$jfree$chart$title$TextTitle;
    
    public TextTitle() {
        this("");
    }
    
    public TextTitle(final String text) {
        this(text, TextTitle.DEFAULT_FONT, TextTitle.DEFAULT_TEXT_PAINT, Title.DEFAULT_POSITION, Title.DEFAULT_HORIZONTAL_ALIGNMENT, Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_SPACER);
    }
    
    public TextTitle(final String text, final Font font) {
        this(text, font, TextTitle.DEFAULT_TEXT_PAINT, Title.DEFAULT_POSITION, Title.DEFAULT_HORIZONTAL_ALIGNMENT, Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_SPACER);
    }
    
    public TextTitle(final String text, final Font font, final Paint paint) {
        this(text, font, paint, Title.DEFAULT_POSITION, Title.DEFAULT_HORIZONTAL_ALIGNMENT, Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_SPACER);
    }
    
    public TextTitle(final String text, final Font font, final HorizontalAlignment horizontalAlignment) {
        this(text, font, TextTitle.DEFAULT_TEXT_PAINT, Title.DEFAULT_POSITION, horizontalAlignment, Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_SPACER);
    }
    
    public TextTitle(final String text, final Font font, final Paint paint, final RectangleEdge position, final HorizontalAlignment horizontalAlignment, final VerticalAlignment verticalAlignment, final Spacer spacer) {
        super(position, horizontalAlignment, verticalAlignment, spacer);
        if (text == null) {
            throw new NullPointerException("TextTitle(..): Text is null");
        }
        if (font == null) {
            throw new NullPointerException("TextTitle(..): Font is null");
        }
        if (paint == null) {
            throw new NullPointerException("TextTitle(..): Paint is null");
        }
        this.text = text;
        this.font = font;
        this.paint = paint;
        this.backgroundPaint = null;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(final String text) {
        if (text == null) {
            throw new NullPointerException("TextTitle.setText(..): Text is null");
        }
        if (!this.text.equals(text)) {
            this.text = text;
            this.notifyListeners(new TitleChangeEvent(this));
        }
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public void setFont(final Font font) {
        if (font == null) {
            throw new IllegalArgumentException("TextTitle.setFont(...): null font not permitted.");
        }
        if (!this.font.equals(font)) {
            this.font = font;
            this.notifyListeners(new TitleChangeEvent(this));
        }
    }
    
    public Paint getPaint() {
        return this.paint;
    }
    
    public void setPaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("TextTitle.setPaint(...): null paint not permitted.");
        }
        if (!this.paint.equals(paint)) {
            this.paint = paint;
            this.notifyListeners(new TitleChangeEvent(this));
        }
    }
    
    public Paint getBackgroundPaint() {
        return this.backgroundPaint;
    }
    
    public void setBackgroundPaint(final Paint paint) {
        this.backgroundPaint = paint;
        this.notifyListeners(new TitleChangeEvent(this));
    }
    
    public float getPreferredWidth(final Graphics2D g2, final float height) {
        float result = 0.0f;
        if (this.text != null && !this.text.equals("")) {
            g2.setFont(this.font);
            final TextBlock title = TextUtilities.createTextBlock(this.text, this.font, this.paint, height, new G2TextMeasurer(g2));
            final Size2D d = title.calculateDimensions(g2);
            result = (float)this.getSpacer().getAdjustedWidth(d.getHeight());
        }
        if (TextTitle.logger.isDebugEnabled()) {
            TextTitle.logger.debug("Title preferred width = " + result);
        }
        return result;
    }
    
    public float getPreferredHeight(final Graphics2D g2, final float width) {
        float result = 0.0f;
        if (this.text != null && !this.text.equals("")) {
            g2.setFont(this.font);
            final float textWidth = (float)this.getSpacer().trimWidth(width);
            final TextBlock title = TextUtilities.createTextBlock(this.text, this.font, this.paint, textWidth, new G2TextMeasurer(g2));
            final Size2D d = title.calculateDimensions(g2);
            result = (float)this.getSpacer().getAdjustedHeight(d.getHeight());
        }
        if (TextTitle.logger.isDebugEnabled()) {
            TextTitle.logger.debug("Title preferred height = " + result);
        }
        return result;
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D area) {
        if (TextTitle.logger.isDebugEnabled()) {
            TextTitle.logger.debug("Drawing title to area " + area.toString());
        }
        if (this.text.equals("")) {
            return;
        }
        if (this.backgroundPaint != null) {
            g2.setPaint(this.backgroundPaint);
            g2.fill(area);
        }
        final RectangleEdge position = this.getPosition();
        if (position == RectangleEdge.TOP || position == RectangleEdge.BOTTOM) {
            this.drawHorizontal(g2, area);
        }
        else if (position == RectangleEdge.LEFT || position == RectangleEdge.RIGHT) {
            this.drawVertical(g2, area);
        }
    }
    
    protected void drawHorizontal(final Graphics2D g2, final Rectangle2D area) {
        final Rectangle2D titleArea = (Rectangle2D)area.clone();
        this.getSpacer().trim(titleArea);
        g2.setFont(this.font);
        g2.setPaint(this.paint);
        final TextBlock title = TextUtilities.createTextBlock(this.text, this.font, this.paint, (float)titleArea.getWidth(), new G2TextMeasurer(g2));
        TextBlockAnchor anchor = null;
        float x = 0.0f;
        final HorizontalAlignment horizontalAlignment = this.getHorizontalAlignment();
        if (horizontalAlignment == HorizontalAlignment.LEFT) {
            x = (float)titleArea.getX();
            anchor = TextBlockAnchor.TOP_LEFT;
        }
        else if (horizontalAlignment == HorizontalAlignment.RIGHT) {
            x = (float)titleArea.getMaxX();
            anchor = TextBlockAnchor.TOP_RIGHT;
        }
        else if (horizontalAlignment == HorizontalAlignment.CENTER) {
            x = (float)titleArea.getCenterX();
            anchor = TextBlockAnchor.TOP_CENTER;
        }
        float y = 0.0f;
        final RectangleEdge position = this.getPosition();
        if (position == RectangleEdge.TOP) {
            y = (float)titleArea.getY();
        }
        else if (position == RectangleEdge.BOTTOM) {
            y = (float)titleArea.getMaxY();
            if (horizontalAlignment == HorizontalAlignment.LEFT) {
                anchor = TextBlockAnchor.BOTTOM_LEFT;
            }
            else if (horizontalAlignment == HorizontalAlignment.CENTER) {
                anchor = TextBlockAnchor.BOTTOM_CENTER;
            }
            else if (horizontalAlignment == HorizontalAlignment.RIGHT) {
                anchor = TextBlockAnchor.BOTTOM_RIGHT;
            }
        }
        title.draw(g2, x, y, anchor);
    }
    
    protected void drawVertical(final Graphics2D g2, final Rectangle2D area) {
        final Rectangle2D titleArea = (Rectangle2D)area.clone();
        this.getSpacer().trim(titleArea);
        g2.setFont(this.font);
        g2.setPaint(this.paint);
        final TextBlock title = TextUtilities.createTextBlock(this.text, this.font, this.paint, (float)titleArea.getHeight(), new G2TextMeasurer(g2));
        TextBlockAnchor anchor = null;
        float y = 0.0f;
        final VerticalAlignment verticalAlignment = this.getVerticalAlignment();
        if (verticalAlignment == VerticalAlignment.TOP) {
            y = (float)titleArea.getY();
            anchor = TextBlockAnchor.TOP_RIGHT;
        }
        else if (verticalAlignment == VerticalAlignment.BOTTOM) {
            y = (float)titleArea.getMaxY();
            anchor = TextBlockAnchor.TOP_LEFT;
        }
        else if (verticalAlignment == VerticalAlignment.CENTER) {
            y = (float)titleArea.getCenterY();
            anchor = TextBlockAnchor.TOP_CENTER;
        }
        float x = 0.0f;
        final RectangleEdge position = this.getPosition();
        if (position == RectangleEdge.LEFT) {
            x = (float)titleArea.getX();
        }
        else if (position == RectangleEdge.RIGHT) {
            x = (float)titleArea.getMaxX();
            if (verticalAlignment == VerticalAlignment.TOP) {
                anchor = TextBlockAnchor.BOTTOM_RIGHT;
            }
            else if (verticalAlignment == VerticalAlignment.CENTER) {
                anchor = TextBlockAnchor.BOTTOM_CENTER;
            }
            else if (verticalAlignment == VerticalAlignment.BOTTOM) {
                anchor = TextBlockAnchor.BOTTOM_LEFT;
            }
        }
        title.draw(g2, x, y, anchor, x, y, -1.5707963267948966);
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof TextTitle) {
            final TextTitle t = (TextTitle)obj;
            if (super.equals(obj)) {
                return ObjectUtils.equal(this.text, t.text) && ObjectUtils.equal(this.font, t.font) && ObjectUtils.equal(this.paint, t.paint) && ObjectUtils.equal(this.backgroundPaint, t.backgroundPaint);
            }
        }
        return false;
    }
    
    public int hashCode() {
        int result = super.hashCode();
        result = 29 * result + ((this.text != null) ? this.text.hashCode() : 0);
        result = 29 * result + ((this.font != null) ? this.font.hashCode() : 0);
        result = 29 * result + ((this.paint != null) ? this.paint.hashCode() : 0);
        result = 29 * result + ((this.backgroundPaint != null) ? this.backgroundPaint.hashCode() : 0);
        return result;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.paint, stream);
        SerialUtilities.writePaint(this.backgroundPaint, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.paint = SerialUtilities.readPaint(stream);
        this.backgroundPaint = SerialUtilities.readPaint(stream);
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        DEFAULT_FONT = new Font("SansSerif", 1, 12);
        DEFAULT_TEXT_PAINT = Color.black;
        logger = Log.createContext((TextTitle.class$org$jfree$chart$title$TextTitle == null) ? (TextTitle.class$org$jfree$chart$title$TextTitle = class$("org.jfree.chart.title.TextTitle")) : TextTitle.class$org$jfree$chart$title$TextTitle);
    }
}
