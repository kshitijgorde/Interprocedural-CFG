// 
// Decompiled by Procyon v0.5.30
// 

package org.utils;

import javax.media.j3d.Material;
import javax.media.j3d.Appearance;
import javax.media.j3d.DirectionalLight;
import javax.vecmath.Vector3f;
import javax.media.j3d.AmbientLight;
import javax.vecmath.Color3f;
import javax.media.j3d.Light;
import java.util.Arrays;
import javax.media.j3d.Shape3D;
import java.awt.GraphicsConfiguration;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import com.sun.j3d.utils.behaviors.vp.ViewPlatformBehavior;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import javax.media.j3d.Canvas3D;
import javax.swing.JFrame;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.media.j3d.SceneGraphObject;
import javax.swing.tree.TreeNode;
import javax.media.j3d.BranchGroup;
import java.util.Enumeration;
import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.BoundingBox;
import javax.vecmath.Vector3d;
import javax.media.j3d.Transform3D;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Node;
import javax.media.j3d.BoundingSphere;
import javax.vecmath.Point3d;
import javax.media.j3d.Bounds;

public class Java3DDebugUtilities
{
    public static final Bounds BOUNDS_WHOLE_WORLD;
    
    static {
        BOUNDS_WHOLE_WORLD = (Bounds)new BoundingSphere(new Point3d(), Double.POSITIVE_INFINITY);
    }
    
    public static String toStringSceneGraph(final Node root) {
        return toStringSceneGraph(root, new StringBuffer(), 0).toString();
    }
    
    public static Point3d setProperViewPoint(final Node node, final SimpleUniverse univ) {
        final Point3d lower = new Point3d();
        final Point3d upper = new Point3d();
        final Transform3D t3d = new Transform3D();
        final BoundingBox bb = getBoundingBox(node);
        if (bb == null) {
            return new Point3d();
        }
        bb.getLower(lower);
        bb.getUpper(upper);
        final Point3d center = new Point3d((lower.x + upper.x) / 2.0, (lower.y + upper.y) / 2.0, (lower.z + upper.z) / 2.0);
        final Point3d size = new Point3d(Math.abs(lower.x - upper.x), Math.abs(lower.y - upper.y), Math.abs(lower.z - upper.z));
        double biggest = getBiggestValue(new double[] { size.x, size.y, size.z }) * 2.0;
        final double smallest = getSmallestValue(new double[] { size.x, size.y, size.z });
        if (biggest == 0.0) {
            biggest = 1.0;
        }
        final View view = univ.getViewer().getView();
        view.setBackClipPolicy(0);
        view.setBackClipDistance(biggest * 2.0);
        final TransformGroup tg = univ.getViewingPlatform().getViewPlatformTransform();
        tg.getTransform(t3d);
        if (size.x == smallest) {
            final Point3d lookAt = new Point3d(center.x + biggest, center.y, center.z);
            t3d.lookAt(lookAt, center, new Vector3d(0.0, 0.0, 1.0));
        }
        else if (size.y == smallest) {
            final Point3d lookAt = new Point3d(center.x, center.y + biggest, center.z);
            t3d.lookAt(lookAt, center, new Vector3d(1.0, 0.0, 0.0));
        }
        else if (size.z == smallest) {
            final Point3d lookAt = new Point3d(center.x, center.y, center.z + biggest);
            t3d.lookAt(lookAt, center, new Vector3d(0.0, 1.0, 0.0));
        }
        t3d.invert();
        tg.setTransform(t3d);
        return center;
    }
    
    private static StringBuffer toStringSceneGraph(final Node node, StringBuffer buf, final int depth) {
        if (node == null) {
            return buf;
        }
        for (int i = 0; i < depth; ++i) {
            buf.append(" ");
        }
        buf.append(node);
        buf.append(" : ");
        buf.append(node.getUserData());
        buf.append("\n");
        if (node instanceof Group) {
            final Group group = (Group)node;
            final Enumeration<Node> e = (Enumeration<Node>)group.getAllChildren();
            while (e.hasMoreElements()) {
                final Node child = e.nextElement();
                buf = toStringSceneGraph(child, buf, depth + 1);
            }
        }
        return buf;
    }
    
    public static Node copyNode(final Node node) {
        Node result;
        try {
            result = node.cloneNode(true);
        }
        catch (Exception e2) {
            return null;
        }
        if (node instanceof Group) {
            final Group group = (Group)node;
            final Enumeration<Node> e = (Enumeration<Node>)group.getAllChildren();
            while (e.hasMoreElements()) {
                final Node child = e.nextElement();
                ((Group)result).addChild(copyNode(child));
            }
            return result;
        }
        return result;
    }
    
    public static void printSceneGraph(final Node root) {
        printSceneGraph(root, 0);
    }
    
    private static void printSceneGraph(final Node node, final int depth) {
        for (int i = 0; i < depth; ++i) {
            System.err.print(" ");
        }
        System.err.print(node);
        System.err.println(" : " + node.getUserData());
        if (node instanceof Group) {
            final Group group = (Group)node;
            final Enumeration<Node> e = (Enumeration<Node>)group.getAllChildren();
            while (e.hasMoreElements()) {
                final Node child = e.nextElement();
                printSceneGraph(child, depth + 1);
            }
        }
    }
    
    public static TreeNode createTreeNode(final BranchGroup root) {
        return createMutableTreeNode((SceneGraphObject)root);
    }
    
