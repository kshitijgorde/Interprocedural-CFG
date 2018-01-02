// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.title;

import java.awt.Color;
import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.PaintUtilities;
import org.jfree.util.ObjectUtilities;
import org.jfree.text.TextBlockAnchor;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.block.BlockResult;
import java.awt.Shape;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.block.EntityBlockParams;
import java.awt.geom.Rectangle2D;
import org.jfree.text.TextMeasurer;
import org.jfree.text.TextUtilities;
import org.jfree.text.G2TextMeasurer;
import org.jfree.data.Range;
import org.jfree.chart.block.LengthConstraintType;
import org.jfree.ui.Size2D;
import org.jfree.chart.block.RectangleConstraint;
import java.awt.Graphics2D;
import org.jfree.chart.event.TitleChangeEvent;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.VerticalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.text.TextBlock;
import org.jfree.ui.HorizontalAlignment;
import java.awt.Paint;
import java.awt.Font;
import org.jfree.util.PublicCloneable;
import java.io.Serializable;

public class TextTitle extends Title implements Serializable, Cloneable, PublicCloneable
{
    private static final long serialVersionUID = 8372008692127477443L;
    public static final Font DEFAULT_FONT;
    public static final Paint DEFAULT_TEXT_PAINT;
    private String text;
    private Font font;
    private HorizontalAlignment textAlignment;
    private transient Paint paint;
    private transient Paint backgroundPaint;
    private String toolTipText;
    private String urlText;
    private TextBlock content;
    private boolean expandToFitSpace;
    
    public TextTitle() {
        this("");
    }
    
    public TextTitle(final String text) {
        this(text, TextTitle.DEFAULT_FONT, TextTitle.DEFAULT_TEXT_PAINT, Title.DEFAULT_POSITION, Title.DEFAULT_HORIZONTAL_ALIGNMENT, Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_PADDING);
    }
    
    public TextTitle(final String text, final Font font) {
        this(text, font, TextTitle.DEFAULT_TEXT_PAINT, Title.DEFAULT_POSITION, Title.DEFAULT_HORIZONTAL_ALIGNMENT, Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_PADDING);
    }
    
