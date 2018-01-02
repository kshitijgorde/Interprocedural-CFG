// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.render;

import java.awt.Paint;
import prefuse.util.ColorLib;
import prefuse.util.GraphicsLib;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.Shape;
import java.awt.FontMetrics;
import prefuse.util.StringLib;
import prefuse.util.FontLib;
import java.awt.Image;
import prefuse.visual.VisualItem;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.AffineTransform;

public class LabelRenderer extends AbstractShapeRenderer
{
    protected ImageFactory m_images;
    protected String m_delim;
    protected String m_labelName;
    protected String m_imageName;
    protected int m_xAlign;
    protected int m_yAlign;
    protected int m_hTextAlign;
    protected int m_vTextAlign;
    protected int m_hImageAlign;
    protected int m_vImageAlign;
    protected int m_imagePos;
    protected int m_horizBorder;
    protected int m_vertBorder;
    protected int m_imageMargin;
    protected int m_arcWidth;
    protected int m_arcHeight;
    protected int m_maxTextWidth;
    AffineTransform m_transform;
    protected RectangularShape m_bbox;
    protected Point2D m_pt;
    protected Font m_font;
    protected String m_text;
    protected Dimension m_textDim;
    
    public LabelRenderer() {
        this.m_images = null;
        this.m_delim = "\n";
        this.m_labelName = "label";
        this.m_imageName = null;
        this.m_xAlign = 2;
        this.m_yAlign = 2;
        this.m_hTextAlign = 2;
        this.m_vTextAlign = 2;
        this.m_hImageAlign = 2;
        this.m_vImageAlign = 2;
        this.m_imagePos = 0;
        this.m_horizBorder = 2;
        this.m_vertBorder = 0;
        this.m_imageMargin = 2;
        this.m_arcWidth = 0;
        this.m_arcHeight = 0;
        this.m_maxTextWidth = -1;
        this.m_transform = new AffineTransform();
        this.m_bbox = new Rectangle2D.Double();
        this.m_pt = new Point2D.Double();
        this.m_textDim = new Dimension();
    }
    
    public LabelRenderer(final String textField) {
        this.m_images = null;
        this.m_delim = "\n";
        this.m_labelName = "label";
        this.m_imageName = null;
        this.m_xAlign = 2;
        this.m_yAlign = 2;
        this.m_hTextAlign = 2;
        this.m_vTextAlign = 2;
        this.m_hImageAlign = 2;
        this.m_vImageAlign = 2;
        this.m_imagePos = 0;
        this.m_horizBorder = 2;
        this.m_vertBorder = 0;
        this.m_imageMargin = 2;
        this.m_arcWidth = 0;
        this.m_arcHeight = 0;
        this.m_maxTextWidth = -1;
        this.m_transform = new AffineTransform();
        this.m_bbox = new Rectangle2D.Double();
        this.m_pt = new Point2D.Double();
        this.m_textDim = new Dimension();
        this.setTextField(textField);
    }
    
    public LabelRenderer(final String textField, final String imageField) {
        this.m_images = null;
        this.m_delim = "\n";
        this.m_labelName = "label";
        this.m_imageName = null;
        this.m_xAlign = 2;
        this.m_yAlign = 2;
        this.m_hTextAlign = 2;
        this.m_vTextAlign = 2;
        this.m_hImageAlign = 2;
        this.m_vImageAlign = 2;
        this.m_imagePos = 0;
        this.m_horizBorder = 2;
        this.m_vertBorder = 0;
        this.m_imageMargin = 2;
        this.m_arcWidth = 0;
        this.m_arcHeight = 0;
        this.m_maxTextWidth = -1;
        this.m_transform = new AffineTransform();
        this.m_bbox = new Rectangle2D.Double();
        this.m_pt = new Point2D.Double();
        this.m_textDim = new Dimension();
        this.setTextField(textField);
        this.setImageField(imageField);
    }
    
    public void setRoundedCorner(final int arcWidth, final int arcHeight) {
        if ((arcWidth == 0 || arcHeight == 0) && !(this.m_bbox instanceof Rectangle2D)) {
            this.m_bbox = new Rectangle2D.Double();
        }
        else {
            if (!(this.m_bbox instanceof RoundRectangle2D)) {
                this.m_bbox = new RoundRectangle2D.Double();
            }
            ((RoundRectangle2D)this.m_bbox).setRoundRect(0.0, 0.0, 10.0, 10.0, arcWidth, arcHeight);
            this.m_arcWidth = arcWidth;
            this.m_arcHeight = arcHeight;
        }
    }
    
    public String getTextField() {
        return this.m_labelName;
    }
    
    public void setTextField(final String labelName) {
        this.m_labelName = labelName;
    }
    
    public void setMaxTextWidth(final int maxTextWidth) {
        this.m_maxTextWidth = maxTextWidth;
    }
    