    private static DefaultMutableTreeNode createMutableTreeNode(final SceneGraphObject obj) {
        final DefaultMutableTreeNode tnode = new DefaultMutableTreeNode(obj);
        if (obj instanceof Group) {
            final Group group = (Group)obj;
            final Enumeration<Node> e = (Enumeration<Node>)group.getAllChildren();
            while (e.hasMoreElements()) {
                final Node node = e.nextElement();
                if (node != null) {
                    tnode.add(createMutableTreeNode((SceneGraphObject)node));
                }
            }
        }
        return tnode;
    }
    
    public static void viewSceneGraph(final BranchGroup bg) {
        final JFrame frame = new JFrame();
        frame.setBounds(100, 100, 640, 480);
        frame.setDefaultCloseOperation(3);
        final GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        final Canvas3D canvas = new Canvas3D(gc);
        final SimpleUniverse simpleUniverse = new SimpleUniverse(canvas);
        simpleUniverse.getViewingPlatform().setNominalViewingTransform();
        final BranchGroup lights = new BranchGroup();
        lights.addChild((Node)createAbmientLight());
        lights.addChild((Node)createDirectionalLight());
        simpleUniverse.addBranchGraph(lights);
        final OrbitBehavior camera = new OrbitBehavior(canvas);
        camera.setRotationCenter(new Point3d());
        final BoundingSphere bs = new BoundingSphere(new Point3d(), Double.POSITIVE_INFINITY);
        camera.setSchedulingBounds((Bounds)bs);
        simpleUniverse.getViewingPlatform().setViewPlatformBehavior((ViewPlatformBehavior)camera);
        setProperViewPoint((Node)bg, simpleUniverse);
        simpleUniverse.addBranchGraph(bg);
        frame.getContentPane().setLayout(new GridLayout());
        frame.getContentPane().add((Component)canvas);
        frame.setVisible(true);
    }
    
    public static BoundingBox getBoundingBox(final Node node) {
        if (node == null) {
            return null;
        }
        BoundingBox result = null;
        if (node instanceof Group) {
            final Group group = (Group)node;
            final Enumeration<Node> e = (Enumeration<Node>)group.getAllChildren();
            while (e.hasMoreElements()) {
                final Node child = e.nextElement();
                final BoundingBox bb = getBoundingBox(child);
                if (bb != null) {
                    if (group instanceof TransformGroup) {
                        final Transform3D t3d = new Transform3D();
                        ((TransformGroup)group).getTransform(t3d);
                        bb.transform(t3d);
                    }
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
    
    public static BoundingBox getBoundingBox1(final Node node) {
        if (node == null) {
            return null;
        }
        BoundingBox result = null;
        if (node instanceof Group) {
            final Group group = (Group)node;
            final Enumeration<Node> e = (Enumeration<Node>)group.getAllChildren();
            while (e.hasMoreElements()) {
                final Node child = e.nextElement();
                final BoundingBox bb = getBoundingBox(child);
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
    
    protected static double getBiggestValue(final double[] darray) {
        Arrays.sort(darray);
        return darray[darray.length - 1];
    }
    
    protected static double getSmallestValue(final double[] darray) {
        Arrays.sort(darray);
        return darray[0];
    }
    
    public static Light createAbmientLight() {
        final AmbientLight light = new AmbientLight(new Color3f(0.5f, 0.5f, 0.5f));
        light.setInfluencingBounds(Java3DDebugUtilities.BOUNDS_WHOLE_WORLD);
        return (Light)light;
    }
    
    public static Light createDirectionalLight() {
        final DirectionalLight light = new DirectionalLight(new Color3f(1.0f, 1.0f, 1.0f), new Vector3f(1.0f, -1.0f, -1.0f));
        light.setInfluencingBounds(Java3DDebugUtilities.BOUNDS_WHOLE_WORLD);
        return (Light)light;
    }
    
    public static BranchGroup createLights() {
        final Light light = createAbmientLight();
        final Light dlight = createDirectionalLight();
        final BranchGroup bg = new BranchGroup();
        bg.addChild((Node)light);
        bg.addChild((Node)dlight);
        return bg;
    }
    
    public static OrbitBehavior createPlatformBehavior(final Canvas3D canvas) {
        return createPlatformBehavior(canvas, new Point3d());
    }
    
    public static OrbitBehavior createPlatformBehavior(final Canvas3D canvas, final Point3d center) {
        final OrbitBehavior camera = new OrbitBehavior(canvas);
        camera.setRotationCenter(center);
        camera.setRotationCenter(new Point3d());
        camera.setSchedulingBounds(Java3DDebugUtilities.BOUNDS_WHOLE_WORLD);
        return camera;
    }
    
    public static Canvas3D getDebugCanvas3D(final BranchGroup bg, final boolean move) {
        final Canvas3D c3d = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        final SimpleUniverse univ = new SimpleUniverse(c3d);
        univ.getViewingPlatform().setNominalViewingTransform();
        final BoundingSphere bs = new BoundingSphere(bg.getBounds());
        if (move) {
            final Point3d point3d = new Point3d();
            bs.getCenter(point3d);
            univ.getViewingPlatform().setViewPlatformBehavior((ViewPlatformBehavior)createPlatformBehavior(c3d, point3d));
        }
        univ.addBranchGraph(bg);
        univ.addBranchGraph(createLights());
        return c3d;
    }
    
    public static Appearance createTestAppearance() {
        final Appearance appearance = new Appearance();
        final Material mat = new Material();
        mat.setDiffuseColor(0.8f, 0.5f, 0.5f);
        mat.setAmbientColor(0.5f, 0.5f, 0.5f);
        appearance.setMaterial(mat);
        return appearance;
    }
}
