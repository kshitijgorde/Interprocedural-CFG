import java.awt.Image;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.image.BufferedImage;

// 
// Decompiled by Procyon v0.5.30
// 

class Sprite implements Drawable
{
    static final int originX = 241;
    static final int originY = 206;
    PlayerCanvas canvas;
    BufferedImage costume;
    BufferedImage rotatedCostume;
    BufferedImage filteredCostume;
    BufferedImage tempImage;
    double x;
    double y;
    boolean isShowing;
    boolean isDraggable;
    double alpha;
    double scale;
    double rotationDegrees;
    int rotationstyle;
    int rotationX;
    int rotationY;
    int offsetX;
    int offsetY;
    Bubble bubble;
    boolean penDown;
    int lastPenX;
    int lastPenY;
    Color penColor;
    int penSize;
    double penHue;
    double penShade;
    boolean filterChanged;
    double color;
    double brightness;
    double fisheye;
    double whirl;
    double mosaic;
    double pixelate;
    ImageFilter imageFilter;
    
    Sprite(final LContext lContext) {
        this.isShowing = true;
        this.isDraggable = false;
        this.alpha = 1.0;
        this.scale = 1.0;
        this.rotationDegrees = 90.0;
        this.penColor = new Color(0, 0, 255);
        this.penSize = 1;
        this.filterChanged = false;
        this.imageFilter = new ImageFilter();
        this.setPenColor(this.penColor);
        if (lContext != null) {
            this.canvas = lContext.canvas;
        }
    }
    
    int screenX() {
        return 241 + (int)(this.x - this.offsetX);
    }
    
    int screenY() {
        return 206 + (int)(-this.y - this.offsetY);
    }
    
    void setscreenX(final double n) {
        this.x = n + this.offsetX - 241.0;
    }
    
    void setscreenY(final double n) {
        this.y = -(n + this.offsetY - 206.0);
    }
    
    public void mouseDown(final int n, final int n2) {
    }
    
    void setStageOffset() {
        final double n = 0.0;
        this.y = n;
        this.x = n;
        this.offsetX = this.costume.getWidth(null) / 2;
        this.offsetY = this.costume.getHeight(null) / 2;
    }
    
    public void dragTo(final int n, final int n2) {
        this.inval();
        this.setscreenX(n);
        this.setscreenY(n2);
        this.updateBubble();
        this.inval();
    }
    
    boolean containsPoint(final int n, final int n2) {
        final BufferedImage outImage = this.outImage();
        final int screenX = this.screenX();
        final int screenY = this.screenY();
        final int width = outImage.getWidth(null);
        final int height = outImage.getHeight(null);
        return n >= screenX && n < screenX + width && n2 >= screenY && n2 < screenY + height && (outImage.getRGB(n - screenX, n2 - screenY) & 0xFF000000) != 0x0;
    }
    
    boolean touchingSprite(final Object o, final LContext lContext) {
        if (!(o instanceof Sprite)) {
            Logo.error("argument must be a Sprite", lContext);
            return false;
        }
        final Sprite sprite = (Sprite)o;
        final Rectangle intersection = this.rect().intersection(sprite.rect());
        if (intersection.width <= 0 || intersection.height <= 0) {
            return false;
        }
        final BufferedImage outImage = this.outImage();
        final BufferedImage outImage2 = sprite.outImage();
        final int n = intersection.x - this.screenX();
        final int n2 = intersection.y - this.screenY();
        final int n3 = intersection.x - sprite.screenX();
        int n4 = intersection.y - sprite.screenY();
        for (int i = n2; i < n2 + intersection.height; ++i) {
            int n5 = n3;
            for (int j = n; j < n + intersection.width; ++j) {
                final int rgb = outImage.getRGB(j, i);
                final int rgb2 = outImage2.getRGB(n5, n4);
                if ((rgb & 0xFF000000) != 0x0 && (rgb2 & 0xFF000000) != 0x0) {
                    return true;
                }
                ++n5;
            }
            ++n4;
        }
        return false;
    }
    
    boolean touchingColor(final Object o, final LContext lContext) {
        if (!(o instanceof Color)) {
            Logo.error("argument of touchingColor? must be a Color", lContext);
            return false;
        }
        final int n = ((Color)o).getRGB() | 0xFF000000;
        final Rectangle rect = this.rect();
        final BufferedImage outImage = this.outImage();
        final BufferedImage drawAreaWithoutSprite = lContext.canvas.drawAreaWithoutSprite(rect, this);
        for (int i = 0; i < rect.height; ++i) {
            for (int j = 0; j < rect.width; ++j) {
                if ((outImage.getRGB(j, i) & 0xFF000000) != 0x0 && this.colorsMatch(drawAreaWithoutSprite.getRGB(j, i), n)) {
                    drawAreaWithoutSprite.flush();
                    return true;
                }
            }
        }
        drawAreaWithoutSprite.flush();
        return false;
    }
    
