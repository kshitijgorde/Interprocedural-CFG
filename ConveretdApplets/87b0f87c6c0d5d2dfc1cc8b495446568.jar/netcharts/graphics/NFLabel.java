// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import netcharts.util.NFParam;
import java.util.StringTokenizer;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import netcharts.util.NFDebug;
import java.applet.Applet;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.awt.Polygon;
import netcharts.util.NFUtil;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Component;

public class NFLabel implements NFLabelIntf
{
    public static final int LEFT = 1;
    public static final int CENTER = 4;
    public static final int RIGHT = 0;
    protected Component comp;
    protected Graphics g;
    protected String lbl;
    protected static Font FONT_DEFAULT;
    protected Font font;
    protected static Color COLOR_DEFAULT;
    protected Color color;
    protected int x;
    protected int y;
    protected NFRegion region;
    protected static int JUSTIFY_DEFAULT;
    protected int justify;
    protected static int EJUSTIFY_DEFAULT;
    protected int ejustify;
    protected static int ANGLE_DEFAULT;
    protected int angle;
    protected Dimension lbounds;
    protected FontMetrics fm;
    protected Font fmFont;
    protected double scale;
    private static final boolean DEBUG = false;
    private static final int maxCache = 10;
    private static final int minFontSize = 4;
    private NFActiveLabel activeLabel;
    private Rectangle parentBounds;
    private Rectangle clipRectangle;
    private Vector imageCache;
    private Vector imageHashID;
    private NFLabelRotate labelRotate;
    private static Object rotateLock;
    private int GAP;
    
    public NFLabel(final Component comp, final Graphics g, final String lbl, final Font font, final Color color, final int x, final int y, final int angle, final NFRegion region) {
        this.comp = null;
        this.g = null;
        this.lbl = "UNDEFINED";
        this.font = NFLabel.FONT_DEFAULT;
        this.color = NFLabel.COLOR_DEFAULT;
        this.x = 0;
        this.y = 0;
        this.region = null;
        this.justify = NFLabel.JUSTIFY_DEFAULT;
        this.ejustify = NFLabel.EJUSTIFY_DEFAULT;
        this.angle = NFLabel.ANGLE_DEFAULT;
        this.lbounds = null;
        this.fm = null;
        this.fmFont = null;
        this.scale = 0.0;
        this.activeLabel = null;
        this.parentBounds = null;
        this.clipRectangle = null;
        this.imageCache = new Vector();
        this.imageHashID = new Vector();
        this.labelRotate = null;
        this.GAP = 6;
        this.comp = comp;
        this.g = g;
        this.lbl = lbl;
        this.font = font;
        this.color = color;
        this.x = x;
        this.y = y;
        this.region = region;
        this.justify = -1;
        this.setAngle(angle);
    }
    
    public NFLabel(final Component comp, final Graphics g, final String lbl, final int x, final int y) {
        this.comp = null;
        this.g = null;
        this.lbl = "UNDEFINED";
        this.font = NFLabel.FONT_DEFAULT;
        this.color = NFLabel.COLOR_DEFAULT;
        this.x = 0;
        this.y = 0;
        this.region = null;
        this.justify = NFLabel.JUSTIFY_DEFAULT;
        this.ejustify = NFLabel.EJUSTIFY_DEFAULT;
        this.angle = NFLabel.ANGLE_DEFAULT;
        this.lbounds = null;
        this.fm = null;
        this.fmFont = null;
        this.scale = 0.0;
        this.activeLabel = null;
        this.parentBounds = null;
        this.clipRectangle = null;
        this.imageCache = new Vector();
        this.imageHashID = new Vector();
        this.labelRotate = null;
        this.GAP = 6;
        this.comp = comp;
        this.g = g;
        this.lbl = lbl;
        this.x = x;
        this.y = y;
    }
    
    public NFLabel() {
        this.comp = null;
        this.g = null;
        this.lbl = "UNDEFINED";
        this.font = NFLabel.FONT_DEFAULT;
        this.color = NFLabel.COLOR_DEFAULT;
        this.x = 0;
        this.y = 0;
        this.region = null;
        this.justify = NFLabel.JUSTIFY_DEFAULT;
        this.ejustify = NFLabel.EJUSTIFY_DEFAULT;
        this.angle = NFLabel.ANGLE_DEFAULT;
        this.lbounds = null;
        this.fm = null;
        this.fmFont = null;
        this.scale = 0.0;
        this.activeLabel = null;
        this.parentBounds = null;
        this.clipRectangle = null;
        this.imageCache = new Vector();
        this.imageHashID = new Vector();
        this.labelRotate = null;
        this.GAP = 6;
    }
    