    protected String getText(final VisualItem visualItem) {
        final String s = null;
        if (visualItem.canGetString(this.m_labelName)) {
            return visualItem.getString(this.m_labelName);
        }
        return s;
    }
    
    public String getImageField() {
        return this.m_imageName;
    }
    
    public void setImageField(final String imageName) {
        if (imageName != null) {
            this.m_images = new ImageFactory();
        }
        this.m_imageName = imageName;
    }
    
    public void setMaxImageDimensions(final int n, final int n2) {
        if (this.m_images == null) {
            this.m_images = new ImageFactory();
        }
        this.m_images.setMaxImageDimensions(n, n2);
    }
    
    protected String getImageLocation(final VisualItem visualItem) {
        return visualItem.canGetString(this.m_imageName) ? visualItem.getString(this.m_imageName) : null;
    }
    
    protected Image getImage(final VisualItem visualItem) {
        final String imageLocation = this.getImageLocation(visualItem);
        return (imageLocation == null) ? null : this.m_images.getImage(imageLocation);
    }
    
    private String computeTextDimensions(final VisualItem visualItem, final String s, final double n) {
        this.m_font = visualItem.getFont();
        if (n != 1.0) {
            this.m_font = FontLib.getFont(this.m_font.getName(), this.m_font.getStyle(), n * this.m_font.getSize());
        }
        final FontMetrics fontMetrics = LabelRenderer.DEFAULT_GRAPHICS.getFontMetrics(this.m_font);
        StringBuffer sb = null;
        int n2 = 1;
        int n3 = 0;
        int i = s.indexOf(this.m_delim);
        this.m_textDim.width = 0;
        while (i >= 0) {
            final String substring;
            int n4 = fontMetrics.stringWidth(substring = s.substring(n3, i));
            if (this.m_maxTextWidth > -1 && n4 > this.m_maxTextWidth) {
                if (sb == null) {
                    sb = new StringBuffer(s.substring(0, n3));
                }
                sb.append(StringLib.abbreviate(substring, fontMetrics, this.m_maxTextWidth));
                sb.append(this.m_delim);
                n4 = this.m_maxTextWidth;
            }
            else if (sb != null) {
                sb.append(substring).append(this.m_delim);
            }
            this.m_textDim.width = Math.max(this.m_textDim.width, n4);
            n3 = i + 1;
            i = s.indexOf(this.m_delim, n3);
            ++n2;
        }
        final String substring2;
        int n5 = fontMetrics.stringWidth(substring2 = s.substring(n3));
        if (this.m_maxTextWidth > -1 && n5 > this.m_maxTextWidth) {
            if (sb == null) {
                sb = new StringBuffer(s.substring(0, n3));
            }
            sb.append(StringLib.abbreviate(substring2, fontMetrics, this.m_maxTextWidth));
            n5 = this.m_maxTextWidth;
        }
        else if (sb != null) {
            sb.append(substring2);
        }
        this.m_textDim.width = Math.max(this.m_textDim.width, n5);
        this.m_textDim.height = fontMetrics.getHeight() * n2;
        return (sb == null) ? s : sb.toString();
    }
    
    protected Shape getRawShape(final VisualItem visualItem) {
        this.m_text = this.getText(visualItem);
        final Image image = this.getImage(visualItem);
        final double size = visualItem.getSize();
        double n = 0.0;
        double n2 = 0.0;
        if (image != null) {
            n2 = image.getHeight(null);
            n = image.getWidth(null);
        }
        int width = 0;
        int height = 0;
        if (this.m_text != null) {
            this.m_text = this.computeTextDimensions(visualItem, this.m_text, size);
            height = this.m_textDim.height;
            width = this.m_textDim.width;
        }
        double n3 = 0.0;
        double n4 = 0.0;
        switch (this.m_imagePos) {
            case 0:
            case 1: {
                n3 = width + size * (n + 2 * this.m_horizBorder + ((width > 0 && n > 0.0) ? this.m_imageMargin : 0));
                n4 = Math.max(height, size * n2) + size * 2.0 * this.m_vertBorder;
                break;
            }
            case 3:
            case 4: {
                n3 = Math.max(width, size * n) + size * 2.0 * this.m_horizBorder;
                n4 = height + size * (n2 + 2 * this.m_vertBorder + ((height > 0 && n2 > 0.0) ? this.m_imageMargin : 0));
                break;
            }
            default: {
                throw new IllegalStateException("Unrecognized image alignment setting.");
            }
        }
        getAlignedPoint(this.m_pt, visualItem, n3, n4, this.m_xAlign, this.m_yAlign);
        if (this.m_bbox instanceof RoundRectangle2D) {
            ((RoundRectangle2D)this.m_bbox).setRoundRect(this.m_pt.getX(), this.m_pt.getY(), n3, n4, size * this.m_arcWidth, size * this.m_arcHeight);
        }
        else {
            this.m_bbox.setFrame(this.m_pt.getX(), this.m_pt.getY(), n3, n4);
        }
        return this.m_bbox;
    }
    
