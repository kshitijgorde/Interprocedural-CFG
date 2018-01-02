// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.display;

import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import prefuse.Display;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Container;
import prefuse.util.io.IOLib;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.Image;

public class BackgroundPainter implements PaintListener
{
    private static final double THRESH = 0.01;
    private Image m_img;
    private boolean m_fixed;
    private boolean m_tiled;
    private AffineTransform m_identity;
    private Clip m_clip;
    
    public BackgroundPainter(final String s, final boolean b, final boolean b2) {
        this(Toolkit.getDefaultToolkit().getImage(IOLib.urlFromString(s)), b, b2);
    }
    
    public BackgroundPainter(final Image img, final boolean fixed, final boolean tiled) {
        this.m_img = img;
        final MediaTracker mediaTracker = new MediaTracker(new Container());
        mediaTracker.addImage(this.m_img, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        mediaTracker.removeImage(this.m_img, 0);
        this.m_fixed = fixed;
        this.m_tiled = tiled;
    }
    
    public void prePaint(final Display display, final Graphics2D graphics2D) {
        final AffineTransform transform = graphics2D.getTransform();
        final boolean translation = isTranslation(transform);
        if (this.m_fixed || translation) {
            final int n = this.m_fixed ? 0 : ((int)transform.getTranslateX());
            final int n2 = this.m_fixed ? 0 : ((int)transform.getTranslateY());
            graphics2D.setTransform(this.getIdentity());
            if (this.m_tiled) {
                final int width = display.getWidth();
                final int width2 = this.m_img.getWidth(null);
                final int height = display.getHeight();
                final int height2 = this.m_img.getHeight(null);
                int n3 = this.m_fixed ? 0 : (n % width2);
                int n4 = this.m_fixed ? 0 : (n2 % height2);
                if (n3 > 0) {
                    n3 -= width2;
                }
                if (n4 > 0) {
                    n4 -= height2;
                }
                for (int i = n3; i < width - n3; i += width2) {
                    for (int j = n4; j < height - n4; j += height2) {
                        graphics2D.drawImage(this.m_img, i, j, null);
                    }
                }
            }
            else {
                graphics2D.drawImage(this.m_img, n, n2, null);
            }
            graphics2D.setTransform(transform);
        }
        else if (this.m_tiled) {
            final int width3 = this.m_img.getWidth(null);
            final int height3 = this.m_img.getHeight(null);
            final Clip clip = this.getClip();
            clip.setClip(0.0, 0.0, display.getWidth(), display.getHeight());
            clip.transform(display.getInverseTransform());
            final int n5 = (int)Math.ceil(clip.getWidth());
            final int n6 = (int)Math.ceil(clip.getHeight());
            final int n7 = (int)clip.getMinX();
            final int n8 = (int)clip.getMinY();
            final int n9 = n7 % width3 + width3;
            final int n10 = n8 % height3 + height3;
            final int n11 = n7 - n9;
            final int n12 = n5 + n9;
            final int n13 = n8 - n10;
            final int n14 = n6 + n10;
            for (int k = n11; k < n11 + n12; k += width3) {
                for (int l = n13; l < n13 + n14; l += height3) {
                    graphics2D.drawImage(this.m_img, k, l, null);
                }
            }
        }
        else {
            graphics2D.drawImage(this.m_img, 0, 0, null);
        }
    }
    
    private static boolean isTranslation(final AffineTransform affineTransform) {
        return Math.abs(affineTransform.getScaleX() - 1.0) < 0.01 && Math.abs(affineTransform.getScaleY() - 1.0) < 0.01 && Math.abs(affineTransform.getShearX()) < 0.01 && Math.abs(affineTransform.getShearY()) < 0.01;
    }
    
    private AffineTransform getIdentity() {
        if (this.m_identity == null) {
            this.m_identity = new AffineTransform();
        }
        return this.m_identity;
    }
    
    private Clip getClip() {
        if (this.m_clip == null) {
            this.m_clip = new Clip();
        }
        return this.m_clip;
    }
    
    public void postPaint(final Display display, final Graphics2D graphics2D) {
    }
}
