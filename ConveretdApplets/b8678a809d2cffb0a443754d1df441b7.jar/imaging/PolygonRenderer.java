// 
// Decompiled by Procyon v0.5.30
// 

package imaging;

import imaging.math3D.Point2D;
import java.awt.geom.GeneralPath;
import java.awt.GraphicsConfiguration;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import imaging.math3D.ViewWindow;
import imaging.math3D.Rectangle3D;
import imaging.math3D.Vector3D;
import imaging.util.PolygonArea;

public class PolygonRenderer extends PolygonArea
{
    public static final int ROTATELEFT = 361;
    public static final int ROTATERIGHT = 362;
    protected Vector3D a;
    protected Vector3D b;
    protected Vector3D c;
    private Quadre perspRect;
    private float rotAngle;
    protected Rectangle3D textureBounds;
    protected ViewWindow viewWindow;
    private int selectedRef;
    
    public PolygonRenderer() {
        this.a = new Vector3D();
        this.b = new Vector3D();
        this.c = new Vector3D();
        this.perspRect = new Quadre();
        this.rotAngle = 0.0f;
        this.textureBounds = new Rectangle3D();
    }
    
    protected boolean changeMouseCursor(final int x, final int y) {
        int leftTop = 2;
        int rightTop = 2;
        int leftBottom = 2;
        int rightBottom = 2;
        leftTop = ((this.perspRect.br.y - y < 0) ? (y - this.perspRect.br.y) : (this.perspRect.br.y - y));
        rightTop = ((this.perspRect.bl.y - y < 0) ? (y - this.perspRect.bl.y) : (this.perspRect.bl.y - y));
        leftBottom = ((this.perspRect.tl.y - y < 0) ? (y - this.perspRect.tl.y) : (this.perspRect.tl.y - y));
        rightBottom = ((this.perspRect.tr.y - y < 0) ? (y - this.perspRect.tr.y) : (this.perspRect.tr.y - y));
        return leftTop <= 1 || rightTop <= 1 || leftBottom <= 1 || rightBottom <= 1;
    }
    
    private void rotateDirs(final Quadre prspRef) {
        final Vector3D textureDirectionU = this.textureBounds.getDirectionU();
        final Vector3D textureDirectionV = this.textureBounds.getDirectionV();
        if (this.viewWindow == null) {
            return;
        }
        float maxZ = this.viewWindow.getDistance() * Math.abs((0.0f + prspRef.br.y - prspRef.tr.y) / (prspRef.bl.y - prspRef.tl.y));
        if (maxZ < 1.0f) {
            maxZ = 1.0f / maxZ;
        }
        maxZ -= this.viewWindow.getDistance();
        textureDirectionU.setTo(prspRef.tl.x - prspRef.tr.x, 0.0f, maxZ);
        maxZ = this.viewWindow.getDistance() * Math.abs((0.0f + prspRef.br.x - prspRef.bl.x) / (prspRef.tr.x - prspRef.tl.x));
        if (maxZ < 1.0f) {
            maxZ = 1.0f / maxZ;
        }
        maxZ -= this.viewWindow.getDistance();
        textureDirectionV.setTo(prspRef.tl.x + prspRef.tr.x - prspRef.br.x - prspRef.bl.x, prspRef.bl.y - prspRef.tl.y, maxZ);
        final Vector3D normal = this.textureBounds.getNormal();
        textureDirectionU.rotateAroundVector(normal, -this.rotAngle);
        textureDirectionV.rotateAroundVector(normal, -this.rotAngle);
        textureDirectionU.normalize();
        textureDirectionV.normalize();
    }
    
    void drawTexture(final Graphics2D graphics, final BufferedImage texture, final boolean noRef, final int xmin, final int xmax, final int ymin, final int ymax) {
        graphics.setClip(this.getGeneralPath());
        this.drawCurrentPolygon(graphics, texture, xmin, xmax, ymin, ymax);
        graphics.setClip(null);
        if (!noRef) {
            graphics.setColor(Color.orange);
            graphics.fillOval(this.perspRect.tl.x - 5, this.perspRect.tl.y - 5, 10, 10);
            graphics.fillOval(this.perspRect.tr.x - 5, this.perspRect.tr.y - 5, 10, 10);
            graphics.fillOval(this.perspRect.bl.x - 5, this.perspRect.bl.y - 5, 10, 10);
            graphics.fillOval(this.perspRect.br.x - 5, this.perspRect.br.y - 5, 10, 10);
            graphics.setColor(Color.black);
            graphics.drawLine(this.perspRect.tl.x, this.perspRect.tl.y, this.perspRect.tr.x, this.perspRect.tr.y);
            graphics.drawLine(this.perspRect.tl.x, this.perspRect.tl.y, this.perspRect.bl.x, this.perspRect.bl.y);
            graphics.drawLine(this.perspRect.tr.x, this.perspRect.tr.y, this.perspRect.br.x, this.perspRect.br.y);
            graphics.drawLine(this.perspRect.bl.x, this.perspRect.bl.y, this.perspRect.br.x, this.perspRect.br.y);
        }
    }
    