    boolean colorTouchingColor(final Object o, final Object o2, final LContext lContext) {
        if (!(o instanceof Color) || !(o2 instanceof Color)) {
            Logo.error("the arguments of colorTouchingColor? must be Colors", lContext);
            return false;
        }
        final int n = ((Color)o).getRGB() | 0xFF000000;
        final int n2 = ((Color)o2).getRGB() | 0xFF000000;
        final Rectangle rect = this.rect();
        final BufferedImage outImage = this.outImage();
        final BufferedImage drawAreaWithoutSprite = lContext.canvas.drawAreaWithoutSprite(rect, this);
        for (int i = 0; i < rect.height; ++i) {
            for (int j = 0; j < rect.width; ++j) {
                if (this.colorsMatch(outImage.getRGB(j, i), n) && this.colorsMatch(drawAreaWithoutSprite.getRGB(j, i), n2)) {
                    drawAreaWithoutSprite.flush();
                    return true;
                }
            }
        }
        drawAreaWithoutSprite.flush();
        return false;
    }
    
    boolean colorsMatch(final int n, final int n2) {
        return (n & 0xFF000000) == (n2 & 0xFF000000) && (n >> 16 & 0xF8) == (n2 >> 16 & 0xF8) && (n >> 8 & 0xF8) == (n2 >> 8 & 0xF8) && (((n & 0xFFFF00) == 0x0 && (n2 & 0xFFFF00) == 0x0 && (n & 0xFF) <= 8 && (n2 & 0xFF) <= 8) || (n & 0xF8) == (n2 & 0xF8));
    }
    
    void setalpha(final Object o, final LContext lContext) {
        double aDouble = Logo.aDouble(o, lContext);
        if (aDouble < 0.0) {
            aDouble = -aDouble;
        }
        if (aDouble > 1.0) {
            aDouble = 1.0;
        }
        this.alpha = aDouble;
        this.inval();
    }
    
    void setcostume(final Object o, final Object o2, final Object o3, final LContext lContext) {
        if (!(o instanceof BufferedImage)) {
            return;
        }
        this.rotationX = Logo.anInt(o2, lContext);
        this.rotationY = Logo.anInt(o3, lContext);
        if (this.costume != null) {
            this.inval();
        }
        this.costume = (BufferedImage)o;
        this.rotateAndScale();
        this.inval();
    }
    
    void costumeChanged() {
        this.inval();
        this.rotateAndScale();
        this.inval();
    }
    
    void setscale(final Object o, final LContext lContext) {
        this.scale = Math.min(Math.max(Logo.aDouble(o, lContext), Math.max(Math.min(this.costume.getWidth(null), 10) / 480.0, Math.min(this.costume.getHeight(null), 10) / 360.0)), Math.min(480.0 / this.costume.getWidth(null), 360.0 / this.costume.getHeight(null)));
        this.costumeChanged();
    }
    
