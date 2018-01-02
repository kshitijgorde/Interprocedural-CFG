// 
// Decompiled by Procyon v0.5.30
// 

package imaging;

import java.awt.ScrollPane;
import java.awt.event.KeyEvent;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import imaging.math3D.Rectangle3D;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.io.File;
import imaging.math3D.ViewWindow;
import java.awt.GraphicsConfiguration;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Event;
import java.awt.Graphics;
import imaging.filters.ScaleFilter;
import java.awt.geom.GeneralPath;
import java.util.Iterator;
import java.awt.Composite;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.Color;
import imaging.util.PolygonArea;
import java.awt.Graphics2D;
import java.util.Hashtable;
import java.awt.image.WritableRaster;
import java.awt.HeadlessException;
import imaging.math3D.Vector3D;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;

public class AreaCanvas extends AbstractCanvas
{
    private static final long serialVersionUID = -7159306846999238495L;
    public static final int ZOOMIN = 351;
    public static final int ZOOMOUT = 352;
    public static final int ROTATELEFT = 361;
    public static final int ROTATERIGHT = 362;
    public static final int TILTUP = 374;
    public static final int TILTLEFT = 375;
    public static final int TILTRIGHT = 376;
    public static final int TILTDOWN = 377;
    public static final short TYPE_PAVING = 1;
    public static final short TYPE_WALLS = 2;
    public static final short TYPE_FREE = 3;
    public boolean moving;
    public boolean noRef;
    private int selectedRef;
    float textureScale;
    AlphaComposite ac;
    private boolean texture;
    private BufferedImage textureImage;
    private BufferedImage scaledTextureImage;
    protected Vector3D a;
    protected Vector3D b;
    protected Vector3D c;
    public short perspectiveType;
    float xmin;
    float ymin;
    float xmax;
    float ymax;
    private boolean currentToSubtract;
    private int[][] perspRect;
    private boolean damaged;
    private float rotAngle;
    
    public AreaCanvas() throws HeadlessException {
        this.moving = false;
        this.noRef = false;
        this.textureScale = 1.0f;
        this.ac = AlphaComposite.getInstance(2);
        this.a = new Vector3D();
        this.b = new Vector3D();
        this.c = new Vector3D();
        this.perspectiveType = 1;
        this.xmin = Float.MAX_VALUE;
        this.ymin = Float.MAX_VALUE;
        this.xmax = 0.0f;
        this.ymax = 0.0f;
        this.perspRect = new int[4][2];
        this.rotAngle = 0.0f;
    }
    
    @Override
    protected void render() throws ArrayIndexOutOfBoundsException, ArithmeticException {
        if (this.orgImage != null) {
            this.image = new BufferedImage(this.orgImage.getColorModel(), (WritableRaster)this.orgImage.getData(), true, null);
            if (this.polygonAreas != null && this.polygonAreas.size() > 0) {
                final Graphics2D graphics = (Graphics2D)this.image.getGraphics();
                if (this.texture) {
                    this.drawTexture(graphics);
                }
                else {
                    this.drawGrayArea(graphics);
                }
                graphics.dispose();
            }
        }
        this.repaint();
        this.damaged = false;
    }
    
    private void drawGrayArea(final Graphics2D graphics) {
        for (final PolygonArea polygonArea : this.polygonAreas) {
            polygonArea.renderPoints(graphics);
            graphics.setColor(Color.BLUE);
            polygonArea.drawLines(graphics);
        }
        if (this.currentArea != null && this.drawing) {
            this.currentArea.drawTail(graphics, this.mouseX, this.mouseY);
        }
        GeneralPath path = this.polygonAreas.get(0).getGeneralPath();
        if (path == null) {
            return;
        }
        final Area area = new Area(path);
        for (int i = 1; i < this.polygonAreas.size(); ++i) {
            final PolygonArea pa = this.polygonAreas.get(i);
            path = pa.getGeneralPath();
            if (path != null) {
                if (pa.isSubtracted()) {
                    area.subtract(new Area(path));
                }
                else {
                    area.add(new Area(path));
                }
            }
        }
        graphics.clip(area);
        graphics.setColor(Color.gray);
        graphics.setComposite(AlphaComposite.getInstance(3, 0.65f));
        graphics.fillRect((int)this.xmin, (int)this.ymin, (int)(this.xmax - this.xmin), (int)(this.ymax - this.ymin));
    }
    
