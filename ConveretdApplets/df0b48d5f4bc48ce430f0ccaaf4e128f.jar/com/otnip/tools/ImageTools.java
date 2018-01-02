// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.tools;

import java.awt.RenderingHints;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics2D;
import java.util.Map;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ImageTools
{
    private static final HashMap<Object, Object> renderingHints;
    
    public static BufferedImage feather(final BufferedImage sourceImage, final int edgeSize, final int arcRadius) {
        final int width = sourceImage.getWidth();
        final int height = sourceImage.getHeight();
        final float blurRadius = edgeSize;
        final int offset = edgeSize / 2;
        final BufferedImage targetImage = new BufferedImage(width, height, 2);
        final Graphics2D g2 = targetImage.createGraphics();
        g2.setRenderingHints(ImageTools.renderingHints);
        g2.fillRoundRect(offset, offset, width - offset * 2, height - offset * 2, arcRadius, arcRadius);
        g2.dispose();
        return targetImage;
    }
    
    public static BufferedImage scale(final BufferedImage source, final int width, final int height) {
        final BufferedImage dest = new BufferedImage(width, height, source.getType());
        final Graphics2D g2 = dest.createGraphics();
        g2.setRenderingHints(ImageTools.renderingHints);
        g2.drawImage(source, 0, 0, width, height, null);
        g2.dispose();
        return dest;
    }
    
    public static BufferedImage getFasterScaledInstance(final BufferedImage img, final int targetWidth, final int targetHeight, final Object hint, final boolean progressiveBilinear) {
        final int type = (img.getTransparency() == 1) ? 1 : 2;
        BufferedImage ret = img;
        BufferedImage scratchImage = null;
        Graphics2D g2 = null;
        int prevW = ret.getWidth();
        int prevH = ret.getHeight();
        final boolean isTranslucent = img.getTransparency() != 1;
        int w;
        int h;
        if (progressiveBilinear) {
            w = img.getWidth();
            h = img.getHeight();
        }
        else {
            w = targetWidth;
            h = targetHeight;
        }
        do {
            if (progressiveBilinear && w > targetWidth) {
                w /= 2;
                if (w < targetWidth) {
                    w = targetWidth;
                }
            }
            if (progressiveBilinear && h > targetHeight) {
                h /= 2;
                if (h < targetHeight) {
                    h = targetHeight;
                }
            }
            if (scratchImage == null || isTranslucent) {
                scratchImage = new BufferedImage(w, h, type);
                g2 = scratchImage.createGraphics();
            }
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(ret, 0, 0, w, h, 0, 0, prevW, prevH, null);
            prevW = w;
            prevH = h;
            ret = scratchImage;
        } while (w != targetWidth || h != targetHeight);
        if (g2 != null) {
            g2.dispose();
        }
        if (targetWidth != ret.getWidth() || targetHeight != ret.getHeight()) {
            scratchImage = new BufferedImage(targetWidth, targetHeight, type);
            g2 = scratchImage.createGraphics();
            g2.drawImage(ret, 0, 0, null);
            g2.dispose();
            ret = scratchImage;
        }
        return ret;
    }
    
    static {
        (renderingHints = new HashMap<Object, Object>()).put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        ImageTools.renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ImageTools.renderingHints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    }
}
