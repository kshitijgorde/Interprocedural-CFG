// 
// Decompiled by Procyon v0.5.30
// 

package imaging;

import javax.media.j3d.GraphicsContext3D;
import javax.media.j3d.DepthComponent;
import javax.media.j3d.Raster;
import javax.vecmath.Point3f;
import imaging.math3D.Rectangle3D;
import java.awt.FileDialog;
import java.awt.Frame;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import java.util.Iterator;
import javax.media.j3d.BoundingBox;
import org.utils.SceneGraphViewer;
import org.utils.Java3DDebugUtilities;
import org.jdesktop.j3d.loaders.kmz.KmzLoader;
import java.io.File;
import java.awt.event.ActionEvent;
import org.jdesktop.j3d.loaders.collada.Collada14Loader;
import java.net.URL;
import java.awt.GraphicsDevice;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Background;
import javax.media.j3d.DirectionalLight;
import javax.vecmath.Vector3f;
import javax.media.j3d.Node;
import javax.media.j3d.Bounds;
import javax.media.j3d.BoundingSphere;
import javax.vecmath.Point3d;
import javax.media.j3d.AmbientLight;
import javax.vecmath.Color3f;
import java.awt.GraphicsConfigTemplate;
import java.awt.GraphicsEnvironment;
import javax.media.j3d.GraphicsConfigTemplate3D;
import java.awt.image.BufferedImage;
import com.sun.j3d.loaders.Scene;
import imaging.math3D.Vector3D;
import java.util.ArrayList;
import javax.media.j3d.BranchGroup;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Canvas3D;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 5670443538252251255L;
    private Canvas3D canvas;
    private SimpleUniverse univ;
    private BranchGroup root;
    private BranchGroup nRoot;
    private double magFactor;
    private boolean debug;
    private ArrayList<ArrayList<Vector3D>> borderPoints;
    private ArrayList<ArrayList<Vector3D>> wayPoints;
    private Vector3D a;
    private Vector3D b;
    private Vector3D c;
    private float scaleX;
    private float scaleY;
    private Vector3D axisX;
    private double angleX;
    private double orientation;
    private final double radTen = 0.17453292519943295;
    private Scene s;
    
    public ImagePanel(final BufferedImage image) {
        this.magFactor = 1.0;
        this.debug = false;
        this.a = new Vector3D();
        this.b = new Vector3D();
        this.c = new Vector3D();
        this.axisX = new Vector3D(1.0f, 0.0f, 0.0f);
        this.orientation = 0.0;
        final GraphicsConfigTemplate3D gc3D = new GraphicsConfigTemplate3D();
        gc3D.setSceneAntialiasing(2);
        final GraphicsDevice[] gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        (this.canvas = new Canvas3D(gd[0].getBestConfiguration((GraphicsConfigTemplate)gc3D))).setSize(image.getWidth(), image.getHeight());
        this.scaleX = 0.5f * image.getWidth();
        this.scaleY = this.scaleX;
        this.univ = new SimpleUniverse(this.canvas);
        this.univ.getViewingPlatform().setNominalViewingTransform();
        (this.root = new BranchGroup()).setCapability(14);
        this.root.setCapability(12);
        this.root.setCapability(13);
        this.root.setCapability(17);
        final AmbientLight alight = new AmbientLight(new Color3f(1.0f, 1.0f, 1.0f));
        alight.setInfluencingBounds((Bounds)new BoundingSphere(new Point3d(), Double.POSITIVE_INFINITY));
        this.root.addChild((Node)alight);
        final DirectionalLight dlight = new DirectionalLight(new Color3f(1.0f, 1.0f, 1.0f), new Vector3f(-1.0f, -1.0f, -1.0f));
        dlight.setInfluencingBounds((Bounds)new BoundingSphere(new Point3d(), Double.POSITIVE_INFINITY));
        this.root.addChild((Node)dlight);
        final DirectionalLight dlight2 = new DirectionalLight(new Color3f(1.0f, 1.0f, 1.0f), new Vector3f(1.0f, 1.0f, 1.0f));
        dlight2.setInfluencingBounds((Bounds)new BoundingSphere(new Point3d(), Double.POSITIVE_INFINITY));
        this.root.addChild((Node)dlight2);
        Background bg;
        if (image == null) {
            bg = new Background(new Color3f(0.5f, 0.5f, 0.5f));
        }
        else {
            try {
                bg = new Background(new ImageComponent2D(image.getType(), image));
            }
            catch (IllegalArgumentException iae) {
                bg = new Background(new ImageComponent2D(2, image));
            }
        }
        bg.setApplicationBounds((Bounds)new BoundingSphere(new Point3d(), Double.POSITIVE_INFINITY));
        this.root.addChild((Node)bg);
        this.univ.addBranchGraph(this.root);
        this.univ.getCanvas().getView().setTransparencySortingPolicy(1);
        this.setLayout(new BorderLayout());
        this.add((Component)this.canvas, "Center");
    }
    
    public void loadKerb(final URL url) {
        this.s = null;
        try {
            final Collada14Loader l = new Collada14Loader();
            this.s = l.load(url);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (this.s == null) {
            return;
        }
        this.createScene(this.s);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        final String file = this.openDialog();
        if (file != null) {
            System.out.println("loading : " + file);
            new Thread() {
                @Override
                public void run() {
                    final File selectedFile = new File(file);
                    try {
                        ImagePanel.this.action(selectedFile);
                    }
                    catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
    
    boolean action(final File file) {
        this.s = null;
        try {
            final KmzLoader l = new KmzLoader();
            this.s = l.load(file.getAbsolutePath());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (this.s == null) {
            try {
                final Collada14Loader i = new Collada14Loader();
                this.s = i.load(file.toURI().toURL());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.s == null) {
            return false;
        }
        this.createScene(this.s);
        return true;
    }
    
    private void createScene(final Scene s) {
        final BranchGroup scene = s.getSceneGroup();
        final BoundingBox bb = Java3DDebugUtilities.getBoundingBox((Node)scene);
        final double rad = new BoundingSphere((Bounds)bb).getRadius();
        final double mag = this.magFactor / rad;
        System.out.println(mag);
        System.out.println("Magnification : " + mag);
        if (this.nRoot != null) {
            this.root.removeChild((Node)this.nRoot);
        }
        (this.nRoot = new BranchGroup()).setCapability(17);
        int k = 0;
        float scaleStart = 0.0f;
        for (final ArrayList<Vector3D> borderPoint : this.borderPoints) {
            Vector3D currentPoint = null;
            Vector3D point1 = null;
            int j = 0;
            final ArrayList<Vector3D> wayPoint = this.wayPoints.get(k++);
            for (final Vector3D point2 : borderPoint) {
                final Vector3D point3 = new Vector3D(point2.x / this.scaleX, point2.y / this.scaleY, 0.0f);
                final Vector3D locationxl = new Vector3D(point2.x - 1.0f, point2.y, point2.z);
                final Vector3D locationxr = new Vector3D(point2.x + 1.0f, point2.y, point2.z);
                final Vector3D locationyt = new Vector3D(point2.x, point2.y - 1.0f, point2.z);
                final Vector3D locationyb = new Vector3D(point2.x, point2.y + 1.0f, point2.z);
                final float xl = this.a.getDotProduct(locationxl) / this.c.getDotProduct(locationxl);
                final float xr = this.a.getDotProduct(locationxr) / this.c.getDotProduct(locationxr);
                final float yt = this.b.getDotProduct(locationyt) / this.c.getDotProduct(locationyt);
                final float yb = this.b.getDotProduct(locationyb) / this.c.getDotProduct(locationyb);
                final float scaleEnd = (float)(this.magFactor * 0.04 / Math.sqrt(Math.abs((xr - xl) * (yb - yt))));
                if (currentPoint != null) {
                    final Vector3D direct = wayPoint.get(j++);
                    final Vector3D add = new Vector3D(point3);
                    add.subtract(point1);
                    final float segmentLength = add.length();
                    add.normalize();
                    Double angle = Math.acos(add.getDotProduct(this.axisX));
                    angle += this.orientation;
                    final Vector3D increment = new Vector3D();
                    int count = 0;
                    float stepLength = 0.0f;
                    final float diffScale = (scaleEnd - scaleStart) / segmentLength;
                    System.out.println("Next point " + point3.x + " " + point3.y + " " + point3.z);
                    float scale;
                    do {
                        scale = scaleStart + diffScale * stepLength;
                        this.addObjectInstance(mag * scale, angle, scene, point1, direct);
                        increment.setTo(add);
                        increment.multiply(scale);
                        stepLength += increment.length();
                        point1.add(increment);
                    } while (stepLength < segmentLength && count++ < 100);
                    this.addObjectInstance(mag * scale, angle, scene, point1, direct);
                }
                point1 = point3;
                currentPoint = point2;
                scaleStart = scaleEnd;
            }
        }
        if (this.debug) {
            SceneGraphViewer.bootSceneGraphViewer(this.nRoot, 2);
        }
        if (bb != null) {
            final Point3d lower = new Point3d();
            final Point3d upper = new Point3d();
            bb.getLower(lower);
            bb.getUpper(upper);
        }
        this.root.addChild((Node)this.nRoot);
    }
    
    private void addObjectInstance(final double scale, final Double angle, final BranchGroup scene, final Vector3D location, final Vector3D direction) {
        if (Double.isNaN(scale) || Double.isInfinite(scale)) {
            return;
        }
        final Transform3D rotate = new Transform3D();
        rotate.rotX(-this.angleX);
        rotate.rotY((double)angle);
        final TransformGroup tgr = new TransformGroup(rotate);
        tgr.addChild(scene.cloneTree());
        final Transform3D t3d = new Transform3D();
        t3d.setScale(scale);
        t3d.setTranslation(new Vector3f(location.x, location.y, 0.0f));
        final TransformGroup tg = new TransformGroup(t3d);
        tg.addChild((Node)tgr);
        this.nRoot.addChild((Node)tg);
    }
    
    public String openDialog() {
        final FileDialog m_fileDialog = new FileDialog(new Frame(), "File Upload", 0);
        m_fileDialog.setVisible(true);
        m_fileDialog.toFront();
        final String file = m_fileDialog.getFile();
        final String directory = m_fileDialog.getDirectory();
        m_fileDialog.setVisible(false);
        if (file == null || file.length() == 0) {
            return null;
        }
        return String.valueOf(directory) + File.separator + file;
    }
    
    public void kerb2Dto3D(final ArrayList<ArrayList<Vector3D>> groups) {
        this.wayPoints = new ArrayList<ArrayList<Vector3D>>();
        this.borderPoints = new ArrayList<ArrayList<Vector3D>>();
        for (final ArrayList<Vector3D> group : groups) {
            final ArrayList<Vector3D> tmp = new ArrayList<Vector3D>();
            final ArrayList<Vector3D> direction = new ArrayList<Vector3D>();
            Vector3D previous = null;
            for (final Vector3D point : group) {
                tmp.add(new Vector3D(point.x, point.y, point.z));
                if (previous != null) {
                    final Vector3D dir = new Vector3D(point);
                    dir.subtract(previous);
                    direction.add(dir);
                }
                previous = point;
            }
            this.borderPoints.add(tmp);
            this.wayPoints.add(direction);
        }
    }
    
    public void setZoom(final float factor) {
        this.magFactor = factor;
        this.createScene(this.s);
    }
    
    public void setTextureBounds(final Rectangle3D textureBounds) {
        final Vector3D u = textureBounds.getDirectionU();
        final Vector3D v = textureBounds.getDirectionV();
        final Vector3D o = textureBounds.getOrigin();
        this.angleX = Math.acos(v.getDotProduct(new Vector3D(0.0f, 1.0f, 0.0f)));
        this.a.setToCrossProduct(v, o);
        this.b.setToCrossProduct(o, u);
        this.c.setToCrossProduct(u, v);
    }
    
    public BufferedImage getImage() {
        final GraphicsContext3D ctx = this.canvas.getGraphicsContext3D();
        final int w = this.getWidth();
        final int h = this.getHeight();
        final BufferedImage bi = new BufferedImage(w, h, 1);
        final ImageComponent2D im = new ImageComponent2D(1, bi);
        final Raster ras = new Raster(new Point3f(-1.0f, -1.0f, -1.0f), 1, 0, 0, w, h, im, (DepthComponent)null);
        ctx.readRaster(ras);
        return ras.getImage().getImage();
    }
    
    public void rotateLeft() {
        this.orientation += 0.17453292519943295;
        this.createScene(this.s);
    }
    
    public void rotateRight() {
        this.orientation -= 0.17453292519943295;
        this.createScene(this.s);
    }
}
