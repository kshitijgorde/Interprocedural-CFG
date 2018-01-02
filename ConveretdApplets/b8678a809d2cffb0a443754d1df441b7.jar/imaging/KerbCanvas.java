// 
// Decompiled by Procyon v0.5.30
// 

package imaging;

import java.awt.ScrollPane;
import java.awt.event.KeyEvent;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import imaging.math3D.Rectangle3D;
import imaging.math3D.ViewWindow;
import imaging.filters.ScaleFilter;
import java.util.Vector;
import java.awt.geom.GeneralPath;
import java.awt.Composite;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.Event;
import java.awt.Graphics;
import imaging.util.PolygonArea;
import java.util.Iterator;
import java.awt.Graphics2D;
import java.util.Hashtable;
import java.awt.image.WritableRaster;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import imaging.math3D.Point2D;
import imaging.util.Polygon;
import imaging.math3D.Vector3D;
import java.util.ArrayList;

public class KerbCanvas extends AbstractCanvas
{
    private final int CURRENTAREA = 567;
    private final int CURRENTLINE = 568;
    private String currentDrawMode;
    public static final int ZOOMIN = 351;
    public static final int ZOOMOUT = 352;
    public static final int ROTATELEFT = 361;
    public static final int ROTATERIGHT = 362;
    private int current;
    private final double WIDTH = 12.0;
    private static final long serialVersionUID = 1439109530483329826L;
    private ArrayList<ArrayList<Vector3D>> borders;
    private Polygon currentPolygon;
    private boolean damaged;
    private boolean currentToSubtract;
    private ArrayList<Polygon> polygons;
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;
    float textureScale;
    public boolean noRef;
    private int selectedRef;
    private int[][] perspRect;
    int[][] tempRect;
    boolean isPolygonCompleted;
    boolean isPolygonClosed;
    Point2D ptClicked;
    ArrayList<Point2D> ptPolyLine;
    AlphaComposite ac;
    private boolean texture;
    private BufferedImage textureImage;
    private BufferedImage scaledTextureImage;
    private float rotAngle;
    int iHeight;
    int iWidth;
    boolean isTextureApplied;
    boolean isKerbAreaSelected;
    boolean isKerbAreaHighlighted;
    int selectKerbAreaNo;
    Point2D standPoint;
    
    public KerbCanvas(final AbstractCanvas canvas) {
        super(canvas);
        this.currentDrawMode = "INSIDE";
        this.current = 568;
        this.damaged = false;
        this.xmin = Integer.MAX_VALUE;
        this.ymin = Integer.MAX_VALUE;
        this.xmax = 0;
        this.ymax = 0;
        this.textureScale = 1.0f;
        this.noRef = false;
        this.perspRect = new int[4][2];
        this.tempRect = new int[4][2];
        this.isPolygonCompleted = false;
        this.isPolygonClosed = false;
        this.ptClicked = new Point2D();
        this.ac = AlphaComposite.getInstance(2);
        this.isTextureApplied = false;
        this.isKerbAreaSelected = false;
        this.isKerbAreaHighlighted = false;
        this.selectKerbAreaNo = -1;
    }
    
    public KerbCanvas() {
        this.currentDrawMode = "INSIDE";
        this.current = 568;
        this.damaged = false;
        this.xmin = Integer.MAX_VALUE;
        this.ymin = Integer.MAX_VALUE;
        this.xmax = 0;
        this.ymax = 0;
        this.textureScale = 1.0f;
        this.noRef = false;
        this.perspRect = new int[4][2];
        this.tempRect = new int[4][2];
        this.isPolygonCompleted = false;
        this.isPolygonClosed = false;
        this.ptClicked = new Point2D();
        this.ac = AlphaComposite.getInstance(2);
        this.isTextureApplied = false;
        this.isKerbAreaSelected = false;
        this.isKerbAreaHighlighted = false;
        this.selectKerbAreaNo = -1;
    }
    
    @Override
    public BufferedImage getImage() {
        this.noRef = true;
        this.render();
        return this.image;
    }
    
    @Override
    protected void render() {
        if (this.orgImage == null) {
            return;
        }
        this.image = new BufferedImage(this.orgImage.getColorModel(), (WritableRaster)this.orgImage.getData(), this.orgImage.isAlphaPremultiplied(), null);
        this.iHeight = this.image.getHeight();
        this.iWidth = this.image.getWidth();
        this.ResetTempRect();
        if (this.polygons != null && this.polygons.size() > 0) {
            final Graphics2D graphics = (Graphics2D)this.image.getGraphics();
            if (!this.texture && !this.isPolygonCompleted) {
                for (final Polygon polygonArea : this.polygons) {
                    polygonArea.renderPoints(graphics);
                }
            }
            if (this.polygonAreas != null && this.polygonAreas.size() > 0) {
                if (this.texture) {
                    this.drawTexture(graphics);
                }
                else {
                    this.drawGrayArea(graphics);
                }
            }
            graphics.dispose();
        }
        this.repaint();
    }
    