    public NFLabel(final NFLabel nfLabel) {
        this();
        if (nfLabel != null) {
            this.comp = nfLabel.comp;
            this.g = nfLabel.g;
            this.lbl = nfLabel.lbl;
            this.font = nfLabel.font;
            this.color = nfLabel.color;
            this.x = nfLabel.x;
            this.y = nfLabel.y;
            this.region = nfLabel.region;
            this.justify = nfLabel.justify;
            this.ejustify = nfLabel.ejustify;
            this.setAngle(nfLabel.angle);
        }
    }
    
    public void setComponent(final Component comp) {
        this.comp = comp;
    }
    
    public void setGraphics(final Graphics g) {
        this.g = g;
    }
    
    public void setJustify(final int justify) {
        this.justify = justify;
    }
    
    public int getJustify() {
        return this.justify;
    }
    
    public void setExternalJustify(final int ejustify) {
        this.ejustify = ejustify;
    }
    
    public int getExternalJustify() {
        return this.ejustify;
    }
    
    public void setLabel(final String lbl) {
        this.lbl = lbl;
    }
    
    public void setAngle(final int n) {
        if (NFUtil.getJDKVersion() >= 1.2) {
            this.angle = n % 360;
        }
        else {
            this.angle = 90 * (n % 360 / 90);
        }
    }
    
    public String getLabel() {
        return this.lbl;
    }
    
    public void setScale(final double n) {
        this.scale = n;
        if (this.region != null) {
            this.region.setScale(n);
        }
    }
    
    public void setFont(final Font font) {
        if (font == this.font) {
            return;
        }
        this.font = font;
        this.fm = null;
        this.fmFont = null;
    }
    
    public Font getFont() {
        return this.font;
    }
    
    private void setFont(final Graphics graphics, final Font font) {
        graphics.setFont(this.getScaleFont(font));
    }
    
    private Font getScaleFont(final Font font) {
        if (this.scale <= 0.0) {
            return font;
        }
        int n = (int)(font.getSize() * this.scale);
        if (n < 4) {
            n = 4;
        }
        return NFUtil.getFont(font.getName(), font.getStyle(), n);
    }
    
