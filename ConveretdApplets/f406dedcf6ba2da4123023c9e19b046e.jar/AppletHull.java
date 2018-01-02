import java.awt.Event;
import java.awt.Label;
import java.awt.Component;
import java.awt.Color;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class AppletHull extends Applet3d
{
    Panel mainPanel;
    Choice modelChoice;
    Choice dataChoice;
    Choice dataChoice2d;
    TextField dataSize;
    int[][] mpoints;
    int count;
    Points3d points3d;
    private Point3dObject3d[] points;
    
    public AppletHull() {
        this.modelChoice = null;
        this.mpoints = new int[500][2];
        this.count = 0;
        this.points3d = new Points3d();
        this.points = new Point3dObject3d[32];
    }
    
    Viewer makeViewer() {
        final Viewer viewer = new Viewer(this);
        Object3dAdaptor.normalColor = viewer.addColor("Normal", "normal objects", this.getBoolParameter("Normal"), Color.green, true);
        Triangle3d.backFaceColor = viewer.addColor(Color.blue);
        Object3dAdaptor.addColor = viewer.addColor("Added", "added objects", this.getBoolParameter("Added"), Color.red, true);
        Object3dAdaptor.deleteColor = viewer.addColor("Deleted", "deleted objects", this.getBoolParameter("Deleted"), Color.yellow, true);
        Points3d.color = viewer.addColor("Points", "input points", this.getBoolParameter("Points"), Color.black, true);
        DivideAndConquer.leftColor = viewer.addColor("Left Hull", "left convex hull", this.getBoolParameter("Left Hull"), Color.cyan, false);
        DivideAndConquer.rightColor = viewer.addColor("Right Hull", "right convex hull", this.getBoolParameter("Right Hull"), Color.magenta, false);
        Object3dAdaptor.selectColor = viewer.addColor("Selected", "selected object", this.getBoolParameter("Selected"), Color.cyan, false);
        this.dataChoice.select(this.getParameter("distribution"));
        this.dataSize.setText(this.getParameter("npoints"));
        this.setDistribution();
        return viewer;
    }
    
    public void init() {
        this.mainPanel = new Panel();
        (this.dataChoice2d = new Choice()).addItem("In Circle");
        this.dataChoice2d.addItem("On Circle");
        this.dataChoice2d.addItem("In Square");
        this.dataChoice = this.dataChoice2d;
        this.dataSize = new TextField(Integer.toString(this.points.length), 3);
        super.init();
        this.mainPanel.add(this.createModelChoice());
        super.viewer.setView();
        super.viewer.setColorVisibility(Points3d.color, true);
        this.mainPanel.remove(this.dataChoice);
        this.mainPanel.add(this.dataChoice2d, 1);
        this.dataChoice = this.dataChoice2d;
        this.validate();
        this.mainPanel.add(this.dataSize);
        this.mainPanel.add(new Label("Points"));
        if (this.getBoolParameter("controls")) {
            this.add("South", this.mainPanel);
        }
        if (this.getParameter("Points") != null) {
            super.viewer.setColorVisibility(Points3d.color, this.getBoolParameter("Points"));
        }
    }
    
    public void setDistribution() {
        final String selectedItem = this.dataChoice.getSelectedItem();
        int max;
        try {
            max = Math.max(Integer.parseInt(this.dataSize.getText()), 3);
        }
        catch (NumberFormatException ex) {
            max = 32;
        }
        final Point3d[] array = new Point3d[max];
        Point3d.setSeed(this.getIntParameter("seed", (int)System.currentTimeMillis()));
        if (selectedItem.equals("On Circle")) {
            this.randomOnCircle(array);
        }
        else if (selectedItem.equals("In Circle")) {
            this.randomInCircle(array);
        }
        else if (selectedItem.equals("In Square")) {
            this.randomInSquare(array);
        }
        this.dataSize.setText(Integer.toString(array.length));
        this.points = new Point3dObject3d[array.length];
        for (int i = 0; i < array.length; ++i) {
            this.points[i] = new Point3dObject3d(array[i]);
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target.equals(this.dataChoice) || event.target.equals(this.dataSize)) {
            this.setDistribution();
            super.viewer.setModel(this.selectModel(this.modelChoice.getSelectedItem()));
            return true;
        }
        return super.action(event, o);
    }
    
    private void randomOnCircle(final Point3d[] array) {
        for (int i = 0; i < array.length; ++i) {
            array[i] = Point3d.randomOnCircle().scale(0.75);
        }
    }
    
    private void randomInCircle(final Point3d[] array) {
        for (int i = 0; i < array.length; ++i) {
            array[i] = Point3d.randomInCircle().scale(0.75);
        }
    }
    
    private void randomInSquare(final Point3d[] array) {
        for (int i = 0; i < array.length; ++i) {
            array[i] = Point3d.random().add(new Point3d(-0.5, -0.5, -0.5)).scale(1.25, 1.25, 0.0);
        }
    }
    
    public Choice createModelChoice() {
        if (this.modelChoice == null) {
            (this.modelChoice = new Choice()).addItem("Brute Force");
            this.modelChoice.addItem("Graham's scan");
            this.modelChoice.addItem("Jarvis's march");
            this.modelChoice.addItem("Divide and Conquer");
            this.modelChoice.addItem("QuickHull");
            this.modelChoice.addItem("Incremental");
            this.modelChoice.addItem("Monotone Chain");
        }
        return this.modelChoice;
    }
    
    public Object3dList selectModel(final String s) {
        HullAlgorithm hullAlgorithm;
        if (s.equals("Brute Force")) {
            hullAlgorithm = new BruteForce(this.points);
        }
        else if (s.equals("Graham's scan")) {
            hullAlgorithm = new GrahamScan(this.points);
        }
        else if (s.equals("Jarvis's march")) {
            hullAlgorithm = new GiftWrap(this.points);
        }
        else if (s.equals("Divide and Conquer")) {
            hullAlgorithm = new DivideAndConquer(this.points);
        }
        else if (s.equals("QuickHull")) {
            hullAlgorithm = new QuickHull(this.points);
        }
        else if (s.equals("Incremental")) {
            hullAlgorithm = new Incremental(this.points);
        }
        else if (s.equals("Monotone Chain")) {
            hullAlgorithm = new ChainHull(this.points);
        }
        else {
            hullAlgorithm = new BruteForce(this.points);
        }
        final Object3dList build2D = hullAlgorithm.build2D();
        build2D.addElement(this.points3d.set(this.points));
        if (!this.getBoolParameter("vrml") && s.equals("Brute Force")) {
            for (int i = 0; i < this.points.length; ++i) {
                if (this.points[i].getSelectFrame() >= 0) {
                    build2D.addElement(new Point3dObject3d(this.points[i], this.points[i].getSelectFrame()));
                }
            }
        }
        return build2D;
    }
    
    public Object3dList defaultModel() {
        this.modelChoice.select(this.getParameter("model"));
        return this.selectModel(this.getParameter("model"));
    }
    
    public String[][] getDefaultParameters() {
        return new String[][] { { "bgcolor", "ffffff" }, { "controls", "on" }, { "dimension", "3D" }, { "model", "Brute Force" }, { "distribution", "In Sphere" }, { "npoints", "32" }, { "Normal", "on" }, { "Added", "on" }, { "Deleted", "on" }, { "Left Hull", "on" }, { "Right Hull", "on" }, { "Selected", "on" } };
    }
    
    public static void main(final String[] array) {
        new AppletHull().init();
        System.exit(0);
    }
}