    private void drawTexture(final Graphics2D graphics) throws ArrayIndexOutOfBoundsException, ArithmeticException {
        final Area area = new Area(this.polygonAreas.get(0).getGeneralPath());
        for (int i = 1; i < this.polygonAreas.size(); ++i) {
            final PolygonArea pa = this.polygonAreas.get(i);
            if (pa.isSubtracted()) {
                area.subtract(new Area(pa.getGeneralPath()));
            }
            else {
                area.add(new Area(this.polygonAreas.get(i).getGeneralPath()));
            }
        }
        graphics.clip(area);
        if (this.textureScale != 1.0) {
            this.scaledTextureImage = new ScaleFilter().filter(this.textureImage, this.textureScale);
        }
        else {
            this.scaledTextureImage = this.textureImage;
        }
        if (this.perspectiveType == 2) {
            this.drawCurrentPolygonWall(graphics, (int)this.xmin, (int)this.ymin, (int)this.xmax, (int)this.ymax);
        }
        else {
            this.drawCurrentPolygon(graphics, (int)this.xmin, (int)this.ymin, (int)this.xmax, (int)this.ymax);
        }
        graphics.setClip(null);
        if (!this.noRef) {
            graphics.setColor(Color.orange);
            graphics.fillOval(this.perspRect[0][0] - 5, this.perspRect[0][1] - 5, 10, 10);
            graphics.fillOval(this.perspRect[1][0] - 5, this.perspRect[1][1] - 5, 10, 10);
            graphics.fillOval(this.perspRect[3][0] - 5, this.perspRect[3][1] - 5, 10, 10);
            graphics.fillOval(this.perspRect[2][0] - 5, this.perspRect[2][1] - 5, 10, 10);
            graphics.setColor(Color.black);
            graphics.drawLine(this.perspRect[0][0], this.perspRect[0][1], this.perspRect[1][0], this.perspRect[1][1]);
            graphics.drawLine(this.perspRect[0][0], this.perspRect[0][1], this.perspRect[3][0], this.perspRect[3][1]);
            graphics.drawLine(this.perspRect[1][0], this.perspRect[1][1], this.perspRect[2][0], this.perspRect[2][1]);
            graphics.drawLine(this.perspRect[3][0], this.perspRect[3][1], this.perspRect[2][0], this.perspRect[2][1]);
        }
    }
    
    @Override
    public void paint(final Graphics g) {
        if (this.m_zoom != 1.0) {
            final Graphics2D g2D = (Graphics2D)g;
            g2D.scale(this.m_zoom, this.m_zoom);
            g2D.getClipBounds();
        }
        super.paint(g);
    }
    
    @Override
    public boolean mouseDown(final Event evt, int x, int y) {
        if (this.m_zoom != 1.0) {
            final double mooz = 1.0 / this.m_zoom;
            x *= (int)mooz;
            y *= (int)mooz;
        }
        if (this.texture) {
            this.selectedRef = -1;
            for (int j = 0; j < 4; ++j) {
                if (this.hitsPoint(this.perspRect[j][0], this.perspRect[j][1], x, y)) {
                    this.selectedRef = j;
                    break;
                }
            }
            return true;
        }
        if (evt.clickCount > 1) {
            this.currentArea = null;
            return true;
        }
        if (this.polygonAreas == null) {
            return true;
        }
        for (final PolygonArea polygonArea : this.polygonAreas) {
            final boolean[] pointSelect = polygonArea.setSelectedPoint(x, y);
            if (pointSelect[0]) {
                this.pointSelected = true;
                this.currentArea = polygonArea;
                if (pointSelect[1] && this.drawing) {
                    this.finishPolygon();
                }
                else {
                    this.drawing = false;
                }
                this.render();
                return true;
            }
        }
        this.pointSelected = false;
        if (!this.drawing) {
            return true;
        }
        if (this.currentArea == null) {
            (this.currentArea = new PolygonArea()).setSubtracted(this.currentToSubtract);
            this.polygonAreas.add(this.currentArea);
        }
        this.currentArea.addNewPoint(this.drawCurve, x, y);
        this.xmin = Math.min(this.xmin, x);
        this.ymin = Math.min(this.ymin, y);
        this.xmax = Math.max(this.xmax, x);
        this.ymax = Math.max(this.ymax, y);
        this.render();
        return true;
    }
    