    public TextTitle(final String text, final Font font, final Paint paint, final RectangleEdge position, final HorizontalAlignment horizontalAlignment, final VerticalAlignment verticalAlignment, final RectangleInsets padding) {
        super(position, horizontalAlignment, verticalAlignment, padding);
        this.expandToFitSpace = false;
        if (text == null) {
            throw new NullPointerException("Null 'text' argument.");
        }
        if (font == null) {
            throw new NullPointerException("Null 'font' argument.");
        }
        if (paint == null) {
            throw new NullPointerException("Null 'paint' argument.");
        }
        this.text = text;
        this.font = font;
        this.paint = paint;
        this.textAlignment = horizontalAlignment;
        this.backgroundPaint = null;
        this.content = null;
        this.toolTipText = null;
        this.urlText = null;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(final String text) {
        if (text == null) {
            throw new IllegalArgumentException("Null 'text' argument.");
        }
        if (!this.text.equals(text)) {
            this.text = text;
            this.notifyListeners(new TitleChangeEvent(this));
        }
    }
    
    public HorizontalAlignment getTextAlignment() {
        return this.textAlignment;
    }
    
    public void setTextAlignment(final HorizontalAlignment alignment) {
        if (alignment == null) {
            throw new IllegalArgumentException("Null 'alignment' argument.");
        }
        this.textAlignment = alignment;
        this.notifyListeners(new TitleChangeEvent(this));
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public void setFont(final Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
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
            throw new IllegalArgumentException("Null 'paint' argument.");
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
    
    public String getToolTipText() {
        return this.toolTipText;
    }
    
    public void setToolTipText(final String text) {
        this.toolTipText = text;
        this.notifyListeners(new TitleChangeEvent(this));
    }
    
    public String getURLText() {
        return this.urlText;
    }
    
    public void setURLText(final String text) {
        this.urlText = text;
        this.notifyListeners(new TitleChangeEvent(this));
    }
    
    public boolean getExpandToFitSpace() {
        return this.expandToFitSpace;
    }
    
    public void setExpandToFitSpace(final boolean expand) {
        this.expandToFitSpace = expand;
        this.notifyListeners(new TitleChangeEvent(this));
    }
    
    public Size2D arrange(final Graphics2D g2, final RectangleConstraint constraint) {
        final RectangleConstraint cc = this.toContentConstraint(constraint);
        final LengthConstraintType w = cc.getWidthConstraintType();
        final LengthConstraintType h = cc.getHeightConstraintType();
        Size2D contentSize = null;
        if (w == LengthConstraintType.NONE) {
            if (h == LengthConstraintType.NONE) {
                throw new RuntimeException("Not yet implemented.");
            }
            if (h == LengthConstraintType.RANGE) {
                throw new RuntimeException("Not yet implemented.");
            }
            if (h == LengthConstraintType.FIXED) {
                throw new RuntimeException("Not yet implemented.");
            }
        }
        else if (w == LengthConstraintType.RANGE) {
            if (h == LengthConstraintType.NONE) {
                throw new RuntimeException("Not yet implemented.");
            }
            if (h == LengthConstraintType.RANGE) {
                contentSize = this.arrangeRR(g2, cc.getWidthRange(), cc.getHeightRange());
            }
            else if (h == LengthConstraintType.FIXED) {
                throw new RuntimeException("Not yet implemented.");
            }
        }
        else if (w == LengthConstraintType.FIXED) {
            if (h == LengthConstraintType.NONE) {
                throw new RuntimeException("Not yet implemented.");
            }
            if (h == LengthConstraintType.RANGE) {
                throw new RuntimeException("Not yet implemented.");
            }
            if (h == LengthConstraintType.FIXED) {
                throw new RuntimeException("Not yet implemented.");
            }
        }
        return new Size2D(this.calculateTotalWidth(contentSize.getWidth()), this.calculateTotalHeight(contentSize.getHeight()));
    }
    
    protected Size2D arrangeRR(final Graphics2D g2, final Range widthRange, final Range heightRange) {
        final RectangleEdge position = this.getPosition();
        if (position == RectangleEdge.TOP || position == RectangleEdge.BOTTOM) {
            final float maxWidth = (float)widthRange.getUpperBound();
            g2.setFont(this.font);
            (this.content = TextUtilities.createTextBlock(this.text, this.font, this.paint, maxWidth, new G2TextMeasurer(g2))).setLineAlignment(this.textAlignment);
            final Size2D contentSize = this.content.calculateDimensions(g2);
            if (this.expandToFitSpace) {
                return new Size2D(maxWidth, contentSize.getHeight());
            }
            return contentSize;
        }
        else {
            if (position != RectangleEdge.LEFT && position != RectangleEdge.RIGHT) {
                throw new RuntimeException("Unrecognised exception.");
            }
            final float maxWidth = (float)heightRange.getUpperBound();
            g2.setFont(this.font);
            (this.content = TextUtilities.createTextBlock(this.text, this.font, this.paint, maxWidth, new G2TextMeasurer(g2))).setLineAlignment(this.textAlignment);
            final Size2D contentSize = this.content.calculateDimensions(g2);
            if (this.expandToFitSpace) {
                return new Size2D(contentSize.getHeight(), maxWidth);
            }
            return new Size2D(contentSize.height, contentSize.width);
        }
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D area) {
        this.draw(g2, area, null);
    }
    
    public Object draw(final Graphics2D g2, Rectangle2D area, final Object params) {
        if (this.content == null) {
            return null;
        }
        area = this.trimMargin(area);
        this.drawBorder(g2, area);
        if (this.text.equals("")) {
            return null;
        }
        ChartEntity entity = null;
        if (params instanceof EntityBlockParams) {
            final EntityBlockParams p = (EntityBlockParams)params;
            if (p.getGenerateEntities()) {
                entity = new ChartEntity(area, this.toolTipText, this.urlText);
            }
        }
        area = this.trimBorder(area);
        if (this.backgroundPaint != null) {
            g2.setPaint(this.backgroundPaint);
            g2.fill(area);
        }
        area = this.trimPadding(area);
        final RectangleEdge position = this.getPosition();
        if (position == RectangleEdge.TOP || position == RectangleEdge.BOTTOM) {
            this.drawHorizontal(g2, area);
        }
        else if (position == RectangleEdge.LEFT || position == RectangleEdge.RIGHT) {
            this.drawVertical(g2, area);
        }
        final BlockResult result = new BlockResult();
        if (entity != null) {
            final StandardEntityCollection sec = new StandardEntityCollection();
            sec.add(entity);
            result.setEntityCollection(sec);
        }
        return result;
    }
    
    protected void drawHorizontal(final Graphics2D g2, final Rectangle2D area) {
        final Rectangle2D titleArea = (Rectangle2D)area.clone();
        g2.setFont(this.font);
        g2.setPaint(this.paint);
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
        this.content.draw(g2, x, y, anchor);
    }
    
    protected void drawVertical(final Graphics2D g2, final Rectangle2D area) {
        final Rectangle2D titleArea = (Rectangle2D)area.clone();
        g2.setFont(this.font);
        g2.setPaint(this.paint);
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
        this.content.draw(g2, x, y, anchor, x, y, -1.5707963267948966);
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TextTitle)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final TextTitle that = (TextTitle)obj;
        return ObjectUtilities.equal(this.text, that.text) && ObjectUtilities.equal(this.font, that.font) && PaintUtilities.equal(this.paint, that.paint) && this.textAlignment == that.textAlignment && PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint);
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
    
    static {
        DEFAULT_FONT = new Font("SansSerif", 1, 12);
        DEFAULT_TEXT_PAINT = Color.black;
    }
}