    void ResetTempRect() {
        this.tempRect[0][0] = -100;
        this.tempRect[0][1] = -100;
        this.tempRect[1][0] = -100;
        this.tempRect[1][1] = -100;
        this.tempRect[2][0] = -100;
        this.tempRect[2][1] = -100;
        this.tempRect[3][0] = -100;
        this.tempRect[3][1] = -100;
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
                if (this.isKerbAreaHighlighted) {
                    final PolygonArea pArea = this.polygonAreas.get(this.selectKerbAreaNo);
                    final PolygonRenderer pRender = (PolygonRenderer)pArea;
                    float ang = pRender.getRotAngle();
                    ang -= 10.0f;
                    pRender.setRotAngle(ang);
                    this.rotateDirs(this.perspRect);
                    break;
                }
                this.rotAngle -= 5.0f;
                this.rotateDirs(this.perspRect);
                break;
            }
            case 362: {
                if (this.isKerbAreaHighlighted) {
                    final PolygonArea pArea = this.polygonAreas.get(this.selectKerbAreaNo);
                    final PolygonRenderer pRender = (PolygonRenderer)pArea;
                    float ang = pRender.getRotAngle();
                    ang += 10.0f;
                    pRender.setRotAngle(ang);
                    break;
                }
                this.rotAngle += 5.0f;
                this.rotateDirs(this.perspRect);
                break;
            }
        }
        this.render();
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
    
    private void highlightArea(final Graphics2D graphics) {
        if (this.selectKerbAreaNo < 0) {
            return;
        }
        final GeneralPath path = this.polygonAreas.get(this.selectKerbAreaNo).getGeneralPath();
        if (path == null) {
            return;
        }
        final Vector<Point2D> pts = this.polygonAreas.get(this.selectKerbAreaNo).getPoints();
        final int[] ptX = new int[pts.size()];
        final int[] ptY = new int[pts.size()];
        int hxMax = -10000000;
        int hxMin = 10000000;
        int hyMax = -10000000;
        int hyMin = 10000000;
        for (int i = 0; i < pts.size(); ++i) {
            final Point2D p = pts.elementAt(i);
            ptX[i] = (int)p.getX();
            ptY[i] = (int)p.getY();
            if (ptX[i] > hxMax) {
                hxMax = ptX[i];
            }
            if (ptX[i] < hxMin) {
                hxMin = ptX[i];
            }
            if (ptY[i] > hyMax) {
                hyMax = ptY[i];
            }
            if (ptY[i] < hyMin) {
                hyMin = ptY[i];
            }
        }
        this.tempRect[0][0] = hxMin;
        this.tempRect[0][1] = hyMin;
        this.tempRect[1][0] = hxMax;
        this.tempRect[1][1] = hyMin;
        this.tempRect[2][0] = hxMax;
        this.tempRect[2][1] = hyMax;
        this.tempRect[3][0] = hxMin;
        this.tempRect[3][1] = hyMax;
        final Area area = new Area(path);
        graphics.setColor(Color.red);
        graphics.drawPolygon(ptX, ptY, ptX.length);
        graphics.setColor(Color.gray);
        graphics.setComposite(AlphaComposite.getInstance(3, 0.4f));
        graphics.fillPolygon(ptX, ptY, ptX.length);
        graphics.setColor(Color.orange);
        graphics.fillOval(this.tempRect[0][0] - 5, this.tempRect[0][1] - 5, 10, 10);
        graphics.fillOval(this.tempRect[1][0] - 5, this.tempRect[1][1] - 5, 10, 10);
        graphics.fillOval(this.tempRect[3][0] - 5, this.tempRect[3][1] - 5, 10, 10);
        graphics.fillOval(this.tempRect[2][0] - 5, this.tempRect[2][1] - 5, 10, 10);
        graphics.setColor(Color.black);
        graphics.drawLine(this.tempRect[0][0], this.tempRect[0][1], this.tempRect[1][0], this.tempRect[1][1]);
        graphics.drawLine(this.tempRect[0][0], this.tempRect[0][1], this.tempRect[3][0], this.tempRect[3][1]);
        graphics.drawLine(this.tempRect[1][0], this.tempRect[1][1], this.tempRect[2][0], this.tempRect[2][1]);
        graphics.drawLine(this.tempRect[3][0], this.tempRect[3][1], this.tempRect[2][0], this.tempRect[2][1]);
    }
    
    private void drawGrayArea(final Graphics2D graphics) {
        this.renderPolyLine(graphics);
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
        graphics.fillRect(this.xmin, this.ymin, this.xmax - this.xmin, this.ymax - this.ymin);
    }
    
    public void renderPolyLine(final Graphics2D graphics) {
        if (this.ptPolyLine.size() == 0) {
            return;
        }
        final int[] x = new int[this.ptPolyLine.size()];
        final int[] y = new int[this.ptPolyLine.size()];
        for (int i = 0; i < this.ptPolyLine.size(); ++i) {
            graphics.setColor(Color.black);
            final Point2D p = this.ptPolyLine.get(i);
            x[i] = (int)p.getX();
            y[i] = (int)p.getY();
            graphics.fillOval((int)(p.getX() - 5.0f), (int)(p.getY() - 5.0f), 10, 10);
        }
        graphics.setColor(Color.blue);
        graphics.drawPolyline(x, y, x.length);
    }
    
    private void drawTexture(final Graphics2D graphics) {
        if (this.textureScale != 1.0) {
            this.scaledTextureImage = new ScaleFilter().filter(this.textureImage, this.textureScale);
        }
        else {
            this.scaledTextureImage = this.textureImage;
        }
        int iCount = 0;
        final ArrayList<Float> af = new ArrayList<Float>();
        for (final PolygonArea polygonArea : this.polygonAreas) {
            final PolygonRenderer render = (PolygonRenderer)polygonArea;
            final float ang = render.getRotAngle();
            af.add(ang);
        }
        for (final PolygonArea polygonArea : this.polygonAreas) {
            PolygonRenderer render = (PolygonRenderer)polygonArea;
            if (iCount == this.polygonAreas.size() - 1) {
                this.rotAngle = af.get(0);
            }
            else {
                this.rotAngle = af.get(iCount + 1);
            }
            render.setViewWindow(this.viewWindow);
            render.setTextureBounds(this.textureBounds);
            int txmin = 10000;
            int txmax = 0;
            int tymin = 10000;
            int tymax = 0;
            for (final Point2D pt : polygonArea.getPoints()) {
                txmin = Math.min(txmin, (int)pt.getX());
                txmin = Math.min(txmin, (int)pt.getX());
                txmax = Math.max(txmax, (int)pt.getX());
                txmax = Math.max(txmax, (int)pt.getX());
                tymin = Math.min(tymin, (int)pt.getY());
                tymin = Math.min(tymin, (int)pt.getY());
                tymax = Math.max(tymax, (int)pt.getY());
                tymax = Math.max(tymax, (int)pt.getY());
            }
            render.drawTexture(graphics, this.scaledTextureImage, this.noRef, txmin, txmax, tymin, tymax);
            ++iCount;
            this.perspRect[0] = new int[] { txmin, tymin };
            this.perspRect[1] = new int[] { txmax, tymin };
            this.perspRect[2] = new int[] { txmax, tymax };
            this.perspRect[3] = new int[] { txmin, tymax };
            this.rotateDirs(this.perspRect);
            render = null;
        }
        graphics.setClip(null);
        if (this.isKerbAreaSelected) {
            this.highlightArea(graphics);
        }
    }
    
    @Override
    public void applyTexture(final BufferedImage x) {
        this.textureImage = x;
        this.scaledTextureImage = this.textureImage;
        this.setDrawMode(false);
        if (!this.texture) {
            this.texture = true;
            if (this.viewWindow == null) {
                this.viewWindow = new ViewWindow(this.xmin, this.ymin, this.xmax - this.xmin, this.ymax - this.ymin, (float)Math.toRadians(60.0));
            }
            this.setPerspectiveType();
            this.render();
        }
        else {
            this.render();
        }
        this.isTextureApplied = true;
    }
    
    @Override
    public void clearAll() {
        this.currentPolygon = null;
        this.polygons = new ArrayList<Polygon>();
        this.polygonAreas = new ArrayList<PolygonArea>();
        this.ptPolyLine = new ArrayList<Point2D>();
        this.render();
        this.textureScale = 1.0f;
        this.textureBounds = new Rectangle3D();
        this.drawing = true;
        this.texture = false;
        this.pointSelected = false;
        this.xmin = Integer.MAX_VALUE;
        this.ymin = Integer.MAX_VALUE;
        this.xmax = 0;
        this.ymax = 0;
        this.isTextureApplied = false;
    }
    
    @Override
    public void clearDraw() {
        this.currentPolygon = null;
        this.polygons = new ArrayList<Polygon>();
        this.polygonAreas = new ArrayList<PolygonArea>();
        this.ptPolyLine = new ArrayList<Point2D>();
        this.render();
        this.drawing = true;
        this.texture = false;
        this.xmin = Integer.MAX_VALUE;
        this.ymin = Integer.MAX_VALUE;
        this.xmax = 0;
        this.ymax = 0;
        this.isTextureApplied = false;
    }
    
    @Override
    public void deleteSelectedPoint() {
        if (this.currentPolygon != null && this.pointSelected) {
            this.currentPolygon.deletePoint();
            this.pointSelected = false;
            this.render();
        }
    }
    
    public void undoLastPoint() {
        if (this.currentPolygon != null) {
            this.currentPolygon.deleteLastPoint();
            this.render();
        }
    }
    
    public void drawBorder(final String InOut) {
        if (this.currentDrawMode.equals(InOut.toUpperCase())) {
            return;
        }
        this.currentDrawMode = InOut.toUpperCase();
        this.generatePolygonsAreas();
        this.damaged = true;
        this.render();
    }
    
    @Override
    public void setDrawMode(final boolean mode) {
        this.drawing = mode;
        if (mode) {
            if (this.polygons == null) {
                this.polygons = new ArrayList<Polygon>();
            }
            this.currentPolygon = null;
            this.pointSelected = false;
        }
    }
    
    @Override
    public void zoomTexture(final double scale) {
        this.textureScale = (float)scale;
        this.render();
    }
    
    public void setPerspectiveType() {
        final float shift = (this.xmax - this.xmin) / 4;
        this.perspRect[0] = new int[] { (int)(this.xmin + shift), this.ymin };
        this.perspRect[1] = new int[] { (int)(this.xmax - shift), this.ymin };
        this.perspRect[2] = new int[] { this.xmax, this.ymax };
        this.perspRect[3] = new int[] { this.xmin, this.ymax };
        this.rotateDirs(this.perspRect);
        this.render();
    }
    
    private void rotateDirs(final int[][] prspRef) {
        final Vector3D textureDirectionU = this.textureBounds.getDirectionU();
        final Vector3D textureDirectionV = this.textureBounds.getDirectionV();
        if (this.viewWindow == null) {
            return;
        }
        float maxZ = this.viewWindow.getDistance() * Math.abs((0.0f + prspRef[2][0] - prspRef[3][0]) / (prspRef[1][0] - prspRef[0][0]));
        if (maxZ < 1.0f) {
            maxZ = 1.0f / maxZ;
        }
        maxZ -= this.viewWindow.getDistance();
        textureDirectionU.setTo(1.0f, 0.0f, 0.0f);
        textureDirectionV.setTo(0.0f, 1.0f, maxZ);
        final Vector3D normal = this.textureBounds.getNormal();
        textureDirectionU.rotateAroundVector(normal, -this.rotAngle);
        textureDirectionV.rotateAroundVector(normal, -this.rotAngle);
        textureDirectionV.normalize();
    }
    
    public ArrayList<ArrayList<Vector3D>> tranformTo3D() {
        final int imageWidth = this.image.getWidth();
        final int imageHeight = this.image.getHeight();
        if (this.polygons == null) {
            return null;
        }
        this.viewWindow = new ViewWindow(0, 0, imageWidth, imageHeight, (float)Math.toRadians(60.0));
        this.borders = new ArrayList<ArrayList<Vector3D>>(this.polygons.size());
        final float z = this.viewWindow.getDistance();
        for (final Polygon pa : this.polygons) {
            final Vector<Point2D> vPoints = pa.getPoints();
            final ArrayList<Vector3D> points = new ArrayList<Vector3D>(vPoints.size());
            for (final Point2D point : vPoints) {
                final float x = this.viewWindow.convertFromScreenXToViewX(point.getX());
                final float y = this.viewWindow.convertFromScreenYToViewY(point.getY());
                points.add(new Vector3D(x, y, z));
            }
            this.borders.add(points);
        }
        return this.borders;
    }
    
    @Override
    public void unloadImage() {
        this.render();
    }
    
    @Override
    public void mouseClicked(final MouseEvent e) {
        if (this.isTextureApplied) {
            int x = e.getX();
            int y = e.getY();
            if (this.m_zoom != 1.0) {
                final double mooz = 1.0 / this.m_zoom;
                x *= (int)mooz;
                y *= (int)mooz;
            }
            if (this.polygonAreas.size() > 0) {
                int iCount = 0;
                boolean isAreaSelected = false;
                for (final PolygonArea polygonArea : this.polygonAreas) {
                    isAreaSelected = polygonArea.isWithin(x, y);
                    if (isAreaSelected) {
                        this.isKerbAreaSelected = true;
                        this.selectKerbAreaNo = iCount;
                        this.render();
                        this.isKerbAreaHighlighted = true;
                        break;
                    }
                    ++iCount;
                }
                if (!isAreaSelected && this.isKerbAreaHighlighted) {
                    this.isKerbAreaSelected = false;
                    this.isKerbAreaHighlighted = false;
                    this.render();
                }
            }
        }
    }
    
    @Override
    public void mouseEntered(final MouseEvent e) {
    }
    
    @Override
    public void mouseExited(final MouseEvent e) {
    }
    
    @Override
    public void mousePressed(final MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (this.m_zoom != 1.0) {
            final double mooz = 1.0 / this.m_zoom;
            x *= (int)mooz;
            y *= (int)mooz;
        }
        this.ptClicked.setX(x);
        this.ptClicked.setY(y);
        if (e.getClickCount() > 1) {
            this.finishPolygon();
            this.isPolygonCompleted = true;
            this.currentPolygon = null;
            this.drawing = false;
            this.isPolygonCompleted = true;
            this.damaged = true;
            return;
        }
        if (this.polygons == null) {
            return;
        }
        if (this.polygonAreas.size() > 0) {
            for (final PolygonArea polygonArea : this.polygonAreas) {
                final boolean[] pointSelect = polygonArea.setSelectedPoint(x, y);
                if (pointSelect[0]) {
                    this.pointSelected = true;
                    this.currentArea = polygonArea;
                    this.current = 567;
                    this.damaged = true;
                    return;
                }
            }
        }
        for (final Polygon polygonArea2 : this.polygons) {
            final boolean[] pointSelect = polygonArea2.setSelectedPoint(x, y);
            if (pointSelect[0]) {
                this.pointSelected = true;
                this.currentPolygon = polygonArea2;
                if (pointSelect[1] && this.drawing) {
                    final Point2D ptTemp = this.currentPolygon.getPoints().get(0);
                    this.currentPolygon.addNewPoint((int)ptTemp.getX(), (int)ptTemp.getY());
                    this.isPolygonClosed = true;
                    this.finishPolygon();
                }
                this.isPolygonCompleted = true;
                this.current = 568;
                this.current = 567;
                this.damaged = true;
                return;
            }
        }
        this.pointSelected = false;
        if (!this.drawing) {
            return;
        }
        if (this.currentPolygon == null) {
            this.currentPolygon = new Polygon();
            this.isPolygonCompleted = false;
            this.polygons.add(this.currentPolygon);
        }
        this.currentPolygon.addNewPoint(x, y);
        this.damaged = true;
    }
    
    @Override
    public void mouseReleased(final MouseEvent e) {
        if (this.damaged) {
            this.render();
            this.damaged = false;
        }
    }
    
    @Override
    public void mouseDragged(final MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (this.m_zoom != 1.0) {
            final double mooz = 1.0 / this.m_zoom;
            x *= (int)mooz;
            y *= (int)mooz;
        }
        int iCount = 0;
        if (this.texture) {
            if (this.selectedRef >= 0) {
                this.tempRect[this.selectedRef] = new int[] { x, y };
                switch (this.selectedRef) {
                    case 0: {
                        this.tempRect[1][1] = y;
                        break;
                    }
                    case 1: {
                        this.tempRect[0][1] = y;
                        break;
                    }
                    case 2: {
                        this.tempRect[3][1] = y;
                        break;
                    }
                    case 3: {
                        this.tempRect[2][1] = y;
                        break;
                    }
                }
                this.rotateDirs(this.tempRect);
                this.damaged = true;
            }
        }
        else if (this.pointSelected) {
            if (this.current == 568) {
                this.currentPolygon.movePointTo(x, y);
            }
            else if (this.current == 567) {
                for (final Polygon tpolygon : this.polygonAreas) {
                    final boolean bAdded = tpolygon.moveRelvantPoint((int)this.ptClicked.getX(), (int)this.ptClicked.getY(), x, y);
                    if (bAdded) {
                        System.out.println("Polygon " + iCount + " Moved");
                        System.out.println("Point Moved");
                        this.MoveBorderPoint(this.ptClicked, new Point2D(x, y));
                        this.xmin = Math.min(this.xmin, x);
                        this.ymin = Math.min(this.ymin, y);
                        this.xmax = Math.max(this.xmax, x);
                        this.ymax = Math.max(this.xmax, y);
                        ++iCount;
                    }
                }
            }
            this.damaged = true;
        }
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
    
    @Override
    public void mouseMoved(final MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
        if (this.m_zoom != 1.0) {
            final double mooz = 1.0 / this.m_zoom;
            this.mouseX *= (int)mooz;
            this.mouseY *= (int)mooz;
        }
        if (this.drawing) {
            this.render();
        }
        int x = e.getX();
        int y = e.getY();
        if (this.m_zoom != 1.0) {
            final double mooz2 = 1.0 / this.m_zoom;
            x *= (int)mooz2;
            y *= (int)mooz2;
        }
        if (this.ChangeMouseCursor(x, y)) {
            this.setCursor(Cursor.getPredefinedCursor(13));
        }
        else {
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
        super.mouseMoved(e);
    }
    
    private boolean ChangeMouseCursor(final int x, final int y) {
        int leftTop = 2;
        int rightTop = 2;
        int leftBottom = 2;
        int rightBottom = 2;
        leftTop = ((this.tempRect[0][0] - x < 0) ? (x - this.tempRect[0][0]) : (this.tempRect[0][0] - x));
        rightTop = ((this.tempRect[1][0] - x < 0) ? (x - this.tempRect[1][0]) : (this.tempRect[1][0] - x));
        leftBottom = ((this.tempRect[2][0] - x < 0) ? (x - this.tempRect[2][0]) : (this.tempRect[2][0] - x));
        rightBottom = ((this.tempRect[3][0] - x < 0) ? (x - this.tempRect[3][0]) : (this.tempRect[3][0] - x));
        return leftTop <= 1 || rightTop <= 1 || leftBottom <= 1 || rightBottom <= 1;
    }
    
    public void setToSubtractCurrent(final boolean toSubtract) {
        this.currentToSubtract = toSubtract;
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
    protected void finishPolygon() {
        this.generatePolygonsAreas();
        super.finishPolygon();
    }
    
    void generatePolygonsAreas() {
        final Vector<Point2D> linePoints = this.currentPolygon.getPoints();
        Vector<Point2D> leftPoints = null;
        Vector<Point2D> rightPoints = null;
        Vector<Point2D> tLeftPoints = null;
        Vector<Point2D> tRightPoints = null;
        tLeftPoints = this.getParallelPoints(linePoints, 12.0f);
        tRightPoints = this.getParallelPoints(linePoints, -12.0f);
        final int iSide = this.getSideToOffset(linePoints, tLeftPoints, tRightPoints);
        leftPoints = new Vector<Point2D>();
        rightPoints = new Vector<Point2D>();
        final int txMin = 0;
        final int tyMin = 0;
        final int txMax = 0;
        final int tyMax = 0;
        for (final Point2D ptTemp : tLeftPoints) {
            this.xmin = Math.min(this.xmin, (int)ptTemp.getX());
            this.ymin = Math.min(this.ymin, (int)ptTemp.getY());
            this.xmax = Math.max(this.xmax, (int)ptTemp.getX());
            this.ymax = Math.max(this.ymax, (int)ptTemp.getY());
        }
        for (int k = 0; k < linePoints.size(); ++k) {
            final float rwidth = this.CalculateBorderWidth(linePoints.get(k).getY());
            if (iSide == 0) {
                tLeftPoints = this.getParallelPoints(linePoints, rwidth);
                tRightPoints = this.getParallelPoints(linePoints, rwidth * -1.0f);
            }
            else if (iSide == 1) {
                tLeftPoints = this.getParallelPoints(linePoints, rwidth * 2.0f);
                tRightPoints = this.getParallelPoints(linePoints, rwidth * 0.0f);
            }
            else if (iSide == 2) {
                tLeftPoints = this.getParallelPoints(linePoints, rwidth * 0.0f);
                tRightPoints = this.getParallelPoints(linePoints, -rwidth * 2.0f);
            }
            leftPoints.add(tLeftPoints.get(k));
            rightPoints.add(tRightPoints.get(k));
        }
        if (this.polygonAreas != null) {
            this.polygonAreas.clear();
        }
        if (this.ptPolyLine != null) {
            this.ptPolyLine.clear();
        }
        final Point2D basePoint = new Point2D();
        double angle = Double.MIN_VALUE;
        if (linePoints.size() > 1) {
            for (int i = 1; i < linePoints.size(); ++i) {
                final PolygonRenderer temp = new PolygonRenderer();
                final Point2D ptPrevLeft = leftPoints.get(i - 1);
                temp.addNewPoint(false, (int)ptPrevLeft.getX(), (int)ptPrevLeft.getY());
                final Point2D ptPrevRight = rightPoints.get(i - 1);
                temp.addNewPoint(false, (int)ptPrevRight.getX(), (int)ptPrevRight.getY());
                final Point2D ptCurRight = rightPoints.get(i);
                temp.addNewPoint(false, (int)ptCurRight.getX(), (int)ptCurRight.getY());
                final Point2D ptCurLeft = leftPoints.get(i);
                temp.addNewPoint(false, (int)ptCurLeft.getX(), (int)ptCurLeft.getY());
                final Point2D ptPrev = linePoints.get(i - 1);
                final Point2D ptCur = linePoints.get(i);
                temp.addNewPoint(false, (int)ptPrevLeft.getX(), (int)ptPrevLeft.getY());
                temp.addNewPoint(false, (int)ptPrevRight.getX(), (int)ptPrevRight.getY());
                final double dy = (ptPrevLeft.getY() + ptPrevRight.getY()) / 2.0f - (ptCurLeft.getY() + ptCurRight.getY()) / 2.0f;
                final double dx = (ptPrevLeft.getX() + ptPrevRight.getX()) / 2.0f - (ptCurLeft.getX() + ptCurRight.getX()) / 2.0f;
                angle = Math.atan2(dy, dx);
                if (Double.isNaN(angle)) {
                    angle = 1.5707963267948966;
                }
                angle = angle * 180.0 / 3.141592653589793;
                System.out.println("Angle = " + angle);
                temp.setRotAngle((float)angle);
                this.polygonAreas.add(temp);
                this.xmin = Math.min(this.xmin, Math.min((int)ptPrevLeft.getX(), (int)ptPrevRight.getX()));
                this.xmin = Math.min(this.xmin, Math.min((int)ptCurLeft.getX(), (int)ptCurRight.getX()));
                this.xmax = Math.max(this.xmax, Math.max((int)ptPrevLeft.getX(), (int)ptPrevRight.getX()));
                this.xmax = Math.max(this.xmax, Math.max((int)ptCurLeft.getX(), (int)ptCurRight.getX()));
                this.ymin = Math.min(this.ymin, Math.min((int)ptPrevLeft.getY(), (int)ptPrevRight.getY()));
                this.ymin = Math.min(this.ymin, Math.min((int)ptCurLeft.getY(), (int)ptCurRight.getY()));
                this.ymax = Math.max(this.ymax, Math.max((int)ptPrevLeft.getY(), (int)ptPrevRight.getY()));
                this.ymax = Math.max(this.ymax, Math.max((int)ptCurLeft.getY(), (int)ptCurRight.getY()));
            }
            for (int i = 0; i < linePoints.size(); ++i) {
                if (i == 0) {
                    final Point2D ptTemp2 = leftPoints.get(i);
                    this.AddBorderEdgePoint(ptTemp2);
                }
                this.AddBorderEdgePoint(rightPoints.get(i));
            }
            for (int i = linePoints.size() - 1; i >= 0; --i) {
                this.AddBorderEdgePoint(leftPoints.get(i));
                if (i == 0) {
                    this.ptPolyLine.add(leftPoints.get(i));
                }
            }
        }
    }
    
    int getSideToOffset(final Vector<Point2D> oPoints, final Vector<Point2D> lPoints, final Vector<Point2D> rPoints) {
        int iSide = -1;
        final float xSum = 0.0f;
        final float ySum = 0.0f;
        final float lSum = 0.0f;
        final float rSum = 0.0f;
        float lArea = 0.0f;
        float rArea = 0.0f;
        final Point2D centroid = new Point2D();
        final int iCount = 0;
        for (int i = 1; i < lPoints.size(); ++i) {
            final Point2D ptCurrent = lPoints.get(i);
            final Point2D ptPrevious = lPoints.get(i - 1);
            lArea += ptCurrent.getY() * ptPrevious.getX() - ptCurrent.getX() * ptPrevious.getY();
        }
        Point2D ptCurrent = lPoints.get(0);
        Point2D ptPrevious = lPoints.get(lPoints.size() - 1);
        lArea += ptCurrent.getY() * ptPrevious.getX() - ptCurrent.getX() * ptPrevious.getY();
        lArea = (float)(Math.abs(lArea) * 0.5);
        for (int i = 1; i < rPoints.size(); ++i) {
            ptCurrent = rPoints.get(i);
            ptPrevious = rPoints.get(i - 1);
            rArea += ptCurrent.getY() * ptPrevious.getX() - ptCurrent.getX() * ptPrevious.getY();
        }
        ptCurrent = rPoints.get(0);
        ptPrevious = rPoints.get(lPoints.size() - 1);
        rArea += ptCurrent.getY() * ptPrevious.getX() - ptCurrent.getX() * ptPrevious.getY();
        rArea = (float)(Math.abs(rArea) * 0.5);
        if (lArea - rArea >= -5.0f && lArea - rArea <= 5.0f) {
            iSide = 0;
        }
        else if (lArea <= rArea) {
            if (this.currentDrawMode.equals("INSIDE")) {
                iSide = 1;
            }
            else {
                iSide = 2;
            }
        }
        else if (rArea < lArea) {
            if (this.currentDrawMode.equals("INSIDE")) {
                iSide = 2;
            }
            else {
                iSide = 1;
            }
        }
        else {
            iSide = -1;
        }
        return iSide;
    }
    
    Vector<Point2D> getParallelPoints(final Vector<Point2D> oPt, final float d) {
        final Vector<Point2D> cPt = new Vector<Point2D>();
        final Object[] objT = oPt.toArray();
        final Point2D ptTemp1 = (Point2D)objT[0];
        final Point2D ptTemp2 = (Point2D)objT[objT.length - 1];
        int iptCount = 0;
        final boolean blnConsiderEdges = this.isPolygonClosed;
        iptCount = objT.length;
        final Point2D[] P = new Point2D[iptCount];
        final Point2D[] U = new Point2D[P.length];
        final Point2D[] H = new Point2D[P.length];
        for (int i = 0; i < P.length; ++i) {
            P[i] = (Point2D)objT[i];
            U[i] = new Point2D();
            H[i] = new Point2D();
        }
        final int N = P.length - 1;
        for (int k = 0; k < N; ++k) {
            final float C = P[k + 1].getX() - P[k].getX();
            final float S = P[k + 1].getY() - P[k].getY();
            final double L = Math.sqrt(C * C + S * S);
            float t = (float)(C / L);
            U[k].setX(t);
            t = (float)(S / L);
            U[k].setY(t);
        }
        if (blnConsiderEdges) {
            final float rWidth = this.CalculateBorderWidth(P[0].getY());
            final double L = d / (1.0f + U[0].getX() * U[U.length - 2].getX() + U[0].getY() * U[U.length - 2].getY());
            float t = (float)(P[0].getX() - L * (U[0].getY() + U[U.length - 2].getY()));
            H[0].setX(t);
            t = (float)(P[0].getY() + L * (U[0].getX() + U[U.length - 2].getX()));
            H[0].setY(t);
        }
        else {
            float rWidth = this.CalculateBorderWidth(P[0].getY());
            float t = P[0].getX() - d * U[0].getY();
            H[0].setX(t);
            rWidth = this.CalculateBorderWidth(P[0].getY());
            t = P[0].getY() + d * U[0].getX();
            H[0].setY(t);
        }
        for (int k = 1; k < N; ++k) {
            final float rWidth = this.CalculateBorderWidth(P[k].getY());
            final double L = d / (1.0f + U[k].getX() * U[k - 1].getX() + U[k].getY() * U[k - 1].getY());
            float t = (float)(P[k].getX() - L * (U[k].getY() + U[k - 1].getY()));
            H[k].setX(t);
            t = (float)(P[k].getY() + L * (U[k].getX() + U[k - 1].getX()));
            H[k].setY(t);
        }
        if (blnConsiderEdges) {
            H[N].setX(H[0].getX());
            H[N].setY(H[0].getY());
        }
        else {
            float rWidth = this.CalculateBorderWidth(P[N - 1].getY());
            float t = P[N].getX() - d * U[N - 1].getY();
            H[N].setX(t);
            rWidth = this.CalculateBorderWidth(P[N - 1].getY());
            t = P[N].getY() + d * U[N - 1].getX();
            H[N].setY(t);
        }
        cPt.clear();
        Point2D[] array;
        for (int length = (array = H).length, j = 0; j < length; ++j) {
            final Point2D ptTemp3 = array[j];
            cPt.add(ptTemp3);
        }
        return cPt;
    }
    
    float CalculateBorderWidth(final float y) {
        final int w = this.ymax - this.ymin;
        final float ydiff = y - this.ymin;
        double rWidth = ydiff * 12.0 / w;
        if (rWidth <= 5.5) {
            rWidth = 5.5;
        }
        return (float)rWidth;
    }
    
    boolean AddBorderEdgePoint(final Point2D ptAdd) {
        if (this.ptPolyLine == null) {
            this.ptPolyLine = new ArrayList<Point2D>();
        }
        for (final Point2D pt : this.ptPolyLine) {
            if (Math.abs(pt.getX() - ptAdd.getX()) <= 2.0f && Math.abs(pt.getY() - ptAdd.getY()) <= 2.0f) {
                return false;
            }
        }
        this.ptPolyLine.add(new Point2D(ptAdd.getX(), ptAdd.getY()));
        return true;
    }
    
    void MoveBorderPoint(final Point2D ptMove, final Point2D ptNewMove) {
        int iCount = 0;
        for (final Point2D pt : this.ptPolyLine) {
            final float diffX = Math.abs(pt.getX() - ptMove.getX());
            final float diffY = Math.abs(pt.getY() - ptMove.getY());
            if (diffX <= 5.0f && diffY <= 5.0f) {
                this.ptPolyLine.set(iCount, ptNewMove);
                System.out.println("Border Point Moved\r\n");
            }
            ++iCount;
        }
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
