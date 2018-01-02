// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.title;

import org.jfree.ui.Size2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.event.TitleChangeEvent;
import org.jfree.ui.Spacer;
import org.jfree.ui.VerticalAlignment;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import java.awt.image.ImageObserver;
import java.awt.Image;

public class ImageTitle extends Title
{
    private Image image;
    private int height;
    private int width;
    
    public ImageTitle(final Image image) {
        this(image, image.getHeight(null), image.getWidth(null), Title.DEFAULT_POSITION, Title.DEFAULT_HORIZONTAL_ALIGNMENT, Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_SPACER);
    }
    
    public ImageTitle(final Image image, final RectangleEdge position, final HorizontalAlignment horizontalAlignment, final VerticalAlignment verticalAlignment) {
        this(image, image.getHeight(null), image.getWidth(null), position, horizontalAlignment, verticalAlignment, Title.DEFAULT_SPACER);
    }
    
    public ImageTitle(final Image image, final int height, final int width, final RectangleEdge position, final HorizontalAlignment horizontalAlignment, final VerticalAlignment verticalAlignment, final Spacer spacer) {
        super(position, horizontalAlignment, verticalAlignment, spacer);
        if (image == null) {
            throw new NullPointerException("ImageTitle(..): Image argument is null.");
        }
        this.image = image;
        this.height = height;
        this.width = width;
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public void setImage(final Image image) {
        if (image == null) {
            throw new NullPointerException("ImageTitle.setImage (..): Image must not be null.");
        }
        this.image = image;
        this.notifyListeners(new TitleChangeEvent(this));
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D titleArea) {
        final RectangleEdge position = this.getPosition();
        if (position == RectangleEdge.TOP || position == RectangleEdge.BOTTOM) {
            this.drawHorizontal(g2, titleArea);
        }
        else {
            if (position != RectangleEdge.LEFT && position != RectangleEdge.RIGHT) {
                throw new RuntimeException("ImageTitle.draw(...) - invalid title position.");
            }
            this.drawVertical(g2, titleArea);
        }
    }
    
    public float getPreferredWidth(final Graphics2D g2, final float height) {
        final Spacer spacer = this.getSpacer();
        final float result = (float)spacer.getAdjustedWidth(this.width);
        return result;
    }
    
    public float getPreferredHeight(final Graphics2D g2, final float width) {
        final Spacer spacer = this.getSpacer();
        final float result = (float)spacer.getAdjustedHeight(this.height);
        return result;
    }
    
    protected Size2D drawHorizontal(final Graphics2D g2, final Rectangle2D chartArea) {
        double startY = 0.0;
        double topSpace = 0.0;
        double bottomSpace = 0.0;
        double leftSpace = 0.0;
        double rightSpace = 0.0;
        final Spacer spacer = this.getSpacer();
        topSpace = spacer.getTopSpace(this.height);
        bottomSpace = spacer.getBottomSpace(this.height);
        leftSpace = spacer.getLeftSpace(this.width);
        rightSpace = spacer.getRightSpace(this.width);
        if (this.getPosition() == RectangleEdge.TOP) {
            startY = chartArea.getY() + topSpace;
        }
        else {
            startY = chartArea.getY() + chartArea.getHeight() - bottomSpace - this.height;
        }
        final HorizontalAlignment horizontalAlignment = this.getHorizontalAlignment();
        double startX = 0.0;
        if (horizontalAlignment == HorizontalAlignment.CENTER) {
            startX = chartArea.getX() + leftSpace + chartArea.getWidth() / 2.0 - this.width / 2;
        }
        else if (horizontalAlignment == HorizontalAlignment.LEFT) {
            startX = chartArea.getX() + leftSpace;
        }
        else if (horizontalAlignment == HorizontalAlignment.RIGHT) {
            startX = chartArea.getX() + chartArea.getWidth() - rightSpace - this.width;
        }
        g2.drawImage(this.image, (int)startX, (int)startY, this.width, this.height, null);
        return new Size2D(chartArea.getWidth() + leftSpace + rightSpace, this.height + topSpace + bottomSpace);
    }
    
    protected Size2D drawVertical(final Graphics2D g2, final Rectangle2D chartArea) {
        double startX = 0.0;
        double topSpace = 0.0;
        double bottomSpace = 0.0;
        double leftSpace = 0.0;
        double rightSpace = 0.0;
        final Spacer spacer = this.getSpacer();
        if (spacer != null) {
            topSpace = spacer.getTopSpace(this.height);
            bottomSpace = spacer.getBottomSpace(this.height);
            leftSpace = spacer.getLeftSpace(this.width);
            rightSpace = spacer.getRightSpace(this.width);
        }
        if (this.getPosition() == RectangleEdge.LEFT) {
            startX = chartArea.getX() + leftSpace;
        }
        else {
            startX = chartArea.getMaxX() - rightSpace - this.width;
        }
        final VerticalAlignment alignment = this.getVerticalAlignment();
        double startY = 0.0;
        if (alignment == VerticalAlignment.CENTER) {
            startY = chartArea.getMinY() + topSpace + chartArea.getHeight() / 2.0 - this.height / 2;
        }
        else if (alignment == VerticalAlignment.TOP) {
            startY = chartArea.getMinY() + topSpace;
        }
        else if (alignment == VerticalAlignment.BOTTOM) {
            startY = chartArea.getMaxY() - bottomSpace - this.height;
        }
        g2.drawImage(this.image, (int)startX, (int)startY, this.width, this.height, null);
        return new Size2D(chartArea.getWidth() + leftSpace + rightSpace, this.height + topSpace + bottomSpace);
    }
}