    void rotateAndScale() {
        this.filterChanged = true;
        final double n = (this.rotationstyle == 0) ? this.rotationDegrees : 90.0;
        if (this.rotatedCostume != null && this.rotatedCostume != this.costume) {
            this.rotatedCostume.flush();
        }
        if (this.scale == 1.0 && this.rotationDegrees == 90.0) {
            this.rotatedCostume = this.costume;
            this.offsetX = this.rotationX;
            this.offsetY = this.rotationY;
            return;
        }
        final int width = this.costume.getWidth(null);
        final int height = this.costume.getHeight(null);
        final double radians = Math.toRadians(n - 90.0);
        final AffineTransform rotateInstance = AffineTransform.getRotateInstance(radians, width / 2, height / 2);
        rotateInstance.scale(this.scale, this.scale);
        final Rectangle2D.Float float1 = (Rectangle2D.Float)new AffineTransformOp(rotateInstance, 2).getBounds2D(this.costume);
        final float n2 = -float1.x;
        final float n3 = -float1.y;
        final AffineTransform rotateInstance2 = AffineTransform.getRotateInstance(radians, width / 2 + n2, height / 2 + n3);
        rotateInstance2.translate(n2, n3);
        rotateInstance2.scale(this.scale, this.scale);
        this.rotatedCostume = new AffineTransformOp(rotateInstance2, 2).filter(this.costume, null);
        final AffineTransform rotateInstance3 = AffineTransform.getRotateInstance(radians, 0.0, 0.0);
        rotateInstance3.scale(this.scale, this.scale);
        final Point2D transform = rotateInstance3.transform(new Point2D.Double(this.rotationX - width / 2, this.rotationY - height / 2), null);
        this.offsetX = (int)(transform.getX() + this.rotatedCostume.getWidth(null) / 2);
        this.offsetY = (int)(transform.getY() + this.rotatedCostume.getHeight(null) / 2);
        if (this.rotationstyle == 1) {
            if (((this.rotationDegrees < 0.0) ? (this.rotationDegrees + 360.0) : this.rotationDegrees) <= 180.0) {
                return;
            }
            final int width2 = this.rotatedCostume.getWidth(null);
            final AffineTransform scaleInstance = AffineTransform.getScaleInstance(-1.0, 1.0);
            scaleInstance.translate(-width2, 0.0);
            this.rotatedCostume = new AffineTransformOp(scaleInstance, 2).filter(this.rotatedCostume, null);
            this.offsetX = (int)(width2 / 2 - this.scale * (this.rotationX - width / 2));
            this.offsetY = (int)(this.scale * this.rotationY);
        }
    }
    
    void show() {
        this.isShowing = true;
        this.inval();
    }
    
    void hide() {
        this.isShowing = false;
        this.inval();
    }
    
    public boolean isShowing() {
        return this.isShowing;
    }
    
    public boolean isVisible() {
        return this.isShowing && this.alpha > 0.0;
    }
    
    public Rectangle rect() {
        final BufferedImage outImage = this.outImage();
        if (outImage == null) {
            return new Rectangle(this.screenX(), this.screenY(), 600, 600);
        }
        return new Rectangle(this.screenX(), this.screenY(), outImage.getWidth(null), outImage.getHeight(null));
    }
    
    public Rectangle fullRect() {
        Rectangle rectangle = this.rect();
        if (this.bubble != null) {
            rectangle = rectangle.union(this.bubble.rect());
        }
        return rectangle;
    }
    
    void inval() {
        this.canvas.inval(this.fullRect());
    }
    
    public void paint(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        if (this.filterChanged) {
            this.applyFilters();
        }
        if (this.alpha != 1.0) {
            final Composite composite = graphics2D.getComposite();
            graphics2D.setComposite(AlphaComposite.getInstance(3, (float)this.alpha));
            graphics2D.drawImage(this.outImage(), this.screenX(), this.screenY(), null);
            graphics2D.setComposite(composite);
        }
        else {
            graphics2D.drawImage(this.outImage(), this.screenX(), this.screenY(), null);
        }
    }
    
    public void paintBubble(final Graphics graphics) {
        if (this.bubble != null) {
            this.bubble.paint(graphics);
        }
    }
    
    void talkbubble(final Object o, final boolean b, final boolean b2, final LContext lContext) {
        final String contents = (String)((o instanceof String) ? o : Logo.prs(o));
        this.inval();
        this.bubble = null;
        if (contents.length() == 0) {
            return;
        }
        this.bubble = new Bubble();
        if (b) {
            this.bubble.beAskBubble();
        }
        if (!b2) {
            this.bubble.beThinkBubble(true);
        }
        this.bubble.setContents(contents);
        if (this.rotationDegrees >= 0.0 && this.rotationDegrees <= 180.0) {
            this.bubble.pointLeft = true;
        }
        this.updateBubble();
    }
    
    void updateBubble() {
        final int x = 3;
        final int n = 482 - x;
        if (this.bubble == null) {
            return;
        }
        this.inval();
        final Rectangle rect = this.rect();
        int pointLeft = this.bubble.pointLeft ? 1 : 0;
        final int[] bubbleInsets = this.bubbleInsets();
        if (pointLeft != 0 && rect.x + rect.width - bubbleInsets[1] + this.bubble.w + x > n) {
            pointLeft = 0;
        }
        if (pointLeft == 0 && rect.x + bubbleInsets[0] - this.bubble.w - x < 0) {
            pointLeft = 1;
        }
        if (pointLeft != 0) {
            this.bubble.pointLeft = true;
            this.bubble.x = rect.x + rect.width - bubbleInsets[1] + x;
        }
        else {
            this.bubble.pointLeft = false;
            this.bubble.x = rect.x + bubbleInsets[0] - this.bubble.w - x;
        }
        if (this.bubble.x + this.bubble.w > n) {
            this.bubble.x = n - this.bubble.w;
        }
        if (this.bubble.x < x) {
            this.bubble.x = x;
        }
        this.bubble.y = Math.max(rect.y - this.bubble.h - 12, 25 + x);
        if (this.bubble.y + this.bubble.h > 387) {
            this.bubble.y = 387 - this.bubble.h;
        }
        this.inval();
    }
    
