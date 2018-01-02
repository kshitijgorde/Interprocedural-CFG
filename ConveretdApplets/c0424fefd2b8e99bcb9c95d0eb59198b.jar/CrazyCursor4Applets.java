import java.awt.geom.Rectangle2D;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Shape;
import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.awt.image.ImageObserver;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Cursor;
import java.awt.Toolkit;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.Image;
import java.awt.Point;
import java.awt.Component;
import java.util.HashMap;
import java.awt.event.MouseMotionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class CrazyCursor4Applets implements MouseMotionListener
{
    private static HashMap crazyCursors;
    private Component component;
    private Point hotSpot;
    private static double theta;
    private Point mousePos;
    private Point mousePosOld;
    private boolean useImages;
    private Image[] images;
    private int imgIndex;
    private static final Image[] nullImages;
    
    private CrazyCursor4Applets(final Component component, final Image[] images, final Point hotspot) {
        this.component = component;
        this.mousePos = new Point();
        this.hotSpot = hotspot;
        if (images == null) {
            this.useImages = false;
        }
        else {
            this.images = images;
            this.useImages = true;
            this.imgIndex = 0;
        }
        component.addMouseMotionListener(this);
    }
    
    public static void create(final Component c) {
        CrazyCursor4Applets.crazyCursors.put(c, new CrazyCursor4Applets(c, CrazyCursor4Applets.nullImages, new Point(15, 0)));
    }
    
    public static void create(final Component c, final Image image, final Point hotspot) {
        CrazyCursor4Applets.crazyCursors.put(c, new CrazyCursor4Applets(c, toArray(image), hotspot));
    }
    
    public static void create(final Component c, final Image[] images, final Point hotspot) {
        CrazyCursor4Applets.crazyCursors.put(c, new CrazyCursor4Applets(c, images, hotspot));
    }
    
    public static void remove(final Component c) {
        c.removeMouseMotionListener(CrazyCursor4Applets.crazyCursors.get(c));
        CrazyCursor4Applets.crazyCursors.remove(c);
    }
    
    private static Image[] toArray(final Image image) {
        final Image[] images = { image };
        return images;
    }
    
    public void mouseMoved(final MouseEvent e) {
        final Point mouseAbs = SwingUtilities.convertPoint((Component)e.getSource(), e.getPoint(), this.component);
        this.mousePosOld = this.mousePos;
        this.mousePos = new Point(mouseAbs.x, mouseAbs.y);
        final int deltaX = this.mousePos.x - this.mousePosOld.x;
        final int deltaY = this.mousePos.y - this.mousePosOld.y;
        final double relHsX = 15.0 * Math.cos(CrazyCursor4Applets.theta);
        final double relHsY = 15.0 * Math.sin(CrazyCursor4Applets.theta);
        final double newTheta = getTheta(relHsX + deltaX, relHsY + deltaY);
        CrazyCursor4Applets.theta = (Double.isNaN(newTheta) ? CrazyCursor4Applets.theta : newTheta);
        final Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(this.getCursorImage(), this.getHotSpot(), "CrazyCursor");
        this.component.setCursor(c);
    }
    
    public void mouseDragged(final MouseEvent e) {
        this.mouseMoved(e);
    }
    
    private static double getTheta(final double x, final double y) {
        double theta = Math.atan(y / x);
        theta += ((x < 0.0) ? 3.141592653589793 : ((y < 0.0) ? 6.283185307179586 : 0.0));
        return theta;
    }
    
    private static Point getRotatedInstance(final Point p, final Point c, final double theta) {
        final double dx = p.x - c.x;
        final double dy = p.y - c.y;
        final double alpha = getTheta(dx, dy);
        final double r = Math.sqrt(dx * dx + dy * dy);
        final int qx = (int)Math.round(c.getX() + r * Math.cos(alpha + theta));
        final int qy = (int)Math.round(c.getY() + r * Math.sin(alpha + theta));
        return new Point(qx, qy);
    }
    
    private Point getHotSpot() {
        return getRotatedInstance(this.hotSpot, new Point(15, 15), CrazyCursor4Applets.theta + 1.5707963267948966);
    }
    
    private Image getCursorImage() {
        final BufferedImage bi = new BufferedImage(32, 32, 6);
        final Graphics2D big = (Graphics2D)bi.getGraphics();
        final AffineTransform tX = AffineTransform.getRotateInstance(CrazyCursor4Applets.theta + 1.5707963267948966, 15.0, 15.0);
        if (this.useImages) {
            big.drawImage(this.images[this.imgIndex], tX, this.component);
            ++this.imgIndex;
            this.imgIndex %= this.images.length;
        }
        else {
            big.setTransform(tX);
            this.paintDefaultCursor(big);
        }
        return bi;
    }
    
    public void paintDefaultCursor(final Graphics2D g2) {
        final GeneralPath arrow = new GeneralPath();
        arrow.moveTo(15.0f, 0.0f);
        arrow.lineTo(29.0f, 23.0f);
        arrow.lineTo(19.0f, 23.0f);
        arrow.lineTo(19.0f, 31.0f);
        arrow.lineTo(11.0f, 31.0f);
        arrow.lineTo(11.0f, 23.0f);
        arrow.lineTo(2.0f, 23.0f);
        arrow.closePath();
        g2.setPaint(new GradientPaint(15.0f, 0.0f, Color.red, 15.0f, 31.0f, Color.blue));
        g2.fill(arrow);
        final float alpha = (float)(Math.cos(CrazyCursor4Applets.theta) * 0.2 + Math.sin(CrazyCursor4Applets.theta) * -0.2 + 1.0) / 2.0f;
        g2.clip(arrow);
        g2.setPaint(Color.white);
        g2.setComposite(AlphaComposite.getInstance(3, alpha));
        g2.fill(new Rectangle2D.Double(0.0, 0.0, 15.0, 32.0));
        g2.setComposite(AlphaComposite.getInstance(3, 1.0f - alpha));
        g2.fill(new Rectangle2D.Double(15.0, 0.0, 15.0, 32.0));
    }
    
    static {
        CrazyCursor4Applets.crazyCursors = new HashMap();
        nullImages = null;
    }
}