    private FontMetrics getFontMetrics(final Graphics graphics, Font scaleFont) {
        scaleFont = this.getScaleFont(scaleFont);
        if (this.fmFont == scaleFont && this.fm != null) {
            return this.fm;
        }
        this.fmFont = scaleFont;
        if (this.fmFont != null) {
            graphics.setFont(this.fmFont);
        }
        return this.fm = graphics.getFontMetrics();
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setPos(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setRegion(final NFRegion region) {
        this.region = region;
        if (region != null) {
            region.setScale(this.scale);
        }
    }
    
    public NFRegion getRegion() {
        return this.region;
    }
    
    public void setGap(final int gap) {
        this.GAP = gap;
    }
    
    public int getGap() {
        return this.GAP;
    }
    
    public Rectangle getParentBounds() {
        return this.parentBounds;
    }
    
    public void setParentBounds(final Rectangle rectangle) {
        if (rectangle == null) {
            this.parentBounds = null;
            return;
        }
        if (this.parentBounds == null) {
            this.parentBounds = new Rectangle();
        }
        this.parentBounds.x = rectangle.x;
        this.parentBounds.y = rectangle.y;
        this.parentBounds.width = rectangle.width;
        this.parentBounds.height = rectangle.height;
    }
    
    public void setClipRectangle(final Rectangle rectangle) {
        if (rectangle == null) {
            this.clipRectangle = null;
            return;
        }
        if (this.clipRectangle == null) {
            this.clipRectangle = new Rectangle();
        }
        this.clipRectangle.x = rectangle.x;
        this.clipRectangle.y = rectangle.y;
        this.clipRectangle.width = rectangle.width;
        this.clipRectangle.height = rectangle.height;
    }
    
    public Rectangle getClipRectangle() {
        return this.clipRectangle;
    }
    
    public Dimension getBounds(final Graphics graphics) {
        return this.getBounds(graphics, null, null);
    }
    
    public Dimension getBounds(final Graphics graphics, final Dimension dimension, final NFRegionBorder nfRegionBorder) {
        return this.getBounds(graphics, dimension, nfRegionBorder, null);
    }
    
    public Dimension getBounds(final Graphics graphics, Dimension dimension, NFRegionBorder border, final Polygon polygon) {
        if (dimension == null) {
            dimension = new Dimension(0, 0);
        }
        if (this.lbl != null && this.lbl.length() > 0) {
            if (graphics != null) {
                dimension = this.stringBounds(this.lbl, this.getFontMetrics(graphics, this.font), dimension);
            }
            else {
                dimension = this.stringBounds(this.lbl, Toolkit.getDefaultToolkit().getFontMetrics(this.font), dimension);
            }
        }
        else if (this.region == null || this.region.getImage() == null) {
            dimension.width = 0;
            dimension.height = 0;
        }
        else {
            final Image image = this.region.getImage();
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            if (width <= 0 || height <= 0) {
                dimension.width = 0;
                dimension.height = 0;
            }
            else {
                if (this.scale > 0.0) {
                    width *= (int)this.scale;
                    if (width < 1) {
                        width = 1;
                    }
                    height *= (int)this.scale;
                    if (height < 1) {
                        height = 1;
                    }
                }
                dimension.width = width;
                dimension.height = height;
            }
        }
        if (dimension.width == 0 && dimension.height == 0) {
            return dimension;
        }
        if (this.region != null) {
            if (this.lbl != null && this.lbl.length() > 0) {
                final Dimension dimension2 = dimension;
                dimension2.width += this.GAP;
                final Dimension dimension3 = dimension;
                dimension3.height += this.GAP;
            }
            border = this.region.getBorder(border);
            final Dimension dimension4 = dimension;
            dimension4.width += border.left + border.right;
            final Dimension dimension5 = dimension;
            dimension5.height += border.top + border.bottom;
        }
        if (NFUtil.getJDKVersion() >= 1.2) {
            final Polygon rotatedBounds = NFOffscreenImage.getRotatedBounds(this.x - dimension.width / 2, this.y - dimension.height / 2, dimension.width, dimension.height, this.angle);
            final Rectangle boundingBox = rotatedBounds.getBoundingBox();
            dimension.width = boundingBox.width;
            dimension.height = boundingBox.height;
            if (polygon != null) {
                polygon.npoints = rotatedBounds.npoints;
                polygon.xpoints = rotatedBounds.xpoints;
                polygon.ypoints = rotatedBounds.ypoints;
            }
        }
        else {
            switch (this.angle) {
                case 90:
                case 270: {
                    final int width2 = dimension.width;
                    dimension.width = dimension.height;
                    dimension.height = width2;
                    break;
                }
            }
        }
        return dimension;
    }
    
    public void draw() {
        this.drawLabel(this.comp, this.g, this.lbl, this.font, this.color, this.x, this.y, this.justify, this.angle, this.region);
    }
    
    public void draw(final Graphics graphics) {
        this.drawLabel(this.comp, graphics, this.lbl, this.font, this.color, this.x, this.y, this.justify, this.angle, this.region);
    }
    
    public void draw(final Component component, final Graphics graphics) {
        this.drawLabel(component, graphics, this.lbl, this.font, this.color, this.x, this.y, this.justify, this.angle, this.region);
    }
    
    public void draw(final Applet applet, final Graphics graphics) {
        this.drawLabel(applet, graphics, this.lbl, this.font, this.color, this.x, this.y, this.justify, this.angle, this.region);
    }
    
    public void draw(final String s) {
        this.drawLabel(this.comp, this.g, s, this.font, this.color, this.x, this.y, this.justify, this.angle, this.region);
    }
    
    public void draw(final String s, final int n, final int n2) {
        this.drawLabel(this.comp, this.g, s, this.font, this.color, n, n2, this.justify, this.angle, this.region);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final Font font, final Color color) {
        this.drawLabel(this.comp, graphics, this.lbl, font, color, n, n2, this.justify, this.angle, this.region);
    }
    
    public void draw(final String s, final int n, final int n2, final int n3) {
        this.drawLabel(this.comp, this.g, s, this.font, this.color, n, n2, n3, this.angle, this.region);
    }
    
    public void draw(final int n, final int n2) {
        this.drawLabel(this.comp, this.g, this.lbl, this.font, this.color, n, n2, this.justify, this.angle, this.region);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2) {
        this.drawLabel(this.comp, graphics, this.lbl, this.font, this.color, n, n2, this.justify, this.angle, this.region);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final String s) {
        this.drawLabel(this.comp, graphics, s, this.font, this.color, n, n2, this.justify, this.angle, this.region);
    }
    
    public void draw(final NFRegion nfRegion) {
        this.drawLabel(this.comp, this.g, this.lbl, this.font, this.color, this.x, this.y, this.justify, this.angle, nfRegion);
    }
    
    public void draw(final Component component, final Graphics graphics, final String s, final Font font, final Color color, final int n, final int n2, final int n3, final int n4, final NFRegion nfRegion) {
        this.drawLabel(component, graphics, s, font, color, n, n2, n3, n4, nfRegion);
    }
    
    protected void drawLabel(final Component component, final Graphics graphics, final String s, final Font font, final Color color, final int n, final int n2, final int n3, final int n4, final NFRegion nfRegion) {
        this.drawLabel(component, graphics, s, font, color, n, n2, n3, n4, nfRegion, 0);
    }
    
    protected void drawLabel(final Component component, final Graphics graphics, final String s, final Font font, final Color color, int n, int n2, final int n3, final int n4, final NFRegion nfRegion, final int n5) {
        if (n4 > 0) {
            this.drawRotatedLabel(component, graphics, s, font, color, n, n2, n3, n4, nfRegion);
            return;
        }
        if (s == null || s.length() == 0) {
            if (this.activeLabel != null) {
                this.activeLabel.setBounds(-1, -1, 0, 0);
            }
            if (nfRegion == null || nfRegion.getImage() == null) {
                return;
            }
            final NFRegionBorder border = nfRegion.getBorder();
            final Image image = nfRegion.getImage();
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            if (width <= 0 || height <= 0) {
                return;
            }
            final int imageType = nfRegion.getImageType();
            if (this.scale <= 0.0) {
                nfRegion.setImageType(4);
            }
            else {
                nfRegion.setImageType(1);
                width *= (int)this.scale;
                if (width < 1) {
                    width = 1;
                }
                height *= (int)this.scale;
                if (height < 1) {
                    height = 1;
                }
            }
            n = n - width / 2 - (border.left + border.right) / 2;
            n2 = n2 - height / 2 - (border.top + border.bottom) / 2;
            final int n6 = width + border.left + border.right;
            final int n7 = height + border.top + border.bottom;
            nfRegion.draw(graphics, n, n2, n6, n7);
            nfRegion.setImageType(imageType);
            if (this.activeLabel != null) {
                this.activeLabel.setBounds(n, n2, n6 - 1, n7 - 1);
            }
        }
        else {
            if (font != null) {
                this.setFont(graphics, font);
                this.setFont(font);
                this.fm = this.getFontMetrics(graphics, font);
            }
            final Dimension stringBounds = this.stringBounds(s, this.fm);
            if (this.parentBounds != null) {
                final Dimension bounds = this.getBounds(graphics);
                final int n8 = n + bounds.width / 2 - (this.parentBounds.x + this.parentBounds.width);
                if (n8 > 0) {
                    n -= n8;
                }
                if (n - bounds.width / 2 < this.parentBounds.x) {
                    n = this.parentBounds.x + bounds.width / 2;
                }
                final int n9 = n2 + bounds.height / 2 - (this.parentBounds.y + this.parentBounds.height);
                if (n9 > 0) {
                    n2 -= n9;
                }
                if (n2 - bounds.height / 2 < this.parentBounds.y) {
                    n2 = this.parentBounds.y + bounds.height / 2;
                }
            }
            if (this.clipRectangle != null && (n > this.clipRectangle.x + this.clipRectangle.width || n2 > this.clipRectangle.y + this.clipRectangle.height || n + stringBounds.width < this.clipRectangle.x || n2 + stringBounds.height < this.clipRectangle.y)) {
                if (this.activeLabel != null) {
                    this.activeLabel.setBounds(-1, -1, 0, 0);
                }
                return;
            }
            if (nfRegion == null) {
                if (color != null) {
                    graphics.setColor(color);
                }
                stringDraw(graphics, s, n, n2, n3, stringBounds.width, this.fm);
                if (this.activeLabel != null) {
                    if (NFUtil.getJDKVersion() >= 1.2 && n5 > 0) {
                        this.activeLabel.setBounds(NFOffscreenImage.getRotatedBounds(n - stringBounds.width / 2 - 2, n2 - stringBounds.height / 2 - 2, stringBounds.width + 3, stringBounds.height + 3, n5));
                    }
                    else {
                        this.activeLabel.setBounds(n - stringBounds.width / 2 - 2, n2 - stringBounds.height / 2 - 2, stringBounds.width + 3, stringBounds.height + 3);
                    }
                }
                return;
            }
            final NFRegionBorder border2 = nfRegion.getBorder();
            nfRegion.draw(graphics, n - (stringBounds.width + this.GAP) / 2 - (border2.left + border2.right) / 2, n2 - (stringBounds.height + this.GAP) / 2 - (border2.top + border2.bottom) / 2, stringBounds.width + border2.left + border2.right + this.GAP, stringBounds.height + border2.top + border2.bottom + this.GAP);
            if (this.activeLabel != null) {
                if (NFUtil.getJDKVersion() >= 1.2 && n5 > 0) {
                    this.activeLabel.setBounds(NFOffscreenImage.getRotatedBounds(n - (stringBounds.width + this.GAP) / 2 - (border2.left + border2.right) / 2, n2 - (stringBounds.height + this.GAP) / 2 - (border2.top + border2.bottom) / 2, stringBounds.width + border2.left + border2.right + this.GAP - 1, stringBounds.height + border2.top + border2.bottom + this.GAP - 1, n5));
                }
                else {
                    this.activeLabel.setBounds(n - (stringBounds.width + this.GAP) / 2 - (border2.left + border2.right) / 2, n2 - (stringBounds.height + this.GAP) / 2 - (border2.top + border2.bottom) / 2, stringBounds.width + border2.left + border2.right + this.GAP - 1, stringBounds.height + border2.top + border2.bottom + this.GAP - 1);
                }
            }
            if (color != null) {
                graphics.setColor(color);
            }
            stringDraw(graphics, s, n + (border2.left - border2.right) / 2, n2 + (border2.top - border2.bottom) / 2, n3, stringBounds.width, this.fm);
        }
    }
    
    private void drawRotatedLabel(final Component component, final Graphics graphics, String s, Font font, Color color, int n, int n2, final int n3, final int n4, final NFRegion nfRegion) {
        final Polygon polygon = new Polygon();
        final Dimension bounds = this.getBounds(graphics, null, null, polygon);
        if (color == null) {
            color = this.color;
        }
        if (font == null) {
            font = this.font;
        }
        Color color2;
        if (nfRegion == null) {
            color2 = color;
        }
        else {
            color2 = nfRegion.getColor();
        }
        if (color2 == null) {
            color2 = Color.black;
        }
        if (s == null) {
            s = "";
        }
        final StringBuffer sb = new StringBuffer();
        sb.append(s);
        sb.append(",");
        sb.append(font);
        sb.append(",");
        sb.append(color);
        sb.append(",");
        sb.append(n4);
        sb.append(",");
        sb.append(n3);
        sb.append(",");
        sb.append(bounds.width);
        sb.append(",");
        sb.append(bounds.height);
        sb.append(",");
        sb.append(this.scale);
        if (nfRegion != null) {
            sb.append(",");
            nfRegion.getHashID(sb);
        }
        final String string = sb.toString();
        Image image = this.getImage(string);
        if (image == null) {
            Image image2 = null;
            if (bounds.width == 0 || bounds.height == 0) {
                return;
            }
            Graphics graphics2;
            if (NFUtil.getJDKVersion() >= 1.2) {
                image = (image2 = NFOffscreenImage.createImage(bounds));
                graphics2 = image2.getGraphics();
                NFOffscreenImage.setRenderingHints(graphics, graphics2);
                NFOffscreenImage.setRotationTransform(graphics2, n4, bounds.width / 2, bounds.height / 2);
                final Rectangle parentBounds = this.parentBounds;
                this.parentBounds = null;
                final Rectangle clipRectangle = this.clipRectangle;
                this.clipRectangle = null;
                this.drawLabel(component, graphics2, s, font, color, bounds.width / 2, bounds.height / 2, n3, 0, nfRegion, n4);
                this.parentBounds = parentBounds;
                this.clipRectangle = clipRectangle;
            }
            else {
                try {
                    switch (n4) {
                        case 0:
                        case 180: {
                            image = component.createImage(bounds.width, bounds.height);
                            break;
                        }
                        case 90:
                        case 270: {
                            image = component.createImage(bounds.height, bounds.width);
                            break;
                        }
                    }
                    graphics2 = image.getGraphics();
                }
                catch (Exception ex) {
                    NFDebug.print("drawRotatedLabel: " + ex);
                    return;
                }
                this.setFont(graphics, font);
                this.fm = this.getFontMetrics(graphics, font);
                int n6;
                int n5 = n6 = 0;
                switch (n4) {
                    case 0:
                    case 180: {
                        n6 = bounds.width / 2;
                        n5 = bounds.height / 2;
                        break;
                    }
                    case 90:
                    case 270: {
                        n5 = bounds.width / 2;
                        n6 = bounds.height / 2;
                        break;
                    }
                }
                Color color3;
                if (!color2.equals(Color.red) && !color.equals(Color.red)) {
                    color3 = Color.red;
                }
                else if (!color2.equals(Color.blue) && !color.equals(Color.blue)) {
                    color3 = Color.blue;
                }
                else {
                    color3 = Color.white;
                }
                graphics2.setColor(color3);
                this.setFont(graphics2, font);
                switch (n4) {
                    case 0:
                    case 180: {
                        graphics2.fillRect(0, 0, bounds.width, bounds.height);
                        break;
                    }
                    case 90:
                    case 270: {
                        graphics2.fillRect(0, 0, bounds.height, bounds.width);
                        break;
                    }
                }
                final Rectangle parentBounds2 = this.parentBounds;
                this.parentBounds = null;
                final Rectangle clipRectangle2 = this.clipRectangle;
                this.clipRectangle = null;
                this.drawLabel(component, graphics2, s, font, color, n6, n5, n3, 0, nfRegion);
                this.parentBounds = parentBounds2;
                this.clipRectangle = clipRectangle2;
                try {
                    if (this.labelRotate == null) {
                        this.labelRotate = new NFLabelRotate();
                    }
                    this.labelRotate.init(n4, color3);
                    image2 = component.createImage(new FilteredImageSource(image.getSource(), this.labelRotate));
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                    NFDebug.print("NetCharts: Can't rotate image");
                }
            }
            if (image2 != null) {
                this.putImage(string, image2);
            }
            if (image != null) {
                if (graphics2 != null) {
                    graphics2.dispose();
                }
                image.flush();
            }
            image = image2;
        }
        if (image != null) {
            if (this.parentBounds != null) {
                final int n7 = n + bounds.width / 2 - (this.parentBounds.x + this.parentBounds.width);
                if (n7 > 0) {
                    n -= n7;
                }
                if (n - bounds.width / 2 < this.parentBounds.x) {
                    n = this.parentBounds.x + bounds.width / 2;
                }
                final int n8 = n2 + bounds.height / 2 - (this.parentBounds.y + this.parentBounds.height);
                if (n8 > 0) {
                    n2 -= n8;
                }
                if (n2 - bounds.height / 2 < this.parentBounds.y) {
                    n2 = this.parentBounds.y + bounds.height / 2;
                }
            }
            if (this.clipRectangle != null && (n > this.clipRectangle.x + this.clipRectangle.width || n2 > this.clipRectangle.y + this.clipRectangle.height || n + bounds.width < this.clipRectangle.x || n2 + bounds.height < this.clipRectangle.y)) {
                if (this.activeLabel != null) {
                    this.activeLabel.setBounds(-1, -1, 0, 0);
                }
                return;
            }
            try {
                graphics.drawImage(image, n - bounds.width / 2, n2 - bounds.height / 2, null);
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
                NFDebug.print("NetCharts: DrawImage failed!!!!");
            }
        }
        this.setActiveBounds(n, n2, bounds, (n4 > 0) ? polygon : null);
    }
    
    public void externallyJustify(final Rectangle rectangle) {
        this.externallyJustify(rectangle, this.getBounds(null));
    }
    
    public void externallyJustify(final Rectangle rectangle, final Dimension dimension) {
        this.externallyJustify(rectangle, dimension, this.getExternalJustify());
    }
    
    public void externallyJustify(final Rectangle rectangle, final Dimension dimension, final int n) {
        int n2 = 0;
        int n3 = 0;
        switch (n) {
            case 2: {
                n2 = rectangle.x + rectangle.width / 2;
                n3 = rectangle.y + dimension.height / 2;
                break;
            }
            case 3: {
                n2 = rectangle.x + rectangle.width / 2;
                n3 = rectangle.y + rectangle.height - dimension.height / 2;
                break;
            }
            case -1:
            case 4: {
                n2 = rectangle.x + rectangle.width / 2;
                n3 = rectangle.y + rectangle.height / 2;
                break;
            }
            case 1: {
                n2 = rectangle.x + dimension.width / 2;
                n3 = rectangle.y + rectangle.height / 2;
                break;
            }
            case 0: {
                n2 = rectangle.x + rectangle.width - dimension.width / 2;
                n3 = rectangle.y + rectangle.height / 2;
                break;
            }
            default: {
                return;
            }
        }
        this.setPos(n2, n3);
    }
    
    private void setActiveBounds(final int n, final int n2, final Dimension dimension, final Polygon bounds) {
        if (this.activeLabel == null) {
            return;
        }
        if (NFUtil.getJDKVersion() >= 1.2) {
            this.activeLabel.setBounds(bounds);
        }
        else {
            this.activeLabel.setBounds(n - dimension.width / 2, n2 - dimension.height / 2, dimension.width - 1, dimension.height - 1);
        }
    }
    
    private Image getImage(final String s) {
        for (int size = this.imageCache.size(), i = 0; i < size; ++i) {
            if (s.equals(this.imageHashID.elementAt(i))) {
                return (Image)this.imageCache.elementAt(i);
            }
        }
        return null;
    }
    
    private void putImage(final String s, Image image) {
        this.imageCache.insertElementAt(image, 0);
        this.imageHashID.insertElementAt(s, 0);
        final int size = this.imageCache.size();
        if (size > 10) {
            image = (Image)this.imageCache.elementAt(size - 1);
            image.flush();
            this.imageCache.removeElementAt(size - 1);
            this.imageHashID.removeElementAt(size - 1);
        }
    }
    
    private static void stringDraw(final Graphics graphics, final String s, final int n, int n2, final int n3, final int n4, final FontMetrics fontMetrics) {
        if (s.indexOf(10) < 0) {
            n2 -= fontMetrics.getHeight() / 2 - fontMetrics.getAscent();
            final int stringWidth = fontMetrics.stringWidth(s);
            switch (n3) {
                case 1: {
                    graphics.drawString(s, n - n4 / 2, n2);
                    break;
                }
                case 0: {
                    graphics.drawString(s, n + n4 / 2 - stringWidth, n2);
                    break;
                }
                default: {
                    graphics.drawString(s, n - stringWidth / 2, n2);
                    break;
                }
            }
        }
        else {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
            n2 -= stringTokenizer.countTokens() * fontMetrics.getHeight() / 2 - fontMetrics.getAscent();
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                final int stringWidth2 = fontMetrics.stringWidth(nextToken);
                switch (n3) {
                    case 1: {
                        graphics.drawString(nextToken, n - n4 / 2, n2);
                        break;
                    }
                    case 0: {
                        graphics.drawString(nextToken, n + n4 / 2 - stringWidth2, n2);
                        break;
                    }
                    default: {
                        graphics.drawString(nextToken, n - stringWidth2 / 2, n2);
                        break;
                    }
                }
                n2 += fontMetrics.getHeight();
            }
        }
    }
    
    private Dimension stringBounds(final Graphics graphics, final String s) {
        return this.stringBounds(s, this.getFontMetrics(graphics, this.font));
    }
    
    private Dimension stringBounds(final String s, final FontMetrics fontMetrics) {
        return this.stringBounds(s, fontMetrics, null);
    }
    
    private Dimension stringBounds(final String s, final FontMetrics fontMetrics, Dimension dimension) {
        if (dimension == null) {
            dimension = new Dimension(0, 0);
        }
        if (s.indexOf(10) < 0) {
            dimension.height = fontMetrics.getHeight();
            dimension.width = fontMetrics.stringWidth(s);
        }
        else {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
            dimension.width = 0;
            dimension.height = 0;
            while (stringTokenizer.hasMoreTokens()) {
                final Dimension dimension2 = dimension;
                ++dimension2.height;
                final int stringWidth = fontMetrics.stringWidth(stringTokenizer.nextToken());
                if (stringWidth > dimension.width) {
                    dimension.width = stringWidth;
                }
            }
            dimension.height *= fontMetrics.getHeight();
        }
        return dimension;
    }
    
    public static NFLabel loadParams(final NFParam nfParam, final Object o) {
        return loadParams(nfParam, o, true);
    }
    
    public static NFLabel loadParams(final NFParam nfParam, final Object o, final boolean b) {
        return loadParams(nfParam, o, 0, b);
    }
    
    public static NFLabel loadParams(final NFParam nfParam, final Object o, final int n) {
        return loadParams(nfParam, o, n, true);
    }
    
    public static NFLabel loadParams(final NFParam nfParam, final Object o, final int n, final boolean b) {
        return loadParams(nfParam, o, n, b, false);
    }
    
    public static NFLabel loadParams(final NFParam nfParam, final Object o, final int n, final boolean b, final boolean b2) {
        final NFLabel nfLabel = new NFLabel();
        final Vector vector = (Vector)o;
        if (vector == null || vector.size() <= n) {
            return null;
        }
        loadParams(nfLabel, vector, n, b, b2);
        nfLabel.setScale(nfParam.getChartScale());
        nfLabel.setComponent(nfParam.getComponent());
        return nfLabel;
    }
    
    public static void loadParams(final NFLabel nfLabel, final Vector vector, final int n) {
        loadParams(nfLabel, vector, n, true);
    }
    
    public static void loadParams(final NFLabel nfLabel, final Vector vector, final int n, final boolean b) {
        loadParams(nfLabel, vector, n, b, false);
    }
    
    public static void loadParams(final NFLabel nfLabel, final Vector vector, final int n, final boolean b, final boolean b2) {
        final String string = NFUtil.getString(vector, n + 0, null);
        final Color color = NFUtil.getColor(vector, n + 1, NFLabel.COLOR_DEFAULT);
        final String string2 = NFUtil.getString(vector, n + 2, NFLabel.FONT_DEFAULT.getName());
        final int number = NFUtil.getNumber(vector, n + 3, NFLabel.FONT_DEFAULT.getSize());
        final int number2 = NFUtil.getNumber(vector, n + 4, NFLabel.ANGLE_DEFAULT);
        nfLabel.setLabel(string);
        nfLabel.setColor(color);
        nfLabel.setFont(NFUtil.getFont(string2, 1, number));
        nfLabel.setAngle(number2);
        if (b) {
            nfLabel.setJustify(NFUtil.getNumber(vector, n + 5, NFLabel.JUSTIFY_DEFAULT));
        }
        if (b2) {
            nfLabel.setExternalJustify(NFUtil.getNumber(vector, n + 6, NFLabel.EJUSTIFY_DEFAULT));
        }
    }
    
    public void loadParams(final NFParam nfParam, final String s) throws Exception {
        if (!nfParam.changed(s)) {
            return;
        }
        loadParams(this, (Vector)nfParam.get(s), 0);
        this.setScale(nfParam.getChartScale());
        this.setComponent(nfParam.getComponent());
    }
    
    public void loadParams(final Vector vector) {
        loadParams(this, vector, 0);
    }
    
    public void setActiveLabel(final NFActiveLabel activeLabel) {
        this.activeLabel = activeLabel;
    }
    
    public NFActiveLabel getActiveLabel() {
        return this.activeLabel;
    }
    
    static {
        NFLabel.FONT_DEFAULT = NFUtil.getFont("TimesRoman", 1, 16);
        NFLabel.COLOR_DEFAULT = Color.black;
        NFLabel.JUSTIFY_DEFAULT = -1;
        NFLabel.EJUSTIFY_DEFAULT = 4;
        NFLabel.ANGLE_DEFAULT = 0;
        NFLabel.rotateLock = new Object();
    }
}
