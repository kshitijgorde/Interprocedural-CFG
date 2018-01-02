// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.geom.Rectangle2D;
import java.awt.TexturePaint;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.Hashtable;

public class NFPatternFactory
{
    protected static final int bg = 16777215;
    protected static final int fg = -16777216;
    protected static final int[][][] predefinedTextures;
    protected static Hashtable textures;
    
    protected static Paint createTexturePattern(final int n, final Color color, final Color color2, final Rectangle rectangle) {
        if (n <= 0 || n >= NFPatternFactory.predefinedTextures.length || color == null || color2 == null) {
            return null;
        }
        BufferedImage cachedTexture = getCachedTexture(n, color, color2);
        if (cachedTexture == null) {
            final int[][] array = NFPatternFactory.predefinedTextures[n];
            final int length = array[0].length;
            final int length2 = array.length;
            final int rgb = color.getRGB();
            final int rgb2 = color2.getRGB();
            cachedTexture = new BufferedImage(length, length2, 2);
            for (int i = 0; i < array.length; ++i) {
                for (int j = 0; j < array[i].length; ++j) {
                    int n2 = array[i][j];
                    if (n2 == 16777215) {
                        n2 = rgb;
                    }
                    else if (n2 == -16777216) {
                        n2 = rgb2;
                    }
                    cachedTexture.setRGB(j, i, n2);
                }
            }
            cacheTexture(cachedTexture, n, color, color2);
        }
        return createImagePattern(cachedTexture, rectangle);
    }
    
    protected static Paint createImagePattern(final Image image, final Rectangle rectangle) {
        final BufferedImage convertToBufferedImage = convertToBufferedImage(image);
        if (convertToBufferedImage == null) {
            return null;
        }
        return new TexturePaint(convertToBufferedImage, new Rectangle((rectangle == null) ? 0 : rectangle.x, (rectangle == null) ? 0 : rectangle.y, convertToBufferedImage.getWidth(), convertToBufferedImage.getHeight()));
    }
    
    protected static BufferedImage convertToBufferedImage(final Image image) {
        if (image == null) {
            return null;
        }
        if (image instanceof BufferedImage) {
            return (BufferedImage)image;
        }
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        if (width <= 0 || height <= 0) {
            return null;
        }
        final BufferedImage bufferedImage = new BufferedImage(width, height, 2);
        final Graphics graphics = bufferedImage.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        bufferedImage.flush();
        return bufferedImage;
    }
    
    protected static BufferedImage getCachedTexture(final int n, final Color color, final Color color2) {
        return NFPatternFactory.textures.get(getTextureKey(n, color, color2));
    }
    
    protected static void cacheTexture(final BufferedImage bufferedImage, final int n, final Color color, final Color color2) {
        NFPatternFactory.textures.put(getTextureKey(n, color, color2), bufferedImage);
    }
    
    protected static Object getTextureKey(final int n, final Color color, final Color color2) {
        return String.valueOf(n) + ":" + Integer.toHexString(color.getRGB()) + ":" + Integer.toHexString(color2.getRGB());
    }
    
    protected static Paint createGradientPattern(final int n, final Color color, final Color color2, final Rectangle rectangle) {
        if (rectangle == null) {
            return null;
        }
        int n2 = 0;
        int x = 0;
        int n3 = 0;
        int n4 = 0;
        switch (n) {
            case 7: {
                x = (n2 = rectangle.x + rectangle.width / 2);
                n3 = rectangle.y;
                n4 = rectangle.y + rectangle.height;
                break;
            }
            case 8: {
                n2 = rectangle.x;
                x = rectangle.x + rectangle.width;
                n4 = (n3 = rectangle.y + rectangle.height / 2);
                break;
            }
            case 9: {
                n2 = rectangle.x + rectangle.width;
                n3 = rectangle.y;
                x = rectangle.x;
                n4 = rectangle.y + rectangle.height;
                break;
            }
            case 10: {
                n2 = rectangle.x;
                n3 = rectangle.y;
                x = rectangle.x + rectangle.width;
                n4 = rectangle.y + rectangle.height;
                break;
            }
            default: {
                return null;
            }
        }
        return new GradientPaint(n2, n3, color, x, n4, color2);
    }
    
    public static Paint getPattern(final int n, final Color color, final Color color2) {
        return getPattern(n, color, color2, null);
    }
    
    public static Paint getPattern(final NFPatternFill nfPatternFill, final Color color, final Color color2, final Rectangle rectangle) {
        if (nfPatternFill == null) {
            return null;
        }
        return getPattern(nfPatternFill.pattern, (nfPatternFill.bg == null) ? color : nfPatternFill.bg, (nfPatternFill.fg == null) ? color2 : nfPatternFill.fg, rectangle, nfPatternFill.image);
    }
    
    public static Paint getPattern(final int n, final Color color, final Color color2, final Rectangle rectangle) {
        return getPattern(n, color, color2, rectangle, null);
    }
    
    public static Paint getPattern(final int n, final Color color, final Color color2, final Rectangle rectangle, final Image image) {
        switch (n) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6: {
                return createTexturePattern(n, color, color2, rectangle);
            }
            case 11: {
                return createImagePattern(image, rectangle);
            }
            case 7:
            case 8:
            case 9:
            case 10: {
                return createGradientPattern(n, color, color2, rectangle);
            }
            default: {
                return null;
            }
        }
    }
    
    static {
        predefinedTextures = new int[][][] { { new int[0] }, { { 16777215, 16777215, -16777216 }, { 16777215, -16777216, 16777215 }, { -16777216, 16777215, 16777215 } }, { { -16777216, 16777215, 16777215 }, { 16777215, -16777216, 16777215 }, { 16777215, 16777215, -16777216 } }, { { -16777216, 16777215, 16777215, 16777215 }, { 16777215, -16777216, 16777215, -16777216 }, { 16777215, 16777215, -16777216, 16777215 }, { 16777215, -16777216, 16777215, -16777216 } }, { { 16777215, 16777215, 16777215 }, { -16777216, -16777216, -16777216 }, { 16777215, 16777215, 16777215 } }, { { 16777215, -16777216, 16777215 }, { 16777215, -16777216, 16777215 }, { 16777215, -16777216, 16777215 } }, { { 16777215, -16777216, 16777215 }, { -16777216, -16777216, -16777216 }, { 16777215, -16777216, 16777215 } } };
        NFPatternFactory.textures = new Hashtable();
    }
}