    private boolean ChangeMouseCursor(final int x, final int y) {
        int leftTop = 2;
        int rightTop = 2;
        int leftBottom = 2;
        int rightBottom = 2;
        if (this.perspectiveType == 2) {
            leftTop = ((this.perspRect[0][0] - x < 0) ? (x - this.perspRect[0][0]) : (this.perspRect[0][0] - x));
            rightTop = ((this.perspRect[1][0] - x < 0) ? (x - this.perspRect[1][0]) : (this.perspRect[1][0] - x));
            leftBottom = ((this.perspRect[2][0] - x < 0) ? (x - this.perspRect[2][0]) : (this.perspRect[2][0] - x));
            rightBottom = ((this.perspRect[3][0] - x < 0) ? (x - this.perspRect[3][0]) : (this.perspRect[3][0] - x));
        }
        else {
            leftTop = ((this.perspRect[2][1] - y < 0) ? (y - this.perspRect[2][1]) : (this.perspRect[2][1] - y));
            rightTop = ((this.perspRect[3][1] - y < 0) ? (y - this.perspRect[3][1]) : (this.perspRect[3][1] - y));
            leftBottom = ((this.perspRect[0][1] - y < 0) ? (y - this.perspRect[0][1]) : (this.perspRect[0][1] - y));
            rightBottom = ((this.perspRect[1][1] - y < 0) ? (y - this.perspRect[1][1]) : (this.perspRect[1][1] - y));
        }
        return leftTop <= 1 || rightTop <= 1 || leftBottom <= 1 || rightBottom <= 1;
    }
    
    @Override
    public boolean keyDown(final Event evt, final int key) {
        if (key == 43 || key == 61) {
            this.m_zoom *= 1.1;
        }
        else if ((key == 45 || key == 95) && this.m_zoom > 1.0) {
            this.m_zoom /= 1.1;
        }
        this.render();
        return true;
    }
    
    private void rotateDirs(final int[][] prspRef) {
        final Vector3D textureDirectionU = this.textureBounds.getDirectionU();
        final Vector3D textureDirectionV = this.textureBounds.getDirectionV();
        if (this.viewWindow == null) {
            return;
        }
        if (this.perspectiveType == 1) {
            float maxZ = this.viewWindow.getDistance() * Math.abs((0.0f + prspRef[2][0] - prspRef[3][0]) / (prspRef[1][0] - prspRef[0][0]));
            if (maxZ < 1.0f) {
                maxZ = 1.0f / maxZ;
            }
            maxZ -= this.viewWindow.getDistance();
            textureDirectionU.setTo(1.0f, 0.0f, 0.0f);
            textureDirectionV.setTo(prspRef[0][0] + prspRef[1][0] - prspRef[2][0] - prspRef[3][0], prspRef[3][1] - prspRef[0][1], maxZ);
            final Vector3D normal = this.textureBounds.getNormal();
            textureDirectionU.rotateAroundVector(normal, this.rotAngle);
            textureDirectionV.rotateAroundVector(normal, this.rotAngle);
            textureDirectionV.normalize();
        }
        else if (this.perspectiveType == 2) {
            float maxZ = this.viewWindow.getDistance() * Math.abs((0.0f + prspRef[2][1] - prspRef[1][1]) / (prspRef[3][1] - prspRef[0][1]));
            if (maxZ < 1.0f) {
                maxZ = 1.0f / maxZ;
            }
            maxZ -= this.viewWindow.getDistance();
            maxZ = 0.0f;
            textureDirectionV.setTo(0.0f, 1.0f, 0.0f);
            textureDirectionU.setTo(prspRef[0][0] - prspRef[1][0], prspRef[2][1] + prspRef[1][1] - prspRef[0][1] - prspRef[3][1], maxZ);
            final Vector3D normal = this.textureBounds.getNormal();
            System.out.println("Angle is = " + this.rotAngle);
            textureDirectionU.rotateAroundVector(normal, -this.rotAngle);
            textureDirectionV.rotateAroundVector(normal, -this.rotAngle);
            textureDirectionU.normalize();
        }
        else {
            float maxZ = this.viewWindow.getDistance() * Math.abs((0.0f + prspRef[2][1] - prspRef[1][1]) / (prspRef[3][1] - prspRef[0][1]));
            if (maxZ < 1.0f) {
                maxZ = 1.0f / maxZ;
            }
            maxZ -= this.viewWindow.getDistance();
            textureDirectionU.setTo(prspRef[0][0] - prspRef[1][0], 0.0f, maxZ);
            maxZ = this.viewWindow.getDistance() * Math.abs((0.0f + prspRef[2][0] - prspRef[3][0]) / (prspRef[1][0] - prspRef[0][0]));
            if (maxZ < 1.0f) {
                maxZ = 1.0f / maxZ;
            }
            maxZ -= this.viewWindow.getDistance();
            textureDirectionV.setTo(prspRef[0][0] + prspRef[1][0] - prspRef[2][0] - prspRef[3][0], prspRef[3][1] - prspRef[0][1], maxZ);
            final Vector3D normal = this.textureBounds.getNormal();
            textureDirectionU.rotateAroundVector(normal, -this.rotAngle);
            textureDirectionV.rotateAroundVector(normal, -this.rotAngle);
            textureDirectionU.normalize();
            textureDirectionV.normalize();
        }
    }
    