    protected void drawCurrentPolygon(final Graphics2D g, final BufferedImage scaledTextureImage, final int xmin, final int xmax, final int ymin, final int ymax) {
        final Vector3D viewPos = new Vector3D();
        final Vector3D o = this.textureBounds.getOrigin();
        o.setTo(0.0f, 0.0f, this.viewWindow.getDistance());
        final Vector3D u = this.textureBounds.getDirectionU();
        final Vector3D v = this.textureBounds.getDirectionV();
        final Vector3D normal = this.textureBounds.getNormal();
        this.a.setToCrossProduct(v, o);
        this.b.setToCrossProduct(o, u);
        this.c.setToCrossProduct(u, v);
        final int textureBoundX = scaledTextureImage.getWidth();
        final int textureBoundY = scaledTextureImage.getHeight();
        viewPos.z = this.viewWindow.getDistance();
        int tx = 0;
        int ty = 0;
        final int width = xmax - xmin;
        final int height = ymax - ymin;
        final GraphicsConfiguration gc = g.getDeviceConfiguration();
        final BufferedImage img = gc.createCompatibleImage(width, height, 1);
        final Graphics2D g2 = img.createGraphics();
        for (int y = ymin; y <= ymax; ++y) {
            viewPos.y = this.viewWindow.convertFromScreenYToViewY(y);
            for (int x = xmin; x <= xmax; ++x) {
                viewPos.x = this.viewWindow.convertFromScreenXToViewX(x);
                try {
                    tx = (int)(this.a.getDotProduct(viewPos) / this.c.getDotProduct(viewPos));
                    ty = (int)(this.b.getDotProduct(viewPos) / this.c.getDotProduct(viewPos));
                    int xrgb = tx % textureBoundX;
                    if (xrgb < 0) {
                        xrgb += textureBoundX;
                    }
                    int yrgb = ty % textureBoundY;
                    if (yrgb < 0) {
                        yrgb += textureBoundY;
                    }
                    final int color = scaledTextureImage.getRGB(xrgb, yrgb);
                    g2.setColor(new Color(color));
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    g2.setColor(Color.gray);
                }
                catch (ArithmeticException ex2) {
                    g2.setColor(Color.gray);
                }
                g2.drawLine(x - xmin, y - ymin, x - xmin, y - ymin);
            }
        }
        g2.dispose();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        final BufferedImage dest = img;
        g.drawImage(dest, xmin, ymin, null);
    }
    
    public void rotateTexture(final int direction) {
        switch (direction) {
            case 361: {
                this.rotAngle += 5.0f;
                break;
            }
            case 362: {
                this.rotAngle -= 5.0f;
                break;
            }
        }
    }
    
    @Override
    public void addNewPoint(final boolean drawCurve, final int x, final int y) {
        super.addNewPoint(drawCurve, x, y);
    }
    
    @Override
    public void movePointTo(final int x, final int y) {
        super.movePointTo(x, y);
    }
    
    public int hitsRef(final int x, final int y) {
        return this.selectedRef = this.perspRect.hitsPoint(x, y);
    }
    
    public Rectangle3D getTextureBounds() {
        return this.textureBounds;
    }
    
    public void setTextureBounds(final Rectangle3D textureBounds) {
        this.textureBounds = textureBounds;
    }
    
    public float getRotAngle() {
        return this.rotAngle;
    }
    
    public void setRotAngle(final float rotAngle) {
        this.rotAngle = rotAngle;
    }
    
    public void setViewWindow(final ViewWindow viewWindow) {
        this.viewWindow = viewWindow;
    }
    
    @Override
    public GeneralPath getGeneralPath() {
        if (this.points.size() == 0) {
            return null;
        }
        final GeneralPath path = new GeneralPath();
        Point2D v = this.points.elementAt(0);
        path.moveTo(v.getX(), v.getY());
        for (int i = 1; i < this.points.size(); ++i) {
            v = this.points.elementAt(i);
            path.lineTo(v.getX(), v.getY());
        }
        path.closePath();
        return path;
    }
}