    protected static void getAlignedPoint(final Point2D point2D, final VisualItem visualItem, final double n, final double n2, final int n3, final int n4) {
        double x = visualItem.getX();
        double y = visualItem.getY();
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            x = 0.0;
        }
        if (Double.isNaN(y) || Double.isInfinite(y)) {
            y = 0.0;
        }
        if (n3 == 2) {
            x -= n / 2.0;
        }
        else if (n3 == 1) {
            x -= n;
        }
        if (n4 == 2) {
            y -= n2 / 2.0;
        }
        else if (n4 == 3) {
            y -= n2;
        }
        point2D.setLocation(x, y);
    }
    
    public void render(final Graphics2D graphics2D, final VisualItem visualItem) {
        final RectangularShape rectangularShape = (RectangularShape)this.getShape(visualItem);
        if (rectangularShape == null) {
            return;
        }
        final int renderType = this.getRenderType(visualItem);
        if (renderType == 2 || renderType == 3) {
            GraphicsLib.paint(graphics2D, visualItem, rectangularShape, this.getStroke(visualItem), 2);
        }
        final String text = this.m_text;
        final Image image = this.getImage(visualItem);
        if (text == null && image == null) {
            return;
        }
        final double size = visualItem.getSize();
        final boolean b = 1.5 > Math.max(graphics2D.getTransform().getScaleX(), graphics2D.getTransform().getScaleY());
        double n = rectangularShape.getMinX() + size * this.m_horizBorder;
        double n2 = rectangularShape.getMinY() + size * this.m_vertBorder;
        if (image != null) {
            final double n3 = size * image.getWidth(null);
            final double n4 = size * image.getHeight(null);
            double n5 = n;
            double n6 = n2;
            switch (this.m_imagePos) {
                case 0: {
                    n += n3 + size * this.m_imageMargin;
                    break;
                }
                case 1: {
                    n5 = rectangularShape.getMaxX() - size * this.m_horizBorder - n3;
                    break;
                }
                case 4: {
                    n2 += n4 + size * this.m_imageMargin;
                    break;
                }
                case 3: {
                    n6 = rectangularShape.getMaxY() - size * this.m_vertBorder - n4;
                    break;
                }
                default: {
                    throw new IllegalStateException("Unrecognized image alignment setting.");
                }
            }
            Label_0481: {
                switch (this.m_imagePos) {
                    case 0:
                    case 1: {
                        switch (this.m_vImageAlign) {
                            case 3: {
                                n6 = rectangularShape.getMaxY() - size * this.m_vertBorder - n4;
                                break;
                            }
                            case 2: {
                                n6 = rectangularShape.getCenterY() - n4 / 2.0;
                                break;
                            }
                        }
                        break;
                    }
                    case 3:
                    case 4: {
                        switch (this.m_hImageAlign) {
                            case 0: {
                                break Label_0481;
                            }
                            case 1: {
                                n5 = rectangularShape.getMaxX() - size * this.m_horizBorder - n3;
                                break Label_0481;
                            }
                            case 2: {
                                n5 = rectangularShape.getCenterX() - n3 / 2.0;
                                break Label_0481;
                            }
                        }
                        break;
                    }
                }
            }
            if (b && size == 1.0) {
                graphics2D.drawImage(image, (int)n5, (int)n6, null);
            }
            else {
                this.m_transform.setTransform(size, 0.0, 0.0, size, n5, n6);
                graphics2D.drawImage(image, this.m_transform, null);
            }
        }
        final int textColor = visualItem.getTextColor();
        if (text != null && ColorLib.alpha(textColor) > 0) {
            graphics2D.setPaint(ColorLib.getColor(textColor));
            graphics2D.setFont(this.m_font);
            final FontMetrics fontMetrics = LabelRenderer.DEFAULT_GRAPHICS.getFontMetrics(this.m_font);
            double n7 = 0.0;
            switch (this.m_imagePos) {
                case 3:
                case 4: {
                    n7 = rectangularShape.getWidth() - 2.0 * size * this.m_horizBorder;
                    break;
                }
                default: {
                    n7 = this.m_textDim.width;
                    break;
                }
            }
            double n8 = 0.0;
            switch (this.m_imagePos) {
                case 0:
                case 1: {
                    n8 = rectangularShape.getHeight() - 2.0 * size * this.m_vertBorder;
                    break;
                }
                default: {
                    n8 = this.m_textDim.height;
                    break;
                }
            }
            double n9 = n2 + fontMetrics.getAscent();
            switch (this.m_vTextAlign) {
                case 3: {
                    n9 += n8 - this.m_textDim.height;
                    break;
                }
                case 2: {
                    n9 += (n8 - this.m_textDim.height) / 2.0;
                    break;
                }
            }
            final int height = fontMetrics.getHeight();
            int n10 = 0;
            for (int i = text.indexOf(this.m_delim); i >= 0; i = text.indexOf(this.m_delim, n10), n9 += height) {
                this.drawString(graphics2D, fontMetrics, text.substring(n10, i), b, n, n9, n7);
                n10 = i + 1;
            }
            this.drawString(graphics2D, fontMetrics, text.substring(n10), b, n, n9, n7);
        }
        if (renderType == 1 || renderType == 3) {
            GraphicsLib.paint(graphics2D, visualItem, rectangularShape, this.getStroke(visualItem), 1);
        }
    }
    
    private final void drawString(final Graphics2D graphics2D, final FontMetrics fontMetrics, final String s, final boolean b, final double n, final double n2, final double n3) {
        double n4 = 0.0;
        switch (this.m_hTextAlign) {
            case 0: {
                n4 = n;
                break;
            }
            case 1: {
                n4 = n + n3 - fontMetrics.stringWidth(s);
                break;
            }
            case 2: {
                n4 = n + (n3 - fontMetrics.stringWidth(s)) / 2.0;
                break;
            }
            default: {
                throw new IllegalStateException("Unrecognized text alignment setting.");
            }
        }
        if (b) {
            graphics2D.drawString(s, (int)n4, (int)n2);
        }
        else {
            graphics2D.drawString(s, (float)n4, (float)n2);
        }
    }
    
    public ImageFactory getImageFactory() {
        if (this.m_images == null) {
            this.m_images = new ImageFactory();
        }
        return this.m_images;
    }
    
    public void setImageFactory(final ImageFactory images) {
        this.m_images = images;
    }
    
    public int getHorizontalTextAlignment() {
        return this.m_hTextAlign;
    }
    
    public void setHorizontalTextAlignment(final int hTextAlign) {
        if (hTextAlign != 0 && hTextAlign != 1 && hTextAlign != 2) {
            throw new IllegalArgumentException("Illegal horizontal text alignment value.");
        }
        this.m_hTextAlign = hTextAlign;
    }
    
    public int getVerticalTextAlignment() {
        return this.m_vTextAlign;
    }
    
    public void setVerticalTextAlignment(final int vTextAlign) {
        if (vTextAlign != 4 && vTextAlign != 3 && vTextAlign != 2) {
            throw new IllegalArgumentException("Illegal vertical text alignment value.");
        }
        this.m_vTextAlign = vTextAlign;
    }
    
    public int getHorizontalImageAlignment() {
        return this.m_hImageAlign;
    }
    
    public void setHorizontalImageAlignment(final int hImageAlign) {
        if (hImageAlign != 0 && hImageAlign != 1 && hImageAlign != 2) {
            throw new IllegalArgumentException("Illegal horizontal text alignment value.");
        }
        this.m_hImageAlign = hImageAlign;
    }
    
    public int getVerticalImageAlignment() {
        return this.m_vImageAlign;
    }
    
    public void setVerticalImageAlignment(final int vImageAlign) {
        if (vImageAlign != 4 && vImageAlign != 3 && vImageAlign != 2) {
            throw new IllegalArgumentException("Illegal vertical text alignment value.");
        }
        this.m_vImageAlign = vImageAlign;
    }
    
    public int getImagePosition() {
        return this.m_imagePos;
    }
    
    public void setImagePosition(final int imagePos) {
        if (imagePos != 4 && imagePos != 3 && imagePos != 0 && imagePos != 1 && imagePos != 2) {
            throw new IllegalArgumentException("Illegal image position value.");
        }
        this.m_imagePos = imagePos;
    }
    
    public int getHorizontalAlignment() {
        return this.m_xAlign;
    }
    
    public int getVerticalAlignment() {
        return this.m_yAlign;
    }
    
    public void setHorizontalAlignment(final int xAlign) {
        this.m_xAlign = xAlign;
    }
    
    public void setVerticalAlignment(final int yAlign) {
        this.m_yAlign = yAlign;
    }
    
    public int getHorizontalPadding() {
        return this.m_horizBorder;
    }
    
    public void setHorizontalPadding(final int horizBorder) {
        this.m_horizBorder = horizBorder;
    }
    
    public int getVerticalPadding() {
        return this.m_vertBorder;
    }
    
    public void setVerticalPadding(final int vertBorder) {
        this.m_vertBorder = vertBorder;
    }
    
    public int getImageTextPadding() {
        return this.m_imageMargin;
    }
    
    public void setImageTextPadding(final int imageMargin) {
        this.m_imageMargin = imageMargin;
    }
}
