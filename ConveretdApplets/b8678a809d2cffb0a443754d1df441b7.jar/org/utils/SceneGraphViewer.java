// 
// Decompiled by Procyon v0.5.30
// 

package org.utils;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JTextArea;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.lang.reflect.Method;
import javax.media.j3d.SpotLight;
import javax.media.j3d.PointLight;
import javax.vecmath.Vector3d;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import com.sun.j3d.utils.geometry.Cylinder;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.vecmath.Vector3f;
import javax.media.j3d.AmbientLight;
import com.sun.j3d.utils.behaviors.vp.ViewPlatformBehavior;
import javax.media.j3d.Background;
import javax.vecmath.Color3f;
import javax.media.j3d.BoundingBox;
import java.util.Enumeration;
import javax.media.j3d.Appearance;
import javax.media.j3d.Shape3D;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Group;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.media.j3d.Node;
import javax.media.j3d.BoundingSphere;
import javax.vecmath.Point3d;
import javax.media.j3d.Bounds;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import javax.swing.JPanel;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Light;
import javax.media.j3d.BranchGroup;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.swing.JTree;
import java.awt.event.ActionListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JFrame;

public class SceneGraphViewer extends JFrame implements TreeSelectionListener, ActionListener
{
    private static final long serialVersionUID = 1756952597860800781L;
    private JTree treeView;
    private SimpleUniverse simpleUniverse;
    private BranchGroup current;
    private Light ambientLight;
    private Light directionalLight;
    private Light directionalLight2;
    private boolean wireRenderEnable;
    private boolean platformMovable;
    private boolean autoFocusEnable;
    private JFrame canvasWindow;
    private Canvas3D canvas;
    private JPanel panel;
    private OrbitBehavior camera;
    private final Bounds BOUNDS_WHOLE_WORLD;
    private final String ACTION_LIGHT_SWITCH = "ACTION_LIGHT_SWITCH";
    private final String ACTION_WIRE_SWITCH = "ACTION_WIRE_SWITCH";
    private final String ACTION_PLATFORM_SWITCH = "ACTION_PLATFORM_SWITCH";
    private final String ACTION_AUTOFOCUS_SWITCH = "ACTION_AUTOFOCUS_SWITCH";
    private static JFrame sgv;
    
    private SceneGraphViewer(final BranchGroup bg) {
        this.BOUNDS_WHOLE_WORLD = (Bounds)new BoundingSphere(new Point3d(), Double.POSITIVE_INFINITY);
        this.wireRenderEnable = false;
        this.platformMovable = true;
        this.autoFocusEnable = true;
        final BranchGroup bg2 = (BranchGroup)this.copyNode((Node)bg, false);
        this.initComponents(bg2);
        this.initCanvasWindow();
    }
    
    private void initCanvasWindow() {
        (this.canvasWindow = new JFrame("3D Canvas")).setSize(300, 300);
        final GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        this.canvas = new Canvas3D(gc);
        final Container cont = this.canvasWindow.getContentPane();
        cont.setLayout(new GridLayout());
        cont.add((Component)this.canvas);
    }
    