    int[] bubbleInsets() {
        final BufferedImage outImage = this.outImage();
        final int width = outImage.getWidth();
        final int height = outImage.getHeight();
        int n = width;
        int n2 = width;
        int n3 = -1;
        for (int i = 0; i < height; ++i) {
            boolean b = false;
            for (int j = 0; j < Math.max(n, n2); ++j) {
                if ((outImage.getRGB(j, i) & 0xFF000000) != 0x0 && j < n) {
                    n = j;
                    b = true;
                }
                if ((outImage.getRGB(width - j - 1, i) & 0xFF000000) != 0x0 && j < n2) {
                    n2 = j;
                    b = true;
                }
            }
            if (n3 < 0) {
                if (b) {
                    n3 = i;
                }
            }
            else if (i >= n3 + 10) {
                break;
            }
        }
        return new int[] { n, n2 };
    }
    
    void setPenDown(final boolean penDown) {
        if (penDown == this.penDown) {
            return;
        }
        if (penDown) {
            final int n = -1000000;
            this.lastPenY = n;
            this.lastPenX = n;
        }
        this.canvas.updatePenTrailForSprite(this);
        this.penDown = penDown;
    }
    
    void setPenColor(final Color penColor) {
        final float[] rgBtoHSB = Color.RGBtoHSB(penColor.getRed(), penColor.getGreen(), penColor.getBlue(), null);
        this.penColor = penColor;
        this.penHue = 200.0 * rgBtoHSB[0];
        final float n = rgBtoHSB[1];
        final float n2 = rgBtoHSB[2];
        if (n2 == 1.0) {
            this.penShade = 50.0 + 50.0 * (1.0 - n);
        }
        else {
            this.penShade = 50.0 * n2;
        }
    }
    
    void setPenHue(final double n) {
        this.penHue = n % 200.0;
        if (this.penHue < 0.0) {
            this.penHue += 200.0;
        }
        this.setPenShade(this.penShade);
    }
    
    void setPenShade(final double n) {
        this.penShade = n % 200.0;
        if (this.penShade < 0.0) {
            this.penShade += 200.0;
        }
        final float n2 = (float)((this.penShade > 100.0) ? (200.0 - this.penShade) : this.penShade);
        if (n2 <= 50.0) {
            this.penColor = new Color(Color.HSBtoRGB((float)(this.penHue / 200.0), 1.0f, (n2 + 10.0f) / 60.0f));
        }
        else {
            this.penColor = new Color(Color.HSBtoRGB((float)(this.penHue / 200.0), (100.0f - n2 + 10.0f) / 60.0f, 1.0f));
        }
    }
    
    BufferedImage outImage() {
        if (this.filteredCostume != null) {
            return this.filteredCostume;
        }
        if (this.rotatedCostume != null) {
            return this.rotatedCostume;
        }
        return this.costume;
    }
    
    void applyFilters() {
        if (!this.filtersActive()) {
            this.filteredCostume = null;
            this.filterChanged = false;
            return;
        }
        this.imageFilter.setSourceImage((this.rotatedCostume != null) ? this.rotatedCostume : this.costume);
        if (this.color != 0.0) {
            this.imageFilter.applyHueShift((int)this.color);
        }
        if (this.brightness != 0.0) {
            this.imageFilter.applyBrightnessShift((int)this.brightness);
        }
        if (this.whirl != 0.0) {
            this.imageFilter.applyWhirl(this.whirl);
        }
        if (this.fisheye != 0.0) {
            this.imageFilter.applyFisheye(this.fisheye);
        }
        if (Math.abs(this.pixelate) >= 5.0) {
            this.imageFilter.applyPixelate(this.pixelate);
        }
        if (Math.abs(this.mosaic) >= 5.0) {
            this.imageFilter.applyMosaic(this.mosaic);
        }
        this.filteredCostume = this.imageFilter.filteredImage;
        this.filterChanged = false;
    }
    
    boolean filtersActive() {
        return this.color != 0.0 || this.brightness != 0.0 || this.fisheye != 0.0 || this.whirl != 0.0 || Math.abs(this.mosaic) >= 5.0 || Math.abs(this.pixelate) >= 5.0;
    }
}