    protected void drawCurrentPolygon(final Graphics2D g, final int xmin, final int ymin, final int xmax, final int ymax) throws ArrayIndexOutOfBoundsException, ArithmeticException {
        final Vector3D viewPos = new Vector3D();
        final Vector3D o = this.textureBounds.getOrigin();
        o.setTo(0.0f, 0.0f, this.viewWindow.getDistance());
        final Vector3D u = this.textureBounds.getDirectionU();
        final Vector3D v = this.textureBounds.getDirectionV();
        this.a.setToCrossProduct(v, o);
        this.b.setToCrossProduct(o, u);
        this.c.setToCrossProduct(u, v);
        final int textureBoundX = this.scaledTextureImage.getWidth();
        final int textureBoundY = this.scaledTextureImage.getHeight();
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
                final int color = this.scaledTextureImage.getRGB(xrgb, yrgb);
                g2.setColor(new Color(color));
                g2.drawLine(x - xmin, y - ymin, x - xmin, y - ymin);
            }
        }
        g2.dispose();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        final BufferedImage dest = img;
        g.drawImage(dest, xmin, ymin, null);
    }
    
    protected void drawCurrentPolygonPaving(final Graphics2D g, final int xmin, final int ymin, final int xmax, final int ymax) throws ArrayIndexOutOfBoundsException, ArithmeticException {
        final Vector3D viewPos = new Vector3D();
        final Vector3D o = this.textureBounds.getOrigin();
        o.setTo(0.0f, 0.0f, this.viewWindow.getDistance());
        final Vector3D u = this.textureBounds.getDirectionU();
        final Vector3D v = this.textureBounds.getDirectionV();
        this.a.setToCrossProduct(v, o);
        this.b.setToCrossProduct(o, u);
        this.c.setToCrossProduct(u, v);
        final int textureBoundX = this.scaledTextureImage.getWidth();
        final int textureBoundY = this.scaledTextureImage.getHeight();
        viewPos.z = this.viewWindow.getDistance();
        int tx = 0;
        int ty = 0;
        final int width = xmax - xmin;
        final int height = ymax - ymin;
        final GraphicsConfiguration gc = g.getDeviceConfiguration();
        BufferedImage img = gc.createCompatibleImage(width, height, 1);
        final Graphics2D g2 = img.createGraphics();
        for (int y = ymin; y <= ymax; ++y) {
            viewPos.y = this.viewWindow.convertFromScreenYToViewY(y);
            for (int x = xmin; x <= xmax; ++x) {
                viewPos.x = this.viewWindow.convertFromScreenXToViewX(x);
                tx = (int)viewPos.x;
                ty = (int)viewPos.y;
                int xrgb = tx % textureBoundX;
                if (xrgb < 0) {
                    xrgb += textureBoundX;
                }
                int yrgb = ty % textureBoundY;
                if (yrgb < 0) {
                    yrgb += textureBoundY;
                }
                final int color = this.scaledTextureImage.getRGB(xrgb, yrgb);
                g2.setColor(new Color(color));
                g2.drawLine(x - xmin, y - ymin, x - xmin, y - ymin);
            }
        }
        g2.dispose();
        float tmax = -1000.0f;
        float tmin = 1000.0f;
        final float shift = (xmax - xmin) / 4;
        if (this.perspRect[0][0] - shift < tmin) {
            tmin = this.perspRect[0][0] - shift;
        }
        if (this.perspRect[0][0] - shift > tmax) {
            tmax = this.perspRect[0][0] - shift;
        }
        if (this.perspRect[1][0] + shift < tmin) {
            tmin = this.perspRect[1][0] + shift;
        }
        if (this.perspRect[1][0] + shift > tmax) {
            tmax = this.perspRect[1][0] + shift;
        }
        if (this.perspRect[2][0] < tmin) {
            tmin = this.perspRect[2][0];
        }
        if (this.perspRect[2][0] > tmax) {
            tmax = this.perspRect[2][0];
        }
        if (this.perspRect[3][0] < tmin) {
            tmin = this.perspRect[3][0];
        }
        if (this.perspRect[3][0] > tmax) {
            tmax = this.perspRect[3][0];
        }
        final double a = (this.perspRect[0][0] - tmin) / (tmax - tmin);
        final double b = (this.perspRect[1][0] - tmin) / (tmax - tmin);
        final double c = (this.perspRect[3][0] - tmin) / (tmax - tmin);
        final double d = (this.perspRect[2][0] - tmin) / (tmax - tmin);
        img = createTrapeziumVertical(img, a, b, c, d);
        g2.dispose();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        final BufferedImage dest = img;
        g.drawImage(dest, xmin, ymin, null);
    }
    
    protected void drawCurrentPolygonWall(final Graphics2D g, final int xmin, final int ymin, final int xmax, final int ymax) throws ArrayIndexOutOfBoundsException, ArithmeticException {
        final Vector3D viewPos = new Vector3D();
        final Vector3D o = this.textureBounds.getOrigin();
        o.setTo(0.0f, 0.0f, this.viewWindow.getDistance());
        final Vector3D u = this.textureBounds.getDirectionU();
        final Vector3D v = this.textureBounds.getDirectionV();
        this.a.setToCrossProduct(v, o);
        this.b.setToCrossProduct(o, u);
        this.c.setToCrossProduct(u, v);
        final int textureBoundX = this.scaledTextureImage.getWidth();
        final int textureBoundY = this.scaledTextureImage.getHeight();
        viewPos.z = this.viewWindow.getDistance();
        int tx = 0;
        int ty = 0;
        final int width = xmax - xmin;
        final int height = ymax - ymin;
        final GraphicsConfiguration gc = g.getDeviceConfiguration();
        BufferedImage img = gc.createCompatibleImage(width, height, 1);
        final Graphics2D g2 = img.createGraphics();
        for (int y = ymin; y <= ymax; ++y) {
            viewPos.y = this.viewWindow.convertFromScreenYToViewY(y);
            for (int x = xmin; x <= xmax; ++x) {
                viewPos.x = this.viewWindow.convertFromScreenXToViewX(x);
                tx = (int)viewPos.x;
                ty = (int)viewPos.y;
                int xrgb = tx % textureBoundX;
                if (xrgb < 0) {
                    xrgb += textureBoundX;
                }
                int yrgb = ty % textureBoundY;
                if (yrgb < 0) {
                    yrgb += textureBoundY;
                }
                final int color = this.scaledTextureImage.getRGB(xrgb, yrgb);
                g2.setColor(new Color(color));
                g2.drawLine(x - xmin, y - ymin, x - xmin, y - ymin);
            }
        }
        g2.dispose();
        int tmax = -1000;
        int tmin = 1000;
        if (this.perspRect[0][1] < tmin) {
            tmin = this.perspRect[0][1];
        }
        if (this.perspRect[0][1] > tmax) {
            tmax = this.perspRect[0][1];
        }
        if (this.perspRect[1][1] < tmin) {
            tmin = this.perspRect[1][1];
        }
        if (this.perspRect[1][1] > tmax) {
            tmax = this.perspRect[1][1];
        }
        if (this.perspRect[2][1] < tmin) {
            tmin = this.perspRect[2][1];
        }
        if (this.perspRect[2][1] > tmax) {
            tmax = this.perspRect[2][1];
        }
        if (this.perspRect[3][1] < tmin) {
            tmin = this.perspRect[3][1];
        }
        if (this.perspRect[3][1] > tmax) {
            tmax = this.perspRect[3][1];
        }
        final double a = (this.perspRect[0][1] - tmin) / (tmax - tmin);
        final double b = (this.perspRect[3][1] - tmin) / (tmax - tmin);
        final double c = (this.perspRect[1][1] - tmin) / (tmax - tmin);
        final double d = (this.perspRect[2][1] - tmin) / (tmax - tmin);
        img = createTrapezium(img, a, b, c, d);
        g2.dispose();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        final BufferedImage dest = img;
        g.drawImage(dest, xmin + (this.perspRect[0][0] - xmin), ymin + (tmin - ymin), null);
    }
    
    @Override
    public void applyTexture(final BufferedImage image) {
        this.textureImage = image;
        this.scaledTextureImage = this.textureImage;
        this.setDrawMode(false);
        if (!this.texture) {
            this.texture = true;
            int ang = 60;
            if (this.perspectiveType == 2) {
                ang = 90;
            }
            if (this.viewWindow == null) {
                this.viewWindow = new ViewWindow((int)this.xmin, (int)this.ymin, (int)(this.xmax - this.xmin), (int)(this.ymax - this.ymin), (float)Math.toRadians(ang));
            }
            this.setPerspectiveType(this.perspectiveType);
        }
        else {
            this.render();
        }
    }
    
    private static BufferedImage createTrapezium(final BufferedImage src, final double a, final double b, final double c, final double d) {
        final int w = src.getWidth();
        final int h = src.getHeight();
        System.out.println("w=" + w + ",h=" + h);
        final double A = h * a;
        final double B = h * b;
        final double C = h * c;
        final double D = h * d;
        final BufferedImage dest = new BufferedImage(w, h, 2);
        for (int x = 0; x < w; ++x) {
            final double x_to_w = x / w;
            final double C_A = A - C;
            final double C_A_offset = C_A * (1.0 - x_to_w);
            final double offset = C + C_A_offset;
            final double trapeziumLine = C_A - C_A_offset + (B - A) + (D - B) * x_to_w;
            final double k = trapeziumLine / h;
            for (int y = 0; y < h; ++y) {
                try {
                    final int destY = Math.min(h - 1, (int)Math.round(offset + y * k));
                    dest.setRGB(x, destY, src.getRGB(x, y));
                }
                catch (Exception ex) {
                    System.out.println("Exception" + ex + "x=" + x + ",y=" + y);
                }
            }
        }
        try {
            ImageIO.write(dest, "jpg", new File("c:\\temp1.jpg"));
        }
        catch (Exception ex2) {}
        return dest;
    }
    
    private static int getTrapeziumX(final int w, final int h, final double a, final double b, final double c, final double d, final int srcX, final int srcY) {
        final double A = w * a;
        final double B = w * b;
        final double C = w * c;
        final double D = w * d;
        int destX = srcX;
        final int x = 0;
        final int y = 0;
        try {
            final double y_to_h = srcY / h;
            final double C_A = A - C;
            final double C_A_offset = C_A * (1.0 - y_to_h);
            final double offset = C + C_A_offset;
            final double trapeziumLine = C_A - C_A_offset + (B - A) + (D - B) * y_to_h;
            final double k = trapeziumLine / w;
            destX = Math.min(w - 1, (int)Math.round(offset + srcX * k));
        }
        catch (Exception exp) {
            System.out.println("x=" + x + ",y=" + y);
        }
        return destX;
    }
    
    private static BufferedImage createTrapeziumVertical(final BufferedImage src, final double a, final double b, final double c, final double d) {
        final int w = src.getWidth();
        final int h = src.getHeight();
        System.out.println("w=" + w + ",h=" + h);
        final double A = w * a;
        final double B = w * b;
        final double C = w * c;
        final double D = w * d;
        int x = 0;
        int y = 0;
        int ty = 0;
        final BufferedImage dest = new BufferedImage(w, h, 2);
        try {
            for (y = 0; y < h; ++y) {
                final double y_to_h = y / h;
                final double C_A = A - C;
                final double C_A_offset = C_A * (1.0 - y_to_h);
                final double offset = C + C_A_offset;
                final double trapeziumLine = C_A - C_A_offset + (B - A) + (D - B) * y_to_h;
                final double k = trapeziumLine / w;
                for (x = 0; x < w; ++x) {
                    final int destX = Math.min(w - 1, (int)Math.round(offset + x * k));
                    ty = (int)(y * (4.0f - y / h));
                    if (ty >= h) {
                        ty %= h;
                    }
                    dest.setRGB(destX, y, src.getRGB(x, ty));
                }
            }
            try {
                ImageIO.write(dest, "jpg", new File("c:\\temp1.jpg"));
            }
            catch (Exception ex) {}
        }
        catch (Exception exp) {
            System.out.println("x=" + x + ",y=" + y);
        }
        return dest;
    }
    
    @Override
    public void loadImage(final BufferedImage pImage) {
        super.loadImage(pImage);
        this.clearAll();
        this.render();
    }
    
    @Override
    public void clearDraw() {
        if (this.texture) {
            this.texture = false;
        }
        else {
            this.textureBounds = new Rectangle3D();
            if (this.drawing) {
                this.polygonAreas = new ArrayList<PolygonArea>();
            }
            this.pointSelected = true;
        }
        this.render();
    }
    
    @Override
    public void clearAll() {
        this.textureScale = 1.0f;
        this.textureBounds = new Rectangle3D();
        this.texture = false;
        if (this.drawing) {
            this.polygonAreas = new ArrayList<PolygonArea>();
        }
        else {
            this.polygonAreas = null;
        }
        this.currentArea = null;
        this.pointSelected = false;
        this.xmin = Float.MAX_VALUE;
        this.ymin = Float.MAX_VALUE;
        this.xmax = 0.0f;
        this.ymax = 0.0f;
        this.render();
    }
    
    @Override
    public void tranformTexture(final int form) {
        switch (form) {
            case 351: {
                this.textureScale *= 1.1;
                break;
            }
            case 352: {
                this.textureScale /= 1.1;
                break;
            }
            case 361: {
                this.rotAngle += 5.0f;
                this.rotateDirs(this.perspRect);
                break;
            }
            case 362: {
                this.rotAngle -= 5.0f;
                this.rotateDirs(this.perspRect);
                break;
            }
        }
        this.render();
    }
    
    @Override
    public void zoomTexture(final double scale) {
        this.textureScale = (float)scale;
        this.render();
    }
    
    @Override
    public void setDrawMode(final boolean mode) {
        this.drawing = mode;
        if (mode) {
            if (this.polygonAreas == null) {
                this.polygonAreas = new ArrayList<PolygonArea>();
            }
            this.currentArea = null;
            this.pointSelected = false;
        }
    }
    
    public void setToSubtractCurrent(final boolean toSubtract) {
        this.currentToSubtract = toSubtract;
    }
    
    public void setPerspectiveType(final short type) {
        this.perspectiveType = type;
        if (type == 1 || type == 3) {
            final float shift = (this.xmax - this.xmin) / 4.0f;
            this.perspRect[0] = new int[] { (int)(this.xmin + shift), (int)this.ymin };
            this.perspRect[1] = new int[] { (int)(this.xmax - shift), (int)this.ymin };
            this.perspRect[2] = new int[] { (int)this.xmax, (int)this.ymax };
            this.perspRect[3] = new int[] { (int)this.xmin, (int)this.ymax };
        }
        else {
            this.perspRect[0] = new int[] { (int)this.xmin, (int)this.ymin };
            this.perspRect[1] = new int[] { (int)this.xmax, (int)this.ymin };
            this.perspRect[2] = new int[] { (int)this.xmax, (int)this.ymax };
            this.perspRect[3] = new int[] { (int)this.xmin, (int)this.ymax };
        }
        this.rotateDirs(this.perspRect);
        this.render();
    }
    
    @Override
    public BufferedImage getImage() {
        this.noRef = true;
        this.render();
        return this.image;
    }
    
    @Override
    public void deleteSelectedPoint() {
        if (this.currentArea != null && this.pointSelected) {
            this.currentArea.deletePoint();
            this.pointSelected = false;
            this.render();
        }
    }
    
    @Override
    public void unloadImage() {
        this.image = null;
        this.orgImage = null;
        this.render();
    }
    
    public void restoreRef() {
        this.noRef = false;
        this.render();
    }
    
    @Override
    public void mouseClicked(final MouseEvent arg0) {
        this.requestFocusInWindow();
    }
    
    @Override
    public void mouseEntered(final MouseEvent arg0) {
    }
    
    @Override
    public void mouseExited(final MouseEvent arg0) {
    }
    
    @Override
    public void mousePressed(final MouseEvent mev) {
        int x = mev.getX();
        int y = mev.getY();
        if (this.m_zoom != 1.0) {
            final double mooz = 1.0 / this.m_zoom;
            x *= (int)mooz;
            y *= (int)mooz;
        }
        if (this.texture) {
            this.selectedRef = -1;
            for (int j = 0; j < 4; ++j) {
                if (this.hitsPoint(this.perspRect[j][0], this.perspRect[j][1], x, y)) {
                    this.selectedRef = j;
                    break;
                }
            }
            return;
        }
        if (mev.getClickCount() > 1) {
            this.currentArea = null;
            return;
        }
        if (this.polygonAreas == null) {
            return;
        }
        for (final PolygonArea polygonArea : this.polygonAreas) {
            final boolean[] pointSelect = polygonArea.setSelectedPoint(x, y);
            if (pointSelect[0]) {
                this.pointSelected = true;
                this.currentArea = polygonArea;
                if (pointSelect[1] && this.drawing) {
                    this.finishPolygon();
                }
                else {
                    this.drawing = false;
                }
                this.render();
                return;
            }
        }
        this.pointSelected = false;
        if (!this.drawing) {
            return;
        }
        if (this.currentArea == null) {
            (this.currentArea = new PolygonArea()).setSubtracted(this.currentToSubtract);
            this.polygonAreas.add(this.currentArea);
        }
        this.currentArea.addNewPoint(this.drawCurve, x, y);
        this.xmin = Math.min(this.xmin, x);
        this.ymin = Math.min(this.ymin, y);
        this.xmax = Math.max(this.xmax, x);
        this.ymax = Math.max(this.ymax, y);
        this.render();
    }
    
    @Override
    public void mouseReleased(final MouseEvent arg0) {
        if (this.damaged) {
            this.render();
        }
    }
    
    @Override
    public void mouseDragged(final MouseEvent dev) {
        int x = dev.getX();
        int y = dev.getY();
        if (x < 0 || x > this.getWidth() || y < 0 || y > this.getHeight()) {
            return;
        }
        if (this.m_zoom != 1.0) {
            final double mooz = 1.0 / this.m_zoom;
            x *= (int)mooz;
            y *= (int)mooz;
        }
        if (this.texture) {
            if (this.selectedRef >= 0) {
                this.perspRect[this.selectedRef] = new int[] { x, y };
                if (this.perspectiveType == 1) {
                    switch (this.selectedRef) {
                        case 0: {
                            this.perspRect[1][1] = y;
                            break;
                        }
                        case 1: {
                            this.perspRect[0][1] = y;
                            break;
                        }
                        case 2: {
                            this.perspRect[3][1] = y;
                            break;
                        }
                        case 3: {
                            this.perspRect[2][1] = y;
                            break;
                        }
                    }
                }
                else if (this.perspectiveType == 2) {
                    switch (this.selectedRef) {
                        case 0: {
                            this.perspRect[3][0] = x;
                            break;
                        }
                        case 1: {
                            this.perspRect[2][0] = x;
                            break;
                        }
                        case 2: {
                            this.perspRect[1][0] = x;
                            break;
                        }
                        case 3: {
                            this.perspRect[0][0] = x;
                            break;
                        }
                    }
                }
                this.rotateDirs(this.perspRect);
                this.damaged = true;
            }
        }
        else if (this.pointSelected) {
            this.currentArea.movePointTo(x, y);
            this.damaged = true;
            this.xmin = Math.min(this.xmin, x);
            this.ymin = Math.min(this.ymin, y);
            this.xmax = Math.max(this.xmax, x);
            this.ymax = Math.max(this.ymax, y);
        }
    }
    
    @Override
    public void mouseMoved(final MouseEvent mev) {
        int x = mev.getX();
        int y = mev.getY();
        if (this.m_zoom != 1.0) {
            final double mooz = 1.0 / this.m_zoom;
            x *= (int)mooz;
            y *= (int)mooz;
        }
        if (this.ChangeMouseCursor(x, y)) {
            this.setCursor(Cursor.getPredefinedCursor(13));
        }
        else {
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
        super.mouseMoved(mev);
    }
    
    @Override
    public void keyPressed(final KeyEvent kev) {
        final char key = kev.getKeyChar();
        if (key == '+' || key == '=') {
            this.m_zoom *= 1.1;
            this.setSize((int)(this.getWidth() * 1.1), (int)(this.getHeight() * 1.1));
        }
        else if ((key == '-' || key == '_') && this.m_zoom > 1.0) {
            this.m_zoom /= 1.1;
            this.setSize((int)(this.getWidth() / 1.1), (int)(this.getHeight() / 1.1));
        }
        this.render();
        final ScrollPane pTemp = (ScrollPane)this.getParent();
        pTemp.doLayout();
        pTemp.invalidate();
    }
    
    @Override
    public void applyZoom(final String IO) {
        if (IO.equals("+")) {
            this.m_zoom *= 1.1;
            this.setSize((int)(this.getWidth() * 1.1), (int)(this.getHeight() * 1.1));
        }
        else if (IO.equals("-") && this.m_zoom > 1.0) {
            this.m_zoom /= 1.1;
            this.setSize((int)(this.getWidth() / 1.1), (int)(this.getHeight() / 1.1));
        }
        this.render();
        final ScrollPane pTemp = (ScrollPane)this.getParent();
        pTemp.doLayout();
        pTemp.invalidate();
    }
}