    private JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();
        final JMenu menuFile = new JMenu("File");
        final JMenu menuView = new JMenu("View");
        final JMenuItem itemRender = new JMenuItem("3D Object View");
        itemRender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                if (SceneGraphViewer.this.canvasWindow.isVisible()) {
                    SceneGraphViewer.this.canvasWindow.setVisible(false);
                }
                else {
                    final Rectangle r = SceneGraphViewer.this.getBounds();
                    SceneGraphViewer.this.canvasWindow.setLocation(r.x + r.width, r.y);
                    SceneGraphViewer.this.canvasWindow.setVisible(true);
                }
            }
        });
        menuView.add(itemRender);
        menuBar.add(menuFile);
        menuBar.add(menuView);
        return menuBar;
    }
    
    private JToolBar createToolBar() {
        final JToolBar toolBar = new JToolBar();
        final JToggleButton lightSwitch = new JToggleButton("Light");
        lightSwitch.setActionCommand("ACTION_LIGHT_SWITCH");
        lightSwitch.setSelected(true);
        lightSwitch.addActionListener(this);
        final JToggleButton wireSwitch = new JToggleButton("Wire");
        wireSwitch.setActionCommand("ACTION_WIRE_SWITCH");
        wireSwitch.setSelected(this.wireRenderEnable);
        wireSwitch.addActionListener(this);
        final JToggleButton platformSwitch = new JToggleButton("Camera Move");
        platformSwitch.setActionCommand("ACTION_PLATFORM_SWITCH");
        platformSwitch.setSelected(this.platformMovable);
        platformSwitch.addActionListener(this);
        final JToggleButton focusSwitch = new JToggleButton("Forcus");
        focusSwitch.setActionCommand("ACTION_AUTOFOCUS_SWITCH");
        focusSwitch.setSelected(this.autoFocusEnable);
        focusSwitch.addActionListener(this);
        toolBar.add(lightSwitch);
        toolBar.add(wireSwitch);
        toolBar.add(platformSwitch);
        toolBar.add(focusSwitch);
        return toolBar;
    }
    
    private void initComponents(final BranchGroup bg) {
        this.setLayout(new BorderLayout());
        (this.treeView = new JTree(Java3DDebugUtilities.createTreeNode(bg))).setRootVisible(true);
        this.treeView.addTreeSelectionListener(this);
        final TreeSelectionModel model = this.treeView.getSelectionModel();
        model.setSelectionMode(1);
        this.panel = new JPanel();
        final JSplitPane split = new JSplitPane(1, new JScrollPane(this.treeView), new JScrollPane(this.panel));
        split.setOneTouchExpandable(true);
        this.add(split, "Center");
        this.add(this.createToolBar(), "North");
        this.setJMenuBar(this.createMenuBar());
    }
    
    private void setPolygonAttributes(final Group bg, final PolygonAttributes pa) {
        final Enumeration<?> enume = (Enumeration<?>)bg.getAllChildren();
        while (enume.hasMoreElements()) {
            final Object obj = enume.nextElement();
            if (obj instanceof Shape3D) {
                Appearance app = ((Shape3D)obj).getAppearance();
                if (app == null) {
                    app = new Appearance();
                    ((Shape3D)obj).setAppearance(app);
                }
                app.setPolygonAttributes(pa);
            }
            else {
                if (!(obj instanceof Group)) {
                    continue;
                }
                this.setPolygonAttributes((Group)obj, pa);
            }
        }
    }
    
    private Node copyNode(final Node node, final boolean visualize) {
        if (node == null) {
            return null;
        }
        Node result;
        try {
            result = node.cloneNode(true);
        }
        catch (Exception e) {
            return null;
        }
        if (node instanceof Group) {
            final Group group = (Group)node;
            final Enumeration<?> enume = (Enumeration<?>)group.getAllChildren();
            while (enume.hasMoreElements()) {
                final Node child = (Node)enume.nextElement();
                if (visualize) {
                    ((Group)result).addChild(this.visualize(child));
                }
                ((Group)result).addChild(this.copyNode(child, visualize));
            }
            return result;
        }
        if (visualize) {
            final BranchGroup bgr = new BranchGroup();
            bgr.addChild(this.visualize(result));
            bgr.addChild(result);
            result = (Node)bgr;
        }
        return result;
    }
    
    private BoundingBox getBoundingBox(final Node node) {
        if (node == null) {
            return null;
        }
        BoundingBox result = null;
        if (node instanceof Group) {
            final Group group = (Group)node;
            final Enumeration<?> enume = (Enumeration<?>)group.getAllChildren();
            while (enume.hasMoreElements()) {
                final Node child = (Node)enume.nextElement();
                final BoundingBox bb = this.getBoundingBox(child);
                if (bb != null) {
                    if (result == null) {
                        result = bb;
                    }
                    else {
                        result.combine((Bounds)bb);
                    }
                }
            }
            return result;
        }
        if (node instanceof Shape3D) {
            result = (BoundingBox)node.getBounds();
            return result;
        }
        return null;
    }
    
    private void setSceneGraph(Node node) {
        try {
            node = this.copyNode(node, true);
        }
        catch (RuntimeException rEx) {
            System.err.println("java.lang.RuntimeException: cloneNode must be defined in subclass");
            return;
        }
        final BranchGroup root = new BranchGroup();
        root.addChild(node);
        final Background bg = new Background(new Color3f(0.5f, 0.5f, 0.5f));
        bg.setApplicationBounds((Bounds)new BoundingSphere(new Point3d(), Double.POSITIVE_INFINITY));
        root.addChild((Node)bg);
        if (this.simpleUniverse == null) {
            this.simpleUniverse = new SimpleUniverse(this.canvas);
            this.simpleUniverse.getViewingPlatform().setNominalViewingTransform();
            final BranchGroup lights = new BranchGroup();
            lights.addChild((Node)(this.ambientLight = this.createAbmientLight()));
            lights.addChild((Node)(this.directionalLight = this.createDirectionalLight()));
            lights.addChild((Node)(this.directionalLight2 = this.createDirectionalLight2()));
            this.simpleUniverse.addBranchGraph(lights);
        }
        else {
            if (this.current == root) {
                return;
            }
            if (this.current != null) {
                this.simpleUniverse.getLocale().removeBranchGraph(this.current);
            }
        }
        Point3d center = new Point3d();
        if (this.autoFocusEnable) {
            center = Java3DDebugUtilities.setProperViewPoint(node, this.simpleUniverse);
        }
        else {
            this.simpleUniverse.getViewingPlatform().setNominalViewingTransform();
        }
        if (this.platformMovable) {
            this.simpleUniverse.getViewingPlatform().setViewPlatformBehavior(this.createPlatformBehavior(this.canvas, center));
            final BoundingBox bb = Java3DDebugUtilities.getBoundingBox(node);
            if (bb != null) {
                final Point3d lower = new Point3d();
                final Point3d upper = new Point3d();
                bb.getLower(lower);
                bb.getUpper(upper);
                final Point3d p3d = new Point3d(Math.abs(lower.x - upper.x), Math.abs(lower.y - upper.y), Math.abs(lower.z - upper.z));
                final double factor = Math.max(p3d.x, Math.max(p3d.y, p3d.z));
                this.camera.setZoomFactor(factor);
                this.camera.setTransFactors(factor, factor);
            }
        }
        else {
            this.simpleUniverse.getViewingPlatform().setViewPlatformBehavior((ViewPlatformBehavior)null);
        }
        if (this.wireRenderEnable) {
            final PolygonAttributes polygonAttr = new PolygonAttributes();
            polygonAttr.setPolygonMode(1);
            polygonAttr.setBackFaceNormalFlip(true);
            this.setPolygonAttributes((Group)root, polygonAttr);
        }
        root.setCapability(17);
        this.simpleUniverse.addBranchGraph(root);
        this.current = root;
    }
    
    private ViewPlatformBehavior createPlatformBehavior(final Canvas3D canvas3D, final Point3d center) {
        (this.camera = new OrbitBehavior(canvas3D)).setRotationCenter(center);
        this.camera.setSchedulingBounds(this.BOUNDS_WHOLE_WORLD);
        return (ViewPlatformBehavior)this.camera;
    }
    
    private Light createAbmientLight() {
        final AmbientLight light = new AmbientLight();
        final BoundingSphere bounds = new BoundingSphere(this.BOUNDS_WHOLE_WORLD);
        light.setInfluencingBounds((Bounds)bounds);
        light.setCapability(13);
        return (Light)light;
    }
    
    private Light createDirectionalLight() {
        final DirectionalLight light = new DirectionalLight(new Color3f(1.0f, 1.0f, 1.0f), new Vector3f(1.0f, -1.0f, -1.0f));
        final BoundingSphere bounds = new BoundingSphere(this.BOUNDS_WHOLE_WORLD);
        light.setInfluencingBounds((Bounds)bounds);
        light.setCapability(13);
        return (Light)light;
    }
    
    private Light createDirectionalLight2() {
        final DirectionalLight light = new DirectionalLight(new Color3f(1.0f, 1.0f, 1.0f), new Vector3f(-1.0f, 1.0f, 1.0f));
        final BoundingSphere bounds = new BoundingSphere(this.BOUNDS_WHOLE_WORLD);
        light.setInfluencingBounds((Bounds)bounds);
        light.setCapability(13);
        return (Light)light;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        final String cmd = e.getActionCommand();
        if (cmd == "ACTION_LIGHT_SWITCH") {
            final boolean b = ((JToggleButton)e.getSource()).isSelected();
            this.ambientLight.setEnable(b);
            this.directionalLight.setEnable(b);
            this.directionalLight2.setEnable(b);
        }
        else if (cmd == "ACTION_WIRE_SWITCH") {
            this.wireRenderEnable = ((JToggleButton)e.getSource()).isSelected();
        }
        else if (cmd == "ACTION_PLATFORM_SWITCH") {
            this.platformMovable = ((JToggleButton)e.getSource()).isSelected();
        }
        else if (cmd == "ACTION_AUTOFOCUS_SWITCH") {
            this.autoFocusEnable = ((JToggleButton)e.getSource()).isSelected();
        }
    }
    
    protected Node visualize(final AmbientLight l) {
        final BranchGroup bg = new BranchGroup();
        return (Node)bg;
    }
    
    protected Node visualize(final DirectionalLight l) {
        final BranchGroup bg = new BranchGroup();
        final Vector3f vec = new Vector3f();
        l.getDirection(vec);
        final Appearance app = new Appearance();
        final Color3f c3f = new Color3f();
        l.getColor(c3f);
        app.setColoringAttributes(new ColoringAttributes(c3f, 1));
        app.setPolygonAttributes(new PolygonAttributes(1, 1, 0.0f));
        final Cylinder c = new Cylinder(0.2f, 1.0f);
        c.setAppearance(app);
        final TransformGroup tg = new TransformGroup();
        final Transform3D t3d = new Transform3D();
        t3d.lookAt(new Point3d((double)vec.x, (double)vec.y, (double)vec.z), new Point3d(), new Vector3d(0.0, 1.0, 0.0));
        tg.setTransform(t3d);
        tg.addChild((Node)c);
        bg.addChild((Node)tg);
        return (Node)bg;
    }
    
    protected Node visualize(final PointLight l) {
        final BranchGroup bg = new BranchGroup();
        return (Node)bg;
    }
    
    protected Node visualize(final SpotLight l) {
        final BranchGroup bg = new BranchGroup();
        return (Node)bg;
    }
    
    private Node visualize(final Node l) {
        final BranchGroup bg = new BranchGroup();
        try {
            final Method m = this.getClass().getDeclaredMethod("visualize", l.getClass());
            return (Node)m.invoke(this, l);
        }
        catch (Exception ex) {
            return (Node)bg;
        }
    }
    
    private Node processLights(final Light l) {
        if (l instanceof DirectionalLight) {
            final DirectionalLight dl = (DirectionalLight)l;
            final JLabel jl = new JLabel("Directional Light");
            this.setComponent(jl);
        }
        else if (l instanceof AmbientLight) {
            final AmbientLight al = (AmbientLight)l;
            final JLabel jl = new JLabel("Ambient Light");
            this.setComponent(jl);
        }
        else if (l instanceof PointLight) {
            final PointLight al2 = (PointLight)l;
            final JLabel jl = new JLabel("Ambient Light");
            this.setComponent(jl);
        }
        else if (l instanceof SpotLight) {
            final SpotLight al3 = (SpotLight)l;
            final JLabel jl = new JLabel("Ambient Light");
            this.setComponent(jl);
        }
        return this.visualize((Node)l);
    }
    
    private Node processDefault(final Node n) {
        final StringBuilder str = new StringBuilder();
        str.append(Java3DDebugUtilities.toStringSceneGraph(n));
        str.append("\n");
        final BoundingBox bb = this.getBoundingBox(n);
        if (bb != null) {
            final Point3d lower = new Point3d();
            final Point3d upper = new Point3d();
            bb.getLower(lower);
            bb.getUpper(upper);
            final Point3d center = new Point3d((lower.x + upper.x) / 2.0, (lower.y + upper.y) / 2.0, (lower.z + upper.z) / 2.0);
            final Point3d size = new Point3d(Math.abs(lower.x - upper.x), Math.abs(lower.y - upper.y), Math.abs(lower.z - upper.z));
            str.append("Center - " + center);
            str.append("\n");
            str.append("\n");
            str.append("Size - " + size);
            str.append("\n");
        }
        final JTextArea jta = new JTextArea();
        jta.setText(str.toString());
        jta.setEditable(false);
        this.setComponent(jta);
        return n;
    }
    
    private void setComponent(final JComponent c) {
        this.panel.removeAll();
        this.panel.add(c);
        this.panel.revalidate();
        this.panel.repaint();
    }
    
    @Override
    public void valueChanged(final TreeSelectionEvent e) {
        final JTree tree = (JTree)e.getSource();
        final DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
        if (node != null) {
            final Object obj = node.getUserObject();
            if (this.current == obj) {
                return;
            }
            if (obj instanceof Node) {
                if (obj instanceof Light) {
                    this.processLights((Light)obj);
                }
                else {
                    this.processDefault((Node)obj);
                }
                this.setSceneGraph((Node)obj);
            }
        }
    }
    
    public static void bootSceneGraphViewer(final BranchGroup bg) {
        (SceneGraphViewer.sgv = new SceneGraphViewer(bg)).setTitle("SceneGraph Viewer");
        SceneGraphViewer.sgv.setBounds(100, 100, 640, 480);
        SceneGraphViewer.sgv.setDefaultCloseOperation(3);
        SceneGraphViewer.sgv.setVisible(true);
    }
    
    public static void bootSceneGraphViewer(final BranchGroup bg, final int closeOp) {
        (SceneGraphViewer.sgv = new SceneGraphViewer(bg)).setTitle("SceneGraph Viewer");
        SceneGraphViewer.sgv.setBounds(100, 100, 640, 480);
        SceneGraphViewer.sgv.setDefaultCloseOperation(closeOp);
        SceneGraphViewer.sgv.setVisible(true);
    }
}
